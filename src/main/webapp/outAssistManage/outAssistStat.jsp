<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>外协统计</title>
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
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
    <div id="win1" class="mini-window" title="查找" style="width:550px;height:100px;" collapseOnTitleClick="true"
      showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" expanded="false"
      showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    	<form id="form0">
	    <table >
	   		<tr>
	          <td>开始时间：</td>
	          <td><input id="bday" class="mini-datepicker" width="100"  allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td>
	          <td>结束时间：</td>
	          <td><input id="eday" class="mini-datepicker"   width="100" allowinput="false"  format="yyyy-MM-dd" required="false"/>
	          </td>
	          <td width="60px" align="right">外协商：</td>
	          <td><input id="waixiecom" name="waixiecom" class="mini-buttonedit" width="100" showClose="true" 
            		onbuttonclick="onButtonEdit" textName="waixiecom"  allowInput="false"/>
	          </td>
	       </tr>
	       <tr>
	          <td>订单号</td>
	          <td ><input id="orderId" name="orderId" class="mini-textbox" width="100" allowInput="true" />
	          </td>
	   		  <td align="right">产品号：</td>
	          <td ><input id="productId" name="productId" class="mini-textbox" width="100%"  allowInput="true"/>
	          </td>
	          <td><input value="查找" type="button" onclick="loadgrid()" style="width:50px;"/></td>
	          <td><input value="导出EXCEL" type="button" onclick="toEXCEL()" style="width:100px;"/></td>
	   		</tr>
	   	</table>
	   </form>
	</div>
  </br>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;"  allowResize="true"   showSummaryRow="true" allowAlternating="true" showPager="true"
         url="OutAssistStatServlet1" allowCellSelect="true" allowCellEdit="true"
         	showColumnsMenu ="true"  frozenStartColumn="0" frozenEndColumn="6" ondrawsummarycell="onDrawSummaryCell" >
       <div property="columns">
            <div type="indexcolumn"></div>
            <div field="orderId" width="130"  headerAlign="center">订单号
            </div> 
            <div field="companyName" width="120" headerAlign="center">单位名称
            </div>
            <div field="productId" width="100" headerAlign="center">图号
            </div>
            <div field="productName"width="80" name="productName" width="120" headerAlign="center">产品名称
            </div>
            <div field="deliverTime" width="90" dateFormat="yyyy-MM-dd"  headerAlign="center">下发时间
            </div>
            <div field="planEndTime" width="90" dateFormat="yyyy-MM-dd" headerAlign="center">要求完成时间
            </div>
            <div field="actuallyEndTime" width="90" dateFormat="yyyy-MM-dd" headerAlign="center">实际完成时间
            </div>
 <!--       <div field="typeName" width="60" headerAlign="center">工种
            </div>
-->        
            <div field="operName" width="100" headerAlign="center">工序名称
            </div>
            <div field="unitPrice" width="90"  headerAlign="center">单价
            </div>
            <div field="num" width="60" headerAlign="center">理论数量
            </div>
            <div field="passNum" width="60" headerAlign="center">实际数量
            </div>
            <div field="theoryTotalPrice" width="60" headerAlign="center">理论总价
            </div>
            <div field="totalPrice" width="60" headerAlign="center">实际总价
            </div>
   <!--     <div field="quality" width="70" headerAlign="center"  renderer="onQualityRenderer" enable="false" >质量情况
            <input property="editor" class="mini-combobox"  width="100%" url="data/quality.txt"/>     
            </div>   
  -->  
            <div field="qualityFine" width="90" headerAlign="center">质量罚款
            </div>
  <!--      <div field="alreadyPay" width="70"  headAlign="center">已付款
            </div>
            <div field="waitPay" width="70"  headerAlign="center">待付款
            </div>
  -->      
            <div field="isOpenTicket" width="70" headerAlign="center" renderer="onGenderRenderer">是否开票
  <!--      <input property="editor" class="mini-combobox"  width="100%" url="data/trueOrFalse.txt"  />       --> 
            </div>
            <div field="payTime" width="100" dateFormat="yyyy-MM-dd" headerAlign="center">付款周期
  <!--       <input property="editor" class="mini-datepicker"    />                                           -->    
            </div>
            <div field="isBusy" width="70"  headerAlign="center"  renderer="onGenderRenderer">是否急件
  <!--      <input property="editor" class="mini-combobox" value="0" width="100%" url="data/trueOrFalse.txt" />     -->       
            </div>
<!--             <div field="connector"width="80" headerAlign="center">联系人 -->
<!--             </div> -->
<!--             <div field="connectorTel" width="100" headerAlign="center">联系电话 -->
<!--             </div> -->
<!--             <div field="fax" width="80" headerAlign="center">传真 -->
<!--             </div> -->
<!--             <div field="address" width="100" headerAlign="center">地址 -->
<!--             </div> -->
            <div field="memo" width="100"  headerAlign="center" renderer="onNullRenderer">备注 
            </div>
       </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function toEXCEL(){
	    	var bday = mini.get("bday").getFormValue();
	    	var eday = mini.get("eday").getFormValue();
	    	var waixiecom = mini.get("waixiecom").getValue();
	    	var orderId = mini.get("orderId").getValue();
	    	var productId = mini.get("productId").getValue();
	    	window.location="OutAssistStatToExcelServlet?bday="+bday+"&eday="+eday+"&waixiecom="+waixiecom+"&orderId="+orderId+"&productId="+productId;	    
	    }
	    
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='保存' href=javascript:ondSave('"+e.row.orderId+"','"+e.row.productId+"','"+e.row.issueNum+"','"+e.row.operId+"','"+e.row.deliverTime+"','"+e.row.planEndTime+"','"+mini.formatDate(e.row.actuallyEndTime,"yyyy-MM-dd")+"','"+e.row.unitPrice+"','"+e.row.quality+"','"+e.row.qualityFine+"','"+e.row.alreadyPay+"','"+e.row.waitPay+"','"+e.row.isOpenTicket+"','"+mini.formatDate(e.row.payTime,"yyyy-MM-dd")+"','"+e.row.isBusy+"','"+e.row.memo+"') ><span class='mini-button-text mini-button-icon icon-save'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	   
//	    function ondSave(orderId,productId,issueNum,operId,deliverTime,planEndTime,actuallyEndTime,unitPrice,quality,qualityFine,alreadyPay,waitPay,isOpenTicket,payTime,isBusy,memo){
//	    	window.location="OutAssistStatSaveServlet?orderId="+orderId+"&productId="+productId+"&issueNum="+issueNum+"&operId="+operId+"&deliverTime="+deliverTime+"&planEndTime="+planEndTime+"&actuallyEndTime="+actuallyEndTime+"&unitPrice="+unitPrice+"&quality="+quality+"&qualityFine="+qualityFine+"&alreadyPay="+alreadyPay+"&waitPay="+waitPay+"&isOpenTicket="+isOpenTicket+"&payTime="+payTime+"&isBusy="+isBusy+"&memo="+memo;
//		}
		function ondSave(orderId,productId,issueNum,operId,deliverTime,planEndTime,actuallyEndTime,unitPrice,quality,qualityFine,alreadyPay,waitPay,isOpenTicket,payTime,isBusy,memo){
            var data = "{\"orderId\":\""+orderId+"\",\"productId\":\""+productId+"\",\"issueNum\":\""+issueNum+"\",\"operId\":\""+operId+"\",\"deliverTime\":\""+deliverTime+"\",\"planEndTime\":\""+planEndTime+"\",\"actuallyEndTime\":\""+actuallyEndTime+"\",\"unitPrice\":\""+unitPrice+"\",\"quality\":\""+quality+"\",\"qualityFine\":\""+qualityFine+"\",\"alreadyPay\":\""+alreadyPay+"\",\"waitPay\":\""+waitPay+"\",\"isOpenTicket\":\""+isOpenTicket+"\",\"payTime\":\""+payTime+"\",\"isBusy\":\""+isBusy+"\",\"memo\":\""+memo+"\"}";
			var params = JSON.parse(data);
			var url = "OutAssistStatSaveServlet";
				jQuery.post(url, params, function(data){
   	   				alert(data.result);
   	   				window.location.href=window.location.href;
   	   			},'json');
		  }
		  
		  
		 function loadgrid(){
	    	var form0 = new mini.Form("form0");
	   	 	form0.validate();
// 	      	 if (form0.isValid() == false) {
// 	      	 	alert("请选取具体时间段");
// 	      	 	return;}
	    	var bday = mini.get("bday").getFormValue();
	    	var eday = mini.get("eday").getFormValue();
	    	var waixiecom = mini.get("waixiecom").getValue();
	    	var orderId = mini.get("orderId").getValue();
	    	var productId = mini.get("productId").getValue();
	    	grid.load({bday:bday, eday:eday, waixiecom:waixiecom, orderId:orderId, productId:productId});
	    }
		  
		  
		  
		  function showAtPos() {
	        var win = mini.get("win1");
	
	        var x = "right";
	        var y = "top";
	
	        win.showAtPos(x, y);
	
	    }
		showAtPos();
		  
		  
		
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
        
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	    var Genders = [{id: "0", text: "否"},{id: "1", text: "是"}];
        function onGenderRenderer(e) {
           for (var i = 0, l = Genders.length; i < l; i++) {
              var g = Genders[i];
               if (g.id == e.value) return g.text;
           }
          return "";
       }
       
      var Quality=[{id:"1",text:"差"},{id:"2",text:"一般"},{id:"3",text:"良好"},{id:"4",text:"很好"}];
      function onQualityRenderer(e){
            for (var i = 0, l = Quality.length; i < l; i++) {
              var g = Quality[i];
               if (g.id == e.value) return g.text;
           }
          return "";
       }
       
      function onNullRenderer(e){
        if (e.value===null)
          return "";
        else 
          return e.value;
       } 
     
   function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            if (e.field == "unitPrice") {                
                var s = "<b style='color:red;'>"
                s +=  	"实际总价："+ result.totalPay + "<br/>"
                		+"实际扣款:"+result.qualityFine+"<br/>"
                		+"实际数量:"+result.number+"<br/>"
                		+"</b>";
                e.cellHtml = s;
            }
      } 
   
        
    </script>
  </body>
</html>