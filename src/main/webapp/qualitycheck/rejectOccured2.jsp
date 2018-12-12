<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>不合格品处理</title>
</head>
<body>
	<h1>存在未处理不合格品</h1>
	<div id= "rejectdiv" class="form">
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   			<tr>
   				<td style="width:70px;"><label for="barcode$text">条码号</label></td>
	            <td><input id="barcode"  name="barcode" class="mini-textbox" width="100%"  allowInput="false" value= "${barcode}"/></td>
	            
	   			<td style="width:70px;"><label for="fo_no$text">工序号</label></td>
	            <td><input id="fo_no"  name="fo_no" class="mini-textbox" width="100%"  readonly="readonly" value= "${fo_no}"/></td>
   				
   				<td style="width:90px;"><label for="num$text">未处理不合格数</label></td>
	            <td><input id="snum"  name="num" class="mini-textbox" width="100%"  allowInput="false" value = "${bnum}"/></td>
   				
   			</tr>
   	</table>
   	<div style="margin: auto auto auto 260px;">
   		<a id="" class="mini-button" iconCls="" plain="false"  onclick="toFix()">返工</a>
   		<a class="mini-button" iconCls="" plain="false"  onclick="toSubmit()">报废</a>
	    <a class="mini-button" iconCls="icon-cancel" plain="false"  onclick="canceledit()">取消</a></td>
   	</div>
   </div>
</body>
	<script type="text/javascript">
		mini.parse();
		var barcode = mini.get("barcode").getValue();
		var fo_no = mini.get("fo_no").getValue();
		var bnum = mini.get("bnum").getValue();
		var form = new mini.Form("rejectdiv");
        
		function CloseWindow(action) {            
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        
		function canceledit(){
			 CloseWindow("cancel");
		}
		function toFix(){
			mini.open({
			    url: "qualitycheck/stateJudge2.jsp",
			    title: "返修审理单", 
			    width: 1000, height: 700,
			    onload: function () {
			    	var iframe = this.getIFrameEl();
					var iframedata = { barcode: barcode, fo_no: fo_no,bnum :bnum};
					iframe.contentWindow.SetData2(iframedata);
			    },
			    ondestroy: function (action){
			    	checked =0;
			    	grid.load({key:barcode,mark:4});		//保存数据后的重新加载 ,4 对应 default状态
			   }
			});
		}
		function toSubmit(){
			mini.open({
			    url: "qualitycheck/stateJudge.jsp",
			    title: "审理单", 
			    width: 1000, height: 700,
			    onload: function () {
			    	var iframe = this.getIFrameEl();
					var iframedata = { barcode: barcode, fo_no: fo_no,bnum :bnum };
					iframe.contentWindow.SetData2(iframedata);
			    },
			    ondestroy: function (action){
			   }
			});
		}
	</script>
</html>