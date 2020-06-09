package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
 * 제너릭 클래스를 이용한 객체생성 예제
 */

public class T07_WildCardTest {
	// FruitBox2<? extends Fruit> fruitBox1 = new FruitBox2<Fruit>();
	FruitBox2<?> fruitBox1 = new FruitBox2();
	FruitBox2<?> fruitBox2 = new FruitBox2<>(); // 위와 동일.
	
	// FruitBox2<?> 는 FruitBox2<? extends Fruit>를 의미함.
	//FruitBox2<?> fruitBox3 = new FruitBox2<Object>(); // 두 타입 일치하지 않음.
	
	FruitBox2<?> fruitBox5 = new FruitBox2<Fruit>();
	FruitBox2<? extends Fruit> fruitBox6 = new FruitBox2<Apple>();
	
	// new 연산자는 타입이 명확해야 객체생성 할 수 있다.(와일드 카드 사용 불가)
	//FruitBox2<? extends Object> fruitBox7 = new FruitBox2<? extends Object>();
}

class FruitBox2<T extends Fruit>{
	List<T> itemList = new ArrayList<>();

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}
	
	public void addItem(T item) {
		this.itemList.add(item);
	}
}














