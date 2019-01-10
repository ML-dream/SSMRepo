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
	
   
    
}
