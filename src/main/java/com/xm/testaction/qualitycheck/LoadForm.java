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
import javax.servlet.http.HttpSession;

import org.hibernate.engine.ExecuteUpdateResultCheckStyle;

import Test.JSON;

import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper0;

public class LoadForm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadForm() {
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
		
//		这是流程卡默认的当前工序质检
		String json="";
		String barcode = request.getParameter("barcode");
		String cbarcode = barcode;  	// 记录当前的条码号，传到前台
//		barcode 长度判断
		boolean cresult=BarcodeLength.barcodeLength(barcode);
		int state = StateJudge.searchState(barcode);
		String editsql= "";		//编辑窗表头信息
//先从流程卡中找，若没有则代表是第一道工序,这里也要判断是第几道工序	
//		待矫正，如果 是中间流程的质检
		ResultSet editRst= null;
		ArrayList<PoFlowBean> editlist = new ArrayList<PoFlowBean>();
		
		if(state == 1){		//如果是返工件
			editsql = "select t.fo_no from attach_info t where barcode ='"+barcode+"'";
			System.out.println(editsql);
//			copy 起始 
			String routingsql="select t.fo_opname,t.fnum,t.workername,t.po_index,t.fo_no,t.accept_num,t.remark from po_routing2 t where barcode='"+barcode+"' order by fo_no desc";
			int fo_noRefer = 0;		//routing表中的 工序
			String accept_num = null;
			String remark = "";
			ResultSet routingRst = null;
			try{
				
				routingRst = Sqlhelper0.executeQuery(routingsql, null);
				routingRst.next();
				
				fo_noRefer = routingRst.getInt(5);
				accept_num = routingRst.getString(6);
//				remark = routingRst.getString(7);
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				//			Sqlhelper0.close();
				if(routingRst != null){
			    	try {
			    		routingRst.close();
				} catch (Exception e2) {
				// TODO: handle exception
					}
			}
			}
			
			try{
				editRst=Sqlhelper0.executeQuery(editsql, null);
					editRst.next();
					int refer =1024;	//如果refer 为1024 ，则 代表是第一道工序
					int refers = Integer.parseInt(editRst.getString(1));
//					9-1
					try {
						//			Sqlhelper0.close();
						if(editRst != null){
					    	try {
					    		editRst.close();
						} catch (Exception e2) {
						// TODO: handle exception
							}
					}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
//					如果routing表中的工序是返工工序，且还未质检，则按正常流程比较
					if(fo_noRefer == refers && accept_num ==null){
						remark ="返工";
						
//						9-10  查找前道工序,如果是第一道工序？？
						String rsql ="select c.fo_no from attach_info a " +
								"left join partsplan b on b.codeid = a.gbarcode " +
								"left join fo_detail c on c.product_id =b.productid and c.issue_num = b.issuenum and c.isinuse='1' " +
								"where a.barcode = '"+barcode+"' order by to_number(c.fo_no) desc ";
						ResultSet rrs =null;
						try {
							
							rrs =Sqlhelper0.executeQuery(rsql, null);
							while(rrs.next()){
								if(rrs.getInt(1)==fo_noRefer){
									break;
								} 
							}
							rrs.next();
							refer = rrs.getInt(1);
							
						} catch (Exception e) {
							// TODO: handle exception
						}finally{
							//			Sqlhelper0.close();
							if(rrs != null){
						    	try {
								rrs.close();
							} catch (Exception e2) {
							// TODO: handle exception
								}
						}
						}
					}else{
						refer = fo_noRefer;
						
					}

					
					
					barcode = SearchFbarcode.searchFbarcode(barcode);	//找gbarcode的工序信息
//					待矫正
//					String posql="select f.fo_opname,f.num,f.staff_name,f.a_index,f.fo_no from (select b.fo_opname,b.num,c.staff_name,b.a_index,b.fo_no from " +
//					"(select d.drawingid from order_detail d where barcode = '"+barcode+"') a" +
//					" left join assign b on b.product_id= a.drawingid " +
//					"left join employee_info c on c.staff_code = b.workId)f " +
//					"order by f.a_index asc";
					
					String posql="select e.fo_opname,c.num,c.workid,rownum rn,c.a_index,e.fo_no,a.partnum " +
						"from partsplan a " +
//						"left join processesplan b on b.planid = a.partsplanid " +
						"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum and e.isinuse='1' " +
						"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
						"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no " +
//						"left join fo_detail e on e.product_id =b.productid and e.issue_num = b.issuenum and e.fo_no = b.operid " +
					"where a.codeid = '"+barcode+"' order by to_number(e.fo_no) asc";
					System.out.println(posql);
					PoFlowBean editBean= new PoFlowBean();
					try {
						editRst=Sqlhelper0.executeQuery(posql, null);//这里是查找所有工序
							while(refer != 1024 && editRst.next()){
									if (editRst.getInt(6)==refer){
										break;
									}
							}
						editRst.next();
						
						editBean.setFo_opname(editRst.getString(1));
						editBean.setNum(editRst.getString(2));
						editBean.setWorkername(editRst.getString(3));
						editBean.setFo_no(editRst.getString(6));
						editBean.setBarcode(cbarcode);
						editBean.setRemark(remark);
						editBean.setInput_num(editRst.getString(7));
						
						editlist.add(editBean);
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
						//			Sqlhelper0.close();
						if(editRst != null){
					    	try {
					    		editRst.close();
						} catch (Exception e2) {
						// TODO: handle exception
							}
					}
				}
					json=JSON.Encode(JsonConvert.beanToMap(editBean));
//					json = PluSoft.Utils.JSON.Encode(editlist);
					System.out.println(json);
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append(json).flush();
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				//			Sqlhelper0.close();
				if(editRst != null){
			    	try {
			    		editRst.close();
				} catch (Exception e2) {
				// TODO: handle exception
					}
			}
			}
		}
//	以上是对返工件的处理	
		
		else{
//				copy 结束 
					
				editsql="select t.fo_opname,t.fnum,t.workername,t.po_index,t.fo_no from po_routing2 t where barcode='"+barcode+"' order by fo_no desc";
				System.out.println("editsql="+editsql);
				String remark = "";
			try{
				
	//			若没有结果，则代表是第一道工序
				if(cresult){	//如果是子表
					barcode = SearchFbarcode.searchFbarcode(barcode);
				}else{
//					待矫正
				}
				editRst=Sqlhelper0.executeQuery(editsql, null);
//			如果 router表里没有数据 
				if(!editRst.next()){
					String posql= "";
	
//					posql="select f.fo_opname,f.num,f.staff_name,f.a_index,f.fo_no from (select b.fo_opname,b.num,c.staff_name,b.a_index,b.fo_no from " +
//					"(select d.drawingid from order_detail d where barcode = '"+barcode+"') a" +
//					" left join assign b on b.product_id= a.drawingid " +
//					"left join employee_info c on c.staff_code = b.workId)f " +
//					"order by f.a_index asc";
					
					posql="select e.fo_opname,c.num,c.workid,rownum rn,c.a_index,e.fo_no,a.partnum " +
					"from partsplan a " +
//					"left join processesplan b on b.planid = a.partsplanid " +
					"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum e.isinuse='1' " +
					"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
					"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no " +
//					"left join fo_detail e on e.product_id =b.productid and e.issue_num = b.issuenum and e.fo_no = b.operid " +
				"where a.codeid = '"+barcode+"' order by to_number(e.fo_no)";
					
					System.out.println(posql);
					
//					9-1
					//			Sqlhelper0.close();
					if(editRst != null){
				    	try {
				    		editRst.close();
					} catch (Exception e2) {
					// TODO: handle exception
						}
				}
					
					try {
						editRst=Sqlhelper0.executeQuery(posql, null);
						
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
//						//			Sqlhelper0.close();
					}
				}else{
	//				这里应修改为工序号
					String refer ="";
					System.out.println(editRst.getString(5));
					refer= editRst.getString(5);
//					String posql="select f.fo_opname,f.num,f.staff_name,f.a_index,f.fo_no from (select b.fo_opname,b.num,c.staff_name,b.a_index,b.fo_no from " +
//					"(select d.drawingid from order_detail d where barcode = '"+barcode+"') a" +
//					" left join assign b on b.product_id= a.drawingid " +
//					"left join employee_info c on c.staff_code = b.workId)f " +
//					"order by f.a_index asc";
					
					String posql="select e.fo_opname,c.num,c.workid,rownum rn,c.a_index,e.fo_no,a.partnum " +
					"from partsplan a " +
//					"left join processesplan b on b.planid = a.partsplanid " +
					"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum and e.isinuse='1' " +
					"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
					"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no " +
//					"left join fo_detail e on e.product_id =b.productid and e.issue_num = b.issuenum and e.fo_no = b.operid " +
				"where a.codeid = '"+barcode+"' order by to_number(e.fo_no)";
					System.out.println(posql);
					try {
						editRst=Sqlhelper0.executeQuery(posql, null);//这里是查找所有工序
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println();
					while(editRst.next()){
	//					int index=1;
	//					index++;
						if (editRst.getString(6).equals(refer)){
							break;
						}
					}
			}
				editRst.next();
				PoFlowBean editBean= new PoFlowBean();
				
				editBean.setFo_opname(editRst.getString(1));
				editBean.setNum(editRst.getString(2));
				editBean.setWorkername(editRst.getString(3));
				editBean.setFo_no(editRst.getString(6));
				editBean.setBarcode(cbarcode);
				editBean.setRemark(remark);
				editBean.setInput_num(editRst.getString(7));
				
				editlist.add(editBean);
				
				json=JSON.Encode(JsonConvert.beanToMap(editBean));
	//			json = PluSoft.Utils.JSON.Encode(editlist);
				System.out.println(json);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
				
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			//			Sqlhelper0.close();
			if(editRst != null){
		    	try {
		    		editRst.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
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
