package controller;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl.forms.User;

import javaBean.machineInfo5505;
import javaBean.user;
import net.sf.json.JSONObject;
import service.AccessoryService;
import service.selectMachineInfoService;
import service.serviceImpl.OrderServiceImpl;



@Controller
public class OrderController { 
	
	@Resource
	OrderServiceImpl orderServiceImpl;
	//跳转至列表页面
	@Autowired
	HttpServletRequest request;
	
	/*
	 * 这个是针对的是我的预约的那个地方的方法，根据工号显示自己的订单
	 * 
	 */
	
	
	@RequestMapping(value="mybookOrder",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String returnMyBookOrder(Model model,String pageIndex,String pageSize){ 
	 
		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();

//		String staffCode="5555";
		int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
	    
		String json  = orderServiceImpl.returnMyBookOrder(staffCode,pageNo, countPerPage);
		System.out.println(json);
		return json;//转向首页
	
	}
	
	/*
	 * 这个是和我的预约的进行联动的请求的，展示机床的信息的额及其后面的预约审核根据审核界面显示详细的机床信息的，两种逻辑是一样的！
	 * 
	 */
	@RequestMapping(value="myBookOrderMachine",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String rerurnMyBookOrderMachine(Model model,String orderId,String pageIndex,String pageSize){ 
	
	    int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
	    
		String json  = orderServiceImpl.returnMybookMachine(orderId, pageNo, countPerPage);
		System.out.println(json);
		
		return json;//转向首页

		
	}
	
//	
//	AuditingBookingOrder.action
	/*
	 * 这个方法是针对的是预约审核，显示相应的额预约审核界面的第一个界面，主界面
	 * 
	 * 
	 */

	@RequestMapping(value="AuditingBookingOrder.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String rerurnAuditingBookingOrder(Model model,String orderId,String pageIndex,String pageSize){ 
	

/*		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();*/

//		String staffCode="5555";
		int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
	    String staffCode="";
		String json  = orderServiceImpl.returnMyBookOrder(staffCode,pageNo, countPerPage);
		System.out.println(json);
		return json;//转向首页

		
	}
    /*
     * 这主要是针对的是预约点进去之后的，然后进行各种审核的界面，比如审核通过还是不通过！
     */
    @RequestMapping(value="AuditingBookOrderAll.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String rerurnAuditingBookingAll(Model model,String bookStatus,String orderId,String checkAdvice,String pageIndex,String pageSize){ 
    	HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
    	//System.out.println(orderId+"11"+bookStatus);

		String json  = orderServiceImpl.updateAudutingBookStatus(orderId, bookStatus,staffCode,checkAdvice);
		System.out.println(json);
		return json;//转向首页

		
	}
    
    //
    /*
     * 这主要是针对的是预约点进去之后的，进行删除订单
     */
    @RequestMapping(value="AuditingBookOrderDelete.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String rerurnAuditingBookOrderDelete(Model model,String unid){ 
	
    	//System.out.println(orderId+"11"+bookStatus);

		String json  = orderServiceImpl.deleteAuditingBookOrder(unid);
		System.out.println(json);
		return json;//转向首页

		
	}
    @RequestMapping(value="AuditingOrderDelete.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnAuditingOrderDelete(Model model,String orderId){ 
 	

 		String json  = orderServiceImpl.deleteAuditingOrder(orderId);
 		System.out.println(json);
 		return json;//转向首页
 	}
//   动态更新前端的状态值
    
    @RequestMapping(value="AuditingBookOrderUpdateStatus.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnAuditingBookOrderUpdateStatus(Model model,String orderId){ 
 	

 		String json  = orderServiceImpl.AuditingBookOrderUpdateStatus(orderId);
 		System.out.println(json);
 		return json;//转向首页
 	}
    
    @RequestMapping(value="AddShiYanOrder.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnAddShiYanOrder(Model model,String orderId,String customer,String connector,String connectorTel,String productName,String productNum,String material){ 
 	

 		String json  = orderServiceImpl.addShiYanOrder(orderId,customer,connector,connectorTel,productName,productNum,material);
 		System.out.println(json);
 		return json;//转向首页
 	}
    
    @RequestMapping(value="LoadDefaultShiYanOrder.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnLoadDefaultShiYanOrder(Model model,String staffCode){ 
 	

 		String json  = orderServiceImpl.loadDefaultShiYanOrder(staffCode);
 		System.out.println(json);
 		return json;//转向首页
 	}
    
}
