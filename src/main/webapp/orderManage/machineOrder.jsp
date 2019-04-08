<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设备预约</title>
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
	<!-- ……………………………………………………………………………………………………………………………………………………………………………………………………………………………… -->
		<script src="<%=path %>/resource/timePlanJs/bootstrap.min.js?v=3.3.6"></script>
		<script src="<%=path %>/resource/timePlanJs/layer.js"></script>
		<script src="<%=path %>/resource/timePlanJs/template.js"></script>
		<script src="<%=path %>/resource/timePlanJs/moment-with-locales.js"></script>
		<script src="<%=path %>/resource/timePlanJs/laypage.js"></script>
		<script src="<%=path %>/resource/timePlanJs/jquery.qrcode.min.js"></script>
		
		<!--jquery validate begin-->
		<script src="<%=path %>/resource/timePlanJs/jquery.validate.min.js"></script>
		<script src="<%=path %>/resource/timePlanJs/messages_zh.min.js"></script>
		<script src="<%=path %>/resource/timePlanJs/jquery.validate.extend.js"></script>
		<!--jquery validate end-->
		
		<script src="<%=path %>/resource/timePlanJs/utils.js"></script>
	<!-- ……………………………………………………………………………………………………………………………………………………………………………………………………………………………… -->
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  
  
<!--   <div>
  	名称  <input id="companyname" name="companyname" class="mini-textbox" onenter="Search()"/>
  	<a class="mini-button" plain="fasle" onclick="Search()">查询</a></div> -->
  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			
   			<tr style= "height:20px;">
   				<td>用户名称</td>
				<td style= "width:200px;"> 
					<input id="customer" name ="customer" class="mini-textbox" width="100%"  allowInput="true" value =""/>
 				</td>
   				
 				<td style="display:none" >开始时间</td>
				<td style= "width:150px;display:none;" >
					<input id="bdate" name ="bdate" class="mini-datepicker" width="100%" emptyText="可不填"  dateFormat="yyyy-MM-dd" allowInput="true" value =""/>
 				</td>
 				<td style="display:none" >结束时间</td>
				<td style= "width:150px;display:none;">
					<input id="edate" name ="edate" class="mini-datepicker" width="100%" emptyText="可不填"  dateFormat="yyyy-MM-dd" allowInput="true" value =""/>
 				</td>
 				<td>
 					<input value="查询" type="button" style= "width:70px;" onclick="searchOrders()" /> 
 				</td>
   			</tr>
   			
   	 	</table>
   	 </form>
  	
  	
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="OrderListServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
            <!-- 
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
             -->
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
       <!--      <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div> -->
            <div field="orderId" width="110" headerAlign="center" align="center" >订单编号
            </div>
            <div field="companyName" width="100" headerAlign="center" align="center" >客户名称
            </div>
             <div field="orderName" width="50" headerAlign="center" align="center" >订单名称
            </div>
            <div field="connector" width="50" headerAlign="center" align="center" >联系人
            </div>
            <div field="connectorTel" width="60" headerAlign="center" align="center" >联系人电话
            </div>
            <!-- <div field="deptUser" width="50" headerAlign="center"  align="center" renderer="onDeptRenderer">使用部门
            	
            	
            </div> -->
            <div field="createTime" width="80" headerAlign="center" align="center"  dateFormat="yyyy-MM-dd HH:mm:ss">订单日期
            </div>
           <!--  <div field="endTime" width="80" headerAlign="center"  dateFormat="yyyy 年 MM 月 dd 日">交付日期
            </div> -->
            <div field="bookStatus" width="60" headerAlign="center" align="center" renderer="onGenderRenderer">预约状态
            
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    function searchOrders(){
			//小窗口的搜索函数，按日期、订单类型查找 订单
				/* var winGrid = mini.get("winGrid"); */
				var bdate =  mini.get("bdate").getFormValue();
	        	var edate =   mini.get("edate").getFormValue();
	        	var customer = mini.get("customer").getValue();
				grid.load({ bdate:bdate, edate:edate , customer:customer });  	
			}
	    
	    function onOperatePower(e) {
	        var str = "";
	      /*   str += "<span>";
	        str += "<a style='margin-right:2px;' title='设备预约' href=javascript:ondAdd(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-add'>&nbsp;</span></a>";
	        str += "</span>"; */
	      
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='预约设备' href=javascript:ondBook(\'" + e.row.orderId+"\',\'"+e.row.connector + "\',\'"+e.row.bookStatus + "\') ><span class='icon-add' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	      	str += "<span>";
	       
	      	
	      /* 	str += "<a style='margin-right:2px;' title='修改订单' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='icon-edit' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        str += "</span>"; */
	 /*        str += "<span>";
	        str += "<a style='margin-right:2px;' title='订单详情' href=javascript:onAllDetail(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-lock'>&nbsp;</span></a>";
	        str += "</span>"; 
	       alert(e.row.staffCode); */
	        return str;
	    }
	    
	    /* /*  str += "<span>";
	        str += "<a style='margin-right:2px;' title='订单报价' href=javascript:onQuotation(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-find'>&nbsp;</span></a>";
	        str += "</span>"; */  
	    
	    function ondBook(orderId,connector,bookStatus){
	        	if(bookStatus>12){
	        		 U.msg("预约信息已经锁定。无法再预约，请联系管理员进行操作！");
	        		return;
	        	}
	        	
	        	$.ajax({
                    type:"POST",
                    url:"isCanBook.action",
                    data: {},
                    dataType: "json",
                    success:function(data){
                  	  if(data.result=="true"){
                  			window.location="OrderSpecServlet?orderId=" + orderId+"&connector="+connector+"&isModify="+"3";
                  		  }else{
                  			U.msg("目前有订单逾期未上报，暂停预约功能！");
                  			return;
                  		  }
                    } 
                });
	    	
		}
	        function ondEdit(orderId,connector){
		        //window.open("EditOrderDetailServlet?orderId=" + orderId,
		        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
		    	window.location="OrderSpecServlet?orderId=" + orderId+"&connector="+connector+"&isModify="+"3";
			}
	   /*      function ondDelete(orderId,connector){
		        //window.open("EditOrderDetailServlet?orderId=" + orderId,
		        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
		    	window.location="OrderSpecServlet?orderId=" + orderId+"&connector="+connector+"&isModify="+"3";
			} */

	    /* function ondSee(orderId,connector){
	    
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	//window.location="ShowOrderAllServlet?orderId=" + orderId+"&connector="+connector;
	    	window.location="GoCheckOrderDetailListServlet?orderId="+orderId+"&para=orderList";
		} */

	   /*  function ondAdd(orderId,connector){
	        //window.location="orderManage/AddOrderDetail.jsp?orderId=" + orderId+"&connector="+connector;
	    	window.location="orderManage/AddOrderDetailMain.jsp?orderId=" + orderId+"&connector="+connector+"&isModify="+"1";
		} */

		function onQuotation(orderId,connector){
			window.location="orderManage/QuotationMainIndex.jsp?orderId=" + orderId+"&connector="+connector;
		}
		/* 
		function onAllDetail(orderId,connector){
			window.location="GoOrderAllDetailServlet?orderId=" + orderId+"&connector="+connector+"&isModify="+"1";
		} */
		
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
/*         
        var orderGenders=[{id: "1", text: "待预约 "},{id: "2", text: "待审核"},{id: "3", text: "提交上级审核"},{id: "4", text: "审核不通过"},{id: "5", text: "审核通过"},	
	                {id: "6", text: "备料"},{id: "7", text: "代加工"},{id: "8", text: "加工中"},{id: "9", text: "完成"},{id: "10", text: "交付中"},{id: "11", text: "交付完成"}] */
        
        var bookGenders=[{id: "11", text: "新建订单待预约"},{id: "12", text: "预约待审核"},{id: "13", text: "预约审核通过"},{id: "14", text: "预约审核不通过"},{id: "15", text: "上报完成"},{id: "16", text: "订单完结"}]
  
        
//	    var Genders = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = bookGenders.length; i < l; i++) {
                var g = bookGenders[i];
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

