<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>

<%
//模板自带的
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<% 
  //取得dotype的值,并将dotype值放入session共享
  String dotype  = ds.toString(request.getParameter("dotype")); 
  session.setAttribute("dotype",dotype);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>新增BHTCM和AO操作显示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../css/person.css" type=text/css rel=stylesheet>
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
  </head>
  
  <script>
  //toservlet函数首先检验输入格式是否错误，提交表单传值
  //这里的document.all从ie4就不应该使用..
  function toservlet(){
    if((document.getElementById("additem_id")).value == ""){alert("请输入物料编号!");return;}
    if((document.getElementById("additem_num")).value == ""){alert("请输入物料数量!");return;}
    document.forsubmit2.submit();
 }
</script>

  <body>
       <%if(dotype.isEmpty() || dotype.equals("")) {%>新增BHMTCM和AO操作在此处显示 <br><%}else if(dotype.equals("add")){ %>
       <form name = "forsubmit2" action = "../bomservlet">
       <table align="center" class = "data_list_table" width="50%" style="float: left;">
       		<tr><td>物料编号：</td><td><input type="text" id = "additem_id" name="additem_id" maxlength="63" size="47"></input><span class="red_star">*</span></td></tr> 
       		<tr><td >物料数量：</td><td><input type="text" id = "additem_num" name="additem_num"maxlength="63" size="47"></input><span class="red_star">*</span></td></tr>
       		<tr><td>废品率：</td><td><input type="text" name="addscrap"maxlength="63" size="47"></input></td></tr>  
       		<tr><td>备注信息：</td><td><textarea name = "addmemo" rows="" cols="45" ></textarea></td></tr>
       		<tr><td><input type = "button" value = "提交" onclick="toservlet()" ></td>
       		<td><input type = "reset" value = "取消"></td></tr>
       </table>
       </form>
       <table id="showitem" align = "left" class = "data_list_table"  style="float: left;">
	 </table>
       <%}else if(dotype.equals("del")){ %>删除bom节点操作<%}else{ %>未选择操作类型dotype=<%=dotype %><%} %>
  
  	
  </body>
  
  <script type="text/javascript">
		$.ajax({
	        url: "<%=basePath%>ItemDetailServlet",
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

	    	var html = "<tr><th>请选择零件</th><th>物料编号</th><th>物料种类</th><th>物料名称</th></tr>";
	    	for ( var i = 0; i < item_id.length; i++) {
				html += "<tr>";
				html += "<td align=\"center\">";
				html += "<a onclick=\"modify('"+item_id[i].firstChild.nodeValue+"');\"  sytle=\"cursor: hand;\"><img title=\"选择\" width=\"30px\" height=\"20px\" src=\"<%=basePath%>img/hand.gif\" /></a>&nbsp;&nbsp;&nbsp;&nbsp;";
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

	    function showTable(){
			document.getElementById("showitem").style.display="";
			alert("shown");
		}

	    function modify(itemId){
		    document.getElementById("additem_id").value="";
		    document.getElementById("additem_id").value=itemId;
		}
		function deleteItem(itemId){
			alert(itemId);
		}
	</script>
</html>
