<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
		<base href="<%=basePath%>">
    <!-- miniui.js -->
          <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		<title>不合格品审理单</title>
		
		<style type="text/css">
			.mini-grid-cell-inner, .mini-grid-headerCell-inner {
                font-size: 10pt;
                font-family: Tahoma, Verdana, 宋体;
                line-height: 18px;
                padding: 0px;
                padding-top: 2px;
                padding-bottom: 2px;
                width: 100%;
                position: relative;
                overflow: hidden;
                white-space: normal;
                word-break: break-all;
            }
		</style>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>开始时间</td>
				<td>
					<input id="bdate" name ="bdate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="true"/>
 				</td>
 				<td>结束时间</td>
				<td>
					<input id="edate" name ="edate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="true"/>
 				</td>
 				<td>
 					<input value="查询" type="button" onclick="search()" />
 				</td>
 				<td>
 					<input value="保存修改" type="button" onclick="saveGrid()" />
 				
 					<input value="导出Excel" type="button" onclick="outPut()" />
 				</td>
			</tr>
			<tr>
				<td>按条码号查询</td>
				<td>
					<input id="barcode" name ="barcode" class="mini-textbox" allowInput="true" onenter="search()"/>
 				</td>
 				<td>产品来源</td>
				<td>
					<input id="sourcer" name="sourcer" class="mini-combobox" width="125" textName="" textField="text" valueField="id" value=""
  						url="data/rejSource.txt"  allowInput="false" showNullItem="false" nullItemText="请选择..."  onvaluechanged="search"/>
 				</td>
 				<td>处理措施</td>
				<td>
					<input id="opinion" name="opinion" class="mini-combobox" width="125" textName="" textField="text" valueField="id" value=""
  						url="qualitycheck/json/byOpinion.txt"  allowInput="false" showNullItem="false" nullItemText="请选择..."  onvaluechanged="search"/>
 				</td>
 				<!--<td>
 					<input value="确定" type="button" onclick="search()" />
 				</td>
 				-->
			</tr>
		</table>
	</div>
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:1300px;height:480px;" allowResize="true" showColumnsMenu="true" allowCellEdit="true" allowCellSelect="true"
         showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell" url="LoadAllStateJudge"  idField="id" multiSelect="true"
          pagesize="10" >
        <div property="columns">
           	<div type="indexcolumn"></div><!--  <div type="checkcolumn" ></div> -->
           	<div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
           	
           	<div field="checkdate" width="60" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">质检日期</div>
           	<div field="companyName" width="110" headerAlign="center" allowSort="false" headerStyle="height:30px;">客户</div> 
           	<div field="productName" width="60" headerAlign="center" allowSort="false">零件名称</div>
           	 
           	<div field="shouldBe" width="60" headerAlign="center" allowSort="false" headerStyle="color:red;">应测值
           		<input property="editor" class="mini-textarea" style="width:100%;" minHeight="80"/>
           	</div> 
           	<div field="realBe" width="60" headerAlign="center" allowSort="false" headerStyle="color:red;">实测值
           		<input property="editor" class="mini-textarea" style="width:100%;" minHeight="80"/>
           	</div> 
           	
           	<div field="rejNum" width="50" headerAlign="center" allowSort="false">数量</div> 
           	<div field="dutyerName" width="80" headerAlign="center" allowSort="false">责任者</div> 
           	<div field="rejTypeName" width="60" headerAlign="center" allowSort="false">问题分析</div> 
           	<div field="opinion" width="40" headerAlign="center" allowSort="false" renderer="onOpinionRenderer" >处理措施</div> 
           	<div field="qualityLoss" width="40" headerAlign="center" allowSort="false">经济损失</div> 
           	<div field="qualityFee" width="50" headerAlign="center" allowSort="false" vtype="float;" headerStyle="color:red;">质量罚款
           		<input property="editor" class="mini-textbox" style="width:100%;height:100%;"/>
           	</div>
           	 
           	<div field="proSource" width="40" headerAlign="center" allowSort="false" renderer="onSourceRenderer" >产品来源</div> 
            <div field="runnum" width="70" headerAlign="center" allowSort="false">审理单号</div>  
              
            <div field="fo_no" width="60" headerAlign="center" allowSort="false" visible="false">工序号</div> 
            <div field="fo_opname" width="60" headerAlign="center" allowSort="false" visible="false" >工序名称</div>   
            
           
            <div field="barcode" width="60" headerAlign="center" allowSort="false" visible="false">条码号</div>
            <div field="remark" width="60" headerAlign="center" allowSort="false" visible = "false" visible="false">备注</div>
            <!-- 需配合datagrid的allowCellEdit等属性一起用 -->
        </div>
     </div>
   </div>
</body>
<script type="text/javascript">
	mini.parse();
	var grid= mini.get("datagrid1");
	grid.load();
	
	grid.on("beforeload", function (e) {
       var data = grid.getChanges();
       if (data.length > 0) {
        mini.alert("请先保存");
          e.cancel = true;
          //return;
       }
   });
    function outPut(){
    	var bdate = mini.get("bdate").getFormValue();
		var edate = mini.get("edate").getFormValue();
		
		var barcode = mini.get("barcode").getValue();
		var sourcer = mini.get("sourcer").getValue();
		
		var opinion = mini.get("opinion").getValue();
		
    	window.location="RejStateExcelOut?bdate="+bdate+"&edate="+edate+"&barcode="+barcode+"&sourcer="+sourcer+"&opinion="+opinion;
    }
    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='查看' href=javascript:rowclick(\'" + e.row.barcode+"\',\'"+e.row.runnum+"\',\'"+e.row.fo_no+"\',\'"+e.row.opinion+"\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        
	        return str;
	    }
    
    
	function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            //服务端汇总计算
            if (e.field == "rejNum") {                
                var s = "<b style='color:red;'>"
                s +=  	"不合格数：<br/>"+ result.numSum + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "qualityLoss") {                
                var s = "<b style='color:red;'>"
                s +=  	"损失：<br/>"+ result.lossSum + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "qualityFee") {                
                var s = "<b style='color:red;'>"
                s +=  	"罚款：<br/>"+ result.feeSum + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
        }
	
	
	function saveGrid(){
		grid.validate ( );
	 	if(!grid.isValid ( )){
	 		return;
	 	}
	 	var griddata = grid.getChanges();
   		var gridjson = mini.encode(griddata);
	 	//alert(gridjson);
	 	
		$.ajax({
	  		type:"post",
	  		url:"RejStateSave",
	 	    data:{gridjson:gridjson},
	 	    dataType:"json",
	 		success: function(data){
	  			alert(data.result);
	  			grid.accept ( );
	  			grid.reload();
	  		}
	  	});
		
	}
	
	function search(){
		var bdate = mini.get("bdate").getFormValue();
		var edate = mini.get("edate").getFormValue();
		
		var barcode = mini.get("barcode").getValue();
		var sourcer = mini.get("sourcer").getValue();
		
		var opinion = mini.get("opinion").getValue();
		grid.load({bdate:bdate,edate:edate,barcode:barcode,sourcer:sourcer,opinion:opinion});
	}
	
	var opinion = [{ "id": "toDiscard", "text": "报废" },{ "id": "toFix", "text": "返修" },{ "id": "toSale", "text": "超差使用" },{"id": "none", "text":"无"}];
        function onOpinionRenderer(e) {
            for (var i = 0, l = opinion.length; i < l; i++) {
                var g = opinion[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
	var source = [{ "id": "self", "text": "本厂" },{ "id": "outer", "text": "外协" }];
        function onSourceRenderer(e) {
            for (var i = 0, l = source.length; i < l; i++) {
                var g = source[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        
	function rowclick(barcode,runnum,fo_no,opinion){
		//var grid= mini.get("datagrid1");
		//var row = e.row;
		var url = "qualitycheck/stateJudge.jsp";
		if(opinion =="empty"){
			url="qualitycheck/stateJudge2.jsp";
		}
		mini.open({
		    url: url,
		    title: "审理单", 
		    width: 880, height: 660,
		    onload: function () {
		    	var iframe = this.getIFrameEl();
					var iframedata = { "runnum" : runnum, "barcode" : barcode, "fo_no" : fo_no};
					//待做，这里加载应与质检新建审理单区分开来。 
    				iframe.contentWindow.loadData(iframedata);
		    },
		    ondestroy: function (action){
		   }
		});
	}
</script>
</html>