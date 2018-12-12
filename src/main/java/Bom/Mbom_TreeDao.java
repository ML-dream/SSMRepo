package Bom;

/**����mbomʱ���ã�����ebom��bom_treedao**/
import java.util.ArrayList;
import java.util.List;

import cfmes.bean.ECBean;
import cfmes.bom.dao.EcDao;
import cfmes.bom.dao.MbomBeanDao;
import cfmes.bom.entity.BomRecord;

public class Mbom_TreeDao {
	String top = "#";
	String father_tree_id = null;
	String tree_id = null;

	public List getCapp_List(String product_id, String issue, String issue_num) {
		List Bom_List = new ArrayList();
		MbomBeanDao mbombeandao = new MbomBeanDao();
		BomRecord mbom = new BomRecord();
		ECBean ecbean = new ECBean();
		EcDao ecdao = new EcDao();

		try {
			int p = 1;
			String issue_like = "";
			for (int i = 1; i <= p; i++) { // ���� issue_numֵ
				if (i == p) {
					issue_like = issue_like + '1';
				}
				if (i < p) {
					issue_like = issue_like + '_';
				}
			}
			int n = 1;
			if (!product_id.equals("1")) {
				n = mbombeandao.getMaxLevelId(product_id);// Integer.parseInt(str_MAX_level);�������levelid��Ϊ��֪��ѭ���Ĵ���fy

				int num = 0;
				for (int i = 1; i <= n; i++) {
					ArrayList array = ecbean.GetEcData(product_id, i, issue_num);// �õ�MBOM��ݡ���NMBOM�Ƚϱ�������
					if (array.size() != 0 && array != null) {
						num = num + array.size();
						for (int j = 0; j < array.size(); j++) {
							mbom = (BomRecord) array.get(j);
							String level = i + "";
							String level2 = (i - 1) + "";
							if (level.length() == 1) {
								level = "0" + level;
							}
							if (level2.length() == 1) {
								level2 = "0" + level2;
							}
							Bom_TreeNode bom_TreeNode = new Bom_TreeNode();

							String item_id = mbom.getxid();
							String father_item_id = mbom.getfxid();
							String id = mbom.getid();
							String fid = mbom.getfid();

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
							bom_TreeNode.setIssue_num(mbom.getissue_num());
							bom_TreeNode.setItem_name(mbom.getname());
							bom_TreeNode.SetMemo1(mbom.getmemo1());
							bom_TreeNode.SetMemo2(mbom.getmemo2());
							bom_TreeNode.SetMemo3(mbom.getmemo3());
							bom_TreeNode.SetMemo4(mbom.getmemo4());
							bom_TreeNode.setEc_id(mbom.getEc_id());
							bom_TreeNode.setEc_name(ecdao.GetEcName(mbom.getEc_id()));

							
							
							
							
							
							
							
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
}
