<%@ page language="java" import="java.util.*,com.wl.forms.ProcessPlan" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'processplanList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" href="./css/wllhgcheck.css" type="text/css"></link>
	<script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	<script type="text/javascript" src="./js/wllhgcheck.js"></script>
	<script type="text/javascript" src="./hidden_show.js"></script>
	
	
	<link rel="icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath%>rili/index.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>rili/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>rili/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcore.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcalendar.min.js"></script>
	<script type="text/javascript">
		J(function(){
			
			J('#starttime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#endtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#starttime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#finishtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });	
		});
	</script>
	

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		J.check.rules = [
		    { name: 'plan_id', mid: 'msg3', requir: true, type: 'cusre', regexp: '^[-\\+]?\\d+(\\.\\d+)?$', warn: '只能输入数字' },
		    { name: 'planid', mid: 'msg4', requir: true, type: 'cusre', regexp: '^[-\\+]?\\d+(\\.\\d+)?$', warn: '只能输入数字' },
		    { name: 'num', mid: 'msg5', requir: true, type: 'cusre', regexp: '^[-\\+]?\\d+(\\.\\d+)?$', warn: '只能输入数字' },
		    { name: 'finishnum', mid: 'msg6', requir: true, type: 'cusre', regexp: '^[-\\+]?\\d+(\\.\\d+)?$', warn: '只能输入数字' },
		    { name: 'passnum', mid: 'msg2', requir: true, type: 'cusre', regexp: '^[-\\+]?\\d+(\\.\\d+)?$', warn: '只能输入数字' },
		    { name: 'failurenum', mid: 'msg1', requir: true, type: 'cusre', regexp: '^[-\\+]?\\d+(\\.\\d+)?$', warn: '只能输入数字' }
		];
		
		window.onload = function()
		{
		    J.check.regform('addprocess_form');
		}
	</script>

</head>
  
  <body>
       
	      <thead>工序详情</thead>
	      
	      <% ArrayList<ProcessPlan> al =(ArrayList<ProcessPlan>)request.getAttribute("processPlan");
	         for(int i=0;i<al.size();i++){
	            ProcessPlan processPlan = new ProcessPlan();
                processPlan = (ProcessPlan)al.get(i);
                System.out.println("进入显示界面了已经！！！！！");
	      %>
	      <form id="showprocess_1" action="/work/processplan.do?flag=processplan" method="post">
	      <table align="center" width="1700px;">
	      <tr><td style="background-color: 6EC2FD;width: 88px;">工序ID1</td><td><input type="text" name="processid" value="<%=processPlan.getProcessid() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD;size: 100px;">orderID</td><td><input type="text" name="orderID" value="<%=processPlan.getOrderID() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD;size: 100px;">产品ID</td><td><input type="text" name="productId" value="<%=processPlan.getProductId() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD;size: 100px;">issue_id</td><td><input type="text" name="issueid" value="<%=processPlan.getIssueid() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD;size: 100px;">物料ID</td><td><input type="text" name="itemid" value="<%=processPlan.getItemid() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD;" width="100px">工序计划ID</td><td><input type="text" name="processplanid" value="<%=processPlan.getProcessplanid() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD;size: 100px;">quality_id</td><td><input type="text" name="qualityid" value="<%=processPlan.getQualityid() %>" size="10" /></td>
	      <tr><td style="background-color: 6EC2FD;size: 100px;">bar_code</td><td><input type="text" name="barcode" value="<%=processPlan.getBarcode() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">level_id</td><td><input type="text" name="levelid" value="<%=processPlan.getLevelid() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">父物料ID</td><td><input type="text" name="fatheritemid" value="<%=processPlan.getFatheritemid() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">开工时间</td><td><input type="text" id="starttime" name="starttime" value="<%=processPlan.getStarttime() %>" size="10"  onclick="J.calendar.get({time:true});"/></td>
	          <td style="background-color: 6EC2FD">完工时间</td><td><input type="text" id="endtime"  name="endtime" value="<%=processPlan.getEndtime() %>" size="10"  onclick="J.calendar.get({time:true});"/></td>
	          <td style="background-color: 6EC2FD">工序状态</td><td><input type="text" name="processstate" value="<%=processPlan.getProcessstate() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">是否撤销</td><td><input type="text" name="idcancle" value="<%=processPlan.getIdcancle() %>" size="10" /></td>
	          
	          
	          <td style="background-color: 6EC2FD">数量</td><td><input id="num" type="text" name="num" value="<%=processPlan.getNum() %>" size="10" /></td><td><span id="msg5">只能数字</span></td></tr>
	      <tr><td style="background-color: 6EC2FD">depatch_pro</td><td><input type="text" name="depatchpro" value="<%=processPlan.getDepatchpro() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">is_co</td><td><input type="text" name="isco" value="<%=processPlan.getIsco() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">部件ID</td><td><input type="text" name="deptid" value="<%=processPlan.getDeptid() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">jigstatus</td><td><input type="text" name="jigstatus" value="<%=processPlan.getJigstatus() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">是否挂起</td><td><input type="text" name="isguaqi" value="<%=processPlan.getIsguaqi() %>" size="10" /></td>	
	          <td style="background-color: 6EC2FD">工作中心</td><td><input type="text" name="workcore" value="<%=processPlan.getWorkcore() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">is_receive</td><td><input type="text" name="isreceive" value="<%=processPlan.getIsreceive() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">完成数量</td><td><input id="finishnum" type="text" name="finishnum" value="<%=processPlan.getFinishnum() %>" size="10" /></td><td><span id="msg6">只能数字</span></td></tr>
	      <tr><td style="background-color: 6EC2FD">report_status</td><td><input type="text" name="reportstatus" value="<%=processPlan.getReportstatus() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">rowstatus</td><td><input type="text" name="rowstatus" value="<%=processPlan.getRowstatus() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">standardstatus</td><td><input type="text" name="standardstatus" value="<%=processPlan.getStandardstatus() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">shelflife</td><td><input type="text" name="shelflife" value="<%=processPlan.getShelflife() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">设备</td><td><input type="text" name="processplana" value="<%=processPlan.getProcessplana() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">ao_no_use</td><td><input type="text" name="aonouse" value="<%=processPlan.getAonouse() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">工人</td><td><input type="text" name="processplanb" value="<%=processPlan.getProcessplanb() %>" size="10" /></td>
	          <td style="background-color: 6EC2FD">pass_num</td><td><input id="passnum" type="text" name="passnum" value="<%=processPlan.getPassnum() %>" size="10" /></td><td><span id="msg2">只能数字</span></td></tr>
	      <tr><td style="background-color: 6EC2FD">废品数量</td><td><input type="text" name="failurenum" value="<%=processPlan.getFailurenum() %>" size="10" /></td><td><span id="msg1">只能数字</span></td></tr>
	      </table>
	      </form>
	      <br/>
	      <%
	         }
	      %>

	    <input type="submit" value="修改" style="width: 100px;height: 30px;"/>
	  
	  <br/><br/>
	
	
  <form id="addprocess_form" action="/work/work_processplan.do?flag=addprocess" method="post" accept-charset="utf-8" lang="utf-8">
  
    <table id="addprocess" style="display: none;" align="center">
      <thead>工序详情</thead>
      
        
        <tr style="background-color: 6EC2FD"><td>工序号</td><td>产品号</td><td>开工时间</td><td>完工时间</td></tr>
        <tr><td><input type="text" name="processid"></td>
           <td><input type="text" name="productid"></td>
           <td><input type="text" id="starttime"  name="starttime" onclick="J.calendar.get({time:true});"></td>
           <td><input type="text" id="finishtime"  name="finishtime" onclick="J.calendar.get({time:true});"></td></tr>
        <tr style="background-color: 6EC2FD"><td>设备</td><td>工人</td><td>工序顺序</td></tr>
        <tr><td><input type="text" name="processplan_a"></td>
           <td><input type="text" name="processplan_b"></td>
           <td><input type="text" name="plan_id" id="plan_id"/></td><td><span id="msg3">只能数字</span></td>
        <tr><td><input type="submit" value="添  加" style="width: 100px;height: 30px;" ></td>
            <td><input type="reset" value="重  置" style="width: 100px;height: 30px;" ></td>
            <td><input id="cancle_add_process" type="button" value="取消添加" style="width: 100px;height: 30px;" onclick="cancleaddprocess();" ></td></tr>
    </table> 
    </form> 
    <button type="button" value="添加工序" id="addpro_button" onclick="add_process();" style="width: 100px;height: 30px;">添加工序</button>    
  </body>
</html>
