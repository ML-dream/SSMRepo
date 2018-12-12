<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>参数维护</title>
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
  
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:780px;height:400px;" 
        url="ParaManage" idField="id" allowResize="true" pageSize="10"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="true"  editNextOnEnterKey= "true"
    >
 		<div property="columns">
            <div type="checkcolumn" visible="false"></div>
            
            <div field="paraid" width="60" headerAlign="center" allowSort="false">参数代号</div>
            <div field="paraname" width="60" headerAlign="center" allowSort="false">参数名称
            </div>    
            <div field="paraval" width="60" headerAlign="center" allowSort="false">参数值
            	<input property="editor" class="mini-textbox" style="width:100%;" minHeight="20"  value=""/>
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
				url: "SaveParaManage",
				data:{"data" : json},
				cache: false,
				success: function (text){
					var t = confirm(text +",是否刷新数据 ？");
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
        
    </script>
  </body>
</html>
