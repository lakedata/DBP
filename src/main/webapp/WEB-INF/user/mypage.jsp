<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<%@ include file="/WEB-INF/home/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
@mixin transition( $in) {
   transition: $in;
   -webkit-transition: $in;
   -moz-transition: $in;
   -o-transition: $in;
   -ms-transition: $in;
}

body {

   background-color: #222;
}

* {
   font-family: Helvetica, sans-serif;
   color: #555;
}

#mmeennuu {
   display: none;
}

#mmeennuu:checked ~ .menu {
   width: 500px;
   border-radius: 5px;
   background-color: transparent;
   border: 3px solid$red;
   height: 85px; &> ul { display : block;
   opacity: 1;} &>.barry { display: none; }

}

.menu {
   display: block;
   margin: 30px auto;
   width: 100px;
   height: 100px;
   background-color: $red;
   border: 3px solid transparent;
   border-radius: 50%;
   overflow: hidden;
   cursor: pointer; @ include transition(all 0.5s ease-in-out);
   
    div .barry { 
		   width : 40px;
		   margin: 35px auto; 
	   . bar { display : block;
		   width: 100%;
		   height: 5px;
		   margin-top: 3px;
		   border-radius: 2px;
		   background-color: #fff;
		}
	}	
}

* {
   font-family: Arial;
   user-select: none;
   outline: none;
}

.font_ml {
   font-size: 16px;
}

.font_bold {
   font-weight: 600;
}

.flex {
   display: flex;
}

.flex-container {
   display: flex;
   justify-content: center
      /* flex-start, center, flex-end, space-around, space-evenly, space-between */
}

.fdc {
   flex-direction: column;
}

.mypage {
   border-radius: 10px;
   width: 95%;
   height: 90%;
}

</style>
</head>
<body>

   <input type='checkbox' id='mmeennuu'>
   <label class='menu' for='mmeennuu'>

      <div class='barry'>
         <span class='bar'></span> <span class='bar'></span> <span class='bar'></span>
         <span class='bar'></span>
      </div>

   
   </label>
   <div class="flex-container"
      style="margin-left: 100px; margin-right: 100px">
      <div class="flex">
         <div class="flex fdc">
            <img class="mypage" style="object-fit: fill;"
               src="<c:url value='/images/write.jpg'/>">
            <a href="<c:url value='/mypage/myPost'/>">
            	<span class="font_ml font_bold"> 작성한글 </span>
            </a>
         </div>

         <div class="flex fdc">
            <img class="mypage" style="object-fit: fill;"
                src="<c:url value='/images/comment.jpg'/>">
            <a href="<c:url value='/mypage/myComment'/>">
            	<span class="font_ml font_bold">작성한댓글</span>
            </a>
         </div>
         <div class="flex fdc">
            <img class="mypage"
                src="<c:url value='/images/scrap.jpg'/>">
            <a href="<c:url value='/mypage/scrap/view'/>">
            	<span class="font_ml font_bold">정책스크랩</span>
            </a>
         </div>
         <div class="flex fdc">
            <img class="mypage" style="object-fit: fill;"
               src="<c:url value='/images/calendar.jpg'/>">
            <a href="<c:url value='/mypage/calendar/view'/>">
            	<span class="font_ml font_bold">정책일정</span>
            </a>
         </div>
      </div>
   </div>
</body>
</html>