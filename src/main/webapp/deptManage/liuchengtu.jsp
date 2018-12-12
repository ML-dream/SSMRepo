<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html  lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="format-detection" content="telephone=no" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <meta name="viewport" content="width=device-width,initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />
        <meta name="description" content="">
        <meta name="keywords" content="">
        <title>Echarts图表统计结果</title>
      
<style type="text/css">  
div#row1 { color: blue;display: inline }  
div#row2 { color: red;display: inline }  

</style> 

    </head>
    <body>
  <!-- <div class="row"> -->
    
  	 <div  class="col-md-6" style="width:800px">     
		<div id="div1"  style="width:100%;height:170px;"></div> 
	</div> 
	<!-- <div class="col-md-6" style="width:50%" >
		<div id="div2"  style="width:100%;height:170px;"></div>  

	</div>> -->
<!-- </div>   -->
<!--     <div id="div1" style="display:inline;float:left;width: 400px;height:170px;"></div>

    <div id="div2" style="display:inline;float:left;width: 400px;height:170px;"></div> -->


            
               
            
          
       
    </body>
    <script type="text/javascript" src="<%=path %>/js/echarts.js"></script>
    <script type="text/javascript" src="<%=path %>/js/echarts.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery-2.1.4.min.js"></script>
    <script>
    $(document).ready(function(){
      //获取饼状图容器 并 初始化echarts实例
    var div1 = echarts.init(document.getElementById('div1'));
 /*    var div2 = echarts.init(document.getElementById('div2')); */
    
    
    //饼状图 配置
    var option = {
    	    title : {
    	        text: '设备信息流程',
    	        subtext: ''
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{b}: {c}"
    	    },
    	    toolbox: {
    	        show : true,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: true, readOnly: false},
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : false,

    	    series : [
    	        {
    	            name:'树图',
    	            type:'tree',
    	            orient: 'horizontal',  // vertical horizontal
    	            rootLocation: {x: 100, y: '60%'}, // 根节点位置  {x: 'center',y: 10}
    	            nodePadding: 20,
    	            symbol: 'circle',
    	            symbolSize: 40,
    	            itemStyle: {
    	                normal: {
    	                    label: {
    	                        show: true,
    	                        position: 'inside',
    	                        textStyle: {
    	                            color: '#000000',
    	                            fontSize: 15,
    	                            fontWeight:  'bolder'
    	                        }
    	                    },
    	                    lineStyle: {
    	                        color: '#000',
    	                        width: 1,
    	                        type: 'broken' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
    	                    }
    	                },
    	                emphasis: {
    	                    label: {
    	                        show: true
    	                    }
    	                }
    	            },
    	            data: [
    	                {
    	                    name: '计划选购',
    	                    value: 6,
    	                    symbolSize: [90, 70],
    	                    symbol: 'heart',
    	                    itemStyle: {
    	                        normal: {
    	                            label: {
    	                                show: false
    	                            }
    	                        }
    	                    },
    	                    children: [
    	                        {
    	                            name: '开箱验收',
    	                            value: 4,
    	                            symbol: '',
    	                            itemStyle: {
    	                                normal: {
    	                                    label: {
    	                                        show: false
    	                                    }
    	                                }
    	                            },
    	                            symbolSize: [60, 60],
    	                            children: [
    	                                {
    	                                    name: '安装调试',
    	                                    symbol: 'circle',
    	                                    symbolSize: 80,
    	                                    value: 4,
    	                                    itemStyle: {
    	                                        normal: {
    	                                            color: '#fa6900',
    	                                            label: {
    	                                                show: true,
    	                                                position: 'right'
    	                                            },
    	                                            
    	                                        },
    	                                        emphasis: {
    	                                            label: {
    	                                                show: false
    	                                            },
    	                                            borderWidth: 0
    	                                        }
    	                                    }
    	                                }
    	                            ],
    	                        },
    	                       
    	                       
    	                    ]
    	                }
    	            ]
    	        }
    	    ]
    	};
                    


    // 使用刚指定的配置项和数据显示图表。
    div1.setOption(option);
  /*   div2.setOption(option); */
});
    </script>
</html>