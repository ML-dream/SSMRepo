<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择刀具</title>
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
  <table>
  	<tr>
  		<td><a class="New_Button" href="javascript:newRow()" style="line-height:25px;">新增数据</a></td>
  		<td style="">条形码号</td>
       	<td style=""><input id="Barcode" name="barcode" class="mini-textbox" /></td>
       	<td style="">工序号</td>
       	<td style=""><input id="fo_no" name="fo_no" class="mini-textbox" /></td>
  	</tr>	
  </table>
    <div id="datagrid1" class="mini-datagrid" style="width:440px;height:250px;" 
        url="LoadFoItem"  idField="id" onselectionchanged="onSelectionChanged">
        <div property="columns">
            <div name="action" width="40" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;"></div>
            <div field="itemname" width="60" headerAlign="center" allowSort="true">刀具名称</div>                
            <div field="itemid" width="80" allowSort="true" >刀具编号</div>
            <div field="itemnum" width="" allowSort="true" >数量</div> 
            
        </div>
    </div>      
    <br />
    <fieldset style="width:500px;height:90px;border:solid 1px #aaa;position:relative;">
        <legend>刀具使用信息</legend>
        <div id="editForm1" style="padding:5px;">
            <input class="mini-hidden" name="id"/>
            <table style="width:100%;">
                <tr>
                    <td style="width:80px;">刀具名称</td>
                    <td style="width:150px;"><input class="mini-combobox" id="itemid" style="width:150px;" textField="text" valueField="id" 
    					url="ShowItemServlet" value="default" showNullItem="true" allowInput="false"/></td>
                    
                    <!-- 
                    <td style="width:80px;">刀具编号</td>
                    <td style="width:150px;"><input name="itemid" class="mini-textbox" /></td>   -->
                    <td style="width:80px;">使用数量</td>
                    <td style="width:150px;"><input name="itemnum" class="mini-textbox" value="1"/></td>
                    <td style="visibility:hidden;width:2px;">是否新增</td>
            		<td style="visibility:hidden;width:2px;"><input style="width:2px;" id="ifnew" name="ifnew" class="mini-textbox" value="1"/></td>
                </tr>
                
                <tr>
                	<td></td>
                	<td style="visibility:hidden;"><input id="aitemid" name="aitemid" class="mini-textbox" value=""/></td>
                    <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                        <a class="Update_Button" href="javascript:updateRow();">确定</a>  
                        <a class="Cancel_Button" href="javascript:cancelRow();">取消</a>
                    </td>                
                </tr>
            </table>
        </div>
    </fieldset>
  
  <!-- 质检的同名页面 
    <div class="mini-fit">

        <div class="mini-fit">

        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" idField="id" allowResize="true"
            borderStyle="border-left:0;border-right:0;" onrowdblclick="onRowDblClick"
        >
            <div property="columns">
                <div type="indexcolumn" ></div>
                <div field="itemid" width="100" headerAlign="center">刀具编号</div>
	            <div field="itemname" width="100" headerAlign="center">刀具名称 </div>
	            <div field="itemnum" width="100" headerAlign="center">使用数量</div>
            </div>
        </div>
    
    </div>     
    
    </div>   
                 
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>
	-->
</body>
</html>

<script type="text/javascript">
    mini.parse();
    var grid = mini.get("datagrid1");
	var barcode  ="";
	var fo_no = 0;
	var editForm = document.getElementById("editForm1");
    var form = new mini.Form("editForm1");
	
	function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;

            var s =  '<a class="Delete_Button" href="javascript:delRow(\'' + uid + '\')">删除</a>';
                       
            return s;
        }
	
 	function onSelectionChanged(e) {
           
           var grid = e.sender;
           var record = grid.getSelected();
           if (record) {
               editRow(record._uid);
           } else {                
               form.reset();
           }
       }

   function newRow() {            
       var row = {};
       grid.addRow(row, 0);

       editRow(row._uid);
   }
    function editRow(row_uid) {
        var row = grid.getRowByUID(row_uid);
        if (row) {
           

            //表单加载员工信息
            var form = new mini.Form("editForm1");
            if (grid.isNewRow(row)) {
                
                form.reset();
            } else {
            	
                form.loading();
                form.setData(row,true);
                mini.get("itemid").setValue(row.itemid);
                mini.get("ifnew").setValue("0");
                //修改前的物料号 
                mini.get("aitemid").setValue(row.itemid);
                form.unmask();
            }

            grid.doLayout();
        }
    }
    function cancelRow() {
        grid.reload({barcode:barcode,fo_no:fo_no});
    }
    
     function delRow(row_uid) {
            var row = grid.getRowByUID(row_uid);
            if (row) {
                if (confirm("确定删除此记录？")) {
                    grid.loading("删除中，请稍后......");
                    $.ajax({
                    	type: "post",
                        url: "DeleteFoItem?itemid=" + row.itemid+"&barcode="+barcode+"&fo_no="+fo_no,
                        success: function (text) {
                            grid.reload({barcode:barcode,fo_no:fo_no});
                        },
                        error: function () {
                        }
                    });
                }
            }
        }

        function updateRow() {
            var form = new mini.Form("editForm1");

            var o = form.getData();
            o.barcode = barcode;
            o.fo_no = fo_no;
			o.itemid = mini.get("itemid").getValue();
			
            grid.loading("保存中，请稍后......");
            var json = mini.encode([o]);
            $.ajax({
                url: "UpdateFoItem",
                data: { data: json },
                
               success: function (text) {
               	grid.reload({barcode:barcode,fo_no:fo_no});
           	 },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });

        }
    
	
    function GetData() {
        var row = grid.getSelected();
        return row;
    }
    function search() {
        var key = mini.get("key").getValue();
        grid.load({ key: key });
    }
    function onKeyEnter(e) {
        search();
    }
    function onRowDblClick(e) {
        onOk();
    }
    //////////////////////////////////
    function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }

    function onOk() {
        CloseWindow("ok");
    }
    function onCancel() {
        CloseWindow("cancel");
    }
	
	function SetData(data) {
        var data = mini.clone(data);
		mini.get("Barcode").setValue(data.barcode);
		mini.get("fo_no").setValue(data.fo_no);
		barcode = data.barcode;
		fo_no = data.fo_no;
		grid.load({barcode:data.barcode,fo_no:data.fo_no});		
       /*
        $.ajax({
        	type: "post",
            url: "ToStateJudge?fo_no=" + fo_no+"&barcode="+barcode,
            cache: false,
            success: function (text) {
                var o = mini.decode(text);
                mini.get("runNum").setValue(o.runnum);
                form.setData(o);
                mini.get("checker").setValue(o.user);
                editControl(o.user);
            }
       });
       */
     }

</script>
