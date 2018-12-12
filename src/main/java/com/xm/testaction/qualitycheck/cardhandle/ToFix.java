package com.xm.testaction.qualitycheck.cardhandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.BarcodeLength;
import com.xm.testaction.qualitycheck.LoadCardHeader;
import com.xm.testaction.qualitycheck.SearchFbarcode;

public class ToFix extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ToFix() {
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
		int fo_no = Integer.parseInt(request.getParameter("fo_no"));
//		根barcode
		String fbarcode = request.getParameter("fbarcode");
		String gbarcode = fbarcode;
		String runnum = request.getParameter("runnum");
//		9-6 
		int ishandle = Integer.parseInt(request.getParameter("ishandle"));
		
		boolean length = BarcodeLength.barcodeLength(fbarcode);
		if(length){
//			如果是子表，则找到 根 barcode
			gbarcode = SearchFbarcode.searchFbarcode(fbarcode);
		}
//			新生成的流程卡号
			String barcode = "";
			
			int status = 1;
//			查询流程卡附表中，多批次状态的序列最大值
			String attachsql="select max(attach_no) from attach_info " +
					"where gbarcode='"+gbarcode+"'";
//			System.out.println("attachsql="+ attachsql);  +"' and status="+status
			int attach_no=1;	//流程卡附表序列，默认从1开始
//			保存附表信息
			
			ResultSet attachRst=null;
			try{	
				attachRst=Sqlhelper0.executeQuery(attachsql, null);
				attachRst.next();
				if(attachRst.getString(1) != null){
					attach_no=attachRst.getInt(1)+1;
				}
				try {
//					Sqlhelper0.close();
					if(attachRst != null){
						try {
							attachRst.close();
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
//				将父流程卡号+状态+序列  串起来
				barcode = gbarcode + attach_no;
				
				try {
					String saveattachsql= "insert into attach_info (barcode,attach_no,status,fbarcode,gbarcode,fo_no,runnum)" +
						" values ('"+barcode+"',"+attach_no+","+status+",'"+fbarcode+"','"+gbarcode+"',"+fo_no+",'"+runnum+"')";
//					9-6 待矫正
					if(ishandle==1){
						saveattachsql ="update attach_info set barcode='"+barcode+"',status=1 where runnum='"+runnum+"'";
					}
					System.out.println("savesql="+ saveattachsql);
					try {
						Sqlhelper0.executeUpdate(saveattachsql,null);
						System.out.println("ok" + saveattachsql);
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
//						Sqlhelper0.close();
					}
	    			

	    			String result ="success";
	    			
//	    			做返工子卡的编辑权限
	    			String newEditsql = "insert into edit_control (barcode,fo_no) values ('"+barcode+"',"+fo_no+")";
	    			try {
	    				Sqlhelper0.executeUpdate(newEditsql,null);
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
//						Sqlhelper0.close();
					}
	    			
	    			
////	    		待矫正	把返工工序的备注栏设置为 “返工”
	    			String remarksql = "insert into po_routing2 (barcode,fo_no,remark) values ('"+barcode+"',"+fo_no+",'返工' )";
	    			System.out.println(remarksql);
	    			try {
	    				Sqlhelper0.executeUpdate(remarksql, null);
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
					}
					
	    			response.setCharacterEncoding("utf-8");
	    			response.getWriter().append(result).flush();
				} catch (Exception e) {
	    			String result ="failed";
	    			response.setCharacterEncoding("utf-8");
	    			response.getWriter().append(result).flush();
	    			e.printStackTrace();
	    		}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
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
