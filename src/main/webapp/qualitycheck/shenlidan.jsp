<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
      <!-- miniui.js -->
  <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link> 
    
    <title>审理单</title>
    
	 <style type="text/css">
    	table#size_mark {
			font-family: verdana,arial,sans-serif;
			font-size:11px;
			color:#333333;
			border-width: 1px;
			border-color: #666666;
			border-collapse: collapse;}
		table#size_mark td {
			border-width: 1px;
			padding: 0px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
		}
    </style>
    

  </head>
  
  <body>
 
    <table >
   		<tr>
   			<td>操作选项</td>
			 <td><input value="打印当前流程卡" type="button" onclick="printCard()" /></td>
			 <td><input value="多批次分卡" type="button" onclick="mutiCard()" /></td>
   		</tr>
   		<tr>
   			<td>
   				<label>审理单类型</label>
   			</td>
   			<td>
   				<input id="cardtype" class="mini-combobox" style="" textField="text" valueField="id" 
  						 url="qualitycheck/json/newCard.txt" value="toFix" showNullItem="false"  allowInput="false"/>
   				
   			</td>
   		</tr>
   		<tr>	
   			<td><label>故障工序号</label></td>
   			<td><input id="Tofo_no"  name="tofo_no" class="mini-textbox" allowInput="true"/></td>
   			<td><input value="新建审理单" type="button" onclick="newCard()" /></td>
   			
   		</tr>
   	</table>
	
  
 
  </body>
  <script type="text/javascript">
		mini.parse();
		mini.get("piece_barcode").focus();
		
		var checked = 0;	//这个全局变量用于标志 所选工序是否质检,0代表未质检 
		var firstFo = 5;	//第一道工序 
		function setFirstFo(){	//设置第一道工序
			var grid = new mini.get("datagrid1");
			var row = grid.getRow(0);
			firstFo = row.fo_no;
		}
		function loadgrid(){
			mini.get("piece_barcode").blur();
			var grid = new mini.get("datagrid1");
			var headerForm = new mini.Form("#form1");
			var form0 = new mini.Form("form0");
    	 	form0.validate();
        	 if (form0.isValid() == false) {return;} 
        	 
			var mark =0;
         	var barcode = mini.get("piece_barcode").getValue();
         	
         	checked = 0;
         	
         	
        //	加载表头 
        	$.ajax({
        		type: "post",
        		url:"LoadCardHeader?barcode="+barcode,
        		cache: false,
    			async:false,
    			success:function(text){
    				var data = mini.decode(text);
    				mark = data.status;		// 根据工件状态判断查询方式 
    				//9-16
					headerForm.setData(data,true);
					grid.load({key:barcode,mark:mark});	
					//11-1
    			},
    			error : function() {
					//alert("加载失败, 错误码：" + jqXHR.responseText);
				}
        	});
		}
		//保存行编辑数据 
		function updateRow(){
			
			var barcode = mini.get("piece_barcode").getValue();
			var grid= mini.get("datagrid1");
			var form = new mini.Form('#editform');
			
			form.validate();
         	if (form.isValid() == false) {return;}
         	
			var data= form.getData();
			
			var sumNum = data.confirm_num;
			sumNum = parseInt(sumNum);
			var accept = data.accept_num;
			accept = parseInt(accept);
			var reject = data.reject_num;
			reject = parseInt(reject);
			var temp = accept + reject ;
			if(temp != sumNum){
				alert("送检数量有误");
				return;
			}
			data.barcode= barcode;
			data.checked = checked;
			data.workerid = mini.get("worker").getValue();
			var test  = mini.get("worker").getText();
			data.machine = mini.get("machine").getValue();
			var json= mini.encode([data]);
			
			//var colData = grid.getColumn("foNo");
			//判断是否是第一道工序 
			if (data.fo_no == firstFo && checked==1){
				var c = confirm("请确认是要修改该工序的数据 ");
				if (c==true){
				//	aidcontrol(json,checked,grid,barcode);
					aidcontrol(json,checked,grid,data);
					checked = 0; 
				}else{
					data.barcode = barcode;
	         		savedata(data);	//新建新批次流程卡
	         		checked = 0;
				}
			}else{
				aidcontrol(json,checked,grid,barcode,data);
           }
			var editWindow = mini.get("editWindow");
			
            editWindow.hide(); 
		}
		function addEmp(){
			//补充人员设备信息 
			var barcode = mini.get("piece_barcode").getValue();
			var form = new mini.Form('#editform');
			var data= form.getData();
			data.barcode= barcode;
			data.workerid = mini.get("worker").getValue();
			data.machine = mini.get("machine").getValue();
			var json= mini.encode([data]);
			$.ajax({
			 	type:"post",
                url: "AddEmp",
                data: { data: json },
                success: function (text) {
                	alert ("操作成功");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
		}
		
		//行点击效果 
		function rowselect(){
			var barcode = document.getElementById('piece_barcode').value;
			var grid= mini.get("datagrid1");
			var row = grid.getSelected();
			var editWindow = mini.get("editWindow");
			
			if(row){
				editWindow.show('right','middle');	//由于页面初始化时，给form赋过值，这里form是有初始值的 
	         	var editform= new mini.Form('#editform')
	         	editform.loading();
	         	var checker = row.checker;
	         	var checkdate = row.checkdate;
	         	//9-5 
	         	row.barcode = barcode;
	         	var json= mini.encode(row);
	         	var jdata= mini.decode(json);
	         	editform.setData(row,true);
	         	//9-1
	         	mini.get("worker").setValue(row.workerid);
				mini.get("machine").setValue(row.machineid);
				mini.get("Barcode").setValue(row.barcode);
				mini.get("worker").setText(row.workername);
				mini.get("machine").setText(row.machine);
				mini.get("ratedtime").setValue(row.rated_time);
				//9-6
				//var usedtime = row.rated_time * row.num;
				//mini.get("usedtime").setValue(usedtime);
	         	
	         	if(row.remark ==null){row.remark ="";}
	         	//判断检验栏是否有数据 
	         	if(row.accept_num != null  || row.checker != null || row.remark !="")
	         	{
	         	//返工件已在表中保存了一道数据，所以这里判断备注栏 
	         		checked = 1;
	         		
	         		var data= editform.getData();
	         		data.checker = checker;
	         		data.checkdate= checkdate;
					data.barcode= barcode;
					var json= mini.encode([data]);
			/*
		         	$.ajax({
		         		type:"post",
		                url: "SaveSession",
		                data: { data: json },
		                success: function (text) {
		                    //把这个json放到session中 ，判断是否已经质检 
		                   // checked = 0;
		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                    alert(jqXHR.responseText);
		                }
		         	});
		        */
	         	}else if(row.fo_no == firstFo){	//waitEdit
	         		
		         	//把工序1 放入 edit_control 表中 
		         		$.ajax({
			         		type:"post",
			                url: "EditControl?barcode="+barcode+"&fo_no="+row.fo_no+"&reject_num="+row.reject_num,
			                success: function (text) {
			                    //把这个json放到session中 ，判断是否已经质检 
			                   // checked = 0;
			                },
			                error: function (jqXHR, textStatus, errorThrown) {
			                    alert(jqXHR.responseText);
			                }
			         	});
		         	
	         		checked = 0;	//复位checked 
	         	}else {checked = 0;}
	         	editform.unmask();
		}
	}
	//取消编辑 
		function cancelRow() {
          // grid.reload();
          	var editWindow = mini.get("editWindow");
            editWindow.hide();
            checked = 0;
        }
		
		//多批次流程卡分卡
		function  mutiCard(){
			var barcode = mini.get("piece_barcode").getValue();
			if(barcode == null || barcode == ""){
				alert("请输入主流程卡卡号");
				return;
			}
			var data = {barcode:barcode};
			savedata(data);
		}
	//窗口显示
	function showAtPos() {
        var win = mini.get("win1");

        var x = "right";
        var y = "top";

        win.showAtPos(x, y);

    }
	showAtPos();
		// 工序1 出新增流程卡页面 
		function savedata(data) {
			mini.open({
			    url: "qualitycheck/mutiBatchCard.jsp",
			    title: "操作提示", 
			    width: 600, height: 250,
			    onload: function () {
			    	var iframe = this.getIFrameEl();
                    var iframedata = data;
                    iframe.contentWindow.SetData(iframedata);
			    },
			    ondestroy: function (action) {
			    	if(action != "cancel" && action!="close"){
				    	//mini.get("Grade").setValue("新增质检批次");
						mini.get("piece_barcode").setValue(action);
						loadgrid();
		         	}
			    }
			});
		}
		//辅助工序1 判断 ,保存行编辑数据 
		function aidcontrol(json,checked,grid,barcode,data){
			$.ajax({
			 	type:"post",
                url: "SaveForm",
                data: { data: json },
                success: function (text) {
                    
                    checked = 0;
                    if(text == "qualitycheck/mutiBatchCard.jsp"){
                   	mini.open({
					    url: text,
					    title: "操作提示", 
					    width: 600, height: 250,
					    onload: function () {
					    },
					    ondestroy: function (action) {
						if (action != "cancel"){
					    	// mini.get("Grade").setValue("新增质检批次");
							
							
					    }else {
					    	//grid.load({key:barcode,mark:1});		//不生成子表时的操作 
					   }
					   }
					});
                  }else if (text == "rejectOccured"){
                  		var add = "qualitycheck/rejectOccured.jsp";
                  		var grade = "";		//参数待定 
                  		mini.open({
						    url: add,
						    title: "操作提示", 
						    width: 720, height: 200,
						    onload: function () {
						    	 var iframe = this.getIFrameEl();
                       			 var iframedata = { barcode: barcode, fo_no: data.fo_no };
                       			 iframe.contentWindow.SetData(iframedata);
						    },
						    ondestroy: function (action) {
								if (action == "ok"){
									// 暂时没用 
									$.ajax({
						         		type: "post",
						         		url:"FindNbarcode?fbarcode="+barcode,		//父条码号 
						         		datatype:"json",
						         		cache: false,
						         		processData: false,
						    			contentType: false,
						    			success:function(text){
						    				var data = mini.decode(text);
						    				var attachBarcode = data.barcode;
						    				var grade = data.status;
											$('#piece_barcode').val(attachBarcode);
											mini.get("Grade").setValue(grade);
											loadgrid();
											//grid.load({key:attachBarcode,mark:1});  	// mark:1代表不合格品出现时查询方式  
						    			},
						    			error : function() {
											//alert("加载失败, 错误码：" + jqXHR.responseText);
										}
						         	});
							    }else if(action == "submit"){
							    		mini.open({
										    url: "qualitycheck/stateJudge.jsp",
										    title: "审理单", 
										    width: 840, height: 660,
										    onload: function () {
										    	var iframe = this.getIFrameEl();
                       							var iframedata = { barcode: barcode, fo_no: data.fo_no };
                       							iframe.contentWindow.SetData(iframedata);
										    },
										    ondestroy: function (action){
										    	checked =0;
										    	grid.load({key:barcode,mark:4});		//保存数据后的重新加载 ,4 对应 default状态
										   }
										});
							    }else if(action == "toFix"){
							    	//9-8 返修处理 
							    	mini.open({
										    url: "qualitycheck/stateJudge2.jsp",
										    title: "返修审理单", 
										    width: 840, height: 660,
										    onload: function () {
										    	var iframe = this.getIFrameEl();
                       							var iframedata = { barcode: barcode, fo_no: data.fo_no };
                       							iframe.contentWindow.SetData(iframedata);
										    },
										    ondestroy: function (action){
										    	checked =0;
										    	loadgrid();
										    	//grid.load({key:barcode,mark:4});		//保存数据后的重新加载 ,4 对应 default状态
										   }
										});
							    	//grid.load({key:barcode,mark:1}); }
							   	}
							   }
						 });
						 
                  		//openHelper(url,barcode);
                  }else {
                  	 grid.load({key:barcode,mark:4});		//保存数据后的重新加载 ,4 对应 default状态 
                  } 
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
		}
		
		//editwindow “X” 关闭时，checked 复位 
		function windowDestroy(){
			checked = 0;
		} 
		
		function newCard(){
			var type = mini.get("cardtype").getValue();
			var tfo_no = mini.get("Tofo_no").getValue();
			var barcode = mini.get("piece_barcode").getValue();
		//9-8
			if(type ==="toDiscard"){
				mini.open({
				    url: "qualitycheck/stateJudge.jsp",
				    title: "审理单", 
				    width: 1000, height: 800,
				    onload: function () {
				    	var iframe = this.getIFrameEl();
	            		var iframedata = { barcode: barcode, fo_no:tfo_no };
	    				iframe.contentWindow.SetData(iframedata);
				    },
				    ondestroy: function (action){
				   }
				});
			}else if(type =="toFix"){
			//9-8
				mini.open({
				    url: "qualitycheck/stateJudge2.jsp",
				    title: "返修审理单", 
				    width: 1000, height: 800,
				    onload: function () {
				    	var iframe = this.getIFrameEl();
						var iframedata = { barcode: barcode, fo_no: tfo_no };
						iframe.contentWindow.SetData(iframedata);
				    },
				    ondestroy: function (action){
				    	checked =0;
				   }
				});
			}
		}
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
		
     
     function selectMachine(e){
     	 var btnEdit = this;
         mini.open({
             url: "qualitycheck/selectMachine.jsp",
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
                         btnEdit.setValue(data.machineid);
                         btnEdit.setText(data.machinename);
                     }
                 }
             }
         });
     }
     //查看刀具信息 
    function  item(){
    	var mbarcode = mini.get("Barcode").getValue();
    	var mfo_no = mini.get("fo_no").getValue();
    	mini.open({
             url: "qualitycheck/selectItem.jsp",
             title: "选择列表",
             width: 500,
             height: 500,
             onload: function () {
			    	var iframe = this.getIFrameEl();
            		var iframedata = { barcode: mbarcode, fo_no:mfo_no };
    				iframe.contentWindow.SetData(iframedata);
			    },
             ondestroy: function (action) {
             }
         });
    }
     //完工，处理最后一道工序 ,9-1
     function finish(){
     	var barcode = mini.get("piece_barcode").getValue();
     	$.ajax({
         		type: "post",
         		url:"LastFoHandle?barcode="+barcode,		
         		cache: false,
         		processData: false,
    			contentType: false,
    			success:function(text){
    				alert(text);
    			},
    			error : function() {
					//alert("加载失败, 错误码：" + jqXHR.responseText);
				}
         	});
     }
     function printCard(){
     	var barcode = mini.get("piece_barcode").getValue();
   		window.open("FlowCardPrint?barcode=" + barcode,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
     }
	</script>
  
</html>
