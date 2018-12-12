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
import com.wl.tools.Stockcl;
import com.wl.tools.StringUtil;

public class doeditTlSheetDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditTlSheetDetailServlet() {
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
		String rmSheetid=(String) datamap.get("rmSheetid");
		String warehouse_id=(String) datamap.get("warehouse_id");
		String rmNum=StringUtil.isNullOrEmpty(datamap.get("rmNum").toString().trim())?"":datamap.get("rmNum").toString().trim();
		String rmNum1=StringUtil.isNullOrEmpty(datamap.get("rmNum1").toString().trim())?"":datamap.get("rmNum1").toString().trim();
		String itemId=(String) datamap.get("itemId");
		String itemName=(String) datamap.get("itemName");
		String orderId=(String) datamap.get("orderId");
		String issueNum=(String) datamap.get("issueNum");
		String itemType=(String) datamap.get("itemType");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String unitprice=(String) datamap.get("unitPrice");
		//String price=(String) datamap.get("price");
		String stockId=(String) datamap.get("stockId");
		String memo=(String) datamap.get("memo");
		double unitPrice=StringUtil.isNullOrEmpty(unitprice)?0:Double.parseDouble(unitprice);
		double rmnum=StringUtil.isNullOrEmpty(rmNum)?0:Double.parseDouble(rmNum);
		double totalPrice=unitPrice*rmnum;
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		
		String[] params={rmSheetid,itemId};
		String sql="update tuiliao_detl set rmNum='"+rmNum+"',unitPrice="+unitPrice+"," +
				"price='"+totalPrice+"',stockId='"+stockId+"',memo='"+memo+"',changePerson='"+changePerson+"'," +
				"changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') where rmSheetid=? and itemId=?";
		try{
			Sqlhelper.executeUpdate(sql, params);
			if(!rmNum.equals(rmNum1)){
				double number1=rmNum.equals("")?0:Double.parseDouble(rmNum);
				double number2=rmNum1.equals("")?0:Double.parseDouble(rmNum1);
				Stockcl.Stockout(itemId, number2);
				Stockcl.Stockin(itemId, itemName,spec, itemType, warehouse_id, stockId, number1,unitPrice, unit);
				
			}
			String json="{\"result\":\"操作成功！\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
