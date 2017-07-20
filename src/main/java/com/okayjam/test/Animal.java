package com.okayjam.test;

import java.io.*;
import java.util.Date;

public class Animal implements Serializable{
	String name;
	//transient don't Serialize
	String passwd;

	final Date born;
	public Animal(String name){
		this.name = name; born = new Date();
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPasswd(){
		return passwd;
	}

	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	
	public void printName(){
		System.out.println("Name is " + name);
	}

	/**
	 *  序列化当前对象，保存到 filePath 中
	 *
	 * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/17 20:57
	 * @param filePath 序列化保存的文件
	 */
	public void serial(String filePath) throws IOException{
		//File file  = new File(filePath);
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filePath));
		oout.writeObject(this);
		oout.close();
	}


	public static Animal reSerial(String filePath) throws IOException, ClassNotFoundException{
		//File file  = new File(filePath);
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(filePath));
		Object newObject = oin.readObject(); // û��ǿ��ת����Person����
		oin.close();
		if( newObject instanceof Animal)
			return (Animal)newObject;
		else return null;
	}

//	@Override
	public String toString(){
		//return 1;
		return "toString:name = " + name;
	}

}
