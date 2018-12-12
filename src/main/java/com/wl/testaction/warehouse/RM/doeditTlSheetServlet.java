package com.wl.testaction.warehouse.RM;

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

public class doeditTlSheetServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditTlSheetServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String rmDate=(String) datamap.get("rmDate");
		String rmSheetid=(String) datamap.get("rmSheetid");
		String warehouseId=(String) datamap.get("warehouse_id");
		String empId=(String) datamap.get("emp_id");
		String dept=(String) datamap.get("dept");
		String operatorId=(String) datamap.get("operator_id");
		
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		
		String[] params={rmSheetid};
		String sql="update tuiliao set warehouseId='"+warehouseId+"',empId='"+empId+"',dept='"+dept+"',operatorId='"+operatorId+"'," +
				"changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') where rmSheetid=?";
		try{
			Sqlhelper.executeUpdate(sql, params);
			String json="{\"result\":\"操作成功！\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
