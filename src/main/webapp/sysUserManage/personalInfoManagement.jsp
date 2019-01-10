<%@ page language="java" import="java.util.*,com.wl.forms.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户信息</title>
    <!-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  -->
       <script src="<%=path %>/resource/timePlanJs/jquery.min.js"></script>

		<script src="<%=path %>/resource/timePlanJs/bootstrap.min.js?v=3.3.6"></script>
		<script src="<%=path %>/resource/timePlanJs/layer.js"></script>
		<script src="<%=path %>/resource/timePlanJs/template.js"></script>
		<script src="<%=path %>/resource/timePlanJs/moment-with-locales.js"></script>
		<script src="<%=path %>/resource/timePlanJs/laypage.js"></script>
		<script src="<%=path %>/resource/timePlanJs/jquery.qrcode.min.js"></script>
		
		<!--jquery validate begin-->
		<script src="<%=path %>/resource/timePlanJs/jquery.validate.min.js"></script>
		<script src="<%=path %>/resource/timePlanJs/messages_zh.min.js"></script>
		<script src="<%=path %>/resource/timePlanJs/jquery.validate.extend.js"></script>
		<!--jquery validate end-->
		
		<script src="<%=path %>/resource/timePlanJs/utils.js"></script>
		    
    
		<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
		<jsp:include page="/commons/miniui_header.jsp" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
		
	<!-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  -->

		<style type="text/css">
	    	*{margin: 0;padding: 0;}
	    	
	    	  .fiedsetclass {
				/* position: ; */
		/* 		height: 50%; */
				width: 38%;
				left:31%;
				top:10%;
				border-radius: 15px;
				border-style: groove; 
				text-align:center;
							}
	    	</style>
	    <style type="text/css">
    	*{margin: 0;padding: 0;}
 
    
    
    
    


       </style>
    	
	    	
  </head>
  <!-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  -->
  
  <body>
  
  	
  	<fieldset class="fiedsetclass" style="width: 100%;" align="center">
		<legend>
			个人信息
		</legend>
		<div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="ondEdit()">修改</a>

   		 </div>
   		
   		<table  rules=none tableframe=void width="100%" style="border-collapse:separate; border-spacing:0px 10px;">
   		<tr >
   			<td width="10%" align="center"><label for="staffCode$text" >用户编号</label></td>
            <td width="25%"><input id="staffCode"  name="staffCode" class="mini-textbox"  width="100%"  value="<%=request.getParameter("staffCode")%>" enabled="false"/></td>
            <td width="10%" align="center"><label for="staffName$text">用户姓名</label></td>
            <td width="25%"><input id="staffName" name="staffName" class="mini-textbox"  width="100%"  value="<%=request.getParameter("staffName")%>" enabled="false"/>
   			<td width="10%" align="center"><label for="sectionCode$text">所属部门</label></td>
            <td width="20%"><input id="sectionCode"  name="sectionCode" class="mini-textbox"  width="100%"  value="<%=request.getParameter("deptUser")%>" enabled="false" />  
            </td>
        </tr>
        <tr>
       	   <td width="10%" align="center"><label for="birthday$text" >出生日期</label></td>
            <td width="25%"><input id="birthday"  name="birthday" class="mini-textbox"  width="100%"  dateformat="yyyy-MM-dd"  value="<%=request.getParameter("staffCode")%>" enabled="false"/></td>
            <td width="10%" align="center"><label for="gender$text">性别</label></td>
            <td width="25%"><input id="gender" name="gender" class="mini-textbox"  width="100%"  value="<%=request.getParameter("staffName")%>" enabled="false"/>
   			<td width="10%" align="center"><label for="connectorTel$text">联系电话</label></td>
            <td width="20%"><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%"  value="<%=request.getParameter("deptUser")%>" enabled="false" />  
            </td>
           
        </tr>

   
   		
   	</table>
   	  </fieldset>
<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  -->
<%--   </br>
  </br>

      	<fieldset class="fiedsetclass" style="width: 100%;" align="center">
		<legend>
			我的客户信息
		</legend>
		
		<div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="addCustomer()">新增客户信息</a>
    </div>
        <div id="bookMachine" class="mini-datagrid" style="width:100%;height:500px;" url="ShowMyCustomer.action?staffCode=<%=((User)request.getSession().getAttribute("user")).getStaffCode()%>">
        <div property="columns">            
        	<div field="companyId" width="120" headerAlign="center" allowSort="true" align="center" headerAlign="center">客户编号</div> 
            <div field="companyName" width="120" headerAlign="center" allowSort="true" align="center" headerAlign="center">客户名称</div> 
            <div field="connector" width="100" allowSort="true" align="center" headerAlign="center" align="center">联系人</div>               
            <div field="connectorTel" width="100" allowSort="true"  align="center" headerAlign="center">联系电话</div>            
            <div field="type" width="100" allowSort="true" align="center" headerAlign="center">客户类型</div>
                                                
        
    </div> 
    </div>
 </fieldset> --%>
  <!-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  -->
  
    <script type="text/javascript">
    
    	mini.parse();
	    //var grid = mini.get("bookMachine");
	    var staffCode="<%=((User)request.getSession().getAttribute("user")).getStaffCode()%>";
	  	// grid.load(); 
	    
	    function ondEdit(){
//	        window.open("EditEmployeeDetailServlet?staffCode=" + staffCode,
//	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	         window.location.href="EditEmployeeDetailServlet?staffCode="+staffCode;
		}
	    
	    function addCustomer(){
//	        window.open("EditEmployeeDetailServlet?staffCode=" + staffCode,
//	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	         window.location.href="customerManage/addCustomer.jsp";
		}
	    
	    load();
   		function load () {
		   			
   				jQuery.ajax({
		  				type: "POST",
		  				url: "LoadDefaultEmployeeInfo.action?staffCode="+staffCode,
		  				dataType: "json", 
		  				cache: false,
		  				success: function (data) {
		  					  mini.get("staffCode").setValue(data.staffCode);
		  					  mini.get("staffName").setValue(data.staffName);
		  					 /*  var test=mini.formatDate(data.birthday,"yyyy-MM-dd"); */
		                      mini.get("birthday").setValue(data.birthday.substring(0,10));
		                   	  mini.get("gender").setValue(data.gender);
		                      mini.get("sectionCode").setValue(data.sectionCode);
		                      mini.get("connectorTel").setValue(data.mobilePhone);
		  				}
					});
                         
                }
        
    </script>
  </body>
</html>
