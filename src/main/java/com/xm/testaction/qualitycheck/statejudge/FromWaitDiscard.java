package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;

public class FromWaitDiscard extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FromWaitDiscard() {
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

		doGet(request, response);
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
		String sql="select b.deptname,c.rejectnum,d.drawingid,e.staff_name sduty_man,g.staff_name dutygrouper," +
		"f.staff_name sdutyparter,g.staff_name sdutygrouper,h.staff_name scoster,i.staff_name ssuperviser," +
		"j.staff_name schecker,a.runnum,a.checkdate,c.dutypart,d.product_name,c.operatecard,c.fo_no,c.dutyman," +
		"c.timeloss,c.materialloss,a.recvalue,c.describle,a.dutyparter,a.dutygrouper,a.coster,a.superviser,a.checker checkerId,k.companyname,l.staff_name checker " +
		"from disdetail a " +
		"left join reject_state c on c.runnum = a.staterunnum " +
		"left join dept b on b.deptid = c.dutypart " +
		"left join po_router d on d.barcode = c.barcode " +
		"left join employee_info e on e.staff_code = c.dutyman " +
		"left join employee_info f on f.staff_code = a.dutyparter " +
		"left join employee_info g on g.staff_code = a.dutygrouper " +
		"left join employee_info h on h.staff_code = a.coster " +
		"left join employee_info i on i.staff_code = a.superviser " +
		"left join employee_info j on j.staff_code = a.checker "+
		"left join outassistcom k on k.companyid= c.dutyman "+
		"  left join employee_info l on l.staff_code=a.checker "+
				"where a.runnum='"+runnum+"' or a.staterunnum='"+state+"' ";
		
		ResultSet sqlrs =null;
		WaitDiscardBean bean = new WaitDiscardBean();
		try {
			System.out.println(sql);
			sqlrs = Sqlhelper0.executeQuery(sql, null);
			
			while (sqlrs.next()){
				bean.setDuty_part(sqlrs.getString(1));
				bean.setReject_num(sqlrs.getString(2));
				bean.setDrawingid(sqlrs.getString(3));
				bean.setDuty_man(sqlrs.getString(4));
				bean.setDutygrouper(sqlrs.getString(5));
				
				bean.setDutyparter(sqlrs.getString(6));
				bean.setDutygrouper(sqlrs.getString(7));
				bean.setCoster(sqlrs.getString(8));
				bean.setSuperviser(sqlrs.getString(9));
				bean.setChecker(sqlrs.getString(10));
				
				bean.setRunnum(sqlrs.getString(11));
				bean.setCheckdate(sqlrs.getString(12));
				bean.setDuty_partid(sqlrs.getString(13));
				bean.setProduct_name(sqlrs.getString(14));
				bean.setOperate_card(sqlrs.getString(15));
				
				bean.setRejectfo(sqlrs.getString(16));
				bean.setFo_no(sqlrs.getString(16));
				
				bean.setDuty_manid(sqlrs.getString(17));
				bean.setTime_loss(sqlrs.getString(18));
				bean.setMaterial_loss(sqlrs.getString(19));
				bean.setRecvalue(sqlrs.getString(20));
				bean.setImgurl(sqlrs.getString(21));
				bean.setDutyparterid(sqlrs.getString(22));
				bean.setDutygrouperid(sqlrs.getString(23));
				bean.setCosterid(sqlrs.getString(24));
				bean.setSuperviserid(sqlrs.getString(25));
				
				String checker = sqlrs.getString(28);
				bean.setCheckerid(sqlrs.getString(26));
				if(checker!=null && !checker.isEmpty()){
					bean.setCheckerid(checker);
				}
				String dutyid = bean.getDuty_man();
				if(dutyid==null || dutyid.equals("")){
					bean.setDuty_man(sqlrs.getString(27));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(sqlrs!=null){
					sqlrs.close();
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
