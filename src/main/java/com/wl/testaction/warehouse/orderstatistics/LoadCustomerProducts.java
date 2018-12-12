package com.wl.testaction.warehouse.orderstatistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;
import com.xm.testaction.qualitycheck.sum.RejectView;

public class LoadCustomerProducts extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadCustomerProducts() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		综合查询，客户下的产品详情
		System.out.println(this.getClass().getName());
		request.setCharacterEncoding("utf-8");
		String orderId = request.getParameter("orderId");
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
//		是否是返回的页面
		String para = StringUtil.isNullOrEmpty(request.getParameter("para"))?"":request.getParameter("para");
	    if(!para.isEmpty()){
	    	pageIndex = Integer.parseInt(para);
	    }
	    
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
//		质量损失
		RejectView.productLoss();
		
		String sqla = "select product_id productid,product_name productname,manuc,allc,productprice,(productprice-allc) profit,round((productprice-allc)/productprice,2) ,productStatus,productNum,qualityLoss from( " +
				"select t.product_id,t.product_name,a.manuc,a.allc,(t.unitprice*t.purduct_num) productprice,t.product_status,b.statusname productStatus,t.purduct_num productNum,c.qualityLoss,rownum rn " +
				" from order_detail t " +
				"left join customerorders a on t.order_id = a.orderid  and a.productid=t.product_id " +
				"left join productstatus b on t.product_status = b.statusid " +
				"left join productLoss c on c.orderId = t.order_id and c.productId=t.product_id and c.issuenum=t.issue_num "+
				"where t.order_id = '"+orderId+"' order by t.product_id)em " +
					" where  rn>"+min+" and rn <="+max;
		String sqlb = "select count(*) from (select t.product_id from order_detail t where t.order_id = '"+orderId+"') ";
		
		List<BeanCustomerOrders> lista = new ArrayList<BeanCustomerOrders>();
		ResultSet rsa = null;
		try {
			System.out.println(sqla);
			rsa = Sqlhelper0.executeQuery(sqla, null);
			while(rsa.next()){
				BeanCustomerOrders bean = new BeanCustomerOrders();
				
				bean.setProductid(rsa.getString(1));
				bean.setProductName(rsa.getString(2));
				bean.setManuc(rsa.getDouble(3));
				bean.setAllc(rsa.getDouble(4));
				bean.setProductprice(rsa.getDouble(5));
				
				bean.setProfit(rsa.getDouble(6));
				bean.setProfitrate(rsa.getString(7));
				bean.setProductStatus(rsa.getString(8));
				bean.setProductNum(rsa.getInt(9));
				
				bean.setQualityLoss(rsa.getDouble(10));
				lista.add(bean);
			}
			
//			lista = Sqlhelper.exeQueryList(sqla, null, BeanCustomerOrders.class);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa !=null){
				try {
					rsa.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		int total =0;
		try {
			total = Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		各种合计
		String Smanuc= "0";
		String Sallc= "0";
		String SqualityLoss= "0";
		String Sorderprice= "0";
		String Sprofit= "0";
		
		String sqlc ="select to_char(sum(manuc)) Smanuc,to_char(sum(allc)) Sallc,to_char(sum(productprice)) Sorderprice,to_char(sum((productprice-allc))) Sprofit,to_char(sum(qualityLoss)) SqualityLoss from( " +
				"select a.manuc,a.allc,(t.unitprice*t.purduct_num) productprice,c.qualityLoss " +
				" from order_detail t " +
				"left join customerorders a on t.order_id = a.orderid  and a.productid=t.product_id " +
				"left join productstatus b on t.product_status = b.statusid " +
				"left join productLoss c on c.orderId = t.order_id and c.productId=t.product_id and c.issuenum=t.issue_num "+
				"where t.order_id = '"+orderId+"' order by t.product_id)em " ;
		
		BeanCustomerOrders beanc = new BeanCustomerOrders();
		try {
			System.out.println(sqlc);
			beanc =Sqlhelper.exeQueryBean(sqlc, null,BeanCustomerOrders.class );
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String json = PluSoft.Utils.JSON.Encode(lista);
//		json = "{\"total\":"+total+",\"data\":"+json+"}";
		json = "{\"total\":"+total+",\"Smanuc\":"+beanc.getSmanuc()+",\"Sallc\":"+beanc.getSallc()+",\"SqualityLoss\":"+beanc.getSqualityLoss()+",\"Sorderprice\":"+beanc.getSorderprice()+",\"Sprofit\":"+beanc.getSprofit()+",\"data\":"+json+"}";
		System.out.println(json);
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(json).flush();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
