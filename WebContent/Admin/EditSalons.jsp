<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Edit Salon</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h3 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
            font-size:35px;
        }

        input[type="text"], input[type="file"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        label {
            font-weight: 500;
            display: block;
            margin-bottom: 6px;
            color: #555;
        }

        .img-preview {
            margin-bottom: 15px;
            text-align: center;
        }

        .img-preview img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        .button-group {
            
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
            text-decoration:none;
            color: white;
        }

        .btn-secondary:hover {
            background-color: #565e64;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Edit Salon</h3>
    <form action="AdminServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="updateSalon">
        <input type="hidden" name="id" value="${salon.salonId}">

        <label>Salon Name</label>
        <input type="text" name="name" value="${salon.salonName}" required>

        <label>Location</label>
        <input type="text" name="location" value="${salon.location}" required>

        <label>Latitude</label>
        <input type="text" name="lat" value="${salon.latitude}" required>

        <label>Longitude</label>
        <input type="text" name="lng" value="${salon.longitude}" required>

        <label>Address</label>
        <input type="text" name="address" value="${salon.address}" required>

        <label>Contact Number</label>
        <input type="text" name="contact" value="${salon.contactNo}" required>

        <label>Current Photo:</label>
        <div class="img-preview">
            <c:if test="${not empty salon.photo}">
                <img src="data:image/jpeg;base64,${fn:escapeXml(Base64.getEncoder().encodeToString(salon.photo))}" alt="Salon Photo" />
            </c:if>
        </div>
  
        <label>Upload New Photo</label>
        <input type="file" name="photo">
        
        

        <div class="button-group">
            <button type="submit" class="btn btn-primary">Update</button>
            <a href="AdminServlet?action=viewSalons" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
