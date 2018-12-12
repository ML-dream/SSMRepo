package com.xm.testaction.qualitycheck.sum;

public class CostConfirmBean {
	private String customer;	//客户
	private String orderid;		//订单
	private String rectime;		//接收时间
	private String deltime;		//交付时间
		
	private String draid;		//图号
	private String proname;		//产品名称
	private String pronum;		//产品数量
	
	private String stuff;		//材料牌号
	private String dia;		//直径
	private String len;		//长
	private String wid;		//宽
	private String hei;		//高
	private String rsize;	//毛坯尺寸
	private String issup;	//是否来料
	private String stucost;		//单件材料费
		
	private String pack; 	//包装费
	private String trans;		//运输费
	
	private String realc;	//实际单价
	private String remark;	//	
//	private String islailiao;	//是否来料
	private String outasist;	//外协成本
	private String helpkey ;	//辅助字段
	
	private double stuffpri;	//材料单价
	private double density;		//材料密度
	public double getStuffpri() {
		return stuffpri;
	}
	public void setStuffpri(double stuffpri) {
		this.stuffpri = stuffpri;
	}
	public double getDensity() {
		return density;
	}
	public void setDensity(double density) {
		this.density = density;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getRectime() {
		return rectime;
	}
	public void setRectime(String rectime) {
		this.rectime = rectime;
	}
	public String getDeltime() {
		return deltime;
	}
	public void setDeltime(String deltime) {
		this.deltime = deltime;
	}
	public String getDraid() {
		return draid;
	}
	public void setDraid(String draid) {
		this.draid = draid;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getPronum() {
		return pronum;
	}
	public void setPronum(String pronum) {
		this.pronum = pronum;
	}
	public String getStuff() {
		return stuff;
	}
	public void setStuff(String stuff) {
		this.stuff = stuff;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getHei() {
		return hei;
	}
	public void setHei(String hei) {
		this.hei = hei;
	}

	public String getIssup() {
		return issup;
	}
	public void setIssup(String issup) {
		this.issup = issup;
	}
	public String getStucost() {
		return stucost;
	}
	public void setStucost(String stucost) {
		this.stucost = stucost;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getRealc() {
		return realc;
	}
	public void setRealc(String realc) {
		this.realc = realc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOutasist() {
		return outasist;
	}
	public void setOutasist(String outasist) {
		this.outasist = outasist;
	}
	public String getRsize() {
		return rsize;
	}
	public void setRsize(String rsize) {
		this.rsize = rsize;
	}
	public String getHelpkey() {
		return helpkey;
	}
	public void setHelpkey(String helpkey) {
		this.helpkey = helpkey;
	}
	
	
}
