package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.ItemCode;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StockinItem;
import com.wl.tools.StringUtil;

public class PoApplyDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PoApplyDetailServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		String warehouseId=request.getParameter("warehouseId");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String itemId=StringUtil.isNullOrEmpty((String) datamap.get("itemId"))?"":(String) datamap.get("itemId");
		String itemName=(String) datamap.get("itemName");
		String spec=(String) datamap.get("spec");
		String itemType=(String) datamap.get("itemType");
		String unit=(String) datamap.get("unit");
		String poNum=(String) datamap.get("poNum");
		String usage=(String) datamap.get("usage");
		String memo=(String) datamap.get("memo");
		String addApply=(String) datamap.get("addApply");
		String applySheetid=request.getParameter("applySheetid");
		
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 String createTime = df.format(new Date());
		 String changeTime = df.format(new Date());
		    
		 HttpSession session = request.getSession();
		 String createPerson = ((User)session.getAttribute("user")).getStaffCode();
		 String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		 
		 int count=0;
		 if(addApply.equals("0")&&itemId.equals("")){
			 String itemCodeSql="select max(seq) seq from itemcode where itemtype='"+itemType+"'";
			 ItemCode itemcode=new ItemCode();
			 try{
				 itemcode=Sqlhelper.exeQueryBean(itemCodeSql, null, ItemCode.class);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 count=StringUtil.isNullOrEmpty(itemcode.getSeq())?0:itemcode.getSeq(); 
//			 xiem	 如果count 为0 ，为了保险，查询该类型的数据总数
			 if(count ==0){
				 System.out.println("itemCodeSql  "+itemCodeSql);
				 String sumSql = "select count(1) from " +
				 		"(select t.seq,rownum rn from itemcode t where itemtype='"+itemType+"' ) a " +
				 		"where a.rn =1";
				 int temp = 0;
				 try {
					temp = Sqlhelper.exeQueryCountNum(sumSql, null);
					System.out.println("sql "+sumSql);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
//			如果结果不为0，则提示报错。
				if(temp!=0){
					String json="{\"result\":\"操作失败！\",\"status\":0}";
					out.append(json).flush();
					return;
				}
			
			 }
			 count++;
			 String stringcount=Integer.toString(count);
			 String result="";
			 for(int i=0,n=6-stringcount.length();i<n;i++){
				
					result += "0";
			 }
			 itemId=itemType+result+stringcount;
			 //存
			 String itemSql="insert into itemcode(seq,itemid,itemname,itemtype) values('"+count+"','"+itemId+"','"+itemName+"','"+itemType+"')";
			 try{
				 Sqlhelper.executeUpdate(itemSql, null);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 Stockcl.Stockin(itemId,itemName,spec,itemType,warehouseId,"",0,0,unit);
		 }
		 
		
		String applySql="insert into POAPPLYDETL (applysheetid,itemid,itemname,spec,unit,itemtype," +
				"usage,ponum,memo,createperson,createtime,changeperson,changetime) values('"+applySheetid+"'," +
					"'"+itemId+"','"+itemName+"','"+spec+"','"+unit+"','"+itemType+"','"+usage+"'," +
					"'"+poNum+"','"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
					"'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
		
		
		try{
			Sqlhelper.executeUpdate(applySql, null);
			
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			out.append(json).flush();
			e.printStackTrace();
		}
		
		
		
	}

}
