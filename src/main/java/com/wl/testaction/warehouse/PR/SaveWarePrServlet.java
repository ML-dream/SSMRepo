package com.wl.testaction.warehouse.PR;

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
import com.wl.tools.Stockcl;
import com.wl.tools.StockinItem;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;

public class SaveWarePrServlet extends HttpServlet {
//	库房审核采购收货入库
	/**
	 * Constructor of the object.
	 */
	public SaveWarePrServlet() {
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
		
		String gridjson =request.getParameter("gridjson");		
		System.out.println(gridjson);
		String prsheet = request.getParameter("prsheet");	//收货单单号
//		String purId = request.getParameter("purId");
		
		String purId ="";
		JSONArray jarr = JSONArray.fromObject(gridjson);
		
		String itemId = "";
		String stockId = "";
		String itemState = "";
		String remark = "";
		
		String itemName = "";
		String spec = "";
		
		String unit = "";
		String itemType = "";
		String ussage = "";
		
		String  prNum = "0";
		
		String inNum = "0";
		String  unitPrice = "0";
		String  price = "0";
		String warehouseId = "";
		String itemTypeName="";
		String json = "{\"result\":\"操作成功!\"}";
		for (int i = 0,j=jarr.size();i<j;i++){
			json = "{\"result\":\"操作成功!\"}";
			  itemId = "";
			  stockId = "";
			  itemState="3";
			  remark = "";
			  
			  itemName = "";
			  spec = "";
				
			  unit = "";
			 itemType = "";
				ussage = "";
				
			  prNum = "0";
				
			  inNum = "0";
			unitPrice = "0";
				 price = "0";
			 warehouseId = "";
			 itemTypeName="";
				
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			System.out.println(map1);
			itemState = (String) (IsJsonNull.isJsonNull(map1.get("itemState"))?itemState:map1.get("itemState"));	//库房审核不通过，不存数据。
//			if(itemState.equals("0")){
//				continue;
//			}
			inNum = (String) (IsJsonNull.isJsonNull(map1.get("inNum").toString())?inNum:map1.get("inNum").toString());
			stockId = (String) (IsJsonNull.isJsonNull(map1.get("stockId"))?stockId:map1.get("stockId"));
			remark = (String) (IsJsonNull.isJsonNull(map1.get("remark"))?remark:map1.get("remark"));
			itemId = (String) (IsJsonNull.isJsonNull(map1.get("itemId"))?itemId:map1.get("itemId"));
			
			itemName =(String) (IsJsonNull.isJsonNull(map1.get("itemName"))?itemName:map1.get("itemName"));
			spec = (String) (IsJsonNull.isJsonNull(map1.get("spec"))?spec:map1.get("spec"));
			
			unit = (String) (IsJsonNull.isJsonNull(map1.get("unit"))?unit:map1.get("unit"));
//			ussage = (String) (IsJsonNull.isJsonNull(map1.get("ussage"))?ussage:map1.get("ussage"));
//			prNum = (IsJsonNull.isJsonNull(map1.get("prNum"))?prNum:map1.get("prNum")).toString();
			
			unitPrice =  (IsJsonNull.isJsonNull(map1.get("unitPrice"))?unitPrice:map1.get("unitPrice")).toString();
//			price = (IsJsonNull.isJsonNull(map1.get("price"))?price:map1.get("price")).toString();
			itemType = (String) (IsJsonNull.isJsonNull(map1.get("itemType"))?itemType:map1.get("itemType"));
			itemTypeName =(String) (IsJsonNull.isJsonNull(map1.get("itemTypeName"))?itemTypeName:map1.get("itemTypeName"));
			warehouseId = (String) (IsJsonNull.isJsonNull(map1.get("warehouseId"))?warehouseId:map1.get("warehouseId"));
			purId = (String) (IsJsonNull.isJsonNull(map1.get("poSheetid"))?purId:map1.get("poSheetid"));
			
			String sqla = "update prdetail t set t.status = '"+itemState+"' ,t.stockid='"+stockId+"' ,t.remark='"+remark+"'" +
					" where t.prsheetid='"+prsheet+"' and t.posheetid='"+purId+"' and t.itemid='"+itemId+"'";
			
			try {
				System.out.println(sqla);
				Sqlhelper.executeUpdate(sqla, null);
				System.out.println("ok  "+sqla);
				
				//response.setCharacterEncoding("UTF-8");
				
			} catch (Exception e) {
				// TODO: handle exception
				
				json = "{\"result\":\"操作失败!\"}";
				//response.getWriter().append(json).flush();
			}
			
//			收货的物品保存到库房
			if(itemState.equals("1")){
				try {
					double dinNum = Double.parseDouble(inNum);
					double dunitPrice = Double.parseDouble(unitPrice);
					Stockcl.Stockin(itemId,itemName,spec,itemType,warehouseId,stockId,dinNum,dunitPrice,unit);
					StockinItem.ItemStockin(itemId,itemName,itemType,spec,dunitPrice,remark);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
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
