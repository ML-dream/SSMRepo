<%@ page language="java" import="java.util.*,com.wl.forms.Tree" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <script language=javascript src="./dtree.js"></script>
  <link rel="stylesheet" href="../dtree.css" type="text/css"></link>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body onload="buildtree();">      <!--点击则创建树  --><!-- 没看到dtree里面有创建树啊？？？ -->
   <div>
      <a href="javascript: d.openAll();"> 全展</a> | 
	  <a href="javascript: d.closeAll();">全关</a> |
	  <a href="javascript: location.reload();">刷新</a>
	  <a href="/test/workplan.do" >tiaozhuan</a>
   </div>
   
   <div>
     
   
   </div>
   
   
   
   
   
   <div id ="dtree" class ="dtree" >
   <script type="text/javascript">
		d = new dTree('d','','dtree');
		function buildtree(){	
			d.add('top','x','EBOM结构树',null,null,null,'','','');
	        d.add('jsq1','top','jsq【零件1】','toworkplan.jsp','零件1','list','','','','ec');
	        d.add('jsq2','top','jsq【零件2】','..','零件2','list','','','','ec');
	        d.add('jsq3','top','jsq【零件3】','..','零件3','list','','','','ec');
	        d.add('jsq4','top','jsq【零件4】','..','零件4','list','','','','ec');
	        
	        d.add('xt1','jsq1','xt[工序集11]','..','工序集','list','');
	        d.add('xt2','jsq1','xt[工序集12]','..','工序集','list','');
	        d.add('xt3','jsq1','xt[工序集13]','..','工序集','list','');
	        d.add('xt4','jsq1','xt[工序集14]','..','工序集','list','');
	        
	        d.add('xt21','jsq2','xt[工序集]21','..','工序集21','list','');
	        d.add('xt22','jsq2','xt[工序集]22','..','工序集22','list','');
	        d.add('xt23','jsq2','xt[工序集]23','..','工序集23','list','');
	        d.add('xt24','jsq2','xt[工序集]24','..','工序集24','list','');
	        
	        d.add('xt31','jsq3','xt[工序集]31','..','工序集31','list','');
	        d.add('xt32','jsq3','xt[工序集]32','..','工序集32','list','');
	        d.add('xt33','jsq3','xt[工序集]33','..','工序集33','list','');
	        d.add('xt34','jsq3','xt[工序集]34','..','工序集34','list','');
	        
	        d.add('xt41','jsq4','xt[工序集]41','..','工序集41','list','');
	        d.add('xt42','jsq4','xt[工序集]42','..','工序集42','list','');
	        d.add('xt43','jsq4','xt[工序集]43','..','工序集43','list','');
	        d.add('xt44','jsq4','xt[工序集]44','..','工序集44','list','');
	        
	        
	        d.add('z1','xt1','z【工序】','goto.html','工序集1','','','','','ec');
            d.add('y2','xt1','y【工序】','....','工序集2','','','','','ec');
            d.add('a3','xt1','a【工序】','....','工序集3','','','','','ec');//x不能作为ID
            
            d.add('z4','xt2','z4【工序】','....','工序集4','','','','','ec');
            d.add('z5','xt3','z5【工序】','....','工序集5','','','','','ec');
            d.add('z6','xt4','z6【工序】','....','工序集6','','','','','ec');
            
            d.add('z7','xt21','z7【工序】','....','工序集7','','','','','ec');
            d.add('z8','xt22','z8【工序】','....','工序集8','','','','','ec');
            d.add('z9','xt23','z9【工序】','....','工序集9','','','','','ec'); 
            d.add('z10','xt24','z10【工序】','....','工序集10','','','','','ec');
            
            d.add('z11','xt31','z11【工序】','....','工序集11','','','','','ec');
            d.add('z12','xt32','z12【工序】','....','工序集12','','','','','ec');
            d.add('z13','xt33','z13【工序】','....','工序集13','','','','','ec');
            d.add('z14','xt34','z14【工序】','....','工序集14','','','','','ec');
            
            d.add('z15','xt41','z15【工序】','....','工序集15','','','','','ec');
            d.add('z16','xt42','z16【工序】','....','工序集16','','','','','ec');
            d.add('z17','xt43','z17【工序】','....','工序集17','','','','','ec');
            d.add('z18','xt44','z18【工序】','....','工序集18','','','','','ec');

            
			d.draw();
		}	
		
	</script>
   </div>
  </body>  
</html>
