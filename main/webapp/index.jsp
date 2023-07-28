<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-wi\dth, initial-scale=1.0">
    <title>메인 플랫폼</title>
    <style>
        .header {
            display: flex;
            align-items: center;
            justify-content: center; /* 중앙 정렬 추가 */
        }

        .header img {
            width: 130px;
            height: 25px;
            margin-right: 10px;
        }
        
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            ba
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
        }
        h3{
            cursor: pointer;
        }
        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            align-items: flex-start;
            width: 80%;
            margin: auto;
            gap: 20px;
            padding-bottom: 50px;
        }
        .box .live {
            position: absolute;
            top: 15px;
            right: 15px;
            background-color: red;
            color: white;
            font-size: 12px;
            padding: 2px 6px;
            border-radius: 2px;
        }
        .box {
        position: relative;
        width: 20%;
        background-color: #f8f8f8;
        border: 1px solid #ddd;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        padding: 15px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }


        
        .box img {
            width:100%;
            height: 150px;
            cursor: pointer;
        }

        .box h3 {
            font-size: 1.2rem;
            margin-bottom: 0.5rem;
        }

        .box p {
            font-size: 0.9rem;
            color: #999;
            margin-top: 0;
        }
    </style>
</head>
<body>
    <div class="header">
    <!-- <img src="images/YouTube_Premium_logo.svg" alt="Logo"> -->
    <h2>보드게임 플랫폼</h2>
</div>
    <div class="container">
        <div class="box">
            <img src="img/omokLogo.jpg" alt="Example Image" id="image1">
            <div class="live">접속가능</div>
            <h3 id="title1">오목 게임</h3>
            <p>1명 접속중</p>
        </div>
        <div class="box">
            <img src="img/noimage.jpg" alt="Example Image">
            <h3 class="title2">게임 없음</h3>
            <p>0명 접속중</p>
        </div>
        <div class="box">
        <img src="img/noimage.jpg" alt="Example Image">
            <h3 class="title3">게임 없음</h3>
            <p>0명 접속중</p>
        </div>
        <div class="box">
            <img src="img/noimage.jpg" alt="Example Image">
            <h3 class="title4">게임 없음</h3>
            <p>0명 접속중</p>
        </div>
        <div class="box">
            <img src ="img/noimage.jpg" alt="Example Image">
            <h3 class="title5">게임 없음</h3>
            <p>0명 접속중</p>
        </div>
        <div class="box">
            <img src="img/noimage.jpg" alt="Example Image">
            <h3 class="title6">게임 없음</h3>
            <p>0명 접속중</p>
        </div>
        <div class="box">
            <img src="img/noimage.jpg" alt="Example Image">
            <h3 class="title7">게임 없음</h3>
            <p>0명 접속중</p>
        </div>
        <div class="box">
            <img src="img/noimage.jpg" alt="Example Image">
            <h3 class="title8">게임 없음</h3>
            <p>0명 접속중</p>
        </div>
    </div>
</body>
<script>
    // 이미지와 제목에 클릭 이벤트 리스너 추가
    document.getElementById("image1").addEventListener("click", function () {
        window.location.href = "loginGuestpage.jsp";
    });
    document.getElementById("title1").addEventListener("click", function () {
        window.location.href = "loginGuestpage.jsp";
    });
</script>
</html>
