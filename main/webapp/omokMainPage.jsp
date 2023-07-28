<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
body {
	background-color: white;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.main-content {
	display: flex;
	width:80%;
	max-width: 90%; /* 이 값을 변경시 중앙 내용 섹션의 최대 너비. */
}

#box {
	background-color: #d2d2d2;
	max-width: 1064px;
	width:50%;
	min-height: 0px;
	top: 40px;
	padding: 10px;
}

#nav {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	width: 300px;
	height: 30px;
	margin-left: 30px;
}

.btn {
	background-color: #F8F8F8;
	border: 1px solid gray;
	border-radius: 2px;
	width: 100px;
	height: 20px;
	text-align: center;
	font-size: 10px;
	line-height: 20px;
	cursor: pointer;
}

.btn2 {
	border-radius: 1px;
	width: 100px;
	height: 20px;
	text-align: center;
	font-size: 10px;
	line-height: 20px;
}

#toggleBtn1 {
	background-color: coral;
	margin-left: 20px;
}

#toggleBtn2 {
	background-color: #FFFFFF;
}

#btn {
	background-color: white;
	width: 200px;
	height: 200px;
}

#btn:hover {
	background-color: #ECECEC;
	cursor: pointer;
}

#mainBox {
	width: 1062px;
	height: 500px;
	margin: 1px;
	display: grid;
	grid-template-columns: 2fr 1fr;
	grid-template-rows: 1fr;
}

#listBox {
	background-color: white;
	width: 550px;
	height: 650px;
	grid-row-start: 1;
	grid-row-end: 2;
	grid-column-start: 1;
	grid-column-end: 2;
	overflow-y: scroll;
}

#ul1 {
	height: 1000px;
	list-style-type: none;
}

#ul2 {
	height: 1000px;
	list-style-type: none;
}

#userBox {
	background-color: white;
	min-width: 300px;
	padding-top:15px;
	width: 150px;
	height: 650px;
	grid-row-start: 1;
	grid-row-end: 2;
	grid-column-start: 2;
	grid-column-end: 3;
	overflow-y: scroll;
	display: flex;
	flex-direction: column;
	justify-content: start;
	align-items: center;
}

.list1 {
	background-color: white;
	font-size: 10px;
	width: 100%;
	border-bottom: 1px solid gray;
}

.gameRoomList {
	background-color: white;
	width: 100%;
	height: 35px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.2);
	display: flex;
	flex-direction: row;
	justify-content: space-evenly;
	align-items: top;
	padding-top: 2px;
	cursor: pointer;
}

.gameRoomList:hover {
	background-color: #ECECEC;
}

.user2 {
	display: flex;
	height: 25px;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.userText {
	font-size: 13px;
}

.userName {
	font-size: 14px;
	font-weight: bold;
}

.userBtn {
	background-color: #D2DFF0;
	width: 25px;
	height: 10px;
	font-size: 10px;
	border-radius: 3px;
	text-align: center;
	line-height: 10px;
}

.userlist {
	display: flex;
	width: 100%;
	height: 20px;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
	cursor: pointer;
}

.userlist:hover {
	background-color: #ECECEC;
}

#chatMainbox {
	position:relative;
	background-color: #d2d2d2;
	margin-left:300px;
	width:490px;
	height:100vh;
}

.chat {
	position:absolute;
	top:45%;
	left:50%;
	transform:translate(-50%, -50%);
	height: 610px;
	width: 350px;
	background-color: #f8f8f8;
	display: flex;
	flex-direction: column;
	padding: 20px;
	margin-top: 36px;
	overflow-y: scroll;
	gap: 8px;
}

.chat-window {
	position: relative;
	height: calc(100% - 10px);
}

.chat-message {
	display: flex;
	align-items: center;
	gap: 8px;
	margin-bottom: 8px;
}

.chat-message img {
	width: 40px;
	height: 40px;
	border-radius: 50%;
}

.chat-message .text {
	background-color: #eee;
	padding: 8px;
	border-radius: 16px;
	font-size: 14px;
}

.chat-message.other-message {
	flex-direction: row;
}

.chat-message.my-message {
	flex-direction: row-reverse;
}

.input-container {
	position: absolute;
	bottom: 0;
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: #f8f8f8;
	padding: 8px;
}

#message-input {
	flex-grow: 1;
	border-radius: 8px;
	border: 1px solid #ccc;
	padding: 8px;
}

#send-button {
	background-color: coral;
	border: none;
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 8px;
}

.modal {
	display: none;
	position: fixed;
	z-index: 2;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
	background-color: white;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

.modal-header {
	padding: 2px 16px;
	background-color: coral;
	color: white;
}

.modal-body {
	padding: 2px 16px;
}

.modal-footer {
	padding: 2px 16px;
}

.modal-close {
	color: white;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.modal-close:hover, .modal-close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="main-content">
		<div id="box">
			<div id="nav">
				<div class="btn">방만들기</div>
				<div class="btn">#100...</div>
				<div id="toggleBtn1" class="btn2">방 목록</div>
				<div id="toggleBtn2" class="btn2">내정보</div>
			</div>
			<!-- Create Room Modal -->
			<div id="createRoomModal" class="modal">
				<div class="modal-content">
					<div class="modal-header">
						<span class="modal-close">&times;</span>
						<h2>방만들기</h2>
					</div>
					<div class="modal-body">
						<label for="roomTitle">방 제목: </label> <input type="text"
							id="roomTitleInput" placeholder="방 제목을 입력하세요"> <br /> <label
							for="password">비밀번호: </label> <input type="password"
							id="password" placeholder="비밀번호를 입력하세요" /> <br /> <label
							for="gameType">게임 유형: </label> <select id="gameType">
							<option value="regular_omok">일반오목</option>
							<option value="speed_omok">스피드오목</option>
						</select>
					</div>
					<div class="modal-footer">
						<button id="createRoomButton">방만들기</button>
						<a id="roomLink" href="omokgame.jsp">게임방으로 이동</a>
					</div>
				</div>
			</div>

			<div id="mainBox">
				<div id="listBox">
					<div class="gameRoomList">
						<span class="userText">#102</span> <span class="userText">2분</span>
						<div class="user2">
							<span class="userName" name="user1">cnp3001g</span> <span
								class="userName" name="user2">silver8532</span>
						</div>
						<div class="userBtn">>></div>
					</div>
					<div class="gameRoomList">
						<span class="userText">#102</span> <span class="userText">2분</span>
						<div class="user2">
							<span class="userName" name="user1">orange1876</span> <span
								class="userName" name="user2">blue4992</span>
						</div>
						<div class="userBtn">>></div>
					</div>
					<div class="gameRoomList">
						<span class="userText">#102</span> <span class="userText">2분</span>
						<div class="user2">
							<span class="userName" name="user1">green7864</span> <span
								class="userName" name="user2">red3587</span>
						</div>
						<div class="userBtn">>></div>
					</div>
					<div class="gameRoomList">
						<span class="userText">#102</span> <span class="userText">2분</span>
						<div class="user2">
							<span class="userName" name="user1">purple5314</span> <span
								class="userName" name="user2">yellow2448</span>
						</div>
						<div class="userBtn">>></div>
					</div>
					<div class="gameRoomList">
						<span class="userText">#102</span> <span class="userText">2분</span>
						<div class="user2">
							<span class="userName" name="user1">black9110</span> <span
								class="userName" name="user2">pink6758</span>
						</div>
						<div class="userBtn">>></div>
					</div>
				</div>
				<div id="userBox">
					<div class="userlist">
						<span class="userText">cnp3001g</span>
						<div class="user4">
							<span class="userText">5749</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">silver8532</span>
						<div class="user4">
							<span class="userText">9620</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">orange1876</span>
						<div class="user4">
							<span class="userText">2348</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">blue4992</span>
						<div class="user4">
							<span class="userText">8791</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">green7864</span>
						<div class="user4">
							<span class="userText">4512</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">red3587</span>
						<div class="user4">
							<span class="userText">7743</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">purple5314</span>
						<div class="user4">
							<span class="userText">1096</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">yellow2448</span>
						<div class="user4">
							<span class="userText">6864</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">black9110</span>
						<div class="user4">
							<span class="userText">3138</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">pink6758</span>
						<div class="user4">
							<span class="userText">5259</span> <span class="userText">#</span>
						</div>
					</div>
					<div class="userlist">
						<span class="userText">white2931</span>
						<div class="user4">
							<span class="userText">2931</span> <span class="userText">#</span>
						</div>
					</div>
				</div>
				<div id="userProfile" style="display: none">
					<h1>User Game Info</h1>
					<!-- 실제로 데이터를 출력하는 부분은 여기에 작성 -->
				</div>
			</div>
		</div>
		<div id="chatMainbox">
				<div class="chat">
					<div class="live-viewers">Live 채팅방</div>
					<div class="chat-window" id="chat-window">
						<div class="chat-message other-message">
							<img src="" alt="">
							<div class="text">오목 할사람</div>
						</div>
						<div class="chat-message other-message">
							<img src="" alt="">
							<div class="text">응 안해</div>
						</div>
						<div class="chat-message other-message">
							<img src="" alt="">
							<div class="text">너나 해</div>
						</div>
						<div class="chat-message other-message">
							<img src="" alt="">
							<div class="text">ㄱㄱ</div>
						</div>
						<div class="chat-message my-message">
							<img src="" alt="">
							<div class="text">ㅎㅇ</div>
						</div>
						<div class="input-container">
							<input type="text" id="message-input"
								placeholder="메세지를 입력해주세요..." />
							<button id="send-button">전송</button>
						</div>
					</div>
				</div>
		</div>
	</div>
	<script src="js/omokmainpage.js" type="text/javascript"></script>
</body>
</html>