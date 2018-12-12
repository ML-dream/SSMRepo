package com.xm.testaction.qualitycheck.statejudge;
/**
 * 焊接件报废时，获取子件信息。
 * @author xiem
 *
 */
public class BeanWeldDiscard {

	private String productId; 
	private String orderId;
	private String issueNum;
	private String partsplanid;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getIssueNum() {
		return issueNum;
	}
	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}
	public String getPartsplanid() {
		return partsplanid;
	}
	public void setPartsplanid(String partsplanid) {
		this.partsplanid = partsplanid;
	}
	
}
