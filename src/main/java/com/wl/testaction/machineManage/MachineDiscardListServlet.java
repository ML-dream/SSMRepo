package com.wl.testaction.machineManage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.sql_data;

import com.wl.tools.StringUtil;
import com.wl.forms.MachineDiscard;
import com.wl.tools.Sqlhelper;

public class MachineDiscardListServlet extends HttpServlet {

	private static final long serialVersionUID = 6768627425357375823L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "machineId";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String totalCountSql = "select count(*) from machineDiscard ";
	    
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String CustomerSql= "";
	    CustomerSql = 
	    	"select machineId,B.deptId,discardDate,discardTo,discardMoney,principal,D.deptName,E.staff_name staffName " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from machineDiscard EM order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+"  order by "+orderStr+") B " +
	    	"left join dept D on D.deptId=B.deptId " +
	    	"left join employee_info E ON E.staff_code=B.principal "+
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    
	    List<MachineDiscard> customerList = new ArrayList<MachineDiscard>();
	    try {
			customerList = Sqlhelper.exeQueryList(CustomerSql, null, MachineDiscard.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(customerList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













