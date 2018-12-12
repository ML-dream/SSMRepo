<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="bom_bean" scope="page" class="Bom.Bom_Bean"/>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%String test ="";
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>零件计划变更量计算</title>
    
    
    
    <!-- 计划管理      计划变更量查询及修改        -->
    
    
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/EcPlanSearch.js"></script>
	<script type="text/javascript" src="js/ec_search.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/person.css">
	
	<script>
	function del(ar){
	
	document.fordel.product_type.value   = ar[11];
	document.fordel.product_id.value     = ar[12];
	document.fordel.issue_num.value      = ar[13];
	document.fordel.lot.value            = ar[14];
	document.fordel.order_id.value       = ar[0];
	document.fordel.plan_id.value        = ar[1];
	document.fordel.item_id.value        = ar[2];
	document.fordel.father_item_id.value = ar[3];
	document.fordel.quality_id.value     = ar[4];
	document.fordel.submit();
}
	</script>
	
  </head>
  
  <body>
    <div class="page_title">请选择查询项 </div>
    <form name="form" class="zipcode" action="" method=post>
    <table class="query_form_table" align="center" width="100%" >
        <tr><th>产品类型：</th>
		    <td ><select id="product_type" name="product_type" style="width:300px;" onchange="addSelect('flight_type',this.value,'product_id');">
<%          ArrayList arraylist=(ArrayList)bom_bean.getFlight_Name("issuedeploy");
			//System.out.println("hello");
            if(arraylist!=null){
%>            <option value="">--请选择--</option>
<%              for(int i=0;i<arraylist.size();i++) {//System.out.println(arraylist.get(i));
%>              <option value="<%=arraylist.get(i)%>"><%=arraylist.get(i)%></option>
<%              }
            }else{
%>              <option value="">--暂无数据--</option>
<%          }%></select></td></tr>
        <tr><th>产品号：</th>
		    <td ><select id="product_id" name="product_id" style="width:300px;" onchange="addSelect('product_id',this.value,'issue_num');">
                 <option value="">--------</option></select></td></tr>
        <tr><th>版本号：</th>
		    <td ><select id="issue_num" name="issue_num" style="width:300px;" onchange="addSelect('issue_num',this.value,'lot');">
                 <option value="">--------</option></select></td></tr>         
        <tr><th>批&nbsp;&nbsp;次：</th>
		    <td ><select id="lot" name="lot" style="width:300px;" onchange="addSelect('lot',this.value);">
                 <option value="">--------</option></select></td></tr>        
        <tr><td  colspan="2">
                <input type="button" name="Submit" value="查 询" class="common_button" onclick="EcPlan_Search();">
                <input type="button"  value="返 回" class="common_button" onclick=""></td>
        </tr>
    </table>
    </form> 
     <div class="page_title">零件计划变更量 </div>
     <table class="query_form_table" id = "ecplantable" border="1" align="center"  cellspacing="0" width="100%" style="word-break:break-all;" >
    <tr ><th>操作</th><th>任务号</th><th>计划号</th><th>物料编号</th><th>父物料号</th><th>质编号</th><th>计划开始时间</th><th>计划结束时间</th><th>数量</th><th>变更类型</th><th>变更内容</th><th>操作时间</th>
    </tr>
    <tr height=25>
		<td align=center colspan=18 >
		共有记录数:当前  页
		<a onclick="firsrpg();" style="cursor:hand">第一页</a>
		<a onclick="lastpg();" style="cursor:hand">上一页 </a>
		<a onclick="nextpg();" style="cursor:hand">下一页</a>
		<a onclick="finalpg();" style="cursor:hand">最后页 </a>
		直接<input type=image src="img/hand.gif" name="gotof" onclick="return chkdata();"/>
		<input type=text size=5 name=bm class=formcolor/>页</TD>
	</tr>
    </table> 
    
    <form name ="fordel" id="fordel" action="DelECPlan" method="post" >
    <input type="hidden" id= "product_type" name = "product_type" value="">
     <input type="hidden" id= "product_id" name= "product_id" value="">
     <input type="hidden" id= "issue_num" name="issue_num" value="">
     <input type="hidden" id= "lot" name="lot" value="">
     <input type="hidden" id= "item_id" name="item_id" value="">
     <input type="hidden" id= "father_item_id" name="father_item_id" value="">
     <input type="hidden" id= "order_id" name="order_id" value="">
     <input type="hidden" id= "plan_id" name="plan_id" value="">
     <input type="hidden" id= "quality_id" name="quality_id" value="">
    </form>
  </body>
</html>
