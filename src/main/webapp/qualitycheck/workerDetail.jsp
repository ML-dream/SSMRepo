<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工生产详情</title>
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
 				<td>员工编号</td>
				<td>
					<input id="staffCode" name ="staffCode" class="mini-textbox" width="100%" value="${param.worker}"/>
 				</td>
 				<td>
 					<input value="查看员工信息" type="button" style= "width:90px;" onclick="seeWorker()" />
 				</td>
 				<!--<td>员工姓名</td>
				<td>
					<input id="staffName" name ="staffName" class="mini-textbox" width="100%" value="${param.workerName}"/>
 				</td>
 				--><input id="bday" name ="bday" class="mini-hidden" width="100%" value="${param.bday}"/>
 				<input id="eday" name ="eday" class="mini-hidden" width="100%" value="${param.eday}"/>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:100%;height:530px;" 
        url="LoadWorkerDetail?worker=${param.worker}&bday=${param.bday}&eday=${param.eday}" idField="id" allowResize="true" pageSize="20"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="true" 
    	onShowRowDetail= "onShowRowDetail"  showColumnsMenu ="true"
    >
 		<div property="columns">
            <div type="checkcolumn" visible="false"></div>
            <div type="expandcolumn" ></div>
            
            <div field="checkDate" width="80" headerAlign="center" allowSort="false">日期</div>
            <div field="machineId" width="60" headerAlign="center" allowSort="false">设备</div>
            <div field="orderId" width="120" headerAlign="center" allowSort="false" visible="false">订单号</div>
            <div field="productId" width="80" headerAlign="center" allowSort="false">图号</div>    
            <div field="productName" width="80" headerAlign="center" allowSort="false">零件名称</div> 
             <div field="barcode" width="60" headerAlign="center" allowSort="false" visible="false">条码号</div>
            
            <div field="foNo" width="40" headerAlign="center" allowSort="false" visible="false">工序号</div>
            <div field="foOpname" width="60" headerAlign="center" allowSort="false">工序名称</div>
            <div field="inputNum" width="60" headerAlign="center" allowSort="false">计划数量</div>
            <div field="confirmNum" width="60" headerAlign="center" allowSort="false">完成数量</div>
            <div field="accept" width="40" headerAlign="center" allowSort="false">合格数</div>
            <div field="reject" width="60" headerAlign="center" allowSort="false" >不合格数</div>
            
   			<div field="disNum" width="40" headerAlign="center" allowSort="false">报废数</div>
   			<div field="fixNum" width="40" headerAlign="center" allowSort="false">返修数</div>
            
            <div field="workBranch" width="60" headerAlign="center" allowSort="false" visible="false">工种</div>
            <div field="price" width="60" headerAlign="center" allowSort="false">工时单价(元)</div>
        	<div field="ratedTime" width="60" headerAlign="center" allowSort="false">额定工时/min</div>
        	
   			<div field="baseTime" width="60" headerAlign="center" allowSort="false">计费工时/min</div><!--合格数*额定工时-->
   			<div field="rewardsTime" width="60" headerAlign="center" allowSort="false" >奖惩工时/min</div>
    		<div field="finalTime" width="60" headerAlign="center" allowSort="false" >结果工时/min</div>
    		<div field="timeValue" width="60" headerAlign="center" allowSort="false" >折算金额(元)</div>
    		<div field="checker" width="40" headerAlign="center" allowSort="false">质检员</div>
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
   </div>    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("datagrid1");
	    var editForm = document.getElementById("editForm1");
	  //  var issave = 0;		//是否保存，0 为未保存 
		grid.load();
		
		grid.on("drawcell",function (e) {
            var record = e.record,
        	column = e.column,
        	field = e.field,
        	value = e.value;
	    	if (field == "reject" && value >0 ) {
                e.rowStyle = "color:red;font-weight:bold;";
            }
	    	
	    	}
	    );
		
		
	   function seeWorker(){
	   		var staffCode=mini.get("staffCode").getValue();
	   		window.location.href="EditEmployeeDetailServlet?staffCode="+staffCode+"&para=seeEmp";
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
    </script>
  </body>
</html>
