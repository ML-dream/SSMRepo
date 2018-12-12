package com.wl.tools;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wl.forms.SheetId;



public class CheckSheetId {
	//入库单号
	public SheetId getSheetid(){
		int countnum=0;
		String s1="CJ-001-";
		String sql="select max(seq) from checkin_sheetid";	
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from checkin_sheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
	
	}
	
	public SheetId getCustomerCheckinSheetid(){
		int countnum=0;
		String s1="CA-001-";
		String sql="select max(seq) from customercheckin_sheetid";	
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from customercheckin_sheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
	}
	
	//出库单号
	public SheetId getCheckoutsheetid(){
		int countnum=0;
		String s1="CC-002-";
		String sql="select max(seq) from checkout_sheetid";	
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from checkout_sheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
		
	}
	
	//采购订货
	public SheetId getPosheetid(){
		int countnum=0;
		String s1="CD-000-";
		String sql="select max(seq) from po_sheetid";	
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from po_sheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
		
	}
	
	
	//采购收货
	public SheetId getPrsheetid(){
		int countnum=0;
		String s1="CS-001-";
		String sql="select max(seq) from pr_sheetid";	
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from pr_sheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
	
	}
	
	//领料单号
	public SheetId getLlsheetid(){
		int countnum=0;
		String s1="LL-001-";
		String sql="select max(seq) from ll_sheetid";	
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from ll_sheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
		
	}
	//退料单号
	public SheetId getRmsheetid(){
		int countnum=0;
		String s1="TL-002-";
		String sql="select max(seq) from rm_sheetid";	
		
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from rm_sheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
		
	}
	
	//付款
	public SheetId getPaysheetid(){
		int countnum=0;
		String s1="CF-001-";
		String sql="select max(seq) from paysheetid";	
		
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from paysheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;

	}
	
	//退货
public SheetId getResheetid(){
		
	int countnum=0;
	String s1="CT-001-";
	String sql="select max(seq) from resheetid";	
	
	try{
		countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
		
	}catch(Exception e){
		e.printStackTrace();
	}
	String sql1="select * from resheetid where seq="+countnum+" order by seq";
	SheetId sheetid=this.getSheetId(countnum, s1, sql1);
	return sheetid;
	
	}
//盘点
	public SheetId getCountSheetId(){
		int countnum=0;
		String s1="WH-001-";
		String sql="select max(seq) from countsheetid";	
		
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from countsheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
	}
	
	//采购申请
	public SheetId getApplySheetId(){
		int countnum=0;
		String s1="CS-001-";
		String sql="select max(seq) from applysheetid";	
		
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from applysheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
		
	}
	
	//客户退货单号
	public SheetId getCustomerReturnSheetid(){
		int countnum=0;
		String s1="CR-001-";
		String sql="select max(seq) from customerreturnsheetid";	
		
		try{
			countnum=StringUtil.isNullOrEmpty(Sqlhelper.exeQueryString(sql, null))?0:Integer.parseInt(Sqlhelper.exeQueryString(sql, null));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql1="select * from customerreturnsheetid where seq="+countnum+" order by seq";
		SheetId sheetid=this.getSheetId(countnum, s1, sql1);
		return sheetid;
		
	}
	
	//
	public SheetId getSheetId(int countnum,String s1,String sql1){
		String s="";
		int seq=0;
		int a=0;
		String id="";
		SheetId sheetid=new SheetId();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String createTime = df.format(new Date());
		String s2=s1+createTime;
		//String sql="select count(*) from resheetid";
		//System.out.println("s2="+s2);
		SheetId sheetId=new SheetId();
		String result="";
		try{		
			if(countnum!=0){
				sheetId=Sqlhelper.exeQueryBean(sql1, null, SheetId.class);
				seq=sheetId.getSeq();
				id=sheetId.getId();
				if((s2+"-"+id).equals(sheetId.getSheetid())){
					++seq;
					a=Integer.parseInt(id);
					++a;
					id=Integer.toString(a);
					for(int i=0,n=3-id.length();i<n;i++)
					{
						result += "0";
					}
					id=result+id;
					s=s2+"-"+id;
				}else
				{
					++seq;
					id="1";
					for(int i=0,n=3-id.length();i<n;i++)
					{
						result += "0";
					}
					id=result+id;
					s=s2+"-"+id;
				}
			}else{
				seq=1;
				id="1";
				for(int i=0,n=3-id.length();i<n;i++)
				{
					result += "0";
				}
				id=result+id;
				s=s2+"-"+id;
			}
			sheetid.setSeq(seq);
			sheetid.setId(id);
			sheetid.setSheetid(s);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{

		}
		return sheetid;
	}
}