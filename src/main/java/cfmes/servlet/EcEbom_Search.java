package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class EcEbom_Search extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql;
		sql_data sqlbean = new sql_data();
		DealString ds = new DealString();

		String product_type = ds.toString(ds.toGBK(request
				.getParameter("product_type")));
		String product_id = ds.toString(ds.toGBK(request
				.getParameter("product_id")));
		String issue_num = ds.toString(ds.toGBK(request
				.getParameter("issue_num")));
		String lot = ds.toString(ds.toGBK(request.getParameter("lot")));

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml");
		response.setHeader("Charset", "utf-8");
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache");
		out.println("<?xml version='1.0' encoding='utf-8'?>");
		out.println("<ECEBOM>");

		try {
			// sql
			// ="select * from work.ec_plan where product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num ='"+issue_num+"' and lot='"+lot+"'"
			// ;
			sql = "select * from work.ec_plan where product_type ='"
					+ product_type + "' and product_id = '" + product_id
					+ "' and issue_num = '" + issue_num + "' and lot = '" + lot
					+ "'";
			System.out.println("sql===" + sql);
			// ResultSet rs_ecebomdata =sqlbean.executeQuery(sql);
			ResultSet rs_ecebomdata = Sqlhelper.executeQuery(sql, null);
			while (rs_ecebomdata.next()) {
				out.println("<ECEBOMDATA>");
				out.println("<ITEM_ID>" + rs_ecebomdata.getString("item_id")+ "</ITEM_ID>");
				out.println("<F_ITEM_ID>"+ rs_ecebomdata.getString("father_item_id")+ "</F_ITEM_ID>");
				//out.println("<FID>" + rs_ecebomdata.getString("fid")+ "</FID>");
				out.println("<FID>" +" "+ "</FID>");
				//out.println("<ID>" + rs_ecebomdata.getString("id") + "</ID>");
				out.println("<ID>" +" "+ "</ID>");
				//out.println("<LEVELID>" + rs_ecebomdata.getString("level_id")+ "</LEVELID>");
				out.println("<LEVELID>" +" "+ "</LEVELID>");
				
				out.println("<ECTYPE>" + rs_ecebomdata.getString("ec_type")+ "</ECTYPE>");
				out.println("<ECCON>" + rs_ecebomdata.getString("ec_con")+ "</ECCON>");
				out.println("<ECTIME>" + rs_ecebomdata.getString("ec_time")+ "</ECTIME>");

				out.println("<PRODUCT_TYPE>"+ rs_ecebomdata.getString("product_type")+ "</PRODUCT_TYPE>");
				out.println("<PRODUCT_ID>"+ rs_ecebomdata.getString("product_id")+ "</PRODUCT_ID>");
				out.println("<ISSUE_NUM>"+ rs_ecebomdata.getString("issue_num")+ "</ISSUE_NUM>");
				out.println("<LOT>" + rs_ecebomdata.getString("lot")+ "</LOT>");
				//out.println("<ECID>" + rs_ecebomdata.getString("ec_id")+ "</ECID>");
				out.println("<ECID>" +" "+ "</ECID>");
				out.println("</ECEBOMDATA>");
			}
			rs_ecebomdata.close();
			sqlbean.closeConn();

			out.println("</ECEBOM>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EcEbom_Search查询异常！！！");
		}
	}

}
