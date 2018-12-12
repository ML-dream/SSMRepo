package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CheckoutForm;
import com.wl.tools.Sqlhelper;

public class CheckoutFormServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckoutFormServlet() {
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
		String orderId=request.getParameter("orderId");
		String productId=request.getParameter("productId");
		String warehouseId=request.getParameter("warehouseId");
		int noPayNum=0;
		String Sql="select PRODUCT_ID productId,PRODUCT_NAME productName,ISSUE_NUM issueNum,drawingid drawingId,PRODUCTTYPE productType,C.item_typedesc productTypeDesc," +
				"A.SPEC spec,PURDUCT_NUM purductNum,A.UNITPRICE unitPrice,alreadyPayNum,B.unit_m unit,T.stock_id stockId,T.stock_num stockNum from order_detail A " +
				"left join item B on A.product_id=B.item_id " +
				"left join itemtype C on C.item_typeid=A.producttype " +
				"left join stock T on T.item_id=A.product_id "+
				"where order_id=? and product_id=? and T.warehouse_id=?";
		String[] params={orderId,productId,warehouseId};
		CheckoutForm checkoutForm=new CheckoutForm();
		try{
			checkoutForm=Sqlhelper.exeQueryBean(Sql, params, CheckoutForm.class);
			int purductNum=checkoutForm.getPurductNum();
			int alreadyPayNum=checkoutForm.getAlreadyPayNum();
			noPayNum=purductNum-alreadyPayNum;
			checkoutForm.setNoPayNum(noPayNum);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(checkoutForm);
//		json="{\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}
}
