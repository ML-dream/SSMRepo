package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.wl.forms.Order;
import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class SaveSelectedOutAssistComServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   doPost(request,response);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    String data = ChineseCode.toUTF8(request.getParameter("data")).replaceAll("\"", "");
    JSONArray jsonArray = JSONArray.fromObject(data);
	List<ProcessesPlan> Process = JSONArray.toList(jsonArray, ProcessesPlan.class);
	for(int i=0;i<Process.size();i++){
		ProcessesPlan process=Process.get(i);
		String sql="update processesPlan set waiXieCom=?,isMenu='1' where orderId=? and productId=? and issueNum=? and operId=?";
		String[] params={process.getWaiXieCom(),process.getOrderId(),process.getProductId(),process.getIssueNum(),process.getOperId()};
		try{
			Sqlhelper.executeUpdate(sql, params);
		}catch(Exception e){
			  String json = "{\"result\":\"操作失败!\"}";
			  response.setCharacterEncoding("UTF-8");
			  response.getWriter().append(json).flush();
		      e.printStackTrace();
		}
	}
	   String json = "{\"result\":\"操作成功!\"}";
	   response.setCharacterEncoding("UTF-8");
       response.getWriter().append(json).flush();
	
	}


}
