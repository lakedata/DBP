<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>마이페이지</title>
<style>
$
lred:#E57373 ;
 $red:#F44336 ;

 @mixin transition( $in) {
	transition: $in;
	-webkit-transition: $in;
	-moz-transition: $in;
	-o-transition: $in;
	-ms-transition: $in;
}

body {
	margin: 0;
	padding: 0;
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
	opacity: 1;
}

&>
.barry {
	display: none;
}

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
	cursor: pointer; @ include transition(all 0.5s ease-in-out); div .barry
	{ width : 40px;
	margin: 35px auto; . bar { display : block;
	width: 100%;
	height: 5px;
	margin-top: 3px;
	border-radius: 2px;
	background-color: #fff;
}

}
ul {
	opacity: 0;
	display: none; @ include transition(all 0.5s ease-in-out);
	list-style-type: none;
	padding: 0;
	width: 500px;
	text-align: center;
	margin-bottom: 0; li { display : inline-block; a { text-decoration :
	none;
	display: inline-block;
	padding: 15px 25px;
	color: $red;
	font-size: 20px; @ include transition(all 0.3s ease-in-out);
	border: 3px solid transparent;
	border-radius: 5px;
	&:
	hover
	{
	border-color
	:
	$
	red;
}

&
:target {
	border-bottom-color: $red;
}

}
}
}
}
body {
	margin: 0px;
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

.travel {
	border-radius: 10px;
	width: 95%;
	height: 90%;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/home/header.jsp" />
	<input type='checkbox' id='mmeennuu'>
	<label class='menu' for='mmeennuu'>

		<div class='barry'>
			<span class='bar'></span> <span class='bar'></span> <span class='bar'></span>
			<span class='bar'></span>
		</div>

		<ul>
			<li><a id='home' href="<%= request.getContextPath() %>/home.jsp">Home</a></li>
			<li><a id='about'
				href="<%= request.getContextPath() %>/policySearch.jsp">작성글</a></li>
			<li><a id='contact'
				href="<%= request.getContextPath() %>/postWrite.jsp">작성댓글</a></li>
			<li><a id='link'
				href="<c:url value='post/scrap/list'/>">스크랩</a></li>
		</ul>
	</label>
	<div class="flex-container"
		style="margin-left: 100px; margin-right: 100px">
		<div class="flex">
			<div class="flex fdc">
				<img class="travel" style="object-fit: fill;"
					src="https://a0.muscache.com/im/pictures/a0316ecb-e49b-4b3a-b6b6-c2876b820e8c.jpg?im_w=720">
				<span class="font_ml font_bold">작성한글</span>
			</div>

			<div class="flex fdc">
				<img class="travel" style="object-fit: fill;"
					src="https://a0.muscache.com/im/pictures/ff69ac49-64e7-4f4a-ae2b-ee01163d0790.jpg?im_w=720">
				<span class="font_ml font_bold">작성한댓글</span>
			</div>
			<div class="flex fdc">
				<img class="travel"
					src="https://a0.muscache.com/im/pictures/ce6814ba-ed53-4d6e-b8f8-c0bbcf821011.jpg?im_w=720">
				<span class="font_ml font_bold">정책스크랩</span>
			</div>
			<div class="flex fdc">
				<img class="travel" style="object-fit: fill;"
					src="https://a0.muscache.com/im/pictures/fbe849a4-841a-41b3-b770-419402a6316f.jpg?im_w=720">
				<span class="font_ml font_bold">정책일정</span>
			</div>
		</div>
	</div>
</body>
</html>