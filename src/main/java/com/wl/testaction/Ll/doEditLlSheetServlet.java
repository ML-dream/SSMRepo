package com.wl.testaction.Ll;

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

public class doEditLlSheetServlet extends HttpServlet {

	
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
		String lldate=(String) datamap.get("lldate");
		String ll_sheetid=(String) datamap.get("llSheetid");
		String warehouse_id=(String) datamap.get("warehouse_id");
		String warehouse_name=(String) datamap.get("warehouse_name");
		String emp_id=(String) datamap.get("emp_id");
		String emp=(String) datamap.get("emp");
		String dept=(String) datamap.get("dept");
		String operator_id=(String) datamap.get("operator_id");
		String operator=(String) datamap.get("operator");
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		String[] params={ll_sheetid};
		String sql="update lingliao set warehouse_id='"+warehouse_id+"',emp_id='"+emp_id+"',dept='"+dept+"'," +
				"operator_id='"+operator_id+"', changePerson='"+changePerson+"'," +
				"changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') where ll_sheetid=?";
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
