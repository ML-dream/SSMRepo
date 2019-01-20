/**
 * 
 */

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年1月18日 下午4:20:13 
* 类说明 
*/

/*  
*    
* 项目名称：SSM   
* 类名称：tuofengming   
* 类描述：   
* 创建人：dell   
* 创建时间：2019年1月18日 下午4:20:13   
* @version        
*/

public class tuofengming {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String string = "timeYmd";
		char[] charArray = string.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		for (char c : charArray) {
			if(c >= 97 && c <= 122) {
				stringBuffer.append(c);
	        }else {
	        	stringBuffer.append("_");
	        	stringBuffer.append(c);
	        }
			
		}
		String string2 = stringBuffer.toString();
		System.out.println(string2);
	}

}
