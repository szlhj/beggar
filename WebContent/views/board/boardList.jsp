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
			memberVo.setId("비회원");
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
		String yesOrNo = (String)request.getAttribute("yesOrNo");
		if(yesOrNo == null){
			yesOrNo = "false";
		}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/board/boardList.css" type="text/css">

<script type="text/javascript">
	function onChangeEvent() {
		searchArticle();
	}
	function searchArticle(){
		var filter = $('#filter');
		var firstTime = $('#firstTime');
		var lastTime = $('#lastTime');
		if(filter.text() == "공지사항"){
			location.href = "/board/notice?pn=1&filter=" + filter.val()
			+ "&firstTime=" + firstTime.val()
			+ "&lastTime=" + lastTime.val();	
		}
		else if(filter.text() == "1:1문의"){
			location.href = "/board/oneAndoneQuestion?pn=1&filter=" + filter.val()
			+ "&firstTime=" + firstTime.val()
			+ "&lastTime=" + lastTime.val();
		}
		else if(filter.text() == "제품관련문의"){
			location.href = "/board/productRelated?pn=1&filter=" + filter.val()
			+ "&firstTime=" + firstTime.val()
			+ "&lastTime=" + lastTime.val();
		}
		else if(filter.text() == "내가쓴글보기"){
			location.href = "/board/myQuestion?pn=1&filter=" + filter.val()
			+ "&firstTime=" + firstTime.val()
			+ "&lastTime=" + lastTime.val();
		}
	}
	function boardAdd() {
		location.href = "/board/addBoard";
	}
	function cancle(){
		location.href = "/";
	}
</script>

</head>
<body>
	<div class="boardListForm">
		<div class="boardList">
			<%if(filter.equals("1")){ %>
				<span id="filter">공지사항</span>
			<%}else if(filter.equals("2")){%>
				<span id="filter">1:1문의</span>
			<%}else if(filter.equals("3")){%>
				<span id="filter">제품관련문의</span>
			<%}else if(filter.equals("4")){%>
				<span id="filter">내가쓴글보기</span>
			<%}%>
			<br>
			날짜
			<input type="date" name="firstTime" id="firstTime"> ~
			<input type="date" name="lastTime" id="lastTime">
			<button onclick="searchArticle()">검색</button>
			<br>
			<%if(adminVo != null) {%>
				<button onclick="location.href='/admin/'">관리자 홈으로</button>
			<%} %>
			<button onclick="location.href='/'">홈으로</button>
		
			<table border="1">
				<tr>
					<th>구분</th>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>생성일자</th>
					<th>조회수</th>
			<%if((filter.equals("2"))||(filter.equals("3"))){ %>
					<th>답변상황</th>
					<%} %>
				</tr>
				<%for (int i = 0; i < list.size(); i++) { %>
					<tr onclick="location.href='/board/detailBoard?board_sq=<%=list.get(i).getBoard_sq() %>&pn=<%=pn %>&mber_id=<%=list.get(i).getMber_id()%>&yesOrNo=<%=yesOrNo%>&filter=<%=filter%>'">
						
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
				<%if((filter.equals("2"))||(filter.equals("3"))){ %>
							<% String CommentTF = "";%>
							<%if(list.get(i).getComment()==null||list.get(i).getComment().equals("")) {%>
							<% 		CommentTF= " X ";	%>
							<%}else{ %>
							<% 		CommentTF= " O ";%>
							<%} %>
							<td><%=CommentTF %></td>
						<%} %>
					</tr>
				<%} %>
			</table>
			<%if(filter.equals("1")){ %>
				<button onclick="location.href='/board/'">뒤로가기</button>
				<br>
				<%if (pagenation.getStartPageNumber() != 1) {%>
				<a href="/board/notice?pn=<%=pagenation.getStartPageNumber() - 1 %>&filter=<%=filter%>"> << </a>
				<% } %>
			
				<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
				<%if (i != Integer.parseInt(pn)) {%>
					<a href="/board/notice?pn=<%=i %>&filter=<%=filter%>"> <%=i%> </a>
				<%} else  {%>
					<%=i %>
				<%} %>
				<%} %>
				<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
					<a href="/board/notice?pn=<%=pagenation.getEndPageNumber() + 1 %>&filter=<%=filter%>"> >> </a>
				<%} %>	
			<%}else if(filter.equals("2")||filter.equals("")){ %>
				<%if((adminVo!=null)||!(memberVo.getId().equals(""))){ %>
				<button onclick="boardAdd()">글작성</button>
				<%}%>
				<button onclick="location.href='/board/'">뒤로가기</button>
				<br>
				<%if (pagenation.getStartPageNumber() != 1) {%>
				<a href="/board/oneAndOneQuestion?pn=<%=pagenation.getStartPageNumber() - 1 %>&filter=<%=filter%>"> << </a>
				<% } %>
			
				<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
				<%if (i != Integer.parseInt(pn)) {%>
					<a href="/board/oneAndOneQuestion?pn=<%=i %>&filter=<%=filter%>"> <%=i%> </a>
				<%} else  {%>
					<%=i %>
				<%} %>
				<%} %>
				<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
					<a href="/board/oneAndOneQuestion?pn=<%=pagenation.getEndPageNumber() + 1 %>&filter=<%=filter%>"> >> </a>
				<%} %>
				
			<%}else if(filter.equals("3")){ %>
				<%if((adminVo!=null)||!(memberVo.getId().equals(""))){ %>
				<button onclick="boardAdd()">글작성</button>
				<%}%>
				<button onclick="location.href='/board/'">뒤로가기</button>
				<br>
				<%if (pagenation.getStartPageNumber() != 1) {%>
				<a href="/board/productRelated?pn=<%=pagenation.getStartPageNumber() - 1 %>&filter=<%=filter%>"> << </a>
				<% } %>
			
				<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
				<%if (i != Integer.parseInt(pn)) {%>
					<a href="/board/productRelated?pn=<%=i %>&filter=<%=filter%>"> <%=i%> </a>
				<%} else  {%>
					<%=i %>
				<%} %>
				<%} %>
				<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
					<a href="/board/productRelated?pn=<%=pagenation.getEndPageNumber() + 1 %>&filter=<%=filter%>"> >> </a>
				<%} %>
				
			<%}else if(filter.equals("4")){ %>
				<%if((adminVo!=null)||!(memberVo.getId().equals(""))){ %>
				<button onclick="boardAdd()">글작성</button>
				<%}%>
				<button onclick="location.href='/board/'">뒤로가기</button>
				<br>
				<%if (pagenation.getStartPageNumber() != 1) {%>
				<a href="/board/myQuestion?pn=<%=pagenation.getStartPageNumber() - 1 %>&filter=<%=filter%>"> << </a>
				<% } %>
			
				<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
				<%if (i != Integer.parseInt(pn)) {%>
					<a href="/board/myQuestion?pn=<%=i %>&filter=<%=filter%>"> <%=i%> </a>
				<%} else  {%>
					<%=i %>
				<%} %>
				<%} %>
				<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
					<a href="/board/myQuestion?pn=<%=pagenation.getEndPageNumber() + 1 %>&filter=<%=filter%>"> >> </a>
				<%} %>
			<%}%>
		</div>
	</div>
</body>
</html>