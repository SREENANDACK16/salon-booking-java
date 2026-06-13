package com.proj.bsb.controller;

import java.io.IOException;
import java.io.InputStream;


import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.proj.bsb.bean.Category;
import com.proj.bsb.bean.CustomerReg;
import com.proj.bsb.bean.Experts;
import com.proj.bsb.bean.ManageBooking;
import com.proj.bsb.bean.Salon;
import com.proj.bsb.bean.SalonCategory;
import com.proj.bsb.bean.SalonOwner;
import com.proj.bsb.service.AdminService;


@MultipartConfig

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 AdminService service = new AdminService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
		 
		    AdminService service = new AdminService();
		    try {
		        switch (action) {
		        
		        
		        case "loadDashboard":
		            int salonCount = service.getSalonCount();
		            int expertCount = service.getExpertCount();
		            int bookingToday = service.getTodayBookingCount();
		            int happyCustomers = service.getHappyCustomerPercent();
		            List<Integer> bookingData = service.getWeeklyBookingData();

		            // Set attributes
		            request.setAttribute("salonCount", salonCount);
		            request.setAttribute("expertCount", expertCount);
		            request.setAttribute("bookingToday", bookingToday);
		            request.setAttribute("happyCustomers", happyCustomers);

		            // Convert list to JS array string
		            String bookingDataJson = bookingData.toString(); // e.g. [1, 2, 3, 4, 5, 6, 7]
		            request.setAttribute("bookingData", bookingDataJson);
		            request.setAttribute("weekLabels", "['Sun','Mon','Tue','Wed','Thu','Fri','Sat']");

		            // ✅ Forward to Dashboard.jsp
		            request.getRequestDispatcher("/Admin/Dashboard.jsp").forward(request, response);  // Make sure the folder name matches (lowercase 'admin' or 'Admin')
		            break;

		            case "viewSalons":
		                String location = request.getParameter("location") != null ? request.getParameter("location") : "";
		                List<Salon> salons = service.getSalons(location);
		                System.out.println("salons");
		                int count = service.getSalonCount();
		                request.setAttribute("salons", salons);
		                request.setAttribute("salonCount", count);
		                request.getRequestDispatcher("Admin/ViewSalons.jsp").forward(request, response);
		                break;
		            
		             
                        
		            case "deleteSalon":
		                int id = Integer.parseInt(request.getParameter("id"));
		                System.out.println("id");
		                service.deleteSalon(id);
		                response.sendRedirect("AdminServlet?action=viewSalons");
		                break;
		            case "editSalon":
		   	    	 try {
		   	    	        int salonId = Integer.parseInt(request.getParameter("id"));
		   	    	        Salon edsalon = service.getSalonById(salonId);
		   	    	     System.out.println("edsalon");
		   	    	        if (edsalon == null) {
		   	    	            response.getWriter().println("Salon not found with ID: " + salonId);
		   	    	            return;
		   	    	        }

		   	    	        request.setAttribute("salon", edsalon);
		   	    	        request.getRequestDispatcher("/Admin/EditSalons.jsp").forward(request, response);
		   	    	    } catch (Exception e) {
		   	    	        e.printStackTrace();
		   	    	        response.getWriter().println("Error: " + e.getMessage());
		   	    	    }
		   	    	    break;
		   	    	    
		            case "filterExperts":
		                int sid = request.getParameter("salonId") != null && !request.getParameter("salonId").isEmpty() 
		                          ? Integer.parseInt(request.getParameter("salonId")) : -1;
		                List<Experts> filteredExperts = sid == -1 ? service.getAllExperts() : service.getExpertsBySalonId(sid);
		                request.setAttribute("expertList", filteredExperts);
		                request.getRequestDispatcher("/Admin/ExpertList.jsp").forward(request, response);
		                break;

		          

		            case "viewExperts":
		                List<Experts> expertList = service.getAllExperts();
		                System.out.println("expertList");
		                request.setAttribute("expertList", expertList);
		                request.getRequestDispatcher("/Admin/ExpertList.jsp").forward(request, response);
		                break;
		            case "viewCustomers":
		                String searchPhone = request.getParameter("searchPhone");
		                List<CustomerReg> customers;
		                if (searchPhone != null && !searchPhone.trim().isEmpty()) {
		                    customers = service.searchCustomersByPhone(searchPhone.trim());
		                } else {
		                    customers = service.getAllCustomers();
		                }
		                request.setAttribute("customerList", customers);
		                request.setAttribute("totalCount", customers.size()); 
		                request.getRequestDispatcher("/Admin/ViewCustomers.jsp").forward(request, response);
		                break;
		            case "viewBookings":
		            	String dateFilter = request.getParameter("filterDate"); 
		                String monthFilter = request.getParameter("filterMonth"); 
		                String yearFilter = request.getParameter("filterYear");
		                String searchPhn = request.getParameter("searchPhone");
		                //System.out.println("Month filter: " + monthFilter);
		                List<ManageBooking> bookings;

		                if (searchPhn != null && !searchPhn.trim().isEmpty()) {
		                    bookings = service.searchBookingsByPhone(searchPhn.trim());
		                } else if (dateFilter != null && !dateFilter.isEmpty()) {
		                    bookings = service.getBookingsByDate(dateFilter);
		                } else if (monthFilter != null && !monthFilter.isEmpty()) {
		                    bookings = service.getBookingsByMonth(monthFilter);
		                } else if (yearFilter != null && !yearFilter.isEmpty()) {
		                    bookings = service.getBookingsByYear(yearFilter);
		                } else {
		                    bookings = service.getAllBookings();
		                }

		                int totalCount = service.getBookingCount();
		                request.setAttribute("bookingList", bookings);
		                request.setAttribute("totalCount", totalCount);
		                request.getRequestDispatcher("/Admin/BookingView.jsp").forward(request, response);
		                break;
		            

		            case "allotBooking":
		                int allotId = Integer.parseInt(request.getParameter("id"));
		                service.allotBookingById(allotId);
		                response.sendRedirect("AdminServlet?action=viewBookings");
		                break;
		            case "viewCategories":
		    	        List<Category> categories = service.getAllCategories();
		    	        request.setAttribute("categories", categories);
		    	        request.setAttribute("totalCount", service.getCategoryCount());
		    	        request.getRequestDispatcher("/Admin/ViewCategory.jsp").forward(request, response);
		    	        break;  
		            case "viewSalonCategory":
		                List<SalonCategory> scList = service.getAllSalonCategoryAllocations();
		                List<Salon> salonNames = service.getAllSalonNames();
		                request.setAttribute("salonCategoryList", scList);
		                request.setAttribute("salonNames", salonNames);
		                request.getRequestDispatcher("/Admin/ViewSalonCategory.jsp").forward(request, response);
		                break;
		            case "gotoAddSalonCategory":
		                List<Salon> salonList = service.getAllSalonNames();  
		                List<Category> categoryList = service.getAllCategories(); 
		                request.setAttribute("salonList", salonList);
		                request.setAttribute("categories", categoryList);
		                RequestDispatcher rd = request.getRequestDispatcher("/Admin/AddSalonCategory.jsp");
		                rd.forward(request, response);
		                break;

		                
		            case "searchSalonCategory":
		    	        String searchSalon = request.getParameter("searchSalon");
		    	        List<SalonCategory> searchResult = service.searchSalonCategory(searchSalon);
		    	        System.out.println("searchResult");
		    	        request.setAttribute("salonCategoryList", searchResult);
		    	        request.setAttribute("salonNames", service.getAllSalonNames());
		    	        request.getRequestDispatcher("/Admin/ViewCategory.jsp").forward(request, response);
		    	        break;
		                
		            case "filterBySalon":
		                String filterSalon = request.getParameter("filterSalon");
		                List<SalonCategory> filterResult;
		                if (filterSalon == null || filterSalon.isEmpty()) {
		                    filterResult = service.getAllSalonCategoryAllocations();
		                } else {
		                    filterResult = service.filterSalonCategory(filterSalon);
		                }
		                request.setAttribute("salonCategoryList", filterResult);
		                request.setAttribute("salonNames", service.getAllSalonNames());
		                request.getRequestDispatcher("/Admin/ViewSalonCategory.jsp").forward(request, response);
		                break;
		            case "Signout":
		            	HttpSession session = request.getSession(false);
		                if (session != null) {
		                    session.invalidate();
		                }
		                response.sendRedirect(request.getContextPath() + "/Admin/AdminLogin.jsp");
		                break;

		        }
		    } catch (Exception e) {
		        throw new ServletException("Servlet Error: " + e.getMessage());
		    }
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	    AdminService service = new AdminService();

	    try {
	        switch (action) {
	        
	        case "login":
	        	String username = request.getParameter("username");
	            String pwd = request.getParameter("pwd");

	            boolean isValid = service.validateAdmin(username, pwd);

	            if (isValid) {
	               
	                int salonCount = service.getSalonCount();
	                int expertCount = service.getExpertCount();
	                int bookingToday = service.getTodayBookingCount();
	                int happyCustomers = service.getHappyCustomerPercent();
	                List<Integer> bookingData = service.getWeeklyBookingData();
	                String weekLabels = "['Sun','Mon','Tue','Wed','Thu','Fri','Sat']";
	                
	                String bookingDataJson = bookingData.toString(); 
	               
	                HttpSession session = request.getSession();
	                session.setAttribute("salonCount", salonCount);
	                session.setAttribute("expertCount", expertCount);
	                session.setAttribute("bookingToday", bookingToday);
	                session.setAttribute("happyCustomers", happyCustomers);
	                session.setAttribute("bookingData", bookingDataJson);
	                session.setAttribute("weekLabels", weekLabels);


	                
	                response.sendRedirect("Admin/Dashboard.jsp");
	            } else {
	              
	                request.setAttribute("error", "Invalid username or password");
	                RequestDispatcher rd = request.getRequestDispatcher("/Admin/AdminLogin.jsp");
	                rd.forward(request, response);
	            }
	            break;

	        
	            case "addSalon":
	                Salon salon = new Salon();
	                salon.setSalonName(request.getParameter("name"));
	                salon.setLocation(request.getParameter("location"));
	                salon.setLatitude(Double.parseDouble(request.getParameter("lat")));
	                salon.setLongitude(Double.parseDouble(request.getParameter("lng")));
	                salon.setAddress(request.getParameter("address"));
	                salon.setContactNo(request.getParameter("contact"));
	                

	                Part photoPart = request.getPart("photo");
	                if (photoPart != null && photoPart.getSize() > 0) {
	                    try (InputStream input = photoPart.getInputStream()) {
	                        salon.setPhoto(input.readAllBytes()); 
	                    }
	                }
	                
	                

	                service.addSalon(salon);
	                response.sendRedirect("AdminServlet?action=viewSalons");
	                break;
	                

	            case "deleteSalon":
	                int id = Integer.parseInt(request.getParameter("id"));
	                service.deleteSalon(id);
	                response.sendRedirect("AdminServlet?action=viewSalons");
	                break;
	            case "approveSalon":
	            	try {
	                    int salonId = Integer.parseInt(request.getParameter("salonId"));
	                    String usernm = request.getParameter("username");
	                    String password = request.getParameter("password");

	                    SalonOwner owner = new SalonOwner();
	                    owner.setSalonId(salonId);
	                    owner.setUsername(usernm);
	                    owner.setPassword(password);

	                    service = new AdminService();
	                    boolean success = service.approveSalon(owner);

	                    if (success) {
	                        response.sendRedirect("AdminServlet?action=viewSalons&msg=Salon+approved+successfully");
	                    } else {
	                        response.sendRedirect("AdminServlet?action=viewSalons&msg=Salon+approval+failed");
	                    }

	                } catch (Exception e) {
	                    e.printStackTrace();
	                    response.sendRedirect("AdminServlet?action=viewSalons&msg=Error+occurred");
	                }
	            	break;
	        
	    case "updateSalon":
   	        Salon updatedSalon = new Salon();
   	        updatedSalon.setSalonId(Integer.parseInt(request.getParameter("id")));
   	        updatedSalon.setSalonName(request.getParameter("name"));
   	        updatedSalon.setLocation(request.getParameter("location"));
   	        updatedSalon.setLatitude(Double.parseDouble(request.getParameter("lat")));
   	        updatedSalon.setLongitude(Double.parseDouble(request.getParameter("lng")));
   	        updatedSalon.setAddress(request.getParameter("address"));
   	        updatedSalon.setContactNo(request.getParameter("contact"));
   	        

   	        Part photoParted = request.getPart("photo");
   	        if (photoParted != null && photoParted.getSize() > 0) {
                   try (InputStream input = photoParted.getInputStream()) {
                   	updatedSalon.setPhoto(input.readAllBytes()); 
                   }
               } else {
   	            
   	            byte[] existingPhoto = service.getSalonById(updatedSalon.getSalonId()).getPhoto();
   	            updatedSalon.setPhoto(existingPhoto);
   	        }
   	        

   	        service.updateSalon(updatedSalon);
   	     response.sendRedirect("AdminServlet?action=viewSalons");
   	        break;
	    
	    case "deleteBooking":
            int delId = Integer.parseInt(request.getParameter("id"));
            service.deleteBookingById(delId);
            response.sendRedirect("AdminServlet?action=viewBookings");
            break;
            
	    

	    case "addCategory":
	        String cname = request.getParameter("categoryName");
	        Category newCat = new Category();
	        newCat.setCategoryName(cname);
	        service.addCategory(newCat);
	        response.sendRedirect("AdminServlet?action=viewCategories");
	        break;

	    case "deleteCategory":
	        int delCId = Integer.parseInt(request.getParameter("categoryId"));
	        service.deleteCategory(delCId);
	        response.sendRedirect("AdminServlet?action=viewCategories");
	        break;

	    case "editCategoryForm":
	        int editId = Integer.parseInt(request.getParameter("categoryId"));
	        Category catToEdit = service.getCategoryById(editId);
	        request.setAttribute("editCategory", catToEdit);
	        request.getRequestDispatcher("/Admin/EditCategory.jsp").forward(request, response);
	        break;

	    case "updateCategory":
	        int cid = Integer.parseInt(request.getParameter("categoryId"));
	        String updatedName = request.getParameter("categoryName");
	        Category updatedCat = new Category(cid, updatedName);
	        service.updateCategory(updatedCat);
	        response.sendRedirect("AdminServlet?action=viewCategories");
	        break;
	    

	    case "addSalonCategory":
	        try {
	            int salonId = Integer.parseInt(request.getParameter("salonId"));
	            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

	            boolean added = service.addSalonCategory(salonId, categoryId);

	            if (added) {
	                response.sendRedirect("AdminServlet?action=viewSalonCategory");
	            } else {
	                throw new ServletException("Failed to add salon-category allocation.");
	            }
	        } catch (Exception e) {
	            throw new ServletException("Servlet Error: " + e.getMessage());
	        }
	        break;
	    case "deleteSalonCategory":
	        int delSalonId = Integer.parseInt(request.getParameter("salonId"));
	        int delCategoryId = Integer.parseInt(request.getParameter("categoryId"));
	        service.deleteSalonCategory(delSalonId, delCategoryId);
	        response.sendRedirect("AdminServlet?action=viewSalonCategory");
	        break;
	    
	    default:
            request.setAttribute("error", "Invalid action.");
            RequestDispatcher rd = request.getRequestDispatcher("/Admin/AdminLogin.jsp");
            rd.forward(request, response);
    }
} catch (Exception e) {
    e.printStackTrace();
    throw new ServletException("Servlet Error: " + e.getMessage());
}
	}


}
