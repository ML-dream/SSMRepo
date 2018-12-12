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

public class AddSheetServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String data = request.getParameter("submitData");
	    HashMap datalist = (HashMap)Test.JSON.Decode(data);
	    double checkin_num=0;
	    double unitprice=0;
	    double price=0;
		
	    String checksheet_id=ChineseCode.toUTF8((String) datalist.get("checksheet_id"));
		String checkindetl_id=ChineseCode.toUTF8((String) datalist.get("checkindetl_id"));
		String item_id=ChineseCode.toUTF8((String) datalist.get("item_id"));
		String item_name=ChineseCode.toUTF8((String) datalist.get("item_name"));
		String item_type=ChineseCode.toUTF8((String) datalist.get("item_type"));
		String issue_num=ChineseCode.toUTF8((String) datalist.get("issue_num"));
		String spec=ChineseCode.toUTF8((String) datalist.get("spec"));
		String unit=ChineseCode.toUTF8((String) datalist.get("unit"));
		String num=ChineseCode.toUTF8((String) datalist.get("checkin_num"));
		String uprice=ChineseCode.toUTF8((String) datalist.get("unitprice"));
		String tprice=ChineseCode.toUTF8((String) datalist.get("price"));
		String stock_id=ChineseCode.toUTF8((String) datalist.get("stock_id"));
		String order_id=ChineseCode.toUTF8((String) datalist.get("order_id"));
		String lot=ChineseCode.toUTF8((String) datalist.get("lot"));
		String quality_id=ChineseCode.toUTF8((String) datalist.get("quality_id"));
		String memo=ChineseCode.toUTF8((String) datalist.get("memo"));
		String warehouse_id=ChineseCode.toUTF8((String) datalist.get("warehouse_id"));
		checkin_num=Double.parseDouble(num);
		unitprice=Double.parseDouble(uprice);
		price=Double.parseDouble(tprice);
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 String createTime = df.format(new Date());
		 String changeTime = df.format(new Date());
		    
		 HttpSession session = request.getSession();
		 String createPerson = ((User)session.getAttribute("user")).getStaffCode();
		 String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	     String sql="insert into mycheckin_detl (checksheet_id,checkindetl_id,item_id,item_name,spec,unit,checkin_num,unitprice,price," +
	    		"stock_id,order_id,lot,quality_id,memo,item_type,issue_num,createperson,createtime,changeperson,changetime) " +
	    		"values('"+checksheet_id+"','"+checkindetl_id+"','"+item_id+"','"+item_name+"','"+spec+"','"+unit+"',"+checkin_num+"," +
	    		""+unitprice+","+price+",'"+stock_id+"','"+order_id+"','"+lot+"','"+quality_id+"','"+memo+"','"+item_type+"','"+issue_num+"'," +
	    		"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
	    System.out.println("sql="+sql);
	   
	    try{
			
			Sqlhelper.executeUpdate(sql, null);
			Stockcl.Stockin(item_id,item_name,spec,item_type,warehouse_id,stock_id,checkin_num,unitprice,unit);
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
