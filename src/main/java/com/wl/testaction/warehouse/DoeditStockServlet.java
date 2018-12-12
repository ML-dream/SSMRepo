package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class DoeditStockServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String data = request.getParameter("submitData");
	    HashMap datalist = (HashMap)Test.JSON.Decode(data);
	    double stockNum=0;
		String itemId=ChineseCode.toUTF8((String) datalist.get("itemId"));
		String itemName=ChineseCode.toUTF8((String) datalist.get("itemName"));
		String spec=ChineseCode.toUTF8((String) datalist.get("spec"));
		String itemType=ChineseCode.toUTF8((String) datalist.get("itemType"));
		String warehouseId=ChineseCode.toUTF8((String) datalist.get("warehouseId"));
		String stockId=ChineseCode.toUTF8((String) datalist.get("stockId"));
		String Num=ChineseCode.toUTF8((String) datalist.get("stockNum"));
		String unit=ChineseCode.toUTF8((String) datalist.get("unit"));
		stockNum=Double.parseDouble(Num);
		
//		 for(int i=0,l=datalist.size(); i<l; i++){
//		    	HashMap datamap = (HashMap) datalist.get(i);
//		    	 itemId = ChineseCode.toUTF8( datamap.get("itemId").toString());
//		    	 itemName=ChineseCode.toUTF8( datamap.get("itemName").toString());
//		    	 warehouseId=ChineseCode.toUTF8( datamap.get("warehouseId").toString());
//		    	 stockId=ChineseCode.toUTF8( datamap.get("stockId").toString());
//		    	 String Num=ChineseCode.toUTF8( datamap.get("stockNum").toString());
//		    	 unit=ChineseCode.toUTF8( datamap.get("unit").toString());
//		    	 stockNum=Integer.parseInt(Num);
//		 }
		 String sql="update stock set item_id='"+itemId+"',item_name='"+itemName+"'," +
			"warehouse_id='"+warehouseId+"',stock_id='"+stockId+"',stock_num="+stockNum+"," +
					"unit='"+unit+"',item_type='"+itemType+"',spec='"+spec+"' where item_id='"+itemId+"' and warehouse_id='"+warehouseId+"'";
		try{
			
		Sqlhelper.executeUpdate(sql, null);
		String json = "{\"result\":\"操作成功!\"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
			
		}
	}

}
