package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

public class SeeCostConfirm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SeeCostConfirm() {
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
//取参数时，应做非空判断，如果一些必要的数据没有，将会影响到全局计算

		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH);	//+1  是当前月 
		int date = c.get(Calendar.DATE); 
		
		String bdate= "" +year +"-"+month+"-"+date;
		String edate= "" +year +"-"+(month+1)+"-"+date;
		
		bdate = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?bdate:request.getParameter("bdate");
		edate = StringUtil.isNullOrEmpty(request.getParameter("edate"))?edate:request.getParameter("edate");
		
//		查询各种计算参数
		String sqla = "select t.paid,t.pavalue from costpara t ";
		HashMap<String, Double> mapa = new HashMap<String, Double>();
		ResultSet rsa = null;
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			String key = "";
			double value = 0;
			
			while(rsa.next()){
				key = rsa.getString(1);
				value = rsa.getDouble(2);
				mapa.put(key, value);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa != null){
				try {
					rsa.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
//		更新工时视图数据
		RewardsView.rewardsView(bdate, edate);
//		创建 视图
		CostCaculate.timec(bdate, edate);	//人工成本
		CostConfirmView.costConfirmView(bdate, edate);
		
		
//		获取计算值
		String  sqlb = "select customer, companyname, order_id, order_date, endtime, product_id, drawingid, product_name, purduct_num, stuff, roughsize, nvl(islailiao,1),stuffname,stuffpri" +
		",nvl(dia,0),nvl(len,1),nvl(wid,1),nvl(hei,1),nvl(pack,0),nvl(trans,0),nvl(realc,0),price,density,timec,lailiao,nvl(outasist,0),nvl(stucost,0)" +
		" from " +
		" (select t.*,m.stuff,m.dia,m.len,m.wid,m.hei,m.pack,m.trans,m.realc,n.price ,m.islailiao lailiao,rownum rn,m.outasist,m.stucost from costconfirmview t" +
		" left join costinput m on m.orderid = t.order_id and m.draid = t.product_id and m.stufflevel = '1' " +
		" left join stuffprice n on m.stuff = n.stuffid"+
		")" +
		" where  rn>"+min+" and rn <="+max +
		" order by order_id asc";
		
		String sqlc = "select count(1) from (select t.customer,t.order_id from costconfirmview t)";
		
		int total = 0 ;
		ResultSet totalRs = Sqlhelper0.executeQuery(sqlc, null);
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
		
		ResultSet rsb = null;
		ArrayList<SeeCostConfirmBean> waitList = new ArrayList<SeeCostConfirmBean>();
		String issup = "是";
		String islailiao = "";
		String roughsize = "";
//		
		
		double focost = 0;	//工序成本
		try {
			rsb = Sqlhelper0.executeQuery(sqlb, null);		//详细数据
			
			while (rsb.next()){
				SeeCostConfirmBean bean = new SeeCostConfirmBean();
				
				bean.setCustomer(rsb.getString(2));
				bean.setOrderid(rsb.getString(3));
				bean.setRectime(rsb.getString(4));
				bean.setDeltime(rsb.getString(5));
				
				bean.setDraid(rsb.getString(7));	//7是图号，6是产品号
				bean.setProductId(rsb.getString(6));
				bean.setProname(rsb.getString(8));
				bean.setPronum(rsb.getString(9));
				bean.setStuff(rsb.getString(10));	//牌号，13为材料名
				
				islailiao = rsb.getString(25);
				
				issup= AidCostConfirm.isSup(islailiao);
				bean.setIssup(issup);
				
				bean.setDia(rsb.getString(15));
				bean.setLen(rsb.getString(16));
				bean.setWid(rsb.getString(17));
				bean.setHei(rsb.getString(18));
				
				bean.setPack(rsb.getString(19));
				bean.setTrans(rsb.getString(20));
				bean.setRealc(rsb.getString(21));
				
				focost = rsb.getDouble(24);
				bean.setOutasist(rsb.getString(26));	//外协成本
				try {
					CostCaculate.alltimec(bean, focost);	//合计人工成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				
//				try{
//					CostCaculate.stuffCaculate(rsb, bean);	//单件材料费
//				}catch (Exception e) {
//					// TODO: handle exception
//				}
				
				bean.setStucost(rsb.getString(27));
				double para = mapa.get("manuc");	//系数
				try {
					CostCaculate.pmanuc(rsb, bean, para);	//单件制造成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.ptaxc(bean, mapa);	//单件税费
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.midc(bean, mapa);	//期间费用
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.pcost(bean, mapa);	//单件总成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.acost(bean, rsb);	//总成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.pconfirm(bean, mapa);	//核算单价
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.sale(bean, rsb);	//销售额
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.profit(bean);		//利润
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.stuffc(bean);		//总材料费
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.cconfirm(bean);	//核算总成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				
//				System.out.println(bean);
				waitList.add(bean);	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(rsb!=null){
					rsb.close();
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
