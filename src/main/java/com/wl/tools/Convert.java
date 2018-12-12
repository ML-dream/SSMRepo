package com.wl.tools;

/**
 * 
 * 类型转换工具类
 * 
 * @author  ZHULI
 * @version  [版本号, Apr 24, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class Convert
{
    private Convert(){}
    
    public static String toString(Object obj)
    {
        if (obj == null)
            return "";
        else
            return obj.toString();
    }
    
    public static Integer toInt(String obj)
    {
        if (obj == null || "".equals(obj))
        {
            return Integer.valueOf(0);
        }

        return Integer.valueOf(obj.toString());
    }
    
    public static Long toLong(Object obj)
    {
        if (obj == null)
            obj = Integer.valueOf(0);
        return Long.valueOf(Long.parseLong(obj.toString()));
    }
    
    public static Float toFloat(Object obj)
    {
        if (obj == null)
            obj = Integer.valueOf(0);
        return Float.valueOf(Float.parseFloat(obj.toString()));
    }
    
    public static Double toDouble(Object obj)
    {
        if (obj == null)
            obj = Integer.valueOf(0);
        return Double.valueOf(Double.parseDouble(obj.toString()));
    }
    
    public static Short toShort(Object obj)
    {
        if (obj == null)
            obj = Integer.valueOf(0);
        return Short.valueOf(Short.parseShort(obj.toString()));
    }
    
    public static Boolean toBoolean(Object obj)
    {
        if (obj == null)
            obj = Boolean.valueOf(false);
        return Boolean.valueOf(Boolean.parseBoolean(obj.toString()));
    }
    
    public static Character toChar(Object obj)
    {
        if (obj == null)
            obj = "";
        return (Character)obj;
    }
}
