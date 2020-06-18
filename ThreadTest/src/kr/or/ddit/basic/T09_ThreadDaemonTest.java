package kr.or.ddit.basic;

public class T09_ThreadDaemonTest {
	public static void main(String[] args) {
		
		AutoSaveThread ast = new AutoSaveThread();
		
		// 데몬 쓰레드로 설정하기 (start()메서드 호출전에 설정한다.)
		ast.setDaemon(true);
		ast.start();
		
		try {
			for(int i = 1; i <= 20; i++) {
				System.out.println("작업" + i);
				
				Thread.sleep(1000);
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("메인 쓰레드 종료...");
	}
}

/**
 * 자동 저장하는 기능을 제공하는 쓰레드
 * @author PC-25
 *
 */
class AutoSaveThread extends Thread{
	public void save() {
		System.out.println("현재 작업을 저장중 입니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			save(); // 저장기능 호출
		}
	}
}