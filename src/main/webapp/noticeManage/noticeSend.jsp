<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
    <title>发送通知</title>
    
  </head>
  
  <body>
  	
  	<table width="100%;"  >
  		<tr style="vertical-align: top;">
  			<td width="60%;">
  				<fieldset style="width: 100%;" align="center" >
					<legend>发送通知</legend>
				   	<div id= "userdiv">
				   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
				   			<tr>
				   				<td width="15%"><label for="receivers$text">收件人列表:</label></td>
				   				<td  colspan="2"><input id="receivers" name="receivers" class="mini-textarea" width="100%" height="100%" required="true" emptyText="请选择收件人"/></td>
							</tr>
				   			<tr>
				   				<td><label for="noticeTitle$text">通知标题:</label></td>
				   				<td><input id="noticeTitle" name="noticeTitle" class="mini-textbox"  width="100%" required="true" value="来自${sessionScope.user.staffName}的通知"/></td>
				   			</tr>
				   			<tr>
				   				<td><label for="grade$text">通知等级:</label></td>
				   				<td><input id="grade"  name="grade" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    									url="data/noticeGradeStatus.txt" value="10" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            					</td>
				   			</tr>
				   			<tr  height="200px;">
				   				<td><label for="content$text">通知内容:</label></td>
				   				<td  colspan="15"><input id="content" name="content" class="mini-textarea" width="100%" height="100%" emptyText="请输入通知内容" /></td>
				   			</tr>
				   			<tr>
				   				<td colspan="2">
				   					<div class="mini-toolbar" align="center">
								  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">发送</a>
								  	</div>
				   				</td>
				   			</tr>
				   		</table>
				   </div>
				</fieldset>
  			</td>
  			<td width="40%;">
  				<fieldset style="width: 100%;" align="center">
					<legend>选择收信人</legend>
					<div id="grid1" class="mini-datagrid" style="width:100%;height:320px;"
				         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
				         url="EditEmployeeInfoServlet" onrowdblclick="rowdblclick">
				        <div property="columns">
				            <div type="indexcolumn"></div>
				            <div field="staffCode" width="20%" headerAlign="center">员工编号</div>
				            <div field="staffName" width="20%" headerAlign="center">员工名称</div>
				            <div field="sectionName" width="20%" headerAlign="center">所属部门</div>
				            <div field="gender" width="10%" headerAlign="center" renderer="onGenderRenderer">性别</div>
				            <div field="mobilePhone" width="30%" headerAlign="center">手机号码</div>
				        </div>
				    </div>
			   </fieldset>
  			</td>
  		</tr>
  	</table>
	
	
   <script>
   		mini.parse();
   		var grid = mini.get("grid1");
	    grid.load();
	    
	    function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
                var params = eval("("+mini.encode(data)+")");
                var url = 'SendNoticeServlet';
   		        jQuery.post(url, params, function(data){
   	   		        alert(data.result);
   	   		  		window.location.href=window.location.href;
   	   		        },'json');
            }
   		}
	    
	    
	    
	    
	    
	    
	    
	    
	    var list = new List();
		var map = new Map();

  		function rowdblclick(e){
			var grid = e.sender;
            var record = grid.getSelected();
            
            /*
            if (record) {
                var receivers = mini.get("receivers");
                if(list.contains(record.staffCode)){
                	list.remove(record.staffCode);
                }else{
                	list.add(record.staffCode);
                }
                var content = "";
                for(var i=0;i<list.size();i++){
                	content += list.get(i) + ";";
                }
                receivers.setValue(content);	
            } else {                
                //
            }
            */
            
            if (record) {
                var receivers = mini.get("receivers");
             	if(map.containsKey(record.staffCode)){
             		map.remove(record.staffCode);
             	}else{
             		map.put(record.staffCode,record.staffName);
             	}
             	
             	var content = "";
             	var keys = new Array();
             	keys = map.keys();
             	for(var i=0;i<keys.length;i++){
             		content += keys[i]+"("+map.get(keys[i])+")"+";";
             	}
             	receivers.setValue(content);
            }else{
            	
            }
			
  	  	}
	    


   		//兼容IE8-，为Array原型添加indexOf方法；
   		if (!Array.prototype.indexOf) {
   		    Array.prototype.indexOf = function (item) {
   		        var index = -1;
   		        for (var i = 0; i < this.length; i++) {
   		            if (this[i] === item) {
   		                index = i;
   		                break;
   		            }
   		        }
   		        return index;
   		    }
   		}

   		//List类实现
   		function List() {
    this.value = [];
    /* 添加 */
    this.add = function(obj) {
    return this.value.push(obj);
    };
    /* 大小 */
    this.size = function() {
    return this.value.length;
    };
    /* 返回指定索引的值 */
    this.get = function(index) {
    return this.value[index];
    };
    /* 删除指定索引的值 */
    this.remove = function(index) {
    this.value.splice(index,1);
    return this.value;
    };
    /* 删除全部值 */
    this.removeAll = function() {
    return this.value = [];
    };
    /* 是否包含某个对象 */
    this.contains = function(obj) {
    for ( var i in this.value) {
    if (obj == this.value[i]) {
    return true;
    } else {
    continue;
    }
    }
    return false;
    };
    /* 是否包含某个对象 */
    this.getAll = function() {
    var allInfos = '';
    for ( var i in this.value) {
    if(i != (value.length-1)){
    allInfos += this.value[i]+",";
    }else{
    allInfos += this.value[i];
    }
    }
    alert(allInfos);
    return allInfos += this.value[i]+",";;
    };
    }
    
    
    
    
    
    
    
    function Map() {
    this.elements = new Array();
    //获取MAP元素个数
    this.size = function() {
    return this.elements.length;
    };
    //判断MAP是否为空
    this.isEmpty = function() {
    return (this.elements.length < 1);
    };
    //删除MAP所有元素
    this.clear = function() {
    this.elements = new Array();
    };
    //向MAP中增加元素（key, value)
    this.put = function(_key, _value) {
    this.elements.push( {
    key : _key,
    value : _value
    });
    };
    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function(_key) {
    var bln = false;
    try {
    for (i = 0; i < this.elements.length; i++) {
    if (this.elements[i].key == _key) {
    this.elements.splice(i, 1);
    return true;
    }
    }
    } catch (e) {
    bln = false;
    }
    return bln;
    };
    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function(_key) {
    try {
    for (i = 0; i < this.elements.length; i++) {
    if (this.elements[i].key == _key) {
    return this.elements[i].value;
    }
    }
    } catch (e) {
    return null;
    }
    };
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function(_index) {
    if (_index < 0 || _index >= this.elements.length) {
    return null;
    }
    return this.elements[_index];
    };
    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function(_key) {
    var bln = false;
    try {
    for (i = 0; i < this.elements.length; i++) {
    if (this.elements[i].key == _key) {
    bln = true;
    }
    }
    } catch (e) {
    bln = false;
    }
    return bln;
    };
    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function(_value) {
    var bln = false;
    try {
    for (i = 0; i < this.elements.length; i++) {
    if (this.elements[i].value == _value) {
    bln = true;
    }
    }
    } catch (e) {
    bln = false;
    }
    return bln;
    };
    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function() {
    var arr = new Array();
    for (i = 0; i < this.elements.length; i++) {
    arr.push(this.elements[i].value);
    }
    return arr;
    };
    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function() {
    var arr = new Array();
    for (i = 0; i < this.elements.length; i++) {
    arr.push(this.elements[i].key);
    }
    return arr;
    };
    }



   		function onIDCardsValidation(e) {
            if (e.isValid) {
                var pattern = /\d*/;
                if (e.value.length < 15 || e.value.length > 18 || pattern.test(e.value) == false) {
                    e.errorText = "必须输入15~18位数字";
                    e.isValid = false;
                }
            }
        }

   		//验证QQ号码5-11位
   		function isQQ(e) {
   			if (e.isValid) {
                var pattern = /^\s*[.0-9]{5,11}\s*$/;
                if (!pattern.test(e.value)) {
                    e.errorText = "必须输入正确QQ号";
                    e.isValid = false;
                }
            }
   		}

   		//校验手机号码
   		function isMobile(e) {
   		    //var patrn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
   		    if (e.isValid) {
   		    	var pattern = /^(13[0-9]{9})|(14[0-9])|(18[0-9])|(15[0-9][0-9]{8})$/;
                if (!pattern.exec(e.value)) {
                    e.errorText = "必须输入正确手机号码";
                    e.isValid = false;
                }
            }
   		}

   		function isTelephone(e){
   			if (e.isValid) {
   				var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
   				var isMobile = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/
                if (!isPhone.exec(e.value)&&!isMobile.exec(e.value)) {
                    e.errorText = "必须输入正确电话号码";
                    e.isValid = false;
                }
            }
   			
   	   	}

   		function isOnlyTelephone(e){
   			if (e.isValid) {
   				var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
   				if (!isPhone.exec(e.value)) {
                    e.errorText = "必须输入正确电话号码";
                    e.isValid = false;
                }
            }
   			
   	   	}
   	   	
   </script>
  </body>
</html>
