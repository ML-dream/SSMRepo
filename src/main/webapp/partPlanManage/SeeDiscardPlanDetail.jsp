<%@ page language="java" import="java.util.*,com.wl.common.OrderStatus,com.wl.common.ProductStatus" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>计划信息查看</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  	<div class="mini-toolbar" style="padding:2px;border:0;">
  		<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()">刷新</a>
  		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-filter" plain="false" onclick="getTime()">设置工序间隔时间</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-add" plain="false" onclick="doPartsPlan()">批量制定零件计划</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-node" plain="false" onclick="seePartsPlan()">查看零件计划</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-ok" plain="false" onclick="submit()">提交零件计划</a>
    </div>
  	
    <div class="mini-fit" >
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;"  borderStyle="border:0;" multiSelect="true"
            url="DiscardBaseServlet?meth=SeePlanDetailServlet" showPager="false" 
            allowCellEdit="true" allowCellSelect="true" multiSelect="true" editNextOnEnterKey="true"  editNextRowCell="true"
        >
            <div property="columns">     
            	<div type="checkcolumn"></div>
       			<div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower" cellStyle="padding:0;">操作</div>       
                <!--
                	<div field="orderId" width="80" headerAlign="center">订单号</div>
                	<div field="productId" width="80" headerAlign="center">零件号</div> 
                	<div field="orderDate" width="50" headerAlign="center" dateFormat="yyyy-MM-dd">接收时间</div> 
                -->           
                <div field="productName" width="80" align="center" headerAlign="center">零件名称</div>            
                <div field="issueNum" width="35" headerAlign="center">版本号</div>
                <div field="drawingId" width="70" headerAlign="center">图号</div>      
                <div field="eTime" width="50" headerAlign="center" dateFormat="yyyy-MM-dd">零件交付时间</div> 
                <div field="endTime" width="50" headerAlign="center" dateFormat="yyyy-MM-dd">零件交付时间</div> 
                <div field="productNum" width="50" headerAlign="center">零件数量</div> 
        <!--    <div field="foOpcontent" width="100" headerAlign="center">工艺顺序</div> 
      
                <div field="partPlanNum" width="50" headerAlign="center">计划数量</div>
         -->
                <div field="planDuration" width="60" headerAlign="center">计划单件工时</div>
                <div field="totalDuration" width="40" headerAlign="center">总工时</div>
       <!--     <div field="productStatusName" autoShowPopup="true" width="60" headerAlign="center">产品状态
                	<input property="editor" class="mini-combobox" style="width:100%;" textField="statusName" valueField="statusId" url="GetProductStatus" /> 
                </div> 
       -->      
            </div>
        </div>  
    </div>
    
    <input class="mini-hidden" id="hour" name="hour">
	<input class="mini-hidden" id="minite" name="minite">
    
    <script type="text/javascript">
    	mini.parse();

        var grid = mini.get("grid1");
        grid.load();
        
        function refresh(){
			grid.load();
		}

		function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='查看工序详情' href=javascript:ondEdit(\'" + e.row.orderId+"','"+e.row.productId+"','"+e.row.issueNum + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(orderId,productId,issueNum){
	        window.location.href="GoFoDetailServlet?orderId="+orderId+"&productId="+productId+"&issueNum="+issueNum;
		}
		
		
		function getTime(e){
        	var hour = mini.get("hour");
        	var minite = mini.get("minite");
    		mini.open({
                url: "<%=path %>/partPlanManage/dateTimeSelectedPage.jsp",
                showMaxButton: false,
                title: "选择树",
                width: 350,
                height: 350,
                ondestroy: function (action) {                    
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data) {
                           	hour.setValue(data.hour);
                           	minite.setValue(data.minite);
                        }
                    }
                }
            });
        }
		
		
		function listDetail(e){
       		var iframe = document.getElementById("mainDetail");
       		var tree = mini.get("leftTree");
   			var nodes = new Array();
   			nodes = getCheckedNodes(tree);
   			
   			var params = getCheckedNodesJson(nodes);
   			var data = [];
   			var j=0;
   			for(var i=0,len=nodes.length;i<len;i++){
   				var node = nodes[i];
   				var orderId = node.orderId;
   				var productId = node.productId;
   				var issueNum = node.issueNum;
   				var productName = node.productName;
   				var drawingId = node.drawingId;
   				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','drawingId':'"+drawingId+"'}";
   				data[j] = subdata;
   				j++;
   			}
   			
   			data = JSON.stringify(data);
   			params = {'data':data};
   			var url = 'PartPlanBaseServlet?meth=GoSeePlanDetail&pathTo=';
   			jQuery.post(url, params, function(data){
	   					iframe.src = '<%=path %>/partPlanManage/SeePlanDetail.jsp';
	   				});    			
       	}
       	
       	
       	function doPartsPlan(e){
       		var rows = grid.getSelecteds();
       	
   			var hour = mini.get("hour").getValue();
       		var minite = mini.get("minite").getValue();
       		
       		if(null==hour){
       			hour = 1;
       		}
       		if(minite==hour){
       			minite = 0;
       		}
   			
   			if(rows.length>0){
   				for(var i=0;i<rows.length;i++){
   					if(rows[i].productStatus>=<%=ProductStatus.PARTPLANING%>){
   						alert(rows[i].productName+"   已经做过计划了，请重新选择!");
   						return;
   					}
   					if('0'==rows[i].isGongYi){
   						alert(rows[i].productName+"   没有做工艺，请确认工艺!");
   						return;
           			}
           			if('1'==rows[i].isCaiGou){
           				alert(rows[i].productName+"   是采购件，请重新选择!");
   						return;
           			}
//           			if('1'==rows[i].isWaiXie){
//           				alert(rows[i].productName+"   是外协件，请重新选择!");
//   						return;
//           			}
       			}
   			}else{
   				alert("请选择零件!!!");
   				return ;
   			}
   			
   			var params = getCheckedRowsJson(rows);
   			var data = [];
   			var j=0;
   			for(var i=0,len=rows.length;i<len;i++){
   				var node = rows[i];
   				var orderId = node.orderId;
   				var productId = node.productId;
   				var issueNum = node.issueNum;
   				var productName = node.productName;
   				var drawingId = node.drawingId;
   				var sortie = hour+","+minite;
   				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','sortie':'"+sortie+"','drawingId':'"+drawingId+"'}";
   				data[j] = subdata;
   				j++;
   			}
   			
   			data = JSON.stringify(data);
   			params = {'data':data};
   			
   			var url = 'PartPlanBaseServlet?meth=AddpartsPlan&pathTo=';
   			jQuery.post(url, params, function(data){
	   				mini.confirm(data.result, "确定？",
               	function (action){            
                   	if (action == "ok") {
                   		window.location.href = window.location.href;	
                   	}
               	});
	   			},'json');
       	}
       	
       	
       	function seePartsPlan(){
       		var rows = grid.getSelecteds();
   			
   			if(rows.length>0){
   				for(var i=0;i<rows.length;i++){
   					if(rows[i].productStatus<<%=ProductStatus.PARTPLANING%>){
   						alert(rows[i].text+"   还没有制定计划，请重新选择!");
   						return;
   					}
       			}
   			}else{
   				alert("请选择零件!!!");
   				return ;
   			}
   			
   			var params = getCheckedRowsJson(rows);
   			var data = [];
   			var j=0;
   			for(var i=0,len=rows.length;i<len;i++){
   				var node = rows[i];
   				var orderId = node.orderId;
   				var productId = node.productId;
   				var issueNum = node.issueNum;
   				var productName = node.productName;
   				var drawingId = node.drawingId;
   				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','drawingId':'"+drawingId+"'}";
   				data[j] = subdata;
   				j++;
   			}
   			
   			data = JSON.stringify(data);
   			params = {'data':data};
   			
   			var pageTO = '';
   			if(rows.length==1){
   				pageTO = 'partProGT.jsp';
   			}else{
   				pageTO = 'partGT.jsp';
   			}
   			
   			var url = 'PartPlanBaseServlet?meth=GoPartsPlanGandT&pathTo=';
   			jQuery.post(url, params, function(data){
   				window.location.href = '<%=path %>/partPlanManage/'+pageTO;
	   			},'json');
       	}
       	
       	function submit(){
       		var rows = grid.getSelecteds();
       		
       		if(rows.length>0){
   				for(var i=0;i<rows.length;i++){
   					if(rows[i].productStatus<<%=ProductStatus.PARTPLANING%>){
   						alert(rows[i].text+"   还没有制定计划，请重新选择!");
   						return;
   					}
   					if(rows[i].productStatus>=<%=ProductStatus.PARTPLANSBUMIT%>){
   						alert(rows[i].text+"   已经提交过，请重新选择!");
   						return;
   					}
       			}
   			}else{
   				alert("请选择零件!!!");
   				return ;
   			}
   			
   			var params = getCheckedRowsJson(rows);
   			var data = [];
   			var j=0;
   			for(var i=0,len=rows.length;i<len;i++){
   				var node = rows[i];
   				var orderId = node.orderId;
   				var productId = node.productId;
   				var issueNum = node.issueNum;
   				var productName = node.productName;
   				var drawingId = node.drawingId;
   				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','drawingId':'"+drawingId+"'}";
   				data[j] = subdata;
   				j++;
   			}
   			
   			data = JSON.stringify(data);
   			params = {'data':data};
   			
   			var url = 'PartPlanBaseServlet?meth=PartsPlanSubmit&pathTo=';
   			jQuery.post(url, params, function(data){
   				window.location.href = window.location.href;
	   			},'json');
       	}
       	
       	
       	function getCheckedRowsJson(rows){
       		return JSON.stringify(rows);
       	}
       	
        //////////////////////////////////////////////
        var Genders = [{ id: 1, text: '男' }, { id: 2, text: '女'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        function saveData() {
            var data = grid.getChanges();
            var json = mini.encode(data);
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "../data/AjaxService.aspx?method=SaveEmployees",
                data: { data: json },
                type: "post",
                success: function (text) {
                    grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }
        
    </script>
  </body>
</html>
