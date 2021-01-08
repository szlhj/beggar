<%@page import="shop.beggar.common.Pagenation"%>
<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<MemberVo> list = (ArrayList<MemberVo>) request.getAttribute("list");
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	String pn = request.getParameter("pn");
	
	String filter = request.getParameter("filter");
	String keyword = request.getParameter("keyword");
	
	if (keyword == null) {
		keyword = "";
	}
	if (filter == null) {
		filter = "";
	}
	if (pn == null) {
		pn = "1";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/adminMemberViews.css" type="text/css">

<script type="text/javascript">
	function searchArticle() {
		var filter = $('#filter option:selected');
		var keyword = $('#keyword');
		var firstTime = $('#firstTime');
		var lastTime = $('#lastTime');
		location.href = "/admin/memberManagement?pn=1&filter=" + filter.val()
				+ "&keyword=" + encodeURI(keyword.val())
				+ "&firstTime=" + firstTime.val()
				+ "&lastTime=" + lastTime.val();
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="adminMemberViews">
		<div class="memberViews">
			<h3>회원관리</h3>
			<select name="filter" id="filter">
				<option value="id" selected>아이디</option>
				<option value="name">이름</option>
			</select>
			<input name="keyword" id="keyword">
			<br>
			<h4>날짜</h4>
			<input type="date" name="firstTime" id="firstTime"> ~
			<input type="date" name="lastTime" id="lastTime">
			<button class="memberViews_button" onclick="searchArticle()">검색</button>
			<br>
		
			<table border=1>
				<tr>
					<td>번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>가입날짜</td>
				</tr>
				<%
					for (int i = 0; i < list.size(); i++) {
				%>
				<tr sq=<%=list.get(i).getMber_sq()%>>
					<td><%=list.get(i).getMber_sq()%></td>
					<td><%=list.get(i).getId()%></td>
					<td><%=list.get(i).getName()%></td>
					<td><%=list.get(i).getDttm()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				if (pagenation.getStartPageNumber() != 1) {
			%>
			<a
				href="/admin/memberManagement?pn=<%=pagenation.getStartPageNumber() - 1%>"><</a>
			<%
				}
			%>
			<%
				for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
				if (i != Integer.parseInt(pn)) {
			%>
			<a href="/admin/memberManagement?pn=<%=i%> "><%=i%></a>
			<%
				} else {
			%>
			<%=i%>
			<%
				}
			}
			%>
			<%
				if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {
			%>
			<a
				href="/admin/memberManagement?pn=<%=pagenation.getEndPageNumber() + 10%>">></a>
			<%
				}
			%>
		</div>
	</div>
	<jsp:include page="/views/admin/adminNavigation.jsp" />

</body>
</html>