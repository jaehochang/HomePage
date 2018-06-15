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
<title>Main</title>

</head>
<body>
	
<div class="container">
		<div class="row">
	<table class="table table-striped" style="border: 1px solid #dddddd" id="mainTb" align=center>	
				<tr>
					<th colspan=5 id="titleheader"  style="text-align: center;">welcome ${sessionScope.loginId}!
				</tr>
				<tr>
					<td style="text-align: center;"><button class="btn btn-primary" id="logout" type="button">로그아웃</button>
					<td style="text-align: center;"><button class="btn btn-default" id="mypage">나의페이지</button>
					<td style="text-align: center;"><button class="btn btn-default" id="modify">정보수정</button>
					<td style="text-align: center;"><button class="btn btn-default" id="drop">회원탈퇴</button>
					<td style="text-align: center;"><button class="btn btn-success" id="board">게시판</button>
				</tr>
	</table>
	</div>
	</div>
	
	<script>
		$("#board").click(function(){
			
			$(location).attr('href','BoardController.bo');
			
		});
		
		$("#modify").click(function(){
			$(location).attr('href','modifyForm.do');
		})
	
		$("#drop").click(function(){
			$(location).attr('href','toMemberOut.do');
		})
		
		$("#logout").click(function(){
			$(location).attr('href','logout.do');
		})
		
		$("#mypage").click(function(){
			$(location).attr('href','mypage.do');
		})
		
	</script>
	
	
		
		

</body>
</html>