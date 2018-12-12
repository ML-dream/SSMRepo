package DataCollection;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;

import com.wl.tools.Sqlhelper;


public  class MachineCollection implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		
		System.out.println("数据借宿采集");		
	}

	public void contextInitialized(ServletContextEvent sce) {

		new Thread(new QueryListener()).start();
		new Thread(new dataInsert2()).start();
		System.out.println("数据开始采集…………");
	}
	private class dataInsert2 implements Runnable{

		public void run() {
			
				 System.out.println("数据采集主线程开始启动…………");
				
				 String host = "192.168.11.190";  
				 String domain = "192.168.11.190"; //SIEMENS-789BE42 SIEMENS-789BE42 SIEMENS-789BE42(this computer)
				 String progId = "OPC.SINUMERIK.Machineswitch";  
				 String user = "auduser";  
				 String password = "SUNRISE"; 
				/* String host = "192.168.11.190";  
				 String domain = "192.168.11.190"; //SIEMENS-789BE42 SIEMENS-789BE42 SIEMENS-789BE42(this computer)
				 String progId = "OPC.SINUMERIK.Machineswitch";*/
				 //ServerList serverList = new ServerList(host,user,password,domain);  
				 System.out.println("111111111111111111111111111111");
				 //showAllOPCServer(serverList); 
				 
//				 String host = "192.168.11.126";  
//				 String domain = "192.168.11.126"; //SIEMENS-789BE42 
//				 String progId = "Knight.OPC.Server.Demo";  
//				 String user = "auduser";  
//				 String password = "SUNRISE"; 
				 
				 final ConnectionInformation ci = new ConnectionInformation();  
				 ci.setHost(host);  
//		    	 ci.setClsid("B57C679B-665D-4BB0-9848-C5F2C4A6A280");  //serverList.getClsIdFromProgId (progId)
			    // System.out.println("11111111111"+serverList.getClsIdFromProgId (progId));
		    	 ci.setClsid("75d00afe-dda5-11d1-b944-9e614d000000");
		    	 ci.setDomain(domain);
				 ci.setUser(user);  
				 ci.setPassword(password);
				 
				 ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();  
				 Server server = new Server(ci, exec);  
				 try{
					 server.connect();
				     syncWrite2(server);}catch(Exception e ){
				 
		    }
			
		}
		
		
	}
	private class QueryListener implements Runnable{

		public void run() {
			 System.out.println("数据采集主线程开始启动…………");
			
			 String host = "192.168.11.190";  
			 String domain = "192.168.11.190"; //SIEMENS-789BE42 SIEMENS-789BE42 SIEMENS-789BE42(this computer)
			 String progId = "OPC.SINUMERIK.Machineswitch";  
			 String user = "auduser";  
			 String password = "SUNRISE"; 
//			 String host = "192.168.11.190";  
//			 String domain = "192.168.11.190"; //SIEMENS-789BE42 SIEMENS-789BE42 SIEMENS-789BE42(this computer)
//			 String progId = "OPC.SINUMERIK.Machineswitch";
			 //ServerList serverList = new ServerList(host,user,password,domain);  
			 System.out.println("111111111111111111111111111111");
			 //showAllOPCServer(serverList); 
			 
//			 String host = "192.168.11.126";  
//			 String domain = "192.168.11.126"; //SIEMENS-789BE42 
//			 String progId = "Knight.OPC.Server.Demo";  
//			 String user = "auduser";  
//			 String password = "SUNRISE"; 
			 
			 final ConnectionInformation ci = new ConnectionInformation();  
			 ci.setHost(host);  
//	    	 ci.setClsid("B57C679B-665D-4BB0-9848-C5F2C4A6A280");  //serverList.getClsIdFromProgId (progId)
//		     System.out.println("11111111111"+serverList.getClsIdFromProgId (progId));
	    	 ci.setClsid("75d00afe-dda5-11d1-b944-9e614d000000");
	    	 ci.setDomain(domain);
			 ci.setUser(user);  
			 ci.setPassword(password);
			 
			 ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();  
			 Server server = new Server(ci, exec);  
			 try{
				 server.connect();
			     syncWrite1(server);}catch(Exception e ){
			 }
	    }
	}
	
	public static void syncWrite2(Server server) throws Exception{
		final String itemId1="/Channel/MachineAxis/aavactB[1]";  
	    final String itemId2="/Channel/MachineAxis/actToolBasePos[u1,1]"; 
	    final String itemId3="/Bag/State/opMode[u1]"; 
//		final String itemId1="a.a.g";  
//	    final String itemId2="a.a.a"; 
//	    final String itemId3="a.a.h"; 
	    
	    
	    Group group = server.addGroup("test");  
	    Item item1 = group.addItem(itemId1);
	    Item item2 = group.addItem(itemId2); 
	    Item item3 = group.addItem(itemId3); 
	      
	    //第一次读  
	    while(true){
	    	  ItemState itemState1 = item1.read(true); 
	    	  ItemState itemState2 = item2.read(true); 
	    	  ItemState itemState3 = item3.read(true); 
	    	  
	  	    System.out.println("<<< first read: " + itemState1.getValue()); 
	  	    System.out.println("<<< first read: " + itemState2.getValue()); 
	  	    System.out.println("<<< first read: " + itemState3.getValue()); 
	  	    Thread.sleep(10000);
	  	    int ddd = itemState2.getValue().toString().length();
	  	    String aaa = itemState1.getValue().toString().substring(2,itemState1.getValue().toString().length()-2);
	  	    String aab = itemState2.getValue().toString().substring(2,itemState2.getValue().toString().length()-2);
	  	    //String Sql1="insert into DATACOLLECTION(X_AXIS_FEED_SPEED,X_AXIS_COORDINATES) values('"+aaa+"','"+aab+"')";
	  	    String Sql2="INSERT INTO DATACOLLECTION01 "
	  	    		+ "( X_AXIS_FEED_SPEED,X_AXIS_COORDINATES,tydate)"
	  	    		+ " VALUES ("+aaa+",'"+aab+"',sysdate)";
	  	    try{
	  	    	Sqlhelper.executeUpdate(Sql2, null);
			}catch(Exception e){
				System.out.println("未知错误哈哈哈");
			}
			System.out.println("更新成功");
	    }		
	 }
	    
	
	public static void syncWrite1(Server server) throws Exception{  
	    final String itemId1="/Channel/MachineAxis/aavactB[1]";  
	    final String itemId2="/Channel/MachineAxis/actToolBasePos[u1,1]"; 
	    final String itemId3="/Bag/State/opMode[u1]"; 
//		final String itemId1="a.a.g";  
//	    final String itemId2="a.a.a"; 
//	    final String itemId3="a.a.h"; 
	    
	    
	    Group group = server.addGroup("test");  
	    Item item1 = group.addItem(itemId1);
	    Item item2 = group.addItem(itemId2); 
	    Item item3 = group.addItem(itemId3); 
	      
	    //第一次读  
	    while(true){
	    	  ItemState itemState1 = item1.read(true); 
	    	  ItemState itemState2 = item2.read(true); 
	    	  ItemState itemState3 = item3.read(true); 
	    	  
	  	    System.out.println("<<< first read: " + itemState1.getValue()); 
	  	    System.out.println("<<< first read: " + itemState2.getValue()); 
	  	    System.out.println("<<< first read: " + itemState3.getValue()); 
	  	    Thread.sleep(1000);
	  	    int ddd = itemState2.getValue().toString().length();
	  	    String aaa = itemState1.getValue().toString().substring(2,itemState1.getValue().toString().length()-2);
	  	    String aab = itemState2.getValue().toString().substring(2,itemState2.getValue().toString().length()-2);
	  	    //String Sql1="insert into DATACOLLECTION(X_AXIS_FEED_SPEED,X_AXIS_COORDINATES) values('"+aaa+"','"+aab+"')";
	  	    String Sql1="update DATACOLLECTION SET X_AXIS_FEED_SPEED='"+aaa+"',X_AXIS_COORDINATES='"+aab+"'WHERE ID=1";
	  	    try{
	  	    	Sqlhelper.executeUpdate(Sql1, null);
			}catch(Exception e){
				System.out.println("未知错误哈哈哈");
			}
			System.out.println("更新成功");
			  
			
			
			//查询已经插入的条数
			int totalCount = 0;
			String sql2 = "select count(*) from DATACOLLECTION";
			try {
				 totalCount = Sqlhelper.exeQueryCountNum(sql2, null);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (totalCount >100)
			{
				String sql3 = "";
			}
			
				
			
	  	  
	    }
	}
}
	
