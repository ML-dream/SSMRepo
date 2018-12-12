<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>退料记录</title>
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
         url="showTlSheetServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
           
            <div name="action" width="80" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            
            <div field="rmDate" width="60" headerAlign="center" dateFormat="yyyy-MM-dd" align="center">日期
            </div>
          <div field="rmSheetid" width="110" headerAlign="center" align="center">单号
          </div>  
            <div field="warehouseName" width="110" headerAlign="center" align="center">库房
            </div>
            <div field="empName" width="60" headerAlign="center" align="center">退料人
            </div>
            <div field="dept" width="60" headerAlign="center" align="center">所属部门
            </div>
              <div field="operatorName" width="60" headerAlign="center" align="center">操作员
            </div>
             
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
	        str += "<a style='margin-right:2px;' title='查看' href=javascript:ondSee(\'" + e.row.rmSheetid+"\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	    	str += "<span>";
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.rmSheetid+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(rmSheetid){
	    	window.location="editTlSheetServlet?rmSheetid="+rmSheetid;
		}

	    function ondSee(rmSheetid){
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	//window.location="ShowOrderAllServlet?orderId=" + orderId+"&connector="+connector;
	    	window.location="showTlSheetDetailServlet?rmSheetid=" +rmSheetid;
		}
        
	    
	 </script>
  </body>
</html>
