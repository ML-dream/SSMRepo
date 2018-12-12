package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;

public class doeditPrDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditPrDetailServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String prSheetid=(String) datamap.get("prSheetid");
		String poSheetid=(String) datamap.get("poSheetid");
		String itemId=(String) datamap.get("itemId");
		String itemName=(String) datamap.get("itemName");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String itemType=(String) datamap.get("itemType");
		String ussage=(String) datamap.get("ussage");
		String prNum=(String) datamap.get("prNum");
		String prNum1=(String) datamap.get("prNum1");
		String unitprice=(String) datamap.get("unitPrice");
		double unitPrice=unitprice.equals("")?0:Double.parseDouble(unitprice);
		String price=(String) datamap.get("price");
		String taxrate=(String) datamap.get("taxrate");
		String tax=(String) datamap.get("tax");
		String stockId=(String) datamap.get("stockId");
		String memo=(String) datamap.get("memo");
		String warehouseId=(String) datamap.get("warehouseId");
		
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		
		String poSql="update prdetail set posheetid='"+poSheetid+"',spec='"+spec+"',unit='"+unit+"',itemType='"+itemType+"',ussage='"+ussage+"',prNum='"+prNum+"'," +
				"unitPrice="+unitPrice+",price='"+price+"',taxrate='"+taxrate+"',tax='"+tax+"',stockid='"+stockId+"',memo='"+memo+"'," +
				"changeperson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss') " +
				"where prsheetid='"+prSheetid+"' and itemId='"+itemId+"'";
		try{
			Sqlhelper.executeUpdate(poSql, null);
			if(!prNum.equals(prNum1)){
			double Num=prNum.equals("")?0:Double.parseDouble(prNum);
			double Num1=prNum1.equals("")?0:Double.parseDouble(prNum1);
			Stockcl.Stockout(itemId, Num1);
			Stockcl.Stockin(itemId, itemName,spec, itemType, warehouseId, stockId, Num,unitPrice, unit);
			}
			String json = "{\"result\":\"操作成功!\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
	}

}
}
