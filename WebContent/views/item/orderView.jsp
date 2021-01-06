<%@page import="shop.beggar.beggar.vo.OrderVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	MemberVo mberVo = (MemberVo) session.getAttribute("vo");
    	String nonmber = (String) request.getAttribute("nonmber");
    	ArrayList<OrderVo> orderList = (ArrayList<OrderVo>) request.getAttribute("orderVoList");
    	OrderVo vo = (OrderVo) request.getAttribute("orderVo");
    	
    	int mber_sq = 0;
    
    	if (mberVo == null || mberVo.equals("0")) {
    		mber_sq = 0;
    	} else {
    		mber_sq = mberVo.getMber_sq();
    	}
    	
    	if (nonmber == null) {
    		nonmber = "";
    	}
    	
    	String formAddr = "";
    	String formName = "";
    	String formPhone = "";
    	String toAddr = "";
    	String toName = "";
    	String toPhone = "";
    	String record = "";
    	String dttm = "";
    	
    	if (vo != null || vo.getMber_sq() != 0) {
	    	formAddr = vo.getAddr_form();
	    	formName = vo.getName_form();
	    	formPhone = vo.getName_form_phone();
	    	toAddr = vo.getAddr_to();
	    	toName = vo.getName_to();
	    	toPhone = vo.getName_to_phone();
	    	record = vo.getRecord_item();
	    	dttm = vo.getOrder_dttm();
    	}
    	
    	int total = 0;
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	function changeComma(e,num) {
		return e.innerHTML = numberWithCommas(num);
	}
	function cancle() {
		location.href="/";
	}
	function orderDelete() {
		$("#orderDeleteForm").submit();
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
	.img{
		width: 100px;
		height: 100px;
	}
	.totalTitle{
		width: 360px;
		height: 50px;
		text-align: center;
	}
	.totalPrice{
		width: 150px;
		height: 50px;
		text-align: center;
	}
</style>

</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	<jsp:include page="/views/item/navigation.jsp"></jsp:include>
	
	<%if (orderList == null) { %>
		결제할 내용이 없습니다.
	<%} else { %>
		<table border="1">
			<tr>
				<td class="img" rowspan="2">이미지</td>
				<td class="orderTd" colspan="2">상품명</td>
				<td class="priceTd" rowspan="2">결재금액</td>
			</tr>
			<tr>
				<td class="priceTd">가격</td>
				<td class="stokTd">수량</td>
			</tr>
		</table>
		<%for (int i = 0; i < orderList.size(); i++) { %>
			<table border="1">
				<tr>
					<td class="imgTd" rowspan="2"><img class="img" src="<%=orderList.get(i).getItem_img() %>"></td>
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
		<% total += orderList.get(i).getPrice() * orderList.get(i).getItem_stok();
		} %>
		<br>
		<table>
			<tr>
				<td class="totalTitle">총합계 금액</td>
				<td class="totalPrice"><%=total %></td>
			</tr>
		</table>
	<%} %>
	<form action="/item/orderDelete" method="post" id="orderDeleteForm">
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
	<button onclick="orderDelete()">주문 삭제</button>
	<button onclick="cancle()">취소</button>
</body>
</html>