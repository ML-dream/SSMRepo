package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;

public class LoadSubStuff extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadSubStuff() {
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
		
		String orderid = request.getParameter("orderid");
		String draid = request.getParameter("draid");
//		String proname = request.getParameter("proname");
//		if(proname==null&&proname.equals("")){
//		}else{
//			proname = ChineseCode.toUTF8(proname);
//		}
		String stufflevel = "2";
		String [] param1 ={orderid,draid,stufflevel};
		String sqla ="select orderid,draid,proname,stuff,dia,len,wid,hei,issup,rsize,helpkey from "+
			"(select t.orderid,t.draid,x.product_name proname,t.stuff,t.dia,t.len,t.wid,t.hei,t.islailiao issup,t.roughsize rsize,t.helpkey,rownum rn " +
				"from costinput t " +
				"left join order_detail x on x.order_id = t.orderid and x.product_id = t.draid and x.issue_num = '1' "+
				"where t.orderid =? and t.draid=? and t.stufflevel = ? ) a" +
				" where  rn>"+min+" and rn <="+max ;
		String sqlb ="select count(1) from "+ 
			"(select t.orderid,t.draid,t.stuff,t.dia,t.len,t.wid,t.hei,t.islailiao issup,rownum rn " +
			"from costinput t where t.orderid =? and t.draid=? and t.stufflevel = ? ) a";
		int total = 0;
		try {
			total = Sqlhelper.exeQueryCountNum(sqlb, param1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<CostConfirmBean> list1 = new ArrayList<CostConfirmBean>();
		 ResultSet rsa = null;
		try {
			rsa= Sqlhelper0.executeQuery(sqla, param1);
			while (rsa.next()){
				CostConfirmBean bean = new CostConfirmBean();
				bean.setOrderid(rsa.getString(1));
				bean.setDraid(rsa.getString(2));
				bean.setProname(rsa.getString(3));
				bean.setStuff(rsa.getString(4));
				bean.setDia(rsa.getString(5));
				
				bean.setLen(rsa.getString(6));
				bean.setWid(rsa.getString(7));
				bean.setHei(rsa.getString(8));
				bean.setIssup(rsa.getString(9));
				bean.setRsize(rsa.getString(10));
				bean.setHelpkey(rsa.getString(11));
				list1.add(bean);
			}
//			list1 = Sqlhelper.exeQueryList(sqla, param1, CostConfirmBean.class);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa != null){
				try {
					rsa.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String json = PluSoft.Utils.JSON.Encode(list1);
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
