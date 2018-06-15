<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberOutView</title>
</head>
<body>
<script>
		if (${result}>0) {
			alert("회원탈퇴가 완료되었습니다");
			location.href= "logout.do";
			close();
		} else {
			alert("회원탈퇴에 실패하셨습니다 \n\t PW를 확인해주세요");
			location.href = "memberOut.jsp";
		}
	</script>
</body>
</html>