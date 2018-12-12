<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
	<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/css/person.css" type=text/css rel=stylesheet>
   
    <title>工序</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  <body leftMargin=0 topMargin=0 style="width:99%;height:100%;">
  	<div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('5')">审核通过</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('4')">审核不通过</a>
		<span class="separator"></span>
    	<a class="mini-button" iconCls="icon-ok" plain="false" onclick="pass('3')">提交上级审核</a>
    </div>
	<fieldset style="width: 99%;" align="center">
		<legend>
			工艺
		</legend>   	
	   	<table class="query_form_table" width="100%" align="center"  border="0" style="word-break:break-all;">
			<tr align=center><th width="15%">订单号</th><th>产品编号</th><th>产品名称</th><th>版本号</th><th>图号</th><th>产品类型</th></tr>
			<tr><td align=center>${result.orderId} <input id="foType" name ="foType" class="mini-hidden" value="${result.foType}"/></td>
    			<td align=center id="productId" name="productId">${result.productId} <input id="barcode" name ="barcode" class="mini-hidden" value="${result.barcode}"/></td>
    			<td align=center>${result.productName}</td>
    			<td align=center id="issueNum" name="issueNum">${result.issueNum}</td>
    			<td align=center>${result.drawingId}</td>
    			<td align=center>${result.itemTypeName}</td><input id="productType" name="productType" type="hidden" value="${result.itemTypeId}"/></td>
        	</tr>
    		<tr><th>父产品号</th><th>产品数量</th><th>产品规格</th><th colspan="3">备注</th></tr>
    		<tr>
    			<td align=center>${result.fproductId}</td>
        		<td align=center>${result.productNum}</td>
        		<td align=center>${result.spec}</td>
        		<td align=center colspan="2">${result.memo}</td>
    		</tr>
    		
    		<tr align=center><th>材料</th><th>供应状态</th><th>技术要求</th><th>技术规格</th><th>毛坯尺寸</th><th>可加工零件数</th></tr>
			<tr><td align=center></td>
    			<td align=center></td>
    			<td align=center></td>
    			<td align=center>${result.spec}</td>
    			<td align=center></td>
    			<td align=center></td>
        	</tr>
  		</table>
  		<div id= "userdiv">
  		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%">
  			<tr height="40px;">
   				<td width="15%"><label for="confirmedAdvice$text">已有审核意见</label></td>
	        	<td ><input id="confirmedAdvice" name="confirmedAdvice" class="mini-textarea" width="100%" height="100%" readonly value="${result.confirmedAdvice}"/></td>
       		</tr>
   			<tr height="40px;">
   				<td width="15%"><label for="confirmAdvice$text">审核意见</label></td>
	        	<td><input id="confirmAdvice" name="confirmAdvice" class="mini-textarea" emptyText="请输入审核意见" width="100%" height="100%"/></td>
       		</tr>
  		</table>
  		</div>
   	</fieldset>
	
	<div id="grid1" class="mini-datagrid" style="width:99%;height:60%;" borderStyle="border:0;" idField="id"  allowResize="true"
         url="BaseServlet?meth=CraftworkList&productId=${result.productId}&issueNum=${result.issueNum}" onshowrowdetail="onShowRowDetail"
         showSummaryRow="true" allowAlternating="true" >   
        <div property="columns">
            <div type="expandcolumn" >#</div>
            <!--
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"cellStyle="padding:0;">操作</div>
            -->
            <div field="foNo" width="70" headerAlign="center">工序编号</div>
            <div field="foOpName" width="70" headerAlign="center">工序名称</div>
            <div field="workBranchName" width="30" headerAlign="center">工种</div>
            <div field="foOpcontent" width="200" headerAlign="center">工序内容</div>
            <div field="cut" width="50" headerAlign="center">刀具</div>
            <div field="tool" width="50" headerAlign="center">夹具</div>
            <div field="operAidTime" width="40" headerAlign="center">辅助工时</div>
            <div field="operTime" width="40" headerAlign="center">总工时</div>
            
            <!--
            <div field="ratedTime" width="50" headerAlign="center">额定工时</div>
            <div field="planTime" width="50" headerAlign="center">计划工时</div>
            <div field="inspTime" width="50" headerAlign="center">检验工时</div>
            <div field="isCo" width="50" headerAlign="center" renderer="onGenderRenderer">是否外协</div>
            <div field="isKey" width="50" headerAlign="center" renderer="onGenderRenderer">关键工序</div>
            <div field="cutNum" width="50" headerAlign="center">刀具数量</div>
            <div field="toolNum" width="50" headerAlign="center">夹具数量</div>
            <div field="measure" width="50" headerAlign="center">量具</div>
            <div field="measureNum" width="50" headerAlign="center">量具数量</div>
            -->
        </div>
    </div>
    
    <div id="editForm1" style="display:none;padding:5px;">
        <input class="mini-hidden" name="id"/>
        <table style="width:100%;">
            <tr>
                <td style="width:80px;">工序编号：</td>
                <td style="width:80px;"><input id= "foNo" name="foNo" class="mini-textbox" readonly/></td>
                <td style="width:80px;">工序名称：</td>
                <td style="width:80px;"><input name="foOpName" class="mini-textbox" readonly/></td>
                <td style="width:80px;">工种：</td>
                <td style="width:80px;"><input name="workBranchName" class="mini-textbox" readonly/></td>
                <td style="width:80px;">辅助工时：</td>
                <td style="width:80px;"><input name="operAidTime" class="mini-textbox" readonly/></td>
            </tr>
            <tr>
            	<td style="width:80px;">总工时：</td>
                <td style="width:80px;"><input name="operTime" class="mini-textbox" readonly/></td>
                <td style="width:80px;">是否关键工序：</td>
                <td style="width:80px;"><input name="isKey" class="mini-textbox" readonly/></td>
                <td style="width:80px;">刀具：</td>
                <td style="width:80px;"><input name="cut" class="mini-textbox" readonly/></td>
                <td style="width:80px;">刀具数量：</td>
                <td style="width:80px;"><input name="cutNum" class="mini-textbox" readonly/></td>
            </tr>
            <tr>
                <td style="width:80px;">量具：</td>
                <td style="width:80px;"><input name="measure" class="mini-textbox" readonly/></td>
                <td style="width:80px;">量具数量：</td>
                <td style="width:80px;"><input name="measureNum" class="mini-textbox" readonly/></td>
                <td style="width:80px;">夹具：</td>
                <td style="width:80px;"><input name="tool" class="mini-textbox" readonly/></td>
                <td style="width:80px;">夹具数量：</td>
                <td style="width:80px;"><input name="toolNum" class="mini-textbox" readonly/></td>
            </tr>
            <tr>
                <td style="width:80px;">原材料：</td>
                <td style="width:80px;"><input name="material" class="mini-textbox" readonly/></td>
                <td style="width:80px;">原材料数量：</td>
                <td style="width:80px;"><input name="materialNum" class="mini-textbox" readonly/></td>
                <td style="width:80px;">辅料：</td>
                <td style="width:80px;"><input name="accessory" class="mini-textbox" readonly/></td>
                <td style="width:80px;">辅料数量：</td>
                <td style="width:80px;"><input name="accessoryNum" class="mini-textbox" readonly/></td>
            </tr>
            <tr>
            	<td>工艺文件下载</td>
   			 	<th><a id="craftdown" onclick="craftDownLoad()" >点击下载</a><br/></th>   
            </tr>
            <tr>
            	<td style="width:80px;">工艺内容：</td>
                <td colspan="7"><input name="foOpcontent" class="mini-textarea" readonly height="100%" width="100%"/></td>
            </tr>
            
        </table>
    </div>
	
</BODY>
<script>
	mini.parse();
   	var grid = mini.get("grid1");
    grid.load();
    
    function craftDownLoad(){
    	var fo = mini.get("foNo").getValue();
    	var url = "DownLoadCraft?productId=${result.productId}&issue=${result.issueNum}&foNo="+fo;
    	
    	document.getElementById("craftdown").href=url;
    }
        
    function onOperatePower(e) {
		var str = "";
		str += "<span>";
		str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.foNo+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;&nbsp;</span></a>";
		str += "</span>";
		str += "<span>";
		str += "<a style='margin-right:2px;' title='刀量夹具' href=javascript:onCMTYF(\'" + e.row.productId+"\',\'" + e.row.issueNum+"\',\'" + e.row.foNo+"\') ><span class='mini-button-text mini-button-icon icon-cut'>&nbsp;&nbsp;</span></a>";
		str += "</span>";
		return str;
	};
	    
	function ondEdit(foNo){
 		var productType = window.parent.document.getElementById("productType").value;
		var productId = window.parent.document.getElementById("productId").innerHTML;
		var issueNum = window.parent.document.getElementById("issueNum").innerHTML;
	    window.parent.document.getElementById("subIframe").src="BaseServlet?meth=FodetailData&pathTo=fodetailData&productType="+productType+"&productId="+productId+"&issueNum="+issueNum+"&foNo="+foNo;
	}
	
	function onCMTYF(productId,issueNum,foNo){
	     window.location.href="BaseServlet?meth=Gofocmtyf&pathTo=focmtyf&productId="+productId+"&issueNum="+issueNum+"&foNo="+foNo;	    
	}
		
	function refresh(){
		grid.reload();
	}
	
	function toservlet(){
 		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
          	data.productType = '${productType}';
          	data.productId = '${productId}';
          	data.issueNum = '${issueNum}';
            var params = eval("("+mini.encode(data)+")");
            var url = 'BaseServlet?meth=Addcraftwork&pathTo=';
 		    jQuery.post(url, params, function(data){
 	   			mini.confirm(data.result, "确定？",
                function (action){            
                    if (action == "ok") {
                    	window.location.href = window.location.href;	
                    }
                });
 	   		},'json');
		}
   	}
   	
   	function pass(orderStatus){
        var form = new mini.Form("#userdiv");
   		form.validate();
        if (form.isValid() == false) {
            return;
        }else{
            var data = form.getData();
            //data.leave=leave;
            data.status=orderStatus;
            data.productId="${result.productId}";
            data.issueNum=${result.issueNum};
            data.orderId="${result.orderId}";
            data.drawingId="${result.drawingId}";
            data.foType = "${result.foType}";	//报废件重做工艺 or 正常件 
            data.barcode = "${result.barcode}";
            var params = eval("("+mini.encode(data)+")");
            var url = 'BaseServlet?meth=updatecraftwork';
   		    jQuery.post(url, params, function(data){
 	   			mini.confirm(data.result, "确定？",
                function (action){            
                    if (action == "ok") {
                    	window.location.href = window.location.href;	
                    }
                });
 	   		},'json');
        }
	}
   		
	function onButtonEditMachine(e) {
           var btnEdit = this;
           mini.open({
               url: "machineManage/selectMachineWindow.jsp",
               title: "选择上级部门",
               width: 650,
               height: 380,
               ondestroy: function (action) {
                   //if (action == "close") return false;
                   if (action == "ok") {
                       var iframe = this.getIFrameEl();
                       var data = iframe.contentWindow.GetData();
                       data = mini.clone(data);    //必须
                       if (data) {
                           btnEdit.setValue(data.machineId);
                           btnEdit.setText(data.machineName);
                           //mini.get("connector").setValue(data.connector);
                           //mini.get("connectorTel").setValue(data.connectorTel);
                       }
                   }
               }
           });
       }
       
       var Genders = [{ id: '1', text: '是' }, { id: '0', text: '否'}];
       function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
       }
       
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
        //alert(Request['productId']);
        //mini.get("productType").value = Request['machineId'];
        
        
        var editForm = document.getElementById("editForm1");        
        
        function onShowRowDetail(e) {
            var row = e.record;
            
            //将editForm元素，加入行详细单元格内
            var td = grid.getRowDetailCellEl(row);
            td.appendChild(editForm);
            editForm.style.display = "";

            //表单加载员工信息
            var form = new mini.Form("editForm1");
            if (grid.isNewRow(row)) {                
                form.reset();
            } else {
            	var rowSelected = grid.getSelected();
            	var o = mini.decode(rowSelected);
            	form.setData(o); 
            	/*
                $.ajax({
                    url: "GetCraftName?" + row.id,
                    success: function (text) {
                        var o = mini.decode(text);
					
                    }
                });
                */
            }
        }
        function updateRow() {
            var form = new mini.Form("editForm1");
            var o = form.getData();
            grid.loading("保存中，请稍后......");
            var json = mini.encode([o]);
            $.ajax({
                url: "../data/AjaxService.aspx?method=SaveEmployees",
                data: { data: json },
                success: function (text) {
                    grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }
</script>
</html>