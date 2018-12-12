<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
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
    			<th>父产品号</th><th>产品数量</th><th>产品规格</th><th colspan="2">交付日期</th></tr>
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
        		<!--<td align=center colspan="2">${result.memo}</td>
    		-->		<td align=center colspan="2">${result.eTime}</td>
    			</tr>
  		</table>
   </fieldset>
   
   <div id="win1" class="mini-window" title="查找工艺路线" style="width:650px;height:450px;" expanded="false" 
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" collapseOnTitleClick="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    <table >
   		<tr>
   			<td>零件名称</td>
          	<td><input id="sproductName" class="mini-textbox" value="" allowinput="true" onvaluechanged="seeProducts" />
          	</td>
          	<td>零件图号</td>
          	<td><input id="sproductId" class="mini-textbox" value="" allowinput="true" onvaluechanged="seeProducts" />
          	</td>
          	<td><a id="findFo" class="mini-button" iconCls="" onclick="seeProducts()">查询</a>
          		<a id="findFo" class="mini-button" iconCls="" onclick="applyFo()">应用此套工序</a>
          		<a id="" class="mini-button" iconCls="" onclick="seeQuotation()">查看零件报价</a>
          	</td>
   		</tr>
   	</table>
    <div id="winGrid" class="mini-datagrid" style="width:80%;height:40%;" allowResize="true" onload= ""
        url="DoneFoProduct"  idField="id" multiSelect="false" pagesize="20" onselect="setFoId()" onrowclick="setFoId()">
        <div property="columns">
            <div type="checkcolumn" ></div> 
            <div field="orderId" width="60" headerAlign="center" allowSort="false">订单号</div> 
            <div field="companyName" width="60" headerAlign="center" allowSort="false">客户</div>  
            <div field="productId" width="60" headerAlign="center" allowSort="false">产品号</div> 
            <div field="productName" width="40" headerAlign="center" allowSort="false">产品名称</div> 
            <div field="issueNum" width="40" headerAlign="center" allowSort="false">版本号</div>  
         </div>   
     </div>
     <div id="foGrid" class="mini-datagrid" style="width:99%;height:60%;" borderStyle="border:0;" idField="id"  allowResize="true"
         url="" onshowrowdetail="onShowRowDetail"
         showSummaryRow="true" allowAlternating="true" >   
        <div property="columns">
      <!--   <div type="expandcolumn" >#</div> -->

            <div field="foNo" width="70" headerAlign="center">工序编号</div>
            <div field="foOpName" width="70" headerAlign="center">工序名称</div>
            <div field="workBranchName" width="30" headerAlign="center">工种</div>
            <div field="foOpcontent" width="200" headerAlign="center">工序内容</div>
            <div field="cut" width="50" headerAlign="center">刀具</div>
            <div field="tool" width="50" headerAlign="center">夹具</div>
            <div field="operAidTime" width="40" headerAlign="center">辅助工时</div>
            <div field="operTime" width="40" headerAlign="center">总工时</div>
        </div>
    </div>
</div>
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
	   	function showAtPos() {
	        var win = mini.get("win1");
	
	        var x = "right";
	        var y = "top";
	
	        win.showAtPos(x, y);
	
	    }
		showAtPos();
		function seeProducts (){
		//查找产品 
			var grid = new mini.get("winGrid");
			var productId = mini.get("sproductId").getValue();
			var productName = mini.get("sproductName").getValue();
			grid.load({productId:productId ,productName:productName});
		}
		function setFoId(){
		//  点击行事件 
			var grid = new mini.get("winGrid");
			var row = grid.getSelected();
			var productId = row.productId;
			var issueNum = row.issueNum;
			
			var foGrid = new mini.get("foGrid");
			foGrid.setUrl("BaseServlet?meth=CraftworkList&productId="+productId+"&issueNum="+issueNum);
			foGrid.load();
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
  		function seeQuotation(){
  			var productId = document.getElementById("productId").innerHTML;//当前零件 
			var orderId = document.getElementById("orderId").innerHTML;
	   		window.open("OrderConfirmDetail?orderId=" + orderId+"&productId="+productId,
               "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
  			
  		}
   </script>
  </body>
</html>
