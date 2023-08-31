<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 페이지</title>
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
    <h1>아이디 찾기</h1>
   <form action="/MyWebProject/FindIdServlet" method="post">
    <label>닉네임: <input type="text" name="name"></label>
    <label>이메일: <input type="email" name="email"></label>
    <input type="submit" value="아이디 찾기">
    </form>
    <a href="loginGuestPage.jsp">로그인 화면으로 가기</a>
</div>
<script>
function validateInput() {
    const txt_name = $("input[name='name']");
    const txt_email = $("input[name='email']");

    if (txt_name.val().length === 0) {
        alert("이름을 입력하세요.");
        txt_name.focus();
        return false;
    }
    if (txt_email.val().length === 0 || !txt_email[0].validity.valid) {
        alert("올바른 이메일 주소를 입력하세요.");
        txt_email.focus();
        return false;
    }
    return true;
}

</script>
</body>
</html>
