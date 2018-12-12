<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>外协单</title>
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
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type='text/javascript' src="<%=basePath%>resources/js/boot.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  <div class="mini-toolbar"> 
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
        <span class="separator"></span>
        <a class="mini-button" iconCls="icon-find" plain="false"  onclick="check()">完成情况</a>
        <span class="separator"></span>
        <a class="mini-button" iconCls="icon-remove" plain="false" onclick="remove()">撤销</a>
        <span class="separator"></span>
        <a class="mini-button" iconCls="icon-print" plain="false"  onclick="printMenu()">打印外协单</a>
	</div>
  <div align="center" >外协加工任务单</div>
    <table style="text-align:left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
 		
   		<tr height="30">
            <td style="width:20%">加工单位</td>
            <td Style="width:30%">${companyName}</td>
            <td style="width:20%">需方单位</td>
   			<td style="width:30%">南京纳联数控技术有限公司</td>
   			<input class="mini-hidden" id="menuId" name="menuId"  value="${menuId}" />     
        </tr>
  
   </table>

    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:1;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="OutAssistMenuServlet?menuId=${menuId}" allowCellSelect="true" allowCellEdit="true">
        <div property="columns">
            <div type="indexcolumn"></div>
           <div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div> 
  
            <div field="orderId" width="140"  headerAlign="center">订单号
            </div> 
            <div field="productId" width="80"  headerAlign="center">图号
            </div>
            <div field="companyName" width="100" headerAlign="center">单位名称
            </div>
            <div field="productName" width="60" headerAlign="center">零件名称
            </div>
            <div field="operName" width="50" headerAlign="center">加工工序
            </div>
            <div field="num" width="30" headerAlign="center">数量
            </div>
            <div field="planStartTime" width="80" dateFormat="yyyy-MM-dd" headerAlign="center">发货日
            <input  property="editor" class="mini-datepicker"   />  
            </div>
            <div field="planEndTime" width="80" dateFormat="yyyy-MM-dd"   headerAlign="center">交货日
            <input  property="editor" class="mini-datepicker"   />     
            </div>
            <div field="unitPrice" width="40" headerAlign="center">单价
            <input property="editor" class="mini-textbox"  width="100%" />  
            </div>
            <div field="totalPrice" width="40" headerAlign="center">总价
            <input property="editor" class="mini-textbox"  width="100%" />
            </div>
     <!--   <div field="memo" width="60"  headerAlign="center"  renderer="onGenderRenderer">备注   -->
            <div field="memo" width="60"  headerAlign="center" >备注    
            <input property="editor" class="mini-textbox"  width="100%"  />    
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='保存' href=javascript:ondSave() ><span class='mini-button-text mini-button-icon icon-save'>&nbsp;</span></a>";
	        str += "</span>";
	        str +="<span>";
	        str += "<a style='margin-right:2px;' title='详情' href=javascript:ondMenu(\'"+ e.row.orderId+"\',\'"+e.row.productId + "\',\'"+e.row.issueNum+"\',\'"+e.row.operId+ "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        str +="<span>";
	        str += "<a style='margin-right:2px;' title='撤销' href=javascript:ondRemove() ><span class='mini-button-text mini-button-icon icon-remove'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	   
//	    function ondSave(orderId,productId,issueNum,operId,planStartTime,planEndTime,unitPrice,totalPrice,memo){
//	    	if(memo == null){
//	    	}
//	    	var s = "OutAssistMenuSaveServlet?orderId="+orderId+"&productId="+productId+"&issueNum="+issueNum+"&operId="+operId+"&planStartTime="+planStartTime+"&planEndTime="+planEndTime+"&unitPrice="+unitPrice+"&totalPrice="+totalPrice+"&memo="+memo;
//	    	window.location= s;
//		}
		function ondSave(){
			
			var data = grid.getSelected();
			var params =  eval("("+mini.encode(data)+")");
			var url1="CheckOutAssistStatusServlet";
	  	    jQuery.post(url1, params, function(data){
               if(data.status>='3')
               { alert("审核已通过，不允许修改");
               window.location.herf=window.location.herf;
                return;}
			    var url = "OutAssistMenuSaveServlet";
				jQuery.post(url, params, function(data){
   	   				alert(data.result);
   	   				window.location.href=window.location.href;
   	   			 },'json');
   	   			},'json');	
		}
		
		function ondRemove(){
		var data=grid.getSelected();
		var params=eval("("+mini.encode(data)+")");
		var url="RemoveProcessFromOutAssistMenuServlet";    
           jQuery.post(url, params, function(data){
               if(data.count!=1)
   	   			{	alert(data.result);
   	   				window.location.href=window.location.href;
   	   				}
   	   			else {
   	   			alert(data.result);
   	   			window.location.href="outAssistManage/outAssistMenuList.jsp";
   	   			}
   	   			 },'json');		
		}
	   
	   function ondMenu(orderId,productId,issueNum,operId){
		var data=grid.getSelected();
		window.location="ProcessOutAssistStatServlet2?orderId="+orderId+"&productId="+productId+"&issueNum="+issueNum+"&operId="+operId;
		}
	  
	    function back(){
			window.history.back(-1);
		}   
	
		
	function confirmClick() {
        mini.confirm("确定删除记录？", "确定？",
            function (action) {
                if (action == "ok") {
                    alert("删除!");
                } else {
                    alert("不删除!");
                }
            }
        );
    }
		
		
		
		
		
		
		
		function check(){
        var menuId=mini.get("menuId").getValue();
        window.open("GoOutAssistCompleteServlet?menuId="+menuId,
	      "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
        
	    }
		
		function printCheckout(){
	      var checksheetId = mini.get("checksheet_id").getValue();
	      window.open("ToPrintCheckout?checkSheetid="+checksheetId,
	      "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
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
        
        function remove(){
        var menuId =mini.get("menuId").getValue();
        var url="OutAssistMenuRemoveServlet";
        var params={'menuId':menuId};
        jQuery.post(url, params, function(data){
           alert(data.result);
           back();   
   	   			},'json');	
        }
        
        
        
        
        
         function back(){
			window.history.back(-1);
		}
		
        function onGenderRenderer(e) {
            if(e.value==null)
            return "";
        }
       
    function printMenu(){
     	var menuId = mini.get("menuId").getValue();
   		window.open("OutAssistMenuPrint?menuId=" + menuId,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
     }
        
    </script>
  </body>
</html>
