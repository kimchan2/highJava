package kr.or.ddit.basic;

public class prodVO {
	private String PROD_ID;
	private String PROD_NAME;
	private String PROD_BUYER;
	private String PROD_COST;
	private String PROD_PRICE;
	private String PROD_SALE;
	private String PROD_OUTLINE;
	private String PROD_DETAIL;
	public String getPROD_ID() {
		return PROD_ID;
	}
	public void setPROD_ID(String pROD_ID) {
		PROD_ID = pROD_ID;
	}
	public String getPROD_NAME() {
		return PROD_NAME;
	}
	public void setPROD_NAME(String pROD_NAME) {
		PROD_NAME = pROD_NAME;
	}
	public String getPROD_BUYER() {
		return PROD_BUYER;
	}
	public void setPROD_BUYER(String pROD_BUYER) {
		PROD_BUYER = pROD_BUYER;
	}
	public String getPROD_COST() {
		return PROD_COST;
	}
	public void setPROD_COST(String pROD_COST) {
		PROD_COST = pROD_COST;
	}
	public String getPROD_PRICE() {
		return PROD_PRICE;
	}
	public void setPROD_PRICE(String pROD_PRICE) {
		PROD_PRICE = pROD_PRICE;
	}
	public String getPROD_SALE() {
		return PROD_SALE;
	}
	public void setPROD_SALE(String pROD_SALE) {
		PROD_SALE = pROD_SALE;
	}
	public String getPROD_OUTLINE() {
		return PROD_OUTLINE;
	}
	public void setPROD_OUTLINE(String pROD_OUTLINE) {
		PROD_OUTLINE = pROD_OUTLINE;
	}
	public String getPROD_DETAIL() {
		return PROD_DETAIL;
	}
	public void setPROD_DETAIL(String pROD_DETAIL) {
		PROD_DETAIL = pROD_DETAIL;
	}
	@Override
	public String toString() {
		return "prodVO [PROD_ID=" + PROD_ID + ", PROD_NAME=" + PROD_NAME + ", PROD_BUYER=" + PROD_BUYER + ", PROD_COST="
				+ PROD_COST + ", PROD_PRICE=" + PROD_PRICE + ", PROD_SALE=" + PROD_SALE + ", PROD_OUTLINE="
				+ PROD_OUTLINE + ", PROD_DETAIL=" + PROD_DETAIL + "]";
	}
	
	
	
}
