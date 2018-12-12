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
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>客户管理</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
	<body style="height: 95%;">
		<div id="tabs1" class="mini-tabs" activeIndex="1" style="width:100%;height:100%;" plain="false"
		    buttons="#tabsButtons" refreshOnClick="true">
		    <div title="新增设备" iconCls="icon-add" showCloseButton="false" url="machineManage/addMachine.jsp"></div>
		    <div title="设备信息"  iconCls="icon-node" showCloseButton="false" url="machineManage/MachineList.jsp"></div>
		    <div title="设备维修记录" iconCls="icon-tools" showCloseButton="false" url="machineManage/MachineRepairList.jsp"></div>
		    <div title="设备租借记录" iconCls="icon-node" showCloseButton="false" url="machineManage/MachineHireList.jsp"></div>
		    <div title="已报废设备" iconCls="icon-node" showCloseButton="false" url="machineManage/MachineDiscardList.jsp"></div>
		</div>
		<script>
			mini.parse();
		</script>
	</body>
</html>
