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
<title>관리자</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/admin/adminMain.css" type="text/css">

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

		var regExp = new RegExp("^[a-z0-9]{4,20}$","g");
		if (regExp.exec($adminId.val()) == null){
			alert("아이디 입력형식이 아닙니다.");
			$adminId.focus;
			$adminId.val('');
			return;
		}
		
		regExp = new RegExp("^[a-zA-Z0-9!@#]{4,20}$","g");
		if (regExp.exec($adminPwd.val()) == null){
			alert("비밀번호 입력형식이 아닙니다.");
			$adminPwd.focus;
			$adminPwd.val('');
			return;
		}
		// ^[가-힣]{2,20}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,20} : 한글2~20자 or 영문2~20자 공백 영문2~20자
		regExp = new RegExp("^[가-힣]{2,20}|[a-zA-Z]{2,20}\s[a-zA-Z]{2,20}$","g");
		if (regExp.exec($adminName.val()) == null){
			alert("이름 입력형식이 아닙니다.");
			$adminName.focus;
			$adminName.val('');
			return;
		}
		
		regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if (regExp.exec($adminEmail.val()) == null){
			alert("이메일 입력형식이 아닙니다.");
			$adminEmail.focus;
			$adminEmail.val('');
			return;
		}
		
		$('#marform').submit();
	}
</script>

</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="adminMain">
	<div class="main">
	<%if (admin_sq == 0) {%>
		<%if (adminSupperCount == 1) { %>
			<h1>관리자 LOG IN</h1>
			<form action="/admin/LoginAdminProc" method="post" id="loginform" >
				<input type="text" id="adminId" name="adminId" /><br>
				<input type="password" id="adminPwd" name="adminPwd" /><br><br><br>
				<button class="main_button" onclick="adminLogin()">로그인</button>
				<button class="main_button" onclick="/admin">취소</button>
				<button class="main_button" onclick="/admin/">비밀번호변경</button>
			</form>
		<%} else { %>
			<h1>슈퍼 관리자 등록</h1>
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
			<button class="main_button" onclick="supperAdminRegister()">슈퍼 관리자 저장</button>
			<button class="main_button" onclick="location.href='/admin'">취소</button>
		<%} %>
	<%} else { %>
		<jsp:include page="/views/admin/adminNavigation.jsp" />
	<%} %>
	</div>
	</div>
</body>
</html>