<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>

<script type="text/javascript">
	function join(){
		var $id = $('#id');
		var $pwd = $('#pwd');
		var $pwdc = $('#pwdc');
		var $name = $('#name');
		var $tel = $('#tel');
		var $email = $('#email');
		var $email_fl = $('#email_fl');
		var $sms_fl = $('#sms_fl');

		if (!$id.val()){
			alert('아이디를 입력 하세요.');
			$id.focus;
			return;
		}
		if (!$pwd.val()){
			alert('비밀번호를 입력 하세요.');
			$pwd.focus;
			return;
		}
		if (!$name.val()){
			alert('이름을 입력 하세요.');
			$name.focus;
			return;
		}
		if (!$tel.val()){
			alert('전화번호를 입력 하세요.');
			$tel.focus;
			return;
		}
		if (!$email.val()){
			alert('이메일을 입력 하세요.');
			$email.focus;
			return;
		}

		if ($pwd.val() != $pwdc.val()){
			alert('비밀번호가 일치하지 않습니다.');
// 			$pwd.focus;
			return;
		}

		var regExp = new RegExp("^[a-z0-9]{4,20}$","g");
		if (regExp.exec($id.val()) == null){
			alert("아이디 입력형식이 아닙니다.");
			$id.focus;
			$id.val('');
			return;
		}
		
		regExp = new RegExp("^[a-zA-Z0-9!@#]{4,20}$","g");
		if (regExp.exec($pwd.val()) == null){
			alert("비밀번호 입력형식이 아닙니다.");
			$pwd.focus;
			$pwd.val('');
			return;
		}
		// ^[가-힣]{2,20}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,20} : 한글2~20자 or 영문2~20자 공백 영문2~20자
		regExp = new RegExp("^[가-힣]{2,20}|[a-zA-Z]{2,20}\s[a-zA-Z]{2,20}$","g");
		if (regExp.exec($name.val()) == null){
			alert("이름 입력형식이 아닙니다.");
			$name.focus;
			$name.val('');
			return;
		}
		
		regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if (regExp.exec($email.val()) == null){
			alert("이메일 입력형식이 아닙니다.");
			$email.focus;
			$email.val('');
			return;
		}

		if ($email_fl.is(":checked")){
			$email_fl.val('Y');
		} else {
			$email_fl.val('N');
		}
		if ($sms_fl.is(":checked")){
			$sms_fl.val('Y');
		} else {
			$sms_fl.val('N');
		}

		$('#reform').submit();
	}

	function cancle(){
		location.href="/";
	}
</script>

</head>
<body>
<div>
	<form action="/member/RegisterProcAction.do" method="post" id="reform">
		<input type="text" id="id" name="id" placeholder="아이디"/><span id="idMessage"></span><br>
		<input type="password" id="pwd" name="pwd" placeholder="비밀번호"/><span id="pwdMessage"></span><br>
		<input type="password" id="pwdc" name="pwdc" placeholder="비밀번호 확인"/><span id="pwdcMessage"></span><br>
		<input type="text" id="name" name="name" placeholder="이름"/><br>
		<input type="tel" id="tel" name="tel" placeholder="전화번호"/><br>
		<input type="email" id="email" name="email" placeholder="이메일"/><br>
		<input type="checkbox" id="email_fl" name="email_fl" />이메일체크<br>
		<input type="checkbox" id="sms_fl" name="sms_fl" />SMS체크<br>
	</form>
	<button onclick="join()">가입</button>
	<button onclick="cancle()">취소</button>
</div>
</body>
</html>