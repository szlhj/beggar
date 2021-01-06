<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function cancle() {
		location.href="/";
	}
</script>

</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	<jsp:include page="/views/item/navigation.jsp"></jsp:include>
	
	<form action="/item/orderView" method="post">
		<input type="text" id="nonmber" name="nonmber" value="0" /><br>
		<button type="submit">확인</button>
		<button type="button" onclick="cancle()">취소</button>
	</form>
</body>
</html>