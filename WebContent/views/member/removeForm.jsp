<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>

<script type="text/javascript" src="./views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function leave() {
		if (confirm("정말 탈퇴 하시겠습니꺄?")) {
			$('#mrform').submit();
		} else {
			location.href="/";
		}
	}
</script>

</head>
<body>
	<div>
		<form action="/removeAction.do" method="post" id="mrform">
			<input type="password" id="pwd" name="pwd" >
		</form>
	</div>
	<div>
		<button type="button" onclick="leave()">탈퇴</button>
	</div>
</body>
</html>