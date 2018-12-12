package com.xm.testaction.qualitycheck.sum;

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

public class LoadWorkerDetail extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadWorkerDetail() {
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
//	加载员工工作详情
		System.out.println(this.getClass().getName());
		request.setCharacterEncoding("utf-8");
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String worker = request.getParameter("worker");
		String bday = request.getParameter("bday");
		String eday = request.getParameter("eday");
//		c.price   返修数跟报废数都是从返修单或者报废单中取的
		String sqla = "select * from (select fr.*,rownum rn from (" +						//nvl(re.basetime,0)
				"select to_char(t.checkdate,'yyyy-mm-dd') checkdate,t.fo_no fono,t.machineid,a.product_name productName,a.drawingid productId,t.fo_opname foOpname,a.input_num inputNum,t.confirm_num confirmNum,t.rated_time ratedTime,b.workbranch typeId, " +
				"c.typename workBranch,c.salaryprice price,t.accept_num accept,t.reject_num reject,fi.fixnum,di.disnum,to_char(t.rated_time*t.accept_num) basetime,re.rewardstime,to_char((t.rated_time*t.accept_num)+nvl(re.rewardstime,0)) finalTime,t.checker,t.barcode,t.workerid," +
				"to_char((t.rated_time*t.accept_num+nvl(re.rewardstime,0))*c.salaryprice) timeValue,a.order_id " +
				"from po_routing2 t" +
				"       left join po_router a on a.barcode= t.barcode " +
				"       left join fo_detail b on b.product_id=a.drawingid and b.issue_num= a.issuenum and b.fo_no=t.fo_no and b.isinuse='1' " +
				"       left join workbranch c on c.typeid = b.workbranch" +
				"       left join fofix fi on fi.barcode= t.barcode and fi.fo_no= t.fo_no" +
				"       left join fodis di on di.barcode= t.barcode and di.fo_no= t.fo_no" +
				"       left join rewardstime re on re.barcode= t.barcode and re.fo_no= t.fo_no " +
				" where t.checkdate between to_date('"+bday+"','yyyy-MM-dd HH:mi:ss') and to_date('"+eday+"','yyyy-MM-dd HH:mi:ss') and  t.workerid= '"+worker+"' " +
						" order by t.checkdate desc) fr order by checkdate desc) " +
				" where  rn>"+min+" and rn <="+max +" order by checkdate desc";
		String sqlb = "select count(1) from (" +
				"select to_char(t.checkdate,'yyyy-mm-dd') checkdate,t.barcode,t.workerid from po_routing2 t" +
				" where t.checkdate between to_date('"+bday+"','yyyy-MM-dd HH:mi:ss') and to_date('"+eday+"','yyyy-MM-dd HH:mi:ss') and  t.workerid= '"+worker+"' "+
				")";
		
		RejectView.fixView();
		RejectView.disView();//返修与报废视图
		
		List<BeanWorkerDetail> lista = new ArrayList<BeanWorkerDetail> ();
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
