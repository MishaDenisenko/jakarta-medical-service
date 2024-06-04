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
    <title>Home Page</title>
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous">
    <link rel="stylesheet" href="../../styles/styles.css">
    <link rel="stylesheet" href="../../styles/header.css">
    <link rel="stylesheet" href="../../styles/home.css">
</head>
<body>
<header class="header__container">
    <nav class="header__nav">
        <div class="header__nav-item">
            <a href="<c:url value='/home'/>" class="header__link">Home</a>
            <c:if test="${role == 'USER'}">
                <a href="<c:url value='/my-appointments'/>" class="header__link">My appointments</a>
            </c:if>
        </div>
        <div class="header__nav-item">
            <span class="header__link">${login}</span>
            <a href="<c:url value='/logout'/>" class="header__link">Logout</a>
        </div>
    </nav>
</header>
<div class="header__container" id="root">
    <h1 class="title">Doctors</h1>

    <table class="table">
        <thead>
        <tr>
            <th style="border-bottom: 0;" scope="col">#</th>
            <th style="border-bottom: 0;" scope="col">Name</th>
            <th style="border-bottom: 0;" scope="col">Profession</th>
            <th style="border-bottom: 0;" scope="col">Page</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="doctor" items="${doctorList}">
            <tr>
                <th scope="row">${doctor.id}</th>
                <td>${doctor.name}</td>
                <td>${doctor.profession}</td>
                <td><a href="<c:url value='/doctor?id=${doctor.id}'/>" class="home__link">see more</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
