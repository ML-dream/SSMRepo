package com.xm.testaction.qualitycheck;

public class StaticTest1 {
	private static int a = 1;
	public static void test1(){
		int b =0;
		System.out.println(a);
		a = 2;
		System.out.println(a);
	}
	public static void test2(){
		System.out.println(a);
		a =3;
		System.out.println(a);
	}
}
