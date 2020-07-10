package kr.or.ddit.structural.decorator;

public class DecoratorPatternDemo {
	public static void main(String[] args) {
		Shape circle = new Circle();
		
		Shape redCircle = new RedShapeDecorator(new Circle());
		
		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		
		// 일반 원 그리기
		circle.draw();
		System.out.println("------------------------------------------------");
		redCircle.draw();
		System.out.println("------------------------------------------------");
		redRectangle.draw();
	}
}
