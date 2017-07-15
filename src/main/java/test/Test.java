package test;

import util.RegexUtil;

import java.io.*;
import java.util.Iterator;


/**
 * Created by Weiguang Chen(chen2621978@gmail.com) on 2017/7/13 21:48.
 * This Class is a Test class that test some syntax rules and the spacial feature
 * If you debug "assert“ keyword，you should operate as follow:
 * compile:javac -source 1.7 test/Test.java
 * run:java -ea test.Test
 */

//abstract class have main method
abstract 
public class Test {
	//synchronized Modify   main
 	synchronized public static void main(String[] args) {
		int a = 1;
		main(a);
		//testString();
		//testRegular();
		//testSwitch();
		//testAssert();
		//testSerializable();
		//testTryCatch();
        //testBase();
		testRegex();

		while(true) {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 17:38
	 * @param
	 * @return void
	 * @description testRegex
	 */
	public static void testRegex() {
		//System.out.println(RegexUtil.checkMobile("18826490653"));
		System.out.println(RegexUtil.checkChinese("asdf"));
		System.out.println(RegexUtil.checkChinese("sdf哈哈sf"));
	}

    /**
     * @Author Weiguang Chen(chen2621978@gmail.com) on 2017/7/13 21:57
     * @description overloaded main
     */
	synchronized final public static void main(int a){
		System.out.println(a);
	}


    /**
     * @Author Weiguang Chen(chen2621978@gmail.com) on 2017/7/13 21:56
     * @description test Assert
     * 	compile:javac -source 1.7 test/Test.java
     *  run: java -ea tets/Test
     */
	public static void testAssert() {
		System.out.println("testAssert start!");
		int a = 1;
		//when a<1 will throw AssertionError and print "error !!!"
		assert (a > 0) : "error!!";
	}

    /**
     * @Author Weiguang Chen(chen2621978@gmail.com) on 2017/7/13 21:58
     * @description test basic data type
     */
	public static void testBase() {
		//Short s = 98S; //error
		//float z = 1.0; //error
		int t = "abc".length();
		//char c = 175c; //error
        int a = 5;
        // output is : "value is  = 9.0" , ps: 10.9d is a double var,so 9i is convert to 9.0d
        System.out.println("value is  = " + ( (a < 5 )? 10.9 : 9 ));

    }


	public static void testTryCatch() {
		try{//without catch 
			int i = 0;

		}finally{
			System.out.println("finally!");
		}

        try {// need to catch exception when the method use "throws"
            testException();
        }catch (Exception ex){
		    ex.printStackTrace();
        }

	}

    /**
     * @Author Weiguang Chen(chen2621978@gmail.com) on 2017/7/14 10:41
     * @description
     */
    public static void testException() throws Exception{
        int i = 0;
        try {
            // need to catch Exception when use to "throw new Exception"
            throw new Exception();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(i);
    }

	public static void testSwitch() {
		int b = 0;
		long l = 1;
		// Java can't convert longs to int automatically
		switch((int)l){
			case 1:
				b = 1;
				break;
			default:
				b = 2;
                }
		System.out.println(b);
	}

	/**
	 * @Author Weiguang Chen(chen2621978@gmail.com) on 2017/7/15 14:21
	 * @description 串行化测试
	 */
	public static void testSerializable() {
	   //Serializable
		Animal animal = new Animal("Cat");
		//transient don't Serialize
	    animal.printName();
		//test polymorphism, override toString
		Object o1;
		o1 = animal;
		System.out.println(o1.toString());
		try{
            animal.setName("dog");
			//animal.serial("./animal.out");
			Animal an = Animal.reSerial("./animal.out");
			System.out.println(an instanceof Animal);
			an.printName();
		}catch(Exception e){
			e.printStackTrace();	
		}
		
	}

	public static void testString()  {
		String[] charsetNames={	
				"utf-8","utf-16","UTF-16BE","UTF-16LE","UTF-32",
				"UTF-32BE","UTF-32LE","unicode","GBK",
				"GB2312","GB18030","ISO8859-1","BIG5","ASCII"  }; 
		for(int i=0;i<charsetNames.length;i++){
			printByteLength(charsetNames[i]);
		}

		String str = "aaaa";
		try {
			System.out.println(str + " default, bytes length:" + str.getBytes().length);
			System.out.println(str + " gbk, bytes length:" + str.getBytes("gbk").length);
			System.out.println(str + " utf-8, bytes length:" + str.getBytes("utf-8").length);
		}catch (Exception e) {
			e.printStackTrace();
		}
		byte[] b  = str.getBytes();
		int i = 4 ;
		for (int j = 0; j< b.length; j++){ System.out.print(b[j] + " ");}
		int j = 0;
		if (b[i - 1] > 0 ) j = i;
		System.out.println(new String(b, 0, j));
			
	}
	private static void printByteLength(String charsetName) {
		String a="a";
		String b="aa";
		try {
			System.out.println(charsetName + " English coding bytes:" + a.getBytes(charsetName).length);
			System.out.println(charsetName + " Chines coding bytes:" + b.getBytes(charsetName).length);
			System.out.println();
		} catch (UnsupportedEncodingException e) {
			System.out.println("ilegal coding!");
		}
	
	}
	
	public static void testRegular() {
		String src = "abc123n4";
		String dsrc = "";
		String reg1 = "[^0-9]";
		dsrc = src.replaceAll(reg1, "");
		System.out.println(dsrc);
		src = "2006-04-15 02:31:04";
		dsrc = src.replaceAll(reg1, "");
		System.out.println(dsrc);

	}
	
}
