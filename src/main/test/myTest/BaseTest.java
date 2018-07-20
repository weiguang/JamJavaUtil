package myTest;

import com.okayjam.test.Animal;
import com.okayjam.test.IfTest;
import com.okayjam.util.FileUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;


/**
 * Created by Weiguang Chen(chen2621978@gmail.com) on 2017/7/13 21:48.
 * This Class is a Test class that test some syntax rules and the spacial feature
 * If you debug "assert“ keyword，you should operate as follow:
 * compile:javac -source 1.7 test/Test.java
 * run:java -ea test.Test
 */

//abstract class have main method
//abstract
public class BaseTest implements IfTest {

	private static final Logger LOG = Logger.getLogger(BaseTest.class);

	//synchronized Modify   main
 	synchronized public static void main(String[] args) {
		// must add this code before log
		BasicConfigurator.configure();
		new BaseTest().testMain();
	}

	/**
	 *  測試函數
	 *
	 * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/26 15:43
	 */
	public void testMain() {
		//int a = 1;
		//main(a);
		//testString();
		//testRegular();
		//testSwitch();
		testAssert();
		//testSerializable();
		//testTryCatch();
		//testBase();
		//testRegex();
		//testFileUtil();

		//new Test().testInterface();

		//LOG.debug("test log4j, debug!!!!!!!!!!!");
		//LOG.info("hello this is log4j info log");
		//LOG.error("test log4j, error!!!!!!!!!!!");

		//testFunction(1,2,3);

		//testGenericity();

		while(true) {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * @author Weiguang Chen(chen2621978@gmail.com) on 2017/7/13 21:57
	 *  overloaded main
	 */
	synchronized  public static void main(int a){
		System.out.println(a);
	}

	/**
	 * 泛型
	 */
	public <T> void  testGenericity() {
		MyArrayList list = new MyArrayList(Animal.class);
		list.setValue(0,"cat1");

		list.getValue(0);
	}
	class MyArrayList<V extends Animal>  {
		private V[] backingArray;
		private Class<V> elementType;
		public MyArrayList(Class<V> elementType) {
			this.elementType = elementType;
			backingArray = (V[]) Array.newInstance(elementType, 10);
		}
		public void setValue(int  index, String value){
			if(backingArray[index] == null){
				Object o = Array.newInstance(elementType,1);
				backingArray[index] = (V) o;
			}
			backingArray[index].setName(value);
		}
		public void getValue(int index) {
			backingArray[index].printName();
		}
	}

	/**
	 *  测试接口
	 *
	 * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/19 21:57
	 * @param
	 * @return
	 */
	public void testInterface() {
		fun();
	}

	/**
	 * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 20:58
	 *
	 */
	public static void testFileUtil() {
		//FileUtil.mkdirs("temp/temp");
		//FileUtil.createNewFile("temp/tt.txt", "123456\n123456\n");
		try {
			//TextUtil.readFile("temp/t2.txt", "GBK");
			//FileUtil.compressedFileZip("temp/t2.txt", null);
			//FileUtil.compressedFile("temp\\tt.txt", ".");
			//FileUtil.compressedFile("哈哈", ".");
			FileUtil.unZipFiles(new File("哈哈.zip"), "d:\\1\\");
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * test varargs,
	 * @param args
	 */
	public static void testFunction (int... args) {
		for (int arg : args) {
			System.out.println(arg);
		}
	}

	/**
	 * testRegex
	 *
	 * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 17:38
	 */
	//@org.junit.Test
	public void testRegex() {
		System.out.println("123gyu13y1g3yu1".replaceAll("\\D","")); //去掉字符串中的数字

		/*
		System.out.println(RegexUtil.checkMobile("18826490653"));
		System.out.println(RegexUtil.checkChinese("asdf"));
		System.out.println(RegexUtil.checkChinese("sdf哈哈sf"));
		*/
	}



    /**
     * @author Weiguang Chen(chen2621978@gmail.com) on 2017/7/13 21:56
     *  test Assert
     * 	compile:javac -source 1.7 test/Test.java
     *  run: java -ea tets/Test
     */
	public static void testAssert() {
		System.out.println("testAssert start!");
		int a = 1;
		//when a<1 will throw AssertionError and print "error !!!"
		assert (a > 0) : "error!!";
		System.out.println("assert after!");
	}

    /**
     * @author Weiguang Chen(chen2621978@gmail.com) on 2017/7/13 21:58
     *  test basic data type
     */

	public static void testBase() {
		//Short s = 98S; //error
		//float z = 1.0; //error
		int t = "abc".length();
		//char c = 175c; //error
        int a = 5;
        // output is : "value is  = 9.0" , ps: 10.9d is a double var,so 9i is convert to 9.0d
        System.out.println("value is  = " + ( (a < 5 )? 10.9 : 9 ));
		byte b1=1,b2=2,b3,b6,b8;
		final byte b4=4,b5=6,b7;
		//b3=(b1+b2);  //语句1 error
		b3 = b4 + b5; // okay

		if(false) b1 =1;

        Integer o1 = 1;
        Integer o2 = 1;
		System.out.println(o1 == o2);

		Integer i = 42;
		Long l = 42l;
		Double d = 42.0;

		System.out.println(l.equals(42L));

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
     * @author Weiguang Chen(chen2621978@gmail.com) on 2017/7/14 10:41
     *
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
	 * @author Weiguang Chen(chen2621978@gmail.com) on 2017/7/15 14:21
	 *  串行化测试
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

	/**
	 *
	 *
	 * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/26 15:36
	 * @param
	 * @return void
	 */
	//@org.junit.Test
	public void testString() {
	    String s1 = "aaa123";
	    String s2 = new String("123");
	    String s3 = "aaa" + s2;
	    System.out.println(  s1 == (s3.intern()) );	//true
		System.out.println(  s1 == s3 );	//false
	}

	/**
	 * 一个字符串，按照byte 分割字符，如果遇到分割到的不是首字符，就去到该字符，然后向后移动
	 * UTF-8 有以下编码规则：
	 * 1. 如果一个字节，最高位（第 8 位）为 0，表示这是一个 ASCII 字符（00 - 7F）。可见，所有 ASCII 编码已经是 UTF-8 了。
	 * 2. 如果一个字节，以 11 开头，连续的 1 的个数暗示这个字符的字节数，例如：110xxxxx 代表它是双字节 UTF-8 字符的首字节。
	 * 3. 如果一个字节，以 10 开始，表示它不是首字节，需要向前查找才能得到当前字符的首字节
	 *
	 * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/26 16:40
	 * @param s	需要分割的字符
	 * @param splitLength 分割的长度，如果分割的时候刚好是多字节字符的中间，则需要向后移动长度以防出现乱码
	 * @throws UnsupportedEncodingException
	 */
	public void cutStringByByte(String s, int splitLength) throws UnsupportedEncodingException{
		//String s = "我bd你a"; //jdk 1.8 下测到 中文编码是 3字节
		System.out.println("字符字节长度（UTF-8）： " + s.getBytes("utf-8").length);
//		for (byte b : s.getBytes()) {
//			System.out.print(b + " \t" + Integer.toBinaryString((b & 255) >>> 6));
//		}
		byte[] t = s.getBytes();
		//如果是字节高位是10开头，就说明不是首位字节，找出剩余开始的首字节
		while ( splitLength < t.length && (t[splitLength] & 255) >>>6 == 2) {
			splitLength++;
		}
		byte[] o = new byte[t.length - splitLength ];
		for (int j = splitLength; j < t.length; j++) {
			o[j - splitLength] = t[j];
		}
		System.out.println("原字符串:" + s +  " 分割后：" + new String(o, "utf-8"));
	}

	/**
	 *
	 * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/26 16:31
	 * @throws UnsupportedEncodingException
	 */
	//@org.junit.Test
	public void testStringCoding () throws UnsupportedEncodingException {
			cutStringByByte("as你好呀", 3);

		/*
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
		*/
			
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
