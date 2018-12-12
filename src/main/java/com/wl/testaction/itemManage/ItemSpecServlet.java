package com.wl.testaction.itemManage;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.Item;
import com.wl.tools.Sqlhelper;

public class ItemSpecServlet extends HttpServlet {
	private static final long serialVersionUID = -5887008420938060963L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String itemId = request.getParameter("itemId");
//	    String connector = ChineseCode.toUTF8(request.getParameter("connector").trim());
	
		String	sql = 
		    	"select item_id itemid,item_typeId itemtypeid,item_name itemname,item_drawing itemdrawing,fitem_id fitemid, " +
		    	"ITEM_SPECIFICATION itemspecification,PURCHASE_UNITE purchaseunite,ITEM_PRICE itemprice,PRICE_UNIT priceunit,MATERIAL_MARK materialmark," +
		    	"ITEM_ATTRI itemattri,LOT_SIZE lotsize,LEAD_TIME leadtime,SAFE_STOCK safestock,STOCK_LOW stocklow," +
		    	"STOCK_HIGH stockhigh,ITEM_WEIGHT itemweight,WEIGHT_UNIT weightunit,MEMO " +
		    	"from item A " +
		    	"where item_id=? ";
		String[] params = {itemId};
		Item result = new Item();
		
		try {
			result = Sqlhelper.exeQueryBean(sql, params, Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("item", result);
		
		request.getRequestDispatcher("itemManage/ItemSpec.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













