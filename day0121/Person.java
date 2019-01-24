package week.day0121;

import java.io.Serializable;

public class Person implements Serializable{
	public static final long serialVersionUID = -3726979342461807520L;
	private String name ;	// ����name���ԣ����Ǵ����Բ������л�
	private int age ;		// ����age����
	public Person(String name,int age){	// ͨ��������������
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

	public String toString(){	// ��дtoString()����
		return "������" + this.name + "�����䣺" + this.age ;
	}
};


