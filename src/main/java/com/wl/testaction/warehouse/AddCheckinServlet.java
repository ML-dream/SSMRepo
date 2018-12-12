package com.wl.testaction.warehouse;

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
import com.wl.tools.CheckData;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;

public class AddCheckinServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
 		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String data=request.getParameter("submitData");
		HashMap datalist = (HashMap)Test.JSON.Decode(data);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		String createTime = df.format(new Date());
		String id=(String) datalist.get("id");
		String seq=(String) datalist.get("seq");
		String checksheet_id=(String) datalist.get("checksheet_id");
		//String checkin_id=(String) datalist.get("checkin_id");
		String warehouse_id=(String) datalist.get("warehouse_id");
		String warehouse_name=(String) datalist.get("warehouse_name");
		String company_id=(String) datalist.get("company_id");
		String company_name=(String) datalist.get("company_name");
		String deptid=(String) datalist.get("deptid");
		String deptname=(String) datalist.get("deptname");
		String checkin_kind=(String) datalist.get("checkin_kind");
		String checkin_date=(String) datalist.get("checkin_date");
		String examine=(String) datalist.get("examine");
		String examine_name=(String) datalist.get("examine_name");
		String admin=(String) datalist.get("admin");
		String admin_name=(String) datalist.get("admin_name");
		String delivery=(String) datalist.get("delivery");
		String delivery_name=(String) datalist.get("delivery_name");
	    
		String item_id1=(String) datalist.get("item_id1");
		String item_id2=(String) datalist.get("item_id2");
		String item_id3=(String) datalist.get("item_id3");
		String item_id4=(String) datalist.get("item_id4");
		String item_id5=(String) datalist.get("item_id5");
		String item_id6=(String) datalist.get("item_id6");
		String item_id7=(String) datalist.get("item_id7");
		String item_id8=(String) datalist.get("item_id8");
		String item_id9=(String) datalist.get("item_id9");
		String item_id10=(String) datalist.get("item_id10");
		
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 String createTime = df.format(new Date());
		 String changeTime = df.format(new Date());
		    
		 HttpSession session = request.getSession();
		 String createPerson = ((User)session.getAttribute("user")).getStaffCode();
		 String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		try{
		if(item_id1.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id1",item_id1,
					"item_name1","item_type1","spec1","unit1","checkin_num1","unitprice1",
					"price1",warehouse_id,"stock_id1","order_id1","lot1","quality_id1","memo1",
					"issue_num1",createPerson,createTime,changePerson,changeTime);
			
		}
		if(item_id2.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id2",item_id2,
					"item_name2","item_type2","spec2","unit2","checkin_num2","unitprice2",
					"price2",warehouse_id,"stock_id2","order_id2","lot2","quality_id2","memo2",
					"issue_num2",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id3.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id3",item_id3,
					"item_name3","item_type3","spec3","unit3","checkin_num3","unitprice3",
					"price3",warehouse_id,"stock_id3","order_id3","lot3","quality_id3","memo3",
					"issue_num3",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id4.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id4",item_id4,
					"item_name4","item_type4","spec4","unit4","checkin_num4","unitprice4",
					"price4",warehouse_id,"stock_id4","order_id4","lot4","quality_id4","memo4",
					"issue_num4",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id5.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id5",item_id5,
					"item_name5","item_type5","spec5","unit5","checkin_num5","unitprice5",
					"price5",warehouse_id,"stock_id5","order_id5","lot5","quality_id5","memo5",
					"issue_num5",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id6.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id6",item_id6,
					"item_name6","item_type6","spec6","unit6","checkin_num6","unitprice6",
					"price6",warehouse_id,"stock_id6","order_id6","lot6","quality_id6","memo6",
					"issue_num6",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id7.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id7",item_id7,
					"item_name7","item_type7","spec7","unit7","checkin_num7","unitprice7",
					"price7",warehouse_id,"stock_id7","order_id7","lot7","quality_id7","memo7",
					"issue_num7",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id8.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id8",item_id8,
					"item_name8","item_type8","spec8","unit8","checkin_num8","unitprice8",
					"price8",warehouse_id,"stock_id8","order_id8","lot8","quality_id8","memo8",
					"issue_num8",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id9.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id9",item_id9,
					"item_name9","item_type9","spec9","unit9","checkin_num9","unitprice9",
					"price9",warehouse_id,"stock_id9","order_id9","lot9","quality_id9","memo9",
					"issue_num9",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id10.length()>0){
			CheckData.Checkin(datalist,checksheet_id,"checkindetl_id10",item_id10,
					"item_name10","item_type10","spec10","unit10","checkin_num10","unitprice10",
					"price10",warehouse_id,"stock_id10","order_id10","lot10","quality_id10","memo10",
					"issue_num10",createPerson,createTime,changePerson,changeTime);
		}
		if(item_id1!=""||item_id2!=""||item_id3!=""||item_id4!=""||item_id5!=""||item_id6!=""
			||item_id7!=""||item_id8!=""||item_id9!=""||item_id10!=""){
			String sql="insert into mycheckin (checkin_date,checksheet_id,company_id,checkin_kind,examine,admin,delivery,warehouse_id," +
					"deptid,createperson,createtime,changeperson,changetime) values(to_date('"+checkin_date+"','yyyy-mm-dd,hh24:mi:ss'),'"+checksheet_id+"','"+company_id+"','"+checkin_kind+"'," +
					"'"+examine+"','"+admin+"','"+delivery+"','"+warehouse_id+"','"+deptid+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
//			String s1="CJ-001-"+createTime+"-";
//			String s2=checksheet_id.replaceAll(s1,"");
			String sqlsheet="insert into checkin_sheetid values("+seq+",'"+id+"','"+checksheet_id+"')";
			System.out.println("sql="+sql);
			System.out.println("sqlsheet="+sqlsheet);
			
					Sqlhelper.executeUpdate(sql, null);
					Sqlhelper.executeUpdate(sqlsheet, null);
					
		}
					String json = "{\"result\":\"操作成功!\"}";
					//response.setCharacterEncoding("UTF-8");
					response.getWriter().append(json).flush();
			}catch(Exception e){
				String json = "{\"result\":\"操作失败!\"}";
				//response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
				e.printStackTrace();
			}
		
		
	}


}
