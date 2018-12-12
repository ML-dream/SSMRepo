<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html >
<html>
<head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>返工单</title>
    <style type="text/css">
    </style>
</head>
<body>
<div style="width:600px; margin:0 auto; border:2px solid #6D6D6D;">
	<h1>返工/返修单</h1>
	<form id="form1">
	<table>
		<tr>
			<td><label>单号</label></td>
	 		<td><input id="runnum"  name="runnum" class="mini-textbox"  value="" vtype="rangeLength:12,12" allowInput="true"/></td>
	 		<td><a id="fromrunnum" class="mini-button" iconCls="" onclick="fromRunnum()">补充</a>
	 		</td>
	 		<td><a id="print" class="mini-button" iconCls="" onclick="print()" width="">打印</a></td>
	 	</tr>
	 	<tr>
	 		<td><label>审理单单号</label></td>
	 		<td><input id="state"  name="state" class="mini-textbox"  value="" vtype="rangeLength:12,12" allowInput="true"/></td>
	 		<td><a id="fromState" class="mini-button" iconCls="" onclick="fromState()">查询</a>
	 		</td>
	 	</tr>
	 </table>
	 <table>
	 	<tr>	
	 		<td><label>责任人</label></td>
 			<td>
 				<input id="duty_man" name="duty_man" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="DUTY_MAN" required="false" value="default" text="" allowInput="false"/>
 			</td>
			<td><label>责任部门</label></td>
 			<td>
 				<input id="duty_part" name="duty_part" class="mini-buttonedit" width="" onbuttonclick="onButtonEdit2" required="true" value="qualitycheck" text="" allowInput="false"/>
 			</td>
 			
			
	 		
	 		<td><label>返修工时</label></td>
 			<td><input id="time_loss"  name="time_loss" class="mini-textbox" allowInput="true" allowInput="false"/></td>
 		</tr>
 		
		<tr>
			<td><label>零件名称</label></td>
 			<td><input id="product_name"  name="product_name" class="mini-textbox authority" allowInput="false"/></td>
 			
 			<td>
 				<label>零件图号</label>
 			</td>
 			<td>
 				<input id="drawingid"  name="drawingid" class="mini-textbox authority" allowInput="false"/>
 			</td>
 			
 			<td><label>施工卡号</label></td>
 			<td><input id="operate_card"  name="operate_card" class="mini-textbox" allowInput="false" /></td>
 		</tr>
 		
 		<tr>
 			<td><label>故障工序</label></td>
 			<td><input id="rejectFO"  name="rejectfo" class="mini-textbox" allowInput="true" allowInput="false"/>
 				<input id="fo_no"  name="fo_no" class="mini-textbox" allowInput="false" style="visibility:hidden; width:2px;" enabled="false"/>
 			</td>
 			
 			<td><label>返修数</label></td>
 			<td><input id="reject_num"  name="reject_num" class="mini-textbox"  allowInput="false"/></td>
 		</tr>
 		
 		<tr>
 			
 		</tr>
	</table>
	<div class="describle">
		<label>废品发生原因(点击图片查看)</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img id="img" src="" alt="" width="580px" height="100px"  onclick="showImg()"/>
	</div>
	
	<div class="describle">
		<label>修正方法</label> </br>
		<textarea id="designerin" name="designerin" class="mini-textarea" width="100%" height="100px" emptyText="请输入备注" enabled="true"></textarea>
		</br>
		<span class="sign"><label>工艺员</label>
		<input id="designer" name="designer" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="designer" required="true" value="default" text=""/>
		
	</div>
	
	<table>
		<tr>
 			<td>
 				<label>发生日期</label>
 			</td>
 			<td>
				<input id="checkdate" name ="checkdate" class="mini-datepicker"  allowInput="true"/>
 			</td>
 			
 			<td><label>责任代表</label></td>
 			<td>
 			<input id="dutyer" name="dutyer" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="dutyer" required="false" value="default" text=""/>
            		</td>
 			
 			<td><label>检验员</label></td>
 			<td>
 			  <input id="checker" name="checker" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="checker" required="false" value="default" text=""/>  
 			</td>
 		</tr>
 		<tr>
 			<td>
 				<label>返回日期</label>
 			</td>
 			<td>
				<input id="returndate" name ="returndate" class="mini-datepicker" value="" allowInput="true"/>
 			</td>
 			
 			<td><label>返修工作者</label></td>
 			<td>
 				<input id="fixer" name="fixer" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="fixer" required="false" value="default" text=""/>
 			</td>
 			
 			<td><label>检验室主任</label></td>
 			<td>
 				<input id="checkheader" name="checkheader" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="checkheader" required="false" value="default" text=""/>
 			</td>
 		</tr>
 		<tr>
 			<td>
	 			<a id="designerbutton" class="mini-button" iconCls="" onclick="save()">保存</a>
	 			
 			</td>
 			<td></td>
 			<td></td><td></td><td></td>
 			<td>
 				<a id="save" class="mini-button" onclick="submit()">审核结束</a>
 			</td>
 		</tr>
 	</table>	
	</form>
</div>

<div id="editWindow2" class="mini-window" title="不合格原因" style="width:820px;height:600px;" 
    showModal="true" allowResize="true" allowDrag="true" ondestroy = "" >
	<img id="img2" src="" alt="" width="800px" height="500px">
</div>

</body>	
<script type="text/javascript">
	function setData(data){
		var form = new mini.Form("form1");
        var data = mini.clone(data);
        form.setData(data);
        $("#img").attr("src" ,data.imgurl);
        mini.get("duty_man").setText(data.duty_mans);
        mini.get("checker").setText(data.checker);
        mini.get("duty_part").setText(data.duty_parts);
        
        mini.get("duty_man").setValue(data.duty_man);
        mini.get("checker").setValue(data.checker);
        mini.get("duty_part").setValue(data.duty_part);
        
       // mini.get("fixer").setValue(data.duty_man);
        // mini.get("duty_part").setValue(data.duty_part);
        
        $.ajax({
        	type: "post",
            url: "ToFixTable?staterunnum="+data.staterunnum,	//runnum 是不合格品审理单的runnum 
            cache: false,
            success: function (text) {
                mini.get("runnum").setValue(text);
                
            },
            error:function (){
            	alert("操作失败 ");
            	 CloseWindow("cancel");
            	
            }
       });
	}
	function loadData(data){
		var form = new mini.Form("form1");
        var json = mini.clone(data);
        $.ajax({
        	type: "post",
            url: "FromWaitFix?runnum=" +json.runnum+"&barcode="+json.barcode+"&fo_no="+json.fo_no,	//barcode 用来做审理单号 
            cache: false,
            success: function (text) {
            	var data=mini.decode(text);
                form.setData(data);
                $("#img").attr("src",data.describle);
                mini.get("duty_man").setText(data.duty_man);
                mini.get("designer").setText(data.designer);
                mini.get("dutyer").setText(data.dutyer);
                mini.get("checkheader").setText(data.checkheader);
        		mini.get("checker").setText(data.checkerid);
        		mini.get("fixer").setText(data.fixer);
        		
        		mini.get("duty_man").setValue(data.duty_manid);
        		mini.get("designer").setValue(data.designerid);
        		mini.get("dutyer").setValue(data.dutyerid);
        		mini.get("checkheader").setValue(data.checkheaderid);
        		mini.get("checker").setValue(data.checkerid);
        		mini.get("fixer").setValue(data.fixerid);
        		 mini.get("duty_part").setText(data.duty_parts);
        
       			 mini.get("duty_part").setValue(data.duty_part);
            }
       });
	}
	function save(){
	//每个人提交保存 
		var form = new mini.Form("form1");
		var data = form.getData();
		//9-6
		data.returndate = mini.get("returndate").getFormValue();
		data.checkdate = mini.get("checkdate").getFormValue();
		
		data.designer =mini.get("designer").getValue();
		data.dutyer = mini.get("dutyer").getValue();
		var fixer = mini.get("fixer").getValue();
		data.fixer = fixer;
		data.checkheader = mini.get("checkheader").getValue();
		data.checker = mini.get("checker").getValue();
	//向 todiscard表存入数据,保存报废单详情  
		var json = mini.encode([data]);
		$.ajax({
			type:"post",
			url: "FixcardTable",
			cache: false,
			data: {data:json},
			success: function (text){
				alert("操作成功 ");
			}
		});
	}
	function submit(){
	// 最后一人提交，报废单处理状态修改  
		var form = new mini.Form("form1");
		var data = form.getData();
		
		$.ajax({
			type:"post",
			url: "EndFixcardTable",
			cache: false,
			data: {data:data},
			success: function (text){
				alert("操作成功 ");
			}
		});
	}
	
	function onButtonEdit(e) {
         var btnEdit = this;
         mini.open({
             url: "qualitycheck/selectEmployeeWindow.jsp",
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
                         btnEdit.setValue(data.staff_code);
                         btnEdit.setText(data.staff_name);
                     }
                 }
             }
         });
     }
     
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
        
     function showImg(){
		var editWindow = mini.get("editWindow2");
		editWindow.show();
		var url = $('#img').attr("src");
		$('#img2').attr("src",url);
	}
	
	function fromRunnum(){
    	$.ajax({
			type:"post",
			url: "addPartsPlan",
			cache: false,
			success: function (text){
				alert("操作成功 ");
			}
		});
    }
    function fromState(){
    	var state = mini.get("state").getValue();
    	var data = {"runnum":"",
    				"barcode":state,
    				"fo_no":"",
    			};
    	loadData(data);
    }
     function CloseWindow(action) {            
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
    function print(){
     	var runnum = mini.get("runnum").getValue();
   		window.open("ShowFixTable?runnum=" + runnum,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
     }
</script>
</html>