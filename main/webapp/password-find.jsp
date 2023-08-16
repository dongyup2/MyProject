<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 페이지</title>
<script src="js/jquery-3.7.0.js"></script>
<style>
body {
    background-color: #d2d2d2;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
.main-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: white;
    padding: 30px;
    border-radius: 10px;
    width: 400px;
}
h1 {
    margin-bottom: 20px;
    font-size: 2em;
}
form {
    display: flex;
    flex-direction: column;
}
label {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    font-size: 1.1em;
}
input[type="text"], input[type="email"] {
    padding: 5px;
    font-size: 1em;
}
input[type="submit"] {
    align-self: center;
    background-color: coral;
    padding: 10px 20px;
    border: none;
    color: white;
    cursor: pointer;
    font-size: 1.1em;
    border-radius: 5px;
}
</style>
</head>
<body>
<div class="main-content">
    <h1>비밀번호 찾기</h1>
    <form action="findPw" method="post">
        <label>아이디: <input type="text" name="id"></label>
        <label>이메일: <input type="email" name="email"></label>
        <input type="submit" value="비밀번호 찾기" onclick="findPw()">
    </form>
</div>
<script>
function validateInput() {
    const txt_id = $("input[name='id']");
    const txt_email = $("input[name='email']");

    if (txt_id.val().length === 0) {
        alert("아이디를 입력하세요.");
        txt_id.focus();
        return false;
    }
    if (txt_email.val().length === 0 || !txt_email[0].validity.valid) {
        alert("올바른 이메일 주소를 입력하세요.");
        txt_email.focus();
        return false;
    }
    return true;
}

function findPw() {
    if (validateInput()) {
        const id = $("input[name='id']").val();
        const email = $("input[name='email']").val();

        $.ajax({
            url: "findPw",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({id: id, email: email}),
            success: function(response) {
                if (response.result === "success") {
                    alert("비밀번호는 이메일로 전송되었습니다!!");
                } else {
                    alert("일치하는 정보가 없습니다!");
                }
            },
            error: function() {
                alert("서버와의 통신이 실패하였습니다.");
            }
        });
    }
}
</script>
</body>
</html>
