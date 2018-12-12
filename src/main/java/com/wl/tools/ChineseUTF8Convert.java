/**
 * 项目名称: work
 * 创建日期：2016-7-15
 * 修改历史：
 *		1.[2016-7-15]创建文件 by Flair
 */
package com.wl.tools;
import java.io.UnsupportedEncodingException;

import org.apache.commons.beanutils.Converter;

/**
 * @author Flair
 *
 */
@SuppressWarnings({ "rawtypes" })
public class ChineseUTF8Convert implements Converter{

	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		} else {
			String val = "";
			try {
				val = ChineseCode.toUTF8(value.toString());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return val;
		}
		
		
	}

}
