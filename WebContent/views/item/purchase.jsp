<%@page import="shop.beggar.beggar.vo.OrderVo"%>
<%@page import="shop.beggar.common.Parser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ItemVo ivo = (ItemVo)request.getAttribute("ivo");
	ArrayList<ItemVo> purch_list = (ArrayList<ItemVo>) request.getAttribute("purch_list");
	OrderVo imvo = (OrderVo)request.getAttribute("imvo");
	
	String addr_form = "";
	String addr_to = "";
	String name_form = "";
	String name_to = "";
	String name_form_phone = "";
	String name_to_phone = "";
	String record_item = "";
	
	int total_price = 0;
	
	if (imvo != null) {
		addr_form = imvo.getAddr_form();
		addr_to = imvo.getAddr_to();
		name_form = imvo.getName_form();
		name_to = imvo.getName_to();
		name_form_phone = imvo.getName_form_phone();
		name_to_phone = imvo.getName_to_phone();
		record_item = imvo.getRecord_item();
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매페이지</title>
<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<script>
function payment(num) {
	var form = $('#orderForm');
	var payment = $('#payment');
	var formAddr = $('#formAddr');
	var formPhone = $('#formPhone');
	var formName = $('#formName');
	var toName = $('#toName');
	var toPhone = $('#toPhone');
	var toAddr = $('#toAddr');
	
	if (!formName.val()){
		alert("보내는 사람 이름이 없습니다.");
		formName.focus();
		return;
	}
	if (!formPhone.val()){
		alert("보내는 사람 전화번호가 없습니다.");
		formPhone.focus();
		return;
	}
	if (!formAddr.val()){
		alert("보내는 사람 주소가 없습니다.");
		formAddr.focus();
		return;
	}
	if (!toName.val()){
		alert("받는 사람 이름이 없습니다.");
		toName.focus();
		return;
	}
	if (!toPhone.val()){
		alert("받는 사람 전화번호가 없습니다.");
		toPhone.focus();
		return;
	}
	if (!toAddr.val()){
		alert("받는 사람 주소가 없습니다.");
		toAddr.focus();
		return;
	}
	
	if (num == "card") {
		payment.val('1');
	} else {
		payment.val('2');
	}
	
	form.submit();
}

function cancle() {
	location.href="/";
}
</script>
</head>
<body>
<form action="/item/purchaseProc" method="post" id="orderForm">
	<%if (ivo != null) { %>
		<table border="1">
			<tr>
				<td class="imgTd" rowspan="2"><img style="width: 100px; height: 100px;" src="<%=ivo.getFilepath() %>"></td>
				<td class="orderTd" colspan="2"><%=ivo.getItem_name() %></td>
				<td class="priceTd" rowspan="2"><%=Parser.disPrice(ivo.getPrice(), ivo.getDiscount()) * ivo.getItem_stok() %></td>
			</tr>
			<tr>
				<td class="priceTd">
					<%=ivo.getPrice() %>
				</td>
				<td class="stoked" name="item_stok">
					<%=ivo.getItem_stok() %> EA
				</td>
				<input style="display:none;" type="checkbox" id="cart_ck" checked name="ck_item_sq" value="<%=ivo.getItem_sq() %>"/>
				<input style="display:none;" type="checkbox" id="dis_price" checked name="dis_price" value="<%=Parser.disPrice(ivo.getPrice(), ivo.getDiscount()) %>"/>
				<input style="display:none;" type="checkbox" id="item_stok" checked name="item_stok" value="<%=ivo.getItem_stok() %>"/>
				<%total_price += Parser.disPrice(ivo.getPrice(), ivo.getDiscount()) * ivo.getItem_stok(); %>
			</tr>
		</table>
	<%} else { %>
	<%for (int i = 0; i < purch_list.size(); i++) { %>
		<table border="1">
			<tr>
				<td class="imgTd" rowspan="2"><img style="width: 100px; height: 100px;" src="<%=purch_list.get(i).getFilepath() %>"></td>
				<td class="orderTd" colspan="2"><%=purch_list.get(i).getItem_name() %></td>
				<td class="priceTd" rowspan="2"><%=Parser.disPrice(purch_list.get(i).getPrice(),purch_list.get(i).getDiscount()) * purch_list.get(i).getItem_stok() %></td>
			</tr>
			<tr>
				<td class="priceTd">
					<%=purch_list.get(i).getPrice() %>
				</td>
				<td class="item_stok" name="item_stok">
					<%=purch_list.get(i).getItem_stok() %> EA
				</td>
				<input style="display:none;" type="checkbox" id="cart_ck" checked name="ck_item_sq" value="<%=purch_list.get(i).getItem_sq() %>"/>
				<input style="display:none;" type="checkbox" id="dis_price" checked name="dis_price" value="<%=Parser.disPrice(purch_list.get(i).getPrice(), purch_list.get(i).getDiscount()) %>"/>
				<input style="display:none;" type="checkbox" id="item_stok" checked name="item_stok" value="<%=purch_list.get(i).getItem_stok() %>"/>
				<%total_price += Parser.disPrice(purch_list.get(i).getPrice(),purch_list.get(i).getDiscount()) * purch_list.get(i).getItem_stok(); %>
			</tr>
		</table>
	<%} %>
	<%} %>
	
		<table border="1">
			<tr>
				<td colspan="2">보내는 사람
					<input type="hidden" id="payment" name="payment">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" id="formName" name="formName" value="<%=name_form %>"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" id="formPhone" name="formPhone" value="<%=name_form_phone %>"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="formAddr" name="formAddr" value="<%=addr_form %>"></td>
			</tr>
			<tr>
				<td colspan="2">받는 사람</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" id="toName" name="toName" value="<%=name_to %>"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" id="toPhone" name="toPhone" value="<%=name_to_phone %>"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="toAddr" name="toAddr" value="<%=addr_to %>"></td>
			</tr>
			<tr>
				<td>요청사항</td>
				<td><input type="text" id="record" name="record" value="<%=record_item %>"></td>
			</tr>
			<tr>
				<td>총 금액</td>
				<td><input type="text" id="total_price" name="total_price" value="<%=total_price %>원"></td>
			</tr>
		</table>
	</form>
	
	<button onclick="payment('card')">카드결제</button>
	<button onclick="payment('cash')">무통장 입금</button>
	
	<button onclick="cancle()">취소</button>
</body>
</html>