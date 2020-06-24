package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
 * 부모객체의 필드값 처리 방법
 * 	
 * 	1. 부모클래스가 Serializable인터페이스를 구현하도록 해야한다.
 * 	2. 자식클래스에 writeObject()와 readObject() 메서드를 이용하여
 * 	      부모객체의 필드값을 처리할 수 있도록 직접 구현한다.
 */
public class T16_NonSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream("e:/D_Other/nonSerializableTest.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child); // 직렬화
		oos.flush(); // 생략가능
		oos.close();
		
		FileInputStream fis = new FileInputStream("e:/D_Other/nonSerializableTest.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Child child2 = (Child) ois.readObject(); // 역직렬화
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
		// fis.close(); // 생략가능
	}
}

class Parent{
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

class Child extends Parent implements Serializable{
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
	 * 직렬화될 때 자동으로 호출됨
	 * (접근 제한자가 private이 아니면 자동호출되지 않음)
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		 // ObjectOutputStream객체의 메서드 이용하여 부모객체 필드값 처리
		out.writeUTF(getParentName());
		out.defaultWriteObject(); // 디폴트 객체 처리..
	}
	
	/**
	 * 역직렬화 될 때 자동으로 호출됨
	 * (접근 제한자가 private이 아니면 자동호출되지 않음)
	 * @param ois
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
		// ObjectInputStream객체의 메서드 이용하여 부모객체 필드값 처리
		setParentName(ois.readUTF());
		ois.defaultReadObject();
	}
}



