<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 작성</title>

<script>
	function postWrite() {
		if (form.title.value == "") {
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		} 
		else if (form.content.value == "") {
			alert("내용을 입력하십시오.");
			form.content.focus();
			return false;
		}	
	
		form.submit();
	}
	
</script>
</head>
<body>

<!-- header -->
   <jsp:include page="/WEB-INF/home/header.jsp" />
   
   <div style=" display: flex; justify-content: center;">

 <!--실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<div>
		<c:if test="${creationFailed}">
			<h6><c:out value="${exception.getMessage()}"/></h6>
		</c:if>
	</div>	 
	
	
    <form class="form" name="form" method="POST" action="<c:url value='/post/add' />">
        <table style="margin-top: 100px;">
                <tr>
                <td height=20 align= center bgcolor=#8080FF><font color=white> 글쓰기</font></td>
                </tr>
                <tr>
                <td bgcolor=white>
                <table class = "table2">
                        <tr>
                        <td>작성자</td>
                        <td><input type="text" name="name" size=20> </td>
                        </tr>
 
                        <tr>
                        <td>제목</td>
                        <td> <input type="text" name="title" size=20
	            			<c:if test="${creationFailed}"> value="${post.title}"</c:if>>	  </td>
                        </tr>
 
                        <tr>
                        <td>내용</td>
                        <td><textarea name="content" cols=85 rows=15></textarea></td>
                        </tr>
 
                      
                        </table>
 
                </td>
                </tr>
        </table>
        
  
		<input type="button" value="등록" onClick="postWrite()"> &nbsp;
	    <input type="reset" value="초기화">
    </form>
</div>

  <!-- footer -->
		<jsp:include page="/WEB-INF/home/footer.jsp"/>
</body>
</html>