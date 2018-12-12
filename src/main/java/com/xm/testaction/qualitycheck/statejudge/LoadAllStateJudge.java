package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;
import com.xm.testaction.qualitycheck.PoFlowBean;

public class LoadAllStateJudge extends HttpServlet {
//加载所有待处理不合格品审理单
	/**
	 * Constructor of the object.
	 */
	public LoadAllStateJudge() {
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
//		待矫正,显示所有主管领导未审核的
		String user = "user";
//		待矫正
//		HttpSession session = request.getSession();
//		if(session.getAttribute("user")!=null){
//			user =(String) session.getAttribute("user");
//		} 
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
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
				" t.opinion,to_char(t.timeloss) qualityLoss,t.prosource,t.runnum,t.barcode,z.typeName rejTypeName,to_char(t.QUALITYFEE) QUALITYFEE from (" +
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
				"where rn >"+min+" and rn <"+max+
				" order by t.runnum desc  ";
		
		String totalsql="select count(*) from (" +
		"select g.runnum from opiniondate g "+
		"left join reject_state re on re.runnum=g.runnum "+condition+")";
		
		int total = 0;
		ResultSet totalRs =null;
		try {
			totalRs = Sqlhelper0.executeQuery(totalsql, null);
			totalRs.next();
			total = totalRs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(totalRs!=null){
					totalRs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
//		数量，质量损失，质量罚款    to_char(nvl(sum(re.qualityfee),0))   to_char(sum(re.qualityfee))
		String sqla = "select to_char(sum(re.rejectnum)) numSum,to_char(sum(re.timeloss)) lossSum,to_char(nvl(sum(re.qualityfee),0)) feeSum from reject_state re " +
				"left join opiniondate g on g.runnum= re.runnum "+condition;
		WaitJudgeBean sumBean = new WaitJudgeBean();
		try {
			sumBean =Sqlhelper.exeQueryBean(sqla, null, WaitJudgeBean.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(waitList);
		json = "{\"total\":"+total+",\"numSum\":"+sumBean.getNumSum()+",\"lossSum\":"+sumBean.getLossSum()+",\"feeSum\":"+sumBean.getFeeSum()+",\"data\":"+json+"}";
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
