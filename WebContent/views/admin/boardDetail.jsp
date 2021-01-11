<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@page import="shop.beggar.beggar.vo.BoardVo"%>
<%@page import="shop.beggar.common.Parser"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVo boardVo = (BoardVo) request.getAttribute("boardVo");
	AdminVo adminVo = (AdminVo) request.getAttribute("adminVo");
	MemberVo memberVo = (MemberVo) request.getAttribute("vo");
	String mber_id = request.getParameter("mber_id");
	boardVo.setMber_id(mber_id);
	String filter = (String)request.getAttribute("filter");
	
	if(memberVo == null && adminVo == null){
		memberVo = new MemberVo();
		memberVo.setId("");
	}
	
	if(adminVo != null){
		memberVo = new MemberVo();
		memberVo.setId("관리자");
	}
	
	if(boardVo.getMber_id()==null||boardVo.getMber_id().equals("")){
		boardVo.setMber_id("관리자");
	}
	
	String pn = (String) request.getAttribute("pn");
	
	int board_number = boardVo.getBoard_number();
	
	String content = Parser.chgToHtml(boardVo.getContent());
	
	if(filter == null||filter.equals("")){
		filter = "0";
	}
	if(boardVo.getComment()==null||boardVo.getComment().equals("")){
		boardVo.setComment("");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보드 디테일</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/boardDetail.css" type="text/css">

<script type="text/javascript">
	var content = '<%=content%>';
</script>

<script type="text/javascript">
	function modify() {
		location.href = "/admin/boardModifyView?pn="+<%=pn %>+"&board_sq="+<%=boardVo.getBoard_sq() %>;
	}
	function cancle() {
		location.href = "/admin/boardList?pn=" + <%=pn%>+"&filter="+<%=filter %>;
	}
	function del(isDel) {
		location.href = "/admin/boardDel?isDel=" + isDel + "&pn=" + <%=pn%> + "&sq=" + <%=boardVo.getBoard_sq()%>;
	}
	function answer(){
		location.href = "/admin/boardAnswer?pn="+<%=pn %>+"&board_sq="+<%=boardVo.getBoard_sq() %>;
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="boardDetail">
		<div class="detail">
			<section>
				게시판번호<input type="text" id="board_sq" name="board_sq" value="<%=boardVo.getBoard_sq() %>" readonly="readonly" /><br>
				
				<%String Board_numberName ="";
			if(boardVo.getBoard_number()==1){ 
				Board_numberName = "공지사항";
			}else if(boardVo.getBoard_number()==2){ 
				Board_numberName = "1:1문의";
			}else if(boardVo.getBoard_number()==3){ 
				Board_numberName = "제품관련문의";
				} %>
				
				카테고리<input type="text" id="board_number" name="board_number" value="<%=Board_numberName %>" readonly="readonly"/><br>
				상품번호<input type="text" id="goods_info" name="goods_info" value="<%=boardVo.getGoods_info() %>" readonly="readonly"/><br>
				제목<input type="text" id="title" name="title" value="<%=boardVo.getTitle() %>" readonly="readonly"/><br>
				내용
				<div readonly="readonly">
				<%=content %>
				</div>
			<%if (!boardVo.getComment().equals("")){ %>
			답변<input type="text" id="comment" name="comment" value="<%=boardVo.getComment() %>" readonly="readonly"/><br>
			<%} %>
				<%if (memberVo!=null&&boardVo.getMber_id().equals(memberVo.getId())) {%>
				<button class="detail_button" onclick="modify()">수정하기</button>
				<%if (boardVo.isDel_fl() == true) { %>
					<button class="detail_button" onclick="del(false)">삭제취소</button>
				<%} else { %>
					<button class="detail_button" onclick="del(true)">삭제하기</button>
				<%} %>
			<%} %>
			<%if((adminVo!=null)&&(Board_numberName.equals("1:1문의")&&(boardVo.getComment().equals("")))) {%>
			<button onclick="answer()">답변하기</button>
			<%} %>
				<button class="detail_button" onclick="cancle()">취소</button>
			</section>
		</div>
	</div>
	<jsp:include page="/views/admin/adminNavigation.jsp" />
</body>
</html>