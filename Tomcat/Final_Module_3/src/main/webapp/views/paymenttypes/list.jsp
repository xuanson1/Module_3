<%--
  Created by IntelliJ IDEA.
  User: Windows
  Date: 23/08/2024
  Time: 9:06 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Type List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            max-width: 800px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Payment Type List</h2>

    <div class="mb-4">
        <a href="${pageContext.request.contextPath}/paymenttypes/create" class="btn btn-success">Create New Payment Type</a>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Payment Type ID</th>
            <th>Payment Type Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="paymentType" items="${paymentTypes}">
            <tr>
                <td>${paymentType.paymentTypeId}</td>
                <td>${paymentType.paymentTypeName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/paymenttypes/delete?id=${paymentType.paymentTypeId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this payment type?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</div>
</body>
</html>
