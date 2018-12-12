/**
 * 项目名称: webTest
 * 创建日期：2016-7-12
 * 修改历史：
 *		1.[2016-7-12]创建文件 by Flair
 */
package com.wl.tools;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
 
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

 
/**
 * 请求信息封装到对象
 * @author   flair
 * @Date     2016-07-12     
 */
public class MyBeanUtil {
 
    static {
        // 在封装之前 注册转换器
        ConvertUtils.register(new DateTimeConverter(), java.util.Date.class);
        ConvertUtils.register(new ChineseUTF8Convert(), null);
    }
 
    /**
     * 请求信息封装到对象
     * @param request 请求信息
     * @param clazz 封装对象
     */
    @SuppressWarnings("unchecked")
    public static <E extends Serializable> E get(HttpServletRequest request, Class<E> clazz) {
        if (request == null)
            throw new IllegalArgumentException("FormBeanUtil.get中的request为空");
        E obj = null;
        try {
            obj = clazz.newInstance();
            Map<String, String[]> parameterMap = request.getParameterMap();
//            for (String key : parameterMap.keySet()) {
//            	String[] value = parameterMap.get(key);
//				for (int i = 0; i < value.length; i++) {
//					value[i] = ChineseCode.toUTF8(value[i]);
//				}
//				parameterMap.put(key, value);
//			}
            
            Iterator<Map.Entry<String, String[]>> it = parameterMap.entrySet().iterator();
            Map<String, String[]> midMap = new HashMap<String, String[]>();
            while (it.hasNext()) {
				Map.Entry<String, String[]> entry = it.next();
				String key = entry.getKey();
				String[] value = parameterMap.get(key);
				for (int i = 0; i < value.length; i++) {
					value[i] = ChineseCode.toUTF8(value[i]);
				}
				midMap.put(key, value);
				
			}
            BeanUtils.populate(obj, midMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
