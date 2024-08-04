<%@ page import="com.example.elearning.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows
  Date: 24/07/2024
  Time: 8:24 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<User> users = (List<User>) request.getAttribute("users"); %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h1>User List</h1>
    <table>
        <tr>
            <td>#</td>
            <td>Name</td>
            <td>Email</td>
        </tr>
        <%for(User user: users) { %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName()%></td>
            <td><%= user.getEmail()%></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
