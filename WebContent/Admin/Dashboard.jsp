<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Salon Booking</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f1f3f6;
            display: flex;
            min-height: 100vh;
        }
        .menu-toggle{
    display:none;
    position:fixed;
    top:15px;
    left:15px;
    z-index:1001;
    background:#343a40;
    color:white;
    border:none;
    padding:10px 15px;
    border-radius:5px;
    font-size:20px;
    cursor:pointer;
}

.overlay{
    display:none;
    position:fixed;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background:rgba(0,0,0,0.4);
    z-index:998;
}

.overlay.active{
    display:block;
}

        .sidebar {
            width: 240px;
            background-color: #343a40;
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
            width: 100%;
            margin-bottom: 15px;
            padding: 12px;
            background-color: #495057;
            color: white;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s ease;
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

        .card {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            flex: 1;
            min-width: 220px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .card h5 {
            font-size: 16px;
            color: #555;
            margin-bottom: 10px;
        }

        .card h2 {
            font-size: 28px;
            color: #333;
        }

        .chart-card {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        canvas {
            width: 100% !important;
            max-width: 100%;
        }

        @media (max-width:768px){

    .menu-toggle{
        display:block;
    }

    .sidebar{
        position:fixed;
        top:0;
        left:0;
        height:100%;
        z-index:999;
        transform:translateX(-100%);
        transition:0.3s;
    }

    .sidebar.active{
        transform:translateX(0);
    }

    .main{
        width:100%;
        padding:70px 15px 15px 15px;
    }

    .dashboard-row{
        flex-direction:column;
    }

    .card{
        min-width:100%;
    }
}
        }
    </style>
    
</head>
<script>
function toggleSidebar()
{
    document.querySelector(".sidebar")
            .classList.toggle("active");

    document.querySelector(".overlay")
            .classList.toggle("active");
}
</script>
<body>

<button class="menu-toggle" onclick="toggleSidebar()">
    ☰
</button>

<div class="sidebar">
    <h2>Salon Admin</h2>
    <a href="${pageContext.request.contextPath}/AdminServlet?action=loadDashboard" class="nav-button">Dashboard</a>
    <a href="${pageContext.request.contextPath}/AdminServlet?action=viewSalons" class="nav-button">View Salons</a>
    <a href="${pageContext.request.contextPath}/AdminServlet?action=viewExperts" class="nav-button">View Experts</a>
    <a href="${pageContext.request.contextPath}/AdminServlet?action=viewCustomers" class="nav-button">View Customers</a>
    <a href="${pageContext.request.contextPath}/AdminServlet?action=viewBookings" class="nav-button">View Bookings</a>
    <a href="${pageContext.request.contextPath}/AdminServlet?action=viewCategories" class="nav-button">View Categories</a>
    <a href="${pageContext.request.contextPath}/AdminServlet?action=viewSalonCategory" class="nav-button">Allot Salons</a>
    <a href="${pageContext.request.contextPath}/AdminServlet?action=Signout" class="nav-button">Sign Out</a>
</div>
<div class="overlay" onclick="toggleSidebar()"></div>

<div class="main">
    <h1>Admin Dashboard Overview</h1>
    
    <div class="dashboard-row">
        <div class="card">
            <h5>Total Salons</h5>
            <h2><c:out value="${salonCount}" default="0"/></h2>
        </div>
        <div class="card">
            <h5>Total Experts</h5>
            <h2>${expertCount}</h2>
        </div>
        <div class="card">
            <h5>Bookings Today</h5>
            <h2>${bookingToday}</h2>
        </div>
        <div class="card">
            <h5>Happy Customers</h5>
            <h2>${happyCustomers}%</h2>
        </div>
    </div>

    <div class="chart-card">
        <h5>Weekly Booking Statistics</h5>
        <canvas id="bookingChart" height="120"></canvas>
    </div>
</div>

<script>
    const labels = ${weekLabels};
    const data = ${bookingData};

    const ctx = document.getElementById('bookingChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Bookings',
                data: data,
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.1)',
                fill: true,
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
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
