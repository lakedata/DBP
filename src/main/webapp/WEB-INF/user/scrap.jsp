<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>scrap</title>
<style type="text/css">
button {
	width: 120px;
	height: 40px;
	color: #fff;
	background: #004fff;
	font-size: 16px;
	border: none;
	border-radius: 20px;
	box-shadow: 0 4px 16px rgba(0, 79, 255, 0.3);
	transition: 0.3s;
	position: relative;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
}

button:focus {
	outline: 0;
}

button:hover {
	background: rgba(0, 79, 255, 0.9);
	cursor: pointer;
	box-shadow: 0 2px 4px rgba(0, 79, 255, 0.6);
}

.button-1 {
	width: 140px;
	height: 50px;
	border: 2px solid #34495e;
	float: left;
	text-align: center;
	cursor: pointer;
	position: relative;
	box-sizing: border-box;
	overflow: hidden;
	margin: 0 0 40px 0;
}

.button-1 a {
	font-family: arial;
	font-size: 16px;
	color: #34495e;
	text-decoration: none;
	line-height: 50px;
	transition: all .5s ease;
	z-index: 2;
	position: relative;
}

.eff-1 {
	width: 140px;
	height: 50px;
	top: -2px;
	right: -140px;
	background: #34495e;
	position: absolute;
	transition: all .5s ease;
	z-index: 1;
}

.button-1:hover .eff-1 {
	right: 0;
}

.button-1:hover a {
	color: #fff;
}

 #wrapper {
	position:absoulute;
	height: 75%;
	width: 75%;
	padding: 100px 100px;
	text-align:center;
	vertical-align:middle; 
	border: solid 1px;
	margin:auto;
 }
</style>
</head>
<body>

	<div class="button-1">
		<div class="eff-1"></div>
		<a href="<c:url value='/main'/>"> home </a>
	</div>

	<!-- 스크랩 목록 부분 -->
	<br>
	<c:choose>
		<c:when test="${empty scrapList}">
		<br><br>
		<div id="wrapper">
		<img src="https://ifh.cc/g/0CuWQr.jpg" class="img-responsive"
				alt="scrapimg" style="width:500px; height:400px;">
			<h2>스크랩을 추가해보세요.</h2>
			<h4>스크랩항목이 없습니다.</h4>
		</div>
	</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${not empty scrap.policyId}">
			<div id="wrapper">
				<table id="scrapList" width="800" border="3" bordercolor="lightgray">
					<c:forEach var="scrap" items="${scrapList}">
						<tr height="30">
							<td>정책명</td>
						</tr>

						<tr>
							<td><a
								href="<c:url value='policy/search'/>">  정책상세보기
									${scrap.policyId()} </a></td>
						</tr>
					</c:forEach>
				</table>
				<h4>스크랩을 더 추가해보세요.</h4>
			</div>
		</c:when>
	</c:choose>
	
	<br><br>
	<div>
		<button onclick="location.href='/policy/view' ">+ Add</button>
	</div>
</body>
</html>