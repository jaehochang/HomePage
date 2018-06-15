<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<title>마이페이지</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">마이페이지</a>
		</div>
	</nav>


	<div class="container">
		<div class="row">
			<table class="table table-striped" style="border: 1px solid #dddddd">
				<tbody>
					<tr align=center>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center">MyPage


						
					</tr>

					<tr>
						<th>아이디
						<td align=center>${result.id}
					</tr>
					<tr>
						<th>이름
						<td align=center>${result.name}
					</tr>
					<tr>
						<th>핸드폰번호
						<td align=center>${result.phone1}${result.phone2}${result.phone3}
					</tr>
					<tr>
						<th>이메일
						<td align=center>${result.email}
					</tr>
					<tr>
						<th>주소
						<td align=center>${result.address1}${result.address2}
					</tr>


				</tbody>

			</table>
		</div>
	</div>



</body>
</html>