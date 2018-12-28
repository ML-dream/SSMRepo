package machineOrderYuyue.yuYue;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;










import javax.servlet.http.HttpSession;

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;

import machineOrderYuyue.beans.loadJson;
import net.sf.json.JSONObject;

public class bookingSubmit extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String orderJsonStr = request.getParameter("order");
		//System.out.println(orderJsonStr);
		
		JSONObject orderJsonObj = JSONObject.fromObject(orderJsonStr);

        //Grades grades = (Grades) JSONObject.toBean(jsonObject, Grades.class);
		
		List<JSONObject> bookings=orderJsonObj.getJSONArray("bookings");
		System.out.println(bookings);
		
		 String result = "successful";
		 String json = "";
		for(JSONObject booking:bookings){
			
			 String orderId=booking.getString("orderId");
			 
			 //String bookingUserName=booking.getString("connector");
			 HttpSession session = request.getSession(); 
			 String bookingUserName= (String) session.getAttribute("connector");
			 
			 String connectorTel =  booking.getString("connectorTel");
			 
			// String connectorTel=new String(booking.getString("connectorTel").getBytes("gbk"), "utf-8");
			
			 /*String connectorTel = null;
			 HttpSession session = request.getSession(); 
			 connectorTel= (String) session.getAttribute("connector");*/
			/*Order order2 = (Order) booking.get("connectorTel");
			 
			 connectorTel = order2.getConnector();
			 System.out.println(connectorTel);*/
			 String unid=booking.getString("uid");//---获取ID的值
			 int deptId=Integer.parseInt(booking.getString("venuesId"));
			 int machineId=Integer.parseInt(booking.getString("siteId"));
			 String machineName=booking.getString("siteName");
			 String startTimeInfo=booking.getString("startTime");//数据库根本用不到这个记录，但是放里面吧 在另外一个表里有模板信息
			 String endTimeInfo=booking.getString("endTime");
			 String deptName=booking.getString("venuesName");
			 //String unid=booking.getString("quantity");
			 String price=booking.getString("price");
			 String type=booking.getString("type");
			 
			 
			 Calendar calendar2 = Calendar.getInstance();
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String createTime = df.format(calendar2.getTime());
			 
			 String timeMd = startTimeInfo.replaceAll(":", "-");
			 
			 String timeYmd = "" ;
			 String[]  unids =unid.split("-");
			 for(int i=0,len=unids.length;i<len;i++){

				 if(i==1||i==2){
					 timeYmd+=unids[i]+"-";
				 }
				 if(i==3){
					 timeYmd+=unids[i];
				 }
			 }
			 
			 int  state = 2;
			 
			 
			// System.out.println(timeYmd);
			
			 String lockTypeName = "已预订";
			 String bookStatus="12";
			 String bookingInsertSql= "INSERT INTO bookinginfo (unid,time_ymd,time_md,state,bookingusername,phone,deptId,machineId,"
			 		+ "starttime_info,endtime_info,createTime,orderId,lockTypename)"
			 		+ "VALUES (?,?,?,"+state+",?,?,"+deptId+","+machineId+",?,?,?,?,?)";
			 String[] param ={unid,timeYmd,timeMd,bookingUserName,connectorTel,startTimeInfo,endTimeInfo,createTime,orderId,lockTypeName};
			 
			 String updateBookStatusSql="UPDATE orders set book_status=? where order_Id=?";
			 String[] param1 = {bookStatus,orderId};
		
			 try {
				Sqlhelper.executeUpdate(bookingInsertSql, param);
				Sqlhelper.executeUpdate(updateBookStatusSql, param1);
			} catch (Exception e) {
				e.printStackTrace();
				result="fail";
				
			}
			 
			
			
			/*PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<HTML>");
				out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
				out.println("  <BODY>");
				out.print(" 预定成功！</br> ");
				
				out.println("  </BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();*/
			
			 
			
			 
			 
			
		}
		
		 response.setContentType("text/html;charset=utf-8");
			
			json = "{\"result\":\""+result+"!\"}";
			System.out.println(json);
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			response.setContentType("text/html");
	

		
		
		
	}
	
	
	/*
	 AjaxJson save(LinkedHashMap<String,Object> list){

		  AjaxJson  j=new AjaxJson();

		　String jsonStr=JSON.toJSONString(list);//-json字符串

		    JSONObject json=JSON.parseObject(jsonStr);//json对象

		　String uid=json.getString("userGroupId");//----获取对应的值

		　JSONArray alarmItems=json.getJSONArray("alarmItems");//---获取数组值 JSONArray是json数组 []中括号的内容

		for(int i=0;i<alarmItems.size();i++){

		　　JSONObject alarmItem=alarmItems.getJSONObject(i); //----JSONObject这个是 json的对象就是[]中的{}内容

		　  String idstr=alarmItem.getString("id");//---获取ID的值

		　 String type=alarmItem.getString("type");//---获取type的值

		　　//---后面的就是对数据的处理 增删改查 方法

		}

		j.setMsg("用户操作成功");

		return j;

		}*/

}
