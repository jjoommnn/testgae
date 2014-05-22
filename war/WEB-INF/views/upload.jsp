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
<script type="text/javascript">
$(function()
{
	var option = {};
	
	$( "#myForm" ).ajaxForm({
		success : function( result )
		{
			$( "#myForm" ).clearForm();
			alert( "저장 했습니다. : " + result );
		}
	});
});
</script>
</head>
<body>
    <h2 class="sub-header">파일을 선택하세요</h2>
    <form id="myForm" action="<%= blobstoreService.createUploadUrl("/fileUpload") %>" method="post" enctype="multipart/form-data">
        <input type="file" name="myFile">
        <input type="submit" value="Submit">
    </form>
    <a href="index.jsp">처음으로</a>
</body>
</html>
