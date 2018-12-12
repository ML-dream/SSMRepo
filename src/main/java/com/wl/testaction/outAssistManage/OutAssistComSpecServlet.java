package com.wl.testaction.outAssistManage;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.OutAssistCom;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class OutAssistComSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 9165458944402669726L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String companyId = ChineseCode.toUTF8(request.getParameter("companyId").trim());
	    String connector = ChineseCode.toUTF8(request.getParameter("connector").trim());
	
		String	CustomerSql = 
		    	"select companyId,companyName,foundingTime,employeeNum,type," +
	    		"address,postCode,telephone,webAddress,header," +
	    		"business,connector,connectorTel,connectorQQ,connectorEmail," +
	    		"bank,account,dutyParagraph,founding,memo,passrate,connector2,connector2Tel,connector3,connector3Tel,connector4,connector4Tel " +
		    	"from outassistcom where companyId=? and connector=? ";
		String[] params = {companyId,connector};
		OutAssistCom result = new OutAssistCom();
		try {
			result = Sqlhelper.exeQueryBean(CustomerSql, params, OutAssistCom.class);
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
//			OutAssistCom result = new OutAssistCom();
//			result.setCompanyId(rs.getString(1));
//			result.setCompanyName(rs.getString(2));
//			result.setFoundingTime(rs.getString(3));
//			result.setEmployeeNum(rs.getInt(4));
//			result.setType(rs.getString(5));
//			
//			result.setAddress(rs.getString(6));
//			result.setPostCode(rs.getString(7));
//			result.setTelephone(rs.getString(8));
//			result.setWebAddress(rs.getString(9));
//			result.setHeader(rs.getString(10));
//			
//			result.setBusiness(rs.getString(11));
//			result.setConnector(rs.getString(12));
//			result.setConnectorTel(rs.getString(13));
//			result.setConnectorQQ(rs.getString(14));
//			result.setConnectorEmail(rs.getString(15));
//			
//			result.setBank(rs.getString(16));
//			result.setAccount(rs.getString(17));
//			result.setDutyParagraph(rs.getString(18));
//			result.setFounding(rs.getDouble(19));
//			result.setMemo(rs.getString(20));
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
		request.getRequestDispatcher("outAssistComManage/editOutAssistCom.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













