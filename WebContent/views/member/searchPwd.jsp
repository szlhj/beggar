<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<link rel="stylesheet" href="/views/css/member/searchPwd.css" type="text/css">

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
	<jsp:include page="/views/navbar.jsp" />
	
	<div class="searchPwdForm">
		<div class="searchPwd">
			<h1>임시 비밀번호 발급</h1>
			<form action="/member/searchPwdProc" method="post" id="shPwdForm">
				<table>
					<tr>
						<td class="td1">아이디</td>
						<td class="td2"><input type="text" id="shId" name="shId" /><br></td>
					</tr>
					<tr>
						<td class="td1">이름</td>
						<td class="td2"><input type="text" id="shName" name="shName" /><br></td>
					</tr>
					<tr>
						<td class="td1">이메일</td>
						<td class="td2"><input type="text" id="shEmail" name="shEmail" /><br></td>
					</tr>
					<tr>
						<td class="td1">전화번호</td>
						<td class="td2"><input type="text" id="shPhone" name="shPhone" /><br></td>
					</tr>
				</table>
			</form>
			<button class="searchPwd_button" type="button" onclick="temporary()">비밀번호 변경</button>
			<button class="searchPwd_button" type="button" onclick="cancle()">취소</button>
		</div>
	</div>
</body>
</html>