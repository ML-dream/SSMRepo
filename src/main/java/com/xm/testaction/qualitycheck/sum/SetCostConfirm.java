package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

public class SetCostConfirm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SetCostConfirm() {
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
//		
//		String bdate= "" +year +"-"+month+"-"+date;
//		String edate= "" +year +"-"+(month+1)+"-"+date;
		String orderId = "";
//		bdate = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?bdate:request.getParameter("bdate");
//		edate = StringUtil.isNullOrEmpty(request.getParameter("edate"))?edate:request.getParameter("edate");
		orderId = StringUtil.isNullOrEmpty(request.getParameter("orderId"))?orderId:request.getParameter("orderId").trim();
//		从统计表里查找数据，costinput 表
		String sqlc = "select orderid,draid,stuff,dia,len,wid,hei,islailiao,remark,outasist from " +
				"(select  t.*,rownum rn " +
				" from COSTINPUT t " +
				" left join orders a on a.order_id = t.orderid" +
				" where t.stufflevel='1' and t.orderid= '"+orderId+"' " +
								"order by t.orderid asc,t.draid asc )"+
								" where  rn>"+min+" and rn <="+max ;
		
		List<Map<String, String>> lista = new ArrayList<Map<String,String>>();
		ResultSet rsc = null;
		String val = "";
		try {
			rsc = Sqlhelper0.executeQuery(sqlc, null);
			while(rsc.next()){
				Map<String, String> mapa = new HashMap<String, String>();
				mapa.put("orderid", rsc.getString(1));
				mapa.put("draid", rsc.getString(2));
				mapa.put("stuff", rsc.getString(3));
				mapa.put("dia", rsc.getString(4));
				mapa.put("len", rsc.getString(5));
				
				mapa.put("wid", rsc.getString(6));
				mapa.put("hei", rsc.getString(7));
				mapa.put("islailiao", rsc.getString(8));
				mapa.put("remark", rsc.getString(9));
				mapa.put("outasist", rsc.getString(10));
				
				lista.add(mapa);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsc != null){
				try {
					rsc.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		创建 视图
//		CostConfirmView.costConfirmView(bdate, edate);
//		OutAsistCost.outAsistView(bdate, edate);
		String sqla = "select customer, companyname, order_id, order_date, endtime, product_id, drawingid, product_name, purduct_num, matirial, roughsize, islailiao,stuffname,stuffpri" +
				",dia,len,wid,hei,pack,trans,realc,outasist,density,stucost" +
				" from " +
				" (select t.*,m.dia,m.len,m.wid,m.hei,m.pack,m.trans,m.realc,rownum rn,n.outasist,m.stucost " +
				"from costconfirmview t" +
				" left join costinput m on m.orderid = t.order_id and m.draid = t.product_id and m.stufflevel='1'" +
				" left join outAsistView n on n.order_id = t.order_id and n.productid = t.product_id "+
				" where t.order_id='"+orderId+"' order by t.product_id asc)" +
				" where  rn>"+min+" and rn <="+max +
				"";
		String sqlb = "select count(1) from (select t.customer,t.order_id from costconfirmview t where t.order_id='"+orderId+"')";
		
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
		ArrayList<CostConfirmBean> waitList = new ArrayList<CostConfirmBean>();
		String issup = "是";
		String islailiao = "";
		String roughsize = "";
		
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			
			while (rsa.next()){
				CostConfirmBean bean = new CostConfirmBean();
				
				bean.setCustomer(rsa.getString(2));
				bean.setOrderid(rsa.getString(3));
				bean.setRectime(rsa.getString(4));
				bean.setDeltime(rsa.getString(5));
				
				bean.setDraid(rsa.getString(6));	//这里取的是产品号 
				bean.setProname(rsa.getString(8));
				bean.setPronum(rsa.getString(9));
				
				islailiao = rsa.getString(12);
				
				issup= AidCostConfirm.isSup(islailiao);
				bean.setIssup(issup);
				
				bean.setDensity(rsa.getDouble(23));
				bean.setStuffpri(rsa.getDouble(14));
				
				bean.setStuff(rsa.getString(10));	//牌号，13为材料名
				roughsize = rsa.getString(11);
				bean.setRsize(roughsize);
				
				try {
					RoughsizeToVol.roughsizeToVol(roughsize, bean);	//毛坯尺寸解析 
				} catch (Exception e) {
					// TODO: handle exception
				}
				String dia = rsa.getString(15);
				if(dia!=null&&!dia.isEmpty()){
					bean.setDia(dia);
				}
				String len = rsa.getString(16);
				if(len!=null&&!len.isEmpty()){
					bean.setLen(len);
				}
				String stucost =rsa.getString(24);
				if(stucost==null||stucost.isEmpty()||stucost.equals("0")){
					try {
						stucost =CostCaculate.inputStuff(bean);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				bean.setStucost(stucost);
				
				bean.setPack(rsa.getString(19));
				bean.setTrans(rsa.getString(20));
				bean.setRealc(rsa.getString(21));
//				取外协数据
				bean.setOutasist(rsa.getString(22));
//				判断统计表是否有数据
				StuffSource.stuffSource(rsa, bean, lista);
				waitList.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
