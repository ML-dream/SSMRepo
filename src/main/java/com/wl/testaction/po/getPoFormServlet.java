package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ApplyDetail;
import com.wl.tools.Sqlhelper;

public class getPoFormServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getPoFormServlet() {
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
		String Sql="select B.APPLYSHEETID,ITEMID,ITEMNAME,SPEC,UNIT,ITEMTYPE,USAGE,PONUM,MEMO,D.ORDERID orderId " +
		"from poapplydetl B left join apply D on D.applysheetid=B.applysheetid " +
		"where B.applysheetid=? and B.itemid=?";
		String[] params={applySheetid,itemId};
		ApplyDetail apply=new ApplyDetail();
		
		try{
			apply=Sqlhelper.exeQueryBean(Sql, params, ApplyDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(apply);
		out.append(json).flush();
		System.out.println(json);
	}

}
