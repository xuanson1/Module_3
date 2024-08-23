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
    <title>Create Payment Type</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            max-width: 600px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Create New Payment Type</h2>

    <form action="${pageContext.request.contextPath}/paymenttypes/create" method="post">
        <div class="form-group">
            <label for="paymentTypeName">Payment Type Name</label>
            <input type="text" id="paymentTypeName" name="paymentTypeName" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success">Create Payment Type</button>
        <a href="${pageContext.request.contextPath}/paymenttypes/list" class="btn btn-secondary">Back to List</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

