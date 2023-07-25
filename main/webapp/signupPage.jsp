<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<style>
    body {
        background-color: #c2c2c2;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .main-content {
        background-color: white;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 20px;
        border-radius: 10px;
    }
    h1 {
        margin-bottom: 20px;
    }
    form {
        display: flex;
        flex-direction: column;
    }
    .input-area {
        display: flex;
        flex-direction: row;
        align-items: center;
        margin-bottom: 10px;
    }
    label {
        margin-right: 10px;
    }
    .result {
        margin-bottom: 5px;
    }
    input[type="submit"] {
        align-self: center;
        background-color: coral;
        padding: 5px 15px;
        border: none;
        color: white;
        cursor: pointer;
        border-radius: 5px;
        margin-top: 10px;
    }
</style>
</head>
<body>
    <div class="main-content">
        <h1>회원가입 페이지</h1>
        <hr>
        <form action="signup" method="post">
            <div class="input-area">
                <label>닉네임</label>
                <input type="text" name="signupName">
                <input type="button" id="btn_nicknameCheck" data-init="0" value="닉네임 중복확인">
            </div>
            <div class="result" id="div_result_nickname"></div>
            <div class="input-area">
                <label>아이디</label>
                <input type="text" name="signupId" maxlength="9" id="txt_id">
                <input type="button" id="btn_idCheck" data-init="0" value="아이디 중복확인" >
            </div>
            <div class="result" id="div_result_id"></div>
            <label>비밀번호 <input type="password" name="signupPw"></label><br>
            <label>비밀번호 확인 <input type="password" name="signupPwCheck"></label><br>
            <label>이메일 <input type="email" name="signupEmail"></label><br>
            <input type="submit" value="회원가입" onclick="return inputCheck()">&nbsp;&nbsp;
        </form>
    </div>
    <script>
	
		const txt_nickname = document.querySelector("input[name='signupName']");
		const btn_nicknameCheck = document.querySelector("#btn_nicknameCheck");
		const div_nickname_result = document.getElementById("div_result_nickname");
		const txt_id = document.querySelector("input[name='signupId']");
		const btn_idCheck = document.querySelector("#btn_idCheck");
		const div_id_result = document.getElementById("div_result_id");
		const txt_email = document.querySelector("input[name='signupEmail']");
		
		const txt_pw = document.querySelector("input[name='signupPw']");
	    const txt_repw = document.querySelector("input[name='signupPwCheck']");
	    txt_repw.addEventListener('change', checkSame);
		
	    btn_nicknameCheck.addEventListener('click', nicknameCheck);
	    txt_nickname.addEventListener('keyup', checkResetNickname);
		btn_idCheck.addEventListener('click', idCheck);
		txt_id.addEventListener('keyup', checkResetId);
		
		let isNicknameAvailable = false;
		let isIdAvailable = false;
		
		function nicknameCheck() {
			if (txt_nickname.value.trim().length == 0) {
		        alert("닉네임을 입력하세요.");
		        txt_nickname.focus();
		        return;
		    }
			btn_nicknameCheck.dataset.init = 1;
			var xhr = new XMLHttpRequest();
			xhr.onload = function () {
				let checkResult = this.responseText;
				if (checkResult === "1") {
					div_result_nickname.innerHTML = "<span style='color: red;'>이미 사용중인 닉네임입니다.</span>";
					btn_nicknameCheck.disabled = false;
		            isNicknameAvailable = false;
				} else {
					div_result_nickname.innerHTML = "<span style='color: blue;'>사용 가능한 닉네임입니다.</span>";
					 btn_nicknameCheck.disabled = true;
			         isNicknameAvailable = true;
				}
			};
			xhr.open("POST", "nicknameCheck", true);
			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhr.send("signupName=" + txt_nickname.value);
		}
		
		function idCheck() {
			if (txt_id.value.trim().length == 0) {
		        alert("아이디를 입력하세요.");
		        txt_id.focus();
		        return;
		    }
			if(txt_id.value.trim().length >= 10){
				alert("10자 미만으로 입력하세요.");
				txt_id.value = txt_id.value.slice(0, 9);
				txt_id.focus();
				return;
			}
			btn_idCheck.dataset.init = 1;
			var xhr = new XMLHttpRequest();
			xhr.onload = function () {
				let checkResult = this.responseText;
				if (checkResult === "1") {
				 console.log("서버에서 반환된 응답 값: ", checkResult);
					div_result_id.innerHTML = "<span style='color: red;'>이미 사용중인 아이디입니다.</span>";
					btn_idCheck.disabled = false;
		            isIdAvailable = false;
				} else {
					div_result_id.innerHTML = "<span style='color: blue;'>사용 가능한 아이디입니다.</span>";
					btn_idCheck.disabled = true;
		            isIdAvailable = true;
				}
			};
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
		
		function checkResetNickname() {
			btn_nicknameCheck.disabled = false;
			div_nickname_result.innerHTML = "닉네임 중복확인을 해야합니다.";
		}

		function checkResetId() {
			btn_idCheck.disabled = false;
			div_id_result.innerHTML = "아이디 중복확인을 해야합니다.";
		}

		function inputCheck() {
			 if (!isNicknameAvailable) {
			        alert("닉네임 중복확인을 해주세요. 사용 가능한 닉네임을 입력하세요.");
			        btn_nicknameCheck.focus();
			        return false;
			    }
			    if (!isIdAvailable) {
			        alert("아이디 중복확인을 해주세요. 사용 가능한 아이디를 입력하세요.");
			        btn_idCheck.focus();
			        return false;
			    }
	        
	        if (txt_pw.value.trim().length === 0) {
	            alert("비밀번호를 입력하세요.");
	            txt_pw.focus();
	            return false;
	        }
	        if (txt_repw.value.trim().length == 0) {
	            alert("비밀번호 확인란을 입력하세요.");
	            txt_repw.focus();
	            return false;
	        }
	        if (txt_email.value.trim().length == 0) {
	            alert("이메일을 입력하세요.");
	            txt_email.focus();
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

