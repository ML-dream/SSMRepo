/**
 * 项目名称: work
 * 创建日期：2016-6-22
 * 修改历史：
 *		1.[2016-6-22]创建文件 by Flair
 */
package com.wl.testaction.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class TestExcel2 {
	/**
	 * @param args
	 */
	/**
	 * 生成Excel文件
	 * 
	 * @param path 文件路径
	 * @param sheetName 工作表名称
	 * @param dataTitles 数据标题
	 */
	public void createExcelFile(String path, String sheetName,String[] dataTitles) {
		WritableWorkbook workbook;
		try {
			OutputStream os = new FileOutputStream(path);
			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet(sheetName, 0); // 添加第一个工作表
			initialSheetSetting(sheet);
			Label label;
			for (int i = 0; i < dataTitles.length; i++) {
				// Label(列号,行号,内容,风格)
				label = new Label(i, 0, dataTitles[i], getTitleCellFormat());
				sheet.addCell(label);
			}
			// 插入一行
			insertRowData(sheet, 1, new String[] { "200201001", "张三", "100","60", "100", "260" },getDataCellFormat(CellType.STRING_FORMULA));
			// 一个一个插入行
			label = new Label(0, 2, "200201002",getDataCellFormat(CellType.STRING_FORMULA));
			sheet.addCell(label);
			label = new Label(1, 2, "李四",getDataCellFormat(CellType.STRING_FORMULA));
			sheet.addCell(label);
			insertOneCellData(sheet, 2, 2, 70.5,getDataCellFormat(CellType.NUMBER));
			insertOneCellData(sheet, 3, 2, 90.523,getDataCellFormat(CellType.NUMBER));
			insertOneCellData(sheet, 4, 2, 60.5,getDataCellFormat(CellType.NUMBER));
			insertFormula(sheet, 5, 2, "C3+D3+E3",getDataCellFormat(CellType.NUMBER_FORMULA));
			// 插入日期
			mergeCellsAndInsertData(sheet, 0, 3, 5, 3, new Date(),getDataCellFormat(CellType.DATE));
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化表格属性
	 * 
	 * @param sheet
	 */
	public void initialSheetSetting(WritableSheet sheet) {
		try {
			// sheet.getSettings().setProtected(true); //设置xls的保护，单元格为只读的
			sheet.getSettings().setDefaultColumnWidth(10); // 设置列的默认宽度
			// sheet.setRowView(2,false);//行高自动扩展
			// setRowView(int row, int height);--行高
			// setColumnView(int col,int width); --列宽
			sheet.setColumnView(0, 20);// 设置第一列宽度
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入公式
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param formula
	 * @param format
	 */
	public void insertFormula(WritableSheet sheet, Integer col, Integer row,String formula, WritableCellFormat format) {
		try {
			Formula f = new Formula(col, row, formula, format);
			sheet.addCell(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入一行数据
	 * 
	 * @param sheet
	 *            工作表
	 * @param row
	 *            行号
	 * @param content
	 *            内容
	 * @param format
	 *            风格
	 */
	public void insertRowData(WritableSheet sheet, Integer row,String[] dataArr, WritableCellFormat format) {
		try {
			Label label;
			for (int i = 0; i < dataArr.length; i++) {
				label = new Label(i, row, dataArr[i], format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入单元格数据
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param data
	 */
	public void insertOneCellData(WritableSheet sheet, Integer col,Integer row, Object data, WritableCellFormat format) {
		try {
			if (data instanceof Double) {
				jxl.write.Number labelNF = new jxl.write.Number(col, row,(Double) data, format);
				sheet.addCell(labelNF);
			} else if (data instanceof Boolean) {
				jxl.write.Boolean labelB = new jxl.write.Boolean(col, row,(Boolean) data, format);
				sheet.addCell(labelB);
			} else if (data instanceof Date) {
				jxl.write.DateTime labelDT = new jxl.write.DateTime(col, row,(Date) data, format);
				sheet.addCell(labelDT);
				setCellComments(labelDT, "这是个创建表的日期说明！");
			} else {
				Label label = new Label(col, row, data.toString(), format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 合并单元格，并插入数据
	 * 
	 * @param sheet
	 * @param col_start
	 * @param row_start
	 * @param col_end
	 * @param row_end
	 * @param data
	 * @param format
	 */
	public void mergeCellsAndInsertData(WritableSheet sheet, Integer col_start,
			Integer row_start, Integer col_end, Integer row_end, Object data,
			WritableCellFormat format) {
		try {
			sheet.mergeCells(col_start, row_start, col_end, row_end);// 左上角到右下角
			insertOneCellData(sheet, col_start, row_start, data, format);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给单元格加注释
	 * 
	 * @param label
	 * @param comments
	 */
	public void setCellComments(Object label, String comments) {
		WritableCellFeatures cellFeatures = new WritableCellFeatures();
		cellFeatures.setComment(comments);
		if (label instanceof jxl.write.Number) {
			jxl.write.Number num = (jxl.write.Number) label;
			num.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.Boolean) {
			jxl.write.Boolean bool = (jxl.write.Boolean) label;
			bool.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.DateTime) {
			jxl.write.DateTime dt = (jxl.write.DateTime) label;
			dt.setCellFeatures(cellFeatures);
		} else {
			Label _label = (Label) label;
			_label.setCellFeatures(cellFeatures);
		}
	}

	/**
	 * 读取excel
	 * 
	 * @param inputFile
	 * @param inputFileSheetIndex
	 * @throws Exception
	 */
	public ArrayList<String> readDataFromExcel(File inputFile,int inputFileSheetIndex) {
		ArrayList<String> list = new ArrayList<String>();
		Workbook book = null;
		Cell cell = null;
		WorkbookSettings setting = new WorkbookSettings();
		java.util.Locale locale = new java.util.Locale("zh", "CN");
		setting.setLocale(locale);
		setting.setEncoding("ISO-8859-1");
		try {
			book = Workbook.getWorkbook(inputFile, setting);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sheet = book.getSheet(inputFileSheetIndex);
		for (int rowIndex = 0; rowIndex < sheet.getRows(); rowIndex++) {// 行
			for (int colIndex = 0; colIndex < sheet.getColumns(); colIndex++) {// 列
				cell = sheet.getCell(colIndex, rowIndex);
				System.out.println(cell.getContents());
				list.add(cell.getContents());
			}
		}
		book.close();
		return list;
	}

	/**
	 * 得到数据表头格式
	 * 
	 * @return
	 */
	public WritableCellFormat getTitleCellFormat() {
		WritableCellFormat wcf = null;
		try {
			// 字体样式
			WritableFont wf = new WritableFont(WritableFont.TIMES, 12,WritableFont.NO_BOLD, false);// 最后一个为是否italic
			wf.setColour(Colour.RED);
			wcf = new WritableCellFormat(wf);
			// 对齐方式
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 边框
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			// 背景色
			wcf.setBackground(Colour.GREY_25_PERCENT);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;
	}

	/**
	 * 得到数据格式
	 * 
	 * @return
	 */
	public WritableCellFormat getDataCellFormat(CellType type) {
		WritableCellFormat wcf = null;
		try {
			// 字体样式
			if (type == CellType.NUMBER || type == CellType.NUMBER_FORMULA) {// 数字
				NumberFormat nf = new NumberFormat("#.00");
				wcf = new WritableCellFormat(nf);
			} else if (type == CellType.DATE || type == CellType.DATE_FORMULA) {// 日期
				jxl.write.DateFormat df = new jxl.write.DateFormat("yyyy-MM-dd hh:mm:ss");
				wcf = new jxl.write.WritableCellFormat(df);
			} else {
				WritableFont wf = new WritableFont(WritableFont.TIMES, 10,WritableFont.NO_BOLD, false);// 最后一个为是否italic
				wcf = new WritableCellFormat(wf);
			}
			// 对齐方式
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 边框
			wcf.setBorder(Border.LEFT, BorderLineStyle.THIN);
			wcf.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			wcf.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			// 背景色
			wcf.setBackground(Colour.WHITE);
			wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;
	}

	/**
	 * 打开文件看看
	 * @param exePath
	 * @param filePath
	 */
	public void openExcel(String exePath, String filePath) {
		Runtime r = Runtime.getRuntime();
		String cmd[] = { exePath, filePath };
		try {
			r.exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		String[] titles = { "学号", "姓名", "语文", "数学", "英语", "总分" };
		TestExcel2 jxl = new TestExcel2();
//		String filePath = "e:/testjxl.xls";
//		jxl.createExcelFile(filePath, "成绩单", titles);
//		jxl.readDataFromExcel(new File(filePath), 0);
//		jxl.openExcel("C:/Program Files/Microsoft Office/OFFICE15/EXCEL.EXE",filePath);	//这个可以直接打开excel
		jxl.openExcel("C:/Program Files/Microsoft Office/Office15/WINWORD.EXE", "E:/word.doc");
	}
}