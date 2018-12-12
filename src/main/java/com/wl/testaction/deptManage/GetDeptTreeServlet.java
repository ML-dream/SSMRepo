package com.wl.testaction.deptManage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.Dept;
import com.wl.tools.Sqlhelper;
public class GetDeptTreeServlet extends HttpServlet {

	private static final long serialVersionUID = -2555272112996105298L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    String sql = "select distinct deptId , deptName ,FDeptId ,deptLevel "+
					"from dept A "+
					"where isAlive='1'" +
					"start with deptLevel='1' "+
					"connect by prior A.deptId=A.FDeptId " +
					"order by deptId ";
	    List<Dept> resultList = new ArrayList<Dept>();
	    try {
			resultList = Sqlhelper.exeQueryList(sql, null, Dept.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(resultList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
//	    System.out.println("sql="+sql);
//	    ResultSet rs =null;
//	    List<Dept> resultList = new ArrayList<Dept>();
//		try{
//			rs = Sqlhelper.executeQuery(sql, null);
////			StringBuffer jsonBuffer = new StringBuffer(8192);
////			jsonBuffer.append("[");
//			while(rs.next()){
//				Dept dept = new Dept();
//				dept.setDeptId(rs.getString(1));
//				dept.setDeptName(rs.getString(2));
//				dept.setFDeptId(rs.getString(3));
//				dept.setDeptLevel(rs.getInt(4));
//				
//				resultList.add(dept);
//				
//			}
////			
////			
////			String itemSql = "select distinct product_id id, product_name text,fproduct_id pid,cengci,quotationTotal ProductPrice "+
////					"from order_detail A "+
////					"start with order_id in (select  order_id id from orders) "+
////					"connect by prior A.product_id=A.fproduct_id " +
////					"order by product_id";
////			System.out.println("itemSql="+itemSql);
////			rs = Sqlhelper.executeQuery(itemSql, null);
////			
////			while(rs.next()){
////				jsonBuffer.append("{");
////				jsonBuffer.append("\"id\":"+"\""+rs.getString("id")+"\",");
////				jsonBuffer.append("\"pid\":"+"\""+rs.getString("pid")+"\",");
////				//jsonBuffer.append("\"orderId\":"+"\""+rs.getString("order_id")+"\",");
////				jsonBuffer.append("\"level\":"+"\""+rs.getString("cengci")+"\",");
////				jsonBuffer.append("\"ProductPrice\":"+"\""+rs.getString("ProductPrice")+"\",");
////				jsonBuffer.append("\"text\":"+"\""+rs.getString("text")+"\"");
////				jsonBuffer.append("},");
////			}
////			String jsonString  = jsonBuffer.substring(0, jsonBuffer.length()-1)+"]";
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













