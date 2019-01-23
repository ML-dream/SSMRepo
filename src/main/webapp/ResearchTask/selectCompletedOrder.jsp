<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>待处理外协单</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
	<!--
	table {
	    table-layout:fixed;
	    word-break: break-all;
	} 
	-->
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
    <script src="resources/scripts/boot.js" type="text/javascript"></script>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>

	
	<!-- <div id="win1" class="mini-window" title="查找" style="width:550px;height:100px;" collapseOnTitleClick="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" expanded="false"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >dddddddddddd -->
    	<form id="form0">
	    <table >
	   		<tr>
	         <!--  <td>开始时间：</td>
	          <td><input id="bday" class="mini-datepicker" width="100"  allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td>
	          <td>结束时间：</td>
	          <td><input id="eday" class="mini-datepicker"   width="100" allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td> -->
	   <!--        <td width="60px" align="right">接收人：</td>
	          <td><input id="creater" name="creater" class="mini-buttonedit" width="100" showClose="true" oncloseclick="onCloseClick('creater')"
            		onbuttonclick="onButtonEdit" textName="worker" required="false" value="" text="" onvaluechanged="loadgrid"  allowInput="false"/>
	          </td> -->
	          
	       </tr>
	       <tr>
	          <td>订单状态：</td>
	          <td><input id="bookStatus" name="bookStatus" class="mini-combobox" width="100" textName="" textField="text" valueField="id"
  				url="data/bookStatus.txt"  allowInput="false" showNullItem="true" nullItemText="请选择..."  onvaluechanged="loadgrid"/>
	          </td>
	   		  <td align="right">客户：</td>
	          <td colspan="2"><input id="companyName" name="companyId" class="mini-buttonedit" width="100%" showClose="true" oncloseclick="onCloseClick('customer')"
            		onbuttonclick="onButtonEdit2" textName="companyName" required="false" value="" text="" onvaluechanged="loadgrid"  allowInput="false"/>
	          </td>
	          <td><input value="查找" type="button" onclick="loadgrid()" style="width:50px;"/></td>
	   		</tr>
	   		<input id="key" name="key"  class="mini-textbox" required="true" value="1" visible="false"/>
	   		
	   	</table>
	   </form>
<!-- 	</div> -->
	
</br></br>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:85%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="mybookOrder.action" allowCellSelect="true" allowCellEdit="true">
        <div property="columns">
            <div type="indexcolumn" width="20"></div>
            <div name="action" width="10" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">审核
            </div>
        <div field="orderId" width="50" allowSort="true" headerAlign="center" align="center">订单编号
            </div> 
         
            <div field="companyName" width="100" headerAlign="center" align="center">客户名称
            </div>
            <div field="orderName" width="100" headerAlign="center" align="center">订单名称
            </div>
            <div field="connector" width="50" headerAlign="center" align="center">联系人
            </div>
            <div field="connectorTel" width="60" headerAlign="center" align="center" >联系人电话
            </div>
            <div field="createTime" width="60" headerAlign="center"  dateFormat="yyyy-MM-dd HH:mm:ss" align="center" >创建时间
            </div>
            <div field="bookStatus" width="20" headalign="center"  renderer="onGenderRenderer">状态
              
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function loadgrid(){
	    	
	    	var form=new mini.Form("#form0");
	    	var data1 =form.getData();
	    	grid.load(data1);
	    	
//			 alert(data1);
/*	    	 var bookStatus = mini.get("bookStatus").getValue();
	    	 var companyName = mini.get("companyName").getValue(); */
//	    	 var data=mini.encode(data1);
	    	
	    	
	    	/* $.ajax({
	    		url:"AuditingBookingOrder.action",
	    		type:"post",
	    		data:data,
	    		success:function(text){
	    			
	    			
	    		}
	    	}); */
	    }
	    
	    function onOperatePower(e) {
	        var str = "";
//	        str += "<span>";
//	        str += "<a style='margin-right:2px;' title='外协详情' href=javascript:ondStat(\'"+e.row.waiXieCom+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
//          str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='审核' href=javascript:ondMenu(\'"+e.row.orderId+"','"+e.row.companyName+"','"+e.row.connectorTel+"','"+e.row.deptUser+"','"+e.row.bookStatus+"','"+mini.formatDate(e.row.createTime,"yyyy-MM-dd-HH:mm:ss")+"','"+e.row.connector+"\') ><span class='icon-add' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        return str;
	    }
	   

		function ondMenu(orderId,companyName,connectorTel,deptUser,bookStatus,createTime,connector){
			

			//var createTime=document.getElementById("createTime");
	    	window.location="ResearchTask/selectCompletedOrderDetail.jsp?orderId="+orderId+"&companyName="+companyName+"&connector="+connector+"&connectorTel="+connectorTel+"&deptUser="+deptUser+"&bookStatus="+bookStatus+"&createTime="+createTime;
		}
        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "outAssistManage/selectOutAssistWindow.jsp",
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.companyId);
                            btnEdit.setText(data.companyName);
                        }
                    }

                }
            });
        }  

        function onButtonEdit2(e) {
            var btnEdit = this;
            mini.open({
                url: "orderManage/selectCustomerWindow.jsp",
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.companyId);
                            btnEdit.setText(data.companyName);
                        }
                    }

                }
            });
        }
        
        function onCloseClick (para){
	    	mini.get(para).setValue("");
	    	mini.get(para).setText("");
	    }
        
        var bookGenders=[{id: "11", text: "新建"},{id: "12", text: "待审核"},{id: "13", text: "审核通过"},{id: "14", text: "审核不通过"},{id: "15", text: "加工完成"},{id: "16", text: "订单完结"}]
        function onGenderRenderer(e) {
            for (var i = 0, l = bookGenders.length; i < l; i++) {
                var g = bookGenders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }

        
    </script>
  </body>
</html>
