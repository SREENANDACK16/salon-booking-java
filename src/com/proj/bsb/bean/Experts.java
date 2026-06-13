package com.proj.bsb.bean;

import java.util.Arrays;

public class Experts {
	private int expertId;
    private int salonId;
    private String expertName;
    private String gender;
    private String dob;
    private String specialization;
    private String phone;
    private String email;
    private String qualification;
    private String availability;
    private String status;
    private String doj;
    private byte[] photo;
	public Experts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Experts(int expertId, int salonId, String expertName, String gender, String dob, String specialization,
			String phone, String email, String qualification, String availability, String status, String doj,
			byte[] photo) {
		super();
		this.expertId = expertId;
		this.salonId = salonId;
		this.expertName = expertName;
		this.gender = gender;
		this.dob = dob;
		this.specialization = specialization;
		this.phone = phone;
		this.email = email;
		this.qualification = qualification;
		this.availability = availability;
		this.status = status;
		this.doj = doj;
		this.photo = photo;
	}
	public int getExpertId() {
		return expertId;
	}
	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}
	public int getSalonId() {
		return salonId;
	}
	public void setSalonId(int salonId) {
		this.salonId = salonId;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Experts [expertId=" + expertId + ", salonId=" + salonId + ", expertName=" + expertName + ", gender="
				+ gender + ", dob=" + dob + ", specialization=" + specialization + ", phone=" + phone + ", email="
				+ email + ", qualification=" + qualification + ", availability=" + availability + ", status=" + status
				+ ", doj=" + doj + ", photo=" + Arrays.toString(photo) + "]";
	}
	

}
