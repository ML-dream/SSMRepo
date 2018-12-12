package com.wl.testaction.warehouse.applyexamine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Apply;
import com.wl.forms.Poplan;
import com.wl.tools.Sqlhelper;

public class ApplyExamineServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ApplyExamineServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String totalCountSql="select count(*) from po_plan where status=1";
		String posheetid=request.getParameter("sheetid");
		String postartDate=request.getParameter("date");
		String orderId=request.getParameter("orderId");
		String companyId=request.getParameter("companyId");
		String drawerId=request.getParameter("drawerId");
//		String isPass=request.getParameter("ispass");
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(posheetid==null){
			posheetid="";
		}
		if(postartDate==null){
			postartDate="";
		}
		if(orderId==null){
			orderId="";
		}
		if(companyId==null){
			companyId="";
		}
		if(drawerId==null){
			drawerId="";
		}
//		and orderid like '"+orderId+"%'
		String applySql="select POSTART_DATE,PO_SHEETID,ORDERID,CUSTOMERID,DRAWERID,STATUS,C.STAFF_NAME drawername,D.COMPANYNAME customername " +
		"from(select A.*,rownum row_num from(select EM.* from po_plan EM where status=1 and po_sheetid like '"+posheetid+"%' " +
				"and to_char(postart_date,'yyyy-MM-dd') like'"+postartDate+"%'  and customerid like '"+companyId+"%' " +
						"and drawerid like '"+drawerId+"%' order by po_sheetid asc) A where rownum<="+(pageSize*pageNow)+" order by po_sheetid) B " +
		"left join EMPLOYEE_INFO C on C.staff_code=B.drawerid " +
		"left join supplier D on D.companyid=B.customerid " +
		"where row_num>="+(pageSize*(pageNow-1)+1)+"";
		
//		if(applySheetid.equals("")&&applyDate.equals("")&&orderId.equals("")){
//			applySql="select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,B.DEPTID,D.DEPTNAME deptName,ISPASS,ORDERID " +
//			"from(select A.*,rownum row_num from(select EM.* from apply EM where ispass=0 order by applysheetid asc) A where rownum<="+(pageSize*pageNow)+" order by applysheetid) B " +
//			"left join EMPLOYEE_INFO C on C.staff_code=B.applicantid " +
//			"left join dept D on D.deptid=B.deptid " +
//			"where row_num>="+(pageSize*(pageNow-1)+1)+"";
//		}else if(!applySheetid.equals("")){
//		applySql="select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,B.DEPTID,D.DEPTNAME deptName,ISPASS,ORDERID " +
//			"from(select A.*,rownum row_num from(select EM.* from apply EM where ispass=0 and applysheetid like '"+applySheetid+"%') A where rownum<="+(pageSize*pageNow)+" ) B " +
//					"left join EMPLOYEE_INFO C on C.staff_code=B.applicantid " +
//					"left join dept D on D.deptid=B.deptid " +
//					"where row_num>="+(pageSize*(pageNow-1)+1)+"";
//			
//		}else if(!applyDate.equals("")){
//			applySql="select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,B.DEPTID,D.DEPTNAME deptName,ISPASS,ORDERID " +
//			"from(select A.*,rownum row_num from(select EM.* from apply EM where ispass=0 and applydate like '"+applyDate+"%'  order by applysheetid asc) A where rownum<="+(pageSize*pageNow)+" order by applysheetid) B " +
//					"left join EMPLOYEE_INFO C on C.staff_code=B.applicantid " +
//					"left join dept D on D.deptid=B.deptid " +
//					"where row_num>="+(pageSize*(pageNow-1)+1)+"";
//		}else if(!orderId.equals("")){
//			applySql="select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,B.DEPTID,D.DEPTNAME deptName,ISPASS,ORDERID " +
//			"from(select A.*,rownum row_num from(select EM.* from apply EM where ispass=0 and orderid like '"+orderId+"%'  order by applysheetid asc) A where rownum<="+(pageSize*pageNow)+" order by applysheetid) B " +
//					"left join EMPLOYEE_INFO C on C.staff_code=B.applicantid " +
//					"left join dept D on D.deptid=B.deptid " +
//					"where row_num>="+(pageSize*(pageNow-1)+1)+"";
//		}
//		
//		
//		
		
		
		
		List<Poplan> resultList=new ArrayList<Poplan>();
		try{
			resultList=Sqlhelper.exeQueryList(applySql, null, Poplan.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
	}

}
