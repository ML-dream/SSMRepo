<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>修改领料信息</title>
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
  <!-- <a class="mini-button" iconCls="icon-print" plain="false" onclick="print()">打印订单</a> 
  <span class="separator"></span> -->
  <a id="getForm" class="mini-button" plain="false" iconCls="icon-save" onclick="getForm()">保存</a>
  
  <span class="separator"></span>
  <a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  </div>
  <fieldset id="allDetail" style="width: 100%;" align="center">
		<legend>
			领料单信息
		</legend>
	<form id="lldetail" name="lldetail" action="doEditLlSheetDetailServlet" method="post">
	<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%"  >
   <tr bgcolor=#EBEFF5>
   <td><label for="item_id$text">物料号</label></td>
   <td><input id="item_id" name="item_id" class="mini-buttonedit" width="100%" textName="itemid_name" required="true" 
   onbuttonclick="onButtonEdit" allowInput="false" enabled="false" value="${lldetail.itemId }" text="${lldetail.itemId }"></td>
   <td><label for="item_name$text">物料名称</label></td>
   <td><input id="item_name" name="item_name" enabled="false" class="mini-textbox" width="100%" required="true"  value="${lldetail.itemName }"></td>
   <td><label for="order_id$text">订单号</label></td>
   <td><input id="order_id" name="order_id" enabled="false" class="mini-buttonedit" width="100%" textName="orderId" required="true"  value="${lldetail.orderId }" text="${lldetail.orderId }"/></td>
   
   
   </tr> 
   <tr bgcolor=#EBEFF5>
   <td><label for="productId$text">产品名称</label></td>
   <td><input id="productId" name="productId" enabled="false" class="mini-buttonedit" width="100%" textName="productName" required="true"  value="${lldetail.productId }" text="${lldetail.productName }"/></td>
   <td><label for="issue_num$text">版本号</label></td>
   <td><input id="issue_num" name="issue_num" enabled="false" class="mini-textbox" width="100%"   required="true"  value="${lldetail.issueNum }"/></td>
   <td><label for="item_type$text">类型</label></td>
   <td><input id="item_type" name="item_type" class="mini-textbox" width="100%"   required="true" enabled="false" value="${lldetail.itemType }"/></td>
   </tr>
   <tr bgcolor=#EBEFF5>
    
    <td><label for="spec$text">规格</label></td>
   <td><input id="spec" name="spec" class="mini-textbox" width="100%"   required="true" enabled="false" value="${lldetail.spec }"/></td>
    <td><label for="unit$text">单位</label></td>
   <td><input id="unit" name="unit" class="mini-textbox" width="100%"   required="true" enabled="false" value="${lldetail.unit }"/></td>
   <td><label for="stock_num$text">当前库存数量</label></td>
   <td><input id="stock_num" name="stock_num" class="mini-textbox" width="100%"   required="true"  value="${lldetail.stockNum }"/></td>
  
   </tr>
   <tr bgcolor=#EBEFF5>
    <td><label for="ll_num$text">数量</label></td>
   <td><input id="ll_num" name="ll_num" class="mini-textbox" width="100%"   required="true"  value="${lldetail.llNum }"/></td>
    <td><label for="unitprice$text">单价</label></td>
   <td><input id="unitprice" name="unitprice" class="mini-textbox" width="100%"   required="true"  value="${lldetail.unitPrice }"/></td>
    <td><label for="price$text">金额</label></td>
   <td><input id="price" name="price" class="mini-textbox" width="100%"   required="false"  value="${lldetail.price }"/></td>
   
   </tr>
   <tr bgcolor=#EBEFF5>
    <td><label for="stock_id$text">库位</label></td>
   <td><input id="stock_id" name="stock_id" class="mini-textbox" width="100%"   required="false"  value="${lldetail.stockId }"/></td>
    <td><label for="memo$text">备注</label></td>
   <td colspan="3"><input id="memo" name="memo" class="mini-textbox" width="100%"   required="false"  value="${lldetail.memo }"/></td>
   
   
   </tr>
   </table> 
   <input id="ll_sheetid" name="ll_sheetid" class="mini-hidden" required="true" value="${lldetail.llSheetid }"/>
   <input id="warehouse_id" name="warehouse_id" class="mini-hidden" required="true" value="${warehouse_id }"/>
    <input id="num1" name="num1" class="mini-hidden" required="true" value="${lldetail.llNum }"/>
<!--     <input id="num2" name="num2" class="mini-hidden" required="true" value="${lldetail.rmNum }"/>-->
   </form>
   </fieldset>
   
   <script type="text/javascript">
   mini.parse();
   function getForm(){
 		  	var form = new mini.Form("lldetail");
 		  	var data=form.getData();
 		  	var json =mini.encode(data);
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	
            	var stockNum=parseFloat(mini.get("stock_num").getValue());
            	var llNum=parseFloat(mini.get("ll_num").getValue());
            	var num1=parseFloat(mini.get("num1").getValue());
            	  if(llNum>(stockNum+num1)){
                	alert("领料数量不能大于库存数量，请修改！");
                }else{
            	
            		mini.get("getForm").disable();
 		  			$.ajax({
    					url: "doEditLlSheetDetailServlet",
    					type: "post",
    					dataType: "json",
   						data: { submitData: json },
      				//	enctype: 'multipart/form-data',
      				//	processData: false,
    				//	contentType: false,
      					success: function (data) {
	       					alert(data.result);	
		           	 		window.location.href = window.location.href;	
		            
    					}
      				
    				});
    			}
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
                       	mini.get("spec").setValue(data.itemspecification);
                       	mini.get("unitprice").setValue(data.itemprice);
                        mini.get("order_id").setValue(data.orderid);
                        mini.get("issue_num").setValue(data.issuenum);
                        }
                    }

                }
            });
        }
   
   </script>
  </body>
</html>
