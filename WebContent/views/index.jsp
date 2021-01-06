<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@page import="shop.beggar.common.LoginManager"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
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
<link rel="preconnect" href="https://fonts.gstatic.com">

</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	<jsp:include page="/views/item/navigation.jsp"></jsp:include>
	
	<jsp:include page="/views/item/itemPage.jsp"></jsp:include>
</body>
</html>