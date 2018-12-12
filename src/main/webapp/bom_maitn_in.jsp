<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="bom_bean" scope="page" class="Bom.Bom_Bean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>即将进入bom维护</title>

	<link rel="stylesheet" type="text/css" href="css/person.css">
	
	<script type="text/javascript" src="js/Bom_Select.js"></script>
	
  </head>
  
  <script type="text/javascript">
   function checkform()
    {
  if (document.form2.product_type.value==""){alert("请选择产品类型！");return ;}
  if (document.form2.product_id.value==""){alert("请选择产品号！");return ;}
  if (document.form2.lot.value=="" ){alert("请选择批次！");return ;}           
  if (document.form2.sortie.value=="" ){alert("请选择编号！");return ;}
      document.form2.submit();
    }
  </script>
  
  <body>
    <div class="page_title">工艺维护 </div> 
    <form name="form2" class="zipcode" action="bom_maitn.jsp" method=post>
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
		    <td ><select id="lot" name="lot" style="width:300px;" onchange="addSelect('lot',this.value,'sortie');">
                 <option value="">--------</option></select></td></tr>
        <tr><th>编&nbsp;&nbsp;号：</th>
		    <td ><select id="sortie" name="sortie" style="width:300px;" onchange="saveissue('sortie',this.value);">
                 <option value="">--------</option></select></td>
        </tr>
        
        <tr><td  colspan="2">
                <input type="button" name="Submit" value="查 询" class="common_button" onclick="javascript:return(checkform());">
                <input type="button"  value="返 回" class="common_button" onclick="javascript:top.window.location.href='index.jsp';"></td>
        </tr>
    </table>
    </form>
  </body>
  
</html>
