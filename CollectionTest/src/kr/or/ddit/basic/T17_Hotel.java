package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*문제)

호텔 운영을 관리하는 프로그램 작성.(Map이용)
 - 키값은 방번호 
 
실행 예시)

**************************
호텔 문을 열었습니다.
**************************

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 101 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 홍길동 <-- 입력
체크인 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 성춘향 <-- 입력
체크인 되었습니다

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향
방번호 : 101, 투숙객 : 홍길동

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
체크아웃 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 허준 <-- 입력
102방에는 이미 사람이 있습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
101방에는 체크인한 사람이 없습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 4 <-- 입력

**************************
호텔 문을 닫았습니다.
**************************
*/

public class T17_Hotel {
	
	private Scanner sc = new Scanner(System.in);
	private Map<String, RoomInfo> map = new HashMap<String, RoomInfo>();
	
	public static void main(String[] args) {
		T17_Hotel test = new T17_Hotel();
		test.start();
	}

	void start(){
	
		int ans = 0;
		System.out.println("******************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("******************************");
		do {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인 \t 2. 체크아웃 \t 3.객실상태 \t 4.업무종료");
		System.out.println("************************************************************");
		System.out.print("메뉴선택 => ");
		ans = sc.nextInt();
		
		switch (ans) {
		case 1:
			checkIn();
			break;
		case 2:
			checkOut();
			break;
		case 3:
			roomCondition();
			break;
		case 4:
			cloese();
			break;
		}
		}while(ans != 4);
	}

	void checkIn() {
		System.out.println();
		System.out.print("방번호 입력 => ");
		String roomNum = sc.next();
		
		if(map.get(roomNum) != null) {
			System.out.println("이미 투숙객이 있는 호실입니다.");
			return;
		}
		sc.nextLine();
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = sc.next();
		
		RoomInfo roomInfo = new RoomInfo(name, roomNum); 
		map.put(roomNum, roomInfo);
		
		System.out.println("체크인 되었습니다");
	}

	void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String roomNum = sc.next();
		
		if(map.get(roomNum) == null) {
			System.out.println( roomNum + "호실은 현재 투숙객이 없는 호실입니다.");
			return;
		}
		map.remove(roomNum);
		System.out.println("체크아웃 되었습니다.");
	}

	void roomCondition() {
		
		for(String key : map.keySet()) {
			System.out.println("방번호 : " + key + ", 투숙객 : " + map.get(key).getName());
		}
	}

	void cloese() {
		System.out.println("******************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("******************************");
	}
}

class RoomInfo{
	private String name;
	private String roomNum;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	public RoomInfo(String name, String roomNum) {
		super();
		this.name = name;
		this.roomNum = roomNum;
	}
	
	@Override
	public String toString() {
		return "roomInfo [name=" + name + ", roomNum=" + roomNum + "]";
	}
	
	
}











