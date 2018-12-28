<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
   <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <meta http-equiv="content-Type" content="text/html;charset=utf-8"/>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		
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
		
    
    <style type="text/css">
        .New_Button, .Edit_Button, .Delete_Button, .Update_Button, .Cancel_Button
        {
            font-size:11px;color:#1B3F91;font-family:Verdana;  
            margin-right:5px;          
        }
       
                
    </style>        
</head>
<body>
    

    <div id="bookOrder" class="mini-datagrid" style="width:100%;height:350px;" url="mybookOrder.action"  idField="id" onselectionchanged="onSelectionChanged"  selectOnLoad="true">
        <div property="columns">            
           <!--  <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">预约设备
            </div> -->
            <div field="orderId" width="110" headerAlign="center" align="center">订单编号
            </div>
            <div field="companyName" width="100" headerAlign="center" align="center">客户名称
            </div>
            <div field="connector" width="50" headerAlign="center" align="center">联系人
            </div>
            <div field="connectorTel" width="60" headerAlign="center" align="center" >联系人电话
            </div>
             
            <div field="bookStatus" width="60" headerAlign="center" align="center" renderer="onGenderRenderer">预约状态
            
            </div>
            <div field="option" width="60" headerAlign="center" align="center" renderer="onOperatePower1">操作
            
            </div>
           
            
                     
        </div>
    </div> 
  
    <br />
    <div id="bookMachine" class="mini-datagrid" style="width:100%;height:430px;" url="myBookOrderMachine.action">
        <div property="columns">            
       		<div field="unid" width="120" headerAlign="center" allowSort="true" align="center" headerAlign="center">预约编号</div> 
            <div field="machineName" width="120" headerAlign="center" allowSort="true" align="center" headerAlign="center">设备名称</div> 
            <div field="timeYmd" width="100" allowSort="true" align="center" headerAlign="center" align="center">日期</div>               
            <div field="startTimeInfo" width="100" allowSort="true"  align="center" headerAlign="center">开始时间</div>            
            <div field="endTimeInfo" width="100" allowSort="true" align="center" headerAlign="center">结束时间</div>
                                                
            <div field="option" width="100" headerAlign="center" allowSort="true" align="center" renderer="onOperatePower" >操作</div>                
        
    </div>      
     </div>  
   
    

    <script type="text/javascript">
        var Genders = [{ id: 1, text: '男' }, { id: 2, text: '女'}];
		var orderId="";
        mini.parse();

        var bookOrder = mini.get("bookOrder");
        var bookMachine = mini.get("bookMachine");
        
        bookOrder.load();
/* 
        var menu = new ColumnsMenu(bookOrder);
        var menu2 = new ColumnsMenu(bookMachine); */
        ///////////////////////////////////////////////////////       
        
        
         
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='删除' href=javascript:bookDelete(\'" + e.row.unid+"\',\'"+e.row.orderId+ "\')>删除</a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
        function onOperatePower1(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='删除' href=javascript:orderDelete(\'" + e.row.unid+"\',\'"+e.row.orderId+ "\')>删除</a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
        
        function orderDelete(unid,orderId){
        	layer.confirm('注意：订单删除的同时，预约信息也会一起删除！您确定要删除订单吗？', {
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                	$.ajax({
                        type:"POST",
                        url:"AuditingOrderDelete.action",
                       
                        
                        data: {orderId:orderId},
                        dataType: "json",
                        success:function(data){
                      	 // alert(data.result);
                      	  U.msg(data.result);
                      	//onSelectionChanged();
                      	bookOrder.load();
   			        // bookMachine.load({ orderId:orderId});
                      	
                        } ,
                        error:function(data){
                         U.msg(data.result);
                        } 
                    });}
               	,
                btn2: function (index, layero) {
                    layer.close(index);
                }
            })
    	   }
        
        function bookDelete(unid,productId){
        	layer.confirm('您确定要删除预定吗？', {
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                	$.ajax({
                        type:"POST",
                        url:"AuditingBookOrderDelete.action",
                       
                        
                        data: {unid:unid},
                        dataType: "json",
                        success:function(data){
                      	 // alert(data.result);
                      	  U.msg(data.result);
                      	//onSelectionChanged();
   			         bookMachine.load({ orderId:orderId});
                      	
                        } ,
                        error:function(data){
                         U.msg(data.result);
                        } 
                    });}
               	,
                btn2: function (index, layero) {
                    layer.close(index);
                }
            })
    	   }
        
     var bookGenders=[{id: "11", text: "新建"},{id: "12", text: "待审核"},{id: "13", text: "审核通过"},{id: "14", text: "审核不通过"},{id: "15", text: "加工完成"},{id: "16", text: "已完成"}]
           function onGenderRenderer(e) {
            for (var i = 0, l = bookGenders.length; i < l; i++) {
                var g = bookGenders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
      


        function onSelectionChanged(e) {
        
            var grid = e.sender;
            var record = grid.getSelected();
            orderId=record.orderId;
            if (record) {
                bookMachine.load({ orderId: record.orderId });
            }
        }

        
    </script>



</body>
</html>

