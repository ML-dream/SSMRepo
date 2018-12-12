package com.xm.testaction.qualitycheck.cardhandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*import javax.jms.Session;*/
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Test.JSON;

import com.wl.forms.User;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.sum.TimeLossSum;

public class ToStateJudge2 extends HttpServlet {
//	加载不合格审理单 表头
	/**
	 * Constructor of the object.
	 */
	public ToStateJudge2() {
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
//		返修  不合格审理单  加载
			String barcode = request.getParameter("barcode");
			int fo_no = Integer.parseInt(request.getParameter("fo_no"));
			String runNum = "";	//审理单流水号
			int mnum = 1;
			String user = "";
			HttpSession session = request.getSession();
		    user = ((User)session.getAttribute("user")).getStaffName();
		    
			String json ="";
			
//			待矫正
//			HttpSession session = request.getSession();
//			if(session.getAttribute("user")!=null){
//				user =(String) session.getAttribute("user");
//			}
//			搜索不合格审理单号
//			加载表头信息 drawingid,product_name,input_num from
			RejectJudgeBean bean = new RejectJudgeBean();
			ResultSet headerRs = null;
			try{
//				9-1 新增 lastfo
//				String headersql = "select a.checkdate,a.reject_num,a.fo_no,a.workername,a.fo_opname,a.rated_time,b.drawingid,b.product_name,b.input_num,a.lastfo from" +
//									"((select checkdate,reject_num,fo_no,workername,fo_opname,rated_time,lastfo from reject_info " +
//										"where barcode='"+barcode+"' and fo_no="+fo_no+")a" +
//									" left join  po_router b on b.barcode='"+barcode+"')";
				
				String headersql = "select a.checkdate,a.reject_num,a.fo_no,a.workername,a.fo_opname,a.rated_time,b.drawingid,b.product_name,b.input_num,a.lastfo,d.workerid,e.staff_name,e.section_code,f.deptname from" +
				"((select checkdate,reject_num,fo_no,workername,fo_opname,rated_time,lastfo from reject_info " +
					" where barcode='"+barcode+"' and fo_no="+fo_no+")a" +
					" left join po_routing2 d on d.barcode='"+barcode+"' and d.fo_no="+fo_no+
					" left join employee_info e on e.staff_code=d.workerid "+
					" left join dept f on f.deptid=e.section_code"+
				" left join  po_router b on b.barcode='"+barcode+"')";
				System.out.println(headersql);
				headerRs = Sqlhelper0.executeQuery(headersql, null);
				headerRs.next();
				
				
//				String test = headerRs.getString(1);
				bean.setCheckdate(headerRs.getString(1));
				bean.setReject_num(headerRs.getInt(2));
				bean.setRejectfo(headerRs.getString(3)+"-"+headerRs.getString(5));
				bean.setFo_no(headerRs.getInt(3));
				
				bean.setDuty_man(headerRs.getString(4));
				bean.setTime_loss(headerRs.getDouble(6));
				bean.setDrawingid(headerRs.getString(7));
				bean.setProduct_name(headerRs.getString(8));
				bean.setInputnum(headerRs.getInt(9));
				bean.setUser(user);
				bean.setBarcode(barcode);
				
				bean.setLastfo(headerRs.getInt(10));
				runNum =RejStateRunnum.rejStateRunnum();
				bean.setRunnum(runNum);
				bean.setDuty_man(headerRs.getString(11));
				bean.setDuty_mann(headerRs.getString(12));
				bean.setDuty_part(headerRs.getString(13));
				bean.setDuty_partn(headerRs.getString(14));
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				//    Sqlhelper0.close();
				if(headerRs != null){
					try {
						headerRs.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
//			9-9  工时损耗
			double timeloss = TimeLossSum.fixTime(barcode, fo_no);
			bean.setTime_loss(timeloss);
			
//			查找是否外协，以及外协厂商
			OutAsistHelper.outAsistHelper(barcode, bean, fo_no);
			try {
				json=JSON.Encode(JsonConvert.beanToMap(bean));
				System.out.println(json);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
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
