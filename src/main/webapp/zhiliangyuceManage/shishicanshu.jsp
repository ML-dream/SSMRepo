<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
    <title>实时加工数据</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
   	
   
   <fieldset style="width: 99%;" align="center">
		<legend>
			<b>加工过程实时参数</b>
		</legend>

    	<table style="text-align: left;border-collapse:collapse;" border=0  width="100%" >
   			<tr>
   				<td><label for="orderId$text">订单</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperOrder.txt" value="1"  required="true" allowInput="false" /></td>
   				<td><label for="orderId$text">工件</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperGongjian.txt" value="1"  required="true" allowInput="false" /></td>
            	<td><label for="orderId$text">图号</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperDrawID.txt" value="1"  required="true" allowInput="false"/></td>
            	<td><label for="orderId$text">工序</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperFo.txt" value="5"  required="true" allowInput="false"/></td>
   				<td><label for="orderId$text">设备</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperMach.txt" value="1"  required="true" allowInput="false" /></td>
            <tr>
   			<tr >
   				<td>
   					<fieldset align="left" style="margin:5px;">
						<legend ><b>1.选择员工</b></legend>
						熟练程度：<input class="mini-textbox" value="4"/><br>
						轮班情况：<input class="mini-textbox" value="3"/><br>
						工作情绪：<input class="mini-textbox" value="2"/>
					</fieldset>
   				</td>
   				<td>
   					<fieldset align="left"  style="margin:5px;">
						<legend><b>2.车间环境</b></legend>
						车间温度(℃)：<input class="mini-textbox" value="18"/><br>
						车间湿度(%)：<input class="mini-textbox" value="47"/><br>
						车间噪声(dB)：<input class="mini-textbox" value="87"/>
					</fieldset>
   				</td>
   				<td rowspan=2  style="text-align:center;vertical-align:top;">
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>3.毛坯参数</b></legend>
						毛坯材料：<input class="mini-textbox" value="40Cr"/><br>
						毛坯成型方式：<input class="mini-textbox" value="铸造"/><br>
						毛坯形状：<input class="mini-textbox" value="圆柱状"/><br>
						毛坯尺寸：<input class="mini-textbox" value="φ25×1345"/><br>
						毛坯热处理：<input class="mini-textbox" value="调质"/>
					</fieldset>
   				</td>
   				<td rowspan=2  style="text-align:center;vertical-align:top;">
   					<fieldset align="left" style="margin:5px;">
						<legend><b>4.刀具参数</b></legend>
						刀具材料：<input class="mini-textbox" value="白刚玉"/><br>
						刀具几何参数：<input class="mini-textbox" value="φ400×50"/><br>
						刀具寿命(min)：<input class="mini-textbox" value=""/><br>
						刀具已切削时长(min)：<input class="mini-textbox" value="9.9"/><br>
						切削温度(℃)：<input class="mini-textbox" value="811"/>
					</fieldset>
   				</td>
   				<td rowspan=2  style="text-align:center;vertical-align:top;">
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>5.其他参数</b></legend>
						切削液稀释比例(%)：<input class="mini-textbox" value="5.2"/><br>
						切削液PH值：<input class="mini-textbox" value="7.4"/><br>
						精度要求(mm)：<input class="mini-textbox" value="0.011"/><br>
						量具参数(mm)：<input class="mini-textbox" value="0.002"/><br>
						上道工序质量：<input class="mini-textbox" value="合格"/><br>
						夹紧力(N)：<input class="mini-textbox" value=""/>
					</fieldset>
   				</td>
   			</tr>
   			<tr>
   				<td>
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>6.机床参数</b></legend>
						主轴转速(r/min)：<input class="mini-textbox" value="3001"/><br>
						机床连续工作时长(min)：<input class="mini-textbox" value="21.0"/><br>
						机床振动：<input class="mini-textbox" value=""/>
					</fieldset>
   				</td>
   				<td>
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>7.切削参数</b></legend>
						进给量(mm)：<input class="mini-textbox" value=""/><br>
						切削深度(mm)：<input class="mini-textbox" value="0.15"/><br>
						进给次数：<input class="mini-textbox" value="6"/>
					</fieldset>
   				</td>
   			</tr>
   	</table>

   </fieldSet>
   
   
   <fieldset style="width: 99%;" align="center">
		<legend>
			<b>质量预测结果</b>
		</legend>

    	<table>
   			<tr>
   				<td><b>K-means算法预测结果</b></td>
   				<td><input id="orderId"  name="orderId" class="mini-textbox" required="true" value="次品"/></td>
    			<td>::概率为<input class="mini-textbox" required="true" value="81.7"/></td>
    			
    			<td style="padding-left:200px;"><b>优化BP算法预测结果</b></td>
   				<td><input id="orderId"  name="orderId" class="mini-textbox" required="true"  value="次品"/></td>
    			<td>::概率为<input class="mini-textbox" required="true"  value="85.9"/></td>
   			</tr>
   			<tr>
   				<td><b>综合预测结果</b></td>
   				<td><input id="orderId"  name="orderId" class="mini-textbox" required="true"  value="次品"/></td>
    			<td>::概率为<input class="mini-textbox" required="true" value="87.5"/></td>
   			</tr>
   		</table>

   </fieldSet> 
  </body>
</html>
