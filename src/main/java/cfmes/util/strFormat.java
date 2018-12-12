package cfmes.util;


public class strFormat {

	/**
	 * 字符串替换，将 source 中的 oldString 全部换成 newString
	 *
	 * @param source 源字符串
	 * @param oldString 老的字符串
	 * @param newString 新的字符串
	 * @return 替换后的字符串
	 * 用于输入的表单字符串转化成HTML格式的文本
	 */
	public static void main(String[] args) {
	}

	public static String Replace(
		String source,
		String oldString,
		String newString) {
		StringBuffer output = new StringBuffer();

		int lengthOfSource = source.length(); // 源字符串长度
		int lengthOfOld = oldString.length(); // 老字符串长度

		int posStart = 0; // 开始搜索位置
		int pos; // 搜索到老字符串的位置

		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));

			output.append(newString);
			posStart = pos + lengthOfOld;
		}

		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}

		return output.toString();
	}

	/*
	public static String ReplaceIgnoreCase(String source, String oldString, String newString) {
	}
	*/

	/**
	 * 将字符串格式化成 HTML 代码输出
	 * 只转换特殊字符，适合于 HTML 中的表单区域
	 *
	 * @param str 要格式化的字符串
	 * @return 格式化后的字符串
	 */
	public static String toHtmlInput(String str) {
		if (str == null)
			return null;

		String html = new String(str);

		html = Replace(html, "&", "&amp;");
		html = Replace(html, "<", "&lt;");
		html = Replace(html, ">", "&gt;");

		return html;
	}

	/**
	 * 将字符串格式化成 HTML 代码输出
	 * 除普通特殊字符外，还对空格、制表符和换行进行转换，
	 * 以将内容格式化输出，
	 * 适合于 HTML 中的显示输出
	 *
	 * @param str 要格式化的字符串
	 * @return 格式化后的字符串
	 */
	public static String toHtml(String str) {
		if (str == null)
			return null;

		String html = new String(str);

		html = toHtmlInput(html);
		html = Replace(html, "\r\n", "\n");
		html = Replace(html, "\n", "<br>\n");
		html = Replace(html, "\t", "    ");
		html = Replace(html, "  ", " &nbsp;");

		return html;
	}

	/**
	 * 将普通字符串格式化成数据库认可的字符串格式
	 *
	 * @param str 要格式化的字符串
	 * @return 合法的数据库字符串
	 */
	public static String toSql(String str) {
		String sql = new String(str);
		return Replace(sql, "'", "''");
	}

	/*
	public static void main(String[] args) {
	    String s = "<html>    ddd";
	    Format f = new Format();
	    System.out.println(f.toHtmlInput(s));
	    System.out.println(f.toHtml(s));
	}
	*/
}