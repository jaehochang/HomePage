<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	

	<table border="1" id="mainTb">
				<tr>
					<td colspan=5 id="titleheader">${sessionScope.loginId}
				</tr>
				<tr>
					<td><button id="logout" type="button">로그아웃</button>
					<td><button id="mypage">나의페이지</button>
					<td><button id="modify">정보수정</button>
					<td><button id="drop">회원탈퇴</button>
					<td><button id="board">게시판</button>
				</tr>
	</table>
	
	<script>
		$("#board").click(function(){
			
			$(location).attr('href','BoardController.bo');
			
		});
	
		$("#drop").click(function(){
			$(location).attr('href','toMemberOut.do');
		})
		
		$("#logout").click(function(){
			$(location).attr('href','logout.do');
		})
	</script>
	
	
		
		

</body>
</html>