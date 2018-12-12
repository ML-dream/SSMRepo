package JSOM;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.wl.common.DatabaseCommon;

public class DB {
//�õ�FandT�ļ���!!!����ݿ�õ�task��factory����ϳ�FandT���Ϸ���
	/**
	 * 得到所有零件号，显示每个零件的工序甘特图
	 */
	public  static java.util.List<FandT> getFandTList(){

		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql;

		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);
			//jdbc:microsoft:sqlserver://127.0.1.1:1433;databaseName=spdb
			
			sql="select distinct item_id,order_id from partplan";       //得到零件号和零件名称,此处规定物料号不能重复！！！！！
			//sql="select item_id from item where item_typeid = "L";  //从物料表里面 得到零件号
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			ResultSet namers = null;
			while(rs.next()){
				Factory factory=new Factory();
				factory.setUID(rs.getString(1));    //此处字符串不能转化成int类型
				
				//查找零件的名称
				String namesql = "select ITEM_NAME from item where ITEM_ID = '"+rs.getString(1)+"'";
				System.out.println("namesql====="+namesql);
				
				ps=connection.prepareStatement(namesql);
				namers=ps.executeQuery();
				namers.next();
				factory.setName(namers.getString(1));
				factoryList.add(factory);	
			}

			for(int i=0;i<factoryList.size();i++)
			{
				java.util.List<Task> taskList=new ArrayList<Task>();
				FandT ft=new FandT();

				ft.setName(factoryList.get(i).getName());
				ft.setUID(factoryList.get(i).getUID());

				String factoryname=factoryList.get(i).getUID();
//				sql="select OPER_ID,NUM,START_TIME,END_TIME,to_char(END_TIME-START_TIME)*24*3600 " +
//						"from process_plan where EQUIPMENT_DRAWID= " +factoryname;
//				sql = "select part_num, PLAN_BGTIME, PLAN_EDTIME" +
//						" from partplan where item_id = '"+factoryname+"'";
				
//				sql = "select part_num" +
//					",to_date(PLAN_BGTIME,'yyyy-mm-dd,hh24:mi:ss')" +
//					",to_date(PLAN_EDTIME,'yyyy-mm-dd,hh24:mi:ss')" +
//					",to_char(to_date(PLAN_EDTIME,'yyyy-mm-dd,hh24:mi:ss') - to_date(PLAN_BGTIME,'yyyy-mm-dd,hh24:mi:ss'))*24*3600 " +
//					"from partplan where item_id = '"+factoryname+"'";
				
				String fo_no_sql = "select oper_id ,START_TIME,END_TIME,to_char(END_TIME-START_TIME)*24*3600  from process_plan where item_id = '"+factoryname+"'";
				System.out.println("fo_no_sql===="+fo_no_sql);
				
				ps=connection.prepareStatement(fo_no_sql);
				rs=ps.executeQuery();

				while (rs.next()) {
					System.out.println("进入循环体！！！！！！！！！！");
					
					Task task=new Task();
					task.setUID(rs.getString(1));
					
					String processnamesql = 
						"select FO_OPNAME from FO_DETAIL where ITEM_ID = '"+factoryname+"'  and  FO_OPERID = '"+rs.getString(1)+"'";
					System.out.println("processnamesql===="+processnamesql);
					ps=connection.prepareStatement(processnamesql);
					ResultSet processnamers=ps.executeQuery();
					processnamers.next();
					
					task.setName(processnamers.getString(1));
					task.setStart(rs.getString(2));
					task.setFinish(rs.getString(3));
					task.setPercentComplete(0);
					task.setDuration(rs.getInt(4));

					taskList.add(task);
				}
				ft.setTasks(taskList);
				ftList.add(ft);
				JackSonTrans.PrintFandTList(ftList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFandTList()出现异常了！！！");
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
				if (rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("getFandTList()资源关闭出现异常！！！");
			}
		}
		return ftList;
	}
	
	/**
	 * 找到所有的设备，然后显示每台设备上的工序甘特图
	 * @return
	 */
	public  static java.util.List<FandT> getFandTList_factory(){

		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql;

		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);
			//jdbc:microsoft:sqlserver://127.0.1.1:1433;databaseName=spdb
			
			//得到所有设备！！从process_plan 表里面的 processplan_a中获得！！！！！
			//sql="select factoryid,factoryname from factory order by factorytype";
			sql = "select distinct processplan_a from process_plan order by processplan_a ";
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Factory factory=new Factory();			
				factory.setUID(rs.getString(1));    //此处字符串不能转化成int类型
				factory.setName(rs.getString(1));
				factoryList.add(factory);	
			}

			for(int i=0;i<factoryList.size();i++)
			{
				java.util.List<Task> taskList=new ArrayList<Task>();
				FandT ft=new FandT();

				ft.setName(factoryList.get(i).getName());
				ft.setUID(factoryList.get(i).getUID());

				String factoryname=factoryList.get(i).getUID();
				//查找每个机床设备对应的加工时间！！！！！此处是在process_plan 表中查询的！！！！！
				//sql="select factoryid,factoryname,starttime,endtime,to_char(endtime-starttime)*24*3600 from factory_use where factoryname='" +factoryname+"'";
				
				sql = "select processplan_a, processplan_a,start_time,end_time,to_char(end_time-start_time)*24*3600  from process_plan where processplan_a ='"+factoryname+"'";
				System.out.println("sql==="+sql);
				
				ps=connection.prepareStatement(sql);
				rs=ps.executeQuery();
				while (rs.next()) {
					Task task=new Task();
					task.setUID(rs.getString(1));
					task.setName(rs.getString(2));
					System.out.println("starttime=="+rs.getString(3));
					task.setStart(rs.getString(3));
					System.out.println("endtime=="+rs.getString(4));
					task.setFinish(rs.getString(4));
					task.setDuration(rs.getInt(5));

					taskList.add(task);
				}
				ft.setTasks(taskList);
				ftList.add(ft);
				//JackSonTrans.PrintFandTList(ftList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFandTList_factory()出现异常了！！！");
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("getFandTList_factory()资源关闭出现异常！！！");
			}
		}
		return ftList;
	}
	
	/**
	 * 找出加工单个零件的所有机床，然后显示甘特图，这个好像没有用到
	 * @param partID
	 * @return
	 */
	public  static java.util.List<FandT> getFandTListByID(String partID){

		System.out.println("partID��ֵ��"+partID);
		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql;

		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);
			//jdbc:microsoft:sqlserver://127.0.1.1:1433;databaseName=spdb
			
			sql="select PROCESSPLAN_A from process_plan where EQUIPMENT_DRAWID='"
				+partID+"' group by PROCESSPLAN_A";
			//sql="select * from factory";
			System.out.println(sql);
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Factory factory=new Factory();
				System.out.println("rs.getInt(1)="+rs.getInt(1));
				factory.setUID(rs.getString(1));
				factory.setName(rs.getString(1));
				factoryList.add(factory);
			}

			System.out.println("factoryList.size()=="+factoryList.size());
			for(int i=0;i<factoryList.size();i++)
			{

				java.util.List<Task> taskList=new ArrayList<Task>();
				FandT ft=new FandT();
				ft.setName(factoryList.get(i).getName());
				ft.setUID(factoryList.get(i).getUID());
				String factoryUID=factoryList.get(i).getUID();
				sql="select PLAN_ID,PROCESSPLAN_B,START_TIME,END_TIME,NUM,FINISHNUM " +
						"from process_plan where PROCESSPLAN_A= " +factoryUID;
				ps=connection.prepareStatement(sql);
				rs=ps.executeQuery();
				while (rs.next()) {
					Task task=new Task();
					task.setUID(rs.getString(1));
					task.setName(rs.getString(2));
					task.setStart(rs.getString(3));
					task.setFinish(rs.getString(4));
					task.setPercentComplete(0);
					task.setDuration(0);
					
					System.out.println(task.getUID());
					System.out.println(task.getName());
					System.out.println(task.getStart());
					System.out.println(task.getFinish());
					System.out.println(task.getPercentComplete());
					System.out.println(task.getDuration());

					taskList.add(task);
				}
				ft.setTasks(taskList);
				ftList.add(ft);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFandTList()�õ�FandT�����쳣");
		}finally{
			//�ر���Դ
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("�õ�FandTʱ�ر���Դ�����쳣������");
			}
		}
		//	JackSonTrans.PrintFandTList(ftList);
		return ftList;
	}
	
	/**
	 * 找出加工单个零件的所有机床，然后显示甘特图
	 * @param partID
	 * @return
	 */
	public  static java.util.List<FandT> getFandTListByID_new(String partID){

		System.out.println("partID=="+partID);
		
		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql;
		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);			
			//此语句查询的是加工一个零件要用到的所有机床！！！！！
			//sql="select PROCESSPLAN_A from process_plan where EQUIPMENT_DRAWID='"+partID+"' group by PROCESSPLAN_A";
			//sql = "select distinct PROCESSPLAN_A from process_plan where item_id = '"+partID+"' order by PROCESSPLAN_A";
			sql = "select distinct wcid PROCESSPLAN_A from process_plan where item_id = '"+partID+"' order by PROCESSPLAN_A";
			
			System.out.println(sql);
			
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Factory factory=new Factory();
				factory.setUID(rs.getString(1));
				
				//此处要查询设备名称！！！！！！！！根据设备编号查询名称！
				
				factory.setName(rs.getString(1));
				System.out.println("factory.getname=="+rs.getString(1));
				factoryList.add(factory);
				
			}
			System.out.println("factoryList.size()=="+factoryList.size());
			for(int i=0;i<factoryList.size();i++)
			{
				java.util.List<Task> taskList=new ArrayList<Task>();
				FandT ft=new FandT();
				ft.setName(factoryList.get(i).getName());
				ft.setUID(factoryList.get(i).getUID());
				String factoryUID=factoryList.get(i).getUID();
				//此语句查询机床上对应零件的加工工序
//				sql="select OPER_ID,START_TIME,END_TIME,to_char(END_TIME-START_TIME)*24*3600,NUM,FINISHNUM " +
//						"from process_plan where PROCESSPLAN_A='"+factoryUID+"'";
				sql = "select OPER_ID, START_TIME, END_TIME, to_char(END_TIME-START_TIME)*24*3600, NUM, FINISHNUM " +
						"from process_plan where  wcid='"+factoryUID+"' and item_id = '"+partID+"' order by OPER_ID";
				System.out.println("sql=="+sql);
				
				ps=connection.prepareStatement(sql);
				rs=ps.executeQuery();
				while (rs.next()) {
					Task task=new Task();
					task.setUID(rs.getString(1));
					
					
					String processnamesql = 
						"select FO_OPNAME from FO_DETAIL where ITEM_ID = '"+partID+"'  and  FO_OPERID = '"+rs.getString(1)+"'";
					System.out.println("processnamesql===="+processnamesql);
					ps=connection.prepareStatement(processnamesql);
					ResultSet processnamers=ps.executeQuery();
					processnamers.next();
					
					
					//此处不一定对，要注意！！！！
					//task.setName(rs.getString(1));
					task.setName(processnamers.getString(1));
					
					
					task.setStart(rs.getString(2));
					task.setFinish(rs.getString(3));
					task.setPercentComplete(0);
					task.setDuration(rs.getInt(4));
					
					taskList.add(task);
				}
				ft.setTasks(taskList);
				ftList.add(ft);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFandTListByID_new出现异常！！！");
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
				if (rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("getFandTListByID_new的ps，cn关闭异常！！！");
			}
		}
		//	JackSonTrans.PrintFandTList(ftList);
		return ftList;
	}
	
    //����FandT���ϣ�����������ݿ�ȡ����task��factory����д����ݿ⣡����
	
	public static void saveFandTList(java.util.List<FandT> ftList){
		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql;

		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);
			for(int i=0;i<ftList.size();i++)
			{
				for(int j=0;j<ftList.get(i).getTasks().size();j++)
				{
					System.out.println("ftList.get(i).getTask().get(j).getFinish()="+ftList.get(i).getTasks().get(j).getFinish());
					System.out.println("ftList.get(i).getTask().get(j).getStart()="+ftList.get(i).getTasks().get(j).getStart());
					sql = "update process_plan set " +
					"START_TIME=to_date('"+ftList.get(i).getTasks().get(j).getStart().replace('T', ' ')+"','yyyy-mm-dd,hh24:mi:ss')," +
					"END_TIME=to_date('"+ftList.get(i).getTasks().get(j).getFinish().replace('T', ' ')+"','yyyy-mm-dd,hh24:mi:ss') " +
					"where OPER_ID='"+ftList.get(i).getTasks().get(j).getUID()+"'" ;
					System.out.println("sql=="+sql);
					ps=connection.prepareStatement(sql);
					ps.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("saveFandTList出现异常！！！");
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("saveFandTList资源关闭异常！！！");
			}
		}
//		try {
//			Class.forName(DatabaseCommon.driver);
//			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);
//			//jdbc:microsoft:sqlserver://127.0.1.1:1433;databaseName=spdb
//			/*ɾ�����ű��*/
//			ps=connection.prepareStatement(" delete  from  task");
//			int num=ps.executeUpdate();	
//			ps=connection.prepareStatement(" delete  from  factory");
//			num=ps.executeUpdate();
//		
//			/*д�� Factory*/
//			for(int i=0;i<ftList.size();i++){
//				sql ="insert into factory values("+ftList.get(i).getUID()+","+"'"+ftList.get(i).getName()+"')";
//				System.out.println(sql);
//				ps=connection.prepareStatement(sql);
//				num=ps.executeUpdate();
//			}
//			/*д�� Task*/
//			for(int i=0;i<ftList.size();i++)
//			{
//				for(int j=0;j<ftList.get(i).getTask().size();j++)
//				{
//					sql ="insert into task values('"
//						+ftList.get(i).getTask().get(j).getUID()+"','"
//						+ftList.get(i).getUID()+"','"
//						+ftList.get(i).getTask().get(j).getName()+"',to_date('"
//						+ftList.get(i).getTask().get(j).getStart().replace('T', ' ')+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"
//						+ftList.get(i).getTask().get(j).getFinish().replace('T', ' ')+"','yyyy-mm-dd,hh24:mi:ss'),'"
//						+(int)ftList.get(i).getTask().get(j).getPercentComplete()+"','"
//						+ftList.get(i).getTask().get(j).getDuration()+"')";
//					System.out.println(sql);
//					ps=connection.prepareStatement(sql);
//					num=ps.executeUpdate();
//				}
//			}
//
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("����FandTʱ�����쳣������");
//		}finally{
//			//�ر���Դ
//			try {
//				if(ps!=null){
//					ps.close();
//				}
//				if(connection!=null){
//					connection.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("�ر�saveFandT����Դʱ�����쳣������");
//			}
//		}

	}
	
	/*
	 * 找出所有已经做好派工的机床，并显示此机床上的工序。
	 * */
	public  static java.util.List<FandT> getDispatchedMachineGandt(){

		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql;

		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);
			//jdbc:microsoft:sqlserver://127.0.1.1:1433;databaseName=spdb
			
			//得到所有设备！！从process_plan 表里面的 processplan_a中获得！！！！！
			//sql="select factoryid,factoryname from factory order by factorytype";
			sql = "select distinct machineid,machinename from machinfo order by machineid";
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Factory factory=new Factory();			
				factory.setUID(rs.getString(1));    //此处字符串不能转化成int类型,机床编号默认只能是数字
				factory.setName(rs.getString(2));
				factoryList.add(factory);	
			}

			for(int i=0;i<factoryList.size();i++)
			{
				java.util.List<Task> taskList=new ArrayList<Task>();
				FandT ft=new FandT();

				ft.setName(factoryList.get(i).getName());
				ft.setUID(factoryList.get(i).getUID());

				String factoryname=factoryList.get(i).getUID();
				//查找每个机床设备对应的加工时间！！！！！此处是在process_plan 表中查询的！！！！！
				//sql="select factoryid,factoryname,starttime,endtime,to_char(endtime-starttime)*24*3600 from factory_use where factoryname='" +factoryname+"'";
				
				sql = "select a.machine_id, b.machinename,start_time,end_time,to_char(end_time-start_time)*24*3600  from departuresheet a ,machinfo b where a.machine_id ='"+factoryname+"' and a.machine_id=b.machineid";
				System.out.println("sql==="+sql);
				
				ps=connection.prepareStatement(sql);
				rs=ps.executeQuery();
				while (rs.next()) {
					Task task=new Task();
					task.setUID(rs.getString(1));
					task.setName(rs.getString(2));
					System.out.println("starttime=="+rs.getString(3));
					task.setStart(rs.getString(3));
					System.out.println("endtime=="+rs.getString(4));
					task.setFinish(rs.getString(4));
					task.setDuration(rs.getInt(5));

					taskList.add(task);
				}
				ft.setTasks(taskList);
				ftList.add(ft);
				//JackSonTrans.PrintFandTList(ftList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFandTList_factory()出现异常了！！！");
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
				if (rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("getFandTList_factory()资源关闭出现异常！！！");
			}
		}
		return ftList;
	}

	
	public static void saveFandTListByProcess(java.util.List<FandT> ftList){
		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql;
		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);
			for(int i=0;i<ftList.size();i++)
			{
				for(int j=0;j<ftList.get(i).getTasks().size();j++)
				{
//					sql ="insert into process_plan (PLAN_ID,PROCESSPLAN_B,START_TIME,END_TIME,NUM,FINISHNUM) values('"
//						+ftList.get(i).getTask().get(j).getUID()+"','"
//						+ftList.get(i).getTask().get(j).getName()+"',to_date('"
//						+ftList.get(i).getTask().get(j).getStart().replace('T', ' ')+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"
//						+ftList.get(i).getTask().get(j).getFinish().replace('T', ' ')+"','yyyy-mm-dd,hh24:mi:ss'),'"
//						+40+"','"
//						+30+"')";
					
					sql = "update process_plan set START_TIME=to_date('"
					+ftList.get(i).getTasks().get(j).getStart().replace('T', ' ')+"','yyyy-mm-dd,hh24:mi:ss'),END_TIME=to_date('"
					+ftList.get(i).getTasks().get(j).getFinish().replace('T', ' ')+"','yyyy-mm-dd,hh24:mi:ss') where OPER_ID='"+ftList.get(i).getTasks().get(j).getUID()+"'";
					System.out.println("sql=="+sql);
					ps=connection.prepareStatement(sql);
					ps.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("saveFandTListByProcess出现异常！！！");
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("saveFandTListByProcess资源关闭异常！！！");
			}
		}

	}

	public  static String clearTableData(String TableName){
		String temp=" delete  from  ";
		temp+=TableName;
		return temp;
	}
	
	/**
	 * 找出加工单个零件的所有机床，然后显示甘特图
	 * @param partID
	 * @return
	 */
	public  static java.util.List<FandT> getFandTListAo(String productID){

		System.out.println("productID=="+productID);
		
		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet placers=null;
		String placeSql;
		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);			
			//此语句查询的是加工一个零件要用到的所有机床！！！！！
			//sql="select PROCESSPLAN_A from process_plan where EQUIPMENT_DRAWID='"+partID+"' group by PROCESSPLAN_A";
			placeSql = "select distinct place_id from station_plan where product_id = '"+productID+"' order by place_id";
			System.out.println(placeSql);
			
			ps=connection.prepareStatement(placeSql);
			placers=ps.executeQuery();
			while(placers.next()){
				Factory factory=new Factory();
				factory.setUID(placers.getString(1));
				
				//此处要查询设备名称！！！！！！！！根据设备编号查询名称！
				
				factory.setName(placers.getString(1));
				System.out.println("factory.getname=="+placers.getString(1));
				factoryList.add(factory);
				
			}
			System.out.println("factoryList.size()=="+factoryList.size());
			for(int i=0;i<factoryList.size();i++)
			{
				java.util.List<Task> taskList=new ArrayList<Task>();
				FandT ft=new FandT();
				ft.setName(factoryList.get(i).getName());
				ft.setUID(factoryList.get(i).getUID());
				String factoryUID=factoryList.get(i).getUID();
				//此语句查询机床上对应零件的加工工序
//				sql="select OPER_ID,START_TIME,END_TIME,to_char(END_TIME-START_TIME)*24*3600,NUM,FINISHNUM " +
//						"from process_plan where PROCESSPLAN_A='"+factoryUID+"'";
				String Gandtsql = "select AO_NO, START_TIME, END_TIME, to_char(END_TIME-START_TIME)*24*3600, NUM " +
						"from station_plan where  PLACE_ID='"+factoryUID+"' and PRODUCT_ID = '"+productID+"' order by AO_NO";
				System.out.println("Gandtsql=="+Gandtsql);
				
				ps=connection.prepareStatement(Gandtsql);
				placers=ps.executeQuery();
				while (placers.next()) {
					Task task=new Task();
					task.setUID(placers.getString(1));
					
					
//					String processnamesql = 
//						"select FO_OPNAME from FO_DETAIL where ITEM_ID = '"+productID+"'  and  FO_OPERID = '"+placers.getString(1)+"'";
//					System.out.println("processnamesql===="+processnamesql);
//					ps=connection.prepareStatement(processnamesql);
//					ResultSet processnamers=ps.executeQuery();
//					processnamers.next();
					
					
					//此处不一定对，要注意！！！！
					//task.setName(rs.getString(1));
					task.setName(placers.getString(1));
					task.setStart(placers.getString(2));
					task.setFinish(placers.getString(3));
					task.setPercentComplete(0);
					task.setDuration(placers.getInt(4));
					
					taskList.add(task);
				}
				ft.setTasks(taskList);
				ftList.add(ft);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFandTListByID_new出现异常！！！");
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
				if (placers!=null) {
					placers.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("getFandTListByID_new的ps，cn关闭异常！！！");
			}
		}
		//	JackSonTrans.PrintFandTList(ftList);
		return ftList;
	}
	
	/**
	 * 得到所有产品号，显示每个产品的工序甘特图
	 */
	public  static java.util.List<FandT> getFandtProduct(){

		java.sql.Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String ProductSql;

		java.util.List<FandT> ftList=new ArrayList<FandT>();
		java.util.List<Factory> factoryList=new ArrayList<Factory>();

		try {
			Class.forName(DatabaseCommon.driver);
			connection=DriverManager.getConnection(DatabaseCommon.url,DatabaseCommon.username,DatabaseCommon.password);
			//jdbc:microsoft:sqlserver://127.0.1.1:1433;databaseName=spdb
			
			ProductSql="select distinct product_id from station_plan";       //得到产品号和产品名称,此处规定物料号不能重复！！！！！
			//sql="select item_id from item where item_typeid = "L";  //从物料表里面 得到零件号
			System.out.println("ProductSql=="+ProductSql);
			ps=connection.prepareStatement(ProductSql);
			rs=ps.executeQuery();
			while(rs.next()){
				Factory factory=new Factory();
				factory.setUID(rs.getString(1));    //此处字符串不能转化成int类型		
				//查找产品的名称,还没找到哪里有，以后要添加！！！！！！！！！！！！！！！~~~~~~~~~~~
//				String namesql = "select ITEM_NAME from item where ITEM_ID = '"+rs.getString(1)+"'";
//				System.out.println("namesql====="+namesql);
//				
//				ps=connection.prepareStatement(namesql);
//				namers=ps.executeQuery();
//				namers.next();
//				factory.setName(namers.getString(1));
				factory.setName(rs.getString(1));
				factoryList.add(factory);	
			}

			for(int i=0;i<factoryList.size();i++)
			{
				java.util.List<Task> taskList=new ArrayList<Task>();
				FandT ft=new FandT();

				ft.setName(factoryList.get(i).getName());
				ft.setUID(factoryList.get(i).getUID());
				String factoryname=factoryList.get(i).getUID();
//				sql="select OPER_ID,NUM,START_TIME,END_TIME,to_char(END_TIME-START_TIME)*24*3600 " +
//						"from process_plan where EQUIPMENT_DRAWID= " +factoryname;
//				sql = "select part_num, PLAN_BGTIME, PLAN_EDTIME" +
//						" from partplan where item_id = '"+factoryname+"'";
				
//				sql = "select part_num" +
//					",to_date(PLAN_BGTIME,'yyyy-mm-dd,hh24:mi:ss')" +
//					",to_date(PLAN_EDTIME,'yyyy-mm-dd,hh24:mi:ss')" +
//					",to_char(to_date(PLAN_EDTIME,'yyyy-mm-dd,hh24:mi:ss') - to_date(PLAN_BGTIME,'yyyy-mm-dd,hh24:mi:ss'))*24*3600 " +
//					"from partplan where item_id = '"+factoryname+"'";
				
//				System.out.println("sql===="+sql);
				String ao_no_sql = "select ao_no ,START_TIME,END_TIME,to_char(END_TIME-START_TIME)*24*3600  from station_plan where product_id = '"+factoryname+"'";
				System.out.println("ao_no_sql===="+ao_no_sql);
				
//				ps=connection.prepareStatement(sql);
//				rs=ps.executeQuery();
				
//				将查询到的结果集保存起来
//				rs.next();
//				int plan_num = rs.getInt(1);
//				String plan_bgtime = rs.getString(2);
//				String plan_edtime = rs.getString(3);
//				int duration = rs.getInt(4);
				
				ps=connection.prepareStatement(ao_no_sql);
				rs=ps.executeQuery();

				while (rs.next()) {
					Task task=new Task();
					task.setUID(rs.getString(1));
					
					String processnamesql = 
						"select aoname from aodetail where productid = '"+factoryname+"'  and  ao_no = '"+rs.getString(1)+"'";
					System.out.println("processnamesql===="+processnamesql);
					ps=connection.prepareStatement(processnamesql);
					ResultSet processnamers=ps.executeQuery();
					processnamers.next();
					
					task.setName(processnamers.getString(1));
					task.setStart(rs.getString(2));
					task.setFinish(rs.getString(3));
					task.setPercentComplete(0);
					task.setDuration(rs.getInt(4));

					taskList.add(task);
				}
				ft.setTasks(taskList);
				ftList.add(ft);
				JackSonTrans.PrintFandTList(ftList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFandtProduct()出现异常了！！！");
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
				if(connection!=null){
					connection.close();
				}
				if (rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("getFandtProduct()资源关闭出现异常！！！");
			}
		}
		return ftList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
