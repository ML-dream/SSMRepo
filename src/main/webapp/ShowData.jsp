<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
 
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!-- miniui.js -->
      	<script type="text/javascript" src="staticResources/js/boot.js"></script>
		<script type="text/javascript" src="staticResources/js/echarts.js"></script>
		<script type="text/javascript" src="staticResources/js/echarts.min.js"></script>
		<script type="text/javascript" src="staticResources/js/jquery.min.js"></script>
		<script type="text/javascript" src="staticResources/js/miniui.js"></script>
		<script type="text/javascript" src="staticResources/js/miniui/themes/default/miniui.css"></script>
		<script type="text/javascript" src="staticResources/js/miniui/themes/icons.css"></script>
		
		
		
	<style> 
/* .container,.container1,.container2{ float:left}  没有用*/
#container {
     width: 180px;
   
    height: 120px;    

    float:left;     
}
#container1 {
     width: 220px;
  
    height: 120px;    

    float:left;     
}
#container2 {
    width: 180px;
  
    height: 120px;    
      float:left;
    }
</style> 	

		
   
    <title>机床数据采集的实时显示</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
   	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;"> 
  	 <a class="mini-button" iconCls="icon-save" plain="false"  onclick="lookMachineInfo()">查看设备详细记录</a>
  	 <a class="mini-button" iconCls="icon-save" plain="false"  onclick="max()">最大化测试</a>

  	 </div>
  	 
  	 
  	 


	<fieldset style="width: 100%;" align="center">
		<legend>
			实时信息显示
		</legend>


   <div id="grid1" class="cals" style="padding:2px;border-top:50;border-left:10;position:relative;border-right:0;width:100%;height:150px;">
   		 <div id="container" style="height:150%;margin:0px auto;margin-top:0px;margin-left:220px;"></div>
   		  <div id="container1" style="height:150%;"></div>
   		   <div id="container2" style="height:150%;"></div>
   		   
   	<fieldset style="width: 100%;" align="center">
		<legend>
			机床基本信息
		</legend>
  <div id="add1" style="background:#EFEFEF" >
		<form name="form1" id="form1" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#EFEFEF">
			<tr>
								<th>机床名称</th>
			    	<td><input id="machineName"  name="machineName" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">机床IP</th>
			    	<td><input id="machineIp"  name="machineIp" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">报警编号</th>
			    	<td><input id="textIndex"  name="textIndex" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>数控系统</th>
			    	<td><input id="nckType"  name="nckType" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">累计开机时间</th>
			    	<td><input id="poweronTime"  name="poweronTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">最大轴数</th>
			    	<td><input id="numMachAxes"  name="numMachAxes" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
   
			</table>
		</form>
		</div>
		
		<fieldset style="width: 100%;" align="center">
		<legend>
			机床加工信息
		</legend>
  <div id="add2" style="background:transparent" >
		<form name="form2" id="form2" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#FFFFFF">
			<tr>
					<th>工件数</th>
			    	<td><input id="totalParts"  name="totalParts" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">指定主轴转速</th>
			    	<td><input id="cmdSpeed"  name="cmdSpeed" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">报警信息</th>
			    	<td><input id="fillText"  name="fillText" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>程序状态</th>
			    	<td><input id="progStatus"  name="progStatus" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">程序段序号</th>
			    	<td><input id="blockNoStr"  name="blockNoStr" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">指定进给倍率</th>
			    	<td><input id="cmdSpeedRel"  name="cmdSpeedRel" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
   
			</table>
		</form>
		</div>
		
		
		<fieldset style="width: 100%;" align="center">
		<legend>
			参数监控
		</legend>
  <div id="add3" style="background:#EFEFEF" >
		<form name="form3" id="form3" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#EFEFEF">
			<tr>
					<th>主轴数量</th>
			    	<td><input id="numSpindles"  name="numSpindles" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">主轴运行方式</th>
			    	<td><input id="opMode"  name="opMode" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">有效通道数量</th>
			    	<td><input id="numChannels"  name="numChannels" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>常规报警数</th>
			    	<td><input id="numAlarms"  name="numAlarms" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">刀具啮合时间</th>
			    	<td><input id="cuttingTime"  name="cuttingTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">主轴负载率</th>
			    	<td><input id="driveLoad"  name="driveLoad" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
   
			</table>
		</form>
		</div>
      
      
      
      
 <script type="text/javascript">
 
		var dom = document.getElementById("container");
		var dom1 = document.getElementById("container1");
		var dom2 = document.getElementById("container2");
		var myChart = echarts.init(dom);
		var myChart1 = echarts.init(dom1);
		var myChart2 = echarts.init(dom2);
		//var testZhi = 0 ;
		var speedOvr = 0;
        var actSpeed = 0;
        var actSpeedRel=0;
		
		var app = {};
		option = null;
		option = {
			    tooltip : {//这个是用来编辑鼠标悬停上然后显示相关内容的
			        formatter: "{a} <br/>{b} : {c}%",
			       	textStyle: {//凡是tyextStyle就是用来调整文字格式 的
			                fontSize: 1
			              
			            }
			    },
			    toolbox: {//用来编辑工具箱的
			        feature: {
			        restore: {},
			        saveAsImage: {}
			        }
			    },
			    grid: {//如果有坐标用来画网格，同时调整图像的位置，此处仪表盘没用
			    	x:10,
			    	y:10
			    	},
			    series: [//很重要，对于仪表盘来说可以调整几乎所有的数据相关，细节部分的显示
			        {
			            name: '',//图标的名字，在悬停时进行显示
			            type: 'gauge',//调用不同的图标函数
			            radius: '90%',//调整表的大小
			            detail: {formatter:'{value}%'},//detail，用处很大，控制实时显示的数据格式，大小等等重要！！！
			            data: [{value: 100, name: '完成率0'}],//这个用来显示实时现实的数值内容！并且name后面显示单位，但是
			            //可以用来显示其他的任意的，比如显示图标的名字
			            
			            min:0,//显示仪表盘的显示范围！
			            max:500,
			            splitNumber:5,//显示将上面的这个范围划分为多大的小范围，就是刻度
			            axisLine: {            // 坐标轴线，用来控制仪表盘外边的盘的边的！粗细！
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    width: 5
			                }
			            },
			            axisTick: {            // 坐标轴小标记，控制每个大刻度的伸出长度！！！！
			                length :15,        // 属性length控制线长
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: 'auto'
			                }
			            },
			            axisLabel: {            // 坐标轴小标记，控制小刻度的伸出长度！！！！
			                textStyle: {       // 属性lineStyle控制线条样式
			                	fontSize: 5
			                }
			            },
			            detail:{//这个就是之前那个detail，可以调整的东西很多，在仪表盘中我只用来调增显示的数值大小
			            	textStyle: {  
			            	
			                   	 fontSize: 5
			                			}
			            	
			            },
			            splitLine: {           // 分隔线的！！粗细！！！
			                length :20,         // 属性length控制线长
			                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			                    color: 'auto'
			                }
			            },
			            title : {
			            	 show : true,
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontWeight: 'bolder',
			                    fontSize: 5,
			                    fontStyle: 'italic'
			                			}
			            		}
			            
			            
			            
			        }
			    ]
			};
		option1 = {
			    tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    toolbox: {
			        feature: {
			        restore: {},
			        saveAsImage: {}
			        }
			    },
			    grid: {
			    	x:10,
			    	y:10
			    	},
			    series: [
			        {
			            name: '业务指标',
			            type: 'gauge',
			            radius: '90%',
			            detail: {formatter:'{value}%'},
			            data: [{value: 50, name: '完成率1'}],
			            
			            min:0,
			            max:220,
			            splitNumber:11,
			            axisLine: {            // 坐标轴线
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    width: 10
			                }
			            },
			            axisTick: {            // 坐标轴小标记
			                length :15,        // 属性length控制线长
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: 'auto'
			                }
			            },

			            detail:{
			            	textStyle: {  
			            	
			                   	 fontSize: 5
			                			}
			            	
			            },
			            splitLine: {           // 分隔线
			                length :20,         // 属性length控制线长
			                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			                    color: 'auto'
			                }
			            },
			            title : {
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontWeight: 'bolder',
			                    fontSize:5,
			                    fontStyle: 'italic'
			                			}
			            		}
			            
			            
			            
			        }
			    ]
			};
		option2 = {
			    tooltip : {//这个是控制悬浮窗的文字显示形式等等
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    toolbox: {
			        feature: {
			        restore: {},
			        saveAsImage: {}
			        }
			    },
			    grid: {
			    	x:10,
			    	y:10
			    	},
		        
			    series: [
			        {
			            name: '业务指标',
			            type: 'gauge',
			            radius: '90%',
			            detail: {formatter:'{value}%'},
			            data: [{value: 5, name: '完成率2'}],
			            
			            min:0,
			            max:550,
			            splitNumber:5,
			            axisLine: {            // 坐标轴线
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    width: 10
			                }
			            },

			            detail:{
			            	formatter:'{value}%',
			            	textStyle: {  
			                   	 fontSize: 5
			                			}
			            	
			            },
			            axisTick: {            // 坐标轴小标记
			                length :15,        // 属性length控制线长
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: 'auto'
			                }
			            },
			            title : {
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontWeight: 'bolder',
			                    fontSize: 3,
			                    fontStyle: 'italic'
			                			}
			            		},
			            splitLine: {           // 分隔线
			                length :20,         // 属性length控制线长
			                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			                    color: 'auto'
			                }
			            }
			            
			            
			            
			        }
			    ]
			};

			setInterval(function () {
			    option.series[0].data[0].value = speedOvr;//testZhi//(Math.random() * 100).toFixed(2) - 0;
			    option1.series[0].data[0].value = actSpeed;//(Math.random() * 100).toFixed(2) - 0;
			    option2.series[0].data[0].value = actSpeedRel; //(Math.random() * 100).toFixed(2) - 0;
			    myChart.setOption(option, true);
			    myChart1.setOption(option1, true);
			    myChart2.setOption(option2, true);
			},2000);
		
		
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		    myChart1.setOption(option1, true);
		    myChart2.setOption(option2, true);
		}
		       </script>
    
    
    
    
    
    
    
    
    
   <script>
   		mini.parse();
  
	    
	   $(function(){
	     update();
         //setInterval(update, 300); 
	    })   

	    function onclick(){
          /*   mini.get("piece_barcode").setValue("niuhfy"); */
            update();
	    }
	    /*  update(); */
   /*       setInterval(update, 3000); */
	     function update(){
	    	 
	    	 var machineId = "<%=request.getParameter("machineId")%>";
	    	 
        	$.ajax({
        		type: "get",
        		url:"dataFeedback.action?machineId="+"5504",
        		 /* data: { data: json },  */
    			success:function(text){
    				
				   /* alert("更新成功"); */
		           var msg=$.parseJSON(text);
		           mini.get("machineName").setValue(msg.machineName);
		           mini.get("machineIp").setValue(msg.data.createTime);//("192.168.0.1");
		           /* mini.get("numSpindles").setValue(msg.data.aaLoad1);
		           mini.get("numChannels").setValue(msg.data.aaLoad2);
		           mini.get("machineName").setValue(msg.data.aaLoad3); */
		           mini.get("textIndex").setValue(msg.data.textIndex);
		           mini.get("nckType").setValue(msg.data.nckType);
		           mini.get("poweronTime").setValue(msg.data.poweronTime);
		           mini.get("numMachAxes").setValue(msg.data.numMachAxes);
		           mini.get("totalParts").setValue(msg.data.totalParts);
		           mini.get("cmdSpeed").setValue(msg.data.cmdSpeed);
		           mini.get("fillText").setValue(msg.data.fillText);
		           mini.get("progStatus").setValue(msg.data.progStatus);
		           mini.get("blockNoStr").setValue(msg.data.aaMm1);
		           mini.get("cmdSpeedRel").setValue(msg.data.cmdSpeedRel);
		           mini.get("numSpindles").setValue(msg.data.numSpindles);
		           mini.get("opMode").setValue(msg.data.opMode);
		           mini.get("numChannels").setValue(msg.data.numChannels);
		           mini.get("numAlarms").setValue(msg.data.numAlarms);
		           mini.get("cuttingTime").setValue(msg.data.cuttingTime);
		           mini.get("driveLoad").setValue(msg.data.driveLoad);
		           //mini.get("opMode").setValue(msg.data.opMode);
		           
		           //testZhi = msg.data.aaLoad1;
		           speedOvr = msg.data.speedOvr;
		           actSpeed = msg.data.actSpeed;
		           actSpeedRel=msg.data.actSpeedRel;
    			    },
    			error : function() {
    			    /* alert("更新失败"); */
				}
        	});
		}
	    
	    
	     /* ^^^^^^^^^………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………… */
	     

	     
	     /*  …………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………*/
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	
	    
	    function ondEdit(id,isReaded){
	        window.location.href="NoticeDetailServlet?id=" + id+"&isReaded="+isReaded;
		}
   		
   		
   		function refresh(){
   			
			
			window.location.href=window.location.href;
		}
   		
		function lookMachineInfo(){
   			
			 var machineId = "<%=request.getParameter("machineId")%>";
			window.location.href="dataCollection/showData.jsp?machineId="+machineId;
		}
		function max(){
   			
			 var machineId = "<%=request.getParameter("machineId")%>";
			window.location.href="mainindex.jsp?machineId="+machineId;
		}
   		
   		
   		
		
   		function getForm(flag){
			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.isAlive=flag;
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditDeptSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}
   		
   		
   	
   </script>
  </body>
</html>