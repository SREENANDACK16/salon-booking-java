<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<%
String salonname = (String) session.getAttribute("ownerSalonName");

if (salonname == null) {
    response.sendRedirect("SalonLogin.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Expert</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

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
    box-sizing:border-box;
}

label{
    font-weight:bold;
    font-size:14px;
    display:block;
    margin-bottom:5px;
}

.btn{
    padding:10px 15px;
    border:none;
    border-radius:6px;
    cursor:pointer;
    font-weight:bold;
    text-decoration:none;
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
    margin-top:15px;
}

</style>
</head>

<body>

<div class="container">

    <div class="card">

        <div class="card-header">
            <h3>Add Expert - <%= salonname %></h3>
        </div>

        <div class="card-body">

            <form method="post"
                  action="SalonOwnersServlet?action=addNewExpert"
                  enctype="multipart/form-data">

                <label>Name</label>
                <input type="text"
                       name="name"
                       class="form-control"
                       required>

                <label>Gender</label>
                <select name="gender"
                        class="form-control">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>

                <label>Date of Birth</label>
                <input type="text"
                       id="datepicker1"
                       name="dob"
                       class="form-control"
                       required>

                <label>Specialization</label>
                <input type="text"
                       name="specialization"
                       class="form-control"
                       required>

                <label>Phone</label>
                <input type="text"
                       name="phone"
                       class="form-control"
                       required>

                <label>Email</label>
                <input type="email"
                       name="email"
                       class="form-control"
                       required>

                <label>Qualification</label>
                <input type="text"
                       name="qualification"
                       class="form-control"
                       required>

                <label>Availability</label>
                <select name="availability"
                        class="form-control">
                    <option>Mon-Sat 10:00 AM - 6:00 PM</option>
                    <option>Mon-Fri 10:00 AM - 7:00 PM</option>
                    <option>Weekends 10:00 AM - 5:00 PM</option>
                    <option>Flexible</option>
                </select>

                <label>Status</label>
                <select name="status"
                        class="form-control">
                    <option value="Joined">Joined</option>
                    <option value="Active">Active</option>
                    <option value="On Leave">On Leave</option>
                </select>

                <label>Date of Joining</label>
                <input type="text"
                       id="datepicker2"
                       name="doj"
                       class="form-control"
                       required>

                <label>Photo</label>
                <input type="file"
                       name="photo"
                       class="form-control"
                       accept="image/*"
                       required>

                <div class="button-group">
                    <button type="submit"
                            class="btn btn-primary">
                        Save Expert
                    </button>

                    <a href="SalonOwnersServlet?action=viewExperts"
                       class="btn btn-secondary">
                        Cancel
                    </a>
                </div>

            </form>

        </div>

    </div>

</div>

<script>
flatpickr("#datepicker1", {
    altInput: true,
    altFormat: "F j, Y",
    dateFormat: "Y-m-d"
});

flatpickr("#datepicker2", {
    altInput: true,
    altFormat: "F j, Y",
    dateFormat: "Y-m-d"
});
</script>

</body>
</html>