package com.wl.testaction.partPlanManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.StringUtil;

public class DiscardBaseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doPost(request,response);
			}
			public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String functionName = request.getParameter("meth");
				String pathTo = StringUtil.isNullOrEmpty(request.getParameter("pathTo"))?"":request.getParameter("pathTo");
				if("GoMainBody".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.GoMainBody(request, response, pathTo+".jsp");
				}
				if("GoPartPlanAdd".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.GoPartPlanAdd(request, response, pathTo+".jsp");
				}
				if("AddpartPlan".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.AddpartPlan(request, response, pathTo+".jsp");
				}
				if("SeePartsPlan".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.SeePartsPlan(request, response, pathTo+".jsp");
				}
				if("addProPlan".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.addProPlan(request, response, pathTo+".jsp");
				}
				if("UpdatepartPlan".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.UpdatepartPlan(request, response, pathTo+".jsp");
				}
				if("GoProPlan".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.GoProPlan(request, response, pathTo+".jsp");
				}
				if("listProPlan".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.listProPlan(request, response, pathTo+".jsp");
				}
				if("allPartsGandT".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.allPartsGandT(request, response, pathTo+".jsp");
				}
				if("GoPartGT".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.GoPartGT(request, response, pathTo+".jsp");
				}
				if("AddpartsPlan".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.AddpartsPlan(request, response, pathTo+".jsp");
				}
				if("seePartsPlan".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.seePartsPlan(request, response, pathTo+".jsp");
				}
				if("GoPartsPlanGandT".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.GoPartsPlanGandT(request, response, pathTo+".jsp");
				}
				if("GoSeePlanDetail".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.GoSeePlanDetail(request, response, pathTo+".jsp");
				}
				if("SeePlanDetailServlet".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.SeePlanDetailServlet(request, response, pathTo+".jsp");
				}
				if("SeeSubmitPlanServlet".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.SeeSubmitPlanServlet(request, response, pathTo+".jsp");
				}
				if("PartsPlanSubmit".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.PartsPlanSubmit(request, response, pathTo+".jsp");
				}
				if("PartsPlanPass".equals(functionName)){
					DiscardActionServlet actionServlet = new DiscardActionServlet();
					actionServlet.PartsPlanPass(request, response, pathTo+".jsp");
				}
			}

}
