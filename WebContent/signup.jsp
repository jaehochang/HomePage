<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign-up</title>


<!-- Jquery CDN -->
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>



<!-- BootStrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<style>
#buttons {
	text-align: right;
}
</style>

</head>
<body>

	<c:if test="${signupResult}">


		<script>


window.onload = function(){
	
	var alarm = "회원 가입에 성공하였습니다. 로그인 페이지로 이동합니다.";
	alert(alarm);

	
	setInterval(function(){
		location.href = "login.jsp";
	},3000);


}

</script>


	</c:if>

	<c:if test="${signupResult==null}">



		<div class="panel panel-default" style="margin: 50px;">
			<div class="panel-body">

				<form action="signup.do" method=post class=container>



					<div class=form-group>
						<label for="id">아이디</label>
						<div class=form-inline>
							<input type=text class=form-control name=id id=id>
							<button class="btn btn-default">중복 확인</button>
						</div>
					</div>




					<div class=form-group>
						<label for="passworld">비밀번호</label> <input type="password"
							class="form-control" name=password id=password>
					</div>



					<div class=form-group>
						<label for="passworld">비밀번호 재확인</label> <input type="password"
							class="form-control" name=password-check id=password-check>
					</div>


					<div class=form-group>
						<label for="name">이름</label> <input type="text" class=form-control
							name=name id=name>
					</div>


					<div class=form-group>
						<label for="contact-label">연락처</label>
						<div class=form-inline>
							<select class="form-control" id=phone1 name=phone1>
								<option>010</option>
								<option>011</option>
								<option>02</option>
								<option>031</option>
								<option>032</option>
								<option>033</option>
								<option>041</option>
								<option>042</option>
								<option>043</option>
								<option>044</option>
								<option>051</option>
								<option>054</option>
								<option>052(2)</option>
								<option>055</option>
								<option>061</option>
								<option>062</option>
								<option>063</option>
								<option>064</option>
							</select> <input type="text" name=phone2 id=phone2 class=form-control>
							<input type="text" name=phone3 id=phone3 class=form-control>

						</div>
					</div>


					<div class="form-group">
						<label for="email">이메일</label> <input type="email" name=email
							id=email class=form-control>
					</div>


					<div class=form-group>
						<label for=zipcode>우편번호</label>
						<div class="input-group">
							<input type=text name=zipcode id=zipcode class=form-control
								placeholder="검색">
							<div class="input-group-btn">

								<button class="btn btn-default" type="button" >
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</div>
						</div>
					</div>

					<div class=form-group>
						<label for=address1>주소</label> <input type=text name=address1
							id=address1 class=form-control>
					</div>

					<div class=form-group>
						<label for=address2>나머지 주소</label> <input type=text name=address2
							id=address2 class=form-control>
					</div>

					<div id=buttons>
						<button type=submit class="btn btn-default">등록</button>
						<button type=button id="return" class="btn btn-default">돌아가기</button>
					</div>
				</form>

			</div>
		</div>



		<script>
		window.onload = function() {
			document.getElementById("return").onclick = function() {
				location.href = "login.jsp";
			}
		}		
	</script>



	</c:if>

</body>
</html>