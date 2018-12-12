package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.cardhandle.LoadFixGrid;

public class ProcessFlowCard extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProcessFlowCard() {
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
		int pageIndex = 0;
		int pageSize = 0;
		String barcode = "";
		int mark =0;
		
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		barcode= request.getParameter("key").trim();
		mark = Integer.parseInt(request.getParameter("mark"));	//待矫正  判断非空
		System.out.println("mark="+mark);
		String json ="";
		
		switch (mark) {
		case 0:
			json = ProcessFlowCard.loadGrid(pageIndex,pageSize,barcode,mark);	//主卡加载,刷条形码的加载
			break;
		case 1:
			json = LoadFixGrid.loadFixGrid(pageIndex, pageSize, barcode);	//返工件加载
			break;
		case 2:
			json = ProcessFlowCard.loadMutiGrid(pageIndex,pageSize,barcode);	//质检多批次加载
			break;
		case 3:
			json = LoadFixGrid.loadFixGrid(pageIndex, pageSize, barcode);	//报废件重新下单加载
			break;

		default:
			json = ProcessFlowCard.reLoadGrid(pageIndex, pageSize, barcode);	//保存数据后的刷新
			break;
		}
		System.out.println(json);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		
	}
	

	public static String loadGrid(int pageIndex,int pageSize,String barcode,int mark){
//		int mark = 0;	0代表初始化查询，1代表编辑后加载
//		分页
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		int totalCount=0;
		
//		barcode 长度判断
		boolean cresult=BarcodeLength.barcodeLength(barcode);
		String headersql="";		//表单数据
		String bodysql="";		//表头数据
		String totalsql="";
		String json="";
		if(mark ==5){
//			搜索父barcode
			String fbarcode = SearchFbarcode.searchFbarcode(barcode);
			
//			8-22
			bodysql = "select f.* from " +
					"(select e.fo_opname,c.num,c.workid,rownum rn,c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark,e.fo_no,e.rated_time,e.equipcode,a.partsplanid,a.codeid,g.staff_name,h.machinename,d.usedtime " +
					",a.partnum,d.workerid,d.machineid " +
					"from partsplan a " +
					"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum and e.isinuse='1' " +
					"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
					"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no "+
					"left join employee_info g on g.staff_code = d.workerid "+
					"left join machinfo h on h.machineid = d.machineid "+
					" where a.codeid = '"+fbarcode+"' and e.ISINUSE='1' order by to_number(e.fo_no) asc)f "+
					"where "+min+ "<rn and rn <=" +max+" order by to_number(fo_no) asc ";
			
			System.out.println("bodysql="+bodysql);
			
			totalsql= "select count(*) from (select a.partsplanid,e.fo_no from " +
					"partsplan a " +
					"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1' " +
					" where a.codeid = '"+fbarcode+"' and e.ISINUSE='1')";
		}else{
			if(cresult){
//				根条码号
				String fbarcode = SearchFbarcode.searchFbarcode(barcode);
				
				bodysql = "select *　from(" +
						"select f.*,rownum rn from " +
				"(select e.fo_opname,c.num,c.workid,'1',c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark,e.fo_no,e.rated_time,e.equipcode,a.partsplanid,a.codeid,g.staff_name,h.machinename,d.usedtime " +
				",a.partnum,d.workerid,d.machineid,i.companyname,e.is_co " +
				"from partsplan a " +
				"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1' " +
				"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
				"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no "+
				"left join employee_info g on g.staff_code = d.workerid "+
				"left join machinfo h on h.machineid = d.machineid "+
				"left join outassistcom i on d.workerid = i.companyid "+
				" where a.codeid = '"+fbarcode+"' and e.ISINUSE='1' order by to_number(e.fo_no) asc )f )"+
				"where "+min+ "<rn and rn <=" +max+" order by to_number(fo_no) asc";
//				System.out.println("bodysql="+bodysql);
				
				totalsql= "select count(*) from (select a.partsplanid,e.fo_no from " +
				"partsplan a " +
				"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1' " +
				" where a.codeid = '"+fbarcode+"' and e.ISINUSE='1')";
				
			}else{
				
				
				bodysql = "select *　from (" +
						"select f.*,rownum rn from " +
				"(select e.fo_opname,c.num,c.workid,'1',c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark,e.fo_no,e.rated_time,e.equipcode,a.partsplanid,a.codeid,g.staff_name,h.machinename,d.usedtime " +
				",a.partnum,d.workerid,d.machineid,i.companyname,e.is_co " +
				"from partsplan a " +
				"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1' " +
				"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
				"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no "+
				"left join employee_info g on g.staff_code = d.workerid "+
				"left join machinfo h on h.machineid = d.machineid "+
				"left join outassistcom i on d.workerid = i.companyid "+
				" where a.codeid = '"+barcode+"' and e.ISINUSE='1' order by to_number(e.fo_no) asc)f )"+
				"where "+min+ "<rn and rn <=" +max+" order by to_number(fo_no) asc";
//			System.out.println("bodysql="+bodysql);
			
//			totalsql="select count(*) from " +
//				"(select d.drawingid from order_detail d where barcode = '"+barcode+"') a" +
//				" left join assign b on b.product_id= a.drawingid ";
			totalsql= "select count(*) from (select a.partsplanid,e.fo_no from " +
			"partsplan a " +
			"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1' " +
				" where a.codeid = '"+barcode+"' and e.ISINUSE='1')";
			}
		}
		ResultSet totalRst=null;
		try{	
			totalRst=Sqlhelper0.executeQuery(totalsql, null);
			totalRst.next();
			totalCount=totalRst.getInt(1);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
//			//			Sqlhelper0.close();
			if(totalRst != null){
		    	try {
				totalRst.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		
		ResultSet poRst =null;
		try{	
			poRst=Sqlhelper0.executeQuery(bodysql, null);
			List<PoFlowBean> poList = new ArrayList<PoFlowBean>();
//			List<Order> orderList = new ArrayList<Order>();
			try{
				while(poRst.next()){
//					工时定额还未取
					PoFlowBean gridbean =new PoFlowBean();
//				
					gridbean.setFo_opname(poRst.getString(1));
//					gridbean.setRated_time(poRst.getString(2));
					gridbean.setNum(poRst.getString(2));
//					gridbean.setWorkername(poRst.getString(3));
					gridbean.setCheckdate(poRst.getString(6));
					gridbean.setAccept_num(poRst.getString(7));
					gridbean.setReject_num(poRst.getString(8));
					gridbean.setChecker(poRst.getString(9));
					gridbean.setConfirm_num(poRst.getString(10));
					gridbean.setRemark(poRst.getString(11));
					gridbean.setFo_no(poRst.getString(12));
					
//					9-1
//					gridbean.setWorkername(poRst.getString(17));
					gridbean.setWorkername(poRst.getString(17));
					gridbean.setMachine(poRst.getString(18));
					gridbean.setUsedtime(poRst.getDouble(19));
//					9-19
					gridbean.setRated_time(poRst.getString(13));
					gridbean.setNum(poRst.getString(20));
					gridbean.setWorkerid(poRst.getString(21));
					gridbean.setMachineid(poRst.getString(22));
//				是否外协
					int isco = poRst.getInt(24);
					String temp = poRst.getString(23);
					if(isco==1&&temp!=null){
						gridbean.setWorkername(poRst.getString(23));
					}
					poList.add(gridbean);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				
			}
			json = PluSoft.Utils.JSON.Encode(poList);
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
//			//			Sqlhelper0.close();
			//			Sqlhelper0.close();
			if(poRst != null){
		    	try {
				poRst.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
//		System.out.println(json);
		return json;
	}
	
	public static String loadMutiGrid(int pageIndex,int pageSize,String barcode){
		String json = "";
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		int totalCount=0;
		
		boolean cresult=BarcodeLength.barcodeLength(barcode);
		String headersql="";		//表单数据
		String bodysql="";		//表头数据
		String totalsql="";
		
		if(cresult){
//			从派工中找,待矫正，这里是从 po_routing2 中找的数据
			String fbarcode = SearchFbarcode.searchFbarcode(barcode);
			
//			bodysql ="select f.* from (select e.fo_opname,c.num,c.workid,rownum rn,c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark,b.operid,a.partsplanid,a.codeid,b.processplanid " +
//			"from partsplan a " +
//				"left join processesplan b on b.planid = a.partsplanid " +
//				"left join assign c on c.planid = a.partsplanid and c.fo_no = b.operid " +
//				"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = b.operid " +
//				"left join fo_detail e on e.product_id =b.productid and e.issue_num = b.issuenum and e.fo_no = b.operid " +
//			"where a.codeid = '"+fbarcode+"')f "+
//				"where "+min+ "<rn and rn <=" +max+" order by to_number(f.operid) asc";
			
			bodysql = "select * from( " +
					"select f.*,rownum rn from " +
			"(select e.fo_opname,c.num,c.workid,'1',c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark,e.fo_no,e.rated_time,e.equipcode,a.partsplanid,a.codeid,g.staff_name,h.machinename,d.usedtime " +
			",a.partnum,d.workerid,d.machineid,i.companyname,e.is_co " +
			"from partsplan a " +
			"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1' " +
			"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
			"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no "+
			"left join employee_info g on g.staff_code = d.workerid "+
			"left join machinfo h on h.machineid = d.machineid "+
			"left join outassistcom i on d.workerid = i.companyid "+
			" where a.codeid = '"+fbarcode+"' and e.ISINUSE='1' order by to_number(e.fo_no) asc)f )"+
			"where "+min+ "<rn and rn <=" +max+" order by to_number(fo_no) asc";
//			System.out.println("bodysql="+bodysql);
			
//			totalsql="select count(*) from " +
//			"(select d.drawingid from order_detail d where barcode = '"+fbarcode+"') a" +
//			" left join assign b on b.product_id= a.drawingid ";
			
			totalsql= "select count(*) from (select a.partsplanid,e.fo_no from " +
			"partsplan a " +
			"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1' " +
			" where a.codeid = '"+fbarcode+"' and e.ISINUSE='1')";
			
		}else{
			
			
//			bodysql ="select f.* from (select e.fo_opname,c.num,c.workid,rownum rn,c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark,b.operid,a.partsplanid,a.codeid,b.processplanid " +
//			"from partsplan a " +
//				"left join processesplan b on b.planid = a.partsplanid " +
//				"left join assign c on c.planid = a.partsplanid and c.fo_no = b.operid " +
//				"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = b.operid " +
//				"left join fo_detail e on e.product_id =b.productid and e.issue_num = b.issuenum and e.fo_no = b.operid " +
//			"where a.codeid = '"+barcode+"')f "+
//				"where "+min+ "<rn and rn <=" +max+" order by to_number(f.operid) asc";
			
			bodysql = "select * from( " +
					"select f.*,rownum rn from " +
			"(select e.fo_opname,c.num,c.workid,'1',c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark,e.fo_no,e.rated_time,e.equipcode,a.partsplanid,a.codeid,g.staff_name,h.machinename,d.usedtime " +
			",a.partnum,d.workerid,d.machineid,i.companyname,e.is_co " +
			"from partsplan a " +
			"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1'  " +
			"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
			"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no "+
			"left join employee_info g on g.staff_code = d.workerid "+
			"left join machinfo h on h.machineid = d.machineid "+
			"left join outassistcom i on d.workerid = i.companyid "+
			" where a.codeid = '"+barcode+"' and e.ISINUSE='1' order by to_number(e.fo_no) asc)f )"+
			"where "+min+ "<rn and rn <=" +max+" order by to_number(fo_no) asc";
//		System.out.println("bodysql="+bodysql);
		
//		totalsql="select count(*) from " +
//			"(select d.drawingid from order_detail d where barcode = '"+barcode+"') a" +
//			" left join assign b on b.product_id= a.drawingid ";
		totalsql= "select count(*) from (select a.partsplanid,e.fo_no from " +
		"partsplan a " +
		"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum  and e.isinuse='1' " +
			" where a.codeid = '"+barcode+"' and e.ISINUSE='1')";
		}
		ResultSet totalRst=null;
		try{	
			totalRst=Sqlhelper0.executeQuery(totalsql, null);
			totalRst.next();
			totalCount=totalRst.getInt(1);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
//			//			Sqlhelper0.close();
			if(totalRst != null){
		    	try {
				totalRst.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		
		ResultSet poRst =null;
		try{	
			poRst=Sqlhelper0.executeQuery(bodysql, null);
			List<PoFlowBean> poList = new ArrayList<PoFlowBean>();
//			List<Order> orderList = new ArrayList<Order>();
			try{
				while(poRst.next()){
//					工时定额还未取
					PoFlowBean gridbean =new PoFlowBean();
//				
					gridbean.setFo_opname(poRst.getString(1));
//					gridbean.setRated_time(poRst.getString(2));
					gridbean.setNum(poRst.getString(2));
//					gridbean.setWorkername(poRst.getString(3));
					gridbean.setCheckdate(poRst.getString(6));
					gridbean.setAccept_num(poRst.getString(7));
					gridbean.setReject_num(poRst.getString(8));
					gridbean.setChecker(poRst.getString(9));
					gridbean.setConfirm_num(poRst.getString(10));
					gridbean.setRemark(poRst.getString(11));
					gridbean.setFo_no(poRst.getString(12));
					
//					9-1
//					gridbean.setWorkername(poRst.getString(17));
					gridbean.setWorkername(poRst.getString(17));
					gridbean.setMachine(poRst.getString(18));
					gridbean.setUsedtime(poRst.getDouble(19));
//					9-19
					gridbean.setRated_time(poRst.getString(13));
					gridbean.setNum(poRst.getString(20));
					gridbean.setWorkerid(poRst.getString(21));
					gridbean.setMachineid(poRst.getString(22));
					
					int isco = poRst.getInt(24);
					String temp = poRst.getString(23);
					if(isco==1&&temp !=null){
						gridbean.setWorkername(poRst.getString(23));
					}
					poList.add(gridbean);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				
			}
			json = PluSoft.Utils.JSON.Encode(poList);
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
//			//			Sqlhelper0.close();
			//			Sqlhelper0.close();
			if(poRst != null){
		    	try {
				poRst.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return json ;
	}
	public static String reLoadGrid(int pageIndex,int pageSize,String barcode){
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		int totalCount=0;

		
		String headersql="";		//表单数据
		String bodysql="";		//表头数据
		String totalsql="";
		String json="";
		
			bodysql="select fo_opname,rated_time,fnum,workername,checkdate,accept_num,reject_num,checker,confirm_num,remark,po_index,fo_no,staff_name,machinename,usedtime,workerid,machineid,companyname " +
					" from (select em.*,rownum rn from (select fo_opname,rated_time,fnum,workername,checkdate,accept_num,reject_num,checker,confirm_num,remark,po_index,fo_no,'1',g.staff_name,h.machinename,f.usedtime,f.workerid,f.machineid,i.companyname from po_routing2 f "+
				"left join employee_info g on g.staff_code = f.workerid "+
				"left join machinfo h on h.machineid = f.machineid "+
				"left join outassistcom i on f.workerid = i.companyid "+
				"where barcode='"+barcode+"')em )where "+min+ "<rn and rn <=" +max+" order by fo_no asc";	//f.po_index
//			System.out.println(bodysql);
			totalsql="select count(*) from po_routing2 " +
			"where barcode = '"+barcode+"'";
//			System.out.println(totalsql);
			
			ResultSet totalRst=null;
			try{	
				
				totalRst=Sqlhelper0.executeQuery(totalsql, null);
				totalRst.next();
				totalCount=totalRst.getInt(1);
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				//			Sqlhelper0.close();
				if(totalRst != null){
			    	try {
					totalRst.close();
				} catch (Exception e2) {
				// TODO: handle exception
					}
			}
			}
			
			ResultSet poRst =null;
			try{	
				System.out.println(bodysql);
				poRst=Sqlhelper0.executeQuery(bodysql, null);
				System.out.println("ok  "+bodysql);
				List<PoFlowBean> poList = new ArrayList<PoFlowBean>();
				try{
					while(poRst.next()){
//						工时定额还未取
						PoFlowBean gridbean =new PoFlowBean();
						
						gridbean.setFo_opname(poRst.getString(1));
						gridbean.setRated_time(poRst.getString(2));
						gridbean.setNum(poRst.getString(3));
//						gridbean.setWorkername(poRst.getString(4));
						gridbean.setCheckdate(poRst.getString(5));
						gridbean.setAccept_num(poRst.getString(6));
						gridbean.setReject_num(poRst.getString(7));
						gridbean.setChecker(poRst.getString(8));
						gridbean.setConfirm_num(poRst.getString(9));
						gridbean.setRemark(poRst.getString(10));
						gridbean.setFo_no(poRst.getString(12));
//						9-1
						gridbean.setWorkername(poRst.getString(13));
						gridbean.setMachine(poRst.getString(14));
						gridbean.setUsedtime(poRst.getDouble(15));
						gridbean.setWorkerid(poRst.getString(16));
						gridbean.setMachineid(poRst.getString(17));
						
						String out = poRst.getString(18);
						if(out==null||out.equals("null")||out.isEmpty()){
							
						}else{
							gridbean.setWorkername(out);
						}
						poList.add(gridbean);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					
				}
				json = PluSoft.Utils.JSON.Encode(poList);
				json = "{\"total\":"+totalCount+",\"data\":"+json+"}";

			}catch (Exception e) {
				// TODO: handle exception
			}finally{
//				//			Sqlhelper0.close();
				//			Sqlhelper0.close();
				if(poRst != null){
			    	try {
					poRst.close();
				} catch (Exception e2) {
				// TODO: handle exception
					}
			}
				
			}
//			System.out.println(json);
		return json;
		
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
		doGet(request,response);
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
