<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	$( "#myForm" ).ajaxForm( function()
	{
		$( "#myForm" ).clearForm();
		alert( "저장 했습니다." );
	});
});
</script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/_header.jsp"></jsp:include>
    
    <div class="container">
    
      <h1>Register</h1>
      
      <form id="myForm" action="doRegister.do">
        <span>아이디:</span><input type="text" name="userId" value=""></input>
        <br/>
        <span>이름:</span><input type="text" name="userName" value=""></input>
        <br/>
        <input type="submit" value="저장"></input>
      </form>
      
    </div>
</body>
</html>
