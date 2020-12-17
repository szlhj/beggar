<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    MemberVo mvo = (MemberVo) request.getAttribute("mvo");
    String email_fl = "false";
    String sms_fl = "false";
    if (mvo.isEmail_fl() == true) {
    	email_fl = "true";
    }
    if (mvo.isSms_fl() == true) {
    	sms_fl = "true";
    }
    /* String shId = null;
	
	if (mvo == null) {
		 = "";
	} else {
		shId = mvo.getMber_sq();
	} */
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<script>

	function modifyInfo(){
		var pwd = $('#pwd');
		var pwdc = $('#pwdc');
		var tel = $('#tel');
		var email = $('#email');
		var email_fl = $('#email_fl');
		var sms_fl = $('#sms_fl');

		if (!pwd.val()){
			alert('비밀번호를 입력 하세요.');
			pwd.focus;
			return;
		}
		if (!tel.val()){
			alert('전화번호를 입력 하세요.');
			tel.focus;
			return;
		}
		if (!email.val()){
			alert('이메일을 입력 하세요.');
			email.focus;
			return;
		}

		if (pwd.val() != pwdc.val()){
			alert('비밀번호가 일치하지 않습니다.');
// 			$pwd.focus;
			return;
		}
		
		regExp = new RegExp("^[a-zA-Z0-9!@#]{4,20}$","g");
		if (regExp.exec(pwd.val()) == null){
			alert("비밀번호 입력형식이 아닙니다.");
			$pwd.focus;
			$pwd.val('');
			return;
		}

		regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if (regExp.exec(email.val()) == null){
			alert("이메일 입력형식이 아닙니다.");
			$email.focus;
			$email.val('');
			return;
		}

		if (email_fl.is(":checked")){
			email_fl.val('Y');
		} else {
			email_fl.val('N');
		}
		if (sms_fl.is(":checked")){
			sms_fl.val('Y');
		} else {
			sms_fl.val('N');
		}

		$('#mfForm').submit();
	}

	function cancle() {
		location.href="/";
	}
</script>
</head>
<body>
회원정보 수정 Form
<form action="/member/modifyProc.do" method="post" id="mfForm">
아이디 <%=mvo.getId() %> <br />
새로운비밀번호<input type="password" id="pwd" name="pwd"/><br />
새로운비밀번호 확인 <input type="password" id="pwdc" name="pwdc" /> <br />
이름 <%=mvo.getName() %> <br />
휴대전화<input type="tel" id="tel" name="tel" value="<%=mvo.getPhone() %>"/> <br />
SMS 수신여부 <input type="checkbox" id="sms_fl" name="sms_fl"  <%=sms_fl.equals("true")?"checked":"" %> /> <br />
이메일<input type="email" id="email" name="email" value="<%=mvo.getEmail()%>"/> <br />
이메일 수신여부 <input type="checkbox" id="email_fl" name="email_fl" <%=email_fl.equals("true")?"checked":"" %> /> <br />
<%-- <input type="hidden" id="mber_sq" name="mber_sq" value="<%= %>" /><br> --%>
</form>
<button type="button" onclick="modifyInfo()">정보수정</button>
<button type="button" onclick="cancle()">취소</button>
</body>
</html>