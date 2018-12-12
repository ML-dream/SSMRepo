package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.DateTimeUtil;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class ItemSvlt extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		DealString ds = new DealString();
		sql_data dealsql = new sql_data();
		String additem_id = ds.toGBK(request.getParameter("additem_id"));
		String additem_type = ds.toGBK(request.getParameter("additem_type"));
		String additem_name = new String(request.getParameter("additem_name").trim().getBytes("ISO-8859-1"), "utf-8");
		additem_name = request.getParameter("additem_name").trim();
		System.out.println("additem_name==="+additem_name);
		java.util.Date createTime = new Date();
		
		java.util.Date updateTime = new Date();
		
		System.out.println(createTime);
		//additem_name = request.getParameter("additem_name").trim();
		// String additem_name =new
		// String(request.getParameter("additem_name").trim().getBytes("ISO-8859-1"),"utf-8");
		//additem_name = new String(request.getParameter("additem_name").trim().getBytes("ISO-8859-1"),"utf-8");
		System.out.println("additem_name==" + additem_name);
		String sql = "insert into WORK.item (item_id,item_typeid,item_name,create_time,update_time) Values ('"
				+ additem_id
				+ "' , '"
				+ additem_type
				+ "' , '"
				+ additem_name
				+ "' , "
				+ "to_date('"+DateTimeUtil.formatDateToString(new Date(), DateTimeUtil.DATE_24SECOND_)+"','yyyy-mm-dd,hh24:mi:ss')"
				+ " , "
				+ "to_date('"+DateTimeUtil.formatDateToString(new Date(), DateTimeUtil.DATE_24SECOND_)+"','yyyy-mm-dd,hh24:mi:ss')"
				+ ")";
		;
		
		System.out.println(sql);
		try {
			dealsql.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("ItemSvlt抛出异常！！" + e);
		} finally {
			dealsql.closeConn();
		}
		out.println("<script>alert('添加成功！');window.history.back();</script>");
		out.flush();
		out.close();

	}

}
