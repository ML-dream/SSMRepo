<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>Testing websockets</title>
<meta content="">
</head>
<body>
	<div>
		<input type="submit" value="Start" onclick="start()" />
	</div>
	<div id="messages"></div>
	<script type="text/javascript">
		var webSocket =  new WebSocket('ws://localhost:8080/WebSocket/websocket');

		webSocket.onerror = function(event) {
			onError(event)
		};

		webSocket.onopen = function(event) {
			onOpen(event)
		};

		webSocket.onmessage = function(event) {
			onMessage(event)
		};

		function onMessage(event) {
			document.getElementById('messages').innerHTML 
				+= '<br />' + event.data;
		}

		function onOpen(event) {
			document.getElementById('messages').innerHTML 
				= '连接成功!!!';
		}

		function onError(event) {
			alert(event.data);
			alert("error");
		}
		
		function start() {
			webSocket.send('hello!!!');
			return false;
		}
		
		
		
		/*
		
		objWebSocket.onmessage = function(evt) {
            // 解析数据
            var objJsonData = JSON.parse(evt.data);
            var strMethodName = objJsonData.method;
            var objData = objJsonData.data;
            // 执行相应的业务逻辑
            window[strMethodName](objData);
        };
		
		
		*/
	</script>
</body>
</html>