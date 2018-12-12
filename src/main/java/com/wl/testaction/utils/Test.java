/**
 * 项目名称: work
 * 创建日期：2016-6-22
 * 修改历史：
 *		1.[2016-6-22]创建文件 by Flair
 */
package com.wl.testaction.utils;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		PoiExcelExport pee = new PoiExcelExport("E:/test.xls", "sheet1");
		// 数据
		List<Man> dataList = new ArrayList();
		Man man1 = new Man("张三", 20, "男", (float) 10000.8);
		Man man2 = new Man("李四", 21, "男", (float) 11000.8);
		Man man3 = new Man("王五", 22, "女", (float) 1200.8);
		Man man4 = new Man("赵六", 23, "男", (float) 13000.8);
		Man man5 = new Man("田七", 24, "男", (float) 14000.8);
		Man man6 = new Man();
		man6.setName("老八");
		dataList.add(man1);
		dataList.add(man2);
		dataList.add(man3);
		dataList.add(man4);
		dataList.add(man5);
		dataList.add(man6);
		// 调用
		String titleColumn[] = { "name", "sex", "idCard", "salary", "" };
		String titleName[] = { "姓名", "性别", "身份证号", "月薪", "年薪" };
		int titleSize[] = { 13, 13, 13, 13, 13 };
		// 其他设置 set方法可全不调用
		String colFormula[] = new String[5];
		colFormula[4] = "D@*12"; // 设置第5列的公式
		pee.setColFormula(colFormula);
		pee.setAddress("A:D"); // 自动筛选

		pee.wirteExcel(titleColumn, titleName, titleSize, dataList);
	}

}
