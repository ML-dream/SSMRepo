package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Apply;
import com.wl.tools.Sqlhelper;

public class ShowApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowApplyServlet() {
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
		String totalCountSql="";
		String applySheetid=request.getParameter("sheetid");
		String applyDate=request.getParameter("date");
//		String isPass=request.getParameter("ispass");
		String orderId=request.getParameter("orderId");
		String applicantId=request.getParameter("applicantId");
		String applySql="";
		
		if(orderId.equals("")){
		totalCountSql="select count(*) from apply where applysheetid like '"+applySheetid+"%' and to_char(applydate,'yyyy-MM-dd hh24:mi:ss') like '"+applyDate+"%' " +
				"and applicantid like '"+applicantId+"%'";
		applySql="select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,B.DEPTID,D.DEPTNAME deptName,ISPASS,ORDERID " +
				"from(select A.*,rownum row_num from(select EM.* from apply EM where applysheetid like '"+applySheetid+"%' and to_char(applydate,'yyyy-MM-dd hh24:mi:ss') like '"+applyDate+"%' " +
				"and applicantid like '"+applicantId+"%' order by applydate desc,applysheetid desc ) A where rownum<="+(pageSize*pageNow)+" order by applydate desc,applysheetid desc ) B " +
						"left join EMPLOYEE_INFO C on C.staff_code=B.applicantid " +
						"left join dept D on D.deptid=B.deptid " +
						"where row_num>="+(pageSize*(pageNow-1)+1)+" order by applydate desc,applysheetid desc";
		
		}else if(!orderId.equals("")){
			totalCountSql="select count(*) from apply where applysheetid like '"+applySheetid+"%' and to_char(applydate,'yyyy-MM-dd hh24:mi:ss') like '"+applyDate+"%' " +
				"and applicantid like '"+applicantId+"%' and orderid like '"+orderId+"%'";
			applySql="select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,B.DEPTID,D.DEPTNAME deptName,ISPASS,ORDERID " +
				"from(select A.*,rownum row_num from(select EM.* from apply EM where applysheetid like '"+applySheetid+"%' and to_char(applydate,'yyyy-MM-dd hh24:mi:ss') like '"+applyDate+"%' " +
				"and applicantid like '"+applicantId+"%' and orderid like '"+orderId+"%' order by applydate desc,applysheetid desc ) A where rownum<="+(pageSize*pageNow)+" order by applydate desc,applysheetid desc ) B " +
					"left join EMPLOYEE_INFO C on C.staff_code=B.applicantid " +
					"left join dept D on D.deptid=B.deptid " +
					"where row_num>="+(pageSize*(pageNow-1)+1)+" order by applydate desc,applysheetid desc";
			
		}
		
		
		
		List<Apply> resultList=new ArrayList<Apply>();
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			resultList=Sqlhelper.exeQueryList(applySql, null, Apply.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
	} 

}
