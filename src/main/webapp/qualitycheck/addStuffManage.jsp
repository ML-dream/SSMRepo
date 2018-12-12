<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>材料种类维护</title>
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
  		<table style="text-align: right;border-collapse:collapse;"  width="800px">
	   		<tr>
             	<td width="60px"><label for="id$text">材料编号</label></td>
                <td width="100px"><input id="id" name="id" class="mini-textbox" width="100%"  value="" required="true" /></td>
   				<td width="60px"><label for="name$text">材料名称</label></td>
            	<td width="100px"><input id="name"  name="name" class="mini-textbox"  width="100%" required="true" value="" /></td>
   				<td width="60px"><label for="price$text">单价</label></td>
            	<td><input id="price"  name="price" class="mini-textbox"  width="100%"  value="" required="false" vtype="float"/></td>
            	<td width="60px"><label for="price$text">密度</label></td>
            	<td><input id="price"  name="price" class="mini-textbox"  width="100%"  value="" required="false" vtype="float"/></td>
   				<td ><input value="新增" type="button" style= "width:70px;" onclick="newFo()" /></td>
   			</tr>
   		</table>
   	</form>
   	</br>
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:780px;height:400px;" 
        url="StuffManage" idField="id" allowResize="true" pageSize="10"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="true"  editNextOnEnterKey= "true"
    >
 		<div property="columns">
            <div type="checkcolumn" visible="false"></div>
            
            <div field="stuffid" width="60" headerAlign="center" allowSort="false">材料编号</div>
            <div field="stuffname" width="60" headerAlign="center" allowSort="false">材料名称
            	<input property="editor" class="mini-textbox" style="width:100%;" minHeight="20"  value=""/>
            </div>    
            <div field="price" width="60" headerAlign="center" allowSort="false">单价
            	<input property="editor" class="mini-textbox" style="width:100%;" minHeight="20"  value="0"/>
            </div> 
            <div field="density" width="60" headerAlign="center" allowSort="false" visible="true">密度
            	<input property="editor" class="mini-textbox" style="width:100%;" minHeight="20"  value="0"/>
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
