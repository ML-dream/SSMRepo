package com.wl.testaction.machineManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.MachineHire;
import com.wl.tools.Sqlhelper;

public class MachineHireSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 2493319358099243522L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String machineId = request.getParameter("machineId");
	
		String	CustomerSql = 
		    	"select machineId,A.deptId,hireStatus,outDate,inDate,backDate,hireMoney,hireNum,principal,A.memo,D.deptName,E.staff_name staffname  " +
		    	"from machineHire A " +
		    	"left join dept D on D.deptId=A.deptId " +
		    	"left join employee_info E ON A.PRINCIPAL=E.Staff_code "+
		    	"where machineId=?";
		String[] params = {machineId};
		MachineHire result = new MachineHire();
		try {
			result = Sqlhelper.exeQueryBean(CustomerSql, params, MachineHire.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result.setOutDate("1".equals(result.getHireStatus())?"":result.getOutDate());
		result.setInDate("0".equals(result.getHireStatus())?"":result.getInDate());
		
		request.setAttribute("machineHire", result);
		
		
//	    System.out.println("CustomerSql=="+CustomerSql);
//	    
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(CustomerSql, null);
//			rs.next();
//			MachineHire result = new MachineHire();
//			
//			result.setMachineId(rs.getString(1));
//			result.setDeptId(rs.getString(2));
//			result.setHireStatus(rs.getString(3));
//			result.setOutDate("1".equals(rs.getString(3))?"":rs.getString(4));
//			result.setInDate("0".equals(rs.getString(3))?"":rs.getString(5));
//			result.setBackDate(rs.getString(6));
//			result.setHireMoney(rs.getDouble(7));
//			result.setHireNum(rs.getInt(8));
//			result.setPrincipal(rs.getString(9));
//			result.setMemo(rs.getString(10));
//			result.setDeptName(rs.getString(11));
//			
//			request.setAttribute("machineHire", result);
//			
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(rs!=null){
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		request.getRequestDispatcher("machineManage/editMachineHire.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













