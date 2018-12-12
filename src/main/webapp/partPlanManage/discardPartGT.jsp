<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>零件甘特图</title>
    
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

	<script src="<%=path %>/GT/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="<%=path %>/GT/scripts/miniui/miniui.js" type="text/javascript"></script>
	<script src="<%=path %>/GT/scripts/miniui/locale/zh_CN.js" type="text/javascript"></script>

	<link href="<%=path %>/GT/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="<%=path %>/GT/scripts/miniui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="<%=path %>/GT/scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />

	<script src="<%=path %>/GT/scripts/rgantt/RGanttSchedule.js" type="text/javascript"></script>
	<script src="<%=path %>/GT/scripts/rgantt/RGanttMenu.js" type="text/javascript"></script>
	
	<style type="text/css">
		*{margin: 0;padding: 0;}
		
		.mini-grid .cellbg {
			background: #d0dff0;
			border-bottom: solid 1px #555;
		}
			
		body .mini-ganttview .mini-ganttview-headercell {
			text-align: center;
		}
			
		.mini-rgantt .myitem .mini-gantt-itembg {
			background: #f6f6c9;
		}
			
		.mini-rgantt .myitem2 .mini-gantt-itembg {
			background: #3590ca;
		}
			
		.mini-rgantt .myitem2 .mini-gantt-text {
			color: White;
		}
			
		.mini-rgantt .myitem .mini-gantt-percentcomplete {
			background: #78e4ae;
		}
			
		.mini-rgantt .myitem2 .mini-gantt-percentcomplete {
			background: #c0c0c0;
		}
	</style>
  </head>
  
  <body>
    <body>
    	<div class="mini-toolbar">
    		<a class="mini-button" iconCls="icon-reload" plain="false" onclick="load();">加载</a>
    		<a class="mini-button" iconCls="icon-save" plain="false" onclick="save();">保存</a>
		</div>
		
		顶层时间刻度：
		<select style="margin-right: 50px;"
			onchange="changeTopTimeScale(this.value)">
			<option value="year">年</option>
			<option value="halfyear">半年</option>
			<option value="quarter">季度</option>
			<option value="month" selected>月</option>
			<option value="week" >周</option>
			<option value="day">日</option>
		</select>
		底层时间刻度：
		<select onchange="changeBottomTimeScale(this.value)">
			<option value="year">年</option>
			<option value="halfyear">半年</option>
			<option value="quarter">季度</option>
			<option value="month">月</option>
			<option value="week">周</option>
			<option value="day" selected>日</option>
			<option value="hour">时</option>
		</select>
		<!--
		<br />
		<input type="button" onclick="add()" value="增加工序" />
		<input type="button" onclick="del()" value="删除工序" />
		
        <input type="checkbox" id="dragUpdown" />
		<label for="dragUpdown">允许上下拖拽</label>
		<input type="checkbox" id="linkInFactory" />
		<label for="linkInFactory">允许设备内联动</label>
		-->
		
		<div id="viewCt" style="margin-top: 8px;"></div>
		
		<div id="taskWindow" class="mini-window" title="编辑工序" style="width:280px;height:180px;">
        	<table style="font-size:13px;">
            	<tr>
                	<td>工序名称：</td>
           	     	<td><input id="taskName" class="mini-textbox" /></td>
            	</tr>
            	<tr>
               		<td>开始日期：</td>
                	<td><input id="taskStart" class="mini-datepicker" enabled="true"/></td>
            	</tr>
            	<tr>
                	<td>完成日期：</td>
                	<td><input id="taskFinish" class="mini-datepicker" enabled="true"/></td>
            	</tr>
            	<tr>
                	<td>进度：</td>
                	<td><input id="taskPercentComplete" class="mini-spinner" /></td>
            	</tr>
            	<tr>
            	    <td>外协:</td>
            	    <td><input id="taskWaiXie" class="mini-combobox" textField="text" valueField="id" url="data/trueOrFalse.txt" /> </td>
            	</tr>
             	<tr>
                	<td></td>
                	<td><a class="mini-button" style="width:60px;" onclick="ok()">确定</a></td>
            	</tr>
        	</table>
    	</div>
	</body>
	<script type="text/javascript">
		/* 创建甘特图对象，设置列配置
		-----------------------------------------------------------------------------*/
		var gantt = new mini.RGantt();
		new RGanttSchedule(gantt);
		

		//任务菜单
		var taskMenu = new RGanttTaskMenu();
		gantt.setTaskMenu(taskMenu);
		//这一段干嘛的
		taskMenu.edit.on("click", function (e) {
    		var task = gantt.getSelectedTask();
    		if (task) {
        		alert("编辑任务dd:"+task.Name);
			}else{
        		alert("请选择一个任务");
    		}
		});

		//右键菜单
		var ganttMenu = new RGanttContextMenu();
		gantt.setGanttBodyMenu(ganttMenu);
		gantt.setStyle("width:100%;height:90%;");
		gantt.OrderType = "hour";           //排程精度为小时
		
		gantt.setTopTimeScale("day");
		gantt.setBottomTimeScale("hour");   //时间刻度为小时
		
		//这里是干嘛的呢？  setCloumns 设置表格列集合。如何设置的呢？
		//<span >html中的标签提示：请使用 <span> 来组合行内元素，以便通过样式来格式化它们。
		//注释：span 没有固定的格式表现。当对它应用样式时，它才会产生视觉上的变化。
		gantt.setColumns([
    		{ 	header: '<span style="font-weight:bold;font-size:13px;color:#44474a;">产品信息</span>', 
        		field: "Name",
         		width: 180,
          		name: "taskname",
           		cellCls: "cellbg",
        		renderer: function (e) {
           			return '<span style="font-weight:bold;font-size:14px;color:#44474a;">' + e.record.Name + '</span>';
       			}
    		}
		]);

		//向id为id="viewCt"的元素增加内容
		gantt.render(document.getElementById("viewCt"));

		/* 业务代码
		-----------------------------------------------------------------------------*/
		//表格右键菜单
		gantt.setTableBodyMenu({
    		type: "menu",
    		items: [
        		{ 	type: "menuitem",  text: "设备工序图",
            		onclick: function (e) {
                                
            		}
        		},
        		{ 	type: "menuitem", iconCls: "icon-addnew", text: "新增任务",
            		onclick: function (e) {
                		var node = gantt.getSelected();
                		var newTask = gantt.newTask();
                		gantt.addTask(newTask, node);                
            		}
        		},
        		{ 	type: "menuitem", text: "针对此设备排程",
            		onclick: function (e) {
              			//  var node = gantt.getSelected();
                		order();
                		//alert("排程车间:" + node.Name);
            		}
        		},
        		'-',
        		{ 	type: "menuitem", text: "取消选择",
            		onclick: function (e) {
                		gantt.deselectAll();
            		}
        		}
    		]
		});

		//设置时间线
//		gantt.setTimeLines([
//			{ 	date: new Date(2017, 0, 3), text: "时间线" },
//			{ 	date: new Date(2017, 0, 5), text: "时间线2", style: "width:2px;background:red;" }
//		]);

		function load() {
		    gantt.loading();
		    url = 'DiscardBaseServlet?meth=seePartsPlan&pathTo=';
		    //alert(${sessionScope.willSeeParts});
		    var params = JSON.stringify(${sessionScope.DiscardWillSeeParts});
		    params = {'data':params};
		    jQuery.post(url, params, function(text){
 	   			var data = mini.decode(text);
		        gantt.loadData(data);
		        gantt.unmask();
 	   		},'json');
 	   		
		    
		    //$.ajax({
		    //   	url: "PartPlanBaseServlet?meth=allPartsGandT&pathTo=",
		    //    success: function (text) {
		    //        var data = mini.decode(text);
		    //       gantt.loadData(data);
		    //        gantt.unmask();
		    //   },
		    //    error: function (jqXHR, textStatus, errorThrown) {
		    //        alert("加载失败, 错误码：" + jqXHR.responseText);
		    //        gantt.unmask();
		    //    }
		    //});
		}
 
		//1)自定义条形图外观显示
		gantt.on("drawitem", function (e) {
		    var item = e.item, node = gantt.getOwnerNode(item);
		   
		    var cls = "myitem2";
		    if (item.Name == "task4") {
		    	cls = "myitem";
		    }
		    if (item.Duration>=172800) {
		       	cls = "myitem";
		    }
		    e.itemCls = cls;
		   	// e.itemHtml = 'wo';
		});
		
		//2)自定义条形图提示信息
		gantt.on('itemtooltipneeded', function (e) {
		    var task = e.task;
		    var string="";
		    node = gantt.getOwnerNode(task);
		    for(var i=0,l=node.Tasks.length;i<l;i++){
		   		string+=node.Tasks[i].Name;  
		    }
		    e.tooltip =   "<div>任务：" + task.Name + "</div>"
		                + "<div ><div style='float:left;'>进度：<b>"+task.PercentComplete + "%</b></div>"
//		                + "<div style='float:right;'>工期："+task.Duration + "分</div></div>"
		                + "<div style='clear:both;'>开始日期：" + mini.formatDate(task.Start, 'yyyy-MM-dd hh-mm-ss') + "</div>"
		                + "<div>完成日期：" + mini.formatDate(task.Finish, 'yyyy-MM-dd hh-mm-ss') + "</div>"
//		                + "<div>本节点的tasks：" + string+ "</div>"
//                      + "<div>tasks持续时间：" +(task.Finish.getTime()-task.Start.getTime())+ "</div>"
		                + "<div>该产品交付时间：" +task.ETime+ "</div>";
		});

		//上面是条形图的菜单颜色
		//节点内的任务被拖拽一次，则其他节点就向前移动一天
		//如何获得被拖拽的task的改变的值
		
		//跨车间任务联动、
		var ttasks =new Array();
		var tempLinked=0;

		var isLinked=false;
		function linked(){
	 		tempLinked++;
	 		var tasks =gantt.getSelectedTasks();
	 		//alert((tempLinked%2));
	 		if((tempLinked%2)==1){
		 		document.getElementById("linked").style.background="red"; 
		  		document.getElementById("linked").value="取消建立任务联动";
		  		isLinked=true;
	   			for(var i=0;i<tasks.length;i++){
    				ttasks[i]=tasks[i];
    			}
    			alert(ttasks[0].Name);
    			gantt.deselectTasks(tasks);
	 		}else{
	 		 	gantt.deselectTasks(tasks);
	 		
		 		document.getElementById("linked").style.background="white"; 
		  		document.getElementById("linked").value="建立任务联动";
		  		isLinked=false;
		  		//ttasks=null;
		  		alert((tempLinked%2));
	 		}  
		}

		//获取变动时间
		var time;
		gantt.on("taskdragstart", function (e) {
			
			var action = e.action;  	//move, start, finish, percentcomplete
		    var task = e.task, node = gantt.getOwnerNode(e.task);
		
		    //进度100%的任务不允许拖拽
		    if (task.PercentComplete == 100) {
		        e.cancel = true;
		    }
			/*
		    var dragUpdownCheckbox = document.getElementById("dragUpdown");
		    time=e.task.Start.getTime();
		    if (dragUpdownCheckbox.checked) {
		        e.dragUpdown = true;
		    } else {
		        e.dragUpdown = false;
		    }
		    */
		    e.dragUpdown = false;
		});

		gantt.on( "taskdragcomplete",function (e){
		    var task=e.task;
		    var node = gantt.getOwnerNode(task);  
		    
		    dropNode = e.dropNode;
		
		    if (dropNode && dropNode.Name == "P3") {
		        e.cancel = true;
		    }
		    /*
		    var  linkInFactory= document.getElementById("linkInFactory");
		    if (linkInFactory.checked){
		    	for(var i=0,l=node.Tasks.length;i<l;i++){
   					if(task!=node.Tasks[i]){ 
				    	if(task.Start.getTime()!=time){ 
  								node.Tasks[i].Start = new Date(node.Tasks[i].Start.getTime()+task.Start.getTime()-time);
  						 		node.Tasks[i].Finish = new Date(node.Tasks[i].Finish.getTime()+task.Start.getTime()-time);
  					 			//alert("任务改变的时间"+(task.Start.getTime()-time)+"另一个时间time"+time);
		 		  		}else{
					 		alert("开始时间没有改变");
  					 	}
  					}						
   				}
		    } 
		    */
		   	// else 
		    //{
		    //  alert("不允许车间内联动");
		   	// }   	 
			if(isLinked==true){
	   			if(ttasks.length>0){   
					for (var h=0;h<ttasks.length;h++){
		 				ttasks[h].Start=new Date(ttasks[h].Start.getTime()+task.Start.getTime()-time);
						ttasks[h].Finish=new Date(ttasks[h].Finish.getTime()+task.Start.getTime()-time); 
 		 			}
					gantt.refresh();
		        }
			} 
			//else
			//{
			// alert("不允许夸车间任务联动");
			//}
			gantt.deselectTask(task);			 
		});


		/*
		gantt.on("taskdragstart", function (e) {
		    var action = e.action;  //move, start, finish, percentcomplete
		    var task = e.task, node = gantt.getOwnerNode(e.task);
		
		    //进度100%的任务不允许拖拽
		    if (task.PercentComplete == 100) {
		        e.cancel = true;
		    }
		
		    //P2的所有任务，只允许调整进度拖拽。 
		    if (node.Name == "P2") {
		        if (action != "percentcomplete") {
		            e.cancel = true;
		        }
		    }
		
		    //P1的所有任务不允许跨行拖拽。     
		    if (node.Name != "P1") {
		        e.dragUpdown = true;
		    }
		});
		gantt.on("taskdragdrop", function (e) {
		    var task = e.task,
		        node = gantt.getOwnerNode(e.task),
		        dropNode = e.dropNode;
		
		    if (dropNode && dropNode.Name == "P3") {
		        e.cancel = true;
		    }
		});
		*/

		//taskWindow窗口 
		var selectedTask = null;
		function ShowTaskWindow() {
		    var task = gantt.getSelectedTask();
		    selectedTask = task;
		    if (task) {
		        var taskWindow = mini.get("taskWindow");
		        taskWindow.show();
		
		        mini.get("taskName").setValue(task.Name);
		        mini.get("taskStart").setValue(task.Start);
		        mini.get("taskFinish").setValue(task.Finish);
		        mini.get("taskPercentComplete").setValue(task.PercentComplete);
		        mini.get("taskWaiXie").setValue(task.isCo);
		    } else {
		        alert("请选择一个任务");
		    }
		}

		function ok() {
		    if(!selectedTask) return;
		    var taskWindow = mini.get("taskWindow");
		    taskWindow.hide();
		
		   	var taskName = mini.get("taskName").getValue();
		    var percentComplete = mini.get("taskPercentComplete").getValue();
		    var taskStart=mini.get("taskStart").getValue();
		    var taskFinish=mini.get("taskFinish").getValue(); 
		    var taskWaiXie=mini.get("taskWaiXie").getValue();
		    gantt.updateTask(selectedTask, {
		        Name: taskName,
		        Start:taskStart,
		        Finish:taskFinish,
		        PercentComplete: percentComplete,
		        isCo:taskWaiXie
		    });
		}
		taskMenu.edit.on("click", function (e) {
		    ShowTaskWindow();
		    var task = e.task;
		    //source 就是gantt
		    var source =e.source;
		    node = source.getOwnerNode(task);
		});
		
		gantt.on("taskdblclick", function (e) {
		    ShowTaskWindow();
		});

		//甘特图项目日历背景
		//定义一个项目日历数据对象,规定了一些基本工作日与非工作日范围
		/*
		type: 1为工作周,0为例外日期
		work: 1工作日,0非工作日
		day: 星期日 0, 星期一 1... 星期六 6
		*/
		var Calendar = [
		        { type: 1, work: 0, day: 0 },   //星期日:非工作日
		        {type: 1, work: 1, day: 1 },  	//周一, 也为非工作日!
		        {type: 1, work: 1, day: 2 },
		        { type: 1, work: 1, day: 3 },
		        { type: 1, work: 1, day: 4 },
		        { type: 1, work: 1, day: 5 },
		        { type: 1, work: 0, day: 6 },   //星期六:非工作日
				//从 2009年1月28日 到 2009年2月1号为非工作日
        		{type: 0, work: 0, start: new Date(2006, 11, 8), finish: new Date(2007, 0, 28) },
				//从 2009年2月38 到 2009年2月18号为工作日
				//Date.parse()和Date.UTC()返回相应日期的毫秒数,
				//Date.UTC()参数分别是：年、月、日、时、分、秒、毫秒.至少有年和月2个参数.其中月和时以0为基数
        		{type: 0, work: 1, start: new Date(2007, 1, 8), finish: new Date(2009, 1, 18) }
    	]
		function isWorkingDate(date) {
		    var day = date.getDay();
		    var time = date.getTime();
		
		    //先处理是否包含在例外日期中(例外日期优先级高)    
		    for (var i = 0, l = Calendar.length; i < l; i++) {
		        var d = Calendar[i];
		        //如果包含在例外日期范围中
		        if (d.type == 0 && time >= d.start.getTime() && time <= d.finish.getTime()) {
		            return d.work;
		        }
		    }
		    //后处理通用星期天逻辑
		    for (var i = 0, l = Calendar.length; i < l; i++) {
		        var d = Calendar[i];
		        //如果星期天一样
		        if (d.type == 1 && d.day == day) {
		            return d.work;
		        }
		    }
		}
		//将判断一天是否工作日, 设置给GanttView
		gantt.ganttView.isWorking = isWorkingDate;

		function save() {
		    
		    //获取当前任务树形数据
		    var tree = gantt.getData();
		    
		    //将数据转换为JSON字符串
		    var strJSON = mini.encode(tree);
//		    alert(strJSON);
		    var params = { tree: strJSON};
		    //alert("将甘特图的任务数据提交到服务端进行保存");
		    //使用jQuery的ajax，把任务数据，发送到服务端进行处理
	        $.ajax({
	        	//url: "../JSP/testReceivePartList.jsp",
	           	//url: "/test/wlaction.do?flag=save",
	           	url: "DiscardSelectedGanttSaveServlet",
	           	type: "POST",
	          	//data: lpf,
	          	//data: params,
	            data: strJSON, 
	           	success: function (data) {
	               	alert(data.result);
	           	}
	       });
		}
		function changeTopTimeScale(value) {
		    gantt.setTopTimeScale(value)
		}
		function changeBottomTimeScale(value) {
		    gantt.setBottomTimeScale(value)
		}
		function zoomIn() {
		    gantt.zoomIn();
		}
		function zoomOut() {
		    gantt.zoomOut();
		}
		function add() {
		    var node = gantt.getSelected();
		    if (node) {        
		        var task = gantt.newTask();        
		        gantt.addTask(task, node);
		    } else {
		        alert("请先选择一个车间");
		    }
		}

		function edit() {
		    var task = gantt.getSelectedTask();
		    if (task) {
		    	//任务修改函数
		    	taskName=prompt("请输入任务的名字","")
		        if(taskName!=null && taskName!=""){
		        	gantt.updateTask(task,{Name: taskName});
		        }else{
			        alert();
		        }
		    } else {
		        alert("请选择一个任务");
		    }
		}
		function split() {
	    	var task = gantt.getSelectedTask();
	    	if (task) {
	        	//alert("拆分任务：" + task.Name+ "修改的名称为 ");
		        if (task.Duration <= 1 * 3600 * 24) {
		            alert("不能拆分小于等于1天的任务");
		            return;
		        }
		        var start = new Date(task.Start.getTime());
		        var finish = new Date(task.Finish.getTime());
		        var duraton = task.Duration;
		        var node = gantt.getOwnerNode(task);    //获取任务所在节点
		        //第一个任务
		        var d = new Date(task.Start.getTime() + task.Duration * 1000 / 2);  //对半拆分
		        d.setDate(d.getDate() - 1);
		        task.Finish = maxTime(d);
		        var days = parseInt((task.Finish - task.Start) / (3600 * 24 * 1000));
		        task.Duration = (days + 1) * (3600 * 24);
		        //拆出来的新任务
		        var newTask = gantt.newTask();
		        var d = new Date(task.Finish);
		        d.setDate(d.getDate() + 1);
		        newTask.Start = clearTime(d);
		        newTask.Duration = duraton - task.Duration;
		        var d = new Date(newTask.Start.getTime() + newTask.Duration * 1000);
		        d.setDate(d.getDate() - 1);
		        newTask.Finish = maxTime(d);
		        gantt.addTask(newTask, node);
		    } else {
		        alert("请选择一个任务");
		    }
		}
		function del() {
		    var tasks = gantt.getSelectedTasks();
		    gantt.removeTasks(tasks);
		}



		/* 排程相关代码
		-----------------------------------------------------------------------------*/
		
		function clearTime(date) {
		    if (!date) return null;
		    return new Date(date.getFullYear(), date.getMonth(), date.getDate());
		}
		function maxTime(date) {
		    if (!date) return null;
		    return new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59);
		}

		function orderTasks(tasks) {
		    if (!tasks || tasks.length == 0) return;
		
		    //1)先按开始日期，排出一个顺序
		    tasks = tasks.sort(function (pre, next) {
		        var t1 = pre.Start.getTime(), t2 = next.Start.getTime();
		        if (t1 > t2) return 1;
		        else if (t1 == t2) return 0;
		        else return -1;
		    });    
		
		    //2)按开始日期顺序，将任务首尾连接排
		    var start = tasks[0].Start;
		    for (var i = 0, l = tasks.length; i < l; i++) {
		        var task = tasks[i];
		        task.Start = new Date(start.getTime());
		        var d = new Date(task.Start.getTime() + task.Duration * 1000);
		        d.setDate(d.getDate() - 1);
		        task.Finish = maxTime(d);
		
		        d.setDate(d.getDate() + 1);
		        start = clearTime(d);
		    }
		
		}

		function order() {
		    var node = gantt.getSelected();
		    if (node) {
		        orderTasks(node.Tasks);
		        gantt.refresh();
		    } else {
		        alert("请先选择一个车间");
		    }
		}
		
		function orderAll() {
		    var nodes = gantt.getNodeList();
		    for (var i = 0, l = nodes.length; i < l; i++) {
		        var node = nodes[i];
		        orderTasks(node.Tasks);
		    }
		    gantt.refresh();
		}

		load(); 
	</script>
</html>