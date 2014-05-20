package com.appspot.jjoommnn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
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
	public String doRegister( HttpServletRequest request )
	{
		String userId = request.getParameter( "userId" );
		String userName = request.getParameter( "userName" );
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity user = new Entity( "User", userId );
		user.setProperty( "userId", userId );
		user.setProperty( "userName", userName );
		
		ds.put( user );
		
		return "doneRegister";
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
	
	@RequestMapping( "/doneUpload.do" )
	public String doUpload( HttpServletRequest request, Model model )
	{
		String blobKey = request.getParameter( "blobKey" );
		
        model.addAttribute( "blobKey", blobKey );
        
		return "doneUpload";
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
			fileList.add( file );
		}
		
		model.addAttribute( "fileList", fileList );
		
		return "listUpload";
	}
}
