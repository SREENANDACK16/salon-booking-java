package com.proj.bsb.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.proj.bsb.bean.Booking;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.SalonOwner;
import com.proj.bsb.service.SalonOwnerService;


@WebServlet("/SalonOwnersServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,      // 1MB
	    maxFileSize = 5 * 1024 * 1024,        // 5MB
	    maxRequestSize = 10 * 1024 * 1024     // 10MB
	)
public class SalonOwnersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SalonOwnerService service = new SalonOwnerService();
       
   
    public SalonOwnersServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String action = request.getParameter("action");

    	

    	if(action == null)
    	{
    	    response.sendRedirect( "SalonOwnersServlet?action=dashboard");
    	    return;
    	}

    	
    	HttpSession session = request.getSession();


        try {

            switch (action) {
            case "dashboard":

    	        int salonId =
    	        (Integer)session.getAttribute("ownerSalonId");

    	        request.setAttribute(
    	            "totalBookings",
    	            service.getTotalBookings(salonId));

    	        request.setAttribute(
    	            "pendingBookings",
    	            service.getPendingBookings(salonId));

    	        request.setAttribute(
    	            "approvedBookings",
    	            service.getApprovedBookings(salonId));

    	        request.setAttribute(
    	            "cancelledBookings",
    	            service.getCancelledBookings(salonId));

    	        request.setAttribute(
    	            "totalExperts",
    	            service.getTotalExperts(salonId));

    	        request.setAttribute(
    	            "totalCustomers",
    	            service.getTotalCustomers(salonId));

    	        request.setAttribute(
    	            "todayBookings",
    	            service.getTodayBookings(salonId));

    	        request.setAttribute(
    	            "monthBookings",
    	            service.getMonthBookings(salonId));

    	        request.getRequestDispatcher(
    	            "Salon/SalonOwnerDashboard.jsp")
    	            .forward(request,response);

    	    break;

           
            case "viewBookings":

                Integer ownerSalonId =
                (Integer)session.getAttribute("ownerSalonId");

                if(ownerSalonId == null)
                {
                    response.sendRedirect(
                            "Salon/SalonLogin.jsp");
                    return;
                }

                viewBookings(
                        request,
                        response,
                        ownerSalonId);

            break;

            case "signout":

                session.invalidate();

                response.sendRedirect(
                        "Salon/SalonLogin.jsp");

            break;
            case "viewExperts":

                salonId = (Integer) session.getAttribute("ownerSalonId");

                String search = request.getParameter("search");

                List<Experts> experts;

                if(search != null && !search.trim().isEmpty())
                {
                    experts = service.searchExpertsBySalon(
                            salonId,
                            search.trim());
                }
                else
                {
                    experts = service.getExpertsBySalon(salonId);
                }

                request.setAttribute("experts", experts);

                request.getRequestDispatcher(
                        "Salon/SalonViewExperts.jsp")
                        .forward(request, response);

            break;
            case "addExpertPage":

                request.getRequestDispatcher("Salon/AddExpert.jsp")
                       .forward(request, response);

                break;
            case "deleteExpert":

                int expertId =
                Integer.parseInt(
                request.getParameter("id"));

                service.deleteExpert(
                        expertId);

                response.sendRedirect(
                "SalonOwnersServlet?action=viewExperts");

            break;
            case "editExpert":

                expertId = Integer.parseInt(request.getParameter("expertId"));

                Experts expert = service.getExpertById(expertId);

                request.setAttribute("expert", expert);

                request.getRequestDispatcher("Salon/EditExpert.jsp")
                       .forward(request, response);

            break;
            

            default:

                response.sendRedirect(
                        "SalonOwnersServlet?action=dashboard");
        }

        }  catch(Exception e)
        {

            throw new ServletException(e);
        }
    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
    	
    	String action = request.getParameter("action");
    	System.out.println("NAME = " + request.getParameter("name")); 
    	System.out.println("ACTION = " + request.getParameter("action"));
    	
    	  if (action == null) {
              response.sendRedirect("SalonOwnersServlet?action=dashboard");
              return;
          }
      	
      	

      	

              	    switch(action)
              	    {

              	    case "login":

              	        String username=
              	                request.getParameter("username");

              	        String password=
              	                request.getParameter("password");

              	        SalonOwner owner=
              	                service.login(
              	                        username,
              	                        password);

              	        if(owner!=null)
              	        {
              	            

              	            session.setAttribute(
              	                    "ownerUserId",
              	                    owner.getUserId());

              	            session.setAttribute(
              	                    "ownerSalonId",
              	                    owner.getSalonId());

              	            session.setAttribute(
              	                    "ownerUsername",
              	                    owner.getUsername());
              	            session.setAttribute(
              	                    "ownerSalonName",
              	                    owner.getSalonName());

              	            response.sendRedirect(
              	                    "SalonOwnersServlet?action=dashboard");
              	        }
              	        else
              	        {
              	            request.setAttribute(
              	                    "msg",
              	                    "Invalid Username or Password");

              	            request.getRequestDispatcher(
              	                    "Salon/SalonLogin.jsp")
              	                    .forward(request,response);
              	        }

              	        break;
              	      case "updateBookingStatus":

              	            updateBookingStatus(
              	                    request,
              	                    response);

              	        break;

              	        case "requestChangeTime":

              	            updateBookingStatus(
              	                    request,
              	                    response,
              	                    "Change Requested (Time)");

              	        break;
              	        case "addNewExpert":

              	            Experts expert =
              	                    new Experts();

              	            expert.setSalonId(
              	            (Integer)session.getAttribute(
              	                    "ownerSalonId"));

              	            expert.setExpertName(
              	            request.getParameter("name"));

              	            expert.setGender(
              	            request.getParameter("gender"));

              	            expert.setDob(
              	            request.getParameter("dob"));

              	            expert.setSpecialization(
              	            request.getParameter(
              	                    "specialization"));

              	            expert.setPhone(
              	            request.getParameter(
              	                    "phone"));

              	            expert.setEmail(
              	            request.getParameter(
              	                    "email"));

              	            expert.setQualification(
              	            request.getParameter(
              	                    "qualification"));

              	            expert.setAvailability(
              	            request.getParameter(
              	                    "availability"));

              	            expert.setStatus(
              	            request.getParameter(
              	                    "status"));

              	            expert.setDoj(
              	            request.getParameter(
              	                    "doj"));

              	            Part part =
              	            request.getPart("photo");

              	                if (part != null && part.getSize() > 0) {
              	                    try (InputStream input = part.getInputStream()) {

              	                expert.setPhoto(input.readAllBytes());
              	            }}

              	            service.addExpert(expert);

              	            response.sendRedirect(
              	            "SalonOwnersServlet?action=viewExperts");

              	        break;
              	      case "updateExpert":

              	    	   Experts uexp = new Experts();

              	    	    uexp.setExpertId(Integer.parseInt(request.getParameter("expertId")));
              	    	    uexp.setSalonId((Integer) session.getAttribute("ownerSalonId"));

              	    	    uexp.setExpertName(request.getParameter("name"));
              	    	    uexp.setGender(request.getParameter("gender"));
              	    	    uexp.setDob(request.getParameter("dob"));
              	    	    uexp.setSpecialization(request.getParameter("specialization"));
              	    	    uexp.setPhone(request.getParameter("phone"));
              	    	    uexp.setEmail(request.getParameter("email"));
              	    	    uexp.setQualification(request.getParameter("qualification"));
              	    	    uexp.setAvailability(request.getParameter("availability"));
              	    	    uexp.setStatus(request.getParameter("status"));
              	    	    uexp.setDoj(request.getParameter("doj"));

              	    	     part = request.getPart("photo");

              	    	    if (part != null && part.getSize() > 0) {
              	    	        try (InputStream input = part.getInputStream()) {
              	    	            uexp.setPhoto(input.readAllBytes());
              	    	        }
              	    	    } else {
              	    	        Experts old = service.getExpertById(uexp.getExpertId());
              	    	        uexp.setPhoto(old.getPhoto());
              	    	    }

              	    	    service.updateExpert(uexp);

              	    	    response.sendRedirect("SalonOwnersServlet?action=viewExperts");
              	    	    break;
              	  
              	    }
		
	}
	
	 // Bookings
    private void viewBookings(HttpServletRequest request, HttpServletResponse response, int salonId) throws ServletException, IOException {
        String dateVal = request.getParameter("dateVal");
        String monthVal = request.getParameter("monthVal");
        String yearVal = request.getParameter("yearVal");

        List<Booking> bookings;

        try {
            if (dateVal != null && !dateVal.isEmpty()) {
                bookings = service.getBookingsByDate(salonId, dateVal);
            } else if (monthVal != null && !monthVal.isEmpty()) {
                bookings = service.getBookingsByMonth(salonId, monthVal);
            } else if (yearVal != null && !yearVal.isEmpty()) {
                bookings = service.getBookingsByYear(salonId, yearVal);
            } else {
                bookings = service.getBookingsBySalon(salonId);
            }
        } catch (Exception e) {
            bookings = service.getBookingsBySalon(salonId);
            request.setAttribute("error", "Invalid filter input. Showing all bookings.");
        }

        try {
            service.markBookingsAsSeenByOwner(salonId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("ownerBookingNotification", false);
        }

        int totalCount = service.getTotalCount(salonId);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("Salon/SalonViewBookings.jsp").forward(request, response);
    }
    
    
    private void updateBookingStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String newStatus = request.getParameter("newStatus");

        boolean success = service.updateBookingStatus(bookingId, newStatus);
        request.setAttribute(success ? "message" : "error", success ? "Booking status updated." : "Failed to update booking status.");

        int salonId = Integer.parseInt(request.getParameter("salonId"));
        viewBookings(request, response, salonId);
    }

    private void updateBookingStatus(HttpServletRequest request, HttpServletResponse response, String newStatus) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));

        boolean success = service.updateBookingStatus(bookingId, newStatus);
        request.setAttribute(success ? "message" : "error", success ? "Booking status updated." : "Failed to update booking status.");

        int salonId = Integer.parseInt(request.getParameter("salonId"));
        viewBookings(request, response, salonId);
    }

}
