package kr.or.ddit.basic;

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
	
	public static void main(String[] args) {
		
		HorseRacingThread hrt1 = new HorseRacingThread(new Horse("1번말"));
		HorseRacingThread hrt2 = new HorseRacingThread(new Horse("2번말"));
		HorseRacingThread hrt3 = new HorseRacingThread(new Horse("3번말"));
		HorseRacingThread hrt4 = new HorseRacingThread(new Horse("4번말"));
		HorseRacingThread hrt5 = new HorseRacingThread(new Horse("5번말"));
		HorseRacingThread hrt6 = new HorseRacingThread(new Horse("6번말"));
		HorseRacingThread hrt7 = new HorseRacingThread(new Horse("7번말"));
		HorseRacingThread hrt8 = new HorseRacingThread(new Horse("8번말"));
		HorseRacingThread hrt9 = new HorseRacingThread(new Horse("9번말"));
		HorseRacingThread hrt10 = new HorseRacingThread(new Horse("10번말"));
		
		RankCheck rc1 = new RankCheck(hrt1);
		RankCheck rc2 = new RankCheck(hrt2);
		RankCheck rc3 = new RankCheck(hrt3);
		RankCheck rc4 = new RankCheck(hrt4);
		RankCheck rc5 = new RankCheck(hrt5);
		RankCheck rc6 = new RankCheck(hrt6);
		RankCheck rc7 = new RankCheck(hrt7);
		RankCheck rc8 = new RankCheck(hrt8);
		RankCheck rc9 = new RankCheck(hrt9);
		RankCheck rc10 = new RankCheck(hrt10);
		
		hrt1.start();
		hrt2.start();
		hrt3.start();
		hrt4.start();
		hrt5.start();
		hrt6.start();
		hrt7.start();
		hrt8.start();
		hrt9.start();
		hrt10.start();
		
		rc1.start();
		rc2.start();
		rc3.start();
		rc4.start();
		rc5.start();
		rc6.start();
		rc7.start();
		rc8.start();
		rc9.start();
		rc10.start();
	}
	
}

class RankCheck extends Thread{
	private HorseRacingThread targetThread;
	private static Integer rank = 1;

	public RankCheck(HorseRacingThread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		HorseRacingThread.State state = targetThread.getState();
		if( state == HorseRacingThread.State.TERMINATED ) {
			targetThread.setRank(rank);
			System.out.println( rank );
			rank++;
		}
	}
	
	
}

class HorseRacingThread extends Thread{
	private String name;
	private Integer rank;
	
	
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public HorseRacingThread(Horse hor) {
		super();
		this.name = hor.getName();
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
			System.out.println(toString(track));
		}
		System.out.println("==========================================================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	public String toString(String[] track) {
		String str = "";
		for(int i = 0; i < track.length; i++) {
			str += track[i];
		}
		return str;
	}
}

class Horse implements Comparable<Integer>{
	
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

	public int getRank() {
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
	public int compareTo(Integer rank) {
		return new Integer(this.getRank()).compareTo(rank);
	}
}








