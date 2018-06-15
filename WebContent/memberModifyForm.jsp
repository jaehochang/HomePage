<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>개인정보수정</title>
<style>
#wrapper {
	border: 1px solid black;
	width: 500px;
	margin: 0 auto;
}

table {
	width: 100%;
}

td {
	box-sizing: border-box;
}

table tr td:first-child {
	width: 100px;
	text-align: right;
}

table tr td:last-child {
	width: 200px;
}

#addr1, #addr2 {
	width: 90%;
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	window.onload = function() {
		document.getElementById("search").onclick = function() {
			new daum.Postcode({
				oncomplete : function(data) {

					var fullAddr = ''; // 최종 주소 변수
					var extraAddr = ''; // 조합형 주소 변수

					// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
						fullAddr = data.roadAddress;

					} else { // 사용자가 지번 주소를 선택했을 경우(J)
						fullAddr = data.jibunAddress;
					}

					// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
					if (data.userSelectedType === 'R') {
						//법정동명이 있을 경우 추가한다.
						if (data.bname !== '') {
							extraAddr += data.bname;
						}
						// 건물명이 있을 경우 추가한다.
						if (data.buildingName !== '') {
							extraAddr += (extraAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
						fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')'
								: '');
					}

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
					document.getElementById('addr1').value = fullAddr;

					// 커서를 상세주소 필드로 이동한다.
					document.getElementById('addr2').focus();
				}
			}).open();
		}
	}
</script>
</head>
<body>
	<form action="modify.do" method="post">
		<div id="wrapper">
			<table border="1">
				<tr>
					<td>아이디:</td>
					<td><input type="text" name="id" value="${rrdto.getId()}"
						readonly></td>
				</tr>
				<tr>
					<td>비밀번호:</td>
					<td><input type="password" name="pw" value="${rdto.getPw()}"></td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><input type="text" name="name" value="${rdto.getName()}"></td>
				</tr>
				<tr>
					<td>이메일:</td>
					<td><input type="email" name="email" value="${rdto.getEmail()}"></td>
				</tr>
				<tr>
					<td>전화번호:</td>
					<td><input type="text" name="phone1"
						value="${rdto.getPhone1()}" style="width: 20%">- <input
						type="text" name="phone2" value="${rdto.getPhone2()}"
						style="width: 20%">- <input type="text" name="phone3"
						value="${rdto.getPhone3()}" style="width: 20%"></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" id="postcode" name="zipcode"
						value="${rdto.getZipcode()}" style="width: 50px;" readonly>
						<button type="button" id="search">우편번호</button></td>
				</tr>
				<tr>
					<td>주소:</td>
					<td><input type="text" id="addr1" name="address1" readonly
						value="${rdto.getAddress1()}"></td>
				</tr>
				<tr>
					<td>상세주소:</td>
					<td><input type="text" id="addr2" name="address2"
						value="${rdto.getAddress2()}"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button>수정하기</button>
						<button type="button" onclick="location.href='login.do'">돌아가기</button>
						<!-- 
						history.back(); 전페이지
						history.go(-2); 전전페이지
					 -->
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>