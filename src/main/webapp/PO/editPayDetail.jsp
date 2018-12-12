<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>修改采购付款信息</title>
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
    <a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
    <span class="separator"></span> 
  	<a class="mini-button" plain="false" icoCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a> 
    </div>
    <form id="paydetail" name="paydetail" action="doeditPayDetailServlet" method="post">
    <table  style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
    <tr bgcolor=#EBEFF5>
    <td><label for="paySheetid$text">单号</label></td>
    <td><input id="paySheetid" name="paySheetid" class="mini-textbox" width="100%" required="true" enabled="false" value="${paydetail.paySheetid }"/></td>
    <td><label for="prSheetid$text">引用单号</label></td>
    <td><input id="prSheetid"  name="prSheetid" class="mini-buttonedit" width="100%" onbuttonclick="onPoButtonEdit" textName="poSheetid" required="true" allowInput="true"
    enabled="false" value="${paydetail.prSheetId}" text="${paydetail.prSheetId}"/></td>
    <td><label for="prdate$text">单据日期</label></td>
    <td><input id="prdate" name="prdate" class="mini-textbox" width="100%" required="true" enabled="false" value="${paydetail.prdate }"></td>
    </tr>
    <tr bgcolor=#EBEFF5>
    <td><label for="itemId$text">货品编码</label></td>
    <td><input id="itemId" name="itemId" class="mini-buttonedit" width="100%" onbuttonclick="onItemButtonEdit" required="true" textName="itemId" 
    enabled="false" value="${paydetail.itemId}" text="${paydetail.itemId}"/></td>
    <td><label for="itemName$text">货品名称</label></td>
    <td><input id="itemName" name="itemName" class="mini-textbox" width="100%" required="true" enabled="false" value="${paydetail.itemName }"></td>
    <td><label for="spec$text">规格</label></td>
    <td><input id="spec" name="spec" class="mini-textbox" width="100%" required="true" enabled="false" value="${paydetail.spec }"></td>
    </tr>
    <tr bgcolor=#EBEFF5>
     <td><label for="price$text">总金额</label></td>
     <td><input id="price" name="price" class="mini-textbox" width="100%" required="true" enabled="false" value="${paydetail.price }"></td>
     <td><label for="haspaid$text">已结算金额</label></td>
     <td><input id="haspaid" name="haspaid" class="mini-textbox" width="100%" required="true" enabled="false" value="${paydetail.haspaid }"></td>
     <td><label for="nopay$text">未结算金额</label></td>
     <td><input id="nopay" name="nopay" class="mini-textbox" width="100%" required="true" enabled="false" value="${paydetail.nopay }"></td>
    </tr>
    <tr bgcolor=#EBEFF5>
    <td><label for="thispay$text">本次结算金额</label></td>
    <td><input id="thispay" name="thispay" class="mini-textbox" width="100%" required="true" value="${paydetail.thispay }"></td>
    <td><label for="memo$text">备注</label></td>
    <td colspan="3"><input id="memo" name="memo" class="mini-textbox" width="100%" required="true" value="${paydetail.memo }"></td>
    
    
    </tr>
    
    </table>
    
    </form>
    
    <script type="text/javascript">
    mini.parse();
    function getForm(){
    var form=new mini.Form("paydetail");
    var data=form.getData();
    var json=mini.encode(data);
    form.validate();
    if(form.isValid()==false){
    return;
    
    }else{
    $.ajax({
    	type:"post",
   		url:"doeditPayDetailServlet",
    	data:{submitData:json},
    	dataType:"json",
    	success: function(data){
    		alert(data.result);
   			window.location.href=window.location.href;
    		}
    	});
     }
   }
    
     function onPoButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "PO/selectPoSheetIdWindow.jsp",
                title: "采购单列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.posheetid);
                            btnEdit.setText(data.posheetid);
                    		mini.get("podate").setValue(data.postartdate);
                    
                     }
                    }

                }
                
            });
      	 
        }
    
    function onItemButtonEdi(e){
        var btnEdit=this;
        var i=0;
        var PoSheetId=mini.get("poSheetid").getValue();
        mini.open({
         url: "PO/selectCheckinItemWindow.jsp?po_sheetid="+PoSheetId,
                title: "选择列表",
                width: 650,
                height: 380,
        ondestroy: function(action){
			if(action){
			var iframe=this.getIFrameEl();
			var data=iframe.contentWindow.GetData();
			data = mini.clone(data);
			if(data){
			btnEdit.setValue(data.itemId);
			btnEdit.setText(data.itemId);
			mini.get("itemName").setValue(data.itemName);
			mini.get("spec").setValue(data.spec);
			mini.get("price").setValue(data.price);
			$.ajax({
			type:"post",
			url:"getPayServlet",
			data:{poSheetid:PoSheetId,itemId:data.itemId,price:data.price},
			dataType:"json",
			success: function(text){
			mini.get("haspaid").setValue(text.haspaid);
			mini.get("nopay").setValue(text.nopay);
			}
			
						});
					}
				}
			}
        });
        
        }
    </script>
  </body>
</html>
