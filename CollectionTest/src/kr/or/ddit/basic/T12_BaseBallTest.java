package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 	   컴퓨터의 숫자는 난수를 이용하여 구한다.
 	  (스트라이크는 'S', 볼은 'B'로 출력한다.
 	  
 	  컴퓨터의 난수가 9 5 7 일때 실행 예시)
 	  
 	 숫자 입력 => 3 5 6
 	 3 5 7 => 1S 0B
 	 숫자 입력 => 7 8 9
 	 7 8 9 => 0S 2B
 	 ...
 	 숫자 입력 => 9 5 7
 	 9 5 7 => 3S 0B
 	 
 	 5번째 만에 맞췄군요.
*/
public class T12_BaseBallTest {
	public static void main(String[] args) {
		Set<Integer> computerNum = new HashSet<>();
		
		while(computerNum.size() < 3) {	//Set의 데이터가 3개가 될때까지 반복한다.
			int num = (int)(Math.random() *9 + 1);	//1~9사이의 난수
			computerNum.add(num);
		}
		System.out.println(computerNum);
		
		List<Integer> intRndList = new ArrayList<Integer>(computerNum);
		
		int cnt = 1;
		
		outer: while(true) {
			Scanner scan = new Scanner(System.in);
			Set<Integer> userNum = new HashSet<>();
			
			System.out.print("숫자를 입력해주세요(1-9)>");
			while(userNum.size() < 3) {
				if(!userNum.add(scan.nextInt())) {
					System.out.println("중복된 숫자를 입력할 수 없습니다.");
					continue outer;
				}
			}
			List<Integer> intSelList = new ArrayList<Integer>(userNum);
			
			int s = 0;
			int b = 0;
			
			for (int i = 0; i < intSelList.size(); i++) {
				for(int j = 0; j<intRndList.size(); j++) {
					if(intSelList.get(i).equals(intRndList.get(j))) {
						if(i == j)s++;
						else {b++;}
						continue;
					}
				}
			}
			System.out.println(intSelList + " => " + b + "B " + s+ "S");
			if(s==3) {
				System.out.println(cnt+"번째 만에 맞췄군요.");
				break;
			}else {
				cnt++;
			}
		}
	}
}
