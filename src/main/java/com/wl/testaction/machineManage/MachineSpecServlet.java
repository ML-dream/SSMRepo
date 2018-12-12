package com.wl.testaction.machineManage;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.Machine;
import com.wl.tools.Sqlhelper;

public class MachineSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 2493319358099243522L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String machineId = request.getParameter("machineId");
	
//		String	CustomerSql = 
//		    	"select machineId,machineName,machineSpec,place,outCode," +
//				"outDate,machNum,workRange,machType,machModel," +
//				"machStandard,machManufacture,usedYears,machPrice,machOldRate," +
//				"is_KeyMach isKeyMach,buyDate,status,power,dept_Id deptId," +
//				"runDate,worker,madeDate,checkTime,A.memo," +
//				"B.deptName,C.STAFF_NAME workerName " +
//		    	"from machinfo A " +
//		    	"left join dept B on B.deptId=A.dept_id " +
//		    	"left join employee_info C on C.STAFF_CODE=A.WORKER " +
//		    	"where machineId='"+machineId+"'";
	    
	    String	CustomerSql = "select machineId,machineName,machineSpec,place,outCode," +
				"outDate,machNum,workRange,machType,machModel," +
				"machStandard,machManufacture,usedYears,machPrice,machOldRate," +
				"is_KeyMach isKeyMach,buyDate,status,power,deptId," +
				"runDate,worker,madeDate,checkTime,A.memo," +
				"B.deptName,C.STAFF_NAME workerName,A.hourPercent,A.countPercent " +
		    	"from machinfo A " +
		    	"left join dept B on B.deptId=A.dept_id " +
		    	"left join employee_info C on C.STAFF_CODE=A.WORKER " +
		    	"where machineId=?";
		String[] params = {machineId};
		
		Machine result = new Machine();
		
		try {
			result = Sqlhelper.exeQueryBean(CustomerSql, params, Machine.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("machineManage/editMachine.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













