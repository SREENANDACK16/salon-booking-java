<%@ page import="java.util.*, com.proj.bsb.bean.*" %>
<%
    List<CustomerReg> customerList = (List<CustomerReg>) request.getAttribute("customerList");
    Integer totalCount = (Integer) request.getAttribute("totalCount");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
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
        .btn-success { background-color: #28a745; color: white; }
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

        <a href="${pageContext.request.contextPath}/AdminServlet?action=loadDashboard"
           class="nav-button">
            Dashboard
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewSalons"
           class="nav-button">
            View Salons
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewExperts"
           class="nav-button">
            View Experts
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewCustomers"
           class="nav-button active">
            View Customers
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewBookings"
           class="nav-button">
            View Bookings
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewCategories"
           class="nav-button">
            Categories
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewSalonCategory"
           class="nav-button">
            Allot Salons
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=Signout"
           class="nav-button">
            Sign Out
        </a>

    </div>

    <!-- Main Content -->
    <div class="main-content">

        <div class="top-header">
            <div>
                <h1>Customer Management</h1>
                <p>View and search registered customers</p>
            </div>
        </div>

        <!-- Stats -->
        <div class="stats">
            <div class="stat-card">
                <h3>Total Customers</h3>
                <h1>
                    <%= (totalCount != null) ? totalCount :
                        (customerList != null ? customerList.size() : 0) %>
                </h1>
            </div>
        </div>

        <!-- Main Card -->
        <div class="card">

            <div class="search-section">

                <form action="AdminServlet" method="get" class="form-inline">

                    <input type="hidden"
                           name="action"
                           value="viewCustomers">

                    <input type="text"
                           name="searchPhone"
                           class="form-control"
                           placeholder="Search by Phone"
                           value="<%= request.getParameter("searchPhone") != null ? request.getParameter("searchPhone") : "" %>">

                    <button type="submit"
                            class="btn btn-primary">
                        Search
                    </button>

                </form>

            </div>

            <div class="table-responsive">

                <table>

                    <thead>
                        <tr>
                            <th>Sl No</th>
                            <th>Name</th>
                            <th>Phone</th>
                        </tr>
                    </thead>

                    <tbody>

                    <%
                    if(customerList != null && !customerList.isEmpty()) {

                        int serialNo = 1;

                        for(CustomerReg c : customerList){
                    %>

                        <tr>
                            <td><%= serialNo++ %></td>
                            <td><%= c.getName() %></td>
                            <td><%= c.getPhone() %></td>
                        </tr>

                    <%
                        }
                    } else {
                    %>

                        <tr>
                            <td colspan="3" style="text-align:center;">
                                No customers found
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
