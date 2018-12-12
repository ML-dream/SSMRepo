package com.wl.testaction.warehouse.orderstatistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.xm.testaction.qualitycheck.sum.BeanWorkerDetail;
import com.xm.testaction.qualitycheck.sum.RejectView;

public class SeeProduceDetail extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SeeProduceDetail() {
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
//		查看零件生产详情
		request.setCharacterEncoding("utf-8");
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String orderId = request.getParameter("orderId").trim();
		String productId = request.getParameter("productId").trim();
		
		String sortBy = "foNo";
		sortBy = StringUtil.isNullOrEmpty(request.getParameter("sortBy"))?sortBy:request.getParameter("sortBy");
		
//		质量损失视图
		RejectView.foLoss();
		
		List<BeanWorkerDetail> lista = new ArrayList<BeanWorkerDetail>();
		String sqla = "select * from (" +						//nvl(re.basetime,0)
			"select to_char(t.checkdate,'yyyy-mm-dd') checkdate,t.machineid,a.product_name productName,a.drawingid productId,t.fo_opname foOpname,a.input_num inputNum,t.confirm_num confirmNum,t.rated_time ratedTime,b.workbranch typeId, " +
			"c.typename workBranch,c.salaryprice price,t.accept_num accept,t.reject_num reject,fi.fixnum,di.disnum,re.rewardstime," +
			"t.checker,t.barcode,t.workerid," +
			"to_char(fl.qualityLoss) qualityLoss,t.workerid staffCode,em.staff_name,t.fo_no foNo,rownum rn " +
			"from po_routing2 t" +
			"       left join po_router a on a.barcode= t.barcode " +
			"       left join fo_detail b on b.product_id=a.drawingid and b.issue_num= a.issuenum and b.fo_no=t.fo_no and b.isinuse='1' " +
			"       left join workbranch c on c.typeid = b.workbranch" +
			"       left join fofix fi on fi.barcode= t.barcode and fi.fo_no= t.fo_no" +
			"       left join fodis di on di.barcode= t.barcode and di.fo_no= t.fo_no" +
			"       left join rewardstime re on re.barcode= t.barcode and re.fo_no= t.fo_no " +
			" 		left join foLoss fl on fl.barcode= t.barcode and fl.fo_no= t.fo_no "+
			"		left join employee_info em on em.staff_code=t.workerid "+
			" where a.order_id='"+orderId+"' and a.drawingid='"+productId+"' " +
					" order by "+sortBy+" asc)" +
			" where  rn>"+min+" and rn <="+max +" order by "+sortBy+" asc";
		
		String sqlb = "select count(1) from (" +
			"select  t.fo_no foNo from po_routing2 t " +
			"       left join po_router a on a.barcode= t.barcode " +
			" where a.order_id='"+orderId+"' and a.drawingid='"+productId+"' " +
			")";
		try {
			lista = Sqlhelper.exeQueryList(sqla, null, BeanWorkerDetail.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int total = 0;
		try {
			total =Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = PluSoft.Utils.JSON.Encode(lista);
		json = "{\"total\":"+total+",\"data\":"+json+"}";
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
