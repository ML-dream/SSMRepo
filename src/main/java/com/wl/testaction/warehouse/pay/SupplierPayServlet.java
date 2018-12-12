package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class SupplierPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SupplierPayServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String companyId=request.getParameter("companyId");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String Sql="";
		String paySql="";
		String qualitySql="";
		
//		4-21 ,默认只加载未对账的单子
		String para = StringUtil.isNullOrEmpty(request.getParameter("para"))?"1":request.getParameter("para"); 
		String condion =  " and ischecked=0 ";
		if(para.equals("0")){
//			表示要查看已对账的单子
			condion =  " and ischecked=1 ";
		}
		
		String totalCountSql="select count(*) from pr where customerid="+companyId+condion;
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String initialPaymentSql="select * from initialpopayment where companyid='"+companyId+"'";

		if(startDate.equals("")||endDate.equals("")){
			Sql="select B.prsheetid,B.customerid,D.companyname customerName,payTerm,cast(sum(C.price) as number(18,2)) price from " +
			"(select A.*,rownum row_num from (select EM.* from pr EM where customerid='"+companyId+"' "+condion+" order by prsheetid) A where rownum <="+pageNow*pageSize+") B " +
				"left join prdetail C on C.prsheetid=B.prsheetid " +
				"left join supplier D on D.companyid=B.customerid " +
		 		"where row_num>="+(pageSize*(pageNow-1)+1)+" " +
				"group by B.prsheetid,B.customerid,D.companyname,payTerm";
			paySql="select sum(thispaid) from supplierpayment where companyid='"+companyId+"'";
			qualitySql="select sum(qualitykk) from supplierpayment where companyid='"+companyId+"'";
		}else{
			Sql="select B.prsheetid,B.customerid,D.companyname customerName,payTerm,cast(sum(C.price) as number(18,2)) price from " +
			"(select A.*,rownum row_num from (select EM.* from pr EM where customerid='"+companyId+"' "+condion+" and payterm between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
				"and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')  order by prsheetid) A where rownum <="+pageNow*pageSize+") B " +
				"left join prdetail C on C.prsheetid=B.prsheetid " +
				"left join supplier D on D.companyid=B.customerid " +
				"where row_num>="+(pageSize*(pageNow-1)+1)+" " +
				"group by B.prsheetid,B.customerid,D.companyname,payTerm";
			paySql="select sum(thispaid) from supplierpayment where companyid='"+companyId+"' and paydate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
			qualitySql="select sum(qualitykk) from supplierpayment where companyid='"+companyId+"' and paydate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
		
		}
		
		List<pr> resultList=new ArrayList<pr>();
		try{
			resultList=Sqlhelper.exeQueryList(Sql, null, pr.class);
		}catch(Exception e){
			e.printStackTrace();
		}

		double totalprice=0;
		for(int i=0,l=resultList.size();i<l;i++){
			pr prprice=resultList.get(i);
			totalprice+=prprice.getPrice();
		}
		
		String hasPaid="";
		String qualityKk="";
		double haspaid=0;
		double qualitykk=0;
		double nopaid=0;
		try{
			hasPaid=Sqlhelper.exeQueryString(paySql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			qualityKk=Sqlhelper.exeQueryString(qualitySql, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(hasPaid==null){
			hasPaid="";
		}
		if(qualityKk==null){
			qualityKk="";
		}
		
		if(hasPaid==null){
			hasPaid="";
		}
		
		pr initialPaymentBean=new pr();
		try {
			initialPaymentBean = Sqlhelper.exeQueryBean(initialPaymentSql, null, pr.class);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		double initialPayment=initialPaymentBean.getInitialPayment();
		totalprice+=initialPayment;
		haspaid=hasPaid.equals("")?0:Double.parseDouble(hasPaid);
		qualitykk=qualityKk.equals("")?0:Double.parseDouble(qualityKk);
		nopaid=totalprice-haspaid-qualitykk;

		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+",\"totalPrice\":"+totalprice+",\"haspaid\":"+haspaid+",\"nopay\":"+nopaid+",\"qualitykk\":"+qualitykk+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
	}

}
