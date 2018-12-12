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
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('<%=ProductStatus.PARTPLANPASS%>')">审核通过</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('<%=ProductStatus.PARTPLANNOTPASS%>')">审核不通过</a>
		<span class="separator"></span>
    	<a class="mini-button" iconCls="icon-ok" plain="false" onclick="pass('<%=ProductStatus.PARTPLANUP%>')">提交上级审核</a>
    </div>
  	
    <div class="mini-fit" >
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;"  borderStyle="border:0;" multiSelect="true"
            url="PartPlanBaseServlet?meth=SeeSubmitPlanServlet" showPager="false" 
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
                <div field="eTime" width="50" headerAlign="center" dateFormat="yyyy-MM-dd">计划交付时间</div> 
                <div field="productNum" width="50" headerAlign="center">零件数量</div> 
                <div field="foOpcontent" width="100" headerAlign="center">工艺顺序</div> 
                <div field="partPlanNum" width="50" headerAlign="center">计划数量</div>
                <div field="planDuration" width="60" headerAlign="center">计划单件工时</div>
                <div field="totalDuration" width="40" headerAlign="center">总工时</div>
                <div field="productStatusName" width="80" headerAlign="center">产品状态</div>
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
	        str += "<a style='margin-right:2px;' title='查看详情' href=javascript:ondEdit(\'" + e.row.deptId + "\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(deptId){
	        //window.location.href="DeptSpecServlet?deptId=" + deptId;
		}
		
       	function pass(status){
       		var rows = grid.getSelecteds();
   			
   			if(rows.length>0){
   				for(var i=0;i<rows.length;i++){
   					if(rows[i].productStatus<<%=ProductStatus.PARTPLANING%>){
   						alert(rows[i].text+"   还没有制定计划，请重新选择!");
   						return;
   					}
   					if(rows[i].productStatus==<%=ProductStatus.PARTPLANPASS%>){
   						alert(rows[i].text+"   已经审核通过，请重新选择!");
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
   				var productStatus = status;
   				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','productStatus':'"+productStatus+"','drawingId':'"+drawingId+"'}";
   				data[j] = subdata;
   				j++;
   			}
   			
   			data = JSON.stringify(data);
   			params = {'data':data};
   			
   			var url = 'PartPlanBaseServlet?meth=PartsPlanPass&pathTo=';
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
