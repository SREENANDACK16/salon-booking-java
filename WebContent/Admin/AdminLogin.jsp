<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body{
    min-height:100vh;
    background:#ffffff;
    display:flex;
    align-items:center;
    justify-content:center;
    font-family:'Segoe UI',sans-serif;
}

.login-card{
    width:400px;
    border:none;
    border-radius:20px;
    box-shadow:0 10px 30px rgba(0,0,0,0.3);
    overflow:hidden;
}

.card-header{
    background:#1f2937;
    color:white;
    text-align:center;
    padding:25px;
}

.logo{
    font-size:50px;
}

.btn-login{
    background:#1f2937;
    color:white;
    width:100%;
    font-weight:bold;
}

.btn-login:hover{
    background:#314158;
    color:white;
}

.form-control{
    border-radius:10px;
}

.form-label{
    font-weight:500;
}

</style>

</head>
<body>

<div class="card login-card">

    <div class="card-header">

        <div class="logo">🔐</div>

        <h3>Admin Login</h3>

        <small>Manage system administration</small>

    </div>

    <div class="card-body p-4">

        <%
        String error = (String)request.getAttribute("error");
        if(error != null){
        %>

        <div class="alert alert-danger">
            <%= error %>
        </div>

        <% } %>

        <form action="${pageContext.request.contextPath}/AdminServlet"
              method="post">

            <input type="hidden"
                   name="action"
                   value="login">

            <div class="mb-3">

                <label class="form-label">
                    Username
                </label>

                <input type="text"
                       name="username"
                       class="form-control"
                       required>

            </div>

            <div class="mb-3">

                <label class="form-label">
                    Password
                </label>

                <input type="password"
                       name="pwd"
                       class="form-control"
                       required>

            </div>

            <button type="submit"
                    class="btn btn-login">
                Login
            </button>

        </form>

    </div>

</div>

</body>
</html>