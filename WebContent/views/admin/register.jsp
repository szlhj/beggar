<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 회원 가입</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function adminJoin() {
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
	<form action="/admin/registerAdminProc" method="post" id="marform">
		<input type="text" id="adminId" name="adminId" placeholder="아이디" /><br>
		<input type="password" id="adminPwd" name="adminPwd" placeholder="비밀번호" /><br>
		<input type="password" id="adminPwdc" name="adminPwdc" placeholder="비밀번호 확인" /><br>
		<input type="text" id="adminName" name="adminName" placeholder="이름" /><br>
		<input type="email" id="adminEmail" name="adminEmail" placeholder="이메일" /><br>
		<input type="tel" id="adminPhone" name="adminPhone" placeholder="전화번호" /><br>
<!-- 		<input type="checkbox" id="adminSupper" name="adminSupper" /><label for="adminSupper">슈퍼관리자</label><br> -->
		<textarea rows="10" cols="30" id="adminMemo" name="adminMemo" placeholder="메모 입력"></textarea><br>
	</form>
	<button onclick="adminJoin()">가입</button>
	<button onclick="location.href='/admin'">취소</button>
</body>
</html>