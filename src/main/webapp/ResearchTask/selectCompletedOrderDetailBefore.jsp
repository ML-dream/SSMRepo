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
/* 	table {
	    table-layout:fixed;
	    word-break: break-all;
	}  */
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
<script src="<%=path %>/resource/timePlanJs/jquery.min.js"></script>
<script src="resources/scripts/boot.js" type="text/javascript"></script>
<script src="resources/demo/datagrid/js/ColumnsMenu.js" type="text/javascript"></script>

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
  

    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:500px;" 
        url="loadCompletedBookingInfo.action" idField="id" 
        allowResize="true" pageSize="20" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        editNextOnEnterKey="true"  editNextRowCell="true"
        
    >
        <div property="columns">
            <div type="indexcolumn"></div>
           <!--  <div type="checkcolumn"></div> -->
           
            <div name="unid"  field="unid" headerAlign="center"  allowSort="true" width="150" >预约编号
                <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" />
            </div>
            
 
            <div type="comboboxcolumn" autoShowPopup="true" name="machineID" field="machineID" width="100"  alowSrot="false" hideable="true" align="center" headerAlign="center">预约设备
                <input property="editor" class="mini-combobox" style="width:100%;" data="machineGenders" />                
            </div>            
        
                              
            <div name="timeYmd" field="timeYmd" width="100"  dateFormat="yyyy-MM-dd">日期
                <input property="editor" class="mini-datepicker" style="width:100%;"/>
            </div>  
<!--             <div name="orderId" field="orderId" width="100"  >订单号
                <input property="editor" class="mini-combobox" style="width:100%;"/>
            </div> -->  
  <!--         <div name="completedRemarks"  field="completedRemarks" headerAlign="center"   width="150" >备注
                <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" />
            </div> -->
          	<div type="comboboxcolumn" autoShowPopup="true" name="startTimeInfo" field="startTimeInfo" width="100"  alowSrot="false" hideable="true" align="center" headerAlign="center">开始时间
                <input property="editor" class="mini-combobox" style="width:100%;" data="datelines" />                
            </div> 
          	<div type="comboboxcolumn" autoShowPopup="true" name="endTimeInfo" field="endTimeInfo" width="100"  alowSrot="false" hideable="true" align="center" headerAlign="center">结束时间
                <input property="editor" class="mini-combobox" style="width:100%;" data="datelines" />                
            </div> 

<!--           <div field="completedRemarks"  name="completedRemarks" width="120" headerAlign="center" >备注
                <input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="50"/>
            </div> -->
           <!--  <div type="comboboxcolumn" autoShowPopup="true" name="completedRemarks" field="completedRemarks" width="120"   align="center" headerAlign="center">备注
                <input property="editor" class="mini-combobox" style="width:100%;" minWidth="200" minHeight="50" />                
            </div>  -->
           
            
            <<!-- div type="checkboxcolumn" field="confirmInfo" trueValue="1" falseValue="0" width="60" headerAlign="center">确认信息</div>       -->                  
        </div>
          
    </div>
   
       

      <script type="text/javascript">
     
        
        mini.parse();

        var grid = mini.get("datagrid1");
        var orderId ="<%=request.getParameter("orderId")%>";
        var menu = new ColumnsMenu(grid);
        var bookStatus;
        
       $(function(){
    	   grid.load({ orderId: orderId,bookStatus:"15" });//设置多个控件数据
       })
        
       
        
        var Genders=[{id: "11", text: "新建"},{id: "12", text: "待审核"},{id: "13", text: "审核通过"},{id: "14", text: "审核不通过"},{id: "15", text: "加工中"},{id: "16", text: "加工完成"},{id: "16", text: "订单完结"}]
        var machineGenders=[{id: "5501", text: "数控电火花成形机床"},{id: "5502", text: "数控低速走丝电火花线切割"},{id: "5503", text: "数控高速成型磨床"},
        	{id: "5504", text: "超精密成形平面磨床"},{id: "5505", text: "数控车床"},{id: "5506", text: "CNC雕刻机"},{id: "5511", text: "车铣复合加工中心"},
        	{id: "5512", text: "超高速磨床"},{id: "5513", text: "超声辅助高速加工中心"},{id: "5514", text: "高速五坐标加工中心"},{id: "5514", text: "加工中心"}]
        var datelines=[{id: "08:00", text: "08:00"},{id: "09:00", text: "09:00"},{id: "10:00", text: "10:00"},{id: "11:00", text: "11:00"},
        					{id: "12:00", text: "12:00"},{id: "13:00", text: "13:00"},{id: "14:00", text: "14:00"},{id: "15:00", text: "15:00"},
        					{id: "16:00", text: "16:00"},{id: "17:00", text: "17:00"},{id: "18:00", text: "18:00"},{id: "19:00", text: "19:00"},
        					{id: "20:00", text: "20:00"},{id: "21:00", text: "21:00"},{id: "22:00", text: "22:00"}]

        
        
        grid.on("cellbeginedit",function(e){
        	if(e.field=="unid"){
          	  e.cancel=true;
          	  };
          	  if(e.field=="unid"){
              	  e.cancel=true;
              	};
               if(e.field=="machineID"){
                   e.cancel=true;
                };
                if(e.field=="timeYmd"){
                     e.cancel=true;
                 };
                 if(e.field=="startTimeInfo"){
                     e.cancel=true;
                 };
                 if(e.field=="endTimeInfo"){
                     e.cancel=true;
                 };
          	})
       /*  
        grid.load();


        var menu = new ColumnsMenu(grid);
         */
        //////////////////////////////////////////////////////


        function addRow() {          
            var newRow = { name: "New Row" };
            grid.addRow(newRow, 0);

            grid.beginEditCell(newRow, "LoginName");
        }
         
        function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);                
            }
        }
        
		
 		


      /*   grid.on("celleditenter", function (e) {
            var index = grid.indexOf(e.record);
            if (index == grid.getData().length - 1) {
                var row = {};
                grid.addRow(row);
            }
        });
 */
        grid.on("beforeload", function (e) {
            if (grid.getChanges().length > 0) {
                if (confirm("有增删改的数据未保存，是否取消本次操作？")) {
                    e.cancel = true;
                }
            }
        });


//        grid.on("cellcommitedit", function (e) {
//            if (e.field == "loginname") {
//                if (e.value == "111") {
//                    alert("不允许为111");
//                    e.cancel = true;
//                }
//            }
//        });

//        grid.on('beforeload', function (e) {
//            if (grid.getChanges().length > 0) {
//                e.cancel = true;
//                alert('有未保存的数据');
//            }
//        });
        
        
        
        
        
        
    </script>
    
  
  </body>
</html>
