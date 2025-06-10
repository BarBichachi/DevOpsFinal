<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DevOps Final Project - MTA</title>
</head>
<body>
    <h1>DevOps Final Project - Semester B @ MTA</h1>
    <p>Welcome! This is the main page of our DevOps final project.</p>

    <h3>Team Members:</h3>
    <ul>
        <li>Amit Kriaaaaf</li>
        <li>Amit Eliya</li>
        <li>Bar Bichachi</li>
    </ul>

    <form method="get">
        <label for="name">Enter your name:</label>
        <input type="text" id="name" name="username" required />
        <input type="submit" value="Submit" />
    </form>

    <%
        String name = request.getParameter("username");
        if (name != null && !name.trim().isEmpty()) {
    %>
        <h2>Hello, <%= name %>! Thanks for visiting our project page.</h2>
    <%
        }
    %>

    <br>
    <a href="#">Click here to view our project documentation (coming soon)</a>
</body>
</html>
