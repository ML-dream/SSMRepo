package com.wl.testaction.Ll;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.StockInfo;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetWarehouseItemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetWarehouseItemServlet() {
		super();
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    /*String orderStr = "product_id";*/
	  //  String orderStr = "item_id";
	 //   orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String warehouseId=request.getParameter("warehouseId");
	    String itemName=request.getParameter("itemName");
	   
	    
	    String totalCountSql = "select count(*) from stock where warehouse_id='"+warehouseId+"' and item_name like '%"+itemName+"%'";
		String sql="select item_id itemId,item_name itemName,spec,unit,stock_num stockNum,stock_id stockId,unitprice unitPrice,item_type itemType,C.item_typedesc itemTypeDesc " +
			"from(select A.*,rownum row_num from(select EM.* from stock EM where warehouse_id='"+warehouseId+"' and item_name like '%"+itemName+"%' order by item_id asc) A where rownum<="+countPerPage*pageNo+" order by item_id) B " +
					"left join itemtype C on C.item_typeid=B.item_type " +
					"where row_num>="+(countPerPage*(pageNo-1)+1)+" order by item_id";
	    
	    
	//    String[] params = {type};
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
//		String sql= "select  ITEM_ID itemid,B.ITEM_TYPEID itemtypeid,C.ITEM_TYPEDESC itemType,ITEM_NAME itemname,ITEM_DRAWING itemdrawing,MATERIAL_MARK materialmark," +
//	    	"ITEM_ATTRI itemattri,ITEM_SPECIFICATION itemspecification,ITEM_STATUS itemstatus,UNIT_M unitm,LOT_SIZE lotsize," +
//	    	"ORDER_MIN ordermin,LEAD_TIME leadtime,VAR_LEAD_TIME varleadtime,ABC_CODE abccode,LLC LLc," +
//	    	"SAFE_STOCK safestock,STOCK_HIGH stockhigh,STOCK_LOW stocklow,MPS_FLAG mpsflag,ITEM_WEIGHT itemweight," +
//	    	"WEIGHT_UNIT weightunit,YEILD yeild,P_TYPE ptype,PURCHASE_UNITE purchaseunite,PLAN_UNITE planunite," +
//	    	"SUB_PRODUCT subproduct,COST_METHOD costmethod,BACKFLASH_FLAG backflash,ITEM_PRICE itemprice,PRICE_UNIT priceunit," +
//	    	"CURRENCY currency,RMB_PRICE rmbprice,EXTRA_A extraA,EXTRA_B extraB,B.MEMO memo," +
//	    	"MTL_SORT mtlsort,ITEM_A itemA,ITEM_B itemB,ITEM_C itemC,ITEM_D itemD," +
//	    	"CREATE_TIME createtime,UPDATE_TIME updatetime,CREATE_PERSON createperson,UPDATE_PERSON updateperson,FITEM_ID fitemid,t.order_id orderid,t.issue_num issuenum " +
//	    	" from (select A.*,ROWNUM row_num from (select EM.* from item EM  order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
//	    	"left join order_detail t on t.product_id=B.item_id " +
//	    	"left join itemtype C on C.item_typeid=B.item_typeid "+
//	    	" where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	 //   String[] params2 = {type};
	    List<StockInfo> resultList = new ArrayList<StockInfo>();
	    
	    try {
			resultList = Sqlhelper.exeQueryList(sql, null, StockInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(resultList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
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

		doGet(request,response);
	}

}
