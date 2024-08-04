<%@ page import="java.util.List" %>
<%@ page import="com.example.case_module_3.entity.Employee" %>
<%@ page import="com.example.case_module_3.entity.Category" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows
  Date: 03/08/2024
  Time: 2:08 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>
<% Employee employee = (Employee) request.getAttribute("employee");
%>
<html>
<head>
    <title>Update Employee</title>
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
    <h2 class="my-4 text-center">Update Employee</h2>
    <form action="/employees/update?id=${employee.employeeId}" method="post">
        <div class="form-group">
            <label for="first_name">First Name:</label>
            <input type="text" id="first_name" class="form-control" value="<c:out value="${employee.firstName}"/>" name="first_name" required>
        </div>
        <div class="form-group">
            <label for="last_name">Last Name:</label>
            <input type="text" id="last_name" class="form-control" value="<c:out value="${employee.lastName}"/>" name="last_name" required>
        </div>
        <div class="form-group">
            <label for="date_of_birth">Date of Birth:</label>
            <input type="date" id="date_of_birth" class="form-control" value="<c:out value="${employee.dateOfBirth}"/>" name="date_of_birth" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" class="form-control" value="<c:out value="${employee.email}"/>" name="email">
        </div>
        <div class="form-group">
            <label for="phone_number">Phone Number:</label>
            <input type="text" id="phone_number" class="form-control" value="<c:out value="${employee.phoneNumber}"/>" name="phone_number">
        </div>
        <div class="form-group">
            <label for="hire_date">Hire Date:</label>
            <input type="date" id="hire_date" class="form-control" value="<c:out value="${employee.hireDate}"/>" name="hire_date" required>
        </div>
        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="number" id="salary" class="form-control" value="<c:out value="${employee.salary}"/>" name="salary" step="0.01" required>
        </div>
        <div class="form-group">
            <label for="category_id">Category:</label>
            <select id="category_id" class="form-control" name="category_id" required>
                <c:forEach var="category" items="${categories}">
                    <option value="<c:out value="${category.categoryId}"/>"
                            <c:if test="${employee.categoryId == category.categoryId}">selected</c:if>>
                        <c:out value="${category.categoryName}"/>
                    </option>
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