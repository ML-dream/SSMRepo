<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>不合格品工时处理</title>
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
  <div id="win1" class="mini-window" title="所有记录" style="width:600px;height:600px;" collapseOnTitleClick="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    <form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
   				
 				<td>开始时间</td>
				<td style= "width:100px;">
					<input id="bdate" name ="bdate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" value ="2016-08-01"/>
 				</td>
 				<td>结束时间</td>
				<td style= "width:100px;">
					<input id="edate" name ="edate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" value ="2019-01-01"/>
 				</td>
   			</tr>
   			<tr style= "height:20px;">
 				<td>订单</td>
				<td>
	 				<input id="orderType" class="mini-combobox" style="width:100px;" textField="text" valueField="id" 
	   					 url="data/rewardspage.txt" value="all" showNullItem="false" allowInput="false"/>  
   			    </td>
 				<td>产品</td>
				<td>
	 				<input id="productType" class="mini-combobox" style="width:100px;" textField="text" valueField="id" 
	   					 url="data/rewardspage.txt" value="all" showNullItem="false" allowInput="false"/>  
   			    </td>
 				<td>
 					<input value="查询" type="button" style= "width:70px;" onclick="searchOrders()" />
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
    <div id="winGrid" class="mini-datagrid" style="width:80%;height:180px;" allowResize="true" onload= ""
        url="LoadRewardsOrders"  idField="id" multiSelect="false" pagesize="10" onselect="toProducts()" onrowclick="toProducts()">
        <div property="columns">
        	 <div type="checkcolumn" ></div> 
            <div field="orderId" width="80" headerAlign="center" allowSort="false">订单号</div> 
            <div field="companyName" width="100" headerAlign="center" allowSort="false">客户</div>  
            <div field="orderDate" width="50" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">订单日期</div>
         </div>   
     </div>
     
     <div id="winGrid2" class="mini-datagrid" style="width:100%;height:300px;" allowResize="true" onload= ""
        url="LoadRewardsProducts"  idField="id" multiSelect="false" pagesize="10" onselect="toFos()" onrowclick="toFos()">
        <div property="columns">
           	 <div type="checkcolumn" ></div> 
            <div field="orderId" width="60" headerAlign="center" allowSort="false">订单号</div> 
            <div field="productId" width="60" headerAlign="center" allowSort="false">产品号</div> 
            <div field="productName" width="40" headerAlign="center" allowSort="false">产品名称</div>  
         </div>   
     </div>
</div>
  	
   	 <form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
   				<td style= "width:70px;"></td>
 				<td>订单</td>
				<td>
					<input id="orderId" name ="orderId" class="mini-textbox" width="100%"/>
 				</td>
 				<td>图号</td>
				<td>
					<input id="productId" name ="productId" class="mini-textbox" width="100%"/>
 				</td>
 				<td >
 					<input value="确定" type="button" style= "width:70px;" onclick="search()" />
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:1300px;height:530px;" 
        url="RewardsPage" idField="id" allowResize="true" pageSize="20"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="false" 
    	onShowRowDetail= "onShowRowDetail"  showColumnsMenu ="true"
    >
 		<div property="columns">
            <div type="checkcolumn" visible="false"></div>
            <div type="expandcolumn" ></div>
            
            <div field="orderId" width="120" headerAlign="center" allowSort="false">订单号</div>
            <div field="productId" width="80" headerAlign="center" allowSort="false">图号</div>    
            <div field="productName" width="80" headerAlign="center" allowSort="false">零件名称</div> 
             <div field="barcode" width="60" headerAlign="center" allowSort="false" visible="false">条码号</div>
             
            <div field="foNo" width="40" headerAlign="center" allowSort="false">工序号</div>
            <div field="foOpname" width="60" headerAlign="center" allowSort="false">工序名称</div>
             
        	<div field="ratedTime" width="60" headerAlign="center" allowSort="false">额定工时/min</div>
        	<div field="acceptNum" width="40" headerAlign="center" allowSort="false">合格数</div>
   			
   			<!--  
	   			<div field="" width="40" headerAlign="center" allowSort="false">报废数</div>
	   			<div field="" width="40" headerAlign="center" allowSort="false">返修数</div>
   			-->
   			<div field="usedtime" width="70" headerAlign="center" allowSort="false">实做工时/min</div>
   			
   			<div field="workerName" width="40" headerAlign="center" allowSort="false">操作工</div>
   			
   			<div field="basetime" width="60" headerAlign="center" allowSort="false">计费工时/min</div>
   			<div field="rejectNum" width="40" headerAlign="center" allowSort="false" value="0">不合格数</div>
    		<div field="rewardstime" width="60" headerAlign="center" allowSort="false" >奖惩工时/min
    			<input property="editor" class="mini-textbox" style="width:100%;" minHeight="20" vtype="float" value="0"/>
    		</div>
    		
    		<div field="remark" width="40" headerAlign="center" allowSort="false">备注</div>
        </div>
     </div>
     
     <div id="editForm1" style="display:none;padding:5px;">
        <input class="mini-hidden" name="barcode"/>
        <input class="mini-hidden" name="fo_no"/>
        <table style="width:100%;">
            <tr>
                <td style="width:60px;">审理单号：</td>
                <td style="width:100px;"><input name="run1" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">责任人：</td>
                <td style="width:80px;"><input name="duty1" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">不合格数量：</td>
                <td style="width:80px;"><input name="reject1" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:80px;">不合格类型：</td>
                <td style="width:100px;"><input name="rejtype1" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">返修者：</td>
                <td style="width:100px;"><input name="fixer1" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">质量扣款：</td>
                <td style="width:60px;"><input name="timeloss1" class="mini-textbox" enabled="false" width="100%"/></td>
            </tr>
            <tr>
                <td style="width:60px;">审理单号：</td>
                <td style="width:100px;"><input name="run2" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">责任人：</td>
                <td style="width:80px;"><input name="duty2" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">不合格数量：</td>
                <td style="width:80px;"><input name="reject2" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:80px;">不合格类型：</td>
                <td style="width:100px;"><input name="rejtype2" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">返修者：</td>
                <td style="width:100px;"><input name="fixer2" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">质量扣款：</td>
                <td style="width:60px;"><input name="timeloss2" class="mini-textbox" enabled="false" width="100%"/></td>
            </tr>
            <tr>
                <td style="width:60px;">审理单号：</td>
                <td style="width:100px;"><input name="run3" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">责任人：</td>
                <td style="width:80px;"><input name="duty3" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">不合格数量：</td>
                <td style="width:80px;"><input name="reject3" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:80px;">不合格类型：</td>
                <td style="width:100px;"><input name="rejtype3" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">返修者：</td>
                <td style="width:100px;"><input name="fixer3" class="mini-textbox" enabled="false" width="100%"/></td>
                <td style="width:60px;">质量扣款：</td>
                <td style="width:60px;"><input name="timeloss3" class="mini-textbox" enabled="false" width="100%"/></td>
            </tr>
        </table>
    </div>
     
     <table>
     	<tr height= "30px">
     		<td width="80px"></td>
     		<td width="60px"> <a class="Update_Button" href="javascript:firstpage()">首页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:uppage()">上一页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:downpage()">下一页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:endpage()">末页</a> </td> 
     		<td width="60px"> <a class="Update_Button" href="javascript:refreshpage()">刷新</a> </td> 
     		<td width="90px"> <a class="Update_Button" href="javascript:celorder()">取消排序</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:saveData()">保存</a> </td>
     	</tr>
     </table>
   </div>    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("datagrid1");
	    var editForm = document.getElementById("editForm1");
	    var issave = 0;		//是否保存，0 为未保存 

		function searchOrders(){
		//小窗口的搜索函数，按日期、订单类型查找 订单
			var winGrid = mini.get("winGrid");
			var bdate =  mini.get("bdate").getFormValue();
        	var edate =   mini.get("edate").getFormValue();
        	var orderType = mini.get("orderType").getValue();
			winGrid.load({ bdate:bdate, edate:edate , orderType:orderType });  	
		}
		
		function toProducts(){
			var winGrid = mini.get("winGrid");
			var row = winGrid.getSelected();
			var orderId = row.orderId;
			if(row){
				var winGrid2 = mini.get("winGrid2");
				var productType = mini.get("productType").getValue();
				winGrid2.load({productType:productType, orderId:orderId });
			}
		}
		function toFos(){
			var winGrid2 = mini.get("winGrid2");
			var row = winGrid2.getSelected();
			var orderId = row.orderId;
			var productId = row.productId;
			if(row){
				grid.load({productId:productId, orderId:orderId });
			}
		}
	    function refreshpage(){
	    	grid.reload();
	    }
	    
	    function search(){
	    //主页面搜索函数，按订单、图号查询 
        	var orderId = mini.get("orderId").getValue();
        	var productId = mini.get("productId").getValue();
        	grid.load({ orderId:orderId, productId:productId });
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
    		// alert (json);
    		$.ajax({
				type:"post",
				url: "SaveRewards",
				data:{"data" : json},
				cache: false,
				success: function (text){
					grid.reload();
					alert (text);
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
         function onShowRowDetail(e) {
            var row = e.record;
            
            //将editForm元素，加入行详细单元格内
            var td = grid.getRowDetailCellEl(row);
            td.appendChild(editForm);
            editForm.style.display = "";

            //表单加载员工信息
            var form = new mini.Form("editForm1");
                grid.loading();
                $.ajax({
                    url: "LoadRewardsForm",
                    data:{barcode:row.barcode, fo_no: row.foNo},
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);                        

                        grid.unmask();
                    }
                });
        }
     function showAtPos() {
        var win = mini.get("win1");

        var x = "right";
        var y = "top";

        win.showAtPos(x, y);

    }
	showAtPos();
    </script>
  </body>
</html>
