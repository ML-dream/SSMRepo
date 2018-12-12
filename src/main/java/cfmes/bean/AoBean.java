package cfmes.bean;

import java.text.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.sql.*;


import cfmes.bom.entity.Ao;
import cfmes.util.DealString;
import cfmes.util.sql_data;
import cfmes.bom.dao.AoBeanDao;
import cfmes.bom.dao.BomBeanDao;
import cfmes.bom.dao.BomImportDao;
import cfmes.bom.dao.FoBeanDao;
import cfmes.bom.dao.IssueBeanDao;

public class AoBean {

	DealString ds = new DealString();
	private String flight_type;
    private String product_id;
    private String issue_num;
    private String item_id;
    private String fo_no;
    private String fover;
	public void setFover(String fover) {
		this.fover = fover;
	}
	public void setFo_no(String fo_no) {
		this.fo_no = fo_no;
	}
	public void setFlight_type(String flight_type) {
		this.flight_type = flight_type;
	}
	public void setIssue_num(String issue_num) {
		this.issue_num = issue_num;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public void addAo(Hashtable hash){
		String flight_type = ds.toString((String)hash.get("FLIGHT_TYPE"));
		String product_id = ds.toString((String)hash.get("PRODUCT_ID"));
		String issue_num = ds.toString((String)hash.get("ISSUE_NUM"));
		String item_id = ds.toString((String)hash.get("ITEM_ID"));
		String ao_no = ds.toString((String)hash.get("AO_NO"));
		String aoname = ds.toString((String)hash.get("AONAME"));
		String aover = ds.toString((String)hash.get("AOVER"));
		String ao_time = ds.toString((String)hash.get("AO_TIME"));
		String workplacecode = ds.toString((String)hash.get("WORKPLACECODE"));
		String workplacename = ds.toString((String)hash.get("WORKPLACENAME"));
		String partno = ds.toString((String)hash.get("PARTNO"));
		
		Vector vect = new Vector();
		
		vect.add("work.AOMAIN");
		vect.add(addVector("PRODUCT_TYPE",flight_type,"CHAR"));
		vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
		vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
		vect.add(addVector("ITEM_ID",item_id,"CHAR"));
		vect.add(addVector("AO_NO",ao_no,"CHAR"));
		vect.add(addVector("AO_NAME",aoname,"CHAR"));
		vect.add(addVector("AO_VER",aover,"CHAR"));
		vect.add(addVector("AO_TIME",ao_time,"NUM"));
		vect.add(addVector("WORKPLACECODE",workplacecode,"CHAR"));
		vect.add(addVector("WORKPLACENAME",workplacename,"CHAR"));
		vect.add(addVector("PART_NO",partno,"CHAR"));
		
		insertRecord(vect);
	}
	/**�޸�һ��bom�汾����ֹ�ܴκͰ汾��*/
	public  void modAo(Hashtable hash){
		String flight_type = ds.toString((String)hash.get("FLIGHT_TYPE"));
		String product_id = ds.toString((String)hash.get("PRODUCT_ID"));
		String issue_num = ds.toString((String)hash.get("ISSUE_NUM"));
		String item_id = ds.toString((String)hash.get("ITEM_ID"));
		String ao_no_old = ds.toString((String)hash.get("AO_NO_OLD"));
		String aover_old = ds.toString((String)hash.get("AOVER_OLD"));
		String ao_no = ds.toString((String)hash.get("AO_NO"));
		String aoname = ds.toString((String)hash.get("AONAME"));
		String aover = ds.toString((String)hash.get("AOVER"));
		String ao_time = ds.toString((String)hash.get("AO_TIME"));
		String workplacecode = ds.toString((String)hash.get("WORKPLACECODE"));
		String workplacename = ds.toString((String)hash.get("WORKPLACENAME"));
		String partno = ds.toString((String)hash.get("PARTNO"));
		Vector vect = new Vector();
			String ao_order = ds.backStr(ao_no, 3); 
			vect.add("meteor.AOMAIN");
			vect.add(addVector("AO_NO",ao_no,"CHAR"));
			vect.add(addVector("AONAME",aoname,"CHAR"));
			vect.add(addVector("AOVER",aover,"CHAR"));
			vect.add(addVector("AO_TIME",ao_time,"NUM"));
			vect.add(addVector("WORKPLACECODE",workplacecode,"CHAR"));
			vect.add(addVector("WORKPLACENAME",workplacename,"CHAR"));
			vect.add(addVector("PARTNO",partno,"CHAR"));
			vect.add(addVector("AO_ORDER",ao_order,"NUM"));
			vect.add("FLIGHTTYPE = '"+flight_type+"' and PRODUCTID ='"+product_id+"' and ISSUE_NUM ='"+issue_num+"' and ITEMID='"+item_id
					+"' and AO_NO='"+ao_no_old+"' and AOVER='"+aover_old+"'");
			updateRecord(vect);
			
			Vector vect1 = new Vector();
			vect1.add("meteor.AO_OPER");
			vect1.add(addVector("AOVER",aover,"CHAR"));
			vect1.add(addVector("AO_NO",ao_no,"CHAR"));
			vect1.add("FLIGHTTPYE = '"+flight_type+"' and PRODUCEID ='"+product_id+"' and ISSUE_NUM ='"+issue_num+"' and ITEMID='"+item_id
					+"' and AO_NO='"+ao_no_old+"' and AOVER='"+aover_old+"'");
			updateRecord(vect1);
		
	}
	/**ɾ��һ��bom�汾*/
	public void delAo(String flight_type,String product_id,String issue_num,String item_id,String ao_no,String aover){
		AoBeanDao aobeandao = new AoBeanDao();
		String sql = "delete from work.aomain where product_type='"+flight_type+"' and product_id='"+product_id
		           +"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"' and ao_no = '"+ao_no+"' and ao_ver = '"+aover+"'";
		aobeandao.update(sql);
	}
	/**ȡ���б�displayIssue.jsp������*/
	public Vector getData(String pxzd,boolean isdesc,String srchzd,String srchzdval,boolean isexact,String flight_type,String product_id,String issue_num,String item_id){
		//pxzd���ĸ��ֶ�����isdesc�Ƿ������У�srchzd���ĸ��ֶ�������isexact�Ƿ�ȷ��ѯ
		BomBeanDao bombeandao = new BomBeanDao();
		sql_data sqlbean = new sql_data();
		Vector vect = new Vector();
		String sql = "";
		try{
		sql = "select ao_no,ao_name,ao_ver,ao_time,workplacecode,workplacename,part_no from work.aomain where product_type='"+flight_type
		      +"' and product_id='"+product_id+"' and issue_num='"+issue_num+"' and item_id='"+item_id+"'";		
		if(!srchzd.equals(""))
		{
			if(isexact)
				sql = sql+" and "+srchzd+"='"+srchzdval+"'";
			if(!isexact)
				sql = sql+" and "+srchzd+" like '%"+srchzdval+"%'";
		}
		if(!pxzd.equals(""))
		{	
			sql = sql+" order by "+pxzd;
		}
		if(isdesc)
		{
			sql = sql+" DESC";
		}
		Hashtable ht = new Hashtable();
		ht.put("sql",sql);
		vect.add(ht);
		
		}catch(Exception e){
			System.out.println("����ʱ���?"+e);
		}finally{
			sqlbean.closeConn();
		}
		return vect;
	}
	
	//
	public Hashtable getData2(String sql){
		sql_data sqlbean=new sql_data();
		Hashtable hash = new Hashtable();
		ResultSet rs;
		try{
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
				
			}rs.close();
		}catch(Exception e){System.out.println("getOther����ʱ����7��"+e);}
		finally{
			sqlbean.closeConn(); 
		}
		return hash;
	}
	
//	ȡ�õ�ǰҳ��
	public Vector getOnePage(String sql, int paramInt1, int paramInt2){//String sql,int page,int records
		sql_data sqlbean=new sql_data();
	    Vector localVector = new Vector();
	    ResultSet rs;
	    int i = paramInt2;
	    try
	    {
	      rs= sqlbean.executeQuery(sql);
	      int j = 0;
	      while (rs.next())
	      {
	        ++j;
	      }
	      localVector.add("" + j);
	      int k = j / i;
	      if ((j % i != 0) || (j == 0)) ++k;
	      localVector.add("" + k);
	      rs= sqlbean.executeQuery(sql);
	      j = (paramInt1 - 1) * i;
	      rs.absolute(j + 1);
	      rs.previous();
	      int l = 0;
	      while (true) { 
	    	if (!(rs.next()))break ;
	        if (l == i) break ;
	        	++l;
	        ResultSetMetaData localResultSetMetaData = rs.getMetaData();
	        int i1 = localResultSetMetaData.getColumnCount();
	        Hashtable localHashtable = new Hashtable();
	        for (int i2 = 1; i2 <= i1; i2++){
	          String str2 = ds.toString(localResultSetMetaData.getColumnName(i2));
	          String str3 = ds.toString(rs.getString(i2));
	          localHashtable.put(str2, str3);
	          
	        }
	        localVector.add(localHashtable); 
	        }rs.close();  
	    } catch (SQLException localSQLException1) {
	      System.out.println("ִ��SQL��� " + sql + " ��ҳ���� " + paramInt1 + " ҳʱ����;����Ϊ:" + localSQLException1);
	    } finally{
	    	 sqlbean.closeConn(); 
	    }
	    return localVector;
	  }

	/**д��ݿ�ʱĳһ���ֶεĴ洢����*/
	protected Vector addVector(String field,String value,String type)
	{
		Vector vect = new Vector();
		vect.add(field);
		vect.add(value);
		vect.add(type);
		return vect;
	}
	/**������¼*/
	public void insertRecord(Vector vect)
	{
		sql_data sqlbean=new sql_data();
		/**Vector:��1�� ����(String)
		//		 ��2�� ����(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])*/
	
		//��ʱ����
		String sqlField = "";//����(F1,F2)
		String sqlValue = "";//����(V1,V2)
		String field = "";
		String value = "";
		String type = "";
		try{
		for(int i=1;i<vect.size();i++)
		{
			//��ĳһ���ֶ�
			Vector v_t = (Vector)vect.get(i);
			field = (String)v_t.get(0);
			value = (String)v_t.get(1);
			if (value.indexOf("'")!=-1)
			{
				value = value.replaceAll("'","''");
			}
			type = (String)v_t.get(2);
	
			//����ֶ�SQL
			if(sqlField.equals(""))sqlField = "(";
			else sqlField = sqlField + ",";
			sqlField = sqlField + field;
	
			//���ֵSQL
			if(sqlValue.equals(""))sqlValue = "(";
			else sqlValue = sqlValue + ",";
			if(value.equals(""))//Ϊ��ʱ
			{
				sqlValue = sqlValue + "null";
			}
			else if(type.equals("CHAR"))//�ַ�
			{
				sqlValue = sqlValue + "'" + value + "'";
			}
			else if(type.equals("NUM"))//��ֵ
			{
				sqlValue = sqlValue + value;
			}
			else if(type.equals("TIME"))//����
			{
				sqlValue = sqlValue + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
			}
			else if(type.equals("CLOB"))//clob����
			{
				sqlValue = sqlValue + "empty_clob()";
			}
			else if(type.equals("BLOB"))//blob����
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

		/**�޸ļ�¼*/
		public void updateRecord(Vector vect)
		{
			/**Vector:��1�� ����(String)
			//		 ��2�� ����(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])
			//		 ��3�� ����(String sql)*/
	
			//��ʱ����
			sql_data sqlbean=new sql_data();
			String sqlSet = "";//����(Name='name',ID=9)
			String field = "";
			String value = "";
			String type = "";
			try{
				
			
	
			int i = 1;
			int n = vect.size();
			for(;i<(n-1);i++)
			{
				//��ĳһ���ֶ�
				Vector v_t = (Vector)vect.get(i);
				field = (String)v_t.get(0);
				value = (String)v_t.get(1);
				if (value.indexOf("'")!=-1)
				{
					value = value.replaceAll("'","''");
				}
				type = (String)v_t.get(2);
	
				//����ֶ�SQL
				if(sqlSet.equals(""))sqlSet = " ";
				else sqlSet = sqlSet + ",";
				sqlSet = sqlSet + field + "=";
				if(value.equals("")&&type.equals("NUM"))//Ϊ��ʱ
				{
					sqlSet = sqlSet + "null";
				}
				if(type.equals("CHAR"))//�ַ�
				{
					sqlSet = sqlSet + "'" + value + "'";
				}
				else if(type.equals("NUM"))//��ֵ
				{
					sqlSet = sqlSet + value;
				}
				else if(type.equals("TIME"))//����
				{
					sqlSet = sqlSet + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
				}
				else if(type.equals("CLOB"))//clob����
				{
				}
				else if(type.equals("BLOB"))//blob����
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
		/**ɾ����е����*/
		public void delTable(String sql){
		    sql_data sqlbean = new sql_data();
		    try {
		    	sqlbean.executeUpdate(sql);
		    } catch (Exception ex) {
		    	System.out.println("AoBeanDao.delTable()����ʱ���?����Ϊ��"+ex);
		    } finally {
		    	sqlbean.closeConn();
		    }
		}

		/**��ӵ�����ԭ��**/
		public void addAoCMT(String flight_type,String product_id,String issue_num,String item_id,String aono,String aover,String fox_id,String num,String type){
			AoBeanDao aobeandao = new AoBeanDao();
			String sql="";
			if(fox_id.equals("")||fox_id==null){
				
			}else{

				 sql = "insert into work.ao_"+type+"("+type+"_id,"+type+"_num,product_type,product_id,item_id,issue_num,ao_no,ao_ver) values ('"+
				 fox_id+"','"+num+"','"+flight_type+"','"+product_id+"','"+item_id+"','"+issue_num+"','"+aono+"','"+aover+"')";
			
			aobeandao.update(sql);
		}
		}
		
		/**ɾ�����о�ԭ��**/
		public void delCMT(String aono,String aover,String table,String id){
			AoBeanDao aobeandao = new AoBeanDao();
			String sql = "delete from work.ao_"+table+" where product_type='"+flight_type+"' and product_id='"+product_id
	                   +"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"' and ao_no = '"+aono+"' and ao_ver='"+aover+"' and "+table+"_id='"+id+"'";
			aobeandao.update(sql);
		}
}
