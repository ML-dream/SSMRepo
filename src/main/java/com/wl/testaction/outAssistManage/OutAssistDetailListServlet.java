package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;
import com.wl.forms.OutAssist;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class OutAssistDetailListServlet extends HttpServlet {

	private static final long serialVersionUID = 7275440415083227921L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "itemId";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());

	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
	    
	    String totalCountSql = "select count(*) from outAssistDetail where createPerson=? ";
	    String[] params1 = {userId};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql= "select orderId,itemId,itemName,drawingId,num," +
	    		"numUnit,unitPrice detailunitPrice,totalPrice detailtotalPrice,detail detaildetail,startDate detailstartDate," +
	    		"planEndDate detailplanEndDate,endDate  detailendDate " +
		    	"from (select A.*,ROWNUM row_num from (select EM.* from outAssistDetail EM where createPerson=? order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
		    	"where row_num>="+((pageNo-1)*countPerPage+1)+" and orderId=? order by "+orderStr;
		
		String[] params = {userId,orderId};
		List<OutAssist> customerList = new ArrayList<OutAssist>();
		try {
			customerList = Sqlhelper.exeQueryList(sql, params, OutAssist.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(customerList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);

//	    System.out.println("sql=="+sql);
//	    
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(sql, null);
//			List<OutAssist> customerList = new ArrayList<OutAssist>();
//			try {
//				while (rs.next()) {
//					OutAssist result = new OutAssist();
//
//					result.setOrderId(rs.getString(1));
//					result.setItemId(rs.getString(2));
//					result.setItemName(rs.getString(3));
//					result.setDrawingId(rs.getString(4));
//					result.setNum(rs.getInt(5));
//					
//					result.setNumUnit(rs.getString(6));
//					result.setDetailunitPrice(rs.getDouble(7));
//					result.setDetailtotalPrice(rs.getDouble(8));
//					result.setDetaildetail(rs.getString(9));
//					result.setDetailstartDate(rs.getString(10));
//					result.setDetailplanEndDate(rs.getString(11));
//					result.setDetailendDate(rs.getString(12));
//					
//					customerList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(customerList);
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(rs!=null){
//					rs.close();
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













