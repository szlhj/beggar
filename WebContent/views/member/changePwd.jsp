<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String randomPwd = (String) request.getAttribute("randomPwd");
    	String mber_sq = (String) request.getAttribute("mber_sq");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<link rel="stylesheet" href="/views/css/member/changePwd.css" type="text/css">

<script type="text/javascript">
	function cancle() {
		location.href = "/";
	}

	function pwdModify() {
		var $extantPwd = $('#extantPwd');
		var $newPwd = $('#newPwd');
		var $newPwdc = $('#newPwdc');

		if (!$extantPwd.val()) {
			alert("현재 비밀번호를 입력하세요.");
			$extantPwd.focus();
			return;
		}
		if (!$newPwd.val()) {
			alert("새로운 비밀번호를 입력하세요.");
			$newPwd.focus();
			return;
		}
		if (!$newPwdc.val()) {
			alert("새로운 비밀번호 확인에 입력하세요.");
			$newPwdc.focus();
			return;
		}
		if ($newPwd.val() != $newPwd.val()) {
			alert("새로운 비밀번호가 서로 다릅니다.");
			return;
		}

		$('#chPwdForm').submit();
	}
</script>

</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	
	<div class="changePwdForm">
		<div class="changePwd">
			<h3>비밀번호 변경</h3>
			<form action="/member/changePwdProc" method="post" id="chPwdForm">						
				<input type="hidden" id="mber_sq" name="mber_sq" value="<%=mber_sq %>" />
				<input type="hidden" id="extantPwd" name="extantPwd" value="<%=randomPwd %>" />
				<table>
					<tr>
						<td class="td1">새로운 비밀번호</td>
						<td class="td2"><input type="password" id="newPwd" name="newPwd" />
						</td>
					</tr>
					<tr>
						<td class="td1">새로운 비밀번호 확인</td>
						<td class="td2"><input type="password" id="newPwdc" name="newPwdc" /></td>
					</tr>
				</table>
			</form>
			<button class="changePwd_button" type="button" onclick="pwdModify()">변경</button>
			<button class="changePwd_button" type="button" onclick="cancle()">취소</button>
		</div>
	</div>
</body>
</html>