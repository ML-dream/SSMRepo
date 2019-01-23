<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
   <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/resources/scripts/boot.js"></script>
    <meta http-equiv="content-Type" content="text/html;charset=utf-8"/>
		<script type="text/javascript" src="<%=path %>/resources/scripts/jquery.min.js"></script>
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
    
	<!-- <div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('13')">审核通过</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('14')">审核不通过</a>	
		<span class="separator"></span>
		
  			<a class="mini-button" iconCls="icon-find" plain="false"  onclick="bookModify()">修改用户预约</a>  
    </div> -->
    
    
      	<form id="form0">
	    <table >
	   		<tr>
	         <!--  <td>开始时间：</td>
	          <td><input id="bday" class="mini-datepicker" width="100"  allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td>
	          <td>结束时间：</td>
	          <td><input id="eday" class="mini-datepicker"   width="100" allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td> -->
	   <!--        <td width="60px" align="right">接收人：</td>
	          <td><input id="creater" name="creater" class="mini-buttonedit" width="100" showClose="true" oncloseclick="onCloseClick('creater')"
            		onbuttonclick="onButtonEdit" textName="worker" required="false" value="" text="" onvaluechanged="loadgrid"  allowInput="false"/>
	          </td> -->
	          
	       </tr>
	       <tr>
	          <td>订单状态：</td>
	          <td><input id="bookStatus" name="bookStatus" class="mini-combobox" width="100" textName="" textField="text" valueField="id"
  				url="data/bookStatus.txt"  allowInput="false" showNullItem="true" nullItemText="请选择..."  onvaluechanged="loadgrid"/>
	          </td>
	   		<!--   <td align="right">客户：</td>
	          <td colspan="2"><input id="companyName" name="companyId" class="mini-buttonedit" width="100%" showClose="true" oncloseclick="onCloseClick('customer')"
            		onbuttonclick="onButtonEdit2" textName="companyName" required="false" value="" text="" onvaluechanged="loadgrid"  allowInput="false"/>
	          </td>
	          <td><input value="查找" type="button" onclick="loadgrid()" style="width:50px;"/></td> -->
	   		</tr>
	   	</table>
	   </form>
    
    
    
    <div id="bookOrder" class="mini-datagrid" style="width:100%;height:350px;" url="mybookOrder.action"  idField="id" onselectionchanged="onSelectionChanged"  selectOnLoad="true">
        <div property="columns">            
           <!--  <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">预约设备
            </div> -->
            <div field="orderId" width="110" headerAlign="center" allowSort="true" align="center">订单编号
            </div>
            <div field="orderName" width="110" headerAlign="center" align="center">订单名称
            </div>
            <div field="companyName" width="100" headerAlign="center" align="center">客户名称
            </div>
            <div field="connector" width="50" headerAlign="center" align="center">联系人
            </div>
            <div field="connectorTel" width="60" headerAlign="center" align="center" >联系人电话
            </div>
            
            <div field="createTime" width="60" headerAlign="center" align="center"  dateFormat="yyyy-MM-dd HH:mm:ss" >创建时间
            </div>
             
            <div field="bookStatus" width="60" headerAlign="center" align="center" renderer="onGenderRenderer">预约状态
            </div>
             <div field="checkAdvice" width="60" headerAlign="center" align="center" >审核意见
            </div>
            <div field="option" width="60" headerAlign="center" align="center" renderer="onOperatePower1">操作
            
            </div>
           
        </div>
    </div> 
  
    <br />
    <div id="bookMachine" class="mini-datagrid" style="width:100%;height:430px;" url="myBookOrderMachine.action">
        <div property="columns">            
       		<div field="unid" width="120" headerAlign="center" allowSort="true"  allowSort="true" align="center" headerAlign="center">预约编号</div> 
       		<div field="bookStatus" width="120" headerAlign="center"  align="center" headerAlign="center" renderer="onGenderRenderer">预约状态</div> 
            <div field="machineName" width="120" headerAlign="center"  align="center" headerAlign="center">设备名称</div> 
            <div field="timeYmd" width="100" allowSort="true" align="center"  headerAlign="center" align="center">日期</div>               
            <div field="startTimeInfo" width="100"   align="center" headerAlign="center">开始时间</div>            
            <div field="endTimeInfo" width="100"  align="center" headerAlign="center">结束时间</div>
                                                
            <div field="option" width="100" headerAlign="center"  align="center" renderer="onOperatePower" >操作</div>                
        
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
        /*
        
        bookOrder.load(data1,function(e){
		    if(e.data.length==0){
		         bookMachine.load()   传空参数，后台判断，如果没参数，那么就返回空数据
		    }
		})
        
        */
         
	    function loadgrid(){
	    	
	    	var form=new mini.Form("#form0");
	    	var data1 =form.getData();
	    	//alert(data1);
	    	/*  var bookStatus = mini.get("bookStatus").getValue();
	    	 var companyName = mini.get("companyName").getValue(); */
	    //	var data=mini.encode(data1);
	    	
	    	bookOrder.load(data1);
	    	/* $.ajax({
	    		url:"AuditingBookingOrder.action",
	    		type:"post",
	    		data:data,
	    		success:function(text){
	    			
	    			
	    		}
	    	}); */
	    }
         
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='取消' href=javascript:bookDelete(\'" + e.row.unid+"\',\'"+e.row.orderId+ "\',\'"+e.row.bookStatus+ "\')>取消</a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
        function onOperatePower1(e) {
        	/*  var grid = e.sender;
             var record = e.record;
             var uid = record._uid;
             var rowIndex = e.rowIndex; */
           
 	        
 	        
	        var str = "";
	        str += "<span>";
	        str += "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='取消订单' href=javascript:orderDelete(\'" + e.row._uid+"\',\'"+e.row.orderId+ "\',\'"+e.row.bookStatus+ "\')>取消</a>";
	        str += "</span>";
	        
	        str += "&nbsp;&nbsp;";
 	        str += "<span>";
 	        str += "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='图形界面查看预约信息' href=javascript:seeBooking(\'" + e.row._uid+"\',\'"+e.row.orderId+ "\',\'"+e.row.bookStatus+ "\')>查看</a>";
 	        str += "</span>";
	        
	        str += "&nbsp;&nbsp;";
	        str += "<span>";
	        str += "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='打印时刻表' href=javascript:orderPrint(\'" + e.row._uid+"\',\'"+e.row.orderId+ "\')>打印</a>";
	        str += "</span>";
	        
	        str += "&nbsp;&nbsp;";
	        str += "<span>";
	        str += "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='修改预约时段' href=javascript:modifyBooking(\'" + e.row._uid+"\',\'"+e.row.orderId+ "\',\'"+e.row.bookStatus+ "\',\'"+e.row.connector+ "\')>修改</a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
        /* 查看预约信息 */
        function seeBooking(unid,orderId,bookStatus){
           
        	window.location="orderManage/seeOrderBooking.jsp?orderId=" +orderId;
    	   }
        
        function modifyBooking(unid,orderId,bookStatus,connector){
            /* alert(connector);  */
           	if(bookStatus==13){
           		 U.msg("审核已通过，请联系管理员进行操作！");
           		return;
           		
           	}
           	if(bookStatus==14){
           	 U.msg("该订单已无效，请联系管理员进行操作！");
        		return;
           		
           	}
        	if(bookStatus>14){
              	 U.msg("该订单目前无法操作，请联系管理员进行操作！");
           		return;
              	}
        	window.location="OrderSpecServlet?orderId=" +orderId +"&connector="+connector+"&isModify="+"3";
    	   }
        
        
        function orderPrint(unid,orderId){
     		
        	var row = bookOrder.getRowByUID(unid);
        	//alert(mini.encode(row));
        	/* var partsplanid = mini.get("partsPlanId").getValue(); */
			window.open("ResearchTask/printBarcodeBookOrder.jsp?orderId=" + row.orderId+"&companyName=" + row.companyName+"&connector=" + row.connector+"&connectorTel=" +row.connectorTel+"&createTime=" +row.createTime+"&bookStatus=" +row.bookStatus,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
			/*
			$.ajax({
        	type: "post",
            url: "PartsplanBarcode?partsplanid=" + partsplanid,
            cache: false,
            success: function (text) {
            	window.confirm(text);
            }
       });
       */
		}

        
        function orderDelete(unid,orderId,bookStatus){
           	/* alert(bookStatus); */
           	if(bookStatus==13){
           		 U.msg("审核已通过，请联系管理员进行操作！");
           		return;
           		
           	}
           	if(bookStatus==14){
           	 U.msg("该订单已无效，请联系管理员进行操作！");
        		return;
           		
           	}
        	if(bookStatus>14){
              	 U.msg("该订单目前无法操作，请联系管理员进行操作！");
           		return;
              	}
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
        
        function bookDelete(unid,productId,bookStatus){
        	/* alert(bookStatus); */
        	if(bookStatus==13){
          		 U.msg("审核已通过，请联系管理员进行操作！");
          		return;
          	}
          	if(bookStatus==14){
          	 U.msg("该订单已无效，请联系管理员进行操作！");
       		return;
          		
          	}
       		if(bookStatus>14){
             	 U.msg("该订单目前无法操作，请联系管理员进行操作！");
          		return;
             	}
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
                bookMachine.load({ orderId: record.orderId,bookStatus:record.bookStatus });
            }
        }

        
    </script>



</body>
</html>

