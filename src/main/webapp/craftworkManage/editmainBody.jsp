<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/css/person.css" type=text/css rel=stylesheet>
    <title>工艺主界面</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="width: 100%;height:100%;">
  	<!--
	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  	</div>
  	-->
	<fieldset style="width: 99%;" align="center">
		<legend>
			工艺
		</legend>   	
	   	<table class="query_form_table" width="100%" align="center"  border="0" style="word-break:break-all;">
			<tr align=center><th width="25%">订单号</th><th>产品编号</th><th>产品名称</th><th>版本号</th><th>图号</th><th>产品类型</th></tr>
			<tr><td align=center id="orderId" name="orderId">${result.orderId}</td>
    			<td align=center id="productId" name="productId">${result.productId}</td>
    			<td align=center>${result.productName}</td>
    			<td align=center id="issueNum" name="issueNum">${result.issueNum}</td>
    			<td align=center id="drawingId" name="drawingId">${result.drawingId}</td>
    			<td align=center>${result.itemTypeName}</td><input id="productType" name="productType" class="mini-hidden" value="${result.itemTypeId}"/>
    			<!--
        		<td align="center"><input id="save" type="button" disabled="disabled" value="保存" style="cursor:hand;" onclick="doB('save');"/>
        		
        			<FONT color="#338800"><A onclick="doB('edit');" style="cursor:hand;">[编辑]</A><A onclick="doB('del');" style="cursor:hand;">[删除]</A></FONT>
        		-->
        		</td>
    		</tr>
    		<tr><td><!--  <a class="mini-button" iconCls="icon-add" plain="false"  onclick="doB('add');">添加子物料</a>--></td>
    			<th>父产品号</th><th>产品数量</th><th>产品规格</th><th colspan="2">备注</th></tr>
    		<tr>
    			<td>
    				<c:choose>
    					<c:when test="${isLeaf=='0'}">
    						<c:if test="${isAoHeader=='1'}">
    							<a class="mini-button" iconCls="icon-node" plain="false" onclick="doB('listaofo');">查看装配工艺</a>
    						</c:if>
    						<c:if test="${isAoHeader=='0'}">
    							<a class="mini-button" iconCls="icon-add" plain="false" onclick="doB('addaofo');">添加装配工艺</a>
    						</c:if>
    						
    					</c:when>
    				</c:choose>
    				<c:choose>
    					<c:when test="${isHere=='1'}">
    						<a class="mini-button" iconCls="icon-node" plain="false"  onclick="doB('listfo');">查看制造工艺</a>
    					</c:when>
    					<c:otherwise>
    						<a id="addfo" class="mini-button" iconCls="icon-add" plain="false"  onclick="doB('addfo');">添加制造工艺</a>
    					</c:otherwise>
    				</c:choose>
    				
    			</td>
    			<td align=center id="fproductId" name="fproductId">${result.fproductId}</td>
        		<td align=center>${result.productNum}</td>
        		<td align=center>${result.spec}</td>
        		<td align=center colspan="2">${result.memo}</td>
    		</tr>
  		</table>
   </fieldset>
   
	<div id= "temp" class="mini-hidden">
		<table>
			<tr> <td><input id="test" class="mini-hidden" value="" allowinput="false"  /></td></tr>
		</table>
	</div>
   
   <iframe id="subIframe" frameborder="0" name="subIframe" style="width:100%;height:75%;" onLoad="javascript:reinitIframeEND();"  ></iframe>
   
   <script type="text/javascript">
   		mini.parse();
   		function refresh(){
   		//给foheader 页面调用，不做它用。禁用添加按钮 
   			mini.get("addfo").hide ( );
   		}
   		function applyFo(){
   			var grid = new mini.get("winGrid");
			var row = grid.getSelected();
			var sproductId = row.productId;	//模版，零件 
			var sissueNum = row.issueNum;	//模版
			
			var productId = document.getElementById("productId").innerHTML;//当前零件 
		    var issueNum = document.getElementById("issueNum").innerHTML;//当前零件 
			var productType = mini.get("productType").getValue();
			var orderId = document.getElementById("orderId").innerHTML;
			
			var form = new mini.Form("#temp");
			var data = form.getData();
			 
			data.sproductId=sproductId;
			data.sissueNum=sissueNum;
			data.productId=productId;
			data.issueNum=issueNum;
			data.productType=productType;
			data.orderId = orderId;
			
			var params = eval("("+mini.encode(data)+")");
			 jQuery.post("ApplyFo", params, function(data){
			 	//var text = mini.decode(data);
   	   		      alert(data.result);
   	   		 },'json');
			
   		}
   		function doB(doingtype){
   			var orderId = document.getElementById("orderId").innerHTML;
		    var productId = document.getElementById("productId").innerHTML;
		    var fproductId = document.getElementById("fproductId").innerHTML;
		    var issueNum = document.getElementById("issueNum").innerHTML;
		    var drawingId = document.getElementById("drawingId").innerHTML;
   			
   			if(doingtype == "addfo"){
		    	document.getElementById("subIframe").src=
		    		"BaseServlet?meth=GetFoHeader&pathTo=foHeader&orderId="+orderId+"&productId="+productId+"&FProductId="+fproductId+"&isHere=0";
		    }
		    if(doingtype == "listfo"){
		    	document.getElementById("subIframe").src=
		    		"BaseServlet?meth=GetFoHeader&pathTo=foHeader&orderId="+orderId+"&productId="+productId+"&FProductId="+fproductId+"&isHere=1";
		    }
   			
		    if(doingtype == "addaofo"){
		    	document.getElementById("subIframe").src=
		    		"BaseServlet?meth=GetAoHeader&pathTo=AoHeader&orderId="+orderId+"&productId="+productId+"&issueNum="+issueNum+"&drawingId="+drawingId+"&isHere=0";
		    }
		    if(doingtype == "listaofo"){
		    	document.getElementById("subIframe").src=
		    		"BaseServlet?meth=GetAoHeader&pathTo=AoHeader&orderId="+orderId+"&productId="+productId+"&issueNum="+issueNum+"&drawingId="+drawingId+"&isHere=1";
		    }
		    else if(doingtype == "edit"){
				    document.getElementById("item_numbt").disabled = "";
				    document.getElementById("save").disabled = "";
				    return;
		    	 }else if(doingtype == "save"){
				        if(document.getElementById("item_numbt").value==""){
					        alert("请输入物料数量！");}
					        document.forsubmit2.action = "../BomEditServlet";
					        document.forsubmit2.submit();
				         	return;
				         }else if(doingtype == "addaofo"){
						   		 document.forsubmit.action = "turn.jsp";
						   	   }else if(doingtype =="del"){
									    alert("删除后不可恢复，是否确定要删除？");
									    document.forsubmit.action = "../bomdelservlet";
		   							 }
		    document.forsubmit.submit();
	    }
   		
   		function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
                var params = eval("("+mini.encode(data)+")");
                var url = 'AddMachineServ';
   		        jQuery.post(url, params, function(data){
   	   		  		mini.confirm(data.result, "刷新页面 ？",
		                 function (action){            
		                     if (action == "ok") {
		                    	window.location.href = window.location.href;	
		                     }
		                 }
		             );
   	   		        },'json');
            }
   		}
   		

   	   	var Genders = [{ id: '1', text: '是' }, { id: '0', text: '否'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        
        function autoHeight() {   
			var ifm= document.getElementById("subIframe");   
			var subWeb = document.frames?document.frames["subIframe"].document:ifm.contentDocument;   
			if(ifm != null && subWeb != null) {
   				ifm.height = subWeb.body.scrollHeight;
   				ifm.width = subWeb.body.scrollWidth;
			}   
		}  
		window.onload=reinitIframeEND();
		
		var timer1 = window.setInterval("reinitIframe()", 1500); //定时开始  
		function reinitIframeEND(){  
			var iframe = document.getElementById("subIframe");  
			try{  
    			var bHeight = iframe.contentWindow.document.body.scrollHeight;  
    			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    			var height = Math.max(bHeight, dHeight);  
    			iframe.height = height;  
			}catch (ex){}  
			// 停止定时  
			window.clearInterval(timer1);  
  		} 
   </script>
  </body>
</html>
