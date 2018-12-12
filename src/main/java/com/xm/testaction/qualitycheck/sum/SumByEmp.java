package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

public class SumByEmp extends HttpServlet {
//    按人员加载统计 
	/**
	 * Constructor of the object.
	 */
	public SumByEmp() {
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
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH);	//+1  是当前月 
		int date = c.get(Calendar.DATE); 
		
		String bdate= "" +year +"-"+month+"-"+date;
		String edate= "" +year +"-"+(month+1)+"-"+date;
		String dept = "scb";
		
		 String orderStr = "donenum";
		    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
		String sortOrder = "asc";
		
		sortOrder = StringUtil.isNullOrEmpty(request.getParameter("sortOrder"))?sortOrder:request.getParameter("sortOrder");
		dept = StringUtil.isNullOrEmpty(request.getParameter("dept"))?dept:request.getParameter("dept");
		bdate = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?bdate:request.getParameter("bdate");
		edate = StringUtil.isNullOrEmpty(request.getParameter("edate"))?edate:request.getParameter("edate");
		String worker = request.getParameter("worker");
		String conditon = "";	//附加条件
		String conditon2="";
		String rejectMode = StringUtil.isNullOrEmpty(request.getParameter("rejectMode"))?"":request.getParameter("rejectMode");	//1 表示查看有不合格产品的员工
		if(rejectMode.equals("1")){
			conditon = " and  a.rejectnum>0 ";
			conditon2= " and  t.rejectnum>0 ";
		}
//		检查po_routing2 表的数据完整性
		String poSql = "update po_routing2 t set t.confirm_num=t.reject_num+t.accept_num,t.usedtime=t.rated_time*(t.reject_num+t.accept_num) where t.usedtime is null";
		
		try {
			Sqlhelper.executeUpdate(poSql, null);
//			清空表中数据
//			SumByEmpClose.sumByEmpClose();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
//		查询，empsumtem 表里是否有记录
		String sqlb ="select count(*) from (" +
		"select rownum rn,a.staff_code " +
		"from empsumtem a " +
			"where a.dept = '"+dept+"' and a.btime = to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and a.etime = to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') " +
					"and a.staff_code like '%"+worker+"')" ;
		
//		ResultSet rsb = null;
		int count = 0 ;		//	判断是否有记录
		try {
//			rsb = Sqlhelper0.executeQuery(sqlb, null);
//			rsb.next();
			count =Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(count == 0){
//			如果没有，则执行先 多表查询，然后存数据 
//			如果有，直接从tem 表里取数据
//			若没有，则执行多表查询
			String workid = "";		// 先查询人员，然后循环 按人员查询数据
			String sqld = "select a.staff_code from employee_info a where a.section_code = '"+dept+"' and a.staff_code like '%"+worker+"'";
			ResultSet rsd = null;
			ArrayList<String> stafflist = new ArrayList<String>();
			try {
				rsd = Sqlhelper0.executeQuery(sqld, null);
				
				while(rsd.next()){
					workid =rsd.getString(1);	//所有人员
					stafflist.add(workid);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				try {
					if(rsd!=null){
						rsd.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//			查询储存 员工不合格数据详情
			EmpRejectSum.empRejectSum(stafflist, bdate, edate);
			
			String sqlc = "";
//		遍历所有人员，查询数据	
			for(int a=0,b=stafflist.size();a<b;a++){
				workid = stafflist.get(a);
				
				sqlc = "declare      " +
				"         btime date ;" +
				"         etime date  ;" +
				"begin" +
				"         btime := to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss');" +
				"         etime := to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss');" +		//x.section_code,sum(y.confirm_num )
				"         insert into empsumtem (staff_code, dept, donenum, acceptnum, rejectnum, fixnum, discardnum, ratedtime, donetime, fixtime, discardtime,  btime, etime)" +
				"               ( select y.workerid,x.section_code,sum(y.accept_num+y.reject_num ) anum,sum(y.accept_num),sum(y.reject_num) bnum," +
				" sum(nvl(z.fixnum,0)),sum(nvl(z.discardnum,0)),sum(nvl(y.rated_time,0)*y.confirm_num) timea,sum(nvl(y.usedtime,0)) timeb,sum(nvl(z.fixtime,0)),sum(nvl(z.discardtime,0)) timec," +

				" to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') btime, to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') etime" +
				" from po_routing2 y " +
				" left join employee_info x on x.staff_code = y.workerid " +
				"  left join empsumtem2 z on z.barcode = y.barcode and z.fo_no = y.fo_no and z.btime=btime and z.etime = etime "+		//
				" where y.workerid = '"+workid+"' " +
				"     and y.checkdate between to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss')" +
				"   group by y.workerid,x.section_code );" +
				"end;";
				
				try {
					System.out.println(sqlc );
					Sqlhelper0.executeUpdate(sqlc, null);
					System.out.println("ok  "+sqlc);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		

//		查询该部门 所有的人员
//		根据人员为条件，加上时间条件，到 po_routing2 表中
//		最后的结果，都是从empsumtem 中取数据   round((a.discardtime/a.donetime*100),2) timeper
		
		String sqla =	"select * from (select fr.*,rownum rn from "+
					"(select b.staff_name,c.deptname,a.staff_code,a.dept,a.donenum,a.acceptnum,a.rejectnum,round((a.rejectnum/a.donenum*100),2) rejectper, " +
					"a.fixnum,a.discardnum,a.ratedtime ratedtime,a.donetime,(a.donetime - a.ratedtime) timegap,a.discardtime,round(a.discardtime,2) timeper,a.fixtime  " +
//					"a.fixnum,a.discardnum,a.ratedtime*a.donenum ratedtime,a.donetime,(a.donetime - a.ratedtime*a.donenum) timegap,a.discardtime,round((a.discardtime/a.donetime*100),2) timeper,rownum rn  " +
					"from empsumtem a " +
				"left join employee_info b on b.staff_code = a.staff_code" +
				" left join dept c on c.deptid = a.dept " +
				"where a.dept = '"+dept+"' and a.btime = to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and a.etime = to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') " +
				"and a.staff_code like '%"+worker+"' " + conditon+
				"order by "+orderStr+" "+sortOrder+") fr order by "+orderStr+" "+sortOrder+" )"+
				"where rn>"+min+" and rn <= "+max;
		
		String totalsql= "select count(*) from (" +
			"select rownum rn,a.staff_code " +
							"from empsumtem a " +
								"where a.dept = '"+dept+"' and a.btime = to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and a.etime = to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss')"+conditon+" )" ;

		//		 临时，待矫正    如果筛选条件为整个车间，则查询所有数据    foradd
		if(dept.equals("scb")){
			// 如果部门筛选条件为空，默认查询 所有工作人员 
			sqla = "select * from (select fr.*,rownum rn from "+
			"(select b.staff_name,c.deptname,a.staff_code,a.dept,a.donenum,a.acceptnum,a.rejectnum,round((a.rejectnum/a.donenum*100),2) rejectper, " +
			"a.fixnum,a.discardnum,a.ratedtime ratedtime,a.donetime,(a.donetime - a.ratedtime) timegap,a.discardtime,round(a.discardtime,2) timeper,a.fixtime  " +
//			"a.fixnum,a.discardnum,a.ratedtime*a.donenum ratedtime,a.donetime,(a.donetime - a.ratedtime*a.donenum) timegap,a.discardtime,round((a.discardtime/a.donetime*100),2) timeper,a.fixtime,rownum rn  " +
			"from empsumtem a " +
			"left join employee_info b on b.staff_code = a.staff_code" +
			" left join dept c on c.deptid = a.dept " +
			"where  a.btime = to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and a.etime = to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') " +
			"and a.staff_code like '%"+worker+"' " +conditon +
			"order by "+orderStr+" "+sortOrder+" ) fr order by "+orderStr+" "+sortOrder+" )"+
			"where rn>"+min+" and rn <= "+max;
			
			totalsql = "select count(*) from (" +
				"select rownum rn,a.staff_code " +
			"from empsumtem a " +
			"where a.btime = to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and a.etime = to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') "+conditon+" )" ;

		}
		
		int total = 0;
		ResultSet totalRs = Sqlhelper0.executeQuery(totalsql, null);
		
		try {
			
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
		ResultSet rsa = null;
		ArrayList<SumByEmpBean> waitList = new ArrayList<SumByEmpBean>();
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			
			while (rsa.next()){
				SumByEmpBean bean = new SumByEmpBean();
				
				bean.setStaff_name(rsa.getString(1));
				bean.setDeptname(rsa.getString(2))  ;
				bean.setStaff_code(rsa.getString(3))  ;
				bean.setDept(rsa.getString(4))  ;
				bean.setDonenum(rsa.getString(5))  ;
				
				bean.setAcceptnum(rsa.getString(6))  ;
				bean.setRejectnum(rsa.getString(7))  ;
				bean.setRejectper(rsa.getString(8))  ;
				bean.setFixnum(rsa.getString(9))  ;
				bean.setDiscardnum(rsa.getString(10))  ;
				
				bean.setRatedtime(rsa.getString(11))  ;
				bean.setDonetime(rsa.getString(12))  ;
				bean.setTimegap(rsa.getString(13))  ;
				bean.setDiscardtime(rsa.getString(14))  ;
				bean.setTimeper(rsa.getString(15))  ;
				bean.setFixtime(rsa.getString(16));
				
				waitList.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(rsa!=null){
					rsa.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		汇总计算
		String sumSql ="select sum(t.donenum) donenum,sum(t.acceptnum) acceptnum,sum(t.rejectnum) rejectnum,sum(t.fixnum) fixnum," +
				"sum(t.discardnum) discardnum,sum(t.ratedtime) ratedtime,sum(t.donetime) donetime,sum(t.fixtime) fixtime,nvl(sum(t.timegap),0) timegap," +
				"sum(t.discardtime) discardtime from empsumtem t where t.dept = '"+dept+"' and t.btime = to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and t.etime = to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') " +
				"and t.staff_code like '%"+worker+"' " + conditon2;
		Map<String, String> sumMap = new HashMap<String, String>();
		try {
			System.out.println(sumSql);
			sumMap=Sqlhelper.executeQueryMap(sumSql, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = PluSoft.Utils.JSON.Encode(waitList);
		json = "{\"total\":"+total+",\"donenum\":"+sumMap.get("DONENUM")+",\"acceptnum\":"+sumMap.get("ACCEPTNUM")+",\"rejectnum\":"+sumMap.get("REJECTNUM")+",\"fixnum\":"+sumMap.get("FIXNUM")+"," +
				"\"discardnum\":"+sumMap.get("DISCARDNUM")+",\"ratedtime\":"+sumMap.get("RATEDTIME")+",\"donetime\":"+sumMap.get("DONETIME")+",\"fixtime\":"+sumMap.get("FIXTIME")+"," +
						"\"timegap\":"+sumMap.get("TIMEGAP")+",\"discardtime\":"+sumMap.get("DISCARDTIME")+",\"data\":"+json+"}";
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
