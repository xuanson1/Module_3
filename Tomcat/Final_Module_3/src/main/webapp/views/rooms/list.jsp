<%@ page import="java.util.List" %>
<%@ page import="com.example.final_module_3.entity.Room" %>
<%@ page import="com.example.final_module_3.entity.PaymentType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    List<Room> rooms = (List<Room>) request.getAttribute("rooms");
    int limit = request.getParameter("limit") != null ? Integer.parseInt(request.getParameter("limit")) : 5;
    Integer totalRooms = (Integer) request.getAttribute("totalRooms");
    if (totalRooms == null) {
        totalRooms = 0;
    }
    int totalPage = (int) Math.ceil((double) totalRooms / limit);
    int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room List</title>
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
    <h2 class="my-4 text-center">Room List</h2>
    <a href="/rooms/create" class="btn btn-create btn-lg mb-3">Create</a>

    <form action="/rooms/search" method="get" class="mb-4">
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
            <th>STT</th>
            <th>Room ID</th>
            <th>Tenant Name</th>
            <th>Phone Number</th>
            <th>Start Date</th>
            <th>Payment Type</th>
            <th>Notes</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="index" value="1"/>
        <c:forEach var="room" items="${rooms}">
            <tr>
                <td>${index}</td>
                <td><c:out value="${room.roomId}"/></td>
                <td><c:out value="${room.tenantName}"/></td>
                <td><c:out value="${room.phoneNumber}"/></td>
                <td><c:out value="${room.startDate}"/></td>
                <td><c:out value="${room.paymentType.paymentTypeName}"/></td>
                <td><c:out value="${room.notes}"/></td>
                <td>
                    <a class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete?')" href="/rooms/delete?id=<c:out value="${room.roomId}"/>">Delete</a>
                </td>
            </tr>
            <c:set var="index" value="${index + 1}"/>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination">
        <% for (int i = 1; i <= totalPage; i++) { %>
        <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
            <a class="page-link" href="/rooms?page=<%= i %>&limit=<%= limit %>"><%= i %></a>
        </li>
        <% } %>
        <li class="page-item <%= (currentPage == totalPage) ? "disabled" : "" %>">
            <a class="page-link" href="/rooms?page=<%= currentPage + 1 %>&limit=<%= limit %>">Next</a>
        </li>
    </ul>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.11/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
