<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>
    <style>
.container {
	width: 385px;
	line-height: 50px;
	margin: 40px auto;
}

h5 {
	text-align: center;
}

h5 span {
	color: teal;
}

.login {
	background-color: #BBDEFB;
	color: white;
	border-radius: 5px;
	border: 0;
	padding: 10px 172px;
}

#signup {
	background-color: white;
	color: teal;
	border: 0;
	font-size: 17px;
}

p {
	text-align: center;
}
 
i {
	color: lightgray;
}
  
input {
	border: 1px solid lightgray;
	border-radius: 3px;
}
</style>
</head>
<body>
	<div class="container">

        	<h5><span>로그인</span> 페이지입니다.</h5>
        <hr />
        <form action="login.do" method="post" action="<c:url value='/user/login' />">
    
            <input type="text" placeholder="아이디" name="id" required style="height:30px; width: 380px" /><br />
            <input type="password" placeholder="비밀번호" name="pw" required style="height:30px; width: 380px" /><br />
            <input type="submit" value="로그인" class="login" />
            <button onclick="location.href='main.jsp';" class="login" >HOME</button>
        </form>
        <hr />
        <p><a href="registerFrom.jsp"><input type="button" value="회원가입" id="signup" /></a></p>
    </div>
</body>
</html>