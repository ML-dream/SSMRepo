package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.ResultSet;
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

import org.apache.commons.collections.map.CompositeMap.MapMutator;

/*import oracle.net.aso.p;*/

import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;
import com.wl.forms.PriceManHour;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class ShowQuotationProductServlet extends HttpServlet {

	private static final long serialVersionUID = -492469948057257491L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
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
	    
	    String totalCountSql = "select count(*) from pricemanhour";
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId"));
		String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId"));
		String name = ChineseCode.toUTF8(request.getParameter("name"));
		String OrderSql = "select A.craftid,A.craftname,A.price ,A.unit,B.PRICE addPrice,B.totalTime,B.totalPrice,B.grossProfit " +
			"from pricemanhour A "+
    		"left join (select craftid,price,totalPrice,totaltime,grossProfit from quotation where orderId=? and productId=? and createPerson=? and fproductId=?) B "+
    		"on B.Craftid=A.Craftid "+
    		"order by A.craftId "
    		;
		
		String[] params1 = {orderId,productId,staffcode,fproductId};
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
		
		
		String totalPriceSql ="select sum(B.totalprice) totalPrice,sum(B.grossProfit) grossProfit "+
			"from( "+
			"  select distinct productid id,fproductid,craftid,totalprice,grossProfit "+
			"  from quotation A where orderId=? and productId=? and createPerson=? and fproductId=? "+
			"  start with productid=? "+
			"  connect by prior A.productid=A.fproductid) B";
		String[] params = {orderId,productId,staffcode,fproductId,productId};
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = Sqlhelper.executeQueryMap(totalPriceSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		double totalPrice = StringUtil.isNullOrEmpty(map.get("TOTALPRICE"))?0:Double.parseDouble(map.get("TOTALPRICE"));
		double totalGrossProfit = StringUtil.isNullOrEmpty(map.get("GROSSPROFIT"))?0:Double.parseDouble(map.get("GROSSPROFIT"));
		
		String json = PluSoft.Utils.JSON.Encode(priceManHourList);
		json = "{\"totalGrossProfit\":\""+totalGrossProfit+"\",\"totalPrice\":\""+totalPrice+"\",\"name\":\""+name+"\",\"fproductId\":\""+fproductId+"\",\"orderId\":\""+orderId+"\",\"productId\":\""+productId+"\",\"sum\":"+sum+",\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
//	    System.out.println("totalPriceSql="+totalPriceSql);
//	    
//	    double totalPrice=0;
//	    double totalGrossProfit=0;
//	    
//	    ResultSet priceManHourRs =null;
//	    ResultSet totalpriceRs=null;
//	    double sum=0.0 ;
//		try{
//			priceManHourRs = Sqlhelper.executeQuery(OrderSql, null);
//			totalpriceRs = Sqlhelper.executeQuery(totalPriceSql, null);
//			
//			List<PriceManHour> priceManHourList = new ArrayList<PriceManHour>();
//			try {
//				while (priceManHourRs.next()) {
//					PriceManHour priceManHour = new PriceManHour();
//					priceManHour.setCraftId(priceManHourRs.getString(1));
//					priceManHour.setCraftName(priceManHourRs.getString(2));
//					priceManHour.setPrice(priceManHourRs.getInt(3));
//					priceManHour.setUnit(priceManHourRs.getString(4));
//					priceManHour.setAddPrice(StringUtil.isNullOrEmpty(priceManHourRs.getString(5))?(priceManHourRs.getInt(3)+""):(priceManHourRs.getString(5)));
//					priceManHour.setTotalTime(StringUtil.isNullOrEmpty(priceManHourRs.getString(6))?"0":priceManHourRs.getString(6));
//					priceManHour.setTotalPrice(StringUtil.isNullOrEmpty(priceManHourRs.getString(7))?"0":priceManHourRs.getString(7));
//					sum+=Double.parseDouble(StringUtil.isNullOrEmpty(priceManHourRs.getString(7))?"0":priceManHourRs.getString(7));
//					priceManHour.setGrossProfit(priceManHourRs.getDouble(8));
//					priceManHourList.add(priceManHour);
//				}
//				
//				totalpriceRs.next();
//				totalPrice = totalpriceRs.getDouble(1);
//				totalGrossProfit =totalpriceRs.getDouble(2);
//				System.out.println(totalPrice);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(priceManHourList);
//			json = "{\"totalGrossProfit\":\""+totalGrossProfit+"\",\"totalPrice\":\""+totalPrice+"\",\"name\":\""+name+"\",\"fproductId\":"+fproductId+",\"orderId\":"+orderId+",\"productId\":"+productId+",\"sum\":"+sum+",\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(priceManHourRs!=null){
//					priceManHourRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













