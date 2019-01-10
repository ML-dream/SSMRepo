package com.wl.testaction;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.Customer;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class EditCustomerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 2654368619466895949L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String companyId = request.getParameter("companyId");
	   // String connector = ChineseCode.toUTF8(request.getParameter("connector"));
	
		String	CustomerSql = 
		    	"select COMPANYID,COMPANYNAME,FOUNDEINGTIME,EMPLLOYEENUM,TYPE,ADDRESS,POSTCODE,TELEPHONE," +
		    	"WEBADDRESS,HEADER,BUSINESS,ADVISE,connector,connectorTel,connector2,connector2Tel,connector3,connector3Tel,connector4,connector4Tel " +
		    	"from customer where companyId=? ";
		
		String[] params = {companyId};
		Customer customer = new Customer();
		try {
			customer = Sqlhelper.exeQueryBean(CustomerSql, params, Customer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("customer", customer);
		
		
//	    System.out.println("CustomerSql=="+CustomerSql);
//	    
//	    ResultSet CustomerRs =null;
//		try{
//			CustomerRs = Sqlhelper.executeQuery(CustomerSql, null);
//			
//			CustomerRs.next();
//			Customer customer = new Customer();
//			
//			customer.setCompanyId(CustomerRs.getString(1));
//			customer.setCompanyName(CustomerRs.getString(2));
//			customer.setFoundingTime(CustomerRs.getString(3));
//			customer.setEmployeeNum(CustomerRs.getString(4));
//			customer.setType(CustomerRs.getString(5));
//			customer.setAddress(CustomerRs.getString(6));
//			customer.setPostCode(CustomerRs.getString(7));
//			customer.setTelephone(CustomerRs.getString(8));
//			customer.setWebAddress(CustomerRs.getString(9));
//			customer.setHeader(CustomerRs.getString(10));
//			customer.setBusiness(CustomerRs.getString(11));
//			customer.setAdvise(CustomerRs.getString(12));
//			customer.setConnector(CustomerRs.getString(13));
//			customer.setConnectorTel(CustomerRs.getString(14));
//			
//			request.setAttribute("customer", customer);
//			
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(CustomerRs!=null){
//					CustomerRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		request.getRequestDispatcher("customerManage/editCustomer.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













