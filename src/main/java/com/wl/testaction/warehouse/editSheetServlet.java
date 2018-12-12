package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;

public class editSheetServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
		PrintWriter out = response.getWriter();
		String data = request.getParameter("submitData");
	    HashMap datalist = (HashMap)Test.JSON.Decode(data);
	    String checksheet_id=ChineseCode.toUTF8((String) datalist.get("checksheet_id"));
		String checkindetl_id=ChineseCode.toUTF8((String) datalist.get("checkindetl_id"));
		String item_id=ChineseCode.toUTF8((String) datalist.get("item_id"));
		String item_name=ChineseCode.toUTF8((String) datalist.get("item_name"));
		String issue_num=ChineseCode.toUTF8((String) datalist.get("issue_num"));
		String item_type=ChineseCode.toUTF8((String) datalist.get("item_type"));
		String spec=ChineseCode.toUTF8((String) datalist.get("spec"));
		String unit=ChineseCode.toUTF8((String) datalist.get("unit"));
		String num=ChineseCode.toUTF8((String) datalist.get("checkin_num"));
		String num1=ChineseCode.toUTF8((String) datalist.get("checkin_num1"));
		String uprice=ChineseCode.toUTF8((String) datalist.get("unitprice"));
		String tprice=ChineseCode.toUTF8((String) datalist.get("price"));
		String stock_id=ChineseCode.toUTF8((String) datalist.get("stock_id"));
		String order_id=ChineseCode.toUTF8((String) datalist.get("order_id"));
		String lot=ChineseCode.toUTF8((String) datalist.get("lot"));
		String quality_id=ChineseCode.toUTF8((String) datalist.get("quality_id"));
		String memo=ChineseCode.toUTF8((String) datalist.get("memo"));
		String warehouse_id=ChineseCode.toUTF8((String) datalist.get("warehouse_id"));
		//String warehouse_id=ChineseCode.toUTF8((String) datalist.get("warehouse_id"));
		double checkin_num=0;
		double checkin_num1=0;
		double unitprice=0;
		double price=0;
		if(num.equals("")){
			checkin_num=0;
		}else{
			checkin_num=Double.parseDouble(num);
		}
		if(uprice.equals("")){
			unitprice=0;
		}else{
			unitprice=Double.parseDouble(uprice);
		}
		if(tprice.equals("")){
			price=0;
		}else{
			price=Double.parseDouble(tprice);
		}
		if(num1.equals("")){
			checkin_num1=0;
		}else{
			checkin_num1=Double.parseDouble(num1);
		}
	    String sql="update mycheckin_detl set item_id='"+item_id+"',item_name='"+item_name+"'," +
	    		"spec='"+spec+"',unit='"+unit+"',checkin_num="+checkin_num+",unitprice="+unitprice+",price="+price+",stock_id='"+stock_id+"',order_id='"+order_id+"'," +
	    				"lot='"+lot+"',quality_id='"+quality_id+"',memo='"+memo+"',item_type='"+item_type+"',issue_num='"+issue_num+"' where  checksheet_id='"+checksheet_id+"' and checkindetl_id='"+checkindetl_id+"'";
	    System.out.println("sql="+sql);
	    try{
			
			Sqlhelper.executeUpdate(sql, null);
			if(checkin_num!=checkin_num1){
				Stockcl.Stockout(item_id, checkin_num1);
				Stockcl.Stockin(item_id, item_name,spec, item_type, warehouse_id, stock_id, checkin_num,unitprice, unit);
			}
			String json = "{\"result\":\"操作成功!\"}";
			
		//	response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			
			}catch(Exception e){
				String json = "{\"result\":\"操作失败!\"}";
		//		response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
				e.printStackTrace();
				
			}
	    
	    
	    
	    
	    
	    
	}

}
