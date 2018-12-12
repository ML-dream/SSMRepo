package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.sql_data;

import com.wl.forms.OrderHead;
import com.wl.tools.Sqlhelper;
public class GetOrderHeadServlet extends HttpServlet {

	private static final long serialVersionUID = -2010070723465074258L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	    String sql= "select orderHead , orderHeadMean from orderHead";
	    List<OrderHead> resultList = new ArrayList<OrderHead>();
	    
	    try {
			resultList =Sqlhelper.exeQueryList(sql, null, OrderHead.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(resultList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	    
	    
//	    System.out.println("Sql=="+Sql);
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(Sql, null);
//			List<OrderHead> resultList = new ArrayList<OrderHead>();
//			try {
//				while (rs.next()) {
//					OrderHead result = new OrderHead();
//					result.setOrderHead(rs.getString(1));
//					result.setOrderHeadMean(rs.getString(2));
//					
//					resultList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(resultList);
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//			e.printStackTrace();
//		}  finally{
//			try {
//				if(rs!=null){
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













