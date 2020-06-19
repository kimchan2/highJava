package kr.or.ddit.basic;

public class T15_SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkThread th1 = new WorkThread("1번쓰레드", sObj);
		WorkThread th2 = new WorkThread("2번쓰레드", sObj);
		
		th1.start();
		th2.start();
	}
}

// 공통으로 사용할 객체
class ShareObject{
	private int sum = 0;
	
	public void add() {
		synchronized (this) {
		for(int i = 0; i < 1000000000; i++) {} // 동기화처리 전까지의 시간벌기용
		
			int n = sum;
			n += 10; // 10 증가
			sum = n;
		}
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





