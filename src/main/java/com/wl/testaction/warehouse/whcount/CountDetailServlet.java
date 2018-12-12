package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.forms.WarehouseCountStatistics;
import com.wl.tools.Sqlhelper;

public class CountDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CountDetailServlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String countSheetid=request.getParameter("countSheetid");
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String itemId=(String) datamap.get("itemId");
		String SitemId=(String) datamap.get("SitemId");
		String itemName=(String) datamap.get("itemName");
		String issueNum=(String) datamap.get("issueNum");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String stockId=(String) datamap.get("stockId");
		String stockNum=(String) datamap.get("stockNum");
		String countNum=(String) datamap.get("countNum");
		String ProfitLossNum=(String) datamap.get("profitLossNum");
		String unitPrice=(String) datamap.get("unitPrice");
		String ProfitLoss=(String) datamap.get("profitLoss");
		String memo=(String) datamap.get("memo");
		String flag= (String) datamap.get("flag");
		String warehouseId=request.getParameter("warehouseId");
		
		HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());
		String changeTime = df.format(new Date());
		String countSql="";
		
//		int year=0;
//		int month=0;
//		int date=0;
//		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
//		year = c.get(Calendar.YEAR); 
//		month = c.get(Calendar.MONTH);//当前月份+1，如当前为9月，则month为8
//		date = c.get(Calendar.DATE); 
//		String time= "" +year+"-"+month+"-"+date+"";
//		String countSql="";
//		String Sql="";
//		//查询上一期时间
//		String dateSql="select countdate from whcountstastic order by countdate";
//		List<WarehouseCountStatistics>dateList=new ArrayList<WarehouseCountStatistics>();
//		try{
//			dateList=Sqlhelper.exeQueryList(dateSql, null, WarehouseCountStatistics.class);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		WarehouseCountStatistics whcountstatistics=new WarehouseCountStatistics();
//		String countdate="";
//		int l=dateList.size();
//		if(l==0){
//			countdate="";
//		}else if(l>=1){
//			whcountstatistics=dateList.get(l-1);
//			countdate=whcountstatistics.getCountDate();
//		}
//		
//		
//		//查询上一期数量、金额
//		String beginSql="select begincountprice,begincountnum from whcountstastic where countdate=to_date('"+countdate+"','yyyy-mm-dd,hh24:mi:ss') and itemid='"+itemId+"'";
//		WarehouseCountStatistics beginCount=new WarehouseCountStatistics();
//		double beginCountPrice=0;
//		double beginCountNum=0;
//		try{
//			beginCount=Sqlhelper.exeQueryBean(beginSql, null, WarehouseCountStatistics.class);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		if(beginCount==null){
//			beginCountPrice=0;
//			beginCountNum=0;
//		}else{
//			beginCountPrice=beginCount.getBeginCountPrice();
//			beginCountNum=beginCount.getBeginCountNum();
//		}
//		
//		//查询进库数量
//		String checkinSql="select sum(checkin_num) from mycheckin_detl where item_id='"+itemId+"' and createtime between to_date('"+countdate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')";//入库数量
//		String tuiliaoSql="select sum(rmnum) from tuiliao_detl where itemid='"+itemId+"' and createtime between to_date('"+countdate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')";//退料数量
//		String prSql="select sum(prnum) from prdetail where itemid='"+itemId+"' and createtime between to_date('"+countdate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')";//采购收货数量
//		String returnSql="select sum(returnnum) from customerreturndetail where itemid='"+itemId+"' and createtime between to_date('"+countdate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')";//客户退货数量
//		String checkin_num="";
//		String rmnum="";
//		String prnum="";
//		String returnnum="";
//		try{
//			checkin_num=Sqlhelper.exeQueryString(checkinSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		try{
//			rmnum=Sqlhelper.exeQueryString(tuiliaoSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		try{
//			prnum=Sqlhelper.exeQueryString(prSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		try{
//			returnnum=Sqlhelper.exeQueryString(returnSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		if(checkin_num==null){
//			checkin_num="";
//		}
//		if(rmnum==null){
//			rmnum="";
//		}
//		if(prnum==null){
//			prnum="";
//		}
//		if(returnnum==null){
//			returnnum="";
//		}
//		double checkinNum=checkin_num.equals("")?0:Double.parseDouble(checkin_num);
//		double rmNum=rmnum.equals("")?0:Double.parseDouble(rmnum);
//		double prNum=prnum.equals("")?0:Double.parseDouble(prnum);
//		double returnNum=returnnum.equals("")?0:Double.parseDouble(returnnum);
//		double CheckinNum=checkinNum+rmNum+prNum+returnNum;
//		
//		//查询出库数量
//		String checkoutSql="select sum(checkout_num) from mycheckout_detl where item_id='"+itemId+"' and createtime between to_date('"+countdate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')";
//		String lingliaoSql="select sum(ll_num) from lingliao_detl where item_id='"+itemId+"' and createtime between to_date('"+countdate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')";
//		String returnGoodSql="select sum(renum) from redetail where itemid='"+itemId+"' and createtime between to_date('"+countdate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')";
//		String checkout_num="";
//		String ll_num="";
//		String re_num="";
//		try{
//			checkout_num=Sqlhelper.exeQueryString(checkoutSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		try{
//			ll_num=Sqlhelper.exeQueryString(lingliaoSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		try{
//			re_num=Sqlhelper.exeQueryString(returnGoodSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		if(checkout_num==null){
//			checkout_num="";
//		}
//		if(ll_num==null){
//			ll_num="";
//		}
//		if(re_num==null){
//			re_num="";
//		}
//		double checkoutNum=checkout_num.equals("")?0:Double.parseDouble(checkout_num);
//		double llNum=ll_num.equals("")?0:Double.parseDouble(ll_num);
//		double reNum=re_num.equals("")?0:Double.parseDouble(re_num);
//		double CheckoutNum=checkoutNum+llNum+reNum;
//		//计算结余数量
//		
//		double endCountNum=beginCountNum+CheckinNum-CheckoutNum;
//		
		if(flag.equals("add")){
		countSql="insert into whcountdetl (COUNTSHEETID,ITEMID,ITEMNAME,ISSUENUM,SPEC,UNIT,STOCKID,STOCKNUM,COUNTNUM,PROFITLOSSNUM,UNITPRICE,PROFITLOSS,MEMO,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME) " +
				"values('"+countSheetid+"','"+itemId+"','"+itemName+"','"+issueNum+"','"+spec+"','"+unit+"','"+stockId+"',"+stockNum+","+countNum+","+ProfitLossNum+","+unitPrice+"," +
						"'"+ProfitLoss+"','"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'))";
//		Sql="insert into whcountstastic(SHEETID,COUNTDATE,WAREHOUSEID,ITEMID,ITEMNAME,SPEC,BEGINCOUNTPRICE,BEGINCOUNTNUM,UNIT,CHECKINNUM,CHECKOUTNUM,ENDCOUNTNUM,TRUENUM) values('"+countSheetid+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
//				"'"+warehouseId+"','"+itemId+"','"+itemName+"','"+spec+"','"+beginCountPrice+"','"+beginCountNum+"','"+unit+"','"+CheckinNum+"','"+CheckoutNum+"','"+endCountNum+"','"+countNum+"')";
//		
		
		}else if(flag.equals("edit")){
		countSql="update whcountdetl set itemId='"+itemId+"',itemName='"+itemName+"',ISSUENUM='"+issueNum+"',SPEC='"+spec+"',UNIT='"+unit+"',STOCKID='"+stockId+"',STOCKNUM='"+stockNum+"',COUNTNUM='"+countNum+"',PROFITLOSSNUM='"+ProfitLossNum+"'," +
				"UNITPRICE='"+unitPrice+"',PROFITLOSS='"+ProfitLoss+"',MEMO='"+memo+"',changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') where countsheetid='"+countSheetid+"' and itemid='"+SitemId+"'";
//		Sql="update whcountstastic set countdate=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),warehouse='"+warehouseId+"',itemId='"+itemId+"',itemName='"+itemName+"',spec='"+spec+"',begincountprice='"+beginCountPrice+"',begincountnum='"+beginCountNum+"'," +
//				"unit='"+unit+"',checkinnum='"+CheckinNum+"',checkoutnum='"+CheckoutNum+"',endcountnum='"+endCountNum+"',truenum='"+countNum+"' where sheetid='"+countSheetid+"' and itemid='"+SitemId+"'";
		
		}
		
		
		
		try{
			Sqlhelper.executeUpdate(countSql, null);
//			Sqlhelper.executeUpdate(Sql, null);
			String json = "{\"result\":\"操作成功!\",\"status\":1}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作成功!\",\"status\":0}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
