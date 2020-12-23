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
		filter = "code";
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

<script type="text/javascript">
	function searchArticle() {
		var filter = $('#filter option:selected');
		var keyword = $('#keyword');
		location.href = "/admin/memberManagement?pn=1&filter=" + filter.val()
				+ "&keyword=" + encodeURI(keyword.val());
	}
</script>
</head>
<body>
	회원관리
	<select name="filter" id="filter">
		<option value="id" selected>아이디</option>
		<option value="name">이름</option>
	</select>
	<input name="keyword" id="keyword">
	<button onclick="searchArticle()">검색</button>
	
	<button onclick="location.href='/admin/'">관리자 홈으로</button>

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

</body>
</html>