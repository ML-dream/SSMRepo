package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.CheckoutId;
import com.wl.forms.User;
import com.wl.tools.CheckData;
import com.wl.tools.Sqlhelper;

public class AddCheckoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = response.getWriter();
	request.setCharacterEncoding("utf-8");
	String data=request.getParameter("submitData");
	HashMap datalist = (HashMap)Test.JSON.Decode(data);
	boolean b=false;
	String checksheet_id=(String) datalist.get("checksheet_id");
	String checkoutidSql="select checksheet_id from mycheckout";
	List<CheckoutId> checkoutIdList=new ArrayList<CheckoutId>();
	try{
		checkoutIdList=Sqlhelper.exeQueryList(checkoutidSql, null, CheckoutId.class);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	for(int i=0,len=checkoutIdList.size();i<len;i++){
		CheckoutId checkoutId=new CheckoutId();
		checkoutId=checkoutIdList.get(i);
		String checksheetId=checkoutId.getChecksheetId();
		if(checksheet_id.equals(checksheetId)){
			b=true;
		}
	}
	
	if(!b){
	HttpSession session = request.getSession();
	String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	String status=request.getParameter("status");
	String createTime = df.format(new Date());
	String changeTime = df.format(new Date());
	String id=(String) datalist.get("id");
	String seq=(String) datalist.get("seq");
	String checkout_date=(String) datalist.get("checkout_date");
	String order_id=(String) datalist.get("order_id");
	String companyId=(String) datalist.get("companyId");
//	String companyName=(String) datalist.get("companyName");
//	String connector=(String) datalist.get("connector");
//	String connectortel=(String) datalist.get("connectortel");
	String warehouse_id=(String) datalist.get("warehouse_id");
	String delivery=(String) datalist.get("delivery");
	String checkoutType=(String)datalist.get("checkoutType");
	String orderStatus=(String)datalist.get("orderStatus");
//	String operator=(String) datalist.get("operator");
	String sql="insert into mycheckout (CHECKOUT_DATE,CHECKSHEET_ID,COMPANYID,WAREHOUSE_ID," +
				"DELIVERY,ORDER_ID,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,STATUS,CHECKOUTTYPE) " +
				"values(to_date('"+checkout_date+"','yyyy-mm-dd,hh24:mi:ss'),'"+checksheet_id+"','"+companyId+"','"+warehouse_id+"','"+delivery+"','"+order_id+"'," +
				"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+status+"','"+checkoutType+"')";
	String sqlsheet="insert into checkout_sheetid values("+seq+",'"+id+"','"+checksheet_id+"')";
	String orderSql="update orders set order_status='"+orderStatus+"' where order_id='"+order_id+"'";
	System.out.println("sql="+sql);
	System.out.println("sqlsheet="+sqlsheet);
	try{
			Sqlhelper.executeUpdate(sql, null);
			Sqlhelper.executeUpdate(sqlsheet, null);
			Sqlhelper.executeUpdate(orderSql, null);
			String json = "{\"result\":\"操作成功!\"}";
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
			}
		
		}
	
		
	}


}
