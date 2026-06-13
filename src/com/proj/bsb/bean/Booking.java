package com.proj.bsb.bean;

public class Booking {

    private int id;             // <-- Add this not in parameter
    private int salonId;
    private String salonName;
    private String username;
    private String phone;
    private String date;
    private String time;
    private String flexible;
    private int rating;
    private String comments;
    private String status;
    private String seenByUser;
    private String seenByOwner;

    public Booking() {
        super();
    }
    public boolean isSeenByUser() {
        return "Y".equalsIgnoreCase(seenByUser);
    }

    public Booking(int salonId, String username, String phone, String date, String time, String flexible) {
        this.salonId = salonId;
        this.username = username;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.flexible = flexible;
        this.status = "Booked"; 
        
        
    }

    

	// Getter and Setter for id
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
    public String getSalonName() { 
    	return salonName; 
    	}
    public void setSalonName(String salonName) { 
    	this.salonName = salonName; 
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
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    

    public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setSeenByUser(String seenByUser) {
	    this.seenByUser = seenByUser;
	}

	public String getSeenByUser() {
	    return seenByUser;
	}
	public String getSeenByOwner() {
	    return seenByOwner;
	}

	// Setter
	public void setSeenByOwner(String seenByOwner) {
	    this.seenByOwner = seenByOwner;
	}

	@Override
    public String toString() {
        return "Booking [id=" + id + ", salonId=" + salonId + ", username=" + username +
               ", phone=" + phone + ", date=" + date + ", time=" + time + ", flexible=" + flexible + "]";
    }
}
