package kr.or.ddit.creational.factory;

public class FactoryPatternDemo {
	public static void main(String[] args) {
		ShapeFactory sf = new ShapeFactory();
		
		Shape shape1 = sf.getShape("CiRcLe");
		shape1.draw();
		
		Shape shape2 = sf.getShape("Rectangle");
		shape2.draw();
		
		Shape shape3 = sf.getShape("square");
		shape3.draw();
		
		Shape shape4 = sf.getShape("Diamond");
		shape4.draw();
	}
}
