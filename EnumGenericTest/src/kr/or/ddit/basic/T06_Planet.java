package kr.or.ddit.basic;

/*문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 

예) 행성의 반지름(KM):
수성(2439), 
금성(6052), 
지구(6371), 
화성(3390), 
목성(69911), 
토성(58232), 
천왕성(25362), 
해왕성(24622)*/

public class T06_Planet {
	
	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
	
		final static double pi = 3.14;
		private long area;
		
		Planet(int r){
			this.area = (long) (4 * pi * Math.pow(r, 2)); 
		}

		public long getArea() {
			return area;
		}		
	}
	
	public static void main(String[] args) {
		for(Planet planet : Planet.values()) {
			System.out.println( planet +"의 면적 : " + planet.getArea() );
		}
	}
}
