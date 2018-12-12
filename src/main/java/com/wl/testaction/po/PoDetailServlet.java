package com.wl.testaction.po;

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

import com.wl.forms.ApplyDetail;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class PoDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PoDetailServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(this.getClass().getName()+"采购订货");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String applySheetid=request.getParameter("applySheetid");
		String posheetid=request.getParameter("posheetid");
//		String status=request.getParameter("status");
		String itemid=(String) datamap.get("itemId");
		String itemname=(String) datamap.get("itemName");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String itemTypeDesc=(String) datamap.get("itemType");
		String usage=(String) datamap.get("usage");
		String poNum=(String) datamap.get("poNum");
		String unitPrice=(String) datamap.get("unitPrice");
		String price=(String) datamap.get("price");
		String orderId=(String) datamap.get("order_id");
		
		double num = Double.parseDouble(poNum);
		double uprice = Double.parseDouble(unitPrice);
		double totalPrice=StringUtil.isNullOrEmpty(price)?num*uprice:Double.parseDouble(price);
		
		
		//String memo=(String) datamap.get("memo");
		
		HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=df.format(new Date());
		String changeTime=df.format(new Date());
		
		double alreadyNum=0;
		String alraedynumSql="select alreadynum from poapplydetl where applysheetid='"+applySheetid+"' and itemid='"+itemid+"'";
		try{
			ApplyDetail applydetail=new ApplyDetail();
			applydetail=Sqlhelper.exeQueryBean(alraedynumSql, null, ApplyDetail.class);
			alreadyNum=applydetail.getAlreadyNum();
			alreadyNum=alreadyNum+num;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String applySql="update poapplydetl set alreadynum='"+alreadyNum+"' where applysheetid='"+applySheetid+"' and itemid='"+itemid+"'";
//		String statusSql="update apply set ispass='"+status+"' where applysheetid='"+applySheetid+"'";
		String sql="insert into poplan_detl (po_sheetid,item_id,item_name,spec,unit,po_num,unitprice,price,orderid," +
				"kind,usage,createperson,createtime,changeperson,changetime) values('"+posheetid+"','"+itemid+"','"+itemname+"'," +
					"'"+spec+"','"+unit+"','"+poNum+"','"+unitPrice+"','"+totalPrice+"','"+orderId+"','"+itemTypeDesc+"','"+usage+"'," +
					"'"+createPerson+"',to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'))";
		try{
			Sqlhelper.executeUpdate(sql, null);
			Sqlhelper.executeUpdate(applySql, null);
//			Sqlhelper.executeUpdate(statusSql, null);
			String json = "{\"result\":\"操作成功!\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
