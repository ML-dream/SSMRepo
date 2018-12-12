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
   
    <title>废品单打印</title>
    <style type="text/css">
    </style>
</head>
<body>
<div style="width:600px; margin:0 auto; border:2px solid #6D6D6D;">
	<h1>废品单</h1>
	<form id="form1">
	<table>
		<tr>
			<td><label>单号</label></td>
	 		<td><input id="runNum"  name="runnum" class="mini-textbox"  value="${runNum}" enabled="true" allowInput="false" /></td>
	 	</tr>
	 	<tr>	
 			<td>
 				<label>发生日期</label>
 			</td>
 			<td>
				<input id="checkdate" name ="checkdate" class="mini-datepicker" readonly/>
 			</td>
 			
 			<td><label>责任部门</label></td>
 			<td>
 				<input id="duty_part" name="duty_part" class="mini-buttonedit" width="" onbuttonclick="onButtonEdit2" readonly/>
 			</td>
 			<td><label>责任人</label></td>
 			<td>
 				<input id="duty_man" name="duty_man" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="duty_man" readonly/>
 			</td>
 		</tr>
 		
		<tr>
			<td><label>零件名称</label></td>
 			<td><input id="PRODUCT_NAME"  name="product_name" readonly class="mini-textbox authority" /></td>
 			
 			<td>
 				<label>零件图号</label>
 			</td>
 			<td>
 				<input id="DRAWINGID"  name="drawingid"  class="mini-textbox authority" readonly/>
 			</td>
 			
 			<td><label>施工卡号</label></td>
 			<td><input id="OPERATE_CARD"  name="operate_card" class="mini-textbox" readonly/></td>
 		</tr>
 		
 		<tr>
 			<td><label>故障工序</label></td>
 			<td><input id="rejectFO"  name="rejectfo" class="mini-textbox"  readonly>
 				<input id="fo_no"  name="fo_no" class="mini-textbox" allowInput="false" style="visibility:hidden; width:2px;"/>
 			</td>
 			
 			<td><label>不合格数</label></td>
 			<td><input id="REJECT_NUM"  name="reject_num"  class="mini-textbox" readonly/></td>
 			<td><label>质量损失</label></td>
 			<td><input id="TIME_LOSS"  name="time_loss" class="mini-textbox" allowInput="false" readonly/></td>
 			
 		</tr>
 		
 		<tr>
 			<td><label>废品残值</label></td>
 			<td><input id="recvalue"  name="recvalue" class="mini-textbox"  readonly/></td>
 		</tr>
	</table>
	<div class="describle">
		<label>残值描述</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<textarea id="recinput" name="recinput" class="mini-textarea" width="100%" height="100px" emptyText="残值用途" readonly></textarea>
		</br>
		<label>废品发生原因(点击图片查看)</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img id="img" src="" alt="" width="580px" height="100px" onclick="showImg()"/>
	</div>
	<table>
		<tr>
 			<td><label>责任部门负责人</label></td>
 			<td>
 				<input id="dutyparter" name="dutyparter" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="dutyparter" required="false" readonly text=""/>
 			</td>
 			
 			<td><label>责任班组长</label></td>
 			<td>
 				<input id="dutygrouper" name="dutygrouper" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="dutygrouper" required="false" readonly text=""/>
 			</td>
 			<td><label>财务</label></td>
 			<td>
 				<input id="coster" name="coster" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="coster" required="false" readonly text=""/>
 			</td>
 		</tr>
 		<tr>
 			<td><label>品质部负责人</label></td>
 			<td>
 				<input id="superviser" name="superviser" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="superviser" required="false" readonly text=""/>
 			</td>
 			
 			<td><label>检验员</label></td>
 			<td>
 				<input id="checker" name="checker" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="checker" required="false" readonly text=""/>
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
	mini.parse();
	fromRunnum();
	function setData(data){
		var form = new mini.Form("form1");
        var data = mini.clone(data);
        form.setData(data);
        $("#img").attr("src" ,data.imgurl);
         mini.get("duty_man").setValue(data.duty_man);
          mini.get("checker").setValue(data.checker);
          mini.get("duty_man").setText(data.duty_mans);
        mini.get("duty_part").setText(data.duty_parts);
        
        mini.get("duty_man").setValue(data.duty_man);
        mini.get("duty_part").setValue(data.duty_part);
        var checkdate = mini.get("checkdate").getFormValue();
        $.ajax({
        	type: "post",
            url: "ToDiscardTable?staterunnum="+data.staterunnum+"&checkdate='"+checkdate+"'",	//runnum 是不合格品审理单的runnum 
            cache: false,
            success: function (text) {
                mini.get("runNum").setValue(text);
            }
       });
	}
	function loadData(data){
		var form = new mini.Form("form1");
        var json = mini.clone(data);
        $.ajax({
        	type: "post",
            url: "FromWaitDiscard?runnum=" +json.runnum+"&barcode="+json.barcode+"&fo_no="+json.fo_no,	//barcode 用来做审理单号 
            cache: false,
            success: function (text) {
            	var data=mini.decode(text);
                form.setData(data);
                $("#img").attr("src",data.imgurl);
                mini.get("duty_man").setText(data.duty_man);
        		mini.get("checker").setText(data.checker);
        		mini.get("duty_part").setText(data.duty_part);
        		
        		mini.get("dutyparter").setText(data.dutyparter);
				mini.get("dutygrouper").setText(data.dutygrouper);
				mini.get("coster").setText(data.coster);
				mini.get("superviser").setText(data.superviser);
				mini.get("checker").setText(data.checkerid);
				
        		mini.get("duty_man").setValue(data.duty_manid);
        		mini.get("checker").setValue(data.checkerid);
       			mini.get("duty_part").setValue(data.duty_part);
       			
       			mini.get("dutyparter").setValue(data.dutyparterid);
				mini.get("dutygrouper").setValue(data.dutygrouperid);
				mini.get("coster").setValue(data.costerid);
				mini.get("superviser").setValue(data.superviserid);
				mini.get("checker").setValue(data.checkerid);
            }
       });
	}
	
	function save(){
		var form = new mini.Form("form1");
		var data = form.getData();
		//还有一个残值 
		data.dutyparter = mini.get("dutyparter").getValue();
		data.dutygrouper = mini.get("dutygrouper").getValue();
		data.coster = mini.get("coster").getValue();
		data.superviser = mini.get("superviser").getValue();
		data.checker = mini.get("checker").getValue();
		data.checkdate =  mini.get("checkdate").getFormValue();
		var json= mini.encode([data]);
	//向 todiscard表存入数据,保存报废单详情  
		$.ajax({
			type:"post",
			url: "DiscardTable",
			cache: false,
			data: {data:json},
			success: function (text){
				alert ("操作成功");
			}
		});
	}
	//最后一人提交 
	function submit(){
	// 最后一人提交，报废单处理状态修改  
		var runnum =mini.get("runNum").getValue();
		
		$.ajax({
			type:"post",
			url: "EndDisTable?runnum="+runnum,
			cache: false,
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
	   	var runnum = mini.get("runNum").getValue();
	   	var data = {"runnum":runnum,
	   				"barcode":"",
	   				"fo_no":"",
	   			};
	   	loadData(data);
    }
     function fromState(){
    	var state = mini.get("state").getValue();
    	var data = {"runnum":"",
    				"barcode":state,
    				"fo_no":"",
    			};
    	loadData(data);
    }
</script>
</html>