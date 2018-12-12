<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
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
		<title>不合格品审理单</title>
		
		<style type="text/css">
			.describle{
				height: 150px;
				
				border-top: 2px solid #6D6D6D;
			}
			.other{
				height: 100px;
				
				border-top: 2px solid #6D6D6D;
			}

            .content{ width:580px; height:120px; border:1px solid #ccc; margin: 0 auto;}
			canvas{width:580px;height:120px;}
		</style>
</head>
<body onselectstart="return false;">
<div style="width:600px; margin:0 auto; border:2px solid #6D6D6D;">
	<h1>不合格品审理单</h1>
<form id="form1">
	<table>
		<tr>
 			<td>
 				<label>审理级别</label>
 			</td>
 			<td>
 				<input id="checkrank"  name="checkrank" class="mini-textbox " readonly/>
 			</td>
 			<td><label>审理单号</label></td>
 			<!-- 待矫正 -->
 			<td><input id="runNum"  name="runNum" class="mini-textbox"   vtype="rangeLength:12,12" readonly value="${runNum}"/></td>
 		</tr>
	</table>
	
	<table>
		<tr style="width:100%;">
 			<td>
 				<label>产品型号</label>
 			</td>
 			<td>
 				<input id="DRAWINGID"  name="drawingid" class="mini-textbox authority" readonly/>
 			</td>
 			<td><label>零件名称</label></td>
 			<td><input id="PRODUCT_NAME"  name="product_name" class="mini-textbox authority" readonly/></td>
 			<td><label>批次号</label></td>
 			<td style="width:30%;"><input id="BATCHCODE"  name="batchcode" class="mini-textbox authority" width="100%" readonly/></td>
 		</tr>
 		
 		<tr>
 			<td>
 				<label>发生日期</label>
 			</td>
 			<td>
				<input id="checkdate" name ="checkdate" class="mini-datepicker" readonly/>
 			</td>
 			<td><label>发现部门</label></td>
 			<td>
 				<input id="rejectad" name="rejectad" class="mini-textbox" readonly />
 			</td>
 			<td><label>故障工序</label></td>
 			<td><input id="rejectFO"  name="rejectfo" class="mini-textbox" readonly width="100%"/>
 				<input id="fo_no"  name="fo_no" class="mini-hidden" allowInput="false" />
 			</td>
 		</tr>
 		
 		<tr>
 			<td>
 				<label>投产总数</label>
 			</td>
 			<td>
 				<input id="INPUTNUM"  name="inputnum" class="mini-textbox" readonly/>
 			</td>
 			<td><label >不合格数</label></td>
 			<td><input id="REJECT_NUM"  name="reject_num" class="mini-textbox" readonly/></td>
 			<td><label>施工卡号</label></td>
 			<td><input id="OPERATE_CARD"  name="operate_card" class="mini-textbox" readonly width="100%"/></td>
 		</tr>
 		
 		<tr>
 			<td>
 				<label >产品来源</label>
 			</td>
 			<td>
			    <input id="prosource"  name="prosource" class="mini-textbox " readonly/>
			</div>
 			<br></td>
 			<td><label>责任部门</label></td>
 			<td>
 				<input id="duty_part" name="duty_part" class="mini-textbox" readonly/>
 			</td>
 			<td><label>责任人</label></td>
 			<td>
 				<input id="duty_man" name="duty_man" class="mini-textbox" readonly width="100%"/>
 			</td>
 		</tr>
 		
 		<tr>
 			<td><label>质量损失</label></td>
 			<td><input id="TIME_LOSS"  name="time_loss" class="mini-textbox" readonly/></td>
 			<!--  
 			<td><label>材料损失</label></td>
 			<td><input id="MATERIAL_LOSS"  name="material_loss" class="mini-textbox" allowInput="true" value= "0"/></td>
 			-->
 			<td></td>
 			<td><input id="barcode"  name="barcode" class="mini-textbox" allowInput="false" style="visibility:hidden;width:2px;"/>
 				<input id="lastfo"  name="lastfo" class="mini-textbox" allowInput="false" style="visibility:hidden; width:2px;"/>
 			</td>
 			
 		</tr>
	</table>
</form>
<form id="form2">	
	<div class="describle" style="height:160px;">
		<label >不合格品产生原因:</label> 
		<input id="rejecttype" class="mini-combobox" style="width:80px;" textField="text" valueField="id" 
   						 url="qualitycheck/json/rejecttype.txt" value="1" showNullItem="false"  allowInput="false"/>
		
		<img id="img" src="" alt="" width="580px" height="100px" onclick="showImg()"/></br>
		<span><label>检验员</label>
		<input id="checker" name="checker" class="mini-textbox" enabled="false"/></span>
	</div>
	
	<div class="other">
		<label>不合格品原因分析、纠正或预防措施</label> </br>
		<textarea id="securityin" name="securityin" class="mini-textarea" width="100%" height="50%" emptyText="" enabled="true"></textarea>
		</br>
		<span class="sign"><label>生产安全部负责人</label>
		<input id="security" name="security" class="mini-textbox" enabled="false"/> </span>
	</div>
	
	<div class="other">
		<label>对产品使用性能影响的分析（仅适合自己设计生产）</label> </br>
		<textarea id="designerin" name="designerin" class="mini-textarea" width="100%" height="50%" emptyText="" enabled="true"></textarea>
		</br>
		<span class="sign"><label>产品主管工艺员</label>
		<input id="designer" name="designer" class="mini-textbox" enabled="false"/></span>
	</div>
	
	<div class="other">
		<label>市场推广部意见（仅适合外协加工产品）</label> </br>
		<textarea id= "marketerin" name="marketerin"class="mini-textarea" width="100%" height="50%" emptyText="" enabled="true"></textarea>
		</br>
		<span class="sign"><label>市场推广部主任</label>
		<input id="marketer" name="marketer" class="mini-textbox" enabled="false"/></span>
	</div>
	
	<div class="other">
		<label>对纠正预防措施有效性的督察检查</label> </br>
		<textarea id="superviserin" name="superviserin" class="mini-textarea" width="100%" height="50%" emptyText="" enabled="true"></textarea>
		</br>
		<span class="sign"><label>品质部负责人</label>
		<input id="superviser" name="superviser" class="mini-textbox" enabled="false"/></span>
	</div>
	
	<div class="other" style="height:120px">
		<label>审理意见</label> 
		<input id="opinion"  name="opinion" class="mini-textbox " enabled="false"/>	
		<textarea id="opinionText" name="opiniontext" class="mini-textarea" width="100%" height="50%" emptyText="" enabled="true"></textarea>
		</br>
		<span class="sign"><label>主管领导</label>
		<input id="opinioner" name="opinioner" class="mini-textbox" enabled="false"/></span>
		<input id="opinionerstate" name="opinionerstate" class="mini-textbox" enabled = "false" style="visibility:hidden;"/>
	</div>
	
	<div class="other">
		<label>返工/返修复检结果</label> </br>
		<textarea id="recheckerin" name="recheckerin" class="mini-textarea" width="100%" height="50%" emptyText="" enabled="true"></textarea>
		</br>
		<span class="sign">
		<label>检验员</label>
		<input id="rechecker" name="rechecker" class="mini-textbox" enabled="false"/></span>
	</div>
</form>	  
</div>  
</body>
<script type="text/javascript">
	mini.parse();
	var barcode ="";
	var fo_no  ="";
	var ishandle=0;
	fromRunnum();
	function describle(){
		var editWindow = mini.get("editWindow");
		editWindow.show('left','top');
	}
	function showImg(){
		var editWindow = mini.get("editWindow2");
		editWindow.show();
		var url = $('#img').attr("src");
		$('#img2').attr("src",url);
	}
	

	function SetData(data) {
		var form = new mini.Form("form1");
        var data = mini.clone(data);
		barcode = data.barcode;
		fo_no = data.fo_no;
		
        $.ajax({
        	type: "post",
            url: "ToStateJudge?fo_no=" + fo_no+"&barcode="+barcode,
            cache: false,
            success: function (text) {
                var o = mini.decode(text);
                mini.get("runNum").setValue(o.runnum);
                form.setData(o);
                mini.get("checker").setValue(o.user);
                mini.get("duty_part").setValue(o.duty_part);
                mini.get("duty_man").setValue(o.duty_man);
                mini.get("duty_part").setText(o.duty_partn);
                mini.get("duty_man").setText(o.duty_mann);
                editControl(o.user);
            }
       });
     }
     function loadData(data){
     	var form = new mini.Form("form1");
     	var form2 = new  mini.Form("form2");
        //var data = mini.clone(data);
		var runnum = data.runnum;
		barcode = data.barcode;
		fo_no = data.fo_no;
		//从待处理页面进入 
		$.ajax({
			type: "post",
			url: "FromPrintPage?runnum=" +runnum+"&barcode="+data.barcode+"&fo_no="+data.fo_no,
			cache: false,
			success: function(text){
				var jsondata=mini.decode(text);
				mini.get("prosource").setValue(jsondata.prosource);
                mini.get("checkrank").setValue(jsondata.checkrank);
				form.setData(jsondata);
				form2.setData(jsondata);
				$("#img").attr("src",jsondata.img);
				 mini.get("runNum").setValue(jsondata.runnum);
				 mini.get("opinion").setValue(jsondata.opinion);
				 //9-20
				// mini.get("checker").setValue(jsondata.user);
				 //9-20
               	 mini.get("duty_part").setValue(jsondata.duty_part);
                 mini.get("duty_man").setValue(jsondata.duty_mann);
               	  mini.get("rejectad").setValue(jsondata.occurplace);
               	 // mini.get("duty_man").setText(jsondata.duty_mann);
				 fo_no = jsondata.fo_no;
				 barcode = jsondata.barcode;
				 if(jsondata.opinion != "empty"){
				 	ishandle=1;			//已审核
				 	mini.get("opinionerbutton").disable ( ); 
				 }
				 
			}
		});
     }   
     
     function CloseWindow(action) {            
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        
		function canceledit(){
			 CloseWindow("cancel");
		}
		
    function fromRunnum(){
    	var runnum = mini.get("runNum").getValue();
    	var data = {"runnum":runnum,
    				"barcode":"",
    				"fo_no":"",
    			};
    	loadData(data);
    }
    //权限控制怎么弄 
    function editControl(user){
    	
    }
    //待矫正  如果修改审核结果 怎么办？ 
    function opinioner(para){
    	var opinion = mini.get("opinion").getValue();
    	
    	var opiniontext = mini.get("opinionText").getValue();
    	var runnum = mini.get("runNum").getValue();
    	
    	var lastfo = mini.get("lastfo").getValue();
    	var para = para;	//参数 ，判断 是修改还是 提交 ,0为 提交，1为修改 
    	$.ajax({
    		type:"post",
    		url: "OpinionSave",
    		cache: false,
    		data: {"opinion" : opinion,"opiniontext" : opiniontext,"fbarcode":barcode,"fo_no":fo_no,"lastfo" : lastfo,"runnum":runnum,"para":para },
    		success: function(text){
    			//说明主管领导已经审核 
    			mini.get("opinionerstate").setValue("1");
    			alert("操作成功");
    			if(text == "1"){
    				
			    }
    		}
    	});
    	if(opinion == "toFix" || opinion =="toRedo"){
    					toFix(barcode,fo_no,runnum,ishandle);}
    				else if(opinion =="toDiscard"){
			    		//var url = "NewBatch?fbarcode="+barcode;
			    		toDiscard(barcode,fo_no,runnum,ishandle);
			    	}	//报废
			    	else {}								//超差使用 
    }
    //其他意见保存 
    function submit(para){
    	var user = para;
    	var usertext = para +"in";
    	var opinioner = mini.get(user).getValue();
    	var opinionerin = mini.get(usertext).getValue();
    	var runnum = mini.get("runNum").getValue();
    	// 9-6 审理意见，如果超差或报废  可能没有 recheck 
    	var result = mini.get("opinion").getValue();
    	$.ajax({
    		type: "post",
    		url: "SubmitSave?runnum="+runnum,
    		datatype: "json",
    		data:{opiniontext:opinionerin , opinion:opinioner ,para:user, result: result},
    		cache: false,
    		success: function (text){
    			alert("操作成功");
    		},
    		error: function (){}
    	});
    }
    
    function toFix(barcode,fo_no,runnum,ishandle){
    //生成子卡条码号 
			$.ajax({
        		type: "post",
        		url:"ToFix?fbarcode="+barcode+"&fo_no="+fo_no+"&runnum="+runnum+"&ishandle="+ishandle,
        		datatype:"json",
        		cache: false,
        		processData: false,
    			contentType: false,
    			success:function(text){
						if(text=="success"){
							CloseWindow("ok");
						}
    			},
    			error : function() {
					//alert("加载失败, 错误码：" + jqXHR.responseText);
				}
        	});
		}
	function toDiscard(barcode,fo_no,runnum,ishandle){
	//生成子卡条码号 
		$.ajax({
    			type: "post",
    			url: "ToDiscard?fbarcode="+barcode+"&fo_no="+fo_no+"&runnum="+runnum+"&ishandle="+ishandle,
    			cache: false,
    			success: function(text){},
    			error:	function(){}
    		});
	}
    //若处理成功，提醒该不合格审理单不再弹出。 
    //生成报废单 
    function discardtable(para){
    
    	var opinion = mini.get("opinion").getValue();
    	
    	if(para == opinion ){
			var form = new mini.Form("form1");
	    	var data = form.getData();
	    	var imgurl = $('#img').attr("src");
	    	data.imgurl = imgurl;
	    	var checker =mini.get("checker").getValue();
	    	data.checker = checker;
	    	var runnum = mini.get("runNum").getValue();
	    	data.staterunnum =runnum;
	    	
	    	data.duty_man = mini.get("duty_man").getValue();
	    	data.duty_mans = mini.get("duty_man").getText();
	    	data.duty_part =  mini.get("duty_part").getValue();
	    	data.duty_parts =  mini.get("duty_part").getText();
	    	
	    	
	    	mini.open({
			    url: "qualitycheck/discardTable.jsp",
			    title: "返修单", 
			    width: 840, height: 660,
			    onload: function () {
			    	var iframe = this.getIFrameEl();
	               	iframe.contentWindow.setData(data);
			    },
			    ondestroy: function (action){
			   }
			});	
	}else{
		alert ("此操作不符合审理结果 ");
	}
}
	function fixtable(para){
		var opinion = mini.get("opinion").getValue();
    	
    	if(para == opinion ){
			var form = new mini.Form("form1");
	    	var data = form.getData();
	    	var imgurl = $('#img').attr("src");
	    	data.imgurl = imgurl;
	    	var checker =mini.get("checker").getValue();
	    	data.checker = checker;
	    	var runnum = mini.get("runNum").getValue();
	    	data.staterunnum =runnum;
	    	
	    	data.duty_man = mini.get("duty_man").getValue();
	    	data.duty_mans = mini.get("duty_man").getText();
	    	data.duty_part =  mini.get("duty_part").getValue();
	    	data.duty_parts =  mini.get("duty_part").getText();
	    	
	    	
	    	mini.open({
			    url: "qualitycheck/fixtable.jsp",
			    title: "返修单", 
			    width: 840, height: 660,
			    onload: function () {
			    	var iframe = this.getIFrameEl();
	               	iframe.contentWindow.setData(data);
			    },
			    ondestroy: function (action){
			   }
			});	
		}else{
			alert ("此操作不符合审理结果 ");
		}									
	}
	
    	
    
    //测试，查找新条码号 
   function showNbarcode(){
   		var runnum = mini.get("runNum").getValue();
   		window.open("ShowNbarcode?runnum=" + runnum,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
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
        //责任人选择 
        function onButtonEdit(e) {
         var btnEdit = this;
         var url ="qualitycheck/selectEmployeeWindow.jsp";
         var outUrl = "qualitycheck/selectOutAssistWindow.jsp";
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
                         btnEdit.setValue(data.staff_code);
                         btnEdit.setText(data.staff_name);
                     }
                 }else if(action =="outer"){
                 	mini.open({
                 		url: outUrl,
			             title: "选择列表",
			             width: 650,
			             height: 380,
			             ondestroy: function (action) {
			                 if (action == "ok") {
			                     var iframe = this.getIFrameEl();
			                     var data = iframe.contentWindow.GetData();
			                     data = mini.clone(data);    //必须
			                     if (data) {
			                         btnEdit.setValue(data.companyId);
			                         btnEdit.setText(data.companyName);
			                     }
			                 }
                 		}
                 	})
         //切换外协厂商 
                 }
             }
         });
     }
     function seefixtable(){
     	var opinion = mini.get("opinion").getValue();
    	var para ="toFix";
    	if(para == opinion ){
	     	var data = {
	     		runnum : "",
	     		barcode : "",
	     		fo_no: ""
	     	}
	     	var runnum = mini.get("runNum").getValue();
	    	data.barcode =runnum;
	    	
	    	mini.open({
			    url: "qualitycheck/fixtable.jsp",
			    title: "返修单", 
			    width: 840, height: 660,
			    onload: function () {
			    	var iframe = this.getIFrameEl();
	               	iframe.contentWindow.loadData(data);
			    },
			    ondestroy: function (action){
			   }
			});	
		}else{
			alert ("此操作不符合审理结果 ");
		}			
     }
     function seediscardtable(){
	     var opinion = mini.get("opinion").getValue();
    	var para = "toDiscard";
    	if(para == opinion ){	
	     	var data = {
	     		runnum : "",
	     		barcode : "",
	     		fo_no: ""
	     	}
	     	var runnum = mini.get("runNum").getValue();
	    	data.barcode =runnum;
	    	
	    	mini.open({
			    url: "qualitycheck/discardTable.jsp",
			    title: "返修单", 
			    width: 840, height: 660,
			    onload: function () {
			    	var iframe = this.getIFrameEl();
	               	iframe.contentWindow.loadData(data);
			    },
			    ondestroy: function (action){
			   }
			});
		}else{
			alert ("此操作不符合审理结果 ");
		}	
     }
</script>
</html>