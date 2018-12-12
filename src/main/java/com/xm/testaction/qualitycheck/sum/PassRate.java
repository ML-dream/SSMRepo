package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class PassRate extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PassRate() {
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
		System.out.println(this.getClass().getName());
		String comId = request.getParameter("comId");
		String sqla = "select nvl(sum(t.num),0) num,nvl(sum(t.passnum),0) passnum from processesplan t where t.waixiecom='"+comId+"'";
		Map< String, String> mapa = new HashMap<String, String>();
		String result = "";
		try {
			mapa = Sqlhelper.executeQueryMap(sqla, null);
			String num = mapa.get("NUM");
			String passnum = mapa.get("PASSNUM");
			if(num.equals("0")||passnum.equals("0")){
				result="0";
			}else{
				double inum = Integer.parseInt(num);
				double ipass = Integer.parseInt(passnum);
				double iresult = ipass/inum*100;
				result=CostCaculate.douToStr(iresult);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
//		更新到外协商表
		String sqlb="update outassistcom t set t.passrate='"+result+"' where t.companyid='"+comId+"'";
		try {
			System.out.println(sqlb);
			Sqlhelper.executeUpdate(sqlb, null);
			System.out.println(sqlb);
		} catch (Exception e) {
			// TODO: handle exception
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
