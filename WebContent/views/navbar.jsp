<%@page import="shop.beggar.common.LoginManager"%>
<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%
    	LoginManager lm = LoginManager.getInstance();
    	String id = lm.getMemberId(session);
    	
    	MemberVo vo = (MemberVo) session.getAttribute("vo");
    	
    	int mber_sq = 0;
    	if (vo != null) {
    		mber_sq = vo.getMber_sq();
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BEGGAR</title>
<link rel="stylesheet" href="/views/css/style.css" type="text/css">
<link rel="stylesheet" href="/views/css/ress.css" type="text/css">
<link rel="stylesheet" href="/views/css/navbar.css" type="text/css">

</head>
<body>
	<nav class="navbar">
		<div class="navbar_logo">
			<a href="/"><img class="logo" alt="X" src="/views/img/logo.png" style="width: 200px; height: 200px"></a>
		</div>
		<div class="navbar_banner">
			<a href="/"><img class="banner" alt="X" src="/views/img/banner.png"></a>
		</div>
		<div>
			<ul class="navbar_sideMenu">
				<%
					if (mber_sq == 0) {
				%>
				<li><a class="sideMenu" href="/member/loginAction">로그인</a></li>
				<li><a class="sideMenu" href="/member/registerAction">회원가입</a></li>
				<li><a class="sideMenu" href="/item/orderInquiry">주문조회</a></li>
				<%
					} else {
				%>
				<li><a class="sideMenu" href="/member/myPage">나의메뉴</a></li>
				<li><a class="sideMenu" href="/member/logoutAction">로그아웃</a></li>
				<%
					}
				%>
				<li><a class="sideMenu" href="/item/cartForm">장바구니</a></li>
				<li><a class="sideMenu" href="/board/">게시판</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>