<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<jsp:useBean id="mbomdao" scope="page" class="cfmes.bom.dao.MbomDao"/>
<jsp:useBean id="partplandao" scope="page" class="cfmes.bom.dao.PartPlanDao"/>
<%@page import="java.util.*" import=" cfmes.bom.entity.PartPlan" import="cfmes.bom.entity.PartStatus"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%  
        String product_id = ds.toGBK(request.getParameter("product_id"));
		String issue_num = ds.toGBK(request.getParameter("issue_num"));
		String node_id = ds.toGBK(request.getParameter("node_id"));
		String dotype = ds.toGBK(request.getParameter("dotype"));	
 %>
 <% 
 /*得到父物料id*/
 String father_id =mbomdao.GetFatherId(product_id,issue_num,node_id);
 %>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>生产计划编制</title>
    
    
    <!-- 编制零件计划     预编制零件计划 -->
    
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/person.css" type=text/css rel=stylesheet>
	 <script language="javaScript" type="text/javascript" src="js/Calendar.js"></script>
	 <script type="text/javascript" src="js/Donode.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 
  </head>
  
  <script>
   function toservlet(){
    /*比较开始日期一定比计划日期要晚*/
   var a = document.getElementById("plan_time").value;
   var b = document.getElementById("plan_bgtime").value;
   if(a>b){alert("开始日期早于计划日期，请重新输入！");document.getElementById("plan_bgtime").value="";return;} 
   /*比较结束日期一定比开始日期要晚*/
   var c = document.getElementById("plan_bgtime").value;
   var d = document.getElementById("plan_edtime").value;
   if(c>d){alert("结束日期早于开始日期，请重新输入！");document.getElementById("plan_edtime").value="";return;}
    if((document.getElementById("plan_id")).value == ""){alert("请输入计划编号!");return;}
    if((document.getElementById("part_num")).value == ""){alert("请输入零件数量!");return;}
    if((document.getElementById("quality_id")).value == ""){alert("请输入质编号!");return;}
    if((document.getElementById("plan_bgtime")).value == ""){alert("请输入计划开始时间!");return;}
    if((document.getElementById("plan_edtime")).value == ""){alert("请输入计划结束时间!");return;}
    document.forsubmit4.submit();
 }
   
   function dochange(select){
   document.forsubmit5.do_type.value = select;
   if(select=="10"){
   
   }else{
   document.getElementById("partnum").readOnly=true;
   document.getElementById("plansttime").readOnly=true;
   document.getElementById("planedtime").readOnly=true;
   }
   }
  </script>
  
  <script>
  function showdate(time){
   /*显示当前日期*/
   var showcurtdate = document.getElementById(time);
   var date = new Date();
   var datemonth = date.getMonth()+1; 
  // alert(datemonth.toString().length);
   if(datemonth.toString().length==1){ datemonth="0"+datemonth;}else{}
   var datestring = date.getFullYear()+"-"+datemonth+"-"+date.getDate();
   showcurtdate.value = datestring;
   if(time=="plan_time"){
   /*计划开始时间和结束时间都默认是今天*/
   document.getElementById("plan_bgtime").value=datestring;
   document.getElementById("plan_edtime").value=datestring;
   }else{}
   }
   
   function editable(statu,sid){
   if(statu=="5"){
     if(sid=="partnum"){}else{
     //目的是时间可手动调整
     new Calendar(2000,2040).show(document.getElementById(sid));}
   }
  
   else{
   //零件数量、结束时间、开始时间不可编辑
   document.getElementById("partnum").readOnly=true;
   document.getElementById("plansttime").readOnly=true;
   document.getElementById("planedtime").readOnly=true;
   }
   }
   
   
  </script>
  <script>
  function doplan(){
   var temp = "";
   temp = document.getElementById("deal").value;
   switch(temp)
   {
    case "0":
    alert("please choose a dotype!");
    break;
    case "10":
    document.forsubmit5.submit();
    break;
    case "15":
    document.forsubmit5.submit();
    break;
    case "20":
    document.forsubmit5.submit();
    break;
    case "25":
    document.forsubmit5.submit();
    break;
   }
   }
  </script>
  <body >
  
  <%if(dotype.isEmpty() || dotype.equals("")){%>对mbom操作<% }else if(dotype.equals("0")){%>
    <form name = "forsubmit4" action = "partplan">
       <table align="center" class = "data_list_table">
       		<tr><th>计划编号：</th><td><input type="text" id = "plan_id" name="plan_id" maxlength="63" onfocus="showdate('plan_time');" size="30"></input></td>
       		<th>计划时间：</th><td><input type="text" id = "plan_time" name="plan_time"maxlength="63" readonly="readonly" size="30"></input></td>
       		<th>计划人：</th><td><input type="text" id = "plan_peo"name="plan_peo"maxlength="63" size="30"></input></td></tr>  
       		<tr> <th>零件号：</th><td><input type="text" id = "part_id"name = "part_id" value="<%=node_id %>" readonly="readonly" size="30" ></input></td>
       		 <th>零件父物料号：</th><td><input type="text" id = "father_id"name = "father_id" value="<%=father_id %>" size="30" ></input></td>
       	 <th>零件数量：</th><td><input type="text" id = "part_num"name = "part_num" size="30" ></input></td></tr>
       	 <tr><th>订单号：</th><td><input type="text"  id = "order_id"name = "order_id" size="30" ></input></td>
       	 <th>计划开始时间：</th><td><input type="text" id = "plan_bgtime"name = "plan_bgtime" onclick="new Calendar(2000,2040).show(this);"  size="30" ></input></td>
       	 <th>计划结束时间：</th><td><input type="text" id = "plan_edtime"name = "plan_edtime" onclick="new Calendar(2000,2040).show(this);"  size="30" ></input></td></tr>
       	 <tr><th>质编号：</th><td><input type="text"  id = "quality_id"name = "quality_id" size="30" ></input></td>       	
       		<td> <input type = "button" value = "提交" onclick="toservlet()" ><input type = "reset" value = "取消"></td><td><br></td></tr>
      		<tr><td><input type="hidden" id="product_id" name = "product_id" value="<%=product_id %>"></input></td><input type="hidden" id="issue_num" name = "issue_num" value="<%=issue_num %>"></input><td><br></td></tr>
      		
       </table>
     </form>
     <%}else{%>
     <form name = "forsubmit5" action = "DoPartPlan">
     <table class="query_form_table" border="1" align="center"  cellspacing="0" width="100%" style="word-break:break-all;">
     <tr><th>操作</th><th>操作时间</th><th>产品号</th><th>版本</th><th>订单号</th><th>计划号</th>
     <th>质编号</th><th>计划员</th><th>状态</th><th>数量</th><th>计划开始时间</th><th>计划结束时间</th></tr>
     <%
     //得到该零件的所有零件计划
      ArrayList partlist = new ArrayList();
      partlist = partplandao.getpartplan(product_id,father_id,node_id);
      for(int i=0;i<partlist.size();i++){
      PartPlan partplan = new PartPlan();
      partplan =(PartPlan)partlist.get(i);
      
      %>
     <tr>
     <td><input type="button" name ="save" id = "save" value="保存" onclick="doplan();" style="cursor:hand;">
     <select  id = "deal" name="deal" onchange="dochange(this.value);">
       <option value = "0">选择操作</option>
       <%
        ArrayList statuslist = new ArrayList();
        statuslist = partplandao.GetStatus(partplan.getPart_status());
        for(int j=0;j<statuslist.size();j++){
        PartStatus psts = new PartStatus();
        psts = (PartStatus)statuslist.get(j);
        %>
           <option value="<%=psts.getStatus_id() %>"><%=psts.getStatus_result()%></option>
        <%} %>
           </select></td>
     <td><input type="text" id = "do_time" name="do_time"maxlength="63" onclick="showdate('do_time');" readonly="readonly" size="30"></input></td>
     <td><input type="text" id = "product_id2" name= "product_id2" value="<%=partplan.getProduct_id() %>" readonly="readonly"></input></td>
     <td><input type="text" id = "issue_num2" name= "issue_num2" value="<%=partplan.getIssue_num() %>" readonly="readonly"></input></td>
     <td><input id = "order_id2" name ="order_id2" value="<%=partplan.getOrder_id() %>" readonly="readonly"></input></td>
     <td><input id= "plan_id2" name = "plan_id2" value="<%=partplan.getPlan_id() %>" readonly="readonly"></input></td>
     <td><input id= "quality_id2" name = "quality_id2" value="<%=partplan.getQuality_id() %>" readonly="readonly"></input></td>
     <td><input id= "plan_peo2" name = "plan_peo2" value="<%=partplan.getPlan_peo() %>" readonly="readonly"></input></td>
     <td><input id="part_status" name = "part_status" value="<%=partplan.getPart_status() %>" readonly="readonly"></input></td>
     <td><input type="text" id = "partnum" name="partnum" value="<%=partplan.getPart_num() %>" onclick="editable('<%=partplan.getPart_status() %>','partnum');" maxlength="63" size="20" onfocus=""></input></td>
     <td><input type="text" id = "plansttime" name="plansttime" value="<%=partplan.getPart_bgtime() %>" onclick="editable('<%=partplan.getPart_status() %>','plansttime');" maxlength="63" size="20" onfocus=""></input></td>
     <td><input type="text" id = "planedtime" name="planedtime" value="<%=partplan.getPart_edtime() %>" onclick="editable('<%=partplan.getPart_status() %>','planedtime');" maxlength="63" size="20" onfocus=""></input></td>
     </tr>
     <tr><input type="hidden" id="node_id" name = "node_id" value="<%=node_id %>"></input>
         <input type="hidden" id="father_id" name = "father_id" value="<%=father_id %>"></input>
         <input type="hidden" id = "do_type" name = "do_type" value=""></input>
     </tr>
     <%} %>
     </table>
     </form>
     <% } %>
  </body>
</html>
