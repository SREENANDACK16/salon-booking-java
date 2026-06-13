<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, com.proj.bsb.bean.*" %>
<%
String salonname =
(String)session.getAttribute("ownerSalonName");
    if (salonname == null) {
        response.sendRedirect("Salon/SalonLogin.jsp");
        return;
    }

    List<Experts> expertList = (List<Experts>) request.getAttribute("experts");
    int count = expertList != null ? expertList.size() : 0;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Salon View Bookings</title>
    <style>
         body {
            font-family: 'Segoe UI', sans-serif;
            background: #f1f3f6;
            margin: 0;
            padding: 20px;
        }
         .container{
    display:flex;
    min-height:100vh;
}

.sidebar{
    width:240px;
    background:#1f2937;
    color:white;
    padding:20px;
    flex-shrink: 0;
}

.sidebar h2{
    margin-bottom:30px;
}

.sidebar a{
    display:block;
    color:white;
    text-decoration:none;
    padding:12px;
    margin-bottom:8px;
    border-radius:8px;
}

.sidebar a:hover,
.sidebar a.active{
    background:#374151;
}
        
        
        .main-content{
    flex:1;
    padding:30px;
}

.top-header{
    display:flex;
    justify-content:space-between;
    align-items:center;
    margin-bottom:25px;
}

.top-header h1{
    margin:0;
    color:#333;
}

.top-header p{
    margin-top:5px;
    color:#666;
}

.stats{
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(220px,1fr));
    gap:20px;
    margin-bottom:25px;
}

.stat-card{
    background:white;
    padding:20px;
    border-radius:12px;
    box-shadow:0 2px 10px rgba(0,0,0,.1);
}

.stat-card h3{
    margin:0;
    color:#666;
    font-size:15px;
}

.stat-card h1{
    margin-top:10px;
}

.card{
    background:#fff;
    border-radius:12px;
    padding:20px;
    margin-bottom:20px;
    box-shadow:0 2px 10px rgba(0,0,0,.1);
}

.search-section{
    display:flex;
    justify-content:flex-start;
    align-items:center;
}

.search-box{
    min-width:170px;
    padding:10px;
    border:1px solid #ddd;
    border-radius:8px;
}

.btn-filter{
    background:#007bff;
    color:white;
    border:none;
    padding:10px 15px;
    border-radius:8px;
    cursor:pointer;
}

.btn-filter:hover{
    background:#0056b3;
}

.table-responsive{
    overflow-x:auto;
}
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 10px 15px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }
        th {
            background: #343a40;
            color: #fff;
        }
        tr:hover {
            background: #f9f9f9;
        }
        .action-btn {
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            color: #fff;
            cursor: pointer;
        }
        .accept-btn { background: #28a745; }
        .change-btn { background: #ffc107; color: #333; }
    </style>
</head>
<body>
<div class="container">

  <!-- Sidebar -->
    <div class="sidebar">

        <div class="logo">
            <h2><%= salonname %></h2>
        </div>

        <a href="SalonOwnersServlet?action=dashboard">
            Dashboard
        </a>

        <a href="SalonOwnersServlet?action=viewBookings" class="active">
            Bookings
        </a>

        <a href="SalonOwnersServlet?action=viewExperts"
           >
            Experts
        </a>

        <a href="SalonOwnersServlet?action=signout">
            Logout
        </a>

    </div>



<div class="main-content">

    <!-- Header -->
    <div class="top-header">
        <div>
            <h1>Booking Management</h1>
            <p>Manage salon bookings</p>
        </div>
    </div>

    <!-- Stats -->
    <div class="stats">
        <div class="stat-card">
            <h3>Total Bookings</h3>
            <h1><c:out value="${totalCount}" default="0"/></h1>
        </div>
    </div>

  <!-- Filter Card -->
    <div class="card">

        <div class="search-section">

            <form method="get"
                  action="SalonOwnersServlet"
                  style="display:flex;gap:10px;flex-wrap:wrap;">

                <input type="hidden"
                       name="action"
                       value="viewBookings"/>

                <input type="date"
                       name="dateVal"
                       class="search-box">

                <select name="monthVal" class="search-box">
                    <option value="">Month</option>
                    <% for(int i=1;i<=12;i++){ %>
                        <option value="<%=i%>">
                            <%=new java.text.DateFormatSymbols().getMonths()[i-1]%>
                        </option>
                    <% } %>
                </select>

                <select name="yearVal" class="search-box">
                    <option value="">Year</option>
                    <% for(int y=2023;
                          y<=Calendar.getInstance().get(Calendar.YEAR);
                          y++){ %>
                        <option value="<%=y%>"><%=y%></option>
                    <% } %>
                </select>

                <button type="submit"
                        class="btn-filter">
                    Apply Filter
                </button>

            </form>

        </div>

    </div>
    
      <div class="card">

        <div class="table-responsive">

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>User Name</th>
        <th>Phone</th>
        <th>Date</th>
        <th>Time</th>
        <th>Flexible</th>
        <th>Status</th>
        <th>Rating</th>
        <th>Comments</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="b" items="${bookings}">
        <tr>
            <td>${b.id}</td>
            <td>${b.username}</td>
            <td>${b.phone}</td>
            <td>${b.date}</td>
            <td>${b.time}</td>
            <td>${b.flexible}</td>
            <td>${b.status}</td>
            <td><c:out value="${b.rating}" default="-" /></td>
            <td><c:out value="${b.comments}" default="-" /></td>
            
            <td>
         <c:if test="${b.status != 'Cancelled'}">
        <form method="post" action="SalonOwnersServlet" style="display:inline;">
            <input type="hidden" name="action" value="updateBookingStatus"/>
            <input type="hidden" name="salonId" value="${b.salonId}"/>
            <input type="hidden" name="bookingId" value="${b.id}"/>
            <input type="hidden" name="newStatus" value="Approved"/>
            <button class="action-btn accept-btn" type="submit">Accept</button>
        </form>

        <form method="post" action="SalonOwnersServlet" style="display:inline;">
            <input type="hidden" name="action" value="requestChangeTime"/>
            <input type="hidden" name="salonId" value="${b.salonId}"/>
            <input type="hidden" name="bookingId" value="${b.id}"/>
            <button class="action-btn change-btn" type="submit">Change Time</button>  
        </form>
    </c:if>
</td>
            
         
    
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</div>
</div>

<c:if test="${empty bookings}">
    <p>No bookings found.</p>
</c:if>
</div>
</body>
</html>
