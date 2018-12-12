/**
 * 项目名称: work
 * 创建日期：2016-6-22
 * 修改历史：
 *		1.[2016-6-22]创建文件 by Flair
 */
package com.wl.testaction.utils;
import java.awt.Color;  
import java.io.IOException;  
import java.lang.reflect.Field;  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.util.Collection;  
import java.util.Iterator;  
  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;  
import org.apache.poi.hssf.usermodel.HSSFComment;  
import org.apache.poi.hssf.usermodel.HSSFFont;  
import org.apache.poi.hssf.usermodel.HSSFPatriarch;  
import org.apache.poi.hssf.usermodel.HSSFRichTextString;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.hssf.util.HSSFColor;  
  
 
import com.lowagie.text.Cell;  
import com.lowagie.text.Document;  
import com.lowagie.text.DocumentException;  
import com.lowagie.text.Element;  
import com.lowagie.text.HeaderFooter;  
import com.lowagie.text.PageSize;  
import com.lowagie.text.Rectangle;  
import com.lowagie.text.pdf.PdfPCell;  
import com.lowagie.text.pdf.PdfPTable;  
import com.lowagie.text.pdf.PdfWriter;  
  
public class ExportUtils<T> { 
	
	public static void main(String[] args) {
		ExportUtils<String> exportUtils = new ExportUtils<String>();
		PdfEntity<String> pdfEntity = new PdfEntity<String>();
		exportUtils.exportPdf(pdfEntity);
		
	}
      
    @SuppressWarnings("unchecked")  
    public void exportPdf(PdfEntity<T> pdfEntity){  
          
        String pageSize = pdfEntity.getPageSize();  
          
        Rectangle rectangle = new Rectangle(getPdfPageSize(pageSize));  
        Document document = new Document(rectangle,pdfEntity.getMargin_bottom(),pdfEntity.getMargin_left(),pdfEntity.getMargin_right(),pdfEntity.getMargin_top());  
          
        try {  
            PdfWriter.getInstance(document, pdfEntity.getOs());  
            //基本配置信息  
            document.addTitle(pdfEntity.getTitle());  
            document.addAuthor(pdfEntity.getAuthor());  
            if(pdfEntity.isCreationDate()){  
                  
                document.addCreationDate();  
            }  
            document.addCreator(pdfEntity.getCreator());  
            document.addKeywords(pdfEntity.getKeywords());  
            document.addSubject(pdfEntity.getSubject());  
              
            //定义页眉和页脚  
            String pageHeader = pdfEntity.getPageHeader();  
            String pageFooter = pdfEntity.getPageFooter();  
            HeaderFooter header = null;  
            HeaderFooter footer = null;  
            if(pageHeader != null){  
                  
                header = new HeaderFooter(new PdfParagraph(pageHeader,14,true),false);  
                header.setBorderWidth(0);  
                header.setAlignment(Element.ALIGN_CENTER);  
            }  
            if(pageFooter != null){  
                  
                footer = new HeaderFooter(new PdfParagraph(pageFooter,14,true),false);  
                footer.setBorderWidth(0);  
                footer.setAlignment(Element.ALIGN_CENTER);  
            }  
            document.setHeader(header);  
            document.setFooter(footer);  
            //打开pdf文档  
            document.open();  
              
            String[] headers = pdfEntity.getHeaders();  
            //创建多少列的表格  
            PdfPTable table = new PdfPTable(headers.length);  
            table.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.setWidthPercentage(16 * headers.length);  
              
            //产生表格栏  
            PdfPCell cell = null;  
            for (int i = 0; i < headers.length; i++) {  
  
                cell = new PdfPCell(new PdfParagraph(headers[i], 14,true));  
                cell.setHorizontalAlignment(Cell.ALIGN_CENTER);  
                cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);  
                cell.setBackgroundColor(Color.cyan);  
                cell.setBorderColor(Color.green);  
                table.addCell(cell);  
             }  
            //装载数据行  
            Collection<T> dataset = pdfEntity.getDataset();  
            Iterator<T> it = dataset.iterator();  
            while(it.hasNext()){  
                  
                T t = it.next();  
                Class tClass = t.getClass();  
                Field[] fields = tClass.getDeclaredFields();  
                for (int i = 0; i < fields.length; i++) {  
                      
                    String fieldName = fields[i].getName();  
                    String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);  
                    Method getMethod = tClass.getMethod(getMethodName, new Class[]{});  
                    Object value = getMethod.invoke(t, new Object[]{});  
                    value = value == null ? "" : value;  
                    if(value != null){  
                          
                        cell = new PdfPCell(new PdfParagraph(value.toString(),12,false));  
                        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);  
                        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);  
                        cell.setBorderColor(Color.green);  
                        table.addCell(cell);  
                    }  
                }  
            }  
            document.add(table);  
            document.close();  
              
        } catch (DocumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (SecurityException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (NoSuchMethodException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
    @SuppressWarnings("unchecked")  
    public void exportExcel(ExcelEntity excelEntity){  
          
        String title = excelEntity.getTitle();  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
        //设置默认列宽度为15个字节  
        sheet.setDefaultColumnWidth(15);  
        //生成一个标题样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        //设置居中  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        //设置填充前景色和背景色  
        style.setFillForegroundColor(HSSFColor.YELLOW.index);  
        style.setFillBackgroundColor(HSSFColor.WHITE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        //设置线条宽度  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font.setColor(HSSFColor.VIOLET.index);  
        font.setFontHeightInPoints((short)12);  
        //字体应用到样式  
        style.setFont(font);  
          
        //生成主体样式  
        HSSFCellStyle bodyStyle = workbook.createCellStyle();  
        //设置居中  
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        //设置填充前景色和背景色  
        bodyStyle.setFillForegroundColor(HSSFColor.WHITE.index);  
        bodyStyle.setFillBackgroundColor(HSSFColor.WHITE.index);  
        bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        //设置线条宽度  
        bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //生成一个字体  
        HSSFFont bodyFont = workbook.createFont();  
        bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        bodyFont.setColor(HSSFColor.VIOLET.index);  
        bodyFont.setFontHeightInPoints((short)12);  
        //字体应用到样式  
        bodyStyle.setFont(bodyFont);  
          
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0,0,0,(short)4,2,(short)6,5));  
        comment.setString(new HSSFRichTextString(excelEntity.getCommentContent()));  
        comment.setAuthor(excelEntity.getCommentAuthor());  
          
        //产生标题行  
        String[] headers = excelEntity.getHeaders();  
        int index = 0;  
        HSSFRow row = sheet.createRow(index);  
        HSSFCell cell = null;  
        for (short i = 0; i < headers.length; i++) {  
  
          cell = row.createCell(i);  
          cell.setCellStyle(style);  
          HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
          cell.setCellValue(text);  
        }  
        //遍历集合,产生数据行  
        Collection<T> dataset = excelEntity.getDataset();  
        Iterator<T> it = dataset.iterator();  
        while(it.hasNext()){  
              
            index++;  
            row = sheet.createRow(index);  
            T t = it.next();  
            Class tClass = t.getClass();  
            Field[] fields = tClass.getDeclaredFields();  
            for (int i = 0; i < fields.length; i++) {  
                  
                cell = row.createCell(i);  
                cell.setCellStyle(style);  
                String fieldName = fields[i].getName();  
                String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);  
                try {  
                    Method getMethod = tClass.getMethod(getMethodName, new Class[]{});  
                    Object value = getMethod.invoke(t, new Object[]{});  
                    value = value == null ? "" : value;  
                    if(value != null){  
                          
                        HSSFRichTextString textString = new HSSFRichTextString(value.toString());  
                        cell.setCellValue(textString);  
                        cell.setCellStyle(bodyStyle);  
                    }  
                } catch (SecurityException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (NoSuchMethodException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (IllegalArgumentException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (InvocationTargetException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        }  
        try {  
            //写出excel  
            workbook.write(excelEntity.getOs());  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
    }  
    public void exportWord(){  
          
          
    }  
    public Rectangle getPdfPageSize(String pageSize){  
          
        Rectangle pSize = null;  
        if("A4".equals(pageSize)){  
              
            pSize = PageSize.A4;  
        }else if("A3".equals(pageSize)){  
              
            pSize = PageSize.A3;  
        }else if("A2".equals(pageSize)){  
              
            pSize = PageSize.A2;  
        }else if("A1".equals(pageSize)){  
              
            pSize = PageSize.A1;  
        }else{  
              
            pSize = PageSize.A4;  
        }  
          
        return pSize;  
    }  
}