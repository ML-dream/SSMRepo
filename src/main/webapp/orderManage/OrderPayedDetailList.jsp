<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单交付</title>
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
  </head>
  
  <body>
  	<div class="mini-toolbar" style="padding:2px;border:0;">
    	<!--<a class="mini-button" iconCls="icon-save" plain="false" onclick="save()">保存</a>-->
		
		<span class="separator"></span>
    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()">刷新</a>
    	<span class="separator"></span>
    	<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
    </div> 
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="OrderPayedDetailListServlet?orderId=${orderId}&productId=${productId}&fproductId=${fproductId}">
        <div property="columns">
            <div type="indexcolumn"></div>
            <!--
            <div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            -->
            <div field="orderId" width="100" headerAlign="center">订单编号
            </div>
            <div field="productId"  width="100" headerAlign="center">产品编号
            </div>
            <div field="productName" name="productName" width="100" headerAlign="center">产品名称
            </div>
            <div field="FProductId" name="productName" width="100" headerAlign="center">父产品号
            </div>
            <div field="payedNum" width="50" headerAlign="center">交付数量
            </div>
            <div field="payedTime" width="100" headerAlign="center"  dateFormat="yyyy 年 MM 月 dd 日">交付日期
            </div>
            <div field="payedPerson" name="productName" width="50" headerAlign="center">交付人
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function back(){
			window.history.back(-1);
		}
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='交付明细' href=javascript:ondDetail(\'" + e.row.orderId+"\',\'"+e.row.productId + "\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='保存' href=javascript:onSave(\'" + e.row.orderId+"\',\'"+e.row.productId + "\',\'"+e.row.orderStatus + "\') ><span class='mini-button-text mini-button-icon icon-save'>&nbsp;</span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='订单交付' href=javascript:onOrderPay(\'" + e.row.orderId+"\',\'"+e.row.productId + "\',\'"+e.row.fproductId + "\') ><span class='mini-button-text mini-button-icon icon-tip'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function refresh(){
			grid.reload();
		}
		
		function onSave(orderId,productId,orderStatus){
			if(grid.isChanged()){		//如果修改过了
				var data = "{\"orderId\":\""+orderId+"\",\"productId\":\""+productId+"\",\"orderStatus\":\""+orderStatus+"\"}";
				var params = JSON.parse(data);
				url = "ChangeProductStatusServlet";
				jQuery.post(url, params, function(data){
   	   				alert(data.result);
   	   				window.location.href=window.location.href;
   	   			},'json');
			}else{
				mini.alert("订单状态未修改！");
				return;
			}
		}
	    
	    function onOrderPay(orderId,productId,fproductId){
	        window.location="orderManage/OrderPayedSpec.jsp?orderId=" + orderId+"&productId="+productId+"&fproductId="+fproductId;
		}

	    function ondSee(orderId,connector){
	        window.location="GoOrderDetailListServlet?orderId="+orderId;
		}

	    function onDetail(orderId,connector){
	        window.location="orderManage/AddOrderDetailMain.jsp?orderId=" + orderId+"&connector="+connector;
		}

		function onQuotation(orderId,connector){
			window.location="orderManage/QuotationMainIndex.jsp?orderId=" + orderId+"&connector="+connector;
		}
		
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	    var Genders = [{id: '0', text: '新建'},{id: '1', text: '代加工'},{id: '2', text: '正在加工'},{id: '3', text: '完工'},{id: '4', text: '已交付'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        /*
        //var status = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"},{id: "6", text: "交付"}];
        var status = new Object([{id: '0', text: '新建'},{id: '1', text: '代加工'},{id: '2', text: '正在加工'},{id: '3', text: '完工'},{id: '4', text: '已交付'}]);
        //status = JSON.parse(status);
        function onStatusRenderer(e){
        	for (var i = 0, l = status.length; i < l; i++) {
                var g = status[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        */

    </script>
  </body>
</html>
