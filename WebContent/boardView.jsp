
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
table {
	width: 900px;
	height: auto;
	margin: 0px auto;
	border: 1px solid #3de03a;
}

#title1 {
	height: 80px;
	color: #3de03a;
	font-size: 48px;
	font-weight: 900;
}

#head {
	height: 10%;
}

#content {
	height: 5%;
}

#seq {
	width: 5%;
	text-align: center;
}

#title {
	width: 55%;
	text-align: center;
}

#writer {
	width: 10%;
}

#writeDay {
	width: 20%;
}

#count {
	width: 5%;
}

#wrapper {
	text-align: center;
}

#navi {
	height: 5%;
}

#footer {
	height: 5%;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	background-color: #e8c60b;
}
</style>
<body>

	<table border=1>
		<tr id="head">
			<th id="title1" colspan=6 align=center>BOARD
		</tr>
		<tr id="content">
			<th id="seq">
			<th id="title">제목
			<th id="writer">작성자
			<th id="writeDay">작성일
			<th id="count">조회
			<th id="ip">ip
		</tr>


		<c:choose>
			<c:when test="${titleMsg == null}">
			<c:choose>
				<c:when test="${result.size() > 0}">
					<c:forEach var="item" items="${result}">



						<tr id="wrapper">

							<td>${item.seq}
							<td><a href="read.jsp?id=${item.seq}&writer=${item.writer}"
								name="id" id="viewcount">${item.title}</a>
							<td>${item.writer}
							<td>${item.writeday}
							<td>${item.viewcount}
							<td>${item.ip}
						</tr>

					</c:forEach>
					
						<tr id="footer">
					<td align=right colspan=6><input type=button value="〈"
						id="back"></input> <input type=button value="글쓰기" id="write"></input>
				</tr>
				<tr id="navi">
					<td align=center colspan=6>${navi}
				</tr>
				<tr>
					<td colspan=6 align=center><select><option>제목</select>
						<input type="text" id="searchInput">
						<button id="search">검색</button>
				</tr>
		         
		         
		         </c:when>
				
				<c:otherwise>
					<tr>
						<td colspan=6 align=center height=100%>표시할 내용이 없습니다.
					</tr>
					
					<tr id="footer">
					<td align=right colspan=6><input type=button value="〈"
						id="back"></input> <input type=button value="글쓰기" id="write"></input>
				</tr>
				<tr id="navi">
					<td align=center colspan=6>${navi}
				</tr>
				<tr>
					<td colspan=6 align=center><select><option>제목</select>
						<input type="text" id="searchInput">
						<button id="search">검색</button>
				</tr>
				</c:otherwise>

			</c:choose>

			</c:when>
			<c:otherwise>
				<c:forEach var="item1" items="${list}">

					<tr id="wrapper">

						<td>${item1.seq}
						<td><a href="read.jsp?id=${item1.seq}&writer=${item1.writer}"
							name="id" id="viewcount">${item1.title}</a>
						<td>${item1.writer}
						<td>${item1.writeday}
						<td>${item1.viewcount}
						<td>${item1.ip}
					</tr>
				</c:forEach>
				<tr id="footer">
					<td align=right colspan=6><input type=button value="〈"
						id="back"></input> <input type=button value="글쓰기" id="write"></input>
				</tr>
				<tr id="navi">
					<td align=center colspan=6>${navisearch}
				</tr>
				<tr>
					<td colspan=6 align=center><select><option>제목</select>
						<input type="text" id="searchInput">
						<button id="search">검색</button>
				</tr>

			</c:otherwise>
		</c:choose>
	</table>

	<script>
		viewcount = 0;
		document.getElementById("write").onclick = function() {

			location.href = "write.jsp";

		}
		document.getElementById("back").onclick = function() {

			location.href = "login.jsp";
		}
		document.getElementById("search").onclick = function() {
			var titleMsg = document.getElementById("searchInput").value;
			location.href = "BoardController?titleMsg=" + titleMsg;

		}
	</script>

</body>
</html>