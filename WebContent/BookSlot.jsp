<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="com.proj.bsb.bean.Salon" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Salon Slot</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

   
     <link rel="stylesheet" type="text/css"  href="assets/css/bookslot.css"/>
</head>
<body>
    <%
    Salon salon = (Salon) request.getAttribute("salon");
    %>

    <div class="booking-container">
    <h2>Book Slot for Salon </h2>

    <form action="SalonSearchServlet" method="get">

        
        <%
         
            String username = (String) session.getAttribute("name");
            String phone = (String) session.getAttribute("phone");
        %>
        <input type="hidden" name="action" value="bookSlot">
        <input type="hidden" name="salonId" value="${salonId}">

        
        <input type="hidden" name="username" value="<%= username %>">
        <input type="hidden" name="phone" value="<%= phone %>">

        
        <label for="date">Select Date</label>
        <input type="text" id="datepicker" name="date" required >

        
        <label>Select Time</label>
        <div class="time-slots" id="timeSlots">
            <% String[] times = {"09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM"}; 
               for (String time : times) { %>
                <div class="time-slot" onclick="selectTime(this)"><%= time %></div>
            <% } %>
        </div>
        <input type="hidden" name="time" id="selectedTime" required>

      
        <div class="checkbox">
            <input type="checkbox" name="flexible" value="yes" id="flex">
            <label for="flex">I am flexible to different time, subject to availability</label>
        </div>

      <div class="button-wrapper">
   <button type="submit" class="submit-btn">Book Slot</button>
     </div>
    </form>
</div>


<script>
    flatpickr("#datepicker", {
        altInput: true,
        altFormat: "F j, Y",
        dateFormat: "Y-m-d",
        minDate: "today"
    });

    function selectTime(el) {
        // Deselect others
        document.querySelectorAll(".time-slot").forEach(slot => slot.classList.remove("selected"));
        // Select clicked
        el.classList.add("selected");
        // Set hidden input
        document.getElementById("selectedTime").value = el.innerText;
    }
</script>

</body>
</html>
