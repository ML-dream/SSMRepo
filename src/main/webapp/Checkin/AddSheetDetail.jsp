<%@ page language="java" import="java.util.*,com.wl.forms.Employee" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>入库详细信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  <%
 // String warehouse_id=request.getParameter("warehouse_id");
  //String checkin_id=request.getParameter("checkin_id");
  
   %>
    <div class="mini-toolbar">
    <a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
    <span class="separator"></span>
    <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
    </div>
    <fieldset  style="width: 100%;" align="center">
    <legend>入库信息</legend>
     <form name="addsheetdiv" id="addsheetdiv" action="AddSheetServlet" method="post" enctype="multipart/form-data">
   	<table style="text-align: left; border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
     <tr>
    
    <td><label for="checksheet_id$text">单号</label></td>
    <td><input id="checksheet_id" name="checksheet_id" class="mini-textbox" width="100%" required="true" enabled="false" readonly="readonly" value="${checksheet_id }"/></td>
    <td><lable for="checkindetl_id$text">序列号</lable></td>
    <td><input id="checkindetl_id" name="checkindetl_id" class="mini-textbox" width="100%" required="true" enabled="false" readonly="readonly" value="${checkindetl_id }" /></td>
    <td><label for="item_id$text">物料编号</label></td>
    <td><input id="item_id"  name="item_id"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" required="true" textName="itemid_name"  allowInput="false"/></</td>
   	<td><label for="item_name$text">物料名称</label></td>
    <td><input id="item_name" name="item_name" class="mini-textbox" width="100%" required="true"/></td>
    </tr>
    <tr>
    <td><label for="order_id$text">订单号</label></td>
    <td><input id="order_id" name="order_id" class="mini-textbox" width="100%" required="false"/></td>
    <td><label for="issue_num$text">版本号</label></td>
    <td><input id="issue_num" name="issue_num" class="mini-textbox" width="100%" required="true"/></td>
    <td><label for="item_type$text">类型</label></td>
    <td><input id="item_type" name="item_type" class="mini-textbox" width="100%" required="true"/></td>
 	<td><label for="spec$text">规格</label></td>
    <td><input id="spec" name="spec" class="mini-textbox" width="100%" required="false"/></td>
    </tr>
    <tr>
    
    <td><label for="unit$text">单位</label></td>
    <td><input id="unit" name="unit" class="mini-textbox" width="100%" ></td>
    <td><label for="checkin_num$text">数量</label></td>
    <td><input id="checkin_num" name="checkin_num" class="mini-textbox" width="100%" required="true" /></td>
    <td><label for="unitprice$text">单价</label></td>
    <td><input id="unitprice" name="unitprice" class="mini-textbox" width="100%" required="true" /></td>
    <td><label for="price$text">金额</label></td>
    <td><input id="price" name="price" class="mini-textbox" width="100%" required="true"/></td>
   <!-- <td><label for="warehouse_id$text">库房</label></td>
    <td><input id="warehouse_id" name="warehouse_id" class="mini-textbox" width="100%" required="true"  /></td> -->
   
    </tr>
    <tr>
      <td><label for="stock_id$text">库位</label></td>
    <td><input id="stock_id" name="stock_id" class="mini-textbox" width="100%" required="true"/></td>
    
    <td><label for="lot$text">批次</label></td>
    <td><input id="lot" name="lot" class="mini-textbox" width="100%" required="false" /></td>
    <td><label for="quality_id$text">质编号</label></td>
    <td><input id="uquality_id" name="quality_id" class="mini-textbox" width="100%" required="false" /></td>
    <td><label for="memo$text">备注</label></td>
    <td><input id="memo" name="memo" class="mini-textbox" width="100%" required="false"/></td>

    </tr>
    </table>
    <input id="warehouse_id" name="warehouse_id" class="mini-hidden" required="true" readonly="readonly" value="${warehouse_id }"/>
    </form> 
    </fieldset>
    <script >
    	mini.parse();
  	 function getForm(){
 		  	var form = new mini.Form("#addsheetdiv");
 		  	var data=form.getData();
 		  	var json =mini.encode(data);
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
 		  		$.ajax({
    				url: "AddSheetServlet",
    				type: "post",
    				dataType: "json",
   					data: { submitData: json },
      			//	enctype: 'multipart/form-data',
      			//	processData: false,
    			//	contentType: false,
      				success: function (text) {
	       				alert(text.result);
        				window.location.href = window.location.href;
    						}
      				
    			});
    		}
   		}
   		
   	  function onButtonEdit(e) {
             var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name").setValue(data.itemname);
                       	mini.get("item_type").setValue(data.itemtypeid);
                        mini.get("order_id").setValue(data.orderid);
                        mini.get("issue_num").setValue(data.issuenum);
                       	mini.get("spec").setValue(data.itemspecification);
                       	mini.get("unit").setValue(data.unitm);
                       	mini.get("unitprice").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
   		
   		
   		
   		
   		
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
        
        
        
     // var Request = new Object(); 
     //   Request = GetRequest();
     //    mini.get("checksheet_id").value = Request['checksheet_id'];
     //    mini.get("warehouse_id").value=Request['warehouse_id'];
    </script>
    
  </body>
</html>
