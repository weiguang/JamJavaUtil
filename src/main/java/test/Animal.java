package test;

import java.io.*;

public class Animal implements Serializable{
	String name;
	//transient don't Serialize
	String passwd;
	public Animal(String name){
		this.name = name;
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

	public void serial(String filePath) throws IOException{
		File file  = new File(filePath);
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
		oout.writeObject(this);
		oout.close();
	}

	public static Animal reSerial(String filePath) throws IOException, ClassNotFoundException{
		File file  = new File(filePath);	
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
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
