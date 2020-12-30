<%@page import="shop.beggar.common.Parser"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ItemVo ivo = (ItemVo)request.getAttribute("ivo");
                
    String content = Parser.chgToHtml(ivo.getExplanation());
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 디테일 화면</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="/views/css/itemDetail.css" type="text/css">
<link rel="stylesheet" href="/views/css/style.css" type="text/css">
<link rel="stylesheet" href="/views/css/ress.css" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<script>
	var content = '<%=content%>';

</script>
</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	<jsp:include page="/views/item/navigation.jsp"></jsp:include>
	
<div class="items">
	<div class="item_left"><img src="<%=ivo.getFilepath() %>" width="500px" height="650px" alt="" /><%=ivo.getPreview() %></div>
	<div class="item_right">
		<table border="1" class="itemTable">
			<tr>
				<td colspan="2">
					<h5>Material</h5>
					<p>상품명<%=ivo.getItem_name() %></p>
				</td>
			</tr>
			<tr>
				<td class="itemTds">
					<h5>Item</h5>
					<p><%=Parser.categoryParser(ivo.getCategory()) %></p>
					
				</td>
				<td>
					<h5>Color</h5>
					<p>상품색깔<%=ivo.getColor() %></p>
				</td>
				
			</tr>
			<tr>
				<td>
					<h5>Product Number</h5>
					<p>상품번호<%=ivo.getItem_number() %></p>
				</td>
				<td>
					<h5>Grade</h5>
					<p>상품등급<%=ivo.getItem_rating() %></p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<h5>Size</h5>
					<p>상품사이즈<%=ivo.getSize() %></p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<h5>Price</h5>
					<p>상품가격<%=ivo.getPrice() %></p>
				</td>
			</tr>	
			<tr>
				<td  colspan="2">
					<h5>할인된 가격</h5>
					<p>상품할인된 가격<%=Parser.disPrice(ivo.getPrice(), ivo.getDiscount())%></p>
				</td>
			</tr>
				<%if(ivo.getStok() == 0 ) {%>
			<tr colspan="2">
				<td>임시품절 상태입니다.</td>
			</tr>
				<%} else { %>
			<tr>
				<td onclick="location.href='/item/shopBasket?item_sq=<%=ivo.getItem_sq() %>'">장바구니</td>
				<td onclick="location.href='/item/purchase'">바로 구매</td>
			</tr>
				<%} %>
		</table>
	</div>
</div>

<div class="item_footer">
	<%=content%>
</div>

	
</body>
</html>