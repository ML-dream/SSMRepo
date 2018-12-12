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

public class OutAssistDetailServlet extends HttpServlet {

	private static final long serialVersionUID = -1701933614486604694L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String itemId = ChineseCode.toUTF8(request.getParameter("itemId").trim());
	
		String	CustomerSql = 
		    	"select orderId,itemId,itemName,drawingId,num," +
	    		"numUnit,unitPrice detailunitPrice,totalPrice detailtotalPrice,detail detaildetail,startDate detailstartDate," +
	    		"planEndDate detailplanEndDate,endDate detailendDate,memo detailMemo " +
		    	"from outAssistDetail where orderId=? and itemId=? ";
		String[] params = {orderId,itemId};
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
//			result.setNum(rs.getInt(5));
//			
//			result.setNumUnit(rs.getString(6));
//			result.setDetailunitPrice(rs.getDouble(7));
//			result.setDetailtotalPrice(rs.getDouble(8));
//			result.setDetaildetail(rs.getString(9));
//			result.setDetailstartDate(rs.getString(10));
//			result.setDetailplanEndDate(rs.getString(11));
//			result.setDetailendDate(rs.getString(12));
//			result.setDetailmemo(rs.getString(13));
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
		request.getRequestDispatcher("outAssistManage/editOutAssistSpec.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













