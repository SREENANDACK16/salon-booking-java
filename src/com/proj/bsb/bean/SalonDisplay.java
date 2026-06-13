package com.proj.bsb.bean;

public class SalonDisplay {
	
	  private int salonId;
      private String salonName;
      private String location;
      private double latitude;
      private double longitude;
      private String address;
      private String contactNo;
      private String photoBase64;
	public SalonDisplay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalonDisplay(int salonId, String salonName, String location, double latitude, double longitude,
			String address, String contactNo, String photoBase64) {
		super();
		this.salonId = salonId;
		this.salonName = salonName;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.contactNo = contactNo;
		this.photoBase64 = photoBase64;
	}
	public int getSalonId() {
		return salonId;
	}
	public void setSalonId(int salonId) {
		this.salonId = salonId;
	}
	public String getSalonName() {
		return salonName;
	}
	public void setSalonName(String salonName) {
		this.salonName = salonName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPhotoBase64() {
		return photoBase64;
	}
	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}
	@Override
	public String toString() {
		return "SalonDisplay [salonId=" + salonId + ", salonName=" + salonName + ", location=" + location
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", address=" + address + ", contactNo="
				+ contactNo + ", photoBase64=" + photoBase64 + "]";
	}
      
      

}
