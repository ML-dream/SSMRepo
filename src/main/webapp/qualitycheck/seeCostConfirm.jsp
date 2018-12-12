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
  
  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
   				<td style= "width:70px;"></td>
 				<!--  
 				<td width="80px"> <a class="Update_Button" href="javascript:">修改材料单价</a> </td>
 				<td width="90px"> <a class="Update_Button" href="javascript:">修改计算系数</a> </td>
 				-->
 				<td>开始时间</td>
				<td>
					<input id="bdate" name ="bdate" class="mini-datepicker" dateFormat="yyyy-MM-dd" allowInput="true" value ="2000-01-01"/>
 				</td>
 				<td>结束时间</td>
				<td>
					<input id="edate" name ="edate" class="mini-datepicker" dateFormat="yyyy-MM-dd" allowInput="true" value ="2017-01-01"/>
 				</td>
 				<td>
 					<input value="查询" type="button" style= "width:70px;" onclick="search()" />
 				</td>
 				<td>
 					<input value="数据输入" type="button" style= "width:70px;" onclick="costConfirm()" />
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
   	 
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:1350px;height:530px;" 
        url="SeeCostConfirm" idField="id" allowResize="true" pageSize="20"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="true" 
    	onShowRowDetail= ""   showColumnsMenu ="true"  frozenStartColumn="0" frozenEndColumn="7"
    >
 		<div property="columns">
 			<div type="checkcolumn" visible="true"></div>
            <div field="customer" width="120" headerAlign="center" allowSort="false" >客户</div>
            <div field="orderid" width="190" headerAlign="center" allowSort="false" showColumnsMenu="true">合同号</div>
            <div field="rectime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">接收时间</div>
            <div field="deltime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">交货时间</div>
            
            <div field="draid" width="100" headerAlign="center" allowSort="false">零件图号</div>
			<div field="productId" width="100" headerAlign="center" allowSort="false" visible="false">产品号</div>    
            <div field="proname" width="80" headerAlign="center" allowSort="false">零件名称</div> 
             <div field="pronum" width="60" headerAlign="center" allowSort="false" visible="true">数量</div>
             
            <div field="stuff" width="100" headerAlign="center" allowSort="false"  visible="true" >材料牌号
            	 <input property="editor" class="mini-combobox" style="width:100%;" data="" url= "ShowStuff" readonly/>
            </div>
            <div header="材料" headerAlign="center" headerStyle="color:red;">
                <div property="columns">
                    <div field="dia" width="60" headerAlign="center" >直径(D)
                    </div>
                    <div field="len" width="40" headerAlign="center" >长(L)
                    </div>
                    <div field="wid" width="40" headerAlign="center">宽(W)
                    </div>
                    <div field="hei" width="40" headerAlign="center">高(H)
                    </div>
                    <div field="issup" width="60" headerAlign="center">是否来料
                    </div>
                    <div field="stucost" width="60" headerAlign="center">材料费用</div>
                </div>
            </div>  
            
            <div field="pack" width="80" headerAlign="center" allowSort="false" headerStyle="color:red;">包装
            </div>
            <div field="trans" width="80" headerAlign="center" allowSort="false" headerStyle="color:red;">运输
            </div>
            <div field="outasist" width="80" headerAlign="center" allowSort="false" headerStyle="">外协
            </div>
    		
            <div name="action" width="80" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">人工成本详情</div>
            <div field="alltimec" width="80" headerAlign="center" allowSort="false">合计人工成本</div>
             
        	<div field="pmanuc" width="80" headerAlign="center" allowSort="false">单件制造成本</div>
        	<div field="ptaxc" width="60" headerAlign="center" allowSort="false">单件税费</div>
   			
   			<div field="midc" width="80" headerAlign="center" allowSort="false">期间费用(财务、管理)</div>
   			
   			<div field="pcost" width="80" headerAlign="center" allowSort="false">单件总成本</div>
   			
   			<div field="acost" width="60" headerAlign="center" allowSort="false">总成本</div>
   			<div field="pconfirm" width="60" headerAlign="center" allowSort="false">核算单价</div>
   			<div field="realc" width="60" headerAlign="center" allowSort="false" headerStyle="color:red;">实际单价
    		</div>
    		
    		<div field="sale" width="60" headerAlign="center" allowSort="false" >销售额</div>
    		<div field="profit" width="60" headerAlign="center" allowSort="false" >利润</div>
    		
    		<div field="stuffc" width="60" headerAlign="center" allowSort="false" >材料费</div>
    		<div field="cconfirm" width="60" headerAlign="center" allowSort="false" >核算成本</div>
    		
    		<div field="remark" width="60" headerAlign="center" allowSort="false">备注
    		</div>
        </div>
     </div>
   </div>    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("datagrid1");
	    var issave = 0;		//是否保存，0 为未保存 
	   // grid.load();
	     function onStuffRenderer(e){
	     
	     }
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
	     
	     function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;

            var s = '<a class="" href="javascript:timecost(\'' + uid + '\')">查看</a>';
            return s;
        }
	    
	    function timecost(uid){
	    	var row = grid.getRowByUID(uid);
	    	var orderid =row.orderid;
	    	var productid = row.productId;
	    	//alert(row);
	    	window.open("ShowAlltimec?orderid="+orderid+"&productid="+productid,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    }
	    function search(){
        	var bdate =  mini.get("bdate").getFormValue();
        	var edate =   mini.get("edate").getFormValue();
        	grid.load({bdate:bdate,edate:edate});
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
			var data = grid.getChanges();
			// grid.selectAll();
			// var data = grid.getSelecteds();
    		 var json = mini.encode(data);
    		alert (json);
    		/*
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
			*/
		}
		function helpSave(){
			// var data = grid.getChanges();
			// var json = mini.encode(data);
			if(issave ==0){
				var r = confirm("当前页数据未保存，是否保存?");
				if(r == true){
					saveData();
					issave = 1;
				}
			}
		}
		
		function costConfirm(){
			window.location.href = "qualitycheck/costConfirm.jsp";
		}
        
    </script>
  </body>
</html>
