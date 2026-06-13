package com.proj.bsb.bean;

public class ManageBooking {
	
	 private int id;            
	    private int salonId;
	    private String username;
	    private String phone;
	    private String date;
	    private String time;
	    private String flexible;
		public ManageBooking() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ManageBooking(int id, int salonId, String username, String phone, String date, String time,
				String flexible) {
			super();
			this.id = id;
			this.salonId = salonId;
			this.username = username;
			this.phone = phone;
			this.date = date;
			this.time = time;
			this.flexible = flexible;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getSalonId() {
			return salonId;
		}
		public void setSalonId(int salonId) {
			this.salonId = salonId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getFlexible() {
			return flexible;
		}
		public void setFlexible(String flexible) {
			this.flexible = flexible;
		}
		
		private String salonName;

		public String getSalonName() {
		    return salonName;
		}

		public void setSalonName(String salonName) {
		    this.salonName = salonName;
		}
		@Override
		public String toString() {
			return "ManageBooking [id=" + id + ", salonId=" + salonId + ", username=" + username + ", phone=" + phone
					+ ", date=" + date + ", time=" + time + ", flexible=" + flexible + ", salonName=" + salonName + "]";
		}

	    
	    

}
