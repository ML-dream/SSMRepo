package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.cardhandle.EditControl;
import com.xm.testaction.qualitycheck.cardhandle.RejectJudge;
import com.xm.testaction.qualitycheck.sum.UpToProcessPlan;

public class SaveForm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveForm() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		检验者由登录账户中取得,待矫正
		String checker="";
		HttpSession session = request.getSession();
	    checker = ((User)session.getAttribute("user")).getStaffName();
	    
		String json = request.getParameter("data");
	    ArrayList datalist = (ArrayList)Test.JSON.Decode(json);
	    String barcode ="";
	    String fo_opname ="";
	    int num =0;	//计划数量
	    String workername ="";
	    int accept_num =0;
	    int reject_num =0;
	    int confirm_num =0;
	    String remark ="";
	    int fo_no = 0;
	    int checked = 0;	//这个字段是是否质检的标志位
	    double rated_time = 0;
//	    9-1
	    String machineid = "";
	    double usedtime=0;
	    String workerid = "";
	    
	    for(int i=0,l=datalist.size(); i<l; i++){
	    	HashMap datamap = (HashMap)datalist.get(i);
//	  		待矫正，工时定额未取   
			 barcode = datamap.get("barcode") != null ? datamap.get("barcode").toString() : "";
	         fo_opname = ChineseCode.toUTF8(datamap.get("fo_opname") != null ? datamap.get("fo_opname").toString() : "");
	         String  nums = datamap.get("num") != null ? datamap.get("num").toString() : "0";
			 workername =ChineseCode.toUTF8(datamap.get("workername") != null ? datamap.get("workername").toString() : "");
			
			 String accept_nums = datamap.get("accept_num") != null ? datamap.get("accept_num").toString() : "";
			 String reject_nums = datamap.get("reject_num") != null ? datamap.get("reject_num").toString() : "";
			 String confirm_nums = datamap.get("confirm_num") != null ? datamap.get("confirm_num").toString() : "";
			 remark = ChineseCode.toUTF8(datamap.get("remark") != null ? datamap.get("remark").toString() : "");
			  String fo_nos= datamap.get("fo_no") != null ? datamap.get("fo_no").toString() : "";
			  String checkeds = datamap.get("checked") != null ? datamap.get("checked").toString() : "";
			  if(nums.equals("")){nums="0";}
			 num=Integer.parseInt(nums);
			 accept_num=Integer.parseInt(accept_nums);
			 reject_num= Integer.parseInt(reject_nums);
//			 9-12  如果 不填写 送检数量，则后台进行处理 
			 if(confirm_nums.equals("")){
				 confirm_num = accept_num + reject_num;
			 }else{
				 confirm_num= Integer.parseInt(confirm_nums);
			 }
			 
			 fo_no = Integer.parseInt(fo_nos);
			String rated_times =datamap.get("ratedtime") != null ? datamap.get("ratedtime").toString() : "0" ;
			rated_time =Double.parseDouble(rated_times);
			 machineid = datamap.get("machine") != null ? datamap.get("machine").toString() : "";
			 usedtime =  (datamap.get("usedtime") != null ? Double.parseDouble(datamap.get("usedtime").toString()) : confirm_num*rated_time ) ;
			 if(usedtime ==0.0){
				 usedtime = confirm_num*rated_time;
			 }
			 workerid = ChineseCode.toUTF8(datamap.get("workerid") != null ? datamap.get("workerid").toString() : "");
			 
			 checked = Integer.parseInt(checkeds);		
	    } 
	    
	    String saveformsql="";	//insert 或者 update 
	    
	    if(checked == 1){ 		//修改数据
	    	
	    	int currentfo =0;
	    	String editsql = "select barcode,fo_no from edit_control where barcode='"+barcode+"'";
	    	ResultSet editrst = null;	
	    	try {
	    		
	    		editrst = Sqlhelper0.executeQuery(editsql, null);
	    		editrst.next();
	    		currentfo = editrst.getInt(2);
	    	}catch (Exception e) {
				// TODO: handle exception
			}finally{
//				Sqlhelper0.close();
				if(editrst != null){
			    	try {
			    		editrst.close();
				} catch (Exception e2) {
				// TODO: handle exception
					}
			}
			}
	    	
//	    	判断修改的行是否是当次工序 currentCode.equals(barcode)&& currentfo 是控制表中的工序
	    	if(currentfo == fo_no){
//	    		是当前工序，允许修改
	    		saveformsql = "update po_routing2 set checkdate=sysdate,rated_time="+rated_time+", fo_opname='"+fo_opname+"',checker='"+checker+"',fnum="+num+",workerid='"+workerid+"',accept_num="
	    						+accept_num+",reject_num="+reject_num+",confirm_num="+confirm_num+",remark='"+remark+"',machineid='"+machineid+"',usedtime="+usedtime+" where " +
	    							"barcode='"+barcode+"' and fo_no="+fo_no+"";
	    		System.out.println(saveformsql);
	    		try {
	    			Sqlhelper0.executeUpdate(saveformsql,null);
//	    			修改数据成功
	    			String result = "success";
//	    			加判断 不合格数 待修正,同时判断修改前是否有不合格品
	    			result = RejectJudge.rejectJudge(reject_num);
	    			
	    			if(result.equals("rejectOccured")){		//出现不合格品
//	    				如果true，则代表修改前的不合格数>0,则前台不提示
	    				if(RejectJudge.numSearch(barcode)){
	    					result="";
//	    					update
	    					RejectJudge.updateRejectInfo(workername,accept_num,reject_num,checker,confirm_num,barcode,fo_no);
	    				}else{
//	    				insert 
	    					RejectJudge.insertRejectInfo(barcode, fo_opname, rated_time, num, workername, accept_num, reject_num, checker,fo_no, confirm_num);
	    				}
//    					修改editcontrol表的不合格数量
	    				RejectJudge.updateEditControl(barcode, fo_no+"", reject_num);
	    			}
	    			
	    			response.setCharacterEncoding("UTF-8");
	    			response.getWriter().append(result).flush();
	    		} catch (Exception e) {
	    			String result = "{\"result\":\"操作失败!\"}";
	    			response.setCharacterEncoding("UTF-8");
	    			response.getWriter().append(result).flush();
	    			e.printStackTrace();
	    		}
	    	}else{
	    		String result= "qualitycheck/mutiBatchCard.jsp";  //待矫正
//	    		request.getRequestDispatcher("qualitycheck/mutiBatchCard.jsp").forward(request, response);
	    		response.setCharacterEncoding("UTF-8");
	 			response.getWriter().append(result).flush();
	    	}
	    }else{
//	    	填充新的质检信息
	    	 saveformsql="insert into po_routing2 (barcode,fo_opname,fnum,workerid,accept_num," +
					 		"reject_num,confirm_num,remark,checker,fo_no,machineid,usedtime,rated_time) values ('"+barcode+"','"+fo_opname+"',"+num+",'"+workerid+"'," +
					 		""+accept_num+","+reject_num+","+confirm_num+",'"+remark+"','"+checker+"',"+fo_no+",'"+machineid+"',"+usedtime+",'"+rated_time+"')";
	 		System.out.println("saveformsql="+saveformsql);
	 		
//	 		Sqlhelper0 sqlData = new Sqlhelper0();
	 		try {
	 			Sqlhelper0.executeUpdate(saveformsql,null);
	 			String result ="";
//	 			9-18   执行成功了，才能统计数据
	 			System.out.println("ok  "+saveformsql);
	 			try {
//	 				9-1 
//					ToSum.flowToSum(barcode, fo_no);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
//	 			加判断 不合格数
	 			result = RejectJudge.rejectJudge(reject_num);
	 			if(result.equals("rejectOccured")){
	 				RejectJudge.insertRejectInfo(barcode, fo_opname, rated_time, num, workername, accept_num, reject_num, checker,fo_no, confirm_num);
	 			}
	 			
	 			response.setCharacterEncoding("UTF-8");
	 			response.getWriter().append(result).flush();
	 		} catch (Exception e) {
	 			String result = "{\"result\":\"操作失败!\"}";
	 			response.setCharacterEncoding("UTF-8");
	 			response.getWriter().append(result).flush();
	 			e.printStackTrace();
	 		}
	 		
	 		String editupdatesql = "update edit_control set fo_no="+fo_no+",reject_num="+reject_num+" where barcode = '"+barcode+"'";
//	 		9-13 查找edit_control 表是否有记录 
	 		String editsql ="select count(*) from edit_control a where a.barcode ='"+barcode+"' ";
	 		int editresult = 0;		//0 默认没有记录
	 		ResultSet editrs =null;
	 		try {
				
				editrs = Sqlhelper0.executeQuery(editsql, null);
				editrs.next();
				editresult = editrs.getInt(1);
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
//				Sqlhelper0.close();
				if(editrs != null){
			    	try {
			    		editrs.close();
				} catch (Exception e2) {
				// TODO: handle exception
					}
			}
			}
	 		if(editresult == 0){
	 			editupdatesql = "insert into edit_control (barcode,fo_no,reject_num) values ('"+barcode+"','"+fo_no+"',"+reject_num+")";
 			}
	 		try {
//	 			更新edit_control 中的当前工序
    			Sqlhelper0.executeUpdate(editupdatesql,null);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    }
	    try {
			UpToProcessPlan.upToProcessPlan(barcode, fo_no);	//上报到工序计划 
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
