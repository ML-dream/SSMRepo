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
import JSOM.JackSonTrans;
import JSOM.Task;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.Sqlhelper;

public class MachineGTServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<FandT> ftList=new ArrayList<FandT>();
		List<Factory> factoryList=new ArrayList<Factory>();

		String sql = "select distinct A.machineId,B.machinename from processesPlan A " +
				"left join machInfo B ON A.MACHINEID=B.machineid ";

		String[] params = {};
		List<ProcessesPlan> processesPlans = new ArrayList<ProcessesPlan>();
		
		try {
			processesPlans = Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0,len=processesPlans.size();i<len;i++){
			ProcessesPlan processesPlan = processesPlans.get(i);
			Factory factory = new Factory();
			factory.setUID(processesPlan.getMachineId());
			factory.setName(processesPlan.getMachineName());
			factoryList.add(factory);
			
			List<Task> taskList=new ArrayList<Task>();
			FandT ft=new FandT();
			ft.setName(factory.getName());
			ft.setUID(factory.getUID());
			String taskSql = "select orderId,productId,issueNum,drawingId,operId," +
					"num,planstartTime,planendTime,operstatus,canclestatus,machineId," +
					"isco,(planendtime-planstarttime)*24*60*60 duration " +
					"from processesPlan  " +
					"where machineId=? ";
			String[] taskParams = {processesPlan.getMachineId()};
			
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
		response.setCharacterEncoding("UTF-8");
		String json = JackSonTrans.JsonBack(ftList);
		response.getWriter().append(json).flush();
		System.out.println(json);
		}
	
	

}
