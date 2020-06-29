package kr.or.ddit.basic;

@FunctionalInterface
public interface LambdaTestInterface1 {
	// 반환값이 없고 매개변수도 없는 추상 메서드 선언
	public abstract void test();
}

@FunctionalInterface
interface LambdaTestInterface2 {
	// 반환값이 없고 매개변수가 있는 추상 메서드 선언
	public abstract void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3 {
	// 반환값이 있고 매개변수도 있는 추상 메서드 선언
	public abstract int test(int a, int b);
}
