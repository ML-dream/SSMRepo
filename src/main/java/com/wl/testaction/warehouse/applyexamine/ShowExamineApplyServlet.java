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

public class ShowExamineApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowExamineApplyServlet() {
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
		String poSheetid=request.getParameter("sheetid");
		String postartDate=request.getParameter("date");
		String orderId=request.getParameter("orderId");
	//	String applicantName=request.getParameter("applicant");
	//	String deptId=request.getParameter("dept");
		String companyId=request.getParameter("companyId");
		String status=request.getParameter("status");
		String drawerId=request.getParameter("drawerId");
		String examineId=request.getParameter("examineId");
		
//	and orderid like '"+orderId+"%'   and orderid like '"+orderId+"%'
		
		String totalCountSql="select count(*) from po_plan where status>=2 and status<=3 and po_sheetid like '"+poSheetid+"%' and to_char(postart_date,'yyyy-MM-dd hh24:mi:ss') like '"+postartDate+"%' " +
			" and status like '"+status+"%' and drawerid like '"+drawerId+"%' and examineid like '"+examineId+"%' and customerid like '"+companyId+"%'";
		String poSql="select POSTART_DATE,PO_SHEETID,DRAWERID,C.STAFF_NAME drawerName,STATUS,EXAMINEID,E.staff_name examinename,orderid,CUSTOMERID,D.companyname customername " +
				"from(select A.*,rownum row_num from(select EM.* from po_plan EM where status>=2 and status<=4 and po_sheetid like '"+poSheetid+"%' and to_char(postart_date,'yyyy-MM-dd hh24:mi:ss') like '"+postartDate+"%' " +
				" and status like '"+status+"%' and drawerid like '"+drawerId+"%' and examineid like '"+examineId+"%' and customerid like '"+companyId+"%' order by po_sheetid asc) A where rownum<="+(pageSize*pageNow)+" order by po_sheetid) B " +
						"left join EMPLOYEE_INFO C on C.staff_code=B.drawerId " +
						"left join supplier D on D.companyid=B.customerid " +
						"left join employee_info E on E.staff_code=B.examineId " +
						"where row_num>="+(pageSize*(pageNow-1)+1)+"";
		
		
		List<Poplan> resultList=new ArrayList<Poplan>();
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			resultList=Sqlhelper.exeQueryList(poSql, null, Poplan.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
		
	}

}
