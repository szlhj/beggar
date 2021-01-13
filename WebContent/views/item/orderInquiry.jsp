<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/views/css/item/orderInquiry.css" type="text/css">

<script type="text/javascript">
	function cancle() {
		location.href="/";
	}
</script>

</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	
	<div class="orderInquiryForm">
		<div class="orderInquiry">
			<form action="/item/orderView" method="post">
				<table>
					<tr>
						<td class="td1">주문번호</td>
						<td class="td2"><input type="text" id="nonmber" name="nonmber" /></td>
					</tr>
				</table>
				<button class="orderInquiry_button" type="submit">확인</button>
				<button class="orderInquiry_button" type="button" onclick="cancle()">취소</button>
			</form>
		</div>
	</div>
</body>
</html>