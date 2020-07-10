package kr.or.ddit.creational.factory;

public class Circle implements Shape{
	private String color;
	
	public Circle() {
		this.color = "black";
	}
	
	public Circle(String color) {
		this.color = color;
	}
	
	@Override
	public void draw() {
		System.out.println("원 안에서 draw()메서드 호출됨.");
		
	}

}
