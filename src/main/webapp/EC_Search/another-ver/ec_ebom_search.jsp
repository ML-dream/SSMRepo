<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="bom_bean" scope="page" class="Bom.Bom_Bean"/>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%String test ="";
 %>


<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>BOM变更量计算</title>
    
    
    <!-- Bom变更量      Bom变更量查询及修改 -->
    
    
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/Ecebom_Table.js"></script>
	<script type="text/javascript" src="js/ec_search.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/person.css">
	
	<script>
	function del(ar){
	
	document.fordel.product_type.value   = ar[8];
	document.fordel.product_id.value     = ar[9];
	document.fordel.issue_num.value      = ar[10];
	document.fordel.lot.value            = ar[11];
	document.fordel.item_id.value        = ar[0];
	document.fordel.father_item_id.value = ar[1];
	document.fordel.fid.value            = ar[2];
	document.fordel.id.value             = ar[3];
	document.fordel.level_id.value       = ar[4];
	document.fordel.ec_id.value          = ar[12];
	document.fordel.submit();
}
	</script>
	
  </head>
  
  <body>
    <div class="page_title">请选择查询项 </div>
    <form id="form" name="form" class="zipcode" action="" method=post>
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
                <input type="button" id = "Submit" name="Submit" value="查 询" class="common_button" onclick="EcEbomSearch();">
                <input type="button"  value="返 回" class="common_button" onclick="ClearAll();"></td>
        </tr>
    </table>
    </form> 
     <div class="page_title">BOM变更量 </div>
     <table class="query_form_table" id = "ecebomtable" border="1" align="center"  cellspacing="0" width="100%" style="word-break:break-all;" >
    <tr ><th>操作</th><th>父物料号</th><th>物料编号</th><th>FID</th><th>ID</th><th>LEVEL_ID</th><th>变更类型</th><th>变更内容</th><th>操作时间</th>
    </tr>
    <tr height=25>
		<td align=center colspan=18 >
		共有记录数:当前  页
		<a onclick="" style="cursor:hand">第一页</a>
		<a onclick="" style="cursor:hand">上一页 </a>
		<a onclick="" style="cursor:hand">下一页</a>
		<a onclick="" style="cursor:hand">最后页 </a>
		直接<input type=image src="img/hand.gif" name="gotof" onclick=""/>
		<input type=text size=5 name=bm class=formcolor/>页</TD>
	</tr>
    </table> 
    
    <form name ="fordel" id="fordel" action="DelECBom" method="post" >
    <input type="hidden" id= "product_type" name = "product_type" value="">
     <input type="hidden" id= "product_id" name= "product_id" value="">
     <input type="hidden" id= "issue_num" name="issue_num" value="">
     <input type="hidden" id= "lot" name="lot" value="">
     <input type="hidden" id= "item_id" name="item_id" value="">
     <input type="hidden" id= "father_item_id" name="father_item_id" value="">
     <input type="hidden" id= "id" name="id" value="">
     <input type="hidden" id= "fid" name="fid" value="">
     <input type="hidden" id= "level_id" name="level_id" value="">
     <input type="hidden" id= "ec_id" name="ec_id" value="">
    </form>
  </body>
</html>
