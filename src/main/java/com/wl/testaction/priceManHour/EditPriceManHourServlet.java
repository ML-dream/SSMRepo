package com.wl.testaction.priceManHour;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.PriceManHour;
import com.wl.tools.Sqlhelper;

public class EditPriceManHourServlet extends HttpServlet {
	private static final long serialVersionUID = 3901028188085958181L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String orderId = request.getParameter("craftId");
	
		String	priceManHourSql = 
		    	"select craftid,craftname,price,unit from pricemanhour  ";
		
		PriceManHour priceManHour = new PriceManHour();
		
		try {
			priceManHour = Sqlhelper.exeQueryBean(priceManHourSql, null, PriceManHour.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("priceManHour", priceManHour);
		
		
		
		
//	    System.out.println("priceManHourSql=="+priceManHourSql);
//	    
//	    ResultSet priceManHourRs =null;
//		try{
//			priceManHourRs = Sqlhelper.executeQuery(priceManHourSql, null);
//			
//			priceManHourRs.next();
//			PriceManHour priceManHour = new PriceManHour();
//			priceManHour.setCraftId(priceManHourRs.getString(1));
//			priceManHour.setCraftName(priceManHourRs.getString(2));
//			priceManHour.setPrice(priceManHourRs.getInt(3));
//			priceManHour.setUnit(priceManHourRs.getString(4));
//			
//			request.setAttribute("priceManHour", priceManHour);
//			
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
		request.getRequestDispatcher("priceManHourManage/editpriceManHour.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













