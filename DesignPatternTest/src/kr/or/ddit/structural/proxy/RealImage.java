package kr.or.ddit.structural.proxy;

public class RealImage implements Image{

	private String fileName;
	
	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}
	
	private void loadFromDisk(String fileName) {
		System.out.println("디스크로부터 로딩중..." + fileName);
		
	}

	@Override
	public void display() {
		System.out.println("이미지 화면에 뿌려줌... : " + fileName);
		
	}
	
}
