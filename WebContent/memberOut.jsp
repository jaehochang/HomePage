<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberOut</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function() {
		$("#cancel").click(function() {
			alert("잘 생각하셨습니다");
			location.href = "login.jsp";
		})
	})
</script>
</head>
<body>
	<form id="memberOutForm" name="mamberOutForm" method="post">
		<table border=1>
			<tr>
				<th colspan=3>비밀번호를 입력하세요
			</tr>
			<tr>
				<td><input type="text" id="pw" name="pw">
				<td><button type="button" id="memberOut">탈퇴</button>
				<td><button type="button" id="cancel">취소</button>
			</tr>
		</table>
	</form>
	<script>
		$("#memberOut").click(function() {
			var pw = $("#pw").val();
			if (pw != "") {
				var RUSure = confirm("정말 탈퇴하시겠습니까?");
				if (RUSure == true) {
					alert("그래요...그러세요...");
					location.href = "memberOut.do?pw=" + pw; 
				} else {
					alert("그래요! 잘 생각하셨어요!");
					location.href = "login.jsp";
				}
			} else {
				alert("비밀번호를 입력해주세요");
			}
		})
	</script>
</body>
</html>