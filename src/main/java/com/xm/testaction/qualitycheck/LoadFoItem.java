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

public class LoadFoItem extends HttpServlet {
// 加载流程卡刀具信息
	/**
	 * Constructor of the object.
	 */
	public LoadFoItem() {
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
		int fo_no = Integer.parseInt(request.getParameter("fo_no"));
		
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
		
//		待矫正  测试用
//		barcode= "145423654625624";
//		fo_no = 15;
		ResultSet brs = null;
		List<LoadFoItemBean> itemList = new ArrayList<LoadFoItemBean>();
		bodysql="select b.* from " +
				"(select a.itemid,b.item_name,a.itemnum,rownum rn from foitem a " +
				"left join item b on b.item_id = a.itemid "+
				"where a.barcode ='"+barcode+"' and a.fo_no= "+fo_no+")b " +
				"where "+min+ "<rn and rn <=" +max;
		try {
			
			brs =Sqlhelper0.executeQuery(bodysql, null);
			while(brs.next()){
				LoadFoItemBean bean = new LoadFoItemBean();
				bean.setItemid(brs.getString(1));
				bean.setItemname(brs.getString(2));
				bean.setItemnum(brs.getInt(3));
				itemList.add(bean);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//     Sqlhelper0.close();
			if(brs != null){
		    	try {
				brs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		
		totalsql= "select count(*) from (select a.itemid,a.itemname,a.itemnum from foitem a " +
				"where a.barcode ='"+barcode+"' and a.fo_no="+ fo_no+
				" ) "  ;
		ResultSet trs = null;
		try {
			trs=Sqlhelper0.executeQuery(totalsql, null);
			trs.next();
			totalCount = trs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//     Sqlhelper0.close();
			if(trs != null){
		    	try {
				trs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		json = PluSoft.Utils.JSON.Encode(itemList);
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
