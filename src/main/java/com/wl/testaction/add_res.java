package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ResManage;

import java.sql.*;
import cfmes.util.*;

public class add_res extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DealString ds = new DealString();
		ResManage resManage=new ResManage();

		String item_id = ds.toString(ds.toGBK(request.getParameter("item_id")));
		String product_id = ds.toString(ds.toGBK(request.getParameter("product_id")));
		String item_name = ds.toString(ds.toGBK(request.getParameter("item_name")));
		String dotype = ds.toGBK(request.getParameter("dotype"));
		String machineid = ds.toString(ds.toGBK(request.getParameter("machineid")));
		String machinename = ds.toString(ds.toGBK(request.getParameter("machinename")));
		String worker = ds.toString(ds.toGBK(request.getParameter("worker")));
		String RFID1 = ds.toString(ds.toGBK(request.getParameter("RFID1")));
		String RFID2 = ds.toString(ds.toGBK(request.getParameter("RFID2")));
		
		response.setContentType("text/html");
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
         
		try{
			if(dotype.equals("itemform")){
				
			   if(resManage.isin_Item(item_id)){			     
			   out.print("<script>alert('已有该物料，请重新输入');</script>");	    			     
			   }else{
			     resManage.update("INSERT INTO work.item (item_id,product_id,item_name)"
			    		 +" Values ('"+item_id+"','"+product_id+"','"+item_name+"')");			          
				   out.print("<script>alert('添加物料成功！');</script>");
			   }
		     }
			if(dotype.equals("machform")){
				resManage.update("INSERT INTO work.machinfo (machineid,machinename,worker,RFID1,RFID2)"
			    		 +" Values ('"+machineid+"','"+machinename+"','"+worker+"','"+RFID1+"','"+RFID2+"')");			          
				   out.print("<script>alert('设备维护成功！');window.location='RFIDJSP/resource.jsp';</script>");			  	
			}
			out.flush();
			out.close();
		}catch(Exception e){
			System.out.println("add_res处理时出错；错误为："+e);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
        doPost(request,response);
    }
}
