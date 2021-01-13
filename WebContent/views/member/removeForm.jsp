<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>

<script type="text/javascript" src="./views/js/jquery-3.5.1.js"></script>

<link rel="stylesheet" href="/views/css/member/removeForm.css" type="text/css">

<script type="text/javascript">
	function leave() {
		if (confirm("정말 탈퇴 하시겠습니꺄?")) {
			$('#mrform').submit();
		} else {
			location.href="history.back()";
		}
	}
	function cancle() {
		location.href="history.back()";
	}
</script>

</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	<jsp:include page="/views/member/myPageNavigation.jsp" />
	
	<div class="removeForm">
		<div class="remove">
			<form action="/removeAction" method="post" id="mrform">
				<table>
					<tr>
						<td class="td1" style="text-align: right;">비밀번호</td>
						<td class="td2"><input type="password" id="pwd" name="pwd"/></td>
					</tr>
				</table>
			</form>
		</div>
	<button class="remove_button" type="button" onclick="leave()">탈퇴</button>
	<button class="remove_button" type="button" onclick="cancle()">취소</button>
	</div>
</body>
</html>