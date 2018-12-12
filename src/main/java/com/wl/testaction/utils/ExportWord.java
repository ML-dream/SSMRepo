/**
 * 项目名称: work
 * 创建日期：2016-6-22
 * 修改历史：
 *		1.[2016-6-22]创建文件 by Flair
 *	iText实现导出word、PDF和在PDF中加入图片
 */
package com.wl.testaction.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * @author Flair
 * 
 */
public class ExportWord {
	public static void main(String[] args) throws DocumentException,
			IOException {
		// // 创建word文档,并设置纸张的大小
		// Document document = new Document(PageSize.A4);
		// try {
		// RtfWriter2.getInstance(document,new FileOutputStream("E:/word.doc"));
		// document.open();
		// // 设置合同头
		// Paragraph ph = new Paragraph();
		// Font f = new Font();
		// Paragraph p = new Paragraph("出口合同", new Font(Font.NORMAL,
		// 18,Font.BOLDITALIC, new Color(0, 0, 0)));
		// p.setAlignment(1);
		// document.add(p);
		// ph.setFont(f);
		//
		// // 设置中文字体
		// // BaseFont bfFont =
		// // BaseFont.createFont("STSongStd-Light",
		// // "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		// // Font chinaFont = new Font();
		// /*
		// * 创建有三列的表格
		// */
		// Table table = new Table(4);
		// document.add(new Paragraph("生成表格"));
		// table.setBorderWidth(1);
		// table.setBorderColor(Color.BLACK);
		// table.setPadding(0);
		// table.setSpacing(0);
		//
		// /*
		// * 添加表头的元素
		// */
		// Cell cell = new Cell("表头");// 单元格
		// cell.setHeader(true);
		// cell.setColspan(3);// 设置表格为三列
		// cell.setRowspan(3);// 设置表格为三行
		// table.addCell(cell);
		// table.endHeaders();// 表头结束
		//
		// // 表格的主体
		// cell = new Cell("Example cell 2");
		// cell.setRowspan(2);// 当前单元格占两行,纵向跨度
		// table.addCell(cell);
		// table.addCell("1,1");
		// table.addCell("1,2");
		// table.addCell("1,3");
		// table.addCell("1,4");
		// table.addCell("1,5");
		// table.addCell(new Paragraph("用java生成的表格1"));
		// table.addCell(new Paragraph("用java生成的表格2"));
		// table.addCell(new Paragraph("用java生成的表格3"));
		// table.addCell(new Paragraph("用java生成的表格4"));
		// document.add(new Paragraph("用java生成word文件"));
		// document.add(table);
		// document.close();
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (DocumentException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// String file = "E:/test.doc";
		// String contextString = "测试iText导出Word文档";
		//		
		// //设置纸张大小
		// Document document = new Document(PageSize.A4);
		// //建立一个书写器，与document对象关联
		// RtfWriter2.getInstance(document, new FileOutputStream(file));
		// document.open();
		// //设置中文字体
		// BaseFont bfChinese =
		// BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		// //标题字体风格
		// Font titleFont = new Font(bfChinese,12,Font.BOLD);
		// //正文字体风格
		// Font contextFont = new Font(bfChinese,10,Font.NORMAL);
		// Paragraph title = new Paragraph("标题");
		// //设置标题格式对齐方式
		// title.setAlignment(Element.ALIGN_CENTER);
		// title.setFont(titleFont);
		// document.add(title);
		// Paragraph context = new Paragraph(contextString);
		// context.setAlignment(Element.ALIGN_LEFT);
		// context.setFont(contextFont);
		// //段间距
		// context.setSpacingBefore(3);
		// //设置第一行空的列数
		// context.setFirstLineIndent(20);
		// document.add(context);
		// //设置Table表格,创建一个三列的表格
		// Table table = new Table(3);
		// int width[] = {25,25,50};//设置每列宽度比例
		// table.setWidths(width);
		// table.setWidth(90);//占页面宽度比例
		// table.setAlignment(Element.ALIGN_CENTER);//居中
		// table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中
		// table.setAutoFillEmptyCells(true);//自动填满
		// table.setBorderWidth(1);//边框宽度
		// //设置表头
		// Cell haderCell = new Cell("表格表头");
		// haderCell.setHeader(true);
		// haderCell.setColspan(3);
		// table.addCell(haderCell);
		// table.endHeaders();
		//          
		// Font fontChinese = new Font(bfChinese,12,Font.NORMAL,Color.GREEN);
		// Cell cell = new Cell(new Paragraph("这是一个3*3测试表格数据",fontChinese));
		// cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// table.addCell(cell);
		// table.addCell(new Cell("#1"));
		// table.addCell(new Cell("#2"));
		// table.addCell(new Cell("#3"));
		//          
		// document.add(table);
		// document.close();

//		exportImg();
//		exportPdf();
//		exportWord();
		
		exportDoc("E:/test/test.doc"); 

	}

	/**
	 * 导出pdf
	 */
	public static void exportPdf() {
		Document document = null;
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
			Font headFont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小

			// 第一步：创建一个document对象。
			document = new Document();
			// 第二步：创建一个PdfWriter实例，将文件输出流指向一个文件。
			PdfWriter.getInstance(document, new FileOutputStream(
					"E:/test/123.pdf"));
			// 第三步：打开文档。
			document.open();
			Paragraph title = new Paragraph("你好，Pdf！", headFont);
			// 第四步：在文档中增加一个段落。
			document.add(title);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (document != null) {
				// 第五步：关闭文档。
				document.close();
			}
		}

	}

	/**
	 * 导出word
	 */
	public static void exportWord() {
		Document document = null;
		try {
			document = new Document();
			RtfWriter2.getInstance(document, new FileOutputStream(
					"E:/test/word.doc"));
			document.open();
			Paragraph title = new Paragraph("你好，Word！");
			document.add(title);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	/**
	 * 导出图片
	 */
	public static void exportImg() {
		Document document = null;
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
			Font headFont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小

			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(
					"E:/test/img.pdf"));
			// 设定文档的作者
			document.addAuthor("林计钦"); // 测试无效
			document.open();
			document.add(new Paragraph("你好，Img！", headFont));
			// 读取一个图片
			Image image = Image.getInstance("E:/test/1.png");
			// 插入一个图片
			document.add(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	public static void exportDoc(String fileName) {
		try {
			Document doc = new Document();
			RtfWriter2.getInstance(doc, new FileOutputStream(fileName));
			// 打开文档
			doc.open();
			// 设置页边距，上、下25.4毫米，即为72f，左、右31.8毫米，即为90f
			doc.setMargins(90f, 90f, 72f, 72f);

			// 设置标题字体样式，粗体、二号、华文中宋
			Font tfont = DocStyleUtils.setFontStyle("华文中宋", 22f, Font.BOLD);
			// 设置正文内容的字体样式，常规、三号、仿宋_GB2312
			Font bfont = DocStyleUtils.setFontStyle("仿宋_GB2312", 16f,
					Font.NORMAL);

			// 构建标题，居中对齐，12f表示单倍行距
			Paragraph title = DocStyleUtils.setParagraphStyle(
					"测试Itext导出Word文档", tfont, 12f, Paragraph.ALIGN_CENTER);
			// 构建正文内容
			StringBuffer contentSb = new StringBuffer();
			contentSb.append("最近项目很忙，这个是项目中使用到的，所以现在总结一下，以便今后可以参考使用，");
			contentSb.append("2011年4月27日 — 2011年5月20日，对以下技术进行使用，");
			contentSb.append("Itext、");
			contentSb.append("Excel、");
			contentSb.append("Word、");
			contentSb.append("PPT。");

			// 首行缩进2字符，行间距1.5倍行距
			Paragraph bodyPar = DocStyleUtils.setParagraphStyle(contentSb
					.toString(), bfont, 32f, 18f);
			Paragraph bodyEndPar = DocStyleUtils.setParagraphStyle(
					"截至2011年4月28日，各种技术已经完全实现。", bfont, 32f, 18f);
			// 设置空行
			Paragraph blankRow = new Paragraph(18f, " ", bfont);
			Paragraph deptPar = DocStyleUtils.setParagraphStyle("（技术开发部盖章）",
					bfont, 12f, Paragraph.ALIGN_RIGHT);
			Paragraph datePar = DocStyleUtils.setParagraphStyle("2011-04-30",
					bfont, 12f, Paragraph.ALIGN_RIGHT);

			// 向文档中添加内容
			doc.add(title);
			doc.add(blankRow);
			doc.add(bodyPar);
			doc.add(bodyEndPar);
			doc.add(blankRow);
			doc.add(blankRow);
			doc.add(blankRow);
			doc.add(deptPar);
			doc.add(datePar);

			// 最后一定要记住关闭
			doc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
