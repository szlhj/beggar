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
	String filter = (String)request.getAttribute("filter");
	
	String yesOrNo = (String)request.getAttribute("yesOrNo");
	
	if(yesOrNo==null){
		yesOrNo="false";
	}
	
	boardVo.setMber_id(mber_id);
	
	if(adminVo != null){
		memberVo = new MemberVo();
		memberVo.setId("관리자");
	}
	
	if(memberVo == null && adminVo == null){
		memberVo = new MemberVo();
		memberVo.setId("비회원");
	}
	
	
	if(boardVo.getMber_id()==null||boardVo.getMber_id().equals("")){
		boardVo.setMber_id("관리자");
	}
	
	String pn = (String) request.getAttribute("pn");
	
	int board_number = boardVo.getBoard_number();
	
	String content = Parser.chgToHtml(boardVo.getContent());
	
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
<link rel="stylesheet" href="/views/css/board/boardDetail.css" type="text/css">

<script type="text/javascript">
	var content = '<%=content%>';
</script>

<script type="text/javascript">
	function modify() {
		location.href = "/board/modifyBoardView?pn="+<%=pn %>+"&board_sq="+<%=boardVo.getBoard_sq() %>;
	}
	
	function del(isDel) {
		location.href = "/board/delBoard?isDel=" + isDel + "&pn=" + <%=pn%> + "&sq=" + <%=boardVo.getBoard_sq()%>;
	}
</script>
</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<jsp:include page="/views/board/boardNavigation.jsp"></jsp:include>
	
	<div class="boardDetail">
		<div class="detail">
			<section>
				<%String buttonhref=""; %>
				<%if(yesOrNo.equals("true")){%>
				<%	buttonhref = "/board/myQuestion"; %>
				<%}else{%>
				<%  if(boardVo.getBoard_number()==1){%>
				<%	  buttonhref = "/board/notice";	%>
				<%  }else if(boardVo.getBoard_number()==2){%>
				<%	  buttonhref = "/board/oneAndOneQuestion";%>
				<%  }else if(boardVo.getBoard_number()==3){%>
				<%	  buttonhref = "/board/productRelated";%>
				<%  }%>
				<%} %>
				<table class="boardDetail_table">
					<tr>
						<th class="td1">게시판번호</th>
						<td class="td2"><input type="text" id="board_sq" name="board_sq" value="<%=boardVo.getBoard_sq() %>" readonly="readonly" /></td>
					</tr>
					<tr>
						<th class="td1">카테고리</th>
						<td class="td2"><input type="text" id="board_number" name="board_number" value="<%=Parser.Board_numberName(boardVo.getBoard_number()) %>" readonly="readonly"/></td>
					</tr>
					<tr>
						<th class="td1">상품번호</th>
						<td class="td2"><input type="text" id="goods_info" name="goods_info" value="<%=boardVo.getGoods_info() %>" readonly="readonly"/></td>
					</tr>
					<tr>
						<th class="td1">제목</th>
						<td class="td2"><input type="text" id="title" name="title" value="<%=boardVo.getTitle() %>" readonly="readonly"/></td>
					</tr>
					<tr>
						<th class="td1">내용</th>
						<td class="td2"><textarea readonly="readonly"><%=content %></textarea></td>
					</tr>
					<%if (!boardVo.getComment().equals("")){ %>
						<tr>
							<th class="td1">답변</th>
							<td class="td2"><input type="text" id="comment" name="comment" value="<%=boardVo.getComment() %>" readonly="readonly"/></td>
						</tr>
					<%} %>
					
				</table>
				<%if (memberVo!=null&&boardVo.getMber_id().equals(memberVo.getId())) {%>
						<button onclick="modify()">수정하기</button>
					<%if (boardVo.isDel_fl() == true) { %>
						<button onclick="del(false)">취소하기</button>
					<%} else { %>
						<button onclick="del(true)">삭제하기</button>
					<%} %>
				<%} %>
				<button onclick= "location.href = '<%=buttonhref%>?pn=<%=pn%>&filter=<%=filter%>'">뒤로가기 </button>
			</section>
		</div>
	</div>
</body>
</html>