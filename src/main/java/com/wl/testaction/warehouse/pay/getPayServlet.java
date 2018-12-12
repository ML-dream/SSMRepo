package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoPayDetl;
import com.wl.tools.Sqlhelper;

public class getPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getPayServlet() {
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
		String prSheetid=request.getParameter("prSheetid");
		String itemId=request.getParameter("itemId");
		String price1=request.getParameter("price");
		double haspaid=0;
		double nopay=0;
		double price=0;
		String paySql="select * from popaydetl where PRSHEETID=? and ITEMID=?";
		String[] params={prSheetid,itemId};
		List<PoPayDetl> resultList=new ArrayList<PoPayDetl>();
		
		try{
			resultList=Sqlhelper.exeQueryList(paySql, params, PoPayDetl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i=0,len=resultList.size();i<len;i++){
			PoPayDetl popay=new PoPayDetl();
			popay=resultList.get(i);
			haspaid+=popay.getThispay();	
		}
		price=price1.equals("")?0:Double.parseDouble(price1);
		nopay=price-haspaid;
		String json="{\"haspaid\":"+haspaid+",\"nopay\":"+nopay+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
