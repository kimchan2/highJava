package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*로또를 구매하는 프로그램 작성하기

사용자는 로또를 구매할 때 구매할 금액을 입력하고
입력한 금액에 맞게 로또번호를 출력한다.
(단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
     출력한다.)

	==========================
        Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 2500  <-- 입력
			
	행운의 로또번호는 아래와 같습니다.
	로또번호1 : 2,3,4,5,6,7
	로또번호2 : 20,21,22,23,24,25
			
	받은 금액은 2500원이고 거스름돈은 500원입니다.
			
  	 ==========================
        Lotto 프로그램
	--------------------------
	  1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 2  <-- 입력
		
	감사합니다*/

public class T13_LottoTest {
	
	public static void main(String[] args) {
		start();
	}

	private static void start() {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		
		do {
		System.out.println("==========================");
		System.out.println("       Lotto 프로그램              ");
		System.out.println("--------------------------");
		System.out.println("       1. Lotto 구입              ");
		System.out.println("       2. 프로그램 종료             ");
		System.out.println("==========================");
		System.out.print("메뉴선택 : ");
		
		answer = Integer.parseInt(sc.nextLine());
		
			switch (answer) {
			case 1:
				buyLotto();
				break;
	
			default:
				System.out.println("감사합니다");
				break;
			}
		}while(answer != 2);
	}

	private static void buyLotto() {
		Scanner sc = new Scanner(System.in);
		int money = 0;
		ArrayList<Integer> lottoSet = new ArrayList<>();
		for(int i = 1; i <= 45; i++ ) {
			lottoSet.add(i);
		}
		
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		
		System.out.println("1000원에 로또번호 하나입니다");
		System.out.print("금액 입력 : ");
		money = Integer.parseInt(sc.nextLine());
		
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for(int i = 0; i < money/1000; i++) {
			Set<Integer> lotto = new HashSet<>();
			while(lotto.size() < 6) {
				int randomIndex = (int)(Math.random()*45);
				lotto.add(lottoSet.get(randomIndex));
			}
			System.out.println("로또번호" + (i+1) + " : " + lotto);
		}
		System.out.println();
		System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + (money%1000) + "원입니다.");
		System.out.println();
	}
	
}

















