<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出库审核记录</title>
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
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="showCheckoutServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
            <!-- 
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>-->
            <div name="action" width="80" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            
            <div field="checkoutDate" width="60" headerAlign="center" dateFormat="yyyy-MM-dd" align="center">日期
            </div>
            <div field="checksheetId" width="110" headerAlign="center" align="center">单号
            </div>  
            <div field="orderId" width="110" headerAlign="center" align="center">订单号
            </div> 
            <div field="companyname" width="60" headerAlign="center" align="center">收货单位
            </div>
            <div field="connector" width="60" headerAlign="center" align="center">收货人
            </div>
            <div field="connectortel" width="60" headerAlign="center" align="center">联系电话
            </div> 
          
            <div field="deliveryName" width="60" headerAlign="center" align="center">送货人
            </div>
         
            <div field="status" width="60" headerAlign="center" align="center" renderer="onGenderRenderer">状态</div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	      //  str += "<span>";
	       // str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.checkin_id+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	      //  str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='查看' href=javascript:ondSee(\'" + e.row.checksheetId+"\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	    	str += "<span>";
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.checksheetId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(checksheetId){
	    	window.location="editExamineCheckoutServlet?checksheet_id="+checksheetId;
		}

	    function ondSee(checksheetId){
	        
	    	window.location="seeExamineCheckoutServlet?checksheet_id=" +checksheetId;
		}
		
	 	
	  var Genders = [{id: '0', text: '待审核'},{id: '1', text: '审核通过'},{id:'2',text:'审核不通过'}];
    function onGenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    }
    

        
    </script>
  </body>
</html>
