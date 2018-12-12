package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper0;

public class UpdateFoItem extends HttpServlet {
// 质检选择刀具
	/**
	 * Constructor of the object.
	 */
	public UpdateFoItem() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

		doPost(request, response);
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
		String data = request.getParameter("data");
		ArrayList datalist = (ArrayList)Test.JSON.Decode(data);
		
		String barcode = "";
		int fo_no=0;
		String itemid= "";
		int itemnum = 0;
		int ifnew =1;	//默认为新增数据
		String aitemid = "";	//修改前的工序号
		
		 for(int i=0,l=datalist.size(); i<l; i++){
		    	HashMap datamap = (HashMap)datalist.get(i);
				 barcode = datamap.get("barcode") != null ? datamap.get("barcode").toString() : "";
		         itemid = datamap.get("itemid") != null ? datamap.get("itemid").toString() : "";
				 fo_no =Integer.parseInt(datamap.get("fo_no") != null ? datamap.get("fo_no").toString() : "0");
				 itemnum = Integer.parseInt(datamap.get("itemnum") != null ? datamap.get("itemnum").toString() : "0");
				 ifnew = Integer.parseInt(datamap.get("ifnew") != null ? datamap.get("ifnew").toString() : "1");
				 
				 aitemid = datamap.get("aitemid") != null ? datamap.get("aitemid").toString() : "";
		 }
		 String sql ="";
		 if(ifnew==1){	//是否新增刀具
			 sql ="insert into foitem (barcode,fo_no,itemid,itemnum) values ('"+barcode+"',"+fo_no+",'"+itemid+"',"+itemnum+")";
			 
		 }else{
			 sql="update foitem set itemid='"+itemid+"',itemnum="+itemnum +" where barcode='"+barcode+"' and fo_no="+fo_no+" and itemid='"+aitemid+"'";
			 }
		 try {
			Sqlhelper0.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			Sqlhelper0.close();
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("success").flush();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
