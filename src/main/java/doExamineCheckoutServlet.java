import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.OrderItem;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;


public class doExamineCheckoutServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doExamineCheckoutServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String,String>) Test.JSON.Decode(data);
//		String operator=datamap.get("operator");
		String opinion=datamap.get("opinion");
		String warehouse_id=datamap.get("warehouse_id");
		String checksheet_id=datamap.get("checksheet_id");
		String status=request.getParameter("status");
		
		HttpSession session=request.getSession();
		String examinePerson=((User)session.getAttribute("user")).getStaffCode();
		
		String Sql="update mycheckout set warehouse_id='"+warehouse_id+"',opinion='"+opinion+"("+examinePerson+")', " +
				"status='"+status+"',examineperson='"+examinePerson+"' where checksheet_id='"+checksheet_id+"'";
//		if(status.equals("1")){
//			Stockcl.Stockout(productId,outNum);
//			if(checkoutType.equals("0")){
//				String productNumSql="select purduct_num from order_detail where order_id='"+orderId+"' and product_id='"+productId+"' and issue_num='"+issueNum+"'";
//				OrderItem orderitem=new OrderItem();
//				
//				try{
//					orderitem=Sqlhelper.exeQueryBean(productNumSql, null, OrderItem.class);
//					productNum=orderitem.getPurductNum();
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				
//				if(alreadyNum<productNum){
//					orderSql="update ORDER_DETAIL set alreadypaynum='"+alreadyNum+"',product_status=80 where order_id=? and product_id=? and issue_num=?";
//				}else{
//					orderSql="update ORDER_DETAIL set alreadypaynum='"+alreadyNum+"',product_status=90 where order_id=? and product_id=? and issue_num=?";
//				}
//				
//				try{
//					Sqlhelper.executeUpdate(orderSql, params);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
			
//		}
		
		
		
		try{
			Sqlhelper.executeUpdate(Sql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			out.append(json).flush();
			e.printStackTrace();
		}
		
	}

}
