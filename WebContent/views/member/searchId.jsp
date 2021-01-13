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

<link rel="stylesheet" href="/views/css/member/searchId.css" type="text/css">

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
		
		$('#searchForm').submit();
	}

	function login() {
		$('#shIdForm').submit();
	}

	function cancle() {
		location.href = "/";
	}

	function searchPwd() {
		location.href="/member/searchPwd";
	}

	function register() {
		location.href="/member/registerAction";
	}
</script>
</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	
	<div class="searchIdForm">
		<div class="searchId">
			<h1>아이디 찾기</h1>
			<%if (numPageCount == null) { %>
				<form action="/member/searchIdProcAction" method="post" id="searchForm" >
					<table>
						<tr>
							<td class="td1">이름</td>
							<td class="td2"><input type="text" id="searchName" name="searchName" /><br></td>
						</tr>
						<tr>
							<td class="td1">이메일</td>
							<td class="td2"><input type="email" id="searchEmail" name="searchEmail" /><br></td>
						</tr>
						<tr>
							<td class="td1">전화번호</td>
							<td class="td2"><input type="tel" id="searchTel" name="searchTel" /><br></td>
						</tr>
					</table>
				</form>
				<button class="searchId_button" type="button" onclick="search()">찾기</button>
				<button class="searchId_button" type="button" onclick="cancle()">취소</button>
			<%} else { %>
				<form action="/member/loginAction" method="post" id="shIdForm">
					<table>
						<tr>
							<%if (searchId == null || searchId.equals("")) { %>
								<td class="td2">
									아이디가 없습니다.
								</td>
							<%} else { %>
								<td class="td2">
									<input type="text" id="searchId" name="searchId" value="<%=searchId %>" readonly="readonly" />
								</td>
							<%} %>
						</tr>
					</table>
				</form>
				<%if (searchId == null || searchId.equals("")) { %>
					<button class="searchId_button" type="button" onclick="register()">회원가입</button>
				<%} else { %>
					<button class="searchId_button" type="button" onclick="searchPwd()">비밀번호 찾기</button>
					<button class="searchId_button" type="button" onclick="login()">로그인</button>
				<%}%>
				<button class="searchId_button" type="button" onclick="cancle()">취소</button>
			<%} %>
		</div>
	</div>
</body>
</html>