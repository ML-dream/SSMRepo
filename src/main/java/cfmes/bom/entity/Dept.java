package cfmes.bom.entity;

public class Dept {
   private String dept_id;
   private String dept_name;
   private String father_dept_id;
public String getDept_id() {
	return dept_id;
}
public void setDept_id(String dept_id) {
	this.dept_id = dept_id;
}
public String getDept_name() {
	return dept_name;
}
public void setDept_name(String dept_name) {
	this.dept_name = dept_name;
}
public String getFather_dept_id() {
	return father_dept_id;
}
public void setFather_dept_id(String father_dept_id) {
	this.father_dept_id = father_dept_id;
}
   
   
}
