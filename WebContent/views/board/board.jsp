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
			memberVo.setId("");
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

<!-- <link rel="stylesheet" href="/views/css/board.css" type="text/css"> -->
<!-- <link rel="stylesheet" href="/views/css/ress.css" type="text/css"> -->
<!-- <link rel="stylesheet" href="/views/css/navbar.css" type="text/css"> -->

</head>
<body>
<!-- 	<div> -->
<%-- 		<jsp:include page="/views/navbar.jsp"></jsp:include> --%>
<!-- 	</div> -->
	<div>
		<ul class="board_sideMenu">
			<li><a href="/board/notice">공지사항</a></li>
			<li><a href="/board/productRelated">제품관련문의</a></li>
			<li><a href="/board/myQuestion">내가쓴글보기</a></li>
		</ul>
	</div>
	<%if((adminVo!=null)||!(memberVo.getId().equals(""))){ %>
	<button onclick="boardAdd()">게시판작성</button>
	<br>
	<%}%>
	<button onclick="location.href='/'">취소</button>
</body>
</html>