package com.wl.testaction.warehouse.orderstatistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.sum.AidCostConfirm;
import com.xm.testaction.qualitycheck.sum.CostCaculate;
import com.xm.testaction.qualitycheck.sum.CostConfirmView;
import com.xm.testaction.qualitycheck.sum.RewardsView;
import com.xm.testaction.qualitycheck.sum.SeeCostConfirmBean;

public class OrderCostView {
	public static void ordersCostView (String bdate,String edate){
//		当前客户下的所有订单成本参数视图

//		更新工时视图数据
		RewardsView.rewardsView(bdate, edate);
//		创建 视图
		CostCaculate.timec(bdate, edate);	//人工成本
		CostConfirmView.costConfirmView(bdate, edate);
	}
	
	public static void oneOrderDetail(String orderSql){
//		一个订单下所有零件的成本计算
//		清空 customerorders 表
		String trusql = "truncate table customerorders";
		try {
			
			Sqlhelper.executeUpdate(trusql, null);
			System.out.println("ok  "+ trusql);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		查询各种计算参数
		String sqla = "select t.paid,t.pavalue from costpara t ";
		HashMap<String, Double> mapa = new HashMap<String, Double>();
		ResultSet rsa = null;
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			String key = "";
			double value = 0;
			
			while(rsa.next()){
				key = rsa.getString(1);
				value = rsa.getDouble(2);
				mapa.put(key, value);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa != null){
				try {
					rsa.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
//		获取计算值
		String  sqlb = "select customer, companyname, order_id, order_date, endtime, product_id, drawingid, product_name, purduct_num, stuff, roughsize, nvl(islailiao,1),stuffname,stuffpri" +
		",nvl(dia,0),nvl(len,1),nvl(wid,1),nvl(hei,1),nvl(pack,0),nvl(trans,0),nvl(realc,0),price,density,timec,lailiao,nvl(outasist,0)" +
		" from " +
		" (select t.*,m.stuff,m.dia,m.len,m.wid,m.hei,m.pack,m.trans,m.realc,n.price,n.density dens,m.islailiao lailiao,rownum rn,m.outasist from costconfirmview t" +
		" left join costinput m on m.orderid = t.order_id and m.draid = t.product_id and m.stufflevel = '1' " +
		" left join stuffprice n on m.stuff = n.stuffid "+
		"where t.order_id in ("+orderSql+"))" ;
		
		ResultSet rsb = null;
//		ArrayList<SeeCostConfirmBean> waitList = new ArrayList<SeeCostConfirmBean>();
		String issup = "是";
		String islailiao = "";
//		String roughsize = "";
//		
		
		double focost = 0;	//工序成本
		try {
			rsb = Sqlhelper0.executeQuery(sqlb, null);		//详细数据
			
			while (rsb.next()){
				SeeCostConfirmBean bean = new SeeCostConfirmBean();
				
				bean.setCustomer(rsb.getString(2));
				bean.setOrderid(rsb.getString(3));
				bean.setRectime(rsb.getString(4));
				bean.setDeltime(rsb.getString(5));
				
				bean.setProductId(rsb.getString(6));
				bean.setDraid(rsb.getString(7));
				bean.setProname(rsb.getString(8));
				bean.setPronum(rsb.getString(9));
				bean.setStuff(rsb.getString(10));	//牌号，13为材料名
				
				islailiao = rsb.getString(25);
				
				issup= AidCostConfirm.isSup(islailiao);
				bean.setIssup(issup);
				
				bean.setDia(rsb.getString(15));
				bean.setLen(rsb.getString(16));
				bean.setWid(rsb.getString(17));
				bean.setHei(rsb.getString(18));
				
				bean.setPack(rsb.getString(19));
				bean.setTrans(rsb.getString(20));
				bean.setRealc(rsb.getString(21));
				
				focost = rsb.getDouble(24);
				bean.setOutasist(rsb.getString(26));	//外协成本
				try {
					CostCaculate.alltimec(bean, focost);	//合计人工成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try{
					CostCaculate.stuffCaculate(rsb, bean);	//单件材料费
				}catch (Exception e) {
					// TODO: handle exception
				}
				double para = mapa.get("manuc");	//系数
				try {
					CostCaculate.pmanuc(rsb, bean, para);	//单件制造成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.ptaxc(bean, mapa);	//单件税费
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.midc(bean, mapa);	//期间费用
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.pcost(bean, mapa);	//单件总成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.acost(bean, rsb);	//总成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.pconfirm(bean, mapa);	//核算单价
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.sale(bean, rsb);	//销售额
				} catch (Exception e) {
					// TODO: handle exception
				}
//				try {
//					CostCaculate.profit(bean);		//利润
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
				try {
					CostCaculate.stuffc(bean);		//总材料费
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.cconfirm(bean);	//核算总成本
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					CostCaculate.allmanuc(rsa, bean);	//总制造成本
				} catch (Exception e) {
					// TODO: handle exception
				}
//				把每个产品的数据保存到CUSTOMERORDERS 表
				String sqlc = "insert into CUSTOMERORDERS (ORDERID, PRODUCTID, MANUC, ALLC) values (?,?,?,?)";
				String params [] ={bean.getOrderid(),bean.getProductId(),bean.getAllmanuc(),bean.getAcost()};
				try {
					System.out.println(sqlc);
					Sqlhelper.executeUpdate(sqlc, params);
					System.out.println("ok  " +sqlc);
				} catch (Exception e) {
					// TODO: handle exception
				}
//				System.out.println(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(rsb!=null){
					rsb.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void oneOrderCost(){
//		一个订单的总成本
		
	}
}
