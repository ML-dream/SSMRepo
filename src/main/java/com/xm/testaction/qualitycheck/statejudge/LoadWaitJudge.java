package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.PoFlowBean;

public class LoadWaitJudge extends HttpServlet {
//加载所有待处理不合格品审理单
	/**
	 * Constructor of the object.
	 */
	public LoadWaitJudge() {
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
//		待矫正,显示所有主管领导未审核的
		String user = "user";
//		待矫正
//		HttpSession session = request.getSession();
//		if(session.getAttribute("user")!=null){
//			user =(String) session.getAttribute("user");
//		} 
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String sql="select * from "+
			"(select z.*,rownum rn from(select a.runnum,a.fo_no,a.occurdate,a.barcode,b.fo_opname,b.checkdate,a.opinion from reject_state a "+
	       "left join po_routing2 b on b.barcode =a.barcode and b.fo_no = a.fo_no "+
	       "where a.opinionerstate=0 order by runnum desc)z )"+
	       "where rn>"+min+" and rn <= "+max;
		
		String totalsql="select count(*) from (select runnum,fo_no,occurdate,barcode from reject_state where opinionerstate="+0+")";
		int total = 0;
		ResultSet totalRs = Sqlhelper0.executeQuery(totalsql, null);
		ResultSet sqlRs = null;
		try {
			
			totalRs.next();
			total = totalRs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			try {
				if(totalRs!=null){
					totalRs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ArrayList<WaitJudgeBean> waitList = new ArrayList<WaitJudgeBean>();
		try {
			sqlRs = Sqlhelper0.executeQuery(sql, null);
			
			while (sqlRs.next()){
				WaitJudgeBean bean = new WaitJudgeBean();
				
				bean.setRunnum(sqlRs.getString(1));
				bean.setFo_no(sqlRs.getInt(2));
				bean.setCheckdate(sqlRs.getString(6));
				bean.setBarcode(sqlRs.getString(4));
				bean.setFo_opname(sqlRs.getString(5));
//				9-6
				String remark = sqlRs.getString(7)+"";
				
				if(remark.equals("toSale")){
					bean.setRemark("超差使用");
				}
				waitList.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			try {
				if(sqlRs!=null){
					sqlRs.close();
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
