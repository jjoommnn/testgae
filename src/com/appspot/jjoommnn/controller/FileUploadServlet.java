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
	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads( req );
        List<BlobKey> blobKeys = blobs.get("myFile");
        Map<String, List<FileInfo>> files = blobstoreService.getFileInfos( req );
        List<FileInfo> fileInfos = files.get("myFile");

        if (blobKeys == null || blobKeys.size() <= 0 )
        {
            res.sendRedirect("/");
        }
        else
        {
        	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
        	BlobKey bk = blobKeys.get( 0 );
        	FileInfo fi = fileInfos.get( 0 );
        	
        	String bks = bk.getKeyString();
        	
        	Entity file = new Entity( "File", bks );
        	file.setProperty( "blobKey", bks );
        	file.setProperty( "fileName", fi.getFilename() );
        	file.setProperty( "fileType", fi.getContentType() );
        	file.setProperty( "fileSize", fi.getSize() );
        	
        	ds.put( file );
        	
            res.sendRedirect("/doneUpload.do?blobKey=" + bks);
        }
	}
}
