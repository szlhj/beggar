<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ItemVo ivo = (ItemVo)request.getAttribute("ivo");
                 ivo.getStok();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 디테일 화면</title>
</head>
<body>
<table>
	<tr>
		<td><%=ivo.getFilepath() %></td>
		<td><%=ivo.getPreview() %></td>
		<td><%=ivo.getItem_name() %></td>
	</tr>
	<tr>
		<td><%=ivo.getCode() %></td>
		<td><%=ivo.getColor() %></td>
	</tr>
	<tr>
		<td><%=ivo.getItem_number() %></td>
		<td><%=ivo.getItem_rating() %></td>
	</tr>
	<tr>
		<td><%=ivo.getSize() %></td>
	</tr>
	<tr>
		<td><%=ivo.getPrice() %></td>
	</tr>
	<tr>
		<td><%=ivo.getPrice() - (ivo.getPrice() * ivo.getDiscount() /100)%></td>
	</tr>
	<%if(ivo.getStok() == 0 ) {%>
	<tr>
		<td>임시품절 상태입니다.</td>
	</tr>
	<%} else { %>
	<tr>
		<td onclick="location.href='/item/shopBasket'">장바구니</td>
		<td onclick="location.href='/item/purchase'">바로 구매</td>
	</tr>
	<%} %>
</table>
</body>
</html>