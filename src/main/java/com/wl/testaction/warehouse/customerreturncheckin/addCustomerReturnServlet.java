package com.wl.testaction.warehouse.customerreturncheckin;

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
import com.wl.tools.Stockcl;

public class addCustomerReturnServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addCustomerReturnServlet() {
		super();
	}

	
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
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String sheetId=(String) datamap.get("sheetId");
		String orderId=(String) datamap.get("orderId");
		String itemId=(String) datamap.get("itemId");
		String itemName=(String) datamap.get("itemName");
		String issueNum=(String) datamap.get("issueNum");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String returnNum=(String) datamap.get("returnNum");
		String unitPrice=(String) datamap.get("unitPrice");
		String price=(String) datamap.get("price");
		String qkprice=(String) datamap.get("qkprice");
		String reason=(String) datamap.get("reason");
		double returnnum=Double.parseDouble(returnNum);
		double unitprice=Double.parseDouble(unitPrice);
		double Price=price.equals("")?(unitprice*returnnum):Double.parseDouble(price);
		
		HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());
		String changeTime = df.format(new Date());
		
		
		String customerSql="insert into customerreturndetail (sheetid,itemid,itemname,issuenum,spec,unit,returnnum," +
				"unitprice,price,qkprice,returnreason,orderid,createperson,createtime,changeperson,changetime) values('"+sheetId+"','"+itemId+"','"+itemName+"','"+issueNum+"'," +
					"'"+spec+"','"+unit+"','"+returnNum+"','"+unitPrice+"','"+Price+"','"+qkprice+"','"+reason+"','"+orderId+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
							"'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
		String orderSql="update order_detail set alreadypaynum=(alreadypaynum-'"+returnnum+"') where order_id='"+orderId+"' and " +
		"product_id='"+itemId+"' and issue_num='"+issueNum+"'";
		
		try{
			Sqlhelper.executeUpdate(customerSql, null);
			Stockcl.Stockin(itemId,itemName,spec,"","","",returnnum,unitprice,unit);
			Sqlhelper.executeUpdate(orderSql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	
		
	}

}
