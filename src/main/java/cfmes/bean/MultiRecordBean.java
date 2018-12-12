package cfmes.bean;

import java.util.Hashtable;
import java.util.Vector;


import cfmes.util.DealString;
import cfmes.util.sql_data;

public class MultiRecordBean {
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
		String search_condition = "";//��ѯ����
		String field = "";
		String value = "";
		String type = "";
		try{

		int i = 1;
		int n = vect.size();
		for(;i<n;i++)
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
			if(search_condition.equals(""))search_condition="";
			else {
				if(field.equals("issue_num")||field.equals("item_num")){}else{
			search_condition = search_condition + " and ";}}
			if(field.equals("issue_num")||field.equals("item_num")){}else{
				search_condition = search_condition + field+"=";
			}
			
			if(sqlSet.equals(""))sqlSet = " ";
			else sqlSet = sqlSet + ",";
			sqlSet = sqlSet + field + "=";
			if(value.equals("")&&type.equals("NUM"))//Ϊ��ʱ
			{
				if(field.equals("issue_num")||field.equals("item_num")){}else{
			search_condition = search_condition+"null";}
				sqlSet = sqlSet + "null";
			}
			if(type.equals("CHAR"))//�ַ�
			{
				if(field.equals("issue_num")||field.equals("item_num")){}else{
				search_condition = search_condition+"'"+value+"'";}
				sqlSet = sqlSet + "'" + value + "'";
			}
			else if(type.equals("NUM"))//��ֵ
			{
				if(field.equals("issue_num")||field.equals("item_num")){}else{
				search_condition = search_condition+value;}
				sqlSet = sqlSet + value;
			}
			else if(type.equals("TIME"))//����
			{
				if(field.equals("issue_num")||field.equals("item_num")){}else{
				search_condition = search_condition+"to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";}
				sqlSet = sqlSet + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
			}
			else if(type.equals("CLOB"))//clob����
			{
			}
			else if(type.equals("BLOB"))//blob����
			{
			}
		}

		String sql = "update " + (String)vect.get(0) + " set " + sqlSet + " where "
		            + search_condition;
		sqlbean.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	    	 sqlbean.closeConn(); 
	    }
	}
	
	//��ѯ��¼
	public void QueryRecord(Vector vect){
		String sql="";
		String field ="";
		String value ="";
		sql_data sqlbean=new sql_data();
		try{
			for(int i=1;i<vect.size();i++){
				Vector v_t=(Vector)vect.get(i);
				field = (String)v_t.get(0);
				value = (String)v_t.get(1);
				if(value.equals("")||value==null){continue;}
				if(i==1){sql=field+"='"+value+"'";
				}else{sql = sql+" and ";
				sql=sql+field+"='"+value+"'";}
			}
			sql="select * from "+vect.get(0)+" where "+sql;
			sqlbean.executeUpdate(sql);
			//System.out.println(sql);
		}catch(Exception ex){ex.printStackTrace();
		}finally{sqlbean.closeConn(); }
	
	}
	
	public void DelRecord(Vector vect){
		String sql="";
		String field ="";
		String value ="";
		sql_data sqlbean=new sql_data();
		try{
			for(int i=1;i<vect.size();i++){
				Vector v_t=(Vector)vect.get(i);
				field = (String)v_t.get(0);
				value = (String)v_t.get(1);
				if(value.equals("")||value==null){continue;}
				if(i==1){sql=field+"='"+value+"'";
				}else{sql = sql+" and ";
				sql=sql+field+"='"+value+"'";}
			}
			sql="delete  from "+vect.get(0)+" where "+sql;
			sqlbean.executeUpdate(sql);
			//System.out.println(sql);
		}catch(Exception ex){ex.printStackTrace();
		}finally{sqlbean.closeConn(); }
		
	}
	
	
	/**д��ݿ�ʱĳһ���ֶεĴ洢����*/
	public Vector addVector(String field,String value,String type)
	{
		Vector vect = new Vector();
		vect.add(field);
		vect.add(value);
		vect.add(type);
		return vect;
	}
	
	/**���̱���¼���ʱ�ı��Ʒ�汾��*/
	 public Vector EcVector(Vector vect,String str,int index){
		 Vector v =new Vector();
		 v = (Vector)vect.get(index);
		 v = addVector((String)v.get(0),((String)v.get(1)).split("-")[0]+"-"+str,(String)v.get(2));
		 vect.set(index, v);
		 return vect;
	 }  
	 
	 /*��һ���汾��queryrecord**/
	 public String Query_Record(Vector vect){
			String sql="";
			String field ="";
			String value ="";
			DealString ds = new DealString();
			try{
				for(int i=1;i<vect.size();i++){
					Vector v_t=(Vector)vect.get(i);
					field = (String)v_t.get(0);
					value = ds.toString((String)v_t.get(1));
					if(value.equals("")||value==null){continue;}
					if(i==1){sql=field+"='"+value+"'";
					}else{sql = sql+" and ";
					sql=sql+field+"='"+value+"'";}
				}
				sql="select * from "+vect.get(0)+" where "+sql;
				//System.out.println(sql);
			}catch(Exception ex){System.out.println("mbomdao.query_record()����ʱ���?����Ϊ��"+ex);}finally{}
			return sql;
		}
   }

