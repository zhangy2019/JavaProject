package week.day0121;

import java.io.Serializable;

public class Person implements Serializable{
	public static final long serialVersionUID = -3726979342461807520L;
	private String name ;	// 声明name属性，但是此属性不被序列化
	private int age ;		// 声明age属性
	public Person(String name,int age){	// 通过构造设置内容
		this.name = name ;
		this.age = age ;
	}
	public void SetAge(int nAge){
		this.age = nAge;
	}

	public void SetName(String strName){
		this.name = strName;
	}
	public String GetName(){
		return this.name;
	}
	public int GetAge(){
		return this.age;
	}

	public String toString(){	// 覆写toString()方法
		return "姓名：" + this.name + "；年龄：" + this.age ;
	}
};


