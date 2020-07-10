package kr.or.ddit.structural.adapter;

public class VlcPlayer implements AdvancedMediaPlayer{

	@Override
	public void playVlc(String fileName) {
		System.out.println("VLC파일 실행중... 파일명 : " + fileName);
		
	}

	@Override
	public void playMp4(String fileName) {
		
	}

}
