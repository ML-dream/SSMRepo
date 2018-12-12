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

import com.wl.tools.StringUtil;
import com.wl.forms.MachineRepair;
import com.wl.tools.Sqlhelper;

public class MachineRepairListServlet extends HttpServlet {

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
	    
	    String totalCountSql = "select count(*) from machineRepair ";
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	    String CustomerSql= "";
	    CustomerSql = 
	    	"select machineId,repairPart,repairFactory,errorDate,repairDate,repairPrice,principal,repairDetail,E.staff_name staffName " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from machineRepair EM order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+"  order by "+orderStr+") B " +
	    	"left join employee_info E ON E.staff_code=B.principal " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    
	    List<MachineRepair> customerList = new ArrayList<MachineRepair>();
	    
	    try {
			customerList = Sqlhelper.exeQueryList(CustomerSql, null, MachineRepair.class);
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













