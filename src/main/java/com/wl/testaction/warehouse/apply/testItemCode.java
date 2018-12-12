package com.wl.testaction.warehouse.apply;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.wl.forms.ItemCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StringUtil;

public class testItemCode {
	public static void main ( String args []){
		for (int l=0,m=1000;l<m;l++){
			String warehouseId="y01";
			String itemId="";
			String itemName="测试"+l;
			String spec="ddd";
			String itemType="Y";
			String unit="";
			String poNum="5";
			String usage="";
			String memo="";
			String addApply="0";
			String applySheetid="ceshi-0404-01";
			
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			 String createTime = df.format(new Date());
			 String changeTime = df.format(new Date());
			    
			 int count=0;
			 if(addApply.equals("0")&&itemId.equals("")){
				 String itemCodeSql="select max(seq) seq from itemcode where itemtype='"+itemType+"'";
				 ItemCode itemcode=new ItemCode();
				 try{
					 itemcode=Sqlhelper.exeQueryBean(itemCodeSql, null, ItemCode.class);
				 }catch(Exception e){
					 e.printStackTrace();
				 }
				 count=StringUtil.isNullOrEmpty(itemcode.getSeq())?0:itemcode.getSeq(); 
//				 xiem	 如果count 为0 ，为了保险，查询该类型的数据总数
				 if(count ==0){
					 System.out.println("itemCodeSql  "+itemCodeSql);
					 String sumSql = "select count(1) from " +
					 		"(select t.seq,rownum rn from itemcode t where itemtype='"+itemType+"' ) a " +
					 		"where a.rn =1";
					 int temp = 0;
					 try {
						temp = Sqlhelper.exeQueryCountNum(sumSql, null);
						System.out.println("sql "+sumSql);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
//				如果结果不为0，则提示报错。
					if(temp!=0){
						String json="{\"result\":\"操作失败！\",\"status\":0}";
//						return;
					}
				
				 }
				 count++;
				 String stringcount=Integer.toString(count);
				 String result="";
				 for(int i=0,n=6-stringcount.length();i<n;i++){
					
						result += "0";
				 }
				 itemId="ceshi"+itemType+result+stringcount;
				 //存
				 String itemSql="insert into itemcode(seq,itemid,itemname,itemtype) values('"+count+"','"+itemId+"','"+itemName+"','"+itemType+"')";
				 try{
					 Sqlhelper.executeUpdate(itemSql, null);
				 }catch(Exception e){
					 e.printStackTrace();
				 }
				 
				 Stockcl.Stockin(itemId,itemName,spec,itemType,warehouseId,"",0,0,unit);
			 }
			 
			
			String createPerson="ces";
			String changePerson="ces";
			String applySql="insert into POAPPLYDETL (applysheetid,itemid,itemname,spec,unit,itemtype," +
					"usage,ponum,memo,createperson,createtime,changeperson,changetime) values('"+applySheetid+"'," +
						"'"+itemId+"','"+itemName+"','"+spec+"','"+unit+"','"+itemType+"','"+usage+"'," +
						"'"+poNum+"','"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
						"'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
			
			
			try{
				Sqlhelper.executeUpdate(applySql, null);
				
				String json="{\"result\":\"操作成功！\",\"status\":1}";
//				out.append(json).flush();
			}catch(Exception e){
				String json="{\"result\":\"操作失败！\",\"status\":0}";
//				out.append(json).flush();
				e.printStackTrace();
			}
		}
	}
}
