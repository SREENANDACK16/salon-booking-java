package com.proj.bsb.bean;

public class SalonCategory {
	
	private int salonId;
    private int categoryId;
    private String salonName;
    private String categoryName;
    
    
	public SalonCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalonCategory(int salonId, int categoryId, String salonName, String categoryName) {
		super();
		this.salonId = salonId;
		this.categoryId = categoryId;
		this.salonName = salonName;
		this.categoryName = categoryName;
	}
	public int getSalonId() {
		return salonId;
	}
	public void setSalonId(int salonId) {
		this.salonId = salonId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getSalonName() {
		return salonName;
	}
	public void setSalonName(String salonName) {
		this.salonName = salonName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "SalonCategory [salonId=" + salonId + ", categoryId=" + categoryId + ", salonName=" + salonName
				+ ", categoryName=" + categoryName + "]";
	}
    
    

}
