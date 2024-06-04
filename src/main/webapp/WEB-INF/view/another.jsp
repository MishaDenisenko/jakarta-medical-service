<%--
  Created by IntelliJ IDEA.
  User: mihailden
  Date: 03.06.2024
  Time: 02:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post">
        <h1>${doctor.name}</h1>
        <button type="submit" name="delete">delete</button>
        <button type="submit" name="change">change</button>
        <input type="number"  placeholder="hour" name="hour"><br>
        <input type="number"  placeholder="minutes" name="minutes"><br>
    </form>
</body>
</html>
