package com.xm.testaction.qualitycheck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.jspsmart.upload.Request;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.sum.UpToProcessPlan;

public class UncheckFoHandle {
//	public static void main (String [] args){
//		uncheckFoHandle("2016110100000021");
//	}
	public static void uncheckFoHandle(String barcode,String checker){
		String partnum = "" ;	//投入量，如果第一道工序未检，则默认合格 投入量
		boolean isson = BarcodeLength.barcodeLength(barcode);	//是否子卡
		boolean isdiscard = BarcodeLength.isDiscard(barcode);	//是否报废件
		String sqla = "";	//查工艺表数据
		
//		这个list用来储存未质检工序号，上报
		List< String > upList = new ArrayList<String>();
		
		if(!isson || isdiscard){
//			主卡 或者报废件
			sqla = "select to_number(b.fo_no) fo_no,a.partnum,round(b.rated_time,2) rated_time from partsplan a " +
					"left join fo_detail b on b.product_id = a.productid and b.issue_num = a.issuenum and b.processissue_num = a.processissuenum  and b.isinuse='1' " +
					"where a.codeid= '"+barcode+"' " +
					"order by to_number(b.fo_no)";
		}else{
			sqla = "select to_number(a.fo_no) fo_no,b.partnum,round(a.rated_time,2) rated_time from fo_detail a " +
					" left join partsplan b on b.productid = a.product_id and b.issuenum = a.issue_num and b.processissuenum = a.processissue_num" +
					" left join attach_info c on c.gbarcode = b.codeid" +
					" where c.barcode= '"+barcode+"' and a.isinuse='1' " +
					" order by to_number(a.fo_no)";
		}
		
		List<Map<String, String>> lista = new ArrayList<Map<String, String>>();	//工艺表 工序
		ResultSet rsa = null;
		try {
			System.out.println(sqla);
			rsa = Sqlhelper0.executeQuery(sqla, null);
			System.out.println("ok " + sqla);
			String fo= "";
			String ratedtime = "";
			while(rsa.next()){
				Map<String, String> mapa = new HashMap<String, String>();
				fo = rsa.getString(1);
				partnum = rsa.getString(2);
				ratedtime = rsa.getString(3);
				mapa.put("fo", fo);
				mapa.put("ratedtime", ratedtime);
				lista.add(mapa);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa != null){
				try {
					rsa.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		String sqlb = "select a.fo_no,a.accept_num from po_routing2 a where a.barcode = '"+barcode+"' order by a.fo_no";	//该条码在流程卡中的工序
		if(isson && !isdiscard){
			sqlb= "select a.fo_no,a.accept_num,nvl(b.rejectnum,0) rejnum from po_routing2 a " +
					"left join attach_info c on c.barcode = a.barcode " +
					"left join reject_state b on c.runnum = b.runnum "+
//					"left join reject_info b on b.barcode = c.gbarcode and b.fo_no = a.fo_no " +
					"where a.barcode = '"+barcode+"' order by a.fo_no";
		}
		ResultSet rsb = null;
		
		String afo = ""; //工艺表工序
		String sqlc = "begin ";	//执行数据插入
		try {
			System.out.println(sqlb);
			rsb = Sqlhelper0.executeQuery(sqlb, null);
			System.out.println("ok " + sqlb);
			String fo= "";
			
			String ratedtime = "";	//额定工时
			String acceptnum = partnum;
//			第一道工序是否质检,如果连续多道工序未检
			Map<String, String> mapb = new HashMap<String, String>();
//			int j =0;	// while 循环结束后，m的值
			while(rsb.next()){	//流程卡中的工序
				fo = rsb.getString(1);
				if(isson && !isdiscard){
//					如果是子卡，而且不是报废件，不需要把 返工工序前的数据补充上来，由于返工件的返工工序一定会有记录，但不一定有质检数据
					for(int m=0,n = lista.size();m<n;m++){
						mapb = lista.get(m);
						afo = mapb.get("fo");
						if(afo.equals(fo)){
							for(int x =0;x<m;x++){
//								把已经保存的工序拿掉
								lista.remove(0);
							}
							break;
						}
					}
				}
//				以上，是拿掉了返工工序前的数据
				for(int m=0,n = lista.size();m<n;m++){
					mapb = lista.get(m);
					afo = mapb.get("fo");
					ratedtime = mapb.get("ratedtime");
					if(!(afo.equals(fo))){
//						int upFo = Integer.parseInt(afo);	//上报工序计划
//						UpToProcessPlan.upToProcessPlan(barcode, upFo);
//						sqlc += "insert into po_routing2 (barcode,fo_no,rated_time,accept_num) values ('"+barcode+"','"+afo+"','"+ratedtime+"','"+acceptnum+"');";
						sqlc += "insert into po_routing2 (barcode,fo_no,rated_time,accept_num,checker) values ('"+barcode+"','"+afo+"','"+ratedtime+"','"+acceptnum+"','"+checker+"');";
						
						upList.add(afo);		//记录未质检工序
					}else{
						for(int x =0;x<=m;x++){
//							把已经保存的工序拿掉
							lista.remove(0);
						}
						break;
					}
				}
//				如果，返工件的返工工序未检，但是，流程卡里有记录，因此上面的遍历会跳过这道工序
				acceptnum = rsb.getString(2);
				if(isson && acceptnum ==null){
					acceptnum = rsb.getString(3);
					sqlc += "update po_routing2 t set t.accept_num = '"+acceptnum+"',t.rated_time='"+ratedtime+"',t.checker='"+checker+"' where t.barcode ='"+barcode+"' and t.fo_no ='"+fo+"' ;";
					upList.add(afo);		//记录未质检工序
				}
			}
//			上面是遍历流程卡的工序
//			把工艺里剩余的工序保存到流程卡
			for(int m=0,n=lista.size();m<n;m++){
				mapb = lista.get(m);
				afo = mapb.get("fo");
				ratedtime = mapb.get("ratedtime");
				sqlc += "insert into po_routing2 (barcode,fo_no,rated_time,accept_num,checker) values ('"+barcode+"','"+afo+"','"+ratedtime+"','"+acceptnum+"','"+checker+"');";
				upList.add(afo);		//记录未质检工序
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsb != null){
				try {
					rsb.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		sqlc += " end;";
			try {
				System.out.println(sqlc);
				Sqlhelper0.executeUpdate(sqlc, null);
				System.out.println("ok  "+sqlc);

			} catch (Exception e) {
				// TODO: handle exception
			}
		
//		查工艺表里的工序
//			存在未质检工序，	工序上报  未检工序也上报
		for (int x=0,y = upList.size(); x<y; x++){
			int temp = Integer.parseInt(upList.get(x));
			UpToProcessPlan.upToProcessPlan(barcode,temp);
			}
		try {
			int lastfo = Integer.parseInt(afo);
			UpToProcessPlan.lastFoUp(barcode, lastfo);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		最后一道工序的处理
		
//		查流程卡的工序，合格数
//		遍历工序
		
	}
}
