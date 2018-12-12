package cfmes.bean;
import java.sql.ResultSet;
import java.util.*;


import cfmes.bom.entity.ItemType;
import cfmes.util.sql_data;

public class DealItem {
   public ArrayList GetItemType(){
     ArrayList list = new ArrayList() ;
	   String sql = "select * from work.itemtype ";
	   sql_data sqlbean =new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
		try{
			while(rs.next()){
				ItemType itemtype = new ItemType();
				itemtype.setItem_typeid(rs.getString("item_typeid"));
				itemtype.setItem_typedesc(rs.getString("item_typedesc"));
               list.add(itemtype);
			}
		}catch(Exception ex){
			System.out.println("MenuBean.GetMenu()����ʱ���?����Ϊ��"+ex);
		}finally{
			sqlbean.closeConn();
		}
	   return list;  
   }
}
