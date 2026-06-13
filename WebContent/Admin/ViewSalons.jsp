<%@ page import="java.util.*, com.proj.bsb.bean.Salon" %>
<%
    List<Salon> salons = (List<Salon>) request.getAttribute("salons");
    int salonCount = (Integer) request.getAttribute("salonCount");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Salon Management</title>
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
           class="nav-button active">
            View Salons
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewExperts"
           class="nav-button">
            View Experts
        </a>

        <a href="${pageContext.request.contextPath}/AdminServlet?action=viewCustomers"
           class="nav-button">
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

    <div class="main-content">
    <div class="top-header">

    <div>
        <h1>Salon Management</h1>
        <p>Manage salon information and approvals</p>
    </div>

</div>

<div class="stats">

    <div class="stat-card">
        <h3>Total Salons</h3>
        <h1><%= salonCount %></h1>
    </div>

</div>

<div class="card">
   
  <div class="search-section">

<form method="get"
      action="AdminServlet"
      class="form-inline">

    <input type="hidden"
           name="action"
           value="viewSalons">

    <input type="text"
           name="location"
           class="form-control"
           placeholder="Filter by Location">

    <button type="submit"
            class="btn btn-primary">
        Filter
    </button>

</form>

<button class="btn btn-success"
        onclick="openModal()">
    + Add Salon
</button>

</div>
    <c:if test="${param.msg != null}">
    <div class="alert alert-info">${param.msg}</div>
    </c:if>
    
    

    <%
        if (salons != null && !salons.isEmpty()) {
    %>
    <div class="table-responsive">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                    <th>Address</th>
                    <th>Contact No</th>
                    <th>Photo</th>
                    
                    <th>UserName</th>
                    
                </tr>
            </thead>
            <tbody>
            <%
                for (Salon salon : salons) {
            %>
                <tr>
                    <td><%= salon.getSalonId() %></td>
                    <td><%= salon.getSalonName() %></td>
                    <td><%= salon.getLocation() %></td>
                    <td><%= salon.getLatitude() %></td>
                    <td><%= salon.getLongitude() %></td>
                    <td class="address-column"><%= salon.getAddress() %></td>
                    <td><%= salon.getContactNo() %></td>
                    <td><img src="ImageServlet?id=<%= salon.getSalonId() %>&type=salon" width="80" height="80" alt="Salon Photo"/></td>
                    
            
                    <td>
    <div class="action-buttons">
    
    <%
if("Approved".equalsIgnoreCase(salon.getStatus())) {
%>
      <span style="
            background:#d4edda;
            color:#155724;
            padding:8px 12px;
            border-radius:20px;
            font-weight:bold;">
             Approved
        </span>
<%
} else {
%>

        <a href="AdminServlet?action=editSalon&id=<%= salon.getSalonId() %>"
           class="btn btn-warning btn-sm">
            Edit
        </a>

        <a href="AdminServlet?action=deleteSalon&id=<%= salon.getSalonId() %>"
           class="btn btn-danger btn-sm"
           onclick="return confirm('Delete this salon?')">
            Delete
        </a>

        <button class="btn btn-success btn-sm"
                onclick="approveopenModal(<%= salon.getSalonId() %>)">
            Approve
        </button>
        <%
}
%>

    </div>
</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
        </div>
        </div>

   
    <%
        } else {
    %>
    <p class="text-muted">No salons found.</p>
    <%
        }
    %>
</div>


<div id="addSalonModal" class="modal">
    <div class="modal-dialog">
        <form method="post" action="AdminServlet" enctype="multipart/form-data">
            <input type="hidden" name="action" value="addSalon">
            <div class="modal-header">
                <span class="modal-title">Add Salon</span>
                <button type="button" class="close" onclick="closeModal()">&times;</button>
            </div>
            <div class="modal-body">
                <input type="text" name="name" class="form-control" placeholder="Salon Name" required>
                <input type="text" name="location" class="form-control" placeholder="Location" required>
                <input type="text" name="lat" class="form-control" placeholder="Latitude" required>
                <input type="text" name="lng" class="form-control" placeholder="Longitude" required>
                <input type="text" name="address" class="form-control" placeholder="Address" required>
                <input type="text" name="contact" class="form-control" placeholder="Contact No" required>
                
                
                <input type="file" name="photo" class="form-control" required>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="closeModal()">Cancel</button>
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </div>
</div>

<script>
    function openModal() {
        document.getElementById('addSalonModal').classList.add('active');
    }
    function closeModal() {
        document.getElementById('addSalonModal').classList.remove('active');
    }
</script>


<div id="approveSalonModal" class="modal">
    <div class="modal-dialog">
        <form method="post" action="AdminServlet" enctype="multipart/form-data">
            <input type="hidden" name="action" value="approveSalon">
            <div class="modal-header">
                <span class="modal-title">Approve Salon</span>
                <button type="button" class="close" onclick="approvecloseModal()">&times;</button>
            </div>
            <div class="modal-body">
                <input type="text" id="approve_salon_id" name="salonId" class="form-control" placeholder="Salon ID" readonly>
                <input type="text" name="username" class="form-control" placeholder="User Name" required>
                <input type="text" name="password" class="form-control" placeholder="Password" required>
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="approvecloseModal()">Cancel</button>
                <button type="submit" class="btn btn-primary">Approve</button>
            </div>
        </form>
    </div>
</div>

<script>
function approveopenModal(salonId) {
    document.getElementById('approveSalonModal').classList.add('active');
    document.getElementById('approve_salon_id').value = salonId;
}

function approvecloseModal() {
    document.getElementById('approveSalonModal').classList.remove('active');
}
</script>
</div> <!-- main-content -->

</body>
 <!-- container-layout -->
</html>
