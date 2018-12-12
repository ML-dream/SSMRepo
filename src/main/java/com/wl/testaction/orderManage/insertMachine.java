package com.wl.testaction.orderManage;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.e;

import Test.JSON;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Customer;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class insertMachine extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println(this.getClass().getName());
	    String orderId = request.getParameter("orderId").trim();
	    String machineId = request.getParameter("machineId").trim();
	    
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    
	    
	   
	    String foId = orderId.substring(6)+"0";
	    
		String	FoSql = "update fo_detail set equipcode=? " + 
							"where fo_id=?";
		String[] params = {machineId,foId};
		
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newdate = sdf.format(new Date());
		Calendar c = Calendar.getInstance();
		 c.set(Calendar.MINUTE, 0);
	     c.set(Calendar.SECOND, 0);
	     c.set(Calendar.HOUR_OF_DAY,8);
	     
	     String demoDate11 = sdf.format(c.getTime());	
  c.set(Calendar.HOUR_OF_DAY, 14);
  String demoDate22 = sdf.format(c.getTime());
  c.set(Calendar.HOUR_OF_DAY, 32);
  String demoDate33 = sdf.format(c.getTime());
 // String demoDate111=new String("2018-09-04 08:00:00"); 
		

		
		
		
		/*Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(date));
		//+3小时
		cal.add(Calendar.HOUR_OF_DAY, 3);
		System.out.println(sdf.format(cal.getTime()));*/
		 
		
		
		Date newDate1 = null;
		try {
			newDate1 = sdf.parse(newdate);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		Date demoDate1= null;
		try {
			demoDate1 = sdf.parse(demoDate11);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		Date demoDate2 = null;
		try {
			demoDate2 = sdf.parse(demoDate22);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		Date demoDate3 = null;
		try {
			demoDate3 = sdf.parse(demoDate33);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		
		String timeStart  = "";
		
		String Time="";		
		
		if (newDate1.before(demoDate1)) {
			 timeStart = demoDate11; //这S个是假是上午8点开始的标准时间
			 
			 try {
				Time = ceshi1(timeStart,machineId);
			} catch (ParseException e) {
				e.printStackTrace();
			}
				
		 }else if (newDate1.before(demoDate2)) {
			  timeStart = sdf.format(demoDate2);//这个时间是下午2点的的标准时间
			  
			  int totalCount3 = 0 ;
			  
				Calendar cal2 = Calendar.getInstance();
				try {
					cal2.setTime(sdf.parse(timeStart));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
				cal2.add(Calendar.HOUR_OF_DAY, 4);
				String timeEnd = sdf.format(cal2.getTime());
				String[] param3 = {timeStart,timeEnd,timeStart,timeEnd,timeStart,timeEnd};
				String totalCountSql = "select count(*) from Processesplan where"
			    		+ " ( machineOrderStart>= to_date(?,'yyyy-mm-dd,hh24:mi:ss') and machineOrderEnd <= to_date(?,'yyyy-mm-dd,hh24:mi:ss') )"
			    		+ " or (machineOrderStart>= to_date(?,'yyyy-mm-dd,hh24:mi:ss') and machineOrderEnd >= to_date(?,'yyyy-mm-dd,hh24:mi:ss')) "
			    		+ "or( machineOrderStart <= to_date(?,'yyyy-mm- dd,hh24:mi:ss') and machineOrderEnd >= to_date(?,'yyyy-mm-dd,hh24:mi:ss')) ";
			    		//+ "and machineid = '"+machineId+"'";
				try {
					totalCount3 = Sqlhelper.exeQueryCountNum(totalCountSql, param3);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(totalCount3==0) {
					Time = timeStart;
				}else {
					timeStart = sdf.format(demoDate3);//就变成了第二天的时间了
					try {
						Time = ceshi1(timeStart,machineId);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				
				}
			  
		 }else {
			  timeStart = sdf.format(demoDate3);//就变成了第二天的时间了
			  try {
				Time = ceshi1(timeStart,machineId);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 }
	
	
		
	
		
		
		

		Calendar cal1 = Calendar.getInstance();
		try {
			cal1.setTime(sdf.parse(Time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		cal1.add(Calendar.HOUR_OF_DAY, 4);
		String TimeEND = sdf.format(cal1.getTime());
		
		
		
		
		
		String ProcessplanSql = "update Processesplan set machineId =?,machineorderstart = to_date(?,'yyyy-mm-dd,hh24:mi:ss'),machineorderend = to_date(?,'yyyy-mm-dd,hh24:mi:ss')  where orderId = ? and operId =? and productId = ?";
		String[] params2 = {machineId,Time,TimeEND,orderId,"0",orderId.substring(6)};
		
		
		
		String result = "操作成功";
		String json = "";
		try {
			Sqlhelper.executeUpdate(FoSql, params);
			
		} catch (SQLException e) {
			result = "操作失败";
			e.printStackTrace();
			
		}
		try {
			Sqlhelper.executeUpdate(ProcessplanSql, params2);
			
		} catch (SQLException e) {
			result = "操作失败";
			e.printStackTrace();
			
		}
		
		json = "{\"result\":\""+result+"!\"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();

	}
	private String ceshi1(String timeStart,String machineId) throws ParseException {
		
			String  Start = timeStart;
			
			int totalCount = 0;
			while(true) {
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(Start));
				//+3小时
				cal.add(Calendar.HOUR_OF_DAY, 4);
				String End = sdf.format(cal.getTime());
					
				 String totalCountSql = "select count(*) from Processesplan where"
				    		+ " ( machineOrderStart>= to_date(?,'yyyy-mm-dd,hh24:mi:ss') and machineOrderEnd <= to_date(?,'yyyy-mm-dd,hh24:mi:ss') )"
				    		+ " or (machineOrderStart>= to_date(?,'yyyy-mm-dd,hh24:mi:ss') and machineOrderEnd >= to_date(?,'yyyy-mm-dd,hh24:mi:ss')) "
				    		+ "or( machineOrderStart <= to_date(?,'yyyy-mm-dd,hh24:mi:ss') and machineOrderEnd >= to_date(?,'yyyy-mm-dd,hh24:mi:ss'))";
				    		//+ " and machineid = '"+ machineId +"'";
				 String[] param1 = {Start,End,Start,End,Start,End};
				 try {
						totalCount = Sqlhelper.exeQueryCountNum(totalCountSql,param1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				 
				 if (totalCount ==0 ) {
					break; 
				 }
				 cal.add(Calendar.HOUR_OF_DAY, 2);
				 
				 Start = sdf.format(cal.getTime());
				 cal.add(Calendar.HOUR_OF_DAY, 4);
				 End   = sdf.format(cal.getTime());
				 String[] param2 = {Start,End};
				 
				 try {
						totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, param1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				 
				 if (totalCount ==0 ) {
					break; 
				 }
				 
				 cal.add(Calendar.HOUR_OF_DAY, 14);

				 Start = sdf.format(cal.getTime());
			
			}
		
				return  Start;
	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













