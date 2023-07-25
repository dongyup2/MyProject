<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
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
        font-size: 2em; /* 수정한 부분 */
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
        font-size: 1.1em; /* 수정한 부분 */
    }
    label:nth-child(2) {
        margin-bottom: 0;
    }
    #maintain {
        margin-bottom: 10px;
    }
    input[type="text"],
    input[type="password"] {
        padding: 5px;
        font-size: 1em; /* 수정한 부분 */
    }

    input[type="submit"] {
        align-self: center;
        background-color: coral;
        padding: 10px 20px; /* 수정한 부분 */
        border: none;
        color: white;
        cursor: pointer;
        font-size: 1.1em; /* 수정한 부분 */
        border-radius: 5px;
    }
    p {
        margin-top: 10px;
    }
</style>
</head>
<body>
    <div class="main-content">
        <h1>로그인</h1>
        <form action="login" method="post" onsubmit="return inputCheck();">
            <label>아이디: <input type="text" name="id"></label>
            <label>비밀번호: <input type="password" name="pw"></label>
            <label for="maintain">로그인 유지: <input type="checkbox" id="maintain" name="maintain" value="keep" /></label>
            <input type="submit" value="로그인">
            <p style="color: red;">${errorMessage}</p>
        </form>
    </div>
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
    </script>
</body>
</html>
