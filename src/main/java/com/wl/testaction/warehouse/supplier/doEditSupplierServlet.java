package com.wl.testaction.warehouse.supplier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class doEditSupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doEditSupplierServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String companyId = ChineseCode.toUTF8(request.getParameter("companyId").trim());
	    String companyName = ChineseCode.toUTF8(request.getParameter("companyName").trim());
	    String type = ChineseCode.toUTF8(request.getParameter("type").trim());
	    
	    String foundingTime = ChineseCode.toUTF8(request.getParameter("foundingTime").trim());
	    String header = ChineseCode.toUTF8(request.getParameter("header").trim());
	    String employeeNum = ChineseCode.toUTF8(request.getParameter("employeeNum").trim());
	    
	    String address = ChineseCode.toUTF8(request.getParameter("address").trim());
	    String postCode = ChineseCode.toUTF8(request.getParameter("postCode").trim());
	    String telephone = ChineseCode.toUTF8(request.getParameter("telephone").trim());
	    
	    String webAddress = ChineseCode.toUTF8(request.getParameter("webAddress"));
	    String business = ChineseCode.toUTF8(request.getParameter("business").trim());
	    String advise = ChineseCode.toUTF8(request.getParameter("advise"));
	    String connector = ChineseCode.toUTF8(request.getParameter("connector").trim());
	    String connectorTel = ChineseCode.toUTF8(request.getParameter("connectorTel").trim());
	    
	    String dutyParagraph= ChineseCode.toUTF8(request.getParameter("dutyParagraph").trim());
	    String bank= ChineseCode.toUTF8(request.getParameter("bank").trim());
	    String account= ChineseCode.toUTF8(request.getParameter("account").trim());
	    
	    String rate=ChineseCode.toUTF8(request.getParameter("rate").trim());
	    String isTogether = request.getParameter("isTogether");
	    
	    String updateSupplierSql="update supplier set " +
		"COMPANYNAME='"+companyName+"',FOUNDEINGTIME=to_date('"+foundingTime+"','yyyy-mm-dd,hh24:mi:ss'),EMPLLOYEENUM='"+employeeNum+"',TYPE='"+type
		+"',ADDRESS='"+address+"',POSTCODE='"+postCode+"',TELEPHONE='"+telephone+"'," +
    	"WEBADDRESS='"+webAddress+"',HEADER='"+header+"',BUSINESS='"+business+"',DUTYPARAGRAPH='"+dutyParagraph+"',BANK='"+bank+"'," +
    	"ACCOUNT='"+account+"',ADVISE='"+advise+"',ISTOGETHER='"+isTogether+"',connectorTel='" +connectorTel+"',rate='"+rate+"' "+
    	"where COMPANYID='"+companyId+"'";
	    System.out.println("updateSupplierSql="+updateSupplierSql);
	    
	    try {
			Sqlhelper.executeUpdate(updateSupplierSql, null);
			String json = "{\"result\":\"操作成功!\"}";
		//	response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			
			
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
		//	response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
