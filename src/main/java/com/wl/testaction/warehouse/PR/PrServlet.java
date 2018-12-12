package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;


import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.JsonConvert;
import com.wl.tools.PrData;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.Stockcl;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;


public class PrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PrServlet() {
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
		
		String gridjson =request.getParameter("gridjson");
		System.out.println(gridjson);
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String prDate=(String) datamap.get("prDate");
		String prSheetid=(String) datamap.get("prSheetid");
	    String customerId=(String) datamap.get("customerId");
	    String connector=(String) datamap.get("connector");
	    String connectorTel=(String) datamap.get("connectorTel");
	    String isBill=(String) datamap.get("isBill");
	    String payTerm=(String) datamap.get("payTerm");
	    String dutyParagraph=(String) datamap.get("dutyParagraph");
	    String bank=(String) datamap.get("bank");
	    String account=(String) datamap.get("account");
	    
	    String purId = (String) datamap.get("purchase");		//订货单id
	    String purState = (String) datamap.get("purState");		//订货单状态
	    
	    String examineId=(String) datamap.get("examineId");
	    String salesmanId=(String) datamap.get("salesmanId");
	    String drawerId=(String) datamap.get("drawerId");
	    
	    String seq = datamap.get("seq").toString();
//	    String seq=(String) datamap.get("seq");
	    String id=datamap.get("id").toString();
	    
	    HttpSession session=request.getSession();
	    String createPerson=((User)session.getAttribute("user")).getStaffCode();
	    String changePerson=((User)session.getAttribute("user")).getStaffCode();
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String createTime=df.format(new Date());
	    String changeTime=df.format(new Date());
	    
	    String tempa ="";
	    String receipt = (String) datamap.get("receipt");
	    
	    String result = "";
//	    如果业务员与开单人为空，则默认当前用户 
	    if(salesmanId.isEmpty()){
	    	salesmanId= createPerson;
	    }
	    if(drawerId.isEmpty()){
	    	drawerId= createPerson;
	    }
	    
		try{
			
			String sql="insert into pr (PRDATE,PRSHEETID,WAREHOUSEID,CUSTOMERID,CONNECTOR,CONNECTORTEL,PAYTERM,DUTYPARAGRAPH,BANK,ACCOUNT,EXAMINEID,ADMINID,SALESMANID,DRAWERID," +
					"RECEIPT,ISBILL,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,purId,purState) values(to_date('"+prDate+"','yyyy-MM-dd,hh24:mi:ss'),'"+prSheetid+"','"+tempa+"','"+customerId+"','"+connector+"'," +
					"'"+connectorTel+"',to_date('"+payTerm+"','yyyy-mm-dd,hh24:mi:ss'),'"+dutyParagraph+"','"+bank+"','"+account+"','"+examineId+"','"+tempa+"','"+salesmanId+"','"+drawerId+"','"+receipt+"'," +
					"'"+isBill+"','"+createPerson+"',to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+purId+"','"+purState+"')";
			String sqlsheet="insert into pr_sheetid values("+seq+",'"+id+"','"+prSheetid+"')";
			System.out.println("sql="+sql);
			System.out.println("sqlsheet="+sqlsheet);
			
			Sqlhelper.executeUpdate(sql, null);
			Sqlhelper.executeUpdate(sqlsheet, null);
			//result = "操作成功";
			
		}catch(Exception e){
			e.printStackTrace();
			result = "操作失败";
		}
	
		String sqlb = "update po_plan t set t.status ='"+purState+"' where t.po_sheetid='"+purId+"'";  //更新po_plan 表订货单状态
		try {
			System.out.println(sqlb);
			Sqlhelper.executeUpdate(sqlb, null);
			System.out.println("ok  "+sqlb);
			result = "操作成功";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JSONArray jarr = JSONArray.fromObject(gridjson);
		
		String itemId = "";
		String itemName = "";
		String spec = "";
		
		String unit = "";
		String itemType = "";
		String ussage = "";
		
		String  prNum = "0";
		
		String inNum = "0";
		String  unitPrice = "0";
		double  price = 0;
		String warehouseId = "";
		String itemTypeName="";
		String stockId = "";
		String payMethod="";
		
		double dinNum = 0;
		double dunitPrice =0;
		
		String itemStatus="3";	//审核结果，审核通过之后不给修改。
		for (int i = 0,j=jarr.size();i<j;i++){
			
			  itemId = "";
			  itemName = "";
			  spec = "";
			
			  unit = "";
			  itemType = "";
			  ussage = "";
			  prNum = "0";
			
			  inNum = "0";
			  unitPrice = "0";
			  price = 0;
			  warehouseId = "";
			  stockId = "";
			  itemTypeName="";
			  purId="";
			  payMethod="";
				  
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			System.out.println(map1);
			inNum = (String) (IsJsonNull.isJsonNull(map1.get("inNum").toString())?inNum:map1.get("inNum").toString());
//			if(inNum.equals("0")){
//				continue;
//			}
			itemStatus = (String) (IsJsonNull.isJsonNull(map1.get("status").toString())?itemStatus:map1.get("status").toString());
			if(itemStatus.equals("1")){
				continue;
			}
			itemId = (String) (IsJsonNull.isJsonNull(map1.get("itemId"))?itemId:map1.get("itemId"));
			itemName =(String) (IsJsonNull.isJsonNull(map1.get("itemName"))?itemName:map1.get("itemName"));
			spec = (String) (IsJsonNull.isJsonNull(map1.get("spec"))?spec:map1.get("spec"));
			
			unit = (String) (IsJsonNull.isJsonNull(map1.get("unit"))?unit:map1.get("unit"));
			ussage = (String) (IsJsonNull.isJsonNull(map1.get("ussage"))?ussage:map1.get("ussage"));
			prNum = (IsJsonNull.isJsonNull(map1.get("prNum"))?prNum:map1.get("prNum")).toString();
			
			unitPrice =  (IsJsonNull.isJsonNull(map1.get("unitPrice"))?unitPrice:map1.get("unitPrice")).toString();
//			price = (IsJsonNull.isJsonNull(map1.get("price"))?price:map1.get("price")).toString();
			dinNum = Double.parseDouble(inNum);
			dunitPrice = Double.parseDouble(unitPrice);
			price = dinNum*dunitPrice;
			
			stockId = (String) (IsJsonNull.isJsonNull(map1.get("stockId"))?stockId:map1.get("stockId"));
			payMethod =(IsJsonNull.isJsonNull(map1.get("payMethod"))?payMethod:map1.get("payMethod")).toString();
			
			itemType = (String) (IsJsonNull.isJsonNull(map1.get("itemType"))?itemType:map1.get("itemType"));
			itemTypeName =(String) (IsJsonNull.isJsonNull(map1.get("itemTypeName"))?itemTypeName:map1.get("itemTypeName"));
			warehouseId = (String) (IsJsonNull.isJsonNull(map1.get("warehouseId"))?warehouseId:map1.get("warehouseId"));
			
			purId = (String) (IsJsonNull.isJsonNull(map1.get("poSheetid"))?purId:map1.get("poSheetid"));
			
			String sqla = "declare " +
					"total number; " +
					"begin " +
					"select count(1) into total from PRDETAIL t where t.prsheetid='"+prSheetid+"' and t.itemid='"+itemId+"' and t.posheetid='"+purId+"';" +
					"if total = 0 then " +
					"insert into PRDETAIL (PRSHEETID,ITEMID,ITEMNAME,ITEMTYPE,SPEC,UNIT," +
						"USSAGE,PRNUM,UNITPRICE,PRICE,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,POSHEETID,innum,warehouseId,paymethod) values " +
						"('"+prSheetid+"','"+itemId+"','"+itemName+"','"+itemType+"','"+spec+"','"+unit+"','"+ussage+"',"+prNum+","+unitPrice+",'"+price+"'" +
						",'"+createPerson+"',to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+purId+"','"+inNum+"','"+warehouseId+"','"+payMethod+"');"+
					"else" +
					" update PRDETAIL t set t.PRSHEETID = '"+prSheetid+"', ITEMID='"+itemId+"', ITEMNAME='"+itemName+"', ITEMTYPE='"+itemType+"', SPEC='"+spec+"', " +
							"UNIT='"+unit+"', USSAGE='"+ussage+"', PRNUM='"+prNum+"', UNITPRICE='"+unitPrice+"' ,PRICE ='"+price+"'," +
									"CHANGEPERSON='"+changePerson+"',CHANGETIME=to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'),POSHEETID='"+purId+"',innum='"+inNum+"',warehouseId='"+warehouseId+"',paymethod='"+payMethod+"' "+
							"where t.prsheetid='"+prSheetid+"' and t.itemid='"+itemId+"' and t.posheetid='"+purId+"';  " +
					"end if;" +
					"end;";	
	
//			double unitprice=unitPrice.equals("")?0:Double.parseDouble(unitPrice);
//			double prnum=prNum.equals("")?0:Double.parseDouble(prNum);
			try {
				System.out.println(sqla);
				Sqlhelper0.executeUpdate(sqla, null);
			//	Stockcl.Stockin(itemId,itemName,spec,itemType,warehouseId,stockId,prnum,unitprice,unit);
				System.out.println("ok "+sqla);
				result = "操作成功";
				 
			} catch (Exception e) {
				// TODO: handle exception
//				result = "操作失败";
			}
		}
		
		String json = "{\"result\":\""+result+"\",\"status\":1}";
		response.getWriter().append(json).flush();
	}

}
