<%@page import="shop.beggar.common.Parser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ItemVo ivo = (ItemVo) request.getAttribute("ivo");    
	ArrayList<ItemVo> list = (ArrayList<ItemVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 페이지</title>
<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<script>

	function cartCount(type, ths) {
		var $input = $(ths).parents("td").find("input[name='i_stok']");  //부모부분인 td의 자식 name i_stok 부분을 넣어줌
		var tCount = Number($input.val()); //i_stok을 숫자로 변환시킴 (변수 $input)
		var sum = $('#sumPrice');
		var total_stok = Number($('#total_stok').val());
		var sell_price = Number($('#sell_price').val());
		if(type == 'p') {
			if(tCount == total_stok) {
				alert("총 구매 가능한 개수입니다.")
			} else {
				$input.val(Number(tCount)+1);
			}
		} else {
			if (tCount > 1) $input.val(Number(tCount)-1);
		}
		sum.val(Number($input.val() * sell_price));
	}
	
	function purchase() {
		if ($('#cart_ck').is(':checked')) {
			$('#ctForm').submit();
		} else {
			alert("선택된 상품이 없습니다.");
			return;
		}
	}

</script>
</head>
<body>

	<form action="/item/purchase" method="post" id="ctForm">
<table>
	<tr>
		<th></th>
		<th>이미지</th>
		<th>상품정보</th>
		<th>판매가</th>
		<th>수량</th>
		<th>합계</th>	
	</tr>
	<% if(ivo != null) {%>
	<tr>
		<td><input type="checkbox" id="cart_ck" value="<%=ivo.getItem_sq() %>" name="ck_item_sq"/></td>
		<td><img src="<%=ivo.getFilepath() %>" alt="" width="50px" height="50px"/></td>
		<td><%=ivo.getItem_name() %></td>
		<td><%=ivo.getPrice() %></td>
		<td>
			<button type="button" onclick="cartCount('m',this)">-</button>
			<input type="text" name="i_stok" value="1" readonly="readonly"/>
			<button type="button" onclick="cartCount('p',this)">+</button>
			<input type="hidden" id="sell_price" value="<%=Parser.disPrice(ivo.getPrice(), ivo.getDiscount())%>"/>
			<input type="hidden" id="total_stok" value="<%=ivo.getStok() %>"/>
			<input type="hidden" id="item_sq" name="item_sq" value="<%=ivo.getItem_sq() %>"/>
		</td>
		<td>
			<input type="text" value="<%=Parser.disPrice(ivo.getPrice(), ivo.getDiscount()) %>" id="sumPrice" readonly />
		</td>
	</tr>	

	<%} else if (list != null){ %>
	<% for (int i = 0; i <list.size(); i++) {%>
	<tr>
		<td><input type="checkbox" id="cart_ck" value="<%=list.get(i).getItem_sq() %>" name="ck_item_sq"/></td>
		<td><img src="<%=list.get(i).getFilepath() %>" alt="" width="50px" height="50px"/></td>
		<td><%=list.get(i).getItem_name() %></td>
		<td><%=Parser.disPrice(list.get(i).getPrice(), list.get(i).getDiscount()) %></td>
		<td>
			<button type="button" onclick="location.href='/item/cartForm?item_stok=<%=list.get(i).getItem_stok()-1%>&item_sq=<%=list.get(i).getItem_sq()%>&stok=<%=list.get(i).getStok()%>'">-</button>
			<input type="text" name="i_stok" value="<%=list.get(i).getItem_stok() %>" readonly="readonly"/>
			<button type="button" onclick="location.href='/item/cartForm?item_stok=<%=list.get(i).getItem_stok()+1%>&item_sq=<%=list.get(i).getItem_sq()%>&stok=<%=list.get(i).getStok()%>'">+</button>
			<input type="hidden" id="sell_price" value="<%=Parser.disPrice(list.get(i).getPrice(), list.get(i).getDiscount()) %>"/>
			<input type="hidden" id="total_stok" value="<%=list.get(i).getStok() %>"/>
		</td>
		<td>
			<input type="text" value="<%=Parser.disPrice(list.get(i).getPrice(), list.get(i).getDiscount()) * list.get(i).getItem_stok()%>" id="sumPrice" readonly />
		</td>
				
	</tr>
		<% } %>
	<%} else { %>
	<tr>
		<td colspan="6">장바구니가 비었습니다.</td>
	</tr>
	<%} %>
	<tr>
		<td colspan="6" style="text-align:end;"><button type="button" onclick="purchase()">선택상품 주문하기</button></td>
	</tr>
</table>
	</form>

</body>
</html>