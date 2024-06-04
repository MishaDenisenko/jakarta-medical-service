<%--
  Created by IntelliJ IDEA.
  User: mihailden
  Date: 03.06.2024
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous">
    <link rel="stylesheet" href="../../styles/styles.css">
    <link rel="stylesheet" href="../../styles/auth.css">
</head>
<body>

<div class="container auth__container" id="root">

    <h1 class="title">Login</h1><br>
    <form class="auth__form" method="post" action="">

        <div class="auth__inputs">
            <div class="input-group flex-nowrap">
                <input name="login" type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="1">
            </div>
            <div class="input-group flex-nowrap">
                <input name="password" type="password" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="2">
            </div>
        </div>

        <input class="btn btn-outline-primary" type="submit" value="Sign up">
    </form>
</div>

</body>
</html>
