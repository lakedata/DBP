<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/home/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내가 쓴 댓글</title>
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
<table>  
    <tr>   
        <td style="width: 80%">댓글</td>
    </tr>
    <c:choose>
        <c:when test="${message != null }">
            <tr>
                <td>${message }</td>
            </tr>   
        </c:when>
        <c:otherwise> 
            <c:forEach var="list" items="${list }">
                <tr>
                    <td><font size="1.5"><b>${list.cid }</b>
                        ${list.ctime }
                        <c:if test = "${sessionId.id == list.cid }">
                            <b>
                                <a href="javascript:open_win('BoardServlet?command=comment_edit_delete&cnum=${list.cnum }','noname')">[수정/삭제]</a>
                            </b>
                        </c:if>
                        </font>
                        <br>
                            ${list.ccontent }
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
 
<c:forEach var="i" begin = "1" end ="${totalCount }" step="1">
    <a href="BoardServlet?command=board_view&num=${param.num }&page=${i}">[${i}]</a>
</c:forEach> 
<br>
</body>
</html>
 <%@ include file="/WEB-INF/home/footer.jsp" %>