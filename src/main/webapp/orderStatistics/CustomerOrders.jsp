<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>客户订单信息</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
	<!--
	table {
	    table-layout:fixed;
	    word-break: break-all;
	} 
	-->
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  	</br>
  	</br>
  	
 <div id="win1" class="mini-window" title="操作" style="width:600px;height:200px;" expanded="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" collapseOnTitleClick="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >

  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
 				<td>开始时间</td>
				<td>
					<input id="bdate" name ="bdate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="false" required="true"/>
 				</td>
 				<td>结束时间</td>
				<td>
					<input id="edate" name ="edate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="false" required="true"/>
 				</td>
 				<td>
 					<input value="查询" type="button" style= "width:70px;" onclick="search()" />
 					<input value="更新" type="button" style= "width:70px;" onclick="updateSearch()" />
 					<input value="查看回款" type="button" style= "width:70px;" onclick="seePay()" />
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
   	 <div id= "userdiv">
   	 	<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td style="width:60px;"><label for="companyId$text">客户编号*</label></td>
            <td style="width:60px;"><input id="companyId"  name="companyId" class="mini-textbox" width="100%"   value="${customer.companyId}" enabled="false"/></td>
   			<td style="width:60px;"><label for="companyName$text">客户姓名*</label></td>
            <td style="width:210px;"> <input id="companyName"  name="companyName" class="mini-textbox"  width="100%"   value="${customer.companyName} " enabled="false"/></td>
   			<td><label for="type$text">企业类型*</label></td>
            <td><input id="type"  name="type" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." enabled="false"
    			url="GetCompanyTypeServlet" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${customer.type}"/>  
            </td>
   		</tr>
        <tr>
            <td><label for="connector$text">联系人*</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox" width="100%"  value="${customer.connector}" enabled="false"/></td>
            <td><label for="connectorTel$text">联系电话*</label></td>
	        <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" width="100%"  onvalidation="isTelephone" required="true" value="${customer.connectorTel}" enabled="false"/></td>
	        <td><label for="telephone$text">传真电话*</label></td>
            <td><input id="telephone"  name="telephone" class="mini-textbox" width="100%"  value="${customer.telephone}" enabled="false"/></td>
   		</tr>
   	</table>
  </div> 	
 </div>
    <div id="grid1" class="mini-datagrid" style="width:90%;height:85%;" allowResize="true"
         borderStyle="border:2;" multiSelect="false"  idField="id" showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell" allowAlternating="true" showPager="true" allowDrag="true"
         url="LoadCustomerOrders">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div type="checkcolumn"></div>
            <div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="orderid" width="110" headerAlign="center">订单编号
            </div>
            <div field="manuc" width="100" headerAlign="center">制造成本
            </div>
            <div field="allc" width="50" headerAlign="center">总成本
            </div>
            <div field="qualityLoss" width="50" headerAlign="center">质量损失
            </div>
            <div field="orderprice" width="50" headerAlign="center" >订单总价
            </div>
            <div field="profit" width="60" headerAlign="center">利润
            </div>
            <div field="profitrate" width="60" headerAlign="center">利润率
            </div>
            <div field="orderStatus" width="60" headerAlign="center" renderer="onGenderRenderer">订单状态
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	   // grid.load();
	   
	   getNextDay();
	   function getNextDay(){
	   		var now=new Date();
	   		var temp = now.getDate()+1;
	   		
	   		now.setDate(temp);
          	var year = now.getFullYear();
      		var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
      		var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
           mini.get("edate").setValue(year+"-"+month+"-"+day);
	   }
	   
	   function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            //服务端汇总计算
            if (e.field == "orderid") {                
                var s = "<b style='color:red;'>"
                s +=  	"合计：<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "manuc") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.Smanuc + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "allc") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.Sallc + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "qualityLoss") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.SqualityLoss + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            
            if (e.field == "orderprice") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.Sorderprice + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "profit") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.Sprofit + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
        }
        
	    function search(){
	    	
	    	var form0 = new mini.Form("form1");
	   	 	form0.validate();
	      	 if (form0.isValid() == false) {
	      	 	alert("请选取具体时间段");
	      	 	return;}
	    	
	    	
	    	var bdate = mini.get("bdate").getFormValue();
	    	var edate = mini.get("edate").getFormValue();
	    	var customer = mini.get("companyId").getValue();
	    	
	    	grid.load({bdate:bdate, edate:edate,customer:customer});
	    	var win = mini.get("win1");
	    	win.setExpanded(false);
	    }
	    function seePay(){
	    	var customer = mini.get("companyId").getValue();
	    	
	    	window.location="getCompanyIdServlet?companyId="+customer+"&para=see";
	    }
	    function updateSearch(){
	    	var bdate = mini.get("bdate").getFormValue();
	    	var edate = mini.get("edate").getFormValue();
	    	var customer = mini.get("companyId").getValue();
	    	
	    	var data ={
	    		bdate : bdate,
	    		edate : edate,
	    		customer:customer
	    	};
	    	var params = eval("("+mini.encode(data)+")");
            jQuery.post("CustomerOrdersCost", params, function(text){
               if(text=="success"){
                 alert("操作成功 ");
                 grid.load({bdate:bdate, edate:edate,customer:customer});
            	}else{
                       alert("操作失败");
            	}
            },'json');
	    	
	    }
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='详情' href=javascript:onAllDetail(\'" + e.row.orderid+"\',\'"+e.rowIndex+"\') ><span class='mini-button-text mini-button-icon icon-lock'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
		function onAllDetail(orderId,rowIndex){
			var bdate = mini.get("bdate").getFormValue();
	    	var edate = mini.get("edate").getFormValue();
	    	var customer = mini.get("companyId").getValue();
	    	
	    	var page = grid.getPageIndex();
	    	
			window.location="ToCustomerProducts?orderId=" + orderId+"&bdate="+bdate+"&edate="+edate+"&customer="+customer+"&rowIndex="+rowIndex+"&page="+page;
		}
		
        var Genders=[{id: "1", text: "新建"},{id: "2", text: "待审核"},{id: "3", text: "提交上级审核"},{id: "4", text: "审核不通过"},{id: "5", text: "审核通过"},	
	                {id: "6", text: "备料"},{id: "7", text: "代加工"},{id: "8", text: "加工中"},{id: "9", text: "完成"},{id: "10", text: "交付中"},{id: "11", text: "交付完成"}]
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }

        var dept = [{id:"jj",text:"机加事业部"},{id:"yy",text:"液压装备事业部"}];
        function onDeptRenderer(e) {
            for (var i = 0, l = dept.length; i < l; i++) {
                var g = dept[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        
     function showAtPos() {
        var win = mini.get("win1");

        var x = "right";
        var y = "top";

        win.showAtPos(x, y);
    }
	showAtPos();
    </script>
  </body>
</html>
