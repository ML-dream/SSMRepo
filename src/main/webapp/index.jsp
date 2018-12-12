<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>bom版本添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/person.css" type=text/css rel=stylesheet>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
  </head>
  
  <script type="text/javascript">
  //clearok(),toservlet()
  function toservlet(){
     if (document.all.product_type.value=="" ){alert("请输入产品类型！");document.all.product_type.focus();return ;}	
	 if (document.all.product_id.value=="" ){alert("请输入产品号！");document.all.product_id.focus();return ;}
	 if (document.all.lot.value=="" )       {alert("请输入批次！");document.all.lot.focus();return ;}	
	 if (document.all.b_sortie.value=="" )  {alert("请输入起始编号！");document.all.b_sortie.focus();return ;}
	 if (document.all.e_sortie.value=="" )  {alert("请输入终止编号！");document.all.e_sortie.focus();return ;}	
	 if (document.all.issue_num.value=="" ) {alert("请输入版本！");document.all.issue_num.focus();return ;}
	 if (document.all.e_sortie.value < document.all.b_sortie.value)  {alert("终止编号必须大于起始编号！");return ;}
		document.all.form1.submit();
  }
  function clearOk(){   
      document.all.product_type.value= "";	
	  document.all.product_id.value= "" ;  
	  document.all.lot.value= "";          
	  document.all.b_sortie.value= "";      
	  document.all.e_sortie.value= "";      
	  document.all.issue_num.value= "";    
    }
  
  </script>
  
  <body>
  
    <form name=form1 action = "IsuSvlt" method = post>
     <table class="data_list_table" width="100%" align="center"  border="1" style="word-break:break-all;">
  <TR  height="22">
    <Th align=center  ><font color="#000000" ><B>产品类型</B></font></th>
    <Th align=center  ><font color="#000000" ><B>产品号</B></font></th>  
    <Th align=center  ><font color="#000000" ><B>版本号</B></font></th> 
    <Th align=center  ><font color="#000000"><B>批次</B></font></th>
    <Th align=center  ><font color="#000000"><B>起始编号</B></font></th>
    <Th align=center  ><font color="#000000"><B>终止编号</B></font></th>
    <Th align=center  ><font color="#000000"><B>备注</B></font></th>
    <Th align=center  ><font color="#000000"><B>操作</B></font></th>
    <Th align=center><input type="button" disabled="true" value="下一步"  onclick="javascript:top.window.location.href='bom_maitn_in.jsp';" style="cursor:hand;"></th>
  </TR>  
  <TR>
    <td align=left><input type=text id="product_type" name="product_type"  size="12" maxlength="40" ></td>
    <td align=left><input type=text id="product_id" name="product_id"    size="45" maxlength="120" ></td>
	<td align=center><input type=text value =""  name="issue_num"  size="10" maxlength="100" ></td>
	<td align=center><input type=text name="lot"  size="8" maxlength="10" ></td>
	<td align=center><input type=text name="b_sortie"  size="8" maxlength="4"></td>
	<td align=center><input type=text name="e_sortie"  size="8" maxlength="4" ></td>
	<td align=center><input type=text name="memo"  size="8" maxlength="180" ></td>
	<td align=right><input type="button"  value="提 交" title="保存新的版本" onclick="toservlet();" style="cursor:hand;"></td>
	<td align=right><input type="button"  value="重 置" title="清空文本框" onclick="clearOk();" style="cursor:hand;"></td>
  </TR>
  </table>
  </form>

	<table id="showitem" align = "left" class = "data_list_table" width ="30%" style="float: left;">
	 </table>
  </body>
  
  
  <script type="text/javascript">
		$.ajax({
	        url: "ItemDetailServlet",
	        type: "POST",
	        //data: id,
	        success: function (text) {
	        	showItem(text);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert("加载失败, 错误码：" + jqXHR.responseText);
	        }
	    });

		
	    function showItem(xmlData){
		    var item_id = xmlData.getElementsByTagName("item_id");
		    var item_type = xmlData.getElementsByTagName("item_type");
		    var item_name = xmlData.getElementsByTagName("item_name");
		    
		    var pageNow = xmlData.getElementsByTagName("pageNow");
		    var pageSize = xmlData.getElementsByTagName("pageSize");
		    var totalNum = xmlData.getElementsByTagName("totalNum");
		    
			pageNow = parseInt(pageNow[0].firstChild.nodeValue);
			pageSize = parseInt(pageSize[0].firstChild.nodeValue);
			totalNum = parseInt(totalNum[0].firstChild.nodeValue);

	    	var html = "<tr><th>操作</th><th>物料编号</th><th>物料种类</th><th>物料名称</th></tr>";
	    	for ( var i = 0; i < item_id.length; i++) {
				html += "<tr>";
				html += "<td>";
				html += "<a onclick=\"modify('"+item_id[i].firstChild.nodeValue+"');\"  sytle=\"cursor: hand;\"><img title=\"编辑\" src=\"<%=basePath%>img/bt_edit.gif\" /></a>&nbsp;&nbsp;&nbsp;&nbsp;";
				html += "<a onclick=\"deleteItem('"+item_id[i].firstChild.nodeValue+"');\"  sytle=\"cursor: hand;\"><img title=\"删除\" src=\"<%=basePath%>img/bt_del.gif\" /></a>";
				html += "</td>";
				html += "<td>" + item_id[i].firstChild.nodeValue+ "</td>"
				html += "<td>" + item_type[i].firstChild.nodeValue + "</td>";
				html += "<td>" + item_name[i].firstChild.nodeValue+ "</td></tr>";
			}

	    	html += "<tr><td ></td>"+
			"<td ><a id='first' onclick=\"fenye(1,"+pageSize+","+totalNum+")\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
			"<a id='previous' onclick=\"fenye("+parseInt(pageNow-1)+","+pageSize+","+totalNum+")\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
			"</td><td><a id='next' onclick=\"fenye("+parseInt(pageNow+1)+","+pageSize+","+totalNum+")\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
			"<a id='last' onclick=\"fenye("+parseInt(totalNum/pageSize+1)+","+pageSize+","+totalNum+")\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
			"</td><td><select onchange=\"fenye("+parseInt(1)+",this.value,"+totalNum+")\";><option value='5' selected='selected'>-请选择-</option><option value='10'>10</option><option value='20'>20</option><option value='50'>50</option><option value='100'>100</option></select>"+
			pageNow+"/"+parseInt(totalNum/pageSize+1)+"&nbsp;&nbsp;共"+totalNum+"条</td></tr>";
			
			var table1 = document.getElementById("showitem");
			while (table1.hasChildNodes()) {
				table1.removeChild(table1.firstChild);
			}
			$(html).prependTo(table1);
		}

	    function fenye(pageNow,pageSize,totalNum){
		    if(pageNow<1 || parseInt(pageNow)>parseInt(totalNum/pageSize+1)){
				return ;
			}else{
				$.ajax({
			        url: "ItemDetailServlet?pageNow="+pageNow+"&pageSize="+pageSize+"&totalNum="+totalNum,
			        type: "POST",
			        //data: id,
			        success: function (text) {
			        	showItem(text);
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            alert("加载失败, 错误码：" + jqXHR.responseText);
			        }
			    });
			}
	    	
	    }
	    </script>
</html>
