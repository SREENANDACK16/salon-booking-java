<%@ page import="java.util.List" %>
<%@ page import="com.proj.bsb.bean.SalonCategory" %>
<%@ page import="com.proj.bsb.bean.Salon" %>
<%
    List<SalonCategory> list = (List<SalonCategory>) request.getAttribute("salonCategoryList");
    List<Salon> salonNames = (List<Salon>) request.getAttribute("salonNames");
    int total = (list != null) ? list.size() : 0;
    String selectedSalon = request.getParameter("filterSalon");
%>
<html>
<head>
    <title>Salon-Category Allocations</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f1f2f6;
            color: #333;
        }
        .container-layout{
    display:flex;
    min-height:100vh;
}

.sidebar{
    width:240px;
    background:#1f2937;
    color:white;
    padding:20px;
    flex-shrink:0;
}

.sidebar h2{
    margin-bottom:30px;
}

.nav-button{
    display:block;
    color:white;
    text-decoration:none;
    padding:12px;
    margin-bottom:8px;
    border-radius:8px;
    background:#374151;
}

.nav-button:hover,
.nav-button.active{
    background:#4b5563;
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
    background:white;
    padding:20px;
    border-radius:12px;
    box-shadow:0 2px 10px rgba(0,0,0,.1);
}

.search-section{
    display:flex;
    justify-content:space-between;
    align-items:center;
    margin-bottom:20px;
    flex-wrap:wrap;
    gap:10px;
}

.table-responsive{
    overflow-x:auto;
}

        .container {
            max-width: 1300px;
            margin: 30px auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        h4 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .badge {
            background-color: #007bff;
            color: #fff;
            padding: 5px 12px;
            border-radius: 50px;
            font-size: 14px;
        }

        form.form-inline {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            margin-bottom: 20px;
        }

        .form-control {
            padding: 8px 12px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 200px;
        }

        .btn {
            padding: 8px 16px;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .btn-primary { background-color: #007bff; color: white; }
        .btn-success { background-color: #28a745; color: white; text-decoration:none; }
        .btn-warning { background-color: #ffc107; color: black; }
        .btn-danger { background-color: #dc3545; color: white; }
        .btn-secondary { background-color: #6c757d; color: white; }

        .btn:hover {
            opacity: 0.9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        table th, table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
            vertical-align: middle;
        }

        table thead {
            background-color: #343a40;
            color: white;
        }

        td.address-column {
            max-width: 200px;
            white-space: normal;
            word-wrap: break-word;
        }
        .action-buttons{
    display:flex;
    gap:8px;
    justify-content:center;
    align-items:center;
    flex-wrap:wrap;
}

.action-buttons .btn{
    min-width:80px;
}

        img {
            border-radius: 8px;
            object-fit: cover;
        }

        .text-muted {
            color: #888;
            text-align: center;
            margin-top: 20px;
        }

        /* Modal Styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 999;
            left: 0; top: 0;
            width: 100%; height: 100%;
            background-color: rgba(0,0,0,0.5);
            justify-content: center;
            align-items: center;
        }

        .modal.active {
            display: flex;
        }

        .modal-dialog {
            background: #fff;
            padding: 20px;
            border-radius: 12px;
            width: 400px;
            max-width: 90%;
            box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        }

        .modal-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .modal-title {
            font-size: 18px;
            font-weight: bold;
        }

        .close {
            cursor: pointer;
            font-size: 22px;
            font-weight: bold;
            border: none;
            background: none;
        }

        .modal-body input {
            width: 95%;
            margin-bottom: 10px;
        }

        .modal-footer {
            display: flex;
            
            justify-content: flex-end;
            gap: 10px;
        }
        .table-responsive a
        {
        text-decoration:none;
        }
    </style>
</head>
<body>

<div class="container-layout">

    <!-- Sidebar -->
    <div class="sidebar">

        <h2>Salon Admin</h2>

        <a href="AdminServlet?action=loadDashboard" class="nav-button">
            Dashboard
        </a>

        <a href="AdminServlet?action=viewSalons" class="nav-button">
            View Salons
        </a>

        <a href="AdminServlet?action=viewExperts" class="nav-button">
            View Experts
        </a>

        <a href="AdminServlet?action=viewCustomers" class="nav-button">
            View Customers
        </a>

        <a href="AdminServlet?action=viewBookings" class="nav-button">
            View Bookings
        </a>

        <a href="AdminServlet?action=viewCategories" class="nav-button">
            Categories
        </a>

        <a href="AdminServlet?action=viewSalonCategory"
           class="nav-button active">
            Allot Salons
        </a>

        <a href="AdminServlet?action=Signout"
           class="nav-button">
            Sign Out
        </a>

    </div>

    <!-- Main Content -->
    <div class="main-content">

        <div class="top-header">
            <div>
                <h1>Salon Category Allocation</h1>
                <p>Manage salon category allocations</p>
            </div>
        </div>

        <div class="stats">
            <div class="stat-card">
                <h3>Total Allocations</h3>
                <h1><%= total %></h1>
            </div>
        </div>

        <div class="card">

            <div class="search-section">

                <!-- Search -->
                <form action="AdminServlet"
                      method="get"
                      class="form-inline">

                    <input type="hidden"
                           name="action"
                           value="searchSalonCategory">

                    <input type="text"
                           name="searchSalon"
                           class="form-control"
                           placeholder="Search by Salon">

                    <button type="submit"
                            class="btn btn-primary">
                        Search
                    </button>

                </form>

                <!-- Filter -->
                <form action="AdminServlet"
                      method="get"
                      class="form-inline">

                    <input type="hidden"
                           name="action"
                           value="filterBySalon">

                    <select name="filterSalon"
                            class="form-control">

                        <option value="">
                            All Salons
                        </option>

                        <%
                        if (salonNames != null) {
                            for (Salon s : salonNames) {
                        %>

                        <option value="<%= s.getSalonName() %>"
                            <%= (s.getSalonName().equals(selectedSalon))
                            ? "selected" : "" %>>
                            <%= s.getSalonName() %>
                        </option>

                        <%
                            }
                        }
                        %>

                    </select>

                    <button type="submit"
                            class="btn btn-primary">
                        Filter
                    </button>

                </form>

                <!-- Add Allocation -->
                <a href="AdminServlet?action=gotoAddSalonCategory"
                   class="btn btn-success">
                    + Add Allocation
                </a>

            </div>

            <div class="table-responsive">

                <table>

                    <thead>
                        <tr>
                            <th>Salon Name</th>
                            <th>Category Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>

                    <%
                    if (list != null && !list.isEmpty()) {
                        for (SalonCategory sc : list) {
                    %>

                    <tr>

                        <td><%= sc.getSalonName() %></td>

                        <td><%= sc.getCategoryName() %></td>

                        <td>

                            <div class="action-buttons">

                                <form action="AdminServlet"
                                      method="post"
                                      style="display:inline;"
                                      onsubmit="return confirm('Delete allocation?')">

                                    <input type="hidden"
                                           name="action"
                                           value="deleteSalonCategory">

                                    <input type="hidden"
                                           name="salonId"
                                           value="<%= sc.getSalonId() %>">

                                    <input type="hidden"
                                           name="categoryId"
                                           value="<%= sc.getCategoryId() %>">

                                    <button type="submit"
                                            class="btn btn-danger">
                                        Delete
                                    </button>

                                </form>

                            </div>

                        </td>

                    </tr>

                    <%
                        }
                    } else {
                    %>

                    <tr>
                        <td colspan="3"
                            style="text-align:center;">
                            No Allocations Found
                        </td>
                    </tr>

                    <%
                    }
                    %>

                    </tbody>

                </table>

            </div>

        </div>

    </div>

</div>

</body>
</html>
