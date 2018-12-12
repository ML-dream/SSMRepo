package com.wl.testaction.priceManHour;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.tools.StringUtil;
import com.wl.forms.PriceManHour;
import com.wl.tools.Sqlhelper;

public class ShowPriceManHourServlet extends HttpServlet {
	private static final long serialVersionUID = -5867985029243100916L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    String orderStr = "craftname";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String totalCountSql = "select count(*) from pricemanhour";
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String OrderSql= "select craftid,craftname,price,unit " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from pricemanhour EM order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	
	    List<PriceManHour> priceManHourList = new ArrayList<PriceManHour>();
	    try {
			priceManHourList = Sqlhelper.exeQueryList(OrderSql, null, PriceManHour.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(priceManHourList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
//	    System.out.println("OrderSql=="+OrderSql);
//	    
//	    ResultSet priceManHourRs =null;
//		try{
//			priceManHourRs = Sqlhelper.executeQuery(OrderSql, null);
//			List<PriceManHour> priceManHourList = new ArrayList<PriceManHour>();
//			
//			try {
//				while (priceManHourRs.next()) {
//					PriceManHour priceManHour = new PriceManHour();
//					priceManHour.setCraftId(priceManHourRs.getString(1));
//					priceManHour.setCraftName(priceManHourRs.getString(2));
//					priceManHour.setPrice(priceManHourRs.getInt(3));
//					priceManHour.setUnit(priceManHourRs.getString(4));
//					priceManHourList.add(priceManHour);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(priceManHourList);
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
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













