<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
<style>
body { 
   background-image: url("https://ifh.cc/g/IdXDEh.jpg");
}
<style>
.image {
	width:5;
	height:5;     
	object-fit:cover;
}
@media ( max-width :420px) {
	#head_wrap {
		padding-left: 4%;
		padding-right: 4%;
	}
	#logo_wrap {
		width: 100%;
		padding-right: 30%;
		padding-left: 30%;
		margin-top: 18%;
		margin-bottom: 18%;
	}
	#start_text_wrap, #reg_text_wrap, #serach_text_wrap, #mypage_text_wrap {
		width: 95%;
		padding-left: 5%;
		padding-right: 5%;
		font-size: 14px;
	}
	#start_wrap, #reg_wrap, #serach_wrap, #mypage_wrap {
		margin-top: 10%;
		margin-bottom: 10%;
	}
}
@media ( min-width :420px) and (max-width:840px) {
	#head_wrap {
		padding-left: 5%;
		padding-right: 5%;
	}
	#logo_wrap {
		width: 100%;
		padding-right: 35%;
		padding-left: 35%;
		margin-top: 15%;
		margin-bottom: 15%;
	}
	#start_text_wrap, #reg_text_wrap, #serach_text_wrap, #mypage_text_wrap {
		width: 90%;
		padding-left: 7%;
		padding-right: 7%;
		font-size: 15px;
	}
	#start_wrap, #reg_wrap, #serach_wrap, #mypage_wrap {
		margin-top: 8%;
		margin-bottom: 8%;
	}
}
@media ( min-width :840px) and (max-width:1280px) {
	#head_wrap {
		padding-left: 6%;
		padding-right: 6%;
	}
	#logo_wrap {
		width: 100%;
		padding-right: 38%;
		padding-left: 38%;
		margin-top: 10%;
		margin-bottom: 10%;
	}
	#start_text_wrap, #reg_text_wrap, #serach_text_wrap, #mypage_text_wrap {
		width: 80%;
		padding-left: 9%;
		padding-right: 9%;
		font-size: 16px;
	}
	#start_wrap, #reg_wrap, #serach_wrap, #mypage_wrap {
		margin-top: 7%;
		margin-bottom: 7%;
	}
}
@media ( min-width :1280px) and (max-width:1400px) {
	#head_wrap {
		padding-left: 8%;
		padding-right: 8%;
	}
	#logo_wrap {
		width: 100%;
		padding-right: 40%;
		padding-left: 40%;
		margin-top: 10%;
		margin-bottom: 10%;
	}
	#start_text_wrap, #reg_text_wrap, #serach_text_wrap, #mypage_text_wrap {
		width: 70%;
		padding-left: 10%;
		padding-right: 10%;
		font-size: 17px;
	}
	#start_wrap, #reg_wrap, #serach_wrap, #mypage_wrap {
		margin-top: 5%;
		margin-bottom: 5%;
	}
}
/*1280 이상일 때 이미지 사이즈에 맞춰서 */
@media ( min-width :1400px) {
	#head_wrap {
		padding-left: 10%;
		padding-right: 10%;
	}
	#introduce_wrap {
		font-size: 36px;
	}
	#start_text_wrap, #reg_text_wrap, #serach_text_wrap, #mypage_text_wrap {
		width: 60%;
		padding-left: 12%;
		padding-right: 12%;
		font-size: 18px;
	}
	#start_wrap, #reg_wrap, #serach_wrap, #mypage_wrap {
		margin-top: 5%;
		margin-bottom: 5%;
	}
}
</style>

</head>
<body >
 <jsp:include page="/header.jsp"/>
 <div id="head_wrap">
		<div id="logo_wrap">
			<img src="https://ifh.cc/g/eOQkpg.png" class="img-responsive"
				alt="checkkeu" height="100">
		</div>
		<div id="introduce_wrap">
			Checkkeu <br> 청년에게 꼭 필요한 사용자 맞춤 청년 정책 검색 웹사이트<br>
		</div>
	</div>
	<!-- head_wrap -->

	<div id="main_wrap">
		<div id="start_wrap">
	
				<a id="start_icon_wrap" href="<c:url value='user/login'/>" style="cursor:pointer;">
				<span>로그인 시작</span></a>
			</div>
			<div id="start_text_wrap">시작하기 버튼을 클릭하면 로그인을 할 수 있습니다.
				사용자 맞춤형 정책 공간에서 자신만의 정책을 찾고 나눠보세요.</div>
		</div>
		<div id="reg_wrap">
			<a id="reg_icon_wrap" href="<c:url value='user/register'/>" style="cursor:pointer;">
				<span>회원등록</span> </a>
			</div>
			<div id="reg_text_wrap">회원가입을 통해 checkkeu에 등록하여 제공되는 서비스를 무료로
				이용할 수 있습니다. 등록한 정보로 접속시 checkkeu에서 자동으로 고객님을 인식합니다.</div>
	
		<div id="serach_wrap">

			<a id="serach_icon_wrap" href="<c:url value='policy/search'/>" style="cursor:pointer;">
				<span>정책 관리</span></a>
			
			<div id="serach_text_wrap">checkkeu에 고객님의 원하는 정책을 검색할 수 있습니다.</div>
		</div>
		<div id="mypage_wrap">

				<a id="mypage_icon_wrap" href="<c:url value='policy/insert'/>" style="cursor:pointer;">
				<span>마이페이지</span>
			</a>
			<div id="mypage_text_wrap">스크랩한 정책과 글, 댓글, 개인정보를 확인할 수 있습니다.</div>
		
		</div>
	<!-- main_wrap -->
</body>
</html>