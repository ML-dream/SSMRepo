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

public class OutAssistSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 9165458944402669726L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	
		String	CustomerSql = 
		    	"select orderId,A.deptId,A.companyId,A.companyName,A.principal," +
	    		"connectorName,A.connectorTel,startDate,planEndDate,trueEndDate," +
	    		"fine,shouldPay,alreadyPay,notPay,isBusy," +
	    		"A.memo,B.deptName,C.companyName,D.staff_name principalName " +
		    	"from outassist A " +
		    	"left join dept B  on B.deptId=A.deptId "+
		    	"left join outassistcom C  on C.companyId=A.companyId "+
		    	"left join employee_info D  on D.staff_code=A.principal "+
		    	"where orderId=? ";
		String[] params = {orderId};
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
//			result.setDeptId(rs.getString(2));
//			result.setCompanyId(rs.getString(3));
//			result.setCompanyName(rs.getString(4));
//			result.setPrincipal(rs.getString(5));
//			result.setConnectorName(rs.getString(6));
//			result.setConnectorTel(rs.getString(7));
//			result.setStartDate(rs.getString(8));
//			result.setPlanEndDate(rs.getString(9));
//			result.setTrueEndDate(rs.getString(10));
//			
//			result.setFine(rs.getDouble(11));
//			result.setShouldPay(rs.getDouble(12));
//			result.setAlreadyPay(rs.getDouble(13));
//			result.setNotPay(rs.getDouble(14));
//			result.setIsBusy(rs.getString(15));
//			result.setMemo(rs.getString(16));
//			result.setDeptName(rs.getString(17));
//			result.setCompanyName(rs.getString(18));
//			result.setPrincipalName(rs.getString(19));
//
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
		request.getRequestDispatcher("outAssistManage/editOutAssist.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













