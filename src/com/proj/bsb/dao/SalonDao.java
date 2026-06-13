package com.proj.bsb.dao;

import com.proj.bsb.bean.BePartner;
import com.proj.bsb.bean.Booking;
import com.proj.bsb.bean.CustomerReg;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.Salon;
import com.proj.bsb.util.DBUtil;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SalonDao {

	 public List<Salon> searchSalons(String location, String category, Double lat, Double lng) throws Exception {
	        List<Salon> salons = new ArrayList<>();
	        Connection conn = DBUtil.getConnection();
	        PreparedStatement ps = null;

	        if (lat != null && lng != null && category != null && !category.isEmpty()) {
	            // GPS-based search using Haversine formula
	            String sql = "SELECT s.* FROM salon s JOIN salon_category sc ON s.salon_id = sc.salon_id JOIN category c ON sc.category_id = c.category_id WHERE (6371 * ACOS(COS((? * 3.14159265359) / 180) * COS((s.latitude * 3.14159265359) / 180) * COS(((s.longitude - ?) * 3.14159265359) / 180) + SIN((? * 3.14159265359) / 180) * SIN((s.latitude * 3.14159265359) / 180))) <= 5 AND LOWER(c.category_name) = ?";

	            ps = conn.prepareStatement(sql);
	            ps.setDouble(1, lat);
	            ps.setDouble(2, lng);
	            ps.setDouble(3, lat);
	            ps.setString(4, category.toLowerCase());
	        } else if (location != null && !location.isEmpty() && category != null && !category.isEmpty()) {
	            // Manual location search
	            String sql = "SELECT s.* FROM salon s JOIN salon_category sc ON s.salon_id = sc.salon_id JOIN category c ON sc.category_id = c.category_id WHERE LOWER(s.location) = ? AND LOWER(c.category_name) = ?";

	            ps = conn.prepareStatement(sql);
	            ps.setString(1, location.toLowerCase());
	            ps.setString(2, category.toLowerCase());
	        }
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Salon salon = new Salon();
	            salon.setSalonId(rs.getInt("salon_id"));
	            salon.setSalonName(rs.getString("salon_name"));
	            salon.setLocation(rs.getString("location"));
	            salon.setLatitude(rs.getDouble("latitude"));
	            salon.setLongitude(rs.getDouble("longitude"));
	            salon.setAddress(rs.getString("address"));
	            salon.setContactNo(rs.getString("contact_no"));
	            salon.setPhoto(rs.getBytes("photo")); // byte[]

	            salons.add(salon);
	            //System.out.println(salon);
	        }
	        

	        rs.close();
	        ps.close();
	        conn.close();

	        return salons;
	    }
	 
	 public List<Salon> getTopSalons(int limit) throws Exception {
		    List<Salon> salons = new ArrayList<>();
		    Connection conn = DBUtil.getConnection();
		    String sql = "SELECT * FROM (SELECT * FROM salon) WHERE ROWNUM <= ?";
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setInt(1, limit);
		    ResultSet rs = ps.executeQuery();

		    while (rs.next()) {
		        Salon salon = new Salon();
		        salon.setSalonId(rs.getInt("salon_id"));
		        salon.setSalonName(rs.getString("salon_name"));
		        salon.setLocation(rs.getString("location"));
		        salon.setLatitude(rs.getDouble("latitude"));
		        salon.setLongitude(rs.getDouble("longitude"));
		        salon.setAddress(rs.getString("address"));
		        salon.setContactNo(rs.getString("contact_no"));
		        salon.setPhoto(rs.getBytes("photo"));
		        salons.add(salon);
		    }

		    rs.close();
		    ps.close();
		    conn.close();

		    return salons;
		}
	 public List<Experts> getOurExperts(int limit) throws Exception {
		    List<Experts> experts = new ArrayList<>();
		    Connection conn = DBUtil.getConnection();
		    String sql = "SELECT * FROM (SELECT * FROM experts) WHERE ROWNUM <= ?";
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setInt(1, limit);
		    ResultSet rs = ps.executeQuery();

		    while (rs.next()) {
		        Experts exp = new Experts();
		        exp.setExpertId(rs.getInt("expert_id"));
		        exp.setSalonId(rs.getInt("salon_id"));
		        exp.setExpertName(rs.getString("expert_name"));
		        //exp.setGender(rs.getString("gender"));
		       // exp.setDob(rs.getDouble("latitude"));
		        exp.setSpecialization(rs.getString("specialization"));
		        exp.setPhoto(rs.getBytes("photo"));
		        exp.setQualification(rs.getString("qualification"));
		        //salon.setContactNo(rs.getString("contact_no"));
		        //salon.setPhoto(rs.getBytes("photo"));
		        experts.add(exp);
		    }

		    rs.close();
		    ps.close();
		    conn.close();

		    return experts;
		}
	 public boolean insertPartner(BePartner partner) {
	        boolean isInserted = false;
	        String sql = "INSERT INTO be_partner (country, emirates, area, category, first_name, last_name, company_name, contact_no, message) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	             
	            ps.setString(1, partner.getCountry());
	            ps.setString(2, partner.getEmirates());
	            ps.setString(3, partner.getArea());
	            ps.setString(4, partner.getCategory());
	            ps.setString(5, partner.getFirstName());
	            ps.setString(6, partner.getLastName());
	            ps.setString(7, partner.getCompanyName());
	            ps.setString(8, partner.getContactNo());
	            ps.setString(9, partner.getMessage());

	            isInserted = ps.executeUpdate() > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return isInserted;
	    }
	 
	 public boolean customerRegDetails(CustomerReg cr)
		{
			try {
				  Connection con = DBUtil.getConnection();
				  String cmd="insert into customer_reg(customer_name,phone)values(?,?)";
				  PreparedStatement ps= con.prepareStatement(cmd);
				 
				  
				  ps.setString(1, cr.getName());
				  ps.setString(2,cr.getPhone());
				 //System.out.println(cr);
				  ps.executeUpdate();
				 return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return false;
			
		}
	 public boolean validateCustomer(String name,String phone) {
			
		  
		  try {
			  Connection con = DBUtil.getConnection();
       String cmd="select * from customer_reg where customer_name=? and phone=?";
			  PreparedStatement ps= con.prepareStatement(cmd);
	  ps.setString(1,name);
	  ps.setString(2,phone);
	  ResultSet rs= ps.executeQuery();
	  if(rs.next())
		  return true;
	  else
		  return false;
	  
		  }
		  catch(Exception e) {
			  
		  }
		  return false;
	  }
	 public CustomerReg getRegDetailsByPhone(String phone) {
			try {
				  Connection con = DBUtil.getConnection();
				  String cmd="select * from customer_reg where phone=?";
				  PreparedStatement ps= con.prepareStatement(cmd);
				  ps.setString(1, phone);
				  ResultSet rs= ps.executeQuery();
				  if(rs.next()) {
					
					String name= rs.getString(1);
					String phn= rs.getString(2);
					
					CustomerReg cr= new CustomerReg(name,phn);
					return cr;
					
				  }
			}
	             catch(Exception e) {
	            	 e.printStackTrace();
	             }
			
	             return null;
			
		}
	 public Salon findById(int salonId) {
	        Salon salon = null;
	        String sql = "SELECT salon_id, salon_name, location, latitude, longitude, address, contact_no FROM salon WHERE salon_id = ?";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, salonId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                salon = new Salon();
	                salon.setSalonId(rs.getInt("salon_id"));
	                salon.setSalonName(rs.getString("salon_name"));
	                salon.setLocation(rs.getString("location"));
	                salon.setLatitude(rs.getDouble("latitude"));
	                salon.setLongitude(rs.getDouble("longitude"));
	                salon.setAddress(rs.getString("address"));
	                salon.setContactNo(rs.getString("contact_no"));
	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return salon;
	    }

	 
	 public boolean insertBooking(Booking booking) {
	        boolean status = false;
	        try (Connection con = DBUtil.getConnection()) {
	            String sql = "INSERT INTO salon_booking (salon_id,username, phone, booking_date, booking_time, flexible, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, booking.getSalonId());
	            ps.setString(2, booking.getUsername());
	            ps.setString(3, booking.getPhone());
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate = sdf.parse(booking.getDate());
	            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	            ps.setDate(4, sqlDate);
	            ps.setString(5, booking.getTime());
	            ps.setString(6, booking.getFlexible());
	            ps.setString(7, booking.getStatus()); 

	            status = ps.executeUpdate() > 0;
	            //System.out.println(booking);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return status;
	    }
	 public List<Booking> getBookingsByUser(String username) {
		    List<Booking> list = new ArrayList<>();
		    try (Connection con = DBUtil.getConnection();
		         PreparedStatement ps = con.prepareStatement(
		             "SELECT b.*, s.salon_name FROM salon_booking b JOIN salon s ON b.salon_id = s.salon_id WHERE b.username = ?")) {

		        ps.setString(1, username);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            Booking b = new Booking();
		            b.setId(rs.getInt("id"));
		            b.setSalonId(rs.getInt("salon_id"));
		            b.setSalonName(rs.getString("salon_name"));
		            b.setUsername(rs.getString("username"));
		            b.setPhone(rs.getString("phone"));
		            b.setDate(rs.getString("booking_date"));
		            b.setTime(rs.getString("booking_time"));
		            b.setFlexible(rs.getString("flexible"));
		            b.setRating(rs.getInt("rating"));
		            b.setComments(rs.getString("comments"));
		            b.setStatus(rs.getString("status"));
		            list.add(b);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return list;
		}
	 public void updateBookingRating(int bookingId, int rating, String comments) {
		    String sql = "UPDATE salon_booking SET rating = ?, comments = ? WHERE id = ?";
		    try (Connection con = DBUtil.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        ps.setInt(1, rating);
		        ps.setString(2, comments);
		        ps.setInt(3, bookingId);
		        ps.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	 private Booking mapBooking(ResultSet rs) throws SQLException {
		    Booking b = new Booking();
		    b.setId(rs.getInt("id"));
		    b.setSalonId(rs.getInt("salon_id"));
		    b.setSalonName(rs.getString("salon_name"));
		    b.setUsername(rs.getString("username"));
		    b.setPhone(rs.getString("phone"));
		    b.setDate(rs.getString("booking_date"));
		    b.setTime(rs.getString("booking_time"));
		    b.setFlexible(rs.getString("flexible"));
		    b.setRating(rs.getInt("rating"));  
		    b.setComments(rs.getString("comments")); 
		    b.setStatus(rs.getString("status"));
		    return b;
		}

	

		
		
		public List<Booking> getBookingsByUserAndSalon(String username, String salonName) {
		    List<Booking> list = new ArrayList<>();
		    try (Connection con = DBUtil.getConnection();
		         PreparedStatement ps = con.prepareStatement(
		             "SELECT b.ID, b.SALON_ID, b.USERNAME, b.PHONE, b.BOOKING_DATE, b.BOOKING_TIME,b.FLEXIBLE, b.RATING, b.COMMENTS, b.STATUS, s.SALON_NAME FROM SALON_BOOKING b JOIN SALON s ON b.SALON_ID = s.SALON_ID WHERE b.username=? AND LOWER(s.salon_name) LIKE ?")) {
		        ps.setString(1, username);
		        ps.setString(2, "%" + salonName.toLowerCase() + "%");
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            list.add(mapBooking(rs));
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return list;
		}



	 public boolean cancelBooking(int bookingId) {
		    boolean success = false;
		    Connection con = null;
		    PreparedStatement ps = null;

		    try {
		        con = DBUtil.getConnection();
		        String sql = "UPDATE salon_booking SET status = 'Cancelled' WHERE id = ?";
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, bookingId);

		        int rows = ps.executeUpdate();
		        success = rows > 0;

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DBUtil.close(con, ps, null);
		    }

		    return success;
		}



	 public String getSalonPhoneById(int salonId) throws Exception {
		    String phone = null;
		    Connection con = DBUtil.getConnection();
		    String sql = "SELECT contact_no FROM salon WHERE salon_id = ?";
		    PreparedStatement ps = con.prepareStatement(sql);
		    ps.setInt(1, salonId);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        phone = rs.getString("contact_no");
		    }
		    con.close();
		    return phone;
		}
	 public boolean updateBookingTime(int bookingId, String newTime) {
		    String sql = "UPDATE SALON_BOOKING SET  BOOKING_TIME = ? WHERE ID = ?";
		    try (Connection con = DBUtil.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        ps.setString(1, newTime);
		        ps.setInt(2, bookingId);
		        return ps.executeUpdate() > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return false;
		}
	 public void updateBookingStatus(int bookingId, String status) throws SQLException {
		    String sql = "UPDATE salon_booking SET status = ?, seen_by_owner = 'N' WHERE id = ?" ;
		    try (Connection con = DBUtil.getConnection();
		    PreparedStatement ps = con.prepareStatement(sql)){
		    ps.setString(1, status);
		    ps.setInt(2, bookingId);
		    ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		}

	 public List<Booking> getBookingsByUserWithSeenStatus(String username) {
		    List<Booking> list = new ArrayList<>();
		    try (Connection con = DBUtil.getConnection();
		         PreparedStatement ps = con.prepareStatement(
		             "SELECT b.*, s.salon_name FROM salon_booking b JOIN salon s ON b.salon_id = s.salon_id WHERE b.username = ?")) {

		        ps.setString(1, username);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            Booking b = new Booking();
		            b.setId(rs.getInt("id"));
		            b.setSalonId(rs.getInt("salon_id"));
		            b.setSalonName(rs.getString("salon_name"));
		            b.setUsername(rs.getString("username"));
		            b.setPhone(rs.getString("phone"));
		            b.setDate(rs.getString("booking_date"));
		            b.setTime(rs.getString("booking_time"));
		            b.setFlexible(rs.getString("flexible"));
		            b.setRating(rs.getInt("rating"));
		            b.setComments(rs.getString("comments"));
		            b.setStatus(rs.getString("status"));
		            b.setSeenByUser(rs.getString("seen_by_user")); // ✅ This line is added
		            list.add(b);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return list;
		}

	 public void markBookingsAsSeen(String username) throws SQLException {
		    String sql = "UPDATE salon_booking SET seen_by_user = 'Y' WHERE username = ? AND seen_by_user = 'N'";
		    try (Connection conn = DBUtil.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {
		        ps.setString(1, username);
		        ps.executeUpdate();
		    }
		}





}
