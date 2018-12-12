//package com.wl.websocketDNC;
//
//import java.io.IOException;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//import org.apache.catalina.websocket.StreamInbound;
//
////@ServerEndpoint(value = "/websocket")
////public class WebSocketTest {
////
////	@OnMessage
////    public void onMessage(String message, Session session) 
////    	throws IOException, InterruptedException {
////		
////		// Print the client message for testing purposes
////		System.out.println("Received: " + message);
////		
////		// Send the first message to the client
////		session.getBasicRemote().sendText("This is the first server message");
////		
////		// Send 3 messages to the client every 5 seconds
////		int sentMessages = 0;
////		while(sentMessages < 3){
////			Thread.sleep(5000);
////			session.getBasicRemote().
////				sendText("This is an intermediate server message. Count: " 
////					+ sentMessages);
////			sentMessages++;
////		}
////		
////		// Send a final message to the client
////		session.getBasicRemote().sendText("This is the last server message");
////    }
////	
////	@OnOpen
////    public void onOpen () {
////        System.out.println("Client connected");
////    }
////
////    @OnClose
////    public void onClose () {
////    	System.out.println("Connection closed");
////    }
////}
//
////@WebServlet(urlPatterns = {"/websocket"})
//public class WebSocketTest extends org.apache.catalina.websocket.WebSocketServlet{
//
//	@Override
//	protected StreamInbound createWebSocketInbound(String arg0,HttpServletRequest arg1) {
//		// TODO Auto-generated method stub
////		System.out.println("StreamInbound");
//		return new WebSocketMessageInboundTest("Test!");
//	}
//	
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

