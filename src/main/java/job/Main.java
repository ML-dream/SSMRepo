package job;

public class Main {
	public static void main(String[] args){
		String s="This is nowcoder";
		System.out.println(reverseString(s));
	}
	public static String reverseString(String iniString) {  
        String str=iniString;  
        StringBuilder str1=new StringBuilder(str);  
        str1.reverse();  
        String str3=str1.toString();  
        return str3;  
    }  
}
