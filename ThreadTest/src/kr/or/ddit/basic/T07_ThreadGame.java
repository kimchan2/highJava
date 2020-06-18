package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한 하고 카운트 다운을 진행한다.
 * 5초안에 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시)
 * 	=== 결과 ===
 *  컴퓨터 : 가위
 *  당  신 : 바위
 *  결  과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	
	public static boolean user_input_check = false;
	
	public static void main(String[] args) {
		
		T07_game game = new T07_game();
		T07_CountDown countDown = new T07_CountDown();
		
		game.start();
		countDown.start();
	}
	
}

class T07_game extends Thread{
	
	public static String computer = "";
	public static String user = "";
	
	@Override
	public void run() {
		String[] game = {"가위", "바위", "보"};
		computer = game[(int)(Math.random()*game.length)];
		
		user = JOptionPane.showInputDialog("가위, 바위, 보 중에 하나를 입력하세요");
		if(user != null && !user.equals("")){ T07_ThreadGame.user_input_check = true; }
		
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : " + computer);
		System.out.println("당 신 : " + user);
		if( (user.equals("가위") && computer.equals("보")) || (user.equals("바위") && computer.equals("가위")) 
				|| (user.equals("보") && computer.equals("바위"))) {
			System.out.println("결과 : user의 승리입니다");
		} else if( user.equals(computer)) {
			System.out.println("결과 : 무승부 입니다");
		} else {
			System.out.println("결과 : 유저의 패배입니다.");
		}
	}
}

class T07_CountDown extends Thread{
	
	@Override
	public void run() {
		
		for(int i = 5; i >= 1; i--) {
			
			if( T07_ThreadGame.user_input_check == true) {
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("5초가 지났습니다 프로그램을 종료합니다");
		System.exit(0);
	}
	
}



