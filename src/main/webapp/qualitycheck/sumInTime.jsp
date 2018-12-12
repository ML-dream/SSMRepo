<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>工时统计</title>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
  </head>
  
  <body onbeforeunload = "return pageClose()">
  
  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
   				<td style= "width:70px;"></td>
 				<td>开始时间</td>
				<td>
					<input id="bdate" name ="bdate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="true"/>
 				</td>
 				<td>结束时间</td>
				<td>
					<input id="edate" name ="edate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="true"/>
 				</td>
 				<td>
 					<input value="查询" type="button" style= "width:70px;" onclick="search()" />
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
    
    <script type="text/javascript">
    	mini.parse();
	    
		//部门选择 
   function onButtonEdit2(e) {
            var btnEdit = this;
            mini.open({
                url: "deptManage/selectDeptTreeWindow.jsp",
                title: "选择上级部门",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.deptId);
                            btnEdit.setText(data.deptName);
                        }
                    }
                }
            });
        }
        
        function search(){
        	var dept = mini.get("dept").getValue();
        	var bdate =  mini.get("bdate").getFormValue();
        	var edate =   mini.get("edate").getFormValue();
        	$.ajax({
				type:"post",
				url: "SumInTime",
				data:{dept:dept,bdate:bdate,edate:edate},
				cache: false,
				success: function (text){
				}
			});
        }
        
        //页面关闭时，删除表中数据  
       function pageClose(){
       		$.ajax({
				type:"post",
				url: "SumInTimeClose",
				cache: false,
				success: function (text){
					return "ok";
				}
			});
       }	
        
    </script>
  </body>
</html>
