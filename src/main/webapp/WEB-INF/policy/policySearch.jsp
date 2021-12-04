<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR"> 
<title>정책 찾기</title>
<style type="text/css">

	table {
			border: 1px solid lightgray;
			border-collapse: collapse;
			margin-top: 30px;
			width: 100%; 
			align: center;
		}
		 th, td {
    		border: 1px solid #EDEDED;

  		}
  		
  		
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
		
		.policySearch-div {
			margin-top: 50px;
			width: 80%;
			margin-left: 120px;
			
			}

</style>
<script>
function uncheck(){
	$(':checkbox:checked').prop('checked',false);
};
</script>
</head>
<body>
<form action = "<%= request.getContextPath() %>/policySearch.jsp">

  	<!-- header -->
   <jsp:include page="/WEB-INF/home/header.jsp" />
   
<div class="policySearch-div">
	<b>정책유형</b>
	<input type="checkbox" name="contents" value="취업지원">취업지원
	<input type="checkbox" name="contents" value="창업지원">창업지원
	<input type="checkbox" name="contents" value="주거금융">주거금융
	<input type="checkbox" name="contents" value="생활복지">생활복지
	<input type="checkbox" name="contents" value="정책참여">정책참여

	<hr/><b>소득분위 </b>
	<select id="income" name="income">
		<option value="">분위</option>
		<c:forEach var="i" begin="1" end="10" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<hr/><b>거주지역 </b>
	<!-- 지역 서울, 경기, 인천,  전라도, 경상도, 충청도, 제주도, 강원도 -->
	<input type="checkbox" name="local" value="서울">서울
	<input type="checkbox" name="local" value="경기도">경기도
	<input type="checkbox" name="local" value="인천">인천
	<input type="checkbox" name="local" value="전라도">전라도
	<input type="checkbox" name="local" value="경상도">경상도
	<input type="checkbox" name="local" value="강원도">강원도
	<input type="checkbox" name="local" value="충청도">충청도
	<input type="checkbox" name="local" value="제주도">제주도
	
	
	
	<hr/><b>나이 </b>
	<select id="age" name="age">
		<option value="">살</option>
		<c:forEach var="i" begin="0" end="30">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<br>
	<br>
	<input type="submit" value="검색">
	<br>
	<br>

	<input type="button" value="페이지 새로고침" onClick="window.location.reload()">
	
	
</form>
  <!-- 정책 목록 부분 -->
    <br>
    <div id="board">
        <table>
         <tr>
                <td>정책명</td>
                <td>유형</td>
                <td style="width: 60%;">요약</td>
         </tr>

        <c:forEach var="policy" items="${polList}">
            <tr>
                <td>
                    <a href="<c:url value='/policy/view'>
                    <c:param name='policy' value='${policy.policyId}'/>
                    </c:url>">
                    ${policy.name}
                    </a>
                </td>
                <td>${policy.category}</td>
                <td>${policy.policySummary}</td>
                
            </tr>  
		</c:forEach>
	
        </table>
    </div>
    
    <!-- 페이지 넘버 부분 -->
    <br>
    <div id="pageForm">
        <c:if test="${startPage != 1}">
            <a href='BoardListAction.bo?page=${startPage-1}'>[ 이전 ]</a>
        </c:if>
        
        <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
            <c:if test="${pageNum == spage}">
                ${pageNum}&nbsp;
            </c:if>
            <c:if test="${pageNum != spage}">
                <a href='BoardListAction.bo?page=${pageNum}'>${pageNum}&nbsp;</a>
            </c:if>
        </c:forEach>
        
        <c:if test="${endPage != maxPage }">
            <a href='BoardListAction.bo?page=${endPage+1 }'>[다음]</a>
        </c:if>
    </div>
    
	</div>
     <!-- footer -->
	<jsp:include page="/WEB-INF/home/footer.jsp"/>

</body>
</html>