package cfmes.util;

import java.util.*;
import java.text.*;
import java.lang.*;
import sun.io.*;

public class DealString{
	public String PickUp_Str(String str){
		String s = str;
		if(str.indexOf('(')>0){
			s = str.substring(0, str.indexOf("("));
		}
		if(str.indexOf('（')>0){
			s = str.substring(0, str.indexOf("（"));
		}
		boolean flag = false;
		String ss ="";
		  for (int i = 0; i < s.length(); i++) {
			  if((str.charAt(i) >= '0' && str.charAt(i) <= '9')
				  || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z' && str.charAt(i) != 'x')
				  || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z' && str.charAt(i) != 'X')){
				  flag=true;
			  }
			  if(flag){
				    ss=ss+s.charAt(i);
				    flag=false;
			  }
		  }
		  return ss;
	}
	public String Chg_Str(String str){
		String s = str;
		String a = "×";
		String c = "";
		
		if(str.contains(a)){
			do{
				c = s.substring(0, s.indexOf(a)) +"*"+ s.substring(s.indexOf(a)+1);
				s = c;
				//System.out.println("c:"+c);
			}while(c.contains(a));
		}

		if(s.contains("x") || s.contains("X")){
			s = s.replaceAll("X", "*");
			s = s.replaceAll("x", "*");
		}
		if(s.startsWith("HB") && isNumeric(s.substring(2, 6)) && ".".equals(s.substring(6, 7))){
			s = s.substring(0,6)+"-"+s.substring(7);
		}
        return s;
	}
	public long makeID ( int uid )
	{
		//�û�ռ5λ
		uid += 10000 ;
		
		//ʱ��ռ9λ:�룬ȥ����һλ
		java.util.Date time = new java.util.Date();
		long second = time.getTime() / 1000 ;
		String str = second + "" ;
		str = str.substring(1,str.length());

		//Ⱥ��ռ5λ
		str = uid + str + 10000 ;
          
		return Long.parseLong(str);
	}
	/**�ж��Ƿ�Ϊ����""*/
	public boolean isNumeric(String str){
		//System.out.println(str);
		for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		    
		   }
		}
		return true;
		}
	/**��nullת��Ϊ""*/
	public String toString(String str)
	{
		if(str==null)str = "";
		if(str.equals("null"))str = "";
		str = str.trim();
		return str;
	}
	/**ת�����룬�ڵ�ַ������*/
	public String toURL(String str)
	{
		try 
		{
			if(str==null)
				str = "";
			else
				str = java.net.URLEncoder.encode(str,"utf-8");
		}catch (Exception e) {System.out.println("DealString::toURL(String)����ʱ���?����Ϊ��"+e);}
		return str;
	}
	/**ת������*/
	public String toGBK(String str)
	{   //System.out.println(str);
		try 
		{
			if(str==null)
				str = "";
			else
				str = new String(str.getBytes("ISO8859-1"),"utf-8");
				//str=new String(java.net.URLDecoder.decode(str,"utf-8").getBytes("ISO-8859-1"),"utf-8"); 
		}catch (Exception e) {System.out.println("DealString::toGBK(String)����ʱ���?����Ϊ��"+e);}
		return str;
	}

	public String toMKByte(int size)
	{
		if(size>(1024*1024)){return ((float)size/(1024*1024)+"").substring(0,4)+"MB";}
		else if(size>1024){return ((float)size/1024+"").substring(0,4)+"KB";}
		else return size+"B";
	}

	/**UTF8*/
	public String toUtf8String(String src)
	{
		byte[] b = src.getBytes();
		char[] c = new char[b.length];
		for(int i=0;i<b.length;i++)
		{
			c[i] = (char)(b[i]&0x00FF);
		}
		return new String(c);
	}

	public String toASCII(String str)
	{
		try 
		{
			if(str==null)
				str = "";
			else 
				str=new String(str.getBytes("utf-8"),"ISO-8859-1"); 
		}catch (Exception e) {System.out.println("DealString::toGBK(String)����ʱ���?����Ϊ��"+e);}
		return str;
	}
	/**�ָ��ַ�*/
	public String[] splitStr(String str,char c)
	{
		str+=c;
		int n=0;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)==c)n++;
		}

		String out[] = new String[n];

		for(int i=0;i<n;i++)
		{
			int index = str.indexOf(c);
			out[i] = str.substring(0,index);
			str = str.substring(index+1,str.length());
		}
		return out;
	}

	/**ȡ��ϵͳʱ��*/
	public String getDateTime()
	{
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
		String time = f.format(new java.util.Date());
		return time;
	}

	/**�滻�ַ�*/
	public String Replace(String source, String oldString, String newString)
	{ 
       StringBuffer output = new StringBuffer(); 

       int lengthOfSource = source.length();   // Դ�ַ��� 
       int lengthOfOld = oldString.length();   // ���ַ��� 
       int posStart = 0;   // ��ʼ����λ�� 
       int pos;            // ���������ַ��λ�� 

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

	/**ȡ���������ڵ�����֮��*/
	public long getDaysInterval(Date d1,Date d2)
	{
		return (d2.getTime()-d1.getTime())/86400000;
	}
	
	/**���ַ��ʽ��Ϊ�̶�����*/
	public String toLengthStr(String instr,int len)
	{
		int n = instr.length();
		for(int i=0;i<(len-n);i++)
		{
			instr = " "+instr;
		}
		return instr;
	}
	/**���ַ��ʽ��Ϊ�̶�����(�ұ߲��ո�)*/
	public String toLengthStrRight(String instr,int len)
	{
		int n = instr.length();
		for(int i=0;i<(len-n);i++)
		{
			instr = instr+" ";
		}
		return instr;
	}
	/*ASCIIת��Ϊ�ַ�*/
	public String AsciiToChineseString(String s) 
	{
		char[] orig = s.toCharArray();
		byte[] dest = new byte[orig.length];
		for (int i=0;i<orig.length;i++)
		dest[i] = (byte)(orig[i]&0xFF);
		try 
		{
			ByteToCharConverter toChar = ByteToCharConverter.getConverter("utf-8");
			return new String(toChar.convertAll(dest));
		}
		catch (Exception e) 
		{
			System.out.println("DealString::AsciiToChineseString(String)����ʱ���?����Ϊ��"+e);
			return s;
		}
	}

	/**�ַ�ת��ΪASCII*/
	public String ChineseStringToAscii(String s) 
	{
		try 
		{
			CharToByteConverter toByte = CharToByteConverter.getConverter("utf-8");
			byte[] orig = toByte.convertAll(s.toCharArray());
			char[] dest = new char[orig.length];
			for (int i=0;i<orig.length;i++)
				dest[i] = (char)(orig[i] & 0xFF);
			return new String(dest);
		}
		catch (Exception e) 
		{
			System.out.println("DealString::ChineseStringToAscii(String)����ʱ���?"+e);
			return s;
		}
	}
	
	/**Unicodeת����GB��Դ��*/
	public String UnicodetoGB(String s)  
	{  
		StringBuffer  sb  =  new  StringBuffer();  
		boolean  escape  =  false;  
		for(int  i=0;  i<s.length();  i++)  
		{  
			char  c  =  s.charAt(i);  
			switch  (c)  
			{  
				case  '\\': escape = true; break;  
				case  'u':  
				case  'U':  
				if(escape)  
				{  
					try  
					{  
						 sb.append((char)Integer.parseInt(s.substring(i+1,i+5),16));  
						 escape  =  false;  
					}  
					catch(NumberFormatException e)  
					{  System.out.println("DealString::UnicodetoGB(String)����ʱ���?"+e+"���׳��µ�IllegalArgumentException�쳣.");
						 throw new IllegalArgumentException();  
					}  
					i += 4;  
				}  
				else  
				{    
					sb.append(c);  
				}  
				break;  
				default:sb.append(c); break;
			}  
		} 
		return  sb.toString();  
	} 
	/*��str���ظ���ȥ��*/
	public String strDistinct(String str)
	{
		String[] strArr=str.split(",");
		String   strAim = ",";
		for(int i=0;i<strArr.length;i++)
		{
			if(strArr[i].equals(""))
				continue;
			if (strAim.indexOf(","+strArr[i]+",")==-1)
			{
				strAim = strAim + strArr[i] + ",";
			}
		}
		if(!strAim.equals(","))
		strAim = strAim.substring(1,strAim.length()-1);
		else strAim = "";
		return strAim;
	}

	/**�ַ�ת��ΪASCII*/
	public int toASCII(char c)
	{
		int i = c;
		return i;
	}

	/**ȡ���ַ��ֽڳ���*/
	public int byteLength(String str)
	{
		return ((str.getBytes()).length);
	}

	/**ȡ���ַ��ͷ��ʼָ�������Ӵ�*/	
	public String strByteCopy(String str,int nEnd)
	{
		if(nEnd==0)
			return "";
		byte[] byteStr=str.getBytes();
		int k=byteStr.length;
		String strSub=new String(byteStr,0,nEnd<k?nEnd:k);
		if (strSub.length()==0) strSub=new String(byteStr,0,nEnd-1);
		return strSub;
	}
	/**ȡ���ַ��ĩβ��ǰָ�������Ӵ�*/	
	public String backStr(String str,int n)
	{
		if(n==0)return "";
//		byte[] byteStr=str.getBytes();
		int k=str.length();
		String strSub=str.substring(n<k?k-n:0,k);
		//if (strSub.length()==0) strSub=new String(byteStr,0,n-1);
		return strSub;
	}
        public boolean strMatch(String motherStr,String childStr)
        {
          boolean matched=false;
          int mLength=motherStr.length();
          int cLength=childStr.length();
          int starWith;
          if(mLength>=cLength){
            starWith=mLength-cLength;
            for(int i=0;i<=starWith;i++){
              matched=motherStr.startsWith(childStr,i);
              if(matched)break;
            }
          }
          return matched;
        }

	public Vector simplify(String[] str){
		Vector vect = new Vector();
		for(int i=0;i<str.length;i++)
			vect.add(str[i]);
		for(int i=0;i<vect.size();i++){
			String[] s1 = ((String)vect.get(i)).split("_");
			for(int j=i+1;j<vect.size();){
				boolean out = true;
				String[] s2 = ((String)vect.get(j)).split("_");
				for(int k=0;k<s1.length;k++){
					if(!s1[k].equals(s2[k])&&!s1[k].equals("0")){
						out = false;
						break;
					}
				}
				if(out)
					vect.remove(j);
				else
					j++;
			}
		}
		return vect;
	}
	public Vector simplify(Vector vstr){
		Vector vect = new Vector();
		for(int i=0;i<vstr.size();i++)
			vect.add(vstr.get(i));
		for(int i=0;i<vect.size();i++){
			String[] s1 = ((String)vect.get(i)).split("_");
			for(int j=i+1;j<vect.size();){
				boolean out = true;
				String[] s2 = ((String)vect.get(j)).split("_");
				for(int k=0;k<s1.length;k++){
					if(!s1[k].equals(s2[k])&&!s1[k].equals("0")){
						out = false;
						break;
					}
				}
				if(out)
					vect.remove(j);
				else
					j++;
			}
		}
		return vect;
	}
	

	public static void main(String args[]) throws Exception
	{
		DealString mb = new DealString();
		System.out.println("begin\r\n\r\n");
		System.out.println(mb.makeID(2345));
		System.out.print("\rggggggggggggggg");
		System.out.println("\rend                 ");
		String str = "asdb34��5��6";
		String s = str;
		String a = "��";
		String c = "";
		//System.out.println("s1:"+s);
		if(str.contains(a)){
			//System.out.println("s2:"+s);
			do{
				c = s.substring(0, s.indexOf(a)) +"*"+ s.substring(s.indexOf(a)+1);
				s = c;
				System.out.println("c:"+c);
			}while(c.contains(a));
			//s = c;
			//System.out.println("s3:"+s);
		}
		//System.out.println("s4:"+s);
	}
}