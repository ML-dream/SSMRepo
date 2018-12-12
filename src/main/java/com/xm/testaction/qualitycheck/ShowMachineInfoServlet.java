package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;

public class ShowMachineInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowMachineInfoServlet() {
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
		int totalCount=0;
		
		String bodysql="";		
		String totalsql="";
		String json="";
//		待矫正， 加类型过滤
		bodysql = "select c.* from(select b.machinetype,b.typename,a.machineid,a.machinename,rownum rn from machinfo a left join machinetype b on a.machtype = b.machinetype)c " +
				"where "+min+ "<rn and rn <=" +max;
		ResultSet brs = null;
		List<MachineInfoBean> mecList = new ArrayList<MachineInfoBean>();
		
		try {
			
			brs =Sqlhelper0.executeQuery(bodysql, null);
			while(brs.next()){
			MachineInfoBean bean = new MachineInfoBean();
			bean.setMachinetype(brs.getString(1));
			bean.setTypename(brs.getString(2));
			bean.setMachineid(brs.getString(3));
			bean.setMachinename(brs.getString(4));
			
			mecList.add(bean);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(brs != null){
		    	try {
				brs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		
		totalsql= "select count(*) from (select a.machineid,a.machinename from " +
			"machinfo a) " ;
		ResultSet trs = null;
		try {
			trs=Sqlhelper0.executeQuery(totalsql, null);
			trs.next();
			totalCount = trs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(trs != null){
		    	try {
				trs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		json = PluSoft.Utils.JSON.Encode(mecList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
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
