<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String searchId = (String) request.getAttribute("searchId");
    	String numPageCount = (String) request.getAttribute("pageCount");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function cancle() {
		location.href = "/";
	}
	
	function search() {
		var $searchName = $('#searchName');
		var $searchEmail = $('#searchEmail');
		var $searchTel = $('#searchTel');

		if ($searchName.val() == null) {
			alert("이름은 반드시 적어야 합니다.");
			$searchName.focus();
			return false;
		}

// 		if ($searchEmail.val() == null && $searchTel.val() == null) {
// 			alert("이메일 과 전화번호중 하나는 입력을 하여야 합니다.");
// 			return;
// 		} else if ($searchEmail.val() != null && $searchTel.val() != null) {
// 			alert("이메일 과 전화번호중 하나만 입력을 하여야 합니다.");
// 			return;
// 		}
		
		$('#searchForm').submit();
	}

	function login() {
		$('#shIdForm').submit();
	}

	function cancle() {
		location.href = "/";
	}

	function searchPwd() {
		location.href="/member/searchPwd.do";
	}
</script>
</head>
<body>
	<h1>아이디 찾기</h1>
	<%if (numPageCount == null) { %>
		<form action="/member/searchIdProcAction" method="post" id="searchForm" />
			<input type="text" id="searchName" name="searchName" placeholder="이름" /><br>
			<input type="email" id="searchEmail" name="searchEmail" placeholder="이메일" /><br>
			<input type="tel" id="searchTel" name="searchTel" placeholder="전화번호" /><br>
		</form>
		<button type="button" onclick="search()">찾기</button>
		<button type="button" onclick="cancle()">취소</button>
	<%} else { %>
		<form action="/member/loginAction" method="post" id="shIdForm">
			<%if (searchId == null || searchId.equals("")) { %>
				아이디가 존재하지 않습니다.
			<%} else { %>
				찾으시는 아이디는<br>
				<input type="text" id="searchId" name="searchId" value="<%=searchId %>" readonly="readonly" />
				<br>입니다.<br>
			<%} %>
		</form>
		<button type="button" onclick="searchPwd()">비밀번호 찾기</button>
		<button type="button" onclick="login()">로그인</button>
		<button type="button" onclick="cancle()">취소</button>
	<%} %>
</body>
</html>