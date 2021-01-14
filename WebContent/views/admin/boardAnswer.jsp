<%@page import="shop.beggar.beggar.vo.BoardVo"%>
<%@page import="shop.beggar.common.Parser"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVo boardVo = (BoardVo) request.getAttribute("boardVo");
	String pn = (String) request.getAttribute("pn");

	int board_number = boardVo.getBoard_number();

	String content = Parser.chgToHtml(boardVo.getContent());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	var content = '<%=content%>';
</script>

<script type="text/javascript">
	function modify() {
		var $comment = $('#comment');

		if (!$comment.val()) {
 			alert('답변을 입력하세요.');
 			$comment.focus;
 			return;
 		}
		
		$('#answer').submit();
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
	<div>
		<section>
			<form action="/admin/boardAnswerProc?pn=<%=pn %>" method="post" id="answer">
			게시판번호<input type="text" id="board_sq" name="board_sq" value="<%=boardVo.getBoard_sq() %>" readonly="readonly" /><br>
				제목<input type="text" id="title" name="title" value="<%=boardVo.getTitle() %>" readonly="readonly" /><br>
				내용
				<div readonly="readonly">
					<%=content %>
				</div>
				<br>
				답변
				<textarea type="text" id ="comment" name="comment"> </textarea>
			</form>
			<button onclick="modify()">완료</button>
			<%if (boardVo.isDel_fl() == true) { %>
				<button onclick="del(false)">삭제취소</button>
			<%} else { %>
				<button onclick="del(true)">삭제하기</button>
			<%} %>
			<button onclick="cancle()">취소</button>
		</section>
	</div>
</body>
</html>