<%@page import="shop.beggar.beggar.vo.BoardVo"%>
<%@page import="shop.beggar.common.Parser"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVo boardVo = (BoardVo) request.getAttribute("boardVo");
	String pn = (String) request.getAttribute("pn");

	String board_number = boardVo.getBoard_number();

	String content = Parser.chgToHtml(boardVo.getContent());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/boardDetail.css" type="text/css">

<script type="text/javascript">
	var content = '<%=content%>';
</script>

<script type="text/javascript">
	function modify() {

		var $board_number = $('#board_number');
		var $goods_info = $('#goods_info');
		var $title = $('#title');
		var $content = $('#content');

		if($board_number.val()=="0"){
			alert('게시판 목차를 선택하세요.');
			return;
			}
 		
 		if (!$goods_info.val()) {
 			alert('상품번호를 입력하세요.');
 			$goods_info.focus;
 			return;
 		}
 		if (!$title.val()) {
 			alert('제목을 입력하세요.');
 			$title.focus;
 			return;
 		}
		
		saveContent();
	}
	
	function cancle() {
		location.href = "/admin/boardList?pn=" + <%=pn%>;
	}
	function del(isDel) {
		location.href = "/admin/boardDel?isDel=" + isDel + "&pn=" + <%=pn%> + "&sq=" + <%=boardVo.getBoard_sq()%>;
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="boardDetail">
		<div class="detail">
			<section>
				<form action="/admin/boardModifyProc?pn=<%=pn %>" method="post" id="editorForm">
					게시판번호<input type="text" id="board_sq" name="board_sq" value="<%=boardVo.getBoard_sq() %>" readonly="readonly" /><br>
					카테고리
						<select id="board_number" name="board_number">
							<option value="0" <%="0".equals(board_number)?"selected":"" %>></option> 
							<option value="1"  <%="1".equals(board_number)?"selected":"" %>>공지사항</option>
							<option value="2" <%="2".equals(board_number)?"selected":"" %>>1:1문의</option>
							<option value="3" <%="3".equals(board_number)?"selected":"" %>>제품관련문의</option>
						</select>
						<br>
						상품번호<input type="text" id="goods_info" name="goods_info" value="<%=boardVo.getGoods_info() %>" /><br>
						제목<input type="text" id="title" name="title" value="<%=boardVo.getTitle() %>" /><br>
						내용
						<div style="width: 1000px;">
							<jsp:include page="/editor/editorSkinForModify.jsp" flush="false" />
						</div>
				</form>
				<br>
				<button class="detail_button" onclick="modify()">수정</button>
				<%if (boardVo.isDel_fl() == true) { %>
					<button class="detail_button" onclick="del(false)">취소하기</button>
				<%} else { %>
					<button class="detail_button" onclick="del(true)">삭제하기</button>
				<%} %>
				<button class="detail_button" onclick="cancle()">취소</button>
			</section>
		</div>
	</div>
	<jsp:include page="/views/admin/adminNavigation.jsp" />
</body>
</html>