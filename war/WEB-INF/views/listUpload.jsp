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
</head>
<body>
    <h2 class="sub-header">리스트</h2>
    <div class="table-responsive">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>키</th>
          <th>이름</th>
          <th>타입</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${fileList}" var="file">
        <tr>
          <td><a href="fileDownload?blobKey=${file.blobKey}">${file.blobKey}</a></td>
          <td>${file.fileName}</td>
          <td>${file.fileType }</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    </div>
    <a href="index.jsp">처음으로</a>
</body>
</html>
