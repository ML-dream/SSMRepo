//package com.wl.websocketDNC;
//
//import java.nio.CharBuffer;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class WebSocketMessageInboundPoolTest {
//	//保存连接的MAP容器
//	private static final Map<String, WebSocketMessageInboundTest> connections
//		= new HashMap<String, WebSocketMessageInboundTest>();
//	
//	//向连接池中添加连接
//	public static void addMessageInbound (WebSocketMessageInboundTest inbound) {
//		connections.put(inbound.getUser(), inbound);
//	}
//	
//	//获取所有在线用户
//	public static Set<String> getOnlineUser(){
//		System.out.println("getOnlineUser");
//		return connections.keySet();
//	}
//	
//	//移除连接
//	public static void removeMessageInbound(WebSocketMessageInboundTest inbound){
//		System.out.println("用户:"+inbound.getUser()+"退出。。。。");
//		connections.remove(inbound.getUser());
//	}
//	
//	//向特定用户发送数据
//	@SuppressWarnings("deprecation")
//	public static void sendMessageToUser(String user, String message){
//		try {
//			System.out.println("向用户："+user+"发送信息："+message);
//			WebSocketMessageInboundTest inbound = connections.get(user);
//			if (inbound != null) {
//				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	//向所有用户发送消息
//	@SuppressWarnings("deprecation")
//	public static void sendMessage (String message){
//		
//		System.out.println("发送信息！！！！"+"   "+message);
//		try {
//			Set<String> keySet = connections.keySet();
//			
//			for (String key : keySet) {
//				WebSocketMessageInboundTest inbound = connections.get(key);
//				if (inbound != null) {
//					
//					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//timer.scheduleAtFixedRate(MyTask(message), 1000, 2000);
//
//	}
//	
//	
//	//向所有用户发送消息
//	public static void sendMessage (){
//		
//		System.out.println("发送无参数信息！！！！");
//		Timer timer = new Timer();  
//		timer.scheduleAtFixedRate(new TimerTask() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					Set<String> keySet = connections.keySet();
//					String message = getMessage();
//					for (String key : keySet) {
//						WebSocketMessageInboundTest inbound = connections.get(key);
//						
//						
//						if (inbound != null) {
//							inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
//						}
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}
//		}, 1000, 200);
//	}
//	
//	
//	//这是获取数据的方法，将获取的数据放到全局变量中，以此方便信息的发送
//	//此方法最好在连接成功的时候就调用，以获取数据
//	private static String getMessage(){
//		String message = "";
//		try {
//			message = "wanglu! "+new Random().nextInt(10);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return message;
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
//
//
//
//
//

