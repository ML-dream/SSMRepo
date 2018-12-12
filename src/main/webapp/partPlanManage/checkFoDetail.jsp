<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户信息</title>
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
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
          url="CheckFoDetailServlet?productId=${productId}&issueNum=${issueNum}" >
        <div property="columns">
            <div type="indexcolumn"></div>
            <div field="foNo" width="80" headerAlign="center">工序编号</div>
            <div field="foOpName" width="100" headerAlign="center">工序名称</div>
            <div field="operTime" width="50" headerAlign="center">工序时间</div>
            <div field="ratedTime" width="50" headerAlign="center">额定工时</div>
            <div field="planTime" width="50" headerAlign="center">计划工时</div>
            <div field="operAidTime" width="50" headerAlign="center">辅助工时</div>
            <div field="inspTime" width="50" headerAlign="center">检验工时</div>
             <div field="isCo" width="50" headerAlign="center" renderer="onGenderRenderer">是否外协</div>
            <div field="isKey" width="50" headerAlign="center" renderer="onGenderRenderer">关键工序</div>
            <div name="foOpcontent"  width="170" headerAlign="center" align="center"  renderer="onPaperDownload"
                 cellStyle="padding:0;">工序内容
            </div>   
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
    
    function refresh(){
		grid.reload();
	}
    
     function back(){
			window.history.back(-1);
		}
    function onPaperDownload(e) {
	    	var paper = e.row.foOpcontent;
	    	var str = "";
	    	if(""==paper||null==paper||undefined==paper){
	    		str += "<span>";
	        	str += "无工艺文件";
	        	str += "</span>";
	    	}else{
	    		str += "<span>";
	        	str += "<a style='margin-right:2px;' title='点击下载图纸' href='DownLoadOrderFileServlet?filename="+paper+"'>"+e.row.foOpcontent+"</a>";
	        	str += "</span>";
	    	}
	        return str;
	    }
        var Genders = [{id: "0", text: "否"},{id: "1", text: "是"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }       
    </script>
  </body>
</html>
