<%@ page language="java" import="java.util.*,com.wl.forms.Item" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'itemList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
    <script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <a href="/test/to_add_items.do?flag=to_add_items">添加物料</a>
    <table>
      <thead>物料列表界面</thead>
      <% ArrayList al=(ArrayList)request.getAttribute("itemList");
         int i=0;
         for(i=0;i<al.size();i++){
             Item item = new Item();
             item = (Item)al.get(i);
      %>
      
      <tr><td style="background-color: 0000ff">物料号</td><td><%=item.getItemid() %></td>
          <td style="background-color: 0000ff">物料种类号</td><td><%=item.getItemtypeid() %></td>
          <td style="background-color: 0000ff">物料名</td><td><%=item.getItemname() %></td>
          <td style="background-color: 0000ff">物料图</td><td><%=item.getItemdrawing() %></td></tr>
      <tr><td style="background-color: 0000ff">材料标记</td><td><%=item.getMaterialmark() %></td>
          <td style="background-color: 0000ff">ITEM_ATTRI</td><td><%=item.getItemattri() %></td>
          <td style="background-color: 0000ff">ITEM_SPECIFICATION</td><td><%=item.getItemspecification() %></td></tr>
      <tr><td style="background-color: 0000ff">ITEM_MB</td><td><%=item.getItemmb() %></td>
          <td style="background-color: 0000ff">物料状态</td><td><%=item.getItemstatus() %></td>
          <td style="background-color: 0000ff">UNIT_M</td><td><%=item.getUnitm() %></td></tr>
      <tr><td style="background-color: 0000ff">LOT_SIZE</td><td><%=item.getLotsize() %></td>
          <td style="background-color: 0000ff">ORDER_MIN</td><td><%=item.getOrdermin() %></td>
          <td style="background-color: 0000ff">LEAD_TIME</td><td><%=item.getLeadtime() %></td></tr>
      <tr><td style="background-color: 0000ff">ABC代码</td><td><%=item.getAbccode() %></td>
          <td style="background-color: 0000ff">LLC</td><td><%=item.getLLc() %></td>
          <td style="background-color: 0000ff">SAFE_STOCK </td><td><%=item.getSafestock() %></td></tr>
      <tr><td style="background-color: 0000ff">STOCK_HIGH</td><td><%=item.getStockhigh() %></td>
          <td style="background-color: 0000ff">STOCK_LOW</td><td><%=item.getStocklow() %></td>
          <td style="background-color: 0000ff">MPS_FLAG </td><td><%=item.getMpsflag() %></td></tr>
      <tr>
          <td style="background-color: 0000ff">物料重量</td><td><%=item.getItemunit() %></td>
          <td style="background-color: 0000ff">重量单位</td><td><%=item.getWeightunit() %></td></tr>
      <tr><td style="background-color: 0000ff">YEILD</td><td><%=item.getYeild() %></td>
          <td style="background-color: 0000ff">P_TYPE</td><td><%=item.getPtype() %></td>
          <td style="background-color: 0000ff">PURCHASE_UNITE</td><td><%=item.getPurchaseunite() %></td></tr>
      <tr><td style="background-color: 0000ff">PLAN_UNITE</td><td><%=item.getPlanunite() %></td>
          <td style="background-color: 0000ff">SUB_PRODUCT</td><td><%=item.getSubproduct() %></td>
          <td style="background-color: 0000ff">ASSEMBLE_AFTERALL</td><td><%=item.getAssembleafterall() %></td></tr>
      <tr><td style="background-color: 0000ff">COST_METHOD</td><td><%=item.getCostmethod() %></td>
          <td style="background-color: 0000ff">DAYS_MULTIPLE</td><td><%=item.getDaysmultiple() %></td>
          <td style="background-color: 0000ff">BACKFLASH_FLAG</td><td><%=item.getBackflash() %></td></tr>
      <tr><td style="background-color: 0000ff">物料价格</td><td><%=item.getItemprice() %></td>
          <td style="background-color: 0000ff">PRICE_UNIT</td><td><%=item.getPriceunit() %></td>
          <td style="background-color: 0000ff">CURRENCY</td><td><%=item.getCurrency() %></td></tr>
      <tr><td style="background-color: 0000ff">RMB_PRICE</td><td><%=item.getRmbprice() %></td>
          <td style="background-color: 0000ff">EXTRA_A</td><td><%=item.getExtraA() %></td>
          <td style="background-color: 0000ff">EXTRA_B</td><td><%=item.getExtraB() %></td></tr>
      <tr><td style="background-color: 0000ff">DY_MTL_MARK</td><td><%=item.getDymtlmark() %></td>
          <td style="background-color: 0000ff">MEMO</td><td><%=item.getMemo() %></td>
          <td style="background-color: 0000ff">MTL_SORT</td><td><%=item.getMtlsort() %></td></tr>
      <tr><td style="background-color: 0000ff">物料A</td><td><%=item.getItemA() %></td>
          <td style="background-color: 0000ff">物料B</td><td><%=item.getItemB() %></td>
          <td style="background-color: 0000ff">物料C</td><td><%=item.getItemB() %></td></tr>
      <tr><td style="background-color: 0000ff">物料D</td><td><%=item.getItemA() %></td></tr>   
      <%}
      %>
    </table>
  </body>
</html>
