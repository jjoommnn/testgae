package com.appspot.jjoommnn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.FileInfo;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@Controller
public class TestController
{
	@RequestMapping("/register.do")
	public String register()
	{
		return "register";
	}
	
	@RequestMapping("/doRegister.do")
	@ResponseBody
	public void doRegister( HttpServletRequest request )
	{
		String userId = request.getParameter( "userId" );
		String userName = request.getParameter( "userName" );
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity user = new Entity( "User", userId );
		user.setProperty( "userId", userId );
		user.setProperty( "userName", userName );
		
		ds.put( user );
	}
	
	@RequestMapping( "/list.do" )
	public String list( Model model )
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query( "User" );
		PreparedQuery pq = ds.prepare(q);
		
		ArrayList userList = new ArrayList();
		
		for( Entity e : pq.asIterable() )
		{
			HashMap user = new HashMap();
			user.put( "userId", e.getProperty( "userId" ) );
			user.put( "userName", e.getProperty( "userName") );
			userList.add( user );
		}
		
		model.addAttribute( "userList", userList );
		
		return "list";
	}
	
	@RequestMapping( "/upload.do" )
	public String upload()
	{
		return "upload";
	}
	
	@RequestMapping( "/doUpload.do" )
	@ResponseBody
	public void doUpload( HttpServletRequest request, HttpServletResponse response ) throws Exception
	{
	    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads( request );
        List<BlobKey> blobKeys = blobs.get("myFile");
        Map<String, List<FileInfo>> files = blobstoreService.getFileInfos( request );
        List<FileInfo> fileInfos = files.get("myFile");

        if (blobKeys == null || blobKeys.size() <= 0 )
        {
            response.sendError( 500 );
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
            file.setProperty(  "fileUploadDate", new Date() );
            
            ds.put( file );
            
            response.getWriter().print( bks );
        }
	}
	
	@RequestMapping( "/listUpload.do" )
	public String listUpload( Model model ) throws Exception
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query( "File" );
		PreparedQuery pq = ds.prepare(q);
		
		ArrayList fileList = new ArrayList();
		
		for( Entity e : pq.asIterable() )
		{
			HashMap file = new HashMap();
			file.put( "blobKey", e.getProperty( "blobKey" ) );
			file.put( "fileName", e.getProperty( "fileName") );
			file.put( "fileType", e.getProperty( "fileType" ) );
			file.put(  "fileSize", e.getProperty( "fileSize" ) );
			file.put(  "fileUploadDate", e.getProperty( "fileUploadDate" ) );
			fileList.add( file );
		}
		
		model.addAttribute( "fileList", fileList );
		
		return "listUpload";
	}
}
