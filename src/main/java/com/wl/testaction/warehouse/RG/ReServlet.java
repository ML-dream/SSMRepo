package com.wl.testaction.warehouse.RG;

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
import com.wl.tools.PrData;
import com.wl.tools.Sqlhelper;

public class ReServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReServlet() {
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
		String reDate=(String) datamap.get("reDate");
		String reSheetid=(String) datamap.get("reSheetid");
		String warehouseId=(String) datamap.get("warehouseId");
	    String customerId=(String) datamap.get("customerId");
	    String connector=(String) datamap.get("connector");
	    String connectorTel=(String) datamap.get("connectorTel");
	    String telephone=(String) datamap.get("telephone");
	    String reType=(String) datamap.get("reType");
	    String account=(String) datamap.get("account");
	    String payMethod=(String) datamap.get("payMethod");
	    String receipt=(String) datamap.get("receipt");
	    String examineId=(String) datamap.get("examineId");
	    String adminId=(String) datamap.get("adminId");
	    String salesmanId=(String) datamap.get("salesmanId");
	    String drawerId=(String) datamap.get("drawerId");
	    String seq=(String) datamap.get("seq");
	    String id=(String) datamap.get("id");
	    
	    HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());
		String changeTime = df.format(new Date());
	    
	    String itemId1=(String) datamap.get("itemId1");
		String itemId2=(String) datamap.get("itemId2");
		String itemId3=(String) datamap.get("itemId3");
		String itemId4=(String) datamap.get("itemId4");
		String itemId5=(String) datamap.get("itemId5");
		String itemId6=(String) datamap.get("itemId6");
		String itemId7=(String) datamap.get("itemId7");
		String itemId8=(String) datamap.get("itemId8");
		String itemId9=(String) datamap.get("itemId9");
		String itemId10=(String) datamap.get("itemId10");
		
		try{
			if(!itemId1.equals("")){
				
				PrData.getReData(datamap,reSheetid,itemId1,
						"itemName1","kind1","spec1","unit1","reNum1","unitPrice1",
						"price1","stockId1","memo1",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId2.equals("")){
				PrData.getReData(datamap,reSheetid,itemId2,
						"itemName2","kind2","spec2","unit2","reNum2","unitPrice2",
						"price2","stockId2","memo2",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId3.equals("")){
				PrData.getReData(datamap,reSheetid,itemId3,
						"itemName3","kind3","spec3","unit3","reNum3","unitPrice3",
						"price3","stockId3","memo3",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId4.equals("")){
				PrData.getReData(datamap,reSheetid,itemId4,
						"itemName4","kind4","spec4","unit4","reNum4","unitPrice4",
						"price4","stockId4","memo4",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId5.equals("")){
				PrData.getReData(datamap,reSheetid,itemId5,
						"itemName5","kind5","spec5","unit5","reNum5","unitPrice5",
						"price5","stockId5","memo5",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId6.equals("")){
				PrData.getReData(datamap,reSheetid,itemId6,
						"itemName6","kind6","spec6","unit6","reNum6","unitPrice6",
						"price6","stockId6","memo6",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId7.equals("")){
				PrData.getReData(datamap,reSheetid,itemId7,
						"itemName7","kind7","spec7","unit7","reNum7","unitPrice7",
						"price7","stockId7","memo7",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId8.equals("")){
				PrData.getReData(datamap,reSheetid,itemId8,
						"itemName8","kind8","spec8","unit8","reNum8","unitPrice8",
						"price8","stockId8","memo8",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId9.equals("")){
				PrData.getReData(datamap,reSheetid,itemId9,
						"itemName9","kind9","spec9","unit9","reNum9","unitPrice9",
						"price9","stockId9","memo9",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			if(!itemId10.equals("")){
				PrData.getReData(datamap,reSheetid,itemId10,
						"itemName10","kind10","spec10","unit10","reNum10","unitPrice10",
						"price10","stockId10","memo10",warehouseId,createPerson,createTime,changePerson,changeTime);
				
			}
			
			if(itemId1!=""||itemId2!=""||itemId3!=""||itemId4!=""||itemId5!=""||itemId6!=""||itemId7!=""
				||itemId8!=""||itemId9!=""||itemId10!="")
			{
				String sql="insert into returngood (REDATE,RESHEETID,WAREHOUSEID,CUSTOMERID,CONNECTOR,CONNECTORTEL,TELEPHONE,RETYPE," +
						"ACCOUNT,PAYMETHOD,RECEIPT,EXAMINEID,ADMINID,SALESMANID,DRAWERID) values(to_date('"+reDate+"','yyyy-mm-dd,hh24:mi:ss')," +
						"'"+reSheetid+"','"+warehouseId+"','"+customerId+"','"+connector+"','"+connectorTel+"','"+telephone+"','"+reType+"'," +
						"'"+account+"','"+payMethod+"','"+receipt+"','"+examineId+"','"+adminId+"','"+salesmanId+"','"+drawerId+"')";
				String sqlsheet="insert into resheetid values("+seq+",'"+id+"','"+reSheetid+"')";
				System.out.println("sql="+sql);
				System.out.println("sqlsheet="+sqlsheet);
				
				Sqlhelper.executeUpdate(sql, null);
				Sqlhelper.executeUpdate(sqlsheet, null);
						
				}
			String json = "{\"result\":\"操作成功!\",\"status\":1}";
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
				}catch(Exception e){
					String json = "{\"result\":\"操作失败!\",\"status\":0}";
					//response.setCharacterEncoding("UTF-8");
					response.getWriter().append(json).flush();
					e.printStackTrace();
		}
		
	}

}
