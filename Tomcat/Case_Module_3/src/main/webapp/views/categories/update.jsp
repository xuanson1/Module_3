<%@ page import="com.example.case_module_3.entity.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Category</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 600px;
            margin: auto;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="my-4 text-center">Update Category</h2>
    <form action="/categories/update" method="post">
        <input type="hidden" name="id" value="<c:out value="${category.categoryId}"/>">
        <div class="form-group">
            <label for="name">Category Name:</label>
            <input type="text" id="name" name="category_name" class="form-control" value="<c:out value="${category.categoryName}"/>">
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="/categories" class="btn btn-secondary">Back to List</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.11/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
