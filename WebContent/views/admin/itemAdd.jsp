<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품추가</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function join() {

		var $item_name = $('#item_name');
		var $categore = $('#categore');
		var $code = $('#code');
		var $price = $('#price');
		var $discount = $('#discount');
		var $stok = $('#stok');
		var $color = $('#color');
		var $item_number = $('#item_number');
		var $item_rating = $('#item_rating');
		var $size = $('#size');
		var $explanation = $('#explanation');

 		if (!$item_name.val()) {
 			alert('상품이름을 입력 하세요.');
 			$item_name.focus;
 			return;
 		}
 		if (!$categore.val()) {
 			alert('카테고리를 입력 하세요.');
 			$categore.focus;
 			return;
 		}
 		if (!$code.val()) {
 			alert('상품코드를 입력 하세요.');
 			$code.focus;
 			return;
 		}
 		if (!$price.val()) {
 			alert('상품가격을 입력 하세요.');
 			$price.focus;
 			return;
 		}
 		if (!$discount.val()) {
 			alert('상품할인율을 입력 하세요.(0~100)');
 			$discount.discount;
 			return;
 		}

 		if (!$stok.val()) {
 			alert('상품의 재고를 입력 하세요.');
 			$stok.discount;
 			return;
 		}

 		if (!$color.val()) {
 			alert('상품의 컬러를 입력 하세요.');
			$color.discount;
 			return;
 		}
 		if (!$item_number.val()) {
 			alert('상품의 넘버를 입력하세요.');
 			$item_number.discount;
 			return;
 		}
 		if (!$item_rating.val()) {
 			alert('상품의 등급을 입력하세요.');
 			$item_rating.discount;
 			return;
 		}
 		if (!$size.val()) {
 			alert('상품의 사이즈를 입력하세요.');
 			$size.discount;
 			return;
 		}
		
		
		if (!$explanation.val()) {
 			alert('상품의 설명을 입력하세요.');
 			$explanation.discount;
 			return;
 		}
		
		saveContent();
	}
	function cancle() {
		location.href = "/";
	}
</script>

</head>
<body>
	<div>
		<div>
			<section>
				<form action="/admin/itemAddProc.do" method="post"
					id="editorForm">
					상품이름<input type="text" id="item_name" name="item_name" /><br>
					카테고리<input type="text" id="categore" name="categore" /><br>
					상품코드<input type="text" id="code" name="code" /><br>
					가격<input type="text" id="price" name="price" /><br>
					상품 할인율<input type="text" id="discount" name="discount" /><br>
					재고<input type="text" id="stok" name="stok" /><br>
					상품컬러<input type="text" id="color" name="color" /><br>
					상품넘버링<input type="text" id="item_number" name="item_number" /><br>
					상품 등급<input type="text" id="item_rating" name="item_rating" /><br>
					사이즈<input type="text" id="size" name="size" /><br>
					설명<input type="text" id="explanation" name="explanation" /><br>
					<div>
						<jsp:include page="/editor/editorSkinForRegister.jsp"
							flush="false" />
					</div>

				</form>
				<button onclick="join()">등록</button>
				<button onclick="cancle()">취소</button>
			</section>
		</div>
	</div>
</body>
</html>