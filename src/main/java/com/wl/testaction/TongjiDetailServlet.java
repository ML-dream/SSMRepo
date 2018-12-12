package com.wl.testaction;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.n;

import com.wl.tools.Sqlhelper;

public class TongjiDetailServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setHeader("Charset","utf-8");    
	    
		String itemId = request.getParameter("itemId");
		String processId = request.getParameter("processId");
		
		String sql = "select prt.wcid ,prt.start_time, prt.end_time, " +
					        "mc.machinename , mc.worker  ," +
					        "prt.num , prt.finishnum , prt.pass_num , prt.failure_num " +
					 "from process_temp prt " +
					 "left join machinfo_cch  mc " +
					   "on prt.wcid = mc.machineid " +
					 "where prt.oper_id = '" + processId + "' " +
					     "and prt.item_id ='" + itemId + "' " ;
		
		System.out.println(sql);
		
		ResultSet rs = null;
		
		try {
			rs = Sqlhelper.executeQuery(sql, null);
			if (rs.next()) {
				request.setAttribute("wcid", rs.getString(1));
				request.setAttribute("start_time", rs.getString(2));
				request.setAttribute("end_time", rs.getString(3));
				request.setAttribute("machinename", rs.getString(4));
				request.setAttribute("worker", rs.getString(5));
				request.setAttribute("num", rs.getString(6));
				request.setAttribute("finishnum", rs.getString(7));
				request.setAttribute("pass_num", rs.getString(8));
				request.setAttribute("failure_num", rs.getString(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rs = null;
			}
		}
		request.setAttribute("processId", processId);
		request.setAttribute("itemId", itemId);
		
		
		//以下是下一道工序的查询过程
		
		String nextProSql = "select min(to_number(pt.oper_id)) as next_oper_id " +
				"from work.process_plan pt " +
				"where pt.item_id='" +itemId+"' and to_number(pt.oper_id) >'"+processId+"' " +
				"order by pt.oper_id";
		
		System.out.println(nextProSql);
		ResultSet nextPrors = null;
		String nextPro = "";
		try {
			nextPrors = Sqlhelper.executeQuery(nextProSql, null);
			if (nextPrors.next()) {
				nextPro = nextPrors.getString(1);
				request.setAttribute("next_proId", nextPro);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (nextPro!=null && !"".equals(nextPro)) {
			String nextMacSql = "select machineid,machinename,status from work.machinfo_cch where machineid= "+
									 "(select pt.wcid " +
									   "from process_temp pt " +
									   "where pt.oper_id ='"+processId + "' " +
									 	  "and pt.item_id = '"+itemId+"' )";
			System.out.println(nextMacSql);
			try {
				nextPrors = Sqlhelper.executeQuery(nextMacSql, null);
				if (nextPrors.next()) {
					request.setAttribute("next_machinId", nextPrors.getString(1));
					request.setAttribute("next_machName", nextPrors.getString(2));
					request.setAttribute("next_status", nextPrors.getString(3));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(nextPrors!=null){
					try {
						nextPrors.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					nextPrors = null;
				}
			}
		}
		
		//以下是工序的统计图功能
		
		String processSql="select num,finishnum,pass_num,failure_num from work.process_temp where oper_id='"  +processId+"' and item_id='"+itemId+"'";
		System.out.println(processSql);
		double finish_rate = 0 ;
		double pass_rate   = 0 ;
		
		try {
			rs = Sqlhelper.executeQuery(processSql, null);
			if (rs.next()) {
				int num         = rs.getInt("num");
				int finishnum   = rs.getInt("finishnum");
				int pass_num    = rs.getInt("pass_num");
				int failure_num = rs.getInt("failure_num");
				
				if(num!=0){
					finish_rate = ((double)finishnum/num)*100;
					pass_rate   = ((double)pass_num/num)*100;
					
					request.setAttribute("finish_rate",finish_rate );
					request.setAttribute("pass_rate", pass_rate);
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				rs = null;
			}
		}
		
		System.out.println( finish_rate + " " + pass_rate);
		
		this.getServletConfig().getServletContext().getRequestDispatcher("/finishPages/tongjiDetail.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
}
