package cfmes.bom.entity;

public class BomRecord {
   private String product_id;
   private String issue_num;
   private String xid;
   private String fxid;
   private String level_id;
   private String id;
   private String fid;
   private String num;
   private String name;
   private String memo1;
   private String memo2;
   private String memo3;
   private String memo4;
   private String ec_id;
   
   public String getEc_id() {
	return ec_id;
}
public void setEc_id(String ecId) {
	ec_id = ecId;
}
public void SetProduct_id(String product_id){
	   this.product_id=product_id;
   }
   public void SetIssue_num(String issue_num){
	   this.issue_num=issue_num;
   }
   public void SetXid(String xid){
	   this.xid=xid;
   }
   public void SetFxid(String fxid){
	   this.fxid=fxid;
   }
   public void SetLevel_id(String level_id){
	   this.level_id=level_id;
   }
   public void SetId(String id){
	   this.id=id;
   }
   public void SetFid(String fid){
	   this.fid=fid;
   }
   public void SetNum(String num){
	   this.num=num;
   }
   public void SetName(String name){
	   this.name=name;
   }
   public void SetMemo1(String memo1){
	   this.memo1=memo1;
   }
   public void SetMemo2(String memo2){
	   this.memo2=memo2;
   }
   public void SetMemo3(String memo3){
	   this.memo3=memo3;
   }
   public void SetMemo4(String memo4){
	   this.memo4=memo4;
   }
   public String getproduct_id() {
		return product_id;
	}
   public String getissue_num() {
		return issue_num;
	}
   public String getxid() {
		return xid;
	}   
   public String getfxid() {
		return fxid;
	}
   public String getlevel_id() {
		return level_id;
	}  
   public String getid() {
		return id;
	}
   public String getfid() {
		return fid;
	}
   public String getname() {
		return name;
	}
   public String getmemo1() {
		return memo1;
	}
   public String getmemo2() {
		return memo2;
	}
   public String getmemo3() {
		return memo3;
	}
   public String getmemo4() {
		return memo4;
	}
   public String getnum() {
		return num;
	}
   
}
