<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
     <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>客户信息</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
	<!--
	table {
	    table-layout:fixed;
	    word-break: break-all;
	} 
	-->
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  <div class="mini-toolbar">
  	<a id="getForm" class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
  	<span class="separator"></span>
  	<a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
  
  </div>
    <fieldset id="allDetail" style="width: 100%;" align="center">
    <legend>修改出库信息</legend>
     <form name="checkout" id="checkout" action="doEditDetailCheckoutServlet" method="post" enctype="multipart/form-data">
   	<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   
    <tr bgcolor=#EBEFF5>
<!--    <td><label for="checksheet_id$text">出库号</label></td>-->
	<input id="checksheet_id" name="checksheet_id" class="mini-hidden" width="100%" required="true" enabled="false" value="${checkoutdetl.checksheetId}"/>
    <td><lable for="order_id$text">订单号</lable></td>
    <td><input id="order_id"  name="order_id" class="mini-buttonedit" width="100%" enabled="false" onbuttonclick="onOrderButtonEdit" value="${order_id }" text="${order_id }"
            textName="orderId" required="true" allowInput="false"/></td>
    <td><label for="item_id$text">图号</label></td>
    <td><input id="item_id" name="item_id" class="mini-textbox" width="100%" enabled="false" value="${checkoutdetl.itemId}"/></td>
   	<td><label for="item_name$text">产品名称</label></td>
    <td><input id="item_name" name="item_name" class="mini-textbox" width="100%" required="true" enabled="false" value="${checkoutdetl.itemName}"/></td>
    </tr>
    <tr bgcolor=#EBEFF5>
    <td><label for="issue_num$text">版本号</label></td>
    <td><input id="issue_num" name="issue_num" class="mini-textbox" width="100%" required="true" enabled="false" value="${checkoutdetl.issueNum}"></td>
  	 <td><label for="drawingId$text">产品号</label></td>
    <td><input id="drawingId" name="drawingId" class="mini-textbox" width="100%" enabled="false" value="${checkoutdetl.drawingId}"/></td>
    <td><label for="item_type$text">类型</label></td>
  	<td><input id="item_type" name="item_type" class="mini-combobox" required="true" width="100%"
  			 textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" enabled="false" allowInput="false" url="GetItemTypeServlet"
  			 value="${checkoutdetl.itemType }" text="${checkoutdetl.itemTypeDesc }"/></td>
    </tr>
    <tr bgcolor=#EBEFF5>
   
    <td><label for="spec$text">规格</label></td>
    <td><input id="spec" name="spec" class="mini-textbox" width="100%" required="true" enabled="false" value="${checkoutdetl.spec}"/></td>
    <td><label for="stockNum$text">库存数量</label></td>
    <td><input id="stockNum" name="stockNum" class="mini-textbox" width="100%" required="true" enabled="false" value="${checkoutdetl.stockNum}"/></td>
    <td><label for="purductNum$text">订单总数</label></td>
    <td><input id="purductNum" name="purductNum" class="mini-textbox" width="100%" required="true" enabled="false" value="${checkoutdetl.purductNum }"/></td>
     </tr>
     <tr bgcolor=#EBEFF5>
    <td><label for="alreadyPayNum$text">已交付数量</label></td>
    <td><input id="alreadyPayNum" name="alreadyPayNum" class="mini-textbox" width="100%" required="true" enabled="false" value="${checkoutdetl.alreadyPayNum }"/></td>
  
    <td><label for="unitprice$text">单价</label></td>
    <td><input id="unitprice" name="unitprice" class="mini-textbox" width="100%"  required="true" enabled="false" value="${checkoutdetl.unitprice}"/></td>
   
   <td><label for="checkout_num$text">本次出库数量</label></td>
    <td><input id="checkout_num" name="checkout_num" class="mini-textbox" width="100%" required="true" value="${checkoutdetl.checkoutNum}"/></td>
    
    </tr>
   <tr bgcolor=#EBEFF5>
   <td><label for="price$text">本次出库金额</label></td>
    <td><input id="price" name="price" class="mini-textbox" width="100%" value="${checkoutdetl.price}"/></td>
    <td><label for="stock_id$text">库位</label></td>
    <td><input id="stock_id" name="stock_id" class="mini-textbox" width="100%" value="${checkoutdetl.stockId}"/></td>
<!--    <td><label for="quality_id$text">质编号</label></td>-->
<!--    <td><input id="uquality_id" name="quality_id" class="mini-textbox" width="100%" required="false"  value="${checkoutdetl.qualityId}"/></td>-->
    <td><label for="memo$text">备注</label></td>
    <td><input id="memo" name="memo" class="mini-textbox" width="100%" required="false"  value="${checkoutdetl.memo}"/></td>
    </tr>
    </table>
    <input id="outNum" name="outNum" class="mini-hidden" width="100%" required="true" value="${checkoutdetl.checkoutNum }"/>
    <input id="warehouse_id" name="warehouse_id" class="mini-hidden" width="100" required="true" value="${warehouse_id }"/>
    </form>
 
    </fieldset>
    
    <script>
    mini.parse;
    function getForm()
    {
    	var form=new mini.Form("checkout");
    	var data=form.getData();
    	var json=mini.encode(data);
    	 form.validate();
    	 if (form.isValid() == false) {
          	return;
    	 }else{
    		mini.get("getForm").disable();	
    		var stockNum=parseFloat(mini.get("stockNum").getValue());
            var checkoutNum=parseFloat(mini.get("checkout_num").getValue());
            var alreadyPayNum=parseFloat(mini.get("alreadyPayNum").getValue());
            var purductNum=parseFloat(mini.get("purductNum").getValue());
            var outNum=parseFloat(mini.get("outNum").getValue());
            if(checkoutNum>(stockNum+outNum)){
                	alert("出库数量不能大于库存数量，请修改！");
                	mini.get("getForm").enable();
            }else if((checkoutNum+alreadyPayNum)>purductNum){
                	mini.confirm("出库数量不能大于订单数量，是否出库？","确定",
                	function(action){
                		if(action=="ok"){
                			$.ajax({
    						type:"POST",
    						url:"doEditDetailCheckoutServlet",
    						dataType:"json",
    						data:{submitData:json},
    						success:function(data){
    							alert(data.result);
        						window.location.href = window.location.href;
		               	
		        				}

   							});
                		}else{
                			alert("请重新填写出库数量！");
     						mini.get("getForm").enable();
                		}
                	
                	});
                }else{
                	$.ajax({
    				type:"POST",
    				url:"doEditDetailCheckoutServlet",
    				dataType:"json",
    				data:{submitData:json},
    				success:function(data){
    					alert(data.result);
        				window.location.href = window.location.href;
                
                		}      	
   					});
   				}
   			}
    }
    
    function onButtonEdit(e){
   	var orderId=mini.get("order_id").getValue();
   	 var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Checkout/selectOrderItemWindow.jsp?orderId="+orderId,
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
                            btnEdit.setValue(data.productId);
                            btnEdit.setText(data.productId);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       		mini.get("item_name").setValue(data.productName);
                      	mini.get("item_type").setValue(data.itemTypeId);
                       	mini.get("spec").setValue(data.itemSpecification);
                       //	mini.get("unitprice1").setValue(data.itemprice);
                        //mini.get("order_id1").setValue(data.orderid);
                       	mini.get("unit").setValue(data.unitm);
                        mini.get("issue_num").setValue(data.issueNum);
                        }
                    }

                }
            });
   
   }
   
    function onOrderButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Checkout/selectOrderIdWindow.jsp",
                title: "订单号列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.orderId);
                            btnEdit.setText(data.orderId);
                            mini.get("companyname").setValue(data.companyName);
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectortel").setValue(data.connectorTel); 
                        }
                    }

                }
            });
        }
    
    
    </script>
  </body>
</html>
