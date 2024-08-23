<%@ page import="com.example.final_module_3.entity.Room" %>
<%@ page import="com.example.final_module_3.entity.PaymentType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<PaymentType> paymentTypes = (List<PaymentType>) request.getAttribute("paymentTypes"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Room</title>
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
    <h2 class="my-4 text-center">Create Room</h2>
    <form action="/rooms/create" method="post">
        <div class="form-group">
            <label for="roomId">Mã Phòng Trọ:</label>
            <input type="text" id="roomId" name="roomId" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="tenantName">Tên Người Thuê:</label>
            <input type="text" id="tenantName" name="tenantName" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Số Điện Thoại:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="startDate">Ngày Bắt Đầu Thuê:</label>
            <input type="date" id="startDate" name="startDate" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="paymentTypeId">Hình Thức Thanh Toán:</label>
            <select id="paymentTypeId" name="paymentTypeId" class="form-control" required>
                <c:forEach var="paymentType" items="${paymentTypes}">
                    <option value="<c:out value="${paymentType.paymentTypeId}"/>"><c:out value="${paymentType.paymentTypeName}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="notes">Ghi Chú:</label>
            <textarea id="notes" name="notes" class="form-control"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="/rooms" class="btn btn-secondary">Back to List</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.11/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
