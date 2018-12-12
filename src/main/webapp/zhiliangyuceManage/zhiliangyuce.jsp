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
    <title>质量预测</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
	<fieldset style="width: 99%;" align="center">
		<legend>
			<b>分析参数选择</b>
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: left;border-collapse:collapse;" border=0  width="100%" >
   			<tr>
   				<td><label for="orderId$text">订单</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperOrder.txt" value="1"  required="true" allowInput="false" /></td>
   				<td><label for="orderId$text">工件</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperGongjian.txt" value="1"  required="true" allowInput="false" /></td>
            	<td><label for="orderId$text">图号</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperDrawID.txt" value="1"  required="true" allowInput="false"/></td>
            	<td><label for="orderId$text">工序</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperFo.txt" value="1"  required="true" allowInput="false"/></td>
   				<td><label for="orderId$text">设备</label><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperMach.txt" value="1"  required="true" allowInput="false" /></td>
            <tr>
   			<tr >
   				<td>
   					<fieldset align="left" style="margin:5px;">
						<legend ><b>1.选择员工</b></legend>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="熟练程度" checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="轮班环境" checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="工作情绪" checked="true"></div>
					</fieldset>
   				</td>
   				<td>
   					<fieldset align="left"  style="margin:5px;">
						<legend><b>2.车间环境</b></legend>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="车间温度"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="车间湿度"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="车间噪声" checked="true"></div>
					</fieldset>
   				</td>
   				<td rowspan=2  style="text-align:center;vertical-align:top;">
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>3.毛坯参数</b></legend>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="毛坯材料"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="毛坯加工方式"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="毛坯形状"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="毛坯大小"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="毛坯热处理"  checked="true"></div>
					</fieldset>
   				</td>
   				<td rowspan=2  style="text-align:center;vertical-align:top;">
   					<fieldset align="left" style="margin:5px;">
						<legend><b>4.刀具参数</b></legend>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="刀具材质"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="刀具几何参数" checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="刀具寿命"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="刀具已切削时长" checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="切削温度" checked="true"></div>
					</fieldset>
   				</td>
   				<td rowspan=2  style="text-align:center;vertical-align:top;">
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>5.其他参数</b></legend>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="切削液稀释比例"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="切削液PH值"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="精度要求"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="量具参数"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="上道工序质量"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="夹紧力"></div>
					</fieldset>
   				</td>
   			</tr>
   			<tr>
   				<td>
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>6.机床参数</b></legend>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="主轴转速"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="机床连续工作时长"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="机床振动"></div>
					</fieldset>
   				</td>
   				<td>
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>7.切削参数</b></legend>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="进给速度"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="背吃刀量"  checked="true"></div><br>
						<div id="ck1" name="product" class="mini-checkbox" readOnly="false" text="切削速度"  checked="true"></div>
					</fieldset>
   				</td>
   			</tr>
   	</table>
   </div>
   </fieldSet>
   
   
   
   
	<fieldset style="width: 99%;" align="center">
		<legend>
			<b>配置数据源</b>
		</legend>
   
		<div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:200px;" plain="false">
    		<div title="工件信息数据源" >
    			<table>
   					<tr>
   						<td>数据表</td><td><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;" 
    							url="data/paperTable.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
    					</td>
   					</tr>
   					<tr>
   						<td  style="padding-right:15px;">工件编号  对应列名</td><td><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperTableCoID.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
    					</td>
   					</tr>
   					<tr>
   						<td>工件名称  对应列名</td><td><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperTableCoName.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
    					</td>
   					</tr>
   					<tr>
   						<td>图号  对应列名</td><td><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperTableDrawID.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
    					</td>
   					</tr>
   					<tr>
   						<td>材料  对应列名</td><td><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperTableMeta.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
    					</td>
   					</tr>
   					<tr>
   						<td>毛坯来源  对应列名</td><td><input id="isBusy"  name="isBusy" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:170px;"
    							url="data/paperTableRes.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>
    					</td>
   					</tr>
   				</table>
    		</div>
    		<div title="工艺信息数据源" >
    		</div>
    		<div title="设备信息数据源" showCloseButton="false">
    		</div>
    		<div title="员工信息数据源" showCloseButton="false" enabled="true">
    		</div>
    		<div title="刀具信息数据源" showCloseButton="false" enabled="true">
    		</div>
    		<div title="工装信息数据源" showCloseButton="false" enabled="true">
    		</div>
    		<div title="量具信息数据源" showCloseButton="false" enabled="true">
    		</div>
		</div>
   </fieldSet> 
   
   	<div class="mini-toolbar">
   		<table>
   			<tr>
   				<td style="width:80%;"></td>
   				<td>
   					<a class="mini-button" iconCls="icon-remove" plain="false" onclick="getForm()"><b>重配参数</b></a>
   				</td>
   				<td style="padding-left:30px;">
   					<a class="mini-button" iconCls="icon-start1" plain="false" onclick="getForm()"><b>启动预测</b></a>
   				</td>
   			<tr>
   		</table>
  		
  	</div>
  </body>
</html>
