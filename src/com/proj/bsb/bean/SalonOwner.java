package com.proj.bsb.bean;

public class SalonOwner {
	
	private int userId;
    private int salonId;
    private String salonName; 
    private String username;
    private String password;

    // getters & setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getSalonId() { return salonId; }
    public void setSalonId(int salonId) { this.salonId = salonId; }
    
    public String getSalonName() { return salonName; }
    public void setSalonName(String salonName) { this.salonName = salonName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
