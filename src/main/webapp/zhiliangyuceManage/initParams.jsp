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
    <title>初始化参数</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>

   <fieldset style="width: 70%;" align="center">
		<legend>
			<b>模型初始化参数</b>
		</legend>

    	<table style="text-align: left;border-collapse:collapse;" border=0  width="100%" >
   			<tr >
   				<td>
   					<fieldset align="left" style="margin:5px;">
						<legend ><b>K-means初始参数</b></legend>
						K初值：<input class="mini-textbox" value="3"/>
					</fieldset>
   					<fieldset align="left"  style="margin:5px;">
						<legend><b>BP神经网络初始参数</b></legend>
						输入节点数：<input class="mini-textbox" value="17"/><br>
						隐藏节点数：<input class="mini-textbox" value="20"/><br>
						输出节点数：<input class="mini-textbox" value="3"/>
					</fieldset>
   				</td>
   				<td rowspan=2  style="text-align:center;vertical-align:top;">
   					<fieldset  align="left" style="margin:5px;">
						<legend><b>遗传算法初始参数</b></legend>
						交叉概率：<input class="mini-textbox" value="0.6"/><br>
						编译概率：<input class="mini-textbox" value="0.01"/><br>
						进化代数：<input class="mini-textbox" value="300"/><br><br>
					</fieldset>
					<a class="mini-button" iconCls="icon-node" plain="false" onclick="getForm()"><b>生成权值和阀值</b></a>
   				</td>
   			</tr>
   			<tr>
   				<td colspan="2">
   					<div class="mini-toolbar">
   						<a class="mini-button" iconCls="icon-ok" plain="false" style="float:right;"><b>设置完成</b></a>
   						<a class="mini-button" iconCls="icon-reload" plain="false"><b>重新设置</b></a>
  					</div>
  				</td>
   			</tr>
   	</table>
   </fieldSet>
   
  </body>
</html>
