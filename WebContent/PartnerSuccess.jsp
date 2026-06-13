<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
body
{
background: url("sw.jpg"); no-repeat;
background-attachment:fixed;
		background-size:100% 100%;
}
.details
{
background-color: #000;
     
      color: white;
      opacity: .9;
      width: 400px;
      height: 250px;
      margin-left:38%;
      margin-top:10%;
      text-align:center;
      border:2px black;

}
.previous 
{
 margin-left:130px;
text-decoration:none;
 border:none ;
 width:150px;
 height:20px;
 color: white;
 justify-content:center;
 background-color:firebrick;
}
.previous a
{
 text-decoration:none;
 border:none ;
 color: white;
 justify-content:center;

}
.details p
{
padding-top:100px;
padding-bottom:20px;

}
</style>

</head>
<body>

<div class="details">
<p>Thank you for your interest. We will get back to you shortly.</p>
<div class="previous">
<a href="SalonSearchServlet?action=loadHome" >Home</a>
</div>
</div>


</body>
</html>