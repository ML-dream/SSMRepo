package cfmes.bean;

import java.util.*;
import java.sql.*;


import Bom.Titles;
//import cfmes.bom.entity.Dept;
import cfmes.util.DealString;
import cfmes.util.sql_data;
import cfmes.bom.dao.FoBeanDao;


public class FoBean {

	DealString ds = new DealString();
	private String flight_type;
    private String product_id;
    private String issue_num;
    private String item_id;
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
	/**ȡ��Ҫ�޸ĵļ�¼��Ϣ*/
//	public Hashtable getOneData(String flight_type,String product_id,String issue_pos){
//		//System.out.println("IssueBean.getOneData: "+ flight_type + "----"+product_id+"-----"+issue_pos);
//		sql_data sqlbean = new sql_data();
//		Hashtable hash = new Hashtable();
//		String sql = "select lot,b_sortie,e_sortie,issue_num from meteor.issue_deploy where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_pos='"+issue_pos+"'";
//		ResultSet rs = sqlbean.executeQuery(sql);
//		Statement stmt = null;
//		try{
//			ResultSetMetaData  rsmd = rs.getMetaData();
//			int cols = rsmd.getColumnCount();
//			while(rs.next()){
//				for(int i=1; i<=cols; i++){
//					String field = ds.toString(rsmd.getColumnName(i));
//					String value = ds.toString(rs.getString(i));
//					hash.put(field,value);
//				}
//			}rs.close();
//		}catch(Exception e){System.out.println("IssueBean.getOneData����ʱ���?"+e);}
//		finally{
//			sqlbean.closeConn();
//		}
//		return hash;
//	}
//	�����Ա��¼
	public void addFo(Hashtable hash){
		Titles titles = new Titles();
		String fo_no = ds.toString((String)hash.get("FO_NO"));
		String fover = ds.toString((String)hash.get("FO_VER"));
		String dept_id = ds.toString((String)hash.get("DEPT_ID"));
		String item_name =titles.getItem_name(item_id);
		Vector vect = new Vector();
		vect.add("work.FO");
		vect.add(addVector("PRODUCT_TYPE",flight_type,"CHAR"));
		vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
		vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
		vect.add(addVector("ITEM_ID",item_id,"CHAR"));
		vect.add(addVector("ITEM_NAME",item_name,"CHAR"));
		vect.add(addVector("FO_NO",fo_no,"CHAR"));
		vect.add(addVector("FO_VER",fover,"CHAR"));
		vect.add(addVector("DEPT_ID",dept_id,"CHAR"));
		insertRecord(vect);
	}
	/**�޸�һ��bom�汾����ֹ�ܴκͰ汾��*/
	public  void modFo(Hashtable hash){
		String fo_no_old = ds.toString((String)hash.get("FO_NO_OLD"));
		String fover_old = ds.toString((String)hash.get("FOVER_OLD"));
		String fo_no = ds.toString((String)hash.get("FO_NO"));
		String fover = ds.toString((String)hash.get("FOVER"));
		String dept_id = ds.toString((String)hash.get("DEPT_ID"));
		    Vector vect = new Vector(); 
			vect.add("meteor.FO");
			vect.add(addVector("FO_NO",fo_no,"CHAR"));
			vect.add(addVector("FOVER",fover,"CHAR"));
			vect.add(addVector("DEPT_ID",dept_id,"CHAR"));
			vect.add("FLIGHT_TYPE = '"+flight_type+"' and PRODUCT_ID ='"+product_id+"' and ISSUE_NUM ='"+issue_num+"' and ITEM_ID='"+item_id
					+"' and FO_NO='"+fo_no_old+"' and FOVER='"+fover_old+"'");
			updateRecord(vect);
			Vector vect1 = new Vector();
			vect1.add("meteor.FO_DETAIL");
			vect1.add(addVector("FO_NO",fo_no,"CHAR"));
			vect1.add(addVector("FOVER",fover,"CHAR"));
			vect1.add("FLIGHT_TYPE = '"+flight_type+"' and PRODUCT_ID ='"+product_id+"' and ISSUE_NUM ='"+issue_num+"' and ITEM_ID='"+item_id
					+"' and FO_NO='"+fo_no_old+"' and FOVER='"+fover_old+"'");
			updateRecord(vect1);
	}
	/**ɾ��һ��bom�汾*/
	public void delFo(String fo_no,String fover){
		FoBeanDao fobeandao = new FoBeanDao();
		String sql = "delete from work.fo where product_type='"+flight_type+"' and product_id='"+product_id
		           +"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"' and fo_no = '"+fo_no+"' and fo_ver = '"+fover+"'";
		fobeandao.update(sql);
		/*String sql2 = "delete from work.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id
                   +"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"' and fo_no = '"+fo_no+"' and fover = '"+fover+"'";
		fobeandao.update(sql2);*/
	}

	/**ȡ���б�fo.jsp������*/
	public Vector getData(String pxzd,boolean isdesc,String srchzd,String srchzdval,boolean isexact){
		
		sql_data sqlbean = new sql_data();
		Vector vect = new Vector();
		String sql = "";
		try{
		sql = "select fo_no,item_name,fo_ver,dept_id from work.fo where product_type='"+flight_type
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
//	ȡ�õ�ǰҳ��
	public Vector getOnePage(String sql, int paramInt1, int paramInt2){//String sql,int page,int records
		sql_data sqlbean=new sql_data();
	    Vector localVector = new Vector();
	    FoBeanDao fobeandao = new FoBeanDao();
	    ResultSet rs;
	    int i = paramInt2;//iΪһҳ�ϵĸ���
	    try
	    {
	      rs= sqlbean.executeQuery(sql);
	      int j = 0;
	      while (rs.next())
	      {
	        ++j;//jΪȡ�õ���ݵĸ���
	      }
	      localVector.add("" + j);
	      int k = j / i;//k��ʾ��Ҫ��ҳ
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
	          if(i2==4){//System.out.println(str3);
	        	  localHashtable.put("DEPT_NAME", fobeandao.getDept_name(str3+""));
	          }
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
			}System.out.print(sql);
			
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

}
