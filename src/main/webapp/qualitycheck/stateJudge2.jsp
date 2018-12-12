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
		<title>当前不合格品审理单</title>
		
		<style type="text/css">
			.describle{
				height: 150px;
				
				border-top: 2px solid #6D6D6D;
			}

            .content{ width:580px; height:120px; border:1px solid #ccc; margin: 0 auto;}
			canvas{width:580px;height:120px;}
		</style>
</head>
<body onselectstart="return false;">
<div style="width:600px; margin:0 auto; border:2px solid #6D6D6D;">
	<h1>返修产品审理单</h1>
	
		<table>
		<tr>
 			<td>
 				<label>审理级别</label>
 			</td>
 			<td>
 				<div class="mini-radiobuttonlist authority" repeatItems="1" repeatLayout="table" repeatDirection="vertical"
				    textField="text" valueField="id" value="default" id="checkrank"
				    url="qualitycheck/json/gradeJson.txt" >
				</div>
 			</td>
 			<td><label>审理单号</label></td>
 			<!-- 待矫正 -->
 			<td><input id="runNum"  name="runnum" class="mini-textbox"  value=""  vtype="rangeLength:12,12" allowInput="true"/></td>
 			<td><a id="fromrunnum" class="mini-button" iconCls="" onclick="fromRunnum()">查询</a> </td>
 		</tr>
	</table>
<form id="form1">
	<table>
		<tr>
 			<td>
 				<label>产品型号</label>
 			</td>
 			<td>
 				<input id="DRAWINGID"  name="drawingid" class="mini-textbox authority" enabled="false"/>
 			</td>
 			<td><label>零件名称</label></td>
 			<td><input id="PRODUCT_NAME"  name="product_name" class="mini-textbox authority" enabled="false"/></td>
 			<td><label>批次号</label></td>
 			<td><input id="BATCHCODE"  name="batchcode" class="mini-textbox authority" value="default" enabled="false"/></td>
 		</tr>
 		
 		<tr>
 			<td>
 				<label>发生日期</label>
 			</td>
 			<td>
				<input id="checkdate" name ="checkdate" class="mini-datepicker" value="" allowInput="true" enabled="false"/>
 			</td>
 			<td><label>发现部门</label></td>
 			<td>
 				<input id="rejectad" name="rejectad" class="mini-buttonedit" width="" onbuttonclick="onButtonEdit2" allowInput="false" value="zjb" text="质检部"/>
 			</td>
 			<td><label>故障工序</label></td>
 			<td><input id="rejectFO"  name="rejectfo" class="mini-textbox" allowInput="false" />
 				<input id="fo_no"  name="fo_no" class="mini-textbox" allowInput="false" style="visibility:hidden; width:2px;"/>
 			</td>
 		</tr>
 		
 		<tr>
 			<td>
 				<label>投产总数</label>
 			</td>
 			<td>
 				<input id="INPUTNUM"  name="inputnum" class="mini-textbox" allowInput="false" enabled="false"/>
 			</td>
 			<td><label style= "color:red;">不合格数</label></td>
 			<td><input id="REJECT_NUM"  name="reject_num" class="mini-textbox" allowInput="true" vtype="int" required="true"/></td>
 			<td><label>施工卡号</label></td>
 			<td><input id="OPERATE_CARD"  name="operate_card" class="mini-textbox" allowInput="true" value="default"/></td>
 		</tr>
 		
 		<tr>
 			<td>
 				<label style= "color:red;">产品来源</label>
 			</td>
 			<td>
 				<div class="mini-radiobuttonlist" repeatItems="1" repeatLayout="table" repeatDirection="vertical"
				    textField="text" valueField="id" value="" id="prosource" name="prosource"
				    url="qualitycheck/json/source.txt" >
				</div>
 			<br></td>
 			<td><label>责任部门</label></td>
 			<td>
 				<input id="duty_part" name="duty_part" class="mini-buttonedit" width="" onbuttonclick="onButtonEdit2" allowInput="false" value="scb" text="生产部"/>
 			</td>
 			<td><label>责任人</label></td>
 			<td>
 				<input id="duty_man" name="duty_man" class="mini-buttonedit" width="" 
            		onbuttonclick="onButtonEdit" textName="DUTY_MAN" required="false" value="default" text="" allowInput="false"/>
 			</td>
 		</tr>
 		
 		<tr>
 			<td><label>质量损失</label></td>
 			<td><input id="TIME_LOSS"  name="time_loss" class="mini-textbox" allowInput="true" value= "0"/></td>
 			<!--  
 			<td><label>材料损失</label></td>
 			<td><input id="MATERIAL_LOSS"  name="material_loss" class="mini-textbox" allowInput="true" value= "0"/></td>
 			<td></td>
 			-->
 			<td><input id="barcode"  name="barcode" class="mini-textbox" allowInput="false" style="visibility:hidden;width:2px;"/>
 				<input id="lastfo"  name="lastfo" class="mini-textbox" allowInput="false" style="visibility:hidden; width:2px;"/>
 			</td>
 			
 		</tr>
	</table>
</form>
<form id="form2">	
	<div class="describle" style="height:160px;">
		<label style= "color:red;">不合格品产生原因:</label> 
		<input id="rejecttype" class="mini-combobox" style="width:80px;" textField="text" valueField="id" 
   						 url="qualitycheck/json/rejecttype.txt" value="1" showNullItem="false"  allowInput="false"/>
		&nbsp;&nbsp;&nbsp;&nbsp;<label>不合格品描述(点击图片查看):</label> &nbsp;&nbsp;
		<button id="checkerButton" class="mini-button" onclick="describle()" enabled="true">添加描述</button>
		
		<img id="img" src="" alt="" width="580px" height="100px" onclick="showImg()"/></br>
		<span><label>检验员</label>
		<input id="checker" name="checker" class="mini-textbox" enabled="false"/></span>
		<a id="opinionerbutton" class="mini-button" iconCls="" onclick="checkerUp" style= "color:red;">提交</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a id="editbutton" class="mini-button" iconCls="" onclick="checkerEdit">保存修改</a>
	</div>
	
</form>	
	<label >先提交审理单，然后生成返修单与打印流程卡。</label> 
	<div style="margin: auto auto auto 180px;">
		<a id="fixtable" class="mini-button" iconCls="" onclick="fixtable('toFix')">生成返修单</a>
		<a id="fixtable" class="mini-button" iconCls="" onclick="seefixtable()">查看返修单</a>
		<!--  <a id="debug" class="mini-button" onclick="debug()">调试</a> -->
		<a id="newbarcode" class="mini-button" onclick="showNbarcode()">打印流程卡</a>
	</div>
</div>


<!-- <div class="button" onclick="test()"><button>submit</button></div> -->

 <div id="editWindow" class="mini-window" title="质检输入" style="width:820px;height:600px;" 
    showModal="true" allowResize="true" allowDrag="true" ondestroy = "" >
    <div class="content" style = "width:800px;height:500px;">
            <canvas id="can" width="800" height="500" style = "width:800px;height:500px;"></canvas>
      </div> 
      <table>
      	<tr>
      		<td style ="width:100px"> </td>
      		<td style ="width:60px"><button onclick="erease()">橡皮擦</button></td>
      		<td style ="width:60px"> <button onclick="paint()">手写笔</button></td>
      		<td style ="width:80px"> <button onclick= "save()">保存</button> </td>
      		<td style ="width:80px">  <button onclick="clearAll()">擦除所有</button></td>
      	</tr>
      </table> 
</div>
<div id="editWindow2" class="mini-window" title="不合格品描述" style="width:820px;height:600px;" 
    showModal="true" allowResize="true" allowDrag="true" ondestroy = "" >
	<img id="img2" src="" alt="" width="800px" height="500px">
</div>
    
</body>
<script type="text/javascript">
	var barcode ="";
	var fo_no  ="";
	
	var  options = {     //定义画笔相关参数
                    lineWidth : 1,      //粗细
                    strokeStyle: '#000000' //颜色
                };
                
            var canvas = document.getElementById('can');
            var context = canvas.getContext('2d');
        (function(){
           /* var canvas = document.getElementById('can'),
                context= canvas.getContext('2d'),
                options = options,
                options = {     //定义画笔相关参数
                    lineWidth : 1,      //粗细
                    strokeStyle: '#000000' //颜色
                },*/
                events = {
                    mousedown: function(e){ //鼠标按下记住
                        this.isButtonDown = true;
                        context.beginPath();
                        context.moveTo( e.offsetX,e.offsetY )
                    },
                    mousemove:function(e){  //移动画图
                        var pos = { x:e.offsetX, y:e.offsetY };
											
                        if(this.isButtonDown){
							setTimeout(function(){
								context.lineTo(pos.x, pos.y);
	                            context.stroke();},0);
                            
                        }
                    },
                    mouseup:function(e){    //鼠标放开停止画图
                       this.isButtonDown = false; 
                    },
                    //双击事件 待矫正 
                    dblclick:function(e){
                		return false;
                	}
                };
				{
					context.strokeStyle ='#A5A2A2';//线条颜色：绿色
		   			context.lineWidth = 2;//设置线宽
		  			context.beginPath();
		  			context.moveTo(0,10);
		   			context.lineTo(800,10);
		   			context.moveTo(790,0);
		   			context.lineTo(790,500);
		   			context.closePath();//可以把这句注释掉再运行比较下不同
		  			context.stroke();//画线框
				}
            	for(var m = 0,n=7;m<n;m++){
            		// context.fillStyle ='#FFFFFF';
		   			context.strokeStyle ='#F2F2F2';//线条颜色：绿色
		   			context.lineWidth = 2;//设置线宽
		  			context.beginPath();
		  			var st = 60*(m+1);
		  			context.moveTo(0,st);
		   			context.lineTo(800,st);
		   			context.closePath();//可以把这句注释掉再运行比较下不同
		  			context.stroke();//画线框
		   			//context.fill();//填充颜色
            	}
   			
   			
            /*把画笔相关参数赋值*/
            for(var k in options){
                context[k] = options[k]
            }
            /*绑定相关事件*/
            for(var i in events){
                canvas.addEventListener(i,events[i]);
            }
        })();
        
        function erease(){
        	canvas.style.cursor= "text";
        	options = {     //定义画笔相关参数
                    lineWidth :18,      //粗细
                    //fillStyle:'#FFFFFF',
                    strokeStyle: '#FFFFFF' //颜色
                };
               /*把画笔相关参数赋值*/
            for(var k in options){
                context[k] = options[k]
            }  
        }
        
        function clearAll(){
        	var canvas = document.getElementById('can');
            var context = canvas.getContext('2d');
            context.beginPath();
            context.rect(0,0,800,500);
            context.fillStyle = "#FFFFFF";
            context.fill();
        }
        
        function paint(){
        	canvas.style.cursor= "default";
        	options = {     //定义画笔相关参数
                    lineWidth : 1,      //粗细
                    //fillStyle:'#FFFFFF',
                    strokeStyle: '#000000' //颜色
                };
               /*把画笔相关参数赋值*/
            for(var k in options){
                context[k] = options[k]
            }
        }
    
    function save(){
    //保存不合格品描述 
    	var runnum = mini.get("runNum").getValue();
    	var canvas = document.getElementById('can');
        var context = canvas.getContext('2d');
        // 图片导出为 png 格式
		var type = 'png';
		var imgData = canvas.toDataURL(type);
		//alert({image : imgData});
		$.ajax({
			type: "post",
			url: "SaveDescrible",
			data: {"image":imgData,"runnum":runnum},
			dataType: "json",
			async:false,
			cache:false,
			success: function (text){
				var editWindow = mini.get("editWindow");
				editWindow.hide();

				//$('#img').attr("src","qualitycheck/describe/0.png");
				var url = "qualitycheck/describe/"+runnum+".png?t="+Math.random();
				$('#img').attr("src",url);
			},
			error: function(){
				alert("error");
			}
		});
    }
    
	function test(){
		//$(".authority").attr("disabled","disabled");
		var form = new mini.Form("#form1");
		form.setEnabled(false);
		mini.get("#checkerButton").setEnabled(true);
		mini.get("#checker2").setEnabled(true);
	}
	
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
	
	function download(){
				
		/**
		 * 获取mimeType
		 * @param  {String} type the old mime-type
		 * @return the new mime-type
		 */
		var _fixType = function(type) {
		    type = type.toLowerCase().replace(/jpg/i, 'jpeg');
		    var r = type.match(/png|jpeg|bmp|gif/)[0];
		    return 'image/' + r;
		};
		   
		// 加工image data，替换mime type
		imgData = imgData.replace(_fixType(type),'image/octet-stream');
		
		/**
		 * 在本地进行文件保存
		 * @param  {String} data     要保存到本地的图片数据
		 * @param  {String} filename 文件名
		 */
		var saveFile = function(data, filename){
		    var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
		    save_link.href = data;
		    save_link.download = filename;
		   
		    var event = document.createEvent('MouseEvents');
		    event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
		    save_link.dispatchEvent(event);
		};
		   
		// 下载后的问题名
		var filename = runnum + '.' + type;
		// download 到本地 
		saveFile(imgData,filename);
		
	}
	function SetData(data) {
		var form = new mini.Form("form1");
        var data = mini.clone(data);
		barcode = data.barcode;
		fo_no = data.fo_no;
		
        $.ajax({
        	type: "post",
            url: "ToStateJudge2?fo_no=" + fo_no+"&barcode="+barcode,
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
     function SetData2(data) {
		var form = new mini.Form("form1");
        var data = mini.clone(data);
		barcode = data.barcode;
		fo_no = data.fo_no;
		
        $.ajax({
        	type: "post",
            url: "ToStateJudge2?fo_no=" + fo_no+"&barcode="+barcode,
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
                mini.get("REJECT_NUM").setValue(data.bnum);
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
			url: "FromWaitPage?runnum=" +runnum+"&barcode="+data.barcode+"&fo_no="+data.fo_no,
			cache: false,
			success: function(text){
				var jsondata=mini.decode(text);
				mini.get("prosource").setValue(jsondata.prosource);
                mini.get("checkrank").setValue(jsondata.checkrank);
				form.setData(jsondata);
				form2.setData(jsondata);
				$("#img").attr("src",jsondata.img);
				 mini.get("runNum").setValue(jsondata.runnum);
				 //9-20
				 mini.get("checker").setValue(jsondata.user);
				 //9-20
               	 mini.get("duty_part").setValue(jsondata.duty_part);
                 mini.get("duty_man").setValue(jsondata.duty_man);
               	 mini.get("duty_part").setText(jsondata.duty_partn);
               	 mini.get("duty_man").setText(jsondata.duty_mann);
               	 mini.get("rejectad").setText(jsondata.occurplace);
               	 mini.get("rejectad").setValue(jsondata.occurplacev);
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
		
    function checkerUp(){
    	var form = new mini.Form("form1");
    	
    	 form.validate();
         if (form.isValid() == false) {return;}

    	var data = form.getData();
    	var imgurl = $('#img').attr("src");
    	data.imgurl = imgurl;
    	var  checkrank = mini.get("checkrank").getValue();
    	data.checkrank =checkrank;
    	var  prosource = mini.get("prosource").getValue();
    	data.prosource = prosource;
    	var checker =mini.get("checker").getValue();
    	data.checker = checker;
    	var runnum = mini.get("runNum").getValue();
    	data.runnum = runnum;
    	var rejectad = mini.get("rejectad").getValue();
    	data.rejectad = rejectad;
    	
    	//9-20
    	var duty_man = mini.get("duty_man").getValue();
    	data.duty_man =duty_man;
    	var duty_part = mini.get("duty_part").getValue();
    	data.duty_part =duty_part;
    	//9-24  
    	var rejecttype = mini.get("rejecttype").getValue();	//不合格原因 
    	data.rejecttype = rejecttype;
    	
    	data.opinionstate =1;
    	data.cardType = "fix";	//审理单类型 
    	var json= mini.encode([data]);
    	
    	$.ajax({
    		type: "post",
    		url: "SaveRejectJudge",
    		cache: false,
    		data: {"data" : json},
    		success: function (text){
    			if(text != "0"){
    				window.open("ShowReject2?barcode="+barcode+"&fo_no="+fo_no+"&bnum="+text,
	                	"editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
    				toFix2(barcode,fo_no,runnum);
    			}else{
    				toFix2(barcode,fo_no,runnum);
    				alert ("操作成功 ");
    			}
    		}
    	})
    }
    function checkerEdit(){
    //质检修改 
    	var form = new mini.Form("form1");
    	form.validate();
         if (form.isValid() == false) {return;}
    	var data = form.getData();
    	var imgurl = $('#img').attr("src");
    	data.imgurl = imgurl;
    	var  checkrank = mini.get("checkrank").getValue();
    	data.checkrank =checkrank;
    	var  prosource = mini.get("prosource").getValue();
    	data.prosource = prosource;
    	var checker =mini.get("checker").getValue();
    	data.checker = checker;
    	var runnum = mini.get("runNum").getValue();
    	data.runnum = runnum;
    	//9-20
    	var duty_man = mini.get("duty_man").getValue();
    	data.duty_man =duty_man;
    	var duty_part = mini.get("duty_part").getValue();
    	data.duty_part =duty_part;
    	//9-24  
    	var rejecttype = mini.get("rejecttype").getValue();	//不合格原因 
    	data.rejecttype = rejecttype;
    	var rejectad = mini.get("rejectad").getValue();
    	data.rejectad = rejectad;
    	var json= mini.encode([data]);
    	$.ajax({
    		type: "post",
    		url: "UpdateRejectState",
    		cache: false,
    		data: {"data" : json},
    		success: function (text){
    			alert ("操作成功 ");
    			// CloseWindow("ok");
    		}
    	})
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
    
	 function toFix2(barcode,fo_no,runnum){
	 //生成子卡条码号  
		$.ajax({
       		type: "post",
       		url:"ToFix?fbarcode="+barcode+"&fo_no="+fo_no+"&runnum="+runnum+"&ishandle=0",
       		datatype:"json",
       		cache: false,
       		processData: false,
   			contentType: false,
   			success:function(text){
					if(text=="success"){
						//CloseWindow("ok");
					}
   			},
   			error : function() {
				//alert("加载失败, 错误码：" + jqXHR.responseText);
			}
       	});
	}
	
	function fixtable(para){
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
     }
</script>
</html>