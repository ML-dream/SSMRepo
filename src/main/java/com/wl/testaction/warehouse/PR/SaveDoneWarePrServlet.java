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

public class SaveDoneWarePrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveDoneWarePrServlet() {
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
//		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String gridjson =request.getParameter("gridjson");		
		System.out.println(gridjson);
		String prsheet = request.getParameter("prsheet");	//收货单单号
//		String purId = request.getParameter("purId");	//订货单单号
		
		String purId="";
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
//		
		String inNum = "0";
		String  unitPrice = "0";
		String  price = "0";
		String warehouseId = "";
		String itemTypeName="";
		String json = "{\"result\":\"操作成功!\"}";
		for (int i = 0,j=jarr.size();i<j;i++){
			
			  itemId = "";
			  stockId = "";
			  itemState="3";
			  remark = "";
//			  
			  itemName = "";
			  spec = "";
				
			  unit = "";
			 itemType = "";
				ussage = "";
				
			  prNum = "0";
//				
			  inNum = "0";
			unitPrice = "0";
				 price = "0";
			 warehouseId = "";
			 itemTypeName="";
				
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			System.out.println(map1);
			itemState = (String) (IsJsonNull.isJsonNull(map1.get("status"))?itemState:map1.get("status"));	//
			itemId = (String) (IsJsonNull.isJsonNull(map1.get("itemId"))?itemId:map1.get("itemId"));
			inNum = IsJsonNull.isJsonNull(map1.get("inNum"))?inNum:map1.get("inNum").toString();
			
			if(itemState.equals("0")){	//审核不通过，再看原处理结果，判断是否需要删掉库存
				String statusEd="";
				statusEd=(String) (IsJsonNull.isJsonNull(map1.get("statusEd"))?statusEd:map1.get("statusEd"));
				if(!statusEd.isEmpty()&&statusEd.equals("1")){
					
					double dinNum = 0;
					inNum = IsJsonNull.isJsonNull(map1.get("inNum"))?inNum:map1.get("inNum").toString();
					dinNum = Double.parseDouble(inNum);
//					先判断库存是否大于当前数量
					String stockSql = "select STOCK_NUM from stock where item_id='"+itemId+"'";
					double stockNUm = 0;
					try {
						String temp = Sqlhelper.exeQueryString(stockSql, null);
						stockNUm = Double.parseDouble(temp);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if(stockNUm>=dinNum){
						Stockcl.Stockout(itemId, dinNum);
					}
				}
			}
			stockId = (String) (IsJsonNull.isJsonNull(map1.get("stockId"))?stockId:map1.get("stockId"));
			remark = (String) (IsJsonNull.isJsonNull(map1.get("remark"))?remark:map1.get("remark"));
			
			purId = (String) (IsJsonNull.isJsonNull(map1.get("poSheetid"))?purId:map1.get("poSheetid"));
			
			itemName =(String) (IsJsonNull.isJsonNull(map1.get("itemName"))?itemName:map1.get("itemName"));
			spec = (String) (IsJsonNull.isJsonNull(map1.get("spec"))?spec:map1.get("spec"));
//			
			unit = (String) (IsJsonNull.isJsonNull(map1.get("unit"))?unit:map1.get("unit"));
//			
			unitPrice =  (IsJsonNull.isJsonNull(map1.get("unitPrice"))?unitPrice:map1.get("unitPrice")).toString();
			itemType = (String) (IsJsonNull.isJsonNull(map1.get("itemType"))?itemType:map1.get("itemType"));
			itemTypeName =(String) (IsJsonNull.isJsonNull(map1.get("itemTypeName"))?itemTypeName:map1.get("itemTypeName"));
			warehouseId = (String) (IsJsonNull.isJsonNull(map1.get("warehouseId"))?warehouseId:map1.get("warehouseId"));
			
			String sqla = "update prdetail t set t.status = '"+itemState+"' ,t.stockid='"+stockId+"' ,t.remark='"+remark+"'" +
					" where t.prsheetid='"+prsheet+"' and t.posheetid='"+purId+"' and t.itemid='"+itemId+"'";
			
			try {
				System.out.println(sqla);
				Sqlhelper.executeUpdate(sqla, null);
				System.out.println("ok  "+sqla);
				json = "{\"result\":\"操作成功!\"}";
				
			} catch (Exception e) {
				// TODO: handle exception
				
				json = "{\"result\":\"操作失败!\"}";
//				response.getWriter().append(json).flush();
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
					e.printStackTrace();
				}
			}
			
		}
		System.out.println(json);
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
