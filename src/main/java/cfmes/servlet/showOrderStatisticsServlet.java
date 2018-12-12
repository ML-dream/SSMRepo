 package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;

public class showOrderStatisticsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showOrderStatisticsServlet() {
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
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String orderId=request.getParameter("orderId");
		String totalCountSql="select count(*) from order_detail where order_id='"+orderId+"'";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String orderSql="select B.order_id orderId,B.product_id productId,B.product_name productName,B.issue_num issueNum," +
		"B.drawingid drawingId,B.purduct_num productNum,B.alreadypaynum alreadyPayNum,C.checkinnum checkinNum from " +
		"(select A.*,rownum row_num from (select EM.* from order_detail EM  where order_id='"+orderId+"' order by order_id asc) A where rownum<="+(pageSize*pageNow)+" order by order_id) B " +
		"left join productcheckin C on C.orderid=B.order_id and C.productid=B.product_id and C.issuenum=B.issue_num " +
		"where row_num>="+(pageSize*(pageNow-1)+1)+" order by order_id";
		List<Order> resultList=new ArrayList<Order>();
		try{
			resultList=Sqlhelper.exeQueryList(orderSql,null,Order.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i=0,len=resultList.size();i<len;i++){
			Order order=new Order();
			order=resultList.get(i);
			int productNum=order.getProductNum();
			int checkinNum=order.getCheckinNum();
			int makingNum=productNum-checkinNum;
			order.setMakingNum(makingNum);
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
