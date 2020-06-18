package kr.or.ddit.basic;

public class T15_SyncThreadTest {
	public static void main(String[] args) {
		
	}
}

// 공통으로 사용할 객체
class ShareObject{
	private int sum = 0;
	
	public void add() {
		for(int i = 0; i < 1000000000; i++) {} // 동기화처리 전까지의 시간벌기용
		
		int n = sum;
		n += 10; // 10 증가
		sum = n;
		
		System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
	}
	
	public int getSum() {
		return sum;
	}
}

// 작업을 수행하는 쓰레드
class WorkThread extends Thread{
	ShareObject sObj;
	
	public WorkThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			sObj.add();
		}
	}
	
	
}





