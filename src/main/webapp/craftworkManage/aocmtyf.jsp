<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
    <title>装配刀量夹具</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    <script type="text/javascript">
     function toservlet(){   
     if (document.all.oper_id.value=="" ){alert("请先编制此fo下的工序！");return ;}
        document.all.form_cmtyf.dotype.value ="addcmtyf";
		document.all.form_cmtyf.action = "../../FocmtyfSvlt";
		document.all.form_cmtyf.submit();
    }
    
     function clearOk(){
          document.all.oper_id.value = "";
		  document.all.focut.value = "";
		  document.all.cut_num.value= "";
		  document.all.fomeasure.value = "";
		  document.all.measure_num.value = "";
		  document.all.fotool.value = "";
		  document.all.tool_num.value = "";
		  document.all.fomaterial.value = "";
		  document.all.material_num.value = "";
		  document.all.foaccessory.value = "";
		  document.all.accessory_num.value = "";
    }
     
    function delcmtyf(){
    if(document.getElementById("oper_id2").value==""){alert("请先选择已编制的fo工序，或先编制工序！");return;}
    document.form_delcmtyf.action="../../FodelcmtyfSvlt";
    document.form_delcmtyf.submit();
    }
    </script>

  </head>
  
	<body>
    	<form id="form_cmtyf" name="form_cmtyf" action="" method=post>
    		<table class="green_list_table" align="center" width="100%" height="50%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#dafec5">
    			<tr>
      				<th>装配号</th>
      				<td><input id="aoId" name="aoId" class="mini-textbox" width="100%" required="true" readonly value="${aoId}"/></td>
      				<th>装配工艺号</th>
	         		<td><input id="aoNo" name="aoNo" class="mini-textbox" width="100%" required="true" readonly value="${aoNo}"/></td>
	         	</tr>
   				<tr><th>使用刀具</th>
   					<td><input id="focut" name="focut" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false" /></td>
   				 	<th>使用刀具数量</th><td><input id="cut_num"  name="cut_num" class="mini-textbox"  width="100%" readonly/></td>
				</tr>
				<tr>
					<th>使用量具</th>
					<td><input id="fomeasure" name="fomeasure" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false"/></td>
   				 	<th>使用量具数量</th><td><input id="measure_num"  name="measure_num" class="mini-textbox"  width="100%" readonly/></td></tr>            
    			<tr>
    				<th>使用夹具</th>
    				<td><input id="fotool" name="fotool" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false"/></td>
   				 	<th>使用夹具数量</th><td><input id="tool_num"  name="tool_num" class="mini-textbox"  width="100%" readonly/></td>
	   			</tr>         
				<tr>
					<th>原材料</th>
					<td><input id="fomaterial" name="fomaterial" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false"/></td>
   				 	<th>使用原材料数量</th><td><input id="material_num"  name="material_num" class="mini-textbox"  width="100%"  readonly/></td>
	     		</tr>        
				<tr><th>辅料</th>
					<td><input id="foaccessory" name="foaccessory" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false"/></td>
   				 	<th>使用辅料数量</th><td><input id="accessory_num"  name="accessory_num" class="mini-textbox"  width="100%" readonly/></td>
				</tr>             
				<tr><td><a class="mini-button" iconCls="icon-save" plain="false"  onclick="toservlet()">保存</a>
	     			<!--<input type="button"  value="清 空" title="清空文本框" onclick="clearOk();" style="cursor:hand;">-->
	     			<input id="orderId" name="orderId" class="mini-hidden" value="${orderId}"/>
				</tr>
			</table>
			<br>
		</form>
		
	<div id="grid1" class="mini-datagrid" style="width:100%;height:300px;"
         	borderStyle="border:0;" multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         	url="BaseServlet?meth=AoCMTYFList&aoId=${aoId}&aoNo=${aoNo}">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"cellStyle="padding:0;">操作</div>
            <div field="aoNo" width="60" headerAlign="center">工序编号</div>
            <div field="cut" width="100" headerAlign="center">刀具</div>
            <div field="cutNum" width="60" headerAlign="center">刀具数量</div>
            <div field="measure" width="100" headerAlign="center">量具</div>
            <div field="measureNum" width="60" headerAlign="center">量具数量</div>
            <div field="tool" width="100" headerAlign="center">夹具</div>
            <div field="toolNum" width="60" headerAlign="center">夹具数量</div>
            <div field="material" width="100" headerAlign="center">原材料</div>
            <div field="materialNum" width="60" headerAlign="center">原材料数量</div>
            <div field="accessory" width="100" headerAlign="center">辅料</div>
            <div field="accessoryNum" width="60" headerAlign="center">辅料数量</div>
        </div>
    </div>
   
  </body>
	
	
	<script>
		mini.parse();
		var grid = mini.get("grid1");
    	grid.load();
		
		function GetRequest() { 
        	var url = location.search; //获取url中"?"符后的字串 
        	var theRequest = new Object(); 
        	if (url.indexOf("?") != -1) { 
        		var str = url.substr(1); 
        		strs = str.split("&"); 
        		for(var i = 0; i < strs.length; i ++) { 
        			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
        		} 
        	} 
        	return theRequest; 
        } 

        var Request = new Object(); 
        Request = GetRequest();
        mini.get("oper_id").value = Request["foNo"];
        mini.get("productId").value = Request["productId"];
        mini.get("issueNum").value = Request["issueNum"];
	
   		function onOperatePower(e) {
			var str = "";
			str += "<span>";
			str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.aoId+"\',\'" + e.row.aoNo+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;&nbsp;</span></a>";
			str += "</span>";
			return str;
		};
		    
		function ondEdit(aoId,aoNo){
		    window.location.href = "BaseServlet?meth=aocmtyfEdit&pathTo=aocmtyfEdit&aoId="+aoId+"&aoNo="+aoNo;
		}
   		
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
