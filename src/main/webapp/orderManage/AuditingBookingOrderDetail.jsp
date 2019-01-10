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
    
       <script src="<%=path %>/resource/timePlanJs/jquery.min.js"></script>




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
    
    
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  
  	<div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('13')">审核通过</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('14')">审核不通过</a>	
		<span class="separator"></span>
		
  			<a class="mini-button" iconCls="icon-find" plain="false"  onclick="bookModify()">修改用户预约</a>  
    </div>
  	<fieldset style="width: 100%;" align="center">
		<legend>
			订单信息
		</legend>
	
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr >
   			<td width="10%"><label for="orderId$text">订单编号</label></td>
            <td width="25%"><input id="orderId"  name="orderId" class="mini-textbox"  width="100%"  value="<%=request.getParameter("orderId")%>" enabled="false"/></td>
            <td width="10%"><label for="companyName$text">订单客户</label></td>
            <td width="25%"><input id="companyName" name="comapnyname" class="mini-textbox"  width="100%"  value="<%=request.getParameter("companyName")%>" enabled="false"/>
   			<td width="10%"><label for="createTime$text">创建时间</label></td>
            <td width="20%"><input id="createTime"  name="deptUser" class="mini-textbox"  width="100%"  value="<%=request.getParameter("deptUser")%>" enabled="false" />  
            </td>
        </tr>
        <tr>
       	    <td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox"  width="100%"  enabled="false" value="<%=request.getParameter("connector")%>"/></td>
            <td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%"  enabled="false" value="<%=request.getParameter("connectorTel")%>"/></td>
            <td><label for="bookStatus$text">订单状态</label></td>
            <td><input id="bookStatus"  name="bookStatus" class="bookStatus" style="width:100%;"    enabled="false" readonly="readonly" value="" />
           
        </tr>
         <tr>
       	 <td><label for="bookStatus$text"></label>审核人</td>
            <td><input id="auditingStaffCode"  name="auditingStaffCode" class="auditingStaffCode" style="width:100%;"    enabled="false" readonly="readonly" value="" />
    	    </td>
    	     </tr>
   		<tr height="60px;">
   			<td><label for="checkAdvice$text">审核意见</label></td>
	        <td colspan="5"><input id="checkAdvice" name="checkAdvice" class="mini-textarea" emptyText="请输入审核意见" width="100%" height="100%"/></td>
       </tr>
   	</table>
   	  </fieldset>
   
        <div id="bookMachine" class="mini-datagrid" style="width:100%;height:500px;" url="myBookOrderMachine.action?orderId=<%=request.getParameter("orderId")%>">
        <div property="columns">            
        	<div field="unid" width="120" headerAlign="center" allowSort="true" align="center" headerAlign="center">预约编号</div> 
            <div field="machineName" width="120" headerAlign="center" allowSort="true" align="center" headerAlign="center">设备名称</div> 
            <div field="timeYmd" width="100" allowSort="true" align="center" headerAlign="center" align="center">日期</div>               
            <div field="startTimeInfo" width="100" allowSort="true"  align="center" headerAlign="center">开始时间</div>            
            <div field="endTimeInfo" width="100" allowSort="true" align="center" headerAlign="center">结束时间</div>
                                                
            <div field="bookState" width="100" headerAlign="center" allowSort="true" align="center" renderer="onOperatePower">编辑</div>                
        
    </div> 
    </div>

  
    
    <script type="text/javascript">
    
    	mini.parse();
	    var grid = mini.get("bookMachine");
	    var orderId="<%=request.getParameter("orderId")%>";
	    grid.load();
	    updateBookOrderStatus();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='删除' href=javascript:bookDelete(\'" + e.row.unid+"\',\'"+e.row.orderId+ "\')>删除</a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function bookDelete(unid,productId){
	    	
	    //	alert(unid);
	    	 $.ajax({
                 type:"POST",
                 url:"AuditingBookOrderDelete.action",
                 
                 data: {unid:unid},
                 dataType: "json",
                 success:function(data){
               	 // alert(data.result);
               	  U.msg(data.result);
               		grid.load();
                 } ,
                 error:function(data){
                  U.msg(data.result);
                 } 
             });
        	
	    	
	    	
	    }
	    
	    
	    
	    
	    function ondEdit(orderId,productId){
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	window.location="OrderDetailSpecServlet?orderId=" + orderId+"&productId="+productId;
		}
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	   // var Genders = [{id: "11", text: "新建"},{id: "12", text: "备料"},{id: "13", text: "代加工"},{id: "14", text: "加工中"},{id: "15", text: "完成"}];
	    
	    var Genders=[{id: "11", text: "新建"},{id: "12", text: "待审核"},{id: "13", text: "审核通过"},{id: "14", text: "审核不通过"},{id: "15", text: "加工中"},{id: "16", text: "加工完成"},{id: "16", text: "订单完结"}]
	      
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        
        
        
//        var checkAdvice=document.getElementById('checkAdvice').value;
         function pass(bookStatus){
        	 var checkAdvice=mini.get("checkAdvice").getValue();
        	 alert(checkAdvice);
         	 $.ajax({
                  type:"POST",
                  url:"AuditingBookOrderAll.action",
                 
                  
                  data: {bookStatus:bookStatus,orderId:orderId,checkAdvice:checkAdvice},
                  dataType: "json",
                  success:function(data){
                	//  alert(data.result);
                	  U.msg(data.result);
                	  updateBookOrderStatus();
           
                  },
                  error:function(data){
                   alert(data.result);
                  } 
              });
         	
         	}
         
         function bookModify(){
    			var orderId="<%=request.getParameter("orderId")%>";
    			var connector="<%=request.getParameter("connector")%>";
    			
 	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
 	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
 	    	window.location="OrderSpecServlet?orderId=" +orderId +"&connector="+connector+"&isModify="+"3";
 		}
        /* 这个方法是用来动态更新订单的状态值的 */ 
         
         function updateBookOrderStatus(){
         	 $.ajax({
                  type:"POST",
                  url:"AuditingBookOrderUpdateStatus.action",
                 
                  
                  data: {orderId:orderId},
                  dataType: "json",
                  success:function(data){
                	 //alert(data.bookStatus);
                	  //U.msg(data.result);
                	  
                	  document.getElementById('bookStatus').value=data.bookStatusName;
                	  document.getElementById('auditingStaffCode').value=data.staffName;
                	  
           
                  },
                  error:function(data){
                   alert(data.result);
                  } 
              });
         	
         	}
         
        
        /* function pass(outAssistStatus){
        	var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	//data.leave=leave;
            	data.outAssistStatus=outAssistStatus;
                var params = eval("("+mini.encode(data)+")");
                var url = 'OutAssistCheckServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
		} */
		
		function callbackFun(data)
   	    {
   	         alert(data.result);
   	      	 window.location.href = window.location.href;
   	    }

        
    </script>
  </body>
</html>
