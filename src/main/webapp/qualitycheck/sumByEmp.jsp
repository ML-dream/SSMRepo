<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工生产情况</title>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<!-- miniui.js -->
	            <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
  </head>
  
  <body onbeforeunload = "return pageClose()">
  
  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
   				<td style= "width:70px;"></td>
				<td><label>部门</label></td>
				<td>
 					<input id="dept" name="dept" class="mini-buttonedit" width="" onbuttonclick="onButtonEdit2" required="false" value="" text=""/>
 				</td>
 				<td style="width:60px;">加工者 </td>
                <td style="width:80px;"><input id="worker" name="worker" class="mini-buttonedit" width="100%" showClose="true"  oncloseclick="onCloseClick('worker')"
            		onbuttonclick="onButtonEdit" textName="worker" required="false"   allowInput="false"/></td>
 				
 				<td>开始时间</td>
				<td>
					<input id="bdate" name ="bdate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="true" required="true"/>
 				</td>
 				<td>结束时间</td>
				<td>
					<input id="edate" name ="edate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="true" required="true"/>
 				</td>
 				<td>查看不合格</td>
				<td>
					<input id="rejectMode" name="rejectMode" class="mini-combobox" width="95" textName="" textField="text" valueField="id" value="0"
  						url="data/rejectMode.txt"  allowInput="false" showNullItem="false" nullItemText="请选择..."  onvaluechanged="search"/>
 				</td>
 				<td>
 					<input value="查询" type="button" style= "width:70px;" onclick="search()" />
 				</td>
 				
 				<td>
 					<input value="更新数据" type="button" style= "width:90px;" onclick="pageClose()" />
 				</td>
   		
   				<td>
 					<input value="导出Excel" type="button" style= "width:90px;" onclick="outExcel()" />
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
   	 
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:1300px;height:480px;" allowResize="true" showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell"
        url="SumByEmp"  idField="id" multiSelect="false" pagesize="20" onselect="" onrowclick=""  onshowrowdetail="onShowRowDetail">
        <div property="columns">
            <div type="expandcolumn" ></div>
            
            <div field="staff_code" width="65" headerAlign="center" allowSort="false">员工编号</div>    
            <div field="staff_name" width="65" headerAlign="center" allowSort="false">员工姓名</div> 
            
            <div field="deptname" width="65" headerAlign="center">所属部门</div>
             <div header="数量" headerAlign="center">
                <div property="columns"> 
		        	<div field="donenum" width="65" headerAlign="center" allowSort="true">完成数量</div>
		        	<div field="acceptnum" width="60" headerAlign="center" allowSort="false">合格数</div>
		   			<div field="fixnum" width="50" headerAlign="center" allowSort="false">返修数</div>
		   			<div field="discardnum" width="50" headerAlign="center" allowSort="false">报废数</div>
		   			<div field="rejectper" width="60" headerAlign="center" allowSort="true">次品率(%)</div>
		   		</div>
            </div>
            <div header="工时" headerAlign="center">
                <div property="columns"> 	
		   			<div field="ratedtime" width="75" headerAlign="center" allowSort="false">计划工时/min</div>
		    		<div field="donetime" width="65" headerAlign="center" allowSort="true">发生工时/min</div>
		    		<div field="timegap" width="65" headerAlign="center" allowSort="false">工时差额/min</div>
		    		<div field="fixtime" width="65" headerAlign="center" allowSort="false">返修质量损失</div>
		    		
		    		<div field="discardtime" width="65" headerAlign="center" allowSort="false">报废质量损失</div>
		    		<!-- <div field="timeper" width="80" headerAlign="center" allowSort="true">报废工时比例(%)</div>  -->
       			 </div>
            </div>
		</div>
     </div>
   </div>    
    
    <script type="text/javascript" charset="utf-8">
    	mini.parse();
	    var grid = mini.get("datagrid1");
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
	   
	   function outExcel(){
	    	var dept = mini.get("dept").getValue();
        	var bdate =  mini.get("bdate").getFormValue();
        	var edate =   mini.get("edate").getFormValue();
        	var worker = mini.get("worker").getValue();
			var rejectMode = mini.get("rejectMode").getValue();
			
	    	window.location="empSumExcelOut?bdate="+bdate+"&edate="+edate+"&worker="+worker+"&dept="+dept+"&rejectMode="+rejectMode;
	    }
	    
	    function search(){
        	var form0 = new mini.Form("form1");
            form0.validate();
            if (form0.isValid() == false) {return;}
        	
        	var dept = mini.get("dept").getValue();
        	var bdate =  mini.get("bdate").getFormValue();
        	var edate =   mini.get("edate").getFormValue();
        	
        	var worker = mini.get("worker").getValue();
        	var rejectMode = mini.get("rejectMode").getValue();
        	
        	grid.load({dept:dept,bdate:bdate,edate:edate ,worker:worker,rejectMode:rejectMode});
        }
	   
	   function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            //服务端汇总计算
            if (e.field == "deptname") {                
                var s = "<b style='color:red;'>"
                s +=  	"合计："
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "donenum") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.donenum + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "acceptnum") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.acceptnum + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            
            if (e.field == "fixnum") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.fixnum + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "discardnum") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.discardnum + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "ratedtime") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.ratedtime + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "donetime") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.donetime + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "timegap") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.timegap + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "fixtime") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.fixtime + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "discardtime") {                
                var s = "<b style='color:red;'>"
                s +=  	 result.discardtime + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
        }
        
	    function onCloseClick (para){
	    	mini.get(para).setValue("");
	    	mini.get(para).setText("");
	    }
	   function onShowRowDetail(e){
	   		var worker = e.record.staff_code;
	   		var workerName = e.record.staff_name;
	   		var bday = mini.get("bdate").getFormValue();
	   		var eday = mini.get("edate").getFormValue();
	   		window.open("qualitycheck/workerDetail.jsp?worker="+worker+"&workerName="+workerName+"&bday="+bday+"&eday="+eday,
	    	          "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    }
	   function setData(row){
        var data = mini.clone(row);
        grid.load({partsplanid:row.partsplanid});
        var form1 = new mini.Form("#form1");
        form1.setData(row);
	}
	function onButtonEdit(e) {
         var btnEdit = this;
         var url ="employeeManage/selectEmployeeWindow.jsp";
         mini.open({
             url: url,
             title: "选择列表",
             width: 650,
             height: 380,
             ondestroy: function (action) {
                 //if (action == "close") return false;
                 if (action == "ok") {
                     var iframe = this.getIFrameEl();
                     var data = iframe.contentWindow.GetData();
                     data = mini.clone(data);    //必须
                     if (data) {
                         btnEdit.setValue(data.staffCode);
                         btnEdit.setText(data.staffName);
                     }
                 }
             }
         });
     }
		//部门选择 
   function onButtonEdit2(e) {
            var btnEdit = this;
            mini.open({
                url: "deptManage/selectDeptTreeWindow.jsp",
                title: "选择上级部门",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.deptId);
                            btnEdit.setText(data.deptName);
                        }
                    }
                }
            });
        }
        
        
        //页面关闭时，删除表中数据  
       function pageClose(){
       		$.ajax({
			type:"post",
			url: "EmpSumClose",
			cache: false,
			success: function (text){
				search();
				//return "ok";
				//alert("操作成功");
			}
		});
       }
        
    </script>
  </body>
</html>
