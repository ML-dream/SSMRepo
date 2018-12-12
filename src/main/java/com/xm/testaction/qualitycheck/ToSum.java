package com.xm.testaction.qualitycheck;

import java.sql.ResultSet;

import com.wl.tools.Sqlhelper0;

public class ToSum {
	public static void flowToSum(String barcode,int afo_no){
//		应该是处理上一道工序，而不是工序号减1。
//		流程卡数据保存至统计表,从saveform.java 进，合格品、废品数
		int fo_no = 0;	//保存前一道工序的数据
		
		
		boolean cresult=BarcodeLength.barcodeLength(barcode);	//判断是否子卡
		String sql = "";
		int ffo_no = findFlowFirstNo(barcode);		//第一道工序
		if(cresult){
//			如果是子卡，则累加到主卡上。
//			9-18 子卡的话，先查询统计表是否有父卡的该工序数据 。
			
			String gbarcode="";
			
			if(afo_no == 1 || afo_no==ffo_no){
//				待矫正,工序1 的数据已经保存至表
			}else{
				fo_no = findFo_no(afo_no, barcode);		 //上一道工序
//				判断 父卡数据是否已经储存
				SflowJudge.sflowJudge(barcode, fo_no);
				System.out.println();
				String keysql = "select b.accept_num,b.reject_num,d.accept,d.reject,c.gbarcode,b.usedtime,d.usedtime from " +
						"po_routing2 b " +
							"left join attach_info c on c.barcode = b.barcode " +
							"left join qualitysum d on d.barcode= c.gbarcode and d.fo_no = b.fo_no " +
						"where b.barcode = '"+barcode+"' and b.fo_no = "+fo_no;
				ResultSet rs = null;
				int accept=0;
				int reject = 0;
				double usedtime = 0;
				try {
					rs = Sqlhelper0.executeQuery(keysql, null);
					rs.next();
					accept = rs.getInt(1) + rs.getInt(3);
					reject = rs.getInt(2) + rs.getInt(4);
					usedtime = rs.getDouble(6)+rs.getDouble(7);
					gbarcode = rs.getString(5);
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					Sqlhelper0.close();
					if(rs != null){
				    	try {
						rs.close();
					} catch (Exception e2) {
					// TODO: handle exception
						}
				}
				}
				
				String usql = " update qualitysum e set e.accept=" +accept+",e.reject="+reject+",e.usedtime="+usedtime+
//						"(e.accept,e.reject) values ("+accept+","+reject+") " +
						" where e.barcode = '"+gbarcode+"' and e.fo_no ="+fo_no;
				System.out.println(usql);
				try {
					Sqlhelper0.executeUpdate(usql, null);
					System.out.println("usql succ");
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
			}
		}else{
//			主卡
			if(afo_no ==1 || afo_no==ffo_no){
//				待矫正
			}else{
				fo_no = findFo_no(afo_no, barcode);
				
				sql = "insert into qualitysum " +
						"(select a.barcode,a.fo_no,a.accept_num,a.reject_num,a.todiscard,a.tofix,a.toredo,a.tosale,a.usedtime,a.fixtime,a.distime " +
						"from po_routing2 a " +
						"where a.barcode = '"+barcode+"' and a.fo_no = "+fo_no+")";
				try {
					Sqlhelper0.executeUpdate(sql, null);
					System.out.println(sql+"succ");
					FflowJudge.fflowJudge(barcode, fo_no, afo_no);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
			}
		}
	}
	
	public static void stateToSum(String type,String runnum,int num,int afo_no,String barcode){
//		不合格品详情 保存至统计表,不合格类型，数量  从废品卡等 进  ,afo_no  为当前工序
//		与流程卡不同的是，第一道工序的数据也要保存
//		每道工序都存，先保存到po_routing2中  type 是为了匹配 统计表中的字段,处理上一道工序数据
//		两步操作，先把当前工序数据保存至po_routing2 ,然后把上一道工序数据累加到统计表。

//		保存当前数据到porouting2
//		9-26  一道工序可能对应多张审理单 
		String timetype = "fixtime";	//工时损耗类型 
		if(type.equals("toDiscard")){
			timetype = "distime";
		}
			
		String sqla ="select a.barcode,a.fo_no,a.tofix,a.todiscard,a.fixtime,a.distime,nvl(b.opinion,'none') type,nvl(b.timeloss,0) timeloss,nvl(b.materialloss,0) matloss,b.runnum,b.rejectnum" +
				" from po_routing2 a " +
				" left join reject_state b on b.barcode = a.barcode and b.fo_no = a.fo_no" +
				" where a.barcode='"+barcode+"' and a.fo_no="+afo_no;
			
		ResultSet rsa = null;
		
		int tofix = 0;
		int todiscard = 0;
		double fixtime = 0 ;
		double distime = 0;
		double stuffloss = 0;
		
		String rejtype = "toFix";
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			while(rsa.next()){
				rejtype = rsa.getString(7);
				if(rejtype.equals("toFix")){
					tofix += rsa.getInt(11);
					fixtime += rsa.getDouble(8);
					
				}else if(rsa.equals("toDiscard")){
					todiscard += rsa.getInt(11);
					distime += rsa.getDouble(8);
				}
				stuffloss += rsa.getDouble(9);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
//			Sqlhelper0.close();
			if(rsa != null){
		    	try {
				rsa.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
			}
		}
		
		String posql ="update po_routing2 a set a.todiscard ="+todiscard+" ,a.tofix ="+tofix+" ,a.fixtime ="+fixtime+" ,a.distime ="+distime+" ,a.stuffloss ="+stuffloss+ 
		" where a.barcode = '"+barcode+"' and a.fo_no ="+ afo_no;
		try {
			Sqlhelper0.executeUpdate(posql, null);
			System.out.println(posql);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			Sqlhelper0.close();
		}
		
}
	public static void fstateToSum(String type,String runnum,int num,int afo_no,String barcode){
//		9-27  弃用 
		boolean cresult=BarcodeLength.barcodeLength(barcode);
		int fo_no =0;
		
		if(cresult){
//	待矫正	
//			如果是子卡，则累加到主卡上。
//			String fbarcode = SearchFbarcode.searchFbarcode(barcode);		//主卡 条形码
			String gbarcode="";
//			if(afo_no == 1){
//				待矫正,工序1 的数据上一步已经保存至表
//			}else{
//				fo_no = stateFindFo_no(afo_no, barcode);
//				上一道工序的数据
				stateToSum(type, runnum, num, afo_no, barcode);
//				System.out.println();
//				9-18   判断父卡数据是否已存
				try {
					SflowJudge.sflowJudge(barcode, fo_no);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
//				本道工序的数据上一步已经保存
				String keysql = "select b."+type+",d."+type+",c.gbarcode from " +
						"po_routing2 b " +
							"left join attach_info c on c.barcode = b.barcode " +
							"left join qualitysum d on d.barcode= c.gbarcode and d.fo_no = b.fo_no " +
						"where b.barcode = '"+barcode+"' and b.fo_no = "+afo_no;
				ResultSet rs = null;
				int typenum=0;
				try {
					rs = Sqlhelper0.executeQuery(keysql, null);
					rs.next();
					typenum = rs.getInt(1) + rs.getInt(2);
					gbarcode = rs.getString(3);
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
//					Sqlhelper0.close();
					if(rs != null){
				    	try {
						rs.close();
					} catch (Exception e2) {
					// TODO: handle exception
						}
				}
				}
				String usql = "update qualitysum e set e."+type+"="+typenum+
					"where e.barcode = '"+gbarcode+"' and e.fo_no ="+afo_no;
				try {
					Sqlhelper0.executeUpdate(usql, null);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
//			}
			
		}else{
//		主卡
//		if(afo_no == 1){
//			待矫正,工序1 的数据上一步已经保存至表
//		}else{
			stateToSum(type, runnum, num, afo_no, barcode);
			String quesql = "select a."+type+",b."+type+" from po_routing2 a " +
					"left join qualitysum b on b.barcode = a.barcode and b.fo_no = a.fo_no"+
					"where a.barcode = '"+barcode+"' and a.fo_no = "+afo_no+")";
			
			ResultSet ars = null;
//			String opinion ="";
			int snum = 0;
			String sql2="";
			try {
				ars = Sqlhelper0.executeQuery(quesql, null);
				ars.next();
//				opinion = ars.getString(1);		//废品 处理意见
				
				snum = ars.getInt(1) + ars.getInt(2);				//数量累加 
//				待矫正
				sql2 ="update qualitysum  set "+type+"="+snum +
				"where e.barcode = '"+barcode+"' and e.fo_no ="+afo_no;
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				Sqlhelper0.close();
				if(ars != null){
			    	try {
					ars.close();
				} catch (Exception e2) {
				// TODO: handle exception
					}
			}
			}
			try {
				Sqlhelper0.executeUpdate(sql2, null);
//				9-18
				try {
					FflowJudge.fflowJudge(barcode, fo_no, afo_no);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
//				Sqlhelper0.close();
			}
//		}
	}
	}
	
	public static void finishToSum(String barcode){
//		流程卡，完工，处理最后一道工序
//		最后一道工序除了要保存上一道工序的数据，还要处理自己的数据 
		
		int fo_no =0;	//最后一道工序
		int reject_num=0;	//最后一道工序的不合格数
		
		String fosql ="select a.fo_no,a.reject_num from po_routing2 a where a.barcode = '"+barcode+"' order by a.fo_no desc";
		ResultSet fors = null;
		try {
			fors = Sqlhelper0.executeQuery(fosql, null);
			fors.next();
			fo_no=fors.getInt(1);
			reject_num = fors.getInt(2);
			if(fors != null){
		    	try {
		    		fors.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
//			倒数第二道工序的数据也要存
			flowToSum(barcode, fo_no);
			
//			最后一道工序的合格数、废品数 累加到统计表
			try {
				finishToSum(barcode,fo_no);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(reject_num > 0){
//				8月24号，断点
//				在这里标示 为最后一道工序，辅助不合格品数据存储
				String lsql = "update reject_info set lastfo=1 where barcode='"+barcode+"' and fo_no="+fo_no;
				System.out.println(lsql);
				try {
					Sqlhelper0.executeUpdate(lsql, null);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
			}else{
//				否则，把reject_info 中最后一道工序的 标志位置为1
				int lfo_no = findStateLfo_no(barcode);
				String lsql = "update reject_info set lastfo=1 where barcode='"+barcode+"' and fo_no="+lfo_no;
				System.out.println(lsql);
				try {
					Sqlhelper0.executeUpdate(lsql, null);
					System.out.println("succ"+lsql);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			Sqlhelper0.close();
		}
		
	}
//	查找不合格品 的最后一道工序
	public static int findStateLfo_no(String barcode){
		int lfo_no = 0;
		String fosql="select c.fo_no from reject_info c where c.barcode ='"+barcode+"' order by c.fo_no desc";
		ResultSet rs = null;
		try {
			rs = Sqlhelper0.executeQuery(fosql, null);
			rs.next();
			lfo_no = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(rs != null){
		    	try {
				rs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return lfo_no;
	}
	public static int findFo_no(int afo_no,String barcode){
//		查找上一道工序
		int fo_no =0;
		String fosql ="select a.fo_no from po_routing2 a where a.barcode = '"+barcode+"' order by a.fo_no desc";
		System.out.println(fosql);
		ResultSet fors = null;
		try {
			fors = Sqlhelper0.executeQuery(fosql, null);
			while(fors.next()){
				if(fors.getInt(1) == afo_no){
					break;
				}
			}
			fors.next();
			fo_no=fors.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			Sqlhelper0.close();
			if(fors != null){
		    	try {
				fors.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return fo_no;
	}
	
//	查找第一道质检数据,流程卡
	public static int findFlowFirstNo(String barcode){
		int ffo_no = 0;
//		9-2
		String fosql ="select  b.fo_no from po_routing2 b where b.barcode ='"+barcode+"' order by b.fo_no asc";
		System.out.println(fosql);
		ResultSet rs = null;
		try {
			rs = Sqlhelper0.executeQuery(fosql, null);
			rs.next();
			ffo_no = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(rs != null){
		    	try {
				rs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return ffo_no;
	}
//	审理单的第一道工序
	public static int findStateFirstNo(String barcode){
		int ffo_no = 0;
		String fosql ="select a.fo_no from reject_state a where a.barcode ='"+barcode+"' order by a.fo_no asc";
		System.out.println(fosql);
		ResultSet rs = null;
		try {
			rs = Sqlhelper0.executeQuery(fosql, null);
			rs.next();
			ffo_no = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(rs != null){
		    	try {
				rs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return ffo_no;
	}
	
	public static int stateFindFo_no(int afo_no,String barcode){
//		从审理单中 查找上一道工序,并不是每道工序都出现不合格品，因此要根据审理单中的数据来。
		int fo_no =0;
//		9-1
		String fosql ="select a.fo_no from reject_state a where a.barcode = '"+barcode+"' order by a.fo_no desc";
		System.out.println(fosql);
		ResultSet fors = null;
		try {
			fors = Sqlhelper0.executeQuery(fosql, null);
			while(fors.next()){
				if(fors.getInt(1) == afo_no){
					break;
				}
			}
			fors.next();
			fo_no=fors.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			Sqlhelper0.close();
			if(fors != null){
		    	try {
				fors.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return fo_no;
	}
	
	public static void finishToSum(String barcode,int afo_no){
//最后一道工序 流程卡tosum
		int fo_no = afo_no;	//保存前一道工序的数据
		
		
		boolean cresult=BarcodeLength.barcodeLength(barcode);	//判断是否子卡
		String sql = "";
		if(cresult){
//			如果是子卡，则累加到主卡上。
//			String fbarcode = SearchFbarcode.searchFbarcode(barcode);		//主卡 条形码
			String gbarcode="";			//未用   
			
				String keysql = "select b.accept_num,b.reject_num,d.accept,d.reject,c.gbarcode,b.usedtime,d.usedtime from " +
						"po_routing2 b " +
							"left join attach_info c on c.barcode = b.barcode " +
							"left join qualitysum d on d.barcode= c.gbarcode and d.fo_no = b.fo_no " +
						"where b.barcode = '"+barcode+"' and b.fo_no = "+fo_no;
				ResultSet rs = null;
				int accept=0;
				int reject = 0;
				double usedtime = 0;
				try {
					rs = Sqlhelper0.executeQuery(keysql, null);
					rs.next();
					accept = rs.getInt(1) + rs.getInt(3);
					reject = rs.getInt(2) + rs.getInt(4);
//					9-2
					usedtime = rs.getDouble(6)+ rs.getDouble(7);
					gbarcode = rs.getString(5);
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					Sqlhelper0.close();
					if(rs != null){
				    	try {
						rs.close();
					} catch (Exception e2) {
					// TODO: handle exception
						}
				}
				}
				
				String usql = " update qualitysum e set e.accept=" +accept +",e.reject="+reject+",e.usedtime="+usedtime+
//						"(e.accept,e.reject) values ("+accept+","+reject+") " +
						"where e.barcode = '"+gbarcode+"' and e.fo_no ="+fo_no;
				try {
					Sqlhelper0.executeUpdate(usql, null);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
			
		}else{
//			主卡
				sql = "insert into qualitysum " +
						"(select a.barcode,a.fo_no,a.accept_num,a.reject_num,a.todiscard,a.tofix,a.toredo,a.tosale,a.usedtime,a.fixtime,a.distime " +
						"from po_routing2 a " +
						"where a.barcode = '"+barcode+"' and a.fo_no = "+fo_no+")";
				try {
					Sqlhelper0.executeUpdate(sql, null);
					System.out.println(sql + "succ");
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
		}
	}
	public void back1 (){
	/*	
//		9-26   工时损耗,材料损耗统计
		String sqlb = "";
		String sqlc = "";	//更新语句
		double timeloss = 0 ;
		double stuffloss = 0;
		
//		String timetype = "fixtime";	//方便后面统计到总表用 
		if(type.equals("toFix")){
			sqlb = "select a.fixtime,a.stuffloss,b.timeloss,b.materialloss from po_routing2 a " +
					"left join reject_state b on b.runnum = '"+runnum+"' " +
					"where a.barcode ='"+barcode+"' and a.fo_no ="+afo_no;
			sqlc = "update  po_routing2 a set a.fixtime = "+timeloss+", a.stuffloss = "+stuffloss+" where a.barcode = '"+barcode+"' and a.fo_no = "+afo_no;
		}else if(type.equals("toDiscard")){
			sqlb = "select a.distime,a.stuffloss,b.timeloss,b.materialloss from po_routing2 a " +
			"left join reject_state b on b.runnum = '"+runnum+"' " +
			"where a.barcode ='"+barcode+"' and a.fo_no ="+afo_no;
			
			sqlc = "update  po_routing2 a set a.distime = "+timeloss+", a.stuffloss = "+stuffloss+" where a.barcode = '"+barcode+"' and a.fo_no = "+afo_no;
			timetype = "distime";
		}
		ResultSet rsb = null;
		
		try {
			rsb= Sqlhelper0.executeQuery(sqlb, null);
			rsb.next();
			timeloss = rsb.getDouble(1) + rsb.getDouble(3);
			stuffloss = rsb.getDouble(2) + rsb.getDouble(4);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
//			Sqlhelper0.close();
			if(rsb != null){
		    	try {
				rsb.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
			}
		}
		
		try {
			Sqlhelper0.executeUpdate(sqlc, null);
			System.out.println("ok  "+ sqlc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		损耗统计 结束
		*/
		
//		boolean para = SflowJudge.sstateJudge(type, runnum, num, afo_no, barcode);		//判断是否有父卡数据,true 表示没有
//		if(!para){
		/*
		int fo_no =0;
		int ffo_no = findStateFirstNo(barcode);	//第一道工序数据
		boolean cresult=BarcodeLength.barcodeLength(barcode);
		if(cresult){
//	待矫正	
//			如果是子卡，则累加到主卡上。
//			String fbarcode = SearchFbarcode.searchFbarcode(barcode);		//主卡 条形码
			String gbarcode="";
			
			if(afo_no == 1 || afo_no== ffo_no){
//				待矫正,工序1 的数据上一步已经保存至表，应该是第一道质检的工序。
			}else{
				fo_no = stateFindFo_no(afo_no, barcode);
//				9-18   判断父卡数据是否已存
				try {
					SflowJudge.sflowJudge(barcode, fo_no);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				String keysql = "select b."+type+",d."+type+",c.gbarcode,b."+timetype+",d."+timetype+",b.stuffloss,d.stuffloss from " +
						"po_routing2 b " +
							"left join attach_info c on c.barcode = b.barcode " +
							"left join qualitysum d on d.barcode= c.gbarcode and d.fo_no = b.fo_no " +
						"where b.barcode = '"+barcode+"' and b.fo_no = "+fo_no;
				ResultSet rs = null;
				int typenum=0;
				double typetime = 0;
				double typestuff = 0 ;
				try {
					rs = Sqlhelper0.executeQuery(keysql, null);
					rs.next();
					typenum = rs.getInt(1) + rs.getInt(2);
					gbarcode = rs.getString(3);
					typetime = rs.getDouble(4) + rs.getDouble(5);
					typestuff = rs.getDouble(6) + rs.getDouble(7);
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
//					Sqlhelper0.close();
					if(rs != null){
				    	try {
						rs.close();
					} catch (Exception e2) {
					// TODO: handle exception
						}
				}
				}
				String usql = "update qualitysum e set e."+type+"="+typenum+",e."+timetype+"="+typetime+",e.stuffloss="+typestuff+
				"where e.barcode = '"+gbarcode+"' and e.fo_no ="+fo_no;
//				String usql = " update qualitysum e set (e.accept,e.reject) values ("+accept+","+reject+") " +
//						"where e.barcode = '"+gbarcode+"' and e.fo_no ="+fo_no;
				try {
					Sqlhelper0.executeUpdate(usql, null);
					System.out.println("ok  "+ usql);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
//					Sqlhelper0.close();
				}
			}
			
		}else{
//		主卡
		if(afo_no == 1 || afo_no== ffo_no){
//			待矫正,工序1 的数据上一步已经保存至表
		}else{
			fo_no = stateFindFo_no(afo_no, barcode);
			String quesql = "select a."+type+",b."+type+",a."+timetype+",b."+timetype+",a.stuffloss,b.stuffloss from po_routing2 a " +
					"left join qualitysum b on b.barcode = a.barcode and b.fo_no = a.fo_no"+
					" where a.barcode = '"+barcode+"' and a.fo_no = "+fo_no;
			
			ResultSet ars = null;
//			String opinion ="";
			int snum = 0;
			double typetime = 0;
			double typestuff = 0 ;
			String sql2="";
			try {
				ars = Sqlhelper0.executeQuery(quesql, null);
				ars.next();
//				opinion = ars.getString(1);		//废品 处理意见
				
				snum = ars.getInt(1) + ars.getInt(2);				//数量累加 
				typetime = ars.getDouble(3) + ars.getDouble(4);
				typestuff = ars.getDouble(5) + ars.getDouble(6);
//				待矫正
				sql2 ="update qualitysum e set "+type+"="+snum +",e."+timetype+"="+typetime+",e.stuffloss="+typestuff+
				"where e.barcode = '"+barcode+"' and e.fo_no ="+fo_no;
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(ars != null){
			    	try {
					ars.close();
				} catch (Exception e2) {
				// TODO: handle exception
					}
			}
			}
			try {
				Sqlhelper0.executeUpdate(sql2, null);
				System.out.println("ok  "+sql2);
//				9-18
				try {
					FflowJudge.fflowJudge(barcode, fo_no, afo_no);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
			}
		}
//		}	//if 结束 	
	}
	*/
	}
}
