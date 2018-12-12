package com.wl.testaction.warehouse.orderstatistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;

public class ProductInDetail extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductInDetail() {
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
		Logger log = Logger.getLogger("ProductInDetail");
		log.debug(this.getClass().getName());
		
		request.setCharacterEncoding("utf-8");
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String para = request.getParameter("para").trim();		//in表示入库，out表示出库
		String productId = request.getParameter("productId").trim();
		
		String sqla = "";
		String sqlb = "";
		String sqlc = "";
		if(para.equals("in")){
			sqla = "select to_char(checkindate,'yyyy-Mm-dd ') checkdate,checksheetid,drawingid,productname,to_char(checkinnum) num,c.staff_name createrName,d.staff_name checkerName from (" +
					"select a.*,rownum rn from(" +
					"       select * from productcheckin t where  t.productid='"+productId+"' and t.status='1' order by t.checkindate desc) a   " +
					"    order by checkindate desc) b " +
					"left join employee_info c on c.staff_code = b.createperson" +
					" left join employee_info d on d.staff_code = b.whstaffid " +
					"where rn >"+min+" and rn <="+max+" order by checkdate desc";
			sqlb = "  select count(1) from productcheckin t where t.productid='"+productId+"' and t.status='1'";
			sqlc = "select to_char(sum(t.checkinnum)) sumNum from productcheckin t where t.productid='"+productId+"' and t.status='1'";
		}else{
//			出库
			sqla = "select to_char(checkout_date,'yyyy-Mm-dd') checkdate, checksheet_id checksheetid,drawingid,item_name productname," +
					"to_char(checkout_num) num,d.staff_name createrName,f.staff_name checkerName from (" +
					"select b.*,rownum rn from (" +
					"select t.*,a.checkout_date,a.delivery,a.examineperson from  mycheckout_detl t                  " +
					" left join mycheckout a on a.checksheet_id=t.checksheet_id" +
					" where t.item_id='"+productId+"' and a.status='1' order by a.checkout_date desc  ) b order by b.checkout_date desc) c " +
					" left join employee_info d on d.staff_code = c.delivery" +
					" left join employee_info f on f.staff_code = c.examineperson" +
					" where rn >"+min+" and rn <="+max+" order by checkout_date desc";
			sqlb = " select count(1) from mycheckout_detl t                 " +
					" left join mycheckout a on a.checksheet_id=t.checksheet_id" +
					" where t.item_id='"+productId+"' and a.status='1'";
			sqlc = " select to_char(sum(t.checkout_num)) sumNum from mycheckout_detl t                 " +
					" left join mycheckout a on a.checksheet_id=t.checksheet_id" +
					" where t.item_id='"+productId+"' and a.status='1'";
		}
		
		List<BeanProductInOrOut> lista = new ArrayList<BeanProductInOrOut>();
		try {
			lista = Sqlhelper.exeQueryList(sqla, null, BeanProductInOrOut.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int total = 0;
		
		try {
			total = Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String sumNum = "0";
		try {
			sumNum  = Sqlhelper.exeQueryString(sqlc, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(sumNum==null){
			sumNum = "0";
		}
		String json = PluSoft.Utils.JSON.Encode(lista);
		json = "{\"total\":"+total+",\"sumNum\":"+sumNum+",\"data\":"+json+"}";
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
