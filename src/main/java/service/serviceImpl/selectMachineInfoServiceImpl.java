package service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


import javaBean.machineInfo5505;
import javaBean.machineInfoSiemens;
import mapper.UserMapper;
import service.selectMachineInfoService;


@Service
public   class selectMachineInfoServiceImpl implements selectMachineInfoService {
	@Resource
	UserMapper userMapper;
	
	/*有点太方便了，通过这中在web。xml中的启动spring的方式进行启动之后，不用自己进行启动
	 * 然后启动spring容器的时候，就自然而然的使用了mybatis的动态代理的方法，进行启动，会直接吧生成的相应的mapper注入到bean之中，使用根据不需要每次都进行获取，直接@resource
	 * 而且使用了单例，明显感觉速度快很多了！
	 * 
	 * 总结下原始的流程：
	 * */
	/*@Resource
	ApplicationContext applicationContext；*/
	
	public machineInfo5505 selectMachineInfo5505ById(String machineId) {
		
		/*applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");  */
	   /* userMapper=(UserMapper)applicationContext.getBean("userMapper");  */
		
		try {

			return userMapper.machineInfo5505(machineId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String selectTotalMachineInfo(String machineId5501, String machineId5502, String machineId5503,
			String machineId5513, String machineId5505) {
		machineInfoSiemens  machineInfoSiemens1 =userMapper.selectTotalMachineInfo5501ById(machineId5501);
		machineInfoSiemens  machineInfoSiemens2 =userMapper.selectTotalMachineInfo5502ById(machineId5502);
		machineInfoSiemens  machineInfoSiemens3 =userMapper.selectTotalMachineInfo5503ById(machineId5503);
		machineInfoSiemens  machineInfoSiemens4 =userMapper.selectTotalMachineInfo5513ById(machineId5513);
		machineInfo5505      machineInfo5505    =userMapper.selectTotalMachineInfo5505ById(machineId5505);
		
		@SuppressWarnings("rawtypes")
		List list =new ArrayList();
		
		list.add(machineInfoSiemens1);
		list.add(machineInfoSiemens2);
		list.add(machineInfoSiemens3);
		list.add(machineInfoSiemens4);
		list.add(machineInfo5505);
		
		String json = PluSoft.Utils.JSON.Encode(list);
	
		return json;
	
	}




	

	

}
