<%--
  Created by IntelliJ IDEA.
  User: Windows
  Date: 23/08/2024
  Time: 8:09 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .pagination {
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Room List</h2>
    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/rooms/create" class="btn btn-success">Create New Room</a>
    </div>

    <form action="${pageContext.request.contextPath}/rooms/search" method="get" class="mb-4">
        <div class="form-row">
            <div class="col">
                <input type="text" name="keyword" class="form-control" placeholder="Search by tenant name or phone number" value="${param.keyword}">
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>

    <form action="${pageContext.request.contextPath}/rooms/deleteMultiple" method="post">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Select</th>
                <th>Room Code</th>
                <th>Room ID</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Start Date</th>
                <th>Payment Type</th>
                <th>Notes</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td><input type="checkbox" name="roomIds" value="${room.roomId}" /></td>
                    <td>${room.roomCode}</td>
                    <td>${room.roomId}</td>
                    <td>${room.tenantName}</td>
                    <td>${room.phoneNumber}</td>
                    <td>${room.startDate}</td>
                    <td>${room.paymentType.paymentTypeName}</td>
                    <td>${room.notes}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/rooms/delete?id=${room.roomId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this room?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button type="submit" class="btn btn-danger">Delete Selected Rooms</button>
    </form>


</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
