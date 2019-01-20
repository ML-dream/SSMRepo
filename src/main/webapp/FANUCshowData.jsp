<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">



<title>法兰克系统实时显示界面</title>   

<script type="text/javascript" src="staticResources/js/echarts.js"></script>
<script type="text/javascript" src="staticResources/js/jquery.min.js"></script>
<script type="text/javascript" src="staticResources/js/echarts.min.js"></script>
<!-- <script type="text/javascript" src="staticResources/js/miniui.js"></script> -->

<script type="text/javascript" src="staticResources/js/dark.js"></script>


<style> 
/* .container,.container1,.container2{ float:left}  没有用*/

    
    
#box1 {
	position: absolute;
	/* border-style: groove; */
	border-radius: 15px;
	width: 545px;
	height: 400px;
	left: 0px;
	top: 0px;

}
#box6{
	position: absolute;
	/* border-style: groove; */
	border-radius: 15px;
	width: 630px;
	height: 510px;
	top: 0px;
	right: 0px;
} 
#box2{
	left: 0px;
	bottom: 0px;

} 


#box8{
	right: 0px;
	bottom: 0px;
} 


.box {
	position: absolute;
	/* border-style: groove; */
	border-radius: 15px;
	width: 530px;
	height: 565px;
	text-align: center;
}

.totalTable {
	position: absolute;
	left: 50%;
   	transform: translate(-50%,0);
   
    bottom: 0px;
   
	border-radius: 15px;
	/* border-style: groove; */
	height: 800px;
	width: 800px;
}
.macinheInfoFiedldset{
	border-radius: 15px;
	}
#machineInfo{
	position: absolute;
	left: 50%;
   	transform: translate(-50%,0);
    top: 95px;
   
	height: 150px;
	width: 800px;
	
	}
#TopTitle {
	

	border-radius: 15px;
	height: 80px;
	width: 800px;
    text-align:center;
	margin:0 auto;  
	
	/* border-style: groove; */
}

    #panelName {
	position: relative;
	height: 40px;
	width: auto;
	bottom: 0px;
	border-radius: 15px;
	/* border-style: groove; */
}
#panelPostion{
	position: relative;
	height: 350px;
	width: auto;
	top: 0px;
	border-radius: 15px;
	/* border-style: groove; */
	}
#test02{
		text-align: center;
}
</style> 	
<style type="text/css">

        body
        {
            font-family:Arial, Sans-Serif;
            font-size:0.85em;
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
            font-size: 14px;
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
				text-align:center;
            width:300px;
            padding:5px;
            position:relative;
            border:solid 1px #666;
            -moz-border-radius:5px;
            -webkit-border-radius:5px;
        }

        ul input.required 
        {
            border: solid 1px #f00;
        }

#panelName {
	font-weight: bold;
}
</style>
</head>
<body   >

<div class="title" id="TopTitle"><h1><strong>智能制造数据可视化系统</strong></h1>
</div>
<div class="title" id="machineInfo">
<!-- <fieldset class="macinheInfoFiedldset">
  <legend>机床基本信息</legend> -->

 <!-- <ul class="test02">
<li>
<h3>机床IP</h3>
<p>
<input type="text" value="" id="machineIp" name="machineIp" /></p>
</li>
<li>
<h3>机床名称</h3>
<p>
<input type="text" value="" id="machineName" name="machineName" /></p>
</li>
<li>
<h3>操作系统</h3>
<p>
<input type="text" value="" id="machineSystem" name="machineSystem" /></p>
</li>
<li>

</ul> -->

<!--  </fieldset> -->
</div>
<div class="box1" id="box1">
<div class="panelPostion" id="panelPostion"></div>

<div  id="panelName">
<h3 align="center">主轴转速 &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;进给速度&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;主轴负载</h3>
</div>

</div> 

<div class="box" id="box2">


<h2><img src="staticResources/image/machineStatusInfo.jpg" alt="Account information" /></h2>
<ul>
<li>
<h3>机床IP</h3>
<p>
<input type="text" value="" id="machineIp" name="machineIp" /></p>
</li>
<li>
<h3>机床名称</h3>
<p>
<input type="text" value="" id="machineName" name="machineName" /></p>
</li>
<li>
<h3>操作系统</h3>
<p>
<input type="text" value="" id="machineSystem" name="MachineSystem" /></p>
</li>
<li>
<h3>运行时间</h3>
<p>
<input type="text" value="" id="createTime" name="createTime" /></p>
</li>
<li >
<h3>机床运行状态</h3>
<p>
<input type="text" value="" id="runCondition" name="runCondition" /></p>
</li>
<li>
<h3>运行模式</h3>
<p>
<input type="text" value="" id="runMode" name="runMode" /></p>
</li>
<li>
<h3>运行的程序号</h3>
<p>
<input type="text" value="" id="runProgramNum" name="runProgramNum" /></p>
</li>
<li>
<h3>主程序的程序号</h3>
<p>
<input type="text" value="" id="mainProgramNum" name="mainProgramNum" /></p>
</li>
<li>
<h3>运行的NC程序号</h3>
<p>
<input type="text" value="" id="runNcProgramNum" name="runNcProgramNum" /></p>
</li>
<li>
<h3>运行的刀片组号</h3>
<p>
<input type="text" value="" id="runCutterGroupNum" name="runCutterGroupNum" /></p>
</li><li>
<h3>运行的刀片号</h3>
<p>
<input type="text" value="" id="runCutterNum" name="runCutterNum" /></p>
</li>

</ul>


</div> 




<div class="box6" id="box6">
</div> 
<div class="box" id="box8">


<h2>
			<img src="staticResources/image/machineStatusInfo.jpg"
				alt="Account information" />
		</h2>
<ul>
<li >
<h3>伺服轴数量</h3>
<p>
<input type="text" value="" id="controledServoAsisNum" name="controledServoAsisNum" /></p>
</li>
<li>
<h3>主轴数</h3>
<p>
<input type="text" value="" id="spindleNum" name="spindleNum" /></p>
</li>
<li>
<h3>机床报警</h3>
<p>
<input type="text" value="" id="isAlarm" name="isAlarm" /></p>
</li>
<li>
<h3>报警编号</h3>
<p>
<input type="text" value="" id="alarmNum" name="alarmNum" /></p>
</li>
<li>
<h3>报警类型</h3>
<p>
<input type="text" value="" id="alarmClass" name="alarmClass" /></p>
</li>
<li>
<h3>报警信息</h3>
<p>
<input type="text" value="" id="alarmInfo" name="alarmInfo" /></p>
</li><li>
<h3>运行时间</h3>
<p>
<input type="text" value="" id="runTime" name="runTime" /></p>
</li>
<li>
<h3>循环时间</h3>
<p>
<input type="text" value="" id="circleTime" name="circleTime" /></p>
</li>
<li>
<h3>切削时间</h3>
<p>
<input type="text" value="" id="userName" name="user_name" /></p>
</li>
<li>
<h3>CNC类型</h3>
<p>
<input type="text" value="" id="CNCclass" name="CNCclass" /></p>
</li>
<li>
<h3>CNC版本号</h3>
<p>
<input type="text" value="" id="CNCversion" name="CNCversion" /></p>
</li>
</ul>

</div> 

<div class="totalTable" id="box10"></div> 





<script type="text/javascript">
 
/* mini.parse(); */
 
var createTime=0;
var machineName=0 ;         
var machineIp =0;           
var machineSystem =0;       
var machineId=0  ;  

var relativeX=0 ;           
var relativeZ=0 ;           
var relativeC=0 ; 
var relativeV=0 ; 

var absoluteX=0 ;           
var absoluteZ=0 ;           
var absoluteC=0 ;   
var absoluteV =0; 

var machineX=0 ;                    
var machineZ=0 ;            
var machineC=0 ;            
var machineV=0 ;  

var resMovDistanceX=0 ;       
var resMovDistanceZ=0 ;     
var resMovDistanceC=0 ;     
var resMovDistanceV=0 ;   

var spindleName=0 ;         
var spindleNum=0 ;          
var spindleLoad=0 ;         
var spindleSpeed=0 ; 

var servoAsisLoad1=0 ;      
var servoAsisLoad2=0 ;      
var servoAsisLoad3=0 ;      
var servoAsisLoad4 =0;  

var runCondition=0 ;        
var runMode=0;              
var runProgramNum=0 ;       
var mainProgramNum =0;      
var runNcProgramNum =0;     
var runCutterGroupNum=0 ;   
var runCutterNum=0;         
var CNCclass=0;             
var controledServoAsisNum=0;
var CNCversion=0;           
var factFeedSpeed=0 ;       
var isAlarm=0;             
var alarmNum =0;            
var alarmClass=0 ;          
var alarmInfo=0 ;           
var runTime =0;             
var circleTime=0 ;          
var cutTime =0;             



$(function(){
    update();
    setInterval(update, 100); 
   })   
   
    function update(){
   	 
   	 <%-- var machineId = "<%=request.getParameter("machineId")%>"; --%>
   	 
   	$.ajax({
   		type: "get",
   		url:"fanucShowData5505.action?machineId="+"5505",
   		 /* data: { data: json },  */
			success:function(text){

			   /* alert("更新成功"); */
			
	           var msg=$.parseJSON(text);
			   
	           				
	           relativeX=msg.relativeX ;
	           relativeZ=msg.relativeZ ;
	           relativeC=msg.relativeC ;
	           relativeV=msg.relativeV ;
	                    
	           absoluteX=msg.absoluteX ;
	           absoluteZ=msg.absoluteZ ;
	           absoluteC=msg.absoluteC ;
	           absoluteV=msg.absoluteV ;
	                    
	           machineX=msg.machineX ;
	           machineZ=msg.machineZ;
	           machineC=msg.machineC ;
	           machineV=msg.machineV ;
	           
	           /*仪表盘数据  */
	           spindleSpeed=msg.spindleSpeed;
	           spindleLoad=msg.spindleLoad;
	           factFeedSpeed=msg.factFeedSpeed;
	           /* 负载对比数据 */
               servoAsisLoad1=msg.servoAsisLoad1;
               servoAsisLoad2=msg.servoAsisLoad1;
               servoAsisLoad3=msg.servoAsisLoad1;
               servoAsisLoad4=msg.servoAsisLoad1;
	           
	           
	           
	           resMovDistanceX=msg.resMovDistanceX;
	           resMovDistanceZ=msg.resMovDistanceZ;
	           resMovDistanceC=msg.resMovDistanceC ;
	           resMovDistanceV=msg.resMovDistanceV;
	           
	           
	           
	       
	           document.getElementById('createTime').value=msg.createTime;
	           document.getElementById('machineName').value=msg.machineName; 
	           document.getElementById('machineIp').value=msg.machineIp;   
	           document.getElementById('machineSystem').value=msg.machineSystem; 
	           
	           
	           
	        /*    document.getElementById('spindleName').value=msg.spindleName;     *//* 这个目前娶不到，不知道怎么回事 */
	           document.getElementById('spindleNum').value=msg.spindleNum;  
	          /*  mini.get("spindleLoad").setValue(msg.spindleLoad); */
	          /*  mini.get("spindleSpeed").setValue(msg.spindleSpeed); */
	           
	           /* mini.get("servoAsisLoad1").setValue(msg.servoAsisLoad1);
	           mini.get("servoAsisLoad2").setValue(msg.servoAsisLoad2);
	           mini.get("servoAsisLoad3").setValue(msg.servoAsisLoad3);
	           mini.get("servoAsisLoad4").setValue(msg.servoAsisLoad4); */
	           
	           document.getElementById('runCondition').value=msg.runCondition; 
	           document.getElementById('runMode').value=msg.runMode; 
	           document.getElementById('runProgramNum').value=msg.runProgramNum;  
	           document.getElementById('mainProgramNum').value=msg.mainProgramNum;  
	           document.getElementById('runNcProgramNum').value=msg.runNcProgramNum;  
	           document.getElementById('runCutterGroupNum').value=msg.runCutterGroupNum;  
	           document.getElementById('runCutterNum').value=msg.runCutterNum;   
	           document.getElementById('controledServoAsisNum').value=msg.controledServoAsisNum;  
	           document.getElementById('CNCversion').value=msg.CNCversion;  
	           /* mini.get("factFeedSpeed").setValue(msg.factFeedSpeed); */
	      	  document.getElementById('isAlarm').value=msg.isAlarm;   
	      	  document.getElementById('alarmNum').value=msg.alarmNum;   	      	
	      	  document.getElementById('alarmClass').value=msg.alarmClass;  
	      	  document.getElementById('alarmInfo').value=msg.alarmInfo;  
	      	  document.getElementById('runTime').value=msg.runTime;  
	       	  document.getElementById('circleTime').value=msg.circleTime; 
	      	  /* document.getElementById('cutTime').value=msg.cutTime;  *//*  这个目前取不到，不知道怎么回事！！！！*/
	           
			    },
			error : function() {
			    alert("更新失败"); 
			}
   	});
	}

/* ………………………………………………………………………………………………………………………………………………………………左上角的仪表盘的展示……………………………………………………………………………………………………………………………………………………………………………… */
option1 = {
    tooltip : {
        formatter: "{a} <br/>{c} {b}"
    },
    toolbox: {
        show: true,
        feature: {
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    series : [
        {
            name: '进给速度',
            type: 'gauge',
            z: 3,
            min: 0,
            max: 220,
            splitNumber: 11,
			  center: ['47%', '60%'], 
            radius: '70%',
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 3
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 10,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length: 20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            axisLabel: {
                backgroundColor: 'auto',
                borderRadius: 2,
                color: '#eee',
                padding: 3,
                textShadowBlur: 2,
                textShadowOffsetX: 1,
                textShadowOffsetY: 1,
                textShadowColor: '#222'
            },
            title : {
                // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                fontWeight: 'bolder',
                fontSize: 10,
                fontStyle: 'italic'
            },
            detail : {
                // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                formatter: function (value) {
                    value = (value + '').split('.');
                    value.length < 2 && (value.push('00'));
                    return ('00' + value[0]).slice(-2)
                        + '.' + (value[1] + '00').slice(0, 2);
                },
                fontWeight: 'bolder',
                borderRadius: 3,
                backgroundColor: '#444',
                borderColor: '#aaa',
                shadowBlur: 5,
                shadowColor: '#333',
                shadowOffsetX: 0,
                shadowOffsetY: 3,
                borderWidth: 2,
                textBorderColor: '#000',
                textBorderWidth: 2,
                textShadowBlur: 2,
                textShadowColor: '#fff',
                textShadowOffsetX: 0,
                textShadowOffsetY: 0,
                fontFamily: 'Arial',
                width: 50,
                color: '#eee',
                rich: {}
            },
            data:[{value: 40, name: '进给速度m/s'}]
        },
        {
            name: '主轴转速',
            type: 'gauge',
            center: ['15%', '70%'],    // 默认全局居中
            radius: '45%',
            min:0,
            max:7,
           
            endAngle:45,
            splitNumber:7,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 3
                }
            },
            axisTick: {            // 坐标轴小标记
                length:12,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length:20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            pointer: {
                width:3
            },
            title: {
                offsetCenter: [0, '-30%'],       // x, y，单位px
				 // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                fontWeight: 'bolder',
                fontSize: 3,
                fontStyle: 'italic'
            },
            detail: {
                // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                fontWeight: 'bolder'
            },
            data:[{value: 1.5, name: 'x1000 r/min'}]
        },
		  {
            name:'主轴负载',
            type:'gauge',
			 center: ['85%', '70%'], 
			
            title : {
                // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                fontWeight: 'bolder',
                fontSize: 3,
                fontStyle: 'italic'
            } ,
			 radius: '47%',
			    axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 5
                }
            },
            axisTick: {            // 坐标轴小标记
                length:8,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length:10,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            pointer: {
                width:5
            },
            
            detail: {
                // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                fontWeight: 'bolder',
				 fontSize: 3
            },
            detail : {formatter:'{value}%  '},
            data:[{value: 50, name: '主轴负载'}]
        }
    ]
};



		var dom1 = document.getElementById("panelPostion");

		
		var myChart1 = echarts.init(dom1);
		setInterval(function (){
		    option1.series[0].data[0].value =factFeedSpeed ;
		    option1.series[1].data[0].value =spindleSpeed ;
		    option1.series[2].data[0].value =spindleLoad ;
		 
		    myChart1.setOption(option1,true);
		},500);


        /* …………………………………………………………………………………………………………………………………………………………右上角负载的对比展示…………………………………………………………………………………………………………………………………………………………………………………… */    
/* option2 = {
    backgroundColor: '#2c343c',

    title: {
        text: 'Customized Pie',
        left: 'center',
        top: 20,
        textStyle: {
            color: '#ccc'
        }
    },

    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },

    visualMap: {
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0, 1]
        }
    },
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[
                {value:20, name:'伺服轴1负载'},
                {value:30, name:'伺服轴2负载'},
                {value:50, name:'伺服轴3负载'},
                {value:40, name:'伺服轴4负载'},
                {value:45, name:'主轴负载'}
            ].sort(function (a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: '#c23531',
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
};


		
	
       
var dom2 = document.getElementById("box6");

		
		var myChart2 = echarts.init(dom2);
		
		setInterval(function (){
		    option2.series[0].data[0].value =servoAsisLoad1 ;
		    option2.series[0].data[1].value =servoAsisLoad2 ;
		    option2.series[0].data[2].value =servoAsisLoad3 ;
		    option2.series[0].data[3].value =servoAsisLoad4 ;
		    option2.series[0].data[4].value =spindleLoad ; 
		 
		    myChart2.setOption(option2,true);
		},2000);
        */

   

        option2 = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:[] },
            series: [
                {
                    name:'负载对比',
                    type:'pie',
                    selectedMode: 'single',
                    radius: [0, '30%'],

                    label: {
                        normal: {
                            position: 'inner'
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                    	{value:20, name:'伺服轴1负载'},
                        {value:30, name:'伺服轴2负载'},
                        {value:50, name:'伺服轴3负载'},
                        {value:40, name:'伺服轴4负载'},
                        {value:45, name:'主轴负载', selected:true}
                       
                    ]
                },
                {
                    name:'负载对比',
                    type:'pie',
                    radius: ['40%', '50%'],
                    label: {
                        normal: {
                            formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                            backgroundColor: '#eee',
                            borderColor: '#aaa',
                            borderWidth: 1,
                            borderRadius: 4,
                            // shadowBlur:3,
                            // shadowOffsetX: 2,
                            // shadowOffsetY: 2,
                            // shadowColor: '#999',
                            // padding: [0, 7],
                            rich: {
                                a: {
                                    color: '#999',
                                    lineHeight: 18,
                                    align: 'center'
                                },
                                // abg: {
                                //     backgroundColor: '#333',
                                //     width: '100%',
                                //     align: 'right',
                                //     height: 22,
                                //     borderRadius: [4, 4, 0, 0]
                                // },
                                hr: {
                                    borderColor: '#aaa',
                                    width: '100%',
                                    borderWidth: 0.5,
                                    height: 0
                                },
                                b: {
                                    fontSize: 9,
                                    lineHeight: 23
                                },
                                per: {
                                    color: '#eee',
                                    backgroundColor: '#334455',
                                    padding: [2, 4],
                                    borderRadius: 2
                                }
                            }
                        }
                    },
                    data:[
                    	{value:20, name:'伺服轴1负载'},
                        {value:30, name:'伺服轴2负载'},
                        {value:50, name:'伺服轴3负载'},
                        {value:40, name:'伺服轴4负载'},
                        {value:45, name:'主轴负载', selected:true}
                    ]
                }
            ]
        };
        
        var dom2 = document.getElementById("box6");

        		
        		var myChart2 = echarts.init(dom2);
        		
        		setInterval(function (){
        		    option2.series[0].data[0].value =servoAsisLoad1 ;
        		    option2.series[0].data[1].value =servoAsisLoad2 ;
        		    option2.series[0].data[2].value =servoAsisLoad3 ;
        		    option2.series[0].data[3].value =servoAsisLoad4 ;
        		    option2.series[0].data[4].value =spindleLoad ; 
        		    option2.series[1].data[0].value =servoAsisLoad1 ;
        		    option2.series[1].data[1].value =servoAsisLoad2 ;
        		    option2.series[1].data[2].value =servoAsisLoad3 ;
        		    option2.series[1].data[3].value =servoAsisLoad4 ;
        		    option2.series[1].data[4].value =spindleLoad ; 
        		 
        		    myChart2.setOption(option2,true);
        		},2000);
/* ……………………………………………………………………………………………………………………………………坐标对比图的展示……………………………………………………………………………………………………………………………… */
 
 
 
 
 
var weatherIcons = {
		/* 可以定义图片标题 */
    'Sunny': './data/asset/img/weather/sunny_128.png',
    'Cloudy': './data/asset/img/weather/cloudy_128.png',
    'Showers': './data/asset/img/weather/showers_128.png'
};

var seriesLabel = {
    normal: {
        show: true,
        textBorderColor: '#333',
        textBorderWidth: 1
    }
}
option3 = {
    title: {
        text: '坐标值对比图'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['相对坐标', '绝对坐标', '机器坐标', '剩余移动距离']
    },
    grid: {
        left: 100
    },
    toolbox: {
        show: true,
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'value',
        name: 'Days',
        axisLabel: {
            formatter: '{value}'
        }
    },
    yAxis: {
        type: 'category',
        inverse: true,
        data: ['X', 'Z','C','V'],
        axisLabel: {
            formatter: function (value) {
                return '{' + value + '| }\n{value|' + value + '}';
            },
            margin: 20,
            rich: {
                value: {
                    lineHeight: 30,
                    align: 'center'
                },
                X: {
                    height: 40,
                    align: 'center',
                    backgroundColor: {
                        image: weatherIcons.Sunny
                    }
                },
                Z: {
                    height: 40,
                    align: 'center',
                    backgroundColor: {
                        image: weatherIcons.Showers
                    }
                },
                C: {
                    height: 40,
                    align: 'center',
                    backgroundColor: {
                        image: weatherIcons.Showers
                    }
                },
                V: {
                    height: 40,
                    align: 'center',
                    backgroundColor: {
                        image: weatherIcons.Showers
                    }
                }
            }
        }
    },
    series: [
        {
            name: '相对坐标',
            type: 'bar',
            data: [165, 170,200,200],
            label: seriesLabel,
            markPoint: {
                symbolSize: 1,
                symbolOffset: [0, '50%'],
                label: {
                   normal: {
                        formatter: '{a|{a}\n}{b|{b} }{c|{c}}',
                        backgroundColor: 'rgb(242,242,242)',
                        borderColor: '#aaa',
                        borderWidth: 1,
                        borderRadius: 4,
                        padding: [4, 10],
                        lineHeight: 26,
                        // shadowBlur: 5,
                        // shadowColor: '#000',
                        // shadowOffsetX: 0,
                        // shadowOffsetY: 1,
                        position: 'right',
                        distance: 20,
                        rich: {
                            a: {
                                align: 'center',
                                color: '#fff',
                                fontSize: 18,
                                textShadowBlur: 2,
                                textShadowColor: '#000',
                                textShadowOffsetX: 0,
                                textShadowOffsetY: 1,
                                textBorderColor: '#333',
                                textBorderWidth: 2
                            },
                            b: {
                                 color: '#333'
                            },
                            c: {
                                color: '#ff8811',
                                textBorderColor: '#000',
                                textBorderWidth: 1,
                                fontSize: 22
                            }
                        }
                   }
                }
            }
        },
        {
            name: '绝对坐标',
            type: 'bar',
            label: seriesLabel,
            data: [-150, 110,100,200]
        },
        {
            name: '机器坐标',
            type: 'bar',
            label: seriesLabel,
            data: [-220, 82,200,200]
        },
        {
            name: '剩余移动距离',
            type: 'bar',
            label: seriesLabel,
            data: [-220,63,300,100]
        }
    ]
};


var dom3 = document.getElementById("box10");

		
		var myChart3 = echarts.init(dom3);
		setInterval(function (){
		    option3.series[0].data=[relativeX,relativeZ,relativeC,relativeV] ;
		    option3.series[1].data=[absoluteX,absoluteZ,absoluteC,absoluteV] ;
		    option3.series[2].data=[machineX,machineZ,machineC,machineV];
		    option3.series[3].data= [resMovDistanceX,resMovDistanceZ,resMovDistanceC,resMovDistanceV] ; 
		  
		    
		   /*  option.series[1].data[1].value =servoAsisLoad2 ;
		   
		    
		    option.series[2].data[2].value =servoAsisLoad3 ;
		    
		    
		    option.series[3].data[3].value =servoAsisLoad4 ; */
		 
		    myChart3.setOption(option3,true);
		},3000);





/* ………………………………………………………………………………………………………………………………………………………… */
 
	  
   </script>

 
</body>
</html>

