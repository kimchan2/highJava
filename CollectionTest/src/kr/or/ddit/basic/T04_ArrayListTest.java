package kr.or.ddit.basic;
import java.util.ArrayList;
import java.util.Scanner;


public class T04_ArrayListTest {
	// 문제1) 5명의 별명을 입력하여 ArrayList에 저장하고
	//		 별명의 길이가 제일 긴 별명을 출력하시오.
	//		 (단, 각 별명의 길이는 모두 다르게 입력한다.)
	
	
	// 문제2) 문제1에서 별명의 길이가 같은 것을 여러개 입력했을 때도
	//		  처리하시오.

	public static void main(String[] args) {
		ArrayList<String> aliasList = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		/*
		// 문제1)
		System.out.println("서로 다른 길이의 별명 5개를 입력하세요.");
		for(int i=1; i<=5; i++){
			System.out.print(i + "번째 별명 : ");
			String alias = scan.next();
			aliasList.add(alias);
		}
		
		// maxAlias는 제일 긴 별명이 저장될 변수
		String maxAlias = aliasList.get(0);
		
		//String maxAlias = "";  // 빈문자로 초기화하면 반복을 0부터 시작한다.
		
		for(int i=1; i<aliasList.size(); i++){
			if(maxAlias.length() < aliasList.get(i).length()){
				maxAlias = aliasList.get(i);
			}
		}
		
		System.out.println("제일 긴 별명 : " + maxAlias);
		*/
		
		
		// 문제2)
		System.out.println("별명 5개를 입력하세요.");
		for(int i=1; i<=5; i++){
			System.out.print(i + "번째 별명 : ");
			String alias = scan.next();
			aliasList.add(alias);
		}
		
		// maxLen은 제일 긴 별명의 길이를 저장하는 변수
		int maxLen = aliasList.get(0).length();
		for(int i=1; i<aliasList.size(); i++){
			if(maxLen < aliasList.get(i).length()){
				maxLen = aliasList.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명들");
		for(int i=0; i<aliasList.size(); i++){
			if(maxLen==aliasList.get(i).length()){
				System.out.println( aliasList.get(i) );
			}
		}
		
	}

}








