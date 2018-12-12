package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class SaveOutAssistPayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String companyId=(String) datamap.get("companyId");
		String payDate=(String) datamap.get("payDate");
		String thispaid=(String) datamap.get("thispaid");
		String qualitykk=(String) datamap.get("qualitykk");		
  String sql="insert into outAssistPay values(?,to_date(?,'yyyy-mm-dd'),?,?)";
  String[] params={companyId,payDate,thispaid,qualitykk};
  try{
	   Sqlhelper.executeUpdate(sql, params);
 	   String json = "{\"result\":\"操作成功!\"}";
	   response.setCharacterEncoding("UTF-8");
	   response.getWriter().append(json).flush();
  }catch(Exception e){
	  String json = "{\"result\":\"操作失败!\"}";
	  response.setCharacterEncoding("UTF-8");
	  response.getWriter().append(json).flush();
	  e.printStackTrace();
  }

  }


}
