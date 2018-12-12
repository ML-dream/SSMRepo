package Bom;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import cfmes.util.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import cfmes.bom.dao.BomBeanDao;

import cfmes.bom.entity.Bom;
import cfmes.bom.dao.BomImportDao;
import cfmes.bom.dao.AoBeanDao;
/**
 * @author Administrator
 *
 */
public class Bom_Bean {
	
	
	
	ResultSet rs=null;  //ResultSet表示数据库结果集的数据表，通常通过执行查询数据库的语句生成--fy
    
	DealString ds= new DealString();
    String bom_other_id;
    //设置原材料、辅料、工装工具item_id
	public  void  setBom_other_id(String str){
		bom_other_id = str;
	}
	public String getBom_other_id() {
		return bom_other_id;
	}

	
	//设置scrap，itemnum，rl，item_type值
	public Hashtable getOther(String product_id,String item_id,String father_item_id,String level_id,String id,String fid){
		sql_data sqlbean=new sql_data();
		Hashtable hash = new Hashtable();
		String sql = "";
		try{
			if(father_item_id.equals("#")){
				sql = "select i1.item_name, b.item_num,b.memo from work.ebom b,work.item i1"
			    	+" where b.product_id='"+product_id+"' and b.item_id='"+item_id+"' and b.father_item_id='"+father_item_id+"' and b.level_id='"+level_id
			    	+"' and b.id='"+id+"' and b.fid='"+fid+"' and i1.item_id='"+item_id+"'";
			}else{
				sql = "select i1.item_name,i2.item_name as FATHER_ITEM_NAME, b.item_num from work.ebom b,work.item i1,work.item i2"
			    	+" where b.product_id='"+product_id+"' and b.item_id='"+item_id+"' and b.father_item_id='"+father_item_id+"' and b.level_id='"+level_id
			    	+"' and b.id='"+id+"' and b.fid='"+fid+"' and i1.item_id='"+item_id+"' and i2.item_id='"+father_item_id+"'";
			}
			rs=sqlbean.executeQuery(sql);
			//System.out.println("getOther: "+sql);
		    ResultSetMetaData  rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while(rs.next())
			{   for(int i=1; i<=cols; i++){
				  String field = ds.toString(rsmd.getColumnName(i));
				  String value = ds.toString(rs.getString(i));
				  hash.put(field,value);
			    }
				if(father_item_id.equals("#")){
					hash.put("FATHER_ITEM_NAME","");
			    }
			}rs.close();
		}catch(Exception e){System.out.println("getOther运行时出错7："+e);}
		finally{
			sqlbean.closeConn(); 
		}
		return hash;
	}
//	设置scrap，itemnum，rl，item_type值
	   
//取得原材料、辅料、工装的信息
	public  Vector getData(String type, String product_id,String issue_num,String item_id,String father_item_id,String level_id,String id,String fid){//
		sql_data sqlbean=new sql_data();
		Vector vect = new Vector();
		BomBeanDao bombeandao = new BomBeanDao();
		String sql = "";
		if (type.equals("Y")){
			sql = "select m.item_id,m.materical_id,m.matrlspec,m.item_num,m.blanking_per,m.marl_qulitycode,m.remark_a from"
				 +" meteor.mater_bom m where m.product_id='"+product_id
				 +"' and m.level_id='"+level_id+"'and m.bom_item_id='"+item_id+"' and m.father_item_id='"+father_item_id
				 +"' and m.id='"+id+"' and m.fid='"+fid+"' and m.issue_num='"+issue_num+"'" ;
		}else if(type.equals("F")){
			sql = "select h.h_item_id,h.h_mater_id,h.matrlspec,h.num,h.remark1 from"
				 +" meteor.hmater_bom h where h.product_id='"+product_id+"' and h.level_id='"+level_id+"'and h.item_id='"+item_id
				 +"' and h.father_item_id='"+father_item_id+"' and h.id='"+id+"' and h.fid='"+fid+"' and h.issue_num='"+issue_num+"'" ;
		}else if(type.equals("T")){
			sql = "select t.tool_id, t.tool_name,t.sequence,t.item_num,t.remark1 from"
				 +" meteor.tool_bom t where t.product_id='"+product_id
				 +"' and t.level_id='"+level_id+"'and t.item_id='"+item_id+"' and t.father_item_id='"+father_item_id
				 +"' and t.id='"+id+"' and t.fid='"+fid+"' and t.issue_num='"+issue_num+"'" ;
		}
		try{
	  	    rs= sqlbean.executeQuery(sql);

			//取得列数和列名
	  	    	ResultSetMetaData  rsmd = rs.getMetaData();
				int cols = rsmd.getColumnCount();
				int rowno=0;
	            	while(rs.next()){
						rowno++;
						Hashtable hash = new Hashtable();
						for(int i=1;i<=cols;i++){
							String value = ds.toString(rs.getString(i));
							String field = rsmd.getColumnName(i);
							hash.put(field,value);
                            if(i==1 && type.equals("T")){
								hash.put("ITEM_TYPEDESC",bombeandao.getItemType(value));
							}else if(i==1 && !type.equals("T")){
								hash.put("ITEM_NAME",bombeandao.getItemName(value));
							}
						}
						vect.add(hash);
					} rs.close();
		}catch(Exception e){
			System.out.println("getData运行时出错1："+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return vect;
	}

	
//	取得原材料、辅料、工装的 itemid和itemname信息，以便在下拉列表中显示
	public  Vector getBMHT_list(String type,String product_id,String issue_num,String item_id,String father_item_id,String level_id,String id,String fid){//
		sql_data sqlbean=new sql_data();
		Vector vect = new Vector();
		String sql="";
		if (type.equals("Y")){
			sql = "select itemid,item_name from meteor.item where item_typeid in(select item_typeid from meteor.itemtype where item_typedesc in('原材料'))"
	           +" and itemid not in(select item_id from meteor.mater_bom where product_id='"+product_id
		       +"' and level_id='"+level_id+"' and bom_item_id='"+item_id+"' and father_item_id='"+father_item_id
		       +"' and id='"+id+"' and fid='"+fid+"' and issue_num='"+issue_num+"')";
		}else if(type.equals("F")){
			sql = "select itemid,item_name from meteor.item where item_typeid in(select item_typeid from meteor.itemtype where item_typedesc in('辅料'))"
	           +" and itemid not in(select h_item_id from meteor.hmater_bom where product_id='"+product_id
	           +"' and level_id='"+level_id+"'and item_id='"+item_id
			   +"' and father_item_id='"+father_item_id+"' and id='"+id+"' and fid='"+fid+"' and issue_num='"+issue_num+"')";
		}else if(type.equals("T")){
			sql = "select itemid,item_name from meteor.item where item_typeid in(select item_typeid from meteor.itemtype where item_typedesc in('工装','样板','刀量具','工具样板'))"
	           +" and itemid not in(select tool_id from meteor.tool_bom where product_id='"+product_id
			   +"' and level_id='"+level_id+"' and item_id='"+item_id+"' and father_item_id='"+father_item_id
			   +"' and id='"+id+"' and fid='"+fid+"' and issue_num='"+issue_num+"')";
		}
		rs=sqlbean.executeQuery(sql);
		try{
			//取得列数和列名
			ResultSetMetaData  rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			int rowno=0;
            	while(rs.next()){
					rowno++;
					Hashtable hash = new Hashtable();
					for(int i=1;i<=cols;i++){
						String value = ds.toString(rs.getString(i));
						String field = ds.toString(rsmd.getColumnName(i));
						hash.put(field,value);
					}
					vect.add(hash);
				}rs.close();
		}catch(Exception e){
			System.out.println("getBMHT_list运行时出错2："+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return vect;
	}
//	取得bom_main中机型编号 的下拉列表
	public  ArrayList getFlight_Type(String type){//
		sql_data sqlbean=new sql_data();
		ArrayList arraylist = new ArrayList();
		String sql = "SELECT distinct flight_type From meteor."+type+" where dept_id='1' order by flight_type";
		rs=sqlbean.executeQuery(sql);
		
		try{
            while(rs.next()){
            	//System.out.println(rs.getString(1));
				arraylist.add(rs.getString(1));
			}rs.close();
		}catch(Exception e){System.out.println("getFlight_Type："+e);}
		finally{
			sqlbean.closeConn(); 
		}
		return arraylist;
	}
//	取得bom_main中机型编号 的下拉列表
	public  ArrayList getFlight_Name(String type){//
		sql_data sqlbean=new sql_data();
		ArrayList arraylist = new ArrayList();
		String sql = "SELECT distinct product_type From work."+type+" order by product_type";  //distinct就是把flight_type重合只取其一——fy
		rs=sqlbean.executeQuery(sql);
		
		try{
            while(rs.next()){
            	//System.out.println(rs.getString(1));
				arraylist.add(rs.getString(1));//把rs中的第一列的值添加到arraylist列表的尾部。——fy
			}rs.close();
		}catch(Exception e){System.out.println("getFlight_Name："+e);}
		finally{
			sqlbean.closeConn(); 
		}
		return arraylist;
	}
//	取得bom_main中机型编号 的下拉列表
	public  ArrayList getFlight(String type){//
		sql_data sqlbean=new sql_data();
		ArrayList arraylist = new ArrayList();
		String sql = "SELECT distinct flight_type From meteor."+type+" where memo2='"+1+"' order by flight_type";
		rs=sqlbean.executeQuery(sql);
		
		try{
            while(rs.next()){
            	//System.out.println(rs.getString(1));
				arraylist.add(rs.getString(1));
			}rs.close();
		}catch(Exception e){System.out.println("getFlight："+e);}
		finally{
			sqlbean.closeConn(); 
		}
		return arraylist;
	}
	
	/*//为取得添加原材料时的ao_no,aover,aooperid---------by zou
	public  ArrayList getList(String producttype,String productid,String issuenum,String itemid,String name,String value1,String value2){//
		sql_data sqlbean=new sql_data();
		ArrayList arraylist = new ArrayList();
		String sql="";
		if(name=="ao_no"){
		 sql = "SELECT distinct AO_NO From meteor.AO_OPER where PRODUCT_TYPE='"+producttype+"' AND PRODUCT_ID='"+productid+"' AND ISSUE_NUM='"
		              +issuenum+"' AND ITEM_ID='"+itemid+"'order by AO_NO";
		}
		else if(name=="aover"){
		 sql = "SELECT distinct AOVER From meteor.AO_OPER where PRODUCT_TYPE='"+producttype+"' AND PRODUCT_ID='"+productid+"' AND ISSUE_NUM='"
            +issuenum+"' AND ITEM_ID='"+itemid+"'AND AO_NO='"+value1+"'order by AOVER";	
		}
		else if(name=="operid"){
			 sql = "SELECT distinct AO_OPERID From meteor.AO_OPER where PRODUCT_TYPE='"+producttype+"' AND PRODUCT_ID='"+productid+"' AND ISSUE_NUM='"
	            +issuenum+"' AND ITEM_ID='"+itemid+"'AND AO_NO='"+value1+"'AND AOVER='"+value2+"' order by AO_OPERID";	
			}
		
		rs=sqlbean.executeQuery(sql);
		
		try{
            while(rs.next()){
            	//System.out.println(rs.getString(1));
				arraylist.add(rs.getString(1));
			}rs.close();
		}catch(Exception e){System.out.println("getList："+e);}
		finally{
			sqlbean.closeConn(); 
		}
		return arraylist;
	}
	*/
	
	//取得单个原材料、辅料、工装信息
	public Hashtable getOneData(String type,String product_id,String issue_num,String item_id,String father_item_id,String level_id,String id,String fid)
	{
		sql_data sqlbean=new sql_data();
		Hashtable hash = new Hashtable();
		String sql = "";
		if (type.equals("Y")){
			sql = "select i.item_name,m.materical_id,m.matrlspec,m.item_num,m.blanking_per,m.marl_qulitycode,m.remark_a from"
				 +" meteor.mater_bom m,meteor.item i where m.product_id='"+product_id+"' and m.level_id='"+level_id
				 +"'and m.bom_item_id='"+item_id+"' and m.father_item_id='"+father_item_id+"' and m.id='"+id+"' and m.fid='"+fid
				 +"' and i.itemid='"+bom_other_id+"' and m.item_id='"+bom_other_id+"' and m.issue_num='"+issue_num+"'" ;
		}else if(type.equals("F")){
			sql = "select i.item_name,h.h_mater_id,h.matrlspec,h.num,h.remark1 from meteor.hmater_bom h,meteor.item i"
				 +"  where h.product_id='"+product_id+"' and h.level_id='"+level_id+"'and h.item_id='"+item_id
				 +"' and h.father_item_id='"+father_item_id+"' and h.id='"+id+"' and h.fid='"+fid
				 +"'and i.itemid='"+bom_other_id+"' and h.h_item_id='"+bom_other_id+"' and h.issue_num='"+issue_num+"'" ;
		}else if(type.equals("T")){
			sql = "select t.tool_name,p.item_typedesc,t.sequence,t.item_num,t.remark1 from meteor.tool_bom t,meteor.item i,meteor.itemtype p "
				 +" where t.product_id='"+product_id+"' and t.level_id='"+level_id+"'and t.item_id='"+item_id
				 +"' and t.father_item_id='"+father_item_id+"' and t.id='"+id+"' and t.fid='"+fid+"' and i.itemid='"+bom_other_id
				 +"' and p.item_typeid=i.item_typeid and t.tool_id='"+bom_other_id+"' and t.issue_num='"+issue_num+"'" ;
		}
		rs = sqlbean.executeQuery(sql);
		try{
			ResultSetMetaData  rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while(rs.next()){
				for(int i=1; i<=cols; i++){
					String field = ds.toString(rsmd.getColumnName(i));
					String value = ds.toString(rs.getString(i));
					hash.put(field,value);
				}
			}rs.close();
		}catch(
				Exception e){System.out.println("getOneData运行时出错3："+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return hash;
	}

	
	//取得原材料 辅料 工装记录列表displayTxun.jsp排序用
	public Vector getData(String pxzd,boolean isdesc,String srchzd,String srchzdval,boolean isexact,String product_id,String item_id,String father_item_id,String level_id,String id,String fid)
	{
		//pxzd按哪个字段排序；isdesc是否降序排列；srchzd按哪个字段搜索；isexact是否精确查询
		sql_data sqlbean=new sql_data();
		Vector vect = new Vector();
		String sql = "";
		sql = "select i.item_name, m.item_id,m.materical_id,m.item_num,m.scrap,m.remark_a from"
			 +" meteor.mater_bom m,meteor.item i where m.product_id='"+product_id
			 +"' and m.level_id='"+level_id+"'and m.bom_item_id='"+item_id+"' and m.father_item_id='"+father_item_id
			 +"' and m.id='"+id+"' and m.fid='"+fid+"' and i.itemid='"+item_id+"'";
//		sql = "select i.item_name, m.item_id,m.materical_id,m.item_num,m.scrap,m.remark_a from"
//			 +" meteor.mater_bom m,meteor.item i where m.product_id='"+product_id
//			 +"'  and i.itemid=m.item_id";
		//sql = "select * from ZZ_TXLB where ZGBH='"+strPersonNo+"'";			
		if(!srchzd.equals(""))
		{
			if(isexact)
				sql = sql+" and "+srchzd+"='"+srchzdval+"'";
			if(!isexact)
				sql = sql+" and "+srchzd+" like '%"+srchzdval+"%'";
		}
		if(!pxzd.equals(""))
		{	
			sql = sql+" order by m."+pxzd;
		}
		if(isdesc)
		{
			sql = sql+" DESC";
		}
		Hashtable ht = new Hashtable();
		ht.put("sql",sql);
		vect.add(ht);

		rs = sqlbean.executeQuery(sql);
		int rowno=0;
		try{
			//取得列数和列名
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while(rs.next())
			{
				rowno++;
				Hashtable hash = new Hashtable();
				for(int i=1;i<=cols;i++)
				{
					String field = ds.toString(rsmd.getColumnName(i));
					String value = ds.toString(rs.getString(i));
					hash.put(field,value);
				}
				vect.add(hash);
			}rs.close();
		}catch(Exception e){
			System.out.println("getData运行时出错5："+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return vect;
	}
//	添加BOM
	public void addBom(Hashtable hash){
		Bom bom = new Bom();
		BomBeanDao bombeandao = new BomBeanDao();
		String product_id = (String)hash.get("PRODUCT_ID");
		String issue_num = (String)hash.get("ISSUE_NUM");//100位表现形式
		String father_item_id =(String)hash.get("FATHER_ITEM_ID");
		String item_id = (String)hash.get("ITEM_ID");
		String fid = (String)hash.get("FID");
		String level_id = (String)hash.get("LEVEL_ID");
		String item_num = (String)hash.get("ITEM_NUM");
		String scrap = (String)hash.get("SCRAP");
		String memo = (String)hash.get("MEMO");
		String id_num="";
		if(level_id.length()==1){
			level_id="0"+level_id;
		}
		try{
		    boolean isin_level = bombeandao.getLevel_exist(product_id, item_id, level_id);   
		    if(!isin_level){       //同层中没有这个物料，直接添加，在这个位置上置1，其余位置置0，后面的都置1
		    	bombeandao.InputBom(product_id, issue_num, item_id, "1", father_item_id, fid, level_id, item_num, scrap,memo);   
		    	id_num="1";
		    }else{          //同层中有这个物料，加以判断
		        boolean isin_father = bombeandao.getFather_exist(product_id, item_id, level_id, father_item_id, fid);
                if(isin_father){//父物料下没有这个物料，同层中相同的物料用id号判别，直接添加，版本号在这个位置上置1，其余位置置0，后面的都置1
                     int n = bombeandao.getMaxId(product_id, item_id, level_id);
                     n=n+1;
                     
                     id_num = n+"";
		             bombeandao.InputBom(product_id, issue_num, item_id, id_num, father_item_id, fid, level_id, item_num, scrap,memo);
                
		         }
		     }
		}catch(Exception e){
			System.out.println("addBom运行出错："+e);
		}
	}
	//-----------------------------------fy
    //	复制后添加BOM
	public void addBom2(Hashtable hash){
		//Bom bom = new Bom();
		BomBeanDao bombeandao = new BomBeanDao();
		String product_id = (String)hash.get("PRODUCT_ID");
		String s = (String)hash.get("ISSUE_NUM");//100位表现形式
		//String s = (String)hash.get("ISSUE_NUM");//100位表现形式
		//int p = Integer.parseInt((String)hash.get("ISSUE"));
		String item_id = (String)hash.get("ITEM_ID");
		String father_item_id =(String)hash.get("FATHER_ITEM_ID");
		String level_id = (String)hash.get("LEVEL_ID");
		//String id = (String)hash.get("ID");
		String fid = (String)hash.get("FID");
		String item_num = (String)hash.get("ITEM_NUM");
		String scrap = (String)hash.get("SCRAP");
		String is_rl = (String)hash.get("IS_RL");
		String route = (String)hash.get("ROUTE");
		String id_num="";
		String memo = (String)hash.get("memo");
		//String fid_num="";

		try{	
			//int isin_level = bombeandao.getLevel_exist2(product_id, item_id, level_id); //这个方法能读取同层下有几个这个物料
		    boolean isin_level = bombeandao.getLevel_exist(product_id, item_id, level_id);   
		    if(!isin_level){       //同层中没有这个物料，直接添加
		    	bombeandao.InputBom(product_id, s, item_id, "1", father_item_id, fid, level_id, item_num, scrap,memo);   
		    }else{          //同层中有这个物料，加以判断
		        boolean isin_father = bombeandao.getFather_exist(product_id, item_id, level_id, father_item_id, fid);
                if(!isin_father){//父物料下没有这个物料，同层中相同的物料用id号判别，直接添加
                     int n = bombeandao.getMaxId(product_id, item_id, level_id);
                     n=n+1;
                     id_num = n+"";
		             bombeandao.InputBom(product_id, s, item_id, id_num, father_item_id, fid, level_id, item_num, scrap,memo);
		        }else{//同层同父物料下有这个物料
		        	int maxfid = bombeandao.getMaxFid(product_id, item_id, level_id, father_item_id);
		        	int maxid = bombeandao.getMaxId2(product_id, item_id, level_id, father_item_id);
		        	maxid=maxid+1;
                    id_num = maxid+"";
                    
                    maxfid=maxfid+1;
                    String fid_num = maxfid+"";
		            bombeandao.InputBom(product_id, s, item_id, id_num, father_item_id, fid_num, level_id, item_num, scrap, memo);
		         }
		     }
		}catch(Exception e){
			System.out.println("addBom2运行出错："+e);
		}
	}
	//添加原材料 辅料 工装
	public void addBMHT(Hashtable hash){
		Vector vect = new Vector();
		BomBeanDao bombeandao = new BomBeanDao();
		try{
		String type = ds.toString((String)hash.get("TYPE"));
		String product_type = ds.toString((String)hash.get("PRODUCT_TYPE"));
		String product_id = ds.toString((String)hash.get("PRODUCT_ID"));
		String issue_num = ds.toString((String)hash.get("ISSUE_NUM"));
		String item_id = ds.toString((String)hash.get("ITEM_ID"));
		
		String no = ds.toString((String)hash.get("NO"));
		String ver = ds.toString((String)hash.get("VER"));
		String operid = ds.toString((String)hash.get("OPERID"));
		//String item_name =ds.toString((String)hash.get("ITEM_NAME"));;
		String id =ds.toString((String)hash.get("ID"));
		String name =ds.toString((String)hash.get("NAME"));
		String spec = ds.toString((String)hash.get("SPEC"));//规格
		String num = ds.toString((String)hash.get("NUM"));
		String qua = ds.toString((String)hash.get("QUALITYCODE"));
		String memo = ds.toString((String)hash.get("MEMO"));
		
		
		if(type.equals("Y")){
			//materical_id = bombeandao.getMatMark(bom_other_id);

			vect.add("meteor.AO_MATERIAL");
			vect.add(addVector("PRODUCT_TYPE",product_type,"CHAR"));
			vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
			
			vect.add(addVector("AO_NO",no,"CHAR"));
			vect.add(addVector("AOVER",ver,"CHAR"));
			vect.add(addVector("AO_OPERID",operid,"CHAR"));
			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
			
			vect.add(addVector("AO_MATER_ID",id,"CHAR"));
			vect.add(addVector("AO_MATER_NAME",name,"CHAR"));
			vect.add(addVector("AO_MATER_SPEC",spec,"CHAR"));
			vect.add(addVector("AO_MATER_NUM",num,"CHAR"));
		
			vect.add(addVector("AO_MATER_QUALITYCODE",qua,"CHAR"));
		
			vect.add(addVector("MEMO",memo,"CHAR"));
		}else if(type.equals("F")){
			//materical_id = bombeandao.getMatMark(bom_other_id);
			vect.add("meteor.AO_MATERIAL");
			vect.add(addVector("PRODUCT_TYPE",product_type,"CHAR"));
			vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
			
			vect.add(addVector("AO_NO",no,"CHAR"));
			vect.add(addVector("AOVER",ver,"CHAR"));
			vect.add(addVector("AO_OPERID",operid,"CHAR"));
			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
			
			vect.add(addVector("AO_MATER_ID",id,"CHAR"));
			vect.add(addVector("AO_MATER_NAME",name,"CHAR"));
			vect.add(addVector("AO_MATER_SPEC",spec,"CHAR"));
			vect.add(addVector("AO_MATER_NUM",num,"CHAR"));
		
			vect.add(addVector("AO_MATER_QUALITYCODE",qua,"CHAR"));
		
			vect.add(addVector("MEMO",memo,"CHAR"));
	
		}else if(type.equals("T")){
			//item_name = bombeandao.getItemName(bom_other_id);

			vect.add("meteor.AO_MATERIAL");
			vect.add(addVector("PRODUCT_TYPE",product_type,"CHAR"));
			vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
			
			vect.add(addVector("AO_NO",no,"CHAR"));
			vect.add(addVector("AOVER",ver,"CHAR"));
			vect.add(addVector("AO_OPERID",operid,"CHAR"));
			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
			
			vect.add(addVector("AO_MATER_ID",id,"CHAR"));
			vect.add(addVector("AO_MATER_NAME",name,"CHAR"));
			vect.add(addVector("AO_MATER_SPEC",spec,"CHAR"));
			vect.add(addVector("AO_MATER_NUM",num,"CHAR"));
		
			vect.add(addVector("AO_QUALITYCODE",qua,"CHAR"));
		
			vect.add(addVector("MEMO",memo,"CHAR"));
		}
		
	  }catch(Exception e){
		  System.out.println("运行时出错 addBMHT："+e);
	  }
		insertRecord(vect);
	}
	/**写数据库时某一个字段的存储类型*/
	protected Vector addVector(String field,String value,String type)
	{
		Vector vect = new Vector();
		vect.add(field);
		vect.add(value);
		vect.add(type);
		return vect;
	}
	//修改原材料 辅料 工装
	public  void modBMHT(Hashtable hash){
		Bom bom = new Bom();
		BomBeanDao bombeandao = new BomBeanDao();
		String type = ds.toString((String)hash.get("TYPE"));
		String product_id = ds.toString((String)hash.get("PRODUCT_ID"));
		String issue_100 = ds.toString((String)hash.get("ISSUE_100"));
		String issue_num = ds.toString((String)hash.get("ISSUE_NUM"));
		String item_id = ds.toString((String)hash.get("ITEM_ID"));
		String father_item_id = ds.toString((String)hash.get("FATHER_ITEM_ID"));
		String id = ds.toString((String)hash.get("ID"));
		String fid = ds.toString((String)hash.get("FID"));
		String level_id = ds.toString((String)hash.get("LEVEL_ID"));
		String issue = ds.toString((String)hash.get("ISSUE"));
//		//System.out.println("flight_type:"+flight_type);
//	    System.out.println("product_id:"+product_id);
//	    //System.out.println("lot:"+lot);
//	    //System.out.println("sortie:"+sortie);
//	    System.out.println("issue_num:"+issue_num);
//	    System.out.println("issue:"+issue);
//	    System.out.println("item_id:"+item_id);
//	    System.out.println("father_item_id:"+father_item_id);
//	    System.out.println("id:"+id);
//	    System.out.println("fid:"+fid);
//	    System.out.println("level_id:"+level_id);
//	    //System.out.println("issue_100:"+issue_100);
		String a = ds.toString((String)hash.get("A")); //规格 或 工装序列号
		String item_num = ds.toString((String)hash.get("ITEM_NUM"));
		String c = ds.toString((String)hash.get("C")); //损耗率  下料依据  
		String d = ds.toString((String)hash.get("D")); //左右件   质量编号
		String remark1 = ds.toString((String)hash.get("REMARK_A"));//路线 备注 
		//String route = (String)hash.get("ROUTE");
		String issue_num_new,issue_num_chg,id_num;
		Double isZero1;
		Double isZero2;
		Vector vect = new Vector();
		
		if(type.equals("B")){
			
			String issue_like="";
			int p = Integer.parseInt(issue);   //得到版本位置
			for(int i=1;i<=100;i++){             //如果没有相同的，则设置 新的issue_num值
		        if(i==p){
		        	issue_like = issue_like+'1';
		        }if(i<p){
		        	issue_like = issue_like+'0';
		        }
		    }
			boolean is_equal=false;
            ArrayList array3 = bombeandao.getBom(product_id, item_id, level_id, father_item_id, fid);
            if(array3.size()>1){
            	for(int i = 0;i<array3.size();i++){//如果数量不相同 则要新加一个数据。
      			    bom = (Bom)array3.get(i);
      			    if(issue_100 == bom.getIssue_num()) continue;// 如果为本身，则继续循环查找下一个。
                    String num=bom.getItem_num();
                    id_num = bom.getId();
                    if(num.equals(item_num)){
                    	is_equal=true;
                        String issue_num_a = bom.getIssue_num();
                        StringBuffer buf   = new StringBuffer(issue_num_a);
                        buf.replace(p-1,p,"1");
                        issue_num_new = buf.toString();//同层同父下有相同数量的物料，将相同的数量的版本位置置1
                        bombeandao.update("update meteor.ebom set issue_num='"+issue_num_new+"' where product_id='"+ product_id
                            	+"' and item_id='"+item_id+"' and issue_num ='"+issue_num_a+"' and level_id='"+level_id
                                +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"' and id='"+id_num+"'");
                        StringBuffer buf2   = new StringBuffer(issue_100);
                        buf2.replace(p-1,p,"0");
                        issue_num_chg = buf2.toString();//并将原来的版本位置置0，如果为 0 则删除。
                        String t1=issue_num_chg.substring(0, 50);
 	                    String t2=issue_num_chg.substring(50,99);
 	                    //System.out.println(t1+t2);
 	                    isZero1 = new Double(Double.parseDouble(t1));
 	                    isZero2 = new Double(Double.parseDouble(t2));
 	                    if(isZero1==0 && isZero2==0){
                        	bombeandao.update("delete from meteor.ebom where product_id='"+ product_id
               		                +"' and item_id='"+item_id+"' and issue_num ='"+issue_100+"' and level_id='"+level_id
                                    +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"' and id='"+id_num+"'");
                        }else{
                        	bombeandao.update("update meteor.ebom set issue_num='"+issue_num_chg+"' where product_id='"+ product_id
               		                +"' and item_id='"+item_id+"' and issue_num ='"+issue_100+"' and level_id='"+level_id
                                    +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"' and id='"+id_num+"'");
                        }
                        break;
                    }
                }if(is_equal == false){
                	vect.add("meteor.ebom");
        		    vect.add(addVector("ITEM_NUM",item_num,"NUM"));
        		    vect.add(addVector("SCRAP",c,"NUM"));
        		    vect.add(addVector("IS_RL",d,"NUM"));
        		    vect.add(addVector("ROUTE",remark1,"CHAR"));
        		    vect.add("PRODUCT_ID = '"+product_id+"' and ITEM_ID ='"+item_id+"' and FATHER_ITEM_ID ='"+father_item_id
        				 +"' and ID ='"+id+"' and FID ='"+fid+"' and LEVEL_ID ='"+level_id+"' and ISSUE_NUM='"+issue_100+"'");
        		    updateRecord(vect);
                }
            }else{
            	//System.out.println("item_num:"+item_num);
            	vect.add("meteor.ebom");
    		    vect.add(addVector("ITEM_NUM",item_num,"NUM"));
    		    vect.add(addVector("SCRAP",c,"NUM"));
    		    vect.add(addVector("IS_RL",d,"NUM"));
    		    vect.add(addVector("ROUTE",remark1,"CHAR"));
    		    vect.add("PRODUCT_ID = '"+product_id+"' and ITEM_ID ='"+item_id+"' and FATHER_ITEM_ID ='"+father_item_id
    				 +"' and ID ='"+id+"' and FID ='"+fid+"' and LEVEL_ID ='"+level_id+"' and ISSUE_NUM='"+issue_100+"'");
    		    updateRecord(vect);
            }
		}else if(type.equals("Y")){
		    vect.add("meteor.MATER_BOM");
		    vect.add(addVector("MATRLSPEC",a,"CHAR"));
			vect.add(addVector("ITEM_NUM",item_num,"CHAR"));
			vect.add(addVector("BLANKING_PER",c,"CHAR"));
			vect.add(addVector("MARL_QULITYCODE",d,"CHAR"));
			vect.add(addVector("SCRAP","0","NUM"));
		    vect.add(addVector("REMARK_A",remark1,"CHAR"));
		    vect.add("PRODUCT_ID = '"+product_id+"' and BOM_ITEM_ID ='"+item_id+"' and FATHER_ITEM_ID ='"+father_item_id
				 +"' and ID ='"+id+"' and FID ='"+fid+"' and LEVEL_ID ='"+level_id+"' and ITEM_ID ='"+bom_other_id+"' and ISSUE_NUM='"+issue_num+"'");
		    updateRecord(vect);
		}else if(type.equals("F")){
			vect.add("meteor.HMATER_BOM");
			vect.add(addVector("MATRLSPEC",a,"CHAR"));
			vect.add(addVector("NUM",item_num,"NUM"));
			vect.add(addVector("SCARP","0","NUM"));
			vect.add(addVector("REMARK1",remark1,"CHAR"));
			vect.add("PRODUCT_ID = '"+product_id+"' and ITEM_ID ='"+item_id+"' and FATHER_ITEM_ID ='"+father_item_id
					 +"' and ID ='"+id+"' and FID ='"+fid+"' and LEVEL_ID ='"+level_id+"' and H_ITEM_ID ='"+bom_other_id+"' and ISSUE_NUM='"+issue_num+"'");
			updateRecord(vect);
		}else if(type.equals("T")){
			vect.add("meteor.TOOL_BOM");
			vect.add(addVector("SEQUENCE",a,"CHAR"));
			vect.add(addVector("ITEM_NUM",item_num,"NUM"));
			vect.add(addVector("SCRAP","0","NUM"));
			vect.add(addVector("REMARK1",remark1,"CHAR"));
			vect.add("PRODUCT_ID = '"+product_id+"' and ITEM_ID ='"+item_id+"' and FATHER_ITEM_ID ='"+father_item_id
					 +"' and ID ='"+id+"' and FID ='"+fid+"' and LEVEL_ID ='"+level_id+"' and TOOL_ID ='"+bom_other_id+"' and ISSUE_NUM='"+issue_num+"'");
			updateRecord(vect);
		}
	}
//	删除Bom
	public int delBom(String flight_type,String issue_100,String issue_n,String issue,String product_id,String item_id,String father_item_id,String level_id,String id,String fid)
	   {       //issue_n 版本名称
	           //issue   版本位置
		       //issue_100;//100位表现形式
//	           ResultSet rs3=null; 
//	           sql_data sqlbean=new sql_data();
		       AoBeanDao aobeandao = new AoBeanDao();
	           BomBeanDao bombeandao = new BomBeanDao();
	           // System.out.println("111111111111111111111111111");
	    	   String issue_num = issue_100;//100位表现形式
		       int m = Integer.parseInt(level_id);             //当前层次号
		       int n = bombeandao.getMaxLevelId(product_id);
		       
		       int p = Integer.parseInt(issue);             //得到版本位置
		       String issue_like="";
		       for(int i=1;i<=p;i++){       //设置 issue_num值
			        if(i==p){
			        	issue_like = issue_like+'1';
			        }if(i<p){
			        	issue_like = issue_like+'_';
			        }
			    }
		       int a=p-1;
		       int b=p;
		       Vector ao_no =new Vector();
		   try{    
		       StringBuffer buf = new StringBuffer(issue_num);
		       buf.replace(a,b,"0");
		       String s = buf.toString();
		       //int isZero = Integer.parseInt(s);
		       //System.out.println("s:"+s);
		       // Double isZero = new Double(Double.parseDouble(s));
		        String t1=s.substring(0, 50);
		        String t2=s.substring(50,99);
                //System.out.println(t1+t2);
		       Double isZero1 = new Double(Double.parseDouble(t1));
		       Double isZero2 = new Double(Double.parseDouble(t2));
                
		       //System.out.println("isZero:"+isZero);
		       bombeandao.update("INSERT INTO meteor.bom_del (product_id,item_id,id,father_item_id,fid,level_id,issue_num) Values"
		    		            +" ('"+product_id+"','"+item_id+"','"+id+"','"+father_item_id+"','"+fid+"','"+level_id+"','"+issue_num+"')");
		    if (m<n){//System.out.print("不是最后一层");
		    	bombeandao.update("delete from meteor.hmater_bom WHERE product_id='"+product_id
		        		  +"' and father_item_id='"+father_item_id+"' and item_id='"+item_id+"' and id='"+id
		        		  +"' and fid='"+fid+"' and level_id='"+level_id+"' and issue_num='"+issue_n+"'");
		    	bombeandao.update("delete from meteor.mater_bom WHERE product_id='"+product_id
		        		  +"' and father_item_id='"+father_item_id+"' and bom_item_id='"+item_id+"' and id='"+id
		        		  +"' and fid='"+fid+"' and level_id='"+level_id+"' and issue_num='"+issue_n+"'");
		    	bombeandao.update("delete from meteor.tool_bom WHERE product_id='"+product_id
		        		  +"' and father_item_id='"+father_item_id+"' and item_id='"+item_id+"' and id='"+id
		        		  +"' and fid='"+fid+"'and level_id='"+level_id+"' and issue_num='"+issue_n+"'");
		    	
		    	bombeandao.update("delete from meteor.ao_oper where flighttpye='"+flight_type+"' and produceid='"+product_id
                          +"' and issue_num = '"+issue_n+"' and itemid = '"+item_id+"'");
		    	bombeandao.update("delete from meteor.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id
		                  +"' and issue_num = '"+issue_n+"' and item_id = '"+item_id+"'");
		    	bombeandao.update("delete from meteor.fo where flight_type='"+flight_type+"' and product_id='"+product_id
		                  +"' and issue_num = '"+issue_n+"' and item_id = '"+item_id+"'");
		    	bombeandao.update("delete from meteor.aomain where flighttype='"+flight_type+"' and productid='"+product_id
		                  +"' and issue_num = '"+issue_n+"' and itemid = '"+item_id+"'");
		    	//ao_no = aobeandao.getAo_no(flight_type, product_id, issue_n, item_id);
		    	for(int i = 0; i<ao_no.size(); i++){
		    		bombeandao.update("delete from meteor.T_OPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and item_id='"+item_id+"'");//FO工序工装
			    	//bombeandao.update("delete from meteor.T_MDMWORKPLACE where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//工位结构
			    	bombeandao.update("delete from meteor.T_MDMAO where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+issue_n+"' and childaocode='"+(String)ao_no.get(i)+"'");//工位AO结构
			    	bombeandao.update("delete from meteor.T_AOSTANDARDPART where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(i)+"'");//AO标准件
			    	bombeandao.update("delete from meteor.T_AOAUXMATLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(i)+"'");//AO辅料
			    	bombeandao.update("delete from meteor.T_AOPROEQUIPLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(i)+"'");//AO工序工装
			    	bombeandao.update("delete from meteor.T_AOOPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(i)+"'");//AO工装
			    	bombeandao.update("delete from meteor.T_AOREQUIREPART where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(i)+"'");//AO零件配套
		    	}
		    	if(isZero1==0 && isZero2==0){
		    		bombeandao.update("delete from meteor.ebom WHERE product_id='"+product_id
			        		  +"' and father_item_id='"+father_item_id+"' and item_id='"+item_id+"'and id='"+id
			        		  +"' and fid='"+fid+"' and level_id='"+level_id+"' and issue_num='"+issue_num+"'");
		    	}else{
		    		bombeandao.update("Update meteor.ebom Set issue_num='"+s+"' WHERE product_id='"+product_id
			        		  +"' and father_item_id='"+father_item_id+"' and item_id='"+item_id+"'and id='"+id
			        		  +"' and fid='"+fid+"' and level_id='"+level_id+"' and issue_num='"+issue_num+"'");
		    	}
		    	
		        for(int i=m;i<=n-1;i++) {
		        	ArrayList array = bombeandao.getBomDel(i);
		        	if(array.size()!=0 && array!=null){
		        		for(int j = 0;j<array.size();j++){
		        			Bom bom = (Bom)array.get(j);
		                    String father_id = bom.getItem_id();
		                    String mmfid     = bom.getId();
		                    int ii=i+1;
		                    String level_new=ii+"";
		                    if(level_new.length()==1){level_new="0"+level_new;}
		                    ArrayList array2 = bombeandao.selBom(product_id, father_id, mmfid, ii, issue_like);
		                    if(array2.size()!=0 && array2!=null){
				        		for(int k = 0;k<array2.size();k++){
				        			Bom bom2 = (Bom)array2.get(k);
				        			String item_id2 = bom2.getItem_id();
		                            String id2      = bom.getId();
		                            String issue_num2= bom2.getIssue_num();
		                         
		                            StringBuffer buf2 = new StringBuffer(issue_num2);
		          		            buf2.replace(a,b,"0");
		          		            String s2 = buf2.toString();
		          		         t1=s2.substring(0, 50);
		   	                     t2=s2.substring(50,99);
		   	                    //System.out.println(t1+t2);
		   	                    isZero1 = new Double(Double.parseDouble(t1));
		   	                    isZero2 = new Double(Double.parseDouble(t2));
		   	                    
		          		            //isZero = new Double(Double.parseDouble(s2));
		          		          bombeandao.update("delete from meteor.mater_bom WHERE product_id='"+product_id+"'and father_item_id='"+father_id+
		                                    "'and bom_item_id='"+item_id2+"'and id='"+id2+"' and fid='"+mmfid+"'and level_id='"+level_new+"' and issue_num='"+issue_n+"'");
		          		          bombeandao.update("delete from meteor.tool_bom WHERE product_id='"+product_id+"'and father_item_id='"+father_id+
		                                    "'and item_id='"+item_id2+"'and id='"+id2+"' and fid='"+mmfid+"'and level_id='"+level_new+"' and issue_num='"+issue_n+"'");
		          		          bombeandao.update("delete from meteor.hmater_bom WHERE product_id='"+product_id+"'and father_item_id='"+father_id+
		                                    "'and item_id='"+item_id2+"'and id='"+id2+"' and fid='"+mmfid+"'and level_id='"+level_new+"' and issue_num='"+issue_n+"'");
		          		          bombeandao.update("delete from meteor.aomain where flighttype='"+flight_type+"' and productid='"+product_id
		      		                        +"' and issue_num = '"+issue_n+"' and itemid = '"+item_id2+"'");
		      		    	      bombeandao.update("delete from meteor.ao_oper where flighttpye='"+flight_type+"' and produceid='"+product_id
		                                    +"' and issue_num = '"+issue_n+"' and itemid = '"+item_id2+"'");
		      		    	      bombeandao.update("delete from meteor.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id
	      		                            +"' and issue_num = '"+issue_n+"' and item_id = '"+item_id2+"'");
	      		    	          bombeandao.update("delete from meteor.fo where flight_type='"+flight_type+"' and product_id='"+product_id
	                                        +"' and issue_num = '"+issue_n+"' and item_id = '"+item_id2+"'");
	      		    	       // ao_no = aobeandao.getAo_no(flight_type, product_id, issue_n, item_id2);
	      				    	for(int g = 0; g<ao_no.size(); g++){
	      				    		bombeandao.update("delete from meteor.T_OPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and item_id='"+item_id2+"'");//FO工序工装
	      					    	//bombeandao.update("delete from meteor.T_MDMWORKPLACE where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//工位结构
	      					    	bombeandao.update("delete from meteor.T_MDMAO where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+issue_n+"' and childaocode='"+(String)ao_no.get(g)+"'");//工位AO结构
	      					    	bombeandao.update("delete from meteor.T_AOSTANDARDPART where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO标准件
	      					    	bombeandao.update("delete from meteor.T_AOAUXMATLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO辅料
	      					    	bombeandao.update("delete from meteor.T_AOPROEQUIPLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO工序工装
	      					    	bombeandao.update("delete from meteor.T_AOOPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO工装
	      					    	bombeandao.update("delete from meteor.T_AOREQUIREPART where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO零件配套
	      				    	}
	      				    	if(isZero1==0 && isZero2==0){
		          		    	bombeandao.update("delete from  meteor.ebom WHERE product_id='"+product_id+"'and father_item_id='"+father_id+
	                                      "'and item_id='"+item_id2+"'and id='"+id2+"' and fid='"+mmfid+"'and level_id='"+level_new+"' and issue_num='"+issue_num2+"'");
		          		      }else{
		          		    	bombeandao.update("Update  meteor.ebom Set issue_num='"+s2+"' WHERE product_id='"+product_id+"'and father_item_id='"+father_id+
	                                      "'and item_id='"+item_id2+"'and id='"+id2+"' and fid='"+mmfid+"'and level_id='"+level_new+"' and issue_num='"+issue_num2+"'");
		          		      }
		          		      bombeandao.update("INSERT INTO meteor.bom_del (product_id,item_id,id,father_item_id,fid,level_id,issue_num)"+
			                          " Values ('"+product_id+"','"+item_id2+"','"+id2+"','"+father_id+"','"+mmfid+"','"+level_new+"','"+issue_num2+"')");
		                      }
		                   } 
		                }
		        	 }
		          }    
		              //sqlbean.executeDelete("delete from meteor.bom_del");
		     }else{	 
		                 //System.out.print("最后一层");
		    	 bombeandao.update("delete from meteor.hmater_bom WHERE product_id='"+product_id
		        		  +"'and father_item_id='"+father_item_id+"'and item_id='"+item_id+"'and id='"+id
		        		  +"' and fid='"+fid+"'and level_id='"+level_id+"' and issue_num='"+issue_n+"'");
		    	 bombeandao.update("delete from meteor.mater_bom WHERE product_id='"+product_id
		        		  +"'and father_item_id='"+father_item_id+"'and bom_item_id='"+item_id+"'and id='"+id
		        		  +"' and fid='"+fid+"'and level_id='"+level_id+"' and issue_num='"+issue_n+"'");
		    	 bombeandao.update("delete from meteor.tool_bom WHERE product_id='"+product_id
		        		  +"'and father_item_id='"+father_item_id+"'and item_id='"+item_id+"'and id='"+id
		        		  +"' and fid='"+fid+"'and level_id='"+level_id+"' and issue_num='"+issue_n+"'");
		    	 bombeandao.update("delete from meteor.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id
		                  +"' and issue_num = '"+issue_n+"' and item_id = '"+item_id+"'");
		    	bombeandao.update("delete from meteor.fo where flight_type='"+flight_type+"' and product_id='"+product_id
		                  +"' and issue_num = '"+issue_n+"' and item_id = '"+item_id+"'");
		    	 bombeandao.update("delete from meteor.aomain where flighttype='"+flight_type+"' and productid='"+product_id
		                  +"' and issue_num = '"+issue_n+"' and itemid = '"+item_id+"'");
		    	 bombeandao.update("delete from meteor.ao_oper where flighttpye='"+flight_type+"' and produceid='"+product_id
                          +"' and issue_num = '"+issue_n+"' and itemid = '"+item_id+"'");
		    	// ao_no = aobeandao.getAo_no(flight_type, product_id, issue_n, item_id);
			    	for(int g = 0; g<ao_no.size(); g++){
			    		bombeandao.update("delete from meteor.T_OPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and item_id='"+item_id+"'");//FO工序工装
				    	//bombeandao.update("delete from meteor.T_MDMWORKPLACE where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//工位结构
				    	bombeandao.update("delete from meteor.T_MDMAO where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+issue_n+"' and childaocode='"+(String)ao_no.get(g)+"'");//工位AO结构
				    	bombeandao.update("delete from meteor.T_AOSTANDARDPART where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO标准件
				    	bombeandao.update("delete from meteor.T_AOAUXMATLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO辅料
				    	bombeandao.update("delete from meteor.T_AOPROEQUIPLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO工序工装
				    	bombeandao.update("delete from meteor.T_AOOPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO工装
				    	bombeandao.update("delete from meteor.T_AOREQUIREPART where flight_type='"+flight_type+"' and product_id='"+product_id+"'  and issue_num = '"+issue_n+"' and ao_no='"+(String)ao_no.get(g)+"'");//AO零件配套
			    	}
			    	if(isZero1==0 && isZero2==0){
		    		    bombeandao.update("delete from meteor.ebom WHERE product_id='"+product_id
			                  +"'and father_item_id='"+father_item_id+"'and item_id='"+item_id+"'and id='"+id
		                      +"' and fid='"+fid+"'and level_id='"+level_id+"' and issue_num='"+issue_num+"'");
			    	}else{
			    		bombeandao.update("Update  meteor.ebom Set issue_num='"+s+"' WHERE product_id='"+product_id
				                  +"'and father_item_id='"+father_item_id+"'and item_id='"+item_id+"'and id='"+id
			                      +"' and fid='"+fid+"'and level_id='"+level_id+"' and issue_num='"+issue_num+"'");
			    	}
		     }
		if( m == 1 ){
			bombeandao.update("delete from meteor.issue_deploy where flight_type='"+flight_type
	                 +"' and product_id='"+product_id+"' and issue_pos='"+issue+"'");
		}    
	    }catch(Exception e){
	    	System.out.println("运行时出错9："+e);
	    }  
        return m; 
	}
	//删除原材料 辅料 工装
	public void delBMHT(String type ,String product_id, String issue_num ,String item_id,String father_item_id,String level_id,String id,String fid)
   {
		sql_data sqlbean=new sql_data();
		String sql=""; 
		try{
		if(type.equals("Y")){
			sql = "delete from meteor.MATER_BOM where PRODUCT_ID='"+product_id+"' and BOM_ITEM_ID='"+item_id+"' and FATHER_ITEM_ID='"+father_item_id
			 +"' and ID ='"+id+"' and FID ='"+fid+"' and LEVEL_ID ='"+level_id+"' and ITEM_ID ='"+bom_other_id+"' and issue_num='"+issue_num+"'";
		}else if(type.equals("F")){
			sql = "delete from meteor.HMATER_BOM where PRODUCT_ID='"+product_id+"' and ITEM_ID='"+item_id+"' and FATHER_ITEM_ID='"+father_item_id
			 +"' and ID ='"+id+"' and FID ='"+fid+"' and LEVEL_ID ='"+level_id+"' and H_ITEM_ID ='"+bom_other_id+"' and issue_num='"+issue_num+"'";
		}else if(type.equals("T")){
			sql = "delete from meteor.TOOL_BOM where PRODUCT_ID='"+product_id+"' and ITEM_ID='"+item_id+"' and FATHER_ITEM_ID='"+father_item_id
			 +"' and ID ='"+id+"' and FID ='"+fid+"' and LEVEL_ID ='"+level_id+"' and TOOL_ID ='"+bom_other_id+"' and issue_num='"+issue_num+"'";
		}
		
		sqlbean.executeUpdate(sql);
       }catch(Exception ex) {
	      System.out.println("delBMHT1 " + ex);
	   }finally{
 	    sqlbean.closeConn(); 
       }
	}
	//删除多条记录
	public void delBMHT(String type ,String ps,String product_id, String issue_num ,String item_id,String father_item_id,String level_id,String id,String fid){
		sql_data sqlbean=new sql_data();
		String sql="";
		try{
        if(type.equals("Y")){
        	sql="delete from meteor.MATER_BOM where PRODUCT_ID = '"+product_id+"' and BOM_ITEM_ID ='"+item_id
    		+"' and FATHER_ITEM_ID='"+father_item_id+"' and ID ='"+id+"' and FID='"+fid+"' and LEVEL_ID='"+level_id
    		+"' and issue_num='"+issue_num+"' and ITEM_ID in("+ps+")";
		}else if(type.equals("F")){
			sql="delete from meteor.HMATER_BOM where PRODUCT_ID = '"+product_id+"' and ITEM_ID ='"+item_id
			+"' and FATHER_ITEM_ID='"+father_item_id+"' and ID ='"+id+"' and FID='"+fid+"' and LEVEL_ID='"+level_id
			+"' and issue_num='"+issue_num+"' and H_ITEM_ID in("+ps+")";
		}else if(type.equals("T")){
			sql="delete from meteor.TOOL_BOM where PRODUCT_ID = '"+product_id+"' and ITEM_ID ='"+item_id
			+"' and FATHER_ITEM_ID='"+father_item_id+"' and ID ='"+id+"' and FID='"+fid+"' and LEVEL_ID='"+level_id
			+"' and issue_num='"+issue_num+"' and TOOL_ID in("+ps+")";
		}
	 	sqlbean.executeUpdate(sql);
	    }catch(Exception ex) {
		      System.out.println("delBMHT2 " + ex);
		}finally{
	    	 sqlbean.closeConn(); 
	    }
	}
//清空通讯录记录
	public void clearRecord(String type ,String product_id,String issue_num ,String item_id,String father_item_id,String level_id,String id,String fid){
		sql_data sqlbean=new sql_data();
		try{
		if(type.equals("Y")){
        	sqlbean.executeUpdate("delete from meteor.MATER_BOM where PRODUCT_ID = '"+product_id+"' and BOM_ITEM_ID ='"+item_id
    				+"' and FATHER_ITEM_ID ='"+father_item_id +"' and ID ='"+id+"' and FID ='"+fid
    				+"' and LEVEL_ID ='"+level_id+"' and issue_num='"+issue_num+"'");
		}else if(type.equals("F")){
			sqlbean.executeUpdate("delete from meteor.HMATER_BOM where PRODUCT_ID = '"+product_id+"' and ITEM_ID ='"+item_id
					+"' and FATHER_ITEM_ID ='"+father_item_id +"' and ID ='"+id+"' and FID ='"+fid
					+"' and LEVEL_ID ='"+level_id+"' and issue_num='"+issue_num+"'");
		}else if(type.equals("T")){
			sqlbean.executeUpdate("delete from meteor.TOOL_BOM where PRODUCT_ID = '"+product_id+"' and ITEM_ID ='"+item_id
					+"' and FATHER_ITEM_ID ='"+father_item_id +"' and ID ='"+id+"' and FID ='"+fid
					+"' and LEVEL_ID ='"+level_id+"' and issue_num='"+issue_num+"'");
		}
		}catch(Exception ex) {
		      System.out.println("clearRecord " + ex);
		}finally{
	    	 sqlbean.closeConn(); 
	    }
		
	}
//取得当前页面
	public Vector getOnePage(String paramString, int paramInt1, int paramInt2)
	{//String sql,int page,int records
		sql_data sqlbean=new sql_data();
	    Vector localVector = new Vector();
	    int i = paramInt2;
	    try
	    {
	      rs= sqlbean.executeQuery(paramString);
	     // System.out.println(rs);//1
	      int j = 0;
	      while (rs.next())
	      {
	        ++j;
	      }//System.out.println(j);//2
	      int k = j / i;
	      //System.out.println(k);//3
	      if ((j % i != 0) || (j == 0)) ++k;
	      localVector.add("" + k);
	      //System.out.println(localVector);//4
	      rs= sqlbean.executeQuery(paramString);
	      j = (paramInt1 - 1) * i;
	      rs.absolute(j + 1);//System.out.println(rs);
	      rs.previous();
	      //System.out.println(rs);
	      int l = 0;
	      while (true) { 
	    	if (!(rs.next()))break ;
	        if (l == i) break ;
	        	++l;
	        	
	        ResultSetMetaData localResultSetMetaData = rs.getMetaData();
	        int i1 = localResultSetMetaData.getColumnCount();
	        System.out.println(i1);
	        Hashtable localHashtable = new Hashtable();
	        for (int i2 = 1; i2 <= i1; i2++)
	        {
	          String str2 = ds.toString(localResultSetMetaData.getColumnName(i2));
	          String str3 = ds.toString(rs.getString(i2));
	          //System.out.println(localResultSetMetaData.getColumnName(i2));
		      //System.out.println(rs.getString(i2));
	          localHashtable.put(str2, str3);
	        }
	        localVector.add(localHashtable); 
	        }rs.close();//sqlbean.closeStmt();
//	        sqlbean.closeConn();    
	    } catch (SQLException localSQLException1) {
	      System.out.println("执行SQL语句 " + paramString + " 分页至第 " + paramInt1 + " 页时出错;错误为:" + localSQLException1);
	    } finally{
	    	 sqlbean.closeConn(); 
	    }
	    return localVector;
	  }
	/**新增记录*/
	public void insertRecord(Vector vect)
	{
		sql_data sqlbean=new sql_data();
		/**Vector:第1项 表名(String)
		//		 第2项 列名(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])*/
     
		//临时变量
		String sqlField = "";//形如(F1,F2)
		String sqlValue = "";//形如(V1,V2)
		String field = "";
		String value = "";
		String type = "";
		try{
		for(int i=1;i<vect.size();i++)
		{
			//对某一个字段
			Vector v_t = (Vector)vect.get(i);
			field = (String)v_t.get(0);
			value = (String)v_t.get(1);
			//System.out.println("field:"+field+" value:"+value);
			if (value.indexOf("'")!=-1)
			{
				value = value.replaceAll("'","''");
			}
			type = (String)v_t.get(2);

			//组合字段SQL
			if(sqlField.equals(""))sqlField = "(";
			else sqlField = sqlField + ",";
			sqlField = sqlField + field;

			//组合值SQL
			if(sqlValue.equals(""))sqlValue = "(";
			else sqlValue = sqlValue + ",";
			if(value.equals(""))//为空时
			{
				sqlValue = sqlValue + "null";
			}
			else if(type.equals("CHAR"))//字符串
			{
				sqlValue = sqlValue + "'" + value + "'";
			}
			else if(type.equals("NUM"))//数值
			{
				sqlValue = sqlValue + value;
			}
			else if(type.equals("TIME"))//日期
			{
				sqlValue = sqlValue + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
			}
			else if(type.equals("CLOB"))//clob类型
			{
				sqlValue = sqlValue + "empty_clob()";
			}
			else if(type.equals("BLOB"))//blob类型
			{
				sqlValue = sqlValue + "empty_blob()";
			}
		}

		sqlField = sqlField + ")";
		sqlValue = sqlValue + ")";
		String sql = "insert into " + (String)vect.get(0) + sqlField + " values" + sqlValue;
	    //System.out.println(sql);
		sqlbean.executeUpdate(sql);
	    } catch(Exception e){
			e.printStackTrace();
		}finally{
	    	 sqlbean.closeConn(); 
	    }
	}
//	public int ExecuteSQL(String paramString)
//	  {
//	    try
//	    {
//	      this.pstm = this.conn.prepareStatement(paramString);
//	      this.pstm.executeUpdate();
//	      this.conn.commit();
//	    }
//	    catch (SQLException localSQLException1)
//	    {
//	      System.out.println("调用SQL语句 " + paramString + " 时出错;\r\n错误为:" + localSQLException1);
//	      return localSQLException1.getErrorCode();
//	    }
//	    finally {
//	      try {
//	        this.pstm.close(); } catch (SQLException localSQLException2) {
//	        System.out.println("调用SQL语句 " + paramString + " 时出错;\r\n错误为:" + localSQLException2); }
//	    }
//	    return 0;
//	  }

	/**修改记录*/
	public void updateRecord(Vector vect)
	{
		/**Vector:第1项 表名(String)
		//		 第2项 列名(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])
		//		 第3项 条件(String sql)*/

		//临时变量
		sql_data sqlbean=new sql_data();
		String sqlSet = "";//形如(Name='name',ID=9)
		String field = "";
		String value = "";
		String type = "";
		try{
			
		

		int i = 1;
		int n = vect.size();
		for(;i<(n-1);i++)
		{
			//对某一个字段
			Vector v_t = (Vector)vect.get(i);
			field = (String)v_t.get(0);
			value = (String)v_t.get(1);
			if (value.indexOf("'")!=-1)
			{
				value = value.replaceAll("'","''");
			}
			type = (String)v_t.get(2);

			//组合字段SQL
			if(sqlSet.equals(""))sqlSet = " ";
			else sqlSet = sqlSet + ",";
			sqlSet = sqlSet + field + "=";
			if(value.equals("")&&type.equals("NUM"))//为空时
			{
				sqlSet = sqlSet + "null";
			}
			if(type.equals("CHAR"))//字符串
			{
				sqlSet = sqlSet + "'" + value + "'";
			}
			else if(type.equals("NUM"))//数值
			{
				sqlSet = sqlSet + value;
			}
			else if(type.equals("TIME"))//日期
			{
				sqlSet = sqlSet + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
			}
			else if(type.equals("CLOB"))//clob类型
			{
			}
			else if(type.equals("BLOB"))//blob类型
			{
			}
		}

		String sql = "update " + (String)vect.get(0) + " set " + sqlSet;
		String sqlWhere = (String)vect.get(vect.size()-1);
		
		if(!sqlWhere.equals(""))
		{
			sql = sql + " where " + sqlWhere;
		}//System.out.print(sql);
		
		sqlbean.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	    	 sqlbean.closeConn(); 
	    }
	}
	
}
