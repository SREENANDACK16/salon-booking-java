<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Assign Category to Salon</title>

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f1f2f6;
        }

        /* MAIN LAYOUT */
        .container-layout {
            display: flex;
            min-height: 100vh;
        }

        /* SIDEBAR (FIXED PROPERLY) */
        .sidebar {
            width: 240px;
            background: #1f2937;
            color: white;
            padding: 20px;
            flex-shrink: 0;

            /* IMPORTANT FIX */
            position: sticky;
            top: 0;
            height: 100vh;
            overflow-y: auto;
        }

        .sidebar h2 {
            margin-bottom: 30px;
            text-align: center;
        }

        .nav-button {
            display: block;
            color: white;
            text-decoration: none;
            padding: 12px;
            margin-bottom: 8px;
            border-radius: 8px;
            background: #374151;
        }

        .nav-button:hover,
        .nav-button.active {
            background: #4b5563;
        }

        /* MAIN CONTENT */
        .main-content {
            flex: 1;
            padding: 30px;
        }

        .top-header {
            margin-bottom: 25px;
        }

        .card {
            background: white;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .form-inline {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            align-items: center;
        }

        .form-control {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 240px;
        }

        .btn {
            padding: 10px 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn-success {
            background: #28a745;
            color: white;
        }

        /* RESPONSIVE FIX (IMPORTANT) */
        @media screen and (max-width: 768px) {

            .container-layout {
                flex-direction: row; /* KEEP SIDE BY SIDE */
            }

            .sidebar {
                width: 200px;
                position: sticky;
                height: 100vh;
            }

            .main-content {
                padding: 15px;
            }

            .form-inline {
                flex-direction: column;
                align-items: stretch;
            }

            .form-control {
                width: 100%;
            }
        }

        /* SMALL MOBILE */
        @media screen and (max-width: 480px) {

            .sidebar {
                width: 180px;
                padding: 10px;
            }

            .nav-button {
                font-size: 13px;
                padding: 10px;
            }
        }

    </style>
</head>

<body>

<div class="container-layout">

    <!-- SIDEBAR -->
    <div class="sidebar">

        <h2>Salon Admin</h2>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=loadDashboard"
           class="nav-button">Dashboard</a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewSalons"
           class="nav-button">View Salons</a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewExperts"
           class="nav-button">View Experts</a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewCustomers"
           class="nav-button">View Customers</a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewBookings"
           class="nav-button">View Bookings</a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewCategories"
           class="nav-button">Categories</a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewSalonCategory"
           class="nav-button active">Allot Salons</a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=Signout"
           class="nav-button">Sign Out</a>

    </div>

    <!-- MAIN -->
    <div class="main-content">

        <div class="top-header">
            <h1>Assign Category to Salon</h1>
        </div>

        <div class="card">

            <form action="AdminServlet" method="post" class="form-inline">

                <input type="hidden" name="action" value="assignSalonCategory">

                <select name="salonId" class="form-control" required>
                    <option value="">Select Salon</option>
                    <c:forEach var="s" items="${salons}">
                        <option value="${s.salonId}">
                            ${s.salonName}
                        </option>
                    </c:forEach>
                </select>

                <select name="categoryId" class="form-control" required>
                    <option value="">Select Category</option>
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.categoryId}">
                            ${c.categoryName}
                        </option>
                    </c:forEach>
                </select>

                <button type="submit" class="btn btn-success">
                    Assign
                </button>

            </form>

        </div>

    </div>

</div>

</body>
</html>