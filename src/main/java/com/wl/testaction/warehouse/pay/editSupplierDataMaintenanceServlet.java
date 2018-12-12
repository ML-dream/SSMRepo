package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class editSupplierDataMaintenanceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editSupplierDataMaintenanceServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
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

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String,String>) Test.JSON.Decode(data);
		String companyId=(String) datamap.get("companyId");
		String maintenanceDate=(String) datamap.get("initialmaintenanceDate");
		String endPayment=(String) datamap.get("endPayment");
		String reason=(String) datamap.get("reason");
		String initialEndPayment=request.getParameter("initialEndPayment");
		
		HttpSession session=request.getSession();
		String editPerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String editTime=df.format(new Date());
		
		
		String Sql="update supplierpaystatistics set endpayment='"+endPayment+"' where companyid='"+companyId+"' and " +
				"maintenanceDate=to_date('"+maintenanceDate+"','yyyy-mm-dd,hh24:mi:ss')";
		
		try {
			Sqlhelper.executeUpdate(Sql, null);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		
		String editSql="insert into editsupplierpaystatistics (companyid,maintenancedate,editendpayment,initialendpayment,editperson,edittime,reason) " +
				"values('"+companyId+"',to_date('"+maintenanceDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+endPayment+"','"+initialEndPayment+"'," +
						"'"+editPerson+"',to_date('"+editTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+reason+"')";
		try {
			Sqlhelper.executeUpdate(editSql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			out.append(json).flush();
		} catch (Exception e) {
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			out.append(json).flush();
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
