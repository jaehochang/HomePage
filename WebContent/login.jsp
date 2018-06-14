<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function(){
		$("#Signup").click(function(){
			
		});
	})
</script>
</head>
<body>
	<form action="login.do" method="post" id="formLogin">
	<table border=1>
		<tr>
			<td colspan="2">회원 로그인</td>
		</tr>
		<tr>
			<td>아이디 :</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>비밀번호 :</td>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td colspan="2"><input type=submit value="로그인">
			<button id="Signup" type=button>회원가입</button></td>
		</tr>
	</table>
	</form>
	</body>
</html>