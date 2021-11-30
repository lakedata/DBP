<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>header</title>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	background-color: #333;
}

ul:after {
	content: '';
	display: block;
	clear: both;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not(.active) {
	background-color: #111;
}

.active {
	background-color: #8080FF;
}

#board, #bList, #pageForm {
	text-align: center;
}
</style>
</head>
<body>
	<ul>
		<li><a class="active"
			href="<%= request.getContextPath() %>/home.jsp">Home</a></li>
		<li><a href="<%= request.getContextPath() %>/policySearch.jsp">정책찾기</a></li>
		<li><a href="<%= request.getContextPath() %>/postWrite.jsp">정책제안게시판</a></li>
		<li><a href="<%= request.getContextPath() %>/mypage.jsp">마이페이지</a></li>
	</ul>

	<c:choose>
		<c:when test="${user_id=='admin'}">
			<li class="nav-item"><admin>관리자계정 로그인<admin>
				<li>
				<li class="nav-item"><a
					href="<c:url value="/user/logout"></c:url>"
					class="nav-link link-dark px-2">Logout</a></li>
				<li class="nav-item"><a
					href="<c:url value="/user/mypage"> <c:param name='user_id' value='${user.user_id}'/></c:url>"
					class="nav-link link-dark px-2">MyPage</a></li>
		</c:when>

		<c:when test="${user_id==NULL}">
			<li class="nav-item"><a
				href="<c:url value="/user/login"></c:url>"
				class="nav-link link-dark px-2">Login</a></li>
			<li class="nav-item"><a
				href="<c:url value="/user/register"></c:url>"
				class="nav-link link-dark px-2">Sign up</a></li>
		</c:when>
		<c:when test="${user_id!=NULL}">

			<li class="nav-item"><a
				href="<c:url value="/user/logout"></c:url>"
				class="nav-link link-dark px-2">Logout</a></li>
			<li class="nav-item"><a
				href="<c:url value="/user/mypage"> <c:param name='user_id' value='${user.user_id}'/></c:url>"
				class="nav-link link-dark px-2">MyPage</a></li>

		</c:when>
	</c:choose>

</body>
</html>