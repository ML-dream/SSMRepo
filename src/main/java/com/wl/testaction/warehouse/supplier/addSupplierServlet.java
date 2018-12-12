package com.wl.testaction.warehouse.supplier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class addSupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addSupplierServlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String companyId = ChineseCode.toUTF8(datamap.get("companyId").toString().trim());
	    String companyName = ChineseCode.toUTF8(datamap.get("companyName").toString().trim());
	    String type = ChineseCode.toUTF8(datamap.get("type").toString().trim());
	    
	    String foundingTime = ChineseCode.toUTF8(datamap.get("foundingTime").toString().trim());
	    String header = ChineseCode.toUTF8(datamap.get("header").toString().trim());
	    String employeeNum = ChineseCode.toUTF8(datamap.get("employeeNum").toString().trim());
	    
	    String address = ChineseCode.toUTF8(datamap.get("address").toString().trim());
	    String postCode = ChineseCode.toUTF8(datamap.get("postCode").toString().trim());
	    String telephone = ChineseCode.toUTF8(datamap.get("telephone").toString().trim());
	    
	    String webAddress = ChineseCode.toUTF8(datamap.get("webAddress").toString());
	    String business = ChineseCode.toUTF8(datamap.get("business").toString());
	    String dutyParagraph=ChineseCode.toUTF8(datamap.get("dutyParagraph").toString());
	    String bank=ChineseCode.toUTF8(datamap.get("bank").toString());
	    String account=ChineseCode.toUTF8(datamap.get("account").toString());
	    String advise=ChineseCode.toUTF8(datamap.get("advise").toString());
	    String connector = ChineseCode.toUTF8(datamap.get("connector").toString());
	    String connectorTel = ChineseCode.toUTF8(datamap.get("connectorTel").toString());
	    String rate = ChineseCode.toUTF8(datamap.get("rate").toString());
	    String addSupplierSql="insert into supplier " +
	    		"(COMPANYID,COMPANYNAME,FOUNDEINGTIME,EMPLLOYEENUM,TYPE," +
				"ADDRESS,POSTCODE,TELEPHONE,WEBADDRESS,HEADER,BUSINESS," +
				"DUTYPARAGRAPH,BANK,ACCOUNT,ADVISE,ISTOGETHER,connector,connectorTel,RATE) " +
				"values('"+companyId+"','"+companyName+"',to_date('"+foundingTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+employeeNum+"','"
				+type+"','"+address+"','"+postCode+"','"+telephone+"','"+
				webAddress+"','"+header+"','"+business+"','"+dutyParagraph+"','"+bank+"','"+account+"','"+advise+"'," +
				"'Y','"+connector+"','"+connectorTel+"','"+rate+"')";
	    System.out.println("addMachineSql=="+addSupplierSql);
	    try{
	    	Sqlhelper.executeUpdate(addSupplierSql, null);
	    	String json = "{\"result\":\"操作成功!\",\"status\":1}";
			response.getWriter().append(json).flush();
	    }catch (SQLException e) {
	    	String json = "{\"result\":\"操作失败!\",\"status\":0}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	
	
	}

}
