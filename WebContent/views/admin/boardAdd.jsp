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
<link rel="stylesheet" href="/views/css/board/boardAdd.css" type="text/css">


<script type="text/javascript">
	function join() {

		var $board_number = $('#board_number');
		var $title = $('#title');

		if($board_number.val()=="0"){
			alert('게시판 목차를 선택하세요.');
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
		location.href = "/admin/boardList";
	}
</script>

</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="boardAdd">
		<div class="add">
			<section>
				<form action="/admin/boardAddProc" method="post" id="editorForm">
					<table>
						<tr>
							<td class="td1">게시판목차</td>
							<td class="td2">
								<select class="selectbox" id="board_number" name="board_number">
									<option value="0" selected></option>
									<%if(adminVo!=null){%>
									<option value="1">공지사항</option>
									<%}%>
									<option value="2">1:1문의</option>
									<option value="3">제품관련문의</option>
								</select>
							</td>
						</tr>
						<tr class="goods" hidden>
							<td class="td1">상품번호</td>
							<td class="td2">
								<input type="text" id="goods_info" name="goods_info" />
							</td>
						</tr>
						<tr>
							<td class="td1">제목</td>
							<td class="td2">
								<input type="text" id="title" name="title" />
							</td>
						</tr>
						<tr>
							<td class="td1">내용</td>
							<td class="td2">
								<div style="width: 1000px;">
									<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
								</div>
							</td>
						</tr>
					</table>
				</form>
			</section>
			<button class="add_button" onclick="join()">등록</button>
			<button class="add_button" onclick="cancle()">취소</button>
		</div>
	</div>
	<jsp:include page="/views/admin/adminNavigation.jsp" />

<script type="text/javascript">
	$('#board_number').change(function() {
		var state = $('#board_number option:selected').val();
		
		if (state == 3) {
			$('.goods').show();
		} else {
			$('.goods').hide();
		}
	});
</script>

</body>
</html>