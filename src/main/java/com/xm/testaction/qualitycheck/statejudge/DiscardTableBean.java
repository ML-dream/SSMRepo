package com.xm.testaction.qualitycheck.statejudge;

public class DiscardTableBean {
	private String barcode; 
	private String runnum; 	//废品单单号 
	private int state	;

	private int rejectnum	;
	private String drawingid;	
	private String ORDER_ID	;
	private String PRODUCT_ID;	
	private String ISSUE_NUM;

	private String partsPlanId ;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getRunnum() {
		return runnum;
	}

	public void setRunnum(String runnum) {
		this.runnum = runnum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getRejectnum() {
		return rejectnum;
	}

	public void setRejectnum(int rejectnum) {
		this.rejectnum = rejectnum;
	}

	public String getDrawingid() {
		return drawingid;
	}

	public void setDrawingid(String drawingid) {
		this.drawingid = drawingid;
	}

	public String getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(String oRDERID) {
		ORDER_ID = oRDERID;
	}

	public String getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(String pRODUCTID) {
		PRODUCT_ID = pRODUCTID;
	}

	public String getISSUE_NUM() {
		return ISSUE_NUM;
	}

	public void setISSUE_NUM(String iSSUENUM) {
		ISSUE_NUM = iSSUENUM;
	}

	public String getPartsPlanId() {
		return partsPlanId;
	}

	public void setPartsPlanId(String partsPlanId) {
		this.partsPlanId = partsPlanId;
	}
	
	
}
