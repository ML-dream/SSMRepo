package com.wl.testaction.machineManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Machine;
import com.wl.forms.StockInfo;
import com.wl.forms.dataCollectionTable;
import com.wl.tools.Sqlhelper;

public class ShowMachineNodeInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ShowMachineNodeInfo() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	   
	    String machineId=request.getParameter("machineId");
	    
	    String noticeWillSql = "select t.machineId, t.machineName,t.machineSpec,t.machtype,t.Machmodel,t.MACHMANUFACTURE from machinfo t where machineId = '"+machineId+"'";
	    List<Machine> notices = new ArrayList<Machine>();
		try {
			notices = Sqlhelper.exeQueryList(noticeWillSql,null, Machine.class);
		} catch (Exception e) {
			System.out.print("关闭的语句");
		}
		String json = PluSoft.Utils.JSON.Encode(notices);
		json = json.substring(1, json.length()-1);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
