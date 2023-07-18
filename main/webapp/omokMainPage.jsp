<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!DOCTYPE html> <html> <head> <meta charset="UTF-8"> <title>오목 메인페이지</title> <style> .container { display: flex; }
.roomList {
  flex: 1;
  margin-right: 20px;
}

.userList {
  flex: 1;
}
</style> </head> <body> <h1>오목 메인페이지</h1> <hr> <form action="detailUser" method="GET"> <button type="submit">마이페이지</button> </form> <button id="createRoom">방만들기</button> <hr> <span>쿠키에 저장된 아이디: <span id="storedId"></span></span> <div class="container"> <div class="roomList"> <h2>방 리스트</h2> <ul id="rooms"> <!-- 방 목록을 추가할 위치 --> </ul> </div> <div class="userList"> <h2>유저 리스트</h2> <ul id="users"> <!-- 유저 목록을 추가할 위치 --> </ul> </div> </div> <script> function createRoom() { const roomName = prompt("방 이름을 입력해주세요.");
  if (roomName) {
    const li = document.createElement('li');
    const a = document.createElement('a');
    a.href = "omokGame.html?room=" + encodeURIComponent(roomName); // 게임 URL을 여기 추가하고, room이라는 쿼리 파라미터로 방 이름을 전달합니다.
    a.textContent = roomName;
    li.appendChild(a);
    rooms.appendChild(li);
} else {
  alert("방 이름을 입력해주세요.");
}
}
document.getElementById('createRoom').addEventListener('click', createRoom); window.onload = function() {
var storedId = document.getElementById("storedId");
var cookies = document.cookie.split("; ");
for (var i = 0; i < cookies.length; i++) {
  var keyValue = cookies[i].split("=");
  if (keyValue[0] === "id") {
    storedId.textContent = keyValue[1];
  }
}
}
</script> </body> </html>