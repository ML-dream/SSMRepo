package com.wl.testaction.Ll;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.Checkin;
import com.wl.forms.LlSheet;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class editLlSheetServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String editPerson=((User)session.getAttribute("user")).getStaffCode();
	    String llSheetid=request.getParameter("llSheetid");
	    String createSql="select createPerson from lingliao where ll_sheetid='"+llSheetid+"'";
	    LlSheet createPerson=new LlSheet();
		String staffCode="";
		try{
			createPerson=Sqlhelper.exeQueryBean(createSql, null, LlSheet.class);
			staffCode=createPerson.getCreatePerson();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(editPerson.equals(staffCode)){
		String sql="select LLDATE,LL_SHEETID,B.WAREHOUSE_ID,C.warehouse_name warehouseName,B.EMP_ID,D.staff_name empName,DEPT," +
		"OPERATOR_ID,F.staff_name operatorName,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME from lingliao B " +
		"left join warehouse C on C.warehouse_id=B.warehouse_id " +
		"left join EMPLOYEE_INFO D on D.staff_code=B.emp_id " +
		"left join EMPLOYEE_INFO F on F.staff_code=B.operator_id " +
		"where ll_sheetid='"+llSheetid+"'";
		LlSheet llsheet=new LlSheet();
		try{
			
			llsheet=Sqlhelper.exeQueryBean(sql, null, LlSheet.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("llsheet",llsheet);
		request.getRequestDispatcher("/Lingliao/editLlSheet.jsp").forward(request, response);
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
