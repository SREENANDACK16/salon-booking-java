<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.proj.bsb.bean.*" %>

<%
Experts e = (Experts) request.getAttribute("expert");
String salonname = (String) session.getAttribute("ownerSalonName");

if (salonname == null) {
    response.sendRedirect("Salon/SalonLogin.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Expert</title>

<style>

body{
    font-family: Arial, sans-serif;
    background:#f0f2f5;
    margin:0;
    padding:30px;
}

.container{
    max-width:700px;
    margin:auto;
}

.card{
    background:#fff;
    border-radius:15px;
    box-shadow:0 2px 15px rgba(0,0,0,0.1);
    overflow:hidden;
}

.card-header{
    background:#333;
    color:#fff;
    padding:15px 20px;
}

.card-header h3{
    margin:0;
}

.card-body{
    padding:20px;
}

.form-control{
    width:100%;
    padding:10px;
    margin-bottom:12px;
    border:1px solid #ccc;
    border-radius:6px;
}

label{
    font-weight:bold;
    font-size:14px;
}

.current-photo img{
    width:100px;
    height:100px;
    border-radius:10px;
    object-fit:cover;
    margin-bottom:10px;
    border:1px solid #ccc;
}

.btn{
    padding:10px 15px;
    border:none;
    border-radius:6px;
    cursor:pointer;
    font-weight:bold;
}

.btn-primary{
    background:#007bff;
    color:white;
}

.btn-secondary{
    background:#6c757d;
    color:white;
}

.btn-primary:hover,
.btn-secondary:hover{
    opacity:0.9;
}

.button-group{
    display:flex;
    gap:10px;
    margin-top:10px;
}

</style>
</head>

<body>

<div class="container">

<div class="card">

    <div class="card-header">
        <h3>Edit Expert - <%= salonname %></h3>
    </div>

    <div class="card-body">

        <form action="SalonOwnersServlet?action=updateExpert"
              method="post"
              enctype="multipart/form-data">

            <input type="hidden" name="expertId" value="<%= e.getExpertId() %>">

            <label>Name</label>
            <input type="text" name="name" class="form-control"
                   value="<%= e.getExpertName() %>" required>

            <label>Gender</label>
            <select name="gender" class="form-control" required>
                <option value="Male" <%= "Male".equals(e.getGender()) ? "selected" : "" %>>Male</option>
                <option value="Female" <%= "Female".equals(e.getGender()) ? "selected" : "" %>>Female</option>
                <option value="Other" <%= "Other".equals(e.getGender()) ? "selected" : "" %>>Other</option>
            </select>

            <label>DOB</label>
            <input type="date" name="dob" class="form-control"
                   value="<%= e.getDob() %>" required>

            <label>Specialization</label>
            <input type="text" name="specialization" class="form-control"
                   value="<%= e.getSpecialization() %>" required>

            <label>Phone</label>
            <input type="text" name="phone" class="form-control"
                   value="<%= e.getPhone() %>" required>

            <label>Email</label>
            <input type="email" name="email" class="form-control"
                   value="<%= e.getEmail() %>" required>

            <label>Qualification</label>
            <input type="text" name="qualification" class="form-control"
                   value="<%= e.getQualification() %>" required>

            <label>Availability</label>
            <select name="availability" class="form-control">
                <option value="Mon-Sat 10:00 AM - 6:00 PM" <%= "Mon-Sat 10:00 AM - 6:00 PM".equals(e.getAvailability()) ? "selected" : "" %>>
                    Mon-Sat 10:00 AM - 6:00 PM
                </option>
                <option value="Mon-Fri 10:00 AM - 7:00 PM" <%= "Mon-Fri 10:00 AM - 7:00 PM".equals(e.getAvailability()) ? "selected" : "" %>>
                    Mon-Fri 10:00 AM - 7:00 PM
                </option>
                <option value="Weekends 10:00 AM - 5:00 PM" <%= "Weekends 10:00 AM - 5:00 PM".equals(e.getAvailability()) ? "selected" : "" %>>
                    Weekends 10:00 AM - 5:00 PM
                </option>
                <option value="Flexible" <%= "Flexible".equals(e.getAvailability()) ? "selected" : "" %>>
                    Flexible
                </option>
            </select>

            <label>Status</label>
            <select name="status" class="form-control">
                <option value="Joined" <%= "Joined".equals(e.getStatus()) ? "selected" : "" %>>Joined</option>
                <option value="Active" <%= "Active".equals(e.getStatus()) ? "selected" : "" %>>Active</option>
                <option value="On Leave" <%= "On Leave".equals(e.getStatus()) ? "selected" : "" %>>On Leave</option>
            </select>

            <label>DOJ</label>
            <input type="date" name="doj" class="form-control"
                   value="<%= e.getDoj() %>" required>

            <label>Current Photo</label><br>
            <div class="current-photo">
                <img src="ImageServlet?type=expert&id=<%= e.getExpertId() %>">
            </div>

            <label>Change Photo</label>
            <input type="file" name="photo" class="form-control">

            <div class="button-group">
                <button type="submit" class="btn btn-primary">Update Expert</button>
                <a href="SalonOwnersServlet?action=viewExperts" class="btn btn-secondary">Cancel</a>
            </div>

        </form>

    </div>

</div>

</div>

</body>
</html>