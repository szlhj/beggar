<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	MemberVo vo = (MemberVo) request.getAttribute("shVo");
    	String shId = null;
    	
    	if (vo == null || vo.getId() == null) {
    		shId = "";
    	} else {
    		shId = vo.getId();
    	}
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<link rel="stylesheet" href="/views/css/member/login.css" type="text/css">

<script type="text/javascript">
	function validateCheck() {
		var $id = $('#id');
		var $pwd = $('#pwd');

		if (!$id.val()) {
			alert("아이디 입력");
			$id.focus;
			return false;
		}
		if (!$pwd.val()) {
			alert("비밀번호 입력");
			$pwd.focus;
			return false;
		}
	}

	function cancle() {
		location.href="/";
	}

	function searchId() {
		location.href="/member/searchId";
	}

	function searchPwd() {
		location.href="/member/searchPwd";
	}
</script>
</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	
	<section>
		<div class="loginForm">
			<div class="login">
				<form action="/member/loginProcAction" method="post" onsubmit="return validateCheck()">
					<table>
						<tr>
							<td colspan="2" style="text-align: center;" class="header"><h1>LOGIN</h1></td>
						</tr>
						<tr>
							<td>아이디</td>
							<td><input type="text" id="id" name="id" placeholder="아이디" value="<%=shId %>" /></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" id="pwd" name="pwd" placeholder="비밀번호" /></td>
						</tr>
					</table>&nbsp;&nbsp;&nbsp;
					<button class="login_button" type="submit">로그인</button>
					<button class="login_button" type="button" onclick="cancle()">취소</button>
				</form>
					&nbsp;
					<button class="login_search_button" type="button" onclick="searchId()">아이디 찾기</button>
					<button class="login_search_button" type="button" onclick="searchPwd()">비밀번호 찾기</button>
			</div>
		</div>
	</section>
</body>
</html>