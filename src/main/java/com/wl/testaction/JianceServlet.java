package com.wl.testaction;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.e;
import oracle.net.aso.f;

import com.wl.forms.Jiance;
import com.wl.tools.Sqlhelper;


public class JianceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String flag = request.getParameter("flag");
		
		//如果是根节点,表示订单中所有产品
		if ("gen".equals(flag)) {						
			String orderId = request.getParameter("orderId");
			
			String sql = "select a.orderid,a.item_id,a.oper_id,a.quality_id,a.start_time,a.end_time,a.wcid,b.machinename,b.worker,c.is_accept,a.num planNum,a.finishnum finishNum,a.pass_num passNum,a.failure_num failureNum " +
					"	  from process_temp a" +
					"		left join machinfo b" +
					"			on a.wcid = b.machineid " +
					"		left join quality_info c" +
					"			on  a.orderid = c.order_id and a.item_id = c.item_id and a.oper_id = c.oper_id" +
					"	  where " +
					"	  a.orderid ='" +orderId +
					"'	  order by a.item_id";					//这里的订单号orderid是要动态改的
			System.out.println(sql);
			ResultSet rs=null;
			try {
				rs = Sqlhelper.executeQuery(sql, null);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			List<Jiance> endList = new ArrayList<Jiance>();
			try {
				while (rs.next()) {
					Jiance jiance = new Jiance();
					jiance.setDingdanhao(rs.getString(1));
					jiance.setLingjianhao(rs.getString(2));
					jiance.setGongxuhao(rs.getString(3));
					jiance.setStartTime(rs.getString(5));
					jiance.setEndTime(rs.getString(6));
					jiance.setMachine(rs.getString("machinename"));
					jiance.setWorker(rs.getString("worker"));
					jiance.setStatus(rs.getString("is_accept"));

					jiance.setPlanNum(rs.getInt("planNum"));
					jiance.setFinishNum(rs.getInt("finishNum"));
					
					endList.add(jiance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String json = PluSoft.Utils.JSON.Encode(endList);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			System.out.println(json);
		}
		
		//如果是叶子节点，表示单个零件
		if ("ye".equals(flag)) {		
			String orderId = request.getParameter("orderId");
			String itemId = request.getParameter("itemId");
			
			String sql = "select a.orderid,a.item_id,a.oper_id,a.quality_id,a.start_time,a.end_time,a.wcid,b.machinename,b.worker,c.is_accept,a.processplan_b gongxuming,a.num planNum,a.finishnum finishNum,a.pass_num passNum,a.failure_num failureNum " +
					"	  from process_temp a" +
					"		left join machinfo b" +
					"			on a.wcid = b.machineid " +
					"		left join quality_info c" +
					"			on  a.orderid = c.order_id and a.item_id = c.item_id and a.oper_id = c.oper_id" +
					"	  where " +
					"	  a.orderid ='" +orderId + "' and a.item_id='" + itemId + "'" + 
					"	  order by to_number(a.oper_id)";					//这里的订单号orderid是要动态改的
			System.out.println(sql);
			ResultSet rs=null;
			try {
				rs = Sqlhelper.executeQuery(sql, null);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			List<Jiance> endList = new ArrayList<Jiance>();
			try {
				while (rs.next()) {
					Jiance jiance = new Jiance();
					jiance.setDingdanhao(rs.getString(1));
					jiance.setLingjianhao(rs.getString(2));
					jiance.setGongxuhao(rs.getString(3));
					jiance.setStartTime(rs.getString(5));
					jiance.setEndTime(rs.getString(6));
					jiance.setMachine(rs.getString("machinename"));
					jiance.setWorker(rs.getString("worker"));
					jiance.setStatus(rs.getString("is_accept"));
					jiance.setPlanNum(rs.getInt("planNum"));
					jiance.setFinishNum(rs.getInt("finishNum"));
					
					jiance.setGongxuming(rs.getString("gongxuming"));
					
					endList.add(jiance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String json = PluSoft.Utils.JSON.Encode(endList);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			System.out.println(json);
		}
		
		//监测当天所有的任务
		if ("today".equals(flag)) {		
			//dtcol >= trunc(sysdate) and dtcol < trunc(sysdate) + 1
			String sql = "select a.orderid,a.item_id,a.oper_id,a.quality_id,a.start_time,a.end_time,a.wcid,b.machinename,b.worker,c.is_accept,a.processplan_b gongxuming,a.num planNum,a.finishnum finishNum,a.pass_num passNum,a.failure_num failureNum " +
					"	  from process_temp a" +
					"		left join machinfo b" +
					"			on a.wcid = b.machineid " +
					"		left join quality_info c" +
					"			on  a.orderid = c.order_id and a.item_id = c.item_id and a.oper_id = c.oper_id" +
					"	  where " +
					"		(trunc(sysdate)<=a.start_time and a.start_time<=trunc(sysdate)+1) or (a.end_time>=trunc(sysdate) and a.end_time<=trunc(sysdate)+1 )"+
					"	  order by a.orderid";					//这里的订单号orderid是要动态改的
			System.out.println(sql);
			ResultSet rs=null;
			try {
				rs = Sqlhelper.executeQuery(sql, null);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			List<Jiance> endList = new ArrayList<Jiance>();
			try {
				while (rs.next()) {
					Jiance jiance = new Jiance();
					jiance.setDingdanhao(rs.getString(1));
					jiance.setLingjianhao(rs.getString(2));
					jiance.setGongxuhao(rs.getString(3));
					jiance.setStartTime(rs.getString(5));
					jiance.setEndTime(rs.getString(6));
					jiance.setMachine(rs.getString("machinename"));
					jiance.setWorker(rs.getString("worker"));
					jiance.setStatus(rs.getString("is_accept"));
					jiance.setPlanNum(rs.getInt("planNum"));
					jiance.setFinishNum(rs.getInt("finishNum"));
					
					jiance.setGongxuming(rs.getString("gongxuming"));
					
					endList.add(jiance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String json = PluSoft.Utils.JSON.Encode(endList);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			System.out.println(json);
		}
		
		
	}


}
