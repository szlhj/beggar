<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.beggar.beggar.vo.OrderVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	OrderVo vo = (OrderVo) request.getAttribute("orderVo");
    	ArrayList<OrderVo> orderList = (ArrayList<OrderVo>) request.getAttribute("orderList");
    	
    	MemberVo memberVo = (MemberVo) session.getAttribute("vo");
    	
    	String nonmber = (String) request.getAttribute("nonmber");
    	
    	if (nonmber == null) {
    		nonmber = "";
    	}
    	
    	int mber_sq = 0;
    
    	if (memberVo != null) {
    		mber_sq = memberVo.getMber_sq();
    	}
    	
    	String formAddr = "";
    	String formName = "";
    	String formPhone = "";
    	String toAddr = "";
    	String toName = "";
    	String toPhone = "";
    	String record = "";
    	
    	if (vo != null) {
	    	formAddr = vo.getAddr_form();
	    	formName = vo.getName_form();
	    	formPhone = vo.getName_form_phone();
	    	toAddr = vo.getAddr_to();
	    	toName = vo.getName_to();
	    	toPhone = vo.getName_to_phone();
	    	record = vo.getRecord_item();
    	}
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/결제</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function payment(num) {
		var form = $('#orderForm');
		var payment = $('#payment');
		var formAddr = $('#formAddr');
		var formPhone = $('#formPhone');
		var formName = $('#formName');
		var toName = $('#toName');
		var toPhone = $('#toPhone');
		var toAddr = $('#toAddr');
		
		if (!formAddr.val()){
			alert("보내는 사람 주소가 없습니다.");
			formAddr.focus();
			return;
		}
		if (!formPhone.val()){
			alert("보내는 사람 전화번호가 없습니다.");
			formPhone.focus();
			return;
		}
		if (!formName.val()){
			alert("보내는 사람 이름이 없습니다.");
			formName.focus();
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

<style type="text/css">
	.priceTd{
		width: 150px;
		height: 50px;
		text-align: center;
	}
	.stokTd{
		width: 100px;
		height: 50px;
		text-align: center;
	}
</style>

</head>
<body>
	<button onclick="location.href='/admin'">관리자 홈</button>
	<button onclick="location.href='/'">홈</button>
	
	<%if (orderList == null) { %>
		결제할 내용이 없습니다.
	<%} else { %>
		<%for (int i = 0; i < orderList.size(); i++) { %>
			<table border="1">
				<tr>
					<td class="imgTd" rowspan="2"><img style="width: 100px; height: 100px;" src="<%=orderList.get(i).getItem_img() %>"></td>
					<td class="orderTd" colspan="2"><%=orderList.get(i).getItem_name() %></td>
					<td class="priceTd" rowspan="2"><%=orderList.get(i).getPrice() * orderList.get(i).getItem_stok() %></td>
				</tr>
				<tr>
					<td class="priceTd">
						<%=orderList.get(i).getPrice() %>
					</td>
					<td class="stokTd">
						<%=orderList.get(i).getItem_stok() %> EA
					</td>
				</tr>
			</table>
		<%} %>
	
		<form action="/item/orderProc" method="post" id="orderForm">
			<table border="1">
				<tr>
					<td colspan="2">보내는 사람
						<input type="hidden" id="mber_sq" name="mber_sq" value="<%=mber_sq %>">
						<input type="hidden" id="payment" name="payment">
						<input type="hidden" id="nonmber" name="nonmber" value="<%=nonmber %>">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" id="formName" name="formName" value="<%=formName %>"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" id="formPhone" name="formPhone" value="<%=formPhone %>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id="formAddr" name="formAddr" value="<%=formAddr %>"></td>
				</tr>
				<tr>
					<td colspan="2">받는 사람</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" id="toName" name="toName" value="<%=toName %>"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" id="toPhone" name="toPhone" value="<%=toPhone %>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id="toAddr" name="toAddr" value="<%=toAddr %>"></td>
				</tr>
				<tr>
					<td>요청사항</td>
					<td><input type="text" id="record" name="record" value="<%=record %>"></td>
				</tr>
			</table>
		</form>
		
		<button onclick="payment('card')">카드결제</button>
		<button onclick="payment('cash')">무통장 입금</button>
	<%} %>
	<button onclick="cancle()">취소</button>
</body>
</html>