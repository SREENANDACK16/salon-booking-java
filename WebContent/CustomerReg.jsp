<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="assets/css/customerreg.css"/>

</head>
<body>

<div class="heading">
<h1>REGISTER</h1>
 </div>
<form action="SalonSearchServlet" >

 <div class="content">
  <input type="text" name="name" placeholder="Enter Your Name" required pattern="^[A-Za-z\s]+$" title="Only Alphabet characters"/> <br><br>
  <input type="text" name="phone" placeholder="Enter WhatsApp No" required pattern="[0-9]{10}" title="Please Enter valid mobile number" /> <br><br>
 <button type="submit" name="action" value="CustomerReg" >Register</button>
  <div class="container">
        	<p>Already have an account? <a href="CustomerLog.jsp">Sign in</a></p>
        	
        </div>
        <% String err = (String) request.getAttribute("error"); %>
<% if (err != null) { %>
    <p style="color:red;text-align:center;"><%= err %></p>
<% } %>
</div>
</form>

</body>
</html>