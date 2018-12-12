<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户信息</title>
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
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="ShowOrderInfoServlet">
        <div property="columns">
            <div type="checkcolumn"></div>
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            
            <div header="产品信息">
            	<div property="columns">
	            	<div field="productID" width="60" headerAlign="center">产品名称</div>
	            	<div field="drawingID" width="100" headerAlign="center">零件代号或图号</div>
	            	<div field="isfirst" width="60" headerAlign="center">是否首件</div>
            	</div>
            </div>
            <div header="材料费">
            	<div property="columns">
	            	<div field="meterialType" width="90" headerAlign="center">材料材质型号</div>
	            	<div field="meterialweight" width="90" headerAlign="center">材料重量(kg)</div>
	            	<div field="meterialUnitPrice" width="60" headerAlign="center">材料单价</div>
	            	<div field="biaozhunjianPrice" width="60" headerAlign="center">标准件费</div>
	            	<div field="caigouPrice" width="50" headerAlign="center">采购费</div>
	            	<div field="MeterialSum" width="70" headerAlign="center">材料费合计</div>
            	</div>
            </div>
            
            <div header="加工费">
            	<div property="columns">
            		<div field="jijiagongfeiSum" width="90" headerAlign="center">机加工费合计</div>
            	</div>
            </div>
            <div header="材料处理费">
            	<div property="columns">
	            	<div field="rechuliUnitPrice" width="80" headerAlign="center">热处理单价</div>
	            	<div field="rechuliSumPrice" width="80" headerAlign="center">材料热处理费</div>
	            	<div field="biaomianchulimianji" width="80" headerAlign="center">表面处理面积</div>
	            	<div field="biaomianchuligeshu" width="80" headerAlign="center">表面处理个数</div>
	            	<div field="biaomianchuliUnitPrice" width="80" headerAlign="center">表面处理单价</div>
	            	<div field="biaomianchuliSumPrice" width="90" headerAlign="center">表面处理费合计</div>
	            	<div field="yunshufeiUnitPrice" width="100" headerAlign="center">运输费①（/公里加过路费）</div>
	            	<div field="yunshufeiSumPrice" width="80" headerAlign="center">运输费用总计</div>
            	</div>
            </div>
            <div header="服务费">
            	<div property="columns">
	            	<div field="yanfafei" width="40" headerAlign="center">研发费</div>
	            	<div field="shejifei" width="40" headerAlign="center">设计费</div>
	            	<div field="errorUnitPrice" width="100" headerAlign="center">每处错误或不清楚10元/处</div>
	            	<div field="errorNum" width="40" headerAlign="center">个数</div>
	            	<div field="shentufei" width="50" headerAlign="center">审图费</div>
	            	<div field="gongbuUnitPrice" width="50" headerAlign="center">工步数</div>
	            	<div field="gongbutushuUnitPrice" width="50" headerAlign="center">工步图数</div>
	            	<div field="fuzagongchengxishuUnitPrice" width="80" headerAlign="center">复杂程度系数</div>
	            	<div field="gongyifei" width="50" headerAlign="center">工艺费</div>
	            	<div field="qitafuwufei" width="70" headerAlign="center">其它服务费</div>
            	</div>
            </div>
            <div header="其他">
            	<div property="columns">
	            	<div field="shoujianshizhifei" width="70" headerAlign="center">首件试制费</div>
	            	<div field="gongzhuangjiajufei" width="70" headerAlign="center">工装夹具费</div>
	            	<div field="teshujiajufujufei" width="80" headerAlign="center">特殊刀具辅具</div>
	            	<div field="" width="50" headerAlign="center">包装费</div>
	            	<div field="" width="50" headerAlign="center">单套运费</div>
	            	<div field="guoqiaofei" width="70" headerAlign="center">过桥过路费</div>
	            	<div field="" width="80" headerAlign="center">运输费②总计</div>
	            	<div field="jiajiFirstDay" width="100" headerAlign="center">加急费第一天（0.3）</div>
	            	<div field="jiajiSecondDay" width="100" headerAlign="center">加急费第二天（0.2）</div>
	            	<div field="jiajiThirdDay" width="100" headerAlign="center">加急费第三天（0.1）</div>
	            	<div field="jiajiSumPrice" width="50" headerAlign="center">加急费</div>
	            	<div field="" width="80" headerAlign="center">前述合计费用</div>
	            	<div field="guanlifei" width="80" headerAlign="center">管理费（8%）</div>
	            	<div field="NoshuiUnitPrice" width="100" headerAlign="center">不含税单价（元）</div>
	            	<div field="WithshuiUnitPrice" width="100" headerAlign="center">不含税单价（元）</div>
            	</div>
            </div>
            <div field="productNum" width="50" headerAlign="center">数量</div>
            <div field="SumPrice" width="50" headerAlign="center">合计</div>
            <div field="jiaohuoqi" width="80" headerAlign="center">交货期（天）</div>
            <div field="jiaohuidate" width="60" headerAlign="center">交货日期</div>
            
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    //window.setInterval(function(){grid.load()},5000);
	    //alert("dddd");
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(orderId,connector){
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	window.location="EditOrderDetailServlet?orderId=" + orderId+"&connector="+connector;
		}
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	    var Genders = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }

        
    </script>
  </body>
</html>
