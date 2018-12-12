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
import com.wl.forms.OrderTree;
import com.wl.forms.Warehouse;
import com.wl.tools.Sqlhelper;

public class SearchMachineInfo extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public SearchMachineInfo() {
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
		String sql="select machineId,machineName from MACHINFO order by machineId";
		List<Machine> MachineTreeList=new ArrayList<Machine>();
		try {
			MachineTreeList = Sqlhelper.exeQueryList(sql, null, Machine.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringBuffer jsonBuffer = new StringBuffer(8192);
	    jsonBuffer.append("[");
	    for (int i = 0,len=MachineTreeList.size(); i < len; i++) {
	    	Machine machine = MachineTreeList.get(i);
			jsonBuffer.append("{");
			jsonBuffer.append("\"id\":"+"\""+machine.getMachineId()+"\",");
			jsonBuffer.append("\"pid\":"+"\"0000\",");
			jsonBuffer.append("\"level\":"+"\"1\",");		//1：库方层
			jsonBuffer.append("\"warehouseId\":"+"\""+machine.getMachineId()+"\",");
			jsonBuffer.append("\"text\":"+"\""+machine.getMachineName()+"\"");
			jsonBuffer.append("},");
	    }
			String jsonString  = jsonBuffer.substring(0, jsonBuffer.length()-1)+"]";
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().append(jsonString).flush();
			System.out.println(jsonString);
	}

}
