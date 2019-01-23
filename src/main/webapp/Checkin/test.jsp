<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户信息</title>

    
<script src="resources/scripts/boot.js" type="text/javascript"></script>

  </head>
  
  <body>


                              
            <div name="timeYmd" field="timeYmd" width="100" allowSort="true" dateFormat="yyyy-MM-dd">日期
                <input property="editor" class="mini-datepicker" style="width:100%;"/>
            </div>    
         

       

      <script type="text/javascript">
     
        
        mini.parse();


  </body>
</html>
