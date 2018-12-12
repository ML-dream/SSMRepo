<%@ page errorPage="error.jsp" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=utf-8"%>

<html>
  <head>
    <title>智能车间生产跟踪与管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
       div{
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
           color:#FFFFFF;
           }  
      </style>	
  </head>
  <body style="text-align:center;padding-top:0px;" background="images/bg14.jpg"  bgcolor="#d9d9d9"></br>
      <!--  <div style="text-align:center">&nbsp;&nbsp;&nbsp;
             <img src="images/logo.JPG" width="220px" hight="100px" alt="biaozhi"/>
      </div>  -->
  <form style="text-align:center" name="form1" method="post" action="LoginHandler">
      <div id="denglu" >
     	L&nbsp;A&nbsp;B&nbsp;_&nbsp;M&nbsp;E&nbsp;S&nbsp;系&nbsp;统
      <!--  &nbsp;&nbsp;&nbsp;生&nbsp;&nbsp;产&nbsp;&nbsp;管&nbsp;&nbsp;理&nbsp;&nbsp;与&nbsp;&nbsp;制&nbsp;&nbsp;造&nbsp;&nbsp;执&nbsp;&nbsp;行&nbsp;&nbsp;系&nbsp;&nbsp;统  -->
      </div></br></br></br>
      <div>用户名<input id="user" type="text" name="username"  maxlength="20" 
			   autocomplete=on value=""  onblur="javascript:return(checkform());">
	 </div></br>
      <div>密&nbsp;码<input id="pwd" type="password" name="passwd" maxlength="20"
			onblur="javascript:return(checkform());" value="">
	  </div></br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<span>
<input type="submit" name="Submit" value="确认登陆"  id="log">
<!-- </span>&nbsp;&nbsp;
<span><input type="button" name="button" value="更改密码"  
			onclick="window.location.href='modify.jsp';" id="modify"></span>
			<a herf="window.location.href='modify.jsp">
        <button>更改密碼</button>
    </a>
  
    </form>
    <a href="www.baidu.com">更改密碼</a>
    <a href="modify.jsp">链接</a>
    <a href="window.location.href='modify.jsp';">链接</a>
    <a href="javascript:window.location.href=modify.jsp"> 点击这里 </a> -->
    <script type="text/javascript">
       function  back(){
          winodw.location.href="modify.jsp";
       
       }
    
    </script>
    <input type="button" value="返回" onclick="back()"/>
    
    
  </body>
</html>

