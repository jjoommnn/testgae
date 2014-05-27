<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function()
{
	$( ".menu_list" ).addClass( "active" );	
});
</script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/_header.jsp"></jsp:include>
    
    <div class="container">
    
	    <h1>List</h1>
	    
	    <div class="table-responsive">
	    <table class="table table-bordered table-striped">
	      <thead>
	        <tr>
	          <th class="text-center">삭제</th>
	          <th>아이디</th>
	          <th>이름</th>
	          <th>날짜</th>
	        </tr>
	      </thead>
	      <tbody>
	      <c:forEach items="${userList}" var="user">
	        <tr>
	          <td class="text-center"><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
	          <td>${user.userId}</td>
	          <td>${user.userName}</td>
	          <td>${user.createDate}</td>
	        </tr>
	      </c:forEach>
	      </tbody>
	    </table>
	    </div>
	    
    </div>
</body>
</html>
