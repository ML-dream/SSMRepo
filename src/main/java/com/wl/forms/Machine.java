/**
 * 项目名称: work
 * 创建日期：2016-5-13
 * 修改历史：
 *		1.[2016-5-13]创建文件 by Flair
 */
package com.wl.forms;

import java.util.Date;

/**
 * @author Flair
 */
public class Machine {
	private String orderId;
	private String foId;
	private String connector;
	private String machineTime;
	private String machineIdMachineTime;
	
	private String machineId;		//设备编号
	private String machineName;		//设备名称
	private String machineSpec;		//设备规格
	private String place;			//放置地点
	private String outCode;			//出厂编号
	private String outDate;			//出厂日期
	private int    machNum;			//设备数量
	private String workRange;		//加工范围
	private String machType;		//设备类别
	private String typeName;		//设备类别名称
	private String machModel;		//设备型号
	private String machStandard;	//设备品牌
	private String machManufacture;	//生产厂商
	private int    usedYears;		//使用年限
	private double machPrice;		//设备价格
	private double machOldRate;		//年折旧率
	private int   isKeyMach;		//是否关键设备
	private String buyDate;			//购买日期
	private String status;			//设备状态
	private double power;			//功率
	private String deptId;			//部门号
	private String deptName;		//部门名称
	private String runDate;			//使用日期
	private String worker;			//操作员工
	private String workerName;		//员工姓名
	private String madeDate;		//制造日期
	private String checkTime;		//检查时间
//	private double checkTime;		
	private String memo;			//附录
	private String beginDate;		//
	private double jf;				//
	private double df;				//
	private String section;			//
	private String mainTypeCode;	//
	private String machKind;		//
	private int    driveNum;		//
	private String wcId;			//
	private String capacity;		//
	private String machGuide;		//
	private String kw;				//
	private String RFID1;			//
	private String RFID2;			//
	private double hourPercent;         //公时百分比
	private double countPercent;        //计件百分比
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getFoId() {
		return foId;
	}
	public void setFoId(String foId) {
		this.foId = foId;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public String getMachineTime() {
		return machineTime;
	}
	public void setMachineTime(String machineTime) {
		this.machineTime = machineTime;
	}
	public String getMachineIdMachineTime() {
		return machineIdMachineTime;
	}
	public void setMachineIdMachineTime(String machineIdMachineTime) {
		this.machineIdMachineTime = machineIdMachineTime;
	}
	public double getHourPercent() {
		return hourPercent;
	}
	public void setHourPercent(double hourPercent) {
		this.hourPercent = hourPercent;
	}
	
	public double getCountPercent() {
		return countPercent;
	}
	public void setCountPercent(double countPercent) {
		this.countPercent = countPercent;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineSpec() {
		return machineSpec;
	}
	public void setMachineSpec(String machineSpec) {
		this.machineSpec = machineSpec;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getOutCode() {
		return outCode;
	}
	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}
	public int getMachNum() {
		return machNum;
	}
	public void setMachNum(int machNum) {
		this.machNum = machNum;
	}
	public String getWorkRange() {
		return workRange;
	}
	public void setWorkRange(String workRange) {
		this.workRange = workRange;
	}
	public String getMachType() {
		return machType;
	}
	public void setMachType(String machType) {
		this.machType = machType;
	}
	public String getMachModel() {
		return machModel;
	}
	public void setMachModel(String machModel) {
		this.machModel = machModel;
	}
	public String getMachStandard() {
		return machStandard;
	}
	public void setMachStandard(String machStandard) {
		this.machStandard = machStandard;
	}
	public String getMachManufacture() {
		return machManufacture;
	}
	public void setMachManufacture(String machManufacture) {
		this.machManufacture = machManufacture;
	}
	public int getUsedYears() {
		return usedYears;
	}
	public void setUsedYears(int usedYears) {
		this.usedYears = usedYears;
	}
	public double getMachPrice() {
		return machPrice;
	}
	public void setMachPrice(double machPrice) {
		this.machPrice = machPrice;
	}
	public double getMachOldRate() {
		return machOldRate;
	}
	public void setMachOldRate(double machOldRate) {
		this.machOldRate = machOldRate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public double getJf() {
		return jf;
	}
	public void setJf(double jf) {
		this.jf = jf;
	}
	public double getDf() {
		return df;
	}
	public void setDf(double df) {
		this.df = df;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getMainTypeCode() {
		return mainTypeCode;
	}
	public void setMainTypeCode(String mainTypeCode) {
		this.mainTypeCode = mainTypeCode;
	}
	public String getMachKind() {
		return machKind;
	}
	public void setMachKind(String machKind) {
		this.machKind = machKind;
	}
	public int getDriveNum() {
		return driveNum;
	}
	public void setDriveNum(int driveNum) {
		this.driveNum = driveNum;
	}
	public String getWcId() {
		return wcId;
	}
	public void setWcId(String wcId) {
		this.wcId = wcId;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getMachGuide() {
		return machGuide;
	}
	public void setMachGuide(String machGuide) {
		this.machGuide = machGuide;
	}
	public String getKw() {
		return kw;
	}
	public void setKw(String kw) {
		this.kw = kw;
	}
	public String getRFID1() {
		return RFID1;
	}
	public void setRFID1(String rFID1) {
		RFID1 = rFID1;
	}
	public String getRFID2() {
		return RFID2;
	}
	public void setRFID2(String rFID2) {
		RFID2 = rFID2;
	}
	public int getIsKeyMach() {
		return isKeyMach;
	}
	public void setIsKeyMach(int isKeyMach) {
		this.isKeyMach = isKeyMach;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public String getRunDate() {
		return runDate;
	}
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	public String getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(String madeDate) {
		this.madeDate = madeDate;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}


	

	
}
