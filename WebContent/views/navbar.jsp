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
				<li><a href="/member/loginAction.do" onMouseOver='this.innerHTML="로그인"' onMouseOut='this.innerHTML="LOGIN"'>LOGIN</a></li>
				<li><a href="/member/registerAction.do" onMouseOver='this.innerHTML="회원가입"' onMouseOut='this.innerHTML="JOIN"'>JOIN</a></li>
				<%
					} else {
				%>
				<li><a href="/mymenu/myPage" onMouseOver='this.innerHTML="나의메뉴"' onMouseOut='this.innerHTML="MY MENU"'>MY MENU</a></li>
				<li><a href="/member/logoutAction.do" onMouseOver='this.innerHTML="로그아웃"' onMouseOut='this.innerHTML="LOGOUT"'>LOGOUT</a></li>
				<%
					}
				%>
				<li><a href="" onMouseOver='this.innerHTML="장바구니"' onMouseOut='this.innerHTML="CART"'>CART</a></li>
				<li><a href="" onMouseOver='this.innerHTML="주문조회"' onMouseOut='this.innerHTML="ORDER INQUIRY"'>ORDER INQUIRY</a></li>
				<li><a href="/board/" onMouseOver='this.innerHTML="게시판"' onMouseOut='this.innerHTML="BOARD"'>게시판</a></li>
				<li><a href="/item/mainItem.do" onMouseOver='this.innerHTML="아이템페이지"' onMouseOut='this.innerHTML="item"'>아이템</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>