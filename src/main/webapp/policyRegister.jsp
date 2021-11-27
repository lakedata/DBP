<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>정책등록</title>
<style>
	 input {
	 	width: 300px;
	 	height: 30px;
	 	border-radius: 5px;
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
	 	background-color:  #8080FF;
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
		height: 50px;
		width: 100px;
	}
	
	#textStyle{
		text-align: left;
		font-size: 15px;
	}

</style>
<script>
	
	
	function policyCreate() {
		if (form.policyName.value == "") {
		      alert("정책이름을 입력하십시오.");
		      form.policyName.focus();
		      return false;
		   }
		if (form.policyContent.value == "") {
		      alert("정책내용을 입력하십시오.");
		      form.policyContent.focus();
		      return false;
		}
	   if (form.qualificationForApplication.value == "") {
	      alert("자격요건을 입력하십시오.");
	      form.qualificationForApplication.focus();
	      return false;
	   } 
	   if (form.local.value == "") {
	      alert("지역을 입력하십시오.");
	      form.local.focus();
	      return false;
	   } 
	   if (form.age.value == "") {
		      alert("나이를 입력하십시오.");
		      form.age.focus();
		      return false;
		}
	   if (form.income.value == "") {
		      alert("소득분위 입력하십시오.");
		      form.income.focus();
		      return false;
		   } 
		if (form.period.value == "") {
		      alert("지원기간을 입력하십시오.");
		      form.period.focus();
		      return false;
		   } 
		   if (form.howToApply.value == "") {
			      alert("지원방법을 입력하십시오.");
			      form.howToApply.focus();
			      return false;
			}
	   form.submit();
	}
	
	function policyList(targetUri) {
	      form.action = targetUri;
	      form.submit();
	}  
	</script>  
	
	
</head>
<body>
<!-- header -->
 <jsp:include page="/header.jsp"/>
  <br/><br/>
	    <center>정책 등록<hr/></center>

	    
	    
 <div id="div1">
	<form name="form" method="POST" action="<c:url value='/community/create' />">
	<table id= tableStyle>
	<tr>
	<td id = textStyle>정책이름</td> 
	<td><input type="text" name="policyName"></td>
	</tr>
	<tr>
	<td id = textStyle>정책내용 </td><td><textarea style="border-radius: 5px; border: 1px solid lightgray;" name="policyContent" rows="5" cols="42.5" ></textarea></td>
	</tr>
	<tr>
	<td id = textStyle>자격요건</td> <td>  <input type="text" name="qualificationForApplication"><br/></td>
	</tr>
	<tr>
	<td id = textStyle>지역 </td> <td><input type="text" name="local"><br/></td>
	</tr>
	<tr>
	<td id = textStyle>나이</td><td> <input type="text" name="age"><br/></td>
	</tr>
	<tr>
	<td id = textStyle>소득분위</td><td> <input type="text" name="income"><br/></td>
	</tr>
	<tr>
	<td id = textStyle>지원기간</td><td> <input type="text" name="period"><br/></td>
	</tr>
	<tr>
	<td id = textStyle>지원방법</td><td> <input type="text" name="howToApply"><br/></td>
	</tr>

	</table>
	<br/><br/>
	 <button type="button" value="등록" onClick="policyCreate()">등록</button>
	 
	</form>
	</div>

</body>
</html>