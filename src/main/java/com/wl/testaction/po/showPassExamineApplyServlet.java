package com.wl.testaction.po;

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
import com.wl.tools.StringUtil;

public class showPassExamineApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showPassExamineApplyServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		
		String applyState = "3";
		String itemType = "";
		
		applyState = StringUtil.isNullOrEmpty(request.getParameter("applyState"))? applyState:request.getParameter("applyState");
		itemType = StringUtil.isNullOrEmpty(request.getParameter("applyType"))? itemType:request.getParameter("applyType");
		
		String condition = " where 1=1 and ispass='"+applyState+"'";
		
		String totalCountSql="select count(*) from apply  " ;
		
		String applySql="select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,B.DEPTID,D.DEPTNAME deptName,ISPASS,EXAMINEID,E.staff_name examineName,orderid " +
			"from(select A.*,rownum row_num from(select EM.* from apply EM "+condition+" order by applydate desc,APPLYSHEETID desc) A where rownum<="+(pageSize*pageNow)+" order by  applydate desc,APPLYSHEETID desc ) B " +
			"left join EMPLOYEE_INFO C on C.staff_code=B.applicantid " +
			"left join dept D on D.deptid=B.deptid " +
			"left join employee_info E on E.staff_code=B.examineid " +
			"where row_num>="+(pageSize*(pageNow-1)+1)+"  order by applydate desc,APPLYSHEETID desc";
		
		totalCountSql += condition;
		
		if(!itemType.isEmpty()){
			
			applySql ="select APPLYDATE,APPLYSHEETID,APPLICANTID,applicantName,DEPTID,deptName,ISPASS,EXAMINEID,examineName,orderid from (" +
					"select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,f.DEPTID,D.DEPTNAME deptName,ISPASS,EXAMINEID,E.staff_name examineName,orderid,rownum rn  from " +
					"( select distinct APPLYDATE,a.APPLYSHEETID,APPLICANTID,ISPASS,EXAMINEID,orderid,a.deptid " +
					" from POAPPLYDETL t " +
					"       left join apply a on a.applysheetid=t.applysheetid " +
					"  where t.itemtype='"+itemType+"' and a.ispass='"+applyState+"' order by APPLYDATE desc,APPLYSHEETID desc )f  " +
					"  left join EMPLOYEE_INFO C on C.staff_code= f.APPLICANTID" +
					"			left join dept D on D.deptid=f.deptid " +
					"			left join employee_info E on E.staff_code=f.examineid " +
					") where "+(pageSize*(pageNow-1)+1)+"<=rn and rn <="+(pageSize*pageNow)+" order by applydate desc,APPLYSHEETID desc";
			totalCountSql ="select count(1) from( select distinct a.APPLYSHEETID " +
					" from POAPPLYDETL t " +
					"       left join apply a on a.applysheetid=t.applysheetid" +
					"  where t.itemtype='"+itemType+"' and a.ispass='"+applyState+"')";
		}
		
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		List<Apply> resultList=new ArrayList<Apply>();
		try{
			resultList=Sqlhelper.exeQueryList(applySql, null, Apply.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
