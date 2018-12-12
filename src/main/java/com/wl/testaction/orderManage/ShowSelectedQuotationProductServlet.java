package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.PriceManHour;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class ShowSelectedQuotationProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    String orderStr = "craftname";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffcode=  ((User)session.getAttribute("user")).getStaffCode();
	    
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId"));
		String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId"));
		String issueNum= ChineseCode.toUTF8(request.getParameter("issueNum"));
	    String totalCountSql = "select count(*) from pricemanhour A "+
	    		"left join (select craftid,price,totalPrice,totaltime,grossProfit from quotation where orderId=? and productId=?  and fproductId=? ) B "+
	    		"on B.Craftid=A.Craftid " +
	    		"where B.totalTime>0 ";
	   String[] Params={orderId,productId,fproductId};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, Params);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		

		String OrderSql = "select A.craftid,A.craftname,A.price ,A.unit,B.PRICE addPrice,B.totalTime,B.totalPrice,B.grossProfit " +
			"from pricemanhour A "+
    		"left join (select craftid,price,totalPrice,totaltime,grossProfit from quotation where orderId=? and productId=?  and fproductId=? ) B "+
    		"on B.Craftid=A.Craftid " +
    		"where B.totalTime>0 "+
    		"order by A.craftId ";
		
		String[] params1 = {orderId,productId,fproductId};
		double sum=0.0 ;
		List<PriceManHour> priceManHourList = new ArrayList<PriceManHour>();
		try {
			priceManHourList = Sqlhelper.exeQueryList(OrderSql, params1, PriceManHour.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0,len=priceManHourList.size(); i < len; i++) {
			PriceManHour priceManHour = priceManHourList.get(i);
			priceManHour.setAddPrice(StringUtil.isNullOrEmptyOrZero(priceManHour.getAddPrice())?(priceManHour.getPrice()):(priceManHour.getAddPrice()));
			priceManHour.setTotalTime(StringUtil.isNullOrEmptyOrZero(priceManHour.getTotalTime())?0:(priceManHour.getTotalTime()));
			priceManHour.setTotalPrice(StringUtil.isNullOrEmptyOrZero(priceManHour.getTotalPrice())?0:(priceManHour.getTotalPrice()));
			sum+= StringUtil.isNullOrEmptyOrZero(priceManHour.getTotalPrice())?0:(priceManHour.getTotalPrice());
		}
		
		
//		String totalPriceSql ="select sum(B.totalprice) totalPrice,sum(B.grossProfit) grossProfit "+
//			"from( "+
//			"  select distinct productid id,fproductid,craftid,totalprice,grossProfit "+
//			"  from quotation A where orderId=? and productId=? and createPerson=? and fproductId=? "+
//			"  start with productid=? "+
//			"  connect by prior A.productid=A.fproductid) B";
//		String[] params = {orderId,productId,staffcode,fproductId,productId};
//		
//		Map<String, String> map = new HashMap<String, String>();
//		try {
//			map = Sqlhelper.executeQueryMap(totalPriceSql, params);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		double totalPrice = StringUtil.isNullOrEmpty(map.get("TOTALPRICE"))?0:Double.parseDouble(map.get("TOTALPRICE"));
//		double totalGrossProfit = StringUtil.isNullOrEmpty(map.get("GROSSPROFIT"))?0:Double.parseDouble(map.get("GROSSPROFIT"));
//		
		String json = PluSoft.Utils.JSON.Encode(priceManHourList);
//		json = "{\"data\":\""+json+"\",\"orderId\":\""+orderId+"\",\"productId\":\""+productId+"\",\"sum\":"+sum+",\"total\":"+totalCount+",\"data\":"+json+"}";
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);

	}


}
