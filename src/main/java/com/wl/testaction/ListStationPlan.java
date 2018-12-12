package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessPlan;
import com.wl.tools.Sqlhelper;

import cfmes.util.sql_data;


public class ListStationPlan extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	    System.out.println("进入ListStationPlan函数体了已经！！！");

	    Date part_starttime = null;
		Date part_finishtime = null;
		int part_status = 0;
		int process_num = 0;
		int part_num = 0;

		String id = request.getParameter("id").toString();
		System.out.println("传进来的node字符串是=="+id);
		String[] idList = null;
		idList = id.split(",");
		for(int i=0;i<idList.length;i++){
			
			System.out.println("idList["+i+"]=="+idList[i]);
			
			String sql = "select *from partplan where item_id='"+idList[i]+"'";//查询零件计划
			System.out.println("sql=="+sql);
			
			//String sql2 = "select OPER_ID,DURATION,PROCESSPLAN_A,PROCESSPLAN_B,PLAN_ID from process where EQUIPMENT_DRAWID='"+idList[i]+"' order by PLAN_ID desc";//查询工序表
			
			String sql2 = "select FO_OPERID,OPER_TIME,EQUIPCODE from fo_detail where item_id ='"+idList[i]+"' and isinuse='1' order by fo_operid desc";
			System.out.println("sql2=="+sql2);
				
			ResultSet lingjian_rs=null;
			try {
				lingjian_rs = (ResultSet) Sqlhelper.executeQuery(sql, null);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}//零件计划表
			ResultSet process_rs=null;
			try {
				process_rs = (ResultSet) Sqlhelper.executeQuery(sql2, null);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			ArrayList<ProcessPlan> al = new ArrayList<ProcessPlan>();	
			try {
				lingjian_rs.next();
				part_starttime = lingjian_rs.getDate("PLAN_BGTIME");
				part_finishtime = lingjian_rs.getDate("PLAN_EDTIME");
				part_status = lingjian_rs.getInt("PART_STATUS");
				part_num = lingjian_rs.getInt("PART_NUM");
				process_num = lingjian_rs.getInt("PARTPLAN_A");
				lingjian_rs.close();
				float start=0;
				float end=0;
				System.out.println("=========================================");
				while(process_rs.next()){
					float duration;                      //存储的是小时数据，小时小时小时！！！！
					String equipment = null;
					String worker =null;
					
					int processplan_sequence = 0;      //第一道工序在数据库中存储为1，第二道为2....
					int processplan_num =process_num;
					Date processplan_starttime = null;
					Date processplan_finishtime = null;
					String processplan_id = null;      //如何获得工序的ID号呢？？
					
					processplan_id = process_rs.getString("FO_OPERID");
					duration = process_rs.getFloat("OPER_TIME");
					equipment = process_rs.getString("EQUIPCODE");
					//worker = process_rs.getString("PROCESSPLAN_B");
					
					end=start;
					start+=duration*part_num;
					
//					processplan_sequence = process_rs.getInt("PLAN_ID");
	                //这个日期转化是绝对的技术核心！！！！！！
//					String insert_processplan_sql = 
//					"insert into process_plan (PROCESSPLAN_A,PROCESSPLAN_B,EQUIPMENT_DRAWID,OPER_ID,NUM,START_TIME,END_TIME) values('"
//					+equipment+"','"+worker+"','"+idList[i]+"','"+processplan_id+"','"+processplan_num+"',to_date('"
//					+part_finishtime+"','yyyy-mm-dd HH24:MI:SS')-"+start+"/24,to_date('"
//					+part_finishtime+"','yyyy-mm-dd HH24:MI:SS')-"+end+"/24)";
					
					String insert_processplan_sql = 
					"insert into process_plan (ITEM_ID,OPER_ID,NUM,PROCESSPLAN_A,START_TIME,END_TIME) values('"
					+idList[i]+"','"
					+processplan_id+"','"
					+processplan_num+"','"
					+equipment+"',"
					+"to_date('"+part_finishtime+"','yyyy-mm-dd HH24:MI:SS')-"+start+"/24," 
					+"to_date('"+part_finishtime+"','yyyy-mm-dd HH24:MI:SS')-"+end+"/24)";
					
					
					System.out.println("insert_processplan_sql=="+insert_processplan_sql);
	
//					Sqlhelper.executeUpdate_noclose(insert_processplan_sql, null); //重大错误隐患！！！！-->>结果集没有及时关闭！！！
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Processplanaction 中保存processplan出现异常！！！！");

			}finally{
				  
			   try{
			     if(lingjian_rs!=null) {
			    	 lingjian_rs.close();
			          }
			     if(process_rs!=null){
			    	 process_rs.close();
			        }
			     }catch (Exception e1) {
				   e1.printStackTrace();
			     }
			  }
		
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}
