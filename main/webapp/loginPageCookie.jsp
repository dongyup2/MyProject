<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<hr>
	<form action="login" method="post" onsubmit="return inputCheck();">
	<label>아이디: <input type="text" name="id"></label><br>
	<label>비밀번호: <input type="password" name="pw"></label><br>
	<label for="maintain">로그인 유지:</label>
	<input type="checkbox" id="maintain" />
  	<br><br>
	<input type="submit" value="로그인">	
	</form>
	<script>
	
    const txt_id = document.querySelector("input[name='id']");
    const txt_pw = document.querySelector("input[name='pw']"); 

	function inputCheck() {
        if (txt_id.value.length == 0) {
            alert("아이디를 입력하세요.");
            txt_id.focus();
            return false;
        }
        if (txt_pw.value.length == 0) {
            alert("비밀번호를 입력하세요.");
            txt_pw.focus();
            return false;
        }
        return true;
    }
	 let formData = new FormData();
	  formData.append("id", txt_id.value);
	  formData.append("pw", txt_pw.value);
	  formData.append("maintain", document.querySelector("#maintain").checked);
	  
	  // POST 요청 전송
	  	fetch("login", {
	    method: "POST",
	    body: formData
	  })
	    .then((response) => response.text())
	    .then((result) => {
	      if (result === "success") {
	        window.location.href = "/"; // 이동할 페이지로 변경하세요.
	      } else {
	        alert("로그인에 실패했습니다.");
	      }
	    });
	}
	</script>
</body>
</html>
