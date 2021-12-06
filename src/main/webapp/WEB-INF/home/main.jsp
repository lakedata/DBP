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

.image {
	width: 5;
	height: 5;
	object-fit: cover;
}

#main_wrap {text-align: center;}

#start_wrap, #reg_wrap, #serach_wrap, #mypage_wrap {
	margin:0 auto;
}

#start_text_wrap, #reg_text_wrap, #serach_text_wrap, #mypage_text_wrap {
    background-color: #c5c6d0;
    color: #607b8b;;
    margin:0 auto;
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
<body>
<%
	request.setAttribute("userId", request.getParameter("userId"));
%>
	<!-- header -->
	<jsp:include page="/WEB-INF/home/header.jsp" />

	<div style="background-color:white; height:100%; opacity: 0.8;">
		<div id="head_wrap">
			<div id="logo_wrap">
				<img src="https://ifh.cc/g/eOQkpg.png" class="img-responsive"
					alt="checkkeu" height="100">
			</div>
			<div id="introduce_wrap">
				Checkkeu <br> 청년에게 꼭 필요한 사용자 맞춤 청년 정책 검색 웹사이트<br>
				1. 정책 검색
				나이, 거주지, 소득분위 등 검색 옵션을 부여하여 DB에서 해당 정책을 가져옴
				회원가입 시에 입력한 본인 정보와 맞는 정책을 보여주는 기능 (필터링)
	
				2. 마이페이지 
				아이콘을 클릭하여 자신의 관심 분야 정책을 스크랩할 수 있으며 ‘마이페이지’에서 캘린더 기능을 통해 확인 가능
				게시판에 작성한 본인의 게시글 열람 가능
				개인 정보 수정
	
				3. 게시판
				사용자가 자유롭게 정책을 제안 및 문제 제기 가능
				찬반, 댓글 기능으로 활발한 소통 가능
			</div>
		</div>
		
		<!-- footer -->
		<jsp:include page="/WEB-INF/home/footer.jsp" />
	</div>
</body>
</html>
