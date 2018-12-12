package JSOM;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;



public class Task implements Serializable{
/*
 * ����jackson��Ҫ������Ӧ����Ϊprivate
 * */
	
	public String Name;
	public String  UID;
	public String Start;
	public String Finish;/*����Ӧ�ø�ΪDate*/
	
	public double PercentComplete;
	public int Duration;
	public int ParentUID;
	public int _id;
	public int __Index;
	public boolean isBaseline;
	public String isCo;
	public String orderId;
	public String productId;
	public String issueNum;
	public String ETime;
	public String isDiscard;
	
	@JsonIgnore
	public String getIsDiscard() {
		return isDiscard;
	}
	@JsonIgnore
	public void setIsDiscard(String isDiscard) {
		this.isDiscard = isDiscard;
	}
	@JsonIgnore
	public String getETime() {
		return ETime;
	}
	@JsonIgnore
	public void setETime(String ETime) {
		this.ETime = ETime;
	}
	@JsonIgnore
	public String getIssueNum() {
		return issueNum;
	}
	@JsonIgnore
	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}
	@JsonIgnore
	public String getProductId() {
		return productId;
	}
	@JsonIgnore
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@JsonIgnore
	public String getOrderId() {
		return orderId;
	}
	@JsonIgnore
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@JsonIgnore
	public String getIsCo() {
		return isCo;
	}
	@JsonIgnore
	public void setIsCo(String isCo) {
		this.isCo = isCo;
	}
	@JsonIgnore
	public String getUID() {
		return UID;
	}
	@JsonIgnore
	public void setUID(String uid) {
		UID = uid;
	}
	@JsonIgnore
	public String getName() {
		return Name;
	}
	@JsonIgnore
	public void setName(String name) {
		Name = name;
	}
	@JsonIgnore
	public String getStart() {
		return Start;
	}
	@JsonIgnore
	public void setStart(String start) {
		Start = start;
	}
	@JsonIgnore
	public String getFinish() {
		return Finish;
	}
	@JsonIgnore
	public void setFinish(String finish) {
		Finish = finish;
	}
	@JsonIgnore
	public double getPercentComplete() {
		return PercentComplete;
	}
	@JsonIgnore
	public void setPercentComplete(double percentComplete) {
		PercentComplete = percentComplete;
	}
	@JsonIgnore
	public int getDuration() {
		return Duration;
	}
	@JsonIgnore
	public void setDuration(int duration) {
		Duration = duration;
	}
	@JsonIgnore
	public int getParentUID() {
		return ParentUID;
	}
	@JsonIgnore
	public void setParentUID(int parentUID) {
		ParentUID = parentUID;
	}
	@JsonIgnore
	public int get_id() {
		return _id;
	}
	@JsonIgnore
	public void set_id(int _id) {
		this._id = _id;
	}
	@JsonIgnore
	public int get__Index() {
		return __Index;
	}
	@JsonIgnore
	public void set__Index(int index) {
		__Index = index;
	}
	@JsonIgnore
	public boolean isBaseline() {
		return isBaseline;
	}
	@JsonIgnore
	public void setBaseline(boolean isBaseline) {
		this.isBaseline = isBaseline;
	}

}
