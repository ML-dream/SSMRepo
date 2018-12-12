
<%@ page language="java" import="java.util.*,com.wl.forms.Employee" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!-- miniui.js -->
      <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		
		<script type="text/javascript" src="<%=path %>/js/echarts.js"></script>
   		 <script type="text/javascript" src="<%=path %>/js/echarts.min.js"></script>
   		 <script type="text/javascript" src="<%=path %>/js/jquery-2.1.4.min.js"></script>
	<style> 
/* .container,.container1,.container2{ float:left}  没有用*/
#container {
     width: 180px;
   
    height: 120px;    

    float:left;     
}
#container1 {
     width: 220px;
  
    height: 120px;    

    float:left;     
}
#container2 {
    width: 180px;
  
    height: 120px;    
      float:left;
    }
</style> 	

		
   
    <title>机床数据采集的实时显示</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
   	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;"> 
  	 <a class="mini-button" iconCls="icon-save" plain="false"  onclick="lookMachineInfo()">查看设备详细记录</a>
  	 <a class="mini-button" iconCls="icon-save" plain="false"  onclick="max()">最大化测试</a>
  	 

  	 </div>
  	 
  	 
  	 
<!--  <div class="navbar navbar-default navbar-fixed-top" role="navigation" id="head">dffd</div> -->


  <!--   <div class="container-fluid">
        <div class="row-fluid example">
            <div id="sidebar-code" class="col-md-4">
                <div class="well sidebar-nav">
                    <div class="nav-header"><a href="#" onclick="autoResize()" class="glyphicon glyphicon-resize-full" id ="icon-resize" ></a>option</div>
                    <textarea id="code" name="code">
 -->
  	 
  	 
  	 
  	 
  <!-- 	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;"> 
	            <a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()">刷新</a>
	            
	            <a class="mini-button" iconCls="icon-remove" plain="false" onclick="getForm('0')">删除</a>     
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('1')">保存</a>
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>  
	                            
 </div> -->
	 <!--   <a class="mini-button" iconCls="icon-reload" plain="false" onclick="onclick()">点击</a>
	    <input id="piece_barcode"  name="piece_barcode" class="mini-textbox" required="true" 
	                	value="" onenter="loadgrid()"   vtype="rangeLength:15,18" style= "width:100%;"/> -->

	        <!-- <div id="grid1" class="mini-datagrid" style="width:100%;height:320px;"
				         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
				         url="dataFeedback" onrowdblclick="rowdblclick">
				        <div property="columns">
				            <div type="indexcolumn"></div>
				            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 				cellStyle="padding:0;">操作
            				</div>    
				            <div field="id" width="15%" headerAlign="center">操作系统</div>
				            <div field="sendTime" width="20%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">采集时间</div>
				            <div field="xaxisfeedspeed" width="15%" headerAlign="center">机床运行模式</div>机床运行模式
				            <div field="xaxiscoordinates" width="15%" headerAlign="center">X轴位置</div>
				            <div field="grade" width="20%" headerAlign="center">运行程序段名称</div>
				            
				            <div field="isReaded" width="15%" headerAlign="center" renderer="onGenderRenderer">X轴进给速度</div>
				            
				        </div>
				    </div> -->
				    
				<!--     <div id="editform" class="form" > -->
       <!--  <input class="mini-hidden" name="id"/>  -->
        <!-- <table style="">
            <tr>
            	<td style="width:80px;">机床       系统</td>
                <td style="width:60px;"><input  id="parm1" name="parm1" class="mini-textbox" allowInput="false" /></td>
                <td style="width:80px;">工序名称 </td>
                <td style="width:80px;"><input  name="fo_opname" class="mini-textbox" allowInput="false"/></td>
                <td style="width:80px;">工时定额 </td>
                <td style="width:80px;"><input id= "ratedtime" name="ratedtime" class="mini-textbox" allowInput="false" style="width:75px;"/>
                	<input name="num" class="mini-textbox" allowInput="false" style="visibility:hidden; width:1px;"  /></td>
            </tr>
            
            <tr>
               <td>X轴进给速度 </td>
                <td><input  id="parm2" name="parm2" class="mini-textbox" required="true" vtype="int"/></td>
                <td>合格数(*) </td>
                <td><input name="accept_num" class="mini-textbox" required="true" vtype="int"/></td>
                <td>不合格数 (*)</td>
                <td><input name="reject_num" class="mini-textbox"  value="0" required="true" vtype="int"/></td>
            </tr>
           
            <tr>
            	<td>X轴          位置</td>
            	<td><input style="" id="parm3" name="parm3" class="mini-textbox" vtype="float"/></td>
            	<td>备注 </td>
            	<td><input style="" name="remark" class="mini-textbox" /></td>
            	
            	<td style="visibility:hidden;">条形码号</td>
            	<td style="visibility:hidden;"><input id="Barcode" name="barcode" class="mini-textbox" /></td>
            </tr>
            <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                	
                    <a class="Update_Button" href="javascript:updateRow()">确定</a> 
                    <a class="Cancel_Button" href="javascript:cancelRow()">取消</a>
                </td>                
            </tr>
             <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                	 <a class="Update_Button" href="javascript:addEmp()">补充加工者信息</a> 
                </td>                
            </tr>
        </table> -->
 <!--    </div> -->
 
 
 
<!--  <div id="add" style="background:#EFEFEF" >
		<form name="form1" id="form1" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#EFEFEF">
			<tr>
								<th>当前程序名称</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">当前运行程序号</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">数控系统已运行时间</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>数控系统已运行时间</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">电机温度</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">各轴实际进给速率</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>各轴实际进给倍率</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">当前刀具号</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">当前运行时间</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false"  width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>当前是否有待处理的报警</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false"  vtype="float"/></td>
			    
			        <th width="12%">当前某通道的报警数</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">NC程序状态</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>当前运行程序状态</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false"  vtype="float"/></td>
			    
			        <th width="12%">NC程序结束计数器</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">已加工的工件总数</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox"  allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>某个报警的消除方式</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">刀具坐标</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">各轴的名称</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>某个报警的消除方式</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">轴的加速度</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">轴的负载</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>某轴的功率</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">轴的类型</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">数控系统类型</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>轴的状态</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">转速的实际值</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">进给速率的单位</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>驱动实际电流值</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴恒定的切削速率</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">当前主轴的运行模式</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>主轴最大转速</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴最小转速</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">主轴当前状态/th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>主轴的实际转速</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴的实际转速</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">主轴的实际转速</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>主轴恒定的切削速度</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴转速期望值</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">主轴驱动负载</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>主轴名字</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴模式</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">当前主轴限速</th>
			    	<td><input id="operAidTime"  name="operAidTime" class= "mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			     <tr>
			    				<th>各轴功率</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">PLC控制轴的状态 </th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">主轴倍率</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			     <tr>
			    				<th>NCU连接是否被激活 </th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">Nck的工作模式</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">报警数量</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			</table>
		</form>
		</div> -->
    
  <!--  <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>  -->

	<fieldset style="width: 100%;" align="center">
		<legend>
			实时信息显示
		</legend>


   <div id="grid1" class="cals" style="padding:2px;border-top:50;border-left:10;position:relative;border-right:0;width:100%;height:150px;">
   		 <div id="container" style="height:150%;margin:0px auto;margin-top:0px;margin-left:220px;"></div>
   		  <div id="container1" style="height:150%;"></div>
   		   <div id="container2" style="height:150%;"></div>
   		   
   	<fieldset style="width: 100%;" align="center">
		<legend>
			机床基本信息
		</legend>
  <div id="add1" style="background:#EFEFEF" >
		<form name="form1" id="form1" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#EFEFEF">
			<tr>
								<th>机床名称</th>
			    	<td><input id="machineName"  name="machineName" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">机床IP</th>
			    	<td><input id="machineIp"  name="machineIp" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">报警编号</th>
			    	<td><input id="textIndex"  name="textIndex" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>数控系统</th>
			    	<td><input id="nckType"  name="nckType" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">累计开机时间</th>
			    	<td><input id="poweronTime"  name="poweronTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">最大轴数</th>
			    	<td><input id="numMachAxes"  name="numMachAxes" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
   
			</table>
		</form>
		</div>
		
		<fieldset style="width: 100%;" align="center">
		<legend>
			机床加工信息
		</legend>
  <div id="add2" style="background:transparent" >
		<form name="form2" id="form2" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#FFFFFF">
			<tr>
					<th>工件数</th>
			    	<td><input id="totalParts"  name="totalParts" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">指定主轴转速</th>
			    	<td><input id="cmdSpeed"  name="cmdSpeed" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">报警信息</th>
			    	<td><input id="fillText"  name="fillText" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>程序状态</th>
			    	<td><input id="progStatus"  name="progStatus" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">程序段序号</th>
			    	<td><input id="blockNoStr"  name="blockNoStr" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">指定进给倍率</th>
			    	<td><input id="cmdSpeedRel"  name="cmdSpeedRel" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
   
			</table>
		</form>
		</div>
		
		
		<fieldset style="width: 100%;" align="center">
		<legend>
			参数监控
		</legend>
  <div id="add3" style="background:#EFEFEF" >
		<form name="form3" id="form3" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#EFEFEF">
			<tr>
					<th>主轴数量</th>
			    	<td><input id="numSpindles"  name="numSpindles" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">主轴运行方式</th>
			    	<td><input id="opMode"  name="opMode" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">有效通道数量</th>
			    	<td><input id="numChannels"  name="numChannels" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>常规报警数</th>
			    	<td><input id="numAlarms"  name="numAlarms" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">刀具啮合时间</th>
			    	<td><input id="cuttingTime"  name="cuttingTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">主轴负载率</th>
			    	<td><input id="driveLoad"  name="driveLoad" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
   
			</table>
		</form>
		</div>
      
      
      
      
 <script type="text/javascript">
 
		var dom = document.getElementById("container");
		var dom1 = document.getElementById("container1");
		var dom2 = document.getElementById("container2");
		var myChart = echarts.init(dom);
		var myChart1 = echarts.init(dom1);
		var myChart2 = echarts.init(dom2);
		//var testZhi = 0 ;
		var speedOvr = 0;
        var actSpeed = 0;
        var actSpeedRel=0;
		
		var app = {};
		option = null;
		option = {
			    tooltip : {//这个是用来编辑鼠标悬停上然后显示相关内容的
			        formatter: "{a} <br/>{b} : {c}%",
			       	textStyle: {//凡是tyextStyle就是用来调整文字格式 的
			                fontSize: 1
			              
			            }
			    },
			    toolbox: {//用来编辑工具箱的
			        feature: {
			        restore: {},
			        saveAsImage: {}
			        }
			    },
			    grid: {//如果有坐标用来画网格，同时调整图像的位置，此处仪表盘没用
			    	x:10,
			    	y:10
			    	},
			    series: [//很重要，对于仪表盘来说可以调整几乎所有的数据相关，细节部分的显示
			        {
			            name: '',//图标的名字，在悬停时进行显示
			            type: 'gauge',//调用不同的图标函数
			            radius: '90%',//调整表的大小
			            detail: {formatter:'{value}%'},//detail，用处很大，控制实时显示的数据格式，大小等等重要！！！
			            data: [{value: 100, name: '完成率0'}],//这个用来显示实时现实的数值内容！并且name后面显示单位，但是
			            //可以用来显示其他的任意的，比如显示图标的名字
			            
			            min:0,//显示仪表盘的显示范围！
			            max:500,
			            splitNumber:5,//显示将上面的这个范围划分为多大的小范围，就是刻度
			            axisLine: {            // 坐标轴线，用来控制仪表盘外边的盘的边的！粗细！
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    width: 5
			                }
			            },
			            axisTick: {            // 坐标轴小标记，控制每个大刻度的伸出长度！！！！
			                length :15,        // 属性length控制线长
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: 'auto'
			                }
			            },
			            axisLabel: {            // 坐标轴小标记，控制小刻度的伸出长度！！！！
			                textStyle: {       // 属性lineStyle控制线条样式
			                	fontSize: 5
			                }
			            },
			            detail:{//这个就是之前那个detail，可以调整的东西很多，在仪表盘中我只用来调增显示的数值大小
			            	textStyle: {  
			            	
			                   	 fontSize: 5
			                			}
			            	
			            },
			            splitLine: {           // 分隔线的！！粗细！！！
			                length :20,         // 属性length控制线长
			                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			                    color: 'auto'
			                }
			            },
			            title : {
			            	 show : true,
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontWeight: 'bolder',
			                    fontSize: 5,
			                    fontStyle: 'italic'
			                			}
			            		}
			            
			            
			            
			        }
			    ]
			};
		option1 = {
			    tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    toolbox: {
			        feature: {
			        restore: {},
			        saveAsImage: {}
			        }
			    },
			    grid: {
			    	x:10,
			    	y:10
			    	},
			    series: [
			        {
			            name: '业务指标',
			            type: 'gauge',
			            radius: '90%',
			            detail: {formatter:'{value}%'},
			            data: [{value: 50, name: '完成率1'}],
			            
			            min:0,
			            max:220,
			            splitNumber:11,
			            axisLine: {            // 坐标轴线
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    width: 10
			                }
			            },
			            axisTick: {            // 坐标轴小标记
			                length :15,        // 属性length控制线长
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: 'auto'
			                }
			            },

			            detail:{
			            	textStyle: {  
			            	
			                   	 fontSize: 5
			                			}
			            	
			            },
			            splitLine: {           // 分隔线
			                length :20,         // 属性length控制线长
			                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			                    color: 'auto'
			                }
			            },
			            title : {
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontWeight: 'bolder',
			                    fontSize:5,
			                    fontStyle: 'italic'
			                			}
			            		}
			            
			            
			            
			        }
			    ]
			};
		option2 = {
			    tooltip : {//这个是控制悬浮窗的文字显示形式等等
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    toolbox: {
			        feature: {
			        restore: {},
			        saveAsImage: {}
			        }
			    },
			    grid: {
			    	x:10,
			    	y:10
			    	},
		        
			    series: [
			        {
			            name: '业务指标',
			            type: 'gauge',
			            radius: '90%',
			            detail: {formatter:'{value}%'},
			            data: [{value: 5, name: '完成率2'}],
			            
			            min:0,
			            max:550,
			            splitNumber:5,
			            axisLine: {            // 坐标轴线
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    width: 10
			                }
			            },

			            detail:{
			            	formatter:'{value}%',
			            	textStyle: {  
			                   	 fontSize: 5
			                			}
			            	
			            },
			            axisTick: {            // 坐标轴小标记
			                length :15,        // 属性length控制线长
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: 'auto'
			                }
			            },
			            title : {
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontWeight: 'bolder',
			                    fontSize: 3,
			                    fontStyle: 'italic'
			                			}
			            		},
			            splitLine: {           // 分隔线
			                length :20,         // 属性length控制线长
			                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			                    color: 'auto'
			                }
			            }
			            
			            
			            
			        }
			    ]
			};

			setInterval(function () {
			    option.series[0].data[0].value = speedOvr;//testZhi//(Math.random() * 100).toFixed(2) - 0;
			    option1.series[0].data[0].value = actSpeed;//(Math.random() * 100).toFixed(2) - 0;
			    option2.series[0].data[0].value = actSpeedRel; //(Math.random() * 100).toFixed(2) - 0;
			    myChart.setOption(option, true);
			    myChart1.setOption(option1, true);
			    myChart2.setOption(option2, true);
			},2000);
		
		
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		    myChart1.setOption(option1, true);
		    myChart2.setOption(option2, true);
		}
		       </script>
    
    
    
    
    
    
    
    
    
   <script>
   		mini.parse();
   		/* var grid = mini.get("grid1");
	    grid.load(); */
	    
	   $(function(){
	     update();
         setInterval(update, 300); 
	    })  
	  /*   update(); */
	    /*  setInterval(update, 500);  */
	    function onclick(){
          /*   mini.get("piece_barcode").setValue("niuhfy"); */
            update();
	    }
	    /*  update(); */
   /*       setInterval(update, 3000); */
	     function update(){
	    	 
	    	 var machineId = "<%=request.getParameter("machineId")%>";
	    	 
        	$.ajax({
        		type: "post",
        		url:"dataFeedback?machineId="+machineId,
        		 /* data: { data: json },  */
    			success:function(text){
    				
				   /* alert("更新成功"); */
		           var msg=$.parseJSON(text);
		           mini.get("machineName").setValue("车铣复合加工中心");
		           mini.get("machineIp").setValue(msg.data.createTime);//("192.168.0.1");
		           /* mini.get("numSpindles").setValue(msg.data.aaLoad1);
		           mini.get("numChannels").setValue(msg.data.aaLoad2);
		           mini.get("machineName").setValue(msg.data.aaLoad3); */
		           mini.get("textIndex").setValue(msg.data.textIndex);
		           mini.get("nckType").setValue(msg.data.nckType);
		           mini.get("poweronTime").setValue(msg.data.poweronTime);
		           mini.get("numMachAxes").setValue(msg.data.numMachAxes);
		           mini.get("totalParts").setValue(msg.data.totalParts);
		           mini.get("cmdSpeed").setValue(msg.data.cmdSpeed);
		           mini.get("fillText").setValue(msg.data.fillText);
		           mini.get("progStatus").setValue(msg.data.progStatus);
		           mini.get("blockNoStr").setValue(msg.data.aaMm1);
		           mini.get("cmdSpeedRel").setValue(msg.data.cmdSpeedRel);
		           mini.get("numSpindles").setValue(msg.data.numSpindles);
		           mini.get("opMode").setValue(msg.data.opMode);
		           mini.get("numChannels").setValue(msg.data.numChannels);
		           mini.get("numAlarms").setValue(msg.data.numAlarms);
		           mini.get("cuttingTime").setValue(msg.data.cuttingTime);
		           mini.get("driveLoad").setValue(msg.data.driveLoad);
		           //mini.get("opMode").setValue(msg.data.opMode);
		           
		           //testZhi = msg.data.aaLoad1;
		           speedOvr = msg.data.speedOvr;
		           actSpeed = msg.data.actSpeed;
		           actSpeedRel=msg.data.actSpeedRel;
    			    },
    			error : function() {
    			    /* alert("更新失败"); */
				}
        	});
		}
	    
	    
	     /* ^^^^^^^^^………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………… */
	     

	     
	     /*  …………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………*/
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	
	    
	    function ondEdit(id,isReaded){
	        window.location.href="NoticeDetailServlet?id=" + id+"&isReaded="+isReaded;
		}
   		
   		
   		function refresh(){
   			
			
			window.location.href=window.location.href;
		}
   		
		function lookMachineInfo(){
   			
			 var machineId = "<%=request.getParameter("machineId")%>";
			window.location.href="dataCollection/showData.jsp?machineId="+machineId;
		}
		function max(){
   			
			 var machineId = "<%=request.getParameter("machineId")%>";
			window.open("dataCollection/showData.jsp");/* "mainindex.jsp?machineId="+machineId; */
		}
   			
   		
   		
		
   		function getForm(flag){
			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.isAlive=flag;
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditDeptSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}
   		
   		
   	
   </script>
  </body>
</html>