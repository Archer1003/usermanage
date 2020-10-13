package com.cserver.saas.system.user.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportExcel {
	
    public static void exportExcel(String title, String[] headers,String[] fiels, 
    		List<Object> list, OutputStream os) {  
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 15);  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.GREEN.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.WHITE.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont(); 
        font2.setColor(HSSFColor.BLACK.index); //字体颜色
        font2.setFontHeightInPoints((short) 12); 
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0); 
        row.setHeight((short)500);
        for (short i = 0; i < headers.length; i++)  
        {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            cell.setCellValue(text);  
        }  
  
        // 遍历集合数据，产生数据行  
        int index = 0;  
        for (int j = 0; j < list.size(); j++) {
        	HashMap<String,Object> map = (HashMap<String, Object>) list.get(j);
            index++;  
            row = sheet.createRow(index);  
            row.setHeight((short)400);
            // 判断值的类型后进行强制类型转换  
            String textValue = null;  
            List<Object> lists = new ArrayList<Object>();
			for (int q = 0; q < fiels.length; q++) {
				for (String key : map.keySet()) {
					if (key.equals(fiels[q])) {
						lists.add(q, map.get(key));
						break;
					}
				}
			}
			for (int i = 0; i < lists.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Object value = lists.get(i);
				if (value != null) {
					textValue = value.toString();
				}
				if (textValue != null) {
					Pattern p = Pattern.compile("^//d+(//.//d+)?$");
					Matcher matcher = p.matcher(textValue);
					if (matcher.matches()) {
						// 是数字当作double处理
						cell.setCellValue(Double.parseDouble(textValue));
					} else {
						HSSFRichTextString richString = new HSSFRichTextString(textValue);
						cell.setCellValue(richString);
					}
				}
			}
        }  
        try  
        {  
            workbook.write(os);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	//excel里sheet页的名称
    	String title = "疫情上报导出";
    	//excel里列标题
    	String[] headers = { "姓名", "年龄","地址"};
    	//需要导出的字段key
    	String[] fiels = { "name", "age","address"};
    	//要导出的数据List, 不用定义泛型，但是必须存Map<String, Object>
    	List list = new ArrayList();
    	Map<String, Object> map1 = new HashMap<String, Object>();
    	map1.put("id", "1");
    	map1.put("name", "张三");
    	map1.put("age", "20.0");
    	map1.put("address", "西安");
    	Map<String, Object> map2 = new HashMap<String, Object>();
    	map2.put("id", "2");
    	map2.put("name", "李四");
    	map2.put("age", "30.2");
    	map2.put("address", "咸阳");
    	Map<String, Object> map3 = new HashMap<String, Object>();
    	map3.put("id", "3");
    	map3.put("name", "王五");
    	map3.put("age", "40");
    	map3.put("address", "汉中");
    	list.add(map1);
    	list.add(map2);
    	list.add(map3);
    	//文件名
    	String exportName =  "F:/疫情上报导出.xls";
    	OutputStream os = new FileOutputStream(exportName);
    	exportExcel(title, headers, fiels, list, os);
	}
    
    
    
    /**
     * Description: 下载导出文件
     * @param path
     * @param response
     */
  public static void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			 response.addHeader("Content-Disposition", "attachment;filename="  
	                    + new String(filename.getBytes("UTF-8"), "ISO8859-1"));  
	           response.addHeader("Content-Length", "" + file.length());  
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
