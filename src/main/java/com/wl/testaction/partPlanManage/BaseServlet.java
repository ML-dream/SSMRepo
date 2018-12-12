package com.wl.testaction.partPlanManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.StringUtil;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 559264262439401995L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String functionName = request.getParameter("meth");
		String pathTo = StringUtil.isNullOrEmpty(request.getParameter("pathTo"))?"":request.getParameter("pathTo");
		if("GoMainBody".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoMainBody(request, response, pathTo+".jsp");
		}
		if("GoPartPlanAdd".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoPartPlanAdd(request, response, pathTo+".jsp");
		}
		if("AddpartPlan".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AddpartPlan(request, response, pathTo+".jsp");
		}
		if("SeePartsPlan".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.SeePartsPlan(request, response, pathTo+".jsp");
		}
			
		if("addProPlan".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.addProPlan(request, response, pathTo+".jsp");
		}
		if("UpdatepartPlan".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.UpdatepartPlan(request, response, pathTo+".jsp");
		}
		if("GoProPlan".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoProPlan(request, response, pathTo+".jsp");
		}
		if("listProPlan".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.listProPlan(request, response, pathTo+".jsp");
		}
		if("allPartsGandT".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.allPartsGandT(request, response, pathTo+".jsp");
		}
		if("GoPartGT".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoPartGT(request, response, pathTo+".jsp");
		}
		if("AddpartsPlan".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AddpartsPlan(request, response, pathTo+".jsp");
		}
		if("seePartsPlan".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.seePartsPlan(request, response, pathTo+".jsp");
		}
		if("GoPartsPlanGandT".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoPartsPlanGandT(request, response, pathTo+".jsp");
		}
		if("GoSeePlanDetail".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoSeePlanDetail(request, response, pathTo+".jsp");
		}
		if("SeePlanDetailServlet".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.SeePlanDetailServlet(request, response, pathTo+".jsp");
		}
		if("SeeSubmitPlanServlet".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.SeeSubmitPlanServlet(request, response, pathTo+".jsp");
		}
		if("PartsPlanSubmit".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.PartsPlanSubmit(request, response, pathTo+".jsp");
		}
		if("PartsPlanPass".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.PartsPlanPass(request, response, pathTo+".jsp");
		}
		if("bindRfid".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.bindRfid(request, response, pathTo+".jsp");
	    }
		}
}
