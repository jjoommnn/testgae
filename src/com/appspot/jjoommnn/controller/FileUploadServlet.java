package com.appspot.jjoommnn.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.FileInfo;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class FileUploadServlet extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("myFile");
        Map<String, List<FileInfo>> files = blobstoreService.getFileInfos( req );
        List<FileInfo> fileInfo = files.get("myFile");

        if (blobKey == null)
        {
            res.sendRedirect("/");
        }
        else
        {
        	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
        	String bks = blobKey.getKeyString();
        	FileInfo fi = fileInfo.get( 0 );
        	
        	Entity file = new Entity( "File", bks );
        	file.setProperty( "blobKey", bks );
        	file.setProperty( "fileName", fi.getFilename() );
        	file.setProperty( "fileType", fi.getContentType() );
        	
        	ds.put( file );
        	
            res.sendRedirect("/doneUpload.do?blobKey=" + blobKey.getKeyString());
        }
	}
}
