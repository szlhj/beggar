<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	    AdminVo vo = (AdminVo) session.getAttribute("adminVo");
		int adminSupperCount = (Integer) session.getAttribute("adminSupperCount");
		
		int admin_sq = 0;
		
		if (vo != null) {
			admin_sq = 1;
		}
		
		boolean admin_supper = (vo == null) ? false : vo.isAdmin_supper();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminNavigationBar</title>
<link rel="stylesheet" href="/views/css/admin/adminNavigation.css" type="text/css">
</head>
<body>
	<ul class="ct" id="navi">
        <li class="group">
            <div class="title">관리자 등록</div>
            <ul class="sub">
                <li><a href="/admin/registerAdmin">관리자등록</a></li>
                <li><a href="/admin/listAdmin">관리자 리스트</a></li>
            </ul>
        </li>
        <li class="group">
            <div class="title">관리</div>
            <ul class="sub">
                <li><a href="/admin/modifyAdmin">관리자수정</a></li>                
                <li><a href="/admin/logoutAdmin">관리자 로그아웃</a></li>
            </ul>
        </li>
        <li class="group">
            <div class="title">회원 관리</div>
            <ul class="sub">
                <li><a href="/admin/memberManagement">회원관리</a></li> 
            </ul>
        </li>        
        <li class="group">
            <div class="title">상품 관리</div>
            <ul class="sub">
                <li><a href="/admin/itemList">상품목록</a></li>
                <li><a href="/admin/itemAdd">상품등록</a></li>
            </ul>
        </li>       
        <li class="group">
            <div class="title">게시판 관리</div>
            <ul class="sub">
                <li><a href="/admin/boardList">게시판리스트</a></li>
                <li><a href="/admin/boardAdd">게시판작성</a></li>
            </ul>
        </li>       
        <li class="group">
            <div class="title">주문 관리</div>
            <ul class="sub">
                <li><a href="/admin/orderList">주문 리스트</a></li>
                <li><a href="/admin/orderListItemDelete">주문 삭제</a></li>
            </ul>
        </li>
    </ul>
</body>
</html>