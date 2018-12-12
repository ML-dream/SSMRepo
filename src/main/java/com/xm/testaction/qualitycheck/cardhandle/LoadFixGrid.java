package com.xm.testaction.qualitycheck.cardhandle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.FixGridString;
import com.xm.testaction.qualitycheck.PoFlowBean;

public class LoadFixGrid {
	public static String loadFixGrid(int pageIndex,int pageSize,String barcode){
		
//		分页
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		int totalCount=0;
		
//	还要判断是否是工序1 ，否则没有数据 	
		String json = "";
		
//		首先查看 是返工件还是报废件
		String statesql= "select status,fo_no,gbarcode,fbarcode from attach_info where barcode = '"+barcode+"'";
		ResultSet stateRst = null;
//		待矫正
		String jsonsql = "";
		ResultSet jsonRst = null;
		
		try{
			int state = 2;		//默认为返工件
			int fo_no = 0;
			String gbarcode = "";
			String fbarcode = "";
//判断工件状态			
			try{
				stateRst = Sqlhelper0.executeQuery(statesql, null);
				stateRst.next();
				state = stateRst.getInt(1);
				fo_no = stateRst.getInt(2);
				gbarcode = stateRst.getString(3);
				fbarcode = stateRst.getString(4);
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				//      Sqlhelper0.close();	
				if(stateRst != null){
					try {
						stateRst.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
//		计算数据总数。	
			String totalsql= "select count(*) from (select a.partsplanid,e.fo_no from " +
			"partsplan a " +
			"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum and e.isinuse='1' " +
			" where a.codeid = '"+gbarcode+"')";
			System.out.println( "ok"+totalsql);
			ResultSet totalRst = null;
			try{
				totalRst = Sqlhelper0.executeQuery(totalsql, null);
				totalRst.next();
				totalCount = totalRst.getInt(1);
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				//      Sqlhelper0.close();
				if(totalRst != null){
					try {
						totalRst.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
			
//			3为报废 重新下单
			if (state == 3){
//				重新下单件，只需要查 gbarcode的工序  与自己的数据
				
				jsonsql = "select * from (" +
						"select f.*,rownum rn from " +
				"(select e.fo_opname,c.num,c.workid,'1',c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark,e.fo_no,e.rated_time,e.equipcode,a.partsplanid,a.codeid,g.staff_name,h.machinename,d.usedtime " +
				",a.partnum,d.workerid,d.machineid,i.companyname,e.is_co " +
				"from partsplan a " +
				"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum and e.isinuse='1' " +
				"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
				"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no "+
				"left join employee_info g on g.staff_code = d.workerid "+
				"left join machinfo h on h.machineid = d.machineid "+
				"left join outassistcom i on d.workerid = i.companyid "+
				" where a.codeid = '"+gbarcode+"' order by to_number(e.fo_no) asc)f )"+
				"where "+min+ "<rn and rn <=" +max+" order by to_number(fo_no) asc";
			}else {
				jsonsql = FixGridString.fixGridString(barcode, fbarcode, gbarcode, fo_no, min, max);
				
				
			}
			System.out.println("fixsql="+jsonsql);
			try {
				jsonRst = Sqlhelper0.executeQuery(jsonsql, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			List<PoFlowBean> fixList = new ArrayList<PoFlowBean>();
			try{
				while(jsonRst.next()){
//					工时定额还未取 待矫正
					PoFlowBean gridbean =new PoFlowBean();
//					
//					gridbean.setFo_opname(jsonRst.getString(1));
////					gridbean.setRated_time(poRst.getString(2));
//					gridbean.setNum(jsonRst.getString(2));
//					gridbean.setWorkername(jsonRst.getString(3));
//					gridbean.setCheckdate(jsonRst.getString(6));
//					gridbean.setAccept_num(jsonRst.getString(7));
//					gridbean.setReject_num(jsonRst.getString(8));
//					gridbean.setChecker(jsonRst.getString(9));
//					gridbean.setConfirm_num(jsonRst.getString(10));
//					gridbean.setRemark(jsonRst.getString(11));
//					gridbean.setFo_no(jsonRst.getString(4));
//  临时  开始					
					gridbean.setFo_opname(jsonRst.getString(1));
//					gridbean.setRated_time(poRst.getString(2));
					gridbean.setNum(jsonRst.getString(2));
//					gridbean.setWorkername(poRst.getString(3));
					gridbean.setCheckdate(jsonRst.getString(6));
					gridbean.setAccept_num(jsonRst.getString(7));
					gridbean.setReject_num(jsonRst.getString(8));
					gridbean.setChecker(jsonRst.getString(9));
					gridbean.setConfirm_num(jsonRst.getString(10));
					gridbean.setRemark(jsonRst.getString(11));
					gridbean.setFo_no(jsonRst.getString(12));
					
//					9-1
//					gridbean.setWorkername(jsonRst.getString(17));
					gridbean.setWorkername(jsonRst.getString(17));
					gridbean.setMachine(jsonRst.getString(18));
					gridbean.setUsedtime(jsonRst.getDouble(19));
//					9-19
					gridbean.setRated_time(jsonRst.getString(13));
					gridbean.setNum(jsonRst.getString(20));
					gridbean.setWorkerid(jsonRst.getString(21));
					gridbean.setMachineid(jsonRst.getString(22));
//					是否外协
					String out =  jsonRst.getString(23);
					if(out==null || out.equals("")){
						
					}else{
						gridbean.setWorkername(out); //外协
					}
//  结束					
					fixList.add(gridbean);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					jsonRst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			json = PluSoft.Utils.JSON.Encode(fixList);
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		
		return json;
	}
}
