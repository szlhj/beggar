<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@page import="shop.beggar.beggar.vo.BoardVo"%>
<%@page import="shop.beggar.common.Pagenation"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		AdminVo adminVo = (AdminVo) request.getAttribute("adminVo");
		MemberVo memberVo = (MemberVo) request.getAttribute("vo");
		
		if(memberVo == null && adminVo == null){
			memberVo = new MemberVo();
			memberVo.setId("비회원");
		}
		
		if(adminVo != null){
			memberVo = new MemberVo();
			memberVo.setId("관리자");
		}
		
    %>
<!DOCTYPE html>
<html>
<script type="text/javascript">

function boardAdd() {
	location.href = "/board/addBoard";
}

</script>
<head>
<meta charset="UTF-8">
<title>게시판 메인</title>

</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<jsp:include page="/views/board/boardNavigation.jsp"></jsp:include>
</body>
</html>