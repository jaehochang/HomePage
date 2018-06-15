<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function() {
		$("#signup").click(function() {
			$(location).attr("href", "signup.jsp");
		});
	})
</script>
</head>
<body>
	<form action="login.do" method="post" id="formLogin">
		<div class="container">
			<div class="row">
				<table class="table table-striped" style="border: 1px solid #dddddd">
					<tbody>
						<tr align=center>
							<th colspan="3"
								style="background-color: #eeeeee; text-align: center">Sign-In
						</tr>

						<tr>
							<th>아이디</th>
							<td><input class="form-control" type="text" name="id"></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input  class="form-control" type="password" name="pw"></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;"><input class="btn btn-warning" type=submit value="로그인">&nbsp;
								<button id="signup" type=button class="btn btn-default">회원가입</button></td>
						</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>