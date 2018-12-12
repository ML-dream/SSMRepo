package Bom;
import java.sql.*;

import cfmes.util.sql_data;
public class Titles {
    
    ResultSet rs=null;
    public String getProduct_name(String product_id){
    	String product_name = "";
    	sql_data sqlbean=new sql_data();
    	try{
    		rs=sqlbean.executeQuery("select item_name from work.item where item_id='"+product_id+"'");
            if(rs.next()){
            	product_name=rs.getString("item_name");
            }rs.close();
//            sqlbean.closeStmt();
// 		    sqlbean.closeConn();
    	}catch(Exception e){System.out.println("����ʱ����3��"+e);}
    	finally{
			sqlbean.closeConn(); 
		}
    	return product_name;
    }
    public String getItem_name(String item_id){
    	String item_name = "";
    	sql_data sqlbean=new sql_data();
    	try{
    		rs=sqlbean.executeQuery("select item_name from work.item where item_id='"+item_id+"'");
            if(rs.next()){
            	item_name=rs.getString("item_name");
            }rs.close();
//            sqlbean.closeStmt();
// 		    sqlbean.closeConn();
    	}catch(Exception e){System.out.println("����ʱ����3��"+e);}
    	finally{
			sqlbean.closeConn(); 
		}
    	return item_name;
    }
    public String getItem_type(String item_id){
    	//System.out.println("titlebean-item_id: "+item_id);
    	String item_type = "";
    	String sql= "select t.item_typedesc from work.item i,work.itemtype t where i.item_id='"+item_id+"' and t.item_typeid=i.item_typeid";
    	//System.out.println("titlebean-sql: "+sql);
    	sql_data sqlbean=new sql_data();
    	try{
    		rs=sqlbean.executeQuery(sql);
            if(rs.next()){
            	item_type=rs.getString("item_typedesc");
            }rs.close();
//            sqlbean.closeStmt();
// 		    sqlbean.closeConn();
    	}catch(Exception e){System.out.println("����ʱ����3��"+e);}
    	finally{
			sqlbean.closeConn(); 
		}
    	return item_type;
    }
}
