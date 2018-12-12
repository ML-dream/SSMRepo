package controller;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javaBean.machineInfo5505;
import javaBean.user;
import net.sf.json.JSONObject;
import service.AccessoryService;
import service.selectMachineInfoService;



@Controller
public class FindControllerTest { 
	
	@Resource
	AccessoryService accessoryService;
	@Resource
	selectMachineInfoService selectMachineInfoService;
	//跳转至列表页面
	@RequestMapping(value="dataFeedback.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String list(Model model,String machineId){ 
		
		user user  = accessoryService.find(machineId);
		//model.addAttribute("machineName",user);
		//String json=PluSoft.Utils.JSON.Encode(user);
		
		JSONObject map = JSONObject.fromObject(user);
		System.out.println(map);
		
		return map.toString();//转向首页

		/*System.out.println(user.getMachineName());
		return user.toString();*/
	}
	
	
	@RequestMapping(value="fanucShowData5505.action",produces = "text/html;charset=UTF-8") 
	@ResponseBody 
    public String fanucShowData5505(Model model,String machineId){ 
		System.out.println(machineId);
		machineInfo5505 machineInfo5505  = selectMachineInfoService.selectMachineInfo5505ById(machineId);
		//model.addAttribute("machineName",user);
		//String json=PluSoft.Utils.JSON.Encode(user);
		
		JSONObject map = JSONObject.fromObject(machineInfo5505);
		System.out.println(map);
		
		return map.toString();//转向首页

		/*System.out.println(user.getMachineName());
		return user.toString();*/
	}

	
	 
}
