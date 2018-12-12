package com.wl.testaction.craftworkManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
/**
 * 应用工艺
 * @author xiem
 *
 */
public class ApplyFo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ApplyFo() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String sproductId = request.getParameter("sproductId");	//模板
		String sissueNum = request.getParameter("sissueNum");	//模板
		String productId = request.getParameter("productId");	//目标零件
		String issueNum = request.getParameter("issueNum");
		String productType = request.getParameter("productType");
		
		String orderId = request.getParameter("orderId");
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	   
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    String result = "";
//	    判断是否做过foheader 
	    String foId= null;
	    foId = IsRepeatProduct.searchIsFo(orderId, productId, issueNum);
	    if(foId == null||foId.isEmpty()){
	    	result = "还未添加制造工艺，不可编制工艺路线。";
	    }else{
	    
			String sqla = "insert into fotemp (product_type, product_id, issue_num, item_id, fo_no, fo_ver, fo_operid, fo_opname, fo_opcontent, rated_time, plan_time, oper_aidtime, insp_time, oper_time, is_key, is_insp, is_arminsp, is_certop, is_co, wcid, equiptype, equipcode, department, section, isfirst, isassembe, demension, imgurl, createperson, createtime, changeperson, changetime, isinuse, cut, accessory, material, measure, tool, cutnum, measurenum, toolnum, materialnum, accessorynum, workbranch, checkperson, checktime, status, confirmadvice, craftpaper, foid, processissue_num, focode)" +
					" (select product_type, product_id, issue_num, item_id, fo_no, fo_ver, fo_operid, fo_opname, fo_opcontent, rated_time, plan_time, oper_aidtime, insp_time, oper_time, is_key, is_insp, is_arminsp, is_certop, is_co, wcid, equiptype, equipcode, department, section, isfirst, isassembe, demension, imgurl, createperson, createtime, changeperson, changetime, isinuse, cut, accessory, material, measure, tool, cutnum, measurenum, toolnum, materialnum, accessorynum, workbranch, checkperson, checktime, status, confirmadvice, craftpaper, foid, processissue_num, focode" +
					" from fo_detail t where  t.product_id= '"+sproductId+"' and t.issue_num= '"+sissueNum+"' and t.isinuse='1');";
			String sqlb = "update fotemp t set t.foid='"+foId+"',t.is_co ='0' ,t.product_id = '"+productId+"',t.product_type='"+productType+"',t.issue_num='"+issueNum+"',t.createperson ='"+createPerson+"' ,t.createtime=to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
					"t.changeperson='"+changePerson+"' ,t.changetime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss')," +
							"t.CONFIRMADVICE='',t.status='1' where t.product_id= '"+sproductId+"' and t.issue_num= '"+sissueNum+"' and t.isinuse='1';";
			String sqlc = "insert into fo_detail (product_type, product_id, issue_num, item_id, fo_no, fo_ver, fo_operid, fo_opname, fo_opcontent, rated_time, plan_time, oper_aidtime, insp_time, oper_time, is_key, is_insp, is_arminsp, is_certop, is_co, wcid, equiptype, equipcode, department, section, isfirst, isassembe, demension, imgurl, createperson, createtime, changeperson, changetime, isinuse, cut, accessory, material, measure, tool, cutnum, measurenum, toolnum, materialnum, accessorynum, workbranch, checkperson, checktime, status, confirmadvice, craftpaper, foid, processissue_num, focode)" +
			" (select product_type, product_id, issue_num, item_id, fo_no, fo_ver, fo_operid, fo_opname, fo_opcontent, rated_time, plan_time, oper_aidtime, insp_time, oper_time, is_key, is_insp, is_arminsp, is_certop, is_co, wcid, equiptype, equipcode, department, section, isfirst, isassembe, demension, imgurl, createperson, createtime, changeperson, changetime, isinuse, cut, accessory, material, measure, tool, cutnum, measurenum, toolnum, materialnum, accessorynum, workbranch, checkperson, checktime, status, confirmadvice, craftpaper, foid, processissue_num, focode" +
			" from fotemp t );";
			String sql = "begin  delete from  fotemp; " +sqla + sqlb + sqlc +" end; ";
			
			
			try {
				Sqlhelper.executeUpdate(sql, null);
				result = "操作成功";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = "操作失败";
			}
	    }
		String json = "{\"result\":\""+result+"\"}";
		System.out.println(json);
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
