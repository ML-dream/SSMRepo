<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<!-- miniui.js -->
<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
<script type="text/javascript"
	src="<%=path%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/miniui/miniui.js"></script>
<link href="<%=path%>/scripts/miniui/themes/default/miniui.css"
	rel="stylesheet" type="txt/css"></link>
<link href="<%=path%>/scripts/miniui/themes/icons.css" rel="stylesheet"
	type="txt/css"></link>

<title>外协付款</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
</style>
</head>

<body style="height:99%">
	<div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
		borderStyle="border:0;" multiSelect="true" idField="id"
		showSummaryRow="true" allowAlternating="true" showPager="true"
		url="OutAssistPayListServlet" 
		onshowrowdetail="onShowRowDetail">
		<div property="columns">
			<div type="indexcolumn">序号</div>
			<div type="expandcolumn"></div>
			<div field="menuId" headerAlign="center" align="center">外协单号</div>
			<div field="companyName" headerAlign="center" align="center">外协单位</div>
			<div field="deliverTime" headerAlign="center" align="center">外协时间</div>
			<div field="theoryTotalPrice" headerAlign="center" align="center">理论报价</div>
            <div field="totalPrice" headerAlign="center" align="center">实际报价</div>
			<div field="qualityFine" headerAlign="center" align="center">质量罚款</div>
		</div>
	</div>

	<div id="detailGrid_Form" style="display:none;">
		<div id="detailgrid" class="mini-datagrid"
			style="width:100%;height:250px;" borderStyle="border:0;"
			multiSelect="true" idField="id" allowAlternating="true"
			showPager="true" url="OutAssistMenuSpecServlet">
			<div property="columns">
				<div type="indexcolumn"></div>
				<div field="orderId" width="140" headerAlign="center">订单号</div>
				<div field="productId" width="80" headerAlign="center">图号</div>
				<div field="companyName" width="100" headerAlign="center">单位名称</div>
				<div field="productName" width="60" headerAlign="center">零件名称</div>
				<div field="operName" width="50" headerAlign="center">加工工序</div>
				<div field="unitPrice" width="40" headerAlign="center">单价</div>
				<div field="num" width="30" headerAlign="center">计划数量</div>
                <div field="passNum" width="30" headerAlign="center">实际数量</div>
                <div field="theoryTotalPrice" width="40" headerAlign="center">计划总价</div>
                <div field="totalPrice" width="40" headerAlign="center">实际总价</div>
				<div field="qualityFine" width="60" headerAlign="center">质量罚款
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		mini.parse();
		var grid = mini.get("grid1");
		var detailgrid = mini.get("detailgrid");
		grid.load();

/**		function onDrawSummaryCell(e) {
			var result = e.result;
			var grid = e.sender;
			if (e.field == "qualityFine") {
				var s = "<b style='color:red;'>"
				s += "应付款：" + result.totalPrice + "<br/>" + "质量扣款:"
						+ result.qualityFine + "<br/>" + "实际应付款:"
						+ result.shouldPay + "<br/>" + "</b>";
				e.cellHtml = s;
			}
		}
*/

		function onShowRowDetail(e) {
			var grid = e.sender;
			var row = e.record;
			var td = grid.getRowDetailCellEl(row);
			td.appendChild(detailGrid_Form);
			detailGrid_Form.style.display = "block";
			detailgrid.load({
				menuId : row.menuId
			});
		}
	</script>
</body>
</html>
