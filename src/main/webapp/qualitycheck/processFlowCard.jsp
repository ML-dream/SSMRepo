<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
  <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>工艺流程卡</title>
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
	<div id= "userdiv" style="margin:0 auto;">
   		<h1>零件条形码号</h1>
   		<form id ="form0" >
   		<table >
	   		<!-- 验证等属性在 textbox 中 -->
	   		<tr>
	   			<td>
	                <label >零件条形码</label>
	            </td>
	            <td style = "width:135px;">
	                <input id="piece_barcode"  name="piece_barcode" class="mini-textbox" required="true" 
	                	value="" onenter="loadgrid()"   vtype="rangeLength:15,18" style= "width:100%;"/>
	               
	                
	            </td>
	            <td> <input value="确定" type="button" onclick="loadgrid()" style="width:50px;"/></td>
	            <td><label>更新待检表</label></td>
	            <td><input value="更新" type="button" onclick="update()" /></td>
	            <td><label>当前流程卡工序全部完成后请报完工</label></td>
	            <td><input value="完工" type="button" onclick="finish()" /></td>
	           
	   		</tr>
	   	</table>
	   	</form>
	  
	   	<!-- <div id="win1" class="mini-window" title="操作" style="width:400px;height:200px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" xAlign="Right" yAlign="Bottom" 
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    	<div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" plain="false"
		    buttons="#tabsButtons" refreshOnClick="true">
		    <div title="审理单"  iconCls="icon-node" showCloseButton="false" url="qualitycheck/shenlidan.jsp"></div>
		    <div title="RFID待检" iconCls="icon-add" showCloseButton="false" url="qualitycheck/RFIDjsp.jsp"></div>
		</div>
    
    </div> -->
    	
    	
    	<div id="win1" class="mini-window" title="RFID待检列表" style="width:400px;height:200px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" xAlign="Right" yAlign="Bottom" 
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
          <div><input value="刷新" type="button" onclick="refresh()" /></div>

    <div id="dept_grid" class="mini-datagrid" style="width:500px;height:180px;" 
        url="Rfidjiazai?param=1"  idField="id"
        onselectionchanged="onSelectionChanged" 
        selectOnLoad="true"
    >
        <div property="columns">  
        <div type="checkcolumn">选择</div>          
            <div field="ip" width="50" headerAlign="center" >IP</div>                                        
            <div field="rfidcode" width="120" headerAlign="left" >标签号码</div>          
        </div>
    </div> 
    
    </div>
   
   

	   	
	   	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr>
				<td><label>订单号</label></td>
	            <td >
   					<input id="Order_id"  name="order_id" class="mini-textbox"  width="240px"enabled="false" allowInput="false"/></td>
   				<td><label>客户名称</label></td>
	            <td >
   					<input id="CompanyName"  name="companyname" class="mini-textbox" width="200px" enabled="false" allowInput="false"/></td>
   				<td><label>工件状态</label></td>
	            <td>
   					<input id="Grade"  name="grade" class="mini-textbox"  value="正常" enabled="false" allowInput="false"/></td>
   			</tr>
   			<tr>
   				<td><label>零件图号</label></td>
	            <td><input id="DrawingId" name="drawingid" class="mini-textbox"  width="240px" enabled="false" allowInput="false"/></td>
   				<td><label>零件名称</label></td>
	            <td><input id="Product_Name" name="product_name" class="mini-textbox" width="200px" enabled="false" allowInput="false"/></td>
	            <td><label>投入量</label></td>
	            <td><input id="Input_Num" name="input_num" class="mini-textbox" enabled="false" allowInput="false"/></td>
   			</tr>
   	 	</table>
   	 </form>
   	<!--   <div id="win33" class="mini-window" title="Window" style="width:400px;height:250px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true" xAlign="Left" yAlign="Below"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    <div property="toolbar" style="padding:5px;">
        <input type='button' value='Hide' onclick="hideWindow()" style='vertical-align:middle;'/>
    </div>
    <div property="footer" style="text-align:right;padding:5px;padding-right:15px;">
        <input type='button' value='Hide' onclick="hideWindow()" style='vertical-align:middle;'/>
    </div>

    1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
</div> -->
   	
   	 
   <div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:980px;height:480px;" allowResize="true" onload= "setFirstFo()"
        url="ProcessFlowCard"  idField="id" multiSelect="false" pagesize="20" onselect="rowselect()" onrowclick="rowselect()">
        <div property="columns">
                
            <!--零件条形码 ，不可见 -->    
           <!--  <div type="indexcolumn"></div><div type="checkcolumn" ></div>   --> 
           
            <div field="fo_no" name="foNo" width="40" headerAlign="center" allowSort="true">工序号</div>    
            <div field="fo_opname" width="60" headerAlign="center" allowSort="false">工序名称</div> 
            <div field="rated_time" width="60" headerAlign="center" allowSort="false">工时定额</div>   
            
            <div header="任务" headerAlign="center">
                <div property="columns">
                    <div field="num" width="60" headerAlign="center">计划数量</div>
                    <div field="workername" width="60" headerAlign="center">加工者</div>
                    <div field="workerid" width="60" headerAlign="center" visible="false">工号</div>  
                    <div field="checkdate" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">日期</div>
                    <div field="machine" width="60" headerAlign="center">设备</div>
                     <div field="machineid" width="60" headerAlign="center"  visible="false">设备id</div>
                    <div field="item" width="60" headerAlign="center">刀具</div>
                </div>
            </div>  
             
             <div header="检验" headerAlign="center">
                <div property="columns">
                	<div field="usedtime" width="60" headerAlign="center" allowSort="false">实做工时</div>
                	<div field="accept_num" width="40" headerAlign="center" allowSort="false">合格数
           			 </div>
           			 <div field="reject_num" width="60" headerAlign="center" allowSort="false">不合格数
           			 </div>
            		 <div field="checker" width="60" headerAlign="center" allowSort="false">检验者</div>
                </div>
            </div> 
            
            <!-- 需配合datagrid的allowCellEdit等属性一起用 -->
            <div field="confirm_num" width="80" headerAlign="center" allowSort="false">送检数量
            </div>    
            <!-- 备注栏自动换行？ -->
            <div field="remark" width="120" headerAlign="center" allowSort="false">备注
            	<!-- <input class="mini-textbox" value="" style="width:100%;"  />  -->
            </div>        
        </div>
     </div>
   </div>
   
   <!-- 行编辑窗口 -->
  <div id="editWindow" class="mini-window" title="质检输入" style="width:650px;" 
    showModal="true" allowResize="true" allowDrag="true" ondestroy = "windowDestroy()"
    >
    <div id="editform" class="form" >
        <input class="mini-hidden" name="id"/>
        <table style="">
            <tr>
            	<td style="width:80px;">工序号 </td>
                <td style="width:60px;"><input  id="fo_no" name="fo_no" class="mini-textbox" allowInput="false" /></td>
                <td style="width:80px;">工序名称 </td>
                <td style="width:80px;"><input  name="fo_opname" class="mini-textbox" allowInput="false"/></td>
                <td style="width:80px;">工时定额 </td>
                <td style="width:80px;"><input id= "ratedtime" name="ratedtime" class="mini-textbox" allowInput="false" style="width:75px;"/>
                	<input name="num" class="mini-textbox" allowInput="false" style="visibility:hidden; width:1px;"  /></td>
            </tr>
            
            <tr>
               <td>送检数量 (*)</td>
                <td><input name="confirm_num" class="mini-textbox" required="true" vtype="int"/></td>
                <td>合格数(*) </td>
                <td><input name="accept_num" class="mini-textbox" required="true" vtype="int"/></td>
                <td>不合格数 (*)</td>
                <td><input name="reject_num" class="mini-textbox"  value="0" required="true" vtype="int"/></td>
            </tr>
            <tr>
            	<td style="width:60px;">加工者 </td>
                <td><input id="worker" name="worker" class="mini-buttonedit" width="100%" 
            		onbuttonclick="onButtonEdit" textName="worker" required="false" value="default" text=""  allowInput="false"/></td>
            	<td style="width:60px;">设备 </td>
                <td><input id="machine" name="machine" class="mini-buttonedit" width="100%" 
            		onbuttonclick="selectMachine" textName="machine" required="false" value="default" text=""  allowInput="false"/></td>
            	<td style="width:60px;">刀具 </td>
                <td><a class="Update_Button" href="javascript:item()">点击查看</a> </td>
            </tr>
            <tr>
            	<td>实做工时</td>
            	<td><input style="" id="usedtime" name="usedtime" class="mini-textbox" vtype="float"/></td>
            	<td>备注 </td>
            	<td><input style="" name="remark" class="mini-textbox" /></td>
            	<!-- -->
            	<td style="visibility:hidden;">条形码号</td>
            	<td style="visibility:hidden;"><input id="Barcode" name="barcode" class="mini-textbox" /></td>
            </tr>
            <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                	
                    <a class="Update_Button" href="javascript:updateRow()">确定</a> 
                    <a class="Cancel_Button" href="javascript:cancelRow()">取消</a>
                </td>                
            </tr>
             <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                	 <a class="Update_Button" href="javascript:addEmp()">补充加工者信息</a> 
                </td>                
            </tr>
        </table>
    </div>
</div>
   
</body>
	<script type="text/javascript">
		mini.parse();

        var dept_grid = mini.get("dept_grid");
        dept_grid.load();
        
        function refresh(){
        var dept_grid = mini.get("dept_grid");
        dept_grid.load();
        }
        
        function update(){
			//mini.get("piece_barcode").blur();
			//var grid = new mini.get("datagrid1");
			//var headerForm = new mini.Form("#form1");
			//var form0 = new mini.Form("form0");
    	 	//form0.validate();
        	// if (form0.isValid() == false) {return;} 
        	 
			//var mark =0;
			
         	var barcode = mini.get("piece_barcode").getValue();
             var param=2;
             /* data.barcode=barcode;
             data.param=param; */
        	$.ajax({
        		type: "post",
        		
        		url:"Rfidjiazai?param="+param+"&barcode="+barcode,
        		/* ",
        		data: { data: json }, */
        		
        	/* 	cache: false,
    			async:false, */
    			success:function(text){
    				/* var data = mini.decode(text);
    				mark = data.status;		// 根据工件状态判断查询方式 
					headerForm.setData(data,true);
					grid.load({key:barcode,mark:mark});	 */
					alert("更新成功");
		          dept_grid.load();
                      
					
    			},
    			error : function() {
					//alert("加载失败, 错误码：" + jqXHR.responseText);
				}
        	});
		}
   /*      $(document).ready(function(){  
       alert("第一种方法。");  
     var myid = mini.get(piece_barcode)
     myid.setValue(3423423);
     
      });  */
       
     

        function onSelectionChanged(e) {
        
            var grid = e.sender;
            var record = grid.getSelected();
          
            if (record) {
                
                var myid = mini.get(piece_barcode);
             
                 myid.setValue(record.rfidcode);
            }
              
            
        }
        

   

       
		mini.get("piece_barcode").focus();
		var barcode = mini.get("piece_barcode").getValue();
		
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