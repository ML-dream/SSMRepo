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
import com.xm.testaction.qualitycheck.sum.CostCaculate;
import com.xm.testaction.qualitycheck.sum.RejectView;

public class LoadCustomerOrders extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadCustomerOrders() {
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
//		综合查询，客户下的订单详情
		System.out.println(this.getClass().getName());
		request.setCharacterEncoding("utf-8");
		String bdate = request.getParameter("bdate");
		String edate = request.getParameter("edate");
		String companyId = request.getParameter("customer");
		
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
//		订单质量损失
		RejectView.orderLoss();
//		订单总价视图
		String priceView = "create or replace view orderprice " +
				"as " +
				"select t.order_id,t.customer,sum(a.unitprice*a.purduct_num) orderprice from orders t " +
				"left join order_detail a on a.order_id = t.order_id " +
				"where t.customer ='"+companyId+"' and t.order_date between to_date('"+bdate+"','yyyy-MM-ddHH:mi:ss') and to_date('"+edate+"','yyyy-MM-ddHH:mi:ss')" +
				"group by t.order_id,t.customer";
		try {
			System.out.println(priceView);
			Sqlhelper.executeUpdate(priceView, null);
			System.out.println("ok  "+priceView);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		订单成本视图
		String costView = "create or replace view customerOrdersView " +
				"    as " +
				"select t.orderid,sum(nvl(t.manuc,0)) manuc,sum(nvl(t.allc,0)) allc from customerorders t " +
				"group by t.orderid";
		try {
			System.out.println(costView);
			Sqlhelper.executeUpdate(costView, null);
			System.out.println("ok  "+costView);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		查找结果    ,round((t.orderprice-a.allc)/a.allc,2) profitrate
		String sqla ="select * from ("+
			"select t.order_id orderid,a.manuc,a.allc,t.orderprice,(t.orderprice-a.allc) profit,b.order_status orderStatus,b.order_status,d.qualityLoss, rownum rn " +
			" from orderprice t " +
				" left join customerOrdersView a on t.order_id =a.orderid " +
				" left join orders b on b.order_id = t.order_id " +
				" left join orderLoss d on d.orderId = t.order_id "+
				" order by orderid asc)" +
				" where  rn>"+min+" and rn <="+max;
		List<BeanCustomerOrders> lista = new ArrayList<BeanCustomerOrders>();
		ResultSet rsa = null;
		try {
			System.out.println(sqla);
			rsa = Sqlhelper0.executeQuery(sqla, null);
			while(rsa.next()){
				BeanCustomerOrders bean = new BeanCustomerOrders();
				bean.setOrderid(rsa.getString(1));
				bean.setManuc(rsa.getDouble(2));
				bean.setAllc(rsa.getDouble(3));
				bean.setOrderprice(rsa.getDouble(4));
				bean.setProfit(rsa.getDouble(5));
				
				double allc = rsa.getDouble(3);
				if(allc ==0){
					bean.setProfitrate("0");
				}else{
					double rate = bean.getProfit()/allc;
					String s = CostCaculate.douToStr(rate);
					bean.setProfitrate(s);
				}
				
				bean.setOrderStatus(rsa.getString(7));
				bean.setQualityLoss(rsa.getDouble(8));
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
		String sqlb = "select count(*) from (" +
			"select t.order_id orderid from orderprice t " +
				")";
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
		
		String sqlc ="select * from ("+
				"select to_char(sum(a.manuc)) Smanuc,to_char(sum(a.allc)) Sallc,to_char(sum(t.orderprice)) Sorderprice,to_char(sum((t.orderprice-a.allc))) Sprofit," +
				"to_char(sum(d.qualityLoss)) SqualityLoss " +
				" from orderprice t " +
					" left join customerOrdersView a on t.order_id =a.orderid " +
					" left join orders b on b.order_id = t.order_id " +
					" left join orderLoss d on d.orderId = t.order_id )";
		
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
