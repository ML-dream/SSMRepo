package com.xm.testaction.qualitycheck;

public class FixGridString {
//  这里放的是返工件查询的 sql 语句
	public static String fixGridString (String barcode,String fbarcode,String gbarcode,int fo_no,int min,int max){
		String sql = "";
		
		sql = "select * from " +
				"(select v.*,rownum rn from " +
				"(select * from (select z.fo_opname,z.fnum,z.workerid workid,'1',z.fo_no a_index,z.checkdate,z.accept_num,z.reject_num,z.checker,z.confirm_num,z.remark, " +
				"fo_no,z.rated_time,z.machineid equip,w.partsplanid,w.codeid,y.staff_name,x.machinename,z.usedtime ,w.partnum,z.workerid,z.machineid,ou.companyname  " +
				"from partsplan w  " +
				"left join po_routing2 z on z.barcode = '"+fbarcode+"'" +
				" left join employee_info y on y.staff_code = z.workerid  " +
				"left join machinfo x on x.machineid = z.machineid  " +
				"left join outassistcom ou on z.workerid = ou.companyid "+
				"where w.codeid = '"+gbarcode+"' and z.fo_no <"+fo_no+"order by fo_no asc ) " +
				"union all " +
				"select * from (select e.fo_opname,c.num,c.workid workid,'1',c.a_index,d.checkdate,d.accept_num,d.reject_num,d.checker,d.confirm_num,d.remark, " +
				"to_number(e.fo_no) fo_no,e.rated_time,e.equipcode,a.partsplanid,a.codeid,g.staff_name,h.machinename,d.usedtime ,a.partnum,d.workerid,d.machineid,i.companyname  " +
				"from partsplan a " +
				"left join fo_detail e on e.product_id =a.productid and e.issue_num = a.issuenum and e.isinuse='1' " +
				"left join assign c on c.planid = a.partsplanid and c.fo_no = e.fo_no " +
				"left join po_routing2 d on d.barcode = '"+barcode+"' and d.fo_no = e.fo_no " +
				"left join employee_info g on g.staff_code = d.workerid " +
				"left join machinfo h on h.machineid = d.machineid " +
				"left join outassistcom i on d.workerid = i.companyid "+
				"where a.codeid = '"+gbarcode+"' order by fo_no asc)f where fo_no >="+fo_no+" ) v" +
						" )" +
				"where "+min+"<rn and rn <="+max ;
		
		return sql ;
	}
}
