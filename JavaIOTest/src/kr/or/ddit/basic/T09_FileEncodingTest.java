package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
/*
 * InputStreamReader객체는 파일의 인코딩 방식을 지정할 수 있다.
 * 형식) new InputStreamReader(바이트 기반 스트림 객체, 인코딩 방식)
 * 
 * 인코딩방식
 * 
 * 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식 두가지로 나뉜다.
 * 원래 한글윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 MS에서 EUC-KR방식에서
 * 확장하였기 때문에 MS949라고도 부른다.
 * 한글 Windows의 메모장에서 이야기하는 ANSI 인코딩이란, CP949(Code Page 949)를 말한다.
 * 	- MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI계열)
 * 	- UTF-8 => 유니코드 UTF-8인코딩 방식(영문자 및 숫자 : 1byte, 한글 : 3byte) => 가변적
 * 	- US-ASCII => 영문전용 인코딩 방식
 * 
 * ANSI는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없었다가 나중에 여기에 EUR-KR,
 * CP949이라는 식으로 한글이 포함되었음.
 * 
 * 참고)
 * ASCII => extended ASCII(ISO 8859-1) => 조합형(초성, 중성, 종성), 완성형(KSC 5601)
 * 		 => 윈도우계열 : CP949(확장완성형)
 * 		 => 유닉스계열 : EUC-KR(확장 유닉스 코드) => ANSI계열(EUC-KR)
 * 										   => 유니코드(UTF-8)
 * 		* 유니코드 (Unicode)
 * 		서로다른 문자 인코딩을 사용하는 컴퓨터간의 문서교환에 어려움을 겪게되고, 이런 문제점을 해결하기 위해 전 세계의
 * 		모든 문자를 하나의 통일된 문자집합(CharSet)으로 표현함
 * 		처음엔 2byte(65536)으로 표현했지만, 부족해지니 21bit(약 200만 문자)로 확장되었다. => 보충문자
 */

	public static void main(String[] args) {
		// 파일 인코딩을 이용하여 읽어오기
		// InputStreamReader스트림 => 바이트기반 스트림을 문자기반 스트림으로
		//							변환해 주는 보조 스트림
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			/*
			 * FileInputStream객체를 생성한 후 이 객체를 매개변수로 받는
			 * InputStreamReader객체를 생성한다.
			 * (바이트 입력 스트림에 연결되어 문자 입력 스트림인 Reader로 변환 시키는 보조스트림)
			 */
			fis = new FileInputStream("e:/D_Other/test_utf8.txt");
			//fis = new FileInputStream("e:/D_Other/test_ansi.txt");
			
			//isr = new InputStreamReader(fis, "CP949");
			//isr = new InputStreamReader(fis, "MS949");
			//isr = new InputStreamReader(fis, "EUC-KR");
			isr = new InputStreamReader(fis, "UTF-8");
			
			int c;
			while( (c=isr.read()) != -1 ) {
				System.out.print((char) c);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
			isr.close(); // 보조 스트림만 닫아도 된다.
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}





















