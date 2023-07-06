<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	<hr>
	<form action="signup" method="post">
	<label>아이디 <input type="text" name="signupId"></label>
	<input type="button" id="btn_idCheck" data-init="0" value="id중복확인" disabled><br>
	<div id="result"></div>
	<label>비밀번호 <input type="password" name="signupPw"></label><br>
	<label>비밀번호 확인 <input type="password" name="signupPwCheck"></label><br>
	<label>이메일 <input type="email" name="signupEmail"></label><br>
	<input type="submit" value="회원가입">
	</form>
	<script>
		const txt_id = document.querySelector("input[name='signupId']");
		const btn_idCheck = document.querySelector("#btn_idCheck");
		const div_id_result = document.getElementById("result");
		btn_idCheck.addEventListener('click', idCheck);
		txt_id.addEventListener('keyup', checkReset);
		
		const txt_pw = document.querySelector("input[name='signupPw']");
	    const txt_repw = document.querySelector("input[name='signupPwCheck']");
	    txt_repw.addEventListener('change', checkSame);
		
		function idCheck() {
			console.log('idCheck called');
	        btn_idCheck.dataset.init = 1;
	        const xhr = new XMLHttpRequest();
	        xhr.onload = function () {
	            let checkResult = this.responseText;
	            if (checkResult === "1") {
	                div_result.innerHTML = "<span style='color: red;'>이미 사용중인 아이디입니다.</span>";
	                btn_idCheck.disabled = false;
	            } else {
	                div_result.innerHTML = "<span style='color: blue;'>사용 가능한 아이디입니다.</span>";
	                btn_idCheck.disabled = true;
	            }
	        }
	        xhr.open("POST", "idCheck", true);
	        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	        xhr.send("id=" + txt_id.value);
	    }
		function checkSame() {
	        if (txt_repw.value != txt_pw.value) {
	            alert("패스워드와 일치하지 않습니다. 다시 입력하세요.");
	            txt_repw.value = "";
	            txt_repw.focus();
	            return false;
	        } else if (txt_repw.value.length == 0 && txt_pw.value.length == 0) {
	            alert("패스워드를 입력하지 않았습니다. 확인하세요.");
	            txt_pw.focus();
	            return false;
	        }
	        return true;
	    }
		
		function checkReset() {
	        btn_idCheck.disabled = false;
	        div_result.innerHTML = "아이디 중복확인을 해야합니다.";
	    }
		function inputCheck() {
	        if (frm_reg.name.value.length == 0) {
	            alert("닉네임을 입력하세요.");
	            frm_reg.name.focus();
	            return false;
	        }
	        if (frm_reg.pw.value.length == 0) {
	            alert("비밀번호를 입력하세요.");
	            frm_reg.pw.focus();
	            return false;
	        }
	        if (frm_reg.repw.value.length == 0) {
	            alert("비밀번호 확인란을 입력하세요.");
	            frm_reg.repw.focus();
	            return false;
	        }
	       
	        if (btn_idCheck.disabled == false || btn_idCheck.getAttribute("data-init") == 0) {
	            alert("아이디 중복확인을 하세요...");
	            btn_idCheck.focus();
	            return false;
	        }
	        if (!checkSame()) {
	            return false;
	        }
	        return true;
	    }
	</script>
</body>
</html>
