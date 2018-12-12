package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.tools.StringUtil;
import com.wl.forms.OutAssist;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class OutAssistListServlet extends HttpServlet {

	private static final long serialVersionUID = 7275440415083227921L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "companyId";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));

	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
	    
	    String totalCountSql = "select count(*) from outAssist where createPerson=? ";
	    String[] params1 = {userId};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql= "select orderId,deptId,companyId,companyName,principal," +
	    		"connectorName,connectorTel,startDate,planEndDate,trueEndDate," +
	    		"fine,shouldPay,alreadyPay,notPay,isBusy,EI.staff_name principalName " +
		    	"from (select A.*,ROWNUM row_num from (select EM.* from outAssist EM where createPerson=? order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
		    	"left join employee_info EI on EI.staff_code=B.principal "+
		    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
		String[] params = {userId};
		
		List<OutAssist> customerList = new ArrayList<OutAssist>();
		try {
			customerList = Sqlhelper.exeQueryList(sql, params, OutAssist.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(customerList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);

//	    System.out.println("sql=="+sql);
//	    
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(sql, null);
//			List<OutAssist> customerList = new ArrayList<OutAssist>();
//			try {
//				while (rs.next()) {
//					OutAssist result = new OutAssist();
//					result.setOrderId(rs.getString(1));
//					result.setDeptId(rs.getString(2));
//					result.setCompanyId(rs.getString(3));
//					result.setCompanyName(rs.getString(4));
//					result.setPrincipal(rs.getString(5));
//					result.setConnectorName(rs.getString(6));
//					result.setConnectorTel(rs.getString(7));
//					result.setStartDate(rs.getString(8));
//					result.setPlanEndDate(rs.getString(9));
//					result.setTrueEndDate(rs.getString(10));
//					
//					result.setFine(rs.getDouble(11));
//					result.setShouldPay(rs.getDouble(12));
//					result.setAlreadyPay(rs.getDouble(13));
//					result.setNotPay(rs.getDouble(14));
//					result.setIsBusy(rs.getString(15));
//					result.setPrincipalName(rs.getString(16));
//
//					
//					customerList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(customerList);
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
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













