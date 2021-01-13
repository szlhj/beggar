<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@page import="shop.beggar.beggar.vo.BoardVo"%>
<%@page import="shop.beggar.common.Pagenation"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
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
		String filter = request.getParameter("filter");
		
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
<title>게시판작성</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>


<script type="text/javascript">
	function join() {

		/* private int board_sq;// 게시판 넘버
		private int person_sq;// 사람 번호
		private int board_number;// 게시판 구분
		private int count; // 게시판 조회수
		private int del_fl; // 삭제 유무
		private String dttm; // 게시판 작성 시간
		private String goods_info; // 상품번호
		private String title; //제목
		private String content; // 내용 */
		var $board_number = $('#board_number');
		var $goods_info = $('#goods_info');
		var $title = $('#title');

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
		location.href = "/board/";
	}
</script>

</head>
<body>
	<div>
		<div>
			<section>
				<form action="/board/addBoardProc" method="post" id="editorForm">
					게시판목차
					<select id="board_number" name="board_number">
						<option value="0" selected></option>
						<%if(adminVo!=null){%>
						<option value="1">공지사항</option>
						<%}%>
						<option value="2">1:1문의</option>
						<option value="3">제품관련문의</option>
					</select>
					<br>
					상품번호<input type="text" id="goods_info" name="goods_info" /><br>
					제목<input type="text" id="title" name="title" /><br>
					내용<div>
						<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
					</div>

				</form>
				<button onclick="join()">등록</button>
				<button onclick="cancle()">취소</button>
			</section>
		</div>
	</div>
</body>
</html>