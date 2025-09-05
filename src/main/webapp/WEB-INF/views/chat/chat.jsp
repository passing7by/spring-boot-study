<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<h1>Chat Page</h1>
	<div>
		<form>
			<input type="text" id="msg">
			<button id="send">send</button>
		</form>
	</div>
	
	<script type="text/javascript">
		const send = document.getElementById('send');
		const msg = document.getElementById('msg');

		// WebSocket 연결
		const socket = new WebSocket("ws://192.168.1.2/chat") // ws는 웹소켓 프로토콜
		
		socket.addEventListener('open', () => {
			console.log('socket 연결 성공');
		});

		// message를 수신했을 때 실행되는 이벤트
		socket.addEventListener('message', (e) => {
			// console.log('message 수신');
			console.log(e.data);
		});

		socket.addEventListener('close', () => {
			console.log('socket 연결 해제');
		});

		socket.addEventListener('error', () => {
			console.log('에러 발생');
		});

		send.addEventListener('click', (e) => {
			e.preventDefault();
			
			let m = msg.value;
			socket.send(m);
		});
	</script>
</body>

</html>