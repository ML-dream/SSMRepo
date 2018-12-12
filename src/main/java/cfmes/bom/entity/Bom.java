package cfmes.bom.entity;

public class Bom {
	private String product_id;
	private String item_id;
    private String father_item_id;
    private String item_name;
    private String level_id;
    private String id;
    private String fid;
    private String issue_num;
    private String item_num;
    private String is_rl;
    private String route;  //---fy
    private String scrap;   //---fy
	public String getFather_item_id() {
		return father_item_id;
	}
	public void setFather_item_id(String father_item_id) {
		this.father_item_id = father_item_id;
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
	public String getIs_rl() {
		return is_rl;
	}
	public void setIs_rl(String is_rl) {
		this.is_rl = is_rl;
	}
	public String getIssue_num() {
		return issue_num;
	}
	public void setIssue_num(String issue_num) {
		this.issue_num = issue_num;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
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
	public String getItem_num() {
		return item_num;
	}
	public void setItem_num(String item_num) {
		this.item_num = item_num;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getScrap() {
		return scrap;
	}
	public void setScrap(String scrap) {
		this.scrap = scrap;
	}
}
