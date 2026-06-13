package com.proj.bsb.service;

import com.proj.bsb.bean.BePartner;
import com.proj.bsb.bean.Booking;
import com.proj.bsb.bean.CustomerReg;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.Salon;
import com.proj.bsb.dao.SalonDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalonService {
    private SalonDao dao = new SalonDao();

    public List<Salon> searchSalons(String location, String category, Double latitude, Double longitude) throws Exception {
        return dao.searchSalons(location, category, latitude, longitude);
    }
    public List<Salon> getTopSalons(int limit) throws Exception {
        return dao.getTopSalons(limit);
    }
    public List<Experts> getOurExperts(int limit) throws Exception {
        return dao.getOurExperts(limit);
    }
    public boolean registerPartner(BePartner partner) {
        return dao.insertPartner(partner);
    }
    public boolean customerRegDetails(CustomerReg cr) {
		boolean crlag=dao.customerRegDetails(cr);
		return crlag;
	}
    public boolean validateCustomer(String name,String phone) {
		 boolean vcflag= dao.validateCustomer(name, phone);
		 return vcflag;
    }
	public CustomerReg getRegDetailsByPhone(String phone) {
		CustomerReg cr= dao.getRegDetailsByPhone(phone);
		
		return cr;
	}
	public Salon getSalonById(int salonId) {
        return dao.findById(salonId);
    }
	 public boolean bookSlot(Booking booking) {
	        
	        return dao.insertBooking(booking);
	    }
	 public List<Booking> getBookingsByUser(String username) {
	        return dao.getBookingsByUser(username);
	    }

	

		public List<Booking> getBookingsByUserAndSalon(String username, String salonName) {
		    return dao.getBookingsByUserAndSalon(username, salonName);
		}

	    public void updateBookingRating(int bookingId, int rating, String comments) {
	        dao.updateBookingRating(bookingId, rating, comments);
	    }
	    public boolean cancelBooking(int bookingId) {
	        return dao.cancelBooking(bookingId);
	    }
	    public boolean updateBookingTime(int bookingId, String newTime) {
	        return dao.updateBookingTime(bookingId, newTime);
	    }
	    public void updateBookingStatus(int bookingId, String status) throws SQLException {
	        dao.updateBookingStatus(bookingId, status);
	    }
	    public List<Booking> getBookingsByUserWithSeenStatus(String username) {
	        return dao.getBookingsByUserWithSeenStatus(username);
	    }

	    public void markBookingsAsSeen(String username) throws SQLException {
	        dao.markBookingsAsSeen(username);
	    }


   
}
