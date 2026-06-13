package com.proj.bsb.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
	public static Connection getConnection() {
		Connection con=null;
		 try {
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521:xe","System","zinku");  
		   return con;
		 }
		 catch(Exception e) {
			  e.printStackTrace();
		  }
		 return con;
		
	}
	public static void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Overloaded close method to close Connection and Statement only (no ResultSet)
    public static void close(Connection con, Statement stmt) {
        close(con, stmt, null);
    }
	
}

