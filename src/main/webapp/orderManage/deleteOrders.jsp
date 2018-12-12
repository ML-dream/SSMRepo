<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单撤销</title>
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
  	<div id="win1" class="mini-window" title="操作" style="width:600px;height:100px;" collapseOnTitleClick="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" expanded="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    	<form id="form0">
	    <table >
	   		<tr>
	   			<td>订单号</td>
	          <td><input id="orderId" class="mini-textbox" value="" allowinput="true" onvaluechanged="onValueChanged" />
	          </td>
	          <td>开始时间</td>
	          <td><input id="bday" class="mini-datepicker" width="100"  allowinput="false"  format="yyyy-MM-dd" required="true"/>
	          </td>
	          <td>结束时间</td>
	          <td><input id="eday" class="mini-datepicker"  width="100" allowinput="false"  format="yyyy-MM-dd" required="true"/>
	          </td>
	          
	   		</tr>
	   		<tr>
	   			<td><input value="查找" type="button" onclick="loadgrid()" style="width:50px;"/></td>
	   			<td><input value="撤销订单" type="button" onclick="deleteOrder()" style="width:60px;"/></td>
	   		</tr>
	   	</table>
	   </form>
	</div>
	</br></br></br></br>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:80%;" allowResize="true" multiSelect="false"
        idField="id" showSummaryRow="false" allowAlternating="true" showPager="true" allowUnselect="true"
         url="OrderForDelete">
        <div property="columns">
             <div type="checkcolumn" ></div>
            <div name="action" width="70" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="orderId" width="110" headerAlign="center">订单编号
            </div>
            <div field="companyName" width="100" headerAlign="center">客户名称
            </div>
            <div field="connector" width="50" headerAlign="center">联系人
            </div>
            <div field="connectorTel" width="60" headerAlign="center">联系人电话
            </div>
            <div field="deptUser" width="50" headerAlign="center" renderer="onDeptRenderer">使用部门
            	
            </div>
            <div field="orderDate" width="80" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">订单日期
            </div>
            <div field="endTime" width="80" headerAlign="center"  dateFormat="yyyy 年 MM 月 dd 日">交付日期
            </div>
            <div field="orderStatus" width="60" headerAlign="center" renderer="onGenderRenderer">订单状态
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    //grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='订单详情' href=javascript:onAllDetail(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-lock'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
		function onAllDetail(orderId,connector){
			window.location="<%=basePath%>ToDeleteProduct?orderId=" + orderId+"&connector="+connector;
		}
        
        var Genders=[{id: "1", text: "新建"},{id: "2", text: "待审核"},{id: "3", text: "提交上级审核"},{id: "4", text: "审核不通过"},{id: "5", text: "审核通过"},	
	                {id: "6", text: "备料"},{id: "7", text: "代加工"},{id: "8", text: "加工中"},{id: "9", text: "完成"},{id: "10", text: "交付中"},{id: "11", text: "交付完成"}]
//	    var Genders = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"}];
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
	function loadgrid(){
		var orderId = mini.get("orderId").getValue();
		var bday = mini.get("bday").getFormValue();
		var eday = mini.get("eday").getFormValue();
		var form0 = new mini.Form("form0");
   	 	form0.validate();
      	 if (form0.isValid() == false) {return;} 
		grid.load({orderId:orderId,bday:bday,eday:eday});
	}
	function deleteOrder(){
		var orderId = "";
		var row = grid.getSelected();
		if(row){
			orderId = row.orderId;
		}else{
			alert("请选择具体订单");
			return;
		}
		var temp = confirm ("删除订单"+orderId+",不可撤销,是否继续?");
		if(temp){
			//var json = mini.encode(row);
			$.ajax({
	       		type: "post",
	       		url:"DeleteOrder?orderId="+orderId,
	       		cache: false,
	   			async:false,
	   			//data:{"data" : json},
	   			success:function(text){
	   				var data = mini.decode(text);
	   				alert(data.result);
	   				grid.reload();
	   			}
	   		})
		}else{
			return;
		}
	}
    </script>
  </body>
</html>
