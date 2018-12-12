package com.wl.testaction.warehouse.productcheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProductCheckin;
import com.wl.forms.SheetId;
import com.wl.tools.CheckSheetId;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.BarcodeLength;
import com.xm.testaction.qualitycheck.SearchFbarcode;

public class getCheckinFormServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getCheckinFormServlet() {
		super();
	}

	
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
		request.setCharacterEncoding("utf-8");
		String codeId=request.getParameter("codeId");
		
		boolean isson = BarcodeLength.barcodeLength(codeId);
		if(isson){
			codeId = SearchFbarcode.searchFbarcode(codeId);
		}
		
		String sql="select B.orderid,B.productid,B.issuenum,C.product_name,C.spec,C.drawingid,C.producttype,E.item_typedesc productTypeDesc,C.unitprice unitPrice from partsplan B " +
				"left join order_detail C on C.order_id=B.orderid and C.product_id=B.productid and C.issue_num=B.issuenum " +
				"left join itemtype E on E.item_typeid=C.producttype " +
				"left join quotation D on D.orderid=B.orderid and D.productid=B.productid " +
				"where B.codeid='"+codeId+"'";
		ProductCheckin productcheckin=new ProductCheckin();
		CheckSheetId checksheetid=new CheckSheetId();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String checkindate=df.format(new Date());
		SheetId checksheet_id=checksheetid.getSheetid();
		
		int seq=checksheet_id.getSeq();
		String id=checksheet_id.getId();
		String sheetId=checksheet_id.getSheetid();
		
		try{
			productcheckin=Sqlhelper.exeQueryBean(sql, null, ProductCheckin.class);
			productcheckin.setChecksheetId(sheetId);
			productcheckin.setId(id);
			productcheckin.setSeq(seq);
			productcheckin.setCheckindate(checkindate);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		String json=PluSoft.Utils.JSON.Encode(productcheckin);
		out.append(json).flush();
		System.out.println(json);
		
		
	}

}
