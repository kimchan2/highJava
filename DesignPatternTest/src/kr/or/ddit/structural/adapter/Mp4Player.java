package kr.or.ddit.structural.adapter;

public class Mp4Player implements AdvancedMediaPlayer{

	@Override
	public void playVlc(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("MP4  파일 실행중... : 파일명 : " + fileName);
		
	}

}
