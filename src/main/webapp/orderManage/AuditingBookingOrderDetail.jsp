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
<script src="resources/scripts/boot.js" type="text/javascript"></script>

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
  
  	<div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="saveAuditAdvice()">保存审核意见</a>
		<span class="separator"></span>
		<!-- <a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('14')">审核不通过</a>	
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('12')">撤销审核</a>	
		<span class="separator"></span> -->
  			<a class="mini-button" iconCls="icon-find" plain="false"  onclick="bookModify()">修改预约时间</a>  
    </div>
  	
   	  <fieldset style="width: 100%;" align="center">
		<legend>
			订单信息
		</legend>
	
   		<table  style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr >
   			<td width="10%" class="labelTd"><label for="orderId$text">订单编号</label></td>
            <td width="25%" ><input id="orderId"  name="orderId" class="mini-textbox"  width="100%"   value="<%=request.getParameter("orderId")%>" enabled="false" borderStyle="border:0"/></td>
            <td width="10%" class="labelTd"><label for="companyName$text">订单客户</label></td>
            <td width="25%"><input id="companyName" name="comapnyname" class="mini-textbox"  width="100%"  value="<%=request.getParameter("companyName")%>" enabled="false" borderStyle="border:0"/>
   			<td width="10%" class="labelTd"><label for="createTime$text">创建时间</label></td>
            <td width="20%"><input id="createTime"  name="deptUser" class="mini-textbox"  width="100%" value="<%=request.getParameter("createTime")%>" enabled="false"  borderStyle="border:0"/>  
            </td>
        </tr>
        <tr>
       	    <td class="labelTd"><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox"  width="100%"  enabled="false" value="<%=request.getParameter("connector")%>"  borderStyle="border:0"/></td>
            <td class="labelTd"><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%"  enabled="false" value="<%=request.getParameter("connectorTel")%>"  borderStyle="border:0"/></td>
            <td class="labelTd"><label for="bookStatus$text">订单状态</label></td>
            <td><input id="bookStatus"  name="bookStatus" class="bookStatus" style="width:100%;"    enabled="false" readonly="readonly" value=""  borderStyle="border:0"/>
           
   			
       </tr>
       <!-- <tr>
       <td class="labelTd"><label for="checkAdvice$text">预约审核意见</label></td>
	        <td colspan="5"><input id="checkAdvice" name="checkAdvice" class="mini-textarea" emptyText="请输入审核意见"  style="height:100%;width:100%" borderStyle="border:0"/></td>
        </tr> -->
   	</table>
   	  </fieldset>
   	      <div id="bookMachine" class="mini-datagrid" style="width:100%;height:500px;" 
        url="myBookOrderMachine1.action?orderId=<%=request.getParameter("orderId")%>" idField="id" 
        allowResize="true" pageSize="20" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        editNextOnEnterKey="true"  editNextRowCell="true" >
        <div property="columns">
            <div type="indexcolumn"></div>
            
            <div name="unid"  field="unid" headerAlign="center" align="center" allowSort="true" width="150" >预约编号
                <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" />
            </div>
            
            <div name="machineName"  field="machineName"  headerAlign="center" align="center" allowSort="false" width="150" >预约设备
                <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" />
            </div>          
            
            <div name="timeYmd" field="timeYmd" width="100" align="center" headerAlign="center" dateFormat="yyyy-MM-dd">日期
                <input property="editor" class="mini-datepicker" style="width:100%;"/>
            </div>  
            
          	<div name="startTimeInfo"  field="startTimeInfo"  headerAlign="center" align="center" allowSort="false" width="150" >开始日期
                <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" />
            </div>
            
            <div name="endTimeInfo"  field="endTimeInfo"  headerAlign="center"  allowSort="false" align="center" width="150" >结束日期
                <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" />
            </div>
            <div type="comboboxcolumn" autoShowPopup="true" name="isPass" field="isPass" width="100"  alowSrot="false" hideable="true" align="center" headerAlign="center">请选择是否通过
                <input property="editor" class="mini-combobox" style="width:100%;" data="datelines" />                
            </div> 
            
	       <div name="isPassAdvice"  field="isPassAdvice"  headerAlign="center"  allowSort="false" allowCellWrap="true" align="center" width="150">请输入审核意见
	              <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" minHeight="50"/>
	            </div>
	       <div name="auditPerson"  field="auditPerson"  headerAlign="center"  allowSort="false" align="center" width="150">审核人
	              <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" minHeight="50"/>
	            </div>
           <!--  <div field="bookState" width="100" headerAlign="center"  align="center" renderer="onOperatePower">编辑</div>  -->
        </div>
    </div>
        <%-- <div id="bookMachine" class="mini-datagrid" style="width:100%;height:500px;" url="myBookOrderMachine1.action?orderId=<%=request.getParameter("orderId")%>">
        <div property="columns">            
        	<div field="unid" width="120" headerAlign="center" allowSort="true" align="center" headerAlign="center">预约编号</div> 
            <div field="machineName" width="120" headerAlign="center"  align="center" headerAlign="center">设备名称</div> 
            <div field="timeYmd" width="100" allowSort="true" align="center" headerAlign="center" align="center">日期</div>               
            <div field="startTimeInfo" width="100"   align="center" headerAlign="center">开始时间</div>            
            <div field="endTimeInfo" width="100"  align="center" headerAlign="center">结束时间</div>
            <div field="bookState" width="100" headerAlign="center"  align="center" renderer="onOperatePower">编辑</div>                
    </div> 
    </div> --%>

    <script type="text/javascript">
    
    	mini.parse();
	    var grid = mini.get("bookMachine");
	    var orderId="<%=request.getParameter("orderId")%>";
	    grid.load();
	    
	    grid.on("load", function () {
            grid.mergeColumns(["rowid", "machineName"]);
        });
	    
	    
	    function saveAuditAdvice() {
				
	    		var data=grid.getData();/* 这是得到的js的arrayList对象 */
				var data02=data[1];/* 这是获取list中的一个json对象 */
				var data01=mini.encode(grid.getData());/*将arrayList序列化为一个字符串 */
				var dataTest=mini.encode({list:data});/* 自己可以在封装其他的参数，类似封装totalCount的时候 */
				
			 $.ajax({
                type:"POST",
                url:"saveAuditAdvice.action",
                data:{list:data01,orderId:orderId},
                success:function(text){
                	grid.load();
               	 	confirm(mini.decode(text).result); 
               		
                } ,
                error:function(text){
               	 	confirm(mini.decode(text).result); 
                } 
            });
		}
	    
	    function updateAudits() {
	    	 $.ajax({
                type:"POST",
                url:"updateAudits.action",
                
                data: {orderId:orderId},
                dataType: "json",
                success:function(data){
              	 
              	// alert(data.result);
              	 $("#form1").html(data.result);
              	 updateBookOrderStatus();
                } 
            }); 
	    }
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a class='mini-button' iconCls=' icon-remove' style='margin-right:2px;' title='删除' href=javascript:bookDelete(\'" + e.row.unid+"\',\'"+e.row.orderId+ "\',\'"+e.row.bookStatus+ "\')>删除</a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    var bookGenders=[{id: "11", text: "新建订单待预约"},{id: "12", text: "预约待审核"},{id: "13", text: "预约审核通过"},{id: "14", text: "预约审核不通过"},{id: "15", text: "上报完成"},{id: "16", text: "订单完结"}]
        var machineGenders=[{id: "5501", text: "数控电火花成形机床"},{id: "5502", text: "数控低速走丝电火花线切割"},{id: "5503", text: "数控高速成型磨床"},
        	{id: "5504", text: "超精密成形平面磨床"},{id: "5505", text: "数控车床"},{id: "5506", text: "CNC雕刻机"},{id: "5511", text: "车铣复合加工中心"},
        	{id: "5512", text: "超高速磨床"},{id: "5513", text: "超声辅助高速加工中心"},{id: "5514", text: "高速五坐标加工中心"},{id: "5514", text: "加工中心"}]
        var datelines=[{id: "是", text: "是"},{id: "否", text: "否"}]
	    
	    grid.on("cellbeginedit",function(e){
	    	   if(e.field=="unid"){
	    	      e.cancel=true;
	    	   }
	    	   if(e.field=="machineName"){
	    	      e.cancel=true;
	    	   }
	    	   if(e.field=="timeYmd"){
	    	      e.cancel=true;
	    	   }
	    	   if(e.field=="startTimeInfo"){
	    	      e.cancel=true;
	    	   }
	    	   if(e.field=="endTimeInfo"){
	    	      e.cancel=true;
	    	   }
	    	   if(e.field=="auditPerson"){
	    	      e.cancel=true;
	    	   }
	    	})
	    
	    
	    
	   /*  自己想做扽动态的加载的页面的地方！！！！！！！！！！以后再说把*/ 
	    function creatTR(dqzhzhygtr,top1){
	    	var strTr="";
	    	strTr+="<tr id='tableTR_"+dqzhzhygtr+"'><td class='bt2'>发明人"+dqzhzhygtr+"姓名 </td>"
	    	 $("#tableTR_"+dqzhzhygtrs).after(strTr)//
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
        	 if(bookStatus==14){
 	    		layer.confirm('注意:审核不通过会使订单中信息失效！！！', {
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
 	          	// var checkAdvice=mini.get("checkAdvice").getValue();
 	          	 var form = new mini.Form("#form1");
 	      	  	 var data =form.getData();
 		    	 var data = mini.encode(data);   //反序列化成对象
	         	 $.ajax({
	                  type:"POST",
	                  url:"AuditingBookOrderAll13.action",
	                  data: {bookStatus:bookStatus,orderId:orderId,data:data},
	                  dataType: "json",
	                  success:function(data){
	                	//  alert(data.result);
	                	  U.msg("data.result");
	                	  //grid.load();
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
 	    	window.location="OrderSpecServlet?orderId=" +orderId +"&connector="+connector+"&isModify="+"4";
 		}
        /* 这个方法是用来更新审核意见的！！！开始进行整改！！！！！！！！！！！！！！！！！！！！！！！！！！*/ 
         
         function updateBookOrderStatus(){
         	 $.ajax({
                  type:"POST",
                  url:"AuditingBookOrderUpdateStatus1.action",
                 
                  
                  data: {orderId:orderId},
                  dataType: "json",
                  success:function(data){
                	  var form = new mini.Form("#form1");
                	  var data = mini.decode(data);   //反序列化成对象
                      form.setData(data); 
                     
                	  //document.getElementById('isPass5506').value=data.isPass5506;
                	  
                	  ///alert(data.rtesult);
                	  /* 	 //alert(data.bookStatus);
                	  //U.msg(data.result);
                	  document.getElementById('bookStatus').value=data.bookStatusName;
                	  document.getElementById('auditingStaffCode').value=data.staffName;
                	 /*  document.getElementById('checkAdvice').value=data.checkAdvice; */ 
                	 // mini.get("checkAdvice").setValue(data.advice);
           
                  },
                  error:function(data){
                	  U.msg(data.result);
                  } 
              });
         	
         	}

		
		function callbackFun(data)
   	    {
   	         alert(data.result);
   	      	 window.location.href = window.location.href;
   	    }

        
    </script>
  </body>
</html>
