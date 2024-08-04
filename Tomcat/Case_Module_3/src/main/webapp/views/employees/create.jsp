<%@ page import="com.example.case_module_3.entity.Employee" %>
<%@ page import="com.example.case_module_3.entity.Category" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows
  Date: 03/08/2024
  Time: 1:11 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>
<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Employee</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 600px;
            margin: auto;
        }
    </style>
</head>
<body>
<div class="container form-container">
    <h2 class="my-4 text-center">Create Employee</h2>
    <form action="/employees/create" method="post">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="dateOfBirth">Date of Birth:</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="hireDate">Hire Date:</label>
            <input type="date" id="hireDate" name="hireDate" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="number" id="salary" name="salary" class="form-control" step="0.01" required>
        </div>
        <div class="form-group">
            <label for="category_id">Category:</label>
            <select id="category_id" name="category_id" class="form-control" required>
                <c:forEach var="category" items="${categories}">
                    <option value="<c:out value="${category.categoryId}"/>"><c:out value="${category.categoryName}"/></option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="/employees" class="btn btn-secondary">Back to List</a>

    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.11/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>