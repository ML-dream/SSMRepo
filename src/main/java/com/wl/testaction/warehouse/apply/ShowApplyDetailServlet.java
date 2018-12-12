package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ApplyDetail;
import com.wl.tools.Sqlhelper;

public class ShowApplyDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowApplyDetailServlet() {
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
		String applySheetid=request.getParameter("applySheetid");
		String applySql="select APPLYSHEETID,ITEMID,ITEMNAME,SPEC,UNIT,ITEMTYPE,C.ITEM_TYPEDESC itemTypeDesc,USAGE,PONUM,MEMO,ALREADYNUM " +
				"from (select A.*,rownum row_num from (select EM.* from poapplydetl EM where applysheetid='"+applySheetid+"' order by applysheetid ) A where rownum<="+(pageSize*pageNow)+" order by applysheetid) B " +
				"left join itemtype C on C.item_typeid=B.itemtype " +
				"where row_num>="+(pageSize*(pageNow-1)+1)+"";
		List<ApplyDetail> resultList=new ArrayList<ApplyDetail>();
		try{
			resultList=Sqlhelper.exeQueryList(applySql, null, ApplyDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		out.append(json).flush();
		System.out.println(json);
		
	}

}
