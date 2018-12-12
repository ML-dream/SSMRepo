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
   
    <title>复制流程卡</title>
</head>
<body>
	<h1>此工序质检信息不允许修改</h1>
	<div id= "userdiv" class="form">
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   			<tr>
	   			<td style="width:70px;"><label for="fo_no$text">工序号</label></td>
	            <td><input id="fo_no"  name="fo_no" class="mini-textbox" width="100%"  readonly="readonly" /></td>
   				
   				<td style="width:70px;"><label for="fo_opname$text">工序名称</label></td>
	            <td><input id="fo_opname"  name="fo_opname" class="mini-textbox" width="100%"  allowInput="readonly" /></td>
   				
   				<td style="width:70px;"><label for="num$text">加工数量</label></td>
	            <td><input id="num"  name="num" class="mini-textbox" width="100%"  allowInput="false" /></td>
   				<!--  
   				<td style="width:70px;"><label for="workername$text">加工者</label></td>
	            <td><input id="workername"  name="workername" class="mini-textbox" width="100%"  allowInput="false" /></td>
	          -->
   			</tr>
   			<tr>
   			<!--  
   				<td><label for="checkdate$text">质检日期</label></td>
	       		<td><input id="checkdate" name ="checkdate" class="mini-datepicker" value="" readonly="readonly"/>  </td>
   			-->	
   				<td><label for="accept_num$text">合格数</label></td>
	            <td><input id="accept_num"  name="accept_num" class="mini-textbox" width="100%"  allowInput="false" /></td>
   				
   				<td><label for="reject_num$text">不合格数</label></td>
	            <td><input id="reject_num"  name="reject_num" class="mini-textbox" width="100%"  allowInput="false" /></td>
   			<!-- 
   				<td><label for="checker$text">检验者</label></td>
	            <td><input id="checker"  name="checker" class="mini-textbox" width="100%"  allowInput="false" /></td>
	        -->
	        </tr>
	        <tr>
	        	<td><label> 操作选项</label></td>
	        	<td><a id="newcard" class="mini-button" iconCls="icon-add" plain="false"  onclick="newcard()">新建流程卡</a></td>
	        	<td><a class="mini-button" iconCls="icon-cancel" plain="false"  onclick="canceledit()">取消</a></td>
	        </tr>
   	</table>
   </div>
</body>
	<script type="text/javascript">
		mini.parse();
		var barcode = "";
	
		function SetData(data){
			var data = mini.clone(data);
			var form = new mini.Form('#userdiv');
			//var t = mini.get("checkdate");
			barcode =data.barcode;
			form.setData(data,true);
		}
		function CloseWindow(action) {            
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        
		function canceledit(){
			 CloseWindow("cancel");
		}
		function newcard(){
			$.ajax({
        		type: "post",
        		url:"NewBatch?fbarcode="+barcode,
        		datatype:"json",
        		cache: false,
        		processData: false,
    			contentType: false,
    			success:function(text){
    				var json = mini.decode(text);
						if(json.result=="success"){
							CloseWindow(json.barcode);
						}
    			},
    			error : function() {
					//alert("加载失败, 错误码：" + jqXHR.responseText);
				}
        	});
		}
		
	</script>
</html>