package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.ArrayList;

import cfmes.util.DealString;
import cfmes.bom.dao.PartPlanDao;
import cfmes.bom.entity.FoDetail;
import cfmes.util.Stringtoint;
import cfmes.util.Datesubday;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoNodeSvlt extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		DealString ds = new DealString();
		String item_id = ds.toGBK(request.getParameter("node_id"));
		String father_item_id = ds.toGBK(request.getParameter("father_id"));
		String product_id = ds.toGBK(request.getParameter("product_id"));
		String issue_num = ds.toGBK(request.getParameter("issue_num"));
		String edtime = ds.toGBK(request.getParameter("partedtime"));
		
		ArrayList list0=new ArrayList();
		PartPlanDao ppd = new PartPlanDao();
		//Stringtoint stoi = new Stringtoint();
	    float time = 0 ;
		list0 = ppd.GetFoDetail(product_id, father_item_id, item_id, issue_num);
		//�����ܹ���Ҫ��ʱ��
		for(int i=0;i<list0.size();i++){
			FoDetail fd = new FoDetail();
			fd=(FoDetail)list0.get(i);
			time=time+fd.getInsp_time()+fd.getOper_aidtime()+fd.getOper_time()+fd.getPlan_time()+fd.getRated_time();
		}
		//����һСʱ����һСʱ��,ceil��������ȡ��
		 Math  m = null ;
		double timemid;
        timemid = m.ceil(time);
        //���������һ��8Сʱ,����һ�찴һ����
        int daynum = 0-(int)m.ceil(timemid/8);
		//���㿪ʼ���ڣ����������ڼ�ȥ����
        Datesubday dsd = new Datesubday();
        String plansttime = dsd.dsd(edtime, daynum);
        
        response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");
	    
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    
		out.println("<ITEM>");
		out.println("<STTIME>"+plansttime+"</STTIME>");
		out.println("</ITEM>");
		out.flush();
		out.close();
	}

}
