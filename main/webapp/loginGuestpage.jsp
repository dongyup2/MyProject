<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오목 메인 페이지 틀</title>
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
}
#box {
    background-color: #ffffff;
    max-width: 600px;
    min-height: 500px;
    padding: 20px;
    box-shadow: 0 0 2px rgba(0,0,0,0.2);
}
#logo {
    text-align: center;
    font-size: 32px;
    font-weight: bold;
    color: #202020;
    margin-bottom: 20px;
}
.button-container {
    display: flex;
    justify-content: space-around;
    width: 100%;
    margin-bottom: 30px;
}
button {
    padding: 10px 20px;
    background-color: #202020;
    color: #ffffff;
    border: none;
    cursor: pointer;
    font-size: 16px;
    text-transform: uppercase;
}
button:hover {
    background-color: #707070;
}
img {
    width: 100%;
    margin-bottom: 20px;
}
.description {
    text-align: center;
    font-size: 16px;
    color: #808080;
}
.link-container {
    font-size: 14px;
    color: #000;
}
a {
    text-decoration: none;
    color: white;
}
.idpwSelect{
	color: #003487
}

</style>
</head>
<body>
<div class="main-content">
    <div id="box">
        <div id="logo">
            오목게임
        </div>
        <div class="button-container">
            <button><a href="loginPageCookie.jsp">로그인</a></button>
            <button><a href="omokMainPage3.jsp">게스트</a></button>
        </div>
        <img src="img/오목게임플레이화면.png" alt="오목 게임 이미지">
        <p class="description">흑백 조각 (돌)이있는 15x15 보드의 두 플레이어를 위한 게임; 목표는 정확히 5 개의 돌을 연속으로 이기는 것입니다.</p>
        <div class="link-container">
            <a href="id-find" class="idpwSelect">아이디 찾기</a> | <a href="password-find" class="idpwSelect">비밀번호 찾기</a>
        </div>
    </div>
</div>

</body>
</html>
