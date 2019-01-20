package com.wl.testaction.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.github.pagehelper.PageInfo;

import service.serviceImpl.AccessoryServiceImpl;

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年1月12日 下午5:17:10 
*  
*/
public class DaiUtils {
	
	public static final Logger LOGGER = LogManager.getLogger(AccessoryServiceImpl.class);
	
	
	/**
	 * @param list
	 * 分页---类说明:用于将返回的查询的list的直接产生接送数据，然后可以直接返回到前端进行解析，注意的只是针对的的list，如果是一个list数据不能直接解析，因为里面
	 * @return
	 */
	public static <T>  String returnMiniUiJson(List<T> list) {
			
			LOGGER.info("进行分页…………");
			
			PageInfo<T> pageInfo = new PageInfo<T>(list);
	        long total = pageInfo.getTotal(); //获取总记录数
	
	        List<T> rowList = pageInfo.getList();
	        //	        使用进行转换可以直接将rowList转换成字符串
//	        String jsonData = PluSoft.Utils.JSON.Encode(list);
			String jsonData = net.sf.json.JSONArray.fromObject(rowList).toString();
			String json = "{\"total\":"+total+",\"data\":"+jsonData+"}";
			return json;
			
			
		}
	
	
	/**
	 * @param e
	 *事务管理方法
	 *
	 *模板代码
	 * public  void mainname() {
		

		// new ClassPathXmlApplicationContext("classpath:spring.xml").start();  
		String jsonData="操作成功";
		try {	
			accessoryService.test();
	  }catch(Exception e){
		   jsonData="操作失败";
	   }
	
	jsonData="{\"data\":\""+jsonData+"\"}";
	System.out.println(jsonData);

	}
	 */
	
	public static void returnIsExceptionJson( Exception e) {
		
		
		 LOGGER.error(e);
		 LOGGER.error("使用 + 号连接直接输出 e : " + e);
         LOGGER.error("使用 + 号连接直接输出 e.getMessage() : " + e.getMessage());
         LOGGER.error("使用 + 号连接直接输出 e.toString() : " + e.toString());
         // 使用逗号分隔，调用两个参数的error方法
         LOGGER.error("使用 , 号 使第二个参数作为Throwable : ", e);
         // 尝试使用分隔符,第二个参数为Throwable,会发现分隔符没有起作用，第二个参数的不同据，调用不同的重载方法
//         LOGGER.error("第二个参数为Throwable，使用分隔符打印 {} : ", e);
         // 尝试使用分隔符，第二个参数为Object,会发现分隔符起作用了，根据第二个参数的不同类型，调用不同的重载方法
         
         throw new RuntimeException("抛出异常，触发回顾事件………………");
     
	}
	
	

/**
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
    
    /**
     * @param jspName
     * 用来进行jsp前端的字符串的名字像oracleName的名字进行转换
     */
    public static String jspNameToOralceName(String jspName) {
		// TODO Auto-generated method stub
		
		char[] charArray = jspName.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		for (char c : charArray) {
			if(c >= 97 && c <= 122) {
				stringBuffer.append(c);
	        }else {
	        	stringBuffer.append("_");
	        	stringBuffer.append(c);
	        }
			
		}
		String string2 = stringBuffer.toString();
		return string2;
    }
	

}
