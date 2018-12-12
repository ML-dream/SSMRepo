package job;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.wl.tools.Sqlhelper0;

public class EmpRejectSum {
	public static void empRejectSum(ArrayList<String> stafflist,String btime,String etime){
//		
//		统计各人员的不合格具体情况
		int fixnum = 0;
		int discardnum = 0;
		double fixtime = 0;		//返修损耗工时
		double discardtime = 0;
		String barcode = "";
		int fo_no= 0;
		
		String workid ="";
		String opinion = "";
		
		String sqlb = "";	//储存语句
		
		String bar = "";
		int fo = 0;
		String opi = "";	//临时
		
		String sqlHelp="";
		for(int a=0,b=stafflist.size();a<b;a++){
			workid = stafflist.get(a);
			
			fixnum = 0;
			discardnum = 0;
			fixtime = 0;
			discardtime = 0;
			
			barcode = "";
			fo_no= 0;
			opinion = "";
			bar = "";
			opi = "";
			
			String sqla = "select a.barCode,a.fo_no,nvl(b.opinion,'toFix') opinion,nvl(b.rejectnum,0) rejectnum," +
					"nvl(b.timeloss,0) timeloss,b.runnum from po_routing2 a " +
					" left join reject_state b on b.barcode = a.barcode and b.fo_no = a.fo_no and b.rejecttype=1" +
					" where a.workerid = '"+workid+"' " +
					" and a.checkdate between to_date('"+btime+"','yyyy-mm-dd hh24:mi:ss') " +
					" and to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss') order by to_number(a.barcode)";
			
			ResultSet rsa = null;
			try {
//				初始化参数，第一条数据
				rsa = Sqlhelper0.executeQuery(sqla, null);
				
			if(	rsa.next()){
				barcode = rsa.getString(1);
				fo_no = rsa.getInt(2);
				opinion = rsa.getString(3);
				fo=fo_no;
				
				if(opinion.equals("toFix")){
					fixnum += rsa.getInt(4);
					fixtime += rsa.getDouble(5);
				}else if(opinion.equals("toDiscard")){
					discardnum += rsa.getInt(4);
					discardtime += rsa.getDouble(5);
				}
				sqlb = "insert into empsumtem2 (barcode, fo_no, fixnum, discardnum, fixtime, discardtime, btime, etime) values " +
					"('"+barcode+"',"+ fo+","+ fixnum +","+ discardnum+","+ fixtime+","+ discardtime+
					",to_date('"+btime+"','yyyy-mm-dd hh24:mi:ss'),to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss'));";
				sqlHelp +=sqlb;
				
//				对接下来的数据进行处理
				while(rsa.next()){
//					先判断条形码
					bar = rsa.getString(1);
					opi = rsa.getString(3);
					
					if(bar.equals(barcode)){
//						同一个条码号的多条数据，再判断工序
						fo= rsa.getInt(2);
						if(fo == fo_no){
//							最后判断 不合格类型，返修or 报废
							if(opi.equals("toFix")){
								fixnum += rsa.getInt(4);
								fixtime += rsa.getDouble(5);
							}else if(opi.equals("toDiscard")){
								discardnum += rsa.getInt(4);
								discardtime += rsa.getDouble(5);
							}
							
						}else{
//							如果是不同工序
							sqlHelp +=sqlb;
							
							if(opi.equals("toFix")){
								fixnum = rsa.getInt(4);
								fixtime = rsa.getDouble(5);
							}else if(opi.equals("toDiscard")){
								discardnum = rsa.getInt(4);
								discardtime = rsa.getDouble(5);
							}
						}
					}else{
//						不同条码,数据都重新累加
						fixnum = 0;
						discardnum = 0;
						fixtime = 0;
						discardtime = 0;
						
						barcode = bar;
						
						fo= rsa.getInt(2);
						
						if(fo == fo_no){
							if(opi.equals("toFix")){
								fixnum = rsa.getInt(4);
								fixtime = rsa.getDouble(5);
							}else if(opi.equals("toDiscard")){
								discardnum = rsa.getInt(4);
								discardtime = rsa.getDouble(5);
							}
						}else{
//							sqlHelp +=sqlb;
							if(opi.equals("toFix")){
								fixnum = rsa.getInt(4);
								fixtime = rsa.getDouble(5);
							}else if(opi.equals("toDiscard")){
								discardnum = rsa.getInt(4);
								discardtime = rsa.getDouble(5);
							}
						}
//						串sql 语句
						
					}
					sqlb = "insert into empsumtem2 (barcode, fo_no, fixnum, discardnum, fixtime, discardtime, btime, etime) values " +
					"('"+barcode+"',"+ fo+","+ fixnum +","+ discardnum+","+ fixtime+","+ discardtime+
					",to_date('"+btime+"','yyyy-mm-dd hh24:mi:ss'),to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss'));";
					sqlHelp +=sqlb;
					fo_no = fo;
				}
			  }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(rsa != null){
						rsa.close();
					}
					rsa = null;
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
		}
//		把sql 语句串成 储存过程 
		String	sqlc = "begin  "+sqlHelp + "   end;";
		try {
			Sqlhelper0.executeUpdate(sqlc, null);
			System.out.println("ok  "+sqlc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
