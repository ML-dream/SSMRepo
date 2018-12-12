<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="dispatchBean" scope="page" class="com.wl.forms.DispatchBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>派工页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" href="./css/person1.css" type="text/css"></link>
	<script type="text/javascript" src="./scripts/jquery.min.js" charset="utf-8" language="utf-8"></script>
    <script type="text/javascript" src="./js/Dispatch_Select.js" charset="utf-8" language="utf-8"></script> 
    <script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	<!-- 
	<link rel="icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	 -->
	<link href="<%=basePath%>rili/index.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>rili/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>rili/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcore.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcalendar.min.js"></script>
	<script type="text/javascript">
		J(function(){
			J('#start_time').calendar({ format:'yyyy-MM-dd' });
			J('#end_time').calendar({ format:'yyyy-MM-dd' });
			
			J('#dispatch_starttime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#dispatch_endtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });	
		});
	</script>
  </head>
 
  <body style="width: 960px;margin: 0 auto;">
      <div class="page_title" style="float: left; border-right-width: 50px;" >任务选择</div>
      <div><a href="GT/demo/wlmachinegandt.html">&nbsp;设备甘特图</a></div>
    <form name="form" class="zipcode" action="" method=post>
    <table class="query_form_table" align="center" width="85%" >
    	<tr> 
             <th>起初时间：</th>
		    <td><input type="text" style="width:300px;" id="start_time" name="start_time"  onclick="J.calendar.get({time:false});"/></td>
             <th>最后时间：</th>
		    <td><input type="text" style="width:300px;" id="end_time" name="end_time"  
		    onclick="J.calendar.get({time:false});"
		    onchange="addSelect('end_time',this.value,'orderid');"/></td>
		    <td><input type="button" onclick="addSelect('end_time',this.value,'orderid');" value="选择订单"/></td>
         </tr>  
         
         
         
        <tr><th>订单号：</th>
		    <td ><select id="orderid" name="orderid" style="width:300px;" 
		    onchange="addSelect('orderid',1,'issue_num');">
		    <!-- 
<%          ArrayList arraylist=(ArrayList)dispatchBean.getOrder("partplan");
			
            if(arraylist!=null){
%>            <option value="">--请选择--</option>
<%              for(int i=0;i<arraylist.size();i++) {//System.out.println(arraylist.size());
%>              <option  value="<%=arraylist.get(i)%>"><%=arraylist.get(i)%></option>
<%              }
            }else{
%>              <option value="">--暂无数据--</option>
<%          }%></select> -->
			</td>
            
            <th>版本号：</th>
		    <td ><select id="issue_num" name="issue_num" style="width:300px;" 
		    onchange="addSelect('issue_num',this.value,'item_id');">
                 <option value="">--------</option></select></td>
         </tr>
         <tr> 
             <th>零件号：</th>
		    <td ><select id="item_id" name="item_id" style="width:300px;" 
		    onchange="addSelect('item_id',this.value,'oper_id');">
                 <option value="">--------</option></select></td>
             <th>工序号：</th>
		    <td ><select id="oper_id" name="oper_id" style="width:300px;"
		     onchange="addSelect('oper_id',this.value,'workerid');">
                 <option value="">--------</option></select></td>
         </tr>  

         
         <tr>
         	<th>开始时间</th>
         	<td>
         	    <input type="text" id="dispatch_starttime" name="dispatch_starttime" style="width:300px;"
         	    onclick="J.calendar.get({time:true});"/>
         	</td>
         	<th>结束时间</th>
         	<td>
         	    <input type="text" id="dispatch_endtime" name="dispatch_endtime" style="width:300px;"
         	    onclick="J.calendar.get({time:true});"/>
         	</td>
         </tr> 
                  
    </table> 
    <div class="page_title">所派员工和机床</div> 
	    <table class="query_form_table" align="center" width="100%" >
	    	<tr><th>选择员工：</th>
			    <td >
			    	<select id="workerid" name="workerid" style="width:300px;"
			     		onchange="addSelect('workerid',this.value,'machineid');">
	            		<option value="">--------</option>
	            	</select>
	            </td>
	        <th>选择机床：</th>
			    <td >
			    	<select id="machineid" name="machineid" style="width:300px;"
			     		onchange="dispatch('machineid',this.value)">
	            		<option value="">--------</option>
	            	</select>
	            </td>
	        </tr>        
        </table>   
    </form>
    <table id="showdispatched" class="query_form_table" align="center" width="100%">
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


