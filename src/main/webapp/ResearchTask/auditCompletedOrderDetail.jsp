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
/* 	table {
	    table-layout:fixed;
	    word-break: break-all;
	}  */
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
<script src="<%=path %>/resource/timePlanJs/jquery.min.js"></script>
<script src="resources/scripts/boot.js" type="text/javascript"></script>
<script src="resources/demo/datagrid/js/ColumnsMenu.js" type="text/javascript"></script>

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
	
 <style>
    .labelTd
    {
        text-align:center;
        text-indent:5px;
        }
    </style>
	
  </head>
  
  <body>
  
   <input id="key" class="mini-textbox" emptyText="请输入订单号" style="width:250px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">查询</a>
                     
                        <div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('16')">完工审核通过</a>
		<span class="separator"></span> 
		<span class="separator"></span> 
		<span class="separator"></span> 
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('13')">重新修改</a>
    </div>
<div id="form1">
   	  <fieldset style="width: 100%;" align="center">
		<legend>
			订单信息
		</legend>
	
   		<table  style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr >
   			<td width="10%" class="labelTd"><label for="orderId$text">订单编号</label></td>
            <td width="25%" ><input id="orderId"  name="orderId" class="mini-textbox"  width="100%"   value="" enabled="false" borderStyle="border:0"/></td>
            <td width="10%" class="labelTd"><label for="companyName$text">订单客户</label></td>
            <td width="25%"><input id="companyName" name="companyName" class="mini-textbox"  width="100%"  value="" enabled="false" borderStyle="border:0"/>
   			<td width="10%" class="labelTd"><label for="createTime$text">创建时间</label></td>
            <td width="20%"><input id="createTime"  name="createTime" class="mini-textbox"  width="100%" value="" dateFormat="yyyy-MM-dd HH:mm:ss" enabled="false"  borderStyle="border:0"/>  
            </td>
        </tr>
        <tr>
       	    <td class="labelTd"><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox"  width="100%"  enabled="false" value=""  borderStyle="border:0"/></td>
            <td class="labelTd"><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%"  enabled="false" value=""  borderStyle="border:0"/></td>
            <td class="labelTd"><label for="orderName$text">订单名称</label></td>
            <td><input id="orderName"  name="orderName" class="mini-textbox" style="width:100%;"    enabled="false" readonly="readonly" value=""  borderStyle="border:0"/>
           
        </tr>
         <tr>
         
    	    <td class="labelTd"><label for="bookStatusName$text">订单状态</label></td>
            <td><input id="bookStatusName"  name="bookStatusName" class="mini-textbox" style="width:100%;"    enabled="false" readonly="readonly" value=""  borderStyle="border:0"/>
         <!--    
       		 <td class="labelTd"><label for="staffName$text"></label>预约审核人</td>
            <td><input id="staffName"  name="staffName" class="mini-textbox" style="width:100%;"    enabled="false" readonly="readonly" value="" />
    	    </td> -->
    	    
            <td class="labelTd"><label for="completedStaffName$text"></label>完工审核人</td>
            <td><input id="completedStaffName"  name="completedStaffName" class="mini-textbox" style="width:100%;"    enabled="false" readonly="readonly" value="" />
    	    </td>
             <td colspan="4">
             </td>
    	     </tr>
   		<!-- <tr height="60px;">
   			<td class="labelTd"><label for="checkAdvice$text">预约审核意见</label></td>
	        <td colspan="5"><input id="checkAdvice" name="checkAdvice" class="mini-textarea" emptyText="无" readonly="readonly" style="height:100%;width:100%" borderStyle="border:0"/></td>
       </tr> -->
       <tr height="60px;">
   			<td class="labelTd"><label for="completedAdvice$text">完工审核审核意见</label></td>
	        <td colspan="5"><input id="completedAdvice" name="completedAdvice" class="mini-textarea" emptyText="请输入审核意见" style="height:100%;width:100%" borderStyle="border:0"/></td>
       </tr>
   	</table>
   	
   	
</div>
  

   		<div class="mini-fit">
			<div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" plain="false"
			    buttons="#tabsButtons">
			    <div title="上报信息" iconCls="icon-node" showCloseButton="false" url="ResearchTask/selectCompletedOrderDetailBehind.jsp?orderId=<%=request.getParameter("orderId")%>">
			    </div>
			    <div title="预约信息"  iconCls="icon-node" showCloseButton="false" url="ResearchTask/selectCompletedOrderDetailBefore.jsp?orderId=<%=request.getParameter("orderId")%>">
			    </div>
			    
			</div>
		</div>
   
       

      <script type="text/javascript">
     
        
        mini.parse();

        var orderId="<%=request.getParameter("orderId")%>" ;
        var bookStatus;
        
        $(function(){ 
        	loadForm(orderId);       	
        }); 
        
        
        function onKeyEnter(e) {
            search();
        }
        
        function search() {
        	orderId = mini.get("key").getValue();
            loadForm(orderId);
           /*  var bookStatus = mini.get("bookStatusName").getValue(); */
        	
        }
        
        function loadForm(orderId) {
            //加载表单数据
            var form = new mini.Form("#form1");            
            $.ajax({
                url: "loadCompletedOrderInfo.action",
                type: "post",
                data:{orderId:orderId},
                success: function (text) {
                	/* alert(text); */
                	/* var date01=eval('(' + text + ')') */
                    var data = mini.decode(text);    //反序列化成对象
                    bookStatus=data.bookStatusName;
                    form.setData(eval('(' + text + ')'));   
                    
                    
                    /* grid.load({ orderId: orderId,bookStatus:bookStatus });//设置多个控件数据 */
                }
            });
        }
        
        var Genders=[{id: "11", text: "新建"},{id: "12", text: "待审核"},{id: "13", text: "审核通过"},{id: "14", text: "审核不通过"},{id: "15", text: "加工中"},{id: "16", text: "加工完成"},{id: "16", text: "订单完结"}]
        var machineGenders=[{id: "5501", text: "数控电火花成形机床"},{id: "5502", text: "数控低速走丝电火花线切割"},{id: "5503", text: "数控高速成型磨床"},
        	{id: "5504", text: "超精密成形平面磨床"},{id: "5505", text: "数控车床"},{id: "5506", text: "CNC雕刻机"},{id: "5511", text: "车铣复合加工中心"},
        	{id: "5512", text: "超高速磨床"},{id: "5513", text: "超声辅助高速加工中心"},{id: "5514", text: "高速五坐标加工中心"},{id: "5514", text: "加工中心"}]
        var datelines=[{id: "08:00", text: "08:00"},{id: "09:00", text: "09:00"},{id: "10:00", text: "10:00"},{id: "11:00", text: "11:00"},
        					{id: "12:00", text: "12:00"},{id: "13:00", text: "13:00"},{id: "14:00", text: "14:00"},{id: "15:00", text: "15:00"},
        					{id: "16:00", text: "16:00"},{id: "17:00", text: "17:00"},{id: "18:00", text: "18:00"},{id: "19:00", text: "19:00"},
        					{id: "20:00", text: "20:00"},{id: "21:00", text: "21:00"},{id: "22:00", text: "22:00"}]

        
        
        grid.on("cellbeginedit",function(e){
        	  if(e.field=="unid"){
        	  e.cancel=true;
        	  };
        	  if(e.field=="unid"){
            	  e.cancel=true;
            	};
             if(e.field=="machineID"){
                 e.cancel=true;
              };
              if(e.field=="timeYmd"){
                   e.cancel=true;
               };
               if(e.field=="startTimeInfo"){
                   e.cancel=true;
               };
               if(e.field=="endTimeInfo"){
                   e.cancel=true;
               };
        	})
        	
        	
        	
        	
        function pass(bookOrderStstus){
        	
        	var completedAdvice=mini.get("completedAdvice").getValue();
        	var orderId=mini.get("orderId").getValue();
        	var bookStatus=bookOrderStstus;
        	/*  alert(checkAdvice); */
         	 $.ajax({
                  type:"POST",
                  url:"completedOrderAudit.action",
                 
                  
                  data: {bookStatus:bookStatus,orderId:orderId,completedAdvice:completedAdvice},
                  dataType: "json",
                  success:function(data){
                	  loadForm(orderId);
                	  alert(data.result);
                	  
           
                  },
                  error:function(data){
                	  alert(data.result);
                  } 
              });
	          	}
        	
       
       /*  
        grid.load();


        var menu = new ColumnsMenu(grid);
         */
        //////////////////////////////////////////////////////


        function addRow() {          
            var newRow = { name: "New Row" };
            grid.addRow(newRow, 0);

            grid.beginEditCell(newRow, "LoginName");
        }
         
        function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);                
            }
        }
        
		 function saveData() {
			 var completedAdvice=mini.get("completedAdvice").getValue();
				/* alert(mini.encode(grid.getData())); */
				var data=grid.getData();/* 这是得到的js的arrayList对象 */
				var data02=data[1];/* 这是获取list中的一个json对象 */
				var data01=mini.encode(grid.getData());/*将arrayList序列化为一个字符串 */
				var dataTest=mini.encode({list:data});/* 自己可以在封装其他的参数，类似封装totalCount的时候 */
			 $.ajax({
                 type:"POST",
                 url:"saveCompletedBookingInfo.action",
                 data:{list:data01,orderId:orderId,completedAdvice:completedAdvice},
         /*         dataType: "json", */
                 success:function(text){
               		  alert(mini.decode(text).result); 
               			loadForm(orderId);
                 } ,
                 error:function(text){
                	  alert(mini.decode(text).result); 
                 } 
             });
		}
        
 		


      /*   grid.on("celleditenter", function (e) {
            var index = grid.indexOf(e.record);
            if (index == grid.getData().length - 1) {
                var row = {};
                grid.addRow(row);
            }
        });
 */
        grid.on("beforeload", function (e) {
            if (grid.getChanges().length > 0) {
                if (confirm("有增删改的数据未保存，是否取消本次操作？")) {
                    e.cancel = true;
                }
            }
        });


//        grid.on("cellcommitedit", function (e) {
//            if (e.field == "loginname") {
//                if (e.value == "111") {
//                    alert("不允许为111");
//                    e.cancel = true;
//                }
//            }
//        });

//        grid.on('beforeload', function (e) {
//            if (grid.getChanges().length > 0) {
//                e.cancel = true;
//                alert('有未保存的数据');
//            }
//        });
        
        
        
        
        
        
    </script>
    
    <%-- <script type="text/javascript">
    
    	mini.parse();
	    var grid = mini.get("bookMachine");
	    var orderId="<%=request.getParameter("orderId")%>";
	    grid.load();
	    updateBookOrderStatus();
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='删除' href=javascript:bookDelete(\'" + e.row.unid+"\',\'"+e.row.orderId+ "\',\'"+e.row.bookStatus+ "\')>删除</a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function bookDelete(unid,productId,bookStatus){
	    	
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
        	 if(bookStatus==14){
 	    		layer.confirm('注意:审核不通过会使订单信息失效!', {
 	                btn: ['确定', '取消'],
 	                yes: function (index, layero) { 
				        	 
				        	 var checkAdvice=mini.get("checkAdvice").getValue();
				        	/*  alert(checkAdvice); */
				         	 $.ajax({
				                  type:"POST",
				                  url:"AuditingBookOrderAll.action",
				                 
				                  
				                  data: {bookStatus:bookStatus,orderId:orderId,checkAdvice:checkAdvice},
				                  dataType: "json",
				                  success:function(data){
				                	//  alert(data.result);
				                	  U.msg(data.result);
				                	  grid.load();
				                	  updateBookOrderStatus();
				           
				                  },
				                  error:function(data){
				                	  U.msg(data.result);
				                  } 
				              });
 	               },
 	                btn2: function (index, layero) {
 	                    layer.close(index);
 	                }
 	            })
 	          		
 	          	}else{
 	   	
 	          	 var checkAdvice=mini.get("checkAdvice").getValue();
	        	/*  alert(checkAdvice); */
	         	 $.ajax({
	                  type:"POST",
	                  url:"AuditingBookOrderAll.action",
	                 
	                  
	                  data: {bookStatus:bookStatus,orderId:orderId,checkAdvice:checkAdvice},
	                  dataType: "json",
	                  success:function(data){
	                	//  alert(data.result);
	                	  U.msg(data.result);
	                	  grid.load();
	                	  updateBookOrderStatus();
	           
	                  },
	                  error:function(data){
	                	  U.msg(data.result);
	                  } 
	              });
 	          	}
         	
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
                	 /*  document.getElementById('checkAdvice').value=data.checkAdvice; */
                	  mini.get("checkAdvice").setValue(data.checkAdvice);
           
                  },
                  error:function(data){
                	  U.msg(data.result);
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

        
    </script> --%>
  </body>
</html>
