package kr.or.ddit.structural.adapter;

public class MediaAdapter implements MediaPlayer{

	AdvancedMediaPlayer advandedMusicPlayer;
	
	public MediaAdapter(String audioType) {
		if(audioType.equalsIgnoreCase("vlc"))
			advandedMusicPlayer = new VlcPlayer();
		else if(audioType.equalsIgnoreCase("mp4"))
			advandedMusicPlayer = new Mp4Player();
	}
	
	@Override
	public void play(String audioType, String fileName) {
		if(audioType.equalsIgnoreCase("vlc")) {
			advandedMusicPlayer.playVlc(fileName);
		}else if(audioType.equalsIgnoreCase("mp4"))
			advandedMusicPlayer.playMp4(fileName);;
		
	}

}
