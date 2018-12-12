package cfmes.bom.dao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;


import cfmes.bom.entity.BomRecord;
import cfmes.bom.entity.Bom;
import cfmes.util.sql_data;

public class MbomDao {
	
	public boolean HasMbom(String product_id,String issue_num){
		boolean has = false;
		sql_data sqlbean = new sql_data();
		   String sql = "select * from work.mbom where product_id='"+product_id+"' and issue_num='"+issue_num+"'";
		   try{
		   ResultSet rs = sqlbean.executeQuery(sql);
		   while(rs.next()){
			   has = true;
		   }rs.close();
		   }catch(Exception ex){
			   System.out.println("mbomdao.clearmBom()����ʱ���?����Ϊ��"+ex);
		   }finally{
				sqlbean.closeConn();
		   }
		return has;
	}
	/**�õ��ڵ����**/
	public String GetFatherId(String product_id,String issue_num,String node_id){
		String name="";
		sql_data sqlbean = new sql_data();
		//System.out.println(product_id.length());
		product_id=product_id.trim();
		//System.out.println(product_id.length());
		issue_num=issue_num.trim();
		node_id=node_id.trim();
//		String sql = "select father_node_id from work.mbom where product_id = '"+product_id+"' and issue_num = '"+issue_num+"' and node_id = '"+node_id+"'";
		String sql = "select father_node_id from work.nmbom where product_id = '"+product_id+"' and issue_num = '"+issue_num+"' and node_id = '"+node_id+"'";
		System.out.println("MbomDao.java中GetFatherId中的sql语句是："+sql);
		
		ResultSet rs = sqlbean.executeQuery(sql);
		 try{
			   while(rs.next()){
				   name = rs.getString("father_node_id");
			   }rs.close();
			   }catch(Exception ex){
				   System.out.println("mbomdao.getfatherid()����ʱ���?����Ϊ��"+ex);
			   }finally{
					sqlbean.closeConn();
			   }
		return name;
	}
	
   public void ClearNMbom(String product_id,String issue_num){
	   sql_data sqlbean = new sql_data();
	   String sql = "delete from work.nmbom where product_id='"+product_id+"' and issue_num='"+issue_num+"'";
	   try{
	   sqlbean.executeQuery(sql);
	   }catch(Exception ex){
		   System.out.println("mbomdao.clearnmBom()����ʱ���?����Ϊ��"+ex);
	   }finally{
			sqlbean.closeConn();
	   }
   }
   public ArrayList Getebomred(String product_id,String issue_num){
	   ArrayList list =new ArrayList();
       String sql="select a.product_id,a.issue_num,a.level_id,a.item_id,a.father_item_id,a.id,a.fid,a.item_num,b.item_name " +
       		"from work.ebom a,work.item b where a.product_id='"+product_id+"' and a.issue_num='"+issue_num+"' and a.item_id=b.item_id";
       sql_data sqlbean = new sql_data();
       ResultSet rs = sqlbean.executeQuery(sql);
       try {
       	while (rs.next()) {
               BomRecord bomrecord = new BomRecord();
               bomrecord.SetProduct_id(rs.getString("product_id"));
               bomrecord.SetIssue_num(rs.getString("issue_num"));
               bomrecord.SetLevel_id(rs.getString("level_id"));
               bomrecord.SetXid(rs.getString("item_id"));
               bomrecord.SetFxid(rs.getString("father_item_id"));
               bomrecord.SetId(rs.getString("id"));
               bomrecord.SetFid(rs.getString("fid"));
               bomrecord.SetNum(rs.getString("item_num"));
               bomrecord.SetName(rs.getString("item_name"));
               bomrecord.SetMemo1("");
               bomrecord.SetMemo2("");
               bomrecord.SetMemo3("");
               bomrecord.SetMemo4("");
               list.add(bomrecord);
           }rs.close();
       }catch (Exception ex) {
       	System.out.println("mbomDao.geteBomred()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
       return list;
   }
   
   public void Insertmbom(BomRecord bmrecd){
	   Vector vect = new Vector();
		vect.add("work.mbom");
		vect.add(addVector("PRODUCT_ID",bmrecd.getproduct_id(),"CHAR"));
		vect.add(addVector("ISSUE_NUM",bmrecd.getissue_num(),"CHAR"));
		vect.add(addVector("NODE_ID",bmrecd.getxid(),"CHAR"));
		vect.add(addVector("FATHER_NODE_ID",bmrecd.getfxid(),"CHAR"));
		vect.add(addVector("LEVEL_ID",bmrecd.getlevel_id(),"CHAR"));
		vect.add(addVector("ID",bmrecd.getid(),"CHAR"));
		vect.add(addVector("FID",bmrecd.getfid(),"CHAR"));
		vect.add(addVector("NODE_NUM",bmrecd.getnum(),"CHAR"));
		vect.add(addVector("NODE_NAME",bmrecd.getname(),"CHAR"));
		vect.add(addVector("MEMO1",bmrecd.getmemo1(),"CHAR"));
		vect.add(addVector("MEMO2",bmrecd.getmemo2(),"CHAR"));
		vect.add(addVector("MEMO3",bmrecd.getmemo3(),"CHAR"));
		vect.add(addVector("MEMO4",bmrecd.getmemo4(),"CHAR"));
		insertRecord(vect);
   
   }
   
   //���뵽�µ�MBOM������
   public void InsertNmbom(BomRecord bmrecd){
	   Vector vect = new Vector();
		vect.add("work.nmbom");
		vect.add(addVector("PRODUCT_ID",bmrecd.getproduct_id(),"CHAR"));
		vect.add(addVector("ISSUE_NUM",bmrecd.getissue_num(),"CHAR"));
		vect.add(addVector("NODE_ID",bmrecd.getxid(),"CHAR"));
		vect.add(addVector("FATHER_NODE_ID",bmrecd.getfxid(),"CHAR"));
		vect.add(addVector("LEVEL_ID",bmrecd.getlevel_id(),"CHAR"));
		vect.add(addVector("ID",bmrecd.getid(),"CHAR"));
		vect.add(addVector("FID",bmrecd.getfid(),"CHAR"));
		vect.add(addVector("NODE_NUM",bmrecd.getnum(),"CHAR"));
		vect.add(addVector("NODE_NAME",bmrecd.getname(),"CHAR"));
		vect.add(addVector("MEMO1",bmrecd.getmemo1(),"CHAR"));
		vect.add(addVector("MEMO2",bmrecd.getmemo2(),"CHAR"));
		vect.add(addVector("MEMO3",bmrecd.getmemo3(),"CHAR"));
		vect.add(addVector("MEMO4",bmrecd.getmemo4(),"CHAR"));
		insertRecord(vect);
   
   }
   
   
   public boolean Isinmbom(BomRecord bmrecd){
	   boolean isin = false;
	   sql_data sqlbean = new sql_data();
	   String sql;
	 /**  String sql = "select * from work.mbom where product_id='"+product_id+"' and issue_num='"+issue_num+"' and level_id='"+level_id+"' and node_id='"+
  		          node_id+"' and father_node_id='"+father_node_id+"' and id='"+id+"' and fid='"+fid+"' and node_num='"+node_num+"'";
	  **/ 
	   Vector vect = new Vector();
		vect.add("work.mbom");
		vect.add(addVector("PRODUCT_ID",bmrecd.getproduct_id(),"CHAR"));
		vect.add(addVector("ISSUE_NUM",bmrecd.getissue_num(),"CHAR"));
		vect.add(addVector("NODE_ID",bmrecd.getxid(),"CHAR"));
		vect.add(addVector("FATHER_NODE_ID",bmrecd.getfxid(),"CHAR"));
		vect.add(addVector("LEVEL_ID",bmrecd.getlevel_id(),"CHAR"));
		vect.add(addVector("ID",bmrecd.getid(),"CHAR"));
		vect.add(addVector("FID",bmrecd.getfid(),"CHAR"));
		vect.add(addVector("NODE_NUM",bmrecd.getnum(),"CHAR"));
		vect.add(addVector("MEMO1",bmrecd.getmemo1(),"CHAR"));
		vect.add(addVector("MEMO2",bmrecd.getmemo2(),"CHAR"));
		vect.add(addVector("MEMO3",bmrecd.getmemo3(),"CHAR"));
		vect.add(addVector("MEMO4",bmrecd.getmemo4(),"CHAR"));
		sql=QueryRecord(vect);
       ResultSet rs = sqlbean.executeQuery(sql);
       try {
           if (rs.next()) {
           	isin=true;
           }rs.close();
       } catch (Exception ex) {
       	System.out.println("mbomdao.isinmbom()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
	   return isin;
   }
   public String Getaofostatus(String product_id,String issue_num,String item_id){
	   String aofo="";
	   sql_data sqlbean = new sql_data();
	   String sql = "";
	   String x="";
	   for(int i=0;i<2;i++){
		   if(i==1){ x="aomain";}else{ x="fo";}
	   sql = "select * from work."+x+" where product_id='"+product_id+"' and issue_num='"+issue_num+"' and item_id='"+item_id+"'";
	   try{
	   ResultSet rs = sqlbean.executeQuery(sql);
	   if(rs.next()){
          if(i==1){aofo="ao";}else{aofo="fo";}
	   }
	   }catch (Exception ex) {
       	System.out.println("mbomdao.getaofostatus()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
       }
	   return aofo;
	   
   }
   
   //�õ�aofo���
   public BomRecord Getaofo(String product_id,String issue_num,String item_id,String aofo,String level_id){
	   ArrayList list =new ArrayList();
	   sql_data sqlbean = new sql_data();
	   String sql;
	   String xtable,xkey;
	   
	   String number;//���������������ѡ��foao�б�Žϴ�Ͱ汾�Žϴ�ļ�¼
	   String ver;
	 
	   BomRecord target_bmrecd = new BomRecord();
	   level_id=String.valueOf(Integer.parseInt(level_id)+1);
	   if(level_id.length()==1){level_id="0"+level_id;}
	   if(aofo.equals("ao")){
        xkey = "ao_no,ao_ver,ao_name";
        xtable = "aomain";
	   }else{
		xkey = "fo_no,fo_ver";
		xtable = "fo";
	   }
	   sql ="select product_id,issue_num,item_id,"+xkey+" from work."+xtable+" where product_id='"
	   +product_id+"' and issue_num='"+issue_num+"' and item_id='"+item_id+"'";
       ResultSet rs = sqlbean.executeQuery(sql);
       try {
       	while (rs.next()) {
       		   BomRecord bomrecord = new BomRecord();
               bomrecord.SetProduct_id(rs.getString("product_id"));
               bomrecord.SetIssue_num(rs.getString("issue_num"));
               
               bomrecord.SetLevel_id(level_id);
               
               bomrecord.SetFxid(rs.getString("item_id"));
               bomrecord.SetId("1");
               bomrecord.SetFid("1");
               bomrecord.SetNum("");
               
               bomrecord.SetMemo3("");
               bomrecord.SetMemo4(rs.getString("item_id"));
               if(aofo.equals("ao")){
               bomrecord.SetXid(rs.getString("ao_no"));
               bomrecord.SetMemo1(rs.getString("ao_no"));
               bomrecord.SetMemo2(rs.getString("ao_ver"));
               bomrecord.SetName(rs.getString("ao_name"));
               
               }else
               {
            	   bomrecord.SetXid(rs.getString("fo_no"));
            	   bomrecord.SetMemo1(rs.getString("fo_no"));
                   bomrecord.SetMemo2(rs.getString("fo_ver"));
                   bomrecord.SetName(rs.getString("fo_no"));}
               list.add(bomrecord);
           }rs.close();
       }catch (Exception ex) {
       	System.out.println("mbomdao.getaofo()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
       //�ж�
       for(int i=0;i<list.size();i++){
    	   BomRecord bomrecord = (BomRecord)list.get(i);
    	   if(i==0){target_bmrecd = (BomRecord)list.get(0);}
    	   if(target_bmrecd.getmemo1().compareTo(bomrecord.getmemo1())<0){
    	   target_bmrecd = bomrecord;
    	   }else if(target_bmrecd.getmemo1().compareTo(bomrecord.getmemo1())==0){
    		   if(target_bmrecd.getmemo2().compareTo(bomrecord.getmemo2())<0){
    			   target_bmrecd = bomrecord;
    		   }
    	   }
       }
       return target_bmrecd;
   }
   /**�õ�fop**/
   public ArrayList GetFop(BomRecord bmrecd){
	   String sql="";
	   ArrayList list = new ArrayList();
	   sql_data sqlbean = new sql_data();
	    Vector vect = new Vector();
	    String level_id = String.valueOf(Integer.parseInt(bmrecd.getlevel_id())+1);
	    if(level_id.length()==1){level_id="0"+level_id;}
		String fxid = bmrecd.getxid();
		vect.add("work.fo_detail");
		vect.add(addVector("PRODUCT_ID",bmrecd.getproduct_id(),"CHAR"));
		vect.add(addVector("ISSUE_NUM",bmrecd.getissue_num(),"CHAR"));
		vect.add(addVector("ITEM_ID",bmrecd.getfxid(),"CHAR"));
		vect.add(addVector("FO_NO",bmrecd.getxid(),"CHAR"));
		vect.add(addVector("FO_VER",bmrecd.getmemo2(),"CHAR"));
		sql=QueryRecord(vect);
		ResultSet rs = sqlbean.executeQuery(sql);
		
		try{
			while(rs.next()){
				   BomRecord bomrecord = new BomRecord();
	               bomrecord.SetProduct_id(rs.getString("product_id"));
	               bomrecord.SetIssue_num(rs.getString("issue_num"));
	               
	               bomrecord.SetLevel_id(level_id);
	               
	               bomrecord.SetFxid(fxid);
	               bomrecord.SetId("1");
	               bomrecord.SetFid("1");
	               bomrecord.SetNum("");
	               bomrecord.SetName(rs.getString("fo_opname"));
	               bomrecord.SetMemo3(rs.getString("fo_operid"));
	               bomrecord.SetMemo4(rs.getString("item_id"));
	               bomrecord.SetXid(rs.getString("fo_operid"));
	               bomrecord.SetMemo1(rs.getString("fo_no"));
	               bomrecord.SetMemo2(rs.getString("fo_ver"));
	               list.add(bomrecord);	
			}rs.close();
		}catch(Exception ex){
			System.out.println("mbomdao.getfop()����ʱ���?����Ϊ��"+ex);}
		finally{	sqlbean.closeConn();}
		return list;
   }
   /**�õ�aomain��fooper�µĵ�����ԭ��**/
   public ArrayList GetCmtyf(BomRecord bmrecd,String aofo){
	   ArrayList list= new ArrayList();
	   sql_data sqlbean = new sql_data();
		String product_id = bmrecd.getproduct_id();
		String issue_num = bmrecd.getissue_num();
		String item_id = bmrecd.getmemo4();
		String x_no = bmrecd.getmemo1();
		String x_ver = bmrecd.getmemo2();
		String x,xkey = "";
		String type="";
		
		String fxid = bmrecd.getxid();
		String level_id = String.valueOf(Integer.parseInt(bmrecd.getlevel_id())+1);
		if(level_id.length()==1){level_id="0"+level_id;}
		if(aofo.equals("ao")){ x="";xkey="";}else{ x=" and a.fo_operid='"+bmrecd.getmemo3()+"'";xkey=",a.fo_operid";}
		for(int i=1;i<6;i++){
			if(i==1){type="cut";}else if(i==2){type="measure";}else if(i==3){type="tool";}
			else if(i==4){type="material";}else{type="accessory";}
        String sql = "select a.product_id,a.issue_num,a.item_id,a."+type+"_id,a."+type+"_num,a."+aofo+"_no,a."+aofo+"_ver,b."+"item_name"+xkey+" from work."+aofo+"_"+type+" a,work.item b where a.product_id='"+product_id
                     +"' and a.issue_num='"+issue_num+"' and a.item_id='"+item_id+"' and a."+aofo
                     +"_no='"+x_no+"' and a."+aofo+"_ver='"+x_ver+"'"+x+" and a."+type+"_id=b.item_id";
        ResultSet rs = sqlbean.executeQuery(sql);
        try{
        	while(rs.next()){
				   BomRecord bomrecord = new BomRecord();
	               bomrecord.SetProduct_id(rs.getString("product_id"));
	               bomrecord.SetIssue_num(rs.getString("issue_num")); 
	               bomrecord.SetLevel_id(level_id);   
	               bomrecord.SetXid(rs.getString(type+"_id"));
	               bomrecord.SetFxid(fxid);
	               bomrecord.SetId("1");
	               bomrecord.SetFid("1");
	               bomrecord.SetNum(rs.getString(type+"_num"));
	               bomrecord.SetName(rs.getString("item_name"));
	               bomrecord.SetMemo4(rs.getString("item_id"));
	               bomrecord.SetMemo1(rs.getString(aofo+"_no"));
	               bomrecord.SetMemo2(rs.getString(aofo+"_ver"));
	               if(aofo.equals("ao")){bomrecord.SetMemo3("");}else{bomrecord.SetMemo3(rs.getString("fo_operid"));}
	               list.add(bomrecord);	
			}rs.close();
        }catch(Exception ex){System.out.println("mbomdao.getcmtyf()����ʱ���?����Ϊ��"+ex);}finally{
        	sqlbean.closeConn();}
		}
		return list;
   } 
   
   /**��ȡ����cmtyf���д��mbom��**/
   public void CmtyfIstMb(ArrayList list){
	   for(int i=0;i<list.size();i++){
	   BomRecord bmrecd = (BomRecord)list.get(i);
	   boolean cmtyf = Isinmbom(bmrecd);
	   if(!cmtyf){Insertmbom(bmrecd);}else{}
   }
   }	   
   /**��ȡ����cmtyf���д��nmbom��**/
   public void CmtyfIstNMb(ArrayList list){
	   for(int i=0;i<list.size();i++){
	   BomRecord bmrecd = (BomRecord)list.get(i);
	   InsertNmbom(bmrecd);
   }
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
	protected void insertRecord(Vector vect)
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
			//System.out.println(i);
		}
	
		sqlField = sqlField + ")";
		sqlValue = sqlValue + ")";
		String sql = "insert into " + (String)vect.get(0) + sqlField + " values" + sqlValue;
	    System.out.println("MbomDao的sql==="+sql);
		sqlbean.executeUpdate(sql);
	    } catch(Exception e){
			//e.printStackTrace();
			System.out.println("mbomdao.insertrecord()����ʱ���?����Ϊ��"+e);
		}finally{
	    	 sqlbean.closeConn(); 
	    }
	}
	public String QueryRecord(Vector vect){
		String sql="";
		String field ="";
		String value ="";
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
			//System.out.println(sql);
		}catch(Exception ex){System.out.println("mbomdao.queryrecord()����ʱ���?����Ϊ��"+ex);}finally{}
		return sql;
	}
}
