<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
        <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>初期数据维护</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:99%">
  <div class="mini-toolbar">
  <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-add" plain="false" onclick="save()">保存</a>
  </div>
  	<form name="userdiv" id="userdiv" action="PrimaryDataMaintainServlet" method="post" >
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="primaryTime$text">初期时间</label></td>
            <td><input id="primaryTime"  name="primaryTime" class="mini-datepicker"  width="100%" required="true" /></td>
            <td><label for="payNum$text">初期应付款</label></td>
            <td><input id="payNum"  name="payNum" class="mini-textbox" style="width:100%" required="true" /></td>
   			<td><label for="reason$text">修改原因/备注</label></td>
            <td><input id="reason"  name="reason" class="mini-textbox"  width="100%" />
            <input class="mini-hidden" id="companyId" name="companyId" value="${companyId}"/></td>
       </tr>
       </table>
  	</form>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="ShowPrimaryDataMaintainServlet?companyId=${companyId}" >
         <div property="columns"> 
         <div type="indexcolumn">序号</div>
    	 <div field="primaryTime" headerAlign="center" align="center"  dateFormat="yyyy-MM-dd" >初期时间</div>
         <div field="payNum" headerAlign="center" align="center">初期应付款</div>
         <div field="modifyTime" headerAlign="center" align="center" dateFormat="yyyy-MM-dd" >维护时间</div>
         <div field="reason" headerAlign="center" align="center">修改原因/备注</div>
 <!--    <div field="modifyPerson" headerAlign="center" align="center">维护人</div>   -->     
       </div>
    </div>
      

    <script type="text/javascript">
      mini.parse();
      var grid=mini.get("grid1");
      grid.load();
      
      
      
  function back(){
			window.history.back(-1);
		} 
      
     function save(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.primaryTime =mini.get("primaryTime").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'PrimaryDataMaintainServlet';
   		        jQuery.post(url, params, function(data){
                 alert(data.result);
                 grid.load();
   	   		        },'json');
            }
   		}

      
      </script> 
  </body>
</html>