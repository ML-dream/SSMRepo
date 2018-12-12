package JSOM;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.LinkedHashMap;

import javax.print.DocFlavor.STRING;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.sun.xml.internal.messaging.saaj.util.CharReader;

/*��Ҫ����jackson���jar��-->��仰��ʲô��˼������*/
public class JackSonTrans {

	public  static java.util.List<FandT> init()
	{
		//FT1
		//��ʼ��Tast
		Task task1=new Task();
		task1.setName("task1");
		task1.setUID("p1");
		task1.setStart("2007-01-03T00:00:00");
		task1.setFinish("2007-01-03T23:59:59");
		task1.setPercentComplete(26);
		task1.setDuration(1*24*3600);
		
		task1.set__Index(0);                      //_index�Ǵ��
		task1.set_id(1);                          
		task1.setParentUID(1);

        //��ʼ��factory
		Factory factory =new Factory();
		factory.setName("P1");
		factory.setUID("1");
		
		factory.set_height(27);
		factory.set_id(1);
		factory.set_level(0);
		factory.set_pid(-1);
		factory.set_uid(1);
		
		FandT ft1=new FandT();

		java.util.List<Task> TaskList1=new ArrayList<Task>();
		TaskList1.add(task1);

		ft1.setTasks(TaskList1);
		ft1.setName(factory.getName());
		ft1.setUID(factory.getUID());
		
		ft1.set_height(factory.get_height());
		ft1.set_id(factory.get_id());
		ft1.set_level(factory.get_level());
		ft1.set_pid(factory.get_pid());
		ft1.set_uid(factory.get_uid());
		
		//FT2
		Task task3=new Task();
		task3.setName("task3");
		task3.setUID("p3");
		task3.setStart("2007-01-09T00:00:00");
		task3.setFinish("2007-01-10T23:59:59");
		task3.setPercentComplete(100);
		task3.setDuration(2*24*60*60);

		Task task4=new Task();
		task4.setName("task4");
		task4.setUID("p4");
		task4.setStart("2007-01-12T00:00:00");
		task4.setFinish("2007-01-16T23:59:59");
		task4.setPercentComplete(50);
		task4.setDuration(5*24*60*60);


		Factory factory2 =new Factory();
		factory2.setName("P2");
		factory2.setUID("2");

		FandT ft2=new FandT();

		java.util.List<Task> TaskList2=new ArrayList<Task>();
		TaskList2.add(task3);
		TaskList2.add(task4);

		ft2.setTasks(TaskList2);
		ft2.setName(factory2.getName());
		ft2.setUID(factory2.getUID());

		//FT3
		Factory factory3 =new Factory();
		factory3.setName("P3");
		factory3.setUID("3");

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

		//JackSonTrans.PrintFandTList(JackSonTrans.ListBack(JackSonTrans.JsonBack(DB.getFandTList())));
//		JackSonTrans.PrintFandTList(JackSonTrans.ListBack(JackSonTrans.getJsonFromFile()));

		//JackSonTrans.GantListBack(s);
		//System.out.println(JackSonTrans.JsonBack(JackSonTrans.init()));
	}

	/*param  List<FantT>
   return  Json*/
	//����FandT���ϣ�����һ��Json
	public static String  JsonBack2(List<Fant2> ftList){

		StringWriter sw = new StringWriter();

		try {
			ObjectMapper mapper = new ObjectMapper();  
			mapper.writeValue(sw,ftList);  //��ftListд��sw�У�����
			

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��FandT��Jsonת������쳣");
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��FandT��Jsonת������쳣");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��FandT��Jsonת������쳣");
		}
		System.out.println("sw.toString()=="+sw.toString());
	    String oldString = sw.toString();
	    String newString = oldString
	    .replaceAll(",\"_id\":0", "")
	    .replaceAll(",\"_uid\":0", "")
	    .replaceAll(",\"_pid\":0", "")
	    .replaceAll(",\"_level\":0", "")
	    .replaceAll(",\"_height\":0", "")
	    .replaceAll(",\"Tasks\":\\[\\]", "");
	    //System.out.println("newString========"+newString);
		return newString;
		
	}

	
	public static String  JsonBack(java.util.List<FandT> ftList){

		StringWriter sw = new StringWriter();

		try {
			ObjectMapper mapper = new ObjectMapper();  
			mapper.writeValue(sw,ftList);  //��ftListд��sw�У�����
			

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��FandT��Jsonת������쳣");
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��FandT��Jsonת������쳣");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��FandT��Jsonת������쳣");
		}
		System.out.println("sw.toString()=="+sw.toString());
	    String oldString = sw.toString();
	    String newString = oldString
	    .replaceAll(",\"_id\":0", "")
	    .replaceAll(",\"_uid\":0", "")
	    .replaceAll(",\"_pid\":0", "")
	    .replaceAll(",\"_level\":0", "")
	    .replaceAll(",\"_height\":0", "")
	    .replaceAll(",\"Tasks\":\\[\\]", "");
	    //System.out.println("newString========"+newString);
		return newString;
		
	}
	/*��json���(����ݿ��ȡ��json)
	 * ������Ganttҳ�淵�ص����*/
	//����Json������һ��FandT���ϣ�����
	public static java.util.List<FandT>  ListBack(String json){
		java.util.List<FandT> ftList=new ArrayList<FandT>();
		ObjectMapper mapper = new ObjectMapper(); 

		try {
			FandT[] arr=mapper.readValue(json, FandT[].class);
			for(int i=0;i<arr.length;i++)
			{
				ftList.add(arr[i]);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ListBack出现异常JsonParseException!!!");
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ListBack出现异常JsonMappingException!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ListBack出现异常IOException!!!");
		}

		return ftList;
	}
    //��ʾ�����FandT����
	public static void PrintFandTList(java.util.List<FandT> ftlist) {
		for(int i=0 ;i<ftlist.size();i++)
		{
			System.out.println("FactoryName: "+ftlist.get(i).getName()+"   "+"FactoryUID: "+ftlist.get(i).getUID());

			for(int j=0;j<ftlist.get(i).getTasks().size();j++)
			{
				System.out.println(
						ftlist.get(i).getTasks().get(j).getName()+" "+
						ftlist.get(i).getTasks().get(j).getUID()+" "+
						ftlist.get(i).getTasks().get(j).getStart()+" "
						+ftlist.get(i).getTasks().get(j).getFinish()+" "
						+ftlist.get(i).getTasks().get(j).getPercentComplete()+" "+
						ftlist.get(i).getTasks().get(j).getDuration()
				);
			}
		}
	}
	
	
	/**String str="i love china!"
       File txt=new File("C:\Documents and Settings\Administrator\����\nihao.txt");
       if(!txt.exists()){
         txt.createNewFile();
       }
       byte bytes[]=new byte[512];
       bytes=str.getBytes();   //�¼ӵ�
       int b=str.length();   //��
       FileOutputStream fos=new FileOutputStream(txt);
       fos.write(bytes,0,b);
       fos.close();
	 * 
	 * 
	 * public class StringReaderTest {
	
	public static final String SOURCE = "�ܳ��ܳ�...";
	
	public static void main(String[] args) throws IOException {
		
		int length = 0; //ÿһ�ζ�ȡ�ĳ���
		
		char[] buffer = new char[2048]; //�軺�����ֵΪ2048�ַ�
		
		//�ַ�תΪ�ַ���
		BufferedReader br = new BufferedReader(new StringReader(SOURCE));
		
		while((length = br.read(buffer)) != -1){ //�������Ĳ���ĩβ
			
			System.out.println(new String(buffer, 0, length)); //������������
		}
	}

}
	 */
//	
//	public static void getFileFromJson(String Json_String){
//		try{
////			byte [] buffer=new byte[1024];
////			BufferedReader br =new BufferedReader(new StringReader(Json_String));
//			FileOutputStream fos= null;
//			fos= new FileOutputStream("G:\\wldata.txt");
////			while((br.readLine())!=null){
////				fos.write(buffer);
////			}
//			fos.write(Json_String.getBytes());
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("getFileFromJsonת�������쳣������");
//		}
//	}
	
	/*���ļ��ж�ȡjson ���*/
	//��ָ����·��ȡ��json��ݣ�����json�ַ�
//	public  static String getJsonFromFile ()
//	{
//		String temp;
//		StringBuffer str=new StringBuffer();
//		File f=new File("C:\\apache-tomcat-6.0.39\\webapps\\javawebZX\\GT\\data\\lpfdata.txt");
//		FileInputStream fis=null;
//		try {
//			fis=new FileInputStream(f);
//			//�ֽ������൱�ڻ��棬ÿ�ζ�ȡ1024���ֽڡ����ļ����ʱ���ܹ����ִ��������
//			byte [] bytes=new byte[1024];
//			int n=0;
//			//���ļ����벢�����
//			while((n=fis.read(bytes))!=-1){
//				String s=new String(bytes,0,n);//�½��ַ�ķ�����
//				//System.out.println(s);
//				str.append(s);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("���ļ��ж�ȡJson��ݳ����쳣������");
//		}finally{
//			//�ر��ļ�����
//			try {
//				fis.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("���ļ��ж�ȡJson��ݵĹر���Դʱ�����쳣������");
//			}
//		}
//		temp=str.toString();
//		return temp;
//	}
	
	/* ���¶�����FandT,����������ӵ�json ��û����
	 * �����Ҫ�ã���Ҫ���¶� Object temp=s.get(j);��string�ַ���н���
����json��� ����ҳ�����json
	 * ����Ganttҳ�淵�ص����*/
//	public static void  GantListBack(String str){
//		java.util.List<FandT> ftList=new ArrayList<FandT>();
//		ObjectMapper objectMapper = new ObjectMapper(); 	
//		try {
//			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(str, List.class);
//			System.out.println(list.size());
//			for (int i = 0; i < list.size(); i++) {
//				Map<String, Object> map = list.get(i);
//				Set<String> set = map.keySet();
//				for (Iterator<String> it = set.iterator();it.hasNext();) {
//					String key = it.next();
//					FandT ft=new FandT();
//					if(key.equals("Name")){
//						ft.setName((String)map.get(key));
//						//System.out.println(key + ":" + map.get(key));
//					}else if(key.equals("UID")){
//						int s=(Integer)map.get(key);
//						ft.setUID(s);
//						//System.out.println(key + ":" + map.get(key));
//					}else if(key.equals("Tasks")){
//						//System.out.println(key + ":" + map.get(key)+map.get(key).getClass());
//						ArrayList<Object> s= new ArrayList<Object>();
//							s=(ArrayList<Object>) map.get(key);	
//							System.out.println(s.size());
//						for(int j =0;j<s.size();j++){
//							
//						Object temp=s.get(j);
//				
//							System.out.println(temp.toString());
//						}
//						
//					}
//					ftList.add(ft);
//
//				}
//				//JackSonTrans.PrintFandTList(ftList);
//			}
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}


}
