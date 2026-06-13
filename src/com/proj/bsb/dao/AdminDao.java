package com.proj.bsb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proj.bsb.bean.Booking;
import com.proj.bsb.bean.Category;
import com.proj.bsb.bean.CustomerReg;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.ManageBooking;
import com.proj.bsb.bean.Salon;
import com.proj.bsb.bean.SalonCategory;
import com.proj.bsb.bean.SalonOwner;
import com.proj.bsb.util.DBUtil;


public class AdminDao {
	
	public boolean validateAdmin(String username, String pwd) {
        String cmd = "SELECT * FROM SYSTEM.adminlogin WHERE username = ? AND pwd = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(cmd)) {
            ps.setString(1, username);
            ps.setString(2, pwd);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if a match found
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	public int getExpertCount() throws SQLException {
	    String sql = "SELECT COUNT(*) FROM experts";
	    try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
	        return rs.next() ? rs.getInt(1) : 0;
	    }
	}

	public int getTodayBookingCount() throws SQLException {
	    String sql = "SELECT COUNT(*) FROM salon_booking WHERE booking_date = CURRENT_DATE";
	    try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
	        return rs.next() ? rs.getInt(1) : 0;
	    }
	}

	public List<Integer> getWeeklyBookingData() throws SQLException {
	    List<Integer> data = new ArrayList<>();
	    String sql = "SELECT TO_CHAR(booking_date, 'DY') AS day, COUNT(*) " +
	                 "FROM salon_booking WHERE booking_date >= SYSDATE - 7 " +
	                 "GROUP BY TO_CHAR(booking_date, 'DY') ORDER BY MIN(booking_date)";
	    Map<String, Integer> map = new HashMap<>();
	    try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            map.put(rs.getString(1).toUpperCase(), rs.getInt(2));
	        }
	    }

	    String[] weekDays = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
	    for (String day : weekDays) {
	        data.add(map.getOrDefault(day, 0));
	    }

	    return data;
	}
	
	 public void addSalon(Salon s) throws Exception {
	        Connection con = DBUtil.getConnection();
	        String sql = "INSERT INTO salon (salon_name, location, latitude, longitude, address, contact_no, photo,status) VALUES ( ?, ?, ?, ?,?,?, ?,?)";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, s.getSalonName());
	        ps.setString(2, s.getLocation());
	        ps.setDouble(3, s.getLatitude());
	        ps.setDouble(4, s.getLongitude());
	        ps.setString(5, s.getAddress());
	        ps.setString(6, s.getContactNo());
	        ps.setBytes(7, s.getPhoto());
	        ps.setString(8, "Pending");
	        ps.executeUpdate();
	        con.close();
	    }

	    public List<Salon> getAllSalons(String locationFilter) throws Exception {
	        List<Salon> list = new ArrayList<>();
	        Connection con = DBUtil.getConnection();
	        String sql = "SELECT * FROM salon WHERE location LIKE ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, "%" + locationFilter + "%");
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Salon s = new Salon();
	            s.setSalonId(rs.getInt("salon_id"));
	            s.setSalonName(rs.getString("salon_name"));
	            s.setLocation(rs.getString("location"));
	            s.setLatitude(rs.getDouble("latitude"));
	            s.setLongitude(rs.getDouble("longitude"));
	            s.setAddress(rs.getString("address"));
	            s.setContactNo(rs.getString("contact_no"));
	            s.setPhoto(rs.getBytes("photo"));
	            s.setStatus(rs.getString("STATUS"));
	            
	         
	            list.add(s);
	        }
	        con.close();
	        return list;
	    }

	    public void deleteSalon(int id) throws Exception {
	        Connection con = DBUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement("DELETE FROM salon WHERE salon_id = ?");
	        ps.setInt(1, id);
	        ps.executeUpdate();
	        con.close();
	    }

	    public int getSalonCount() throws Exception {
	        Connection con = DBUtil.getConnection();
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM salon");
	        rs.next();
	        int count = rs.getInt(1);
	        con.close();
	        return count;
	    }
	    public Salon getSalonById(int id) throws Exception {
	        Connection con = DBUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM salon WHERE salon_id = ?");
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        Salon s = null;
	        if (rs.next()) {
	            s = new Salon();
	            s.setSalonId(rs.getInt("salon_id"));
	            s.setSalonName(rs.getString("salon_name"));
	            s.setLocation(rs.getString("location"));
	            s.setLatitude(rs.getDouble("latitude"));
	            s.setLongitude(rs.getDouble("longitude"));
	            s.setAddress(rs.getString("address"));
	            s.setContactNo(rs.getString("contact_no"));
	            s.setPhoto(rs.getBytes("photo"));
	            s.setStatus(rs.getString("STATUS"));
	            
	            
	        }
	        return s;
	    }
	    public List<Salon> getAllSalons() {
	        List<Salon> list = new ArrayList<>();
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement("SELECT * FROM salon");
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Salon s = new Salon();
	                s.setSalonId(rs.getInt("salon_id"));
	                s.setSalonName(rs.getString("salon_name"));
	                s.setLocation(rs.getString("location"));
	                s.setAddress(rs.getString("address"));
	                s.setContactNo(rs.getString("contact_no"));
	                s.setPhoto(rs.getBytes("photo"));
	                s.setStatus(rs.getString("STATUS"));
	                
	                
	                list.add(s);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	    
	    
	    public boolean approveSalon(SalonOwner owner) {

	        boolean status = false;
	        Connection con = null;
	        PreparedStatement ps1 = null;
	        PreparedStatement ps2 = null;

	        try {

	            con = DBUtil.getConnection();

	            // Insert owner login
	            String sql1 =
	            "INSERT INTO salon_owner (SALON_ID, USERNAME, PASSWORD) VALUES (?, ?, ?)";

	            ps1 = con.prepareStatement(sql1);
	            ps1.setInt(1, owner.getSalonId());
	            ps1.setString(2, owner.getUsername());
	            ps1.setString(3, owner.getPassword());

	            int rows = ps1.executeUpdate();

	            if(rows > 0) {

	                // Update salon status
	                String sql2 =
	                "UPDATE salon SET status='Approved' WHERE salon_id=?";

	                ps2 = con.prepareStatement(sql2);
	                ps2.setInt(1, owner.getSalonId());

	                ps2.executeUpdate();

	                status = true;
	            }

	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.close(con, ps1, null);
	            DBUtil.close(null, ps2, null);
	        }

	        return status;
	    }
	    
	    public void updateSalon(Salon s) throws Exception {
	        Connection con = DBUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement(
	            "UPDATE salon SET salon_name=?, location=?, latitude=?, longitude=?, address=?, contact_no=?,photo=? WHERE salon_id=?"
	        );
	        ps.setString(1, s.getSalonName());
	        ps.setString(2, s.getLocation());
	        ps.setDouble(3, s.getLatitude());
	        ps.setDouble(4, s.getLongitude());
	        ps.setString(5, s.getAddress());
	        ps.setString(6, s.getContactNo());
	        ps.setBytes(7, s.getPhoto());
	        
	        ps.setInt(8, s.getSalonId());
	        ps.executeUpdate();
	    }
	    public List<Experts> getAllExperts() {
	        List<Experts> list = new ArrayList<>();
	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement("SELECT * FROM experts");
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Experts e = extractExpertFromResultSet(rs);
	                list.add(e);
	            }
	        } catch (Exception ex) { ex.printStackTrace(); }
	        return list;
	    }
	    

	   

	    public Experts getExpertById(int id) {
	        String sql = "SELECT * FROM experts WHERE expert_id=?";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                Experts e = new Experts();
	                e.setExpertId(rs.getInt("expert_id"));
	                e.setSalonId(rs.getInt("salon_id"));
	                e.setExpertName(rs.getString("expert_name"));
	                e.setGender(rs.getString("gender"));
	                e.setDob(rs.getString("dob"));
	                e.setSpecialization(rs.getString("specialization"));
	                e.setPhone(rs.getString("phone"));
	                e.setEmail(rs.getString("email"));
	                e.setQualification(rs.getString("qualification"));
	                e.setAvailability(rs.getString("availability"));
	                e.setStatus(rs.getString("status"));
	                e.setDoj(rs.getString("doj"));
	                e.setPhoto(rs.getBytes("photo"));
	                return e;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public List<Experts> getExpertsBySalonId(int salonId) {
	        List<Experts> list = new ArrayList<>();
	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement("SELECT * FROM experts WHERE salon_id = ?")) {
	            ps.setInt(1, salonId);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Experts e = extractExpertFromResultSet(rs);
	                    list.add(e);
	                }
	            }
	        } catch (Exception ex) { ex.printStackTrace(); }
	        return list;
	    }

	   

	    private Experts extractExpertFromResultSet(ResultSet rs) throws SQLException {
	        Experts e = new Experts();
	        e.setExpertId(rs.getInt("expert_id"));
	        e.setSalonId(rs.getInt("salon_id"));
	        e.setExpertName(rs.getString("expert_name"));
	        e.setGender(rs.getString("gender"));
	        e.setDob(rs.getString("dob"));
	        e.setSpecialization(rs.getString("specialization"));
	        e.setPhone(rs.getString("phone"));
	        e.setEmail(rs.getString("email"));
	        e.setQualification(rs.getString("qualification"));
	        e.setAvailability(rs.getString("availability"));
	        e.setStatus(rs.getString("status"));
	        e.setDoj(rs.getString("doj"));
	        e.setPhoto(rs.getBytes("photo"));
	        return e;
	    }

	    public List<CustomerReg> getAllCustomers() {
	        List<CustomerReg> list = new ArrayList<>();
	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement("SELECT CUSTOMER_ID,CUSTOMER_NAME, PHONE FROM customer_reg");
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                list.add(new CustomerReg(rs.getString("CUSTOMER_NAME"), rs.getString("PHONE")));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    public List<CustomerReg> searchCustomersByPhone(String phone) {
	        List<CustomerReg> list = new ArrayList<>();
	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement("SELECT CUSTOMER_NAME, PHONE FROM customer_reg WHERE PHONE LIKE ?")) {
	            
	            ps.setString(1, "%" + phone + "%");
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                list.add(new CustomerReg(rs.getString("CUSTOMER_NAME"), rs.getString("PHONE")));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	    public int getTotalCustomersCount() {
	        int count = 0;
	        String sql = "SELECT COUNT(*) AS total FROM customer_reg";
	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            
	            if (rs.next()) {
	                count = rs.getInt("total");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return count;
	    }


	        // Get all bookings
	    public List<ManageBooking> getAllBookings() {
	        List<ManageBooking> list = new ArrayList<>();
	        String sql = "SELECT b.*, s.SALON_NAME FROM salon_booking b " +
	                     "JOIN salon s ON b.SALON_ID = s.SALON_ID " +
	                     "ORDER BY b.BOOKING_DATE DESC";
	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                ManageBooking b = new ManageBooking(
	                    rs.getInt("ID"),
	                    rs.getInt("SALON_ID"),
	                    rs.getString("USERNAME"),
	                    rs.getString("PHONE"),
	                    rs.getDate("BOOKING_DATE").toString(),
	                    rs.getString("BOOKING_TIME"),
	                    rs.getString("FLEXIBLE")
	                );
	                b.setSalonName(rs.getString("SALON_NAME"));  // Set salon name
	                list.add(b);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }


	        // Get total booking count
	        public int getBookingCount() {
	            int count = 0;
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM salon_booking");
	                 ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) count = rs.getInt(1);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return count;
	        }

	        // Filter bookings by date
	        public List<ManageBooking> getBookingsByDate(String date) {
	            List<ManageBooking> list = new ArrayList<>();
	            String sql = "SELECT b.*, s.SALON_NAME FROM salon_booking b " +
	                         "JOIN salon s ON b.SALON_ID = s.SALON_ID " +
	                         "WHERE TO_CHAR(b.BOOKING_DATE, 'YYYY-MM-DD') = ? " +
	                         "ORDER BY b.BOOKING_DATE DESC";
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setString(1, date);
	                ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                    ManageBooking b = new ManageBooking(
	                        rs.getInt("ID"),
	                        rs.getInt("SALON_ID"),
	                        rs.getString("USERNAME"),
	                        rs.getString("PHONE"),
	                        rs.getDate("BOOKING_DATE").toString(),
	                        rs.getString("BOOKING_TIME"),
	                        rs.getString("FLEXIBLE")
	                    );
	                    b.setSalonName(rs.getString("SALON_NAME"));
	                    b.setId(rs.getInt("ID"));
	                    list.add(b);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }


	        // Filter bookings by month
	        public List<ManageBooking> getBookingsByMonth(String month) {
	            List<ManageBooking> list = new ArrayList<>();
	            String sql ="SELECT b.*, s.SALON_NAME FROM salon_booking b JOIN salon s ON b.SALON_ID = s.SALON_ID  WHERE EXTRACT(MONTH FROM BOOKING_DATE) = ?";
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setString(1, month);
	                ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                	ManageBooking b = new ManageBooking(
	                    	rs.getInt("ID"),
	                        rs.getInt("SALON_ID"),
	                        rs.getString("USERNAME"),
	                        rs.getString("PHONE"),
	                        rs.getDate("BOOKING_DATE").toString(),
	                        rs.getString("BOOKING_TIME"),
	                        rs.getString("FLEXIBLE")
	                    );
	                	b.setSalonName(rs.getString("SALON_NAME")); 
	                    b.setId(rs.getInt("ID"));
	                    list.add(b);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }

	        // Filter bookings by year
	        public List<ManageBooking> getBookingsByYear(String year) {
	            List<ManageBooking> list = new ArrayList<>();
	            String sql = "SELECT b.*, s.SALON_NAME FROM salon_booking b JOIN salon s ON b.SALON_ID = s.SALON_ID WHERE TO_CHAR(BOOKING_DATE, 'YYYY') = ?";
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setString(1, year);
	                ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                	ManageBooking b = new ManageBooking(
	                    	rs.getInt("ID"),
	                        rs.getInt("SALON_ID"),
	                        rs.getString("USERNAME"),
	                        rs.getString("PHONE"),
	                        rs.getDate("BOOKING_DATE").toString(),
	                        rs.getString("BOOKING_TIME"),
	                        rs.getString("FLEXIBLE")
	                    );
	                    b.setId(rs.getInt("ID"));
	                    b.setSalonName(rs.getString("SALON_NAME"));
	                    list.add(b);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }

	        // Search bookings by phone
	        public List<ManageBooking> searchBookingsByPhone(String phone) {
	            List<ManageBooking> list = new ArrayList<>();
	            String sql = "SELECT b.*, s.SALON_NAME FROM salon_booking b JOIN salon s ON b.SALON_ID = s.SALON_ID  WHERE PHONE LIKE ?";
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setString(1, "%" + phone + "%");
	                ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                	ManageBooking b = new ManageBooking(
	                    		
	                    	rs.getInt("ID"),
	                        rs.getInt("SALON_ID"),
	                        rs.getString("USERNAME"),
	                        rs.getString("PHONE"),
	                        rs.getDate("BOOKING_DATE").toString(),
	                        rs.getString("BOOKING_TIME"),
	                        rs.getString("FLEXIBLE")
	                    );
	                    b.setId(rs.getInt("ID"));
	                    b.setSalonName(rs.getString("SALON_NAME"));
	                    list.add(b);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }

	        // Delete booking
	        public boolean deleteBookingById(int id) {
	            boolean status = false;
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("DELETE FROM salon_booking WHERE ID = ?")) {
	                ps.setInt(1, id);
	                status = ps.executeUpdate() > 0;
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return status;
	        }

	        // Allot booking (you can expand this based on actual logic)
	        public void allotBookingById(int id) {
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("UPDATE salon_booking SET FLEXIBLE = 'Allotted' WHERE ID = ?")) {
	                ps.setInt(1, id);
	                ps.executeUpdate();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    
	        public List<Category> getAllCategories() {
	            List<Category> list = new ArrayList<>();
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("SELECT * FROM category ORDER BY category_id")) {
	                ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                    list.add(new Category(rs.getInt(1), rs.getString(2)));
	                }
	            } catch (Exception e) { e.printStackTrace(); }
	            return list;
	        }

	        public void addCategory(Category c) {
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("INSERT INTO category (category_name) VALUES (?)")) {
	                ps.setString(1, c.getCategoryName());
	                ps.executeUpdate();
	            } catch (Exception e) { e.printStackTrace(); }
	        }

	        public void deleteCategory(int id) {
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("DELETE FROM category WHERE category_id=?")) {
	                ps.setInt(1, id);
	                ps.executeUpdate();
	            } catch (Exception e) { e.printStackTrace(); }
	        }

	        public Category getCategoryById(int id) {
	            Category c = null;
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("SELECT * FROM category WHERE category_id=?")) {
	                ps.setInt(1, id);
	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) {
	                    c = new Category(rs.getInt(1), rs.getString(2));
	                }
	            } catch (Exception e) { e.printStackTrace(); }
	            return c;
	        }

	        public void updateCategory(Category c) {
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("UPDATE category SET category_name=? WHERE category_id=?")) {
	                ps.setString(1, c.getCategoryName());
	                ps.setInt(2, c.getCategoryId());
	                ps.executeUpdate();
	            } catch (Exception e) { e.printStackTrace(); }
	        }

	        public int getCategoryCount() {
	            int count = 0;
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM category")) {
	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) count = rs.getInt(1);
	            } catch (Exception e) { e.printStackTrace(); }
	            return count;
	        }
	        public List<SalonCategory> getAllAllocations() {
	            List<SalonCategory> list = new ArrayList<>();
	            String sql = "SELECT sc.salon_id, sc.category_id, s.salon_name, c.category_name " +
	                         "FROM salon_category sc " +
	                         "JOIN salon s ON sc.salon_id = s.salon_id " +
	                         "JOIN category c ON sc.category_id = c.category_id";

	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql);
	                 ResultSet rs = ps.executeQuery()) {

	                while (rs.next()) {
	                    SalonCategory sc = new SalonCategory(
	                        rs.getInt("salon_id"),
	                        rs.getInt("category_id"),
	                        rs.getString("salon_name"),
	                        rs.getString("category_name")
	                    );
	                    list.add(sc);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }

	        public boolean addSalonCategory(int salonId, int categoryId) {
	            String sql = "INSERT INTO salon_category (salon_id, category_id) VALUES (?, ?)";
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setInt(1, salonId);
	                ps.setInt(2, categoryId);
	                return ps.executeUpdate() > 0;
	            } catch (Exception e) {
	                e.printStackTrace();
	                return false;
	            }
	        }

	        public boolean deleteSalonCategory(int salonId, int categoryId) {
	            String sql = "DELETE FROM salon_category WHERE salon_id = ? AND category_id = ?";
	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setInt(1, salonId);
	                ps.setInt(2, categoryId);
	                return ps.executeUpdate() > 0;
	            } catch (Exception e) {
	                e.printStackTrace();
	                return false;
	            }
	        }

	        public List<SalonCategory> searchBySalonName(String salonName) {
	            List<SalonCategory> list = new ArrayList<>();
	            String sql = "SELECT sc.salon_id, sc.category_id, s.salon_name, c.category_name " +
	                         "FROM salon_category sc " +
	                         "JOIN salon s ON sc.salon_id = s.salon_id " +
	                         "JOIN category c ON sc.category_id = c.category_id " +
	                         "WHERE LOWER(s.salon_name) LIKE LOWER(?)";

	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setString(1, "%" + salonName + "%");
	                ResultSet rs = ps.executeQuery();

	                while (rs.next()) {
	                    list.add(new SalonCategory(
	                        rs.getInt("salon_id"),
	                        rs.getInt("category_id"),
	                        rs.getString("salon_name"),
	                        rs.getString("category_name")
	                    ));
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }

	        public List<SalonCategory> filterBySalon(String salonName) {
	            List<SalonCategory> list = new ArrayList<>();
	            String sql = "SELECT sc.salon_id, sc.category_id, s.salon_name, c.category_name " +
	                         "FROM salon_category sc " +
	                         "JOIN salon s ON sc.salon_id = s.salon_id " +
	                         "JOIN category c ON sc.category_id = c.category_id " +
	                         "WHERE s.salon_name = ?";

	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setString(1, salonName);
	                ResultSet rs = ps.executeQuery();

	                while (rs.next()) {
	                    list.add(new SalonCategory(
	                        rs.getInt("salon_id"),
	                        rs.getInt("category_id"),
	                        rs.getString("salon_name"),
	                        rs.getString("category_name")
	                    ));
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }
	        public List<Salon> getAllSalonNames() {
	            List<Salon> list = new ArrayList<>();
	            String sql = "SELECT salon_id,salon_name FROM salon";

	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql);
	                 ResultSet rs = ps.executeQuery()) {
	            	while (rs.next()) {
	                    Salon sc = new Salon();
	                    sc.setSalonId(rs.getInt("salon_id"));
	                    sc.setSalonName(rs.getString("salon_name"));
	                    list.add(sc);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }
	        public List<Category> getAllCategory() {
	            List<Category> list = new ArrayList<>();
	            String sql = "SELECT category_id, category_name FROM category";

	            try (Connection con = DBUtil.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql);
	                 ResultSet rs = ps.executeQuery()) {

	                while (rs.next()) {
	                    list.add(new Category(
	                        rs.getInt("category_id"),
	                        rs.getString("category_name")
	                    ));
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return list;
	        }

	     
	       

}
