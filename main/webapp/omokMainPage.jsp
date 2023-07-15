<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>오목 메인페이지</title>
  <style>
    .container {
      display: flex;
    }

    .roomList {
      flex: 1;
      margin-right: 20px;
    }

    .userList {
      flex: 1;
    }
  </style>
</head>

<body>
  <h1>오목 메인페이지</h1>
  <hr>
  <form action="detailUser" method="GET">
    <button type="submit">마이페이지</button>
  </form>
  <span>쿠키에 저장된 아이디: <span id="storedId"></span></span>
  <hr>
  <button type="">방만들기</button>
  <div class="container">
    <div class="roomList">
      <h2>방 리스트</h2>
      <ul id="rooms">
        <!-- 방 목록을 추가할 위치 -->
      </ul>
    </div>
    <div class="userList">
      <h2>유저 리스트</h2>
      <ul id="users">
        <!-- 유저 목록을 추가할 위치 -->
      </ul>
    </div>
  </div>

  <script>
    // 방 목록 데이터를 기반으로 li 태그를 추가하는 예시 코드입니다.
    // 서버에서 가져온 방 목록 데이터로 변경해 주세요.
    const roomList = ['방1', '방2', '방3'];
    const rooms = document.getElementById('rooms');

    roomList.forEach(room => {
      const li = document.createElement('li');
      const a = document.createElement('a');
      a.href = "#";
      a.textContent = room;
      li.appendChild(a);
      rooms.appendChild(li);
    });

    // 유저 목록 데이터를 기반으로 li 태그를 추가하는 예시 코드입니다.
    // 서버에서 가져온 유저 목록 데이터로 변경해 주세요.
    const userList = ['유저1', '유저2', '유저3'];
    const users = document.getElementById('users');

    userList.forEach(user => {
      const li = document.createElement('li');
      li.textContent = user;
      users.appendChild(li);
    });

    window.onload = function () {
      var storedId = document.getElementById("storedId");
      var cookies = document.cookie.split("; ");

      for (var i = 0; i < cookies.length; i++) {
        var keyValue = cookies[i].split("=");
        if (keyValue[0] === "id") {
          storedId.textContent = keyValue[1];
        }
      }
    };
  </script>
</body>

</html>
