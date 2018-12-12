package com.wl.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.TabableView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *  json 和 Map 以及Bean之间的转化，依赖的json的jar包为net.sf.json
 *  @author wanglu
 *  @date 2016.04.11
 *  @version 1.0
 * */
public class JsonConvert {
	
	/**
	 *  将List<Map<String, T>>转化为json字符串 ，返回的json字符串；
	 *  @author wanglu
	 *  @return json 字符串
	 *  @date 2016.04.11
	 *  @version 1.0
	 * */
	public static <T> String list2JsonStringObject(List<Map<String, T>> jsonList){
		return JSONArray.fromObject(jsonList).toString();
	}
	
	/**
	 *  将Map<String, String>转化为json字符串 ，返回的json字符串；
	 *  @author wanglu
	 *  @return json 字符串
	 *  @date 2016.04.11
	 *  @version 1.0
	 * */
	public static <T> String map2JsonString(Map<String, T> map){
		return JSONArray.fromObject(map).toString();
	}
	
	/**
	 *  json字符串转化为List<Map<String,Object>> ，返回List<Map<String,Object>>；
	 *  @author wanglu
	 *  @return List<Map<String,Object>>
	 *  @date 2016.04.11
	 *  @version 1.0
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * */
	public static <T> List<Map<String,T>> json2ListMap(String jsonString){
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List<Map<String,T>> mapListJson = (List)jsonArray;   
		return mapListJson;
	} 
	
	/**
	 *  json字符串转化为Map<String, Object> ，返回Map<String, Object>；
	 *  @author wanglu
	 *  @return Map<String, Object>
	 *  @date 2016.04.11
	 *  @version 1.0
	 * */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> json2Map (String jsonObjectData){
        JSONObject jsonObject = JSONObject.fromObject(jsonObjectData);  
        Map<String, T> mapJson = JSONObject.fromObject(jsonObject);  
        return mapJson;
	}
	
	/**
	 *  将bean转化为Map<String, Object> ，返回Map<String, Object>；
	 *  @author wanglu
	 *  @return Map<String, Object>
	 *  @date 2016.04.11
	 *  @version 1.0
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * */
	public static <T> Map<String, T> beanToMap(Object bean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String, T> result=new HashMap<String, T>();
		Method[] methods = bean.getClass().getDeclaredMethods();
		for (Method method:methods) {
			if (method.getName().startsWith("get")) {
				String field=method.getName();
				field=field.toLowerCase().substring(3);
				T value=(T)method.invoke(bean, null);
				result.put(field, value==null?null:value);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("11", "nihao");
		map1.put("12", "12");
		map1.put("13", 13);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("21", "nihao");
		map2.put("22", "22");
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		
		System.out.println(list2JsonStringObject(list));
		System.out.println(map2JsonString(map1));
		
		System.out.println(json2Map(map2JsonString(map1)));
		System.out.println(list2JsonStringObject(list));
		
	}
}
