package com.wl.tools;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: luming
 * Date: 2014-1-3
 * Time: 17:30:07
 * To change this template use File | Settings | File Templates.
 */
public final class StringUtil {
    private StringUtil() {
    }

    /**
     * 判断是否为空，true : 空 false : 不为空
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString());
    }
    
    public static boolean isNullOrEmptyOrZero(Object obj){
    	if(isNumeric(obj.toString())){
    		return 0==Double.parseDouble(obj.toString());
    	}else {
    		return obj == null || "".equals(obj.toString());
		}
    	
    }

    public static String toString(Object obj) {
        if (obj == null)
            return "null";
        else
            return obj.toString();
    }

    @SuppressWarnings("unchecked")
    public static String join(Collection collection, String s) {
        StringBuffer stringbuffer = new StringBuffer();
        for (Iterator it = collection.iterator(); it.hasNext();) {
            stringbuffer.append(it.next());
            if (it.hasNext()) {
                stringbuffer.append(s);
            }
        }

        return stringbuffer.toString();
    }

    public static int toInt(String s) {
        String s1 = "";

        try {
            return Convert.toInt(s).intValue();
        }
        catch (Exception _ex) {
            s1 = "";
        }

        for (int i = 0; i < s.length(); i++) {
            String s2;
            if (!isInteger(s2 = s.substring(i, i + 1)))
                break;
            s1 = (new StringBuilder(String.valueOf(s1))).append(s2).toString();
        }

        return Convert.toInt(s1).intValue();
    }

    public static boolean isInteger(String s) {
        return true;
    }
    
    public static  boolean isNumeric(String str){ 
//	   Pattern pattern = Pattern.compile("[0-9]*"); 
//	   Matcher isNum = pattern.matcher(str);
//	   if( !isNum.matches() ){
//	       return false; 
//	   } 
//	   return true; 
    	return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
