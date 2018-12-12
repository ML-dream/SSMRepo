package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ApplyDetail;
import com.wl.tools.Sqlhelper;

public class editApplyDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editApplyDetailServlet() {
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
		String applySheetid=request.getParameter("applySheetid");
		String itemId=request.getParameter("itemId");
		String applySql="select ITEMID,ITEMNAME,SPEC,UNIT,ITEMTYPE,C.ITEM_TYPEDESC itemTypeDesc,USAGE,PONUM,MEMO " +
		"from poapplydetl B left join itemtype C on C.item_typeid=B.itemtype where applysheetid='"+applySheetid+"' and " +
				"itemid='"+itemId+"'";
		ApplyDetail applydetail=new ApplyDetail();
		try{
			applydetail=Sqlhelper.exeQueryBean(applySql, null, ApplyDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String json=PluSoft.Utils.JSON.Encode(applydetail);
		out.append(json).flush();
		System.out.println(json);
		
		
	}

}
