<%@ page session="true" %>
<%
if(session.getAttribute("ownerUserId")==null)
{
    response.sendRedirect("SalonLogin.jsp");
    return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>  <%=session.getAttribute("ownerSalonName")%> Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f1f3f6;
            display: flex;
            min-height: 100vh;
        }

        .sidebar {
            width: 240px;
            background-color: #1f2937;
            color: white;
            padding: 20px;
            flex-shrink: 0;
        }

        .sidebar h2 {
            font-size: 1.4rem;
            margin-bottom: 30px;
        }

        .nav-button {
            display: block;
            margin-bottom: 15px;
            padding: 12px;
            background-color: #495057;
            color: white;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s;
        }

        .nav-button:hover {
            background-color: #6c757d;
        }

        .main {
            flex: 1;
            padding: 30px;
        }

        .main h1 {
            font-size: 28px;
            margin-bottom: 30px;
        }

        .dashboard-row {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            margin-bottom: 30px;
        }

        .card{
    position:relative;
    overflow:hidden;
    transition:.3s;
}

.card:hover{
    transform:translateY(-5px);
}

.card i{
    position:absolute;
    right:15px;
    top:15px;
    font-size:32px;
    opacity:.2;
}

.total{
    border-left:5px solid #0d6efd;
}

.pending{
    border-left:5px solid #ffc107;
}

.approved{
    border-left:5px solid #198754;
}

.cancelled{
    border-left:5px solid #dc3545;
}

.experts{
    border-left:5px solid #6610f2;
}

.customers{
    border-left:5px solid #20c997;
}

.today{
    border-left:5px solid #fd7e14;
}

.month{
    border-left:5px solid #6f42c1;
}

        .chart-card {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        canvas {
            width: 100% !important;
        }
        
    </style>
    <script>
    setTimeout(() => {
    	  if (location.search.includes("action=dashboard")) location.reload();
    	}, 10000); // 30000 ms = 10 seconds
</script>
</head>
<body>

<div class="sidebar">
    <h2>  <%=session.getAttribute("ownerSalonName")%> </h2>
    <a href="${pageContext.request.contextPath}/SalonOwnersServlet?action=dashboard" class="nav-button">Dashboard</a>
    <a href="${pageContext.request.contextPath}/SalonOwnersServlet?action=viewBookings" class="nav-button">View Bookings

    <c:if test="${sessionScope.ownerBookingNotification == true}">
        <i class="fas fa-bell" style="color:gold; font-size: 14px; margin-left: 6px;" title="New Booking Notification"></i>
    </c:if>
</a>
  <a href="${pageContext.request.contextPath}/SalonOwnersServlet?action=viewExperts" class="nav-button">View Experts</a>
    <a href="${pageContext.request.contextPath}/SalonOwnersServlet?action=signout" class="nav-button">Sign Out</a>
</div>

<div class="main">
    <h1>  <%=session.getAttribute("ownerSalonName")%> Dashboard Overview</h1>

   <div class="dashboard-row">

    <div class="card total">
        <i class="fas fa-calendar-check"></i>
        <h5>Total Bookings</h5>
        <h2>${totalBookings}</h2>
    </div>

    <div class="card pending">
        <i class="fas fa-clock"></i>
        <h5>Pending</h5>
        <h2>${pendingBookings}</h2>
    </div>

    <div class="card approved">
        <i class="fas fa-check-circle"></i>
        <h5>Approved</h5>
        <h2>${approvedBookings}</h2>
    </div>

    <div class="card cancelled">
        <i class="fas fa-times-circle"></i>
        <h5>Cancelled</h5>
        <h2>${cancelledBookings}</h2>
    </div>

    <div class="card experts">
        <i class="fas fa-user-tie"></i>
        <h5>Experts</h5>
        <h2>${totalExperts}</h2>
    </div>

    <div class="card customers">
        <i class="fas fa-users"></i>
        <h5>Customers</h5>
        <h2>${totalCustomers}</h2>
    </div>

    <div class="card today">
        <i class="fas fa-calendar-day"></i>
        <h5>Today's Bookings</h5>
        <h2>${todayBookings}</h2>
    </div>

    <div class="card month">
        <i class="fas fa-calendar-alt"></i>
        <h5>This Month</h5>
        <h2>${monthBookings}</h2>
    </div>

</div>
    <div class="chart-card">
        <h5>Weekly Booking Stats</h5>
        <canvas id="salonBookingChart" height="120"></canvas>
    </div>
</div>

<script>
    const labels = ${weekLabels};
    const data = ${bookingData};

    const ctx = document.getElementById('salonBookingChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Bookings',
                data: data,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    precision: 0
                }
            }
        }
    });
</script>

</body>
</html>
