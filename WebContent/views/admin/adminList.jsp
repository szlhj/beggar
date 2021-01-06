<%@page import="shop.beggar.common.Pagenation"%>
<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<AdminVo> list = (ArrayList<AdminVo>) request.getAttribute("adminList");
		Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
    	
    	String pn = (String) request.getAttribute("pn");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 리스트</title>
</head>
<body>
	<button onclick="location.href='/admin/'">관리자 홈으로</button>
	<button onclick="location.href='/'">홈으로</button>
	<button onclick="location.href='/admin/listDummy'">admin dummy</button>

	<table border="1">
		<tr>
			<th>관리자 일련번호</th>
			<th>관리자 아이디</th>
			<th>관리자 이름</th>
			<th>관리자 이메일</th>
			<th>관리자 전화번호</th>
			<th>관리자 슈퍼관리자</th>
			<th>관리자 삭제 여부</th>
			<th>관리자 생성일자</th>
		</tr>
		<%for (int i = 0; i < list.size(); i++) { %>
			<tr onclick="location.href='/admin/detailAdmin?admin_sq=<%=list.get(i).getAdmin_sq() %>&pn=<%=pn %>'">
				
				<td><%=list.get(i).getAdmin_sq() %></td>
				<td><%=list.get(i).getAdminId() %></td>
				<td><%=list.get(i).getAdminName() %></td>
				<td><%=list.get(i).getAdminEmail() %></td>
				<td><%=list.get(i).getAdminPhone() %></td>
				<%if (list.get(i).isAdmin_supper() == true) { %>
					<td>슈퍼 관리자</td>
				<%} else { %>
					<td>관리자</td>
				<%} %>
				<%if (list.get(i).isAdmin_del_fl() == true) { %>
					<td>삭제</td>
				<%} else { %>
					<td>사용중</td>
				<%} %>
				<td><%=list.get(i).getDttm() %></td>
				
			</tr>
		<%} %>
	</table>
	
	<%if (pagenation.getStartPageNumber() != 1) {%>
		<a href="/admin/boardlist?pn=<%=pagenation.getStartPageNumber() - 1 %>"> << </a>
	<% } %>
	
	<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
		<%if (i != Integer.parseInt(pn)) {%>
			<a href="/admin/boardlist?pn=<%=i %>"> <%=i%> </a>
		<%} else  {%>
			<%=i %>
		<%} %>
	<%} %>
	<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
		<a href="/admin/boardlist?pn=<%=pagenation.getEndPageNumber() + 1 %>"> >> </a>
	<%} %>
</body>
</html>