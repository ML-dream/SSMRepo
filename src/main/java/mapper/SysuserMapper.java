/**
 * 
 */
package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wl.forms.Customer;
import com.wl.forms.Employee;

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年1月4日 下午2:47:55 
* 类说明 
*/

/*  
*    
* 项目名称：SSM   
* 类名称：SysuserMapper   
* 类描述：   
* 创建人：dell   
* 创建时间：2019年1月4日 下午2:47:55   
* @version        
*/

public interface SysuserMapper {
	public Employee selectLoadDefaultSysuserInfo(String staffCode);

	/**
	 * @param staffCode
	 * @return
	 */
	public List<Customer> selecShowMyCustomertList(@Param("staffCode") String staffCode);

	/**
	 * @param staffCode
	 * @return
	 */
	public int selectShowMyCustomertCount(String staffCode);

	/**
	 * @param sectionCode
	 * @param connectorTel
	 * @param staffCode
	 */
	public int updateDoEditEmployeeDetail(@Param("sectionCode") String sectionCode,@Param("connectorTel") String connectorTel,@Param("staffCode") String staffCode);

	/**
	 * @param sectionCode
	 * @param connectorTel
	 * @param staffCode
	 * @return
	 */
	public int updateDoEditCustomerDetail(@Param("sectionCode") String sectionCode,@Param("connectorTel") String connectorTel,@Param("staffCode") String staffCode);
}
