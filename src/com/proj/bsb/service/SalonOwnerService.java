package com.proj.bsb.service;


import java.sql.SQLException;
import java.util.List;

import com.proj.bsb.bean.Booking;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.SalonOwner;
import com.proj.bsb.dao.SalonOwnerDao;

public class SalonOwnerService {
	
	   SalonOwnerDao dao = new SalonOwnerDao();

	  

	   public SalonOwner login(String username,String password)
	   {
	       return dao.login(username,password);
	   }
	   
	   public int getTotalExperts(int salonId)
	   {
	       return dao.getTotalExperts(salonId);
	   }

	   public int getTotalCustomers(int salonId)
	   {
	       return dao.getTotalCustomers(salonId);
	   }

	   public int getTodayBookings(int salonId)
	   {
	       return dao.getTodayBookings(salonId);
	   }

	   public int getMonthBookings(int salonId)
	   {
	       return dao.getMonthBookings(salonId);
	   }
	   
	   public List<Booking> getBookingsBySalon(int salonId) {
	        return dao.getBookingsBySalon(salonId);
	    }

	    public List<Booking> getBookingsByDate(int salonId, String date) {
	        return dao.getBookingsByDate(salonId, date);
	    }

	    public List<Booking> getBookingsByMonth(int salonId, String month) {
	        return dao.getBookingsByMonth(salonId, month);
	    }

	    public List<Booking> getBookingsByYear(int salonId, String year) {
	        return dao.getBookingsByYear(salonId, year);
	    }

	    public int getTotalCount(int salonId) {
	        return dao.getTotalCount(salonId);
	    }
	    public boolean updateBookingStatus(int bookingId, String newStatus) {
	        return dao.updateBookingStatus(bookingId, newStatus);
	    }
	    public void markBookingsAsSeenByOwner(int salonId) throws SQLException {
	        dao.markBookingsAsSeenByOwner(salonId);
	    }
	    public List<Booking> getBookingsBySalonIdWithSeenStatus(int salonId) throws SQLException {
	        return dao.getBookingsBySalonIdWithSeenStatus(salonId);
	    }
	    public int getTotalBookings(int salonId) {
	        return dao.getBookingCount(salonId, null); // null = all statuses
	    }

	    public int getPendingBookings(int salonId) {
	        return dao.getBookingCount(salonId, "Change Requested (Time)");
	    }

	    public int getApprovedBookings(int salonId) {
	        return dao.getBookingCount(salonId, "Approved");
	    }

	    public int getCancelledBookings(int salonId) {
	        return dao.getBookingCount(salonId, "Cancelled");
	    }

	    public String getWeekLabelsJson(int salonId) {
	        return dao.getWeekLabels(salonId);  // returns JSON string like "['Mon','Tue',...]"
	    }

	    public String getWeeklyBookingCountsJson(int salonId) {
	        return dao.getWeeklyBookingCounts(salonId); // returns JSON string like "[3, 5, 2, 0, ...]"
	    }
	    public List<Experts> getExpertsBySalon(
	            int salonId)
	    {
	        return dao.getExpertsBySalon(
	                salonId);
	    }

	    public boolean addExpert(
	            Experts e)
	    {
	        return dao.addExpert(e);
	    }

	    public boolean deleteExpert(
	            int expertId)
	    {
	        return dao.deleteExpert(
	                expertId);
	    }

	    public Experts getExpertById(int id) {
	        return dao.getExpertById(id);
	    }
	   

	    public void updateExpert(Experts expert) {
	        dao.updateExpert(expert);
	    }
	    public List<Experts> searchExpertsBySalon(
	            int salonId,
	            String expertName)
	    {
	        return dao.searchExpertsBySalon(
	                salonId,
	                expertName);
	    }


}
