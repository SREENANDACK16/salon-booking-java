package com.proj.bsb.controller;

import com.proj.bsb.bean.BePartner;
import com.proj.bsb.bean.Booking;
import com.proj.bsb.bean.CustomerReg;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.Salon;

import com.proj.bsb.service.SalonService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;


public class SalonSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SalonService service = new SalonService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String target = ""; 

        try {
            if (action == null) {
                
               List<Salon> topSalons = service.getTopSalons(4);
               List<Experts> ourExperts = service.getOurExperts(4);
                request.setAttribute("topSalons", topSalons);
                     request.setAttribute("ourExperts", ourExperts);
                     request.getRequestDispatcher("Home.jsp").forward(request, response);
                      return;
                      //response.sendRedirect("SalonSearchServlet?action=loadHome");
            	
            }
            else {
                switch (action) {
                case "loadHome":
                    List<Salon> topSal = service.getTopSalons(4);
                    List<Experts> ourExp = service.getOurExperts(4);
                    request.setAttribute("topSalons", topSal);
                    request.setAttribute("ourExperts", ourExp);
                    target = "Home.jsp";
                    break;
                case "loadCustomerIndex":
                	

                    HttpSession sess = request.getSession(false);
                    if (sess != null && sess.getAttribute("name") != null) {
                        String user = (String) sess.getAttribute("name");

                        List<Booking> bookings = service.getBookingsByUserWithSeenStatus(user);

                        boolean hasNotification = bookings.stream().anyMatch(b ->
                            (
                            		
                                "Approved".equalsIgnoreCase(b.getStatus()) ||
                                "Booked".equalsIgnoreCase(b.getStatus()) ||
                                "Change Requested (Time)".equalsIgnoreCase(b.getStatus())
                            ) &&
                            (b.getSeenByUser() == null || !"Y".equalsIgnoreCase(b.getSeenByUser()))
                        );

                        // Only set to true if there's a new/unseen status
                        sess.setAttribute("bookingNotification", hasNotification);
                    }

                    List<Salon> topSalo = service.getTopSalons(4);
                    List<Experts> ourExpe = service.getOurExperts(4);
                    String usname = request.getParameter("username");

                    request.setAttribute("username", usname);
                    request.setAttribute("topSalons", topSalo);
                    request.setAttribute("ourExperts", ourExpe);

                    target = "CustomerIndex.jsp";
                    break;

                    case "Search Salons":
                    	
                        String location = request.getParameter("location");
                        String category = request.getParameter("category");
                        String latStr = request.getParameter("lat");
                        String lngStr = request.getParameter("lng");

                        Double lat = (latStr != null && !latStr.isEmpty()) ? Double.parseDouble(latStr) : null;
                        Double lng = (lngStr != null && !lngStr.isEmpty()) ? Double.parseDouble(lngStr) : null;

                        List<Salon> salons = service.searchSalons(location, category, lat, lng);
                        if (salons != null && !salons.isEmpty()) {
                        	int page = 1;
                            int recordsPerPage = 9;
                            
                            String pageStr = request.getParameter("page");
                            if (pageStr != null && !pageStr.isEmpty()) {
                                page = Integer.parseInt(pageStr);
                            }
                            int totalSalons = salons.size();
                            int totalPages = (int) Math.ceil((double) totalSalons / recordsPerPage);

                            int start = (page - 1) * recordsPerPage;
                            int end = Math.min(start + recordsPerPage, totalSalons);
                            List<Salon> paginatedSalons = salons.subList(start, end);
                        	
                            request.setAttribute("salons", paginatedSalons);
                            request.setAttribute("currentPage", page);
                            request.setAttribute("totalPages", totalPages);
                            request.setAttribute("location", location); 
                            request.setAttribute("category", category);
                            
                            target = "SearchSalonResult.jsp";//-----------------------------------
                        } else {
                            request.setAttribute("errorMessage", "Salons not Found");
                            target = "SearchError.jsp";
                        }
                        break;
                        case "CustSearch Salons":
                    	
                        String loc = request.getParameter("location");
                        String cat = request.getParameter("category");
                        String latSt = request.getParameter("lat");
                        String lngSt = request.getParameter("lng");

                        Double lati = (latSt != null && !latSt.isEmpty()) ? Double.parseDouble(latSt) : null;
                        Double lngi = (lngSt != null && !lngSt.isEmpty()) ? Double.parseDouble(lngSt) : null;

                        List<Salon> custsalons = service.searchSalons(loc, cat, lati, lngi);
                        if (custsalons != null && !custsalons.isEmpty()) {
                        	int page = 1;
                            int recordsPerPage = 9;
                            
                            String pageStr = request.getParameter("page");
                            if (pageStr != null && !pageStr.isEmpty()) {
                                page = Integer.parseInt(pageStr);
                            }
                            int totalSalons = custsalons.size();
                            int totalPages = (int) Math.ceil((double) totalSalons / recordsPerPage);

                            int start = (page - 1) * recordsPerPage;
                            int end = Math.min(start + recordsPerPage, totalSalons);
                            List<Salon> paginatedSalons = custsalons.subList(start, end);
                        	
                            request.setAttribute("salons", paginatedSalons);
                            request.setAttribute("currentPage", page);
                            request.setAttribute("totalPages", totalPages);
                            request.setAttribute("location", loc); 
                            request.setAttribute("category", cat);
                            
                            target = "CustomerSearchSalon.jsp";//-----------------------------------
                        } else {
                            request.setAttribute("errorMessage", "Salons not Found");
                            target = "CustSearchError.jsp";
                        }
                        break;
                        case "BePartner":
                        	BePartner partner = new BePartner();
                            partner.setCountry(request.getParameter("country"));
                            partner.setEmirates(request.getParameter("emirates"));
                            partner.setArea(request.getParameter("area"));
                            partner.setCategory(request.getParameter("category"));
                            partner.setFirstName(request.getParameter("firstName"));
                            partner.setLastName(request.getParameter("lastName"));
                            partner.setCompanyName(request.getParameter("companyName"));
                            partner.setContactNo(request.getParameter("contactNo"));
                            partner.setMessage(request.getParameter("message"));

                            boolean isSaved = service.registerPartner(partner);

                            if (isSaved) {
                            	target =("PartnerSuccess.jsp");
                            } else {
                            	target =("PartnerError.jsp");
                            }
                            break;
                        case "CustBePartner":
                        	BePartner Cpartner = new BePartner();
                            Cpartner.setCountry(request.getParameter("country"));
                            Cpartner.setEmirates(request.getParameter("emirates"));
                            Cpartner.setArea(request.getParameter("area"));
                            Cpartner.setCategory(request.getParameter("category"));
                            Cpartner.setFirstName(request.getParameter("firstName"));
                            Cpartner.setLastName(request.getParameter("lastName"));
                            Cpartner.setCompanyName(request.getParameter("companyName"));
                            Cpartner.setContactNo(request.getParameter("contactNo"));
                            Cpartner.setMessage(request.getParameter("message"));

                            boolean CisSaved = service.registerPartner(Cpartner);

                            if (CisSaved) {
                            	target =("CustPartnerSuccess.jsp");
                            } else {
                            	target =("CustPartnerError.jsp");
                            }
                            break;
                    case "CustomerReg":
                    	
            			String name= request.getParameter("name");
            			String phone= request.getParameter("phone");
            			
            		    if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            		        
            		        request.setAttribute("error", "Please enter valid name and phone.");
            		        target = "CustomerReg.jsp";
            		        break;
            		    }

            		    
            		    boolean crFlag = service.validateCustomer(name, phone);

            		    if (crFlag) {
            		       
            		        request.setAttribute("message", "Already registered. Please login.");
            		        target = "CustomerLog.jsp";
            		    } else {
            		        
            		        CustomerReg cr = new CustomerReg(name, phone);
            		        boolean flag = service.customerRegDetails(cr); 

            		        if (flag) {
            		           
            		            request.setAttribute("message", "Registration successful. Please login.");
            		            target = "CustomerLog.jsp";
            		        } else {
            		            
            		            request.setAttribute("error", "Please enter valid name and phone.");
            		            target = "CustomerReg.jsp";
            		        }
            		    }
            			break;
                    case "CustomerLog":
                		
                    String logname = request.getParameter("name");
           			 String phn = request.getParameter("phone");
                       
                       CustomerReg cl=service.getRegDetailsByPhone(phn);
                       boolean vcFlag = service.validateCustomer(logname,phn);
                       if (vcFlag) {
                           HttpSession sn = request.getSession();
                           sn.setAttribute("phone", phn);
                           sn.setAttribute("name", logname);
                           List<Salon> topSalons = service.getTopSalons(4);
                           List<Experts> ourExperts = service.getOurExperts(4); // make sure this method exists

                           request.setAttribute("topSalons", topSalons);
                           request.setAttribute("ourExperts", ourExperts);

                           response.sendRedirect("SalonSearchServlet?action=loadCustomerIndex");  
                       } else {
                           target = "LogInvalid.jsp";
                       }
                       return;
                    case "Signout":
                        HttpSession sn = request.getSession(false);
                        if (sn != null) {
                            sn.invalidate();
                        }
                        response.sendRedirect("SalonSearchServlet?action=loadHome");
                        return; 
                    case "loadBookingPage":
                        String salonIdStr = request.getParameter("salonId");
                        if (salonIdStr != null) {
                            int salonId = Integer.parseInt(salonIdStr);
                            Salon salon = service.getSalonById(salonId);  // You need to implement this method
                            if (salon != null) {
                                // Store salon info in session or request
                                request.setAttribute("salonId", salon.getSalonId());
                                request.setAttribute("salonName", salon.getSalonName());
                                
                                // Or if you want to store in session (not recommended for multi-user)
                                // HttpSession session = request.getSession();
                                // session.setAttribute("salonId", salon.getSalonId());
                                // session.setAttribute("salonName", salon.getSalonName());

                                target = "BookSlot.jsp";  // Your booking JSP page
                            } else {
                                request.setAttribute("errorMessage", "Salon not found");
                                target = "Error.jsp";
                            }
                        } else {
                            request.setAttribute("errorMessage", "Salon ID missing");
                            target = "Error.jsp";
                        }
                        break;
  
                    case "bookSlot":
                        int salonId = Integer.parseInt(request.getParameter("salonId"));
                        String username = request.getParameter("username");
                        String ph = request.getParameter("phone");
                        String date = request.getParameter("date");
                        String time = request.getParameter("time");
                        String flexible = request.getParameter("flexible") != null ? "Yes" : "No";

                        Booking booking = new Booking(salonId, username, ph, date, time, flexible);

                        boolean booked = service.bookSlot(booking);

                        if (booked) {
                            

                            target="bookingSuccess.jsp";
                        } else {
                            target="bookingFail.jsp";
                        }
                        break;

                    case "viewMyBookings":
                        try {
                            HttpSession session = request.getSession(false);
                            if (session == null || session.getAttribute("name") == null) {
                                response.sendRedirect("login.jsp");
                                return;
                            }

                            String user = (String) session.getAttribute("name");

                            // ✅ Mark bookings as seen
                            service.markBookingsAsSeen(user);

                            // ✅ Reset notification
                            session.setAttribute("bookingNotification", false);

                            String salonName = request.getParameter("salon_name");

                            List<Booking> myBookings;
                            if (salonName != null && !salonName.trim().isEmpty()) {
                                myBookings = service.getBookingsByUserAndSalon(user, salonName);
                            } else {
                                myBookings = service.getBookingsByUserWithSeenStatus(user);
                            }

                            request.setAttribute("myBookings", myBookings);
                            request.getRequestDispatcher("MyBookings.jsp").forward(request, response);

                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendRedirect("error.jsp");
                        }
                        return;

                    case "rateBooking":
                        try {
                            int rateId = Integer.parseInt(request.getParameter("bookingId"));
                            int rating = Integer.parseInt(request.getParameter("rating"));
                            String comments = request.getParameter("comments");
                            service.updateBookingRating(rateId, rating, comments);

                            response.sendRedirect("SalonSearchServlet?action=viewMyBookings");
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendRedirect("error.jsp");
                            return;
                        }


                    case "cancelBooking":
                        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
                        service.cancelBooking(bookingId);
                        response.sendRedirect("SalonSearchServlet?action=viewMyBookings");
                        return; 
                    case "updateTimeFromUser":
                        int bkId = Integer.parseInt(request.getParameter("bookingId"));
                        String newTime = request.getParameter("newTime");
                        String newStatus = request.getParameter("newStatus"); 
                        service.updateBookingTime(bkId, newTime);
                        service.updateBookingStatus(bkId, newStatus);
                        // Reload bookings after update
                        String uname = (String) request.getSession().getAttribute("name");
                        List<Booking> myBookings = service.getBookingsByUser(uname);
                        request.setAttribute("myBookings", myBookings);
                        request.getRequestDispatcher("MyBookings.jsp").forward(request, response);
                        return;
                        
   

                    
                        
                    default:
                        request.setAttribute("errorMessage", "Invalid action");
                        target = "SearchError.jsp";
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            target = "SearchError.jsp";
        }

        
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
}
}
