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

import com.wl.tools.Sqlhelper0;

public class LoadAllFix extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadAllFix() {
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

		doGet(request, response);
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
		
		String bdate= request.getParameter("bdate");
		String edate= request.getParameter("edate");
		
		String sql="select * from "+
		"(select z.*,rownum rn from("+
		"select a.runnum,c.fo_no,c.occurdate,c.barcode,b.fo_opname,a.returndate from fixdetail a "+
        "left join reject_state c on c.runnum = a.staterunnum "+
		"left join po_routing2 b on b.barcode =c.barcode and b.fo_no = c.fo_no "+
	       "where a.returndate between to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and " +
   			"to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss')" +"order by runnum desc)z) "+
   			"where rn>"+min+" and rn <= "+max;
		
		String totalsql="select count(*) from (" +
		"select a.runnum from fixdetail a "+
	       "where a.returndate between to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and " +
	       	"to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') )";
		int total = 0;
		ResultSet totalRs = Sqlhelper0.executeQuery(totalsql, null);
		ResultSet sqlRs = null;
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
		ArrayList<WaitJudgeBean> waitList = new ArrayList<WaitJudgeBean>();
		try {
			sqlRs = Sqlhelper0.executeQuery(sql, null);
			
			while (sqlRs.next()){
				WaitJudgeBean bean = new WaitJudgeBean();
				
				bean.setRunnum(sqlRs.getString(1));
				bean.setFo_no(sqlRs.getInt(2));
				bean.setCheckdate(sqlRs.getString(6));		//这里其实是返回日期
				bean.setBarcode(sqlRs.getString(4));
				bean.setFo_opname(sqlRs.getString(5));
				waitList.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
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
