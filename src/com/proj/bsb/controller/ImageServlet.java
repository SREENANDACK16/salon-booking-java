package com.proj.bsb.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.bsb.util.DBUtil;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        String id = request.getParameter("id");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBUtil.getConnection();

            if ("salon".equals(type)) {
                ps = con.prepareStatement("SELECT photo FROM salon WHERE salon_id=?");
            } 
            else if ("expert".equals(type)) {
                ps = con.prepareStatement("SELECT photo FROM experts WHERE expert_id=?");
            } 
            else {
                response.setContentType("image/png");
                return;
            }

            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();

            if (rs.next()) {

                Blob blob = rs.getBlob("photo");

                if (blob != null) {

                    byte[] imageBytes = blob.getBytes(1, (int) blob.length());

                    response.setContentType("image/jpeg");
                    response.getOutputStream().write(imageBytes);

                } else {

                    // OPTIONAL: default image (ONLY if DB is empty)
                    response.setContentType("image/png");
                    InputStream is = getServletContext()
                            .getResourceAsStream("/assets/img/default-image.jpg");

                    if (is == null) {
                        response.setContentType("text/plain");
                        response.getWriter().write("No Image Found");
                        return;
                    }

                    byte[] buffer = is.readAllBytes();
                    response.setContentType("image/jpeg");
                    response.getOutputStream().write(buffer);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}