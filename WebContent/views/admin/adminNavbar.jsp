<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@page import="shop.beggar.common.LoginManager"%>
<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%
	    AdminVo vo = (AdminVo) session.getAttribute("adminVo");
		int adminSupperCount = (Integer) session.getAttribute("adminSupperCount");
		
		int admin_sq = 0;
		
		if (vo != null) {
			admin_sq = 1;
		}
		
		boolean admin_supper = (vo == null) ? false : vo.isAdmin_supper();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BEGGAR</title>
<link rel="stylesheet" href="/views/css/style.css" type="text/css">
<link rel="stylesheet" href="/views/css/ress.css" type="text/css">
<link rel="stylesheet" href="/views/css/admin/adminNavbar.css" type="text/css">

</head>
<body>
	<nav class="navbar">
<!-- 		<div class="navbar_logo"> -->
<!-- 			<a href="/"><img class="logo" alt="X" src="/views/img/logo.png" style="width: 200px; height: 200px"></a> -->
<!-- 		</div> -->
		<div class="navbar_banner">
			<a href="/"><img class="banner" alt="X" src="/views/img/banner.png"></a>
		</div>
<!-- 		<div class="navbar_logo"> -->
<!-- 			<a href="/"><img class="logo" alt="X" src="/views/img/logo.png" style="width: 200px; height: 200px"></a> -->
<!-- 		</div> -->
	</nav>
</body>
</html>