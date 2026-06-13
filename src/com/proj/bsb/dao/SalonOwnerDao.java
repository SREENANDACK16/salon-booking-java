package com.proj.bsb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proj.bsb.bean.Booking;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.SalonOwner;
import com.proj.bsb.util.DBUtil;

public class SalonOwnerDao {
	
	public SalonOwner login(String username, String password)
	{
	    SalonOwner owner = null;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	        	    "SELECT so.USER_ID, " +
	        	    "       so.SALON_ID, " +
	        	    "       so.USERNAME, " +
	        	    "       s.SALON_NAME " +
	        	    "FROM SALON_OWNER so " +
	        	    "JOIN SALON s ON so.SALON_ID = s.SALON_ID " +
	        	    "WHERE so.USERNAME=? AND so.PASSWORD=?";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, username);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            owner = new SalonOwner();

	            owner.setUserId(rs.getInt("USER_ID"));
	            owner.setSalonId(rs.getInt("SALON_ID"));
	            owner.setUsername(rs.getString("USERNAME"));
	            owner.setSalonName(rs.getString("SALON_NAME"));
	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

	    return owner;
	}
	
	public int getTotalExperts(int salonId)
	{
	    int count = 0;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	            "SELECT COUNT(*) FROM EXPERTS WHERE SALON_ID=?";

	        PreparedStatement ps =
	            con.prepareStatement(sql);

	        ps.setInt(1, salonId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }

	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	public int getTotalCustomers(int salonId)
	{
	    int count = 0;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	        "SELECT COUNT(DISTINCT PHONE) " +
	        "FROM SALON_BOOKING " +
	        "WHERE SALON_ID=?";

	        PreparedStatement ps =
	            con.prepareStatement(sql);

	        ps.setInt(1, salonId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }

	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

	    return count;
	}
	public int getTotalBookings(int salonId)
	{
	    int count = 0;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	        "SELECT COUNT(*) FROM SALON_BOOKING WHERE SALON_ID=?";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, salonId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	public int getPendingBookings(int salonId)
	{
	    int count = 0;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	        "SELECT COUNT(*) FROM SALON_BOOKING " +
	        "WHERE SALON_ID=? AND UPPER(STATUS)='PENDING'";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, salonId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	public int getApprovedBookings(int salonId)
	{
	    int count = 0;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	        "SELECT COUNT(*) FROM SALON_BOOKING " +
	        "WHERE SALON_ID=? AND UPPER(STATUS)='APPROVED'";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, salonId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	public int getCancelledBookings(int salonId)
	{
	    int count = 0;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	        "SELECT COUNT(*) FROM SALON_BOOKING " +
	        "WHERE SALON_ID=? AND UPPER(STATUS)='CANCELLED'";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, salonId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	public int getTodayBookings(int salonId)
	{
	    int count = 0;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	        "SELECT COUNT(*) " +
	        "FROM SALON_BOOKING " +
	        "WHERE SALON_ID=? " +
	        "AND TRUNC(BOOKING_DATE)=TRUNC(SYSDATE)";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, salonId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	public int getMonthBookings(int salonId)
	{
	    int count = 0;

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	        "SELECT COUNT(*) " +
	        "FROM SALON_BOOKING " +
	        "WHERE SALON_ID=? " +
	        "AND EXTRACT(MONTH FROM BOOKING_DATE)=EXTRACT(MONTH FROM SYSDATE) " +
	        "AND EXTRACT(YEAR FROM BOOKING_DATE)=EXTRACT(YEAR FROM SYSDATE)";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, salonId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	
	
	

	// All bookings
	public List<Booking> getBookingsBySalon(int salonId) {
	    List<Booking> list = new ArrayList<>();
	    String sql = "SELECT b.*, s.salon_name FROM salon_booking b " +
	             "JOIN salon s ON b.salon_id = s.salon_id " +
	             "WHERE b.salon_id = ? ORDER BY b.booking_date DESC";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            list.add(mapBooking(rs));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	// Date filter
	public List<Booking> getBookingsByDate(int salonId, String date) {
	    List<Booking> list = new ArrayList<>();
	    String sql = "SELECT b.*, s.salon_name FROM salon_booking b JOIN salon s ON b.salon_id = s.salon_id where b.salon_id = ? AND TO_CHAR(b.booking_date, 'YYYY-MM-DD') = ? ORDER BY b.booking_date DESC";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        ps.setString(2, date);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            list.add(mapBooking(rs));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
  
	// Month filter
	public List<Booking> getBookingsByMonth(int salonId, String month) {
	    List<Booking> list = new ArrayList<>();
	    String sql = "SELECT b.*, s.SALON_NAME FROM salon_booking b JOIN salon s ON b.SALON_ID = s.SALON_ID  WHERE b.SALON_ID=? and EXTRACT(MONTH FROM b.BOOKING_DATE) = ?";
	    try (Connection con = DBUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        ps.setString(2, month);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            list.add(mapBooking(rs));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	// Year filter
	public List<Booking> getBookingsByYear(int salonId, String year) {
	    List<Booking> list = new ArrayList<>();
	    String sql = "SELECT b.*, s.salon_name FROM salon_booking b JOIN salon s ON b.salon_id = s.salon_id  WHERE b.salon_id = ? AND To_CHAR(b.booking_date, 'YYYY') = ? ORDER BY b.booking_date DESC";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        ps.setString(2, year);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            list.add(mapBooking(rs));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	// Helper to map booking row to object
	private Booking mapBooking(ResultSet rs) throws SQLException {
	    Booking b = new Booking(
	        rs.getInt("salon_id"),
	        rs.getString("username"),
	        rs.getString("phone"),
	        rs.getDate("booking_date").toString(),
	        rs.getString("booking_time"),
	        rs.getString("flexible")
	    );
	    b.setId(rs.getInt("id"));
	    b.setSalonName(rs.getString("salon_name"));  
	    b.setStatus(rs.getString("status"));
	    b.setRating(rs.getInt("rating"));
	    b.setComments(rs.getString("comments"));
	    return b;
	}


	// Total count
	public int getTotalCount(int salonId) {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM salon_booking WHERE salon_id = ?";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return count;
	}

	public boolean updateBookingStatus(int bookingId, String newStatus) {
	    String sql = "UPDATE salon_booking SET STATUS = ?,seen_by_user='N' WHERE ID = ?";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, newStatus);
	        ps.setInt(2, bookingId);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public List<Booking> getBookingsBySalonIdWithSeenStatus(int salonId) throws SQLException {
	    List<Booking> list = new ArrayList<>();
	    String sql = "SELECT * FROM salon_booking WHERE salon_id = ?";

	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Booking b = new Booking();
	            b.setId(rs.getInt("id"));
	            b.setUsername(rs.getString("username"));
	            b.setSalonId(rs.getInt("salon_id"));
	            b.setPhone(rs.getString("phone"));
	            b.setDate(rs.getString("booking_date"));
	            b.setTime(rs.getString("booking_time"));
	            b.setFlexible(rs.getString("flexible"));
	            b.setStatus(rs.getString("status"));
	            b.setSeenByOwner(rs.getString("seen_by_owner")); // ✅ must exist in DB
	            list.add(b);
	        }
	    }
	    return list;
	}
	public void markBookingsAsSeenByOwner(int salonId) throws SQLException {
	    String sql = "UPDATE salon_booking SET seen_by_owner = 'Y' WHERE salon_id = ? AND seen_by_owner = 'N'";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        ps.executeUpdate();
	    }
	}
	public int getBookingCount(int salonId, String status) {
	    String sql = "SELECT COUNT(*) FROM salon_booking WHERE salon_id = ?";
	    if (status != null) {
	        sql += " AND status = ?";
	    }

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        if (status != null) ps.setString(2, status);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) return rs.getInt(1);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	public String getWeekLabels(int salonId) {
	    return "['Mon','Tue','Wed','Thu','Fri','Sat','Sun']"; // static for simplicity
	}

	public String getWeeklyBookingCounts(int salonId) {
	    String sql = "SELECT TO_CHAR(booking_date, 'DY') AS day, COUNT(*) AS count " +
	                 "FROM salon_booking WHERE salon_id = ? " +
	                 "AND booking_date >= TRUNC(SYSDATE, 'IW') " + // start of week
	                 "GROUP BY TO_CHAR(booking_date, 'DY')";

	    Map<String, Integer> counts = new HashMap<>();
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, salonId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            counts.put(rs.getString("day"), rs.getInt("count"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    String[] weekDays = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
	    List<Integer> finalCounts = new ArrayList<>();
	    for (String day : weekDays) {
	        finalCounts.add(counts.getOrDefault(day, 0));
	    }
	    return finalCounts.toString(); // "[3, 4, 2, 0, 1, 5, 0]"
	}
	
	public List<Experts> getExpertsBySalon(int salonId)
	{
	    List<Experts> list = new ArrayList<>();

	    try
	    {
	        Connection con =
	                DBUtil.getConnection();

	        String sql =
	        "SELECT * FROM EXPERTS " +
	        "WHERE SALON_ID=? " +
	        "ORDER BY EXPERT_ID DESC";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setInt(1,salonId);

	        ResultSet rs =
	                ps.executeQuery();

	        while(rs.next())
	        {
	            Experts e =
	                    new Experts();

	            e.setExpertId(
	                    rs.getInt("EXPERT_ID"));

	            e.setSalonId(
	                    rs.getInt("SALON_ID"));

	            e.setExpertName(
	                    rs.getString("EXPERT_NAME"));

	            e.setGender(
	                    rs.getString("GENDER"));

	            Date dobDate = rs.getDate("dob");
	            if (dobDate != null) {
	                e.setDob(new SimpleDateFormat("yyyy-MM-dd").format(dobDate));
	            } else {
	                e.setDob("");
	            }


	            e.setSpecialization(
	                    rs.getString("SPECIALIZATION"));

	            e.setPhone(
	                    rs.getString("PHONE"));

	            e.setEmail(
	                    rs.getString("EMAIL"));

	            e.setQualification(
	                    rs.getString("QUALIFICATION"));

	            e.setAvailability(
	                    rs.getString("AVAILABILITY"));

	            e.setStatus(
	                    rs.getString("STATUS"));

	            Date dojDate = rs.getDate("doj");
	            if (dojDate != null) {
	                e.setDoj(new SimpleDateFormat("yyyy-MM-dd").format(dojDate));
	            } else {
	                e.setDoj("");
	            }

	            e.setPhoto(
	                    rs.getBytes("PHOTO"));

	            list.add(e);
	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public boolean addExpert(Experts e)
	{
	    boolean status=false;

	    try
	    {
	        Connection con =
	                DBUtil.getConnection();

	        String sql=
	        "INSERT INTO EXPERTS " +
	        "(EXPERT_ID,SALON_ID,EXPERT_NAME," +
	        "GENDER,DOB,SPECIALIZATION," +
	        "PHONE,EMAIL,QUALIFICATION," +
	        "AVAILABILITY,STATUS,DOJ,PHOTO)" +
	        " VALUES(" +
	        "EXPERT_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setInt(1,e.getSalonId());
	        ps.setString(2,e.getExpertName());
	        ps.setString(3,e.getGender());

	        ps.setDate(4, java.sql.Date.valueOf(e.getDob()));

	        ps.setString(5,
	                e.getSpecialization());

	        ps.setString(6,
	                e.getPhone());

	        ps.setString(7,
	                e.getEmail());

	        ps.setString(8,
	                e.getQualification());

	        ps.setString(9,
	                e.getAvailability());

	        ps.setString(10,
	                e.getStatus());

	        ps.setDate(11, java.sql.Date.valueOf(e.getDoj()));

	        ps.setBytes(12,
	                e.getPhoto());

	        status=
	        ps.executeUpdate()>0;
	    }
	    catch(Exception ex)
	    {
	        ex.printStackTrace();
	    }

	    return status;
	}
	public void updateExpert(Experts expert) {

	    String sql = "UPDATE experts SET " +
	            "salon_id=?, expert_name=?, gender=?, dob=?, " +
	            "specialization=?, phone=?, email=?, qualification=?, " +
	            "availability=?, status=?, doj=?, photo=? " +
	            "WHERE expert_id=?";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, expert.getSalonId());
	        ps.setString(2, expert.getExpertName());
	        ps.setString(3, expert.getGender());
	        ps.setDate(4, java.sql.Date.valueOf(expert.getDob()));
	        ps.setString(5, expert.getSpecialization());
	        ps.setString(6, expert.getPhone());
	        ps.setString(7, expert.getEmail());
	        ps.setString(8, expert.getQualification());
	        ps.setString(9, expert.getAvailability());
	        ps.setString(10, expert.getStatus());
	        ps.setDate(11, java.sql.Date.valueOf(expert.getDoj()));
	        ps.setBytes(12, expert.getPhoto());
	        ps.setInt(13, expert.getExpertId());

	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public List<Experts> searchExpertsBySalon(
	        int salonId,
	        String expertName)
	{
	    List<Experts> list = new ArrayList<>();

	    try
	    {
	        Connection con = DBUtil.getConnection();

	        String sql =
	            "SELECT * FROM EXPERTS " +
	            "WHERE SALON_ID=? " +
	            "AND UPPER(EXPERT_NAME) LIKE UPPER(?) " +
	            "ORDER BY EXPERT_ID DESC";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setInt(1, salonId);
	        ps.setString(2, "%" + expertName + "%");

	        ResultSet rs = ps.executeQuery();

	        while(rs.next())
	        {
	            Experts e = new Experts();

	            e.setExpertId(rs.getInt("EXPERT_ID"));
	            e.setSalonId(rs.getInt("SALON_ID"));
	            e.setExpertName(rs.getString("EXPERT_NAME"));
	            e.setGender(rs.getString("GENDER"));

	            Date dobDate = rs.getDate("DOB");
	            if(dobDate != null)
	            {
	                e.setDob(
	                    new SimpleDateFormat("yyyy-MM-dd")
	                    .format(dobDate));
	            }

	            e.setSpecialization(rs.getString("SPECIALIZATION"));
	            e.setPhone(rs.getString("PHONE"));
	            e.setEmail(rs.getString("EMAIL"));
	            e.setQualification(rs.getString("QUALIFICATION"));
	            e.setAvailability(rs.getString("AVAILABILITY"));
	            e.setStatus(rs.getString("STATUS"));

	            Date dojDate = rs.getDate("DOJ");
	            if(dojDate != null)
	            {
	                e.setDoj(
	                    new SimpleDateFormat("yyyy-MM-dd")
	                    .format(dojDate));
	            }

	            e.setPhoto(rs.getBytes("PHOTO"));

	            list.add(e);
	        }

	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

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
	                Date dobDate = rs.getDate("dob");
	                if (dobDate != null) {
	                    e.setDob(new SimpleDateFormat("yyyy-MM-dd").format(dobDate));
	                }
	                e.setSpecialization(rs.getString("specialization"));
	                e.setPhone(rs.getString("phone"));
	                e.setEmail(rs.getString("email"));
	                e.setQualification(rs.getString("qualification"));
	                e.setAvailability(rs.getString("availability"));
	                e.setStatus(rs.getString("status"));
	                Date dojDate = rs.getDate("doj");
	                if (dojDate != null) {
	                    e.setDoj(new SimpleDateFormat("yyyy-MM-dd").format(dojDate));
	                }
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
	            System.out.println("Searching experts for salonId = " + salonId);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Experts e = extractExpertFromResultSet(rs);
	                    list.add(e);
	                }
	                System.out.println("Found experts = " + list.size());
	            }
	        } catch (Exception ex) { ex.printStackTrace(); }
	        return list;
	    }
	    public boolean deleteExpert(int expertId)
	    {
	        boolean status=false;

	        try
	        {
	            Connection con =
	                    DBUtil.getConnection();

	            PreparedStatement ps=
	            con.prepareStatement(
	            "DELETE FROM EXPERTS " +
	            "WHERE EXPERT_ID=?");

	            ps.setInt(1,expertId);

	            status=
	            ps.executeUpdate()>0;
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }

	        return status;
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




}
