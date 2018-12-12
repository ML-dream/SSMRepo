package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.StringToDate;


import cfmes.util.DealString;
import cfmes.util.sql_data;

public class DispatchFinish extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");			   
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    out.println("<finishState>");
	    String type = ds.toString(ds.toGBK(request.getParameter("type")));
	    String orderid = ds.toString(ds.toGBK(request.getParameter("orderid")));
	    String issue_num = ds.toString(ds.toGBK(request.getParameter("issue_num")));
	    String item_id = ds.toString(ds.toGBK(request.getParameter("item_id")));		    
	    String oper_id = ds.toString(ds.toGBK(request.getParameter("oper_id")));
	    String machineid = ds.toString(ds.toGBK(request.getParameter("machineid")));	    
	    
	    String dispatch_starttime = request.getParameter("dispatch_starttime");
	    String dispatch_endtime = request.getParameter("dispatch_endtime");
	    String wokerid = ds.toString(ds.toGBK(request.getParameter("workerid")));

		if(type.equals("machineid")){
			
			String timesql = "select start_time,end_time from process_plan where " 
//	    		"orderid='"+orderid+""
	    		+" item_id='"+item_id+"'"
	    		+" and oper_id='"+oper_id+"'";                      //查询待派工的工序的开始和结束时间
		    System.out.println("DispatchFinish的timesql:"+timesql);
		    
		    ResultSet timers = sqlbean.executeQuery(timesql);
		    String starttime ="";
		    String endtime = "";
		    try {
				timers.next();
				starttime =timers.getDate(1)+" "+timers.getTime(1);
				endtime =timers.getDate(2)+" "+timers.getTime(2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					if(timers!=null){
						timers.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    
		    System.out.println("starttime==="+starttime);
		    System.out.println("endtime==="+endtime);
		    
		    
		    String dispatchedTimeSql = "select start_time, end_time from departuresheet " +
		    		"where machine_id='"+machineid+"' order by start_time";
		    System.out.println("dispatchedTimeSql==="+dispatchedTimeSql);
		    
		    ResultSet dispachedTimeRs = sqlbean.executeQuery(dispatchedTimeSql);
		    boolean dispatchedable = true;
		    long starttime_process = StringToDate.FromChina24ToMills(starttime);
		    long endtime_process = StringToDate.FromChina24ToMills(endtime);
		    try {
		    	while (dispachedTimeRs.next()) {
					long starttime_machine = StringToDate.FromChina24ToMills
						(dispachedTimeRs.getDate(1)+" "+dispachedTimeRs.getTime(1));
					long endtime_machine = StringToDate.FromChina24ToMills
						(dispachedTimeRs.getDate(2)+" "+dispachedTimeRs.getTime(2));
					/*
					 * 下面这个判断条件是为了确定是否可以进行派工操作，dispatchedable==false表示不能派工，
					 * dispatchedable==false的条件是将派的工序占用的时间和设备上已经派好的时间相互冲突，此处默认的是
					 * 工序的开始时间在结束时间之前，机床已经完成的派工时间中开始时间也在结束时间之前，总的来说就是时间有交集
					 * */
					System.out.println("starttime_process==="+starttime_process);
					System.out.println("endtime_process==="+endtime_process);
					System.out.println("starttime_machine==="+starttime_machine);
					System.out.println("endtime_machine==="+endtime_machine);
					if ((starttime_process>starttime_machine && starttime_process<endtime_machine) ||
							endtime_process>starttime_machine && endtime_process<endtime_machine) {
						dispatchedable = false;
					}
					System.out.println(dispatchedable);
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally{
				try {
					if(dispachedTimeRs!=null){
						dispachedTimeRs.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			if (dispatchedable) {               //dispatchedable==true时表示可以派工
				String dispatch_sql = "insert into work.DEPARTURESHEET " +
				"(machine_id,orderid,issue_num,item_id,oper_id,START_TIME,END_TIME,worker) values" +
				"('"+machineid+"','"+orderid+"','"+issue_num+"','"+item_id+"','"+oper_id
				+"',to_date('"+dispatch_starttime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+dispatch_endtime+"','yyyy-mm-dd,hh24:mi:ss'),'"
				+wokerid+"')";
				System.out.println("dispatch_sql=="+dispatch_sql);
				
				try {
					int s=sqlbean.exeUpdateThrowExcep(dispatch_sql);
					out.println("<finish>1</finish>");           //这个是判断当前派工是否已经完成，1表示完成
					out.println("<isfinish>0</isfinish>");       //这个事判断此工序是否之前已经派过工，0表示之前没有派过工
					out.println("<timeout>0</timeout>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					if (e.getErrorCode()==1) {
						System.out.println("捕捉到错误码为1了已经！！！！");
						out.println("<finish>0</finish>");
						out.println("<isfinish>1</isfinish>");
						out.println("<timeout>0</timeout>");
					}else {
						e.printStackTrace();
					}
				} finally{
					sqlbean.closeConn();
			    }
			}else {
				System.out.println("此机床在当前指定时间已经有任务了，请检查计划是否合理！！");
				out.println("<finish>0</finish>");
				out.println("<isfinish>0</isfinish>");
				out.println("<timeout>1</timeout>");            //1表示时间冲突了，0表示时间没有冲突！！
			}

//			ServletContext context=getServletContext();
//			RequestDispatcher rd=context.getRequestDispatcher("/SuccessDisp");
//			rd.forward(request, response);
		//	response.sendRedirect("cch-jsp/Success.jsp");
//			out.print("<script>window.location.href='cch-jsp/Success.jsp';</script>");				
		}
		out.println("</finishState>");
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}	

}
