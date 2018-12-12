package com.wl.testaction.warehouse.salesstatement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;

public class RemarkReceipt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RemarkReceipt() {
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

//  	标记出库是否开发票
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String result = "操作成功";
		
		String gridjson =request.getParameter("gridjson");
		String para =request.getParameter("para");		
		JSONArray jarr = JSONArray.fromObject(gridjson);
		
		String checksheetId = "";
		String itemId = "";		//onOperatePower 触发时，会取消当前行的选中状态 
		String orderId = ""; 
		String sqla = "begin ";
		
		for (int i = 0,j=jarr.size();i<j;i++){
			
			checksheetId = "";
			itemId = "";
			orderId = "";
			
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			System.out.println(map1);
				
			checksheetId = (String) (IsJsonNull.isJsonNull(map1.get("checksheetId"))?checksheetId:map1.get("checksheetId"));
			itemId =(String) (IsJsonNull.isJsonNull(map1.get("itemId"))?itemId:map1.get("itemId"));
			orderId =(String) (IsJsonNull.isJsonNull(map1.get("orderId"))?orderId:map1.get("orderId"));
			
			sqla += "update mycheckout_detl t set t.isreceipted="+para+" where t.checksheet_id='"+checksheetId+"' and t.order_id='"+orderId+"' and t.item_id='"+itemId+"'; ";
			
		}
		sqla += " end;";
		
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, null);
			System.out.println("ok "+sqla);
			result="操作成功";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result="操作失败";
		}

		
		String json = "{\"result\":\""+result+"\"}";
		out.append(json).flush();
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
