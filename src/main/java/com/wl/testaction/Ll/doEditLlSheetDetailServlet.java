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
import com.wl.tools.Stockcl;
import com.wl.tools.StringUtil;

public class doEditLlSheetDetailServlet extends HttpServlet {

	
	private static final Object HashMap = null;

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
		String ll_sheetid=(String) datamap.get("ll_sheetid");
		String warehouse_id=(String) datamap.get("warehouse_id");
		String num1=StringUtil.isNullOrEmpty((String)datamap.get("num1"))?"":datamap.get("num1").toString().trim();
//		String num2=datamap.get("num2").toString().trim();
		String item_id=(String) datamap.get("item_id");
		String item_name=(String) datamap.get("item_name");
		String order_id=(String) datamap.get("order_id");
		String productId=(String)datamap.get("productId");
		String productName=(String)datamap.get("productName");
		String issue_num=(String) datamap.get("issue_num");
		String item_type=(String) datamap.get("item_type");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String ll_num=StringUtil.isNullOrEmpty((String)datamap.get("ll_num"))?"":datamap.get("ll_num").toString().trim();
		String stockNum=datamap.get("stock_num").toString().trim();
		
		String unitprice=(String) datamap.get("unitprice");
		
		//String price=StringUtil.isNullOrEmpty((String) datamap.get("price"))?(String) datamap.get("price");
		String stock_id=(String) datamap.get("stock_id");
//		String rm_num=datamap.get("rm_num").toString().trim();
		String memo=(String) datamap.get("memo");
		double unitPrice=StringUtil.isNullOrEmpty(unitprice)?0:Double.parseDouble(unitprice);
		double Llnum=StringUtil.isNullOrEmpty(ll_num)?0:Double.parseDouble(ll_num);
		double price=unitPrice*Llnum;
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		
		String[] params={ll_sheetid,item_id};
		String sql="update lingliao_detl set ll_num='"+ll_num+"',unitprice='"+unitprice+"'," +
				"price='"+price+"',stock_id='"+stock_id+"',memo='"+memo+"'," +
				"changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') " +
				"where ll_sheetid=? and item_id=?";
		
		try{
			Sqlhelper.executeUpdate(sql, params);
			if(!num1.equals(ll_num)){
				double number1=num1.equals("")?0:Double.parseDouble(num1);
				double number2=ll_num.equals("")?0:Double.parseDouble(ll_num);
				Stockcl.Stockin(item_id, item_name,spec, item_type, warehouse_id, stock_id, number1,unitPrice, unit);
				Stockcl.Stockout(item_id, number2);
			}
//			if(!num2.equals(rm_num)){
//				double number1=num2.equals("")?0:Double.parseDouble(num2);
//				double number2=rm_num.equals("")?0:Double.parseDouble(rm_num);
//				Stockcl.Stockout(item_id, number1);
//				Stockcl.Stockin(item_id, item_name,spec, item_type, warehouse_id, stock_id, number2,unitPrice, unit);
//				
//			}
			String json="{\"result\":\"操作成功！\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
