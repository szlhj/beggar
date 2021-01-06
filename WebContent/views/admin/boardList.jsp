<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@page import="shop.beggar.beggar.vo.BoardVo"%>
<%@page import="shop.beggar.common.Pagenation"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	    ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
		Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
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
		
		String pn = request.getParameter("pn");
		String filter = (String)request.getAttribute("filter");
		
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
<title>Insert title here</title>
<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	
	function onChangeEvent() {
		searchArticle();
	}
	function searchArticle(){
		var filter = $('#filter option:selected');
		var firstTime = $('#firstTime');
		var lastTime = $('#lastTime');
		location.href = "/admin/boardList?pn=1&filter=" + filter.val()
		+ "&firstTime=" + firstTime.val()
		+ "&lastTime=" + lastTime.val();
	}
	function boardAdd() {
		location.href = "/admin/boardAdd";
	}
</script>

</head>
<body>
	게시판리스트
	<select name="filter" id="filter" onchange="onChangeEvent();">
		<option value="" <%="".equals(filter)?"selected":"" %>></option> 
		<option value="1"  <%="1".equals(filter)?"selected":"" %>>공지사항</option>
		<option value="2" <%="2".equals(filter)?"selected":"" %>>1:1문의</option>
		<option value="3" <%="3".equals(filter)?"selected":"" %>>제품관련문의</option>
		<option value="4" <%="4".equals(filter)?"selected":"" %>>내가쓴글보기</option>
	</select>
	날짜
	<input type="date" name="firstTime" id="firstTime"> ~
	<input type="date" name="lastTime" id="lastTime">
	<button onclick="searchArticle()">검색</button>
	<br>

	<button onclick="location.href='/admin/'">관리자 홈으로</button>
	<button onclick="location.href='/'">홈으로</button>

	<table border="1">
		<tr>
			<th>구분</th>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>생성일자</th>
			<th>조회수</th>
			<th>삭제여부</th>
		</tr>
		<%for (int i = 0; i < list.size(); i++) { %>
			<tr onclick="location.href='/admin/boardDetail?board_sq=<%=list.get(i).getBoard_sq() %>&pn=<%=pn %>&mber_id=<%=list.get(i).getMber_id() %>'">
				
				<%String Board_numberName =""; %>
				<%if(list.get(i).getBoard_number()==1){ %>
					<%Board_numberName = "공지사항";%>
				<%}else if(list.get(i).getBoard_number()==2){ %>
					<%Board_numberName = "1:1문의";%>
				<%}else if(list.get(i).getBoard_number()==3){ %>
					<%Board_numberName = "제품관련문의";%>
				<%} %>
				
				<td><%=Board_numberName %></td>
				<td><%=list.get(i).getBoard_sq() %></td>
				<td><%=list.get(i).getTitle() %></td>
				<td>
					<%if (list.get(i).getMber_sq() == 0) { %>
						관리자
					<%} else { %>
						<%=list.get(i).getMber_id() %>
					<%} %>
				</td>
				<td><%=list.get(i).getDttm() %></td>
				<td><%=list.get(i).getCount() %></td>
				<td><%=list.get(i).getDel_fl() %></td>
			</tr>
		<%} %>
	</table>
	
	<%if(adminVo!=null||!memberVo.getId().equals("")){ %>
	<button onclick="boardAdd()">글작성</button>
	<br>
	<%}%>
	
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