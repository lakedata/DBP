<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!--<%@page import = "model.dto.*" %>-->
<!--<%@page import = "model.dao.*" %>-->
<!--<%@page import ="controller.product.*" %>-->

<%  // DAO를 사용한 트랜젝션
int arr[] = null;
%>    

	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="EUC-KR">
	<title>정책 상세보기</title>
	<style>
		#detail {
			padding-left: 150px;
			width: 80%;

		}
		
		#policyTypeMenu {
			
			width: 100px;
			background-color: lightgray;
			
		}
		#policyTypeMenu li:hover {
			background-color: #635BFF;
			color: white;
		}
		ul {
			list-style-type: none;
		
		}
		li {

		display: block;
		}
		
		table {
			border: 1px solid lightgray;
			border-collapse: collapse;
			padding: 10px;
			width: 90%;
		}
		 th, td {
    		border: 1px solid #EDEDED;
    		padding: 5px;
 
  		}
  		th {
  			background-color: #F5F5F5;
  			width: 90px;
  			font-weight: normal;
  		}
	
	</style>
	</head>
	<script>
	function chkDelete(id) {
	//  삭제 여부 확인
	   var r = confirm("삭제하시겠습니까?");
	   
	   if (r) {
	      location.href = "deleteOk.jsp?uid=" + id;
	   }
	}
	</script>
	<body>
	
<!-- header -->
 <jsp:include page="/header.jsp"/>
	

				
	<div id="detail">
	<h3>
	${policy.name} </h3>
	${policy.contents} <br><br>
	
	
	<b>지원대상</b><br><br>
	<table>
	<tr>
	<th>자격 요건</th>  
	<td>${policy.qualificationForApplication}</td>
	</tr>
	<tr>
	<th>지역</th>
	<td>${viewItem.local} </td>
	</tr>
	<tr>
	<th>나이</th>
	<td>${viewItem.age} </td>
	</tr>
	<tr>
	<th>소득분위</th>
	<td>
	${viewItem.income} 분위 이하 
	</td>
	</tr>
	</table>
	<br>
	<b>신청 방법</b><br><br>
	<table>
	<tr>
	<th>지원기간	</th>
	<td>${viewItem.period} </td>
	</tr>
	<tr>
	<th>지원방법</th>
	<td>
	${viewItem.howToApply} </td>
	
	
	</table>
	
	<br>
	</div>

	
</body>
</html>