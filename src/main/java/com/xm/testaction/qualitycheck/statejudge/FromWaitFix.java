package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;

public class FromWaitFix extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FromWaitFix() {
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

		String runnum = request.getParameter("runnum");
		String state = request.getParameter("barcode");
//		int fo_no = Integer.parseInt(request.getParameter("fo_no"));
		String json ="";
//		多取了一个checkheader 
		String sql="select b.timeloss,c.product_name,c.drawingid,b.operatecard," +
		"b.fo_no,b.rejectnum,a.runnum,a.designerin,d.staff_name designer,a.returndate,e.staff_name dutyer,f.staff_name checker,g.staff_name fixer,a.checkheader,h.staff_name checkerheadern,rownum rn,i.staff_name dutyman,a.checkdate,b.dutyman dutymanid," +
		"a.designer,a.dutyer,a.checker,a.fixer,b.describle,b.dutypart,j.deptname,k.companyname,m.companyname dutyer,l.companyname fixer " +
		" from fixdetail a  " +
		"left join reject_state b on b.runnum = a.staterunnum " +
		"left join po_router c on c.barcode = b.barcode " +
		"left join employee_info d on d.staff_code  = a.designer " +
		"left join employee_info e on e.staff_code = a.dutyer " +
		"left join employee_info f on f.staff_code = a.checker " +
		"left join employee_info g on g.staff_code = a.fixer " +
		"left join employee_info h on h.staff_code = a.checkheader "+
		"left join employee_info i on i.staff_code = b.dutyman "+
		"left join dept j on j.deptid = b.dutypart "+
		"left join outassistcom k on k.companyid= b.dutyman "+
		"left join outassistcom l on l.companyid= a.fixer "+
		"left join outassistcom m on m.companyid= a.dutyer "+
				"where a.runnum='"+runnum+"' or a.staterunnum='"+state+"' ";
		ResultSet sqlRs =null;
		WaitFixTableBean bean = new WaitFixTableBean();
		try {
			sqlRs = Sqlhelper0.executeQuery(sql, null);
			
			while (sqlRs.next()){
				
				bean.setTime_loss(sqlRs.getDouble(1));
				bean.setProduct_name(sqlRs.getString(2));
				bean.setDrawingid(sqlRs.getString(3));
//				bean.setOperatecard(sqlRs.getString(4));
				bean.setOperate_card(sqlRs.getString(4));
				bean.setFo_no(sqlRs.getString(5));
				bean.setRejectfo(sqlRs.getString(5));
				bean.setReject_num(sqlRs.getString(6));
				bean.setRunnum(sqlRs.getString(7));
				bean.setDesignerin(sqlRs.getString(8));
				bean.setDesigner(sqlRs.getString(9));
				bean.setReturndate(sqlRs.getString(10));
				
				bean.setDutyer(sqlRs.getString(11));	//责任代表
				String dutyer = sqlRs.getString(28);
				if(dutyer!=null&& !dutyer.isEmpty()){
					bean.setDutyer(dutyer);
				}
				
				
				bean.setChecker(sqlRs.getString(12));
				bean.setFixer(sqlRs.getString(13));
				String fixer = sqlRs.getString(29);
				if(fixer !=null && !fixer.isEmpty()){
					bean.setFixer(fixer);
				}
				
				bean.setCheckheader(sqlRs.getString(15));
				
				bean.setDuty_man(sqlRs.getString(17));
				bean.setCheckdate(sqlRs.getString(18));
				bean.setDuty_manid(sqlRs.getString(19));
				
				bean.setCheckheaderid(sqlRs.getString(14));
				bean.setDesignerid(sqlRs.getString(20));
				bean.setDutyerid(sqlRs.getString(21));
				bean.setCheckerid(sqlRs.getString(22));
				bean.setFixerid(sqlRs.getString(23));
				bean.setDescrible(sqlRs.getString(24));
				bean.setDuty_part(sqlRs.getString(25));
				bean.setDuty_parts(sqlRs.getString(26));
				String dutyname = bean.getDuty_man();
				if(dutyname==null || dutyname.equals("")){
					bean.setDuty_man(sqlRs.getString(27));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(sqlRs!=null){
					sqlRs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		json = PluSoft.Utils.JSON.Encode(bean);
 		System.out.println(json);
		response.setCharacterEncoding("utf-8");
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
