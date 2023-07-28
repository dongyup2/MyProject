document.addEventListener("DOMContentLoaded", function() {
const exitRoomButton = document.getElementById("exit-room-btn");
exitRoomButton.addEventListener("click", () => {
  exitRoom(roomId);
});
function exitRoom(roomId) {
    // 사용자를 방에서 제거하는 로직 코드 추가
    removeUserFromRoom(roomId);

    // 방 삭제 여부 확인 및 삭제
    checkEmptyAndDeleteRoom(roomId);
}

function removeUserFromRoom(roomId) {
  $.ajax({
    url: "removeUserFromRoom",
    type: "POST",
    data: {
      roomId: roomId
    },
    dataType: "json",
    success: function (data) {
      if (data.result === "success") {
        console.log("User removed from room successfully");
      } else {
        console.error("Error removing user from room:", data.message);
      }
    },
    error: function (xhr, status, error) {
      console.error("Error removing user from room:", error);
    },
  });
}

function checkEmptyAndDeleteRoom(roomId) {
  $.ajax({
    url: "checkEmptyAndDeleteRoom",
    type: "POST",
    data: {
      roomId: roomId
    },
    dataType: "json",
    success: function (data) {
      if (data.result === "success") {
        console.log("Empty room deleted successfully");
        // 페이지를 새로 고침하거나 다른 페이지로 이동
        window.location.href = "omokMainPage3.jsp"; // 웹 사이트의 메인 페이지 URL 변경
      } else {
        console.error("Error deleting empty room:", data.message);
      }
    },
    error: function (xhr, status, error) {
      console.error("Error deleting empty room:", error);
    },
  });
}

   
});
