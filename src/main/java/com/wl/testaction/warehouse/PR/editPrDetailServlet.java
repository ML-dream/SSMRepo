package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PrDetail;
import com.wl.tools.Sqlhelper;

public class editPrDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editPrDetailServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String prSheetid=request.getParameter("prSheetid");
		String itemId=request.getParameter("itemId");
		String warehouseId=request.getParameter("warehouseId");
//		String prSql="select * from prdetail where prsheetid='"+prSheetid+"' and itemid='"+itemId+"'";
		String prSql="select PRSHEETID,POSHEETID,ITEMID,ITEMNAME,SPEC,UNIT,ITEMTYPE,USSAGE,PRNUM,UNITPRICE,PRICE,TAXRATE,TAX,STOCKID,MEMO,C.ITEM_TYPEDESC itemTypeName " +
		"from prdetail B left join itemtype C on C.item_typeid=B.itemtype where prsheetid='"+prSheetid+"' and itemid='"+itemId+"'";
		
		PrDetail prdetail=new PrDetail();
		
		
		try{
			prdetail=Sqlhelper.exeQueryBean(prSql, null, PrDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("warehouseId", warehouseId);
		request.setAttribute("prdetail", prdetail);
		request.getRequestDispatcher("/PO/editPrDetail.jsp").forward(request, response);
	}

}
