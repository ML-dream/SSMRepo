package cfmes.bom.entity;
public class Issue {
	
    private String flight_type;
    private String product_id;
    private String issue_pos;
    private String issue_num;
    private String lot;
    private String b_sortie;
    private String e_sortie;
    
    
	public String getB_sortie() {
		return b_sortie;
	}
	public void setB_sortie(String b_sortie) {
		this.b_sortie = b_sortie;
	}
	public String getE_sortie() {
		return e_sortie;
	}
	public void setE_sortie(String e_sortie) {
		this.e_sortie = e_sortie;
	}
	public String getFlight_type() {
		return flight_type;
	}
	public void setFlight_type(String flight_type) {
		this.flight_type = flight_type;
	}
	public String getIssue_num() {
		return issue_num;
	}
	public void setIssue_num(String issue_num) {
		this.issue_num = issue_num;
	}
	public String getIssue_pos() {
		return issue_pos;
	}
	public void setIssue_pos(String issue_pos) {
		this.issue_pos = issue_pos;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
    
}
