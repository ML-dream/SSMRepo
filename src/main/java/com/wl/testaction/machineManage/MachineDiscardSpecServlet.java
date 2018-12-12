package com.wl.testaction.machineManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.MachineDiscard;
import com.wl.tools.Sqlhelper;

public class MachineDiscardSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 2493319358099243522L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String machineId = request.getParameter("machineId");
	
		String	CustomerSql = 
		    	"select machineId,A.deptId,discardDate,discardTo,discardMoney,principal,A.memo,B.deptName,C.staff_name staffName " +
		    	"from machineDiscard A " +
		    	"left join dept B on B.deptId=A.deptId " +
		    	"left join employee_info C on C.staff_code=A.principal "+
		    	"where machineId='"+machineId+"'";
		MachineDiscard result = new MachineDiscard();
		try {
			result = Sqlhelper.exeQueryBean(CustomerSql, null, MachineDiscard.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("machineDiscard", result);

		request.getRequestDispatcher("machineManage/editMachineDiscard.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













