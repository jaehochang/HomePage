<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF8");
	response.setCharacterEncoding("UTF8");
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign-up</title>




<!-- Jquery CDN -->
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>

<!-- BootStrap CDN 1/2/3-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->


<!-- 주소찾기 DAUM API  -->
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>


<!-- 부트 스트랩 Modal  -->
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style type="text/css">
#buttons {
	text-align: right;
}

body {
	font-family: 'Varela Round', sans-serif;
}

.modal-confirm {
	color: #434e65;
	width: 525px;
}

.modal-confirm .modal-content {
	padding: 20px;
	font-size: 16px;
	border-radius: 5px;
	border: none;
}

.modal-confirm .modal-header {
	background: #47c9a2;
	border-bottom: none;
	position: relative;
	text-align: center;
	margin: -20px -20px 0;
	border-radius: 5px 5px 0 0;
	padding: 35px;
}

.modal-confirm h4 {
	text-align: center;
	font-size: 36px;
	margin: 10px 0;
}

.modal-confirm .form-control, .modal-confirm .btn {
	min-height: 40px;
	border-radius: 3px;
}

.modal-confirm .close {
	position: absolute;
	top: 15px;
	right: 15px;
	color: #fff;
	text-shadow: none;
	opacity: 0.5;
}

.modal-confirm .close:hover {
	opacity: 0.8;
}

.modal-confirm .icon-box {
	color: #fff;
	width: 95px;
	height: 95px;
	display: inline-block;
	border-radius: 50%;
	z-index: 9;
	border: 5px solid #fff;
	padding: 15px;
	text-align: center;
}

.modal-confirm .icon-box i {
	font-size: 64px;
	margin: -4px 0 0 -4px;
}

.modal-confirm.modal-dialog {
	margin-top: 80px;
}

.modal-confirm .btn {
	color: #fff;
	border-radius: 4px;
	background: #eeb711;
	text-decoration: none;
	transition: all 0.4s;
	line-height: normal;
	border-radius: 30px;
	margin-top: 10px;
	padding: 6px 20px;
	border: none;
}

.modal-confirm .btn:hover, .modal-confirm .btn:focus {
	background: #eda645;
	outline: none;
}

.modal-confirm .btn span {
	margin: 1px 3px 0;
	float: left;
}

.modal-confirm .btn i {
	margin-left: 1px;
	font-size: 20px;
	float: right;
}

.trigger-btn {
	display: inline-block;
	margin: 100px auto;
}
</style>

</head>


<body>

	<c:if test="${signupResult}">
		<%
			//난수 돌려 라이언 화면 바꾸기 0-21까지 

				int randomNumOneOrTwo = (int) Math.floor(Math.random() * 2);
				int randomNumZeroToNine = (int) Math.floor(Math.random() * 8 + 1);

		%>
		<script>
			$(document).ready(function() {
				$("#myModal").modal('show');
				$("#myModal").css("z-index", "1500");
			})
		</script>

		<div id="myModal" class="modal fade">
			<div class="modal-dialog modal-confirm">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #337ab7;">
					</div>
					<div class="modal-body text-center">
						<h4>성공!</h4>
						<img
							src="http://t1.daumcdn.net/liveboard/emoticon/kakaofriends/v3/ryan/0<%=randomNumOneOrTwo%><%=randomNumZeroToNine%>.gif"
							style='margin: 0 auto; height: 200px; width: 200px'></img>
						<p>아이디가 성공적으로 생성 되었습니다!</p>
						<button id=toLogin onclick="window.location.href='login.jsp'"
							class="btn btn-success" data-dismiss="modal">
							<span>로그인</span> <i class="material-icons">&#xE5C8;</i>
						</button>
					</div>
				</div>
			</div>
		</div>





	</c:if>


	<!------------------------------------------------------------- 분기점 ------------------------------------------------------------->


	<c:if test="${signupResult==null}">



		<div class="panel panel-primary" style="margin: 50px;">

			<div class="panel-heading">
				<h3 style="text-align: center">회원가입</h3>
			</div>



			<div class="panel-body">
				<form action="signup.do" method=post class=container>


					<div class="form-group">
						<label for="id">아이디</label>
						<div class="form-inline">
							<input type=text class="form-control" name=id id=id required
								placeholder="영문 8-13자 (숫자 맨 앞자리 불가)"
								pattern="^[^0-9][a-zA-z0-9]{7,12}$"
								title="8 - 13자리의 영소문자 조합만 가능합니다(첫 자리 숫자 불가)" maxlength=13
								size=30>

							<button class="btn btn-default" type=button id=idDupleCheck>중복
								확인</button>
							<!-- DB 에서 아이디 중복 체크 : select * from members where id = ?  연결 해야함-->

						</div>
					</div>

					<div class="form-group">
						<label for="password">비밀번호</label><input type="password"
							class="form-control" size=30 minlength=8 maxlength=10 name=pw
							id=password required title="최소 8 - 10자 입력"
							placeholder="10자리 이하, 특수문자 가능">
					</div>

					<div class=form-group id=pwCheckPlace>
						<label for="password-check">비밀번호 재확인</label> <input
							type="password" class="form-control" size=30 minlength=8
							maxlength=10 name=password-check title="최소 8 - 10자 입력"
							id=password-check required placeholder="비밀번호 재입력"> <span
							id=errorPwDup style="margin: 10px; color: red;"></span>
					</div>


					<script>
						document.getElementById("password-check").onkeyup = function() {

							// 비밀번호 일치 여부 확인

							var pwInput = document.getElementById("password").value;
							var pwDupleCheck = document
									.getElementById("password-check").value;

							if (pwInput == pwDupleCheck) {

								errorPwDup.innerHTML = "동일합니다.";
								errorPwDup.style.color = "green";

							} else {
								errorPwDup.innerHTML = "동일하지 않습니다.";
								errorPwDup.style.color = "red";

							}

						}
					</script>
					<div class=form-group>
						<label for="name">이름</label> <input type="text"
							class="form-control" name=name id=name required
							pattern="^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z\b]+$" title="한글과 영문만 가능"
							placeholder="한글 또는 영문 성명" size=10 maxlength=10> <span
							id=nameCheck></span>
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

							</select> <input type="text" name=phone2 id=phone2 class=form-control
								required placeholder="3-4자리 숫자" pattern="^[0-9]{3,4}"
								title="최소 3자리 숫자를 입력하셔야 합니다." maxlength=4> <input
								type="text" name=phone3 id=phone3 class=form-control required
								placeholder="연락처 마지막 4자리" maxlength=4 pattern="^[0-9]{4}"
								title="최소 4자리 숫자를 입력하셔야 합니다."> <span id=phoneAlert></span>


							<!-- Phone 번호 유효성 검사 시작 -->

							<script>
								document.getElementById("phone2").oninput = function() {

									var phone2Counts = document
											.getElementById("phone2").value;

									var phoneAlertSpan = document
											.getElementById("phoneAlert");

									var regex = /^[0-9]{1,4}$/;

									if (regex.test(phone2Counts)) {

										phoneAlertSpan.innerHTML = "";

									} else {

										phoneAlertSpan.innerHTML = "연락처는 3-4자를 입력해야합니다.";
										phoneAlertSpan.style.color = "red";
										document.getElementById("phone2").value = "";
									}

								}

								document.getElementById("phone3").oninput = function() {

									var phone3Counts = document
											.getElementById("phone3").value;

									var phoneAlertSpan = document
											.getElementById("phoneAlert");

									var regex = /^[0-9]{1,4}$/;

									if (regex.test(phone3Counts)) {

										phoneAlertSpan.innerHTML = "";

									} else {

										phoneAlertSpan.innerHTML = "연락처는 3-4자를 입력해야합니다.";
										phoneAlertSpan.style.color = "red";
										document.getElementById("phone3").value = "";

									}

								}
							</script>

							<!-- 끝 -->

						</div>
					</div>


					<div class="form-group">
						<label for="email">이메일</label> <input type="email" name=email
							id=email class=form-control size=40 maxlength=40 required>
					</div>


					<div class=form-group>

						<label for=zipcode>우편번호</label>

						<div class=form-inline>

							<input type=text id="sample2_postcode" name=zipcode id=zipcode
								class="form-control col-3" placeholder="검색 버튼 클릭" required
								style="z-index: 0;">

							<button class="btn btn-default"
								onclick="sample2_execDaumPostcode()" type="button"
								id=zipcodeFind>
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</div>

					<div class=form-group>
						<label for=address1>주소</label> <input type=text
							id="sample2_address" name=address1 id=address1 class=form-control
							required>
					</div>

					<div class=form-group>
						<label for=address2>나머지 주소</label> <input type=text
							id="sample2_addressEnglish" name=address2 id=address2
							maxlength=300 placeholder="상세 주소를 입력해주세요." class=form-control
							required>
					</div>

					<!-- 다음 주소찾기 API IFRAME 시작 -->

					<div id="layer"
						style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
						<img
							src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png"
							id="btnCloseLayer"
							style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
							onclick="closeDaumPostcode()" alt="닫기 버튼">
					</div>


					<!-- 다음 주소 찾기 API IFRAME 끝 -->
					<script>
						// 우편번호 찾기 화면을 넣을 element
						var element_layer = document.getElementById('layer');

						function closeDaumPostcode() {
							// iframe을 넣은 element를 안보이게 한다.
							element_layer.style.display = 'none';
						}

						function sample2_execDaumPostcode() {
							new daum.Postcode(
									{
										oncomplete : function(data) {
											// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

											// 각 주소의 노출 규칙에 따라 주소를 조합한다.
											// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
											var fullAddr = data.address; // 최종 주소 변수
											var extraAddr = ''; // 조합형 주소 변수

											// 기본 주소가 도로명 타입일때 조합한다.
											if (data.addressType === 'R') {
												//법정동명이 있을 경우 추가한다.
												if (data.bname !== '') {
													extraAddr += data.bname;
												}
												// 건물명이 있을 경우 추가한다.
												if (data.buildingName !== '') {
													extraAddr += (extraAddr !== '' ? ', '
															+ data.buildingName
															: data.buildingName);
												}
												// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
												fullAddr += (extraAddr !== '' ? ' ('
														+ extraAddr + ')'
														: '');
											}

											// 우편번호와 주소 정보를 해당 필드에 넣는다.
											document
													.getElementById('sample2_postcode').value = data.zonecode; //5자리 새우편번호 사용
											document
													.getElementById('sample2_address').value = fullAddr;

											// iframe을 넣은 element를 안보이게 한다.
											// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
											element_layer.style.display = 'none';
										},
										width : '100%',
										height : '100%',
										maxSuggestItems : 5
									}).embed(element_layer);

							// iframe을 넣은 element를 보이게 한다.
							element_layer.style.display = 'block';

							// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
							initLayerPosition();
						}

						// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
						// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
						// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
						function initLayerPosition() {
							var width = 300; //우편번호서비스가 들어갈 element의 width
							var height = 400; //우편번호서비스가 들어갈 element의 height
							var borderWidth = 5; //샘플에서 사용하는 border의 두께

							// 위에서 선언한 값들을 실제 element에 넣는다.
							element_layer.style.width = width + 'px';
							element_layer.style.height = height + 'px';
							element_layer.style.border = borderWidth
									+ 'px solid';
							// 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
							element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth)
									+ 'px';
							element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth)
									+ 'px';
						}
					</script>


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
