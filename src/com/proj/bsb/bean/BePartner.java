package com.proj.bsb.bean;

public class BePartner {
	
	    private int id;
	    private String country;
	    private String emirates;
	    private String area;
	    private String category;
	    private String firstName;
	    private String lastName;
	    private String companyName;
	    private String contactNo;
	    private String message;
	    
	    
		public BePartner() {
			super();
			// TODO Auto-generated constructor stub
		}


		public BePartner(int id, String country, String emirates, String area, String category, String firstName,
				String lastName, String companyName, String contactNo, String message) {
			super();
			this.id = id;
			this.country = country;
			this.emirates = emirates;
			this.area = area;
			this.category = category;
			this.firstName = firstName;
			this.lastName = lastName;
			this.companyName = companyName;
			this.contactNo = contactNo;
			this.message = message;
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public String getEmirates() {
			return emirates;
		}


		public void setEmirates(String emirates) {
			this.emirates = emirates;
		}


		public String getArea() {
			return area;
		}


		public void setArea(String area) {
			this.area = area;
		}


		public String getCategory() {
			return category;
		}


		public void setCategory(String category) {
			this.category = category;
		}


		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getCompanyName() {
			return companyName;
		}


		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}


		public String getContactNo() {
			return contactNo;
		}


		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}


		public String getMessage() {
			return message;
		}


		public void setMessage(String message) {
			this.message = message;
		}


		@Override
		public String toString() {
			return "BePartner [id=" + id + ", country=" + country + ", emirates=" + emirates + ", area=" + area
					+ ", category=" + category + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", companyName=" + companyName + ", contactNo=" + contactNo + ", message=" + message + "]";
		}

	    
	    
	    
	    
	}



