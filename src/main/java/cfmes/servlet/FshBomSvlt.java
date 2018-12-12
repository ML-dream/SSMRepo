package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cfmes.util.DealString;
import cfmes.util.sql_data;

import java.sql.*;

public class FshBomSvlt extends HttpServlet {

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
		sql_data sqlbean = new sql_data();
		String product_type = ds.toString(ds.toGBK(request.getParameter("flight_type")));
		String product_id = ds.toString(ds.toGBK(request.getParameter("product_id")));
		String issue_num = ds.toString(ds.toGBK(request.getParameter("issue_num")));
		String lot = ds.toString(ds.toGBK(request.getParameter("lot")));
		
		
		//ao,fo�����Ƿ�����ݣ��������
		String aosql = "select * from work.aomain where product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num+"'";
		ResultSet aors = sqlbean.executeQuery(aosql);
		String fosql = "select * from work.fo where product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num+"'";
		ResultSet fors = sqlbean.executeQuery(fosql);
		String fodsql = "select * from work.fo_detail where product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num+"'";
		ResultSet fodrs = sqlbean.executeQuery(fodsql);
		try{
			if(aors.next()){
				aosql = "update work.aomain set issue_num='"+issue_num+"-0"+"' where product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num+"'";
				sqlbean.executeUpdate(aosql);
				aors.close();}
			if(fors.next()){
				fosql = "update work.fo set issue_num='"+issue_num+"-0"+"' where product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num+"'";
				sqlbean.executeUpdate(fosql);
				fors.close();}
			if(fodrs.next()){fodsql = "update work.fo_detail set issue_num='"+issue_num+"-0"+"' where product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num+"'";
			    sqlbean.executeUpdate(fodsql);
			    fodrs.close();}
			//����issuedeploy��
			String issuesql = "update work.issuedeploy set issue_num='"+issue_num+"-0"+"' where product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num+"'";
			sqlbean.executeUpdate(issuesql);
			//����EBOM��
			String ebomsql = "update work.ebom set issue_num='"+issue_num+"-0"+"' where product_id='"+product_id+"' and issue_num='"+issue_num+"'";
			sqlbean.executeUpdate(ebomsql);
			
			
	    out.println("<script>alert('���³ɹ���');</script>");
	    out.println("<script>document.getElementById('bt_mbom').click();</script>");
		out.flush();
		out.close();
	}catch(Exception e){
		System.out.println("FshBomSvlt����ʱ���?����Ϊ��"+e);
	}finally{
		sqlbean.closeConn();
	}
			
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost( request,  response);
	}
	

}
