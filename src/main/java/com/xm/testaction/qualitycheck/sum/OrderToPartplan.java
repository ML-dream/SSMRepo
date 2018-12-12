package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.common.ProductStatus;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.PoFlowBean;

public class OrderToPartplan extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderToPartplan() {
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
		String orderid = request.getParameter("orderId");
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		int totalCount=0;
		String partstatus ="";
		int tem =0;
		
		String json ="";
		String sql ="select * from (select c.*,rownum rn from " +
				"(select a.partsplanid,a.plantime,a.productid,a.partnum,b.product_name,b.order_id,a.partstatus from partsplan a " +
				"left join order_detail b on b.order_id= a.orderid and b.product_id = a.productid " +
				"where a.orderid= '"+orderid+"' order by plantime desc)c )" +
				"where rn>"+min+" and rn<="+max;
		System.out.println(sql);
		
		String totalsql ="select count(*) from (select a.partsplanid,a.plantime,a.productid,a.partnum,b.product_name from partsplan a " +
				"left join order_detail b on b.order_id= a.orderid and b.product_id = a.productid " +
				"where a.orderid= '"+orderid+"' order by plantime desc )";
//		数据条数
		ResultSet totalrs = null;
		try {
			
			totalrs = Sqlhelper0.executeQuery(totalsql, null);
			totalrs.next();
			totalCount = totalrs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(totalrs != null){
		    	try {
		    		totalrs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		ResultSet rs = null;
		try {
			
			rs = Sqlhelper0.executeQuery(sql, null);
			List<OrderToPartplanBean> poList = new ArrayList<OrderToPartplanBean>();
			while(rs.next()){
//					工时定额还未取
					OrderToPartplanBean bean =new OrderToPartplanBean();
					
					bean.setPartsplanid(rs.getString(1));
					bean.setPlantime(rs.getString(2));
					bean.setProduct_id(rs.getString(3));
					bean.setPartnum(rs.getInt(4));
					bean.setProduct_name(rs.getString(5));
					bean.setOrder_id(rs.getString(6));
					
//					9-9 零件计划状态
					try {
						tem= rs.getInt(7);
					} catch (Exception e) {
						// TODO: handle exception
					}
//				9-10 product status  产品状态	
					if (tem < ProductStatus.DONE){
						partstatus ="生产中";
					}else {
						partstatus ="完成";
					}
					bean.setPstate(partstatus);
					poList.add(bean);
				}
			json = PluSoft.Utils.JSON.Encode(poList);
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rs != null){
		    	try {
				rs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		System.out.println(json);
		response.setCharacterEncoding("UTF-8");
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
