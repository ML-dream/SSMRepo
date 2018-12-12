package com.wl.listeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wl.tools.Sqlhelper;

import cfmes.util.sql_data;
public class SeverAndroid implements ServletContextListener {
	private ServerSocket serverSocket;
	private boolean isQuit;

	public void contextDestroyed(ServletContextEvent sce) {
		isQuit=true;
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("tomcat shutdown******");		
	}

	public void contextInitialized(ServletContextEvent sce) {

		SeverAndroid.this.isQuit=false;
		try {
			int port = 8899;
			serverSocket=new ServerSocket(port);						 
			new Thread(new QueryListener()).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("tomcat startup*******");
	}

	private class QueryListener implements Runnable{

		public void run() {
			// TODO Auto-generated method stub
			System.out.println("服务器主线程启动");
			try {
				if(serverSocket!=null)
					System.out.println("server socket ok");
				while(!SeverAndroid.this.isQuit){
					System.out.println("服务器已就绪");
					Socket clientSocket=serverSocket.accept();
					new HandleQuery(clientSocket).start();
					System.out.println("子线程已启动");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	private class HandleQuery extends Thread  {
		private Socket socket;
		public HandleQuery(Socket socket) {
			super();
			this.socket = socket;
		}		
		@Override
		public void run() {
			
//			sql_data sdData=new sql_data();
//			
//			Writer writer=null;
//			ResultSet rs=null;
//			
//			String oper_id="";
//			String quality_id="";
		
			try {
				BufferedReader br=null;
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
				StringBuilder sb = new StringBuilder();  
				String temp;  
				int index;  
				while ((temp=br.readLine()) != null) {  
					System.out.println(temp);  
					if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
						sb.append(temp.substring(0, 15));  
						break;
					}  
					
					sb.append(temp);  
				}  
				System.out.println("from client: " + sb); 
				

				String rfid1=socket.getInetAddress().toString();
//				String rfid2=socket.getInetAddress().toString();
				rfid1=rfid1.substring(1); 
//				rfid2=rfid2.substring(1); 
				
//				sdData=new sql_data();
//				sdData.getConn();
//				String sql="select t.barcode,t.order_id orderId,t.companyname companyName,t.drawingid productId,t.product_name productName,rownum rn from PO_ROUTER t " +
//						"where t.barcode='"+sb+"'";
				String barCodeSql="insert into RFIDBADCODE(RFIDCODE,IP) values('"+sb+"','"+rfid1+"')";
				try{
					Sqlhelper.executeUpdate(barCodeSql, null);
				}catch(Exception e){
					System.out.println("违反唯一约束");
				}
				System.out.println("11111"+barCodeSql);
				
				/*String sql = "select oper_id,quality_id from work.process_temp where " +
						"wcid=(select machineid from work.machinfo where RFID1='"
				         + rfid1+ "' or RFID2='"+ rfid2+ "') and item_id='"+sb+"'";
			System.out.println("11111"+sql);*/

//				try {
//					rs = sdData.executeQuery(sql);
//					while(rs.next()){
//						
//						System.out.println("11111"+sql);
//						oper_id=rs.getString("oper_id");//获取当前工序号
//		//				quality_id=rs.getString("quality_id");
//						System.out.println("当前工序："+oper_id);
//			//			System.out.println("当前工序质检结果："+quality_id);
//					}
					
					//实际开工时间
//	  			    String sql2="update work.process_temp set start_time=to_date
//					('"+new Date().toLocaleString()+"','yyyy-mm-dd HH24:MI:SS') 
//					where item_id='sx123' and oper_id='10'";
//					String sql2="update work.process_temp set start_time=to_date
//					('"+new Date().toLocaleString()+"','yyyy-mm-dd HH24:MI:SS') 
//					where wcid=(select machineid from work.machinfo where RFID1='"
//						+ rfid1+ "') and item_id='"+sb+"'";
//					String sql2="update work.process_temp set start_time=" +
//							"to_date((select to_char(sysdate,'yyyy-mm-dd HH24:MI:SS')" +
//							"from dual),'yyyy-mm-dd HH24:MI:SS') where wcid=(select" +
//							" machineid from work.machinfo where RFID1='"+ rfid1+ "') " +
//									"and item_id='"+sb+"'";					
//					int s=sdData.executeUpdate(sql2);
//					System.out.println("22222"+sql2);
//					//实际完工时间
//					String sql3="update work.process_temp set end_time=" +
//					"to_date((select to_char(sysdate+1/24,'yyyy-mm-dd HH24:MI:SS')" +
//					"from dual),'yyyy-mm-dd HH24:MI:SS') where wcid=(select" +
//					" machineid from work.machinfo where RFID2='"+ rfid2+ "') " +
//							"and item_id='"+sb+"'";
//					 s=sdData.executeUpdate(sql3);
//					System.out.println("33333"+sql3);
					
				//	sql="select is_accept from work.quality_info where quality_id='" +quality_id+"'";					
//					rs = sdData.executeQuery(sql);
//					System.out.println("44444"+sql);
//					rs.next();
//					String is_accept=rs.getString("is_accept");//获取当前工序质量检验结果
//					System.out.println(is_accept);
					
					/** 在数据库中直接更新列值(完成个数、合格个数、不合格个数)增1**/
//					sql="update work.process_temp set finishnum=(select " +
//							"finishnum+1 from process_temp where oper_id='"
//					+oper_id+"' and product_id="
//					+"(select product_id from work.item where item_id='"
//				  +sb+"') and item_id='"+sb+"') where oper_id='"+oper_id+"' and " +
//   "product_id=(select product_id from work.item where item_id='"+sb+"') and item_id='"+sb+"'";
//					 System.out.println("666"+sql);
//					 s=sdData.executeUpdate(sql);
//					 System.out.println("666"+sql);
					
//					String rfid1_new = "";
//					String rfid2_new = "";
//					sql="select rfid1,rfid2 from machinfo where RFID1='"+rfid1+ "' or RFID2='"+ rfid2+ "'";
//					System.out.println(sql);
//					rs = sdData.executeQuery(sql);
//					rs.next();
//					System.out.println("rfid1_new"+rfid1_new);
//					rfid1_new=rs.getString("rfid1");
//					rfid2_new=rs.getString("rfid2");
//					
//					if(rfid2_new.equals(rfid2)){	
//					sql="update work.process_temp set finishnum=(select " +
//							"finishnum+1 from process_temp where oper_id='"
//					+oper_id+"' and product_id="
//					+"(select product_id from work.item where item_id='"
//					+sb+"') and item_id='"+sb+"') where oper_id='"+oper_id+"' and " +
//				  	"product_id=(select product_id from work.item where item_id='"+sb+"') and item_id='"+sb+"'";
//					 System.out.println("666"+sql);
//					 s=sdData.executeUpdate(sql);
//					 System.out.println("666"+sql);
//					}
					
					
					 
//					if(is_accept.equals("合格")){
//						/**在java中更改变量再存入数据库  
//						sql="select finishnum from work.process_plan where oper_id='"
//						+oper_id+"' and product_id=(select product_id from " +
//								"work.item where item_id='"+sb+"')";
//						rs = sdData.executeQuery(sql);
//						rs.next();
//						int finishnum=rs.getInt("finishnum");					
//						finishnum=finishnum+1;
//						System.out.println("555555:::"+finishnum);									
//						sql="update work.process_plan set finishnum='"+finishnum+
//								"' where oper_id='"+oper_id+"' and product_id=(select product_id from " +
//								"work.item where item_id='"+sb+"')";
//		             **/									 												
//						 sql="update work.process_temp set pass_num=(select " +
//							"pass_num+1 from work.process_temp where oper_id='"
//					+oper_id+"' and product_id=(select product_id from " +
//							"work.item where item_id='"+sb+"') and item_id='"+sb+"') where oper_id='"
//					+oper_id+"' and product_id=(select product_id from " +
//							"work.item where item_id='"+sb+"') and item_id='"+sb+"'";
//				System.out.println("777"+sql);
//					 s=sdData.executeUpdate(sql);
//					 System.out.println("777"+sql);						
//					}	
//					if(is_accept.equals("不合格")){
//						sql="update work.process_temp set failure_num=(select " +
//						"failure_num+1 from work.process_temp where oper_id='"
//				+oper_id+"' and product_id=(select product_id from " +
//						"work.item where item_id='"+sb+"') and item_id='"+sb+"') where oper_id='"
//				+oper_id+"' and product_id=(select product_id from " +
//						"work.item where item_id='"+sb+"') and item_id='"+sb+"'";
//			   System.out.println("888"+sql);
//				 s=sdData.executeUpdate(sql);
//				 System.out.println("888"+sql);	
//					}
										
					/**判断当前工序是否为最后一道工序，若为最后一道工序则向零件报工**/
					
//					sql="select max(to_number(oper_id))  from work.process_temp where item_id='"+sb+"'";
//					System.out.println(sql);
//					rs = sdData.executeQuery(sql);
//					rs.next();
//					int lastOper=rs.getInt("max(to_number(oper_id))");
//					System.out.println(lastOper);
//					if(Integer.parseInt(oper_id)==lastOper){
//						System.out.println("零件报工");
//						sql = "select quality_id from work.part_temp where item_id='"+sb+"'";
//						rs = sdData.executeQuery(sql);
//						rs.next();
//						quality_id=rs.getString("quality_id");
//						System.out.println(quality_id);
						
//						sql="select is_accept from work.quality_part where quality_id='" +quality_id+"'";
//						rs = sdData.executeQuery(sql);
//						rs.next();
//						is_accept=rs.getString("is_accept");
//						System.out.println("零件：：："+is_accept);
						
//						sql="update work.part_temp set finish_num=(select " +
//								"finish_num+1 from work.part_temp where product_id=" +
//								"(select product_id from work.item where item_id='"
//			  +sb+"') and item_id='"+sb+"') where product_id=(select product_id from work.item where item_id='"+sb+"') and item_id='"+sb+"'";						
//				 System.out.println("99999"+sql);
//				 s=sdData.executeUpdate(sql);
//				 System.out.println("99999"+sql);
//						if(is_accept.equals("合格")){
//							sql="update work.part_temp set pass_num=(select " +
//							"pass_num+1 from work.part_temp where product_id=" +
//								"(select product_id from work.item where item_id='"
//			  +sb+"') and item_id='"+sb+"') where product_id=(select product_id from work.item where item_id='"+sb+"') and item_id='"+sb+"'";	
//				
//					 s=sdData.executeUpdate(sql);	
//					 System.out.println("121212"+sql);		
//						}
//						if(is_accept.equals("不合格")){
//							sql="update work.part_temp set failure_num=(select " +
//							"failure_num+1 from work.part_temp where product_id=" +
//								"(select product_id from work.item where item_id='"
//			  +sb+"') and item_id='"+sb+"') where product_id=(select product_id from work.item where item_id='"+sb+"') and item_id='"+sb+"'";	
//				
//					 s=sdData.executeUpdate(sql);	
//					 System.out.println("131313"+sql);		
//						}
//					}
					
					/**记录零件实际加工时间、检验等信息，并完成零件报工**/
					
					//读完后写一句  
//					writer = new OutputStreamWriter(socket.getOutputStream());  
//					writer.write("Hello Client."+oper_id);  
//					writer.write("eof\n");  
//					writer.flush(); 
//					
//					writer.close();  
//					br.close();  
//																			
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally{
//				try {
//					if (rs != null)
//						rs.close();
//					if (writer != null)
//						writer.close();
//					if (br != null)
//						br.close();
//					sdData.closeConn();
//					socket.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
