package com.wl.testaction.noticeManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Notice;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ToPrintNotice extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ToPrintNotice() {
		super();
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

		doPost(request,response);
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

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id").trim();
		
		System.out.println("id=="+id);
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//	    String createTime = df.format(new Date());
		
	//	String noticeReadSql = "update notice set isReaded='1',readTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss') where id=? ";
	//	String[] noticeReadParams = {createTime,id};
		
	    String noticeWillSql = "select id,B.staff_name sender,C.staff_name receiver,sendTime,readTime," +
	    		"contentTitle,content,attachment,isReaded,A.grade grade " +
	    		"from notice A " +
	    		"left join employee_info B on A.sender=B.staff_code " +
	    		"left join employee_info C on A.receiver=C.staff_code  " +
	    		"where id=?";
	    String[] params = {id};
	    Notice result = new Notice();
		try {
			result = Sqlhelper.exeQueryBean(noticeWillSql, params, Notice.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("/noticeManage/printNoticeDetail.jsp").forward(request, response);
	}

}
