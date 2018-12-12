package cfmes.util;
import cfmes.util.Datesubday;

public class Test {
  public static void main(String args[]){
	/*
	String a="10";
	  
	  byte b = Byte.parseByte( a ); 
	  short t = Short.parseShort( a ); 
	  int i = Integer.parseInt( a ); 
	  long l = Long.parseLong( a ); 
	  Float f = Float.parseFloat( a ); 
	  Double d = Double.parseDouble( a);
	  System.out.println(d);
  */
	 /*   float time1 = (float) 15.5;
	    
	    Math  m = null ;
		double timemid;
        timemid = m.ceil(time1);
        int daynum = (int)m.ceil(timemid/8);
        System.out.println(daynum);
  */
	Datesubday dsd = new Datesubday();
	System.out.println(dsd.dsd("2014-09-15", -16));
  }
}
