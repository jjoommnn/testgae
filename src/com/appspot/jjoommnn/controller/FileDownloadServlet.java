package com.appspot.jjoommnn.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class FileDownloadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String bks = req.getParameter( "blobKey" );
		
		Key fileKey = KeyFactory.createKey( "File", bks );
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        
        Query q = new Query( "File", fileKey );
        PreparedQuery pq = ds.prepare( q );
        
        Entity fileInfo = null;
        for( Entity e : pq.asIterable() )
        {
            fileInfo = e;
            break;
        }
        
        if( fileInfo == null )
        {
            res.sendError( 404 );
            return;
        }
        
        String fileName = (String)fileInfo.getProperty( "fileName" );
        fileName = URLEncoder.encode( fileName, "UTF-8" );
        fileName = fileName.replaceAll( "\\+", " " ); 
        
        res.setHeader( "Content-Disposition", "attachment; filename=" + fileName );
		
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		
		BlobKey blobKey = new BlobKey( bks );
        blobstoreService.serve( blobKey, res );
	}
}
