<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Salon Booking System</title>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
  <link rel="stylesheet" type="text/css"  href="assets/css/home.css"/>

   
    <script type="text/javascript" src="assets/js/Home.js"></script>
</head>
<body>

   <nav class="navbar">
   
   
    
  <div class="nav-items" id="navItems">
   
    <a href="SalonSearchServlet" id="nav-home">Home</a>
    <a href="Aboutus.jsp" id="nav-about">About Us</a>
     <a href="Services.jsp" id="nav-service">Services</a>
    <a href="ContactUs.jsp" id="nav-contact">Contact Us</a>
    <a href="BeaPartner.jsp" id="nav-partner">Be with us</a>
  </div>
  <div class="language-switcher">
    <select id="languageSelect" onchange="changeLanguage()">
      <option value="en">EN</option>
      <option value="ar">AR</option>
    </select>
  </div>
    <div class="hamburger"  onclick="toggleMobileMenu()">
    <div></div>
    <div></div>
    <div></div>
    
  </div>
</nav>

    <script>
    function toggleMobileMenu() {
      const nav = document.getElementById("navItems");
      nav.classList.toggle("mobile-active");
    }
  </script>
 




    

<header class="search-section">
   
  
   
        <form id="searchForm" action="SalonSearchServlet" method="get">
            <div class="search-box">

                
<input type="text" id="locationInput" name="location" placeholder="📍Select or Use Current Location" readonly>


<select id="locationDropdown" onchange="handleLocationChange()" name="location">
    <option value="" id="loc-default">-- Select Location --</option>
    <option value="current" id="loc-current">📍 Use My Current Location</option>
    <option value="IRITTY" id="loc-ir">IRITTY</option>
    <option value="KANNUR" id="loc-kn">KANNUR</option>
    <option value="MATTANNUR" id="loc-mt">MATTANNUR</option>
     <option value="PAYYANNUR" id="loc-py">PAYYANNUR</option>
    <option value="THALASSERY" id="loc-th">THALASSERY</option>
   <option value="CHAKKARAKAL" id="loc-ch">CHAKKARAKAL</option>
   
</select>


                   

           
                <select name="category" id="categoryDropdown">
    <option value="" id="cat-default">--Categories--</option>
    <option value="BODY TREATMENTS" id="cat-body">BODY TREATMENTS</option>
    <option value="BROWS AND LASHES" id="cat-brows">BROWS AND LASHES</option>
    <option value="HAIR REMOVAL" id="cat-hair-removal">HAIR REMOVAL</option>
    <option value="HAIR SERVICE" id="cat-hair-service">HAIR SERVICE</option>
    <option value="MAKEUP SERVICE" id="cat-makeup">MAKEUP SERVICE</option>
    <option value="MASSAGE THERAPY" id="cat-massage">MASSAGE THERAPY</option>
    <option value="NAIL SERVICE" id="cat-nail">NAIL SERVICE</option>
    <option value="SKIN CARE" id="cat-skin">SKIN CARE</option>
    <option value="TANNING" id="cat-tanning">TANNING</option>
    <option value="WELLNESS SERVICE" id="cat-wellness">WELLNESS SERVICE</option>
    
    
    <c:forEach var="cat" items="${topCategories}">
        <option value="${cat}">${cat}</option>
    </c:forEach>
</select>

               
                <input type="hidden" name="lat" id="lat" />
                <input type="hidden" name="lng" id="lng" />

                <button type="submit" name="action" id="search-btn" value="Search Salons">Search Salons</button>
            </div>
        </form>
</header>


<section class="top-salons">
  <h1 id="section-top-salons">Beauty Salons Near You</h1>
     <div class="salons">
        <c:forEach var="salon" items="${topSalons}">
            <div class="salon-card">
               <img src="ImageServlet?type=salon&id=${salon.salonId}" width="100%" height="150"/>
                <h3 class="salon-name">${salon.salonName}</h3>
                <p class="salon-address">${salon.address}</p>
                <p><span class="label-location">📍 </span><span class="salon-location">${salon.location}</span></p>
                <p>📞 ${salon.contactNo}</p>
           
        <div class="book-buttons">
            
                <input type="hidden" name="action" value="viewSalon">
                <input type="hidden" name="salonId" value="${salon.salonId}">
                <br> <a href="CustomerReg.jsp" >BOOK NOW</a>
           
        </div>
            </div>
            
        </c:forEach>
 </div>
</section>



<section class="allexperts">
    <h1 id="section-experts">Experts</h1>
    <div class="experts">
        <c:forEach var="exp" items="${ourExperts}">
            <div class="expert-card">
               <img src="ImageServlet?type=expert&id=${exp.expertId}" />
                <h3 class="expert-name">${exp.expertName}</h3>
                <p class="expert-details">${exp.specialization}<br>
                (${exp.qualification})</p>
                </div>
                </c:forEach>
                </div>
               
</section>


<footer class="footer">
  <div class="footer-left">
    <p id="footer-left">© All Rights Reserved by ISAM GLOBAL</p>
  </div>

  <div class="footer-center">
    <i class="fab fa-facebook"></i>
    <i class="fab fa-twitter"></i>
    <i class="fab fa-linkedin"></i>
  </div>

  <div class="footer-right">
    <a href="PrivacyPolicy.jsp" id="footer-pp">Privacy Policy</a>
    <a href="T&C.jsp" id="footer-tc">Terms & Conditions</a>
    <a href="Cookies.jsp" id="footer-ck">Cookies</a>
  </div>
</footer>


</body>
</html>
