package kr.or.ddit.basic;

class Util2{
	// 제한된 타입 파라미터
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

/*
 * 제한된 타입 파라미터(Bounded Parameter) 예제
 */
public class T05_GenericMethod {
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);
		System.out.println(result2);
		
		// int result3 = Util2.compare(new Integer(5), new BigDecimal(4));
		
		// Util2.compare("C", "JAVA"); // 에러발생
	}
}
