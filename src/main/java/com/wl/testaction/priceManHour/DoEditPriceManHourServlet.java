package com.wl.testaction.priceManHour;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.e;
/*import oracle.net.aso.l;*/

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class DoEditPriceManHourServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String craftId = ChineseCode.toUTF8(request.getParameter("craftId").trim());
	    String craftName = ChineseCode.toUTF8(request.getParameter("craftName").trim());
	    String price = ChineseCode.toUTF8(request.getParameter("price").trim());
	    String unit = ChineseCode.toUTF8(request.getParameter("unit").trim());
	    
//	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//	    String changeTime = df.format(new Date());
//	    
//	    HttpSession session = request.getSession();
//	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		
	    
	    /*
	     * String  addOrderSql = "insert into orders " +
				"(ORDER_ID,DEPT_USER,MEMO,endTime,ORDER_DATE," +
				"ORDER_STATUS,CUSTOMER,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME) values ('"+
				orderId+"','"+deptUser+"','"+memo+"',to_date('"+endTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+orderDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+
				orderStatus+"','"+customer+"','"+
				createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"
				+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
	     * */
		String updateOrderSql="update pricemanhour set " +
				"craftname='"+craftName+"',price='"+price+"',unit='"+unit+"'" +
				"where craftid='"+craftId+"'";
		System.out.println("updateOrderSql="+updateOrderSql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(updateOrderSql);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













