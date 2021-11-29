<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>마이페이지</title>
<style>
$lred:#E57373;
$red:#F44336;

@mixin transition($in) {
	transition:$in;
	-webkit-transition:$in;
	-moz-transition:$in;
	-o-transition:$in;
	-ms-transition:$in;
}

body {
	margin:0;
	padding:0;
	background-color:#222;
}
* {
	font-family:Helvetica,sans-serif;
	color:#555;
}

#mmeennuu {
	display:none;
}

#mmeennuu:checked ~ .menu {

	width:500px;
	border-radius:5px;
	background-color:transparent;
	border:3px solid $red;
	height:85px;

	&>ul {
		display:block;
		opacity:1;
	}
	&>.barry {
		display:none;
	}
}

.menu {
	display:block;
	margin:30px auto;
	width:100px;
	height:100px;
	background-color:$red;
	border:3px solid transparent;
	border-radius:50%;
	overflow:hidden;
	cursor:pointer;
	@include transition(all 0.5s ease-in-out);

	div.barry {
		width:40px;
		margin:35px auto;


		.bar {
			display:block;
			width:100%;
			height:5px;
			margin-top:3px;
			border-radius:2px;
			background-color:#fff;
		}
	}

	ul {
		opacity:0;
		display:none;
		@include transition(all 0.5s ease-in-out);
		list-style-type:none;
		padding:0;
		width:500px;
		text-align:center;
		margin-bottom: 0;

		li {
			display:inline-block;
			a {
				text-decoration:none;
				display:inline-block;
				padding:15px 25px;
				color:$red;
				font-size:20px;
				@include transition(all 0.3s ease-in-out);
				border:3px solid transparent;
				border-radius:5px;

				&:hover {
					border-color:$red;
				}
				
				&:target {
					border-bottom-color:$red;
				}
			}
		}
	}
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp" />
<input type='checkbox' id='mmeennuu'>
<label class='menu' for='mmeennuu'>

<div class='barry'>
	<span class='bar'></span>
	<span class='bar'></span>
	<span class='bar'></span>
	<span class='bar'></span>
</div>
	
<ul>
	<li><a id='home' href="<%= request.getContextPath() %>/home.jsp">Home</a></li>
	<li><a id='about' href="<%= request.getContextPath() %>/policySearch.jsp">작성글</a></li>
	<li><a id='contact' href="<%= request.getContextPath() %>/postWrite.jsp">작성댓글</a></li>
	<li><a id='link' href="<%= request.getContextPath() %>/scrap.jsp">스크랩</a></li>
</ul>



</label>	
</body>
</html>