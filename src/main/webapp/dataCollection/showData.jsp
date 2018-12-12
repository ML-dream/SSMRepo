<%@ page language="java" import="java.util.*,com.wl.forms.Employee" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!-- miniui.js -->
      <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>已发通知</title>
    <div></div>>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  	
  	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;"> 
	            <a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()">刷新</a>
	            <a class="mini-button" iconCls="icon-reload" plain="false" onclick="downLoad()">下载数据</a>
	            <!--
	            <a class="mini-button" iconCls="icon-remove" plain="false" onclick="getForm('0')">删除</a>     
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('1')">保存</a>
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>  
	            -->                 
	        </div>
	        
	       

	        <div id="grid1" class="mini-datagrid" style="width:100%;height:320px;"
				         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
				         url="dataFeedback01" onrowdblclick="rowdblclick">
				        <div property="columns">
				           <!--  <div type="indexcolumn"></div>
				            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 				cellStyle="padding:0;">操作
            				</div>   -->  
				        	 <div field="xaxisfeedspeed" width="10%" headerAlign="center">刀具坐标</div>
				            
				            <div field="date" width="10%" headerAlign="center">程序名称</div>
				            <div field="id03" width="10%" headerAlign="center">实际转速</div>
				            <div field="xaxiscoordinates" width="10%" headerAlign="center">主轴模式</div>
				            <div field="id04" width="10%" headerAlign="center">驱动负载</div>
				            <div field="id05" width="10%" headerAlign="center">主轴倍率</div>
				            <div field="id06" width="10%" headerAlign="center">报警数量</div>
				            <div field="id07" width="10%" headerAlign="center">各轴功率</div>
				            <div field="id08" width="10%" headerAlign="center">主轴名字</div>
				            <div field="id09" width="10%" headerAlign="center">轴的类型</div>
				            <div field="id10" width="10%" headerAlign="center">NC程序状态</div>
				            <div field="id11" width="10%" headerAlign="center">电机温度</div>
				           
				            <div field="id12" width="10%" headerAlign="center">主轴模式</div>
				            <div field="id13" width="10%" headerAlign="center">驱动负载</div>
				            <div field="id14" width="10%" headerAlign="center">主轴倍率</div>
				            <div field="id15" width="10%" headerAlign="center">报警数量</div>
				            <div field="id16" width="10%" headerAlign="center">各轴功率</div>
				           
				            
				        </div>
				    </div>
   <script>
   		mini.parse();
   		var grid = mini.get("grid1");
   	 	var machineId = "<%=request.getParameter("machineId")%>";
	    grid.load({machineId:machineId});
	    
	    
	    
	    function ondEdit(id,isReaded){
	        window.location.href="NoticeDetailServlet?id=" + id+"&isReaded="+isReaded;
		}
   		
	   /*  refresh(); */
	  /*   setInterval(refresh, 6000); */
   		
   		function refresh(){
   			var grid = mini.get("grid1");
			grid.load();
		}
   		
   		function downLoad(){
   			//var itemName=mini.get("item_name").getValue();
   			
    		window.location="dataDownload?machineId="+machineId;
		}
   		
   		function getForm(flag){
			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.isAlive=flag;
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditDeptSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
          		
            
            }
   		}
   		
   </script>
  </body>
</html>
