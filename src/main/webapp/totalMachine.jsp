
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>边框样式</title>   



<style> 
/* .container,.container1,.container2{ float:left}  没有用*/
#container {

     width: 300px;
   border-style:groove;
    height: 300px;    

    float:left;     
}
#container1 {
border-style:groove;
     width: 300px;
  border-style:groove;
    height: 300px;    

    float:left;     
}
#container2 {
border-style:groove;
border-style:groove;
    width: 300px;
  
    height: 300px;    
      float:left;
    }
    
    #wrapper{ position:relative; width:200px; height:300px; border-radius:15px;left:0px; top:10px;border-style:groove;background:url() no-repeat;background-size:cover;} 
/* position:relative是绝对定位关键，父级设置 */ 
.box1{ position:relative; width:200px; height:300px; border-radius:15px;left:10px; top:10px;border-style:groove;} 
.box2{position:relative; width:200px; height:300px; border-radius:15px;left:10px;top:30px; border-style:groove;} 
.box3{position:absolute; width:200px; height:300px; border-radius:15px;left:230px;top:20px;border-style:groove;} 
.box4{position:absolute; width:200px; height:300px; border-radius:15px;left:230px;top:340px;border-style:groove;} 
/* 三个小盒子使用position:relative同时设置宽度 高度 left right top bottom实现布局与准确定位 */ 


</style> 	

</head>
<body >



</div> 
<div class="box2">box2</div> 
<div class="box3">box3</div> 
<div class="box4">box4</div>
   	

 
</body>
</html>

