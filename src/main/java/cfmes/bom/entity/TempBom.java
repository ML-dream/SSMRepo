package cfmes.bom.entity;

public class TempBom {
	private String PRODUCTCODE;
	private String PARENTITEM;
	private String CHILDITEM;
	private String CHILDVERSION;
	private String STARTPLANENO;
	private String ENDPLANENO;
	private String PERQUITY;
	private String ITEMNAME;
	private String ROUTE;
	public String getROUTE() {
		return ROUTE;
	}
	public void setROUTE(String route) {
		ROUTE = route;
	}
	public String getCHILDITEM() {
		return CHILDITEM;
	}
	public void setCHILDITEM(String childitem) {
		CHILDITEM = childitem;
	}
	public String getCHILDVERSION() {
		return CHILDVERSION;
	}
	public void setCHILDVERSION(String childversion) {
		CHILDVERSION = childversion;
	}
	public String getENDPLANENO() {
		return ENDPLANENO;
	}
	public void setENDPLANENO(String endplaneno) {
		ENDPLANENO = endplaneno;
	}
	public String getITEMNAME() {
		return ITEMNAME;
	}
	public void setITEMNAME(String itemname) {
		ITEMNAME = itemname;
	}
	public String getPARENTITEM() {
		return PARENTITEM;
	}
	public void setPARENTITEM(String parentitem) {
		PARENTITEM = parentitem;
	}
	public String getPERQUITY() {
		return PERQUITY;
	}
	public void setPERQUITY(String perquity) {
		PERQUITY = perquity;
	}
	public String getPRODUCTCODE() {
		return PRODUCTCODE;
	}
	public void setPRODUCTCODE(String productcode) {
		PRODUCTCODE = productcode;
	}
	public String getSTARTPLANENO() {
		return STARTPLANENO;
	}
	public void setSTARTPLANENO(String startplaneno) {
		STARTPLANENO = startplaneno;
	}
     
	
}
