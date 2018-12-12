<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
	
	<head>
		 <base href="<%=basePath%>">
		<title>条码打印</title>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/qualitycheck/barcode/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=path %>/qualitycheck/barcode/jquery-barcode.js"></script>
		 <!-- miniui.js 
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>	-->
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		
		<style type="text/css">
			.barcodeImg{margin:10px 0px;
				height:160px;
			}
			.mini-grid-cell-inner, .mini-grid-headerCell-inner {
                font-size: 10pt;
                font-family: Tahoma, Verdana, 宋体;
                line-height: 18px;
                padding: 0px;
                padding-top: 2px;
                padding-bottom: 2px;
                width: 100%;
                position: relative;
                overflow: hidden;
                white-space: normal;
                word-break: break-all;
            }
			.mini-textbox-input{
				font-size: 10pt;
			}
		</style>
	</head>
	<body>
		<div style="margin:10px;height:140px;">	
			<input id="src" value="${barcode}" style="visibility:hidden;"></input>				
			<!--  <input type="button" onclick='code39()' value="code39" >  -->
			<!-- <input type="button" onclick='code128()' value="条形码" style="visibility:hidden;"> -->
			<div id="bcTarget" class="barcodeImg" style= "margin : 0 auto;"></div>			
		</div>
			<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr>
				<td><label>合同号</label></td>
	            <td>
   					<input id="Order_id"  name="order_id" width="240px"  class="mini-textbox"  enabled="false" allowInput="false"/></td>
   				<td><label>客户名称</label></td>
	            <td>
   					<input id="CompanyName"  name="companyname" width="240px" class="mini-textbox"  enabled="false" allowInput="false"/></td>
   				<td><label>工件状态</label></td>
	            <td>
   					<input id="Grade"  name="grade" class="mini-textbox"  value="正常" enabled="false" allowInput="false"/></td>
   			</tr>
   			<tr>
   				<td><label>零件图号</label></td>
	            <td><input id="DrawingId" name="drawingid"  width="240px" class="mini-textbox" enabled="false" allowInput="false"/></td>
   				<td><label>零件名称</label></td>
	            <td><input id="Product_Name" name="product_name" width="240px"  class="mini-textbox" enabled="false" allowInput="false"/></td>
	            <td><label>投入量</label></td>
	            <td><input id="Input_Num" name="input_num" class="mini-textbox" enabled="false" allowInput="false"/></td>
   			</tr>
   	 	</table>
   	 </form>
   	 <div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:960px;height:1080px;border:1px solid" allowResize="true" 
        url="ProcessFlowCard"  idField="id" multiSelect="true" pagesize="25" onselect="rowselect()" onrowclick="rowselect()">
        <div property="columns">
                
            <!--零件条形码 ，不可见 -->    
           <!--  <div type="indexcolumn"></div><div type="checkcolumn" ></div>   --> 
           
            <div field="fo_no" width="40" headerAlign="center" allowSort="true" cellStyle="height:40px;font-size:24px;">工序号</div>    
            <div field="fo_opname" width="60" headerAlign="center" allowSort="false" cellStyle="height:40px;font-size:24px;">工序名称</div> 
            <div field="rated_time" width="60" headerAlign="center" allowSort="false">工时定额</div>   
            
            <div header="任务" headerAlign="center">
                <div property="columns">
                    <div field="num" width="60"  headerAlign="center">计划数量</div>
                    <div field="workername" width="60" headerAlign="center">加工者</div>
                    <div field="workerid" width="60" headerAlign="center">工号</div>
                    <div field="checkdate" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">日期</div>
                    <div field="machine" width="60" headerAlign="center">设备</div>
                    <div field="item" width="60" headerAlign="center">刀具</div>
                </div>
            </div>  
             
             <div header="检验" headerAlign="center">
                <div property="columns">
                	<div field="usedtime" width="60" headerAlign="center" allowSort="false">实做工时</div>
                	<div field="accept_num" width="40" headerAlign="center" allowSort="false">合格数
           			 </div>
           			 <div field="reject_num" width="40" headerAlign="center" allowSort="false">废品数
           			 </div>
            		 <div field="checker" width="60" headerAlign="center" allowSort="false">检验者</div>
                </div>
            </div> 
            
            <!-- 需配合datagrid的allowCellEdit等属性一起用 -->
            <div field="confirm_num" width="60" headerAlign="center" allowSort="false">数量确认
            </div>    
            <!-- 备注栏自动换行？ -->
            <div field="remark" width="100" headerAlign="center" allowSort="false">备注
            	<!-- <input class="mini-textbox" value="" style="width:100%;"  />  -->
            </div>        
        </div>
     </div>
   </div>
   <div>
   		<table>
   			<tr style="height:20px"></tr>
   			<tr style="height:40px">
   				<td style="width:80px"></td>
   				<td>调度:</td>
   				<td style="width:180px"></td>
   				<td>审核:</td>
   				<td style="width:180px"></td>
   				<td>日期:</td>
   			</tr>
   		</table>
   </div>
</body>
	<script type="text/javascript">
		mini.parse();
		var checked = 0;	//这个全局变量用于标志 所选工序是否质检,0代表未质检 
		code128();
		function loadgrid(){
			var grid = new mini.get("datagrid1");
			var headerForm = new mini.Form("#form1");
			
			var mark =0;
			//结合form中各输入项 name属性
           // var data = form.getData(true, false);
          //  var s = mini.encode(data);
         //   alert(s);
         //获取条形码，应判断是否为空 
         	var barcode = document.getElementById('src').value;
         	
        //	加载表头 
        	$.ajax({
        		type: "post",
        		url:"LoadCardHeader?barcode="+barcode,
        		datatype:"json",
        		cache: false,
        		processData: false,
    			contentType: false,
    			success:function(text){
    				var data = mini.decode(text);
    				mark = data.status;		// 根据工件状态判断查询方式 
    				//9-16
					headerForm.setData(data,true);
					
		         	grid.load({key:barcode,mark:mark});
    			},
    			error : function() {
					//alert("加载失败, 错误码：" + jqXHR.responseText);
				}
        	});
		}
		
		
	/* 	function code39(){
			$("#bcTarget").empty().barcode($("#src").val(), "code39",{barWidth:3, barHeight:60,showHRI:false});
		}
 */
		 function code128(){
		  $("#bcTarget").empty().barcode($("#src").val(), "code128",{barWidth:2, barHeight:60,showHRI:true});
			loadgrid();
		}

	</script>


</html>