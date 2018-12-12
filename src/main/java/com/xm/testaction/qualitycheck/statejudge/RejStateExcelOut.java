package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ExportExcelUtil;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

public class RejStateExcelOut extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RejStateExcelOut() {
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

		String bdate= StringUtil.isNullOrEmpty(request.getParameter("bdate"))?"":request.getParameter("bdate");
		String edate= StringUtil.isNullOrEmpty(request.getParameter("edate"))?"":request.getParameter("edate");
		
		String barcode = StringUtil.isNullOrEmpty(request.getParameter("barcode"))?"":request.getParameter("barcode").trim();
		String sourcer = StringUtil.isNullOrEmpty(request.getParameter("sourcer"))?"":request.getParameter("sourcer").trim();
		
		String opinion = StringUtil.isNullOrEmpty(request.getParameter("opinion"))?"":request.getParameter("opinion").trim();
		String condition = " where 1=1 ";
		if(!bdate.isEmpty()&&!edate.isEmpty()){	//opiniondate表
			condition += " and g.opiniondate between to_date('"+bdate+"','yyyy-MM-dd HH:mi:ss') and to_date('"+edate+"','yyyy-MM-dd HH:mi:ss')";
		}
		if(!barcode.isEmpty()){
			condition += " and re.barcode='"+barcode+"' ";
		}
		if(!sourcer.isEmpty()){
			condition += " and re.prosource='"+sourcer+"'";
		}
		if(!opinion.isEmpty()){
			condition += " and re.opinion='"+opinion+"'";
		}
		String sql ="select to_char(a.checkdate,'yyyy-MM-dd') checkDate,c.customer companyId,d.companyname,b.drawingid productId,b.product_name productName,t.shouldbe,t.realbe,to_char(t.rejectnum) rejNum,t.dutyman dutyer,e.staff_name dutyerName,f.companyname outCompany,to_char(t.rejecttype) rejType," +
				" t.opinion opinionid,to_char(t.timeloss) qualityLoss,t.prosource,t.runnum,t.barcode,z.typeName rejTypeName,to_char(t.QUALITYFEE) QUALITYFEE,y.name opinion from (" +
				" select em.*,rownum rn from " +
				" (select re.*  from reject_state re  " +
				" left join opiniondate g on g.runnum= re.runnum " +
				condition+
				" order by re.runnum desc" +
				" ) em order by em.runnum desc )t  " +
				"left join po_routing2 a on a.barcode=t.barcode and a.fo_no = t.fo_no  " +
				"left join po_router b on b.barcode=t.barcode  " +
				"left join orders c on c.order_id= b.order_id  " +
				"left join customer d on d.companyid=c.customer  " +
				"left join employee_info e on e.staff_code= t.dutyman " +
				"left join outassistcom f on f.companyid = t.dutyman " +
				"left join rejType z on z.typeId = t.rejecttype " +
				"left join opinion y on y.id=t.opinion " +
//				"where rn >"+min+" and rn <"+max+
				" order by t.runnum desc  ";
		
//		String totalsql="select count(*) from (" +
//		"select o.runnum from opiniondate g "+
//		"left join reject_state re on re.runnum=g.runnum "+condition+")";
//		
//		int total = 0;
//		ResultSet totalRs =null;
//		try {
//			totalRs = Sqlhelper0.executeQuery(totalsql, null);
//			totalRs.next();
//			total = totalRs.getInt(1);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally{
//			try {
//				if(totalRs!=null){
//					totalRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		List<WaitJudgeBean> waitList = new ArrayList<WaitJudgeBean>();
		try {
			waitList =Sqlhelper.exeQueryList(sql, null, WaitJudgeBean.class);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
		}
//		对外协责任者的认定
		String outCompany = "";
		for(int i=0,j = waitList.size(); i<j;i++){
			WaitJudgeBean bean = new WaitJudgeBean();
			bean = waitList.get(i);
			outCompany = bean.getOutCompany();
			if(outCompany!=null && !outCompany.isEmpty()){
				bean.setDutyerName(outCompany);
			}
		}
//		数量，质量损失，质量罚款
//		String sqla = "select to_char(sum(re.rejectnum)) numSum,to_char(sum(re.timeloss)) lossSum,to_char(sum(re.qualityfee)) feeSum from reject_state re " +
//				"left join opiniondate g on g.runnum= re.runnum "+condition;
//		WaitJudgeBean sumBean = new WaitJudgeBean();
//		try {
//			sumBean =Sqlhelper.exeQueryBean(sqla, null, WaitJudgeBean.class);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		
		liebiaoxiang.put("checkDate", "质检日期");
		liebiaoxiang.put("companyId", "客户编号");
		liebiaoxiang.put("companyname", "客户名称");
		liebiaoxiang.put("productId", "产品号");
		liebiaoxiang.put("productName", "产品名称");
		liebiaoxiang.put("shouldbe", "应测值");
		liebiaoxiang.put("realbe", "实测值");
		liebiaoxiang.put("rejNum", "不合格数量");
		
		liebiaoxiang.put("dutyer", "责任者工号");
		liebiaoxiang.put("dutyerName", "责任者");
		liebiaoxiang.put("opinion", "处理意见");
		liebiaoxiang.put("qualityLoss", "质量损失");
		liebiaoxiang.put("rejTypeName", "问题分析"); 
		liebiaoxiang.put("QUALITYFEE", "质量罚款");
		liebiaoxiang.put("runnum", "审理单单号");
		
		List<Integer> columnWidth = new ArrayList<Integer>();
		columnWidth.add(6500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		
		columnWidth.add(6500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		
		ExportExcelUtil.exportExcel(request, response, liebiaoxiang, columnWidth, waitList);
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
