package com.proj.bsb.bean;

import java.util.Arrays;
import java.util.Base64;

public class Salon {
    private int salonId;
    private String salonName;
    private String location;
    private double latitude;
    private double longitude;
    private String address;
    private String contactNo;
    private byte[] photo;
    private String status;
    
    
    
    
	public Salon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Salon(int salonId, String salonName, String location, double latitude, double longitude, String address,
			String contactNo, byte[] photo) {
		super();
		this.salonId = salonId;
		this.salonName = salonName;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.contactNo = contactNo;
		
		this.photo = photo;
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



	public byte[] getPhoto() {
		return photo;
	}




	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
	}




	@Override
	public String toString() {
		return "Salon [salonId=" + salonId + ", salonName=" + salonName + ", location=" + location + ", latitude="
				+ latitude + ", longitude=" + longitude + ", address=" + address + ", contactNo=" + contactNo
				+ ", photo=" + Arrays.toString(photo) + "]";
	}




	public String getBase64Photo() {
	    if (photo != null && photo.length > 0) {
	        return Base64.getEncoder().encodeToString(photo);
	    }
	    return null;
	}
}
