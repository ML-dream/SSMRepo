<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<style>
	body{ margin:0; background-image:url(imgs/login/logina.png); width:100%;}
	img{ display:block;}
	h1{ margin:0; padding:0; font-size:16px; margin-bottom:20px;}
	.logo{ width:920px; margin:auto; padding-top:50px;padding-bottom:100px;}
	.login{width:911px; margin:auto;  overflow:hidden; background-color:#FFF;-webkit-box-shadow: 2px 2px 5px #808080; padding-bottom:2px;}
	.photo,.logobg{ float:left;}
	.photo{margin-left:2px; margin-top:2px;}
	.logobg{ margin-left:3px;margin-top:2px; position:relative;}
	.content{ position:absolute;  top:30px; width:100%;}
	.small{ overflow:hidden; margin-bottom:15px;}
	.small a{ font-size:14px; color:#2a4663; font-weight:normal; padding-top:4px;}
	input{ width:150px; border:#99cbf2 solid 1px; height:22px; line-height:22px;}
	.small a,input{ float:left}
	.Remember{ font-size:14px; font-weight:normal;color:#2a4663; margin-left:70px; overflow:hidden;}
	.Remember img,.Remember a{ float:left; margin-right:4px;}
	.Remember img{ cursor:pointer;}
	.log{width:150px; background-image:url(imgs/login/sma_03.png); border-radius:6px; border:#4887cc 1px solid; line-height:26px; margin-left:0px; margin-top:10px;}
	.log a{font-size:14px; color:#FFF; font-weight:bold; margin-left:12px; text-decoration:none; line-height:26px; letter-spacing:5px; cursor:pointer;}
	.log a:hover{ color:#C30;}
	.footer{ margin-top:132px;}
	.footer span{ font-size:13px; color:#666666; width:322px; display:block; margin:auto;}
</style>
<meta charset="utf-8">
<title>系统登录</title>
</head>

<body>
<form action="login.action" method="post" id="loginForm" onkeypress="if(event.keyCode==13||event.which==13){doSubmit()}">
<div class="logo"><img src="imgs/login/logo.png"></div>
<div class="login">
	<div class="photo"><img src="imgs/login/photo_03.png"></div>
    <div class="logobg">
    <img src="imgs/login/logobg_03.png">
    <div class="content">
    	<table style="width:100%;">
    	   <tr style="width:100%;"><td style="width:100%;text-align:center;" colspan="3"><h1>用户登录</h1></td></tr>

    	   <tr style="width:100%;height:10px"><td style="width:100%;text-align:center" colspan="3"></td></tr>
    	   
    	   <tr style="width:100%;">
    	       <td style="width:30%;text-align:center"> 用户名：</td>
    	       <td style="width:70%;text-align:left" colspan="2">
    	             <input id="user" type="text" name="username" class="mini-textbox" style="width:150px;margin-left:0px;">
    	       </td>
    	   </tr>
    	   
    	   <tr style="width:100%;height:10px"><td style="width:100%;text-align:center" colspan="3"></td></tr>
    	   
    	   <tr style="width:100%;">
    	       <td style="width:30%;text-align:center">密码：</td>
    	       <td style="width:70%;text-align:left" colspan="2">
    	            <input name="sysUsersBean.password" type="password" id="password" class="mini-password" style="width:150px;margin-left:0px;">
    	       </td>
    	   </tr>
    	   <tr style="width:100%;height:10px">
    	      <td style="width:100%;text-align:center" colspan="3"></td>
    	   </tr>
    	   <tr style="width:100%;height:15px">
    	      <td style="width:30%;text-align:center"></td>
    	      <td style="width:70%;text-align:center" align="left" colspan="2">
    	         <div class="log" onclick="doSubmit()" ><a href="javascript:doSubmit();">登录</a></div>
    	      </td>
   
    	</table>
    </div>
    </div>	
</div>
</form>
</body>
</html>


    <script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
    <jsp:include page="/commons/jquery_header.jsp"></jsp:include>
    <jsp:include page="/commons/include.jsp"></jsp:include>  
    <jsp:include page="/commons/miniui_header.jsp"></jsp:include>

    <script language="JavaScript">
		if (window != top) // session丢失, 登录页面跳出iframe框架显示(一层iframe)
		{
			top.location.href = location.href;
		}
		
		if (window != top) //  session丢失, 登录页面跳出iframe框架显示(二层iframe)
		{
			top.location.href = location.href;
		}
    </script>
    <script language="javascript">
	
		function doSubmit() 
		{
	     	// 校验表单
	     	var checkOk = checkForm();
	     	if (checkOk == false)
	     	{
	     		//return false;
	     	}
	     	else
	     	{
				var theform = document.getElementById('loginForm');
				theform.submit();

	     	}
		}	
		
		function checkForm()
		{  
			var login_name = mini.get("login_name").getValue();
			login_name = login_name.replace(/\s{1,}/g,"");
			if (null == login_name || "" == login_name)
			{
			  	alert("登录账户不能为空!");
			  	$("#login_name").focus();
				return false;
			} 

			var orgCode = mini.get("orgCode").getValue();
			orgCode = orgCode.replace(/\s{1,}/g,"");
			if (null == orgCode || "" == orgCode)
			{   
				if(login_name != 'admin'){
					alert("组织机构不能为空!");
				  	$("#login_name").focus();
					return false;
				}
			} 

		  var password = mini.get("password").getValue();
		  password = password.replace(/\s{1,}/g,"");
		  if (null == password || "" == password)
		  {
		  	  alert("登录密码不能为空!");
		  	  $("#password").focus();
		  	  
			  return false;
		  }
		  //校验通过
		  return true;
		}

    </script>
    <script language="javascript">
  		var message = '${message}';
  		if (null != message && "" != message)
  		{
  			alert(message);
  		}
    </script>
