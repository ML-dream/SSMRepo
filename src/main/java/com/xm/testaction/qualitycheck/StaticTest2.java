package com.xm.testaction.qualitycheck;

import java.util.ArrayList;
import java.util.List;

import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.statejudge.BeanWeldDiscard;

public class StaticTest2 {
	public static void main ( String [] args){
		String runnum= "Q20161218104";
		String reject_num = "0";
		try {
			String sqla = "select t.product_id productId,t.order_id orderId,t.issue_num issueNum,c.partsplanid from order_detail t " +
					" left join (select a.order_id,a.product_id,a.issue_num,a.producttype from order_detail a) b on b.order_id= t.order_id and b.product_id= t.fproduct_id and b.issue_num= t.issue_num" +
					" left join partsplan c on c.orderid = t.order_id and c.productid= t.product_id and c.issuenum=t.issue_num " +
					"where t.fproduct_id in " +
					"(select d.drawingid from po_router d left join reject_state e on e.barcode=d.barcode where e.runnum='"+runnum+"') " +
					"and b.producttype='L' order by productId";	//查询该件是否为焊接件父件,获取相关信息
			List <BeanWeldDiscard> lista = new ArrayList<BeanWeldDiscard>();
			try {
				lista = Sqlhelper.exeQueryList(sqla, null, BeanWeldDiscard.class);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String barcode = "";	//报废子件的条码号
			
			String productId ="";
			String orderId = "";
			String issueNum = "";
			String partsplanid= "";
			String state = "0";
			
			int len = lista.size();
			if(len==0){
				return;
			}else{
				for(int j=0;j<len;j++){
					barcode = ToBarcode.toWeldBarcode();
					BeanWeldDiscard bean = null;
					bean = lista.get(j);
					productId = bean.getProductId();
					orderId = bean.getOrderId();
					issueNum = bean.getIssueNum();
					partsplanid = bean.getPartsplanid();
					
					String sqlb = "insert into todiscard (barcode, runnum, state, rejectnum, drawingid, order_id, product_id, issue_num, partsplanid) " +
							"values(?,?,?,  ?,?,?  ,?,?,?)";	//保存到报废表
					String param []={barcode,runnum,state,reject_num,productId,orderId,productId,issueNum,partsplanid};
					try {
						Sqlhelper.executeUpdate(sqlb, param);
						System.out.println("ok  "+sqlb);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
