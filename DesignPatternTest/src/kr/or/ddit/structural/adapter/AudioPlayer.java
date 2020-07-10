package kr.or.ddit.structural.adapter;

public class AudioPlayer implements MediaPlayer{
	MediaAdapter mediaAdapter;
	
	@Override
	public void play(String audioType, String fileName) {
		if(audioType.equalsIgnoreCase("mp3")) {
			System.out.println("mp3파일 실행중 ... 파일명 : " + fileName);
			
		}else if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
			mediaAdapter = new MediaAdapter(audioType);
			mediaAdapter.play(audioType, fileName);
		}else {
			System.out.println("지원하지 않는 미디어타입입니다. : " + audioType);
		}
		
	}

}
