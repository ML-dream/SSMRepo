package com.wl.testaction.craftworkManage;



import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
public class CraftModifyListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
		
	    String totalCountSql = "select count(*) from todiscard  where state='0' ";
	    String[] params1 = {};
	    
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
		
	    String sql= "select C.ORDER_ID orderId,C.PRODUCT_ID productId,C.ISSUE_NUM issueNum,B.FPRODUCT_ID fproductId,b.product_name productName,C.DRAWINGID,state,rejectnum,c.barcode " +
	    	"from (select A.*,ROWNUM  from (select * from todiscard  where state='0') A " +
	    	"where ROWNUM<="+(countPerPage*pageNo)+" and rownum>="+((pageNo-1)*countPerPage+1)+") C "+
	       "left join order_detail B on B.ORDER_ID=C.ORDER_ID AND C.PRODUCT_ID=B.PRODUCT_ID and b.issue_num = c.ISSUE_NUM order by productId";
	    String[] params = {};
	    List<Order> resultList = new ArrayList<Order>();
	    try {
			resultList = Sqlhelper.exeQueryList(sql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(resultList);
//	    json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}







