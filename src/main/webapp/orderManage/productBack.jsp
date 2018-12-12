<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户退货</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
	<style type="text/css">
			.mini-grid-cell-inner, .mini-grid-headerCell-inner {
                font-size: 10pt;
                font-family: Tahoma, Verdana, 宋体;
                line-height: 18px;
                padding: 0px;
                padding-top: 2px;
                padding-bottom: 2px;
                width: 100%;
                position: relative;
                overflow: hidden;
                white-space: normal;
                word-break: break-all;
            }
		</style>
	
  </head>
  
  <body>
  	<div id="win1" class="mini-window" title="查找" style="width:650px;height:100px;" collapseOnTitleClick="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" expanded="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    	<form id="form0">
	    <table >
	   		<tr>
	          <td>开始时间：</td>
	          <td><input id="bday" class="mini-datepicker" width="100"  allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td>
	          <td>结束时间：</td>
	          <td><input id="eday" class="mini-datepicker"   width="100" allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td>
	          <td width="60px" align="right">接收人：</td>
	          <td><input id="creater" name="creater" class="mini-buttonedit" width="100" showClose="true" oncloseclick="onCloseClick('creater')"
            		onbuttonclick="onButtonEdit" textName="worker" required="false" value="" text="" onvaluechanged="loadgrid"  allowInput="false"/>
	          </td>
	       </tr>
	       <tr>
	          <td>订单状态：</td>
	          <td><input id="status" name="status" class="mini-combobox" width="100" textName="" textField="text" valueField="id"
  				url="data/seeOrderStatus.txt"  allowInput="false" showNullItem="true" nullItemText="请选择..."  onvaluechanged="loadgrid"/>
	          </td>
	   		  <td align="right">客户：</td>
	          <td colspan="2"><input id="customer" name="creater" class="mini-buttonedit" width="100%" showClose="true" oncloseclick="onCloseClick('customer')"
            		onbuttonclick="onButtonEdit2" textName="worker" required="false" value="" text="" onvaluechanged="loadgrid"  allowInput="false"/>
	          </td>
	          <td><input value="查找" type="button" onclick="loadgrid()" style="width:50px;"/>
	          		<input value="保存修改" type="button" onclick="saveGrid()" style="width:70px;"/></td>
	          	<!--<td><input value="导出Excel" type="button" onclick="outPut()" style="width:70px;"/></td>
	   		--></tr>
	   	</table>
	   </form>
	</div>
	</br></br></br></br></br>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:90%;"  showColumnsMenu ="true" allowCellEdit="true" allowCellSelect="true"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="false" allowAlternating="true" showPager="true"  oncellendedit="ontotal"
           url="ProductsForSaler">
        <div property="columns">
            
            <div field="orderId" name="orderId" width="110" headerAlign="center" cellStyle="font-size:24px;">订单编号
            </div>
            <div type="indexcolumn">序号</div>
            <div field="productId" width="110" headerAlign="center">图号
            </div>
            <div field="productName" width="110" headerAlign="center">名称
            </div>
            
            <div field="unitPrice" width="60" headerAlign="center">单价
            </div>
            <div field="productNum" width="60" headerAlign="center">数量
            </div>
            
            <div field="backNum" width="60" headerAlign="center" vtype="int" headerStyle="color:red;">退货数量
            	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" />
            </div>
            <div field="backPrice" width="60" headerAlign="center" vtype="float;" headerStyle="color:red;">退货总金额
            	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" />
            </div>
            <div field="backRemark" width="110" headerAlign="center">备注
            	<input property="editor" class="mini-textarea" style="width:100%;" minHeight="80"/>
            </div>
            
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	  	grid.load();
	   getNextDay();
	   function getNextDay(){
	   		var now=new Date();
	   		var temp = now.getDate()+1;
	   		
	   		now.setDate(temp);
          	var year = now.getFullYear();
      		var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
      		var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
           mini.get("eday").setValue(year+"-"+month+"-"+day);
	   }
	   
	   grid.on("beforeload", function (e) {
	       var data = grid.getChanges();
	       if (data.length > 0) {
	        mini.alert("请先保存");
	          e.cancel = true;
	          //return;
	       }
	   });
	   //精确的乘法结果
	   function accMul(arg1,arg2)  
		{  
			var m=0,s1=arg1.toString(),s2=arg2.toString();  
			try{m+=s1.split(".")[1].length}catch(e){}  
			try{m+=s2.split(".")[1].length}catch(e){}  
			return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)  
		}  
	   function ontotal(e){
			var row = e.row;
			
			var backNum = row.backNum;
			var unitPrice = row.unitPrice;
			var backPrice = 0;
			if(backNum==null){
				return;
			}else{
				var temp =parseFloat(unitPrice);
				var temp2 = parseInt(backNum);
				row.backPrice = accMul(temp,temp2);
				grid.updateRow(row,row);
			}
		}
	   
	   function saveGrid(){
		grid.validate ( );
	 	if(!grid.isValid ( )){
	 		return;
	 	}
	 	var griddata = grid.getChanges();
   		var gridjson = mini.encode(griddata);
	 	//alert(gridjson);
	 	
		$.ajax({
	  		type:"post",
	  		url:"BackProductSave",
	 	    data:{gridjson:gridjson},
	 	    dataType:"json",
	 		success: function(data){
	  			alert(data.result);
	  			grid.accept ( );
	  			grid.reload();
	  		}
	  	});
		
	}
	    grid.on("load", function () {
            grid.mergeColumns(["orderId"]);
        });
        
	    function loadgrid(){
	    	/*
	    	var form0 = new mini.Form("form0");
	   	 	form0.validate();
	      	 if (form0.isValid() == false) {
	      	 	alert("请选取具体时间段");
	      	 	return;}
	      	 */
	    	var bday = mini.get("bday").getFormValue();
	    	var eday = mini.get("eday").getFormValue();
	    	var creater = mini.get("creater").getValue();
	    	var status = mini.get("status").getValue();
	    	var customer = mini.get("customer").getValue();
	    	grid.load({bday:bday, eday:eday, creater:creater, status:status, customer:customer});
	    	//grid.mergeColumns ( ["orderId"] );
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
