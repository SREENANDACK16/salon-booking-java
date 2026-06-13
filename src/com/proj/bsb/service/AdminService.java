package com.proj.bsb.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import com.proj.bsb.bean.Booking;
import com.proj.bsb.bean.Category;
import com.proj.bsb.bean.CustomerReg;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.ManageBooking;
import com.proj.bsb.bean.Salon;
import com.proj.bsb.bean.SalonCategory;
import com.proj.bsb.bean.SalonOwner;
import com.proj.bsb.dao.AdminDao;

public class AdminService {
	AdminDao dao = new AdminDao();
	
	 public boolean validateAdmin(String username, String pwd) {
	        return dao.validateAdmin(username, pwd);
	    }
	public int getExpertCount() throws SQLException {
	    return dao.getExpertCount();
	}
	public int getTodayBookingCount() throws SQLException {
	    return dao.getTodayBookingCount();
	}
	public int getHappyCustomerPercent() {
	    return 92; // You can calculate if needed
	}
	public List<Integer> getWeeklyBookingData() throws SQLException {
	    return dao.getWeeklyBookingData(); // 7 data points
	}


    public List<Salon> getSalons(String location) throws Exception {
        return dao.getAllSalons(location);
    }

    public void addSalon(Salon s) throws Exception {
        dao.addSalon(s);
    }

    public void deleteSalon(int id) throws Exception {
        dao.deleteSalon(id);
    }

    public int getSalonCount() throws Exception {
        return dao.getSalonCount();
    }
    public Salon getSalonById(int id) throws Exception {
        return dao.getSalonById(id);
    }
    public List<Salon> getAllSalons() {
        return dao.getAllSalons();
    }
    public boolean approveSalon(SalonOwner owner) {
        return dao.approveSalon(owner);
    }
    public void updateSalon(Salon s) throws Exception {
        dao.updateSalon(s);
    }
    public List<Experts> getAllExperts() {
        return dao.getAllExperts();
    }

    public List<Experts> getExpertsBySalonId(int salonId) {
        return dao.getExpertsBySalonId(salonId);
    }
    public Experts getExpertById(int id) {
        return dao.getExpertById(id);
    }
    
    public List<CustomerReg> getAllCustomers() {
        return dao.getAllCustomers();
    }

    public List<CustomerReg> searchCustomersByPhone(String phone) {
        return dao.searchCustomersByPhone(phone);
    }
    public int getTotalCustomersCount() {
        return dao.getTotalCustomersCount();
    }
    public List<ManageBooking> getAllBookings() {
        return dao.getAllBookings();
    }

    public int getBookingCount() {
        return dao.getBookingCount();
    }

    public List<ManageBooking> getBookingsByDate(String date) {
        return dao.getBookingsByDate(date);
    }

    public List<ManageBooking> getBookingsByMonth(String month) {
        return dao.getBookingsByMonth(month);
    }

    public List<ManageBooking> getBookingsByYear(String year) {
        return dao.getBookingsByYear(year);
    }

    public List<ManageBooking> searchBookingsByPhone(String phone) {
        return dao.searchBookingsByPhone(phone);
    }

    public boolean deleteBookingById(int id) {
        return dao.deleteBookingById(id);
    }

    public void allotBookingById(int id) {
        dao.allotBookingById(id);
    }
    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }

    public void addCategory(Category cat) {
    	dao.addCategory(cat);
    }

    public void deleteCategory(int id) {
    	dao.deleteCategory(id);
    }

    public Category getCategoryById(int id) {
        return dao.getCategoryById(id);
    }

    public void updateCategory(Category cat) {
    	dao.updateCategory(cat);
    }

    public int getCategoryCount() {
        return dao.getCategoryCount();
    }
    public List<SalonCategory> getAllSalonCategoryAllocations() {
        return dao.getAllAllocations();
    }
    
    public boolean addSalonCategory(int salonId, int categoryId) {
        return dao.addSalonCategory(salonId, categoryId);
    }

    public boolean deleteSalonCategory(int salonId, int categoryId) {
        return dao.deleteSalonCategory(salonId, categoryId);
    }

    public List<SalonCategory> searchSalonCategory(String salonName) {
        return dao.searchBySalonName(salonName);
    }

    public List<SalonCategory> filterSalonCategory(String salonName) {
        return dao.filterBySalon(salonName);
    }

    public List<Salon> getAllSalonNames() {
        return dao.getAllSalonNames();
    }
  
    public List<Category> getAllCategory() {
        return dao.getAllCategories();
    }


   

}
