package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.sum.UpToPartsplan;

public class LastFoHandle extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LastFoHandle() {
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
		String barcode = request.getParameter("barcode");
//		ToSum.finishToSum(barcode);
		String sqlb = "select t.fo_no from edit_control t where t.barcode = '"+barcode+"'";
		String para = "";
		String checker = "";
		try {
			HttpSession session = request.getSession();
			checker = ((User)session.getAttribute("user")).getStaffName();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		ResultSet rsb = null;
		try {
			rsb = Sqlhelper0.executeQuery(sqlb, null);
			rsb.next();
			para = rsb.getString(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsb != null){
				try {
					rsb.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(para.equals("1010")){
//			1010 用来区分是否已报完工
			response.setCharacterEncoding("utf-8");
			response.getWriter().append("已报完工").flush();
		}else{
//		9-3
			String donesql= "update  po_router a set a.done = 1 where a.barcode='"+barcode+"'";		//标识 该条形码已经质检完成 
			try {
				Sqlhelper0.executeUpdate(donesql, null);
				System.out.println("ok"+donesql);
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
	//			Sqlhelper0.close();
			}
			
	//		11-10 做流程卡的修改权限 
			String sqla = "update edit_control t set t.fo_no = '1010' where t.barcode = '"+barcode+"'";
			try {
				System.out.println(sqla);
				Sqlhelper0.executeUpdate(sqla, null);
				System.out.println("ok"+donesql);
			} catch (Exception e) {
				// TODO: handle exception
			}

	//将未质检的工序默认为通过，数据为上一道工序的数据		
			try {
				UncheckFoHandle.uncheckFoHandle(barcode,checker);
			} catch (Exception e) {
				// TODO: handle exception
			}
//		9-8 零件计划上报，完成数量统计,如果重复点击“完工”，会重复累加数量，先处理未质检工序，然后上报零件计划，不然零件状态一直是加工中
			try {
				UpToPartsplan.upToPartsplan(barcode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			response.setCharacterEncoding("utf-8");
			response.getWriter().append("操作成功").flush();
		}
		
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
