package kr.or.ddit.basic;


public class Info{
	private String ZIPCODE;
	private String SIDO;
	private String GUGUN;
	private String DONG;
	private String BUNJI;
	public String getZIPCODE() {
		return ZIPCODE;
	}
	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
	}
	public String getSIDO() {
		return SIDO;
	}
	public void setSIDO(String sIDO) {
		SIDO = sIDO;
	}
	public String getGUGUN() {
		return GUGUN;
	}
	public void setGUGUN(String gUGUN) {
		GUGUN = gUGUN;
	}
	public String getDONG() {
		return DONG;
	}
	public void setDONG(String dONG) {
		DONG = dONG;
	}
	public String getBUNJI() {
		return BUNJI;
	}
	public void setBUNJI(String bUNJI) {
		BUNJI = bUNJI;
	}
	public Info(String zIPCODE, String sIDO, String gUGUN, String dONG, String bUNJI) {
		super();
		ZIPCODE = zIPCODE;
		SIDO = sIDO;
		GUGUN = gUGUN;
		DONG = dONG;
		BUNJI = bUNJI;
	}
	
	public Info() {
		super();
	}
	@Override
	public String toString() {
		return "Info [ZIPCODE=" + ZIPCODE + ", SIDO=" + SIDO + ", GUGUN=" + GUGUN + ", DONG=" + DONG + ", BUNJI="
				+ BUNJI + "]";
	}
}