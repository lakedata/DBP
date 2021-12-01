<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
	ul:after{
	    content:'';
	    display: block;
	    clear:both;
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
	            text-align :center;
	}
	
	.nav-left {
		 background-color: #333;
		float: right;
		
	}

</style>
</head>
<body>
	<ul>
			<li><img style="padding-top: 14px; padding-left: 15px; padding-right: 15px;" class ="logo" src="<c:url value='/images/logo_checkkeu.png'/>" height="20px"></li>
	      <li><a class="active" href="<%= request.getContextPath() %>/home.jsp">Home</a></li>
	      <li><a href="<c:url value="/policy/search"></c:url>">정책찾기</a></li>
	      <li><a href="<c:url value='/post/list'/>">정책제안게시판</a></li>
	      
		<div style="margin-right: 50px;">
			<c:choose>
			<c:when test="${userId=='dbpro0102'}">
				<li class="nav-left" style="color: red; margin-top: 13px; margin-left: 3px;"><admin>관리자계정<admin><li>
				<li class="nav-left"><a href="<c:url value="/user/logout"></c:url>">Logout</a></li>
				<li class="nav-left"><a href="<c:url value="/mypage"> <c:param name='user_id' value='${user.userId}'/></c:url>">마이페이지</a></li>
			</c:when>    
			<c:when test="${userId==NULL}">
				<li class="nav-left"><a href="<c:url value="/user/login"></c:url>">Login</a></li>
	            <li class="nav-left"><a href="<c:url value="/user/register"></c:url>">Sign up</a></li>
			</c:when>
			<c:when test="${userId!=NULL}">      
	            <li class="nav-left"><a href="<c:url value="/user/logout"></c:url>">Logout</a></li>
	          	<li class="nav-left"><a href="<c:url value="/mypage"><c:param name='userId' value='${user.userId}'/></c:url>">마이페이지</a></li>
			</c:when>
	        </c:choose> 
		</div>
         
        </ul>
</body>
</html>