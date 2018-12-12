package com.wl.testaction.po;

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

public class doEditPoServlet extends HttpServlet {

	
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
		String postart_date=(String) datamap.get("postart_date");
		String po_sheetid=(String) datamap.get("po_sheetid");
//		String orderId=(String) datamap.get("order_id");
		String customerid=(String) datamap.get("customerid");
		String connector=(String) datamap.get("connector");
		String connectortel=(String) datamap.get("connectortel");
//		String telephone=(String) datamap.get("telephone");
		String end_date=(String) datamap.get("end_date");
		String salesman_id=(String) datamap.get("salesman_id");
		String drawer_id=(String) datamap.get("drawer_id");
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		String poSql="update po_plan set status='1',postart_date=to_date('"+postart_date+"','yyyy-MM-dd,hh24:mi:ss'),customerid='"+customerid+"'," +
				"connector='"+connector+"',connectortel='"+connectortel+"',end_date=to_date('"+end_date+"','yyyy-MM-dd,hh24:mi:ss')," +
				"salesman_id='"+salesman_id+"',drawerid='"+drawer_id+"',changeperson='"+changePerson+"',changetime=to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss') " +
						"where po_sheetid='"+po_sheetid+"'";
		try{
			Sqlhelper.executeUpdate(poSql, null);
			String json="{\"result\":\"操作成功！\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
