package JSOM;

import java.awt.List;
import java.util.ArrayList;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * Json�ر��� 
 *  commons-beanutils-1.7.0.jar
 *  ��������� java.lang.NoClassDefFoundError: org/apache/commons/beanutils/DynaBean 
 *  commons-collections-3.2.jar 
 *   ��������� java.lang.NoClassDefFoundError:  org/apache/commons/collections/map/ListOrderedMap 
 *commons-lang-2.3.jar
 ��������� java.lang.NoClassDefFoundError:  org/apache/commons/lang/exception/NestableRuntimeException  
 *commons-logging-1.0.4.jar
 *���������   java.lang.NoClassDefFoundError: org/apache/commons/logging/LogFactory  
 * ezmorph-1.0.6.jar
 * ���������  java.lang.NoClassDefFoundError: net/sf/ezmorph/Morpher 
 *      �������ײ⣬û�������myeclipse�����쳣������ִ�е�JSONObjectjsonObj = JSONObject.fromObject(jsonMap);��δ�����ж��ˣ����Ժ�Ĵ���Ҳ��ִ���ˣ�ȷʵû��������˵�Ĳ����쳣��   json-lib-2.1-jdk15.jar���������  
 *  java.lang.NoClassDefFoundError: net/sf/json/JSONObject */
public class TransTask {
	
	public  static java.util.List<FandT> init()
	{
		//FT1
		Task task1=new Task();
		task1.setDuration(2334);
		task1.setUID("p1");
		task1.setFinish("20070101");
		task1.setPercentComplete(0);
		task1.setStart("20070102");
		task1.setName("task1");
		
		Task task2=new Task();
		task2.setDuration(2334);
		task2.setUID("p2");
		task2.setFinish("20070101");
		task2.setPercentComplete(0);
		task2.setStart("20070102");
		task2.setName("task2");
			
		Factory factory =new Factory();
		factory.setName("P1");
		factory.setUID("1");
		
		FandT ft1=new FandT();
		
		java.util.List<Task> TaskList1=new ArrayList<Task>();
		TaskList1.add(task1);
		TaskList1.add(task2);
		
		ft1.setTasks(TaskList1);
		ft1.setName(factory.getName());
		ft1.setUID(factory.getUID());
		
		//FT2
		
		Task task3=new Task();
		task3.setDuration(2334);
		task3.setUID("p3");
		task3.setFinish("20070101");
		task3.setPercentComplete(0);
		task3.setStart("20070102");
		task3.setName("task1");
		
		Task task4=new Task();
		task4.setDuration(2334);
		task4.setUID("p4");
		task4.setFinish("20070101");
		task4.setPercentComplete(0);
		task4.setStart("20070102");
		task4.setName("task2");
		
		Factory factory2 =new Factory();
		factory2.setName("P2");
		factory2.setUID("2");
		
		FandT ft2=new FandT();
		
		java.util.List<Task> TaskList2=new ArrayList<Task>();
		TaskList2.add(task1);
		TaskList2.add(task2);
		
		ft2.setTasks(TaskList2);
		ft2.setName(factory2.getName());
		ft2.setUID(factory2.getUID());
		
		//FT3
		Factory factory3 =new Factory();
		factory3.setName("P2");
		factory3.setUID("2");

		FandT ft3=new FandT();
	
		ft3.setName(factory3.getName());
		ft3.setUID(factory3.getUID());
		
		//FTList
		java.util.List<FandT> ftList=new ArrayList<FandT>();
		ftList.add(ft1);
		ftList.add(ft2);
		ftList.add(ft3);
			
		return ftList;
	}
	
	public static void main(String []args){
	
//		
//		JSONObject json;
//		json = JSONObject.fromObject(TransTask.init());
//		System.out.println(json.toString());
		JSONArray json1;
		json1 = JSONArray.fromObject(TransTask.init());
		System.out.println(json1.toString());
	}
    //��jsonת����json����
	public static <T> T jsonToObject(String jsonString, Class<T> pojoCalss) {
		Object pojo;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoCalss);
		return (T) pojo;
	}
}
