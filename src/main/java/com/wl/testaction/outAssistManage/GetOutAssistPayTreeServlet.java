package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Customer;
import com.wl.forms.OutAssistCom;
import com.wl.tools.Sqlhelper;

public class GetOutAssistPayTreeServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String sql="select companyid,companyname from outassistcom order by companyid";
		List<OutAssistCom> CompanyTreeList=new ArrayList<OutAssistCom>();
		try {
			CompanyTreeList = Sqlhelper.exeQueryList(sql, null, OutAssistCom.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringBuffer jsonBuffer = new StringBuffer(8192);
	    jsonBuffer.append("[");
	    for (int i = 0,len=CompanyTreeList.size(); i < len; i++) {
	    	OutAssistCom customer = CompanyTreeList.get(i);
			jsonBuffer.append("{");
			jsonBuffer.append("\"id\":"+"\""+customer.getCompanyId()+"\",");
			jsonBuffer.append("\"pid\":"+"\"0000\",");
			jsonBuffer.append("\"level\":"+"\"1\",");		//1：库方层
			jsonBuffer.append("\"companyId\":"+"\""+customer.getCompanyId()+"\",");
			jsonBuffer.append("\"text\":"+"\""+customer.getCompanyName()+"\"");
			jsonBuffer.append("},");
	    }
	    
	    String jsonString  = jsonBuffer.substring(0, jsonBuffer.length()-1)+"]";
		//response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonString).flush();
		System.out.println(jsonString);
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}
}
