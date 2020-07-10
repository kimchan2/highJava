package kr.or.ddit.behavioral.command;

public class Stock {
	private String name = "ABC";
	private int quantity = 10;
	
	public void buy() {
		System.out.println("주식[이름: " + name + ", 보유량: " + quantity + "] 구매했음.");
	}
	
	public void sell() {
		System.out.println("주식[이름: " + name + ", 보유량: " + quantity + "] 판매했음.");
	}
}
