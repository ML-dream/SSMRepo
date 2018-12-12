<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id = "dealitem" scope = "page" class ="cfmes.bean.DealItem" />
<%@ page  import="cfmes.bom.entity.ItemType" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
  ArrayList itemlist  = new ArrayList();
  itemlist = dealitem.GetItemType();
 
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>物料主文件维护</title>
    
	<link href="css/person.css" type=text/css rel=stylesheet>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
  <script>
    function check(){
    if((document.getElementById("additem_id")).value == ""){alert("请输入物料编号!");return;}
    if((document.getElementById("additem_type")).value == ""){alert("请输入物料类型!");return;}
    document.item.submit();
    }
  </script>
  </head>
  
	<body>
	<div>
	 <form id = "item" name = "item" action = "ItemSvlt" method = post class  = "zipcode" >
	     <table align="center" class = "data_list_table" width="">
	     		<tr><td class = "page_title">添加物料</td><th> 物料编号：</th><td><input type="text" id = "additem_id" name="additem_id" maxlength="63" size = "25" /><span class="red_star">*</span></td> 
	     		<th>物料类型：</th><td>
	     		<select id = "additem_type" name= "additem_type" style="width:150px" ><option value = "">---请选择---</option>
	     		<%ItemType itemtype ;
	     		for(int i =0;i<itemlist.size();i++){
	     		  itemtype =(ItemType)itemlist.get(i);%>
	     		<option value = "<%=itemtype.getItem_typeid() %>"><%=itemtype.getItem_typedesc() %></option>
	     		<% }
	     		 %>
	     		</select>
	     		<span class="red_star">*</span></td>
	     		<th>物料名称：</th><td><input type="text" name="additem_name" id="additem_name" maxlength="63" size="25"/></td>  
	     		
	     		<td><input type = "button" value = "提交" onclick="check();" /></td><td> <input type = "reset" value = "取消" /></td></tr>
	     </table>
	     </form>
	</div>
	<hr color = "yellow" >
	 <table id="showitem" align = "left" class = "data_list_table" width ="30%" style="float: left;">
	 </table>
	 
	 <table width="60%" style="display: none;" >
	 	<tr><td>物料号</td><td><input type="text" id="item_id" name="item_id"></td>
	 		<td>物料种类</td><td></td>
	 		<td>物料名称</td><td></td></tr>
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

	    function modify(itemId){
		    alert(itemId);
		}
		function deleteItem(itemId){
			alert(itemId);
		}
	</script>
</html>
















