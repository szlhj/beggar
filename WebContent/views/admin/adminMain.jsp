<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	AdminVo vo = (AdminVo) session.getAttribute("adminVo");
    	int adminSupperCount = (Integer) session.getAttribute("adminSupperCount");
    	
    	int admin_sq = 0;
    	
    	if (vo != null) {
    		admin_sq = 1;
    	}
    	
    	boolean admin_supper = (vo == null) ? false : vo.isAdmin_supper();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function adminLogin(){
		var $adminId = $('#adminId');
		var $adminPwd = $('#adminPwd');
		
		if (!$adminId.val()){
			alert("아이디를 입력하세요.");
			$adminId.focus;
			return;
		}
		if (!$adminPwd.val()){
			alert("비밀번호를 입력하세요.");
			$adminPwd.focus;
			return;
		}
		
		$('#loginform').submit();
	}
	
	function supperAdminRegister() {
		var $adminId = $('#adminId');
		var $adminPwd = $('#adminPwd');
		var $adminPwdc = $('#adminPwdc');
		var $adminName = $('#adminName');
		var $adminEmail = $('#adminEmail');
		var $adminPhone = $('#adminPhone');
		var $adminMemo = $('#adminMemo');
		
		if (!$adminId.val()){
			alert("아이디를 입력하세요.");
			$admin.focus;
			return;
		}
		if (!$adminPwd.val()){
			alert("비밀번호를 입력하세요.");
			$adminPwd.focus;
			return;
		}
		if (!$adminName.val()){
			alert("이름을 입력하세요.");
			$adminName.focus;
			return;
		}
		if (!$adminEmail.val()){
			alert("이메일을 입력하세요.");
			$adminEmail.focus;
			return;
		}
		if (!$adminPhone.val()){
			alert("전화번호를 입력하세요.");
			$adminPhone.focus;
			return;
		}
		
		if ($adminPwd.val() != $adminPwdc.val()){
			alert("비밀번호가 동일하지 않습니다.");
			return;
		}
		
		$('#marform').submit();
	}
</script>

</head>
<body>
	<%if (admin_sq == 0) {%>
		<%if (adminSupperCount == 1) { %>
			<form action="/admin/LoginAdminProc" method="post" id="loginform" >
				<input type="text" id="adminId" name="adminId" /><br>
				<input type="password" id="adminPwd" name="adminPwd" /><br>
				<button onclick="adminLogin()">로그인</button>
				<button onclick="/admin">취소</button>
			</form>
		<%} else { %>
			<form action="/admin/registerAdminProc" method="post" id="marform">
				<input type="text" id="adminId" name="adminId" placeholder="아이디" /><br>
				<input type="password" id="adminPwd" name="adminPwd" placeholder="비밀번호" /><br>
				<input type="password" id="adminPwdc" name="adminPwdc" placeholder="비밀번호 확인" /><br>
				<input type="text" id="adminName" name="adminName" placeholder="이름" /><br>
				<input type="email" id="adminEmail" name="adminEmail" placeholder="이메일" /><br>
				<input type="tel" id="adminPhone" name="adminPhone" placeholder="전화번호"/><br>
		 		<input type="checkbox" id="adminSupper" name="adminSupper" checked="checked" /><label for="adminSupper">슈퍼관리자</label><br>
				<textarea rows="10" cols="30" id="adminMemo" name="adminMemo" placeholder="메모 입력"></textarea><br>
			</form>
			<button onclick="supperAdminRegister()">슈퍼 관리자 저장</button>
			<button onclick="location.href='/admin'">취소</button>
		<%} %>
	<%} else { %>
		<%if (admin_supper == true) { %>
			<a href="/admin/registerAdmin">관리자등록</a>
		<%} %>
		<a href="/admin/modifyAdmin">관리자수정</a>
		<a href="/admin/memberManagement">회원관리</a>
		<a href="/admin/itemList">상품목록</a>
		<a href="/admin/itemAdd">상품등록</a>
		<a href="/admin/logoutAdmin">관리자 로그아웃</a>
		<a href="/admin/fileUpAndDown">파일 up/down</a>
		<a href="/admin/boardList">게시판리스트</a>
		<a href="/admin/boardAdd">게시판작성</a>
		<button onclick="/admin/">취소</button>
	<%} %>
</body>
</html>