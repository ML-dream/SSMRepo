package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PrDetail;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class ShowSupplierPayDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowSupplierPayDetailServlet() {
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
		request.setCharacterEncoding("utf-8");
		String prSheetid=request.getParameter("prSheetid");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		
		String para = StringUtil.isNullOrEmpty(request.getParameter("para"))?"0":request.getParameter("para");	//页面参数，如果是 supplierpayment 页面，则para=1
		String lookChecked = StringUtil.isNullOrEmpty(request.getParameter("lookChecked"))?"0":request.getParameter("lookChecked");		//是否查看已对账，0表示只看未对账
		
		String totalCountSql="";
		int totalCount=0;
		if(para.equals("1")){
			if(lookChecked.equals("0")){
				totalCountSql="select count(*) from prdetail where prsheetid='"+prSheetid+"'  and status='1' and ischecked=0 ";
			}else{
				totalCountSql="select count(*) from prdetail where prsheetid='"+prSheetid+"'  and status='1' and ischecked=1 ";
			}
		}else {
			totalCountSql="select count(*) from prdetail where prsheetid='"+prSheetid+"'  and status='1' ";
		}
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
//		只取审核通过的,如果是付款页面，以及未标记的,
		String detailSql="";
		
		if(para.equals("1")){
			if(lookChecked.equals("0")){
//				表示只加载未对账的
				detailSql="select prSheetid,itemid itemId,itemname itemName,spec,unit,innum prnum,unitprice,price from" +
				"(select A.*,rownum row_num from(select EM.* from prdetail EM where prsheetid='"+prSheetid+"' and status='1' and ischecked=0 ) A " +
					"where rownum<="+pageSize*pageNow+") B where row_num>="+(pageSize*(pageNow-1)+1)+"";
			}else{
				detailSql="select prSheetid,itemid itemId,itemname itemName,spec,unit,innum prnum,unitprice,price from" +
				"(select A.*,rownum row_num from(select EM.* from prdetail EM where prsheetid='"+prSheetid+"' and status='1' and ischecked=1 ) A " +
					"where rownum<="+pageSize*pageNow+") B where row_num>="+(pageSize*(pageNow-1)+1)+"";
			}
			
		}else{
			detailSql="select prSheetid,itemid itemId,itemname itemName,spec,unit,innum prnum,unitprice,price from" +
			"(select A.*,rownum row_num from(select EM.* from prdetail EM where prsheetid='"+prSheetid+"' and status='1'  ) A " +		//and ischecked=0
				"where rownum<="+pageSize*pageNow+") B where row_num>="+(pageSize*(pageNow-1)+1)+"";
		}
		
		List<PrDetail> resultList=new ArrayList<PrDetail>();
		try{
			resultList=Sqlhelper.exeQueryList(detailSql, null, PrDetail.class);
		}catch(Exception e){
			e.printStackTrace(); 
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
