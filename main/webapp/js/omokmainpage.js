const messageInput = document.getElementById("message-input");
const sendButton = document.getElementById("send-button");
const chatWindow = document.getElementById("chat-window"); // 채팅 창 엘리먼트의 ID

function createMyMessage(text) {
	const message = document.createElement("div");
	message.classList.add("chat-message");
	message.classList.add("my-message");

	const img = document.createElement("img");
	img.src = ""; // 본인 이미지 URL 입력하면 될듯 ?
	img.alt = "";

	const msgText = document.createElement("div");
	msgText.classList.add("text");
	msgText.innerText = text;

	message.appendChild(img);
	message.appendChild(msgText);

	return message;
}

function sendMessage() {
	const messageText = messageInput.value;

	if (messageText === "") {
		return;
	}

	const message = createMyMessage(messageText);
	chatWindow.appendChild(message);

	messageInput.value = "";
	chatWindow.scrollTop = chatWindow.scrollHeight; // 스크롤을 채팅 창 맨 아래로 이동
}

sendButton.addEventListener("click", sendMessage);
messageInput.addEventListener("keydown", (event) => {
	if (event.key === "Enter") {
		sendMessage();
	}
});

const toggleBtn1 = document.getElementById("toggleBtn1");
const toggleBtn2 = document.getElementById("toggleBtn2");
const listBox = document.getElementById("listBox");
const userBox = document.getElementById("userBox");
const userProfile = document.getElementById("userProfile");

toggleBtn1.addEventListener("click", () => {
	toggleBtn1.style.backgroundColor = "coral";
	toggleBtn2.style.backgroundColor = "#FFFFFF";
	userBox.style.display = "block";
	userProfile.style.display = "none";
	listBox.style.display = "block";
});

toggleBtn2.addEventListener("click", () => {
	toggleBtn1.style.backgroundColor = "#FFFFFF";
	toggleBtn2.style.backgroundColor = "coral";
	userBox.style.display = "none";
	userProfile.style.display = "block";
	listBox.style.display = "none";

	loadUserProfile();
});
function loadUserProfile() {
    fetch("/MyWebProject/principal")
        .then((response) => response.json())
        .then((data) => {
            const user = data.user;
            const gameRecord = data.game_record;

            userProfile.innerHTML = `
                <h3>나의 번호: <span>${user.mno}</span></h3>
                <h3>이름: <span>${user.name}</span></h3>
                <h3>아이디: <span>${user.id}</span></h3>
                <h3>이메일: <span>${user.email}</span></h3>
                <h3>가입한 날짜: <span>${user.regDate}</span></h3>
                <h3>Game record ID: <span>${gameRecord.record_id}</span></h3>
                <h3>승리: <span>${gameRecord.win}</span></h3>
                <h3>패배: <span>${gameRecord.lose}</span></h3>
                <h3>무승부: <span>${gameRecord.draw}</span></h3>
                <h3>승률: <span>${gameRecord.odds}</span></h3>
                <h3>마지막 플레이 시간: <span>${gameRecord.play_date}</span></h3>
            `;
        })
        
        .catch((error) => {
            console.error("Error fetching user profile data:", error);
        });
}

const createRoomModal = document.getElementById("createRoomModal");
const createRoomBtn = document.querySelector(".btn");
const closeModalBtn = document.querySelector(".modal-close");

// 방만들기 버튼 클릭 시 모달 창 열기
createRoomBtn.onclick = () => {
	createRoomModal.style.display = "block";
};

// 모달 창 닫기 버튼 클릭 시 모달 창 닫기
closeModalBtn.onclick = () => {
	createRoomModal.style.display = "none";
};

// 모달 창 바깥쪽 클릭 시 모달 창 닫기
window.onclick = (event) => {
	if (event.target == createRoomModal) {
		createRoomModal.style.display = "none";
	}
};



const createRoomButton = document.getElementById("createRoomButton");
createRoomButton.addEventListener("click", () => {
	createRoom(createRoomModal);
});

function createRoom(modal) {
  const roomTitle = document.getElementById("roomTitleInput").value;
  const password = document.getElementById("password").value;
  const gameType = document.getElementById("gameType").value;
  
  $.ajax({
    url: "createRoom",
    type: "POST",
    data: {
      roomTitle: roomTitle,
      password: password,
      gameType: gameType
    },
    dataType: "json",
    success: function(data) {
      addGameRoomToList(data.roomTitle, data.gameType);
      modal.style.display = "none";
    },
    error: function(xhr, status, error) {
      console.error('Error creating room:', error);
    }
  });
}
function addGameRoomToList(roomTitle, gameType, id) {
  const listBox = document.getElementById("listBox");

  const gameRoomElement = document.createElement("div");
  gameRoomElement.classList.add("gameRoomList");
  gameRoomElement.setAttribute("data-room-id", id); // 게임방의 식별자를 저장합니다.

  const roomTitleElement = document.createElement("span");
  roomTitleElement.classList.add("userText");
  roomTitleElement.innerText = roomTitle;

  const gameTypeElement = document.createElement("span");
  gameTypeElement.classList.add("userText");
  gameTypeElement.innerText = gameType;

  gameRoomElement.appendChild(roomTitleElement);
  gameRoomElement.appendChild(gameTypeElement);

  gameRoomElement.addEventListener("click", () => {
    window.location.href = "omokgame.jsp?roomId=" + id;
  });

  listBox.appendChild(gameRoomElement);
}

// 오목 게임 제목 요소 바로 아래에 표시
/*const navElem = document.querySelector(".nav");
navElem.parentNode.insertBefore(roomTitleElem, navElem.nextSibling);

*/