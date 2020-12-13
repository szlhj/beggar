<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function cancle() {
		location.href = "/";
	}

	function temporary() {
		var $shId = $('#shId')
		
		if (!$shId.val()) {
			alert("아이디를 입력하여야 합니다.");
			$shId.focus;
			return;
		}
		
		$('#shPwdForm').submit();
	}
</script>

</head>
<body>
	<form action="/member/searchPwdProc.do" method="post" id="shPwdForm">
		<input type="text" id="shId" name="shId" placeholder="아이디" /><br>
		<input type="text" id="shName" name="shName" placeholder="이름" /><br>
		<input type="text" id="shEmail" name="shEmail" placeholder="이메일" /><br>
		<input type="text" id="shPhone" name="shPhone" placeholder="전화번호" /><br>
	</form>
	<button type="button" onclick="temporary()">임시비밀번호 발급</button>
	<button type="button" onclick="cancle()">취소</button>
</body>
</html>