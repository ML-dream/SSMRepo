package com.wl.testaction.warehouse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.StringUtil;
import com.wl.forms.Customer;
import com.wl.forms.Item;
import com.wl.forms.ItemSelect;
import com.wl.tools.Sqlhelper;

public class getItemServlet extends HttpServlet {
	private static final long serialVersionUID = 2078395864301616233L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "item_id";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
//	    String type = request.getParameter("type");
//		System.out.println(type);
	    
//	    String totalCountSql = "select count(1) from item where ITEM_TYPEID=?";
//	    String[] params = {type};
	    String totalCountSql = "select count(*) from item";
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	    String sql= "select  ITEM_ID itemid,ITEM_TYPEID itemtypeid,ITEM_NAME itemname,ITEM_DRAWING itemdrawing," +
	    	"ITEM_SPECIFICATION itemspecification,ITEM_PRICE itemprice,UNIT_M unit from item where rownum>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr+"";
//	    String[] params2 = {type};
	    List<ItemSelect> resultList = new ArrayList<ItemSelect>();
	    ResultSet rs=null;
	    try {
			rs=Sqlhelper.executeQuery(sql, null);
			while(rs.next()){
				ItemSelect itemselect=new ItemSelect();
				itemselect.setItemid(rs.getString(1));
				itemselect.setItemtypeid(rs.getString(2));
				itemselect.setItemname(rs.getString(3));
				itemselect.setItemdrawing(rs.getString(4));
				itemselect.setItemspecificati(rs.getString(5));
				itemselect.setItemprice(rs.getDouble(6));
				itemselect.setUnit(rs.getString(7));
				resultList.add(itemselect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(resultList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	    
	    
//	    System.out.println("sql=="+sql);
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(sql, null);
//			List<Item> resultList = new ArrayList<Item>();
//			try {
//				while (rs.next()) {
//					Item result = new Item();
//					result.setItemid(rs.getString(1));
//					result.setItemtypeid(rs.getString(2));
//					result.setItemname(rs.getString(3));
//					result.setItemdrawing(rs.getString(4));
//					result.setMaterialmark(rs.getString(5));
//					
//					result.setItemattri(rs.getString(6));
//					result.setItemspecification(rs.getString(7));
//					result.setItemstatus(rs.getString(8));
//					result.setUnitm(rs.getString(9));
//					result.setLotsize(rs.getInt(10));
//					
//					result.setOrdermin(rs.getInt(11));
//					result.setLeadtime(rs.getInt(12));
//					result.setVarleadtime(rs.getInt(13));
//					result.setAbccode(rs.getString(14));
//					result.setLLc(rs.getInt(15));
//					
//					result.setSafestock(rs.getInt(16));
//					result.setStockhigh(rs.getInt(17));
//					result.setStocklow(rs.getInt(18));
//					result.setMpsflag(rs.getInt(19));
//					result.setItemweight(rs.getDouble(20));
//					
//					result.setWeightunit(rs.getString(21));
//					result.setYeild(rs.getDouble(22));
//					result.setPtype(rs.getString(23));
//					result.setPurchaseunite(rs.getString(24));
//					result.setPlanunite(rs.getDouble(25));
//					
//					result.setSubproduct(rs.getDouble(26));
//					result.setCostmethod(rs.getString(27));
//					result.setBackflash(rs.getDouble(28));
//					result.setItemprice(rs.getDouble(29));
//					result.setPriceunit(rs.getString(30));
//					
//					result.setCurrency(rs.getString(31));
//					result.setRmbprice(rs.getDouble(32));
//					result.setExtraA(rs.getString(33));
//					result.setExtraB(rs.getString(34));
//					result.setMemo(rs.getString(35));
//					
//					result.setMtlsort(rs.getString(36));
//					result.setItemA(rs.getString(37));
//					result.setItemB(rs.getString(38));
//					result.setItemC(rs.getString(39));
//					result.setItemD(rs.getString(40));
//					
//					result.setCreatetime(rs.getString(41));
//					result.setUpdatetime(rs.getString(42));
//					result.setCreateperson(rs.getString(43));
//					result.setUpdateperson(rs.getString(44));
//					result.setFitemid(rs.getString(45));
//					
//					result.setItemTypeName(rs.getString(46));
//					
//					resultList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(resultList);
//			
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//			e.printStackTrace();
//		}  finally{
//			try {
//				if(rs!=null){
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













