package cfmes.util;

public class Stringtoint {
  public double stoi(String a){
	  byte b = Byte.parseByte( a ); 
	  short t = Short.parseShort( a ); 
	  int i = Integer.parseInt( a ); 
	  long l = Long.parseLong( a ); 
	  Float f = Float.parseFloat( a ); 
	  Double d = Double.parseDouble( a);
	  return d;
  }
}
