package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.PoData;
import com.wl.tools.Sqlhelper;

public class PoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String status=request.getParameter("status");
		String data=request.getParameter("submitData");
		HashMap datamap = (HashMap)Test.JSON.Decode(data);
		String postart_date=(String) datamap.get("postart_date");
		String po_sheetid=(String) datamap.get("po_sheetid");
//		String orderId=(String) datamap.get("order_id");
//		String telephone=(String) datamap.get("telephone");
		String customerid=(String) datamap.get("customerid");
//		String customername=(String) datamap.get("customername");
		String connector=(String) datamap.get("connector");
		String connectortel=(String) datamap.get("connectortel");
		String end_date=(String) datamap.get("end_date");
		String salesman_id=(String) datamap.get("salesmanId");
		String drawer_id=(String) datamap.get("drawerId");
		String id=(String) datamap.get("id");
		String seq=(String) datamap.get("seq");
//		String scount=request.getParameter("count");
//		int count=Integer.parseInt(scount);
		HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=df.format(new Date());
		String changeTime=df.format(new Date());

		
		String Posql="insert into po_plan (po_sheetid,customerid,connector,connectortel,postart_date,end_date,salesman_id,createperson,createtime,changeperson,changetime,drawerid,status) values('"+po_sheetid+"','"+customerid+"','"+connector+"','"+connectortel+"',to_date('"+postart_date+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+end_date+"','yyyy-mm-dd,hh24:mi:ss')," +
				"'"+salesman_id+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+drawer_id+"','"+status+"')";
		String sqlsheet="insert into po_sheetid values("+seq+",'"+id+"','"+po_sheetid+"')";
		System.out.println("POsql="+Posql);
		System.out.println("sqlsheet="+sqlsheet);
		
		try{
			Sqlhelper.executeUpdate(Posql, null);
			Sqlhelper.executeUpdate(sqlsheet, null);
			String json = "{\"result\":\"操作成功!\",\"count\":1}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
		}
	
		
	}


