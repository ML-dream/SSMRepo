package com.wl.testaction.priceManHour;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class AddPriceManHourServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String craftId = ChineseCode.toUTF8(request.getParameter("craftId").trim());
	    String craftName = ChineseCode.toUTF8(request.getParameter("craftName").trim());
	    String price = ChineseCode.toUTF8(request.getParameter("price").trim());
	    String unit = ChineseCode.toUTF8(request.getParameter("unit").trim());
	    
//	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//	    String createTime = df.format(new Date());
//	    String changeTime = df.format(new Date());
//	    
//	    HttpSession session = request.getSession();
//	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
//	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
//	    
		String  addpricemanhourSql = "insert into pricemanhour " +
				"(craftid,craftname,price,unit) values ('"+
				craftId+"','"+craftName+"','"+price+"','"+unit+"')";

		System.out.println("addpricemanhourSql=="+addpricemanhourSql);
		
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(addpricemanhourSql);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败！！！工艺编号重复，请检查！！\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}


}
