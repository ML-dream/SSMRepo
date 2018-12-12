<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工时单价维护</title>
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
  </br>
  	<form id= "form1">
  		<table style="text-align: right;border-collapse:collapse;"  width="600px">
	   		<tr>
	   			<td><label >新增工种：</label></td>
             	<td><label for="id$text">工种编号</label></td>
                <td><input id="id" name="id" class="mini-textbox" width="100%"  value="" required="true" /></td>
   				<td><label for="name$text">工种名称</label></td>
            	<td><input id="name"  name="name" class="mini-textbox"  width="100%" required="true" value="" /></td>
   				<td><label for="price$text">工种计费单价</label></td>
            	<td><input id="price"  name="price" class="mini-textbox"  width="100%"  value="" required="true" vtype="float"/></td>
   				<td><input value="新增" type="button" style= "width:70px;" onclick="newFo()" /></td>
   			</tr>
   		</table>
   	</form>
   	</br>
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:780px;height:560px;" 
        url="FoPriceManage" idField="id" allowResize="true" pageSize="20"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="true"  editNextOnEnterKey= "true"
    >
 		<div property="columns">
            <div type="checkcolumn" visible="false"></div>
            
            <div field="foid" width="60" headerAlign="center" allowSort="false">工种编号</div>
            <div field="foname" width="60" headerAlign="center" allowSort="false">工种名称
           		<input property="editor" class="mini-textbox" style="width:100%;" minHeight="20"  value=""/>
            </div>    
            <div field="price" width="60" headerAlign="center" allowSort="false">工种计费单价(￥/min)
            	<input property="editor" class="mini-textbox" style="width:100%;" minHeight="20"  value="" vtype="float"/>
            </div> 
        </div>
     </div>
     
     
     <table>
     	<tr height= "30px">
     		<td width="80px"></td>
     		<td width="60px"> <a class="Update_Button" href="javascript:firstpage()">首页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:uppage()">上一页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:downpage()">下一页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:endpage()">末页</a> </td>
     		<td width="90px"> <a class="Update_Button" href="javascript:celorder()">取消排序</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:saveData()">保存</a> </td>
     	</tr>
     </table>
   </div>    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("datagrid1");
	    var issave = 0;		//是否保存，0 为未保存 
	    grid.load();
	    
	    function search(){
        	var para1 =  mini.get("search").getValue();
        	grid.load({para1:para1});
        }
		function uppage(){
			helpSave();
			var size = grid.getPageSize();
			var npage = grid.getPageIndex();	//获取当前页码 
			var page = npage;
			if(npage ==0){
			}else{
				page = npage -1;
				grid.gotoPage ( page, size );
			}
			issave =0;
		}
		function downpage(){
			helpSave();
			var size = grid.getPageSize();
			var npage = grid.getPageIndex();	//获取当前页码 
			
			var total = grid.getTotalCount();	//数据总数 
			var tpage = Math.ceil(total/size);	//总页数 
			
			var page = npage;		//跳转页码 
			if(npage == tpage-1){
			}else{
				page = npage +1;
				grid.gotoPage ( page, size );
			}
			issave =0;
		}
		function firstpage(){
			helpSave();
			var size = grid.getPageSize();
			grid.gotoPage ( 0, size );
			issave =0;
		}
		function endpage(){
			helpSave();
			var size = grid.getPageSize();
			var npage = grid.getPageIndex();	//获取当前页码 
			
			var total = grid.getTotalCount();	//数据总数 
			var tpage = Math.ceil(total/size);	//总页数 
			
			grid.gotoPage ( tpage-1, size );
			issave =0;
		}
		function celorder(){
			helpSave();
			grid.clearSort ( );		
		}
		function saveData(){
			 var data = grid.getChanges();
    		 var json = mini.encode(data);
    		 //alert (json);
    		$.ajax({
				type:"post",
				url: "SaveFoPriceManage",
				data:{"data" : json},
				cache: false,
				success: function (text){
					//grid.reload();
					//alert (text);
					var t = confirm(text +",是否刷新页面？");
					if(t==true){
						grid.reload();
					}
				},
				error: function (text){
					alert ("保存失败 ");
				}
			});
		}
		function helpSave(){
			var data = grid.getChanges();
			var json = mini.encode(data);
			if(json != "[]" && issave ==0){
				var r = confirm("当前页数据已发生修改，是否保存?");
				if(r == true){
					saveData();
					issave = 1;
				}
			}
		}
        function newFo(){
        	var form = new mini.Form("#form1");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	var params = eval("("+mini.encode(data)+")");
 		  		jQuery.ajax({
      				type: "POST",
      				url: "NewFoPrice",
      				cache: false,
      				data: params,
      				success: function (data) {
        				alert(data);
        				grid.reload();
      				}
    			});
    		}
        }
    </script>
  </body>
</html>
