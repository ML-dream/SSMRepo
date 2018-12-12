package com.wl.testaction.warehouse;

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
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StringUtil;

public class doEditDetailCheckoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String data = request.getParameter("submitData");
	    HashMap datalist = (HashMap)Test.JSON.Decode(data);
	    String checksheet_id=(String) datalist.get("checksheet_id");
		String order_id=(String) datalist.get("order_id");
		String item_id=(String) datalist.get("item_id");
		String item_name=(String) datalist.get("item_name");
		String drawingId=(String) datalist.get("drawingId");
		String issue_num=(String) datalist.get("issue_num");
		String item_type=(String) datalist.get("item_type");
		String spec=(String) datalist.get("spec");
		String stockNum=(String) datalist.get("stockNum");
		String num=(String) datalist.get("checkout_num");
		String outnum=(String) datalist.get("outNum");
		String uprice=(String) datalist.get("unitprice");
		String tprice=(String) datalist.get("price");
		String stock_id=(String) datalist.get("stock_id");
		//String order_id=(String) datalist.get("order_id");
		//String lot=(String) datalist.get("lot");
		//String quality_id=(String) datalist.get("quality_id");
		String memo=(String) datalist.get("memo");
		String warehouse_id=(String) datalist.get("warehouse_id");
		
		//String warehouse_id=ChineseCode.toUTF8((String) datalist.get("warehouse_id"));
		double checkout_num=StringUtil.isNullOrEmpty(num)?0:Double.parseDouble(num);
		double unitprice=Double.parseDouble(uprice);
		double outNum=StringUtil.isNullOrEmpty(num)?0:Double.parseDouble(outnum);
		double price=checkout_num*unitprice;
		
		HttpSession session = request.getSession();
		String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String changeTime = df.format(new Date());
		
	    String sql="update mycheckout_detl set item_id='"+item_id+"',item_name='"+item_name+"',spec='"+spec+"'," +
	    			"Stocknum='"+stockNum+"',checkout_num="+checkout_num+",unitprice="+unitprice+",price="+price+",stock_id='"+stock_id+"'," +
	    			"memo='"+memo+"',issue_num='"+issue_num+"',item_type='"+item_type+"',changeperson= '" +changePerson+"',changetime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') "+
	    			"where checksheet_id='"+checksheet_id+"' and item_id='"+item_id+"'";
	    System.out.println("sql="+sql);
	    try{
			
	    	Sqlhelper.executeUpdate(sql, null);
	    	if(checkout_num!=outNum){
				Stockcl.Stockin(item_id, item_name,spec,item_type, warehouse_id, stock_id, outNum,unitprice, "");
				Stockcl.Stockout(item_id, checkout_num);
			}
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
