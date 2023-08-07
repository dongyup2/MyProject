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
        url: "createRoomAndCheck",
        type: "POST",
        data: {
            roomTitle: roomTitle,
            password: password,
            gameType: gameType
        },
        dataType: "json",
        success: function(data) {
            if (data.status === 'success') {
                addGameRoomToList(data.roomId, data.roomTitle, data.gameType, data.userId, data.userName);
                modal.style.display = "none";
            } else {
                // 방 생성 실패, 에러 메시지 표시
                alert(data.message);
            }
        },
        error: function(xhr, status, error) {
            console.error('Error creating room:', error);
        }
    });
}

function addGameRoomToList(roomId, roomTitle, gameType, userId, userName) {
    const listBox = document.getElementById("listBox");

    const gameRoomElement = document.createElement("div");
    gameRoomElement.classList.add("gameRoomList");

    const roomMnoElement = document.createElement("span");
    roomMnoElement.classList.add("roomMno");
    roomMnoElement.innerText = `#${roomId}`;

    const roomTitleElement = document.createElement("span");
    roomTitleElement.classList.add("roomTitle");
    roomTitleElement.innerText = roomTitle;

    const userDivElement = document.createElement("div");
    userDivElement.classList.add("user2");

    const userNameElement1 = document.createElement("span");
    userNameElement1.classList.add("userName");
    userNameElement1.setAttribute("name", "user1");
    userNameElement1.innerText = userName;

    const userNameElement2 = document.createElement("span");
    userNameElement2.classList.add("userName");
    userNameElement2.setAttribute("name", "user2");
    // user2의 이름은 나중에 업데이트되어야 합니다.
    userNameElement2.innerText = "";

    userDivElement.appendChild(userNameElement1);
    userDivElement.appendChild(userNameElement2);

    const gameTypeElement = document.createElement("span");
    gameTypeElement.classList.add("gameType");
    gameTypeElement.innerText = gameType;

    const userBtnElement = document.createElement("div");
    userBtnElement.classList.add("userBtn");
    userBtnElement.innerText = ">>";

    gameRoomElement.appendChild(roomMnoElement);
    gameRoomElement.appendChild(roomTitleElement);
    gameRoomElement.appendChild(userDivElement);
    gameRoomElement.appendChild(gameTypeElement);
    gameRoomElement.appendChild(userBtnElement);

    listBox.appendChild(gameRoomElement);
}

//  기존에 사용하셨던 AJAX 코드를 유지하고 변경 사항이 있을 때만 업데이트하는 기능을 추가해 보았습니다.

/*function listCheck() {
    console.log("실시간 방 목록 업데이트중...");

    $.ajax({
        url: "checkEmptyRoom",
        type: "get",
        dataType: "json",
        success: function(data) {
            if (isArraysDifferent(tempVOListInView, data)) {
                // inViewList 는 현재 화면에 출력된 TempVO 리스트를 나타냅니다.
                tempVOListInView = data; // 전체 업데이트
                updateView(tempVOListInView);
            }
        }
    });
}*/

// 함수 : 두 ArrayList 비교
function isArraysDifferent(array1, array2) {
    if (array1.length !== array2.length) {
        return true;
    }
    for (var i = 0; i < array1.length; i++) {
        if (JSON.stringify(array1[i]) !== JSON.stringify(array2[i])) {
            return true;
        }
    }
    return false;
}

// DOM을 업데이트하는 함수입니다.
function updateView(tempVOList) {
    $(".gameRoomList").empty();
    $.each(tempVOList, function (index, gameRoom) {
        var gameRoomElem = '<div class="gameRoomList">';
        // 나머지 HTML 요소를 추가하세요.
        gameRoomElem += "</div>";

        $(".gameRoomList").append(gameRoomElem);
    });
}



var timer = setInterval(listCheck, 1000);

function listCheck() {
    console.log("실시간 방 목록 업데이트중...");

    $.ajax({
        url: "checkEmptyRoom",
        type: "get",
        dataType: "json",
        success: function(data) {
				console.log(data);
            $('.gameRoomList').remove();
            $.each(data, function(index, gameRoom) {
                var gameRoomElem = '<div class="gameRoomList">' +
                    '<span class="roomMno">' + gameRoom.roomMno + '</span>' +
                    '<span class="roomTitle">' + gameRoom.roomTitle + '</span>' +
                    '<div class="user2">' +
                    '<span class="userName" name="user1">' + gameRoom.user1 + '</span>' +
                    '<span class="userName" name="user2">' + gameRoom.user2 + '</span>' +
                    '</div>' +
                    '<div class="gameType"><span>' + gameRoom.gameType + '</span></div>' +
                    '<div class="userBtn">>></div>' +
                    '</div>';
                $('#listBox').append(gameRoomElem);
            });
        }
    });
}
// 오목 게임 제목 요소 바로 아래에 표시
/*const navElem = document.querySelector(".nav");
navElem.parentNode.insertBefore(roomTitleElem, navElem.nextSibling);

*/