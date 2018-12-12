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
    <title>工艺调整主界面</title>
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
    			<th>父产品号</th><th>产品数量</th><th>产品规格</th><th colspan="2">交付时间</th><th >原材料</th></tr>
    		<tr>
    			<td align=center id="fproductId" name="fproductId">${result.fproductId}</td>
        		<td align=center>${result.productNum}</td>
        		<td align=center>${result.spec}</td>
        		<!--<td align=center colspan="2">${result.memo}</td>
    		-->		<td align=center colspan="2">${result.eTime}</td>
    			<td align=center >${result.matirial}</td>
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
   		doB();
   		function doB(){
   			var orderId = document.getElementById("orderId").innerHTML;
		    var productId = document.getElementById("productId").innerHTML;
		    var fproductId = document.getElementById("fproductId").innerHTML;
		    var issueNum = document.getElementById("issueNum").innerHTML;
		    var drawingId = document.getElementById("drawingId").innerHTML;
   			var  productType = mini.get("productType").getValue();
		    	document.getElementById("subIframe").src=
		    		"BaseServlet?meth=Gofodetail&pathTo=adjustFodetail&productType="+productType+"&productId="+productId+"&issueNum="+issueNum+"&orderId="+orderId+"&foId=0 &drawingId="+drawingId;
		    document.forsubmit.submit();
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
