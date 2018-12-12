package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;


import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.UUIDHexGenerator;

public class GoAllOutAssistListServlet extends HttpServlet {
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    String data = ChineseCode.toUTF8(request.getParameter("data")).replaceAll("\"", "");
	    String companyId=ChineseCode.toUTF8(request.getParameter("companyId"));
	    JSONArray jsonArray = JSONArray.fromObject(data);
		List<ProcessesPlan> Process = JSONArray.toList(jsonArray, ProcessesPlan.class);
		Date date=  new Date();   
		SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String now = sd.format(date);

		 
		String sql="update processesPlan set waiXieCom=?,isMenu='1',menuId=? where orderId=? and productId=? and issueNum=? and operId=? and isdiscard='0'";
		String Sql="insert into outAssistMenu(menuId,waiXieCom,deliverTime,totalNum) values(?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_number(?))";
		String searchSql="select to_char(planEndTime,'yyyy-mm-hh') from processesPlan where orderId=? and productId=? and issueNum=? and operId=? and isdiscard='0'";
		String insertSql="insert into outAssistStat(orderId,productId,issueNum,operId,deliverTime,planEndTime) values(?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'))";
		String totalNum=Process.size()+"";
		String UUID = UUIDHexGenerator.getInstance().generate();
		String[] Params={UUID,companyId,now,totalNum};
		for(int i=0;i<Process.size();i++){
			ProcessesPlan process=Process.get(i);
			String[] searchParams={process.getOrderId(),process.getProductId(),process.getIssueNum(),process.getOperId()};
			String planEndTime=null;
			String[] params={process.getWaiXieCom(),UUID,process.getOrderId(),process.getProductId(),process.getIssueNum(),process.getOperId()};
			try{
				Sqlhelper.executeUpdate(sql, params);
				planEndTime=Sqlhelper.exeQueryString(searchSql, searchParams);
				String[] insertParams={process.getOrderId(),process.getProductId(),process.getIssueNum(),process.getOperId(),now,planEndTime};
				Sqlhelper.executeUpdate(insertSql, insertParams);
			}catch(Exception e){
				  String json = "{\"result\":\"操作失败!\"}";
				  response.setCharacterEncoding("UTF-8");
				  response.getWriter().append(json).flush();
			      e.printStackTrace();
			}
		}
		try{
			Sqlhelper.executeUpdate(Sql, Params);
		}catch(Exception e){
			  String json = "{\"result\":\"操作失败!\"}";
			  response.setCharacterEncoding("UTF-8");
			  response.getWriter().append(json).flush();
		      e.printStackTrace();
		}
		
		   String json = "{\"result\":\"操作成功!\"}";
		   response.setCharacterEncoding("UTF-8");
	       response.getWriter().append(json).flush();
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}


}
