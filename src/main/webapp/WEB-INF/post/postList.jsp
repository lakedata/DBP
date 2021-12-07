<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ include file="/WEB-INF/home/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판</title>
<style>

		table {
			border: 1px solid lightgray;
			border-collapse: collapse;
			padding: 10px;
			margin-top: 80px;
			margin: 100px auto;
			width: 70%; 
			align: center;
		}
		 th, td {
    		border: 1px solid #EDEDED;
    		padding: 5px;
 
  		}
  	
</style>
</head>
<body>
	<%
	request.setAttribute("userId", request.getParameter("userId"));
	%>
	
	  <c:choose>
	  <c:when test="${userId!=NULL}">    
             <button style="border: none; width: 80px; height: 30px; margin-top: 50px; margin-left: 995px; border-radius: 7px;"><a style="text-decoration : none;" href="<c:url value='/post/write'/>">글쓰기</a></button>
      </c:when>
      </c:choose>
       
	<table>
      <thead>
		<tr>
		  <td style="width: 70%">제목</td>
		  <td>작성자</td>
		  <td>날짜</td>
		</tr>
      </thead>
      <tbody>  	 <!-- post 최신 글을 상단으로 올리기  -->
		<c:forEach var="post" items="${postList}">
			<tr>
			  <td style="width: 70%"><a href="<c:url value='/post/view'>
						      <c:param name='postNum' value='${post.postNum}'/>
						   </c:url>">
				  ${post.title} </a>
			  </td>
			  <td>${post.userId}</td>
			  <td>${post.writeDate}</td>
			</tr>
		</c:forEach>
	  </tbody>
	</table>		
   
</body>
</html>

 <%@ include file="/WEB-INF/home/footer.jsp" %>