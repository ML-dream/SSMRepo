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

public class doeditPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditPayServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request,response);
	}

	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String,String>) Test.JSON.Decode(data);
		String payDate=datamap.get("payDate");
		String paySheetid=datamap.get("paySheetid"); 
		String customerId=datamap.get("customerId");  
		String connector=datamap.get("connector"); 
		String connectorTel=datamap.get("connectorTel"); 
		String payType=datamap.get("payType"); 
		String payable=datamap.get("payable");
		String prepaid=datamap.get("prepaid"); 
		String payment=datamap.get("payment");
		String payMethod=datamap.get("payMethod");
		String isBill=datamap.get("isBill");
		String account=datamap.get("account"); 	
		String examineId=datamap.get("examineId"); 
		String salesmanId=datamap.get("salesmanId");
		String drawerId=datamap.get("drawerId");
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		String Paysql="update popay set paydate=to_date('"+payDate+"','yyyy-MM-dd,hh24:mi:ss'),CUSTOMERID='"+customerId+"',CONNECTOR='"+connector+"'," +
		"CONNECTORTEL='"+connectorTel+"',PAYTYPE='"+payType+"',PAYABLE='"+payable+"',PREPAID='"+prepaid+"',PAYMENT='"+payment+"'," +
		"PAYMETHOD='"+payMethod+"',ACCOUNT='"+account+"',EXAMINEID='"+examineId+"',SALESMANID='"+salesmanId+"',DRAWERID='"+drawerId+"'," +
		"ISBILL='"+isBill+"',changeperson='"+changePerson+"',changetime=to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss') where paySheetid='"+paySheetid+"'";
		try{
			Sqlhelper.executeUpdate(Paysql, null);
			String json="{\"result\":\"操作成功！\"}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			out.append(json).flush();
			e.printStackTrace();
		}
	}

}
