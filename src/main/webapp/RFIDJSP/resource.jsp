<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源维护</title>
<link href="../css/person.css" type=text/css rel=stylesheet>
</head>
<body>
<form action="" name="itemform">

 <input type = "hidden" name = "dotype" value = "itemform">

<div class="page_title">物料维护</div>
    <table  class="query_form_table"  align="center"  border="0" style="word-break:break-all;float: left;" width="40%">
	<tr>
	   <th>物料号</th>
	   <td><input type=text id="item_id" name="item_id"  size=40 >
	    <span class="red_star">*</span></td></tr> 	 
	 <tr>
	    <th>批次号</th>
	    <td><input type=text name="product_id"  size=40 t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
	    <span class="red_star">* </span>
	    <font color="#008000">填写数字</font></td></tr>
    <tr>
       <th>物料名称</th>
       <td><input type=text id="item_name" name="item_name" size=40 ></td></tr>   
    <tr>
       <td align="left" rowspan="1" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="修 改" onclick="return toservlet();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="重 置" onclick="return toservlet();">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="提 交" onclick="return toservlet();"></td></tr>
      
  </table>
</form>

<form action="" name="machform">
 <input type = "hidden" name = "dotype" value = "machform">
<div class="page_title" style="clear: both;">设备绑定</div>
  <table  class="query_form_table"  align="center"  border="0" style="word-break:break-all;float: left;" width="40%">
	<tr>
	    <th>机床编号</th>
	    <td><input type=text id="machineid" name="machineid"  size=40 ><span class="red_star">*</span></td>	 
	    <td><button value="选择机床" name="选择机床" onclick="">选择机床</button></td>
	</tr> 
    <tr>
       <th>机床名称</th>
       <td><input type=text name="machinename"  size=40 />
       <span class="red_star">*</span></td></tr>
    <tr>
       <th>操作工人</th>
       <td><input type=text id="worker" name="worker"  size=40 >
       <span class="red_star">*</span></td></tr>   
    <tr>
       <th>入口RFIDIP</th>
       <td><input type=text id="RFID1" name="RFID1"  size=40 >
       <span class="red_star">*</span></td></tr>      
    <tr>  
       <th>出口RFIDIP</th>
       <td><input type=text id="RFID2" name="RFID2"  size=40 >
       <span class="red_star">*</span></td></tr>      
    <tr>
       <td align="left" rowspan="1" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="修 改" onclick="return toservlet2();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="重 置" onclick="return toservlet2();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="提 交" onclick="return toservlet2();"></td></tr>
  </table>
</form>

</body>
<script language="javascript" type="text/javascript">
  function toservlet() {
    if (document.itemform.item_id.value=="" ){
    alert("请输入物料号！");
    document.itemform.item_id.focus();
    return ;
    }
	if (document.itemform.product_id.value=="" ){
	alert("请输入批次号！");
	document.itemform.product_id.focus();
	return ;
	}
    document.itemform.action = "../add_res";
	document.itemform.submit();
  }	
function toservlet2() {
    if (document.machform.machineid.value=="" ){
    alert("请输入机床编号！");
    document.machform.machineid.focus();
    return ;
    }
	if (document.machform.machinename.value=="" ){
	alert("请输机床名称 ！");
	document.machform.machinename.focus();
	return ;
	}
	if (document.machform.worker.value=="" ){
	alert("请输入操作工人！");
	document.machform.worker.focus();
	return ;
	}
	if (document.machform.RFID1.value=="" ){
	alert("请输入入口阅读器IP！");
	document.machform.RFID.focus();
	return ;
	}
	if (document.machform.RFID2.value=="" ){
	alert("请输入出口阅读器IP！");
	document.machform.RFID.focus();
	return ;
	}
    document.machform.action = "../add_res";
	document.machform.submit();
  }	  
  
</script>
</html>