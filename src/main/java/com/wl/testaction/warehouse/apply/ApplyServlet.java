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

import com.wl.forms.Item;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StockinItem;

public class ApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ApplyServlet() {
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
		String applyDate=datamap.get("applyDate");
		String applySheetid=datamap.get("applySheetid");
		String deptId=datamap.get("deptId");
		String applicantId=datamap.get("applicantId");
		String orderId=datamap.get("order_id");
		String id=datamap.get("id");
		String seq=datamap.get("seq");
		String isPass=datamap.get("isPass");
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 String createTime = df.format(new Date());
		 String changeTime = df.format(new Date());
		    
		 HttpSession session = request.getSession();
		 String createPerson = ((User)session.getAttribute("user")).getStaffCode();
		 String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		 boolean b=false;
			try{
				for(int i=1,n=11;i<n;i++){
					String itemId=datamap.get("item_id"+i);
					if(!itemId.equals("")){
						String itemName=datamap.get("item_name"+i);
						String spec=datamap.get("spec"+i);
						String unit=datamap.get("unit"+i);
						String kind=datamap.get("kind"+i);
						String usage=datamap.get("usage"+i);
						String poNum=datamap.get("po_num"+i);
						String memo=datamap.get("memo"+i);
						String sql="insert into poapplydetl (APPLYSHEETID,ITEMID,ITEMNAME,SPEC,UNIT,ITEMTYPE,USAGE,PONUM,MEMO,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME) " +
						"values('"+applySheetid+"','"+itemId+"','"+itemName+"','"+spec+"','"+unit+"','"+kind+"','"+usage+"','"+poNum+"'," +
							"'"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
						Sqlhelper.executeUpdate(sql, null);
						b=true;
					}
				}
		
				if(b){
					String applySql="insert into apply (APPLYSHEETID,APPLYDATE,APPLICANTID,DEPTID,ISPASS,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,ORDERID) " +
					"values('"+applySheetid+"',to_date('"+applyDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+applicantId+"','"+deptId+"','"+isPass+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
							"'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+orderId+"')";
					String sheetSql="insert into applysheetid values("+seq+",'"+id+"','"+applySheetid+"')";
					Sqlhelper.executeUpdate(applySql, null);
					Sqlhelper.executeUpdate(sheetSql, null);
				}
				String json="{\"result\":\"操作成功！\",\"status\":1}";
				out.append(json).flush();
			}catch(Exception e){
				String json="{\"result\":\"操作失败！\",\"status\":0}";
				out.append(json).flush();
				e.printStackTrace();
			}
		}
		

}
