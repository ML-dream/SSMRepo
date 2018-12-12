package com.xm.testaction.qualitycheck.sum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;

public class CostCaculate {
//	成本核算的各种计算
	public static void stuffCaculate (ResultSet rsa,SeeCostConfirmBean bean){
//		计算单件材料费
		double price = 0 ;	//材料单价
		double density = 0;
		double dia = 0;
		double len = 0 ;
		double wid = 0 ;
		double hei = 0 ;
		
//		double vol = 0;
		double stuff = 0 ;
		
		String islailiao = "";
		try {
			islailiao = rsa.getString(25);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			price = rsa.getDouble(22);	
			density = rsa.getDouble(23);
			dia = rsa.getDouble(15);
			len = rsa.getDouble(16);
			wid = rsa.getDouble(17);
			hei = rsa.getDouble(18);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(islailiao.equals("N")){
//			是否来料,否
		
//		板料
			if(dia == 0){
				stuff = len*wid*hei/1000*density/1000*price;
			}else{
				stuff = 3.14*((dia/2/10)*(dia/2/10))*len/10*density/1000*price;
			}
		}else{
			stuff = 0;
		}
//		以上是焊件第一个部分的材料计算
		double temp = 0;	//各部分焊件的材料价格
		String sqla = "select a.price,a.density,t.islailiao,t.dia,t.len,t.wid,t.hei from costinput t " +
				" left join stuffprice a on a.stuffid = t.stuff " +
				"where t.orderid = ? and t.draid = ? and t.stufflevel='2' ";
		String [] params ={bean.getOrderid(),bean.getDraid()}; 
		List<StuffCaculateBean> lista = new ArrayList<StuffCaculateBean>();
//		List <SqlhelperTest> lista = new ArrayList<SqlhelperTest>();
		try {
//			lista = Sqlhelper.exeQueryList(sqla, params, SqlhelperTest.class);
			lista = Sqlhelper.exeQueryList(sqla, params, StuffCaculateBean.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		for(int j=0,k=lista.size();j<k;j++){
			StuffCaculateBean beana = lista.get(j);
//			StuffCaculateBean beana = lista.get(j);
			temp= helpStuff(beana);
			stuff += temp;
		}
		
		String  s = douToStr(stuff);
		bean.setStucost(s);
	}
	
	
	public static double helpStuff(StuffCaculateBean bean){
//		辅助计算单价材料费
		double stuff= 0;
		double price = bean.getPrice() ;	//材料单价
		double density = bean.getDensity();
		double dia = bean.getDia();
		double len = bean.getLen();
		double wid = bean.getWid();
		double hei = bean.getHei();
		String islailiao = bean.getIslailiao();
		
		
		if(islailiao.equals("N")){
//			是否来料,否
		
//		板料
			if(dia == 0){
				stuff = len*wid*hei/1000*density/1000*price;
			}else{
				stuff = 3.14*((dia/2/10)*(dia/2/10))*len/10*density/1000*price;
			}
		}else{
			stuff = 0;
		}
		return stuff;
	}
	
	public static String inputStuff(CostConfirmBean bean){
//		辅助计算单价材料费
		double stuff= 0;
		double price = bean.getStuffpri() ;	//材料单价
		double density = bean.getDensity();
		double dia = 0;
		double len = 0;
		double wid = 0;
		double hei =0;
		try {
			dia =Double.parseDouble(bean.getDia());
			len =Double.parseDouble(bean.getLen());
			wid = Double.parseDouble(bean.getWid());
			hei =Double.parseDouble(bean.getHei());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String islailiao = bean.getIssup();
		
		
		if(islailiao.equals("N")){
//			是否来料,否
		
//		板料
			if(dia == 0){
				stuff = len*wid*hei/1000*density/1000*price;
			}else{
				stuff = 3.14*((dia/2/10)*(dia/2/10))*len/10*density/1000*price;
			}
		}else{
			stuff = 0;
		}
		String s = "";
		s= douToStr(stuff);
		return s;
	}
	
	
	public static void timec (String bdate,String edate){
//		人工成本视图
		String sqla = "create or replace view  alltimecview " +
				"as   " +
				"select a.order_id,b.drawingid,sum((nvl(b.basetime,0) + nvl(c.rewardstime,0))*price) timec " +		//这地方应该是basetime * 工时单价 
				" from orders a " +
				" left join rewardstemp b on b.order_id = a.order_id" +
				" left join rewardstime c on c.barcode = b.barcode and c.fo_no = b.fo_no" +		//工时view
				" where a.order_date between  to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') " +	//奖惩工时
				" and to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss')" +
				" group by a.order_id,b.drawingid ";
		try {
			System.out.println(sqla);
			Sqlhelper0.executeUpdate(sqla, null);
			System.out.println("ok"  + sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void alltimec(SeeCostConfirmBean bean,double focost){
//		合计人工成本，包括包装跟运输 以及外协
		double num = Double.parseDouble(bean.getPronum());
		double trans = Double.parseDouble(bean.getTrans());
		double pack = Double.parseDouble(bean.getPack());
		double outasist = Double.parseDouble(bean.getOutasist());
		
		double alltimec = (trans + pack )*num +focost + outasist;
		String s = douToStr(alltimec);
		bean.setAlltimec(s);
	}
	public static void pmanuc (ResultSet rsa,SeeCostConfirmBean bean,double para){
//		单件制造成本
		double pmanuc = 0;
		double stucost = Double.parseDouble(bean.getStucost());
//		首先是查找人工成本
		double alltimec = 0;
		double num = 0 ;
		alltimec = Double.parseDouble(bean.getAlltimec());		//总人工成本
		num = Double.parseDouble(bean.getPronum());
//		try {
//			timec = rsa.getDouble(24);	//每件产品的总人工成本
//			num = rsa.getInt(9);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		外协成本
//		double outasist = Double.parseDouble(bean.getOutasist());
		
		try {
			pmanuc = alltimec/(num*para)+ stucost;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		计算
		String s = douToStr(pmanuc);
		bean.setPmanuc(s);
	}
	/**
	 * 单间总制造成本
	 * @param rsa
	 * @param bean
	 */
	public static void allmanuc (ResultSet rsa,SeeCostConfirmBean bean){
		double pmanuc = Double.parseDouble(bean.getPmanuc());
		double num = Double.parseDouble(bean.getPronum());
		
		double allc = pmanuc * num;
		String s = douToStr(allc);
		bean.setAllmanuc(s);
	}
	
	public static void ptaxc (SeeCostConfirmBean bean,Map<String, Double> map){
//		单件税费
		double pmanuc = Double.parseDouble(bean.getPmanuc());	//单件制造成本
		double stucost = Double.parseDouble(bean.getStucost());	//单件材料费
		double taxc = map.get("taxc");	//税率
		double taxc2 = map.get("taxc2");	//计算参数 2
		
		double ptaxc =0;
		ptaxc = (pmanuc-stucost)/taxc2*taxc;
		String s = douToStr(ptaxc);
		bean.setPtaxc(s);
		
	}
	
	public static void midc (SeeCostConfirmBean bean,Map<String, Double> map){
//		期间费用
		double pmanuc = Double.parseDouble(bean.getPmanuc());	//单件制造成本
		double midc = map.get("midc");	//参数
		double result = 0 ;
		 result = pmanuc * midc;
		 String s = "";
		 s= douToStr(result);
		 bean.setMidc(s);
	}
	
	public static void pcost (SeeCostConfirmBean bean,Map<String, Double> map){
//		单件总成本
		double pmanuc = Double.parseDouble(bean.getPmanuc());	//单件制造成本
		double ptaxc = Double.parseDouble(bean.getPtaxc());	//单件税费成本
		double midc = Double.parseDouble(bean.getMidc());	//单件期间成本
		
		double pcost = 0;
		pcost = pmanuc + ptaxc + midc;
		String s = "";
		s = douToStr(pcost);
		bean.setPcost(s);
	}
	
	public static void acost (SeeCostConfirmBean bean,ResultSet rsa ){
//		总成本
		
		int num = 0;
		try {
			num = rsa.getInt(9);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//数量
		double pcost = 0;
		pcost = Double.parseDouble(bean.getPcost());
		
		double acost= 0 ;
		acost = pcost * num ;
		String s = "";
	    s = douToStr(acost);
	    bean.setAcost(s);
		
	}
	
	public static void pconfirm(SeeCostConfirmBean bean,Map<String, Double> map){
//		核算单价
		double realc = 0;
		realc =Double.parseDouble(bean.getRealc());
		double para = map.get("confirmc");
		double confirmc = 0;
		
		confirmc = realc * para ;
		String s = "";
		s = douToStr(confirmc);
		bean.setPconfirm(s);
	}
	
	public static void sale(SeeCostConfirmBean bean,ResultSet rsa){
//		销售额
		
		int num = 0;
		try {
			num = rsa.getInt(9);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double realc =Double.parseDouble(bean.getRealc()); 
		double sale = realc * num ;
		String s = "";
		s = douToStr(sale);
		bean.setSale(s);
	}
	
	public static void profit (SeeCostConfirmBean bean){
//		总利润
		double sale = Double.parseDouble(bean.getSale());
		double acost = Double.parseDouble(bean.getAcost());
		
		double profit = sale - acost;
		String s = douToStr(profit);
		bean.setProfit(s);
	}
	
	public static void stuffc(SeeCostConfirmBean bean){
//		材料费
		double pstuff = Double.parseDouble(bean.getStucost());
		double num = Double.parseDouble(bean.getPronum());
		double stuffc = pstuff * num ;
		String s = "";
		s= douToStr(stuffc);
		bean.setStuffc(s);
	}
	
	public static void cconfirm (SeeCostConfirmBean bean){
//		核算成本
		double pmanuc = Double.parseDouble(bean.getPmanuc());
		double num = Double.parseDouble(bean.getPronum());
		double cconfirm = num * pmanuc;
		String s = "";
		s = douToStr(cconfirm);
		bean.setCconfirm(s);
	}
	public static String douToStr(double para ){
		String s = "";
		DecimalFormat format  = new DecimalFormat("0.00");	//这样为保持2位
		s = format.format(para);
		return s;
	}

}
