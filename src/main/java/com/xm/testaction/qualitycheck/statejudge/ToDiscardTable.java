package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.ToBarcode;
import com.xm.testaction.qualitycheck.cardhandle.RejStateRunnum;

public class ToDiscardTable extends HttpServlet {
// 质检生成废品单
	/**
	 * Constructor of the object.
	 */
	public ToDiscardTable() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
			doPost(request, response);
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
		String runNum= RejStateRunnum.disRunnum();
		
//		9-6  往 disdetail表中插入数据
		String staterunnum=request.getParameter("staterunnum");	//审理单单号
		String checkdate = request.getParameter("checkdate");
		checkdate =  ChineseCode.toUTF8(checkdate != null ? checkdate : "");
		try {
			String  dissql="insert into disdetail (runnum,staterunnum,checkdate) values ('"+runNum+"','"+staterunnum+"',to_date("+checkdate+",'yyyy-mm-dd hh24:mi:ss'))";
			Sqlhelper0.executeUpdate(dissql, null);
			System.out.println(dissql +"ok");
			
//			保存重新下单信息 
			String reordersql="insert into todiscard (barcode, runnum, state, rejectnum, drawingid, order_id, product_id, issue_num, partsplanid)" +
					"( select a.barcode,a.runnum,'0',f.rejectnum,b.drawingid,b.orderid,b.productid,b.issuenum,b.partsplanid " +
					"from disdetail e " +
					"left join attach_info a on a.runnum= e.staterunnum " +
					"left join Partsplan b on b.codeid = a.gbarcode " +
//					"left join reject_info d on d.barcode = a.fbarcode and d.fo_no ="+fo_no+" " +
					"left join reject_state f on f.runnum = e.staterunnum "+
							"where f.runnum='"+staterunnum+"')";
			try {
				System.out.println(reordersql);
				Sqlhelper0.executeUpdate(reordersql, null);
				System.out.println("ok "+ reordersql);
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
//				Sqlhelper0.close();
			}
			
//			如果是焊接件父件报废
			try {
				String sqla = "select t.product_id productId,t.order_id orderId,t.issue_num issueNum,c.partsplanid from order_detail t " +
						" left join (select a.order_id,a.product_id,a.issue_num,a.producttype from order_detail a) b on b.order_id= t.order_id and b.product_id= t.fproduct_id and b.issue_num= t.issue_num" +
						" left join partsplan c on c.orderid = t.order_id and c.productid= t.product_id and c.issuenum=t.issue_num " +
						"where t.fproduct_id in " +
						"(select d.drawingid from po_router d left join reject_state e on e.barcode=d.barcode " +
							"left join disdetail f on  f.staterunnum = e.runnum " +
							"where f.runnum='"+runNum+"') " +
						"and b.producttype='L' order by productId";	//查询该件是否为焊接件父件,获取相关信息
				List <BeanWeldDiscard> lista = new ArrayList<BeanWeldDiscard>();
				try {
					lista = Sqlhelper.exeQueryList(sqla, null, BeanWeldDiscard.class);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				response.setCharacterEncoding("utf-8");
				response.getWriter().append(runNum).flush();
				
//			9-7	生成报废单后，审理单即为已处理
				String opsql = "update reject_state set opinionerstate=1 where runnum='"+staterunnum+"'";
				try {
					Sqlhelper0.executeUpdate(opsql, null);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
				
				int len = lista.size();
				if(len==0){
					return;
				}else{
					String barcode = "";	//报废子件的条码号
					
					String productId ="";
					String orderId = "";
					String issueNum = "";
					String partsplanid= "";
					String state = "0";
					
					for(int j=0;j<len;j++){
						barcode = ToBarcode.toWeldBarcode();
						BeanWeldDiscard bean = null;
						bean = lista.get(j);
						productId = bean.getProductId();
						orderId = bean.getOrderId();
						issueNum = bean.getIssueNum();
						partsplanid = bean.getPartsplanid();
						
						String sqlb = "insert into todiscard (barcode, runnum, state, rejectnum, drawingid, order_id, product_id, issue_num, partsplanid) " +
								"values(?,?,?,  ?,?,?  ,?,?,?)";	//保存到报废表
						String param []={barcode,staterunnum,state,"0",productId,orderId,productId,issueNum,partsplanid};
						try {
							Sqlhelper.executeUpdate(sqlb, param);
							System.out.println("ok  "+sqlb);
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
