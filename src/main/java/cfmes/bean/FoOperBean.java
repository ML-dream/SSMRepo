package cfmes.bean;

import java.text.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.sql.*;


import cfmes.bom.entity.Fo;
import cfmes.util.DealString;
import cfmes.util.sql_data;
import cfmes.bom.dao.FoBeanDao;
import cfmes.bom.dao.BomBeanDao;
import cfmes.bom.dao.BomImportDao;
import cfmes.bom.dao.IssueBeanDao;

public class FoOperBean {
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
	
	//��ӹ������¼
	public void AddFoOperUpdateEc(Hashtable hash){
		String oper_id = ds.toString((String)hash.get("OPER_ID"));
		String oper_name = ds.toString((String)hash.get("OPER_NAME"));
		String oper_content = ds.toString((String)hash.get("OPER_CONTENT"));
		String oper_time = ds.toString((String)hash.get("OPER_TIME"));
		String rated_time = ds.toString((String)hash.get("RATED_TIME"));
		String plan_time = ds.toString((String)hash.get("PLAN_TIME"));
		String oper_aidtime = ds.toString((String)hash.get("OPER_AIDTIME"));
		String insp_time = ds.toString((String)hash.get("INSP_TIME"));
		String is_keyop = ds.toString((String)hash.get("IS_KEYOP"));
		String is_insp = ds.toString((String)hash.get("IS_INSP"));
		String is_arminsp = ds.toString((String)hash.get("IS_ARMINSP"));
		String is_certop = ds.toString((String)hash.get("IS_CERTOP"));
		String is_co = ds.toString((String)hash.get("IS_CO"));
		String wcid = ds.toString((String)hash.get("WCID"));
		String equiptype = ds.toString((String)hash.get("EQUIPTYPE"));
		String equipcode = ds.toString((String)hash.get("EQUIPCODE"));
		FoBeanDao fobeandao =new FoBeanDao();
		if(fobeandao.isinFoOp_Ec(product_id, issue_num, fo_no, fover,oper_id)){
			Vector vect = new Vector();
			vect.add("work.FOOPER_UPDATEEC");
			vect.add(addVector("PRODUCT_TYPE",flight_type,"CHAR"));
			vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
			vect.add(addVector("FO_NO",fo_no,"CHAR"));
			vect.add(addVector("FO_VER",fover,"CHAR"));
			vect.add(addVector("FO_OPERID",oper_id,"CHAR"));
			vect.add(addVector("FO_OPNAME",oper_name,"CHAR"));
			vect.add(addVector("FO_OPCONTENT",oper_content,"CHAR"));
			vect.add(addVector("OPER_TIME",oper_time,"NUM"));
			vect.add(addVector("RATED_TIME",rated_time,"NUM"));
			vect.add(addVector("PLAN_TIME",plan_time,"NUM"));
			vect.add(addVector("OPER_AIDTIME",oper_aidtime,"NUM"));
			vect.add(addVector("INSP_TIME",insp_time,"NUM"));
			vect.add(addVector("IS_KEY",is_keyop,"CHAR"));
			vect.add(addVector("IS_INSP",is_insp,"CHAR"));
			vect.add(addVector("IS_ARMINSP",is_arminsp,"CHAR"));
			vect.add(addVector("IS_CERTOP",is_certop,"CHAR"));
			vect.add(addVector("IS_CO",is_co,"CHAR"));
			vect.add(addVector("WCID",wcid,"CHAR"));
			vect.add(addVector("EQUIPTYPE",equiptype,"CHAR"));
			vect.add(addVector("EQUIPCODE",equipcode,"CHAR"));
			vect.add("product_TYPE = '"+flight_type+"' and PRODUCT_ID ='"+product_id+"' and ISSUE_NUM ='"+issue_num+"' and ITEM_ID='"+item_id
					+"' and FO_NO='"+fo_no+"' and FO_VER='"+fover+"' and FO_OPERID='"+oper_id+"'");
			updateRecord(vect);
		}else{
			Vector vect = new Vector();
			vect.add("work.FOOPER_UPDATEEC");
			vect.add(addVector("PRODUCT_TYPE",flight_type,"CHAR"));
			vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
			vect.add(addVector("FO_NO",fo_no,"CHAR"));
			vect.add(addVector("FO_VER",fover,"CHAR"));
			vect.add(addVector("FO_OPERID",oper_id,"CHAR"));
			vect.add(addVector("FO_OPNAME",oper_name,"CHAR"));
			vect.add(addVector("FO_OPCONTENT",oper_content,"CHAR"));
			vect.add(addVector("OPER_TIME",oper_time,"NUM"));
			vect.add(addVector("RATED_TIME",rated_time,"NUM"));
			vect.add(addVector("PLAN_TIME",plan_time,"NUM"));
			vect.add(addVector("OPER_AIDTIME",oper_aidtime,"NUM"));
			vect.add(addVector("INSP_TIME",insp_time,"NUM"));
			vect.add(addVector("IS_KEY",is_keyop,"CHAR"));
			vect.add(addVector("IS_INSP",is_insp,"CHAR"));
			vect.add(addVector("IS_ARMINSP",is_arminsp,"CHAR"));
			vect.add(addVector("IS_CERTOP",is_certop,"CHAR"));
			vect.add(addVector("IS_CO",is_co,"CHAR"));
			vect.add(addVector("WCID",wcid,"CHAR"));
			vect.add(addVector("EQUIPTYPE",equiptype,"CHAR"));
			vect.add(addVector("EQUIPCODE",equipcode,"CHAR"));
			insertRecord(vect);
		}
		
	}
//	��ӹ����¼
	public void addFoOper(Hashtable hash){		 
		String oper_id = ds.toString((String)hash.get("OPER_ID"));
		String oper_name = ds.toString((String)hash.get("OPER_NAME"));
		String oper_content = ds.toString((String)hash.get("OPER_CONTENT"));
		String oper_time = ds.toString((String)hash.get("OPER_TIME"));
		String rated_time = ds.toString((String)hash.get("RATED_TIME"));
		String plan_time = ds.toString((String)hash.get("PLAN_TIME"));
		String oper_aidtime = ds.toString((String)hash.get("OPER_AIDTIME"));
		String insp_time = ds.toString((String)hash.get("INSP_TIME"));
		String is_keyop = ds.toString((String)hash.get("IS_KEYOP"));
		String is_insp = ds.toString((String)hash.get("IS_INSP"));
		String is_arminsp = ds.toString((String)hash.get("IS_ARMINSP"));
		String is_certop = ds.toString((String)hash.get("IS_CERTOP"));
		String is_co = ds.toString((String)hash.get("IS_CO"));
		String wcid = ds.toString((String)hash.get("WCID"));
		String equiptype = ds.toString((String)hash.get("EQUIPTYPE"));
		String equipcode = ds.toString((String)hash.get("EQUIPCODE"));
		Vector vect = new Vector();
		vect.add("work.FO_DETAIL");
		vect.add(addVector("PRODUCT_TYPE",flight_type,"CHAR"));
		vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
		vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
		vect.add(addVector("ITEM_ID",item_id,"CHAR"));
		vect.add(addVector("FO_NO",fo_no,"CHAR"));
		vect.add(addVector("FO_VER",fover,"CHAR"));
		vect.add(addVector("FO_OPERID",oper_id,"CHAR"));
		vect.add(addVector("FO_OPNAME",oper_name,"CHAR"));
		vect.add(addVector("FO_OPCONTENT",oper_content,"CHAR"));
		vect.add(addVector("OPER_TIME",oper_time,"NUM"));
		vect.add(addVector("RATED_TIME",rated_time,"NUM"));
		vect.add(addVector("PLAN_TIME",plan_time,"NUM"));
		vect.add(addVector("OPER_AIDTIME",oper_aidtime,"NUM"));
		vect.add(addVector("INSP_TIME",insp_time,"NUM"));
		vect.add(addVector("IS_KEY",is_keyop,"CHAR"));
		vect.add(addVector("IS_INSP",is_insp,"CHAR"));
		vect.add(addVector("IS_ARMINSP",is_arminsp,"CHAR"));
		vect.add(addVector("IS_CERTOP",is_certop,"CHAR"));
		vect.add(addVector("IS_CO",is_co,"CHAR"));
		vect.add(addVector("WCID",wcid,"CHAR"));
		vect.add(addVector("EQUIPTYPE",equiptype,"CHAR"));
		vect.add(addVector("EQUIPCODE",equipcode,"CHAR"));
		insertRecord(vect);
	}
	/**�޸�һ������*/
	public  void modFoOper(Hashtable hash){
		
		String oper_id = ds.toString((String)hash.get("OPER_ID"));
		String oper_name = ds.toString((String)hash.get("OPER_NAME"));
		String oper_content = ds.toString((String)hash.get("OPER_CONTENT"));
		String oper_time = ds.toString((String)hash.get("OPER_TIME"));
		String rated_time = ds.toString((String)hash.get("RATED_TIME"));
		String plan_time = ds.toString((String)hash.get("PLAN_TIME"));
		String oper_aidtime = ds.toString((String)hash.get("OPER_AIDTIME"));
		String insp_time = ds.toString((String)hash.get("INSP_TIME"));
		String is_keyop = ds.toString((String)hash.get("IS_KEYOP"));
		String is_insp = ds.toString((String)hash.get("IS_INSP"));
		String is_arminsp = ds.toString((String)hash.get("IS_ARMINSP"));
		String is_certop = ds.toString((String)hash.get("IS_CERTOP"));
		String is_co = ds.toString((String)hash.get("IS_CO"));
		String wcid = ds.toString((String)hash.get("WCID"));
		String equiptype = ds.toString((String)hash.get("EQUIPTYPE"));
		String equipcode = ds.toString((String)hash.get("EQUIPCODE"));
		Vector vect = new Vector();
			vect.add("work.FO_DETAIL");
			vect.add(addVector("PRODUCT_TYPE",flight_type,"CHAR"));
			vect.add(addVector("PRODUCT_ID",product_id,"CHAR"));
			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
			vect.add(addVector("FO_NO",fo_no,"CHAR"));
			vect.add(addVector("FO_VER",fover,"CHAR"));
			vect.add(addVector("FO_OPERID",oper_id,"CHAR"));
			vect.add(addVector("FO_OPNAME",oper_name,"CHAR"));
			vect.add(addVector("FO_OPCONTENT",oper_content,"CHAR"));
			vect.add(addVector("OPER_TIME",oper_time,"NUM"));
			vect.add(addVector("RATED_TIME",rated_time,"NUM"));
			vect.add(addVector("PLAN_TIME",plan_time,"NUM"));
			vect.add(addVector("OPER_AIDTIME",oper_aidtime,"NUM"));
			vect.add(addVector("INSP_TIME",insp_time,"NUM"));
			vect.add(addVector("IS_KEY",is_keyop,"CHAR"));
			vect.add(addVector("IS_INSP",is_insp,"CHAR"));
			vect.add(addVector("IS_ARMINSP",is_arminsp,"CHAR"));
			vect.add(addVector("IS_CERTOP",is_certop,"CHAR"));
			vect.add(addVector("IS_CO",is_co,"CHAR"));
			vect.add(addVector("WCID",wcid,"CHAR"));
			vect.add(addVector("EQUIPTYPE",equiptype,"CHAR"));
			vect.add(addVector("EQUIPCODE",equipcode,"CHAR"));
			vect.add("product_TYPE = '"+flight_type+"' and PRODUCT_ID ='"+product_id+"' and ISSUE_NUM ='"+issue_num+"' and ITEM_ID='"+item_id
					+"' and FO_NO='"+fo_no+"' and FO_VER='"+fover+"' and FO_OPERID='"+oper_id+"'");
			updateRecord(vect);
	    
		
	}
	
	/**��ӵ����о�**/
	public void addFoCMT(String oper_id,String fox_id,String num,String type){
		FoBeanDao fobeandao = new FoBeanDao();
		String sql="";
		if(fox_id.equals("")||fox_id==null){
			
		}else{

			 sql = "insert into work.fo_"+type+"("+type+"_id,"+type+"_num,product_type,product_id,item_id,issue_num,fo_no,fo_ver,fo_operid) values ('"+
			 fox_id+"','"+num+"','"+flight_type+"','"+product_id+"','"+item_id+"','"+issue_num+"','"+fo_no+"','"+fover+"','"+oper_id+"')";
		
		fobeandao.update(sql);
	}
	}
	
	/**ɾ��һ��fo����**/
	public void delFoOper(String oper_id){
		FoBeanDao fobeandao = new FoBeanDao();
		String sql2 = "delete from work.fo_detail where product_type='"+flight_type+"' and product_id='"+product_id
                   +"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"' and fo_no = '"+fo_no+"' and fo_ver='"+fover+"' and fo_operid ='"+oper_id+"'";
		fobeandao.update(sql2);
	}
	
	/**ɾ�����о�ԭ��**/
	public void delCMT(String oper_id,String table,String id){
		FoBeanDao fobeandao = new FoBeanDao();
		String sql = "delete from work.fo_"+table+" where product_type='"+flight_type+"' and product_id='"+product_id
                   +"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"' and fo_no = '"+fo_no+"' and fo_ver='"+fover+"' and fo_operid ='"+oper_id+"' and "+table+"_id='"+id+"'";
		fobeandao.update(sql);
	}
	
//	ɾ�������¼
	public void delFoOper2(String oper_id){
		FoBeanDao fobeandao = new FoBeanDao();
		String sql2 = "delete from meteor.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id
                   +"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"' and fo_no='"+fo_no+"' and fover='"+fover+"' and oper_id in("+oper_id+")";
		fobeandao.update(sql2);
	}
//���ͨѶ¼��¼
	public void clearRecord(){
		FoBeanDao fobeandao = new FoBeanDao();
		String sql2 = "delete from meteor.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id
                   +"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"' and fo_no='"+fo_no+"' and fover='"+fover+"'";	
		fobeandao.update(sql2);
	}
	/**ȡ���б�displayIssue.jsp������*/
	public Vector getData(String pxzd,boolean isdesc,String srchzd,String srchzdval,boolean isexact){
		//pxzd���ĸ��ֶ�����isdesc�Ƿ������У�srchzd���ĸ��ֶ�������isexact�Ƿ�ȷ��ѯ
		sql_data sqlbean = new sql_data();
		Vector vect = new Vector();
		String sql = "";
		try{
			
		sql = "select fo_operid,fo_opname,fo_opcontent,oper_time,rated_time,plan_time,oper_aidtime,insp_time,is_key,is_insp,is_arminsp,"
			  +"is_certop,is_co,wcid,equiptype,equipcode from work.fo_detail where product_type='"+flight_type+"' and product_id='"+product_id
		      +"' and issue_num='"+issue_num+"' and item_id='"+item_id+"' and fo_no='"+fo_no+"' and fo_ver='"+fover+"'";		
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
	          if(i2==14){
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
			}//System.out.print(sql);
			
			System.out.println("FoOperBean的494行的sql语句："+sql);
			
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
