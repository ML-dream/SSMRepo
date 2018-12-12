package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Test.JSON;

import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper0;

public class LoadCardHeader extends HttpServlet {
//加载流程卡表头
	/**
	 * Constructor of the object.
	 */
	public LoadCardHeader() {
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
		
		String headersql="";
		String json = "";
		String barcode = "";
		String headersql0 ="";
		barcode = request.getParameter("barcode");
		boolean cresult = BarcodeLength.barcodeLength(barcode);		//是否子卡
		String grade = "正常";
		int state = 0 ;		//  = router表中的  garde
		
		headersql0="select t.order_id,t.drawingid,t.product_name,t.companyname,t.input_num,t.grade" +
				" from po_router t where barcode='"+barcode+"'";
		ResultSet headerRst=null;
		PoFlowBean headerBean= new PoFlowBean();
		
		try{
			headerRst=Sqlhelper0.executeQuery(headersql0, null);
//			headerRst.next();
//			待处理  
			if(!headerRst.next()){
				headersql = "select a.orderid,a.productid,b.product_name,d.companyname,a.partnum,a.issuenum from " +	//a.drawingid
					"partsplan a " +
					"left join order_detail b on b.product_id = a.productid and b.order_id = a.orderid and b.issue_num= a.issuenum "+
					"left join orders c on c.order_id = b.order_id " +
					"left join customer d on d.companyid = c.customer " +
					"where a.codeid = '"+barcode+"'";
				
//				没取 input_num 待矫正  header表没有数据，则从其它表找
				if (cresult){
					String fbarcode = SearchFbarcode.searchFbarcode(barcode);
					state = StateJudge.searchState(barcode);
					grade = StateJudge.stateJudge(state);	//中文描述
					
					switch (state) {
					case 2:		//多批次
						headersql = "select a.orderid,a.productid,b.product_name,d.companyname,a.partnum,a.issuenum from " +
							"partsplan a " +
							"left join order_detail b on b.product_id = a.productid and b.order_id = a.orderid and b.issue_num= a.issuenum "+
							"left join orders c on c.order_id = b.order_id " +
							"left join customer d on d.companyid = c.customer " +
							"where a.codeid = '"+fbarcode+"'";
						break;
					case 1:		//返工
						headersql = "select a.orderid,a.productid,b.product_name,d.companyname,f.rejectnum,a.issuenum from " +
							"partsplan a " +
							"left join order_detail b on b.product_id = a.productid and b.order_id = a.orderid and b.issue_num= a.issuenum "+
							"left join orders c on c.order_id = b.order_id " +
							"left join customer d on d.companyid = c.customer " +
							"left join attach_info e on e.gbarcode = a.codeid "+
//					         " left join reject_info f on f.barcode = e.fbarcode "+
							" left join reject_state f on f.runnum = e.runnum "+
							"where e.barcode = '"+barcode+"'";
						break;
					case 3:		//报废
						headersql = "select a.orderid,a.productid,b.product_name,d.companyname,a.partnum,a.issuenum from " +  //a.drawingid,
							"partsplan a " +
							"left join order_detail b on b.product_id = a.productid and b.order_id = a.orderid and b.issue_num= a.issuenum "+
							"left join orders c on c.order_id = b.order_id " +
							"left join customer d on d.companyid = c.customer " +
							"where a.codeid = '"+barcode+"'";
						break;

					default:
						break;
					}
					
				}else{
					
//					headersql = "select a.orderid,a.drawingid,b.product_name,d.companyname,a.partnum,a.issuenum from " +
//						"partsplan a " +
//						"left join order_detail b on b.drawingid = a.drawingid " +
//						"left join orders c on c.order_id = b.order_id " +
//						"left join customer d on d.companyid = c.customer " +
//						"where a.codeid = '"+barcode+"'";
				}
				System.out.println("headersql="+headersql);
				//9-1
//				//			Sqlhelper0.close();
//				if(headerRst != null){
//			    	try {
//			    		headerRst.close();
//				} catch (Exception e2) {
//				// TODO: handle exception
//					}
//			}
				String	insertsql ="";
				try{
					headerRst=Sqlhelper0.executeQuery(headersql, null);
					headerRst.next();
//					没存inpu_num ,这个地方限制了质检处不能修改表头数据   待矫正
					String order_id=headerRst.getString(1);
					String drawingid = headerRst.getString(2);
					String product_name = headerRst.getString(3);
					String companyname = headerRst.getString(4);
					String input_num = headerRst.getString(5);
					String issuenum  = headerRst.getString(6);
					insertsql ="insert into po_router (barcode,order_id,companyname,drawingid,product_name,grade,input_num,issuenum) " +
							"values ('"+barcode+"','"+order_id+"','"+companyname+"','"+drawingid+"','"+product_name
							+"','"+state+"','"+input_num+"','"+issuenum+"')";
					System.out.println(insertsql);
					
					headerBean.setOrder_id(order_id);
					headerBean.setDrawingId(drawingid);
					headerBean.setProduct_name(product_name);
					headerBean.setCompanyname(companyname);
					headerBean.setGrade(grade);
					headerBean.setStatus(state);
					headerBean.setInput_num(input_num);
					
//					9-1 
//					//			Sqlhelper0.close();
//					if(headerRst != null){
//				    	try {
//				    		headerRst.close();
//					} catch (Exception e2) {
//					// TODO: handle exception
//						}
//				}
					
				}catch (Exception e) {
					// TODO: handle exception
				}finally{
					//			Sqlhelper0.close();
					if(headerRst != null){
				    	try {
				    		headerRst.close();
					} catch (Exception e2) {
					// TODO: handle exception
						}
					}
				}
				try {
					Sqlhelper0.executeUpdate(insertsql, null);
					System.out.println("succ"+insertsql);
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					//			Sqlhelper0.close();
				}
		}	//以上，若router表里没有数据，则执行多表查询    1 if
			else{
				headerBean.setOrder_id(headerRst.getString(1));
				headerBean.setDrawingId(headerRst.getString(2));
				headerBean.setProduct_name(headerRst.getString(3));
				headerBean.setCompanyname(headerRst.getString(4));
				headerBean.setInput_num(headerRst.getString(5));
				headerBean.setStatus(headerRst.getInt(6));
				
				grade = StateJudge.stateJudge(headerRst.getInt(6));
				
				headerBean.setGrade(grade);
//				9-16
//				//			Sqlhelper0.close();
//				if(headerRst != null){
//			    	try {
//			    		headerRst.close();
//				} catch (Exception e2) {
//				// TODO: handle exception
//					}
//			}
		}
			
			json=JSON.Encode(JsonConvert.beanToMap(headerBean));
			System.out.println(json);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			
		}catch (Exception e) {
			// TODO: handle exception 待矫正
		}finally{
			//			Sqlhelper0.close();
			if(headerRst != null){
		    	try {
		    		headerRst.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
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
