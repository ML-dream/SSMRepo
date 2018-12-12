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
import com.wl.forms.OutAssistCom;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class OutAssistComListServlet extends HttpServlet {

	private static final long serialVersionUID = 49040000705475944L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "companyId";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
		String totalCountSql = "select count(*) from outAssistCom where istogether='1'";
        String[] Params={};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, Params);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql= "select companyId,companyName,foundingTime,employeeNum,type," +
	    		"address,postCode,telephone,webAddress,header," +
	    		"business,connector,connectorTel,connectorQQ,connectorEmail," +
	    		"bank,account,dutyParagraph,founding,CO.name typeName,nvl(passrate,0) passrate,memo " +
		    	"from (select A.*,ROWNUM row_num from (select EM.* from outassistcom EM where istogether='1' order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
		    	"left join companytype CO on CO.id=B.type "+
		    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
		
		String[] params = {};
		List<OutAssistCom> customerList = new ArrayList<OutAssistCom>();
		try {
			customerList = Sqlhelper.exeQueryList(sql, params, OutAssistCom.class);
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
//			List<OutAssistCom> customerList = new ArrayList<OutAssistCom>();
//			try {
//				while (rs.next()) {
//					OutAssistCom result = new OutAssistCom();
//					result.setCompanyId(rs.getString(1));
//					result.setCompanyName(rs.getString(2));
//					result.setFoundingTime(rs.getString(3));
//					result.setEmployeeNum(rs.getInt(4));
//					result.setType(rs.getString(5));
//					
//					result.setAddress(rs.getString(6));
//					result.setPostCode(rs.getString(7));
//					result.setTelephone(rs.getString(8));
//					result.setWebAddress(rs.getString(9));
//					result.setHeader(rs.getString(10));
//					
//					result.setBusiness(rs.getString(11));
//					result.setConnector(rs.getString(12));
//					result.setConnectorTel(rs.getString(13));
//					result.setConnectorQQ(rs.getString(14));
//					result.setConnectorEmail(rs.getString(15));
//					
//					result.setBank(rs.getString(16));
//					result.setAccount(rs.getString(17));
//					result.setDutyParagraph(rs.getString(18));
//					result.setFounding(rs.getDouble(19));
//					result.setTypeName(rs.getString(20));
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













