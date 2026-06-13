<%@ page session="true" %>
<%@ page import="java.util.*, com.proj.bsb.bean.Booking" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Bookings</title>
    <link rel="stylesheet" href="assets/css/mybookings.css"/>
    <style>
        .rating-stars input { display: none; }
        .rating-stars label {
            font-size: 18px;
            color: lightgray;
            cursor: pointer;
        }
        .rating-stars input:checked ~ label,
        .rating-stars label:hover,
        .rating-stars label:hover ~ label {
            color: gold;
        }
        .filter-form, .search-form {
            display: inline-block;
            margin-right: 10px;
        }
    </style>
</head>
<body>

<%
    String username = (String) session.getAttribute("name");
%>

<div class="container">
    <h2>My Bookings</h2>

    
<form method="get" action="SalonSearchServlet">
    <input type="hidden" name="action" value="viewMyBookings" />

    
    
    <label>Salon Name:</label>
    <input type="text" name="salon_name" />

    <button type="submit">Filter</button>
</form>
    <table>
        <thead>
            <tr>
                <th>Salon Name</th>
                <th>Date</th>
                <th>Time</th>
                <th>Flexible</th>
                <th>Rating</th>
                <th>Review</th>
                <th>Action</th>
                <th>Status</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="b" items="${myBookings}">
            <tr>
                <td>${b.salonName}</td>
                <td>${b.date}</td>  
                <td>${b.time}</td>  
                <td>${b.flexible}</td>
                 
      <c:choose>
        <c:when test="${b.rating == 0}">
            
            <form method="get" action="SalonSearchServlet">
                <input type="hidden" name="action" value="rateBooking"/>
                <input type="hidden" name="bookingId" value="${b.id}"/>

               
                <td>
                    <div class="rating-stars">
                        <input type="radio" name="rating" value="5" id="star5-${b.id}" required/><label for="star5-${b.id}">★</label>
                        <input type="radio" name="rating" value="4" id="star4-${b.id}"/><label for="star4-${b.id}">★</label>
                        <input type="radio" name="rating" value="3" id="star3-${b.id}"/><label for="star3-${b.id}">★</label>
                        <input type="radio" name="rating" value="2" id="star2-${b.id}"/><label for="star2-${b.id}">★</label>
                        <input type="radio" name="rating" value="1" id="star1-${b.id}"/><label for="star1-${b.id}">★</label>
                    </div>
                </td>

                
                <td>
                    <textarea name="comments" rows="2" cols="20" placeholder="Add Review" required></textarea>
                    <button type="submit">Submit</button>
                </td>
            </form>
        </c:when>

        <c:otherwise>
            
            <td>${b.rating} ★</td>
            <td><c:out value="${b.comments}" default="-" /></td>
        </c:otherwise>
    </c:choose>
    
                <td>
                    <form action="SalonSearchServlet" method="get">
                        <input type="hidden" name="action" value="cancelBooking"/>
                        <input type="hidden" name="bookingId" value="${b.id}"/>
                        <button type="submit" class="cancel-btn">Cancel</button>
                    </form>
                </td>
                
<td>
    <c:choose>
        <c:when test="${b.status eq 'Cancelled'}">
            <span style="color: red;">Cancelled</span>
        </c:when>
        <c:when test="${b.status eq 'Approved'}">
            <span style="color: green;">Approved</span>
        </c:when>
        <c:when test="${b.status eq 'Change Requested (Date)'}">
            <span style="color: orange;">Change Date</span>
        </c:when>
        <c:when test="${b.status eq 'Change Requested (Time)'}">
            <span style="color: orange;">Change Time</span>
        </c:when>
        
        <c:otherwise>
            <span style="color: blue;">${b.status}</span>
        </c:otherwise>
    </c:choose>
</td>
<td>
    <c:if test="${b.status == 'Change Requested (Time)'}">
        <form action="SalonSearchServlet" method="get">
            <input type="hidden" name="action" value="updateTimeFromUser"/>
            <input type="hidden" name="newStatus" value="Updated"/>
            <input type="hidden" name="bookingId" value="${b.id}"/>
            <label>New Time:</label>
            <input type="time" name="newTime" required/>
            <button type="submit">Update</button>
        </form>
    </c:if>
    
    
</td>



            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
