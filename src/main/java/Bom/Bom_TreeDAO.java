package Bom;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;

import cfmes.util.*;
import cfmes.bom.dao.BomBeanDao;
import cfmes.bom.entity.*;

//import cfmes.bom.entity.TempBom;
public class Bom_TreeDAO {
	ArrayList Bom_List2 = new ArrayList();
	String top = "#";
	String father_tree_id = null;
	String tree_id = null;

	public List getBom_List(String product_id, String issue) {// String
		// flight_type,,String
		// lot,String
		// sortie
		// sql_data sqlBean=new sql_data();
		System.out.println("product_id" + product_id + " issue" + issue);
		List Bom_List = new ArrayList();
		BomBeanDao bombeandao = new BomBeanDao();
		Bom bom = new Bom();
		try {
			int p = Integer.parseInt(issue);
			String issue_like = "";
			for (int i = 1; i <= p; i++) { // ���� issue_numֵ
				if (i == p) {
					issue_like = issue_like + '1';
				}
				if (i < p) {
					issue_like = issue_like + '_';
				}
			}
			int n = bombeandao.getMaxLevelId(product_id);// Integer.parseInt(str_MAX_level);

			for (int i = 1; i <= n; i++) {
				ArrayList array = bombeandao.getBomList(product_id, i,
						issue_like);
				if (array.size() != 0 && array != null) {
					for (int j = 0; j < array.size(); j++) {
						bom = (Bom) array.get(j);
						String level = i + "";
						String level2 = (i - 1) + "";
						if (level.length() == 1) {
							level = "0" + level;
						}
						if (level2.length() == 1) {
							level2 = "0" + level2;
						}
						Bom_TreeNode bom_TreeNode = new Bom_TreeNode();

						String item_id = bom.getItem_id();
						String father_item_id = bom.getFather_item_id();
						String id = bom.getId();
						String fid = bom.getFid();

						tree_id = item_id + level + id;
						if (father_item_id.equals(top)) {
							father_tree_id = "top";
							bom_TreeNode.setFather_item_id(top);
						} else {
							father_tree_id = father_item_id + level2 + fid;
							bom_TreeNode.setFather_item_id(father_item_id);
						}
						bom_TreeNode.setFather_tree_id(father_tree_id);
						bom_TreeNode.setTree_id(tree_id);
						bom_TreeNode.setProduct_id(product_id);
						bom_TreeNode.setItem_id(item_id);

						bom_TreeNode.setId(id);
						bom_TreeNode.setFid(fid);
						bom_TreeNode.setLevel_id(level);
						bom_TreeNode.setIssue_num(bom.getIssue_num());
						bom_TreeNode.setItem_name(bom.getItem_name());

						Bom_List.add(bom_TreeNode);

					}
				}
			}
			// --------------------------------------------------------------------------------------------------------------------------
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return Bom_List;
	}

	public List getCapp_List(String flight_type, String product_id, String issue, String issue_num) {
		List Bom_List = new ArrayList();
		BomBeanDao bombeandao = new BomBeanDao();
		Bom bom = new Bom();
		try {
//			int p = Integer.parseInt(issue);
//			String issue_like = "";
//			for (int i = 1; i <= p; i++) { // ���� issue_numֵ
//				if (i == p) {
//					issue_like = issue_like + '1';
//				}
//				if (i < p) {
//					issue_like = issue_like + '_';
//				}
//			}
//			System.out.println("issue_like===="+issue_like);
			int n = 1;
			if (!product_id.equals("1")) {
				n = bombeandao.getMaxLevelId(product_id);// Integer.parseInt(str_MAX_level);�������levelid��Ϊ��֪��ѭ���Ĵ���fy
				System.out.println("bombeandao.getMaxLevelId(product_id)==="+n);
				int num = 0;
				for (int i = 1; i <= n; i++) {
					ArrayList array = bombeandao.getBomList(product_id, i,issue_num);
					if (array.size() != 0 && array != null) {
						num = num + array.size();
						for (int j = 0; j < array.size(); j++) {
							bom = (Bom) array.get(j);
							String level = i + "";
							String level2 = (i - 1) + "";
							if (level.length() == 1) {
								level = "0" + level;
							}
							if (level2.length() == 1) {
								level2 = "0" + level2;
							}
							Bom_TreeNode bom_TreeNode = new Bom_TreeNode();

							String item_id = bom.getItem_id();
							String father_item_id = bom.getFather_item_id();
							String id = bom.getId();
							String fid = bom.getFid();

							String aofo = bombeandao.getAo(flight_type,product_id, issue_num, item_id);
							if (aofo.equals("")) {
								aofo = bombeandao.getFo(flight_type,product_id, issue_num, item_id);
							}
							bom_TreeNode.setAofo(aofo);

							tree_id = item_id + level + id;
							if (father_item_id.equals(top)) {
								father_tree_id = "top";
								bom_TreeNode.setFather_item_id(top);
							} else {
								father_tree_id = father_item_id + level2 + fid;
								bom_TreeNode.setFather_item_id(father_item_id);
							}
							bom_TreeNode.setFather_tree_id(father_tree_id);
							bom_TreeNode.setTree_id(tree_id);
							bom_TreeNode.setProduct_id(product_id);
							bom_TreeNode.setItem_id(item_id);

							bom_TreeNode.setId(id);
							bom_TreeNode.setFid(fid);

							bom_TreeNode.setLevel_id(level);
							bom_TreeNode.setIssue_num(bom.getIssue_num());
							bom_TreeNode.setItem_name(bom.getItem_name());

							Bom_List.add(bom_TreeNode);

						}
					}
				}
				Bom_TreeNode bom_TreeNode = new Bom_TreeNode();
				bom_TreeNode.setNum(num);
				Bom_List.add(bom_TreeNode);
			}
			// --------------------------------------------------------------------------------------------------------------------------
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return Bom_List;
	}

	// ------fy ����AO�õ�
	public ArrayList getAOMain_List(String flight_type, String product_id,
			String issue_num, String item_id) {

		ArrayList Ao_List = new ArrayList();

		String sql = "SELECT * from meteor.aomain " + "where productid='"
				+ product_id + "' and flighttype='" + flight_type
				+ "' and issue_num='" + issue_num + "' and itemid='" + item_id
				+ "'ORDER BY ITEMID";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
		DealString ds = new DealString();
		try {
			while (rs.next()) {

				Ao ao = new Ao();
				ao.setFlight_type(ds.toString(rs.getString("flighttype"))); // toString�ǽ����ԭ�������nullʱ������ת���ɿ��ٲ����µı?�������һ������Ͳ���null�ˡ�
				ao.setProduct_id(ds.toString(rs.getString("productid")));
				ao.setIssue_num(ds.toString(rs.getString("issue_num")));
				ao.setItem_id(ds.toString(rs.getString("itemid")));
				ao.setAo_no(ds.toString(rs.getString("ao_no")));
				ao.setAo_ver(ds.toString(rs.getString("aover")));
				ao.setParentao_no(ds.toString(rs.getString("parentao_no")));
				ao.setAo_time(ds.toString(rs.getString("ao_time")));
				ao.setAoname(ds.toString(rs.getString("aoname")));
				ao.setWorkplacecode(ds.toString(rs.getString("workplacecode")));
				ao.setWorkplacename(ds.toString(rs.getString("workplacename")));
				ao.setPartno(ds.toString(rs.getString("partno")));
				ao.setAo_order(ds.toString(rs.getString("ao_order")));
				Ao_List.add(ao);
			}
			rs.close();
		} catch (Exception ex) {
			System.out.println("Bom_TreeDao.getAOMain_List()����ʱ���?����Ϊ��"
					+ ex);
		} finally {
			sqlbean.closeConn();
		}
		return Ao_List;
	}

	// ------fy ����FO�õ�
	public ArrayList getFOMain_List(String flight_type, String product_id,
			String issue_num, String item_id) {

		ArrayList Fo_List = new ArrayList();

		String sql = "SELECT * from meteor.fo " + "where product_id='"
				+ product_id + "' and flight_type='" + flight_type
				+ "' and issue_num='" + issue_num + "' and item_id='" + item_id
				+ "'ORDER BY ITEM_ID";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
		DealString ds = new DealString();
		try {
			while (rs.next()) {

				Fo fo = new Fo();
				fo.setFlight_type(ds.toString(rs.getString("flight_type")));
				fo.setProduct_id(ds.toString(rs.getString("product_id")));
				fo.setIssue_num(ds.toString(rs.getString("issue_num")));
				fo.setItem_id(ds.toString(rs.getString("item_id")));
				fo.setFo_no(ds.toString(rs.getString("fo_no")));
				fo.setFo_ver(ds.toString(rs.getString("fover")));
				fo.setItem_name(ds.toString(rs.getString("item_name")));
				fo.setPerqty(ds.toString(rs.getString("perqty")));
				fo.setDept_id(ds.toString(rs.getString("dept_id")));
				Fo_List.add(fo);
			}
			rs.close();
		} catch (Exception ex) {
			System.out.println("Bom_TreeDao.getFOMain_List()����ʱ���?����Ϊ��"
					+ ex);
		} finally {
			sqlbean.closeConn();
		}
		return Fo_List;
	}

	// ------fy ����bom�õ�
	public ArrayList getbom_List(String product_id, String issue_100,
			String item_id, String father_item_id, String level_id, String id,
			String fid) {
		// ArrayList Bom_List = new ArrayList();
		BomBeanDao bombeandao = new BomBeanDao();
		Bom bom = new Bom();
		Bom bom2 = new Bom();
		Bom bom3 = new Bom();

		String product_id2 = product_id;
		String issue_1002 = issue_100;
		String item_id2 = item_id;
		String father_item_id2 = father_item_id;
		String level_id2 = level_id;
		String id2 = id;
		String fid2 = fid;

		try {
			int p = Integer.parseInt(level_id);

			int n = bombeandao.getMaxLevelId(product_id2);// Integer.parseInt(str_MAX_level);�������levelid��Ϊ��֪��ѭ���Ĵ���fy
			// int n = q + 1;

			ArrayList array2 = bombeandao.getBomList3(product_id2, issue_1002,
					item_id2, father_item_id2, level_id2, id2, fid2);
			bom2 = (Bom) array2.get(0);
			Bom_List2.add(bom2);

			// �����Ǹ�ʵ���
			// ArrayList array3 =
			// bombeandao.getBomList3(product_id2,issue_1002,"DD-J10-32101-0-YH",item_id2,"03",id2,fid2);
			// bom3 = (Bom)array3.get(0);
			// Bom_List.add(bom3);

			String father_item_id3 = item_id2;
			String fid3 = id2;
			int y = p + 1;
			digui(product_id2, issue_1002, father_item_id3, y, fid3, n);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return Bom_List2;
	}

	// ----fy
	public void digui(String product_id2, String issue_1002,
			String father_item_id3, int i, String fid3, int n) {
		Bom bom = new Bom();
		BomBeanDao bombeandao = new BomBeanDao();

		ArrayList array = bombeandao.getBomList2(product_id2, issue_1002,
				father_item_id3, i, fid3);

		if (array.size() != 0 && array != null) {

			for (int j = 0; j < array.size(); j++) {
				bom = (Bom) array.get(j);
				Bom_List2.add(bom);
				father_item_id3 = bom.getItem_id();
				fid3 = bom.getId();
				i++;
				if (i <= n) {
					digui(product_id2, issue_1002, father_item_id3, i, fid3, n);
					i--;
				}
			}
		}

	}
}
