<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>客户退货入库</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
  </head>
  
  <body>
    <div class="mini-toolbar">
<!--  	 	<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>-->
<!--  	 	<span class="separator"></span>-->
  	 	<a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
 	 	<span class="separator"></span>
 	 	<a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
<!-- 	 	<a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>-->
  	</div>
  	<div>
  		<form id="CustomerReturn" name="CustomerReturn" action="#" method="post">
    	<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tr bgcolor=#EBEFF5>
   		<td><label for="sheetId$text">单号</label></td>
   		<td><input id="sheetId" name="sheetId" class="mini-textbox" width="100%" enabled="false" required="true" value="${customerreturn.sheetId }"/></td>
   		<td><label for="orderId$text">订单号</label></td>
   		<td><input id="orderId" name="orderId" class="mini-textbox" width="100%" enabled="false" required="true" value="${customerreturn.orderId }"/></td>
   		<td><label for="itemId$text">编号</label></td>
   		<td><input id="itemId" name="itemId" class="mini-buttonedit" onbuttonclick="onCustomerReturnEdit" width="100%" required="true" 
   		textName="itemName" allowinput="false" value="${customerreturn.itemId }" text="${customerreturn.itemName }"/></td>
   		</tr>
   		<tr bgcolor=#EBEFF5>
   		<td><label for="issueNum$text">版本号</label></td>
   		<td><input id="issueNum" name="issueNum" class="mini-textbox" width="100%" value="${customerreturn.issueNum }"/></td>
   		<td><label for="spec$text">规格</label></td>
   		<td><input id="spec" name="spec" class="mini-textbox" width="100%" value="${customerreturn.spec }"/></td>
   		<td><label for="returnNum$text">数量</label></td>
   		<td><input id="returnNum" name="returnNum" class="mini-textbox" required="true" width="100%" value="${customerreturn.returnNum }"/></td>
   		
   		</tr>
   		<tr bgcolor=#EBEFF5>
   		<td><label for="unit$text">单位</label></td>
   		<td><input id="unit" name="unit" class="mini-textbox" width="100%" value="${customerreturn.unit }"/></td>
   		<td><label for="unitPrice$text">单价</label></td>
   		<td><input id="unitPrice" name="unitPrice" class="mini-textbox" width="100%" value="${customerreturn.unitPrice }"/></td>
   		<td><label for="price$text">总价</label></td>
   		<td><input id="price" name="price" class="mini-textbox" width="100%" value="${customerreturn.price }"/></td>
   		</tr>
   		<tr bgcolor=#EBEFF5>
   		<td><label for="qkprice$text">质量扣款</label></td>
   		<td><input id="qkprice" name="qkprice" class="mini-textbox" width="100%" value="${customerreturn.qkprice }"/></td>
   		<td><label for="returnreason$text">退货原因</label></td>
   		<td colspan="3"><input id="returnreason" name="returnreason" class="mini-textbox" width="100%" value="${customerreturn.returnreason }"/></td>
   		
   		</tr>
   		</table>
   		<input id="item_id" name="item_id" class="mini-hidden" value="${customerreturn.itemId }">
   		</form>
  	
  	
  	<script>
  	mini.parse();
  	
  	function getForm(){
  		var form=new mini.Form("CustomerReturn");
  		form.validate();
  		if(form.isValid()==false){
  			return;
  		}else{
  			var data=form.getData();
  			var json=mini.encode(data);
  			$.ajax({
  				type: "POST",
      			url: "doeditCustomerReturnDetailSerclet", 
      			dataType: "json",  				
      			data: { submitData: json },
  				success:function(data){
  					alert(data.result);
  					if(data.status==1){
  						window.location.href=window.location.href;
  					}
  				}
  			
  			});
  		
  		}
  	
  	}
  	
  	function onCustomerReturnEdit(e){
  		var btnEdit = this;
  		var orderId=mini.get("orderId").getValue();
  		mini.open({
  			url:"Checkin/selectCustomerReturnItem.jsp?orderId="+orderId,
  			title:"选择物料号",
  			width: 650,
            height: 380,
            ondestroy: function (action) {
            	if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.productId);
                            btnEdit.setText(data.productName);
                            mini.get("issueNum").setValue(data.issueNum);
                            mini.get("spec").setValue(data.spec);
                            mini.get("unitPrice").setValue(data.unitPrice);
                        
                        }
                    }
            }
  	
  		});
  	}
  	
  	</script>
  	</div>
  </body>
</html>
