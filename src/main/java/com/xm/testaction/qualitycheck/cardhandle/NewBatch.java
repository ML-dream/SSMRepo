package com.xm.testaction.qualitycheck.cardhandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.repackage.cglib.proxy.Dispatcher;

import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.BarcodeLength;
import com.xm.testaction.qualitycheck.PoFlowBean;
import com.xm.testaction.qualitycheck.SearchFbarcode;

public class NewBatch extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NewBatch() {
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
//		根barcode 多批次这里不存runnum了，因为runnum是主键
		String fbarcode = request.getParameter("fbarcode");
		String gbarcode = fbarcode;
		String runnum = "";
		boolean length = BarcodeLength.barcodeLength(fbarcode);
		if(length){
//			如果是子表，则找到 根 barcode
			gbarcode = SearchFbarcode.searchFbarcode(fbarcode);
		}
//			新生成的流程卡号
			String barcode = "";
			
			int status = 2;
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
				
//				将父流程卡号+序列  串起来
				barcode = gbarcode + attach_no;
				
				try {
					String saveattachsql= "insert into attach_info (barcode,attach_no,status,fbarcode,gbarcode)" +	//runnum
						" values ('"+barcode+"',"+attach_no+","+status+",'"+fbarcode+"','"+gbarcode+"' )";	//,'"+runnum+"'
					System.out.println("savesql="+ saveattachsql);
	    			Sqlhelper0.executeUpdate(saveattachsql,null);
	    			String result ="success";
	    			String json = "{\"result\":\""+result+"\",\"barcode\":\""+barcode+"\"}";
	    			response.setCharacterEncoding("utf-8");
	    			response.getWriter().append(json).flush();
				} catch (Exception e) {
	    			String result ="failed";
	    			String json = "{\"result\":"+result+",\"barcode\":"+barcode+"}";
	    			response.setCharacterEncoding("utf-8");
	    			response.getWriter().append(json).flush();
	    			e.printStackTrace();
	    		}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				try {
					if(attachRst!=null){
						attachRst.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
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
