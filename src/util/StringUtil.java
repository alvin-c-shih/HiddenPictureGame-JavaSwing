package util;

public class StringUtil {	
	public static String repeat(String s,int n){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;++i){sb.append(s);}
		return sb.toString();
	}
	public static String repeat(char c,int n){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;++i){sb.append(c);}
		return sb.toString();
	}
	public static String pad(String s,int n){
		return repeat(' ',n-s.length())+s;
	}
}
