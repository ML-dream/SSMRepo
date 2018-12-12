<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
  <head>
  	
  </head>
  <body background="#dafec5">

  <table>
    <tr ><h2 >该物料还未添加工艺路线，请选择其工艺路线类型：</h2></tr>
    <tr>
    	<td style="font-size:20px;" valign="bottom">
    		<a href="BaseServlet?meth=Goaodetail&pathTo=aodetail&productType=${productType}&productId=${productId}&issueNum=${issueNum}">
    		<font color="#ff0000">装配工艺路线</font></a>
    	</td>
    	<td>&nbsp;&nbsp;</td>
    	<td>&nbsp;&nbsp;</td>
    	<td style="font-size:20px;" valign="bottom">
    		<a href="BaseServlet?meth=Gofodetail&pathTo=fodetail&productType=${productType}&productId=${productId}&issueNum=${issueNum}">
    		<font color="#008080">制造工艺路线</font></button>
    	</td>
    	<td>&nbsp;&nbsp;</td>
    	<td>&nbsp;&nbsp;</td>
    	<td style="font-size:20px;" valign="bottom">
    		<a href="'BaseServlet?meth=ProductInfoServlet&pathTo=foHeader&orderId=&productId=${productId}&FProductId='+node.pid">
    		<font color="#008080">工艺路线头</font></button>
    	</td>
    </tr>
   </table>
  </body>
  <script>
  		function goFO(){
  			window.location.href = "fodetail.jsp?productType="+Request['productType'];
  		}
  		
  		function GetRequest() { 
        	var url = location.search; //获取url中"?"符后的字串 
        	var theRequest = new Object(); 
        	if (url.indexOf("?") != -1) { 
        		var str = url.substr(1); 
        		strs = str.split("&"); 
        		for(var i = 0; i < strs.length; i ++) { 
        			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
        		} 
        	} 
        	return theRequest; 
        } 

        var Request = new Object(); 
        Request = GetRequest();
        //mini.get("machineId").value = Request['machineId'];
        alert(Request['productId']);
        document.getElementById("fo").href="fodetail.jsp?productType="+Request['productType']+"&productId="+Request['productId'];
  </scirpt>
</html>