<%@ page import="java.util.List" %>
<%@ page import="com.example.case_module_3.entity.Employee" %>
<%@ page import="com.example.case_module_3.entity.Category" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows
  Date: 03/08/2024
  Time: 11:50 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    int limit = request.getParameter("limit") != null ? Integer.parseInt(request.getParameter("limit")) : 5;
    Integer totalEmployee = (Integer) request.getAttribute("totalEmployees");
    if (totalEmployee == null) {
        totalEmployee = 0;
    }
    int totalPage = (int) Math.ceil((double) totalEmployee / limit);
    int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .btn-delete {
            color: red;
            text-decoration: none;
        }
        .btn-create {
            background-color: green;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="my-4 text-center">Employee List</h2>
    <a href="/employees/create" class="btn btn-create btn-lg mb-3">Create</a>

    <form action="/employees/search" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" name="keyword" class="form-control" placeholder="Search by keyword">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
        </div>
    </form>

    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Date of Birth</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Hire Date</th>
            <th>Salary</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="index" value="1"/>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${index}</td>
                <td><c:out value="${employee.firstName}"/></td>
                <td><c:out value="${employee.lastName}"/></td>
                <td><c:out value="${employee.dateOfBirth}"/></td>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.phoneNumber}"/></td>
                <td><c:out value="${employee.hireDate}"/></td>
                <td><c:out value="${employee.salary}"/></td>
                <td><c:out value="${employee.category.categoryName}"/></td>
                <td>
                    <a class="btn btn-warning btn-sm" href="/employees/update?id=<c:out value="${employee.employeeId}"/>">Update</a>
                    <a class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete?')" href="/employees/delete?id=<c:out value="${employee.employeeId}"/>">Delete</a>
                </td>
            </tr>
            <c:set var="index" value="${index + 1}"/>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination">
        <% for (int i = 1; i <= totalPage; i++) { %>
        <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
            <a class="page-link" href="/employees?page=<%= i %>&limit=<%= limit %>"><%= i %></a>
        </li>
        <% } %>
        <li class="page-item <%= (currentPage == totalPage) ? "disabled" : "" %>">
            <a class="page-link" href="/employees?page=<%= currentPage + 1 %>&limit=<%= limit %>">Next</a>
        </li>
    </ul>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.11/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>