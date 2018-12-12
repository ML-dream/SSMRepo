<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择客户</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    
	<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
	
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style>
  </head>
  
  <body>
  	<!-- 
    <div class="mini-toolbar" style="text-align:center;line-height:30px;" borderStyle="border:0;">
          <label >名称：</label>
          <input id="key" class="mini-textbox" style="width:150px;" onenter="onKeyEnter"/>
          <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
    </div>
    
    <div class="mini-fit">

        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" idField="id" allowResize="true"
            borderStyle="border-left:0;border-right:0;" onrowdblclick="onRowDblClick"
        >
            <div property="columns">
                <div type="indexcolumn" ></div>

                <div field="itemid" width="100" headerAlign="center">物料编号</div>
	            <div field="itemtypeid" width="70" headerAlign="center">物料种类 </div>
	            <div field="itemname" width="100" headerAlign="center">物料名称</div>
	            <div field="itemdrawing" width="100" headerAlign="center">图号</div>
	            <div field="itemspecification" width="100" headerAlign="center">物料规格</div>
            	<div field="purchaseunite" width="100" headerAlign="center">采购单位</div>  
            	<div field="itemprice" width="70" headerAlign="center">物料价格</div>           
            </div>
        </div>
    
    </div>                
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>
     -->
    
    
    
    <div style="text-align:center;line-height:23px;padding:5px;">
        <!--
        <div>
        	
            <span>部门：</span>    
            <input id="deptTree" class="mini-treeselect" url="../data/listTree.txt" valueField="id" textField="text" style="width:160px;"
                    
            />
            <a class="mini-button" onclick="onClearClick" style="width:60px;height:20px;">清除</a>       
        </div>
        
        <div>
            <span>姓名：</span>    
            <input id="keyText" class="mini-textbox" style="width:160px;" onenter="onSearchClick"/>
            <a class="mini-button" onclick="onSearchClick" style="width:60px;height:20px;">查找</a>       
        </div>
        -->
    </div>
    <div style="padding-left:5px;padding-right:5px;">
        <table cellpadding="0" cellspacing="0" >
            <tr>
                <td >
                    <h4 style="margin:0;line-height:22px;font-size:13px;">物料列表：</h4>
                    <div id="grid1" class="mini-datagrid" style="width:470px;height:380px;" idField="itemid" 
                            showPageSize="true" showPageIndex="true" pagerStyle="padding:2px;" multiSelect="true"
                            allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        			editNextOnEnterKey="true"  editNextRowCell="true"
                    >
                        <div property="columns">
                            <div type="checkcolumn" ></div>

                			<div field="itemid" width="50" headerAlign="center">物料编号</div>
	            			<div field="itemTypeName" width="30" headerAlign="center">物料种类 </div>
	            			<div field="itemname" width="50" headerAlign="center">物料名称</div>
	            			<!--<div field="itemdrawing" width="50" headerAlign="center">图号</div>-->
	            			<div field="itemspecification" width="50" headerAlign="center">物料规格</div>
            				<div field="itemprice" width="30" headerAlign="center">物料价格</div>
            				<div field="num" width="50" headerAlign="center">数量
            					<input property="editor" class="mini-spinner" minValue="0" maxValue="200" value="1" style="width:100%;"/>
            				</div>
                        </div>
                    </div>
              
                </td>
                <td style="padding:5px;">
            <input type="button" value="选择" onclick="addSelected()" style="width:50px;"/><br />
                </td>
                <td >
                    <h4 style="margin:0;line-height:22px;font-size:13px;">已选人员：</h4>
                    <div id="selectedList" class="mini-listbox" style="width:480px;height:380px;" showCheckBox="true" multiSelect="true" 
                    >    
                        <div property="columns">
                			<div field="itemid" width="50" headerAlign="center">物料编号</div>
	            			<div field="itemname" width="50" headerAlign="center">物料名称</div>
	            			<div field="itemspecification" width="50" headerAlign="center">物料规格</div>
	            			<div field="num" width="50" headerAlign="center">数量</div>
                        </div>
                    </div>                       
                </td>
                <td style="padding:5px;">
            <input type="button" value="删除" onclick="removeSelecteds()" style="width:55px;margin-bottom:2px;"/><br />                
            <input type="button" value="清空" onclick="removeAllSelecteds()" style="width:55px;"/><br />                
                </td>
            </tr>
        </table>
    </div>
    <div style="padding:15px;text-align:center;">   
            
        <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
        <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
    </div>
    

</body>
</html>

<script type="text/javascript">
    	mini.parse();
    	var grid = mini.get("grid1");
    	
    	function GetRequest() { 
        	var url = location.search; //获取url中"?"符后的字串 
        	var theRequest = new Object(); 
        	if (url.indexOf("?") != -1) { 
        		var str = url.substr(1); 
        		strs = str.split("&"); 
        		for(var i = 0; i < strs.length; i ++) { 
        			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
        		} 
       	 	} 
        	return theRequest; 
    	} 
    	var Request = new Object(); 
    	Request = GetRequest();
    	grid.setUrl("GetItemServlet?type="+Request['type']);
    	grid.load();

        //////////////////////////////////////        

        function SetData(data) {
            //跨页面调用，克隆数据更安全
            data = mini.clone(data);

            grid.load();
            grid.deselectAll();
            selectedList.removeAll();
        }
        function GetData() {
            var rows = selectedList.getData();
            var ids = [], texts = [], nums = [];
            for (var i = 0, l = rows.length; i < l; i++) {
                var row = rows[i];
                ids.push(row.itemid);
                texts.push(row.itemname);
                if(row.num==""||row.num==null){
                	mini.alert("请选择数量!");
                	return null;
                }else{
                	nums.push(row.num);
                }
            }

            var data = {};
            data.id = ids.join(",");
            data.text = texts.join(",");
            data.num = nums.join(",");
            return data;
        }
        function CloseWindow(action) {            
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }
        function onOk(e) {
            CloseWindow("ok");
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
        /////////////////////////////////////

        var win = mini.get("selectWindow");
        var selectedList = mini.get("selectedList");
        var keyText = mini.get("keyText");
        var deptTree = mini.get("deptTree");


        function onSearchClick(e) {
            grid.load({
                key: keyText.value
            });
        }
        function onClearClick(e) {
            deptTree.setValue("");
        }

        function addSelected() {
            
            var items = grid.getSelecteds();

            //根据id属性，来甄别要加入选中的记录
            var idField = grid.getIdField();

            //把已选中的数据，用key-value缓存，以便进一步快速匹配
            var idMaps = {};
            var selecteds = selectedList.getData();
            for (var i = 0, l = selecteds.length; i < l; i++) {
                var o = selecteds[i];
                var id = o[idField];
                idMaps[id] = o;
            }

            //遍历要加入的数组
            for (var i = items.length - 1; i >= 0; i--) {
                var o = items[i];
                var id = o[idField];
                if (idMaps[id] != null) items.removeAt(i);
            }

            selectedList.addItems(items);
        }

        function removeSelecteds() {
            var items = selectedList.getSelecteds();
            selectedList.removeItems(items);
        }
        function removeAllSelecteds() {
            selectedList.removeAll();
        }


</script>
