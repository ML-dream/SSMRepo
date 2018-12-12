<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加报价</title>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">

	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  	<div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-save" plain="false" onclick="save()">保存</a>
		<span class="separator"></span>
    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()">刷新</a>
    </div>
  	
    <div id="grid1" class="mini-datagrid" style="width:100%;height:93%;" 
        url="ShowQuotationProductServlet?orderId=${orderId}&productId=${productId}&fproductId=${fproductId}&name=${name}" idField="id" 
        allowResize="true" borderStyle="border:0;"
        allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        editNextOnEnterKey="true"  editNextRowCell="true" pagerButtons="#buttons"
        showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell" showFilterRow="true" oncellendedit="ontotal"
    >
        <div property="columns">
            <div type="indexcolumn"></div>
            <!--
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            -->
            <div field="craftId" width="50" headerAlign="center">工艺编号</div>
            <div field="craftName" width="100" headerAlign="center">工艺名称</div>
            <div field="price" width="100" headerAlign="center">辅助单价</div>
            <div field="addPrice" width="100" headerAlign="center" onblur= "getTotal()" >实报单价
            	<input property="editor" class="mini-textbox" style="width:100%;" minWidth="80" emptyText="请输入报价"  />
            </div>
            <div field="unit" width="60" headerAlign="center">单位</div>
            <div field="totalTime" width="100" headerAlign="center" onblur= "getTotal()">实报工时
            	<input property="editor" class="mini-textbox" style="width:100%;" minWidth="80" emptyText="请输入报价" />
            </div>
            <div field="totalPrice" width="100" headerAlign="center" >总报价</div>
            <div field="grossProfit" width="100" headerAlign="center">毛利润</div>
        </div>
    </div>
    <!--
    <div id="buttons">
	    <span class="separator"></span>
	    <a class="mini-button" iconCls="icon-add" plain="true" id="add"></a>
	    <span class="separator"></span>
	    <a class="mini-button" iconCls="icon-edit" plain="true"></a>
	    <span class="separator"></span>
	    <a class="mini-button" iconCls="icon-remove" plain="true"></a>
	    <span class="separator"></span>
	    <a class="mini-button" iconCls="icon-save" plain="false"  onclick="save()">保存</a>
	    <span class="separator"></span>
	</div>
    -->
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.craftId + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(craftId){
	        window.open("EditPriceManHourServlet?craftId=" + craftId,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
		}
		
		function refresh(){
			grid.reload();
		}
		
		//精确的乘法结果
	   function accMul(arg1,arg2)  
		{  
			var m=0,s1=arg1.toString(),s2=arg2.toString();  
			try{m+=s1.split(".")[1].length}catch(e){}  
			try{m+=s2.split(".")[1].length}catch(e){}  
			return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)  
		}
		//加法
		function accAdd(arg1,arg2){  
			var r1,r2,m;  
			try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
			try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
			m=Math.pow(10,Math.max(r1,r2))  
			return (arg1*m+arg2*m)/m  
		}
		//减法
		function accSub(arg1,arg2){  
			return accAdd(arg1,-arg2);  
		}  
		function ontotal(e){
			var row = e.row;
			
			var addPrice = row.addPrice;
			var price = row.price;
			var totalTime = row.totalTime;
			if(totalTime==null||addPrice==null){
				return;
			}else{
				row.totalPrice=accMul(parseFloat(addPrice),parseFloat(totalTime));
				row.grossProfit = accMul((accSub(parseFloat(addPrice),parseFloat(price))),parseFloat(totalTime));
				
				grid.updateRow(row,row);
			}
		}
		function getTotal(){
			//这个 onblur 触发，需要点击两次才能取到正确的值 
			var row = grid.getSelected();
			
			var addPrice = row.addPrice;
			var price = row.price;
			var totalTime = row.totalTime;
			if(totalTime==null||addPrice==null){
				return;
			}else{
				row.totalPrice = parseFloat(addPrice)*parseFloat(totalTime);
				row.grossProfit = (parseFloat(addPrice)-parseFloat(price))*parseFloat(totalTime);
				grid.updateRow(row,row);
			}
		}

	    var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }

		function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            //服务端汇总计算
            if (e.field == "totalPrice") {                
                var s = "<b style='color:red;'>"
                /*
                s +=    "Total=" + result.total +"<br/>"
                        + 'Min age=' + result.minAge + "<br/>"
                        + 'Max age=' + result.maxAge + "<br/>"
                        + 'Avg age=' + result.avgAge + "<br/>"
                        + "</span>";
                */
 //               s +=  	"合计："+ result.sum + "/  <br/>总计："+result.toonDrawSummaryCelltalPrice+"<br/>"
                  s +=  	"合计："+ result.sum +"<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }

            if (e.field == "craftId") {                
                var s = "<b style='color:red;'>"
                s +=  	"订单号："+ result.orderId + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "craftName") {                
                var s = "<b style='color:red;'>"
                s +=  	"产品号："+ result.productId + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "price") {                
                var s = "<b style='color:red;'>"
                s +=  	"产品名称："+ result.name + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            
            if (e.field == "grossProfit") {                
                var s = "<b style='color:red;'>"
                s +=  	"合计毛利："+ result.totalGrossProfit + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            
            //客户端汇总计算
            if (e.field == "salary") {
                e.cellHtml = "平均: " + e.cellHtml;
            }
            if (e.field == "age") {
                e.cellHtml = "最大年龄: " + e.value;
            }
        }
        
        function getDataGrid(){
            var data="{";
            var gridData = grid.getData();
            var len = parseInt(gridData.length);
            var orderId = Request['orderId'];
            var productId = Request['productId'];
            var fproductId = Request['fproductId'];
            
            data+="\"orderId\":"+"\""+orderId+"\",";
            data+="\"productId\":"+"\""+productId+"\",";
            data+="\"fproductId\":"+"\""+fproductId+"\",";
            /*
            data+="\"data\":[{";
			for(i=0;i<len;i++){
				if(gridData[i].addPrice==undefined){
					data+="\""+gridData[i].craftId+"\":"+"\"0\",";
				}else{
					data+="\""+gridData[i].craftId+"\":"+"\""+gridData[i].addPrice+"\",";
				}
			}
			data+="\"productId\":"+"\""+productId+"\"}]}";
			*/
			
			data+="\"data\":\"";
			for(i=0;i<len;i++){
				if(gridData[i].addPrice==undefined){
					data+=gridData[i].craftId+"#"+"0#"+gridData[i].unit+",";
				}else{
					data+=gridData[i].craftId+"#"+gridData[i].addPrice+"#"+gridData[i].unit+"#"+gridData[i].totalTime+"#"+gridData[i].totalPrice+"#"+gridData[i].grossProfit+",";
				}
			}
			data = data.substring(0,data.length-1);
			data+="\"}";
			
			params = JSON.parse(data);
			url = "QuotationProductServlet";
			jQuery.post(url, params, function(data){
   	   			alert(data.result);
   	   			window.location.href=window.location.href;
   	   		},'json');
        }

        function save(){
        	if(grid.isChanged()){	//false表明没有修改
        		getDataGrid();
        	}else{
        		mini.alert("数据没有修改！");
        		return;
        	}
        }
        
        function IsChanged(){
        	alert();
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

        var Request = new Object(); 
        Request = GetRequest();
        //mini.get("orderId").value = Request['orderId'];
        //mini.get("FProductId").value = Request['productId'];

        
    </script>
  </body>
</html>
