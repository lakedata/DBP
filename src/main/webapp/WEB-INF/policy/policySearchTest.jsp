<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>policy search test</title>

<style type="text/css">

		#board, #bList, #pageForm {
		    text-align :center;
		}
		
		.policySearch-div {
			margin-top: 50px;
			width: 80%;
			margin-left: 120px;
			
			}
			
		.btnStyle {
			border: none;
			width: 90px;
			height: 30px;
			border-radius: 20px;
		}
		.btn-div{
		 	display: flex;
  			justify-content: center;
  			margin: 30px;
		}

</style>

</head>
<body>
 	<!-- header -->
<jsp:include page="/WEB-INF/home/header.jsp" />
	<h1>test</h1>
  <!-- 정책 검색 목록  -->
    <br>
    <div id="board">
        <table>
        <thead>
         <tr>
                <td>정책명</td>
                <td>유형</td>
                <td style="width: 60%;">요약</td>
         </tr>
		</thead>
		<tbody>
        <c:forEach var="policy" items="${searchPolList}">
            <tr>
                <td>
                    <a href="<c:url value='/policy/view'>
                    <c:param name='policyId' value='${policy.policyId}'/>
                    </c:url>">
                    ${policy.name}</a>
                </td>
                <td>
                 ${policy.category}
               
                </td>
                <td>${policy.policySummary}</td>  
            </tr>  
		</c:forEach>
		</tbody>
        </table>
    </div>
    
    <!-- 목록 검색 
    <div>
    	<c:choose>
		<c:when test="${empty polList}">
				결과없음</c:when>
		</c:choose>
		
		<c:forEach var="policy" items="${polList}">   
			<c:choose>
				<c:when test="${policy.name=='취업지원'}">
				<span>서울</span>
				</c:when>
				<c:when test="${policy.name=='창업지원'}">
				<span>경기</span>
				</c:when>
				<c:when test="${policy.name=='주거금융'}">
				<span>인천</span>
				</c:when>
				<c:when test="${policy.name=='생활복지'}">
				<span>기타</span>
				</c:when>
				<c:when test="${policy.name=='정책참여'}">
				<span>기타</span>
				</c:when>
			</c:choose>
							
		</c:forEach>
			
    </div>
    -->
    
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
    
     <!-- footer -->
	<jsp:include page="/WEB-INF/home/footer.jsp"/>

</body>
</html>