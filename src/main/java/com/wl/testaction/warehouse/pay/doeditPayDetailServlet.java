package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class doeditPayDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditPayDetailServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String,String>) Test.JSON.Decode(data);
		String paySheetid=datamap.get("paySheetid");
		String prSheetid=datamap.get("prSheetid");
		String prdate=datamap.get("prdate");
		String itemId=datamap.get("itemId");
		String thispay=datamap.get("thispay");
		String memo=datamap.get("memo");
		
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		
		String paySql="update popaydetl set thispay='"+thispay+"',memo='"+memo+"',changeperson='"+changePerson+"'," +
				"changetime=to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss') where paysheetid='"+paySheetid+"' and " +
				"prsheetid='"+prSheetid+"' and itemid='"+itemId+"'";
		try{
			Sqlhelper.executeUpdate(paySql, null);
			String json="{\"result\":\"操作成功！\"}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			out.append(json).flush();
			e.printStackTrace();
		}
	
	}

}
