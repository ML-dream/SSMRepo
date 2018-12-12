package JSOM;

import java.io.Serializable;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


//�㲻������Ǹ�ʲô��˼��������
public class Fant2 implements Serializable{
	public String Name;
	public String UID;
	public  int _id;
	public  int _uid;
	public  int _pid;
	public int _level;
	public int _height;
	
	public java.util.List<MachineTimeGT> Tasks=new ArrayList<MachineTimeGT>();
	
	
	@JsonIgnore
	public java.util.List<MachineTimeGT> getTasks() {
		return Tasks;
	}
	@JsonIgnore

	public void setTasks(java.util.List<MachineTimeGT> Tasks) {
		this.Tasks = Tasks;
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
	public int get_id() {
		return _id;
	}
	@JsonIgnore
	public void set_id(int _id) {
		this._id = _id;
	}
	@JsonIgnore
	public int get_uid() {
		return _uid;
	}
	@JsonIgnore
	public void set_uid(int _uid) {
		this._uid = _uid;
	}
	@JsonIgnore
	public int get_pid() {
		return _pid;
	}
	@JsonIgnore
	public void set_pid(int _pid) {
		this._pid = _pid;
	}
	@JsonIgnore
	public int get_level() {
		return _level;
	}
	@JsonIgnore
	public void set_level(int _level) {
		this._level = _level;
	}
	@JsonIgnore
	public int get_height() {
		return _height;
	}
	@JsonIgnore
	public void set_height(int _height) {
		this._height = _height;
	}
	



}
