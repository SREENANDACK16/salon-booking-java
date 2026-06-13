<%@ page import="java.util.*, com.proj.bsb.bean.Experts" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
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
<meta charset="UTF-8">
    <title><%= salonname %> - Manage Experts</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f1f3f6;
            display: flex;
            min-height: 100vh;
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

.stats{
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(200px,1fr));
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

.search-section{
    display:flex;
    justify-content:space-between;
    align-items:center;
    margin-bottom:20px;
    flex-wrap:wrap;
    gap:10px;
}

.search-box{
    width:300px;
    padding:10px;
    border:1px solid #ddd;
    border-radius:8px;
}
        .card {
            background-color: #fff;
            margin: 40px auto;
            padding: 20px;
            max-width: 98%;
            border-radius: 15px;
            box-shadow: 0 2px 15px rgba(0,0,0,0.1);
        }
        .card-header {
            background-color: #333;
            color: #fff;
            padding: 15px 20px;
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .card-header h4 {
            margin: 0;
        }
        .card-body {
            padding: 20px;
        }
        .table-responsive {
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background-color: #fff;
        }
        thead {
            background-color: #444;
            color: white;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
            vertical-align: middle;
        }
        td img.expert-photo {
            width: 60px;
            height: 60px;
            object-fit: cover;
            border-radius: 50%;
        }
        .btn {
            padding: 5px 10px;
            font-size: 14px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            color: #fff;
            display: inline-block;
        }
         .btn-success { background-color: #28a745; color: white; }
              .btn-primary { background-color: #007bff; color: white; }
        .btn-warning {
            background-color: #ffc107;
        }
        .btn-danger {
            background-color: #dc3545;
        }
        .btn-light {
            background-color: #f8f9fa;
            color: #000;
            font-weight: bold;
            padding: 6px 12px;
        }
        .btn-secondary {
            background-color: #6c757d;
        }
        .text-success {
            color: green;
            font-weight: bold;
        }
        .text-danger {
            color: red;
            font-weight: bold;
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

        .modal-body input,
.modal-body select {
    width: 95%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
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



<div class="container">

    <!-- Sidebar -->
    <div class="sidebar">

        <div class="logo">
            <h2><%= salonname %></h2>
        </div>

        <a href="SalonOwnersServlet?action=dashboard">
            Dashboard
        </a>

        <a href="SalonOwnersServlet?action=viewBookings">
            Bookings
        </a>

        <a href="SalonOwnersServlet?action=viewExperts"
           class="active">
            Experts
        </a>

        <a href="SalonOwnersServlet?action=signout">
            Logout
        </a>

    </div>

    <!-- Main Content -->
    <div class="main-content">
    <div class="top-header">

    <div>
        <h1>Expert Management</h1>
        <p>Manage your salon experts</p>
    </div>

    <a href="SalonOwnersServlet?action=addExpertPage" class="btn btn-success">
    + Add Expert
</a>

</div>
    <div class="card-body">
        <%
int activeCount = 0;
int leaveCount = 0;

if(expertList != null)
{
    for(Experts e : expertList)
    {
        if("Active".equalsIgnoreCase(e.getStatus()))
            activeCount++;

        if("On Leave".equalsIgnoreCase(e.getStatus()))
            leaveCount++;
    }
}
%>

<div class="stats">

    <div class="stat-card">
        <h3>Total Experts</h3>
        <h1><%= count %></h1>
    </div>

    <div class="stat-card">
        <h3>Active Experts</h3>
        <h1><%= activeCount %></h1>
    </div>

    <div class="stat-card">
        <h3>On Leave</h3>
        <h1><%= leaveCount %></h1>
    </div>

</div>

        <div class="table-responsive">
        <div class="search-section">

<form method="get"
      action="SalonOwnersServlet">

    <input type="hidden"
           name="action"
           value="viewExperts">

    <input type="text"
           name="search"
           placeholder="Search Expert Name"
           class="search-box">

    <button  type="submit" class="btn btn-primary">
        Search
    </button>

</form>

</div>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>DOB</th>
                        <th>Specialization</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Qualification</th>
                        <th>Availability</th>
                        <th>Status</th>
                        <th>DOJ</th>
                        
                        <th>Photo</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (expertList != null) {
                        for (Experts e : expertList) { %>
                    <tr>
                        <td><%= e.getExpertId() %></td>
                        <td><%= e.getExpertName() %></td>
                        <td><%= e.getGender() %></td>
                        <td><%= e.getDob() %></td>
                        <td><%= e.getSpecialization() %></td>
                        <td><%= e.getPhone() %></td>
                        <td><%= e.getEmail() %></td>
                        <td><%= e.getQualification() %></td>
                        <td><%= e.getAvailability() %></td>
                        <td>
                            <% if("Active".equalsIgnoreCase(e.getStatus())) { %>
                                <span class="text-success"><%= e.getStatus() %></span>
                            <% } else if("On Leave".equalsIgnoreCase(e.getStatus())) { %>
                                <span class="text-danger"><%= e.getStatus() %></span>
                            <% } else { %>
                                <%= e.getStatus() %>
                            <% } %>
                        </td>
                        <td><%= e.getDoj() %></td>
                        
                        <td>
                           <img src="ImageServlet?type=expert&id=<%= e.getExpertId() %>"
     class="expert-photo"
     onclick="window.open(this.src)"
     style="cursor:pointer;" />
                        </td>
                        <td>
                            <a href="SalonOwnersServlet?action=editExpert&expertId=<%= e.getExpertId() %>" class="btn btn-warning">
    Edit
</a>
                            <a href="SalonOwnersServlet?action=deleteExpert&id=<%= e.getExpertId() %>" 
                               class="btn btn-danger" onclick="return confirm('Delete this expert?')">Delete</a>
                        </td>
                    </tr>
                    <% } } else { %>
                    <tr><td colspan="14">No experts found.</td></tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    </div>
    
   


</div> <!-- main-content -->



</body>
</html>
