<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 상세보기</title>
<style>
		.container {
			width: 80%;
			line-height: 50px;
			margin: 100px auto;
			margin-bottom: 100px;
		}
		
		h5 {
			text-align: center;
		}
		
		h5 span {
			color: teal;
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
  		 #btn {
		 	text-decoration:none
		 }


</style>
<script>
function postRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>

</head>
<body>
	
<!-- header -->
 <jsp:include page="/header.jsp"/>
	<div class="container">
        	<h5><span>글 상세보기</span></h5>
        	
	<table>
    
	  	  <tr>
			<th>제목</th>
			<td>${post.title}</td>
		  </tr>
		  <tr>
			<th>작성자</th>
			<td>${post.userId}</td>
		  </tr>
		  <tr>
			<th>날짜</th>
			<td>${post.writeDate}</td>
		  </tr>
		  <tr>
			<th>내용</th>
			<td>${post.content}</td>
		  </tr>
	
	</table>
	<!-- 동의/비동의 -->
	
	동의  비동의 
	
	
	<!-- 댓글 쓰기 -->
	<div style="margin-top: 50px;">
	<form name="form" method="POST" action="<c:url value='/post/reply/add' />">
		<input type="text" name="comment" placeholder="댓글을  작성하세요" style="width:85%; height: 50px; border-radius: 8px; border: none; background-color: #F5F5F5;">
		<input type="submit" value="등록" style="border: none; height: 50px; width: 50px; border-radius: 8px; " />
	</form>
	</div>
	
	<!-- 댓글 보기  -->
	<div>
	
	
	</div>
	
	
	<!-- 수정, 삭제, 목록  -->
	<div style="text-align: center; padding: 50px;">
		<a id="btn" href="<c:url value='/post/update'>
		     		   <c:param name='commId' value='${post.post_id}'/>
				  </c:url>">수정</a>		  
	    <a id="btn" href="<c:url value='/post/delete'>
					   <c:param name='commId' value='${post.post_id}'/>
				 </c:url>" onclick="return communityRemove();">삭제</a> 
	    <a id="btn" href="<c:url value='/post/list' />">목록</a> 
    
    </div>

</div>

</body>
</html>