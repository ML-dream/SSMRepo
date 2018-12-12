package com.wl.testaction.Ll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.LlSheet;
import com.wl.tools.Sqlhelper;

public class showLlSheetDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

	    String llSheetid=request.getParameter("llSheetid");
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
		request.getRequestDispatcher("/Lingliao/seeLlSheet.jsp").forward(request, response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
