package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수 순으로 출력한다.
*/

public class HorseRacing {
	
	//static RankThread rt = RankThread.getInstance();
	
	public static void main(String[] args) {
		
		List<Horse> horses = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			horses.add(new Horse(i+"번말"));
		}
/*		Horse[] horses = new Horse[] {
				new Horse("1번말"), new Horse("2번말"), new Horse("3번말"), new Horse("4번말"), new Horse("5번말"),
				new Horse("6번말"), new Horse("7번말"), new Horse("8번말"), new Horse("9번말"), new Horse("10번말")
		};
*/		
		List<HorseRacingThread> hrts = new ArrayList<HorseRacingThread>();
		for(int i = 0; i < horses.size(); i++) {
			hrts.add(new HorseRacingThread(horses.get(i)));
		}
		
		/*
		HorseRacingThread[] hrts = new HorseRacingThread[10];
		for(int i = 0; i < horses.length; i++) {
			hrts[i] = new HorseRacingThread(horses[i]);
		}
		*/

		for(HorseRacingThread hrt : hrts) {
			
			hrt.start();
			
		}
		
		for(HorseRacingThread hrt : hrts) {
			
			try {
				hrt.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		Collections.sort(horses); // horses를 등수순으로 정렬
		for(Horse hr : horses) {
			System.out.println("Main 실행 : " + hr.getName() + " => " + hr.getRank() + "등");
		}
	}
}


class HorseRacingThread extends Thread{
	private String name;
	private static Integer rank = 0;
	private Horse hor;
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getRank() {
		return this.rank;
	}
	public HorseRacingThread(Horse hor) {
		super();
		this.name = hor.getName();
		this.hor = hor;
	}

	@Override
	public void run() {
		for(int i = 1; i <= 49; i++) {
			String[] track = {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
							  "-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
							  "-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
							  "-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
							  "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
			
			for(int j = 0; j <= i; j++) {
				track[j] = "-";
			}
			
			try {
				Thread.sleep((long)(Math.random()*91) + 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			track[i] = ">";
			System.out.println("Thread 실행 : " + name + "\t" + toString(track));
		}
		this.rank++;
		hor.setRank(this.rank);
		System.out.println();
		System.out.println("Thread 실행 : 골인\t" + toString());
		
	}
	
	public String toString(String[] track) {
		String str = "";
		for(int i = 0; i < track.length; i++) {
			str += track[i];
		}
		return str;
	}

	@Override
	public String toString() {
		return "HorseRacingThread [name=" + name + ", rank=" + rank + "]";
	}
	
}

class Horse implements Comparable<Horse>{
	
	private String name;
	private Integer rank;
	
	public Horse(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Horse [name=" + name + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Horse horse) {
		return this.getRank().compareTo(horse.getRank());
	}
}







