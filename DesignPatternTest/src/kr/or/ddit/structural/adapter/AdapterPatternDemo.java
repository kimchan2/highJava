package kr.or.ddit.structural.adapter;

public class AdapterPatternDemo {
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer();
		
		audioPlayer.play("mp3", "파일명.mp3");
		audioPlayer.play("mp4", "mp4용 음악.mp4");
		audioPlayer.play("vlc", "vlc용 음악.vlc");
		audioPlayer.play("avi", "좋은동영상.avi");
	}
}
