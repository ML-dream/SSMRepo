<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">



<title>法兰克系统实时显示界面</title>   

 <script type="text/javascript" src="<%=path %>/staticResources/js/echarts.js"></script>
<script type="text/javascript" src="<%=path %>/staticResources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/staticResources/js/echarts.min.js"></script>
<script type="text/javascript" src="<%=path %>/staticResources/js/miniui.js"></script>

<script type="text/javascript" src="<%=path %>/staticResources/js/dark.js"></script> 



<style> 
/* .container,.container1,.container2{ float:left}  没有用*/
.box {
	
/* 	border-style: groove; */
	border-radius: 15px;
	

	text-align: center;

}
    
  #boxclass1 {
	position: absolute;
	height: 99%;
	width: 30%;
	left:0px;
	top:0px;
	border-radius: 15px;
/* 	border-style: groove; */
	   text-align:center;
}
  #boxclass2 {
	position: absolute;
	height: 99%;
	width: 30%;
	right:0px;
	top:0px;
	border-radius: 15px;
/* 	border-style: groove; */
	   text-align:center;
	
}
#boxclass3 {
	position: absolute;
	height: 99%;
	width: 38%;
	left:31%;
	top:0px;
	border-radius: 15px;
/* 	border-style: groove; */
	   text-align:center;
}
  
#box1 {
	position: relative;

	height:49%;
	width: 100%;
	
	float:left;
	
	clear: left;
	Top:0px

}
#box2{
	
	float:left;
	clear: both;
	width: 100%;
	height:49%;
} 

#box5{
	
	
	border-radius: 15px;
	width: 100%;
	height: 49%;
	float:right;
	top:0px;
	right:0px;
	clear: right;
	
} 


#box6{
	position: relative;

	width: 100%;
	float:right;
	height: 49%;
	clear: right;
} 




#box3 {


	position: relative;
    left:7.5%;
	height:46%;
	width: 85%;

}
#box4 {


	position: relative;
	left:7.5%;
	height:46%;
	width: 85%;
	
}


#TopTitle {
	

	position: relative;
	border-radius: 15px;
	height: 7%;
	width: 100%;
    text-align:center;

	border-style: groove;
}




</style> 	
<!--

以下是用来的进行里面的每个input进行格式 调整到的css
-->
<style type="text/css">

      
        body
        {
            font-family:Arial, Sans-Serif;
            font-size:0.85em;
			overflow-x:hidden;
			overflow-y:auto;
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
            width: 53%;
        }

        ul h3
        {
            float: left;
            font-size: 3%;
            font-weight: bold;
            margin: 1px 0 0 0;
            width: 34%;
            margin-left:1%;
        }

        ul li
        {      
            padding: 0.1% 0;
            width:100%;
            overflow:hidden;
        }

        ul input[type="text"], ul input[type="password"]

        {

            width:50%;
            padding:1.1%;
            position:relative;
			  border:solid 1% #00FFFF; 
/*            border:none;//去除边框 */
     		 box-sizing: border-box;
  			text-align:center;
  			font-size:1.4em;


  				border:1px solid #c8cccf;
 				 color:#6a6f77;
 			 -web-kit-appearance:none;
 			 -moz-appearance: none;
			  display:block;
  				outline:0;
  				padding:0 1em;

          
            -moz-border-radius:5px;
            -webkit-border-radius:5px;
        }

        ul input.required 
        {
            border: solid 1% #f00;
        }

#panelName {
	font-weight: bold;
}
.test02 {
	text-align: center;
}


</style>
</head>
<body   >

<div id="boxclass3">

<div class="title" id="TopTitle"><h1><strong>南航机电学院实验室实时显示系统</strong></h1></div>


<div class="box" id="box3"  >

<h2><img src="http://localhost:8080/SSM/staticResources/image/chexifuhe.jpg" alt="Account information" /></h2>
<ul>

<li>
<h3>机床IP</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>机床名称</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>操作系统</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>
<li>
<h3>主轴倍率</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li >
<h3>主轴负载率</h3>
<p><input type="text" value="" id="name" name="name" /></p>
</li>

<li>
<h3>主轴转速</h3>
<p><input type="text" value="" id="name" name="email" /></p>
</li>

<li>
<h3>实际进给速度</h3>
<p><input type="text" value="" id="name" name="passwd" /></p>
</li>

<li>
<h3>实际进给倍率</h3>
<p><input type="text" value="" id="name" name="passwd_conf" /></p>
</li>

<li>
<h3>报警信息</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>报警编号</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>当前程序的程序名称</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>
<li>
<h3>启动以来的时间</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>
<li>
<h3>程序状态</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>
<li>
<h3>刀具啮合时间</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

</ul>
</div>

<div class="box" id="box4">
<h2><img src="http://localhost:8080/SSM/staticResources/image/chexifuhe.jpg" alt="Account information" /></h2>
<ul>

<li>
<h3>机床IP</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>机床名称</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>操作系统</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>
<li>
<h3>主轴倍率</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li >
<h3>主轴负载率</h3>
<p><input type="text" value="" id="name" name="name" /></p>
</li>

<li>
<h3>主轴转速</h3>
<p><input type="text" value="" id="name" name="email" /></p>
</li>

<li>
<h3>实际进给速度</h3>
<p><input type="text" value="" id="name" name="passwd" /></p>
</li>

<li>
<h3>实际进给倍率</h3>
<p><input type="text" value="" id="name" name="passwd_conf" /></p>
</li>

<li>
<h3>报警信息</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>报警编号</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>当前程序的程序名称</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>
<li>
<h3>启动以来的时间</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>

<li>
<h3>程序状态</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>
<li>
<h3>刀具啮合时间</h3>
<p><input type="text" value="" id="userName" name="user_name" /></p>
</li>
</ul>
</div>
</div>



<div class="box"  id="boxclass1">

<div class="box"  id="box1"  >
<h2><img src="http://localhost:8080/SSM/staticResources/image/chexifuhe.jpg" alt="Account information" /></h2>
<ul>

<li>
<h3>机床IP</h3>
<p><input type="text" value="" id="machineIp5501" name="machineIp5501" /></p>
</li>

<li>
<h3>机床名称5501</h3>
<p><input type="text" value="" id="machineName5501" name="machineName5501" /></p>
</li>

<li>
<h3>操作系统</h3>
<p><input type="text" value="" id="machineSystem5501" name="machineSystem5501" /></p>
</li>
<li>
<h3>主轴倍率</h3>
<p><input type="text" value="" id="speedOvr5501" name="speedOvr5501" /></p>
</li>

<li >
<h3>主轴负载率</h3>
<p><input type="text" value="" id="driveLoad5501" name="driveLoad5501" /></p>
</li>

<li>
<h3>实际主轴转速</h3>
<p><input type="text" value="" id="actSpeed5501" name="actSpeed5501" /></p>
</li>

<li>
<h3>实际进给速度</h3>
<p><input type="text" value="" id="actFeedRate5501" name="actFeedRate5501" /></p>
</li>

<li>
<h3>实际进给倍率</h3>
<p><input type="text" value="" id="actSpeedRel5501" name="actSpeedRel5501" /></p>
</li>

<li>
<h3>报警信息</h3>
<p><input type="text" value="" id="fillText5501" name="fillText5501" /></p>
</li>

<li>
<h3>报警编号</h3>
<p><input type="text" value="" id="textIndex5501" name="textIndex5501" /></p>
</li>

<li>
<h3>当前程序的程序名称</h3>
<p><input type="text" value="" id="progName5501" name="progName5501" /></p>
</li>
<li>
<h3>启动以来时间</h3>
<p><input type="text" value="" id="poweronTime5501" name="poweronTime5501" /></p>
</li>

<li>
<h3>程序状态</h3>
<p><input type="text" value="" id="progStatus5501" name="progStatus5501" /></p>
</li>
<li>
<h3>刀具啮合时间</h3>
<p><input type="text" value="" id="cuttingTime5501" name="cuttingTime5501" /></p>
</li>
</ul>
</div>


<div class="box" id="box2">
<h2><img src="http://localhost:8080/SSM/staticResources/image/chexifuhe.jpg" alt="Account information" /></h2>
<ul>

<li>
<h3>机床IP</h3>
<p><input type="text" value="" id="machineIp5502" name="machineIp5502" /></p>
</li>

<li>
<h3>机床名称5502</h3>
<p><input type="text" value="" id="machineName5502" name="machineName5502" /></p>
</li>

<li>
<h3>操作系统</h3>
<p><input type="text" value="" id="machineSystem5502" name="machineSystem5502" /></p>
</li>
<li>
<h3>主轴倍率</h3>
<p><input type="text" value="" id="speedOvr5502" name="speedOvr5502" /></p>
</li>

<li >
<h3>主轴负载率</h3>
<p><input type="text" value="" id="driveLoad5502" name="driveLoad5502" /></p>
</li>

<li>
<h3>实际主轴转速</h3>
<p><input type="text" value="" id="actSpeed5502" name="actSpeed5502" /></p>
</li>

<li>
<h3>实际进给速度</h3>
<p><input type="text" value="" id="actFeedRate5502" name="actFeedRate5502" /></p>
</li>

<li>
<h3>实际进给倍率</h3>
<p><input type="text" value="" id="actSpeedRel5502" name="actSpeedRel5502" /></p>
</li>

<li>
<h3>报警信息</h3>
<p><input type="text" value="" id="fillText5502" name="fillText5502" /></p>
</li>

<li>
<h3>报警编号</h3>
<p><input type="text" value="" id="textIndex5502" name="textIndex5502" /></p>
</li>

<li>
<h3>当前程序的程序名称</h3>
<p><input type="text" value="" id="progName5502" name="progName5502" /></p>
</li>
<li>
<h3>启动以来时间</h3>
<p><input type="text" value="" id="poweronTime5502" name="poweronTime5502" /></p>
</li>

<li>
<h3>程序状态</h3>
<p><input type="text" value="" id="progStatus5502" name="progStatus5502" /></p>
</li>
<li>
<h3>刀具啮合时间</h3>
<p><input type="text" value="" id="cuttingTime5502" name="cuttingTime5502" /></p>
</li>
</ul>
</div> 


</div>


<div id="boxclass2">

<div class="box" id="box5">
<h2><img src="http://localhost:8080/SSM/staticResources/image/chexifuhe.jpg" alt="Account information" /></h2>
<ul>

<li>
<h3>机床IP</h3>
<p><input type="text" value="" id="machineIp5503" name="machineIp5503" /></p>
</li>

<li>
<h3>机床名称5503</h3>
<p><input type="text" value="" id="machineName5503" name="machineName5503" /></p>
</li>

<li>
<h3>操作系统</h3>
<p><input type="text" value="" id="machineSystem5503" name="machineSystem5503" /></p>
</li>
<li>
<h3>主轴倍率</h3>
<p><input type="text" value="" id="speedOvr5503" name="speedOvr5503" /></p>
</li>

<li >
<h3>主轴负载率</h3>
<p><input type="text" value="" id="driveLoad5503" name="driveLoad5503" /></p>
</li>

<li>
<h3>实际主轴转速</h3>
<p><input type="text" value="" id="actSpeed5503" name="actSpeed5503" /></p>
</li>

<li>
<h3>实际进给速度</h3>
<p><input type="text" value="" id="actFeedRate5503" name="actFeedRate5503" /></p>
</li>

<li>
<h3>实际进给倍率</h3>
<p><input type="text" value="" id="actSpeedRel5503" name="actSpeedRel5503" /></p>
</li>

<li>
<h3>报警信息</h3>
<p><input type="text" value="" id="fillText5503" name="fillText5503" /></p>
</li>

<li>
<h3>报警编号</h3>
<p><input type="text" value="" id="textIndex5503" name="textIndex5503" /></p>
</li>

<li>
<h3>当前程序的程序名称</h3>
<p><input type="text" value="" id="progName5503" name="progName5503" /></p>
</li>
<li>
<h3>启动以来时间</h3>
<p><input type="text" value="" id="poweronTime5503" name="poweronTime5503" /></p>
</li>

<li>
<h3>程序状态</h3>
<p><input type="text" value="" id="progStatus5503" name="progStatus5503" /></p>
</li>
<li>
<h3>刀具啮合时间</h3>
<p><input type="text" value="" id="cuttingTime5503" name="cuttingTime5503" /></p>
</li>
</ul>
</div> 

<div class="box" id="box6">
<h2><img src="http://localhost:8080/SSM/staticResources/image/chexifuhe.jpg" alt="Account information" /></h2>
<ul>

<li>
<h3>机床IP</h3>
<p><input type="text" value="" id="machineIp5513" name="machineIp5513" /></p>
</li>

<li>
<h3>机床名称5513</h3>
<p><input type="text" value="" id="machineName5513" name="machineName5513" /></p>
</li>

<li>
<h3>操作系统</h3>
<p><input type="text" value="" id="machineSystem5513" name="machineSystem5513" /></p>
</li>
<li>
<h3>主轴倍率</h3>
<p><input type="text" value="" id="speedOvr5513" name="speedOvr5513" /></p>
</li>

<li >
<h3>主轴负载率</h3>
<p><input type="text" value="" id="driveLoad5513" name="driveLoad5513" /></p>
</li>

<li>
<h3>实际主轴转速</h3>
<p><input type="text" value="" id="actSpeed5513" name="actSpeed5513" /></p>
</li>

<li>
<h3>实际进给速度</h3>
<p><input type="text" value="" id="actFeedRate5513" name="actFeedRate5513" /></p>
</li>

<li>
<h3>实际进给倍率</h3>
<p><input type="text" value="" id="actSpeedRel5513" name="actSpeedRel5513" /></p>
</li>

<li>
<h3>报警信息</h3>
<p><input type="text" value="" id="fillText5513" name="fillText5513" /></p>
</li>

<li>
<h3>报警编号</h3>
<p><input type="text" value="" id="textIndex5513" name="textIndex5513" /></p>
</li>

<li>
<h3>当前程序的程序名称</h3>
<p><input type="text" value="" id="progName5513" name="progName5513" /></p>
</li>
<li>
<h3>启动以来时间</h3>
<p><input type="text" value="" id="poweronTime5513" name="poweronTime5513" /></p>
</li>

<li>
<h3>程序状态</h3>
<p><input type="text" value="" id="progStatus5513" name="progStatus5513" /></p>
</li>
<li>
<h3>刀具啮合时间</h3>
<p><input type="text" value="" id="cuttingTime5513" name="cuttingTime5513" /></p>
</li>
</ul>
</div> 
</div>

<script>

       
/* var machineIp5501 =0;    
var machineName5501=0 ;  
var machineSystem5501 =0;       
var speedOvr5501=0  ; 
var driveLoad5501=0  ; 
var actSpeed5501=0  ; 
var actFeedRate5501=0  ; 
var actSpeedRel5501=0  ; 
var fillText5501=0  ; 
var textIndex5501=0  ; 
var progName5501=0  ; 
var poweronTime5501=0  ; 
var progStatus5501=0  ; 
var cuttingTime5501=0  ; 


var machineIp5502 =0;    
var machineName5502=0 ;  
var machineSystem5502 =0;       
var speedOvr5502=0  ; 
var driveLoad5502=0  ; 
var actSpeed5502=0  ; 
var actFeedRate5502=0  ; 
var actSpeedRel5502=0  ; 
var fillText5502=0  ; 
var textIndex5502=0  ; 
var progName5502=0  ; 
var poweronTime5502=0  ; 
var progStatus5502=0  ; 
var cuttingTime5502=0  ; 


var machineIp5503 =0;    
var machineName5503=0 ;  
var machineSystem5503 =0;       
var speedOvr5503=0  ; 
var driveLoad5503=0  ; 
var actSpeed5503=0  ; 
var actFeedRate5503=0  ; 
var actSpeedRel5503=0  ; 
var fillText5503=0  ; 
var textIndex5503=0  ; 
var progName5503=0  ; 
var poweronTime5503=0  ; 
var progStatus5503=0  ; 
var cuttingTime5503=0  ; 


var machineIp5513 =0;    
var machineName5513=0 ;  
var machineSystem5513 =0;       
var speedOvr5513=0  ; 
var driveLoad5513=0  ; 
var actSpeed5513=0  ; 
var actFeedRate5513=0  ; 
var actSpeedRel5513=0  ; 
var fillText5513=0  ; 
var textIndex5513=0  ; 
var progName5513=0  ; 
var poweronTime5513=0  ; 
var progStatus5513=0  ; 
var cuttingTime5513=0  ; 
 */
 mini.parse();
 
$(function(){
 update();
 setInterval(update, 300); 
})  


 function update(){
	 
	
	 
	$.ajax({
		type: "get",
		url:"totalDataFeedback.action",

		success:function(text){
			
		
           var msg=$.parseJSON(text);
    
           document.getElementById('machineName5501').value=msg[0].machineName; 
           document.getElementById('machineIp5501').value=msg[0].machineIp;   
           document.getElementById('machineSystem5501').value=msg[0].machineSystem;
           document.getElementById('speedOvr5501').value=msg[0].speedOvr;
           document.getElementById('driveLoad5501').value=msg[0].driveLoad;
           document.getElementById('actSpeed5501').value=msg[0].actSpeed;
           document.getElementById('actFeedRate5501').value=msg[0].actFeedRate;
           document.getElementById('actSpeedRel5501').value=msg[0].actSpeedRel;
           document.getElementById('fillText5501').value=msg[0].fillText;
           document.getElementById('textIndex5501').value=msg[0].textIndex;
           document.getElementById('progName5501').value=msg[0].progName;
           document.getElementById('poweronTime5501').value=msg[0].poweronTime;
           document.getElementById('progStatus5501').value=msg[0].progStatus;
           document.getElementById('cuttingTime5501').value=msg[0].cuttingTime;
         	
           document.getElementById('machineName5502').value=msg[1].machineName; 
           document.getElementById('machineIp5502').value=msg[1].machineIp;   
           document.getElementById('machineSystem5502').value=msg[1].machineSystem;
           document.getElementById('speedOvr5502').value=msg[1].speedOvr;
           document.getElementById('driveLoad5502').value=msg[1].driveLoad;
           document.getElementById('actSpeed5502').value=msg[1].actSpeed;
           document.getElementById('actFeedRate5502').value=msg[1].actFeedRate;
           document.getElementById('actSpeedRel5502').value=msg[1].actSpeedRel;
           document.getElementById('fillText5502').value=msg[1].fillText;
           document.getElementById('textIndex5502').value=msg[1].textIndex;
           document.getElementById('progName5502').value=msg[1].progName;
           document.getElementById('poweronTime5502').value=msg[1].poweronTime;
           document.getElementById('progStatus5502').value=msg[1].progStatus;
           document.getElementById('cuttingTime5502').value=msg[1].cuttingTime;
           
           document.getElementById('machineName5503').value=msg[2].machineName; 
           document.getElementById('machineIp5503').value=msg[2].machineIp;   
           document.getElementById('machineSystem5503').value=msg[2].machineSystem;
           document.getElementById('speedOvr5503').value=msg[2].speedOvr;
           document.getElementById('driveLoad5503').value=msg[2].driveLoad;
           document.getElementById('actSpeed5503').value=msg[2].actSpeed;
           document.getElementById('actFeedRate5503').value=msg[2].actFeedRate;
           document.getElementById('actSpeedRel5503').value=msg[2].actSpeedRel;
           document.getElementById('fillText5503').value=msg[2].fillText;
           document.getElementById('textIndex5503').value=msg[2].textIndex;
           document.getElementById('progName5503').value=msg[2].progName;
           document.getElementById('poweronTime5503').value=msg[2].poweronTime;
           document.getElementById('progStatus5503').value=msg[2].progStatus;
           document.getElementById('cuttingTime5503').value=msg[2].cuttingTime;
           
           document.getElementById('machineName5513').value=msg[3].machineName; 
           document.getElementById('machineIp5513').value=msg[3].machineIp;   
           document.getElementById('machineSystem5513').value=msg[3].machineSystem;
           document.getElementById('speedOvr5513').value=msg[3].speedOvr;
           document.getElementById('driveLoad5513').value=msg[3].driveLoad;
           document.getElementById('actSpeed5513').value=msg[3].actSpeed;
           document.getElementById('actFeedRate5513').value=msg[3].actFeedRate;
           document.getElementById('actSpeedRel5513').value=msg[3].actSpeedRel;
           document.getElementById('fillText5513').value=msg[3].fillText;
           document.getElementById('textIndex5513').value=msg[3].textIndex;
           document.getElementById('progName5513').value=msg[3].progName;
           document.getElementById('poweronTime5513').value=msg[3].poweronTime;
           document.getElementById('progStatus5513').value=msg[3].progStatus;
           document.getElementById('cuttingTime5513').value=msg[3].cuttingTime;
		   
		
		},
			error : function() {
		    /* alert("更新失败"); */
		}
	});
}

</script>
</body>

</html>

