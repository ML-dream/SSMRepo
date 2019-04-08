/**
 * 
 */
package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.serviceImpl.OrderServiceImpl;
import service.serviceImpl.SysuserServiceImpl;

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年1月4日 下午2:42:22 
* 类说明 
*/

/*  
*    
* 项目名称：SSM   
* 类名称：SysuserController   
* 类描述：   
* 创建人：dell   
* 创建时间：2019年1月4日 下午2:42:22   
* @version        
*/
@Controller
public class SysuserController {


	@Autowired
	HttpServletRequest request;
	@Resource
	SysuserServiceImpl sysuserServiceImpl;


	/*
	 * 这个是和我的预约的进行联动的请求的，展示机床的信息的额及其后面的预约审核根据审核界面显示详细的机床信息的，两种逻辑是一样的！
	 * 
	 */
	@RequestMapping(value="LoadDefaultEmployeeInfo.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String rerurnLoadDefaultEmployeeInfo(Model model,String staffCode){ 
	
	   /* int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
	    */
		String json  = sysuserServiceImpl.loadDefaultEmployeeInfo(staffCode);
		System.out.println(json);
		
		return json;//转向首页

		
	}
	

	/*
	 * 针对的是前端显示我自己创建的客户！
	 * 
	 */
	@RequestMapping(value="ShowMyCustomer.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String rerurnShowMyCustomer(Model model,String staffCode){ 
	
	   /* int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
	    */
		String json  = sysuserServiceImpl.showMyCustomer(staffCode);
		System.out.println(json);
		
		return json;//转向首页

		
	}
	
	/**
	 * 执行更新员工的信息
	 */
	@RequestMapping(value="DoEditEmployeeDetail.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String returnDoEditEmployeeDetail(Model model,String sectionCode,String connectorTel,String staffCode) {
		return sysuserServiceImpl.DoEditEmployeeDetail(sectionCode,connectorTel,staffCode);

	}
	
	
	/**
	 * 删除更新客户的信息
	 */
	@RequestMapping(value="showCustomerDelete.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String showCustomerDelete(Model model,String companyId) {
		return sysuserServiceImpl.showCustomerDeleteService(companyId);

	}
	
	/**
	 * 删除户员工的信息
	 */
	@RequestMapping(value="showEmployeeDelete.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String showEmployeeDelete(Model model,String staffCode) {
		return sysuserServiceImpl.showCEmployeeDeleteService(staffCode);
		
	}
	
//	………………………………………………………………………………………………一下是关于设备管理员方面的controller………………………………………………………………………………………………………………………………………………
	/**
	 * 增加户设备管理员工的信息
	 */
	@RequestMapping(value="addMachineManager.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String addMachineManager(Model model,String staffCode,String staffName,String machineId) {
		return sysuserServiceImpl.addMachineManagerService(staffCode,staffName,machineId);
		
	}
	/**
	 * 删除户设备管理员工的信息
	 */
	@RequestMapping(value="deleteMachineManager.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String deleteMachineManager(Model model,String staffCode,String machineId) {
		return sysuserServiceImpl.deleteMachineManagerService(staffCode,machineId);
		
	}
	/**
	 * 加载户设备管理员工的信息
	 */
	@RequestMapping(value="loadMachineManager.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
	public String loadMachineManager(Model model,String pageIndex,String pageSize,String sortField,String sortOrder,String machineId) {
		int pageNo = Integer.parseInt(pageIndex)+1;
	    int countPerPage = Integer.parseInt(pageSize);
		return sysuserServiceImpl.loadMachineManagerService(pageNo,countPerPage,sortField,sortOrder,machineId);
		
	}
}
