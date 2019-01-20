import org.junit.Test;

import service.serviceImpl.OrderServiceImpl;

/**
 * 
 */

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年1月17日 下午12:38:50 
* 类说明 
*/

/*  
*    
* 项目名称：SSM   
* 类名称：testBookOrderInfo   
* 类描述：   
* 创建人：dell   
* 创建时间：2019年1月17日 下午12:38:50   
* @version        
*/

public class testBookOrderInfo {
	
	@Test
	public void test01(){
		
		new OrderServiceImpl().returnShowOrderByMachineService("5501","550");
		//	new OrderServiceImpl().returnBookMachByMachService("5501","550");
	}
	
}
