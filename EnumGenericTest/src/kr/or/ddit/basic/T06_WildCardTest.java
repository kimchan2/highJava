package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
 * 와일드 카드
 * <? extends T>	=> 와일드 카드의 상한 제한, T와 그 자손들만 가능
 * <? super T>		=> 와일드 카드의 하한 제한, T와 그 조상들만 가능
 * <?>				=> 모든타입이 가능 <? extends Object>와 동일
 * 
 * * 와일드카드와 제한된 타입 파라미터 예시 :
 * 1. 동일한 파라미터 타입으로강제하고 싶은 경우.
 * 	ex) public static <T extends Number> void copy(List<T> dest, List<T> src)
 * 		=> 메서드의 파라미터 타입을 동일한 타입으로 강제함.
 * 		public static void copy(List <? extends Number> destm List<? extends Number> src)
 * 		=> 동일 타입으로 강제하지 않음.
 * 
 * 2. Type parameters는 하한 제한만 가능(와일드 카드는 상한, 하한 가능)
 * 	ex) public void print(List<? super Integer> list) // OK
 * 		public <T super Integer> void print(<List<T> list) // 컴파일 에러
 * 
 */

public class T06_WildCardTest {
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<>();
		FruitBox<Apple> appleBox = new FruitBox<>();
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
		//Juicer.makeJuice(fruitBox); // 과일상자인 경우에는 아무런 문제가 없음
		Juicer.makeJuice(appleBox); // apple이 fruit를 상속받아서 될거같지만 안됨 => Generic 문법, 한타입만 올 수 있다
									// 제너릭메서드나 와일드 카드를 이용하여 해결가능
		
		/*FruitBox<Drink> drinkBox = new FruitBox<>();
		drinkBox.add(new Drink("콜라"));
		drinkBox.add(new Drink("사이다"));
		drinkBox.add(new Drink("환타"));
		drinkBox.add(new Drink("펩시"));
		
		Juicer.makeJuice(drinkBox);*/
	}
	
}

/*
 * 쥬서
 */
class Juicer{
	// static void makeJuice(FruitBox<Fruit> box) { // FruitBox<Fruit> 타입만 올 수 있음
	// static <T> void  makeJuice(FruitBox<T> box) { // 제너릭 메서드 선언하여 처리
												     // => 타입 제한이 없으므로 모든타입 가능
	// static <T extends Fruit> void makeJuice(FruitBox<T> box) {
	static void makeJuice(FruitBox<? extends Fruit> box) {
		String fruitListStr = ""; // 과일목록
		int cnt = 0;
		//for(Fruit f : box.getFruitList()) {
		for(Fruit f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
		}
		System.out.println(fruitListStr + " => 쥬스 완성!!!");
	}
}

/*
 * 과일
 */

class Fruit{
	
	private String name; // 과일 이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Fruit [name=" + name + "]";
	}
	
}

/*
 * 사과
 */

class Apple extends Fruit{

	public Apple() {
		super("사과");
	}
	
}

/*
 * 포도
 */

class Grape extends Fruit{

	public Grape() {
		super("포도");
	}
	
}

/*
 * 과일상자
 */
class FruitBox<T>{
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<>();
	}
	
	public List<T> getFruitList(){
		return fruitList;
	}
	
	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}

// 음료수 클래스
class Drink{
	private String name;
	
	public Drink(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Drink [name=" + name + "]";
	}
}







