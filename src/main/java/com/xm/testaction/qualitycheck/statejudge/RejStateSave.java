package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.wl.forms.User;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;

public class RejStateSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RejStateSave() {
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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String result = "操作成功";
		
		String gridjson =request.getParameter("gridjson");
		
//		查看工种，是否质检，如果否，不允许填写
		HttpSession session=request.getSession();
	    String staffCode=((User)session.getAttribute("user")).getStaffCode();
		String sql="select t.work_type from employee_info t where t.staff_code='"+staffCode+"'";
		String workType = "";
		try {
			workType = Sqlhelper.exeQueryString(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		为了防止业务逻辑的修改，导致程序变动，将比较逻辑封装在方法中
		boolean typeResult = true;
		typeResult = TypeResult.isQualityCheck(workType);	//如果是质检，返回true
		if(!typeResult){
			result = "当前工种，不允许操作";
			String json = "{\"result\":\""+result+"\"}";
			out.append(json).flush();
			return;
		}
		System.out.println(gridjson);
		
		JSONArray jarr = JSONArray.fromObject(gridjson);
		
		String runnum = "";
		String shouldBe = "";
		String realBe = "";
		
		String qualityFee = "";
		
		for (int i = 0,j=jarr.size();i<j;i++){
			
			runnum = "";
			shouldBe = "";
			realBe = "";
			
			qualityFee = "";
			  
			  Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			  System.out.println(map1);
				
			  runnum = (String) (IsJsonNull.isJsonNull(map1.get("runnum"))?runnum:map1.get("runnum"));
			  shouldBe =(String) (IsJsonNull.isJsonNull(map1.get("shouldBe"))?shouldBe:map1.get("shouldBe"));
			  realBe = (String) (IsJsonNull.isJsonNull(map1.get("realBe"))?realBe:map1.get("realBe"));
			  qualityFee = (String) (IsJsonNull.isJsonNull(map1.get("qualityFee"))?qualityFee:map1.get("qualityFee"));
			  
			  String sqla = "update reject_state t set t.shouldbe='"+shouldBe+"',t.realbe='"+realBe+"',t.qualityfee='"+qualityFee+"' where t.runnum='"+runnum+"'";
			  try {
				System.out.println(sqla);
				Sqlhelper.executeUpdate(sqla, null);
				System.out.println("ok "+sqla);
				result="操作成功";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result="操作失败";
			}
		}
		String json = "{\"result\":\""+result+"\"}";
		out.append(json).flush();
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
