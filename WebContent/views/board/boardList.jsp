<%@page import="shop.beggar.common.Parser"%>
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
		String firstTime = (String)request.getAttribute("firstTime");
		String lastTime = (String)request.getAttribute("lastTime");
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
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<jsp:include page="/views/board/boardNavigation.jsp"></jsp:include>
	
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
			<button class="boardList_button" onclick="searchArticle()">검색</button>
			<br>
			<%if(adminVo != null) {%>
				<button class="boardList_button" onclick="location.href='/admin/'">관리자 홈으로</button>
			<%} %>
			<button class="boardList_button" onclick="location.href='/'">홈으로</button>
		
			<table class="boardList">
				<tr>
					<th class="thCol" style="width: 100px;">구분</th>
					<th class="thCol" style="width: 80px;">글번호</th>
					<th class="thCol" style="width: 350px;">제목</th>
					<th class="thCol" style="width: 80px;">작성자</th>
					<th class="thCol" style="width: 100px;">생성일자</th>
					<th class="thCol" style="width: 80px;">조회수</th>
					<%if((filter.equals("2"))||(filter.equals("3"))){ %>
					<th class="thCol" style="width: 80px;">답변상황</th>
					<%} %>
				</tr>
				<%for (int i = 0; i < list.size(); i++) { %>
					<tr onclick="location.href='/board/detailBoard?board_sq=<%=list.get(i).getBoard_sq() %>&pn=<%=pn %>&mber_id=<%=list.get(i).getMber_id()%>&yesOrNo=<%=yesOrNo%>&filter=<%=filter%>'">
						
						<td class="tdCol"><%=Parser.Board_numberName(list.get(i).getBoard_number()) %></td>
						<td class="tdCol"><%=list.get(i).getBoard_sq() %></td>
						<td class="tdCol"><%=list.get(i).getTitle() %></td>
						<td class="tdCol">
							<%=Parser.Board_Member_Admin(list.get(i).getBoard_number(), list.get(i).getMber_id()) %>
						</td>
						<td class="tdCol"><%=list.get(i).getDttm() %></td>
						<td class="tdCol"><%=list.get(i).getCount() %></td>
						<%if((filter.equals("2"))||(filter.equals("3"))){ %>
							<td class="tdCol"><%=Parser.Board_Comment(list.get(i).getComment())%></td>
						<%} %>
					</tr>
				<%} %>
			</table>
			<div class="pagination board">
				<%if(filter.equals("1")){ %>
					<div>
						<button class="boardList_button_pagination" onclick="location.href='/board/'">뒤로가기</button>
						<br><br>
					</div>
					<div>
						<%if (pagenation.getStartPageNumber() != 1) {%>
							<a class="page_prev" href="/board/notice?pn=<%=pagenation.getStartPageNumber() - 1 %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> << </a>
						<% } %>
					
						<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
							<%if (i != Integer.parseInt(pn)) {%>
								<a class="page_num" href="/board/notice?pn=<%=i %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> <%=i%> </a>
							<%} else  {%>
								<p class="page_frt"><%=i %></p>
							<%} %>
						<%} %>
						
						<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
							<a class="page_next" href="/board/notice?pn=<%=pagenation.getEndPageNumber() + 1 %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> >> </a>
						<%} %>
					</div>
				<%} else if(filter.equals("2")||filter.equals("")){ %>
					<%if((adminVo!=null)||!(memberVo.getId().equals("비회원"))){ %>
						<button class="boardList_button_pagination" onclick="boardAdd()">글작성</button>
					<%}%>
						<button class="boardList_button_pagination" onclick="location.href='/board/'">뒤로가기</button>
						<br>
					<%if (pagenation.getStartPageNumber() != 1) {%>
						<a class="page_prev" href="/board/oneAndOneQuestion?pn=<%=pagenation.getStartPageNumber() - 1 %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> << </a>
					<% } %>
				
					<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
						<%if (i != Integer.parseInt(pn)) {%>
							<a class="page_num" href="/board/oneAndOneQuestion?pn=<%=i %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> <%=i%> </a>
						<%} else  {%>
							<p class="page_frt"><%=i %></p>
						<%} %>
					<%} %>
					
					<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
						<a class="page_next" href="/board/oneAndOneQuestion?pn=<%=pagenation.getEndPageNumber() + 1 %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> >> </a>
					<%} %>
					
				<%}else if(filter.equals("3")){ %>
					<%if((adminVo!=null)||!(memberVo.getId().equals("비회원"))){ %>
						<button class="boardList_button_pagination" onclick="boardAdd()">글작성</button>
					<%}%>
						<button class="boardList_button_pagination" onclick="location.href='/board/'">뒤로가기</button>
						<br>
					<%if (pagenation.getStartPageNumber() != 1) {%>
						<a class="page_prev" href="/board/productRelated?pn=<%=pagenation.getStartPageNumber() - 1 %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> << </a>
					<% } %>
				
					<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
						<%if (i != Integer.parseInt(pn)) {%>
							<a class="page_num" href="/board/productRelated?pn=<%=i %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> <%=i%> </a>
						<%} else  {%>
							<p class="page_frt"><%=i %></p>
						<%} %>
					<%} %>
					
					<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
						<a class="page_next" href="/board/productRelated?pn=<%=pagenation.getEndPageNumber() + 1 %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> >> </a>
					<%} %>
					
				<%}else if(filter.equals("4")){ %>
					<%if((adminVo!=null)||!(memberVo.getId().equals("비회원"))){ %>
						<button class="boardList_button_pagination" onclick="boardAdd()">글작성</button>
					<%}%>
						<button class="boardList_button_pagination" onclick="location.href='/board/'">뒤로가기</button>
						<br>
					<%if (pagenation.getStartPageNumber() != 1) {%>
						<a class="page_prev" href="/board/myQuestion?pn=<%=pagenation.getStartPageNumber() - 1 %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> << </a>
					<% } %>
				
					<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
						<%if (i != Integer.parseInt(pn)) {%>
							<a class="page_num" href="/board/myQuestion?pn=<%=i %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> <%=i%> </a>
						<%} else  {%>
							<p class="page_frt"><%=i %></p>
						<%} %>
					<%} %>
					
					<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
						<a class="page_next" href="/board/myQuestion?pn=<%=pagenation.getEndPageNumber() + 1 %>&filter=<%=filter%>&firstTime=<%=firstTime%>&lastTime=<%=lastTime%>"> >> </a>
					<%} %>
				<%}%>
			</div>
		</div>
	</div>
</body>
</html>