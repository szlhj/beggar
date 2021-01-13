<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	AdminVo vo = (AdminVo) request.getAttribute("adminVo");
    	
    	String del_fl = null;
    	String supper = null;
    	
    	if (vo.isAdmin_del_fl() == true){
    		del_fl = "삭제";
    	} else {
    		del_fl = "사용중";
    	}
    	if (vo.isAdmin_supper() == true){
    		supper = "슈퍼관리자";
    	} else {
    		supper = "관리자";
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 상세보기</title>

<link rel="stylesheet" href="/views/css/admin/adminDetail.css" type="text/css">

<script type="text/javascript">
	function modify() {
		location.href="/admin/modifyAdmin?admin_sq=" + <%=vo.getAdmin_sq() %>;
	}
	function cancle() {
		location.href="/admin";
	}
</script>

</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	
	<div class="adminDetail">
		<div class="detail">
			<table>
				<tr>
					<td>관리자 일련번호</td>
					<td><%=vo.getAdmin_sq() %></td>
				</tr>
				<tr>
					<td>관리자 아이디</td>
					<td><%=vo.getAdminId() %></td>
				</tr>
				<tr>
					<td>관리자 이름</td>
					<td><%=vo.getAdminName() %></td>
				</tr>
				<tr>
					<td>관리자 이메일</td>
					<td><%=vo.getAdminEmail() %></td>
				</tr>
				<tr>
					<td>관리자 전화번호</td>
					<td><%=vo.getAdminPhone() %></td>
				</tr>
				<tr>
					<td>관리자 삭제 여부</td>
					<td><%=del_fl %></td>
				</tr>
				<tr>
					<td>슈퍼관리자 여부</td>
					<td><%=supper %></td>
				</tr>
				<tr>
					<td>관리자 메모</td>
					<td><%=vo.getAdminMemo() %></td>
				</tr>
				<tr>
					<td>관리자 생성일자</td>
					<td><%=vo.getDttm() %></td>
				</tr>
			</table>
			
			<button class="detail_button" onclick="modify()">수정</button>
			<button class="detail_button" onclick="cancle()">취소</button>
		</div>
	</div>
	
	<jsp:include page="/views/admin/adminNavigation.jsp" />
</body>
</html>