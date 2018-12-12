package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cfmes.util.DealString;
import cfmes.util.sql_data;

public class DelECPlan extends HttpServlet {

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

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		DealString ds = new DealString();
		sql_data dealsql = new sql_data();
		String product_type = ds.toGBK(request.getParameter("product_type"));
		String product_id = ds.toGBK(request.getParameter("product_id"));
		String issue_num = ds.toGBK(request.getParameter("issue_num"));
		String lot = ds.toGBK(request.getParameter("lot"));
		String item_id = ds.toGBK(request.getParameter("item_id"));
		String father_item_id = ds.toGBK(request.getParameter("father_item_id"));
		String order_id = ds.toGBK(request.getParameter("order_id"));
		String plan_id = ds.toGBK(request.getParameter("plan_id"));
		String quality_id = ds.toGBK(request.getParameter("quality_id"));
		
		String sql = "delete  from WORK.EC_PLAN where product_type='"+product_type+"' and product_id='"+product_id+"' and issue_num='"+
		             issue_num+"' and lot='"+lot+"' and item_id='"+item_id+"' and father_item_id='"+father_item_id+"' and order_id='"+order_id
		             +"' and plan_id='"+plan_id+"' and quality_id='"+quality_id+"'";
		try{
		dealsql.executeQuery(sql);
		}catch (Exception e){
			System.out.println("DelECPlan����ʱ���?����Ϊ��"+e);
		}finally{
			dealsql.closeConn();
		}
		out.println("<script>alert('ɾ����ɣ�');document.getElementById('Submit').click();</script>");
		out.flush();
		out.close();
		
		
	}

}
