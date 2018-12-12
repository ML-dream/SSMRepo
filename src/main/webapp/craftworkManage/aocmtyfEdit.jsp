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
    <title>设备报废</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
	<body>
    	<form id="form_cmtyf" name="form_cmtyf" action="" method=post>
    		<table class="green_list_table" align="center" width="100%" height="50%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#dafec5">
    			<tr>
      				<th>装配号</th>
      				<td><input id="aoId" name="aoId" class="mini-textbox"  width="100%" required="true" readonly value="${result.aoId}"/></td>
	         		<th>装配工艺号</th>
      				<td><input id="aoNo" name="aoNo" class="mini-textbox"  width="100%" required="true" readonly value="${result.aoNo}"/></td>
	         	</tr>
   				<tr><th>使用刀具</th>
   					<td><input id="focut" name="focut" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false" value="${result.cut}" text="${result.cutName}"/></td>
   				 	<th>使用刀具数量</th><td><input id="cut_num"  name="cut_num" class="mini-textbox"  width="100%" readonly value="${result.cutNum}"/></td>
				</tr>
				<tr>
					<th>使用量具</th>
					<td><input id="fomeasure" name="fomeasure" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false" value="${result.measure}" text="${result.measureName}"/></td>
   				 	<th>使用量具数量</th><td><input id="measure_num"  name="measure_num" class="mini-textbox"  width="100%" readonly value="${result.measureNum}"/></td></tr>            
    			<tr>
    				<th>使用夹具</th>
    				<td><input id="fotool" name="fotool" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false" value="${result.tool}" text="${result.toolName}"/></td>
   				 	<th>使用夹具数量</th><td><input id="tool_num"  name="tool_num" class="mini-textbox"  width="100%" readonly value="${result.toolNum}"/></td>
	   			</tr>         
				<tr>
					<th>原材料</th>
					<td><input id="fomaterial" name="fomaterial" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false" value="${result.material}" text="${result.materialName}"/></td>
   				 	<th>使用原材料数量</th><td><input id="material_num"  name="material_num" class="mini-textbox"  width="100%" readonly value="${result.materialNum}"/></td>
	     		</tr>        
				<tr><th>辅料</th>
					<td><input id="foaccessory" name="foaccessory" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false" value="${result.accessory}" text="${result.accessoryName}"/></td>
   				 	<th>使用辅料数量</th><td><input id="accessory_num"  name="accessory_num" class="mini-textbox"  width="100%" readonly value="${result.accessoryNum}"/></td>
				</tr>             
				<tr><td><a class="mini-button" iconCls="icon-save" plain="false"  onclick="toservlet()">保存</a>
				</tr>
			</table>
			<br>
		</form>
    </div>
  </body>
	
	
	<script>
		mini.parse();

   		function toservlet(){
   			var form = new mini.Form("#form_cmtyf");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	//data.discardDate = mini.get("discardDate").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'BaseServlet?meth=AddAoCMTYF';
   		        jQuery.post(url, params, function(data){
   	   		  		mini.confirm(data.result, "确定？",
		                 function (action){            
		                     if (action == "ok") {
		                    	window.location.href = window.location.href;	
		                     }
		                 }
		             );
   	   		        },'json');
            }
   		}
   		
   		function onButtonEdit(e) {
   			var aimURL = "";
   			var aimTitle = "";
   			if("focut"==e.sender.name){
   				pathURL = "itemManage/selectItemMutiComplex.jsp?type=C";
   				aimTitle = "选择刀具";
   			}
   			if("fomeasure"==e.sender.name){
   				pathURL = "itemManage/selectItemMutiComplex.jsp?type=M";
   				aimTitle = "选择量具";
   			}
   			if("fotool"==e.sender.name){
   				pathURL = "itemManage/selectItemMutiComplex.jsp?type=T";
   				aimTitle = "选择夹具";
   			}
   			if("foaccessory"==e.sender.name){
   				pathURL = "itemManage/selectItemMutiComplex.jsp?type=F";
   				aimTitle = "选择辅料";
   			}
   			if("fomaterial"==e.sender.name){
   				pathURL = "itemManage/selectItemMutiComplex.jsp?type=Y";
   				aimTitle = "选择原材料";
   			}
            var btnEdit = this;
            mini.open({
                url: pathURL,
                showMaxButton: true,
                title: aimTitle,
                width: 1100,
                height: 500,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    iframe.contentWindow.SetData(null);
                },
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须

                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                        //alert(btnEdit.name.substr(2)+"_cut");
                        mini.get(btnEdit.name.substr(2)+"_num").setValue(data.num);

                    }
                }
            });
        }
        
        
	</script>
</html>
