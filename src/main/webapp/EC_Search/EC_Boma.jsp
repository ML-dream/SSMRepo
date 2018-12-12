<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>

<jsp:useBean id="bom_bean" scope="page" class="Bom.Bom_Bean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>条件查询</title>
    
    
    
    <!-- Bom变更量      请选择查询项 -->
    
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/ec_search.js"></script>
	<link rel="stylesheet" type="text/css" href="css/person.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="page_title">请选择查询项 </div>
    <form name="form" class="zipcode" action="javascript:EC_Search/EC_Bomb.jsp" method=post>
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
                <input type="button" name="Submit" value="查 询" class="common_button" onclick="">
                <input type="button"  value="返 回" class="common_button" onclick="javascript:top.window.location.href='ec_ebom_search.jsp';"></td>
        </tr>
    </table>
    </form>
  </body>
</html>
