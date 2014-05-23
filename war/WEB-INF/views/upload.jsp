<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/jquery.form.min.js"></script>
<script type="text/javascript" src="./js/jquery.blockUI.js"></script>
<script type="text/javascript">
$(function()
{
	$( ".menu_upload" ).addClass( "active" );
	
	$( "#myForm" ).ajaxForm({
		beforeSubmit : function( arr, $form, options )
		{ 
		    $.blockUI( { message: "<p>저장 중입니다.</p>" } );
		},
		error : function()
		{
			$.unblockUI();
			alert( "저장에 실패했습니다." );
		},
		success : function( result )
		{
			$.unblockUI();
			$( "#myForm" ).clearForm();
			alert( "저장 했습니다. : " + result );
		}
	});
});
</script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/_header.jsp"></jsp:include>
    
    <div class="container">
    
      <h1>File Upload</h1>
      
      <form id="myForm" action="<%= blobstoreService.createUploadUrl("/doUpload.do") %>" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="myFile">파일</label>
            <input type="file" id="myFile" name="myFile" />
        </div>
        <button type="submit" class="btn btn-primary">저장</button>
      </form>
      
    </div>
</body>
</html>
