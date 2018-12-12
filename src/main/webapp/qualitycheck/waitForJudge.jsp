<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
		<base href="<%=basePath%>">
    <!-- miniui.js -->
      <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		<title>待审理</title>
		
		<style type="text/css">
		</style>
</head>
<body>
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:700px;height:480px;" allowResize="true"
        url="LoadWaitJudge"  idField="id" multiSelect="true" pagesize="20" onselect="" onrowclick="rowclick()">
        <div property="columns">
           	<div type="indexcolumn"></div><!--  <div type="checkcolumn" ></div> -->
            <div field="runnum" width="40" headerAlign="center" allowSort="false">审理单号</div>    
            <div field="fo_no" width="60" headerAlign="center" allowSort="false">工序号</div> 
            <div field="fo_opname" width="60" headerAlign="center" allowSort="false">工序名称</div>   
            
           <div field="checkdate" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">质检日期</div>
            <div field="barcode" width="60" headerAlign="center" allowSort="false">条码号</div>
            <div field="remark" width="60" headerAlign="center" allowSort="false">备注</div>
            <!-- 需配合datagrid的allowCellEdit等属性一起用 -->
        </div>
     </div>
   </div>
</body>
<script type="text/javascript">
	mini.parse();
	var grid= mini.get("datagrid1");
	grid.load();
	//$(document).ready(function (){
			
		//待校正 ,如果是第一次编辑，如果是修改 
		//正常登陆时，如何查询数据？ 
	//})
	function rowclick(){
		var grid= mini.get("datagrid1");
		var row = grid.getSelected();
		
		mini.open({
		    url: "qualitycheck/stateJudge.jsp",
		    title: "审理单", 
		    width: 880, height: 660,
		    onload: function () {
		    	var iframe = this.getIFrameEl();
					var iframedata = { "runnum" : row.runnum, "barcode" : row.barcode, "fo_no" : row.fo_no};
					//待做，这里加载应与质检新建审理单区分开来。 
    				iframe.contentWindow.loadData(iframedata);
		    },
		    ondestroy: function (action){
		   }
		});
	}
</script>
</html>