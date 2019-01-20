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

	/**
	 * @param sectionCode
	 * @param connectorTel
	 * @param staffCode
	 * @return
	 * 
	 * 注意：此处由于更新用户的同时需要同时更新customer表！
	 */
	public String DoEditEmployeeDetail(String sectionCode, String connectorTel, String staffCode) {
		// TODO Auto-generated method stub
		String json;
		int returnInfo = sysuserMapper.updateDoEditEmployeeDetail(sectionCode,connectorTel,staffCode);
		int returnCustomerInfo=sysuserMapper.updateDoEditCustomerDetail(sectionCode, connectorTel, staffCode);
		json=returnInfo>=1 &&returnCustomerInfo>=1?"操作成功":"操作失败";
		
		return "{\"data\":\""+json+"\"}";
	}

}
