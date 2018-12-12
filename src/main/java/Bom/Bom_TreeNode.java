package Bom;
import java.io.Serializable;

public class Bom_TreeNode  implements Serializable{
	
	private String product_id;
	private String item_id;
    private String father_item_id;
    private String item_name;
    private String level_id;
    private String id;
    private String fid;
    private String issue_num;
    
    private String tree_id;
    private String father_tree_id;
    private String aofo;
    private String memo1;
    private String memo2;
    private String memo3;
    private String memo4;
    private String ec_id;
    private String ec_name;
    public String getEc_id() {
		return ec_id;
	}
	public void setEc_id(String ecId) {
		ec_id = ecId;
	}
	public String getEc_name() {
		return ec_name;
	}
	public void setEc_name(String ecName) {
		ec_name = ecName;
	}
	private int num;
    
    public void SetMemo1(String memo1){
    	this.memo1=memo1;
    }
    public String GetMemo1(){
    	return memo1;
    }
    public void SetMemo2(String memo2){
    	this.memo2=memo2;
    }
    public String GetMemo2(){
    	return memo2;
    }
    public void SetMemo3(String memo3){
    	this.memo1=memo3;
    }
    public String GetMemo3(){
    	return memo3;
    }
    public void SetMemo4(String memo4){
    	this.memo4=memo4;
    }
    public String GetMemo4(){
    	return memo4;
    }
    public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAofo() {
		return aofo;
	}
	public void setAofo(String aofo) {
		this.aofo = aofo;
	}
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFather_item_id() {
		return father_item_id;
	}
	public void setFather_item_id(String father_item_id) {
		this.father_item_id = father_item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getLevel_id() {
		return level_id;
	}
	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getFather_tree_id() {
		return father_tree_id;
	}
	public void setFather_tree_id(String father_tree_id) {
		this.father_tree_id = father_tree_id;
	}
	public String getTree_id() {
		return tree_id;
	}
	public void setTree_id(String tree_id) {
		this.tree_id = tree_id;
	}
	public String getIssue_num() {
		return issue_num;
	}
	public void setIssue_num(String issue_num) {
		this.issue_num = issue_num;
	}

}
