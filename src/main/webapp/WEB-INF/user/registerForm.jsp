<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>회원가입</title>
<style>
input {
	width: 150px;
	height: 40px;
	border-radius: 15px;
	border: 1px solid lightgray;
}

button {
	width: 100px;
	height: 90x;
	padding: 10px;
	border: none;
	border-radius: 25px;
}

button:hover {
	background-color: #8080FF;
	color: white;
}

#div1 {
	text-align: center;
}

#tableStyle {
	margin: auto;
	text-align: center;
}

td {
	height: 100px;
	width: 190px;
}

#textStyle {
	text-align: left;
	font-size: 15px;
}
</style>
<script>
	function userCreate() {

		if (form.userId.value == "") {
			alert("사용자 ID를 입력하십시오.");
			form.userId.focus();
			;
			return false;
		}
		if (form.password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.password.focus();
			return false;
		}
		if (form.name.value == "") {
			alert("이름을 입력하십시오.");
			form.name.focus();
			return false;
		}
		<!--
		if (form.pw_re.value == "") {
			alert("비밀번호 확인을 입력하십시오.");
			form.password.focus();
			return false;
		}
		-->
		if (form.email.value == "") {
			alert("이메일을 입력하십시오.");
			form.email.focus();
			return false;
		}
		if (form.birth.value == "") {
			alert("생년월일을 입력하십시오.");
			form.birth.focus();
			return false;
		}
		var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
		if (phoneExp.test(form.phone.value) == false) {
			alert("전화번호 형식이 올바르지 않습니다.");
			form.phone.focus();
			return false;
		}

		form.submit();
	}
<!--
	function userList(targetUri) {
		form.action = targetUri;
		form.submit();
	} -->
</script>

</head>
<body>

	<!-- header -->
	<jsp:include page="/header.jsp" />

	<br />
	<br />
	<center>
		JOIN
		<hr />
	</center>

	<br />
	<br />
=======
	      form.action = targetUri;
	      form.submit();
	}  
	
	</script>
	
	</head>
	<body>
	
<!-- header -->
 <jsp:include page="/header.jsp"/>
	    
	    <br/><br/>
	    <center>JOIN<hr/></center>
	    
	    <br/><br/>
>>>>>>> branch 'develop' of https://github.com/juri123123/DBP.git
	<div id="div1">
		<!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
		<div class="row col-lg-12">
			<c:if test="${registerFailed}">
				<h6 class="text-danger">
					<c:out value="${exception.getMessage()}" />
				</h6>
			</c:if>
		</div>

		<form name="form" method="POST" action="<c:url value='/user/register'/>">

			<table id=tableStyle>


				<tr>
					<td id=textStyle>아이디</td>
					<td><input type="text" name="userId"><br /></td>
				</tr>

				<tr>
					<td id=textStyle>비밀번호</td>
					<td><input type="text" name="password"><br /></td>
				</tr>
				<!--  
	<tr>
	<td id = textStyle>비밀번호 확인 </td><td><input type="text" name="password"><br/></td>
	</tr>-->
				<tr>
					<td id=textStyle>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td id=textStyle>이메일</td>
					<td><input type="text" name="email"><br /></td>
				</tr>
				<tr>
					<td id=textStyle>생년월일</td>
					<td><input type="text" name="birth"></td>
				</tr>
				<tr>
					<td id=textStyle>핸드폰번호<br />
					<p style="color: gray; font-size: 10px;">ex.010-OOOO-OOOO</p></td>
					<td><input type="tel" name="phone"
						pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}" maxlength="13"><br />
					</td>
				</tr>
			</table>
			<br/>
			<br/> 
			<input type="button" value="회원가입" onClick="userCreate()">
		</form>
	</div>
</body>
</html>