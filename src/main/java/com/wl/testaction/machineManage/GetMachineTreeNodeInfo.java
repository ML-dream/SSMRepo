package com.wl.testaction.machineManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Machine;
import com.wl.forms.Warehouse;
import com.wl.tools.Sqlhelper;

public class GetMachineTreeNodeInfo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Constructor of the object.
	 */
	public GetMachineTreeNodeInfo() {
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
		request.setCharacterEncoding("utf-8");
		String machineName = null;
		String machineId=request.getParameter("machineId");
		String sql="select machineName from MACHINFO where machineId='"+machineId+"'";
		String machine="";
		Machine machine1 =new Machine();
		try{
			machine1=Sqlhelper.exeQueryBean(sql, null, Machine.class);
			machineName=machine1.getMachineName();
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("machineId", machineId);
		request.setAttribute("machineName", machineName);
		request.getRequestDispatcher("machineManage/showMachineInfo.jsp").forward(request, response);
		
	}

}

