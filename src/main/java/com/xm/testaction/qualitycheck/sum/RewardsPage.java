package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

public class RewardsPage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RewardsPage() {
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

		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
//		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
//
//		int year = c.get(Calendar.YEAR); 
//		int month = c.get(Calendar.MONTH);	//+1  是当前月 
//		int date = c.get(Calendar.DATE); 
		
//		String bdate= "" +year +"-"+month+"-"+date;
//		String edate= "" +year +"-"+(month+1)+"-"+date;
		
		String orderStr = "";
		orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
		String sortOrder = "asc";
		
		sortOrder = StringUtil.isNullOrEmpty(request.getParameter("sortOrder"))?sortOrder:request.getParameter("sortOrder");
//		bdate = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?bdate:request.getParameter("bdate");
//		edate = StringUtil.isNullOrEmpty(request.getParameter("edate"))?edate:request.getParameter("edate");
		
		String orderId = StringUtil.isNullOrEmpty(request.getParameter("orderId"))?"":request.getParameter("orderId").trim();
		String productId = StringUtil.isNullOrEmpty(request.getParameter("productId"))?"":request.getParameter("productId").trim();
		
//		创建 视图
//		RewardsView.rewardsView(bdate, edate);
		
//		从视图中查找数据
		String sqla = "select * from(" +
							"select order_id, drawingid, product_name, t.barcode, t.fo_no, fo_opname, rated_time, accept_num, reject_num, usedtime," +
							" t.workerid, t.basetime, workername ,a.rewardstime,t.remark ,rownum rn from rewardstemp t " +
							" left join rewardsTime a on a.barcode = t.barcode and a.fo_no = t.fo_no " +
							"where t.order_id like '%"+orderId+"' and t.drawingid like '%"+productId+"' " +
							"order by order_id asc,drawingid asc,fo_no asc) "+
							"where rn>"+min+" and rn <= "+max;
//		order_date asc,
//		if(orderStr.equals("reject_num")){
//			sqla = "select * from(" +
//				"select order_id, drawingid, product_name, t.barcode, t.fo_no, fo_opname, rated_time, accept_num, reject_num, usedtime," +
//				" t.workerid, t.basetime, workername ,a.rewardstime,t.remark ,rownum rn from rewardstemp t " +
//				" left join rewardsTime a on a.barcode = t.barcode and a.fo_no = t.fo_no " +
//				"order by "+orderStr+" "+sortOrder+",order_date asc,order_id asc,drawingid asc,fo_no asc) "+
//				"where rn>"+min+" and rn <= "+max;
//		}
		
		String sqlb ="select count(*) from (" + 
						"select order_id,drawingid,barcode from rewardstemp t " +
						"where t.order_id like '%"+orderId+"' and t.drawingid like '%"+productId+"' " +
						")";
		
		int total = 0 ;
		ResultSet totalRs = Sqlhelper0.executeQuery(sqlb, null);
		try {
					
			totalRs.next();
			total = totalRs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(totalRs!=null){
					totalRs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		ResultSet rsa = null;
		ArrayList<RewardsBean> waitList = new ArrayList<RewardsBean>();
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			
			while (rsa.next()){
				RewardsBean bean = new RewardsBean();
				
				bean.setOrderId(rsa.getString(1));
				bean.setProductId(rsa.getString(2));
				bean.setDrawingId(rsa.getString(2));
				bean.setProductName(rsa.getString(3));
				bean.setBarcode(rsa.getString(4));
				bean.setFoNo(rsa.getString(5));
				
				bean.setFoOpname(rsa.getString(6));
				bean.setRatedTime(rsa.getString(7));
				bean.setAcceptNum(rsa.getString(8));
				bean.setRejectNum(rsa.getString(9));
				bean.setUsedtime(rsa.getString(10));
				
				bean.setWorkerId(rsa.getString(11));
				bean.setBasetime(rsa.getString(12));
				bean.setWorkerName(rsa.getString(13));
				bean.setRewardstime(rsa.getString(14));
				bean.setRemark(rsa.getString(15));
				
				waitList.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(rsa!=null){
					rsa.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String json = PluSoft.Utils.JSON.Encode(waitList);
		json = "{\"total\":"+total+",\"data\":"+json+"}";
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
