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

</style>
</head>
<body>
	<ul>
	      <li><a class="active" href="<%= request.getContextPath() %>/home.jsp">Home</a></li>
	      <li><a href="<%= request.getContextPath() %>/policySearch.jsp">정책찾기</a></li>
	      <li><a href="<%= request.getContextPath() %>/postWrite.jsp">정책제안게시판</a></li>
	      <li><a href="<%= request.getContextPath() %>/mypage.jsp">마이페이지</a></li>
	</ul>
</body>
</html>