<%--
  Created by IntelliJ IDEA.
  User: Windows
  Date: 23/08/2024
  Time: 8:47 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Room</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            max-width: 800px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Create New Room</h2>

    <form action="${pageContext.request.contextPath}/rooms/create" method="post">
        <div class="form-group">
            <label for="tenantName">Tenant Name</label>
            <input type="text" id="tenantName" name="tenantName" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="phoneNumber">Phone Number</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="startDate">Start Date</label>
            <input type="date" id="startDate" name="startDate" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="paymentTypeId">Payment Type</label>
            <select id="paymentTypeId" name="paymentTypeId" class="form-control" required>
                <c:forEach var="paymenttype" items="${paymenttypes}">
                    <option value="${paymenttype.paymentTypeId}">${paymenttype.paymentTypeName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="notes">Notes</label>
            <textarea id="notes" name="notes" class="form-control"></textarea>
        </div>

        <button type="submit" class="btn btn-success">Create Room</button>
        <a href="${pageContext.request.contextPath}/rooms/list" class="btn btn-secondary">Back to List</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
