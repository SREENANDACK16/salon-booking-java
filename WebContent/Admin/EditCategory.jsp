<%@ page import="com.proj.bsb.bean.Category" %>
<%
    Category c = (Category) request.getAttribute("editCategory");
%>
<html>
<head>
    <title>Edit Category</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 500px;
            margin: 60px auto;
            background-color: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        input[type="text"] {
            padding: 10px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 16px;
            outline: none;
        }

        input[type="submit"] {
            padding: 10px;
            border: none;
            border-radius: 8px;
            background-color: #3498db;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        @media (max-width: 600px) {
            .container {
                margin: 30px 15px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Category</h2>
        <form action="AdminServlet" method="post">
            <input type="hidden" name="action" value="updateCategory" />
            <input type="hidden" name="categoryId" value="<%= c.getCategoryId() %>" />
            <input type="text" name="categoryName" value="<%= c.getCategoryName() %>" required />
            <input type="submit" value="Update" />
        </form>
    </div>
</body>
</html>
