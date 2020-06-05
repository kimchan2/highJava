package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class Collection_q {
/*5명의 사람 이름을 입력받아  ArrayList에 저장하고
    이 중에서 ‘김’씨 성의 이름을 출력하시오.
     (단, 입력은 Scanner를 이용하여 입력 받는다.)
*/
   public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
//      ArrayList<String> list =new ArrayList<String>();
//      
//      for(int i =0; i<5; i++) {
//         System.out.print("입력받은 이름");
//         String input = s.nextLine();
//         list.add(input);
//         
//      }
//      String kim = "";
//      for(int i =0; i<list.size();i++) {
//         String pong = list.get(i);
//            char ping = pong.charAt(0);
//         if(ping=='김') {
//            kim+=pong+", ";
//         }
//      }
//      
//      System.out.println(kim);
      
//      5명의 별명을 입력하여 ArrayList에 저장하고
//      별명의 길이가 제일 긴 별명을 출력하시오.
//      (단, 각 별명의 길이는 모두 다르게 입력한다.)
      
		ArrayList<String> list2 = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			System.out.print("입력받은 별명");
			String input = s.nextLine();
			list2.add(input);
		}
		String temp2 = list2.get(0);
		int temp = list2.get(0).length();
		for (int i = 0; i < list2.size() - 1; i++) {
			if (temp < list2.get(i + 1).length()) {
				temp = list2.get(i + 1).length();
				temp2 = list2.get(i + 1);
			}
		}
		System.out.println(temp2);
	}
}