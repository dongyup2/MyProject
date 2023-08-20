<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>connTest</title>
<script src="js/jquery-3.7.0.js"></script>
<style>
	#bigBox{
		width: 100%;
		height: 1000px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}
	.smallBox {
		width: 100px;
		height: 600px;
		border: 5px solid black;
	}
</style>
</head>
<body>
	<div id="bigBox">
		<div class="smallBox">
			<h1 id="name1">나</h1>
			<div></div>
		</div>
		<div class="smallBox">
			<h1 id="name2">상대방</h1>
			<div></div>
		</div>
	</div>
</body>
<script>
	roomInfo();
	var timer = setInterval(check, 1000);
	var timer2 = setInterval(turnCheck, 1000);

	function roomInfo() {
		$.ajax({
			url: "ConnTest",
			type: "get",
			data: {
				run : "roomInfo"
			},
			dataType: "text",
			success: function(data) {
				myData = JSON.parse(data);
				console.log(myData);
				if (myData.pno2 != 0) {
					$("#name1").html(myData.name1);
					$("#name2").html(myData.name2);
					clearInterval(timer);
				} else if (myData.pno1 != 0) {
					$("#name1").html(myData.name1);
					$("#name2").html("빈 방");
				} 
			}
		})
	}
	
	function check() {
		console.log("상대방 기다리는중....");
		$.ajax({
			url: "ConnTest",
			type: "get",
			data: {
				run : "check"
			},
			dataType: "text",
			success: function(data) {
				if(data != 0) {
					myData = JSON.parse(data);
					$("#name1").html(myData.name1);
					$("#name2").html(myData.name2);
					clearInterval(timer);
					var startBtn = document.createElement("button");
					startBtn.innerHTML = "게임 시작!";
					$("#bigBox").append(startBtn);
				}
			}
		})
	}
</script>
</html>