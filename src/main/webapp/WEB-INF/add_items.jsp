<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'add_items.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	
	<link rel="icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath%>rili/index.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>rili/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>rili/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcore.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcalendar.min.js"></script>
	<script type="text/javascript">
		J(function(){
			
			J('#leadtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
				
		});
	</script>
	
	
  </head>
  
  <body>
    <form action="/test/add_items.do?flag=add_items" method="post">
      <table>
        <thead>添加物料</thead>
        <tr><td style="background-color: 0000ff">物料号</td><td><input type="text" name="itemid" /></td>
          <td style="background-color: 0000ff">物料种类号</td><td><input type="text" name="itemtypeid" /></td>
          <td style="background-color: 0000ff">物料名</td><td><input type="text" name="itemname" /></td>
          <td style="background-color: 0000ff">物料图</td><td><input type="text" name="itemdrawing" /></td></tr>
      <tr><td style="background-color: 0000ff">材料标记</td><td><input type="text" name="materialmark" /></td>
          <td style="background-color: 0000ff">ITEM_ATTRI</td><td><input type="text" name="itemattri" /></td>
          <td style="background-color: 0000ff">ITEM_SPECIFICATION</td><td><input type="text" name="itemspecification" /></td></tr>
      <tr><td style="background-color: 0000ff">ITEM_MB</td><td><input type="text" name="itemmb" /></td>
          <td style="background-color: 0000ff">物料状态</td><td><input type="text" name="itemstatus" /></td>
          <td style="background-color: 0000ff">UNIT_M</td><td><input type="text" name="unitm" /></td></tr>
      <tr><td style="background-color: 0000ff">LOT_SIZE</td><td><input type="text" name="lotsize" /></td>
          <td style="background-color: 0000ff">ORDER_MIN</td><td><input type="text" name="ordermin" /></td>
          <td style="background-color: 0000ff">LEAD_TIME</td><td><input type="text" id="leadtime" name="leadtime" onclick="J.calendar.get({time:true});" /></td></tr>
          <td style="background-color: 0000ff"></td><td><input type="text" name="varleadtime" /></td></tr>
      <tr><td style="background-color: 0000ff">ABC代码</td><td><input type="text" name="abccode" /></td>
          <td style="background-color: 0000ff">LLC</td><td><input type="text" name="LLc" /></td>
          <td style="background-color: 0000ff">SAFE_STOCK </td><td><input type="text" name="safestock" /></td></tr>
      <tr><td style="background-color: 0000ff">STOCK_HIGH</td><td><input type="text" name="stockhigh" /></td>
          <td style="background-color: 0000ff">STOCK_LOW</td><td><input type="text" name="stocklow" /></td>
          <td style="background-color: 0000ff">MPS_FLAG </td><td><input type="text" name="mpsflag" /></td></tr>
      <tr><td style="background-color: 0000ff">PHANTOM_FLAG</td><td><input type="text" name="phantomunit" /></td>
          <td style="background-color: 0000ff">物料重量</td><td><input type="text" name="itemunit" /></td>
          <td style="background-color: 0000ff">重量单位</td><td><input type="text" name="weightunit" /></td></tr>
      <tr><td style="background-color: 0000ff">YEILD</td><td><input type="text" name="yeild" /></td>
          <td style="background-color: 0000ff">P_TYPE</td><td><input type="text" name="ptype" /></td>
          <td style="background-color: 0000ff">PURCHASE_UNITE</td><td><input type="text" name="purchaseunite" /></td></tr>
      <tr><td style="background-color: 0000ff">PLAN_UNITE</td><td><input type="text" name="planunite" /></td>
          <td style="background-color: 0000ff">SUB_PRODUCT</td><td><input type="text" name="subproduct" /></td>
          <td style="background-color: 0000ff">ASSEMBLE_AFTERALL</td><td><input type="text" name="assembleafterall" /></td></tr>
      <tr><td style="background-color: 0000ff">COST_METHOD</td><td><input type="text" name="costmethod" /></td>
          <td style="background-color: 0000ff">DAYS_MULTIPLE</td><td><input type="text" name="daysmultiple" /></td>
          <td style="background-color: 0000ff">BACKFLASH_FLAG</td><td><input type="text" name="backflash" /></td></tr>
      <tr><td style="background-color: 0000ff">物料价格</td><td><input type="text" name="itemprice" /></td>
          <td style="background-color: 0000ff">PRICE_UNIT</td><td><input type="text" name="priceunit" /></td>
          <td style="background-color: 0000ff">CURRENCY</td><td><input type="text" name="currency" /></td></tr>
      <tr><td style="background-color: 0000ff">RMB_PRICE</td><td><input type="text" name="rmbprice" /></td>
          <td style="background-color: 0000ff">EXTRA_A</td><td><input type="text" name="extraA" /></td>
          <td style="background-color: 0000ff">EXTRA_B</td><td><input type="text" name="extraB" /></td></tr>
      <tr><td style="background-color: 0000ff">DY_MTL_MARK</td><td><input type="text" name="dymtlmark" /></td>
          <td style="background-color: 0000ff">MEMO</td><td><input type="text" name="memo" /></td>
          <td style="background-color: 0000ff">MTL_SORT</td><td><input type="text" name="mtlsort" /></td></tr>
      <tr><td style="background-color: 0000ff">物料A</td><td><input type="text" name="itemA" /></td>
          <td style="background-color: 0000ff">物料B</td><td><input type="text" name="itemB" /></td>
          <td style="background-color: 0000ff">物料C</td><td><input type="text" name="itemC" /></td></tr>
      <tr><td style="background-color: 0000ff">物料D</td><td><input type="text" name="itemD" /></td></tr> 
      <tr><td><input type="submit" value="保存物料" style="width: 100px;height: 30px;" /></td>
          <td><input type="reset" value="重       置" style="width: 100px;height: 30px;" /></td></tr>
      </table>
    </form>
  </body>
</html>
