package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.biff.Fonts;

/*import oracle.net.aso.l;*/

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ExportExcelUtil;
import com.wl.tools.Sqlhelper;
import com.wl.tools.UUIDHexGenerator;
import com.wl.tools.Util;

public class OrderInfoExcelDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1301655321688437935L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 访问数据库，得到数据集
		String OrderSql= "select ORDER_ID orderId,F.text deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER," +
				"E.orderStatusDesc orderStatus,C.COMPANYNAME companyName,D.deptname,A.connector,A.connectorTel " +
				"from  orders A "+
				"left join customer C on A.CUSTOMER=C.COMPANYID " +
				"left join dept D on A.DEPT_USER=D.deptid " +
				"left join orderStatus E on A.order_status=E.orderstatusid " +
				"left join shiyebu F on A.DEPT_USER=F.id " +
				"order by order_date  " ;
		List<Order>	orderList = new ArrayList<Order>();
		
		try {
			orderList = Sqlhelper.exeQueryList(OrderSql, null, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		liebiaoxiang.put("checkDate", "质检日期");
		liebiaoxiang.put("CompanyName", "客户名称");
		liebiaoxiang.put("Connector", "联系人");
		liebiaoxiang.put("ConnectorTel", "联系电话");
		liebiaoxiang.put("DeptUser", "使用部门");
		liebiaoxiang.put("OrderDate", "订单日期");
		liebiaoxiang.put("EndTime", "交付日期");
		liebiaoxiang.put("OrderStatus", "订单状态");
		
		List<Integer> columnWidth = new ArrayList<Integer>();
		columnWidth.add(6500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		
		
		
		ExportExcelUtil.exportExcel(request, response, liebiaoxiang, columnWidth, orderList);
		
	}
	
	/**
	 * @author Flair
	 * @param request			请求，HttpServletRequest
	 * @param response			回复，HttpServletResponse
	 */
	public static void demo(HttpServletRequest request,HttpServletResponse response) {
		
		HSSFWorkbook workbook = new HSSFWorkbook(); 		// 创建一个Excel文件
		HSSFSheet sheet = workbook.createSheet(); 			// 创建一个Excel的Sheet
		sheet.createFreezePane(1, 3); 						// 冻结 ,冻结前3行
		// 设置列宽

		sheet.setColumnWidth(0, 6500);
		sheet.setColumnWidth(1, 5500);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 6500);
		sheet.setColumnWidth(6, 6500);
		sheet.setColumnWidth(7, 4500);
		
		HSSFCellStyle sheetStyle = workbook.createCellStyle();				// Sheet样式		
		sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);	// 背景色的设定		
		sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);	// 前景色的设定		
		sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);					// 填充模式
		// 设置列的样式
		for (int i = 0; i <= 14; i++) {
			sheet.setDefaultColumnStyle((short) i, sheetStyle);
		}
		// 设置字体
		HSSFFont headfont = workbook.createFont();
		headfont.setFontName("黑体");
		headfont.setFontHeightInPoints((short) 22); 					// 字体大小
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 				// 加粗
		// 另一个样式
		HSSFCellStyle headstyle = workbook.createCellStyle();
		headstyle.setFont(headfont);
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 			// 左右居中
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 	// 上下居中
		headstyle.setLocked(true);
		headstyle.setWrapText(true);									// 自动换行
		// 另一个字体样式
		HSSFFont columnHeadFont = workbook.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 列头的样式
		HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
		columnHeadStyle.setFont(columnHeadFont);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 			// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index); 			// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index); 		// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 		// 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); 		// 设置单元格的边框颜色
		// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);
		// 普通单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); 						// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP); 			// 上下居中
		style.setWrapText(true);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft((short) 1);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 					// 设置单元格的边框为粗体
		style.setBottomBorderColor(HSSFColor.BLACK.index); 					// 设置单元格的边框颜色．
		style.setFillForegroundColor(HSSFColor.WHITE.index); 				// 设置单元格的背景颜色．
		// 另一个样式
		HSSFCellStyle centerstyle = workbook.createCellStyle();
		centerstyle.setFont(font);
		centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 				// 左右居中
		centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	// 上下居中
		centerstyle.setWrapText(true);
		centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
		centerstyle.setBorderLeft((short) 1);
		centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
		centerstyle.setBorderRight((short) 1);
		centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 			// 设置单元格的边框为粗体
		centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); 			// 设置单元格的边框颜色．
		centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);	 		// 设置单元格的背景颜色．

		try {
			
			HSSFRow row0 = sheet.createRow(0);						// 创建第一行
			row0.setHeight((short) 900);							// 设置行高
			HSSFCell cell0 = row0.createCell(0);					// 创建第一列
			cell0.setCellValue(new HSSFRichTextString("订单表"));
			cell0.setCellStyle(headstyle);
			/**
			 * 合并单元格 第一个参数：第一个单元格的行数（从0开始） 第二个参数：第二个单元格的行数（从0开始）
			 * 第三个参数：第一个单元格的列数（从0开始） 第四个参数：第二个单元格的列数（从0开始）
			 */
			CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
			sheet.addMergedRegion(range);

			HSSFRow row1 = sheet.createRow(1);						// 创建第二行
			HSSFCell cell1 = row1.createCell(0);
			cell1.setCellValue(new HSSFRichTextString("本表展示的是订单头信息"));
			cell1.setCellStyle(centerstyle);
			range = new CellRangeAddress(1, 2, 0, 7);				// 合并单元格
			sheet.addMergedRegion(range);
			
			HSSFRow row2 = sheet.createRow(3);						// 第三行
			row2.setHeight((short) 750);
			HSSFCell cell = row2.createCell(0);
			cell.setCellValue(new HSSFRichTextString("订单编号"));
			cell.setCellStyle(columnHeadStyle);
			cell = row2.createCell(1);
			cell.setCellValue(new HSSFRichTextString("客户名称"));
			cell.setCellStyle(columnHeadStyle);
			cell = row2.createCell(2);
			cell.setCellValue(new HSSFRichTextString("联系人"));
			cell.setCellStyle(columnHeadStyle);
			cell = row2.createCell(3);
			cell.setCellValue(new HSSFRichTextString("联系电话"));
			cell.setCellStyle(columnHeadStyle);
			cell = row2.createCell(4);
			cell.setCellValue(new HSSFRichTextString("使用部门"));
			cell.setCellStyle(columnHeadStyle);
			cell = row2.createCell(5);
			cell.setCellValue(new HSSFRichTextString("订单日期"));
			cell.setCellStyle(columnHeadStyle);
			cell = row2.createCell(6);
			cell.setCellValue(new HSSFRichTextString("交付日期"));
			cell.setCellStyle(columnHeadStyle);
			cell = row2.createCell(7);
			cell.setCellValue(new HSSFRichTextString("订单状态"));
			cell.setCellStyle(columnHeadStyle);

			// 访问数据库，得到数据集
			String OrderSql= "select ORDER_ID orderId,F.text deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER," +
					"E.orderStatusDesc orderStatus,C.COMPANYNAME companyName,D.deptname,A.connector,A.connectorTel " +
					"from  orders A "+
					"left join customer C on A.CUSTOMER=C.COMPANYID " +
					"left join dept D on A.DEPT_USER=D.deptid " +
					"left join orderStatus E on A.order_status=E.orderstatusid " +
					"left join shiyebu F on A.DEPT_USER=F.id " +
					"order by order_date  " ;
			List<Order>	orderList = new ArrayList<Order>();
			
			try {
				orderList = Sqlhelper.exeQueryList(OrderSql, null, Order.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int m=4;
			int k=4;
			for(int i=0;i<orderList.size();i++){
				Order order = orderList.get(i);
				HSSFRow row = sheet.createRow(m);	//创建一行
				cell = row.createCell(0);
				cell.setCellValue(new HSSFRichTextString(order.getOrderId()));
				cell.setCellStyle(centerstyle);
				
				cell = row.createCell(1);
				cell.setCellValue(new HSSFRichTextString(order.getCompanyName()));
				cell.setCellStyle(centerstyle);
				
				cell = row.createCell(2);
				cell.setCellValue(new HSSFRichTextString(order.getConnector()));
				cell.setCellStyle(centerstyle);
				
				cell = row.createCell(3);
				cell.setCellValue(new HSSFRichTextString(order.getConnectorTel()));
				cell.setCellStyle(centerstyle);
				
				cell = row.createCell(4);
				cell.setCellValue(new HSSFRichTextString(order.getDeptUser()));
				cell.setCellStyle(centerstyle);
				
				cell = row.createCell(5);
				cell.setCellValue(new HSSFRichTextString(order.getOrderDate()));
				cell.setCellStyle(centerstyle);
				
				cell = row.createCell(6);
				cell.setCellValue(new HSSFRichTextString(order.getEndTime()));
				cell.setCellStyle(centerstyle);
				
				cell = row.createCell(7);
				cell.setCellValue(new HSSFRichTextString(order.getOrderStatus()));
				cell.setCellStyle(centerstyle);
				
				m++;
			}

			int footRownumber = sheet.getLastRowNum();
			HSSFRow footRow = sheet.createRow(footRownumber + 1);
			HSSFCell footRowcell = footRow.createCell(0);
			HttpSession session = request.getSession();
			String createPerson = ((User) session.getAttribute("user")).getStaffCode();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String createTime = df.format(new Date());
			footRowcell.setCellValue(new HSSFRichTextString("                    打印者："+createPerson+"      打印日期："+createTime));
			footRowcell.setCellStyle(centerstyle);
			range = new CellRangeAddress(footRownumber + 1, footRownumber + 2,0, 7);
			sheet.addMergedRegion(range);

			String UUID = UUIDHexGenerator.getInstance().generate();

			String filename = UUID+".xls"; // 设置下载时客户端Excel的名称
			filename = Util.encodeFilename(filename, request);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="+ filename);
			OutputStream ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	/**
//	 * Excel单元格类型
//	 */
//	private static HSSFCellStyle getCellStyle(HSSFWorkbook wb) {
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
//		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
//		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
//		cellStyle.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
//		cellStyle.setVerticalAlignment((short) HSSFCellStyle.VERTICAL_CENTER);
//		return cellStyle;
//	}

}