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
<script type="text/javascript" src="./js/jquery.blockUI.js"></script>
<script type="text/javascript">
$(function()
{
	$( ".menu_register" ).addClass( "active" );
	
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
		success : function()
		{
			$.unblockUI();
			$( "#myForm" ).clearForm();
			alert( "저장 했습니다." );
		}
	});
});
</script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/_header.jsp"></jsp:include>
    
    <div class="container">
    
      <h1>Register</h1>
      
      <form id="myForm" action="doRegister.do" role="form">
        <div class="form-group">
		    <label for="userId">아이디</label>
		    <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력하세요.">
		</div>
		<div class="form-group">
            <label for="userName">이름</label>
            <input type="text" class="form-control" id="userName" name="userName" placeholder="이름을 입력하세요.">
        </div>
        <button type="submit" class="btn btn-primary">저장</button>
      </form>
      
    </div>
</body>
</html>
