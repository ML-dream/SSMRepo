package com.wl.testaction.outAssistManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.OutAssist;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class OutAssistGetSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 449543228702584474L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String itemId = ChineseCode.toUTF8(request.getParameter("itemId").trim());
	    String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId").trim());
	
		String	CustomerSql = 
		    	"select orderId,itemId,itemName,drawingId,getNum numget," +
	    		"numUnit numUnitget,getDate dateget,getPerson personget,memo,B.staff_name personNameget " +
		    	"from outAssistGET A " +
		    	"left join employee_info B on B.staff_code=A.getPerson "+
		    	"where orderId=? and itemId=? and drawingId=? ";
		String[] params = {orderId,itemId,drawingId};
		OutAssist result = new OutAssist();
		try {
			result = Sqlhelper.exeQueryBean(CustomerSql, params, OutAssist.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		
//	    System.out.println("CustomerSql=="+CustomerSql);
//	    
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(CustomerSql, null);
//			
//			rs.next();
//			OutAssist result = new OutAssist();
//			result.setOrderId(rs.getString(1));
//			result.setItemId(rs.getString(2));
//			result.setItemName(rs.getString(3));
//			result.setDrawingId(rs.getString(4));
//			result.setNumget(rs.getInt(5));
//			
//			result.setNumUnitget(rs.getString(6));
//			result.setDateget(rs.getString(7));
//			result.setPersonget(rs.getString(8));
//			result.setMemoget(rs.getString(9));
//			result.setPersonNameget(rs.getString(10));
//			
//			request.setAttribute("result", result);
//			
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(rs!=null){
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		request.getRequestDispatcher("outAssistManage/editOutAssistGet.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













