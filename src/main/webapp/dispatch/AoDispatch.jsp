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
    
    <title>装配任务分派页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" href="./css/person1.css" type="text/css"></link>
	<script type="text/javascript" src="./scripts/jquery.min.js" charset="utf-8" language="utf-8"></script>
    <script type="text/javascript" src="./js/AoDispatch.js" charset="utf-8" language="utf-8"></script> 
  </head>
 
  <body>
      <div class="page_title" style="float: left; border-right-width: 50px;" >任务选择</div>
      <div><a href="GT/demo/wlmachinegandt.html">&nbsp;设备甘特图</a></div>
    <form name="form" class="zipcode" action="" method=post>
    <table class="query_form_table" align="center" width="100%" >
        <tr><th>订单号：</th>
		    <td ><select id="orderid" name="orderid" style="width:300px;" 
		    onchange="addSelect('orderid',this.value,'item_id');">
<%          ArrayList arraylist=(ArrayList)dispatchBean.getOrder("partplan");
			
            if(arraylist!=null){
%>            <option value="">--请选择--</option>
<%              for(int i=0;i<arraylist.size();i++) {//System.out.println(arraylist.size());
%>              <option value="<%=arraylist.get(i)%>"><%=arraylist.get(i)%></option>
<%              }
            }else{
%>              <option value="">--暂无数据--</option>
<%          }%></select></td>
          
             <th>产品号：</th>
		    <td ><select id="item_id" name="item_id" style="width:300px;" 
		    onchange="addSelect('item_id',this.value,'oper_id');">
                 <option value="">--------</option></select></td>
             <th>工位号：</th>
		    <td ><select id="oper_id" name="oper_id" style="width:300px;"
		     onchange="addSelect('oper_id',this.value,'worker');">
                 <option value="">--------</option></select></td>
         </tr>                    
    </table> 
    <div class="page_title">所派场地和员工</div> 
	    <table class="query_form_table" width="50%" >
	        <tr><th>选择员工：</th>
			    <td >
			    	<select id="worker" name="worker" style="width:150px;"
			     		onchange="addSelect('worker',this.value,'machineid');">
	            		<option value="">--------</option>
	            	</select>
	            </td>
	        </tr>
	        <tr><th>选择场地：</th>
			    <td >
			    	<select id="machineid" name="machineid" style="width:150px;"
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
</html>


