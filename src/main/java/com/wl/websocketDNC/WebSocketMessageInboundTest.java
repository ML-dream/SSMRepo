//package com.wl.websocketDNC;
//
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.CharBuffer;
//import java.util.Random;
//
//import net.sf.json.JSONObject;
//
//import org.apache.catalina.websocket.MessageInbound;
//import org.apache.catalina.websocket.WsOutbound;
//
//import com.sun.org.apache.regexp.internal.recompile;
//
//@SuppressWarnings("deprecation")
//public class WebSocketMessageInboundTest extends MessageInbound {
//
//	private final String user;
//	public String getUser(){
//		return this.user;
//	}
//	
//	public WebSocketMessageInboundTest(String user) {
////		System.out.println("WebSocketMessageInboundTest::"+user);
//		this.user = user;
//	}
//	
//	@Override
//	protected void onOpen(WsOutbound outbound) {
////		System.out.println("onOpen");
//		try {
//			OracleDCN.test();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		WebSocketMessageInboundPoolTest.addMessageInbound(this);				//这一句相当关键，特别是this关键字！！！！
//		String message = "登录成功！！！";
//		WebSocketMessageInboundPoolTest.sendMessage(message);
//	};
//	
//	@Override
//	protected void onClose(int status) {
//		System.out.println("onClose");
//		WebSocketMessageInboundPoolTest.removeMessageInbound(this);
//	};
//	
//	
//	@Override
//	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
//		// TODO Auto-generated method stub
//		System.out.println("onBinaryMessage");
//	}
//
//	@Override
//	protected void onTextMessage(CharBuffer message) throws IOException {
//		// TODO Auto-generated method stub
//		System.out.println("onTextMessage======"+message.toString());
//		WebSocketMessageInboundPoolTest.sendMessage();
//	}
//	
//	
//}
