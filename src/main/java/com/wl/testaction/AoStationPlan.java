package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.ProcessPlan;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

import cfmes.util.sql_data;


public class AoStationPlan extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	    System.out.println("进入AoStationPlan函数体了已经！！！");
	    
	    HttpSession session = request.getSession(true);
	    String product_id     =new String(request.getParameter("productid").trim().getBytes("ISO-8859-1"),"utf-8") ;
		Date product_starttime = null;
		Date product_finishtime = null;
		int product_status = 0;
		int product_num = 0;

		//String sql = "select *from partplan where PRODUCT_ID='"+id+"'";//查询装配计划
		String ProductSql = "select *from ao_plan where product_id='"+product_id+"'";  //此处的产品号是要程序动态传过来的
		System.out.println("ProductSql=="+ProductSql); 
		
		//查询此零件对应的工序,FO_OPERID工序编号，这个编号中有是第几道工序,此处OPER_TIME代表此道工序的总共加工时间，EQUIPCODE设备编号，
		String stationSql = "select * from aodetail where productid ='"+product_id+"'  order by ao_no desc";
		System.out.println("stationSql=="+stationSql);
		
		ResultSet ProductRs=null;
		try {
			ProductRs = (ResultSet) Sqlhelper.executeQuery(ProductSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//零件计划表
		ResultSet stationRs=null;
		try {
			stationRs = (ResultSet) Sqlhelper.executeQuery(stationSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ArrayList<ProcessPlan> al = new ArrayList<ProcessPlan>();	
		try {
			ProductRs.next();
			product_starttime = ProductRs.getDate("START_TIME");       //开始时间
			product_finishtime = ProductRs.getDate("END_TIME");      //结束时间
			product_num = ProductRs.getInt("NUM");                 //零件数量
         
			float start=0;
			float end=0;			
			while(stationRs.next()){				
				float duration;                       //存储的是小时数据，小时小时小时！！！！
				String equipment = null;
				String worker =null;
				
				int stationplan_sequence = 0;       //第一道工序在数据库中存储为5，第二道为10....
				Date stationplan_starttime = null;
				Date stationplan_finishtime = null;
				String stationplan_id = null;       //如何获得工序的ID号呢？？				
				stationplan_id = stationRs.getString("AO_NO");
				duration = stationRs.getFloat("AO_TIME");			
				end=start;
				start+=duration*product_num;
                //这个日期转化是绝对的技术核心！！！！！！
				//还有编制计划的计划人没有动态保存！！！！！！！！！！！！！！！~~~~~~~~~~~~~~~~~~·
				String insertStationSql = "insert into station_plan (ORDER_ID,PRODUCT_ID,AO_NO,NUM," +
				"START_TIME,END_TIME,PLAN_PERSON,PLAN_TIME) values('"+ProductRs.getString("ORDER_ID")+"','"+product_id+"','"+stationplan_id+"','"+product_num+"',"
				+"to_date('"+product_starttime+"','yyyy-mm-dd,hh24:mi:ss')-"+start+"/24," 
				+"to_date('"+product_finishtime+"','yyyy-mm-dd,hh24:mi:ss')-"+end+"/24,'"+((User)session.getAttribute("user")).getStaffCode()+"'," 
				+"to_date('"+new java.util.Date().toLocaleString()+"','yyyy-mm-dd,hh24:mi:ss'))";
				
				System.out.println("insertStationSql=="+insertStationSql);
				Sqlhelper.executeUpdate_noclose(insertStationSql, null); //重大错误隐患！！！！-->>结果集没有及时关闭！！！
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Processplanaction 中的processplan出现异常！！！！");
		} finally{
			try {
				if (stationRs!=null) {
					stationRs.close();
				}
				if (ProductRs!=null) {
					ProductRs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		return null;
//		try {
//			sqlData.exeUpdateThrowExcep(addAOPlanSql);
//			request.setAttribute("addOk", "装配计划编制成功！！");
//		} catch (SQLException e) {
//			// TODO: handle exception
//			request.setAttribute("addOk", "装配计划编制失败！！");
//			e.printStackTrace();
//		}
		
//		this.getServletConfig().getServletContext().getRequestDispatcher("/partplan/aoplan.jsp").forward(request, response);
//		response.sendRedirect("/work/partplan/aoplan.jsp");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}
