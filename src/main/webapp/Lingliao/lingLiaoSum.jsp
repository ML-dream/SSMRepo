<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>领料统计</title>
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
  	<div id="win1" class="mini-window" title="查找" style="width:550px;height:100px;" collapseOnTitleClick="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" expanded="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    	<form id="form0">
	    <table >
	   		<tr>
	          <td>开始时间：</td>
	          <td><input id="bdate" class="mini-datepicker" width="100"  allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td>
	          <td>结束时间：</td>
	          <td><input id="edate" class="mini-datepicker"   width="100" allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td>
	       
	          <td>物料类型：</td>
	          <td><input id="itemType" name="itemType" class="mini-combobox" width="100" textName="" textField="text" valueField="id"
  				url="GetItemTypeServlet"  allowInput="false" showNullItem="true" nullItemText="请选择..."  onvaluechanged="loadgrid"/>
	          </td>
	        </tr>
	        <tr>
	          <td><input value="查找" type="button" onclick="loadgrid()" style="width:50px;"/></td>
	           <td><input value="导出Excel" type="button" onclick="outPut()" style="width:70px;"/></td>
	   		</tr>
	   	</table>
	   </form>
	</div>
	</br></br></br></br></br>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:90%;"  showColumnsMenu ="true" 
         borderStyle="border:0;" multiSelect="false"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         ondrawsummarycell=""  url="LingliaoSum">
        <div property="columns">
            <div type="indexcolumn"></div>
            <!-- 
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
             -->
             
            <div field="liDate" width="100" headerAlign="center">领料日期
            </div>
            <div field="stuff" width="50" headerAlign="center">材料
            </div>
            <div field="spec" width="60" headerAlign="center">规格
            </div>
            <div field="itemType" width="50" headerAlign="center" >物料类型
            </div>
            <div field="liNum" width="60" headerAlign="center">领料数量
            </div>
            <div field="unit" width="60" headerAlign="center">单位
            </div>
            <div field="unitPrice" width="60" headerAlign="center">物料单价
            </div>
            <div field="sumPrice" width="60" headerAlign="center">总价
            </div>
            <div field="llStaffName" width="60" headerAlign="center">领料人
            </div>
            
            <div field="productName" width="110" headerAlign="center">产品名称
            </div>
            <div field="productId" width="110" headerAlign="center">产品编号
            </div>
            <div field="customer" width="110" headerAlign="center">客户
            </div>
            <div field="customerId" width="110" headerAlign="center">客户编号
            </div>
            <div field="productNum" width="110" headerAlign="center">产品数量
            </div>
            
            <div field="orderId" width="110" headerAlign="center">订单编号
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	   grid.load();
	   
	   function outPut(){
    	var bdate = mini.get("bdate").getFormValue();
		var edate = mini.get("edate").getFormValue();
		
		var itemType = mini.get("itemType").getValue();
		
    	window.location="LingLiaoExcel?para=para&bdate="+bdate+"&edate="+edate+"&itemType="+itemType;
    }
	   
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
	   
	    function loadgrid(){
	    	var form0 = new mini.Form("form0");
	   	 	form0.validate();
	      	 if (form0.isValid() == false) {
	      	 	alert("请选取具体时间段");
	      	 	return;}
	    	var bdate = mini.get("bdate").getFormValue();
			var edate = mini.get("edate").getFormValue();
		
			var itemType = mini.get("itemType").getValue();
	    	grid.load({bdate:bdate, edate:edate, itemType:itemType});
	    }
	    function onCloseClick (para){
	    	mini.get(para).setValue("");
	    	mini.get(para).setText("");
	    }
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='查看订单' href=javascript:ondSee(\'" + e.row.orderId+"\',\'"+e.row.connector + "\',\'"+e.rowIndex+"\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        
	        return str;
	    }
	    function showAtPos() {
	        var win = mini.get("win1");
	
	        var x = "right";
	        var y = "top";
	
	        win.showAtPos(x, y);
	
	    }
		showAtPos();
		function onButtonEdit(e) {
         var btnEdit = this;
         var url ="qualitycheck/selectEmployeeWindow.jsp";
         mini.open({
             url: url,
             title: "选择列表",
             width: 650,
             height: 380,
             ondestroy: function (action) {
                 if (action == "ok") {
                     var iframe = this.getIFrameEl();
                     var data = iframe.contentWindow.GetData();
                     data = mini.clone(data);    //必须
                     if (data) {
                         btnEdit.setValue(data.staff_code);
                         btnEdit.setText(data.staff_name);
                     }
                 }
             }
         });
     }
      function onButtonEdit2(e) {
         var btnEdit = this;
         mini.open({
             url: "orderManage/selectCustomerWindow.jsp",
             title: "选择列表",
             width: 650,
             height: 380,
             ondestroy: function (action) {
                 //if (action == "close") return false;
                 if (action == "ok") {
                     var iframe = this.getIFrameEl();
                     var data = iframe.contentWindow.GetData();
                     data = mini.clone(data);    //必须
                     if (data) {
                         btnEdit.setValue(data.companyId);
                         btnEdit.setText(data.companyName);
                     }
                 }

             }
         });
     }

	    function ondSee(orderId,connector,rowIndex){
	    	//alert(rowIndex);
	    	var bday = mini.get("bday").getFormValue();
	    	var eday = mini.get("eday").getFormValue();
	    	var page = grid.getPageIndex();
	    	var creater = mini.get("creater").getValue();
	    	var status = mini.get("status").getValue();
	    	var customer = mini.get("customer").getValue();
	    	
	    	window.location="GoCheckOrderDetailListServlet?orderId="+orderId+"&para=seeAll&bday="+bday+"&eday="+eday+"&rowIndex="+rowIndex+"&page="+page+"&creater="+creater+"&status="+status+"&customer="+customer;
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
    </script>
  </body>
</html>
