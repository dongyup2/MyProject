const BOARD_SIZE = 15; // 보드판 크기
const BLACK = "●"; // 흑돌
const WHITE = "○"; // 백돌
const TIMER_LIMIT = 301;//시간
const user1 = "user1"; // 실제 로그인 정보를 사용해야 함.
const user2 = "user2"; // 실제 로그인 정보를 사용해야 함.
const currentPlayerInfo = {
  [user1]: BLACK, // 유저1은 흑돌
  [user2]: WHITE // 유저2는 백돌
};
let currentUser;

let currentPlayer = BLACK; // 기본값 현재 플레이어 

let remainingTime1 = TIMER_LIMIT; // 플레이어1 남은시간
let remainingTime2 = TIMER_LIMIT; // 플레이어2 남은시간 
let intervalId1;
let intervalId2;

function startTimer() {
  if (currentPlayer === BLACK) {
    clearInterval(intervalId2);
    intervalId1 = setInterval(() => {
      updateTime("timer1", remainingTime1, intervalId1);
    }, 100);
  } else {
    clearInterval(intervalId1);
    intervalId2 = setInterval(() => {
      updateTime("timer2", remainingTime2, intervalId2);
    }, 100);
  }
}


function updateTime(timerId, remainingTime, intervalId) {
  remainingTime--;
  const timer = document.querySelector(`#${timerId}`);
  timer.textContent = `남은 시간: ${remainingTime / 10}초`;

  if (remainingTime === 0) {
    clearInterval(intervalId);
    alert(`시간 초과! ${currentPlayer === BLACK ? "플레이어 2" : "플레이어 1"} 승리!`);
    resetTimer();
  }

  if (currentPlayer === BLACK) {
    remainingTime1 = remainingTime;
  } else {
    remainingTime2 = remainingTime;
  }
}


function resetTimer() {
  if (currentPlayer === BLACK) {
    clearInterval(intervalId1);
    remainingTime1 = TIMER_LIMIT;
  } else {
    clearInterval(intervalId2);
    remainingTime2 = TIMER_LIMIT;
  }
  startTimer();
}


const board = Array.from({ length: BOARD_SIZE }, () => Array(BOARD_SIZE).fill(0));

function isValidCoordinate(row, col) {//현재 좌표(row, col)가 게임판 내에 있는지 검사하는 로직 
  return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
}

function checkPattern(row, col, dx, dy) {//특정 방향(dx, dy)으로 같은 돌이 몇 개인지 카운트 하는 로직
  let count = 0; 
  let x = row,
      y = col;
  let curPlayer = board[row][col]; 
  if (curPlayer === 0) return 0;

  while (isValidCoordinate(x, y) && board[x][y] === curPlayer) {
    count++;
    x += dx;
    y += dy;
  }
  return count;
}

function isValidMove(row, col) {//현재 좌표(row, col)에 플레이어가 착수할 수 있는지 확인하는 로직
  return isValidCoordinate(row, col) && board[row][col] === 0;
}

function makeMove(row, col) {//현재 플레이어의 돌을 게임판에 놓는 로직
  board[row][col] = currentPlayer;
}

function isGameOver() {// 게임이 종료되었는지, 즉 연속 5개의 돌이 놓였는지 검사(33,44,6목 x)
  for (let row = 0; row < BOARD_SIZE; row++) { //row 가로줄(열), col 세로줄(행) 
    for (let col = 0; col < BOARD_SIZE; col++) {
      if (board[row][col] !== 0) {
        for (let dx = -1; dx <= 1; dx++) {
          for (let dy = -1; dy <= 1; dy++) {
            if (dx === 0 && dy === 0) continue;
            let count = checkPattern(row, col, dx, dy);
			//게임판의 모든 위치와 8 방향( ↗ ↙ ↖ ↘ → ← ↑ ↓) 검사를 통해 5개 이상의 돌이 연속되어 있는지 확인
            if (count >= 5) {//5개 이상일경우 true 리턴
              return true;
            }
          }
        }
      }
    }
  }
  return false;
}
function onUserEnter() {
  currentUser = user1; // 실제 로그인 정보를 사용해야 함.
  currentPlayer = currentPlayerInfo[currentUser];
}
function handleCellClick(row, col) {
  if (isValidMove(row, col)) {
    makeMove(row, col);
    const cell = document.querySelector(`.board__row:nth-child(${row + 1}) .board__col:nth-child(${col + 1}) .col__grid`);
     
	  if (currentPlayer === BLACK) {
		  cell.classList.add('black');
	  } else {
		  cell.classList.add('white');  
	  }

    if (isGameOver()) {
      clearInterval(intervalId1);
      clearInterval(intervalId2);
      alert(`Player ${currentPlayer === BLACK ? "1" : "2"} wins!`);
    }
    currentPlayer = currentPlayer === BLACK ? WHITE : BLACK;
    document.getElementById("currentTurn").innerText = `Player ${currentPlayer === BLACK ? "1" : "2"}'s Turn`;
    resetTimer();
  }
  
  // 셀 클릭 시 좌표를 서버(서블릿)로 전송
  $.ajax({
    url: '/MyWebProject/OmokGameServlet', // 전송할 URL
    type: 'POST',
    data: { row: row, col: col }, // 전송할 데이터
    success: function (response) {
      console.log(row,col)
    },
    error: function (error) {
      // 서버에서 오류가 발생한 경우 처리
      console.error("Ajax 요청 중 오류 발생:", error);
    }
  });
}



function createBoard() {
  const board = document.querySelector(".board");//.board 클래스를 가진 HTML 요소를 가져와 변수 board에 할당

  for (let i = 0; i < BOARD_SIZE; i++) {//이중 for 루프를 사용하여 각 행과 열에 대해 동작을 반복
    const boardRow = document.createElement("div");
    boardRow.classList.add("board__row");//boardRow 변수에 새 div 요소를 생성하고, 이에 .board__row 클래스를 추가.

    for (let j = 0; j < BOARD_SIZE; j++) {
      const boardCol = document.createElement("div");
      boardCol.classList.add("board__col");//boardCol 변수에 새 div 요소를 생성하고, 이에 .board__col 클래스를 추가

      const colGrid = document.createElement("div");//이 클래스는 오목판의 각 셀의 스타일과 격자 모양을 적용하는 데 사용.
      colGrid.classList.add("col__grid");
      colGrid.addEventListener("click", () => handleCellClick(i, j));
      /*colGrid 요소에 클릭 이벤트 처리를 위한 핸들러를 추가.
      이때, handleCellClick 함수에 (i, j) 좌표를 전달하며 호출.
      이를 통해 사용자가 특정 셀을 클릭할 때 어떤 동작을 수행할지 결정.*/ 

      boardCol.appendChild(colGrid); //colGrid를 boardCol 요소에 추가하고(열과 셀을 연결),
      boardRow.appendChild(boardCol);//각 boardCol 요소를 이전에 생성된 boardRow(행)에 추가합니다.                 
    }
    board.appendChild(boardRow);//이를 통해 오목판의 전체 격자 구조가 완성
  }
}
function checkIfBothPlayersEntered() {
  $.ajax({
    url: "/MyWebProject/ReadyToGameStartServlet",
    type: "GET",
    success(response) {
      const { player1, player2 } = JSON.parse(response);
      if (player1 && player2) {
        document.getElementById("start-game-button").style.display = "block";
      } else {
        setTimeout(checkIfBothPlayersEntered, 1000); // 1초마다 플레이어 입장 상태를 확인합니다.
      }
    },
    error(error) {
      console.error("플레이어 입장 상태 확인 중 오류 발생:", error);
    },
  });
}

function onUserEnter() {
  currentUser = user1; // 실제 로그인 정보를 사용해야 함.
  currentPlayer = currentPlayerInfo[currentUser];
  checkIfBothPlayersEntered();
}

function onGameStartButtonClick() {
  document.getElementById("start-game-button").disabled = true;
  startTimer();
}

document.addEventListener("DOMContentLoaded", () => {
  createBoard();
  onUserEnter();
});

