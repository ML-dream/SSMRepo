package com.wl.testaction.machineManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.Machine;
import com.wl.forms.MachineRepair;
import com.wl.tools.Sqlhelper;

public class updateRepairServlet extends HttpServlet {

	private static final long serialVersionUID = 2493319358099243522L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    	String mainId = request.getParameter("mainId");
			String	updateSql = "update MACHINEREPAIR t set t.repairState='否' where t.mainId='"+mainId+"'";
			 
//			String[] params = {mainId};
		
			String json = "{\"result\":\"操作成功!\"}";
			try {
				Sqlhelper.executeUpdate(updateSql, null);
			} catch (Exception e) {
				e.printStackTrace();
				
				 json = "{\"result\":\"操作失败!\"}";
				
			}
			
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			System.out.println(json);
	
			
		}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













