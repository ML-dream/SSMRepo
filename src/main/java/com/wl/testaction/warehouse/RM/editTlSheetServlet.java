package com.wl.testaction.warehouse.RM;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.Checkin;
import com.wl.forms.LlSheet;
import com.wl.forms.TlSheet;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class editTlSheetServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String rmSheetid=request.getParameter("rmSheetid");
		HttpSession session=request.getSession();
		String editPerson=((User)session.getAttribute("user")).getStaffCode();
	    String createSql="select createPerson from tuiliao where rmsheetid='"+rmSheetid+"'";
		TlSheet createPerson=new TlSheet();
		String staffCode="";
		try{
			createPerson=Sqlhelper.exeQueryBean(createSql, null, TlSheet.class);
			staffCode=createPerson.getCreatePerson();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(editPerson.equals(staffCode)){
	    
		String sql="select RMDATE,RMSHEETID,B.WAREHOUSEID,C.warehouse_name warehouseName,EMPID,D.staff_name empName,DEPT," +
		"OPERATORID,F.staff_name operatorName,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME from tuiliao B " +
		"left join warehouse C on C.warehouse_id=B.warehouseid " +
		"left join EMPLOYEE_INFO D on D.staff_code=B.empid " +
		"left join EMPLOYEE_INFO F on F.staff_code=B.operatorid " +
		"where rmsheetid='"+rmSheetid+"'";
		TlSheet tlsheet=new TlSheet();
		try{
			
			tlsheet=Sqlhelper.exeQueryBean(sql, null, TlSheet.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("tlsheet",tlsheet);
		request.getRequestDispatcher("/Tuiliao/editTlSheet.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("Fail.jsp").forward(request, response);
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
