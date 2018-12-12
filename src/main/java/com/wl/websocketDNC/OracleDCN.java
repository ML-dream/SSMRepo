//package com.wl.websocketDNC;
//import java.util.*;
//
//import javax.naming.InitialContext;
//import java.math.BigDecimal;
//import javax.sql.DataSource;
//
//import org.apache.naming.java.javaURLContextFactory;
//
//import java.sql.*;
//
//import oracle.sql.*;
//import oracle.jdbc.*;
//import oracle.jdbc.dcn.*;
//public class OracleDCN {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		try {
//			test();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	/*************************以下是建立连接*****************************/
//
//	static OracleConnection getConnection () throws SQLException{
//		OracleDriver driver = new OracleDriver();
//		Properties prop = new Properties();
//		prop.setProperty("user", OracleDCNSimple.USERNAME);
//		prop.setProperty("password", OracleDCNSimple.PASSWORD);
//		Connection coon = driver.connect(OracleDCNSimple.URL, prop);
//		OracleConnection ocoon = (OracleConnection)coon.unwrap(OracleConnection.class);
//		return ocoon;
//	}
//	
//	
//	/*************************以上是建立连接*****************************/
//	
//	
//	public static void test() throws Exception{
//		String type = "register";
//		if ("register".equals(type)) {														//这里应该是可以把判断去掉
//			Connection conn = getConnection();
//			
//			OracleConnection ocoon = (OracleConnection)conn.unwrap(OracleConnection.class);	//得到Oracle连接
//			
//			//这里应该首先注销掉以前的注册，但是此处省略了。。。。。。。。
//			
//			Properties prop = new Properties();                                             //设置属性
//			prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");					//要取得更改记录的rowid
//			prop.setProperty(OracleConnection.DCN_IGNORE_DELETEOP, "false");				//设置不忽略delete
//			prop.setProperty(OracleConnection.NTF_LOCAL_TCP_PORT, "47632");					//设置程序的tcp监听端口，默认是47632端口
//			prop.setProperty(OracleConnection.NTF_TIMEOUT,"0");								//设置超时，3600表示的是1个小时，0或者不设置表示永不关闭
//			prop.setProperty(OracleConnection.NTF_QOS_PURGE_ON_NTFN, "false");				//设置在第一次通知后，注册是否作废撤销
//			
//			DatabaseChangeRegistration dcr= ocoon.registerDatabaseChangeNotification(prop);	//注册
//			System.out.println("registerDatabaseChangeNotification, regId="+dcr.getRegId());//打印注册ID，在oracle中select * from dba_CHANGE_NOTIFICATION_REGS可以看到记录
//			try {
//				dcr.addListener(new DatabaseChangeListener() {
//					
//					public void onDatabaseChangeNotification(DatabaseChangeEvent e) {
//						// TODO Auto-generated method stub
//						DatabaseChangeEvent.EventType etype = e.getEventType();				//事件的种类
//						System.out.println("receive "+etype+" event, RegId="+e.getRegId());
//						if (etype!=DatabaseChangeEvent.EventType.OBJCHANGE) {
//							return;															//如果不是数据改变事件，如表结构更改或者删除表，则返回
//						}
//						
//						new WebSocketMessageInboundTest("table is changing!!!!!!");
//						TableChangeDescription [] tcds = e.getTableChangeDescription();		//得到表改变的描述信息
//						for (TableChangeDescription tcd : tcds) {
//							System.out.println("change table="+tcd.getTableName());
//							java.util.EnumSet<TableChangeDescription.TableOperation> tops = tcd.getTableOperations();	//获得表操作类型
//							for (TableChangeDescription.TableOperation top : tops) {
//								System.out.println(".... TableOperation="+top);
//								WebSocketMessageInboundPoolTest.sendMessage("databasechanged===="+tcd.getTableName()+"   "+"tableoperation==="+top);
//							}
//							RowChangeDescription [] rcds = tcd.getRowChangeDescription();
//							for (RowChangeDescription rcd : rcds) {							//获得更改的记录描述，包括更改类型insert、update、delete、以及rowid。
//								System.out.println("....RowOperation="+rcd.getRowOperation()+" , rowid= " + rcd.getRowid().stringValue());
//								
//							}
//						}	
//					}
//				});
//				Statement stmt = ocoon.createStatement();
//				((OracleStatement)stmt).setDatabaseChangeRegistration(dcr);
//				ResultSet rs = stmt.executeQuery("select 1 from user_test where 1=2");//这里是关联RPT_CHENJUN_DDJH_ERR_CODE 表的更改事件？？？？？？？
//				//while(rs.next()){}
//				for (String tableName : dcr.getTables()) {
//					System.out.println("DatabaseChangeRegistration ON "+ tableName);
//				}
//				rs.close();
//				stmt.close();
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				if(ocoon != null)
//					ocoon.unregisterDatabaseChangeNotification(dcr);
//				throw e;
//			} finally{
//				try{
//					conn.close();
//				}catch(Exception innerex){ 
//					innerex.printStackTrace();
//				}
//			}
//		}
//		
//		
//		if ("insert".equals(type)) {
//			Connection conn = null;
//			try {
//				conn = getConnection();
//				OracleConnection ocoon = conn.unwrap(OracleConnection.class);
//				ocoon.setAutoCommit(false);
//				Statement stmt2 = ocoon.createStatement();
//				stmt2.executeUpdate("insert into TEST_WL_USER_DICT values (222, '','','','')", Statement.RETURN_GENERATED_KEYS);
//				ResultSet autogeneratedKey = stmt2.getGeneratedKeys();
//				if (autogeneratedKey.next()) {
//					System.out.println("inserted one row with  ROWID="+autogeneratedKey.getString(1));
//					
//				}
//				stmt2.close();
//				ocoon.commit();
//				ocoon.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally{
//				if (conn != null) {
//					conn.close();
//				}
//			}
//		}
//	}
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
//}
