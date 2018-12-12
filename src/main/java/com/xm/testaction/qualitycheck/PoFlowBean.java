package com.xm.testaction.qualitycheck;

import org.apache.struts.action.ActionForm;

import cfmes.util.strFormat;

public class PoFlowBean extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7713978507133574403L;
	private String barcode;	//把barcode放到bean中，可能会出问题  待矫正
	private String order_id;
	private String companyname;
	private String drawingId;
	private String product_name;
	private String  input_num;
	private String fo_opname;
	private String	rated_time;
	private String  num;		//计划数量
	private String workername;
	private String checkdate;
	private String 		accept_num;
	private String 		reject_num;
	private String  checker;
	private String 		confirm_num;
	private String 	remark;
	private String fo_no;
	private String grade;
	private int status;
	
//	新增 8-22
	private String machineid;
	private String machine;
	private String item;
	private String itemid;
	private double usedtime;
	private String workerid ; 
	public String getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(String orderId) {
		this.order_id = orderId;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getDrawingId() {
		return drawingId;
	}
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String productName) {
		this.product_name = productName;
	}
	public String getInput_num() {
		return input_num;
	}
	public void setInput_num(String inputNum) {
		this.input_num = inputNum;
	}
	public String getFo_opname() {
		return fo_opname;
	}
	public void setFo_opname(String foOpname) {
		this.fo_opname = foOpname;
	}
	public String getRated_time() {
		return rated_time;
	}
	public void setRated_time(String rated_time) {
		this.rated_time = rated_time;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getWorkername() {
		return workername;
	}
	public void setWorkername(String workername) {
		this.workername = workername;
	}
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public String getAccept_num() {
		return accept_num;
	}
	public void setAccept_num(String acceptNum) {
		this.accept_num = acceptNum;
	}
	public String getReject_num() {
		return reject_num;
	}
	public void setReject_num(String rejectNum) {
		this.reject_num = rejectNum;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getConfirm_num() {
		return confirm_num;
	}
	public void setConfirm_num(String confirmNum) {
		this.confirm_num = confirmNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFo_no() {
		return fo_no;
	}

	public void setFo_no(String foNo) {
		this.fo_no = foNo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMachineid() {
		return machineid;
	}

	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public double getUsedtime() {
		return usedtime;
	}

	public void setUsedtime(double usedtime) {
		this.usedtime = usedtime;
	}

	public String getWorkerid() {
		return workerid;
	}

	public void setWorkerid(String workerid) {
		this.workerid = workerid;
	}
	
	
}
