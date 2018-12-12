package com.wl.testaction.warehouse.supplier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.Customer;
import com.wl.forms.Supplier;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetSupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetSupplierServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "COMPANYID";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
//	    HttpSession session = request.getSession();
//		String userId = ((User)session.getAttribute("user")).getUserId();
	    
	    String totalCountSql = "select count(*) from supplier where istogether='Y'";
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String SupplierSql= "";
	    SupplierSql = 
	    	"select COMPANYID,COMPANYNAME,FOUNDEINGTIME,EMPLLOYEENUM,TYPE,ADDRESS,POSTCODE,TELEPHONE," +
	    	"WEBADDRESS,HEADER,BUSINESS,DUTYPARAGRAPH,BANK,ACCOUNT,ADVISE,C.Name typeName,connector,connectorTel " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from SUPPLIER EM order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" and ISTOGETHER='Y' order by "+orderStr+") B " +
	    	"left join companytype C on B.TYPE=C.id " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	
	    List<Supplier> supplierList = new ArrayList<Supplier>();
	    try {
	    	supplierList = Sqlhelper.exeQueryList(SupplierSql,null, Supplier.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    String json = PluSoft.Utils.JSON.Encode(supplierList);
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
