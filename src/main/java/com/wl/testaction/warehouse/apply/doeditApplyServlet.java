package com.wl.testaction.warehouse.apply;

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

public class doeditApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditApplyServlet() {
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
		HashMap<String,String> datamap= (HashMap<String, String>) Test.JSON.Decode(data);
		String applySheetid=request.getParameter("applySheetid");
		String itemId=datamap.get("itemId");
		String itemName=datamap.get("itemName");
		String spec=datamap.get("spec");
		String unit=datamap.get("unit");
		String itemType=datamap.get("itemType");
		String usage=datamap.get("usage");
		String poNum=datamap.get("poNum");
		String memo=datamap.get("memo");
		String SitemId=datamap.get("SitemId");
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String changeTime = df.format(new Date());
		
		String applySql="update poapplydetl set itemid='"+itemId+"',itemname='"+itemName+"',spec='"+spec+"',unit='"+unit+"',itemtype='"+itemType+"'," +
				"usage='"+usage+"',ponum='"+poNum+"',memo='"+memo+"',changeperson='"+changePerson+"',changetime=to_date('"+changeTime+"','yyyy-mm-dd,hh24-mi-ss') where applysheetid='"+applySheetid+"' and itemid='"+SitemId+"'";
		try{
			Sqlhelper.executeUpdate(applySql, null);
			String json="{\"result\":\"操作成功！\"}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			out.append(json).flush();
			e.printStackTrace();
		}
		
	}

}
