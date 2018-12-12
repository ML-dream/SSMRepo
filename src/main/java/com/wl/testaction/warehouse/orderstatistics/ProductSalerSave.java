package com.wl.testaction.warehouse.orderstatistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.wl.forms.User;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.statejudge.TypeResult;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;

public class ProductSalerSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductSalerSave() {
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
			System.out.println(this.getClass().getName());
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			request.setCharacterEncoding("utf-8");
			String result = "操作成功";
			
			String gridjson =request.getParameter("gridjson");
			
			System.out.println(gridjson);
			
			JSONArray jarr = JSONArray.fromObject(gridjson);
			
			String orderId = "";
			String productId = "";
			String batch = "";
			String productRemark = "";
			
			String qualityFee = "";
			
			for (int i = 0,j=jarr.size();i<j;i++){
				
				orderId = "";
				productId = "";
				batch = "";
				productRemark = "";
				  
				  Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
				  System.out.println(map1);
					
				  orderId = (String) (IsJsonNull.isJsonNull(map1.get("orderId"))?orderId:map1.get("orderId"));
				  productId =(String) (IsJsonNull.isJsonNull(map1.get("productId"))?productId:map1.get("productId"));
				  batch = (String) (IsJsonNull.isJsonNull(map1.get("batch"))?batch:map1.get("batch"));
				  productRemark = (String) (IsJsonNull.isJsonNull(map1.get("productRemark"))?productRemark:map1.get("productRemark"));
				  
				  if(orderId.isEmpty() || productId.isEmpty()){
					  continue;
				  }
				  String sqla = "update order_detail t set t.batch='"+batch+"' ,t.productremark='"+productRemark+"' where t.order_id='"+orderId+"' and t.product_id='"+productId+"'";
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
