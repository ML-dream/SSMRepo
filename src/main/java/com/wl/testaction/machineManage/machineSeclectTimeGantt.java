package com.wl.testaction.machineManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JSOM.Factory;
import JSOM.FandT;
import JSOM.Fant2;
import JSOM.JackSonTrans;
import JSOM.MachineTimeGT;
import JSOM.Task;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.Sqlhelper;

public class machineSeclectTimeGantt extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String machineId = request.getParameter("machineId").trim();

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		    
		
		    Date beginDate = new Date();
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			String createTime = df.format(date.getTime());
			
		    List<Fant2> ftList=new ArrayList<Fant2>();
			
			
			
			
			String taskSql = "select ISSUENUM, machineorderstart,machineorderend,processplanId,orderId,productId,issueNum,drawingId,operId," +
					"num,planstartTime,planendTime,operstatus,canclestatus,machineId," +
					"isco,(planendtime-planstarttime)*24*60*60 duration " +
					"from processesPlan " +
					"where machineId=? order by machineorderstart ";//and planstartTime> to_date(? ,'yyyy-mm-dd,hh24:mi:ss') ,createTime
			String[] taskParams = {machineId};
			
			List<ProcessesPlan> processesPlanList = new ArrayList<ProcessesPlan>();
			try {
				processesPlanList = Sqlhelper.exeQueryList(taskSql, taskParams, ProcessesPlan.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			for(int j=0,length=processesPlanList.size();j<length;j++){
				ProcessesPlan totalPlan = processesPlanList.get(j);
				Fant2 ft=new Fant2();
				List<MachineTimeGT> taskList=new ArrayList<MachineTimeGT>();
				ft.setName(totalPlan.getOrderId());
				ft.setUID(totalPlan.getProcessPlanId());
				
				
				
				MachineTimeGT MachineTimeGT=new MachineTimeGT();
				MachineTimeGT.setUID(totalPlan.getProcessPlanId());
				MachineTimeGT.setName(totalPlan.getOrderId());
				
				MachineTimeGT.setStart(totalPlan.getMachineOrderStart());
				MachineTimeGT.setFinish(totalPlan.getMachineOrderEnd());
				MachineTimeGT.setPercentComplete(0);								
				MachineTimeGT.setDuration(totalPlan.getDuration());
				MachineTimeGT.setMachineId(machineId);
				MachineTimeGT.setProductId(totalPlan.getProductId());
				MachineTimeGT.setOperId(totalPlan.getOperId());
				taskList.add(MachineTimeGT);
				ft.setTasks(taskList);
				ftList.add(ft);
		}
		response.setCharacterEncoding("UTF-8");
		String json = JackSonTrans.JsonBack2(ftList);
		response.getWriter().append(json).flush();
		System.out.println(json);
		}
	
	

}
