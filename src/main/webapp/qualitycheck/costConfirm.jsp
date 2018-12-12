<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>成本核算明细表</title>
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
  <div id="win1" class="mini-window" title="所有记录" style="width:600px;height:500px;" collapseOnTitleClick="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    <form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
   				
 				<td align="right">开始时间</td>
				<td style= "width:100px;">
					<input id="bdate" name ="bdate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" value ="2016-08-01"/>
 				</td>
 				<td align="right">结束时间</td>
				<td style= "width:100px;">
					<input id="edate" name ="edate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" value ="2019-01-01"/>
 				</td>
 				<td>
 					<input value="查询" type="button" style= "width:70px;" onclick="searchOrders()" />
 				</td>
   			</tr>
   			<tr style= "height:20px;">
   				<td width="90px"> <a class="mini-button" href="javascript:seeInPage()">单独页面查看</a> </td>
   				<td width="90px"> <a class="mini-button" href="javascript:fopriceManage()">工种单价维护</a> </td>
 				<td width="90px"> <a class="mini-button" href="javascript:stuffManage()">材料单价维护</a> </td>
 				<td width="90px"> <a class="mini-button" href="javascript:paraManage()">计算系数维护</a> </td>
 				
 				<td>
 					<input value="查看成本" type="button" style= "width:70px;" onclick="seeCostConfirm()" />
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
    <div id="winGrid" class="mini-datagrid" style="width:95%;height:380px;" allowResize="true" onload= ""
        url=LoadCostOrders  idField="id" multiSelect="false" pagesize="10" onselect="toProducts()" onrowclick="toProducts()">
        <div property="columns">
        	 <div type="checkcolumn" ></div> 
            <div field="orderId" width="80" headerAlign="center" allowSort="false">订单号</div> 
            <div field="companyName" width="100" headerAlign="center" allowSort="false">客户</div>  
            <div field="orderDate" width="50" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">订单日期</div>
         </div>   
     </div>
</div>
  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			
   			<tr style= "height:15px;"><label>先输入数据，再进行材料单价维护，材料牌号尽量不要输入中文。</label></tr>
   	 	</table>
   	 </form>
   	 
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:1350px;height:530px;" 
        url="SetCostConfirm" idField="id" allowResize="true" pageSize="20"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "false" showPagerButtonIcon="false" 
    	   showColumnsMenu ="true"  frozenStartColumn="0" frozenEndColumn="7" editNextOnEnterKey= "true" 
    	allowCellWrap="true"  onshowrowdetail="onShowRowDetail"
    >
 		<div property="columns">
 			<!--  <div type="checkcolumn" visible="true"></div>-->
 			<div type="expandcolumn" ></div>
            <div field="orderid" name = "orderid" width="190" headerAlign="center" allowSort="false" showColumnsMenu="true">合同号</div>
            <div field="customer"  headerAlign="center" allowSort="false" >客户</div>
           
            <div field="rectime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">接收时间</div>
            <div field="deltime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">交货时间</div>
            
            <div field="draid" width="100" headerAlign="center" allowSort="false">零件图号</div>    
            <div field="proname" width="80" headerAlign="center" allowSort="false">零件名称</div> 
             <div field="pronum" width="60" headerAlign="center" allowSort="false" visible="true">数量</div>
             
            <div field="stuff" width="150" headerAlign="center" allowSort="false"  visible="true" headerStyle="color:red;">材料牌号
            	 <input property="editor" class="mini-combobox" style="width:100%;" data="" url= "ShowStuff" allowinput = "true"/>
            </div>
            <div header="材料" headerAlign="center" headerStyle="color:red;">
                <div property="columns">
                    <div field="dia" width="60" headerAlign="center" >直径(D)
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
                    </div>
                    <div field="len" width="40" headerAlign="center" >长(L)
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
                    </div>
                    <div field="wid" width="40" headerAlign="center">宽(W)
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
                    </div>
                    <div field="hei" width="40" headerAlign="center">高(H)
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
                    </div>
                   <!--   <div field="vol" width="60" headerAlign="center" visible="false">体积(V)</div> -->
                    <div field="rsize" width="100" headerAlign="center">毛坯规格</div>
                    <div field="issup" width="60" headerAlign="center">是否来料
                    	<input property="editor" class="mini-combobox" style="width:100%;height:100%;" url="qualitycheck/json/stuff.txt"/>
                    </div>
                    <div field="stucost" width="80" headerAlign="center">材料费用
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
                    </div>
                </div>
            </div>  
            
            <div field="pack" width="80" headerAlign="center" allowSort="false" headerStyle="color:red;">包装
            	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
            </div>
            <div field="trans" width="80" headerAlign="center" allowSort="false" headerStyle="color:red;">运输
            	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
            </div>
            <div field="outasist" width="80" headerAlign="center" allowSort="false" headerStyle="">外协
            	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
            </div>
            <div field="realc" width="60" headerAlign="center" allowSort="false" headerStyle="color:red;">实际单价
    			<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
    		</div>
    		
           
    		
    		<div field="remark" width="60" headerAlign="center" allowSort="false">备注
    			<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="" value=""/>
    		</div>
        </div>
     </div>
     
     <div id="editWindow" class="mini-window" title="Window" style="width:1080px;" 
    showModal="true" allowResize="true" allowDrag="true"
    >
    <div id="editform" class="form" >
        <input class="mini-hidden" name="orderid" id= "orderid"/>
        <input class="mini-hidden" name="draid" id= "draid"/>
        <table style="width:1060px;">
            <tr>
                <td style="width:60px;">材料牌号：</td>
                <td style="width:90px;"><input id="stuff" name="stuff" class="mini-combobox" style="width:100%;" data="" url= "ShowStuff" allowinput = "true"/></td>
                <td style="width:40px;">直径：</td>
                <td style="width:60px;"><input name="dia" class="mini-textbox" style="width:100%;" value="0"/></td>
                <td style="width:40px;">长：</td>
                <td style="width:60px;"><input name="len" class="mini-textbox" style="width:100%;" value="1"/></td>
                <td style="width:40px;">宽：</td>
                <td style="width:60px;"><input name="wid" class="mini-textbox" style="width:100%;" value="1"/></td>
                <td style="width:40px;">高：</td>
                <td style="width:60px;"><input name="hei" class="mini-textbox" style="width:100%;" value="1"/></td>
                
                <td style="width:60px;">是否来料：</td>
                <td style="width:40px;"><input name="issup" class="mini-combobox" style="width:100%;height:100%;" url="qualitycheck/json/stuff.txt" value="N"/></td>
                <td style="text-align:center;padding-top:5px;padding-left:20px;width:40px;" colspan="">
                    <a class="Update_Button" href="javascript:updateRow()">新增毛坯</a> 
                </td>
                <td style="text-align:center;padding-top:5px;width:80px;" colspan="">
                	 <a class="Update_Button" href="javascript:saveSubStuff()">保存表格数据</a>
                </td>                
            </tr>
        </table>
        </br>
		<div id="datagrid2" class="mini-datagrid"  style="width:1060px;height:380px;" 
	        url="LoadSubStuff" idField="id" allowResize="true" pageSize="10"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
	    	showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="true" 
	    	   showColumnsMenu ="true"  editNextOnEnterKey= "true" 
	    >
 		<div property="columns">
 			<!--  <div type="checkcolumn" visible="true"></div>-->
            <div field="orderid" name = "orderid" width="190" headerAlign="center" allowSort="false" showColumnsMenu="true">合同号</div>
            <div field="draid" width="100" headerAlign="center" allowSort="false">零件图号</div>    
            <div field="proname" width="80" headerAlign="center" allowSort="false">零件名称</div> 
             
            <div field="stuff" width="100" headerAlign="center" allowSort="false"  visible="true" headerStyle="color:red;">材料牌号
            	 <input property="editor" class="mini-combobox" style="width:100%;" data="" url= "ShowStuff" allowinput = "true"/>
            </div>
            <div header="材料" headerAlign="center" headerStyle="color:red;">
                <div property="columns">
                    <div field="dia" width="60" headerAlign="center" >直径(D)
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="0"/>
                    </div>
                    <div field="len" width="40" headerAlign="center" >长(L)
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="1"/>
                    </div>
                    <div field="wid" width="40" headerAlign="center">宽(W)
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="1"/>
                    </div>
                    <div field="hei" width="40" headerAlign="center">高(H)
                    	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" vtype="float" value="1"/>
                    </div>
                   <!--   <div field="vol" width="60" headerAlign="center" visible="false">体积(V)</div> -->
                    <div field="rsize" width="100" headerAlign="center">毛坯规格</div>
                    <div field="issup" width="60" headerAlign="center">是否来料
                    	<input property="editor" class="mini-combobox" style="width:100%;height:100%;" url="qualitycheck/json/stuff.txt"/>
                    </div>
                    <div field="helpkey" width="80" headerAlign="center" allowSort="false" visible = "false"> 焊件序号</div>
                </div>
            </div>  
        </div>
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
     		<td width="60px"> <a class="Update_Button" href="javascript:celorder()">取消排序</a> </td>
     		<!-- 
     		<td width="100px"> <input id="frozen" name ="frozen" class="mini-textbox"  allowInput="true" value ="4" width="30px" vtype="number"/>
     		<a class="Update_Button" href="javascript:frozen()">窗口冻结</a> </td>   -->
     		<td width="80px"> <a class="Update_Button" href="javascript:delfrozen()">取消窗口冻结</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:saveData()">保存</a> </td>
     		<td><label>鼠标右击表头，可选择隐藏列</label></td>
     	</tr>
     </table>
   </div>    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("datagrid1");
	    var issave = 0;		//是否保存，0 为未保存 
	   // grid.load();
	    getNextDay();
         function getNextDay(){
                  var now=new Date();
                  var temp = now.getDate()+1;

                  now.setDate(temp);
            var year = now.getFullYear();
                  var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
                  var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
           mini.get("edate").setValue(year+"-"+month+"-"+day);
         }
	   
	  function searchOrders(){
	  //小窗口查找订单 
	  	var winGrid = mini.get("winGrid");
		var bdate =  mini.get("bdate").getFormValue();
       	var edate =   mini.get("edate").getFormValue();
		winGrid.load({ bdate:bdate, edate:edate }); 
	  }
	  function toProducts(){
			var winGrid = mini.get("winGrid");
			var row = winGrid.getSelected();
			var orderId = row.orderId;
			if(row){
				grid.load({ orderId:orderId });
			}
	}
	  function showAtPos() {
        var win = mini.get("win1");
        var x = "right";
        var y = "top";
        win.showAtPos(x, y);
	  }
	  showAtPos();
	  
	  
	     function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;

            var s = '<a class="" href="javascript:timecost(\'' + uid + '\')">查看</a>';
            return s;
        }
	    
	    function timecost(uid){}
	    
	    function search(){
        	var bdate =  mini.get("bdate").getFormValue();
        	var edate =   mini.get("edate").getFormValue();
        	grid.load({bdate:bdate,edate:edate});
        	 grid.mergeColumns (["orderid"]);
        	
        }
        function frozen(){
        	var para = mini.get("frozen").getValue();
        	para =parseInt(para) -1;
        	grid.frozenColumns ( 0, para );
        }
        function delfrozen(){
        	grid.unFrozenColumns ( );
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
			// var data = grid.getChanges();
			grid.selectAll();
			var data = grid.getSelecteds();
    		 var json = mini.encode(data);
    		//alert (json);
    		grid.deselectAll();
    		$.ajax({
				type:"post",
				url: "SaveCostConfirm",
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
		function seeCostConfirm(){
			window.location.href = "qualitycheck/seeCostConfirm.jsp";
		}
		function stuffManage(){
			window.open("ShowStuffManagePage",
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
		}
		function paraManage(){
			window.open("ShowParaManagePage",
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
		}
		function fopriceManage(){
			window.open("ShowFoPriceManagePage",
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
		}
		function seeInPage(){
			window.open("SeeInPage",
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
		}
		
        function onShowRowDetail(e) {
            //var grid = e.sender;	//主grid 
            var row = e.record;
            
            var editWindow = mini.get("editWindow");
            editWindow.show();
            
            var grid2 = mini.get("datagrid2");	//子grid 
           // var orderid = row.orderid;
            mini.get("orderid").setValue(row.orderid);
            mini.get("draid").setValue(row.draid);
            
            grid2.load({ orderid:row.orderid, draid:row.draid});
        }
        function saveSubStuff(){
        	var grid2 = mini.get("datagrid2");	//子grid 
        	var data = grid2.getChanges();
        	var json = mini.encode(data);
        	var orderid = mini.get("orderid").getValue();
        	var draid = mini.get("draid").getValue();
        	$.ajax({
				type:"post",
				url: "SaveSubStuff",
				data:{"data" : json,"orderid":orderid,"draid":draid},
				cache: false,
				success: function (text){
					var t = confirm(text +",是否刷新数据 ？");
					if(t==true){
						grid2.reload();
					}
				},
				error: function (text){
					alert ("保存失败 ");
				}
			});
        }
        function updateRow(){
        	var form = new mini.Form("#editform");
        	var grid2 = mini.get("datagrid2");	//子grid 
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	var params = eval("("+mini.encode(data)+")");
 		  		jQuery.ajax({
      				type: "POST",
      				url: "SaveNewSubStuff",
      				cache: false,
      				data: params,
      				success: function (data) {
        				alert(data);
        				grid2.reload();
        				form.reset();
      				}
    			});
    		}
        }
    </script>
  </body>
</html>
