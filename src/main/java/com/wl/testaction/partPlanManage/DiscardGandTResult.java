package com.wl.testaction.partPlanManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JSOM.Factory;
import JSOM.FandT;
import JSOM.Task;

import com.wl.common.ProductStatus;
import com.wl.forms.Order;
import com.wl.forms.PartsPlan;
import com.wl.forms.ProcessesPlan;
import com.wl.tools.Sqlhelper;

public class DiscardGandTResult extends HttpServlet {
	public  static java.util.List<FandT> getPartProGandT(PartsPlan partsPlan){
		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		String sql = "select distinct A.operId operId " +
				"from processesplan A "+
				"where A.orderId=? and A.productId=? and A.issueNum=? and drawingId=? ";
		String[] params = {partsPlan.getOrderId(),partsPlan.getProductId(),partsPlan.getIssueNum(),partsPlan.getDrawingId()};
		List<ProcessesPlan> processesPlans = new ArrayList<ProcessesPlan>();
		
		try {
			processesPlans = Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0,len=processesPlans.size();i<len;i++){
			ProcessesPlan processesPlan = processesPlans.get(i);
			Factory factory = new Factory();
			factory.setUID(processesPlan.getOperId());
			factory.setName(processesPlan.getOperId());
			factoryList.add(factory);
			
			java.util.List<Task> taskList=new ArrayList<Task>();
			FandT ft=new FandT();
			ft.setName(factory.getName());
			ft.setUID(factory.getUID());
			String taskSql = "select orderId,productId,issueNum,drawingId,operId," +
					"num,planstartTime,planendTime,operstatus,canclestatus," +
					"isco,(planendtime-planstarttime)*24*60*60 duration " +
					"from processesPlan  " +
					"where orderId=? and productId=? and issueNum=? and drawingId=? and operId=? ";
			String[] taskParams = {partsPlan.getOrderId(),partsPlan.getProductId(),partsPlan.getIssueNum(),partsPlan.getDrawingId(),factory.getUID()};
			
			List<ProcessesPlan> processesPlanList = new ArrayList<ProcessesPlan>();
			try {
				processesPlanList = Sqlhelper.exeQueryList(taskSql, taskParams, ProcessesPlan.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			for(int j=0,length=processesPlanList.size();j<length;j++){
				ProcessesPlan totalPlan = processesPlanList.get(j);
				Task task=new Task();
				task.setUID(totalPlan.getOperId());
				task.setName(totalPlan.getOperId());
				
				task.setStart(totalPlan.getPlanStartTime());
				task.setFinish(totalPlan.getPlanEndTime());
				task.setPercentComplete(0);								//???????????????????????????????????????????????????????????
				task.setDuration(totalPlan.getDuration());
				
				taskList.add(task);
			}
			ft.setTasks(taskList);
			ftList.add(ft);
		}
		return ftList;
	}
	
	public  static java.util.List<FandT> getAllPartsGandT(){
		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		String sql = "select distinct B.product_name productName,A.orderId,A.productId,A.issueNum,A.drawingId " +
				"from partsplan A "+
				"left join order_detail B on A.orderId=B.order_Id and A.productId=B.product_id and A.issueNum=B.issue_num and A.drawingId=B.drawingId "+
				"where A.CANCELSTATUS='0' and B.product_status<to_number(?) and B.product_status>=to_number(?) ";
		String[] params = {ProductStatus.DELEVERED+"",ProductStatus.PARTPLANING+""};
		List<Order> orders = new ArrayList<Order>();
		
		try {
			orders = Sqlhelper.exeQueryList(sql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0,len=orders.size();i<len;i++){
			Order order = orders.get(i);
			Factory factory = new Factory();
			factory.setUID(order.getProductId());
			factory.setName(order.getProductName());
			factoryList.add(factory);
			
			java.util.List<Task> taskList=new ArrayList<Task>();
			FandT ft=new FandT();
			ft.setName(factory.getName());
			ft.setUID(factory.getUID());
			String taskSql = "select orderId,productId,issueNum,drawingId,operId," +
					"num,planstartTime,planendTime,operstatus,canclestatus," +
					"isco,(planendtime-planstarttime)*24*60*60 duration " +
					"from processesPlan  " +
					"where orderId=? and productId=? and issueNum=? and drawingId=?  ";
			String[] taskParams = {order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId()};
			
			List<ProcessesPlan> processesPlanList = new ArrayList<ProcessesPlan>();
			try {
				processesPlanList = Sqlhelper.exeQueryList(taskSql, taskParams, ProcessesPlan.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			for(int j=0,length=processesPlanList.size();j<length;j++){
				ProcessesPlan totalPlan = processesPlanList.get(j);
				Task task=new Task();
				task.setUID(totalPlan.getOperId());
				task.setName(totalPlan.getOperId());
				
				task.setStart(totalPlan.getPlanStartTime());
				task.setFinish(totalPlan.getPlanEndTime());
				task.setPercentComplete(0);								//???????????????????????????????????????????????????????????
				task.setDuration(totalPlan.getDuration());
				
				taskList.add(task);
			}
			ft.setTasks(taskList);
			ftList.add(ft);
		}
		return ftList;
	}
	
	
	public  static java.util.List<FandT> getSelectedPartsProGandT(List<Order> orders){
		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();
		for(Order order: orders){
			Factory factory = new Factory();
			factory.setUID(order.getProductId());
			factory.setName(order.getProductName());
			factoryList.add(factory);
			String sql="select to_char(E_Time,'yyyy-mm-dd') ETime from order_detail where order_Id=? and product_Id=? and issue_Num=?";
			String[] params={order.getOrderId(),order.getProductId(),order.getIssueNum()};
			ProcessesPlan process=new ProcessesPlan();
			try{
				process=Sqlhelper.exeQueryBean(sql, params, ProcessesPlan.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			java.util.List<Task> taskList=new ArrayList<Task>();
			FandT ft=new FandT();
			ft.setName(factory.getName()+"("+process.getETime()+")");
			ft.setUID(factory.getUID());
			String taskSql = "select A.orderId,A.productId,A.issueNum,A.drawingId,A.operId," +
					"A.num,A.planstartTime,A.planendTime,A.operstatus,A.canclestatus," +
					"A.isco,(A.planendtime-A.planstarttime)*24*60*60 duration,A.passnum,C.FO_OPNAME OPERNAME," +
					"to_char(D.E_Time,'yyyy-mm-dd') ETime,A.isdiscard " +
					"from processesPlan A " +
					"left join order_detail D ON A.orderId=D.order_Id and A.productId=D.product_Id and A.issueNum=D.issue_Num " +
					"left join foheader B on B.ORDERID=A.ORDERID AND B.PRODUCTID=A.PRODUCTID AND B.ISSUENUM=A.ISSUENUM " +
					"left join fo_detail C on B.productID=C.PRODUCT_ID AND B.FOID=C.FOID and C.fo_no =A.OPERID "+
					"where A.orderId=? and A.productId=? and A.issueNum=? and A.drawingId=? and A.isdiscard=? and C.isinuse='1' ORDER BY A.operId ";
			String[] taskParams = {order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId(),order.getBarcode()};
			
			List<ProcessesPlan> processesPlanList = new ArrayList<ProcessesPlan>();
			try {
				processesPlanList = Sqlhelper.exeQueryList(taskSql, taskParams, ProcessesPlan.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			for(int j=0,length=processesPlanList.size();j<length;j++){
				ProcessesPlan totalPlan = processesPlanList.get(j);
				String waixie="";
				if(totalPlan.getIsCo().equals("1"))
					waixie="(外协)";
				Task task=new Task();
				double k=((double)totalPlan.getPassNum())/((double)totalPlan.getNum())*100;
				task.setUID(totalPlan.getOperId());
				task.setName(totalPlan.getOperId()+totalPlan.getOperName()+waixie);
				task.setIsCo(totalPlan.getIsCo());
				task.setStart(totalPlan.getPlanStartTime());
				task.setFinish(totalPlan.getPlanEndTime());
				task.setPercentComplete(k);								//???????????????????????????????????????????????????????????
				task.setDuration(totalPlan.getDuration());
				task.setOrderId(order.getOrderId());
				task.setProductId(order.getProductId());
				task.setIssueNum(order.getIssueNum());
				task.setETime(totalPlan.getETime());
				task.setIsDiscard(totalPlan.getIsDiscard());
				taskList.add(task);
			}
			ft.setTasks(taskList);
			ftList.add(ft);
			
		}
		return ftList;
	}
//	
	public  static java.util.List<FandT> getOnePartGandT(Order order){
		
		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		String sql = "select distinct A.operId operId,A.ISCO,C.FO_OPNAME OPERNAME " +
				"from processesplan A "+
				"left join foheader B on B.ORDERID=A.ORDERID AND B.PRODUCTID=A.PRODUCTID AND B.ISSUENUM=A.ISSUENUM " +
				"left join fo_detail C on B.productID=C.PRODUCT_ID AND B.FOID=C.FOID and C.fo_no =A.OPERID "+
				"where A.orderId=? and A.productId=? and A.issueNum=? and A.drawingId=? and A.isdiscard=? and C.isinuse='1' order by to_number(operId)";
		String[] params = {order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId(),order.getBarcode()};
		List<ProcessesPlan> processesPlans = new ArrayList<ProcessesPlan>();
		
		try {
			processesPlans = Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0,len=processesPlans.size();i<len;i++){
			ProcessesPlan processesPlan = processesPlans.get(i);
			Factory factory = new Factory();
			factory.setUID(processesPlan.getOperId());
			factory.setName(processesPlan.getOperName());
			factoryList.add(factory);
			
			java.util.List<Task> taskList=new ArrayList<Task>();
			FandT ft=new FandT();
			if(processesPlan.getIsCO().equals("1"))
				ft.setName(factory.getUID()+factory.getName()+"(外协)");
			else
				ft.setName(factory.getUID()+factory.getName());
			ft.setUID(factory.getUID());
			String taskSql = "select orderId,productId,issueNum,drawingId,operId," +
					"num,planstartTime,planendTime,operstatus,canclestatus,passnum," +
					"isco,(planendtime-planstarttime)*24*60*60 duration " +
					"from processesPlan  " +
					"where orderId=? and productId=? and issueNum=? and drawingId=? and operId=? and isDiscard=? ";
			String[] taskParams = {order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId(),factory.getUID(),order.getBarcode()};
			
			List<ProcessesPlan> processesPlanList = new ArrayList<ProcessesPlan>();
			try {
				processesPlanList = Sqlhelper.exeQueryList(taskSql, taskParams, ProcessesPlan.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			for(int j=0,length=processesPlanList.size();j<length;j++){
				ProcessesPlan totalPlan = processesPlanList.get(j);
				double k=((double)totalPlan.getPassNum())/((double)totalPlan.getNum())*100;
				Task task=new Task();
				task.setUID(totalPlan.getOperId());
				task.setName(totalPlan.getOperId());
				task.setStart(totalPlan.getPlanStartTime());
				task.setFinish(totalPlan.getPlanEndTime());
				task.setPercentComplete(k);	
				task.setDuration(totalPlan.getDuration());
  			    task.setIsCo(totalPlan.getIsCO());
				taskList.add(task);
			}
			ft.setTasks(taskList);
			ftList.add(ft);
		}
		return ftList;
	}
	

}
