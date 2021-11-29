<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 상세보기</title>
<style>
		#detail {
			padding-top: 30px;
			padding-left: 150px;
			width: 80%;
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
	<div id="detail">
	
	<center>글 상세보기</center><br><br>
	<table>
    
	  	  <tr>
			<th>제목</th>
			<td>${review.title}</td>
		  </tr>
		  <tr>
			<th>작성자</th>
			<td>${review.writer}</td>
		  </tr>
		  <tr>
			<th>날짜</th>
			<td>${review.creationDate}</td>
		  </tr>
		  <tr>
			<th>내용</th>
			<td>${review.content}</td>
		  </tr>
	
	</table>
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