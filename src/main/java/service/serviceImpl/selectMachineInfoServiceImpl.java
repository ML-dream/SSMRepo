package service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javaBean.machineInfo5505;
import mapper.UserMapper;
import service.selectMachineInfoService;


@Service
public  class selectMachineInfoServiceImpl implements selectMachineInfoService {
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

			return userMapper.selectMachineInfo5505ById(machineId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	

}
