<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	AdminVo vo = (AdminVo) request.getAttribute("vo");
    
    	String adminId = "";
    	String adminName = "";
    	String adminEmail = "";
    	String adminPhone = "";
    	String adminMemo="";
    	
    	if (vo != null) {
    		adminId = vo.getAdminId();
    		adminName = vo.getAdminName();
    		adminEmail = vo.getAdminEmail();
    		adminPhone = vo.getAdminPhone();
    		adminMemo = vo.getAdminMemo();
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 회원 수정</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/admin/adminModify.css" type="text/css">

<script type="text/javascript">
	function adminModify() {
		var $adminId = $('#adminId');
		var $adminPwd = $('#adminPwd');
		var $adminPwdc = $('#adminPwdc');
		var $adminName = $('#adminName');
		var $adminEmail = $('#adminEmail');
		var $adminPhone = $('#adminPhone');
		var $adminMemo = $('#adminMemo');
		
		
		if (!$adminId.val()){
			alert("아이디를 입력하세요.");
			$adminId.focus;
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
		
		if (!$adminPwd.val()){
			alert("비밀번호를 입력하세요.");
			$adminPwd.focus;
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
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="adminModify">
		<div class="modify">
			<form action="/admin/modifyAdminProc" method="post" id="marform">
				<input type="text" id="adminId" name="adminId" placeholder="아이디" value="<%=adminId %>" readonly="readonly" /><br>
				<input type="password" id="adminPwd" name="adminPwd" placeholder="비밀번호" /><br>
				<input type="password" id="adminPwdc" name="adminPwdc" placeholder="비밀번호 확인" /><br>
				<input type="text" id="adminName" name="adminName" placeholder="이름" value="<%=adminName %>" readonly="readonly" /><br>
				<input type="email" id="adminEmail" name="adminEmail" placeholder="이메일" value="<%=adminEmail %>" /><br>
				<input type="tel" id="adminPhone" name="adminPhone" placeholder="전화번호"  value="<%=adminPhone %>"/><br>
		<!-- 		<input type="checkbox" id="adminSupper" name="adminSupper" /><label for="adminSupper">슈퍼관리자</label><br> -->
				<textarea rows="10" cols="30" id="adminMemo" name="adminMemo" placeholder="메모 입력"><%=adminMemo %></textarea><br>
			</form>
			<button class="modify_button" onclick="adminModify()">수정</button>
			<button class="modify_button" onclick="location.href='/admin'">취소</button>
		</div>
	</div>
	<jsp:include page="/views/admin/adminNavigation.jsp" />
</body>
</html>