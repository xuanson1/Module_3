<%@ page import="com.example.case_module_3.entity.Category" %>
<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: Windows
  Date: 03/08/2024
  Time: 2:52 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% List<Category> categories = (List<Category>) request.getAttribute("categories");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category List </title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="my-4 text-center">List Category</h2>
    <a href="/categories/create" class="btn btn-success mb-3">Create</a>

    <form action="/categories/search" method="get" class="mb-4">
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
            <th>Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="index" value="1"/>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td><c:out value="${index}"/></td>
                <td><c:out value="${category.categoryName}"/></td>
                <td>
                    <a class="btn btn-warning btn-sm" href="/categories/update?id=${category.categoryId}">Update</a>
                    <a class="btn btn-danger btn-sm" href="/categories/delete?id=${category.categoryId}">Delete</a>
                </td>

            </tr>
            <c:set var="index" value="${index + 1}"/>
        </c:forEach>
        </tbody>
    </table>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.11/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
