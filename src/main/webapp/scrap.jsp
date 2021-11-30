<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
	position: absolute;
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
</style>
</head>
<body>

	<div class="button-1">
		<div class="eff-1"></div>
		<a href="#"> home </a>
	</div>

	<!-- 스크랩 목록 부분 -->
	<br>
	<c:choose>
		<c:when test="${empty scrapList}">
			결과없음
	</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${not empty scrap.policyId}">

			<div id="scrapboard">
				<table id="scrapList" width="800" border="3" bordercolor="lightgray">
					<c:forEach var="scrap" items="${scrapList}">
						<tr heigh="30">
							<td>정책명</td>
						</tr>

						<tr>
							<td><a
								href="<%=request.getContextPath()%>/policyDetail.jsp"> 정책상세보기
									${scrap.policyId()} </a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:when>
	</c:choose>
	<button>+ Add</button>


</body>
</html>