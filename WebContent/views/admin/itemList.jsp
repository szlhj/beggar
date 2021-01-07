<%@page import="shop.beggar.common.Pagenation"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	    ArrayList<ItemVo> list = (ArrayList<ItemVo>) request.getAttribute("list");
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
<title>Insert title here</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/itemList.css" type="text/css">

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
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="itemList">
		<div class="list">
			<h3>상품관리</h3>
			<select name="filter" id="filter">
				<option value="code" <%="code".equals("filter")?"selected":"" %>>상품코드</option>
				<option value="name" <%="name".equals("filter")?"selected":"" %>>상품명</option>
			</select>
			<input name="keyword" id="keyword" value="<%=keyword %>">
			<button class="list_button" onclick="searchArticle()">검색</button>
			
<!-- 			<button onclick="location.href='/admin/'">관리자 홈으로</button> -->
<!-- 			<button onclick="location.href='/'">홈으로</button> -->
			
			<table border="1">
				<tr>
					<th>상품카테고리code</th>
					<th>상품카테고리</th>
					<th>상품코드</th>
					<th>상품명</th>
					<th>가격</th>
					<th>생성일자</th>
					<th>등록여부code</th>
					<th>등록여부</th>
					<th>등록자code</th>
					<th>등록자</th>
					<th>삭제여부code</th>
					<th>삭제여부</th>
				</tr>
				<%if (list == null) { %>
					<tr>
						<td>존재하지 않습니다.</td>
					</tr>
				<%} else { %>
					<%for (int i = 0; i < list.size(); i++) { %>
						<tr onclick="location.href='/admin/itemDetail?item_sq=<%=list.get(i).getItem_sq() %>&pn=<%=pn %>'">
							<td><%=list.get(i).getCategory() %></td>
							<%if (list.get(i).getCategory().equals("1")) { %>
								<td>NEW IN(뉴어라이벌)</td>
							<%} else if (list.get(i).getCategory().equals("2")) { %>
								<td>EARRINGS(이어링)</td>
							<%} else if (list.get(i).getCategory().equals("3")) { %>
								<td>NECKLACES(네크리스)</td>
							<%} else if (list.get(i).getCategory().equals("4")) { %>
								<td>BRACELETS(브레이슬릿)</td>
							<%} else if (list.get(i).getCategory().equals("5")) { %>
								<td>RINGS(링)</td>
							<%} else if (list.get(i).getCategory().equals("6")) { %>
								<td>ANKLETS(앵클릿)</td>
							<%} else if (list.get(i).getCategory().equals("7")) { %>
								<td>BEST(베스트셀러)</td>
							<%} else { %>
								<td>기타</td>
							<%} %>
							
							<td><%=list.get(i).getCode() %></td>
							<td><%=list.get(i).getItem_name() %></td>
							<td><%=list.get(i).getPrice() %></td>
							<td><%=list.get(i).getDttm() %></td>
							
							<td><%=list.get(i).isShow_fl() %></td>
							<%if (list.get(i).isShow_fl() == true) { %>
								<td>등록 중</td>
							<%} else { %>
								<td>등록 중지</td>
							<%} %>
							<td><%=list.get(i).getAdmin_sq() %></td>
							<td><%=list.get(i).getAdmin_name() %></td>
							<td><%=list.get(i).isDel_fl() %></td>
							<%if (list.get(i).isDel_fl() == true) { %>
								<td>사용 중지</td>
							<%} else { %>
								<td>사용 중</td>
							<%} %>
						</tr>
					<%} %>
				<%} %>
			</table>
			
			<%if (pagenation.getStartPageNumber() != 1) {%>
				<a href="/admin/itemList?pn=<%=pagenation.getStartPageNumber() - 1 %>"> << </a>
			<% } %>
			
			<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
				<%if (i != Integer.parseInt(pn)) {%>
					<a href="/admin/itemList?pn=<%=i %>"> <%=i%> </a>
				<%} else  {%>
					<%=i %>
				<%} %>
			<%} %>
			<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
				<a href="/admin/itemList?pn=<%=pagenation.getEndPageNumber() + 1 %>"> >> </a>
			<%} %>
			
		</div>
	</div>
	<jsp:include page="/views/admin/adminNavigation.jsp" />
	
</body>
</html>