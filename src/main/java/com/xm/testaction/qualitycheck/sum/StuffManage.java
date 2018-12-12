package com.xm.testaction.qualitycheck.sum;

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
import com.wl.tools.StringUtil;

public class StuffManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StuffManage() {
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

//		加载 材料单价维护,以成本表为基准，若成本表没有数据，该页面也不会有数据  
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String para1 = null;
		para1 = StringUtil.isNullOrEmpty(request.getParameter("para1"))?para1:request.getParameter("para1");
		
		String sqla = "select stuff,stuffname,price,density from ("+
			"select stuff,stuffname,price,density,rownum rn from( "+
				"select distinct t.stuff,a.stuffname,a.price price,a.density " +
					" from costinput t " +
					"left join stuffprice a on a.stuffid = t.stuff " +
					"order by price asc,stuff asc )" +
				")"+
				" where  rn>"+min+" and rn <="+max ;
		String sqlb = "select count(1) from (" +
						"select distinct t.stuff,a.stuffname,a.price price,a.density" +
						" from costinput t " +
						"left join stuffprice a on a.stuffid = t.stuff " +
					")";
		if(para1 != null){
			sqla = "select stuff,stuffname,price,density from ("+
				"select stuff,stuffname,price,density,rownum rn from( "+
					"select distinct t.stuff,a.stuffname,a.price price,a.density,rownum rn" +
					" from costinput t " +
					"left join stuffprice a on a.stuffid = t.stuff " +
					" where t.stuff='"+para1+"'"+
					"order by price asc,stuff asc )" +
				")"+
				" where  rn>"+min+" and rn <="+max ;
			sqlb = "select count(1) from (" +
					"select distinct t.stuff,a.stuffname,a.price price,a.density" +
					" from costinput t " +
					"left join stuffprice a on a.stuffid = t.stuff " +
					" where t.stuff='"+para1+"'"+
				")";
		}
		
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
		String stuffid = "";
		ResultSet rsa = null;
		ArrayList<StuffManageBean> waitList = new ArrayList<StuffManageBean>();
		try {
			System.out.println(sqla);
			rsa = Sqlhelper0.executeQuery(sqla, null);		//详细数据
			
			while (rsa.next()){
				stuffid = rsa.getString(1);
				if(stuffid ==null){
					continue;
				}
				StuffManageBean bean = new StuffManageBean();
				
				bean.setStuffid(rsa.getString(1));
				bean.setStuffname(rsa.getString(2));
				bean.setPrice(rsa.getString(3));
				bean.setDensity(rsa.getString(4));
				
				waitList.add(bean);	
			}
		} catch (Exception e) {
			// TODO: handle exception
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
