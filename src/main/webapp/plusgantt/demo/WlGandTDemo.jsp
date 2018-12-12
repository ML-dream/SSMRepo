<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script><%=path %>
    
    <title>甘特图Demo界面</title>
    
	<script src="<%=path %>/plusgantt/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="<%=path %>/plusgantt/scripts/miniui/miniui.js" type="text/javascript"></script>    
	<script src="<%=path %>/plusgantt/scripts/miniui/locale/zh_CN.js" type="text/javascript"></script>
	
	<link href="<%=path %>/plusgantt/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="<%=path %>/plusgantt/scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />    
	<link href="<%=path %>/plusgantt/scripts/miniui/themes/icons.css" rel="stylesheet" type="text/css" />
	
	<script src="<%=path %>/plusgantt/scripts/plusgantt/GanttMenu.js" type="text/javascript"></script>
	<script src="<%=path %>/plusgantt/scripts/plusgantt/GanttSchedule.js" type="text/javascript"></script>
	<script src="<%=path %>/plusgantt/scripts/plusgantt/GanttService.js" type="text/javascript"></script>

  </head>
  
  <body style="background:white;font-size:13px;">

    顶层时间刻度：
    <select style="margin-right:20px;" onchange="changeTopTimeScale(this.value)">
        <option value="year">年</option>
        <option value="halfyear">半年</option>
        <option value="quarter">季度</option>
        <option value="month">月</option>
        <option value="week" selected>周</option>
        <option value="day">日</option>
        <option value="hour">时</option>
    </select>
    底层时间刻度：
    <select onchange="changeBottomTimeScale(this.value)" style="margin-right:20px;" >
        <option value="year">年</option>
        <option value="halfyear">半年</option>
        <option value="quarter">季度</option>
        <option value="month">月</option>
        <option value="week">周</option>
        <option value="day" selected>日</option>
        <option value="hour">时</option>
    </select>
   <input type="button" value="放大" onclick="zoomIn()" /> <input type="button" value="缩小" onclick="zoomOut()" />
   <br />
   <input type="button" value="增加任务" onclick="addTask()"/>
    <input type="button" value="删除任务" onclick="removeTask()"/>
    <input type="button" value="修改任务" onclick="updateTask()"/>    
    <input type="button" value="升级任务" onclick="upgradeTask()"/>
    <input type="button" value="降级任务" onclick="downgradeTask()"/>
    <br />    
    
    <input type="button" style="width:80px;" value="保存" onclick="save()"/>


</body>
</html>

<script type="text/javascript">

/* 创建甘特图对象，设置列配置
-----------------------------------------------------------------------------*/

var gantt = new CreateGantt();
gantt.render(document.body);

//右键菜单
var ganttMenu = new GanttMenu();
gantt.setContextMenu(ganttMenu);

/* 业务代码
-----------------------------------------------------------------------------*/


function save() {
    //获取当前任务树形数据
    var tasktree = gantt.getTaskTree();
    //获取所有被删除的任务
    var taskRemoved = gantt.getRemovedTasks();

    //将数据转换为JSON字符串
    var taskJSON = mini.encode(tasktree);
    var remvedJSON = mini.encode(taskRemoved);

    var params = {
        tasks: taskJSON,
        removeds: remvedJSON
    };
    alert("将甘特图的任务数据提交到服务端进行保存");

    //使用jQuery的ajax，把任务数据，发送到服务端进行处理
//    $.ajax({
//        url: 'savegant.aspx',
//        type: "POST",
//        data: params,
//        success: function (text) {
//            alert("保存成功");
//        }
//    });
}

//加载树形数据
function loadTree() {
    gantt.loading();
    $.ajax({
        url: "data/taskTree.txt",
        cache: false,
        success: function (text) {
            var data = mini.decode(text);

            gantt.loadTasks(data);

            gantt.unmask();

            //折叠全部
            //gantt.collapseAll();
        }
    });
}

//加载列表数据
function loadList() {
    gantt.loading();
    $.ajax({
        url: "data/taskList.txt",
        cache: false,
        success: function (text) {
            var data = mini.decode(text);
            //列表转树形
            data = mini.arrayToTree(data, "children", "UID", "ParentTaskUID");
            gantt.loadTasks(data);
            gantt.unmask();            
        }
    });
}
loadList();     //这个方式，服务端只需要生成列表数据就可以。
//loadTree();

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

function addTask() {
    var newTask = gantt.newTask();
    newTask.Name = '<新增任务>';    //初始化任务属性

    var selectedTask = gantt.getSelected();
    if (selectedTask) {
        gantt.addTask(newTask, "before", selectedTask);   //插入到到选中任务之前
        //project.addTask(newTask, "add", selectedTask);       //加入到选中任务之内            
    } else {
        gantt.addTask(newTask);
    }
}
function removeTask() {
    var task = gantt.getSelected();
    if (task) {
        if (confirm("确定删除任务 \"" + task.Name + "\" ？")) {
            gantt.removeTask(task);
        }
    } else {
        alert("请选中任务");
    }
}
function updateTask() {
    var selectedTask = gantt.getSelected();
    alert("编辑选中任务:" + selectedTask.Name);    
}
function upgradeTask() {
    var task = gantt.getSelected();
    if (task) {
        gantt.upgradeTask(task);
    } else {
        alert("请选选中任务");
    }
}
function downgradeTask() {
    var task = gantt.getSelected();
    if (task) {
        gantt.downgradeTask(task);
    } else {
        alert("请选选中任务");
    }
}


</script>

