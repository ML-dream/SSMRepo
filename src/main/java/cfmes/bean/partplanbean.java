package cfmes.bean;
import java.sql.ResultSet;
import java.util.*;


import cfmes.util.DealString;
import cfmes.util.sql_data;

public class partplanbean {
//������Ԥ�ƻ�
	DealString ds = new DealString();
	public void addpartplan(Hashtable hash){
		String plan_id = ds.toString((String)hash.get("plan_id"));
		String plan_time = ds.toString((String)hash.get("plan_time"));
		String plan_peo = ds.toString((String)hash.get("plan_peo"));
		String part_id = ds.toString((String)hash.get("part_id"));
		String father_id = ds.toString((String)hash.get("father_id"));
		String part_num = ds.toString((String)hash.get("part_num"));
		String order_id = ds.toString((String)hash.get("order_id"));
		String plan_bgtime = ds.toString((String)hash.get("plan_bgtime"));
		String plan_edtime = ds.toString((String)hash.get("plan_edtime"));
		String quality_id = ds.toString((String)hash.get("quality_id"));
		String product_id = ds.toString((String)hash.get("product_id"));
		String issue_num = ds.toString((String)hash.get("issue_num"));
		String part_status = "5";
		Vector vect = new Vector();
		vect.add("work.PARTPLAN");
		vect.add(addVector("plan_id",plan_id,"CHAR"));
		vect.add(addVector("plan_time",plan_time,"CHAR"));
		vect.add(addVector("plan_peo",plan_peo,"CHAR"));
		vect.add(addVector("item_id",part_id,"CHAR"));
		vect.add(addVector("father_item_id",father_id,"CHAR"));
		vect.add(addVector("part_num",part_num,"CHAR"));
		vect.add(addVector("order_id",order_id,"CHAR"));
		vect.add(addVector("plan_bgtime",plan_bgtime,"CHAR"));
		vect.add(addVector("plan_edtime",plan_edtime,"CHAR"));
		vect.add(addVector("quality_id",quality_id,"CHAR"));
		vect.add(addVector("product_id",product_id,"CHAR"));
		vect.add(addVector("issue_num",issue_num,"CHAR"));
		vect.add(addVector("part_status",part_status,"CHAR"));
		insertRecord(vect);
		
	}
	
	/**��������ƻ�**/
	public void EditPartPlan(Hashtable hash){
		String plan_id = ds.toString((String)hash.get("plan_id"));
		String plan_time = ds.toString((String)hash.get("plan_time"));
		String plan_peo = ds.toString((String)hash.get("plan_peo"));
		String part_id = ds.toString((String)hash.get("part_id"));
		String father_id = ds.toString((String)hash.get("father_id"));
		String part_num = ds.toString((String)hash.get("part_num"));
		String order_id = ds.toString((String)hash.get("order_id"));
		String plan_bgtime = ds.toString((String)hash.get("plan_bgtime"));
		String plan_edtime = ds.toString((String)hash.get("plan_edtime"));
		String quality_id = ds.toString((String)hash.get("quality_id"));
		String product_id = ds.toString((String)hash.get("product_id"));
		String issue_num = ds.toString((String)hash.get("issue_num"));
		String part_status = ds.toString((String)hash.get("part_status"));
		Vector vect = new Vector();
		vect.add("work.PARTPLAN");
		vect.add(addVector("plan_id",plan_id,"CHAR"));
		vect.add(addVector("plan_time",plan_time,"CHAR"));
		vect.add(addVector("plan_peo",plan_peo,"CHAR"));
		vect.add(addVector("item_id",part_id,"CHAR"));
		vect.add(addVector("father_item_id",father_id,"CHAR"));
		vect.add(addVector("part_num",part_num,"CHAR"));
		vect.add(addVector("order_id",order_id,"CHAR"));
		vect.add(addVector("plan_bgtime",plan_bgtime,"CHAR"));
		vect.add(addVector("plan_edtime",plan_edtime,"CHAR"));
		vect.add(addVector("quality_id",quality_id,"CHAR"));
		vect.add(addVector("product_id",product_id,"CHAR"));
		vect.add(addVector("issue_num",issue_num,"CHAR"));
		vect.add(addVector("part_status",part_status,"CHAR"));
		updateRecord(vect);
	}
	
	/*��ѯ��ݿ��Ƿ��������Ƶ����´������ƻ�*/
	public boolean isdeliveredplan(Hashtable hash){
		boolean istrue = false;
		String product_id = ds.toString((String)hash.get("product_id"));
		String issue_num = ds.toString((String)hash.get("issue_num"));
		String quality_id = ds.toString((String)hash.get("quality_id"));
		String part_id = ds.toString((String)hash.get("part_id"));
		String father_id = ds.toString((String)hash.get("father_id"));
		
		String sql = "select * from work.partplan where product_id = '"+product_id+"' and issue_num = '"+issue_num+
		"' and quality_id = '"+quality_id+"' and item_id = '"+part_id+"' and father_item_id='"+father_id+"' and part_status='15'";
		sql_data sqlbean = new sql_data();
	    ResultSet rs = sqlbean.executeQuery(sql);
	  
	    try {
	           if (rs.next()) {
	           	istrue=true;
	           }rs.close();
	       } catch (Exception ex) {
	       	System.out.println("partplanbean.istrue()����ʱ���?����Ϊ��"+ex);
	       } finally {
	       	sqlbean.closeConn();
	       }
		return istrue;
	}
	
	/*ɾ����ݿ��е�����ƻ�*/
	public void delplan(Hashtable hash){
		String product_id = ds.toString((String)hash.get("product_id"));
		String issue_num = ds.toString((String)hash.get("issue_num"));
		String quality_id = ds.toString((String)hash.get("quality_id"));
		String part_id = ds.toString((String)hash.get("part_id"));
		String father_id = ds.toString((String)hash.get("father_id"));
		String sql = "delete  from work.partplan where product_id = '"+product_id+"' and issue_num = '"+issue_num+
		"' and quality_id = '"+quality_id+"' and item_id = '"+part_id+"' and father_item_id='"+father_id+"'";
		sql_data sqlbean = new sql_data();
		sqlbean.executeQuery(sql);
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
		String sql1 = "insert into work.part_temp" + sqlField + " values" + sqlValue;
		
	    
		System.out.println("partplanbean.java的sql语句是："+sql);
		System.out.println("partplanbean.java的sql1语句是："+sql1);
		
		//System.out.println(sql);
		sqlbean.executeUpdate(sql);
		sqlbean.executeUpdate(sql1);
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
			sqlbean.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
		    	 sqlbean.closeConn(); 
		    }
		}
	
}
