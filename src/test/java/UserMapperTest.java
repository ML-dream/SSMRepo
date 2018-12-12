

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javaBean.user;
import mapper.UserMapper;




public class UserMapperTest {
private ApplicationContext applicationContext;  
@Autowired  
UserMapper userMapper;
			
	
    @Before  
    public void setup() throws Exception{  
        applicationContext=new ClassPathXmlApplicationContext("classpath:spring.xml");  
    }  
      
    @Test  
    public void testFindUserById() throws Exception{  
    	UserMapperTest test=new UserMapperTest();
    	
    	UserMapper userMapper=(UserMapper)applicationContext.getBean("userMapper");  
       
    	user user=userMapper.findUserById("5504");  

        System.out.println(user.getNckType()+user.getMachineName()+user.getMachineSpec());  
    }  
}
