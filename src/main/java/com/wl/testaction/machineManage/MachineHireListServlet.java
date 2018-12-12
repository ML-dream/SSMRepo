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
import com.wl.forms.MachineDiscard;
import com.wl.forms.MachineHire;
import com.wl.forms.MachineRepair;
import com.wl.tools.Sqlhelper;

public class MachineHireListServlet extends HttpServlet {

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
	    
	    String totalCountSql = "select count(*) from machineHire ";
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	    String CustomerSql= "";
	    CustomerSql = 
	    	"select machineId,B.deptId,hireStatus,outDate,inDate,backDate,hireMoney,hireNum,principal,D.deptName,E.staff_name staffname " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from machineHire EM order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+"  order by "+orderStr+") B " +
	    	"left join dept D on D.deptId=B.deptId "+
	    	"left join employee_info E ON E.staff_code=B.principal "+
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    
	    List<MachineHire> customerList = new ArrayList<MachineHire>();
	    try {
			customerList = Sqlhelper.exeQueryList(CustomerSql, null, MachineHire.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0,len=customerList.size();i<len;i++){
			MachineHire machineHire = new MachineHire();
			machineHire = customerList.get(i);
			machineHire.setOutDate("1".equals(machineHire.getHireStatus())?"":machineHire.getOutDate());
			machineHire.setInDate("0".equals(machineHire.getHireStatus())?"":machineHire.getInDate());
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(customerList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	
//	    System.out.println("CustomerSql=="+CustomerSql);
//	    
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(CustomerSql, null);
//			List<MachineHire> customerList = new ArrayList<MachineHire>();
//			try {
//				while (rs.next()) {
//					MachineHire result = new MachineHire();
//					
//					result.setMachineId(rs.getString(1));
//					result.setDeptId(rs.getString(2));
//					result.setHireStatus(rs.getString(3));
//					result.setOutDate("1".equals(rs.getString(3))?"":rs.getString(4));
//					result.setInDate("0".equals(rs.getString(3))?"":rs.getString(5));
//					result.setBackDate(rs.getString(6));
//					result.setHireMoney(rs.getDouble(7));
//					result.setHireNum(rs.getInt(8));
//					result.setPrincipal(rs.getString(9));
//					result.setDeptName(rs.getString(10));
//					
//					customerList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(customerList);
//			
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//			e.printStackTrace();
//		}  finally{
//			try {
//				if(rs!=null){
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













