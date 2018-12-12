package com.wl.testaction;

import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wl.forms.ProcessPlan;
import com.wl.forms.StationPlan;

import com.wl.tools.Null_change;
import com.wl.tools.Sqlhelper;

public class QueryStationPlanOne extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	    System.out.println("进入QueryStationPlanOne函数体了已经！！！");

		String id = request.getParameter("id").toString();
//		String ispart=request.getParameter("part");
		String sql = null;
//		if("yes".equals(ispart)){
//			sql = "select *from process_plan where item_id='"+id+"'";
//		}
//		if("no".equals(ispart)){
			sql = "select *from station_plan where product_id='"+id+"'";
//		}
		System.out.println("sql=="+sql);
//		String paras[]={pid};
		ResultSet rs=null;
		try {
			rs = (ResultSet) Sqlhelper.executeQuery(sql, null);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<StationPlan> al = new ArrayList<StationPlan>();	
		try {
			Null_change null_change=null;
			while(rs.next()){
				StationPlan stationPlan=new StationPlan();
				
				stationPlan.setOrderid(rs.getString(1));
				stationPlan.setProductid(rs.getString(2));
				stationPlan.setAono(rs.getInt(3));
				stationPlan.setNum(rs.getInt(4));
				stationPlan.setStarttime(rs.getDate(5));
				stationPlan.setEndtime(rs.getDate(6));
				stationPlan.setPlaceid(rs.getInt(7));
				stationPlan.setPlacename(rs.getString(8));
				stationPlan.setPlanperson(rs.getString(9));
				stationPlan.setPlantime(rs.getDate(10));
				stationPlan.setQualityid(rs.getString(11));
				stationPlan.setBarcode(rs.getString(12));
				
				al.add(stationPlan);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("processPlan转化成list异常！！！");
		} finally{
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.setAttribute("stationplan", al);
		
		this.getServletConfig().getServletContext().getRequestDispatcher("/stationPlanDetailOne.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}
