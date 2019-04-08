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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl.forms.User;
import com.wl.testaction.utils.DaiUtils;

import javaBean.machineInfo5505;
import javaBean.user;
import net.sf.json.JSON;
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
    public String returnMyBookOrder(Model model,String pageIndex,String sign,String pageSize,String sortField,String sortOrder,String bookStatus,String orderId,String companyId,String companyName){ 
	 
		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
		String json;
//		String staffCode="5555";
		int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
//	    这个是根据是否是完工审核查询，进行的判断
	    sign = sign==null?"":sign;
	    if(sign.equals("audit")) {
	    	staffCode="";
	    	 json  = orderServiceImpl.returnMyBookOrder(orderId==null?"":orderId,staffCode,pageNo, countPerPage,sortField,sortOrder,bookStatus==null?"":bookStatus,companyId==null?"":companyId,companyName==null?"":companyName);
	    }else if(sign.equals("uploadSelect")){
	    	json  = orderServiceImpl.returnMyBookOrderToo(orderId==null?"":orderId,staffCode,pageNo, countPerPage,sortField,sortOrder,bookStatus==null?"":bookStatus,companyId==null?"":companyId,companyName==null?"":companyName);
	    }else {
	      json  = orderServiceImpl.returnMyBookOrder(orderId==null?"":orderId,staffCode,pageNo, countPerPage,sortField,sortOrder,bookStatus==null?"":bookStatus,companyId==null?"":companyId,companyName==null?"":companyName);
	    }
		System.out.println(json);
		return json;//转向首页
	
	}
	
	
	/*
	 * 这个是和我的预约的进行联动的请求的，展示机床的信息的额及其后面的预约审核根据审核界面显示详细的机床信息的，两种逻辑是一样的！
	 * 
	 */
	@RequestMapping(value="myBookOrderMachine",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String rerurnMyBookOrderMachine(Model model,String orderId,String pageIndex,String pageSize,String sortField,String sortOrder){ 
	
	    int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
	    
		String json  = orderServiceImpl.returnMybookMachine(orderId, pageNo, countPerPage,sortField,sortOrder);
		System.out.println(json);
		
		return json;//转向首页

		
	}
	
	/*
	 * 这个是重新写的逻辑是根据订单及其审核人显示自己所能审核的界面！！！
	 * 
	 */
	@RequestMapping(value="myBookOrderMachine1",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String rerurnMyBookOrderMachine1(Model model,String orderId,String pageIndex,String pageSize,String sortField,String sortOrder){ 
		
		int pageNo = Integer.parseInt(pageIndex)+1;
		int countPerPage = Integer.parseInt(pageSize);
		
		String json  = orderServiceImpl.returnMybookMachine1(orderId, pageNo, countPerPage,sortField,sortOrder);
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
    public String rerurnAuditingBookingOrder(Model model,/*String key,*/String orderId,String pageIndex,String pageSize,String sortField,String sortOrder,String bookStatus,String companyId,String companyName){ 
		
		
		String staffCode="";
	/*	if(key.equals("1")) {
			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("user")).getUserId();
			staffCode =  ((User)session.getAttribute("user")).getStaffCode();
			
		}*/
		int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
	    
		String json  = orderServiceImpl.returnMyBookOrder(orderId,staffCode,pageNo, countPerPage,sortField,sortOrder,bookStatus==null?"":bookStatus,companyId==null?"":companyId,companyName==null?"":companyName);
		System.out.println(json);
		return json;//转向首页

		
	}
	/*
	 * 这个我再次重构的额代码，主要是用于普通的设备管理员的controller，因为对于管理员来说查询到比较苦难。于是我把它单独拎出来，其他的不需要的这些复杂逻辑的查询都是
	 * 放在其他的controller里面！
	 * 
	 */
	@RequestMapping(value="AuditingBookingOrder1.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String rerurnAuditingBookingOrder1(Model model,String pageIndex,String pageSize,String sortField,String sortOrder,String bookStatus){ 
		
//		定义这个的目的，就是根据不同的管理员实现不同的显示，只需要传入当前登陆人的账号【已经给其分配好权限的基础上！】及其索要审核的订单的状态，一般就是待审核的状态就好了
		String staffCode="";
		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		staffCode =  ((User)session.getAttribute("user")).getStaffCode();
			
		
		int pageNo = Integer.parseInt(pageIndex)+1;
		int countPerPage = Integer.parseInt(pageSize);
		
		String json  = orderServiceImpl.returnMyBookOrder1(staffCode,pageNo, countPerPage,sortField,sortOrder,bookStatus);
		System.out.println(json);
		return json;//转向首页
		
		
	}
    /*
     * 这主要是针对的是预约点进去之后的，然后进行各种审核的界面，比如审核通过还是不通过！【这个暂时不管用了，因为，我都把逻辑进行了替换！！！！】
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
    /*
     * 标识审核通过的时候!!!上面是以前写的针对通过或者不通过的时候的controller,现在逻辑叶不同了,同时也分开了写了,不再是一起写了!!!!下面在针对选的是否的时候呢
     */
    @RequestMapping(value="AuditingBookOrderAll13.action",produces = "text/html;charset=UTF-8") 
    @ResponseBody 
    public String rerurnAuditingBookingAll13(Model model,String bookStatus,String orderId,String checkAdvice,String data){ 
    	HttpSession session = request.getSession();
    	String userId = ((User)session.getAttribute("user")).getUserId();
    	String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
    	//System.out.println(orderId+"11"+bookStatus);
    	Map map = (Map) PluSoft.Utils.JSON.Decode(data);
    	String json  = orderServiceImpl.AuditingBookingAll13(orderId, bookStatus,staffCode,map);
    	System.out.println(json);
    	return json;//转向首页
    	
    	
    }
    
    //AddShiYanOrder.actio
    /*
     * 这主要是针对的是预约点进去之后的，进行删除订单
     */
    @RequestMapping(value="AuditingBookOrderDelete.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String rerurnAuditingBookOrderDelete(Model model,String unid,String orderId){ 
	
    	//System.out.println(orderId+"11"+bookStatus);

		String json = "";
		try {
			json = orderServiceImpl.deleteAuditingBookOrder(unid,orderId);
		} catch (Exception e) {
			json = "{\"result\":"+"\""+"删除失败！"+"\"}";
			DaiUtils.returnIsExceptionJson(e);
			System.out.println("抛出异常，触发回顾事件………………");
		}
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
//   动态更新前端的状态值预约审核界面中的！这个是新建的，由于在后面的完工审核也是用的这个页面，所以重新写一个进行解耦你
    
    @RequestMapping(value="AuditingBookOrderUpdateStatus1.action",produces = "text/html;charset=UTF-8") 
    @ResponseBody 
    public String rerurnAuditingBookOrderUpdateStatus1(Model model,String orderId){ 
    	
    	
    	String json  = orderServiceImpl.AuditingBookOrderUpdateStatus1(orderId);
    	System.out.println(json);
    	return json;//转向首页
    }
//   动态生成相应的审核表单
    
    @RequestMapping(value="updateAudits.action",produces = "text/html;charset=UTF-8") 
    @ResponseBody 
    public String rerurnUpdateAudits(Model model,String orderId){ 
    	
    	
    	String json  = orderServiceImpl.updateAuditsService(orderId);
    	System.out.println(json);
    	return json;//转向首页
    }
    
    @RequestMapping(value="AddShiYanOrder.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnAddShiYanOrder(Model model,String orderId,String customer,String connector,String connectorTel,String orderName,String productNum,String material){ 
 	

 		String json  = orderServiceImpl.addShiYanOrder(orderId,customer,connector,connectorTel,orderName,productNum,material);
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
 /* 
  * 
  * 这个是用于打印出带有二维码的打印单的是函数
  */
    @RequestMapping(value="LoadPrintBookOrder.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnLoadPrintBookOrder(Model model,String orderId,String pageIndex,String pageSize, String sortField, String sortOrder){ 
    		
    	    int pageNo = Integer.parseInt(pageIndex)+1;
    	    int countPerPage = Integer.parseInt(pageSize);
    	    
    		String json  = orderServiceImpl.returnMybookMachine(orderId, pageNo, countPerPage, sortField, sortOrder);
    		System.out.println(json);
    		
    		return json;//转向首页
 	}
    
    /**
     * @param model
     * @param machineId
     * @param deptId
     * @return
     * 这个是用来返回按照机床进行预约的加载的详细信息的
     */
    @RequestMapping(value="MachineShowOrderByMachine.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnLMachineShowOrderByMachine(Model model,String bday,String eday,String machineId,String deptId){ 
    		
    	    
    		String json  = orderServiceImpl.returnShowOrderByMachineService(machineId,deptId,bday,eday);
    		System.out.println(json);
    		
    		return json;//转向首页
 	}
    
    
    /**
     * @param model
     * @param machineId
     * @param deptId
     * @return 
     * 主要是用于返回前端上面一栏机床的信息
     */
    @RequestMapping(value="ShowOrderMachineInfoByMachine.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnBookMachByMach(Model model,String machineId,String deptId){ 
    		
    	    
    		String json  = orderServiceImpl.returnBookMachByMachService(machineId,deptId);
    		System.out.println(json);
    		
    		return json;//转向首页
 	}

	/**
	 * @param model
	 * @param unid
	 * @return
	 */
	@RequestMapping(value="deleteSelectedBookingInfo.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String rerurnDeleteSelectedBookingInfo(Model model,String unid,String orderId){ 
		
		
		String json = "";
		try {
			json = orderServiceImpl.deleteSelectedBookingInfo(unid,orderId);
		} catch (Exception e) {
			 DaiUtils.returnIsExceptionJson(e);
			 System.out.println("抛出异常，触发回顾事件………………");
		}
		System.out.println(json);
		
		return json;//转向首页
	}
	
	
	
	 @RequestMapping(value="loadCompletedOrderInfo.action",produces = "text/html;charset=UTF-8") 
 	@ResponseBody 
     public String rerurnLoadCompletedOrderInfo(Model model,String orderId){ 
 	

 		String json  = orderServiceImpl.LoadCompletedOrderInfoService(orderId);
 		System.out.println(json);
 		return json;//转向首页
 	}
	 
		/*
		 * 这个是和我的预约的进行联动的请求的，展示机床的信息的额及其后面的预约审核根据审核界面显示详细的机床信息的，两种逻辑是一样的！
		 * 
		 */
		@RequestMapping(value="loadCompletedBookingInfo.action",produces = "text/html;charset=UTF-8") 
		@ResponseBody 
	    public String rerurnLoadCompletedBookingInfo(Model model,String orderId,String pageIndex,String pageSize,String sortField,String sortOrder,String bookStatus){ 
		
		    int pageNo = Integer.parseInt(pageIndex)+1;
		    int countPerPage = Integer.parseInt(pageSize);
		    if(bookStatus.equals("上报完成")||bookStatus.equals("15")) {
		    	
		    	String json  = orderServiceImpl.returnCompletedBookMachine(orderId, pageNo, countPerPage,sortField,sortOrder);
				System.out.println(json);
				
				return json;//转向首页
		    }else {
		    	String json  = orderServiceImpl.returnMybookMachine(orderId, pageNo, countPerPage,sortField,sortOrder);
				System.out.println(json);
				
				return json;//转向首页
		    	
		    }
			
		    
		} 
		    
		    /**
		     * @param model
		     * @param list
		     * @param orderId
		     * @return
		     * 此方法是针对的是保存预约信息二次编辑的信息，后期还会加入进行判断是否是保存的信息，是否进行修改！
		     */
		    @RequestMapping(value="saveCompletedBookingInfo.action",produces = "text/html;charset=UTF-8") 
			@ResponseBody 
		    public String saveCompletedBookingInfo(Model model, String list ,String orderId,String completedAdvice){ 
//		    	JSONObject obj = new JSONObject(list);
		    	ArrayList<HashMap<String,String>> saveList = (ArrayList<HashMap<String,String>>) PluSoft.Utils.JSON.Decode(list);
		    	
		    	String jsonData="successful";
				try {	
					orderServiceImpl.saveCompletedBookingInfoService(saveList,orderId,completedAdvice);
			  }catch(Exception e){
				   jsonData="fail";
				   DaiUtils.returnIsExceptionJson(e);
				   System.out.println("抛出异常，触发回顾事件………………");
			   }
			
				jsonData="{\"result\":\""+jsonData+"\"}";
				System.out.println(jsonData);

		
		    	return jsonData; 
			    	
		}
		    
		    
		    
		
		    /**
		     * 此方法是用于最终的信息确认使用的，这里面显示的都是一切最终的确认信息
		     * @param model
		     * @param orderId
		     * @param completedAdvice
		     * @param bookStatus
		     * @return
		     */
		    @RequestMapping(value="completedOrderAudit.action",produces = "text/html;charset=UTF-8") 
			@ResponseBody 
		    public String completedOrderAudit(Model model,String orderId,String completedAdvice,String bookStatus){ 
//		    	JSONObject obj = new JSONObject(list);
		    	HttpSession session = request.getSession();
				String userId = ((User)session.getAttribute("user")).getUserId();
				String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
		    	String jsonData="successful";
				try {	
					orderServiceImpl.completedOrderAuditService(orderId,completedAdvice,bookStatus,staffCode);
			  }catch(Exception e){
				  
				   System.out.println("抛出异常，触发回顾事件………………");
			   }
			
				jsonData="{\"result\":\""+jsonData+"\"}";
				System.out.println(jsonData);

		
		    	return jsonData; 
			    	
		}
		    /**
		     * @param model
		     * @return
		     * @author dell
		     * 这个使用判断是否可以预约的！返回true或者false
		     */
		    @RequestMapping(value="isCanBook.action",produces = "text/html;charset=UTF-8") 
		    @ResponseBody 
		    public String isCanBookController(Model model){ 
		    	HttpSession session = request.getSession();
		    	String userId = ((User)session.getAttribute("user")).getUserId();
		    	String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
		    	 
		    	
		    	String jsonData=orderServiceImpl.isCanBookService(staffCode);
		   
		    	
		    	jsonData="{\"result\":\""+jsonData+"\"}";
		    	System.out.println(jsonData);
		    	return jsonData; 
		    	
		    }

		    
	 
	}
