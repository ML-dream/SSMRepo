/**
 * 
 */
package com.wl.tools;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.Count;
import org.springframework.transaction.annotation.Transactional;

import machineOrderYuyue.beans.bookingInfoBean;
import machineOrderYuyue.beans.machineBean;
import mapper.BookOrderMapper;

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年4月4日 上午9:50:38 
* 类说明 
*/

/*  
*    
* 项目名称：SSM   
* 类名称：DataBaseTool   
* 类描述：   
* 		
* 创建人：dell   
* 创建时间：2019年4月4日 上午9:50:38   
* @version        
*/

public class DataBaseTool {

	public static final Logger logger = LogManager.getLogger(DataBaseTool.class);
	/*
	 * 1、这个类是用来每次只要是修改预约的时间，就会计算相应的截至上报期限，然后插入到orders表中
     * 2、现在我想在加一个功能，目的是为了可以在预约一旦被修改的时候，然后把预约的信息，包括所有的设备信息全都插入其中！！！【这个目前我已经把他取消了】
     * 3、现在还要在加一个功能，功能主要是随时根据目前删除的预约信息，看是当前的订单的当前设备的预约信息有没有全都删光，如果删除光了
	 * 
	 */
	@Transactional
	public static  Boolean  selectNewDate(String orderId) throws Exception{
		
		if (orderId.equals("")) logger.info("当前的查询的订单名字为空！！！！");
		
//		……………………………………………………………………………………………………此处是进行第三个问题，进行判断是不是删除光了，如果是删除光了，那么就会自动的将
		
		
		
		
		
		Boolean result=true;
 		//	先查询出最新的日期
		bookingInfoBean infoBean = null;
		String sql="select time_ymd from BOOKINGINFO t  where orderId='"+orderId+"' order by time_ymd desc";
		try {
			 infoBean = Sqlhelper.exeQueryBean(sql, null, bookingInfoBean.class);
		} catch (Exception e) {
			logger.error("查询最新订单的时候异常，向上抛出");
//			此处的目的是因为删除光了之后，就会导致查询最新的时间的时候，就会查询到=值是空值，所以此时利用异常进处理，当这种情况出现的时候，然后个更新表中的时间值为“”	
			String insertOrderSql = "UPDATE orders set upload_date=? where order_Id=?";
			String[] param1 = {"",orderId};
			
			try {
				Sqlhelper.executeUpdate(insertOrderSql, param1);
				return result;
			} catch (SQLException e1) {
				logger.error("更新的最新订单的时候异常，向上抛出");
				result=false;
				
			}
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dd = null;
		try {
			dd = format.parse(infoBean.getTimeYmd());
		} catch (ParseException e) {
			logger.error("转换时间的的时候异常，向上抛出");
			result=false;
			throw new RuntimeException("抛出更新订单的时候异常，向上抛出！");
		} 
//		将日期加上3天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd); 
		calendar.add(Calendar.DAY_OF_MONTH, 3); 
		String newDate = format.format(calendar.getTime() ) ;
//     把最新的日期期限插入到orders的表中！！！		
		String insertOrderSql = "UPDATE orders set upload_date=? where order_Id=?";
		String[] param1 = {newDate,orderId};
		
		try {
			Sqlhelper.executeUpdate(insertOrderSql, param1);
		} catch (SQLException e) {
			logger.error("更新的最新订单的时候异常，向上抛出");
			result=false;
			throw new RuntimeException("抛出更新订单的时候异常，向上抛出！");
			
		}
		/*String deleteOrdersMachinesSql="delete   from ORDERSMACHINESAUDITS t  where order_id='"+orderId+"'";
		String updateOrdersMachinesSql="insert into ordersmachinesaudits (ORDER_ID,machine_id )  select a.orderid,a.machineid  from "
				+ "(select distinct a.machineid,a.orderId  from bookinginfo a where  a.orderId='"+orderId+"' group by a.machineid ,a.orderiD order by a.machineid) a";
		try {////
			Sqlhelper.executeUpdate(deleteOrdersMachinesSql, null);
			Sqlhelper.executeUpdate(updateOrdersMachinesSql, null);
		} catch (Exception e) {
			logger.error("更新的ordersMachines的时候异常，向上抛出");
			result=false;
			e.printStackTrace();
		}*/
		
		return result;
		
		
	}
//	在这个地方调用一个函数，该函数用于判断当前是否是所有的设备的相应的管理员都已经审核结束 ，也就是可以通过当前的提交审核人的信息，。查询到它管理的设备在当前订单的所有的设备中占几个？，然后维护一个订单总共有几个设备？
//	然后进行对比，直至相等，就将当前的状态置为下一个！但是有个12.5的状态，代表审核中！
	@Transactional 
	public static String updateOrderMachinesAudits(String bookStatus,String staffCode,String checkAdvice,String orderId,BookOrderMapper bookOrdeMapper) throws Exception {
//		 这个sql语句是用来查询档当前的审核人员，能够审核那几个设备！审核的步骤是，进行打电话修改然后审核通过 ！然后进行提交！提交审核就到了这个步骤，根据查询出来的这个当前的老师所具有的审核的设备的能力；
//		  进行对当前的订单的他所能审核的设备进行更新审核意见及填充审核人，再然后根据所建立的数据库中的ordersMachines 的表进行查询，直到所有的设备的后面的审核没有空值，就可以直接判定进入下一个步骤来 
		 String result="操作成功";
		  
		  String  sql ="select b.machineId ,c.machinename from " + 
		  		"(select a.machineid  from bookinginfo a where a.orderid='"+orderId+"' and  a.machineid  in " + 
		  		"(select t.machine_id from MACHINEMANAGER t where t.machine_manager_id='"+staffCode+"' ) order by a.machineid) b left join machinfo c on b.machineId = c.machineid";
		  List<machineBean> list = null;
		 
			 list = Sqlhelper.exeQueryList(sql, null,machineBean.class);
		
		
		  String machineId;
		  String machineName;
		  checkAdvice=checkAdvice==null?"":checkAdvice;
//这个地方没有写任何逻辑，直接就是update进行覆盖！		  
		 for(machineBean mac :list) {
			 String sql2= "update ordersMachinesAudits set audit_person=? ,advice=? where order_id=? and machine_id=?";
			 machineId=String.valueOf(mac.getMachineId());
			 machineName=mac.getMachineName();
			 String [] param= {staffCode,checkAdvice,orderId,machineId};
			 Sqlhelper.executeUpdate(sql2, param);
		
		 }
		 
//		 接下来开始进行判断，是否满足进行下一步的条件！！！
		 int countNum=0;
		 String sql3 ="select count(*) from ORDERSMACHINESAUDITS  t  where t.audit_person is null and order_id='"+orderId+"'";
		 countNum = Sqlhelper.exeQueryCountNum(sql3, null);
		
		 if (countNum>0) return result;
//		 下面是进行把更新下一步的过程！
//		 此处的审核人和审核意见已经没有作用了，因为所有的审核人和审核意见全都转到了相应的ordersMachinesAudits的页面中！！！！
		 bookOrdeMapper.updateAuditingBookStatus(bookStatus,staffCode,"",orderId);
		 return result;
	 }
	
}
