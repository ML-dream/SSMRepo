<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<jsp:useBean id="bom_bean" scope="page" class="Bom.Bom_Bean"/>
<jsp:useBean id ="bombeandao" scope="page" class = "cfmes.bom.dao.BomBeanDao"/>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  //用到的参数
  String item_name="暂无";
  String father_item_name="暂无";
  String item_num="无";
  String memo="无";
  String dotype = "";
  String but_name_1 ="";
  String but_name_2=""; 
  String aofo="";
 %>

<%
 //获取地址栏中文参数传递值
    String flight_type  = (String)session.getAttribute("flight_type");
    String product_id  = (String)session.getAttribute("product_id");
    String issue_num   = (String)session.getAttribute("issue_num");
    String item_id     = (String)session.getAttribute("item_id");
	String father_item_id = (String)session.getAttribute("father_item_id");
	String id          = (String)session.getAttribute("id");
	String fid         = (String)session.getAttribute("fid");
	String level_id    =(String)session.getAttribute("level_id");
 %>
 
 <%
   //从ebom表和item表中取该物料的数据
        Hashtable hash = (Hashtable)bom_bean.getOther(product_id, item_id, father_item_id, level_id, id, fid);//
	     item_name = ds.toString((String)hash.get("ITEM_NAME"));
	  	 father_item_name= ds.toString((String)hash.get("FATHER_ITEM_NAME"));
		 item_num = ds.toString((String)hash.get("ITEM_NUM"));
	     memo=ds.toString((String)hash.get("MEMO"));
%>

<% //取得ao/fo编号
     
    if(bombeandao.getAo(flight_type,product_id,issue_num,item_id)=="AO"){
       but_name_1 = "查看";but_name_2 ="AO";aofo = "AO"; }else if (
       bombeandao.getFo(flight_type,product_id,issue_num,item_id)=="FO"){
       but_name_1 = "查看";but_name_2 = "FO";aofo = "FO";}else{
       but_name_1 = "添加";but_name_2 = "AO/FO";}
%>

<%
  session.setAttribute("aofo",aofo);
 %> 
 
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base target="work">
    
    <title>显示物料的详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../css/person.css" type=text/css rel=stylesheet>

  </head>
  
  <script>
    //javascript程序在浏览器端执行
    function doB(doingtype){
	    document.forsubmit.dotype.value = doingtype;
	    if(doingtype == "add"){
	    	document.forsubmit.action =  "blank.jsp";
	    }
	    else if(doingtype == "edit"){
			    document.getElementById("item_numbt").disabled = "";
			    document.getElementById("save").disabled = "";
			    return;
	    	 }else if(doingtype == "save"){
			        if(document.getElementById("item_numbt").value==""){
				        alert("请输入物料数量！");}
				        document.forsubmit2.action = "../BomEditServlet";
				        document.forsubmit2.submit();
			         	return;
			         }else if(doingtype == "addaofo"){
					   		 document.forsubmit.action = "turn.jsp";
					   	   }else if(doingtype =="del"){
								    alert("删除后不可恢复，是否确定要删除？");
								    document.forsubmit.action = "../bomdelservlet";
	   							 }
	    document.forsubmit.submit();
    }
  </script>
  
  <body>
  <form name = "forsubmit2"  method="post">
    <table class="query_form_table" border="1" align="center"  cellspacing="0" width="100%" style="word-break:break-all;" >
    <tr ><th>操作</th><th>物料名称</th><th>物料编号</th><th>父物料名称</th><th>数量</th><th>备注</th>
    </tr>
    <tr>
        <td align="center"><input id= "save" type= "button" disabled = "disabled" value = "保存" style = "cursor:hand;" onclick="doB('save');"/><FONT color="#338800">
        <A onclick="doB('edit'); "style="cursor:hand;">[编辑]</A><A onclick="doB('del'); "style="cursor:hand;">[删除]</A></FONT></td>
        <td><%=item_name %></td>
        <td><%=item_id %></td>
        <td><%=father_item_name %></td>
        <td><input type="text" disabled = "disabled" id = "item_numbt" name= "item_numbt" value = "<%=item_num %>"/></td>
        <td><%=memo %></td></tr>
    <tr ><td>
    <!--  
        <input type = "button"   value = "添加子物料" onclick = "doB('add')" style="cursor:hand;">
    -->   
        <input type = "button"  value = "<%=but_name_1 %><%=but_name_2 %>" onclick = "doB('addaofo')" style="cursor:hand;">
        </td>    
    </tr>
    </table> 
    </form>
    <form name = "forsubmit">
    <input type = "hidden" name = "dotype" value = "<%=dotype %>">
    </form> 
       
  </body>
</html>
