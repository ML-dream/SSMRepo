<%@ page errorPage="error.jsp" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>智能制造MES系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
    
    
    <script language="javascript">
	    $ = function(eid) {
				return document.getElementById(eid);
			}
		function checkform() {
			if (document.form1.username.value == ""&& document.form1.passwd.value == "")
              {
				alert("用户名或密码为空！");
				return false;
			}
			return true;
		}
    </script> 
    <style type="text/css">
      /*  div{
           font-size:20pt;
           color:#FFFFFF;
           
            }  
         input[id=user]{
           font-size:15pt;
           width:300px;
           height:45px;
         
          }
          input[id=pwd]{
           font-size:15pt;
           width:300px;
           height:45px;
          }
         input[id=log]{ 
           width:100px;
           height:30px; 
           font-size:15pt;
           border:1px solid #FFFFFF;
           background-color:#FFFFFF;
           color:#0000E3;
          }
          input[id=modify]{
           width:100px;
           height:30px;   
           font-size:15pt;
           border:1px solid #FFFFFF;
           background-color:#FFFFFF;
           color:#0000E3;
           }
            span>input:hover{
           background-color:#66B3FF;
           color:#FFFFFF; */
           }  
      </style>	
      
      
         <style type="text/css">
    	*{margin: 0;padding: 0;}
 
    
    
    

        body
        {
            font-family:Arial, Sans-Serif;
            font-size:2em;
			overflow-x:hidden;
			overflow-y:hidden;
	
        }

        img 
        {
            border:none;
        }

        ul, ul li
        {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        ul li.first
        {
            border-top: 0px solid #DFDFDF;
        }
       
        ul p
        {
            float: left;
            margin: 0;
            width: 310px;
        }

        ul h3
        {
            float: left;
            font-size: 25px;
            font-weight: bold;
            margin: 1px 0 0 0;
            width: 200px;
            margin-left:2px;
        }

        ul li
        {      
            padding: 9px 0;
            width:600px;
            overflow:hidden;
        }

        ul input[type="text"], ul input[type="password"]

        {	
     	   font-size:15px;
			text-align:center;
            width:300px;
            padding:10px;
            position:relative;
            border:solid 1px #666;
            -moz-border-radius:5px;
            -webkit-border-radius:5px;
        }

        ul input.required 
        {
            border: solid 1px #f00;
        }

  
      fieldset{
    border: 0;
}  
   .test01 {
	position: absolute;
	height: 50%;
	width: 38%;
	left:31%;
	top:40%;
	border-radius: 15px;
	border-style: groove; 
	text-align:center;
}

.mini-button-inner{
   padding:18px;
   font-size:20px;
}
   
       </style>
  </head>
  <body style="text-align:center;padding-top:0px;" background="staticResources/image/bj.jpg"  bgcolor="#d9d9d9"></br>
 
<!--   <form style="text-align:center" name="form1" method="post" action="LoginHandler">
      <div id="denglu" >
     	L&nbsp;A&nbsp;B&nbsp;_&nbsp;M&nbsp;E&nbsp;S&nbsp;系&nbsp;统
       &nbsp;&nbsp;&nbsp;生&nbsp;&nbsp;产&nbsp;&nbsp;管&nbsp;&nbsp;理&nbsp;&nbsp;与&nbsp;&nbsp;制&nbsp;&nbsp;造&nbsp;&nbsp;执&nbsp;&nbsp;行&nbsp;&nbsp;系&nbsp;&nbsp;统 
      </div></br></br></br>
      <div>用户名<input id="user" type="text" name="username"  maxlength="20" 
			   autocomplete=on value=""  onblur="javascript:return(checkform());">
	 </div></br>
      <div>密&nbsp;码<input id="pwd" type="password" name="passwd" maxlength="20"
			onblur="javascript:return(checkform());" value="">
	  </div></br></br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span>
		<input type="submit" name="Submit" value="确认登陆"  id="log"> -->


	<fieldset class="test01"  align="center" >

		<legend align="center">
			智能制造MES系统
		</legend>
		<br />
		<br />
		
     <div class="box" id="box3"  >
		<ul>
		
		<li>
		<h3>用户名</h3>
		<p><input id="user" name="username" type="text" width="100%"  style="BACKGROUND-COLOR: transparent;"/></p>
		</li>
		
		<li>
		<h3>密码</h3>
		<p><input id="pwd" type="password" name="passwd" width="100%"   style="BACKGROUND-COLOR: transparent;"/></p>
		</li>
		

		
		</ul>
</div>

	<div >
  	 <a class="mini-button"  plain="false"  onclick="getForm()">登陆</a>
  		<!-- <input type="submit" name="Submit"  value="确认登陆"  id="log"> -->
  	</div>
       
      </fieldset>  



    <script type="text/javascript">
       function  back(){
          winodw.location.href="modify.jsp";
       
       }
    
       
  		function getForm(){
  			var username=document.getElementById("user").value; 
  			var passwd=document.getElementById("pwd").value; 
  			window.location.href="LoginHandler?username="+username+"&passwd="+passwd;
   		
  		}
       
       
    </script>

  </body>
</html>

