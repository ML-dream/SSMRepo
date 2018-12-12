package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class SaveNewSubStuff extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveNewSubStuff() {
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

		String orderid = request.getParameter("orderid");
		String draid= request.getParameter("draid");
		
		String stuff= request.getParameter("stuff");
		String dia = request.getParameter("dia");
		String len = request.getParameter("len");
		String wid = request.getParameter("wid");
		String hei = request.getParameter("hei");
		String issup = request.getParameter("issup");
//		查询当前零件子材料的数量
		String sqla= "select a.helpkey from costinput a where a.orderid =? and a.draid = ? and a.stufflevel ='2'";
		String [] parama = {orderid,draid};
		int helpkey = 0;
		try {
			helpkey = Sqlhelper.exeQueryCountNum(sqla, parama);
		} catch (Exception e) {
			// TODO: handle exception
		}
		helpkey +=2;	//子焊件序号加2
		String helpkeys = ""+helpkey;
		String sqlb = "insert into costinput (orderid,draid,stuff,dia,len,wid,hei,islailiao,stufflevel,helpkey) values(?,?,?,?,? ,?,?,?,?,? )";
		String [] paramb = {orderid,draid,stuff,dia,len,wid,hei,issup,"2",helpkeys};
		String result = "操作成功";
		try {
			Sqlhelper.executeUpdate(sqlb, paramb);
			System.out.println("ok  "+sqlb);
		} catch (Exception e) {
			// TODO: handle exception
			result = "操作失败";
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(result).flush();
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
