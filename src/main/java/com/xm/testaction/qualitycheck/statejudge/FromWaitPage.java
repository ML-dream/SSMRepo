package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Test.JSON;

import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;

public class FromWaitPage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FromWaitPage() {
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
		String barcode = request.getParameter("barcode");
//		int fo_no = Integer.parseInt(request.getParameter("fo_no"));
		int fo_no=0;
		String json ="";
		FromWaitPageBean bean = new FromWaitPageBean();
		ResultSet Rs = null;
		
		String statestr = RejectStateString.state;
//		9-1 新增 lastfo    表中新增  reject_type   9-25
		String sql="select a.*,b.reject_num,b.fo_no,b.fo_opname,c.drawingid,c.product_name,c.input_num,b.lastfo,b.checkdate,e.staff_name,f.deptname,g.deptname,h.companyname from " +
						"((select "+statestr+"　from reject_state where runnum='" +runnum+"') a "+
								" left join reject_info b on b.barcode=a.barcode and b.fo_no=a.fo_no" +
								" left join employee_info e on e.staff_code=a.dutyman "+
								" left join dept f on f.deptid=a.dutypart "+
								" left join dept g on g.deptid= a.occurplace "+
								"left join outassistcom h on h.companyid= a.dutyman "+
										" left join po_router c on c.barcode=a.barcode)";
		try{
			Rs=Sqlhelper0.executeQuery(sql, null);
			Rs.next();
			bean.setRunnum(Rs.getString(1));
			bean.setCheckrank(Rs.getString(2));
			bean.setBatchcode(Rs.getString(3));
//			bean.setOccurplace(Rs.getString(4));
			bean.setOccurplacev(Rs.getString(4));
			bean.setOperate_card(Rs.getString(5));
			
			String source = Rs.getString(6);
			bean.setProsource(Rs.getString(6));
			bean.setDuty_part(Rs.getString(7));
			bean.setDuty_man(Rs.getString(8));
			bean.setTime_loss(Rs.getDouble(9));
			bean.setMaterial_loss(Rs.getString(10));
			
			bean.setImg(Rs.getString(11));
			bean.setChecker(Rs.getString(12));
			bean.setSecurityin(Rs.getString(13));
			bean.setSecurity(Rs.getString(14));
			bean.setDesignerin(Rs.getString(15));
			bean.setDesigner(Rs.getString(16));
			bean.setMarketerin(Rs.getString(17));
			bean.setMarketer(Rs.getString(18));  
			bean.setSuperviserin(Rs.getString(19));
			bean.setSuperviser(Rs.getString(20));
			bean.setOpinion(Rs.getString(21));
			bean.setOpinioner(Rs.getString(22));
			bean.setRecheckerin(Rs.getString(23));
			bean.setRechecker(Rs.getString(24));
			bean.setBarcode(Rs.getString(27));
			bean.setOpinionText(Rs.getString(28));
			bean.setOpinionstate(Rs.getInt(29));
			bean.setCheckdate(Rs.getString(30));
//			从这
			bean.setReject_num(Rs.getInt(32));
			bean.setRejectfo(Rs.getString(34)+"-"+Rs.getString(35));
			bean.setDrawingid(Rs.getString(36));
			bean.setProduct_name(Rs.getString(37));
			bean.setInputnum(Rs.getInt(38));
			bean.setFo_no(Rs.getString(34));
			
			bean.setLastfo(Rs.getInt(39));
//			9-20
			bean.setCheckdate(Rs.getString(40));
			bean.setDuty_mann(Rs.getString(41));
			bean.setDuty_partn(Rs.getString(42));
			bean.setOccurplace(Rs.getString(43));
//			外协责任人
			if(source!=null&&source.equals("outer")){
				bean.setDuty_mann(Rs.getString(44));
			}
//			bean.setUser(user);
			System.out.println(bean.getProduct_name());
			json = PluSoft.Utils.JSON.Encode(bean);
//			json=JSON.Encode(JsonConvert.beanToMap(bean));
			System.out.println(json);
			response.setCharacterEncoding("utf-8");
			response.getWriter().append(json).flush();
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(Rs != null){
				try {
					Rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
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
