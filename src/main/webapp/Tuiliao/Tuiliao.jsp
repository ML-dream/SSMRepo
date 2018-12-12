<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.wl.forms.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>退料</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
	<!--
	table {
	    table-layout:fixed;
	    word-break: break-all;
	} 
	-->
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
    <div class="mini-toolbar">
   <a class="mini-button" iconcls="icon-print" plain="false" onclick="print()">打印入库单</a> 
  <span class="separator"></span> 
  <a  id="getForm" class="mini-button" iconcls="icon-save" plain="false" onclick="getForm()">保存</a> 
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>
  
 <!-- &lt;span class=&quot;separator&quot;&gt;&lt;/span&gt;
  &lt;a class=&quot;mini-button&quot; iconCls=&quot;icon-undo&quot; plain=&quot;false&quot; onclick=&quot;javascript:window.history.back(-1);&quot;&gt;返回&lt;/a&gt; -->  
   </div>
 
  <fieldset id="llsheet" style="width: 100%;" align="center">
  <legend><h2>退料单</h2></legend> 
   
   
   <form id="Tuiliao" name="Tuiliao" action="#" method="post">
   <table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%"  >
   <tr bgcolor=#EBEFF5>
   <td><lable for="rmDate$text">日&nbsp;&nbsp;&nbsp;&nbsp;期</lable></td>
   <td><input id="rmDate" name="rmDate" class="mini-datepicker" width="100%" required="true" showTodayButton="false" showClearButton="false" allowInput="false"></td>
   <td><lable for="rmSheetid">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</lable></td>
   <td><input id="rmSheetid" name="rmSheetid" class="mini-textbox" width="100%" required="true" enabled="false" value="${Rm_sheetid.sheetid }"></td>
   <td><lable for="warehouseId">库&nbsp;&nbsp;&nbsp;&nbsp;房</lable></td>
   <td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%" textName="warehouseName" onbuttonclick="onButtonEditWarehouse" 
  allowInput="false" required="true"/></td>
   </tr>
   
   </table> 
  <table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%" > 
   		<tr height="25" align="right"> 
   		<td>货品编码</td><td>货品名称</td><td>订单号</td><td>版本号</td><td>类型</td><td>规格</td><td>单位</td><td>数量</td><td>单价</td><td>金额</td><td>库位</td><!--  &lt;td&gt;订单号&lt;/td&gt;&lt;td&gt;批次&lt;/td&gt;&lt;td&gt;质编号&lt;/td&gt; <td>退料数量</td>--><td>备注</td> 
   		</tr> 
   		<tr height="20"><td><input width="100%" id="item_id1" name="item_id1" class="mini-buttonedit" onbuttonclick="onButtonEdit1" textname="itemid_name1" allowinput="false"></td><td><input width="100%" id="item_name1" name="item_name1" class="mini-textbox"></td><td><input width="100%" id="order_id1" name="order_id1" class="mini-textbox"></td><td><input width="100%" id="issue_num1" name="issue_num1" class="mini-textbox"></td><td><input width="100%" id="item_type1" name="item_type1" class="mini-textbox"></td><td><input width="100%" id="spec1" name="spec1" class="mini-textbox"></td><td><input width="100%" id="unit1" name="unit1" class="mini-textbox"></td><td><input width="100%" id="rm_num1" name="rm_num1" class="mini-textbox"></td><td><input width="100%" id="unitprice1" name="unitprice1" class="mini-textbox"></td><td><input width="100%" id="price1" name="price1" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id1&quot;  name=&quot;warehouse_id1&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id1" name="stock_id1" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id1&quot;  name=&quot;order_id1&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot1&quot;  name=&quot;lot1&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id1&quot;  name=&quot;quality_id1&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num1" name="rm_num1" class="mini-textbox"></td>--><td><input width="100%" id="memo1" name="memo1" class="mini-textbox"></td></tr> 
   		<tr height="20"><td><input width="100%" id="item_id2" name="item_id2" class="mini-buttonedit" onbuttonclick="onButtonEdit2" textname="itemid_name2" allowinput="false"></td><td><input width="100%" id="item_name2" name="item_name2" class="mini-textbox"></td><td><input width="100%" id="order_id2" name="order_id2" class="mini-textbox"></td><td><input width="100%" id="issue_num2" name="issue_num2" class="mini-textbox"></td><td><input width="100%" id="item_type2" name="item_type2" class="mini-textbox"></td><td><input width="100%" id="spec2" name="spec2" class="mini-textbox"></td><td><input width="100%" id="unit2" name="unit2" class="mini-textbox"></td><td><input width="100%" id="rm_num2" name="rm_num2" class="mini-textbox"></td><td><input width="100%" id="unitprice2" name="unitprice2" class="mini-textbox"></td><td><input width="100%" id="price2" name="price2" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id2&quot;  name=&quot;warehouse_id2&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id2" name="stock_id2" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id2&quot;  name=&quot;order_id2&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot2&quot;  name=&quot;lot2&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id2&quot;  name=&quot;quality_id2&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num2" name="rm_num2" class="mini-textbox"></td>--><td><input width="100%" id="memo2" name="memo2" class="mini-textbox"></td></tr> 
  		<tr height="20"><td><input width="100%" id="item_id3" name="item_id3" class="mini-buttonedit" onbuttonclick="onButtonEdit3" textname="itemid_name3" allowinput="false"></td><td><input width="100%" id="item_name3" name="item_name3" class="mini-textbox"></td><td><input width="100%" id="order_id3" name="order_id3" class="mini-textbox"></td><td><input width="100%" id="issue_num3" name="issue_num3" class="mini-textbox"></td><td><input width="100%" id="item_type3" name="item_type3" class="mini-textbox"></td><td><input width="100%" id="spec3" name="spec3" class="mini-textbox"></td><td><input width="100%" id="unit3" name="unit3" class="mini-textbox"></td><td><input width="100%" id="rm_num3" name="rm_num3" class="mini-textbox"></td><td><input width="100%" id="unitprice3" name="unitprice3" class="mini-textbox"></td><td><input width="100%" id="price3" name="price3" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id3&quot;  name=&quot;warehouse_id3&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id3" name="stock_id3" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id3&quot;  name=&quot;order_id3&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot3&quot;  name=&quot;lot3&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id3&quot;  name=&quot;quality_id3&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num3" name="rm_num3" class="mini-textbox"></td>--><td><input width="100%" id="memo3" name="memo3" class="mini-textbox"></td></tr> 
  		<tr height="20"><td><input width="100%" id="item_id4" name="item_id4" class="mini-buttonedit" onbuttonclick="onButtonEdit4" textname="itemid_name4" allowinput="false"></td><td><input width="100%" id="item_name4" name="item_name4" class="mini-textbox"></td><td><input width="100%" id="order_id4" name="order_id4" class="mini-textbox"></td><td><input width="100%" id="issue_num4" name="issue_num4" class="mini-textbox"></td><td><input width="100%" id="item_type4" name="item_type4" class="mini-textbox"></td><td><input width="100%" id="spec4" name="spec4" class="mini-textbox"></td><td><input width="100%" id="unit4" name="unit4" class="mini-textbox"></td><td><input width="100%" id="rm_num4" name="rm_num4" class="mini-textbox"></td><td><input width="100%" id="unitprice4" name="unitprice4" class="mini-textbox"></td><td><input width="100%" id="price4" name="price4" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id4&quot;  name=&quot;warehouse_id4&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id4" name="stock_id4" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id4&quot;  name=&quot;order_id4&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot4&quot;  name=&quot;lot4&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id4&quot;  name=&quot;quality_id4&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num4" name="rm_num4" class="mini-textbox"></td>--><td><input width="100%" id="memo4" name="memo4" class="mini-textbox"></td></tr> 
  		<tr height="20"><td><input width="100%" id="item_id5" name="item_id5" class="mini-buttonedit" onbuttonclick="onButtonEdit5" textname="itemid_name5" allowinput="false"></td><td><input width="100%" id="item_name5" name="item_name5" class="mini-textbox"></td><td><input width="100%" id="order_id5" name="order_id5" class="mini-textbox"></td><td><input width="100%" id="issue_num5" name="issue_num5" class="mini-textbox"></td><td><input width="100%" id="item_type5" name="item_type5" class="mini-textbox"></td><td><input width="100%" id="spec5" name="spec5" class="mini-textbox"></td><td><input width="100%" id="unit5" name="unit5" class="mini-textbox"></td><td><input width="100%" id="rm_num5" name="rm_num5" class="mini-textbox"></td><td><input width="100%" id="unitprice5" name="unitprice5" class="mini-textbox"></td><td><input width="100%" id="price5" name="price5" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id5&quot;  name=&quot;warehouse_id5&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id5" name="stock_id5" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id5&quot;  name=&quot;order_id5&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot5&quot;  name=&quot;lot5&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id5&quot;  name=&quot;quality_id5&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num5" name="rm_num5" class="mini-textbox"></td>--><td><input width="100%" id="memo5" name="memo5" class="mini-textbox"></td></tr> 
  		<tr height="20"><td><input width="100%" id="item_id6" name="item_id6" class="mini-buttonedit" onbuttonclick="onButtonEdit6" textname="itemid_name6" allowinput="false"></td><td><input width="100%" id="item_name6" name="item_name6" class="mini-textbox"></td><td><input width="100%" id="order_id6" name="order_id6" class="mini-textbox"></td><td><input width="100%" id="issue_num6" name="issue_num6" class="mini-textbox"></td><td><input width="100%" id="item_type6" name="item_type6" class="mini-textbox"></td><td><input width="100%" id="spec6" name="spec6" class="mini-textbox"></td><td><input width="100%" id="unit6" name="unit6" class="mini-textbox"></td><td><input width="100%" id="rm_num6" name="rm_num6" class="mini-textbox"></td><td><input width="100%" id="unitprice6" name="unitprice6" class="mini-textbox"></td><td><input width="100%" id="price6" name="price6" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id6&quot;  name=&quot;warehouse_id6&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id6" name="stock_id6" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id6&quot;  name=&quot;order_id6&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot6&quot;  name=&quot;lot6&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id6&quot;  name=&quot;quality_id6&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num6" name="rm_num6" class="mini-textbox"></td>--><td><input width="100%" id="memo6" name="memo6" class="mini-textbox"></td></tr> 
  		<tr height="20"><td><input width="100%" id="item_id7" name="item_id7" class="mini-buttonedit" onbuttonclick="onButtonEdit7" textname="itemid_name7" allowinput="false"></td><td><input width="100%" id="item_name7" name="item_name7" class="mini-textbox"></td><td><input width="100%" id="order_id7" name="order_id7" class="mini-textbox"></td><td><input width="100%" id="issue_num7" name="issue_num7" class="mini-textbox"></td><td><input width="100%" id="item_type7" name="item_type7" class="mini-textbox"></td><td><input width="100%" id="spec7" name="spec7" class="mini-textbox"></td><td><input width="100%" id="unit7" name="unit7" class="mini-textbox"></td><td><input width="100%" id="rm_num7" name="rm_num7" class="mini-textbox"></td><td><input width="100%" id="unitprice7" name="unitprice7" class="mini-textbox"></td><td><input width="100%" id="price7" name="price7" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id3&quot;  name=&quot;warehouse_id3&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id7" name="stock_id7" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id3&quot;  name=&quot;order_id3&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot3&quot;  name=&quot;lot3&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id3&quot;  name=&quot;quality_id3&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num7" name="rm_num7" class="mini-textbox"></td>--><td><input width="100%" id="memo7" name="memo7" class="mini-textbox"></td></tr> 
  		<tr height="20"><td><input width="100%" id="item_id8" name="item_id8" class="mini-buttonedit" onbuttonclick="onButtonEdit8" textname="itemid_name8" allowinput="false"></td><td><input width="100%" id="item_name8" name="item_name8" class="mini-textbox"></td><td><input width="100%" id="order_id8" name="order_id8" class="mini-textbox"></td><td><input width="100%" id="issue_num8" name="issue_num8" class="mini-textbox"></td><td><input width="100%" id="item_type8" name="item_type8" class="mini-textbox"></td><td><input width="100%" id="spec8" name="spec8" class="mini-textbox"></td><td><input width="100%" id="unit8" name="unit8" class="mini-textbox"></td><td><input width="100%" id="rm_num8" name="rm_num8" class="mini-textbox"></td><td><input width="100%" id="unitprice8" name="unitprice8" class="mini-textbox"></td><td><input width="100%" id="price8" name="price8" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id4&quot;  name=&quot;warehouse_id4&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id8" name="stock_id8" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id4&quot;  name=&quot;order_id4&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot4&quot;  name=&quot;lot4&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id4&quot;  name=&quot;quality_id4&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num8" name="rm_num8" class="mini-textbox"></td>--><td><input width="100%" id="memo8" name="memo8" class="mini-textbox"></td></tr> 
  		<tr height="20"><td><input width="100%" id="item_id9" name="item_id9" class="mini-buttonedit" onbuttonclick="onButtonEdit9" textname="itemid_name9" allowinput="false"></td><td><input width="100%" id="item_name9" name="item_name9" class="mini-textbox"></td><td><input width="100%" id="order_id9" name="order_id9" class="mini-textbox"></td><td><input width="100%" id="issue_num9" name="issue_num9" class="mini-textbox"></td><td><input width="100%" id="item_type9" name="item_type9" class="mini-textbox"></td><td><input width="100%" id="spec9" name="spec9" class="mini-textbox"></td><td><input width="100%" id="unit9" name="unit8" class="mini-textbox"></td><td><input width="100%" id="rm_num9" name="rm_num9" class="mini-textbox"></td><td><input width="100%" id="unitprice9" name="unitprice9" class="mini-textbox"></td><td><input width="100%" id="price9" name="price9" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id5&quot;  name=&quot;warehouse_id5&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id9" name="stock_id9" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id5&quot;  name=&quot;order_id5&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot5&quot;  name=&quot;lot5&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id5&quot;  name=&quot;quality_id5&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num9" name="rm_num8" class="mini-textbox"></td>--><td><input width="100%" id="memo9" name="memo9" class="mini-textbox"></td></tr> 
  		<tr height="20"><td><input width="100%" id="item_id10" name="item_id10" class="mini-buttonedit" onbuttonclick="onButtonEdit10" textname="itemid_name10" allowinput="false"></td><td><input width="100%" id="item_name10" name="item_name10" class="mini-textbox"></td><td><input width="100%" id="order_id10" name="order_id10" class="mini-textbox"></td><td><input width="100%" id="issue_num10" name="issue_num10" class="mini-textbox"></td><td><input width="100%" id="item_type10" name="item_type10" class="mini-textbox"></td><td><input width="100%" id="spec10" name="spec10" class="mini-textbox"></td><td><input width="100%" id="unit10" name="unit10" class="mini-textbox"></td><td><input width="100%" id="rm_num10" name="rm_num10" class="mini-textbox"></td><td><input width="100%" id="unitprice10" name="unitprice10" class="mini-textbox"></td><td><input width="100%" id="price10" name="price10" class="mini-textbox"></td><!--&lt;td&gt;&lt;input id=&quot;warehouse_id6&quot;  name=&quot;warehouse_id6&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;--><td><input width="100%" id="stock_id10" name="stock_id10" class="mini-textbox"></td><!-- &lt;td&gt;&lt;input id=&quot;order_id6&quot;  name=&quot;order_id6&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;lot6&quot;  name=&quot;lot6&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt;&lt;td&gt;&lt;input id=&quot;quality_id6&quot;  name=&quot;quality_id6&quot; class=&quot;mini-textbox&quot;  width=&quot;100%&quot; /&gt;&lt;/td&gt; <td><input width="100%" id="rm_num10" name="rm_num10" class="mini-textbox"></td>--><td><input width="100%" id="memo10" name="memo10" class="mini-textbox"></td></tr> 
  	 
  	</table>	
   
   <table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;" width="99%">
   <tr bgcolor=#EBEFF5>
   <td><lable for="empId$text">退料人</lable></td>
  <td><input id="empId" name="empId"  class="mini-buttonedit" width="100%" textName="emp" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true"></td>
   <td><lable for="dept$text">所属部门</lable></td>
   <td><input id="dept" name="dept" class="mini-textbox" width="100%" required="true"></td>
   <td><lable for="operatorId$text">操作员</lable></td>
   <td><input id="operatorId" name="operatorId" class="mini-buttonedit" width="100%" textName="operator" onbuttonclick="onButtonEditOperator" value="<%=((User)session.getAttribute("user")).getStaffCode()%>" text="<%=((User)session.getAttribute("user")).getStaffName()%>"
   allowInput="false" required="true"></td>
   </tr> 
   
   </table>
   <input id="id" name="id" class="mini-hidden" required="true" value="${Rm_sheetid.id}"/>
   <input id="seq" name="seq" class="mini-hidden" required="true" value="${Rm_sheetid.seq}"/>	
   </form>
   </fieldset>
   <script type="text/javascript">
   mini.parse();
   
    function getForm(){
 		  
 		  	var form = new mini.Form("#Tuiliao");
 		  	var data=form.getData();
 		  	data.rmDate=mini.get("rmDate").getFormValue();
 		  	var json=mini.encode(data);
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	mini.get("getForm").disable();
 		  		$.ajax({
      				type: "POST",
      				url: "TuiliaoServlet", 
      				dataType: "json",  				
      				//cache: false,
      				//enctype: 'multipart/form-data',
      				data: { submitData: json },
      				//processData: false,
    				//contentType: false,
      				success: function (data) {
        				alert(data.result);
        				if(data.status==1){
        					window.location.href = window.location.href;
        				}
      				}
      				
    			});
    		}
   		}
   		
    function nextForm(){
    window.location.href=window.location.href;
    }
   
   function onButtonEditWarehouse(e){
   	var btnEdit = this;
            mini.open({
                url: "warehouseDefi/selectWarehouseWindow.jsp",
                title: "选择库房",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.warehouseid);
                            btnEdit.setText(data.warehousename);
                         
                        }
                    }
                }
            });
   
   }
   
    function onButtonEditEmployee(e) {
            var btnEdit = this;
            mini.open({
                url: "employeeManage/selectEmployeeWindow.jsp",
                title: "选择上级部门",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.staffCode);
                            btnEdit.setText(data.staffName);
                            mini.get("dept").setValue(data.sectionName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
        }
   
    function onButtonEditOperator(e){
    	 var btnEdit = this;
            mini.open({
                url: "employeeManage/selectEmployeeWindow.jsp",
                title: "选择上级部门",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.staffCode);
                            btnEdit.setText(data.staffName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });

    }
   //物料号
   
   	 function onButtonEdit1(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            //btnEdit.setValue(data.itemid);
                     //       btnEdit.setText(data.itemid);
                    //   	mini.get("item_name1").setValue(data.itemname);
                     //  	mini.get("item_type1").setValue(data.itemtypeid);
                     //  	mini.get("spec1").setValue(data.itemspecification);
                     //  	mini.get("unitprice1").setValue(data.itemprice);
                     //   mini.get("order_id1").setValue(data.orderid);
                     //   mini.get("issue_num1").setValue(data.issuenum);
                     		btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name1").setValue(data.itemName);
                       		mini.get("item_type1").setValue(data.itemType);
                       		mini.get("spec1").setValue(data.spec);
                       		mini.get("unit1").setValue(data.unit);
                       		mini.get("unitprice1").setValue(data.unitPrice);
                       		mini.get("stock_id1").setValue(data.stockId);
                        }
                    }

                }
            });
        }
    function onButtonEdit2(e) {
            var btnEdit = this;
             var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name2").setValue(data.itemName);
                       		mini.get("item_type2").setValue(data.itemType);
                       		mini.get("spec2").setValue(data.spec);
                       		mini.get("unit2").setValue(data.unit);
                       		mini.get("unitprice2").setValue(data.unitPrice);
                       		mini.get("stock_id2").setValue(data.stockId); 
                        }
                    }

                }
            });
        }
         function onButtonEdit3(e) {
            var btnEdit = this;
             var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                           btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name3").setValue(data.itemName);
                       		mini.get("item_type3").setValue(data.itemType);
                       		mini.get("spec3").setValue(data.spec);
                       		mini.get("unit3").setValue(data.unit);
                       		mini.get("unitprice3").setValue(data.unitPrice);
                       		mini.get("stock_id3").setValue(data.stockId);
                        }
                    }

                }
            });
        }
         function onButtonEdit4(e) {
            var btnEdit = this;
             var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name4").setValue(data.itemName);
                       		mini.get("item_type4").setValue(data.itemType);
                       		mini.get("spec4").setValue(data.spec);
                       		mini.get("unit4").setValue(data.unit);
                       		mini.get("unitprice4").setValue(data.unitPrice);
                       		mini.get("stock_id4").setValue(data.stockId);
                        }
                    }

                }
            });
        }
         function onButtonEdit5(e) {
            var btnEdit = this;
             var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                             btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name5").setValue(data.itemName);
                       		mini.get("item_type5").setValue(data.itemType);
                       		mini.get("spec5").setValue(data.spec);
                       		mini.get("unit5").setValue(data.unit);
                       		mini.get("unitprice5").setValue(data.unitPrice);
                       		mini.get("stock_id5").setValue(data.stockId);
                        }
                    }

                }
            });
        }
         function onButtonEdit6(e) {
            var btnEdit = this;
             var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name6").setValue(data.itemName);
                       		mini.get("item_type6").setValue(data.itemType);
                       		mini.get("spec6").setValue(data.spec);
                       		mini.get("unit6").setValue(data.unit);
                       		mini.get("unitprice6").setValue(data.unitPrice);
                       		mini.get("stock_id6").setValue(data.stockId);
                        }
                    }

                }
            });
        }
         function onButtonEdit7(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name7").setValue(data.itemName);
                       		mini.get("item_type7").setValue(data.itemType);
                       		mini.get("spec7").setValue(data.spec);
                       		mini.get("unit7").setValue(data.unit);
                       		mini.get("unitprice7").setValue(data.unitPrice);
                       		mini.get("stock_id7").setValue(data.stockId);
                        }
                    }

                }
            });
        }
         function onButtonEdit8(e) {
            var btnEdit = this;
             var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name8").setValue(data.itemName);
                       		mini.get("item_type8").setValue(data.itemType);
                       		mini.get("spec8").setValue(data.spec);
                       		mini.get("unit8").setValue(data.unit);
                       		mini.get("unitprice8").setValue(data.unitPrice);
                       		mini.get("stock_id8").setValue(data.stockId);
                        }
                    }

                }
            });
        }
         function onButtonEdit9(e) {
            var btnEdit = this;
             var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name9").setValue(data.itemName);
                       		mini.get("item_type9").setValue(data.itemType);
                       		mini.get("spec9").setValue(data.spec);
                       		mini.get("unit9").setValue(data.unit);
                       		mini.get("unitprice9").setValue(data.unitPrice);
                       		mini.get("stock_id9").setValue(data.stockId);
                        }
                    }

                }
            });
        }
         function onButtonEdit10(e) {
            var btnEdit = this;
             var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       		mini.get("item_name10").setValue(data.itemName);
                       		mini.get("item_type10").setValue(data.itemType);
                       		mini.get("spec10").setValue(data.spec);
                       		mini.get("unit10").setValue(data.unit);
                       		mini.get("unitprice10").setValue(data.unitPrice);
                       		mini.get("stock_id10").setValue(data.stockId);
                        }
                    }

                }
            });
        }
        function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("rmDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
   </script>
  </body>
</html>
