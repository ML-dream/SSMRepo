package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.PoData;
import com.wl.tools.Sqlhelper;

public class payServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public payServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String payDate=(String) datamap.get("payDate");
		String paySheetid=(String) datamap.get("paySheetid");
		String customerId=(String) datamap.get("customerId");
		String connector=(String) datamap.get("connector");
		String connectorTel=(String) datamap.get("connectorTel");
		String payType=(String) datamap.get("payType");
		String payable=datamap.get("payable").toString().trim();
		String prepaid=datamap.get("prepaid").toString().trim();
		String payment=datamap.get("payment").toString().trim();
		String isBill=(String) datamap.get("isBill");
		String payMethod=(String) datamap.get("payMethod");
		String account=(String) datamap.get("account");
		
		String examineId=(String) datamap.get("examineId");
		String salesmanId=(String) datamap.get("salesmanId");
		String drawerId=(String) datamap.get("drawerId");
		HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=df.format(new Date());
		String changeTime=df.format(new Date());
		
		String id=(String) datamap.get("id");
		String seq=(String) datamap.get("seq");
		
		String PrSheetId1=(String) datamap.get("PrSheetId1");
		String PrSheetId2=(String) datamap.get("PrSheetId2");
		String PrSheetId3=(String) datamap.get("PrSheetId3");
		String PrSheetId4=(String) datamap.get("PrSheetId4");
		String PrSheetId5=(String) datamap.get("PrSheetId5");
		String PrSheetId6=(String) datamap.get("PrSheetId6");
		String PrSheetId7=(String) datamap.get("PrSheetId7");
		String PrSheetId8=(String) datamap.get("PrSheetId8");
		String PrSheetId9=(String) datamap.get("PrSheetId9");
		String PrSheetId10=(String) datamap.get("PrSheetId10");
		
		try{
			if(!PrSheetId1.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId1,"Prdate1","itemId1",
				"itemName1","spec1","price1","haspaid1","nopay1","thispay1","memo1",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId2.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId2,"Prdate2","itemId2",
				"itemName2","spec2","price2","haspaid2","nopay2","thispay2","memo2",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId3.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId3,"Prdate3","itemId3",
				"itemName3","spec3","price3","haspaid3","nopay3","thispay3","memo3",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId4.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId4,"Prdate4","itemId4",
				"itemName4","spec4","price4","haspaid4","nopay4","thispay4","memo4",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId5.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId5,"Prdate5","itemId5",
				"itemName5","spec5","price5","haspaid5","nopay5","thispay5","memo5",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId6.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId6,"Prdate6","itemId6",
				"itemName6","spec6","price6","haspaid6","nopay6","thispay6","memo6",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId7.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId7,"Prdate7","itemId7",
				"itemName7","spec7","price7","haspaid7","nopay7","thispay7","memo7",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId8.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId8,"Prdate8","itemId8",
				"itemName8","spec8","price8","haspaid8","nopay8","thispay8","memo8",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId9.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId9,"Prdate9","itemId9",
				"itemName9","spec9","price9","haspaid9","nopay9","thispay9","memo9",createPerson,createTime,changePerson,changeTime);
			}
			if(!PrSheetId10.equals("")){
				PoData.PaySheetData(datamap,paySheetid,PrSheetId10,"Prdate10","itemId10",
				"itemName10","spec10","price10","haspaid10","nopay10","thispay10","memo10",createPerson,createTime,changePerson,changeTime);
			}
			//popay
			 if(!PrSheetId1.equals("")||!PrSheetId2.equals("")||!PrSheetId3.equals("")||!PrSheetId4.equals("")||!PrSheetId5.equals("")||
					 !PrSheetId6.equals("")||!PrSheetId7.equals("")||!PrSheetId8.equals("")||!PrSheetId9.equals("")||!PrSheetId10.equals("")){
					String Paysql="insert into popay (PAYDATE,PAYSHEETID,CUSTOMERID,CONNECTOR,CONNECTORTEL,PAYTYPE,PAYABLE," +
							"PREPAID,PAYMENT,PAYMETHOD,ACCOUNT,EXAMINEID,SALESMANID,DRAWERID,ISBILL,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME) " +
							"values(to_date('"+payDate+"','yyyy-MM-dd,hh24:mi:ss'),'"+paySheetid+"','"+customerId+"','"+connector+"','"+connectorTel+"','"+payType+"','"+payable+"','"+prepaid+"'," +
							"'"+payment+"','"+payMethod+"','"+account+"','"+examineId+"','"+salesmanId+"','"+drawerId+"','"+isBill+"','"+createPerson+"'," +
							"to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'))";
					String sqlsheet="insert into PAYSHEETID values("+seq+",'"+id+"','"+paySheetid+"')";
					Sqlhelper.executeUpdate(Paysql, null);
					Sqlhelper.executeUpdate(sqlsheet, null);
				  	}
				  
					String json = "{\"result\":\"操作成功!\",\"status\":1}";
					response.getWriter().append(json).flush();
			
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\",\"status\":0}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	}

}
