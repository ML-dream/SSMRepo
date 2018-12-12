package com.wl.forms;

import org.apache.struts.action.ActionForm;

public class Item extends ActionForm {
	private static final long serialVersionUID = 3049570704237836227L;
	private String orderId;
	private String itemid;
	private String itemtypeid;
	private String itemname;
	private String itemdrawing;
	private String materialmark;
	private String itemattri;
	private String itemspecification;
	private String itemmb;
	private String itemstatus;
	private String unitm;
	private int lotsize;
	private double ordermin;
	private double leadtime;
	private double varleadtime;
	private String abccode;
	private int LLc;
	private int safestock;
	private int stockhigh;
	private int stocklow;
	private double mpsflag;
	private double itemweight;
	private String itemunit;
    private String weightunit;
	private double yeild;
	private String ptype;
	private String purchaseunite;
	private double planunite;
	private double subproduct;
	private double assembleafterall;
	private String costmethod;
	private double daysmultiple;
	private double backflash;
	private double itemprice;
	private String priceunit;
	private String currency;
	private double rmbprice;
	private String extraA;
	private String extraB;
	private String dymtlmark;
	private String memo;
	private String mtlsort;
	private String itemA;
	private String itemB;
	private String itemC;
	private String itemD;
	
	private String createtime;
	private String updatetime;
	private String createperson;
	private String updateperson;
	private String fitemid;
	
	private String itemTypeName;
	
	
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemtypeid() {
		return itemtypeid;
	}
	public void setItemtypeid(String itemtypeid) {
		this.itemtypeid = itemtypeid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemdrawing() {
		return itemdrawing;
	}
	public void setItemdrawing(String itemdrawing) {
		this.itemdrawing = itemdrawing;
	}
	public String getMaterialmark() {
		return materialmark;
	}
	public void setMaterialmark(String materialmark) {
		this.materialmark = materialmark;
	}
	public String getItemattri() {
		return itemattri;
	}
	public void setItemattri(String itemattri) {
		this.itemattri = itemattri;
	}
	public String getItemspecification() {
		return itemspecification;
	}
	public void setItemspecification(String itemspecification) {
		this.itemspecification = itemspecification;
	}
	public String getItemmb() {
		return itemmb;
	}
	public void setItemmb(String itemmb) {
		this.itemmb = itemmb;
	}
	public String getItemstatus() {
		return itemstatus;
	}
	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}
	public String getUnitm() {
		return unitm;
	}
	public void setUnitm(String unitm) {
		this.unitm = unitm;
	}
	

	public String getAbccode() {
		return abccode;
	}
	public void setAbccode(String abccode) {
		this.abccode = abccode;
	}
	public float getLLc() {
		return LLc;
	}

	public String getWeightunit() {
		return weightunit;
	}
	public void setWeightunit(String weightunit) {
		this.weightunit = weightunit;
	}
	
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getCostmethod() {
		return costmethod;
	}
	public void setCostmethod(String costmethod) {
		this.costmethod = costmethod;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public double getYeild() {
		return yeild;
	}
	public void setYeild(double yeild) {
		this.yeild = yeild;
	}
	public double getRmbprice() {
		return rmbprice;
	}
	public void setRmbprice(double rmbprice) {
		this.rmbprice = rmbprice;
	}
	public String getExtraA() {
		return extraA;
	}
	public void setExtraA(String extraA) {
		this.extraA = extraA;
	}
	public String getExtraB() {
		return extraB;
	}
	public void setExtraB(String extraB) {
		this.extraB = extraB;
	}
	public String getDymtlmark() {
		return dymtlmark;
	}
	public void setDymtlmark(String dymtlmark) {
		this.dymtlmark = dymtlmark;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMtlsort() {
		return mtlsort;
	}
	public void setMtlsort(String mtlsort) {
		this.mtlsort = mtlsort;
	}
	public String getItemA() {
		return itemA;
	}
	public void setItemA(String itemA) {
		this.itemA = itemA;
	}
	public String getItemB() {
		return itemB;
	}
	public void setItemB(String itemB) {
		this.itemB = itemB;
	}
	public String getItemC() {
		return itemC;
	}
	public void setItemC(String itemC) {
		this.itemC = itemC;
	}
	public String getItemD() {
		return itemD;
	}
	public void setItemD(String itemD) {
		this.itemD = itemD;
	}

	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getCreateperson() {
		return createperson;
	}
	public void setCreateperson(String createperson) {
		this.createperson = createperson;
	}
	public String getUpdateperson() {
		return updateperson;
	}
	public void setUpdateperson(String updateperson) {
		this.updateperson = updateperson;
	}
	public String getFitemid() {
		return fitemid;
	}
	public void setFitemid(String fitemid) {
		this.fitemid = fitemid;
	}
	public int getLotsize() {
		return lotsize;
	}
	public void setLotsize(int lotsize) {
		this.lotsize = lotsize;
	}
	public double getOrdermin() {
		return ordermin;
	}
	public void setOrdermin(double ordermin) {
		this.ordermin = ordermin;
	}
	public double getLeadtime() {
		return leadtime;
	}
	public void setLeadtime(double leadtime) {
		this.leadtime = leadtime;
	}
	public double getVarleadtime() {
		return varleadtime;
	}
	public void setVarleadtime(double varleadtime) {
		this.varleadtime = varleadtime;
	}
	public int getSafestock() {
		return safestock;
	}
	public void setSafestock(int safestock) {
		this.safestock = safestock;
	}
	public int getStockhigh() {
		return stockhigh;
	}
	public void setStockhigh(int stockhigh) {
		this.stockhigh = stockhigh;
	}
	public int getStocklow() {
		return stocklow;
	}
	public void setStocklow(int stocklow) {
		this.stocklow = stocklow;
	}
	public double getMpsflag() {
		return mpsflag;
	}
	public void setMpsflag(double mpsflag) {
		this.mpsflag = mpsflag;
	}
	public double getItemweight() {
		return itemweight;
	}
	public void setItemweight(double itemweight) {
		this.itemweight = itemweight;
	}
	public String getItemunit() {
		return itemunit;
	}
	public void setItemunit(String itemunit) {
		this.itemunit = itemunit;
	}
	public double getPlanunite() {
		return planunite;
	}
	public void setPlanunite(double planunite) {
		this.planunite = planunite;
	}
	public double getSubproduct() {
		return subproduct;
	}
	public void setSubproduct(double subproduct) {
		this.subproduct = subproduct;
	}
	public double getAssembleafterall() {
		return assembleafterall;
	}
	public void setAssembleafterall(double assembleafterall) {
		this.assembleafterall = assembleafterall;
	}
	public double getDaysmultiple() {
		return daysmultiple;
	}
	public void setDaysmultiple(double daysmultiple) {
		this.daysmultiple = daysmultiple;
	}
	public double getBackflash() {
		return backflash;
	}
	public void setBackflash(double backflash) {
		this.backflash = backflash;
	}
	public double getItemprice() {
		return itemprice;
	}
	public void setItemprice(double itemprice) {
		this.itemprice = itemprice;
	}
	public void setLLc(int lLc) {
		LLc = lLc;
	}
	public String getPurchaseunite() {
		return purchaseunite;
	}
	public void setPurchaseunite(String purchaseunite) {
		this.purchaseunite = purchaseunite;
	}
	public String getPriceunit() {
		return priceunit;
	}
	public void setPriceunit(String priceunit) {
		this.priceunit = priceunit;
	}
	
	
}	                                                                    



