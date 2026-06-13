<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Be a Partner</title>
       <link rel="stylesheet" type="text/css"  href="assets/css/beapartner.css"/>
</head>
<body>

<header>
    <h1>Partner with WrapZoid</h1>
    <p>Grow your salon business with India's fast-growing beauty service platform</p>
</header>
<div class="partner-form-container">
    
    <form action="SalonSearchServlet" method="get">
        <div class="form-row">
            <input type="text" name="country" placeholder="Country" required />
            <select name="emirates" required>
              <option value="" >--Select Emirates--</option>
              <option value="Abu Dhabi">Abu Dhabi</option>
              <option value="Dubai">Dubai</option>
              <option value="Sharjah">Sharjah</option>
              <option value="Ajman">Ajman</option>
              <option value="Umm Al-Quwain">Umm Al-Quwain</option>
              <option value="Ras Al Khaimah">Ras Al Khaimah</option>
              <option value="Fujairah">Fujairah</option>
             </select>
            <input type="text" name="area" placeholder="Area" required />
            <input type="text" name="category" placeholder="Category" required />
        </div>

        <div class="form-row">
            <input type="text" name="firstName" placeholder="First Name" required />
            <input type="text" name="lastName" placeholder="Last NAME" required />
        </div>

        <div class="form-row">
            <input type="text" name="companyName" placeholder="Company Name" required />
            <input type="text" name="contactNo" placeholder="Contact No" required />
        </div>

        <div class="form-row">
            <textarea name="message" placeholder="Message" required></textarea>
        </div>

        <div class="submit-btn">
         
            <button type="submit" name="action" value="CustBePartner" >Submit</button>
        </div>
    </form>
</div>

</body>
</html>
