package com.wl.testaction.orderManage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.ItemCode;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;

public class AddCustomerItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1301655321688437935L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String customeId = ChineseCode.toUTF8(request.getParameter("customeId").trim());
	    String customeName = ChineseCode.toUTF8(request.getParameter("customeName").trim());
	    String itemId = ChineseCode.toUTF8(request.getParameter("itemId").trim());
	    String itemname = ChineseCode.toUTF8(request.getParameter("itemname").trim());
	    
	    String itemType = ChineseCode.toUTF8(request.getParameter("itemType").trim());
	    String itemspec = ChineseCode.toUTF8(request.getParameter("itemspec").trim());
	    String itemNum = ChineseCode.toUTF8(request.getParameter("itemNum").trim());
	    String itemPrice = ChineseCode.toUTF8(request.getParameter("itemPrice").trim());
	    String unit = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("unit"))?"":request.getParameter("unit").trim());
	    
	    String starttime = ChineseCode.toUTF8(request.getParameter("starttime").trim());
	    String endtime = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("endtime"))?"":request.getParameter("endtime").trim());
	    String person = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("person"))?"":request.getParameter("person").trim());
	    String isCheckIn = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("isCheckIn"))?"":request.getParameter("isCheckIn").trim());
	    String memo = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("memo"))?"":request.getParameter("memo"));
	    String checkinstatus=ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("checkinstatus"))?"":request.getParameter("checkinstatus"));
	    
//	    xiem	新增单位
	    String itemUnit = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("itemUnit"))?"":request.getParameter("itemUnit"));
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    if(itemId.isEmpty()){
	    	itemId = Stockcl.toItemCode(itemType, itemname);
	    }
	    
	    String UUID = UUIDHexGenerator.getInstance().generate();
	    String sql = "insert into customeasset " +
			"(mainid,orderid,customeId,itemId,itemname," +
			"itemspec,starttime,endtime," +
			"person,memo,orderDetailId,itemType,itemNum," +
			"itemPrice,isCheckIn,unit,customeName," +
			"createTime,changeTime,createPerson,changePerson,checkinstatus,itemunit)" +
			"values" +
			"('"+UUID+"','"+orderId+"','"+customeId+"','"+itemId+"','"+itemname+"'," +
			"'"+itemspec+"',to_date('"+starttime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+endtime+"','yyyy-mm-dd,hh24:mi:ss')," +
			"'"+person+"','"+memo+"','','"+itemType+"','"+itemNum+"'," +
			"'"+itemPrice+"','"+isCheckIn+"','"+unit+"','"+customeName+"'," +
			"to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+createPerson+"','"+changePerson+"','"+checkinstatus+"','"+itemUnit+"')";

	    System.out.println(sql);
	    
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
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


}
