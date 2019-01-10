/**
 * 
 */
package service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wl.forms.Customer;
import com.wl.forms.Employee;

import mapper.BookOrderMapper;
import mapper.SysuserMapper;
import service.SysuserService;

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年1月4日 下午2:46:27 
* 类说明 
*/

/*  
*    
* 项目名称：SSM   
* 类名称：SysuserServiceImpl   
* 类描述：   
* 创建人：dell   
* 创建时间：2019年1月4日 下午2:46:27   
* @version        
*/
@Service
public class SysuserServiceImpl {

	@Resource
	SysuserMapper sysuserMapper;
	
	/**
	 * @param staffCode
	 * @return
	 */
	public String loadDefaultEmployeeInfo(String staffCode) {
	
		Employee employee = sysuserMapper.selectLoadDefaultSysuserInfo(staffCode);
		String json = PluSoft.Utils.JSON.Encode(employee);
		return json;
	}

	/**
	 * @param staffCode
	 * @return
	 */
	public String showMyCustomer(String staffCode) {
		List<Customer> myCustomer = sysuserMapper.selecShowMyCustomertList(staffCode);
		
		int count = sysuserMapper.selectShowMyCustomertCount(staffCode);
		String jsonData = PluSoft.Utils.JSON.Encode(myCustomer);
		
		String json = "{\"total\":"+count+",\"data\":"+jsonData+"}";
		return json;
	}

}
