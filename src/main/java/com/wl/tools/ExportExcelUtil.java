package com.wl.tools;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.wl.forms.User;


public final class ExportExcelUtil
{
	/**
	 * @author Flair
	 * @param request		请求，
	 * @param response		响应，
	 * @param liebiaoxiang	列表项，key表示对应的属性，value表示属性对应的中文解释，这里的Map要求是LinkedHashMap
	 * @param columnWidth	列宽，
	 * @param content		循环展示的内容，
	 */
	public static <T> void exportExcel(HttpServletRequest request,HttpServletResponse response,
			LinkedHashMap<String, String> liebiaoxiang, List<Integer> columnWidth, List<T> content){
		
		HSSFWorkbook workbook = new HSSFWorkbook(); 		// 创建一个Excel文件
		HSSFSheet sheet = workbook.createSheet(); 			// 创建一个Excel的Sheet
		sheet.createFreezePane(1, 1); 						// 冻结 ,冻结第一行
		//设置列宽
		for(int i=0;i<columnWidth.size();i++){
			sheet.setColumnWidth(i, columnWidth.get(i));
		}
		
		// Sheet样式
		HSSFCellStyle sheetStyle = workbook.createCellStyle();
		// 背景色的设定
		sheetStyle.setFillBackgroundColor(HSSFColor.WHITE.index); 	//GREY_25_PERCENT
		// 前景色的设定
		sheetStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		// 填充模式
		sheetStyle.setFillPattern(HSSFCellStyle.NO_FILL);	//FINE_DOTS
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
			
			HSSFCell cell = row0.createCell(0);
			//这里遍历Map
			int len=0;
			List<String> liebiaoshuxing = new ArrayList<String>(liebiaoxiang.size());				//存放属性key
			for (Map.Entry<String, String> entry : liebiaoxiang.entrySet()) {  
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
			    liebiaoshuxing.add(entry.getKey());
			    cell = row0.createCell(len);
				cell.setCellValue(new HSSFRichTextString(entry.getValue()));
				cell.setCellStyle(columnHeadStyle);
				len++;
			}

			int m=1;
			for(int i=0;i<content.size();i++){
				T order = content.get(i);
				Class clazz = order.getClass();
				Method[] methods = clazz.getDeclaredMethods();
				HSSFRow row = sheet.createRow(m);	//创建一行
				for(Method method : methods){
					String methodName =method.getName();
					if(methodName.startsWith("get")){
						String fieldName = methodName.substring(3,4).toLowerCase()+methodName.substring(4);
						for(int j=0;j<liebiaoshuxing.size();j++){
							if((fieldName.toLowerCase()).equals(liebiaoshuxing.get(j).toLowerCase())){
								cell = row.createCell(j);
								cell.setCellValue(new HSSFRichTextString(method.invoke(order)+""));
								cell.setCellStyle(centerstyle);
								break;
							}
						}
					}
				}
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
			
			/*
			 * 合并单元格 第一个参数：第一个单元格的行数（从0开始） 第二个参数：第二个单元格的行数（从0开始）
			 * 第三个参数：第一个单元格的列数（从0开始） 第四个参数：第二个单元格的列数（从0开始）
			 */
			CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
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
	
	
	
	/**
	 * @author Flair
	 * @param request			请求，HttpServletRequest
	 * @param response			回复，HttpServletResponse
	 */
//	public static void demo(HttpServletRequest request,HttpServletResponse response) {
//		HSSFWorkbook workbook = new HSSFWorkbook(); 		// 创建一个Excel文件
//		HSSFSheet sheet = workbook.createSheet(); 			// 创建一个Excel的Sheet
//		sheet.createFreezePane(1, 3); 						// 冻结 ,冻结前3行
//		// 设置列宽
//
//		sheet.setColumnWidth(0, 6500);
//		sheet.setColumnWidth(1, 5500);
//		sheet.setColumnWidth(2, 4000);
//		sheet.setColumnWidth(3, 5000);
//		sheet.setColumnWidth(4, 5000);
//		sheet.setColumnWidth(5, 6500);
//		sheet.setColumnWidth(6, 6500);
//		sheet.setColumnWidth(7, 4500);
//		// Sheet样式
//		HSSFCellStyle sheetStyle = workbook.createCellStyle();
//		// 背景色的设定
//		sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
//		// 前景色的设定
//		sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//		// 填充模式
//		sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
//		// 设置列的样式
//		for (int i = 0; i <= 14; i++) {
//			sheet.setDefaultColumnStyle((short) i, sheetStyle);
//		}
//		// 设置字体
//		HSSFFont headfont = workbook.createFont();
//		headfont.setFontName("黑体");
//		headfont.setFontHeightInPoints((short) 22); 					// 字体大小
//		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 				// 加粗
//		// 另一个样式
//		HSSFCellStyle headstyle = workbook.createCellStyle();
//		headstyle.setFont(headfont);
//		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 			// 左右居中
//		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 	// 上下居中
//		headstyle.setLocked(true);
//		headstyle.setWrapText(true);									// 自动换行
//		// 另一个字体样式
//		HSSFFont columnHeadFont = workbook.createFont();
//		columnHeadFont.setFontName("宋体");
//		columnHeadFont.setFontHeightInPoints((short) 10);
//		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		// 列头的样式
//		HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
//		columnHeadStyle.setFont(columnHeadFont);
//		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 			// 左右居中
//		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
//		columnHeadStyle.setLocked(true);
//		columnHeadStyle.setWrapText(true);
//		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index); 			// 左边框的颜色
//		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
//		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index); 		// 右边框的颜色
//		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
//		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 		// 设置单元格的边框为粗体
//		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); 		// 设置单元格的边框颜色
//		// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
//		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//
//		HSSFFont font = workbook.createFont();
//		font.setFontName("宋体");
//		font.setFontHeightInPoints((short) 10);
//		// 普通单元格样式
//		HSSFCellStyle style = workbook.createCellStyle();
//		style.setFont(font);
//		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); 						// 左右居中
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP); 			// 上下居中
//		style.setWrapText(true);
//		style.setLeftBorderColor(HSSFColor.BLACK.index);
//		style.setBorderLeft((short) 1);
//		style.setRightBorderColor(HSSFColor.BLACK.index);
//		style.setBorderRight((short) 1);
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 					// 设置单元格的边框为粗体
//		style.setBottomBorderColor(HSSFColor.BLACK.index); 					// 设置单元格的边框颜色．
//		style.setFillForegroundColor(HSSFColor.WHITE.index); 				// 设置单元格的背景颜色．
//		// 另一个样式
//		HSSFCellStyle centerstyle = workbook.createCellStyle();
//		centerstyle.setFont(font);
//		centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 				// 左右居中
//		centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	// 上下居中
//		centerstyle.setWrapText(true);
//		centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
//		centerstyle.setBorderLeft((short) 1);
//		centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
//		centerstyle.setBorderRight((short) 1);
//		centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 			// 设置单元格的边框为粗体
//		centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); 			// 设置单元格的边框颜色．
//		centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);	 		// 设置单元格的背景颜色．
//
//		try {
//			
//			HSSFRow row0 = sheet.createRow(0);						// 创建第一行
//			row0.setHeight((short) 900);							// 设置行高
//			HSSFCell cell0 = row0.createCell(0);					// 创建第一列
//			cell0.setCellValue(new HSSFRichTextString("订单表"));
//			cell0.setCellStyle(headstyle);
//			/**
//			 * 合并单元格 第一个参数：第一个单元格的行数（从0开始） 第二个参数：第二个单元格的行数（从0开始）
//			 * 第三个参数：第一个单元格的列数（从0开始） 第四个参数：第二个单元格的列数（从0开始）
//			 */
//			CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
//			sheet.addMergedRegion(range);
//
//			
//			HSSFRow row1 = sheet.createRow(1);						// 创建第二行
//			HSSFCell cell1 = row1.createCell(0);
//			cell1.setCellValue(new HSSFRichTextString("本表展示的是订单头信息"));
//			cell1.setCellStyle(centerstyle);
//			range = new CellRangeAddress(1, 2, 0, 7);				// 合并单元格
//			sheet.addMergedRegion(range);
//			
//			HSSFRow row2 = sheet.createRow(3);						// 第三行
//			row2.setHeight((short) 750);
//			/*
//			 * 此处可以作为参数传入
//			 */
//			HSSFCell cell = row2.createCell(0);
//			cell.setCellValue(new HSSFRichTextString("订单编号"));
//			cell.setCellStyle(columnHeadStyle);
//			cell = row2.createCell(1);
//			cell.setCellValue(new HSSFRichTextString("客户名称"));
//			cell.setCellStyle(columnHeadStyle);
//			cell = row2.createCell(2);
//			cell.setCellValue(new HSSFRichTextString("联系人"));
//			cell.setCellStyle(columnHeadStyle);
//			cell = row2.createCell(3);
//			cell.setCellValue(new HSSFRichTextString("联系电话"));
//			cell.setCellStyle(columnHeadStyle);
//			cell = row2.createCell(4);
//			cell.setCellValue(new HSSFRichTextString("使用部门"));
//			cell.setCellStyle(columnHeadStyle);
//			cell = row2.createCell(5);
//			cell.setCellValue(new HSSFRichTextString("订单日期"));
//			cell.setCellStyle(columnHeadStyle);
//			cell = row2.createCell(6);
//			cell.setCellValue(new HSSFRichTextString("交付日期"));
//			cell.setCellStyle(columnHeadStyle);
//			cell = row2.createCell(7);
//			cell.setCellValue(new HSSFRichTextString("订单状态"));
//			cell.setCellStyle(columnHeadStyle);
//
//			// 访问数据库，得到数据集
//			String OrderSql= "select ORDER_ID orderId,F.text deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER," +
//					"E.orderStatusDesc orderStatus,C.COMPANYNAME companyName,D.deptname,A.connector,A.connectorTel " +
//					"from  orders A "+
//					"left join customer C on A.CUSTOMER=C.COMPANYID " +
//					"left join dept D on A.DEPT_USER=D.deptid " +
//					"left join orderStatus E on A.order_status=E.orderstatusid " +
//					"left join shiyebu F on A.DEPT_USER=F.id " +
//					"order by order_date  " ;
//			List<Order>	orderList = new ArrayList<Order>();
//			
//			try {
//				orderList = Sqlhelper.exeQueryList(OrderSql, null, Order.class);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			int m=4;
//			int k=4;
//			for(int i=0;i<orderList.size();i++){
//				Order order = orderList.get(i);
//				HSSFRow row = sheet.createRow(m);	//创建一行
//				cell = row.createCell(0);
//				cell.setCellValue(new HSSFRichTextString(order.getOrderId()));
//				cell.setCellStyle(centerstyle);
//				
//				cell = row.createCell(1);
//				cell.setCellValue(new HSSFRichTextString(order.getCompanyName()));
//				cell.setCellStyle(centerstyle);
//				
//				cell = row.createCell(2);
//				cell.setCellValue(new HSSFRichTextString(order.getConnector()));
//				cell.setCellStyle(centerstyle);
//				
//				cell = row.createCell(3);
//				cell.setCellValue(new HSSFRichTextString(order.getConnectorTel()));
//				cell.setCellStyle(centerstyle);
//				
//				cell = row.createCell(4);
//				cell.setCellValue(new HSSFRichTextString(order.getDeptUser()));
//				cell.setCellStyle(centerstyle);
//				
//				cell = row.createCell(5);
//				cell.setCellValue(new HSSFRichTextString(order.getOrderDate()));
//				cell.setCellStyle(centerstyle);
//				
//				cell = row.createCell(6);
//				cell.setCellValue(new HSSFRichTextString(order.getEndTime()));
//				cell.setCellStyle(centerstyle);
//				
//				cell = row.createCell(7);
//				cell.setCellValue(new HSSFRichTextString(order.getOrderStatus()));
//				cell.setCellStyle(centerstyle);
//				
//				m++;
//			}
//			
//			// List<DeitelVO> deitelVOList =
//			// getEntityManager().queryDeitelVOList();
//			// int m = 4;
//			// int k = 4;
//			// for (int i = 0; i < deitelVOList.size(); i++) {
//			// DeitelVO vo = deitelVOList.get(i);
//			// String dname = vo.getDname();
//			// List<Workinfo> workList = vo.getWorkInfoList();
//			// HSSFRow row = sheet.createRow(m);
//			// cell = row.createCell(0);
//			// cell.setCellValue(new HSSFRichTextString(dname));
//			// cell.setCellStyle(centerstyle);
//			// // 合并单元格
//			// range = new CellRangeAddress(m, m + workList.size() - 1, 0, 0);
//			// sheet.addMergedRegion(range);
//			// m = m + workList.size();
//			//		  
//			// for (int j = 0; j < workList.size(); j++) {
//			// Workinfo w = workList.get(j);
//			// // 遍历数据集创建Excel的行
//			// row = sheet.getRow(k + j);
//			// if (null == row) {
//			// row = sheet.createRow(k + j);
//			// }
//			// cell = row.createCell(1);
//			// cell.setCellValue(w.getWnumber());
//			// cell.setCellStyle(centerstyle);
//			// cell = row.createCell(2);
//			// cell.setCellValue(new HSSFRichTextString(w.getWitem()));
//			// cell.setCellStyle(style);
//			// cell = row.createCell(3);
//			// cell.setCellValue(new HSSFRichTextString(w.getWmeting()));
//			// cell.setCellStyle(style);
//			// cell = row.createCell(4);
//			// cell.setCellValue(new HSSFRichTextString(w.getWbweek()));
//			// cell.setCellStyle(style);
//			// cell = row.createCell(5);
//			// cell.setCellValue(new HSSFRichTextString(w.getWtweek()));
//			// cell.setCellStyle(style);
//			// cell = row.createCell(6);
//			// cell.setCellValue(new HSSFRichTextString(w.getWproblem()));
//			// cell.setCellStyle(style);
//			// cell = row.createCell(7);
//			// cell.setCellValue(new HSSFRichTextString(w.getWremark()));
//			// cell.setCellStyle(style);
//			// }
//			// k = k + workList.size();
//			// }
//			// 列尾
//
//			int footRownumber = sheet.getLastRowNum();
//			HSSFRow footRow = sheet.createRow(footRownumber + 1);
//			HSSFCell footRowcell = footRow.createCell(0);
//			HttpSession session = request.getSession();
//			String createPerson = ((User) session.getAttribute("user")).getStaffCode();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
//			String createTime = df.format(new Date());
//			footRowcell.setCellValue(new HSSFRichTextString("                    打印者："+createPerson+"      打印日期："+createTime));
//			footRowcell.setCellStyle(centerstyle);
//			range = new CellRangeAddress(footRownumber + 1, footRownumber + 2,0, 7);
//			sheet.addMergedRegion(range);
//
//			String UUID = UUIDHexGenerator.getInstance().generate();
//
//			String filename = UUID+".xls"; // 设置下载时客户端Excel的名称
//			// 请见：http://zmx.iteye.com/blog/622529
//			filename = Util.encodeFilename(filename, request);
//			response.setContentType("application/vnd.ms-excel");
//			response.setHeader("Content-disposition", "attachment;filename="+ filename);
//			OutputStream ouputStream = response.getOutputStream();
//			workbook.write(ouputStream);
//			ouputStream.flush();
//			ouputStream.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
    
    
}
