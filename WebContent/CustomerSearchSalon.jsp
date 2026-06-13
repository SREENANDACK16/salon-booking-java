<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Salon Search Results</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
    <link rel="stylesheet" type="text/css" href="assets/css/customersearchsalon.css">
</head>
<body>
<h2>Available Salons</h2>

<c:if test="${empty salons}">
    <p>No salons found for the selected location and category.</p>
</c:if>

<div class="salon-grid">
<c:forEach var="salon" items="${salons}">
    <div class="salon-card">

   
        <c:if test="${not empty salon.photo}">
            <%
                com.proj.bsb.bean.Salon salonObj = (com.proj.bsb.bean.Salon) pageContext.getAttribute("salon");
                byte[] photoBytes = salonObj.getPhoto();
                String base64Image = Base64.getEncoder().encodeToString(photoBytes);
            %>
            <img src="data:image/jpeg;base64,<%= base64Image %>" width="100%" height="150" />
        </c:if>
        <input type="hidden" name="action" value="loadBookingPage">
    <input type="hidden" name="salonId" value="${salon.salonId}">
        
        <h3>${salon.salonName}</h3>
        
        <p><i class="fas fa-location"></i><strong></strong> ${salon.address}<br>
         ${salon.location}<br>
        <i class="fas fa-phone"> </i> ${salon.contactNo}</p>

       
        <iframe
            width="100%"
            height="80"
            frameborder="0"
            style="border:0"
            src="https://www.google.com/maps?q=${salon.latitude},${salon.longitude}&output=embed"
            allowfullscreen>
        </iframe>

    
        <div class="nav-buttons">

                
                <a href="SalonSearchServlet?action=loadBookingPage&salonId=${salon.salonId}">BOOK NOW</a>
            
        </div>

    </div>
</c:forEach>
</div>

<div class="movepage">
<c:if test="${not empty salons and totalPages > 1}">
    <div class="pagination">

        <c:if test="${currentPage > 1}">
            <a href="SalonSearchServlet?action=Search Salons&page=${currentPage - 1}&location=${location}&category=${category}" class="pagination-button">Previous</a>
        </c:if>

        <c:forEach begin="1" end="${totalPages}" var="i">
            <a href="SalonSearchServlet?action=Search Salons&page=${i}&location=${location}&category=${category}" 
               class="pagination-button <c:if test='${i == currentPage}'>active</c:if>'">${i}</a>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <a href="SalonSearchServlet?action=Search Salons&page=${currentPage + 1}&location=${location}&category=${category}" class="pagination-button">Next</a>
        </c:if>

    </div>
</c:if>


</div>
</body>
</html>
