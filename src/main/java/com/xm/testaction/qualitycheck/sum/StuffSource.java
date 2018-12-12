package com.xm.testaction.qualitycheck.sum;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StuffSource {
	public static void  stuffSource(ResultSet rs,CostConfirmBean bean,List<Map<String, String>> list){
		String orderid = "";
		String draid = "";
		
		orderid = bean.getOrderid();
		draid = bean.getDraid();
		
		Map< String, String> para = new HashMap<String, String>();
		String s = "";
		for(int i = 0 ,j = list.size();i<j;i++){
			para = list.get(i);
			s = para.get("orderid");
			System.out.println(i);
			System.out.println(list.get(i));
			if(s.equals(orderid)){
				if(para.get("draid").equals(draid)){
					bean.setStuff(para.get("stuff"));
					bean.setDia(para.get("dia"));
					bean.setLen(para.get("len"));
					bean.setWid(para.get("wid"));
					bean.setHei(para.get("hei"));
					bean.setIssup(para.get("islailiao"));
					bean.setRemark(para.get("remark"));
					bean.setOutasist(para.get("outasist"));
					
					list.remove(i);
					break;
				}
			}
		}
	}
}
