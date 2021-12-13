<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/home/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>scrap</title>
<style type="text/css">
	table {
			border: 1px solid #EDEDED;
			border-collapse: collapse;
			margin-top: 30px;
			width: 100%; 
			align: center;
	}
	th, td {
			border-bottom: 1px solid #EDEDED;
    		padding: 5px;
 			height: 40px;
	}
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
 .scrap-div {
	margin-top: 50px;
	width: 80%;	
	margin-left: 120px;
}
</style>
</head>
<body>

<div class="scrap-div">


<c:choose>
		<c:when test="${empty scrapList}">
		<br><br>
		<div id="wrapper">
		<img src="https://ifh.cc/g/eOQkpg.png" class="img-responsive"
				alt="scrapimg" style="width:100px; height:100px;">
			<h2>스크랩을 추가해보세요</h2>
			<h4>스크랩항목이 없습니다</h4>
		</div>
	</c:when>
	</c:choose>
	
		<c:choose>
		<c:when test="${not empty scrapList}">  
		 <h3 style="margin-top: 100px; margin-bottom: 20px;">스크랩한 정책 </h3>
	<table>
      <thead>
      	<tr>
      	<td><b>정책유형</b></td>
      	<td><b>정책명</b></td>
		</tr>
      </thead>
      <tbody>  
		<c:forEach var="scrap" items="${scrapList}">
			<tr>
			<td style="width: 15%;">
				${scrap.category} 
			</td>
			  <td><a style="text-decoration: none;" href="<c:url value='/policy/view'>
						      <c:param name='policyId' value='${scrap.policyId}'/>
						   </c:url>">
				  ${scrap.name} </a>
			  </td>
				
			</tr>
		</c:forEach>
	  </tbody>
	</table>	
	
	</c:when>
	</c:choose>	
	 
</div>

	<br><br>
	<div>
		<button onclick="location.href='/policy/search' ">+ Add</button>
	</div>
</body>
</html>
 <%@ include file="/WEB-INF/home/footer.jsp" %>