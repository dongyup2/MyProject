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
	fetch("/path/to/your/api") // 여기에 API 경로를 입력하세요.
		.then((response) => response.json())
		.then((userGameInfo) => {
			userProfile.innerHTML = `
                <h3>Member Number: <span>${userGameInfo.mno}</span></h3>
                <h3>Name: <span>${userGameInfo.name}</span></h3>
                <h3>ID: <span>${userGameInfo.id}</span></h3>
                <h3>Email: <span>${userGameInfo.email}</span></h3>
                <h3>regDate: <span>${userGameInfo.regDate}</span></h3>
                <h3>Game record ID: <span>${userGameInfo.record_id}</span></h3>
                <h3>Win: <span>${userGameInfo.win}</span></h3>
                <h3>Lose: <span>${userGameInfo.lose}</span></h3>
                <h3>Draw: <span>${userGameInfo.draw}</span></h3>
                <h3>Last play date: <span>${userGameInfo.play_date}</span></h3>
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

function getRandomRoomNumber() {
	return Math.floor(Math.random() * 1000);
}

function createRoomTitle(clientRoomTitle) {
	const roomNumber = getRandomRoomNumber();
	const roomTitle = `${clientRoomTitle}#${roomNumber}`;
	return roomTitle;
}
// Create Room Button 클릭 이벤트
document.getElementById("createRoomButton").addEventListener("click", () => {
	const clientRoomTitle = document.getElementById("roomTitleInput").value;
	const roomTitle = createRoomTitle(clientRoomTitle);

	const roomLinkElem = document.getElementById("roomLink");
	roomLinkElem.href = 'omokgame.jsp';
	//roomLinkElem.href = `omokGame.html?roomTitle=${encodeURIComponent(roomTitle)}`;
});

// URL에서 방 제목 가져오기
function getRoomTitleFromUrl() {
	const urlParams = new URLSearchParams(window.location.search);
	const roomTitle = urlParams.get('roomTitle');
	return roomTitle ? decodeURIComponent(roomTitle) : '';
}

// 표시할 방 제목 요소
const roomTitleElem = document.createElement('div');
roomTitleElem.innerText = getRoomTitleFromUrl();

// 오목 게임 제목 요소 바로 아래에 표시
const navElem = document.querySelector(".nav");
navElem.parentNode.insertBefore(roomTitleElem, navElem.nextSibling);

