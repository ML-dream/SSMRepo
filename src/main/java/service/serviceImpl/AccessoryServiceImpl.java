package service.serviceImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javaBean.user;
import mapper.UserMapper;
import service.AccessoryService;




@Service  //为了包扫描的时候这个Service被扫描到  
public class AccessoryServiceImpl implements AccessoryService{  
    
	public AccessoryServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
    UserMapper userMapper;

	

	@Override
	public user find(String id) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");  
    
      
   
    	 userMapper=(UserMapper)applicationContext.getBean("userMapper");  
		
		try {
			
			
			
			return userMapper.findUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
